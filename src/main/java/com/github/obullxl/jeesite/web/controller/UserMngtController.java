/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.dal.DBSize;
import com.github.obullxl.jeesite.utils.UserConverter;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.user.UserDTO;
import com.github.obullxl.lang.utils.MD5Utils;
import com.github.obullxl.lang.utils.TextUtils;

/**
 * 用户后台管理控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserMngtController.java, V1.0.1 2013年12月9日 下午7:19:35 $
 */
@Controller
@RequestMapping("/admin")
public class UserMngtController extends AbstractController {

    /**
     * 用户管理
     */
    @RequestMapping("/user/manage.htm")
    public String manage() {
        return this.toAdminView(VOPT_USER_MANAGE, "user-manage");
    }

    /**
     * 新增用户
     */
    @RequestMapping(value = "/user/create.htm", method = RequestMethod.GET)
    public String create() {
        return this.toAdminView(VOPT_USER_CREATE, "user-create");
    }

    @ResponseBody
    @RequestMapping(value = "/user/create.htm", method = RequestMethod.POST)
    public BizResponse create(String uname, String passwd, String passwd2, String uemail) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 参数检查
            if (StringUtils.isBlank(uname) || StringUtils.isBlank(passwd) || StringUtils.isBlank(uemail)) {
                this.buildResponse(response, BizResponseEnum.REQUIRE_PARAM);
                return response;
            }

            if (!StringUtils.equals(passwd, passwd2)) {
                this.buildResponse(response, BizResponseEnum.INVALID_PASSWD);
                return response;
            }

            // 用户名检查
            UserDTO user = this.userService.findByNickName(uname);
            if (user != null) {
                this.buildResponse(response, BizResponseEnum.EXIST_UNAME);
                return response;
            }

            // 电子邮箱检查
            // TODO:

            // 新建
            user = this.newInitUser();
            user.setNo(UserConverter.encode(this.userTicketService.nextValue()));
            user.setNickName(uname);
            user.setPasswd(MD5Utils.digest(passwd));
            user.setEmail(uemail);

            // 更新
            this.userService.create(user);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, uname);
        } catch (Exception e) {
            logger.error("新增用户异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 更新用户
     */
    @RequestMapping(value = "/user/update-{id}.htm", method = RequestMethod.GET)
    public String update(@PathVariable long id) {
        this.setWebData("userId", id);

        return this.toAdminView(VOPT_USER_MANAGE, "user-update");
    }

    @ResponseBody
    @RequestMapping(value = "/user/update-{id}.htm", method = RequestMethod.POST)
    public BizResponse update(@PathVariable long id, String uname, String passwd, String passwd2, String uemail) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 查询
            UserDTO user = this.userService.findByNickName(uname);
            user.setPasswd(MD5Utils.digest(passwd));
            user.setEmail(uemail);

            // 更新
            this.userService.update(user);
        } catch (Exception e) {
            logger.error("修改用户异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/user/cinfo-{id}.htm", method = RequestMethod.GET)
    public String cinfo(@PathVariable long id) {
        this.setWebData("userId", id);

        return this.toAdminView(VOPT_USER_CINFO, "user-cinfo");
    }

    @ResponseBody
    @RequestMapping(value = "/user/cinfo.htm", method = RequestMethod.POST)
    public BizResponse cinfo(String no, String unick) {
        this.setWebData("no", no);

        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 查找
            UserDTO user = this.userService.findByNo(no);
            if (user == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 长度
            unick = StringUtils.trimToEmpty(unick);
            user.setNickName(TextUtils.truncate(unick, DBSize.User.NICK_NAME_MAX));

            // 更新
            this.userService.update(user);
        } catch (Exception e) {
            logger.error("修改登录用户基本信息异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 修改用户邮箱
     */
    @RequestMapping(value = "/user/cemail-{id}.htm", method = RequestMethod.GET)
    public String cemail(@PathVariable long id) {
        this.setWebData("userId", id);

        return this.toAdminView(VOPT_USER_CEMAIL, "user-cemail");
    }

    @ResponseBody
    @RequestMapping(value = "/user/cemail.htm", method = RequestMethod.POST)
    public BizResponse cemail(String no, String uemail) {
        this.setWebData("no", no);

        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 查找
            UserDTO user = this.userService.findByNo(no);
            if (user == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 长度
            uemail = StringUtils.trimToEmpty(uemail);
            user.setEmail(TextUtils.truncate(uemail, DBSize.User.EMAIL_MAX));

            // 更新
            this.userService.update(user);
        } catch (Exception e) {
            logger.error("修改登录用户电子邮箱异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 修改用户密码
     */
    @RequestMapping(value = "/user/cpasswd-{id}.htm", method = RequestMethod.GET)
    public String cpasswd(@PathVariable long id) {
        this.setWebData("userId", id);

        return this.toAdminView(VOPT_USER_CPASSWD, "user-cpasswd");
    }

    @ResponseBody
    @RequestMapping(value = "/user/cpasswd.htm", method = RequestMethod.POST)
    public BizResponse cpasswd(String no, String opasswd, String passwd, String passwd2) {
        this.setWebData("no", no);

        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 查找
            UserDTO user = this.userService.findByNo(no);
            if (user == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 原密码
            if (!StringUtils.equals(user.getPasswd(), MD5Utils.digest(opasswd))) {
                this.buildResponse(response, BizResponseEnum.INVALID_PASSWD);
                return response;
            }

            // 新密码
            if (!StringUtils.equals(passwd, passwd2)) {
                this.buildResponse(response, BizResponseEnum.INVALID_PASSWD);
                return response;
            }

            // 长度
            user.setPasswd(MD5Utils.digest(passwd));

            // 更新
            this.userService.update(user);
        } catch (Exception e) {
            logger.error("修改登录用户密码异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
