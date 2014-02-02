/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.io.File;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.enums.TmptCatgEnum;
import com.github.obullxl.jeesite.web.form.TmptCreateForm;
import com.github.obullxl.jeesite.web.form.TmptStoreForm;
import com.github.obullxl.jeesite.web.webx.CfgWebX;
import com.github.obullxl.lang.Consts;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.lang.web.WebContext;
import com.github.obullxl.lang.xml.FileTreeUtils;

/**
 * 模板管理控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: TmptMngtController.java, V1.0.1 2014年1月3日 上午10:03:43 $
 */
@Controller
@RequestMapping("/admin")
public class TmptMngtController extends AbstractController {

    /**
     * 模板管理
     */
    @RequestMapping("/tmpt/manage.html")
    public String manage() {
        String ctxPath = CfgWebX.findTmptContextPath();
        String realPath = WebContext.getServletContext().getRealPath(ctxPath);

        this.setWebData("ctxPath", ctxPath).setWebData("realPath", realPath);
        this.setWebData("ztreeNodes", JSON.toJSONString(FileTreeUtils.parse(realPath)));

        return this.toAdminView(VOPT_TMPT_MANAGE, "tmpt-manage");
    }

    /**
     * 创建模板文件/目录
     */
    @RequestMapping(value = "/tmpt/create.html", method = RequestMethod.GET)
    public String create() {
        return this.toAdminView(VOPT_TMPT_CREATE, "tmpt-create");
    }

    @ResponseBody
    @RequestMapping(value = "/tmpt/create.html", method = RequestMethod.POST)
    public BizResponse create(@Valid TmptCreateForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            form.validateEnumBase(errors);

            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            // 检测
            String realPath = this.findRealPath(form.getTmptName());
            if (logger.isInfoEnabled()) {
                logger.info("[模板管理]-新增模板-{}", realPath);
            }

            File file = new File(realPath);
            if (file.exists()) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 新建
            if (TmptCatgEnum.DIRECTORY == TmptCatgEnum.findByCode(form.getCatgFlag())) {
                FileUtils.forceMkdir(file);
            } else {
                FileUtils.forceMkdir(file.getParentFile());
                FileUtils.write(file, StringUtils.EMPTY);
            }
        } catch (Exception e) {
            logger.error("[模板管理]-存储模板异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 修改模板
     */
    @RequestMapping(value = "/tmpt/update.html", method = RequestMethod.GET)
    public String update(String tmptName) {
        this.setWebData("tmptName", tmptName);

        String realPath = this.findRealPath(tmptName);
        if (logger.isInfoEnabled()) {
            logger.info("[模板管理]-修改模板-{}", realPath);
        }

        try {
            File file = new File(realPath);
            if (file.exists()) {
                this.setWebData("content", FileUtils.readFileToString(file, Consts.ENCODING));
            }
        } catch (Exception e) {
            logger.error("[模板管理]-读取模板异常-{}", realPath, e);
        }

        return this.toAdminView(VOPT_TMPT_MANAGE, "tmpt-update");
    }

    @ResponseBody
    @RequestMapping(value = "/tmpt/update.html", method = RequestMethod.POST)
    public BizResponse update(@Valid TmptStoreForm form, BindingResult errors) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            form.validateEnumBase(errors);

            if (errors.hasErrors()) {
                this.buildResponse(response, BizResponseEnum.INVALID_PARAM);
                return response;
            }

            String realPath = this.findRealPath(form.getTmptName());
            if (logger.isInfoEnabled()) {
                logger.info("[模板管理]-修改模板-{}", realPath);
            }

            File file = new File(realPath);
            if (!file.exists()) {
                form.setBackFlag(ValveBoolEnum.FALSE.code());
                logger.warn("[模板管理]-模板不存在-新增-{}", realPath);
            }

            // 备份
            if (ValveBoolEnum.is(form.getBackFlag())) {
                String content = FileUtils.readFileToString(file, Consts.ENCODING);
                String fname = realPath + "." + DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS");
                FileUtils.write(new File(fname), content, Consts.ENCODING);
            }

            // 存储
            FileUtils.write(file, form.getContent(), Consts.ENCODING);
        } catch (Exception e) {
            logger.error("[模板管理]-存储模板异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 删除模板
     */
    @ResponseBody
    @RequestMapping(value = "/tmpt/delete.html", method = RequestMethod.POST)
    public BizResponse delete(String tmptName) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            String realPath = this.findRealPath(tmptName);
            if (logger.isInfoEnabled()) {
                logger.info("[模板管理]-删除模板-{}", realPath);
            }

            File file = new File(realPath);
            if (!file.exists()) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            if (file.isDirectory()) {
                String[] names = file.list();
                if (names != null && names.length > 0) {
                    this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                    return response;
                }

                // 删除目录
                FileUtils.deleteDirectory(file);
            } else {
                // 删除文件
                file.delete();
            }
        } catch (Exception e) {
            logger.error("[模板管理]-删除模板异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 获取模板实际路径
     */
    private String findRealPath(String tmptName) {
        String root = WebContext.getServletContext().getRealPath(CfgWebX.findTmptContextPath());
        if (!StringUtils.startsWith(tmptName, "/")) {
            tmptName = "/" + tmptName;
        }

        return FilenameUtils.normalize(FilenameUtils.normalizeNoEndSeparator(root) + tmptName);
    }

}
