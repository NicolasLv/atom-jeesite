/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.biz.mngt;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.obullxl.jeesite.dal.dao.ImageDAO;
import com.github.obullxl.jeesite.dal.dto.ImageDTO;
import com.github.obullxl.lang.utils.CacheUtils;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.query.TopicPageList;
import com.github.obullxl.model.topic.service.TopicService;
import com.github.obullxl.model.user.UserModel;

/**
 * 通用业务
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractMngt.java, V1.0.1 2014年1月13日 下午2:11:31 $
 */
public class AbstractMngt {
    /** Logger */
    protected static final Logger logger          = LogUtils.get();

    /** 用户缓存 */
    public static final String    USER_CACHE_KEY  = "UserCache";

    /** 主题缓存 */
    public static final String    TOPIC_CACHE_KEY = "TopicCache";

    /** 图片缓存 */
    public static final String    IMAGE_CACHE_KEY = "ImageCache";

    /** 主题DAO */
    @Autowired
    protected TopicService        topicService;

    /** 图片DAO */
    @Autowired
    protected ImageDAO            imageDAO;

    // ~~~~~~~~~~~~~~~~~~~ 用户区域 ~~~~~~~~~~~~~~~~~~~ //

    /**
     * 清空用户缓存
     */
    public final AbstractMngt clearUsers() {
        CacheUtils.clear(USER_CACHE_KEY);
        return this;
    }

    /**
     * 删除缓存用户
     */
    public final AbstractMngt evictUser(String key) {
        CacheUtils.evict(USER_CACHE_KEY, key);
        return this;
    }

    /**
     * 缓存单个用户
     */
    public final AbstractMngt putUser(String key, UserModel value) {
        CacheUtils.put(USER_CACHE_KEY, key, value);
        return this;
    }

    /**
     * 获取缓存用户
     */
    public final UserModel getUser(String key) {
        return CacheUtils.get(USER_CACHE_KEY, key);
    }

    // ~~~~~~~~~~~~~~~~~~~ 主题区域 ~~~~~~~~~~~~~~~~~~~ //

    /**
     * 清空主题缓存
     */
    public final AbstractMngt clearTopics() {
        CacheUtils.clear(TOPIC_CACHE_KEY);
        return this;
    }

    /**
     * 删除缓存主题
     */
    public final AbstractMngt evictTopic(String key) {
        CacheUtils.evict(TOPIC_CACHE_KEY, key);
        return this;
    }

    /**
     * 缓存单个主题
     */
    public final AbstractMngt putTopic(String key, TopicModel value) {
        CacheUtils.put(TOPIC_CACHE_KEY, key, value);
        return this;
    }

    /**
     * 缓存主题列表
     */
    public final AbstractMngt putTopics(String key, List<TopicModel> value) {
        CacheUtils.put(TOPIC_CACHE_KEY, key, value);
        return this;
    }

    /**
     * 缓存主题分页列表
     */
    public final AbstractMngt putTopicPage(String key, TopicPageList value) {
        CacheUtils.put(TOPIC_CACHE_KEY, key, value);
        return this;
    }

    /**
     * 获取缓存主题
     */
    public final TopicModel getTopic(String key) {
        return CacheUtils.get(TOPIC_CACHE_KEY, key);
    }

    /**
     * 获取缓存主题列表
     */
    public final List<TopicModel> getTopics(String key) {
        return CacheUtils.get(TOPIC_CACHE_KEY, key);
    }

    /**
     * 获取缓存主题分页列表
     */
    public final TopicPageList getTopicPage(String key) {
        return CacheUtils.get(TOPIC_CACHE_KEY, key);
    }

    // ~~~~~~~~~~~~~~~~~~~ 图片区域 ~~~~~~~~~~~~~~~~~~~ //

    /**
     * 清空图片缓存
     */
    public final AbstractMngt clearImages() {
        CacheUtils.clear(IMAGE_CACHE_KEY);
        return this;
    }

    /**
     * 删除缓存图片
     */
    public final AbstractMngt evictImages(String key) {
        CacheUtils.evict(IMAGE_CACHE_KEY, key);
        return this;
    }

    /**
     * 缓存单个图片
     */
    public final AbstractMngt putImages(String key, List<ImageDTO> value) {
        CacheUtils.put(IMAGE_CACHE_KEY, key, value);
        return this;
    }

    /**
     * 获取缓存图片
     */
    public final List<ImageDTO> getImages(String key) {
        return CacheUtils.get(IMAGE_CACHE_KEY, key);
    }

}
