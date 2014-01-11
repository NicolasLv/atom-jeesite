/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.net.URLClassLoader;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.github.obullxl.jeesite.dal.dao.CatgDAO;
import com.github.obullxl.jeesite.dal.dao.ConfigDAO;
import com.github.obullxl.jeesite.dal.dao.CrawlDAO;
import com.github.obullxl.jeesite.dal.dao.ImageDAO;
import com.github.obullxl.jeesite.dal.dao.ReplyDAO;
import com.github.obullxl.jeesite.dal.dao.RightDAO;
import com.github.obullxl.jeesite.dal.dao.TopicDAO;
import com.github.obullxl.jeesite.dal.dao.UserDAO;
import com.github.obullxl.jeesite.dal.dao.UserRgtDAO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.jeesite.dal.valve.TopicValve;
import com.github.obullxl.jeesite.web.enums.TopicMediaEnum;
import com.github.obullxl.jeesite.web.enums.TopicReplyEnum;
import com.github.obullxl.jeesite.web.enums.TopicStateEnum;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.spring.DatePropertyEditor;
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
    protected static final Logger logger             = LogUtils.get();

    /** 错误参数 */
    public static final String    ERR_MSG_KEY        = "errorMessage";

    /** 后台主页 */
    public static final String    ADMIN_INDEX        = "/admin/index.html";

    //~~~~~~~~~~~~~ 后台 ~~~~~~~~~~~~~//
    public static final String    VOPT_ADMIN_HOME    = "admin-home";

    //~~~~~~~~~~~~~ 用户 ~~~~~~~~~~~~~//
    public static final String    VIEW_USER_LOGIN    = "user-login";
    public static final String    VIEW_USER_REGIST   = "user-regist";

    public static final String    VOPT_USER_CREATE   = "user-create";
    public static final String    VOPT_USER_MANAGE   = "user-manage";

    public static final String    VOPT_USER_CINFO    = "user-cinfo";
    public static final String    VOPT_USER_CEMAIL   = "user-cemail";
    public static final String    VOPT_USER_CPASSWD  = "user-cpasswd";

    //~~~~~~~~~~~~~ 权限 ~~~~~~~~~~~~~//
    public static final String    VOPT_RIGHT_CREATE  = "right-create";
    public static final String    VOPT_RIGHT_MANAGE  = "right-manage";

    //~~~~~~~~~~~~~ 参数 ~~~~~~~~~~~~~//
    public static final String    VOPT_CONFIG_CREATE = "config-create";
    public static final String    VOPT_CONFIG_MANAGE = "config-manage";

    //~~~~~~~~~~~~~ 模板 ~~~~~~~~~~~~~//
    public static final String    VOPT_TMPT_CREATE   = "tmpt-create";
    public static final String    VOPT_TMPT_MANAGE   = "tmpt-manage";

    //~~~~~~~~~~~~~ 主题 ~~~~~~~~~~~~~//
    public static final String    VOPT_TOPIC_CREATE  = "topic-create";
    public static final String    VOPT_TOPIC_MANAGE  = "topic-manage";

    //~~~~~~~~~~~~~ 分类 ~~~~~~~~~~~~~//
    public static final String    VOPT_CATG_CREATE   = "catg-create";
    public static final String    VOPT_CATG_MANAGE   = "catg-manage";

    //~~~~~~~~~~~~~ 爬虫 ~~~~~~~~~~~~~//
    public static final String    VOPT_CRAWL_INPUT   = "crawl-input";
    public static final String    VOPT_CRAWL_CREATE  = "crawl-create";
    public static final String    VOPT_CRAWL_MANAGE  = "crawl-manage";

    /** 参数DAO */
    @Autowired
    protected ConfigDAO           configDAO;

    /** 爬虫DAO */
    @Autowired
    protected CrawlDAO            crawlDAO;

    /** 用户DAO */
    @Autowired
    protected UserDAO             userDAO;

    /** 用户权限DAO */
    @Autowired
    protected UserRgtDAO          userRgtDAO;

    /** 主题DAO */
    @Autowired
    protected TopicDAO            topicDAO;

    /** 图片DAO */
    @Autowired
    protected ImageDAO            imageDAO;

    /** 评论DAO */
    @Autowired
    protected ReplyDAO            replyDAO;

    /** 权限DAO */
    @Autowired
    protected RightDAO            rightDAO;

    /** 分类DAO */
    @Autowired
    protected CatgDAO             catgDAO;

    /** 校验器 */
    @Autowired
    private Validator             validator;

    /**
     * 初始化
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.validator);
        binder.registerCustomEditor(Date.class, new DatePropertyEditor());
    }

    /**
     * 设置Web数据值
     */
    public AbstractController setWebData(String key, Object value) {
        WebContext.get().getData().put(key, value);
        return this;
    }

    /**
     * 设置JSON请求标志
     */
    public AbstractController setRequestJSON() {
        WebContext.get().setRequestJSON();
        return this;
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
        WebContext.get().setRequestRedirect();
        return "redirect:" + url;
    }

    /**
     * Servlet上下文
     */
    public ServletContext findServletContext() {
        return WebContext.getServletContext();
    }

    /**
     * 获取Servlet真实路径
     */
    public String findServletRealPath(String path) {
        return this.findServletContext().getRealPath(path);
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
        this.buildResponse(response, enm.code(), enm.desp());
    }

    /**
     * 构建返回结果
     */
    public void buildResponse(BizResponse response, String code, String desp) {
        response.setSuccess(false);
        response.setRespCode(code);
        response.setRespDesp(desp);
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

        TopicValve valve = topic.findValve();
        valve.sotState(TopicStateEnum.findDefault());
        valve.sotTop(ValveBoolEnum.findDefault());
        valve.sotLink(ValveBoolEnum.findDefault());
        valve.sotMedia(TopicMediaEnum.findDefault());
        valve.sotReply(TopicReplyEnum.findDefault());
        topic.setFlag(valve.getValve());

        // topic.setCatg(TopicCatgEnum.findInit().code());
        topic.setLinkUrl(StringUtils.EMPTY);
        topic.setMediaUrl(StringUtils.EMPTY);
        topic.setTitle(StringUtils.EMPTY);
        topic.setSummary(StringUtils.EMPTY);
        topic.setContent(StringUtils.EMPTY);

        return topic;
    }

}
