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

    static
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             //connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=CampusConnect", "PreRecDB", "PreRecDB");
            connection = DriverManager.getConnection("jdbc:sqlserver://10.10.10.93;databaseName=CampusConnect", "cconnect", "C0NN3C!@$32");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public AuthorizationFilter() throws SQLException {
    }


    public String getRoleForUser( String username ) throws SQLException
    {
        String sql = "select Role from LoginInfo where Login_name = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            String userRole = resultSet.getString("Role");
            LOGGER.debug(String.format("Role found. Username %s is %s",username, userRole));
            return userRole;
        }

        return "";
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

                try
                {

                    if (this.getRoleForUser(username).equalsIgnoreCase("A"))
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
                catch (SQLException sqlex){
                    LOGGER.error(sqlex.getMessage());
                }
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

    }

}