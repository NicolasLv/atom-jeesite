/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.net.URLClassLoader;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.obullxl.jeesite.dal.dao.CatgDAO;
import com.github.obullxl.jeesite.dal.dao.CrawlDAO;
import com.github.obullxl.jeesite.dal.dao.ReplyDAO;
import com.github.obullxl.jeesite.dal.dao.TopicDAO;
import com.github.obullxl.jeesite.dal.dao.UserDAO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.jeesite.web.enums.TopicCatgEnum;
import com.github.obullxl.jeesite.web.enums.TopicStateEnum;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.lang.web.WebContext;

/**
 * 控制器基类
 * 
 * @author obullxl@gmail.com
 * @version $Id: AbstractController.java, V1.0.1 2013年12月4日 下午5:22:21 $
 */
public abstract class AbstractController {
    /** Logger */
    protected static final Logger logger            = LogUtils.get();

    //~~~~~~~~~~~~~ 后台 ~~~~~~~~~~~~~//
    public static final String    VOPT_ADMIN_HOME  = "admin-home";
    
  //~~~~~~~~~~~~~ 用户 ~~~~~~~~~~~~~//
    public static final String    VOPT_USER_CREATE  = "user-create";
    public static final String    VOPT_USER_MANAGE  = "user-manage";

    //~~~~~~~~~~~~~ 主题 ~~~~~~~~~~~~~//
    public static final String    VOPT_TOPIC_CREATE = "topic-create";
    public static final String    VOPT_TOPIC_MANAGE = "topic-manage";

    //~~~~~~~~~~~~~ 分类 ~~~~~~~~~~~~~//
    public static final String    VOPT_CATG_CREATE  = "catg-create";
    public static final String    VOPT_CATG_MANAGE  = "catg-manage";

    //~~~~~~~~~~~~~ 爬虫 ~~~~~~~~~~~~~//
    public static final String    VOPT_CRAWL_INPUT  = "crawl-input";
    public static final String    VOPT_CRAWL_CREATE = "crawl-create";
    public static final String    VOPT_CRAWL_MANAGE = "crawl-manage";

    /** 爬虫DAO */
    @Autowired
    protected CrawlDAO            crawlDAO;

    /** 用户DAO */
    @Autowired
    protected UserDAO             userDAO;

    /** 主题DAO */
    @Autowired
    protected TopicDAO            topicDAO;

    /** 评论DAO */
    @Autowired
    protected ReplyDAO            replyDAO;

    /** 分类DAO */
    @Autowired
    protected CatgDAO             catgDAO;

    /**
     * 设置Web数据值
     */
    public void setWebData(String key, Object value) {
        WebContext.get().getData().put(key, value);
    }

    /**
     * 设置JSON请求标志
     */
    public void setRequestJSON() {
        WebContext.get().setRequestJSON();
    }

    /**
     * 新建业务返回对象
     */
    public BizResponse newBizResponse() {
        return this.newBizResponse(true);
    }

    /**
     * 新建业务返回对象
     */
    public BizResponse newBizResponse(boolean json) {
        BizResponse response = new BizResponse();
        response.setBizLog(LogUtils.findLogID());
        response.setSuccess(true);

        if (json) {
            this.setRequestJSON();
        }

        return response;
    }

    /**
     * 后台视图
     */
    public String toAdminView(String vopt, String name) {
        this.setWebData("vopt", vopt);

        if (!StringUtils.startsWith(name, "/")) {
            name = "/" + name;
        }

        return "/admin" + name;
    }

    /**
     * 前台视图
     */
    public String toFrontView(String name) {
        if (!StringUtils.startsWith(name, "/")) {
            name = "/" + name;
        }

        return "/bootword" + name;
    }

    /**
     * 重定向页面
     */
    public String redirectTo(String url) {
        return "redirect:" + url;
    }

    /**
     * 关闭类加载器
     */
    public void close(URLClassLoader loader) {
        try {
            loader.close();
        } catch (Exception e) {
            // ignore
        }
    }

    /**
     * 构建返回结果
     */
    public void buildResponse(BizResponse response, EnumBase enm) {
        response.setSuccess(false);
        response.setRespCode(enm.code());
        response.setRespDesp(enm.desp());
    }

    /**
     * 新增初始化用户
     */
    public UserDTO newInitUser() {
        UserDTO user = new UserDTO();

        return user;
    }

    /**
     * 新增初始化主题
     */
    public TopicDTO newInitTopic() {
        TopicDTO topic = new TopicDTO();
        topic.setState(TopicStateEnum.findInit().code());
        topic.setCatg(TopicCatgEnum.findInit().code());
        topic.setTflag("T");
        topic.setRflag("F");
        topic.setRfrom(StringUtils.EMPTY);
        topic.setMflag("F");
        topic.setMpath(StringUtils.EMPTY);
        topic.setMcount(0L);
        topic.setTreply("T");
        topic.setTitle(StringUtils.EMPTY);
        topic.setSummary(StringUtils.EMPTY);
        topic.setContent(StringUtils.EMPTY);

        return topic;
    }

}
