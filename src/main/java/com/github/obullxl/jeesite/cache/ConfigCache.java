/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.jeesite.dal.dto.ConfigDTO;

/**
 * 系统参数缓存
 * 
 * @author obullxl@gmail.com
 * @version $Id: ConfigCache.java, V1.0.1 2013年12月5日 下午7:25:46 $
 */
public class ConfigCache {
    public static final String            DOT   = ".";

    /** 缓存对象 */
    private static Map<String, ConfigDTO> cache = new ConcurrentHashMap<String, ConfigDTO>();

    /**
     * 刷新
     */
    public static void refresh(List<ConfigDTO> configs) {
        cache.clear();

        for (ConfigDTO config : configs) {
            cache.put(config.getCatg() + DOT + config.getName(), config);
        }
    }

    /**
     * 根据分类获取配置
     */
    public static List<ConfigDTO> findByCatg(String catg) {
        List<ConfigDTO> configs = new ArrayList<ConfigDTO>();

        for (ConfigDTO config : cache.values()) {
            if (StringUtils.equals(catg, config.getCatg())) {
                configs.add(config);
            }
        }

        return configs;
    }

    /**
     * 获取配置
     */
    public static ConfigDTO findConfig(String catg, String name) {
        return cache.get(catg + DOT + name);
    }

}
