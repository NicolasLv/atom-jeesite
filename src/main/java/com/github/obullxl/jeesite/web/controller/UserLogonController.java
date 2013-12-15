/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.user.UserContext;
import com.github.obullxl.lang.user.UserContextUtils;
import com.github.obullxl.lang.utils.LogUtils;
import com.github.obullxl.lang.utils.MD5Utils;
import com.github.obullxl.lang.web.WebContext;

/**
 * 用户登录控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserLogonController.java, V1.0.1 2013年12月12日 下午1:22:21 $
 */
@Controller
public class UserLogonController extends AbstractController {

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login() {
        return this.toFrontView("/login");
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(ModelMap model, String uname, String passwd, String extra) {
        // 操作结果
        BizResponse response = new BizResponse();
        model.put("result", response);

        response.setBizLog(LogUtils.findLogID());
        response.setSuccess(true);

        response.getBizData().put("uname", String.valueOf(uname));
        response.getBizData().put("passwd", String.valueOf(passwd));
        response.getBizData().put("extra", String.valueOf(extra));

        try {
            // 获取用户
            UserDTO user = this.userDAO.findByName(uname);
            if (user == null) {
                this.buildResponse(response, BizResponseEnum.USER_NOT_EXIST);
                return this.toFrontView("/login");
            }

            if (!StringUtils.equals(user.getPasswd(), MD5Utils.digest(passwd))) {
                this.buildResponse(response, BizResponseEnum.INVALID_PASSWD);
                return this.toFrontView("/login");
            }

            // TODO: 设置上下文
            UserContext uctx = UserContext.newMockContext();
            UserContextUtils.setLogin(uctx, true);
            UserContextUtils.setAdmin(uctx, true);
            UserContextUtils.setSessionContext(WebContext.get().getSession(), uctx);

            String gotoUrl = UserContextUtils.findGotoURL();
            if (StringUtils.isBlank(gotoUrl)) {
                return this.redirectTo(gotoUrl);
            }
        } catch (Exception e) {
            logger.error("用户登录异常[" + uname + "]!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // 成功登录返回
        return this.toFrontView("/login");
    }

    /**
     * 用户登出
     */
    @RequestMapping("/logout.html")
    public String logout() {
        UserContextUtils.setSessionContext(WebContext.get().getSession(), UserContext.newMockContext());
        return this.redirectTo("/");
    }

}
