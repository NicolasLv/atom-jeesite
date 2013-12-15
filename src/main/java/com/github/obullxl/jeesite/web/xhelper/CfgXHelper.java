/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.xhelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.dal.dao.ConfigDAO;
import com.github.obullxl.jeesite.dal.dto.ConfigDTO;
import com.github.obullxl.lang.timer.AbstractTickTimer;
import com.github.obullxl.lang.xhelper.XHelper;

/**
 * 参数配置X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgXHelper.java, V1.0.1 2013年12月13日 下午1:09:17 $
 */
@Component("cfgXHelper")
public class CfgXHelper extends AbstractTickTimer implements XHelper {
    /** 分隔符 */
    private static final String                 DOT   = ".";

    /** 缓存对象 */
    private static final Map<String, ConfigDTO> cache = new ConcurrentHashMap<String, ConfigDTO>();

    /** 参数DAO */
    @Autowired
    private ConfigDAO                           configDAO;

    /**
     * 初始化
     */
    public CfgXHelper() {
        super.setInterval(1 * 60 * 1000);
    }

    /** 
     * @see com.github.obullxl.lang.xhelper.XHelper#findHelperName()
     */
    public String findHelperName() {
        return this.getClass().getSimpleName();
    }

    /** 
     * @see com.github.obullxl.lang.timer.AbstractTickTimer#tickInternal(java.util.Date)
     */
    public void tickInternal(Date start) {
        long now = System.currentTimeMillis();

        if (logger.isInfoEnabled()) {
            logger.info("[配置刷新]-开始刷新配置参数...");
        }

        List<ConfigDTO> configs = this.configDAO.findAll();
        refresh(configs);

        if (logger.isInfoEnabled()) {
            logger.info("[配置刷新]-配置刷新完成, 耗时[{}]ms.", (System.currentTimeMillis() - now));
        }
    }

    /**
     * 刷新
     */
    public static void refresh(List<ConfigDTO> configs) {
        cache.clear();

        for (ConfigDTO config : configs) {
            cache.put(config.getCatg() + DOT + config.getName(), config);

            if (logger.isInfoEnabled()) {
                logger.info("[配置刷新]-{}.", config);
            }
        }
    }

    /**
     * 获取配置
     */
    public static ConfigDTO findConfig(String catg, String name) {
        return cache.get(catg + DOT + name);
    }

    /**
     * 获取配置值
     */
    public static String findCfgValue(String catg, String name) {
        ConfigDTO cfg = cache.get(catg + DOT + name);
        if (cfg != null) {
            return cfg.getValue();
        }

        return StringUtils.EMPTY;
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
     * 根据分类获取配置
     */
    public static List<ConfigDTO> findByName(String name) {
        List<ConfigDTO> configs = new ArrayList<ConfigDTO>();

        for (ConfigDTO config : cache.values()) {
            if (StringUtils.equals(name, config.getName())) {
                configs.add(config);
            }
        }

        return configs;
    }

}
