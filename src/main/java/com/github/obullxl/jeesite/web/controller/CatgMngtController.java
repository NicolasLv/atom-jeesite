/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.dal.dto.CatgDTO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.enums.TrueFalseEnum;
import com.github.obullxl.jeesite.web.form.CatgForm;
import com.github.obullxl.jeesite.web.xhelper.CatgXHelper;
import com.github.obullxl.lang.biz.BizResponse;

/**
 * 主题分类控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgMngtController.java, V1.0.1 2013年12月13日 下午4:43:16 $
 */
@Controller
@RequestMapping("/admin")
public class CatgMngtController extends AbstractController {

    /** 分类X工具 */
    @Autowired
    private CatgXHelper catgXHelper;

    /**
     * 分类管理
     */
    @RequestMapping(value = "/catg/manage.html", method = RequestMethod.GET)
    public String manage() {
        return this.toAdminView(VOPT_CATG_MANAGE, "catg-manage");
    }

    /**
     * 新增分类
     */
    @RequestMapping(value = "/catg/create.html", method = RequestMethod.GET)
    public String create() {
        return this.toAdminView(VOPT_CATG_CREATE, "catg-create");
    }

    @ResponseBody
    @RequestMapping(value = "/catg/create.html", method = RequestMethod.POST)
    public BizResponse create(@Valid CatgForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            CatgDTO catg = this.catgDAO.findCode(form.getCatgCode());
            if (catg != null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            catg = this.catgDAO.findName(form.getCatgName());
            if (catg != null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 新增
            catg = new CatgDTO();
            catg.setCode(form.getCatgCode());
            catg.setTop(TrueFalseEnum.findDefault(form.getCatgTop()).code());
            catg.setCatg(form.getCatgCatg());
            catg.setSort(Math.abs(form.getCatgSort()));
            catg.setName(form.getCatgName());

            long id = this.catgDAO.insert(catg);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, Long.toString(id));

            // 刷新缓存
            this.catgXHelper.refresh();
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
    @RequestMapping(value = "/catg/update-{id}.html", method = RequestMethod.GET)
    public String update(@PathVariable long id) {
        this.setWebData("catgId", id);
        return this.toAdminView(VOPT_CATG_MANAGE, "catg-update");
    }

    @ResponseBody
    @RequestMapping(value = "/catg/update-{id}.html", method = RequestMethod.POST)
    public BizResponse update(@PathVariable long id, @Valid CatgForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            // 查询
            CatgDTO catg = this.catgDAO.find(id);
            if (catg == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 更新
            catg.setCode(form.getCatgCode());
            catg.setTop(TrueFalseEnum.findDefault(form.getCatgTop()).code());

            if (form.getCatgCatg() != catg.getId()) {
                catg.setCatg(form.getCatgCatg());
            }

            catg.setSort(form.getCatgSort());
            catg.setName(form.getCatgName());

            this.catgDAO.update(catg);

            // 刷新缓存
            this.catgXHelper.refresh();
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
    @RequestMapping(value = "/catg/delete.html", method = RequestMethod.POST)
    public BizResponse delete(long id) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 查询
            CatgDTO catg = this.catgDAO.find(id);
            if (catg == null) {
                this.buildResponse(response, BizResponseEnum.CATG_NOT_EXIST);
                return response;
            }

            // 校验: 没有子分类
            catg = this.catgDAO.findCatg(id);
            if (catg != null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 校验: 没有主题信息
            List<String> codes = CatgXHelper.findAllCatgCode(id);
            TopicDTO topic = this.topicDAO.findCatgOne(codes);
            if (topic != null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 执行删除操作
            this.catgDAO.delete(id);

            // 刷新缓存
            this.catgXHelper.refresh();
        } catch (Exception e) {
            logger.error("删除分类异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
