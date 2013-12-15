/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.xhelper;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import com.github.obullxl.lang.xhelper.AbstractXHelper;

/**
 * JDBC帮助类
 * 
 * @author obullxl@gmail.com
 * @version $Id: JdbcXHelper.java, V1.0.1 2013年12月4日 下午12:30:03 $
 */
@Component("jdbcXHelper")
public class JdbcXHelper extends AbstractXHelper {

    /** 数据源 */
    @Autowired
    @Qualifier("jeesiteDataSource")
    private DataSource jdbcDataSource;

    /**
     * 获取连接
     */
    public Connection getConnection() {
        try {
            return DataSourceUtils.getConnection(this.jdbcDataSource);
        } catch (Exception e) {
            throw new RuntimeException("JdbcXHelper#getConnection()", e);
        }
    }

    /**
     * 关闭连接
     */
    public void releaseConnection(Connection conn) {
        try {
            DataSourceUtils.releaseConnection(conn, this.jdbcDataSource);
        } catch (Exception e) {
            throw new RuntimeException("JdbcXHelper#releaseConnection()", e);
        }
    }

}
