/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.utils;

import java.util.Map;

import org.apache.commons.lang.ObjectUtils;

import com.github.obullxl.lang.web.WebContext;

/**
 * Web请求数据工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: WebDataUtils.java, V1.0.1 2013年12月5日 下午8:18:48 $
 */
public class WebDataUtils {

    public static final Map<String, Object> findData() {
        return WebContext.get().getData();
    }

    public static final String findString(String key) {
        return ObjectUtils.toString(findData().get(key));
    }

    public static final String findCatg() {
        return findString("catg");
    }

    public static final int findPage() {
        int page = 1;
        try {
            page = Integer.parseInt(findString("page"));
        } catch (Exception e) {
            // ignore
        }

        return page;
    }
    
    public static final long findUserID() {
        long id = -1L;
        try {
            id = Long.parseLong(findString("userId"));
        } catch (Exception e) {
            // ignore
        }

        return id;
    }
    
    public static final long findTopicID() {
        long id = -1L;
        try {
            id = Long.parseLong(findString("topicId"));
        } catch (Exception e) {
            // ignore
        }

        return id;
    }
    
}
