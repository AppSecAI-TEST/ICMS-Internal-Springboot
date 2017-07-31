package com.icms.internal.cachemanager;

import net.rossillo.spring.web.mvc.CacheControlHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Infocepts India in 2017.
 */
@Configuration
public class CacheManagerInterceptor extends WebMvcConfigurerAdapter implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CacheControlHandlerInterceptor());
    }
}

