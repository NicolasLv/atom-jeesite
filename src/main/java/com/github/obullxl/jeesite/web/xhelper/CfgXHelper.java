/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.xhelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.dal.dao.ConfigDAO;
import com.github.obullxl.jeesite.dal.dto.ConfigDTO;
import com.github.obullxl.lang.spring.ServletReadyEvent;
import com.github.obullxl.lang.timer.AbstractTickTimer;
import com.github.obullxl.lang.web.WebContext;
import com.github.obullxl.lang.xhelper.XHelper;

/**
 * 参数配置X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgXHelper.java, V1.0.1 2013年12月13日 下午1:09:17 $
 */
@Component("cfgXHelper")
public class CfgXHelper extends AbstractTickTimer implements XHelper, InitializingBean, ApplicationListener<ServletReadyEvent> {
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
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        this.refresh();
    }

    /** 
     * @see com.github.obullxl.lang.xhelper.XHelper#findHelperName()
     */
    public String findHelperName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 刷新系统参数
     */
    public void refresh() {
        this.tickInternal(new Date());
    }

    /** 
     * @see com.github.obullxl.lang.timer.AbstractTickTimer#tickInternal(java.util.Date)
     */
    public void tickInternal(Date start) {
        long now = System.currentTimeMillis();

        if (logger.isInfoEnabled()) {
            logger.info("[配置刷新]-开始刷新配置参数...");
        }

        // 加载所有
        refresh(this.configDAO.findAll());

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

        // 设置图片主机
        setAlbumImageHost();
    }

    /** 
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    public void onApplicationEvent(ServletReadyEvent event) {
        setAlbumImageHost();
    }

    /**
     * 设置图片主机
     */
    public static void setAlbumImageHost() {
        String ictx = findAlbumImageHost();
        if (StringUtils.isNotBlank(ictx)) {
            if (WebContext.isReady()) {
                WebContext.setImageHostCtx(StringUtils.trim(ictx));
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

    /**
     * 业务功能-是否显示广告
     */
    public static boolean isShowAds() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.SHOW_ADS);
        return BooleanUtils.toBoolean(value);
    }

    /**
     * 业务功能-获取关于分类代码
     */
    public static String findCatgAboutCode() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.CATG_ABOUT_CODE);
        if (StringUtils.isBlank(value)) {
            value = "about";
        }

        return value;
    }

    /**
     * 业务功能-获取相册分类代码
     */
    public static String findCatgAlbumCode() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.CATG_ALBUM_CODE);
        if (StringUtils.isBlank(value)) {
            value = "album";
        }

        return value;
    }

    /**
     * 业务功能-后台分页大小
     */
    public static int findMngtPageSize() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.MNGT_PAGE_SIZE);
        return NumberUtils.toInt(value, 20);
    }

    /**
     * 业务功能-前台排行榜大小
     */
    public static int findFrontTopSize() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.FRONT_TOP_SIZE);
        return NumberUtils.toInt(value, 10);
    }

    /**
     * 业务功能-前台分页大小
     */
    public static int findFrontPageSize() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.FRONT_PAGE_SIZE);
        return NumberUtils.toInt(value, 20);
    }

    /**
     * 业务功能-获取数据同步主机
     */
    public static List<String> findDataSyncHosts() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.DATA_SYNC_HOST);
        value = StringUtils.trimToEmpty(value);
        if (StringUtils.isBlank(value)) {
            return new ArrayList<String>();
        }

        return Arrays.asList(StringUtils.split(value, "|"));
    }

    /**
     * 业务功能-获取模板Context相对路径
     */
    public static String findTmptContextPath() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.TMPT_CONTEXT_PATH);
        if (StringUtils.isBlank(value)) {
            value = "/WEB-INF/templates";
        }

        return value;
    }

    /**
     * 业务功能-获取相册封面宽度
     */
    public static int findAlbumCoverWidth() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.ALBUM_COVER_WIDTH);
        return NumberUtils.toInt(value, 400);
    }

    /**
     * 业务功能-获取相册封面高度
     */
    public static int findAlbumCoverHeight() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.ALBUM_COVER_HEIGHT);
        return NumberUtils.toInt(value, 350);
    }

    /**
     * 业务功能-获取相册图片主机
     */
    public static final String findAlbumImageHost() {
        return findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.ALBUM_IMAGE_HOST);
    }

    /**
     * 根据ID获取参数
     */
    public static ConfigDTO findByID(long id) {
        for (ConfigDTO cfg : cache.values()) {
            if (cfg.getId() == id) {
                return cfg;
            }
        }

        return null;
    }

    /**
     * 获取缓存中所有系统参数
     */
    public static List<ConfigDTO> findAll() {
        List<ConfigDTO> cfgs = new ArrayList<ConfigDTO>(cache.values());
        Collections.sort(cfgs, ConfigDTO.COMPARATOR);

        return cfgs;
    }

}
