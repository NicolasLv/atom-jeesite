/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.webx;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.obullxl.lang.ObjectMap;
import com.github.obullxl.lang.Paginator;
import com.github.obullxl.lang.catg.ForumDTO;
import com.github.obullxl.lang.catg.ForumUtils;
import com.github.obullxl.lang.topic.TopicDTO;
import com.github.obullxl.lang.topic.TopicPageList;
import com.github.obullxl.lang.user.UserDTO;
import com.github.obullxl.lang.webx.WebX;
import com.google.common.collect.Lists;

/**
 * 论坛X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumWebX.java, V1.0.1 2014年1月31日 下午8:53:55 $
 */
@Component("forumWebX")
public class ForumWebX implements WebX {

    /** 论坛服务 */
    // @Autowired
    // private ForumService forumService;

    /**
     * 获取所有论坛
     */
    public List<ForumDTO> findForums() {
        return ForumUtils.find();
    }

    /**
     * 根据论坛代码获取论坛
     */
    public ForumDTO findForum(String code) {
        return ForumUtils.find(code);
    }
    
    /**
     * 根据论坛获取主题信息
     */
    public ObjectMap findForumTopics(String code, int page) {
        ObjectMap om = ObjectMap.newObjectMap();
        
        // 全局置顶主题
        om.put("TopGlobal", this.newTopics(3));
        
        // 分类置顶主题
        om.put("TopCategory", this.newTopics(5));
        
        // 论坛主题分页列表
        int count = 1000;
        int pageSize = CfgWebX.findFrontPageSize();
        Paginator pager = new Paginator(pageSize, count);
        pager.setPageNo(1);
        
        List<TopicDTO> topics = this.newTopics(pageSize);
        
        om.put("PageList", new TopicPageList(pager, topics));
        
        return om;
    }
    
    /**
     * 根据ID获取论坛主题
     */
    public TopicDTO findTopic(String id) {
        return this.newTopics(1).get(0);
    }
    
    /**
     * 获取论坛主题发表者信息
     */
    public UserDTO findPostUser(String userNo) {
        UserDTO user = new UserDTO();
        
        user.setNo(userNo);
        user.setNickName("老牛啊");
        user.setAvatarPath("http://avatar.ppdai.com/crop/maoleiyu-092215298f0752f4.bmp_120");
        user.setRegistDate("2014-01-01");
        
        return user;
    }

    private List<TopicDTO> newTopics(int n) {
        List<TopicDTO> topics = Lists.newArrayList();

        for (int i = 1; i <= n; i++) {
            TopicDTO topic = new TopicDTO();
            topics.add(topic);

            topic.setId("" + i);
            topic.setTitle("论坛测试主题标题" + i);
            topic.setReplyCount(10);
            topic.setVisitCount(300);

            topic.setPostUserNo("0001");
            topic.setPostNickName("主题发布者" + i);
            topic.setGmtPost(new Date());

            topic.setReplyUserNo("0002");
            topic.setReplyNickName("主题跟帖者" + i);
            topic.setGmtReply(new Date());
        }

        return topics;
    }

}
