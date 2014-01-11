/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.servlet;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;

import com.github.obullxl.lang.utils.LogUtils;

/**
 * Servlet监听器
 * 
 * @author obullxl@gmail.com
 * @version $Id: ServletContextStartup.java, V1.0.1 2013年12月31日 上午11:17:55 $
 */
public class ServletContextStartup implements ServletContextListener {
    /** Logger */
    private static final Logger logger = LogUtils.get();

    /** 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        String ctx = sce.getServletContext().getRealPath("/");
        ctx = FilenameUtils.normalizeNoEndSeparator(ctx, true);
        logger.warn("[JeeSite-ARGS]-RealRootPath-{}", ctx);
        System.out.println("[JeeSite-ARGS]-RealRootPath-" + ctx);

        File file = new File(".");
        String path = FilenameUtils.normalizeNoEndSeparator(file.getAbsolutePath(), true);
        logger.warn("[JeeSite-ARGS]-FileRootPath-{}", path);
        System.out.println("[JeeSite-ARGS]-FileRootPath-" + path);
    }

    /** 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
