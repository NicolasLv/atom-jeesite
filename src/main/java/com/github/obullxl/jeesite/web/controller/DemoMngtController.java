/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

/**
 * Demo测试控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: DemoMngtController.java, V1.0.1 2014年1月21日 下午9:26:06 $
 */
@Controller
@RequestMapping("/demo")
public class DemoMngtController extends AbstractController {

    /**
     * 头像上传
     */
    @RequestMapping("/avatar.html")
    public String avatar() {
        return this.toFrontView("demo-avatar");
    }

    @ResponseBody
    @RequestMapping(value = "/avatar.html", method = RequestMethod.POST)
    public void avatar(HttpServletResponse response, @RequestParam MultipartFile pic, @RequestParam MultipartFile pic1, @RequestParam MultipartFile pic2, @RequestParam MultipartFile pic3)
                                                                                                                                                                                           throws Exception {
        PrintWriter out = response.getWriter();
        out.println("{\"status\":1,\"picUrl\":\"" + "123" + "\"}");
    }

    /**
     * 头像上传2
     */
    @RequestMapping("/avatar2.html")
    public String avatar2() {
        return this.toFrontView("demo-avatar2");
    }

    @ResponseBody
    @RequestMapping(value = "/avatar2.html", method = RequestMethod.POST)
    public void avatar2(HttpServletResponse response, MultipartRequest request) throws Exception {
        Map<String, MultipartFile> files = request.getFileMap();
        for (String name : files.keySet()) {
            System.out.println(name + " -> " + files.get(name).getSize());
            files.get(name).transferTo(new File("c:/" + name + ".jpg"));
        }

        PrintWriter out = response.getWriter();
        out.println("{\"status\":1,\"picUrl\":\"" + "123" + "\"}");
    }

}
