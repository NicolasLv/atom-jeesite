/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test.impt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.Consts;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicImportMain.java, V1.0.1 2014年1月10日 下午9:01:25 $
 */
public class TopicImportMain {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");
        Class.forName("org.sqlite.JDBC").newInstance();
        String connStr = "jdbc:sqlite:./WebRoot/public/jeesite.db";
        Connection conn = DriverManager.getConnection(connStr);
        
        List<String> sqls = readSqls("D:/NetDisk/BaiduYun/我的文档/开发工具/SQL配置/atom_topic.sql");
        Statement stat = conn.createStatement();
        for (String dllsql : sqls) {
            System.out.println(dllsql);
            stat.execute(dllsql);
        }
        stat.close();
        conn.close();
    }
    
    public static List<String> readSqls(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Consts.UTF8));
        List<String> sqlList = new ArrayList<String>();
        StringBuilder sqlSb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            if (s.startsWith("/*") || s.startsWith("#") || StringUtils.isBlank(s)) {
                continue;
            }
            if (s.endsWith(";")) {
                sqlSb.append(s);
                sqlSb.setLength(sqlSb.length() - 1);
                sqlList.add(sqlSb.toString());
                sqlSb.setLength(0);
            } else {
                sqlSb.append(s);
            }
        }

        br.close();
        return sqlList;
    }

}
