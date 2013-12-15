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

import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.utils.MD5Utils;

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
    @RequestMapping("/user/manage.html")
    public String manage() {
        return this.toAdminView(VOPT_USER_MANAGE, "user-manage");
    }

    /**
     * 新增用户
     */
    @RequestMapping(value = "/user/create.html", method = RequestMethod.GET)
    public String userCreate() {
        return this.toAdminView(VOPT_USER_CREATE, "user-create");
    }

    @ResponseBody
    @RequestMapping(value = "/user/create.html", method = RequestMethod.POST)
    public BizResponse userCreate(String uname, String passwd, String passwd2, String uemail) {
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
            UserDTO user = this.userDAO.findByName(uname);
            if (user != null) {
                this.buildResponse(response, BizResponseEnum.EXIST_UNAME);
                return response;
            }

            // 电子邮箱检查
            // TODO:

            // 新建
            user = this.newInitUser();
            user.setUname(uname);
            user.setPasswd(MD5Utils.digest(passwd));
            user.setUemail(uemail);

            // 更新
            long id = this.userDAO.insert(user);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, Long.toString(id));
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
    @RequestMapping(value = "/user/update-{id}.html", method = RequestMethod.GET)
    public String topicModify(@PathVariable long id) {
        this.setWebData("userId", id);

        return this.toAdminView(VOPT_USER_MANAGE, "user-update");
    }

    @ResponseBody
    @RequestMapping(value = "/user/update-{id}.html", method = RequestMethod.POST)
    public BizResponse topicUpdate(@PathVariable long id, String uname, String passwd, String passwd2, String uemail) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 查询
            UserDTO user = this.userDAO.findByName(uname);
            user.setPasswd(MD5Utils.digest(passwd));
            user.setUemail(uemail);

            // 更新
            this.userDAO.update(user);
        } catch (Exception e) {
            logger.error("修改用户异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
