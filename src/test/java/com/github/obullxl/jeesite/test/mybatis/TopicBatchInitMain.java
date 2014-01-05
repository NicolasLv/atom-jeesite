/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test.mybatis;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.jeesite.dal.dao.TopicDAO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.dal.valve.TopicValve;
import com.github.obullxl.jeesite.web.enums.TopicMediaEnum;
import com.github.obullxl.jeesite.web.enums.TopicReplyEnum;
import com.github.obullxl.jeesite.web.enums.TopicStateEnum;
import com.github.obullxl.lang.enums.ValveBoolEnum;

/**
 * 批量初始化主题
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicBatchInitMain.java, V1.0.1 2013年12月6日 下午12:41:12 $
 */
public class TopicBatchInitMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/*.xml");

        TopicDAO topicDAO = context.getBean(TopicDAO.class);

        for (String catg : Arrays.asList("blog", "news", "album", "misc")) {
            for (int i = 1; i <= 1001; i++) {
                TopicDTO topic = new TopicDTO();

                TopicValve valve = topic.findValve();
                valve.sotState(TopicStateEnum.findDefault());
                valve.sotTop(ValveBoolEnum.TRUE);
                valve.sotLink(ValveBoolEnum.FALSE);
                valve.sotMedia(TopicMediaEnum.findDefault());
                valve.sotReply(TopicReplyEnum.findDefault());
                
                topic.setFlag(valve.getValve());
                topic.setCatg(catg);
                topic.setVisitCnt(0L);
                topic.setReplyCnt(0L);

                String ucatg = StringUtils.upperCase(catg);
                String idx = StringUtils.leftPad(Integer.toString(i), 3, "0");
                topic.setTitle(ucatg + "-测试标题-" + idx);
                topic.setSummary(ucatg + "-测试摘要-" + idx);
                topic.setContent(ucatg + "-测试内容-" + idx);

                String id = topicDAO.insert(topic);
                System.out.println(ucatg + "-插入-" + idx + "-ID: " + id);
            }
        }
    }

}
