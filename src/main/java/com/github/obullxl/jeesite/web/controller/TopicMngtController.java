/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.utils.DBTicketUtils;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.form.TopicSelectForm;
import com.github.obullxl.jeesite.web.form.TopicStoreForm;
import com.github.obullxl.jeesite.web.webx.CfgWebX;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.das.DAS;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.utils.TextUtils;
import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.enums.TopicMediaEnum;
import com.github.obullxl.model.topic.enums.TopicStateEnum;
import com.github.obullxl.model.topic.enums.TopicTopEnum;
import com.github.obullxl.model.topic.query.TopicPageList;
import com.github.obullxl.model.topic.query.TopicQueryForm;

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
        return this.manage(new TopicSelectForm());
    }

    @RequestMapping("/topic/index.htm")
    public String indexHtml() {
        return this.manage(new TopicSelectForm());
    }

    /**
     * 主题管理
     */
    @RequestMapping("/topic/manage.htm")
    public String manage(@Valid TopicSelectForm form) {
        if (StringUtils.isBlank(form.getFormCatg()) || StringUtils.isBlank(form.getTpcId())) {
            form.setFormCatg(TopicSelectForm.FUZZY);
        }

        if (form.getPage() < 1) {
            form.setPage(1);
        }

        if (logger.isInfoEnabled()) {
            logger.info("[主题管理]-Web查询条件-{}.", form);
        }

        TopicQueryForm query = form.to();
        query.setPage(form.getPage());
        query.setPageSize(CfgWebX.findMngtPageSize());

        TopicPageList tpl = this.topicService.findPageList(query);

        // 返回
        this.setWebData("form", form).setWebData("topicPageList", tpl);
        return this.toAdminView(VOPT_TOPIC_MANAGE, "topic-manage");
    }

    /**
     * 新增主题
     */
    @RequestMapping(value = "/topic/create.htm", method = RequestMethod.GET)
    public String create() {
        return this.toAdminView(VOPT_TOPIC_CREATE, "topic-create");
    }

    @ResponseBody
    @RequestMapping(value = "/topic/create.htm", method = RequestMethod.POST)
    public BizResponse create(@Valid TopicStoreForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            form.validateEnumBase(errors);

            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            // 新建
            TopicModel topic = this.newInitTopic();

            // 填充
            this.fillTopic(form, topic);

            // 新增
            topic.setId(DBTicketUtils.newTopicID());
            this.topicService.create(topic);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, topic.getId());
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
    @RequestMapping(value = "/topic/update-{id}.htm", method = RequestMethod.GET)
    public String update(@PathVariable String id) {
        this.setWebData("topicId", id);

        return this.toAdminView(VOPT_TOPIC_MANAGE, "topic-update");
    }

    @ResponseBody
    @RequestMapping(value = "/topic/update.htm", method = RequestMethod.POST)
    public BizResponse update(@Valid TopicStoreForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            form.validateEnumBase(errors);

            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            // 查询
            TopicModel topic = this.topicService.findByID(form.getTpcId());

            // 填充
            this.fillTopic(form, topic);

            // 更新
            this.topicService.update(topic);
        } catch (Exception e) {
            logger.error("修改主题异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 删除主题
     */
    @ResponseBody
    @RequestMapping(value = "/topic/delete.htm", method = RequestMethod.POST)
    public BizResponse delete(String id) {
        BizResponse response = this.newBizResponse();

        try {
            this.topicService.removeByTopicID(id);
            this.topicService.removeByID(id);
        } catch (Exception e) {
            logger.error("删除主题异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        return response;
    }

    /**
     * 填充主题信息
     */
    private void fillTopic(TopicStoreForm form, TopicModel topic) {
        topic.setStateEnum(TopicStateEnum.findDefault(form.getTpcStateFlag()));
        topic.setTopEnum(TopicTopEnum.findDefault(form.getTpcTopFlag()));
        topic.setOriginalEnum(ValveBoolEnum.findDefault(form.getTpcLinkFlag()));
        topic.setMediaEnum(TopicMediaEnum.findDefault(form.getTpcMediaFlag()));
        topic.setReplyEnum(ValveBoolEnum.findDefault(form.getTpcReplyFlag()));
        topic.setCatg(form.getTpcCatg());
        topic.setLinkUrl(StringUtils.trimToEmpty(form.getTpcLinkUrl()));
        topic.setMediaUrl(StringUtils.trimToEmpty(form.getTpcMediaUrl()));
        topic.setTitle(form.getTpcTitle());

        if (StringUtils.isBlank(form.getTpcSummary())) {
            form.setTpcSummary(TextUtils.truncate(form.getTpcContent(), DAS.TOPIC.SUMMARY_MAX));
        }

        topic.setSummary(form.getTpcSummary());
        topic.setContent(form.getTpcContent());
    }

}
