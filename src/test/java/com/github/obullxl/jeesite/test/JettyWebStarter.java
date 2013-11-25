/**
 * aBoy Group.
 * Copyright (c) 2009-2010 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * @author shizihu
 * @version $Id: JettyWebStarter.java, v 0.1 2010-6-24 01:11:33 shizihu Exp $
 * <pre>
 *       <!-- ================================================= -->
 *       <!-- Jetty -->
 *       <!-- ================================================= -->
 *       <dependency>
 *           <groupId>org.mortbay.jetty</groupId>
 *           <artifactId>jetty</artifactId>
 *           <version>6.1.26</version>
 *       </dependency>
 * </pre>
 */
public class JettyWebStarter {

    /**
     * Main.
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server();

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(80);
        server.addConnector(connector);

        WebAppContext context = new WebAppContext();
        context.setDefaultsDescriptor("/jetty.xml");
        
        context.setContextPath("/");
        context.setDescriptor("WebRoot/WEB-INF/web.xml");
        context.setResourceBase("WebRoot");

        server.setHandler(context);

        server.start();
    }
}
