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

import com.github.obullxl.jeesite.dal.dao.TopicDAO;
import com.github.obullxl.jeesite.dal.dao.UserDAO;
import com.github.obullxl.jeesite.dal.dto.TopicDO;
import com.github.obullxl.jeesite.dal.dto.UserDO;

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
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/*.xml");

        UserDAO userDAO = context.getBean(UserDAO.class);
        long count = userDAO.count();
        logger.warn("用户中记录数:{}", count);
        List<UserDO> users = userDAO.findAll();
        logger.warn("用户记录列表:\n{}", users);
        
        List<String> unames = userDAO.findName();
        logger.warn("用户名称列表:\n{}", unames);
        
        TopicDAO topicDAO = context.getBean(TopicDAO.class);
        List<TopicDO> topics = topicDAO.findAll();
        logger.warn("主题记录列表:\n{}", topics);
    }

}
