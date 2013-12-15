/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.utils.TextUtils;

/**
 * 主题后台管理控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicMngtController.java, V1.0.1 2013年12月7日 下午8:11:34 $
 */
@Controller
@RequestMapping("/admin")
public class TopicMngtController extends AbstractController {

    /**
     * 后台主页
     */
    @RequestMapping("/topic/index.htm")
    public String indexHtm() {
        return this.mngtCatgPage("index", 1);
    }

    @RequestMapping("/topic/index.html")
    public String indexHtml() {
        return this.mngtCatgPage("index", 1);
    }

    /**
     * 主题管理
     */
    @RequestMapping("/topic/manage.html")
    public String manage() {
        return this.mngtCatgPage("index", 1);
    }

    @RequestMapping("/topic/manage-{catg}-{page}.html")
    public String mngtCatgPage(@PathVariable String catg, @PathVariable int page) {
        this.setWebData("catg", catg);
        this.setWebData("page", page);

        return this.toAdminView(VOPT_TOPIC_MANAGE, "topic-manage");
    }

    /**
     * 删除主题
     */
    @RequestMapping("/topic/delete-{catg}-{page}-{id}.html")
    public String delete(@PathVariable String catg, @PathVariable int page, @PathVariable long id) {
        // 删除
        this.replyDAO.deleteTopic(Long.toString(id));
        this.topicDAO.delete(id);

        return this.mngtCatgPage(catg, page);
    }

    /**
     * 新增主题
     */
    @RequestMapping(value = "/topic/create.html", method = RequestMethod.GET)
    public String topicCreate() {
        return this.toAdminView(VOPT_TOPIC_CREATE, "topic-create");
    }

    @ResponseBody
    @RequestMapping(value = "/topic/create.html", method = RequestMethod.POST)
    public BizResponse topicCreate(String catg, String title, String summary, String content) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 新建
            TopicDTO topic = this.newInitTopic();
            topic.setCatg(catg);
            topic.setTitle(title);

            if (StringUtils.isBlank(summary)) {
                summary = TextUtils.truncate(content, 255);
            }
            topic.setSummary(summary);
            topic.setContent(content);

            // 新增
            long id = this.topicDAO.insert(topic);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, Long.toString(id));
        } catch (Exception e) {
            logger.error("新增主题异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 更新主题
     */
    @RequestMapping(value = "/topic/update-{id}.html", method = RequestMethod.GET)
    public String topicModify(@PathVariable long id) {
        this.setWebData("topicId", id);

        return this.toAdminView(VOPT_TOPIC_MANAGE, "topic-update");
    }

    @ResponseBody
    @RequestMapping(value = "/topic/update-{id}.html", method = RequestMethod.POST)
    public BizResponse topicUpdate(@PathVariable long id, String catg, String title, String summary, String content) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 查询
            TopicDTO topic = this.topicDAO.find(id);
            topic.setCatg(catg);
            topic.setTitle(title);
            if (StringUtils.isBlank(summary)) {
                summary = TextUtils.truncate(content, 255);
            }
            topic.setContent(content);

            // 更新
            this.topicDAO.update(topic);
        } catch (Exception e) {
            logger.error("修改主题异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
