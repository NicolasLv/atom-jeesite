/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test.dalgen;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.utils.DBUtils;

/**
 * 数据元数据
 * 
 * @author obullxl@gmail.com
 * @version $Id: MetaDataMain.java, V1.0.1 2013年12月18日 上午9:04:18 $
 */
public class MetaDataMain {

    /**
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        BasicDataSource ds = null;
        Connection conn = null;
        try {
            ds = new BasicDataSource();

            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/osnode?useUnicode=true&amp;characterEncoding=UTF8");
            ds.setUsername("osnode");
            ds.setPassword("site");

            conn = ds.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet crs = null;
            try {
                crs = meta.getColumns("osnode", "osnode", "atom_topic", null);
                while (crs.next()) {
                    int sqlType = crs.getInt("DATA_TYPE");
                    String sqlTypeName = crs.getString("TYPE_NAME");
                    String columnName = crs.getString("COLUMN_NAME");
                    String columnDefaultValue = crs.getString("COLUMN_DEF");

                    String remark = crs.getString("REMARKS");
                    if (StringUtils.isBlank(remark)) {
                        remark = columnName;
                    }

                    // if columnNoNulls or columnNullableUnknown assume "not nullable"
                    boolean isNullable = (DatabaseMetaData.columnNullable == crs.getInt("NULLABLE"));
                    int size = crs.getInt("COLUMN_SIZE");
                    int decimalDigits = crs.getInt("DECIMAL_DIGITS");

                    System.out.println(sqlType + ": " + columnName + "-" + sqlTypeName + "-" + size);
                }
            } finally {
                DBUtils.closeQuietly(crs);
            }
        } finally {
            DBUtils.closeQuietly(conn);
            ds.close();
        }
    }

}
