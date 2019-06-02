/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.config;

import com.softserve.academy.controller.SecurityInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * Config class, sets all WebMvc configurations.
 *
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 29.05.2019
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.softserve.academy"})
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Sets path and extension for view pages.
     *
     * @return internal resource view resolver.
     */
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * Configure resource message source.
     *
     * @return message source.
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

    /**
     * Creates validator for factory bean and sets message source for it.
     *
     * @return validator.
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    /**
     * Add resource path to registry.
     *
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resources/**")
            .addResourceLocations("/resources/");
    }

    /**
     * Add security interceptor path to registry.
     *
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor());
    }
}
