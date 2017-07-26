package com.icms.internal;

import com.icms.internal.authfilter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class IcmsInternalApplication {

//	@Bean
//	@Autowired
//	public FilterRegistrationBean authApiFilter(AuthorizationFilter authorizationFilter) {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(authorizationFilter);
//		registration.addUrlPatterns("/admin/*");
//		registration.setOrder(1);
//		return registration;
//	}
//
//	@Bean
//	public AuthorizationFilter authFilter() throws SQLException {
//		return new AuthorizationFilter();
//	}

	public static void main(String[] args) {
		SpringApplication.run(IcmsInternalApplication.class, args);
	}
}
