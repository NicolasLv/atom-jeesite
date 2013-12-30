/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Spring注解方式
 * 
 * @author obullxl@gmail.com
 * @version $Id: SpringConfigMain.java, V1.0.1 2013年12月18日 下午1:28:20 $
 */
@Configuration
@Profile("production")
@ComponentScan("com.acme.app.services")
@PropertySource("classpath:/com/acme/app.properties")
@ImportResource("classpath:/com/acme/database-config.xml")
public class SpringConfigMain {

    @Inject
    protected Environment env;

    @Value("${bean.name}")
    protected String      beanName;

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

}
