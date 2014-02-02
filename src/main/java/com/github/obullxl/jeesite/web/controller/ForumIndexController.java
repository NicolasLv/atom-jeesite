/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 论坛首页控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: ForumIndexController.java, V1.0.1 2014年1月31日 下午7:14:43 $
 */
@Controller
@RequestMapping("/forum")
public class ForumIndexController extends AbstractController {

    /**
     * 论坛首页
     */
    @RequestMapping("/index.htm")
    public String indexHtml() {
        return this.toForumView("index");
    }
    
}
