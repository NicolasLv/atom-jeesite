/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.obullxl.jeesite.utils.UserConverter;
import com.github.obullxl.jeesite.web.enums.UserRightEnum;
import com.github.obullxl.jeesite.web.form.UserLoginForm;
import com.github.obullxl.lang.enums.ActiveEnum;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.relate.UserRightDTO;
import com.github.obullxl.lang.user.UserContext;
import com.github.obullxl.lang.user.UserContextUtils;
import com.github.obullxl.lang.user.UserDTO;
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
    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String login() {
        this.setWebData("form", new UserLoginForm());
        return this.toFrontView(VIEW_USER_LOGIN);
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    public String login(@Valid UserLoginForm form, BindingResult errors) {
        this.setWebData("form", form);

        try {
            // 校验
            form.validateEnumBase(errors);

            if (errors.hasErrors()) {
                this.setWebData(ERR_MSG_KEY, "参数输入错误！");
                return this.toFrontView(VIEW_USER_LOGIN);
            }

            // 获取用户
            UserDTO user = this.userService.findByNickName(form.getUsrName());
            if (user == null) {
                this.setWebData(ERR_MSG_KEY, "用户不存在！");
                return this.toFrontView(VIEW_USER_LOGIN);
            }

            if (user.findValve().gotActiveState() != ActiveEnum.ACTIVE_NORMAL) {
                this.setWebData(ERR_MSG_KEY, "用户未激活，请联系管理员！");
                return this.toFrontView(VIEW_USER_LOGIN);
            }

            if (!StringUtils.equals(user.getPasswd(), MD5Utils.digest(form.getUsrPasswd()))) {
                this.setWebData(ERR_MSG_KEY, "密码输入错误！");
                return this.toFrontView(VIEW_USER_LOGIN);
            }

            // 设置上下文
            HttpSession session = WebContext.get().getSession();
            UserContext uctx = UserContextUtils.findSessionContext(session);
            if (uctx == null) {
                uctx = UserContext.newMockContext();
            }

            UserConverter.convert(uctx, user);
            UserContextUtils.setSessionContext(session, uctx);

            // 设置用户信息
            UserContextUtils.setLogin(uctx, true);
            uctx.getUserRights().add(UserRightEnum.RGT_LOGIN_NORMAL.code());

            boolean admin = (user.findValve().gotAdmin() == ValveBoolEnum.TRUE);
            UserContextUtils.setAdmin(uctx, admin);
            if (!admin) {
                List<UserRightDTO> rgts = this.userRightService.findByUserNo(user.getNo());
                for (UserRightDTO rgt : rgts) {
                    uctx.getUserRights().add(rgt.getRgtCode());
                }
            }

            // 页面跳转
            String gotoUrl = UserContextUtils.findGotoURL(uctx);
            if (StringUtils.isBlank(gotoUrl)) {
                gotoUrl = ADMIN_INDEX;
            }

            // 成功登录返回
            return this.redirectTo(gotoUrl);
        } catch (Exception e) {
            logger.error("用户登录异常-{}", form, e);
            this.setWebData(ERR_MSG_KEY, "系统异常，请联系管理员！");
            return this.toFrontView(VIEW_USER_LOGIN);
        }
    }

    /**
     * 用户登出
     */
    @RequestMapping("/logout.htm")
    public String logout() {
        HttpSession session = WebContext.get().getSession();
        UserContextUtils.setSessionContext(session, UserContext.newMockContext());

        return this.redirectTo("/");
    }

}
