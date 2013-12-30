/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.dal.dto.ConfigDTO;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.form.ConfigForm;
import com.github.obullxl.jeesite.web.xhelper.CfgXHelper;
import com.github.obullxl.lang.biz.BizResponse;

/**
 * 系统参数控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: ConfigMngtController.java, V1.0.1 2013年12月25日 下午12:32:23 $
 */
@Controller
@RequestMapping("/admin")
public class ConfigMngtController extends AbstractController {

    /** 配置X工具 */
    @Autowired
    private CfgXHelper cfgXHelper;

    /**
     * 权限管理
     */
    @RequestMapping("/config/manage.html")
    public String manage() {
        return this.toAdminView(VOPT_CONFIG_MANAGE, "config-manage");
    }

    /**
     * 新增参数
     */
    @RequestMapping(value = "/config/create.html", method = RequestMethod.GET)
    public String create() {
        return this.toAdminView(VOPT_CONFIG_CREATE, "config-create");
    }

    @ResponseBody
    @RequestMapping(value = "/config/create.html", method = RequestMethod.POST)
    public BizResponse create(@Valid ConfigForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            ConfigDTO cfg = this.configDAO.find(form.getCfgCatg(), form.getCfgName());
            if (cfg != null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 新增
            cfg = new ConfigDTO();
            cfg.setCatg(form.getCfgCatg());
            cfg.setName(form.getCfgName());
            cfg.setState(form.getCfgState());
            cfg.setValue(form.getCfgValue());
            cfg.setCvalue(StringUtils.EMPTY);

            long id = this.configDAO.insert(cfg);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, Long.toString(id));

            // 刷新缓存
            this.cfgXHelper.refresh();
        } catch (Exception e) {
            logger.error("新增系统参数异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 更新参数
     */
    @RequestMapping(value = "/config/update-{id}.html", method = RequestMethod.GET)
    public String update(@PathVariable long id) {
        this.setWebData("cfgId", id);
        return this.toAdminView(VOPT_CONFIG_MANAGE, "config-update");
    }

    @ResponseBody
    @RequestMapping(value = "/config/update-{id}.html", method = RequestMethod.POST)
    public BizResponse update(@Valid ConfigForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            ConfigDTO cfg = this.configDAO.find(form.getCfgCatg(), form.getCfgName());
            if (cfg == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 更新
            this.configDAO.update(form.getCfgCatg(), form.getCfgName(), form.getCfgState(), form.getCfgValue(), cfg.getValue());

            // 刷新缓存
            this.cfgXHelper.refresh();
        } catch (Exception e) {
            logger.error("更新系统参数异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 删除参数
     */
    @ResponseBody
    @RequestMapping(value = "/config/delete-{id}.html", method = RequestMethod.POST)
    public BizResponse delete(@PathVariable long id) {
        this.setWebData("cfgId", id);
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            ConfigDTO cfg = CfgXHelper.findByID(id);
            if (cfg == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 更新
            this.configDAO.delete(cfg.getCatg(), cfg.getName());

            // 刷新缓存
            this.cfgXHelper.refresh();
        } catch (Exception e) {
            logger.error("删除系统参数异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
