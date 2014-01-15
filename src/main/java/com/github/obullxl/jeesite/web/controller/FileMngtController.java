/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.obullxl.lang.Consts;

/**
 * 文件下载控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: FileMngtController.java, V1.0.1 2014年1月15日 下午2:05:29 $
 */
@Controller
@RequestMapping("/admin")
public class FileMngtController extends AbstractController {

    /**
     * 文件提取
     */
    @RequestMapping(value = "/file/fetch.html", method = RequestMethod.GET)
    public String download() {
        return this.toAdminView(VOPT_FILE_FETCH, "file-fetch");
    }

    @RequestMapping(value = "/file/fetch.html", method = RequestMethod.POST)
    public void download(HttpServletResponse response, String file) throws Exception {
        File diskFile = new File(FilenameUtils.normalize(StringUtils.trimToEmpty(file)));
        if (diskFile == null || !diskFile.exists()) {
            throw new RuntimeException("[文件提取]-文件[" + file + "]不存在或不是文件类型！");
        }

        String fileName = URLEncoder.encode(StringUtils.trimToEmpty(diskFile.getName()), Consts.ENCODING);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.addHeader("Content-Length", Long.toString(diskFile.length()));
        response.setContentType("application/octet-stream; charset=" + Consts.ENCODING);

        InputStream input = null;
        OutputStream output = null;
        try {
            input = new BufferedInputStream(new FileInputStream(diskFile));
            output = new BufferedOutputStream(response.getOutputStream());
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(input);
        }
    }

}
