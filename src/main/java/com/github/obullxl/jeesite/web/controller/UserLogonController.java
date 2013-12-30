/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.jeesite.dal.dto.UserRgtDTO;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.enums.UserRightEnum;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.user.UserContext;
import com.github.obullxl.lang.user.UserContextUtils;
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

    @ResponseBody
    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public BizResponse login(ModelMap model, String uname, String passwd, String extra) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 获取用户
            UserDTO user = this.userDAO.findByName(uname);
            if (user == null) {
                this.buildResponse(response, BizResponseEnum.USER_NOT_EXIST);
                return response;
            }

            if (!StringUtils.equals(user.getPasswd(), MD5Utils.digest(passwd))) {
                this.buildResponse(response, BizResponseEnum.INVALID_PASSWD);
                return response;
            }

            // 设置上下文
            HttpSession session = WebContext.get().getSession();
            UserContext uctx = UserContextUtils.findSessionContext(session);
            if (uctx == null) {
                uctx = UserContext.newMockContext();
            }
            
            uctx.setUserId(user.getId());
            uctx.setUserName(user.getUname());
            uctx.setUserEmail(user.getUemail());
            uctx.setUserNick(user.getUnick());
            
            UserContextUtils.setSessionContext(session, uctx);
            String gotoUrl = UserContextUtils.findGotoURL(uctx);

            // 设置用户信息
            UserContextUtils.setLogin(uctx, true);
            uctx.getUserRights().add(UserRightEnum.RGT_LOGIN_NORMAL.code());

            boolean admin = user.findBitFlag().isAdmin();
            UserContextUtils.setAdmin(uctx, admin);
            if (!admin) {
                List<UserRgtDTO> rgts = this.userRgtDAO.findByUser(uname);
                for (UserRgtDTO rgt : rgts) {
                    uctx.getUserRights().add(rgt.getRgtCode());
                }
            }

            // 页面跳转
            if (StringUtils.isNotBlank(gotoUrl)) {
                response.getBizData().put("gotoUrl", gotoUrl);
            }

            // 成功登录返回
            return response;
        } catch (Exception e) {
            logger.error("用户登录异常[" + uname + "]!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
            return response;
        }
    }

    /**
     * 用户登出
     */
    @RequestMapping("/logout.html")
    public String logout() {
        HttpSession session = WebContext.get().getSession();
        UserContextUtils.setSessionContext(session, UserContext.newMockContext());
        
        return this.redirectTo("/");
    }

}
