/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String indexHtml() {
        return this.toAdminView(VOPT_ADMIN_HOME, "index");
    }
    
}
