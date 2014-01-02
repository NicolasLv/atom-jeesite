/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.xhelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.dal.dao.ReplyDAO;
import com.github.obullxl.jeesite.dal.dao.TopicDAO;
import com.github.obullxl.jeesite.dal.dto.ReplyDTO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.dal.query.TopicQuery;
import com.github.obullxl.jeesite.web.result.TopicPageList;
import com.github.obullxl.lang.Paginator;
import com.github.obullxl.lang.xhelper.AbstractXHelper;

/**
 * 主题X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicXHelper.java, V1.0.1 2013年12月5日 下午8:09:10 $
 */
@Component("topicXHelper")
public class TopicXHelper extends AbstractXHelper {

    /** 主题DAO */
    @Autowired
    private TopicDAO topicDAO;

    /** 评论DAO */
    @Autowired
    private ReplyDAO replyDAO;

    /**
     * 分页查询
     */
    public TopicPageList findPage(String catg, int page) {
        // 统计
        List<String> catgs = CatgXHelper.findAllCatgCode(catg);

        TopicQuery args = new TopicQuery();
        if (CollectionUtils.isNotEmpty(catgs)) {
            args.setCatgs(catgs);
        }

        int count = (int) this.topicDAO.findFuzzyCount(args);
        int pageSize = CfgXHelper.findFrontPageSize();

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
        return new TopicPageList(pager, topics);
    }

    /**
     * 查询主题详情
     */
    public TopicDTO findDetail(String id) {
        TopicDTO topic = this.topicDAO.find(id);
        if (topic != null) {
            List<ReplyDTO> replys = this.replyDAO.findTopic(id);
            if (replys != null) {
                topic.setReplys(replys);
            }
        } else {
            return new TopicDTO();
        }

        return topic;
    }

    /**
     * 阅读排行榜
     */
    public List<TopicDTO> findTopVisit(String catg) {
        List<String> catgs = CatgXHelper.findAllCatgCode(catg);
        int size = CfgXHelper.findFrontTopSize();

        if (CollectionUtils.isEmpty(catgs)) {
            return this.topicDAO.findTopVisit(null, size);
        } else {
            return this.topicDAO.findTopVisit(catgs, size);
        }
    }

    /**
     * 阅读排行榜
     */
    public List<TopicDTO> findTopReply(String catg) {
        List<String> catgs = CatgXHelper.findAllCatgCode(catg);
        int size = CfgXHelper.findFrontTopSize();

        if (CollectionUtils.isEmpty(catgs)) {
            return this.topicDAO.findTopReply(null, size);
        } else {
            return this.topicDAO.findTopReply(catgs, size);
        }
    }

}
