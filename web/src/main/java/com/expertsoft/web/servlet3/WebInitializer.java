package com.expertsoft.web.servlet3;

import com.expertsoft.core.config.CoreConfiguration;
import com.expertsoft.web.config.SpringSecurityConfig;
import com.expertsoft.web.config.SpringWebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { SpringWebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { CoreConfiguration.class, SpringSecurityConfig.class };
    }

}