/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test.mybatis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.jeesite.dal.dto.UserDO;
import com.github.obullxl.jeesite.service.UserService;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: MyBatisSpringMain.java, V1.0.1 2013年11月25日 下午12:59:37 $
 */
public class MyBatisSpringMain {
    private static final Logger logger = LoggerFactory.getLogger("LOGGER");

    /**
     * @param args
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/mybatis-context.xml");
        UserService service = context.getBean(UserService.class);

        int count = service.count();
        logger.warn("Count value is:{}", count);

        List<UserDO> users = service.findAll();
        logger.warn("All users:\n{}", users);
    }

}
