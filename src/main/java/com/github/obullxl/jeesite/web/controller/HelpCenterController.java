/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 帮助中心控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: HelpCenterController.java, V1.0.1 2014年2月2日 下午5:05:12 $
 */
@Controller
@RequestMapping("/help")
public class HelpCenterController extends AbstractController {

    /**
     * 帮助问题明细
     */
    @RequestMapping("/detail-{id}.htm")
    public String detail(@PathVariable String id) {
        this.setWebData("id", id);
        
        return this.toHelpView("detail");
    }
    
}
