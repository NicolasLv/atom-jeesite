/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.webx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.biz.mngt.TopicMngt;
import com.github.obullxl.jeesite.dal.dto.ImageDTO;
import com.github.obullxl.lang.webx.WebX;
import com.github.obullxl.model.topic.TopicModel;
import com.github.obullxl.model.topic.query.TopicPageList;

/**
 * 主题X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicWebX.java, V1.0.1 2013年12月5日 下午8:09:10 $
 */
@Component("topicWebX")
public class TopicWebX implements WebX {

    /** 主题业务管理 */
    @Autowired
    private TopicMngt topicMngt;

    /**
     * 查询所有置顶主题
     */
    public TopicPageList findTop(String catg) {
        return this.topicMngt.findTop(catg);
    }

    /**
     * 分页查询
     */
    public TopicPageList findPage(String catg, int page) {
        return this.topicMngt.findPage(catg, page);
    }

    /**
     * 查询主题详情
     */
    public TopicModel findTopic(String id) {
        return this.topicMngt.findTopic(id);
    }

    /**
     * 查询主题详情，包括评论列表
     */
    public TopicModel findDetail(String id) {
        return this.topicMngt.findDetail(id);
    }

    /**
     * 阅读排行榜
     */
    public List<TopicModel> findTopVisit(String catg) {
        return this.topicMngt.findTopVisit(catg);
    }

    /**
     * 阅读排行榜
     */
    public List<TopicModel> findTopReply(String catg) {
        return this.topicMngt.findTopReply(catg);
    }

    /**
     * 获取相册图片列表
     */
    public List<ImageDTO> findAlbumImages(String topicId) {
        return this.topicMngt.findAlbumImages(topicId);
    }

}
