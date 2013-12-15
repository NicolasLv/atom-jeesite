/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test.mybatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.jeesite.dal.dao.CatgDAO;
import com.github.obullxl.jeesite.dal.dto.CatgDTO;

/**
 * 主题分类批量初始化
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgBatchInitMain.java, V1.0.1 2013年12月13日 下午9:01:17 $
 */
public class CatgBatchInitMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/*.xml");

        CatgDAO catgDAO = context.getBean(CatgDAO.class);
        
        catgDAO.deleteAll();
        List<Long> ids = new ArrayList<Long>(100);

        for (int i = 1; i <= 100; i++) {
            CatgDTO catg = new CatgDTO();
            if (RandomUtils.nextBoolean()) {
                catg.setTop("T");
                catg.setCatg(randomCatgID(ids));
            } else {
                catg.setTop("F");
            }

            catg.setName("分类" + StringUtils.leftPad(Integer.toString(i), 4, "0"));
            long id = catgDAO.insert(catg);

            ids.add(id);
        }
    }

    private static long randomCatgID(List<Long> catgs) {
        if (catgs.isEmpty()) {
            return 0L;
        }

        Collections.shuffle(catgs);

        int idx = RandomUtils.nextInt(catgs.size());
        return catgs.get(idx);
    }

}
