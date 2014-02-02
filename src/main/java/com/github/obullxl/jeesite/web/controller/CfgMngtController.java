/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.form.CfgStoreForm;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.cfg.CfgDTO;
import com.github.obullxl.lang.cfg.CfgUtils;

/**
 * 系统参数控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgMngtController.java, V1.0.1 2013年12月25日 下午12:32:23 $
 */
@Controller
@RequestMapping("/admin")
public class CfgMngtController extends AbstractController {

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
    public BizResponse create(@Valid CfgStoreForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            CfgDTO cfg = CfgUtils.find(form.getCfgCatg(), form.getCfgName());
            if (cfg != null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 新增
            cfg = new CfgDTO();
            cfg.setCatg(form.getCfgCatg());
            cfg.setName(form.getCfgName());
            cfg.setTitle(form.getCfgTitle());
            cfg.setValue(form.getCfgValue());
            cfg.setValueExt(form.getCfgValueExt());

            this.cfgService.create(cfg);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, cfg.getName());
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
    public BizResponse update(@Valid CfgStoreForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            CfgDTO cfg = CfgUtils.find(form.getCfgCatg(), form.getCfgName());
            if (cfg == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 更新
            cfg.setCatg(form.getCfgCatg());
            cfg.setName(form.getCfgName());
            cfg.setTitle(form.getCfgTitle());
            cfg.setValue(form.getCfgValue());
            cfg.setValueExt(form.getCfgValueExt());
            
            this.cfgService.update(cfg);
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
    @RequestMapping(value = "/config/delete.html", method = RequestMethod.POST)
    public BizResponse delete(String catg, String name) {
        this.setWebData("catg", catg).setWebData("name", name);
        
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            CfgDTO cfg = CfgUtils.find(catg, name);
            if (cfg == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 删除
            this.cfgService.remove(catg, name);
        } catch (Exception e) {
            logger.error("删除系统参数异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
