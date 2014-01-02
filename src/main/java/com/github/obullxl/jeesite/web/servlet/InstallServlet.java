package com.github.obullxl.jeesite.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.MapExt;

public class InstallServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Spring配置文件
        MapExt map = MapExt.parse(request.getParameter("args"), "\n", "=");

        String springConfig = this.getServletContext().getRealPath("/WEB-INF/config/spring-config.xml");
        String txt = FileUtils.readFileToString(new File(springConfig));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = "{{" + StringUtils.trim(entry.getKey()) + "}}";
            txt = StringUtils.replace(txt, key, StringUtils.trim(entry.getValue()));
        }
        FileUtils.writeStringToFile(new File(springConfig), txt);

        // WebXML配置文件
        String webXmlFrom = "/WEB-INF/config/web-prod.xml";
        String webXmlTo = "/WEB-INF/web.xml";

        String webXmlFromPath = this.getServletContext().getRealPath(webXmlFrom);
        String webXmlToPath = this.getServletContext().getRealPath(webXmlTo);
        FileUtils.copyFile(new File(webXmlFromPath), new File(webXmlToPath));

        // 安装完成
        RequestDispatcher dispatcher = request.getRequestDispatcher("/install_setup.html");
        dispatcher.forward(request, response);
    }

}
