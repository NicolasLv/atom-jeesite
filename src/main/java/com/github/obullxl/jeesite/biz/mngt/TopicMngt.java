/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.biz.mngt;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.github.obullxl.jeesite.dal.dto.ImageDTO;
import com.github.obullxl.jeesite.dal.dto.ReplyDTO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.dal.query.TopicQuery;
import com.github.obullxl.jeesite.web.result.TopicPageList;
import com.github.obullxl.jeesite.web.webx.CfgWebX;
import com.github.obullxl.lang.Paginator;
import com.github.obullxl.lang.catg.CatgUtils;
import com.github.obullxl.lang.enums.ValveBoolEnum;

/**
 * 主题业务
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicMngt.java, V1.0.1 2014年1月13日 下午2:12:24 $
 */
@Service("topicMngt")
public class TopicMngt extends AbstractMngt {

    /**
     * 查询所有置顶主题
     */
    public TopicPageList findTop(String catg) {
        // 缓存
        String ckey = this.findTopicTopCacheKey(catg);
        TopicPageList tpl = super.getTopicPage(ckey);
        if (tpl != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findTopc({})-缓存命中!", catg);
            }

            return tpl;
        }

        // 统计
        List<String> catgs = CatgUtils.findBranchCodes(catg);

        TopicQuery args = new TopicQuery();
        args.setTop(ValveBoolEnum.TRUE.code());

        if (CollectionUtils.isNotEmpty(catgs)) {
            args.setCatgs(catgs);
        }

        int count = (int) this.topicDAO.findFuzzyCount(args);

        Paginator pager = new Paginator(Integer.MAX_VALUE, count);
        pager.setPageNo(1);

        // 明细
        List<TopicDTO> topics = null;
        if (count <= 0) {
            topics = new ArrayList<TopicDTO>();
        } else {
            args.setOffset(pager.getOffset());
            args.setPageSize(pager.getPageSize());

            topics = this.topicDAO.findFuzzy(args);
        }

        // 分页结果
        tpl = new TopicPageList(pager, topics);
        super.putTopicPage(ckey, tpl);

        if (logger.isInfoEnabled()) {
            logger.info("TopicMngt.findTopc({})-更新缓存!", catg);
        }

        // 返回
        return tpl;
    }

    /**
     * 分页查询
     */
    public TopicPageList findPage(String catg, int page) {
        // 缓存
        String ckey = this.findTopicPageCacheKey(catg, page);
        TopicPageList tpl = super.getTopicPage(ckey);
        if (tpl != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findPage({}, {})-缓存命中!", catg, page);
            }

            return tpl;
        }

        // 统计
        List<String> catgs = CatgUtils.findBranchCodes(catg);

        TopicQuery args = new TopicQuery();
        if (CollectionUtils.isNotEmpty(catgs)) {
            args.setCatgs(catgs);
        }

        int count = (int) this.topicDAO.findFuzzyCount(args);
        int pageSize = CfgWebX.findFrontPageSize();

        Paginator pager = new Paginator(pageSize, count);
        pager.setPageNo(page);

        // 明细
        List<TopicDTO> topics = null;
        if (count <= 0) {
            topics = new ArrayList<TopicDTO>();
        } else {
            args.setOffset(pager.getOffset());
            args.setPageSize(pager.getPageSize());

            topics = this.topicDAO.findFuzzy(args);
        }

        // 分页结果
        tpl = new TopicPageList(pager, topics);
        super.putTopicPage(ckey, tpl);

        if (logger.isInfoEnabled()) {
            logger.info("TopicMngt.findPage({}, {})-更新缓存!", catg, page);
        }

        // 返回
        return tpl;
    }

    /**
     * 查询主题详情
     */
    public TopicDTO findTopic(String id) {
        String ckey = "topic-info-" + id;
        TopicDTO topic = super.getTopic(ckey);
        if (topic != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findTopic({})-缓存命中!", id);
            }

            return topic;
        }

        topic = this.topicDAO.find(id);

        if (logger.isInfoEnabled()) {
            logger.info("TopicMngt.findTopic({})-更新缓存!", id);
        }

        super.putTopic(ckey, topic);
        return topic;
    }

    /**
     * 查询主题详情，包括评论列表
     */
    public TopicDTO findDetail(String id) {
        String ckey = "topic-detail-" + id;
        TopicDTO topic = super.getTopic(ckey);
        if (topic != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findDetail({})-缓存命中!", id);
            }

            return topic;
        }

        topic = this.topicDAO.find(id);
        if (topic != null) {
            List<ReplyDTO> replys = this.replyDAO.findTopic(id);
            if (replys != null) {
                topic.setReplys(replys);
            }
        } else {
            topic = new TopicDTO();
        }

        if (logger.isInfoEnabled()) {
            logger.info("TopicMngt.findDetail({})-更新缓存!", id);
        }

        super.putTopic(ckey, topic);
        return topic;
    }

    /**
     * 阅读排行榜
     */
    public List<TopicDTO> findTopVisit(String catg) {
        String ckey = "topic-top-visit-" + catg;
        List<TopicDTO> topics = super.getTopics(ckey);
        if (topics != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findTopVisit({})-缓存命中!", catg);
            }

            return topics;
        }

        List<String> catgs = CatgUtils.findBranchCodes(catg);
        int size = CfgWebX.findFrontTopSize();

        if (CollectionUtils.isEmpty(catgs)) {
            topics = this.topicDAO.findTopVisit(null, size);
        } else {
            topics = this.topicDAO.findTopVisit(catgs, size);
        }

        if (logger.isInfoEnabled()) {
            logger.info("TopicMngt.findTopVisit({})-更新缓存!", catg);
        }

        super.putTopics(ckey, topics);
        return topics;
    }

    /**
     * 阅读排行榜
     */
    public List<TopicDTO> findTopReply(String catg) {
        String ckey = "topic-top-reply-" + catg;
        List<TopicDTO> topics = super.getTopics(ckey);
        if (topics != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findTopReply({})-缓存命中!", catg);
            }

            return topics;
        }

        List<String> catgs = CatgUtils.findBranchCodes(catg);
        int size = CfgWebX.findFrontTopSize();

        if (CollectionUtils.isEmpty(catgs)) {
            topics = this.topicDAO.findTopReply(null, size);
        } else {
            topics = this.topicDAO.findTopReply(catgs, size);
        }

        if (logger.isInfoEnabled()) {
            logger.info("TopicMngt.findTopReply({})-更新缓存!", catg);
        }

        super.putTopics(ckey, topics);
        return topics;
    }

    /**
     * 获取相册图片列表
     */
    public List<ImageDTO> findAlbumImages(String topicId) {
        String ckey = "album-images-" + topicId;
        List<ImageDTO> images = super.getImages(ckey);
        if (images != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findAlbumImages({})-缓存命中!", topicId);
            }

            return images;
        }

        images = this.imageDAO.findTopic(topicId);

        if (logger.isInfoEnabled()) {
            logger.info("TopicMngt.findAlbumImages({})-更新缓存!", images);
        }

        super.putImages(ckey, images);
        return images;
    }

    /**
     * 获取Top主题KEY
     */
    protected String findTopicTopCacheKey(String catg) {
        return "topic-top-catg-" + catg;
    }

    /**
     * 获取主题分页列表KEY
     */
    protected String findTopicPageCacheKey(String catg, int page) {
        return "topic-catg-" + catg + "-page-" + page;
    }

}
