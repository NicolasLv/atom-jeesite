/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test.mybatis;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.jeesite.dal.dao.ConfigDAO;
import com.github.obullxl.jeesite.dal.dto.ConfigDTO;

/**
 * MyBatis与Spring整合测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: MyBatisSpringMain.java, V1.0.1 2013年11月25日 下午12:59:37 $
 */
public class MyBatisSpringMain {

    /**
     * @param args
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/*.xml");

        ConfigDAO configDAO = context.getBean(ConfigDAO.class);

        // 删除所有记录
        configDAO.deleteAll();

        // 插入20条记录
        for (int i = 0; i < 20; i++) {
            ConfigDTO config = new ConfigDTO();

            String idx = StringUtils.leftPad(Integer.toString(i), 2, "0");
            config.setCatg("test");
            config.setName("key" + idx);
            config.setState("T");
            config.setValue("value" + idx);

            long id = configDAO.insert(config);
            System.out.println("插入-" + idx + "-ID: " + id);
        }

        // 单条查询
        ConfigDTO config = configDAO.find("test", "key03");
        System.out.println("单条查询: " + config);

        // 查询所有
        List<ConfigDTO> configs = configDAO.findAll();
        System.out.println("查询所有: " + configs.size());
        System.out.println("查询所有: " + configs);
    }

}
