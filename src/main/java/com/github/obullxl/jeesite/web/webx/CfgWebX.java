/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.webx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.github.obullxl.lang.cfg.CfgDTO;
import com.github.obullxl.lang.cfg.CfgUtils;
import com.github.obullxl.lang.spring.ServletReadyEvent;
import com.github.obullxl.lang.web.WebContext;
import com.github.obullxl.lang.webx.WebX;

/**
 * 参数配置X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgWebX.java, V1.0.1 2013年12月13日 下午1:09:17 $
 */
@Component("cfgWebX")
public class CfgWebX implements WebX, ApplicationListener<ServletReadyEvent> {

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
     * 获取系统参数
     */
    public static CfgDTO findConfig(String catg, String name) {
        return CfgUtils.find(catg, name);
    }

    /**
     * 获取配置值
     */
    public static String findCfgValue(String catg, String name) {
        CfgDTO cfg = findConfig(catg, name);
        if (cfg != null) {
            return cfg.getValue();
        }

        return StringUtils.EMPTY;
    }

    /**
     * 根据分类获取系统参数
     */
    public static List<CfgDTO> findByCatg(String catg) {
        List<CfgDTO> cfgs = CfgUtils.find(catg);
        return new ArrayList<CfgDTO>(cfgs);
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
     * 业务功能-展示主题查看次数标志
     */
    public static final boolean isShowTopicVisit() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.TOPIC_SHOW_VISIT);
        return BooleanUtils.toBoolean(value);
    }

    /**
     * 业务功能-更新主题查看次数标志
     */
    public static final boolean isUpdateTopicVisit() {
        String value = findCfgValue(CfgConst.SYSTEM.CATG, CfgConst.SYSTEM.TOPIC_UPDATE_VISIT);
        return BooleanUtils.toBoolean(value);
    }

    /**
     * 获取缓存中所有系统参数
     */
    public static List<CfgDTO> findAll() {
        return new ArrayList<CfgDTO>(CfgUtils.find());
    }

}
