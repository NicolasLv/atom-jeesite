/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.obullxl.jeesite.web.webx.CatgWebX;
import com.github.obullxl.jeesite.web.webx.CfgWebX;
import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.TopicModelEnum;
import com.github.obullxl.model.topic.enums.TopicStateEnum;
import com.google.common.collect.Maps;

/**
 * 主页控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: HomePageController.java, V1.0.1 2013年12月4日 上午9:44:12 $
 */
@Controller
public class HomePageController extends AbstractController {

    /**
     * 前台首页
     */

    @RequestMapping("/")
    public String index() {
        return this.indexCatgPage("index", 1);
    }

    @RequestMapping("/index.htm")
    public String indexHtm() {
        return this.indexCatgPage("index", 1);
    }

    @RequestMapping("/default.htm")
    public String defaultHtm() {
        return this.indexCatgPage("index", 1);
    }

    /**
     * 分类分页主页
     */
    @RequestMapping("/index-{catg}-{page}.htm")
    public String indexCatgPage(@PathVariable String catg, @PathVariable int page) {
        this.setWebData("catg", catg).setWebData("page", page);

        if (CatgWebX.isAlbumCatg(catg)) {
            return this.toFrontView("/index-album");
        }

        return this.toFrontView("/index-topic");
    }

    /**
     * 主题详情页面
     */
    @RequestMapping("/topic/{catg}-{id}.htm")
    public String topicDetail(@PathVariable String catg, @PathVariable String id) {
        this.setWebData("catg", catg).setWebData("topicId", id);

        // 更新主题访问次数
        if (CfgWebX.isUpdateTopicVisit()) {
            this.topicService.deltaVisitCount(id, 1);
        }

        if (CatgWebX.isAlbumCatg(catg)) {
            this.setWebData("topicId", id).setWebData("imageIndex", "0");

            String[] args = StringUtils.split(id, "-");
            if (args != null && args.length >= 2) {
                this.setWebData("topicId", args[0]).setWebData("imageIndex", args[1]);
            }

            return this.toFrontView("/detail-album");
        }

        return this.toFrontView("/detail-topic");
    }

    /**
     * 发表评论
     */
    @RequestMapping("/topic/post-reply.htm")
    public String postReply(String uname, String uemail, String usite, String content, String ufrom, String topic, String title) {
        // 存储评论
        TopicModel reply = new TopicModel();
        reply.setModelEnum(TopicModelEnum.BLOG_REPLY);
        reply.setStateEnum(TopicStateEnum.findDefault());
        reply.setTopic(topic);
        reply.setTitle(title);
        reply.setPostNickName(uname);
        reply.setContent(content);
        
        Map<String, String> extMap = Maps.newConcurrentMap();
        extMap.put("post_user_email", uemail);
        extMap.put("post_user_site", usite);
        reply.setExtMap(JSON.toJSONString(extMap));

        this.topicService.create(reply);

        // 更新主题评论次数
        this.topicService.deltaReplyCount(topic, 1);

        // 页面跳转
        return this.redirectTo(ufrom);
    }

    /**
     * 关于
     */
    @RequestMapping("/about.htm")
    public String about() {
        this.setWebData("catg", CfgWebX.findCatgAboutCode());
        return this.toFrontView("/detail-about");
    }

}
