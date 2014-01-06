/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.utils;

import com.github.obullxl.lang.utils.CacheUtils;

/**
 * 数据缓存工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: DataCacheUtils.java, V1.0.1 2014年1月6日 下午4:39:26 $
 */
public class DataCacheUtils {
    /** 用户缓存 */
    public static final String USER_CACHE_KEY  = "UserCache";

    /** 主题缓存 */
    public static final String TOPIC_CACHE_KEY = "TopicCache";

    /**
     * 缓存单个用户
     */
    public static final void putUser(Object key, Object value) {
        CacheUtils.put(USER_CACHE_KEY, key, value);
    }

}
