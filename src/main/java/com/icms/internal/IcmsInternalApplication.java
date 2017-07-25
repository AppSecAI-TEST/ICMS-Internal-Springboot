package com.icms.internal;

import com.icms.internal.authfilter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IcmsInternalApplication {

	@Bean
	@Autowired
	public FilterRegistrationBean authApiFilter(AuthFilter authFilter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(authFilter);
		registration.addUrlPatterns("/**");
		registration.setOrder(1);
		return registration;
	}

	@Bean
	public AuthFilter authFilter(){
		return new AuthFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(IcmsInternalApplication.class, args);
	}
}
