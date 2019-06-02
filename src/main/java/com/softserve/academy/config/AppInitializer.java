/*
 * This is a simple web application utilizing Spring MVC and Hibernate.
 * Developed by Lv-409.Java group of Softserve Academy.
 *
 * Copyright (c) 1993-2019 Softserve, Inc.
 * This software is the confidential and proprietary information of Softserve.
 *
 */

package com.softserve.academy.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * Config class, loads all application configurations.
 *
 * @author Volodymyr Oseredchuk
 * @version 1.0
 * @since 29.05.2019
 *
 */
public class AppInitializer extends
    AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Set Class object for Hibernate configuration.
     *
     * @return array of Class objects.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {HibernateConfig.class};
    }

    /**
     * Set Class object for WebMvc configuration.
     *
     * @return array of Class objects.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebMvcConfig.class};
    }

    /**
     * Set servlet mappings.
     *
     * @return array of strings.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
