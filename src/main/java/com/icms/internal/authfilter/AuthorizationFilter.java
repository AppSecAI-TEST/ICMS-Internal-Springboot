package com.icms.internal.authfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Infocepts India in 2017.
 */

@Component
public class AuthorizationFilter extends OncePerRequestFilter
{
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);
    private static Connection connection = null;
    private Map<String,String> authorizationList = new HashMap<>();

    static
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             //connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=CampusConnect", "PreRecDB", "PreRecDB");
            connection = DriverManager.getConnection("jdbc:sqlserver://10.10.5.85;databaseName=CampusConnect", "cconnect", "C0NN3C!@$32");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public AuthorizationFilter() throws SQLException {

        String sql = "select Login_name , Role from LoginInfo";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String username = resultSet.getString("Login_name");
            String role = resultSet.getString("Role");

            authorizationList.put(username,role);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException
    {

        String requestUrl = httpServletRequest.getRequestURL().toString();

        if( requestUrl.contains("admin") ) {
            LOGGER.debug(">> " + new Object() {
            }.getClass().getEnclosingMethod().getName());

            LOGGER.debug("Filter Request for : " + requestUrl);

            String authToken = httpServletRequest.getHeader("authToken");

            if(null == authToken){
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
            } else
            {

                String base64Decoded = new String(Base64Utils.decode(authToken.getBytes()));

                String username = base64Decoded.split(":")[0];

                if (this.authorizationList.get(username).equalsIgnoreCase("A"))
                {
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }
                else
                {
                    LOGGER.error(String.format("%s tried to access %s url", username, requestUrl));
                    LOGGER.error("Sending Unauthorized error");
                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
                }
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

    }

}