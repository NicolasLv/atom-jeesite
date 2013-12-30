/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.xhelper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.dal.dao.ReplyDAO;
import com.github.obullxl.jeesite.dal.dao.TopicDAO;
import com.github.obullxl.jeesite.dal.dto.ReplyDTO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.utils.WebDataUtils;
import com.github.obullxl.jeesite.web.enums.TopicCatgEnum;
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
        TopicCatgEnum enm = TopicCatgEnum.findByCode(catg);
        if (enm == null) {
            catg = null;
        }

        // 统计
        int count = (int) this.topicDAO.findCatgPageCount(catg);
        int pageNo = WebDataUtils.findPage();
        int pageSize = 20;

        Paginator pager = new Paginator(pageSize, count);
        pager.setPageNo(pageNo);

        // 明细
        List<TopicDTO> topics = null;
        if (count <= 0) {
            topics = new ArrayList<TopicDTO>();
        } else {
            topics = this.topicDAO.findCatgPage(catg, pager.getOffset(), pager.getPageSize());
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
        TopicCatgEnum enm = TopicCatgEnum.findByCode(catg);
        if (enm == null) {
            catg = null;
        }

        return this.topicDAO.findTopVisit(catg, 10);
    }

    /**
     * 阅读排行榜
     */
    public List<TopicDTO> findTopReply(String catg) {
        TopicCatgEnum enm = TopicCatgEnum.findByCode(catg);
        if (enm == null) {
            catg = null;
        }

        return this.topicDAO.findTopReply(catg, 10);
    }

}
