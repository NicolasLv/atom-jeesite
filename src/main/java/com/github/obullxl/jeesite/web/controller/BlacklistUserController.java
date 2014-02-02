/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.obullxl.lang.utils.DateUtils;
import com.google.common.collect.Lists;

/**
 * 黑名单用户控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: BlacklistUserController.java, V1.0.1 2014年2月2日 下午7:32:10 $
 */
@Controller
@RequestMapping("/user")
public class BlacklistUserController extends AbstractController {

    /**
     * 黑名单首页
     */
    @RequestMapping("/blacklist.htm")
    public String blacklist() {
        return this.blacklist(this.findMaxYear());
    }
    
    /**
     * 黑名单
     */
    @RequestMapping("/blacklist-{year}.htm")
    public String blacklist(@PathVariable int year) {
        int start = 2010;
        int max = this.findMaxYear();
        
        List<Integer> years = Lists.newArrayList();
        for(int i = max; i >= start; i--) {
            years.add(i);
        }
        
        this.setWebData("start", start).setWebData("year", year).setWebData("years", years);
        return this.toUserView("blacklist");
    }
    
    /**
     * 获取最大年份
     */
    private int findMaxYear() {
        return Integer.parseInt(DateUtils.toString(new Date(), "yyyy"));
    }
    
}
