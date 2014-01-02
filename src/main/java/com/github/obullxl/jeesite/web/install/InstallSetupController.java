/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.install;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.obullxl.lang.MapExt;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.lang.web.WebContext;

/**
 * 系统安装控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: InstallSetupController.java, V1.0.1 2013年12月31日 下午12:32:35 $
 */
@Controller
@RequestMapping("/install")
public class InstallSetupController {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /**
     * 安装
     */
    @RequestMapping(value = "/install.html", method = RequestMethod.GET)
    public String install() {
        return "install-start";
    }

    @RequestMapping(value = "/install.html", method = RequestMethod.POST)
    public String install(HttpServletRequest request, String args) throws Exception {
        logger.warn("[系统安装]-参数-{}", args);

        // Spring配置文件
        MapExt map = MapExt.parse(args, "\n", "=");

        String springConfig = WebContext.get().getServletContext().getRealPath("/WEB-INF/config/spring-config.xml");
        String txt = FileUtils.readFileToString(new File(springConfig));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = "{{" + StringUtils.trim(entry.getKey()) + "}}";
            txt = StringUtils.replace(txt, key, StringUtils.trim(entry.getValue()));
        }
        FileUtils.writeStringToFile(new File(springConfig), txt);

        // WebXML配置文件
        String webXmlFrom = "/WEB-INF/config/web-prod.xml";
        String webXmlTo = "/WEB-INF/web.xml";

        String webXmlFromPath = WebContext.get().getServletContext().getRealPath(webXmlFrom);
        String webXmlToPath = WebContext.get().getServletContext().getRealPath(webXmlTo);
        FileUtils.copyFile(new File(webXmlFromPath), new File(webXmlToPath));

        return "install-setup";
    }

}
