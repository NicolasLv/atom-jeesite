/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.form.CatgStoreForm;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.model.catg.CatgModel;
import com.github.obullxl.model.catg.CatgUtils;
import com.github.obullxl.model.topic.TopicModelEnum;
import com.github.obullxl.model.topic.query.TopicPageList;
import com.github.obullxl.model.topic.query.TopicQueryForm;

/**
 * 主题分类控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgMngtController.java, V1.0.1 2013年12月13日 下午4:43:16 $
 */
@Controller
@RequestMapping("/admin")
public class CatgMngtController extends AbstractController {

    /**
     * 分类管理
     */
    @RequestMapping(value = "/catg/manage.htm", method = RequestMethod.GET)
    public String manage() {
        return this.toAdminView(VOPT_CATG_MANAGE, "catg-manage");
    }

    /**
     * 新增分类
     */
    @RequestMapping(value = "/catg/create.htm", method = RequestMethod.GET)
    public String create() {
        return this.toAdminView(VOPT_CATG_CREATE, "catg-create");
    }

    @ResponseBody
    @RequestMapping(value = "/catg/create.htm", method = RequestMethod.POST)
    public BizResponse create(@Valid CatgStoreForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            form.validateEnumBase(errors);

            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            CatgModel catg = CatgUtils.find(form.getCtgCode());
            if (catg != null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 新增
            catg = new CatgModel();
            catg.setCatg(form.getCtgCatg());
            catg.setCode(form.getCtgCode());
            catg.setSort(form.getCtgSort());
            catg.setTitle(form.getCtgTitle());
            catg.setExtMap(form.getCtgExtMap());
            catg.setSummary(form.getCtgSummary());

            this.catgService.create(catg);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, catg.getCode());
        } catch (Exception e) {
            logger.error("分类新增异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 更新分类
     */
    @RequestMapping(value = "/catg/update-{id}.htm", method = RequestMethod.GET)
    public String update(@PathVariable long id) {
        this.setWebData("catgId", id);
        return this.toAdminView(VOPT_CATG_MANAGE, "catg-update");
    }

    @ResponseBody
    @RequestMapping(value = "/catg/update.htm", method = RequestMethod.POST)
    public BizResponse update(@Valid CatgStoreForm form, BindingResult errors) {
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
            CatgModel catg = CatgUtils.find(form.getCtgCode());
            if (catg == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 更新
            catg.setCatg(form.getCtgCatg());
            catg.setCode(form.getCtgCode());
            catg.setSort(form.getCtgSort());
            catg.setTitle(form.getCtgTitle());
            catg.setExtMap(form.getCtgExtMap());
            catg.setSummary(form.getCtgSummary());

            this.catgService.update(catg);
        } catch (Exception e) {
            logger.error("分类更新异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 删除分类
     */
    @ResponseBody
    @RequestMapping(value = "/catg/delete.htm", method = RequestMethod.POST)
    public BizResponse delete(String code) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 查询
            CatgModel catg = CatgUtils.find(code);
            if (catg == null) {
                this.buildResponse(response, BizResponseEnum.CATG_NOT_EXIST);
                return response;
            }

            // 校验: 没有子分类
            if (CatgUtils.hasBranch(code)) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 校验: 没有主题信息
            List<String> codes = CatgUtils.findBranchCodes(code);
            if (!codes.isEmpty()) {
                TopicQueryForm form = new TopicQueryForm();
                form.setModelEnum(TopicModelEnum.BLOG_TOPIC);
                form.setPage(1);
                form.setPageSize(1);
                form.setCatgs(codes);

                TopicPageList topics = this.topicService.findPageList(form);
                if (topics != null && !topics.getItems().isEmpty()) {
                    this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                    return response;
                }
            }

            // 执行删除操作
            this.catgService.remove(code);
        } catch (Exception e) {
            logger.error("删除分类异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
