/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.dal.DBSize;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.dal.query.TopicQuery;
import com.github.obullxl.jeesite.dal.valve.TopicValve;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.enums.TopicMediaEnum;
import com.github.obullxl.jeesite.web.enums.TopicReplyEnum;
import com.github.obullxl.jeesite.web.enums.TopicStateEnum;
import com.github.obullxl.jeesite.web.form.TopicQueryForm;
import com.github.obullxl.jeesite.web.form.TopicStoreForm;
import com.github.obullxl.jeesite.web.result.TopicPageList;
import com.github.obullxl.jeesite.web.xhelper.CfgXHelper;
import com.github.obullxl.lang.Paginator;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.enums.ValveBoolEnum;
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
        return this.manage(new TopicQueryForm());
    }

    @RequestMapping("/topic/index.html")
    public String indexHtml() {
        return this.manage(new TopicQueryForm());
    }

    /**
     * 主题管理
     */
    @RequestMapping("/topic/manage.html")
    public String manage(@Valid TopicQueryForm form) {
        if (StringUtils.isBlank(form.getFormCatg()) || StringUtils.isBlank(form.getTpcId())) {
            form.setFormCatg(TopicQueryForm.FUZZY);
        }

        if (form.getPage() < 1) {
            form.setPage(1);
        }

        if (logger.isInfoEnabled()) {
            logger.info("[主题管理]-Web查询条件-{}.", form);
        }

        TopicQuery query = form.to();
        query.setPageSize(CfgXHelper.findMngtPageSize());

        // 统计
        int count = (int) this.topicDAO.findFuzzyCount(query);
        Paginator pager = new Paginator(query.getPageSize(), count);
        pager.setPageNo(form.getPage());

        query.setOffset(pager.getOffset());

        if (logger.isInfoEnabled()) {
            logger.info("[主题管理]-DB查询条件-{}.", query);
        }

        // 明细
        List<TopicDTO> topics = null;
        if (count <= 0) {
            topics = new ArrayList<TopicDTO>();
        } else {
            topics = this.topicDAO.findFuzzy(query);
        }

        // 返回
        this.setWebData("form", form).setWebData("topicPageList", new TopicPageList(pager, topics));
        return this.toAdminView(VOPT_TOPIC_MANAGE, "topic-manage");
    }

    /**
     * 新增主题
     */
    @RequestMapping(value = "/topic/create.html", method = RequestMethod.GET)
    public String create() {
        return this.toAdminView(VOPT_TOPIC_CREATE, "topic-create");
    }

    @ResponseBody
    @RequestMapping(value = "/topic/create.html", method = RequestMethod.POST)
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
            TopicDTO topic = this.newInitTopic();

            // 填充
            this.fillTopic(form, topic);

            // 新增
            String id = this.topicDAO.insert(topic);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, id);
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
    public String update(@PathVariable String id) {
        this.setWebData("topicId", id);

        return this.toAdminView(VOPT_TOPIC_MANAGE, "topic-update");
    }

    @ResponseBody
    @RequestMapping(value = "/topic/update.html", method = RequestMethod.POST)
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
            TopicDTO topic = this.topicDAO.find(form.getTpcId());

            // 填充
            this.fillTopic(form, topic);

            // 更新
            this.topicDAO.update(topic);
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
    @RequestMapping(value = "/topic/delete.html", method = RequestMethod.POST)
    public BizResponse delete(String id) {
        BizResponse response = this.newBizResponse();

        try {
            this.replyDAO.deleteTopic(id);
            this.topicDAO.delete(id);
        } catch (Exception e) {
            logger.error("删除主题异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        return response;
    }

    /**
     * 填充主题信息
     */
    private void fillTopic(TopicStoreForm form, TopicDTO topic) {
        TopicValve valve = topic.findValve();
        valve.sotState(TopicStateEnum.findDefault(form.getTpcStateFlag()));
        valve.sotTop(ValveBoolEnum.findDefault(form.getTpcTopFlag()));
        valve.sotLink(ValveBoolEnum.findDefault(form.getTpcLinkFlag()));
        valve.sotMedia(TopicMediaEnum.findDefault(form.getTpcMediaFlag()));
        valve.sotReply(TopicReplyEnum.findDefault(form.getTpcReplyFlag()));

        topic.setFlag(valve.getValve());
        topic.setCatg(form.getTpcCatg());
        topic.setLinkUrl(StringUtils.trimToEmpty(form.getTpcLinkUrl()));
        topic.setMediaUrl(StringUtils.trimToEmpty(form.getTpcMediaUrl()));
        topic.setTitle(form.getTpcTitle());

        if (StringUtils.isBlank(form.getTpcSummary())) {
            form.setTpcSummary(TextUtils.truncate(form.getTpcContent(), DBSize.Topic.SUMMARY_MAX));
        }
        topic.setSummary(form.getTpcSummary());
        topic.setContent(form.getTpcContent());
    }

}
