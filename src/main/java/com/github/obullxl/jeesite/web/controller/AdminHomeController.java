/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.utils.DateUtils;

/**
 * 后台管理页面控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: AdminHomeController.java, V1.0.1 2013年12月11日 下午12:57:55 $
 */
@Controller
public class AdminHomeController extends AbstractController {

    /**
     * 后台管理首页
     */
    @RequestMapping("/admin/index.html")
    public String bopsIndex() {
        return this.toAdminView(VOPT_ADMIN_HOME, "bops-index");
    }

    /**
     * 后台管理菜单
     */
    @RequestMapping("/admin/menu.html")
    public String bopsMenu() {
        return this.toAdminView(VOPT_ADMIN_HOME, "bops-menu");
    }

    /**
     * 后台管理底页
     */
    @RequestMapping("/admin/foot.html")
    public String bopsFoot() {
        return this.toAdminView(VOPT_ADMIN_HOME, "bops-foot");
    }

    /**
     * 后台用户同步
     */
    @ResponseBody
    @RequestMapping(value = "/admin/tick.html", method = RequestMethod.POST)
    public BizResponse bopsTick() {
        // 操作结果
        BizResponse response = this.newBizResponse();
        response.getBizData().put("stamp", DateUtils.toStringDL(new Date()));
        
        try {
            // response.getBizData().put("stamp", DateUtils.toStringDL(new Date()));
        } catch (Exception e) {
            logger.error("后台用户同步!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 后台管理主页
     */
    @RequestMapping("/admin/main.html")
    public String adminIndex() {
        return this.toAdminView(VOPT_ADMIN_HOME, "admin-index");
    }

}
