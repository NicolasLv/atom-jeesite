/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.biz.mngt;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.github.obullxl.jeesite.dal.dto.ImageDTO;
import com.github.obullxl.jeesite.web.webx.CfgWebX;
import com.github.obullxl.lang.Paginator;
import com.github.obullxl.lang.das.DAS;
import com.github.obullxl.lang.enums.OrderbyEnum;
import com.github.obullxl.model.catg.CatgUtils;
import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.TopicModelEnum;
import com.github.obullxl.model.topic.enums.TopicTopEnum;
import com.github.obullxl.model.topic.query.TopicPageList;
import com.github.obullxl.model.topic.query.TopicQueryForm;

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

        TopicQueryForm form = new TopicQueryForm();
        form.setModelEnum(TopicModelEnum.BLOG_TOPIC);
        form.setPage(1);
        form.setPageSize(Integer.MAX_VALUE);
        form.setTopEnum(TopicTopEnum.CATEGORY);

        if (CollectionUtils.isNotEmpty(catgs)) {
            form.setCatgs(catgs);
        }

        // 查询
        List<TopicModel> topics = this.topicService.findPage(form);

        Paginator pager = new Paginator(Integer.MAX_VALUE, topics.size());
        pager.setPageNo(1);

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

        // 分页查询
        TopicQueryForm form = new TopicQueryForm();
        form.setModelEnum(TopicModelEnum.BLOG_TOPIC);
        form.setCount(true);
        form.setPage(page);
        form.setPageSize(CfgWebX.findFrontPageSize());

        List<String> catgs = CatgUtils.findBranchCodes(catg);
        if (CollectionUtils.isNotEmpty(catgs)) {
            form.setCatgs(catgs);
        }

        // 分页结果
        tpl = this.topicService.findPageList(form);
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
    public TopicModel findTopic(String id) {
        String ckey = "topic-info-" + id;
        TopicModel topic = super.getTopic(ckey);
        if (topic != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findTopic({})-缓存命中!", id);
            }

            return topic;
        }

        topic = this.topicService.findByID(id);

        if (logger.isInfoEnabled()) {
            logger.info("TopicMngt.findTopic({})-更新缓存!", id);
        }

        super.putTopic(ckey, topic);
        return topic;
    }

    /**
     * 查询主题详情，包括评论列表
     */
    public TopicModel findDetail(String id) {
        String ckey = "topic-detail-" + id;
        TopicModel topic = super.getTopic(ckey);
        if (topic != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findDetail({})-缓存命中!", id);
            }

            return topic;
        }

        topic = this.topicService.findByID(id);
        if (topic != null) {
            TopicQueryForm form = new TopicQueryForm();
            form.setModelEnum(TopicModelEnum.BLOG_REPLY);
            form.setTopicId(id);

            List<TopicModel> replys = this.topicService.findPage(form);
            if (replys != null && !replys.isEmpty()) {
                topic.getExtData().put("replys", replys);
            }
        } else {
            topic = new TopicModel();
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
    public List<TopicModel> findTopVisit(String catg) {
        String ckey = "topic-top-visit-" + catg;
        List<TopicModel> topics = super.getTopics(ckey);
        if (topics != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findTopVisit({})-缓存命中!", catg);
            }

            return topics;
        }
        
        TopicQueryForm form = new TopicQueryForm();
        form.setModelEnum(TopicModelEnum.BLOG_TOPIC);
        form.setPage(1);
        form.setPageSize(CfgWebX.findFrontTopSize());
        
        form.setOrderbyEnum(OrderbyEnum.DESC);
        form.setOrderbyField(DAS.TOPIC.VISIT_COUNT);

        List<String> catgs = CatgUtils.findBranchCodes(catg);
        if (CollectionUtils.isNotEmpty(catgs)) {
            form.setCatgs(catgs);
        }
        
        topics = this.topicService.findPage(form);

        if (logger.isInfoEnabled()) {
            logger.info("TopicMngt.findTopVisit({})-更新缓存!", catg);
        }

        super.putTopics(ckey, topics);
        return topics;
    }

    /**
     * 阅读排行榜
     */
    public List<TopicModel> findTopReply(String catg) {
        String ckey = "topic-top-reply-" + catg;
        List<TopicModel> topics = super.getTopics(ckey);
        if (topics != null) {
            if (logger.isInfoEnabled()) {
                logger.info("TopicMngt.findTopReply({})-缓存命中!", catg);
            }

            return topics;
        }

        TopicQueryForm form = new TopicQueryForm();
        form.setModelEnum(TopicModelEnum.BLOG_TOPIC);
        form.setPage(1);
        form.setPageSize(CfgWebX.findFrontTopSize());
        
        form.setOrderbyEnum(OrderbyEnum.DESC);
        form.setOrderbyField(DAS.TOPIC.REPLY_COUNT);

        List<String> catgs = CatgUtils.findBranchCodes(catg);
        if (CollectionUtils.isNotEmpty(catgs)) {
            form.setCatgs(catgs);
        }
        
        topics = this.topicService.findPage(form);

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
