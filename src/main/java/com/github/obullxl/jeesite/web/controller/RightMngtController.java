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

import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.cfg.RightDTO;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.relate.UserRightDTO;
import com.github.obullxl.lang.user.UserDTO;

/**
 * 权限管理控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: RightMngtController.java, V1.0.1 2013年12月16日 下午12:39:05 $
 */
@Controller
@RequestMapping("/admin")
public class RightMngtController extends AbstractController {

    /**
     * 权限管理
     */
    @RequestMapping("/right/manage.htm")
    public String manage() {
        return this.toAdminView(VOPT_RIGHT_MANAGE, "right-manage");
    }

    /**
     * 新增权限
     */
    @RequestMapping(value = "/right/create.htm", method = RequestMethod.GET)
    public String create() {
        return this.toAdminView(VOPT_RIGHT_CREATE, "right-create");
    }

    @ResponseBody
    @RequestMapping(value = "/right/create.htm", method = RequestMethod.POST)
    public BizResponse create(String code, String name) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            if (StringUtils.isBlank(code)) {
                this.buildResponse(response, BizResponseEnum.REQUIRE_PARAM);
                return response;
            }

            // 存在性
            RightDTO rgt = this.rightService.find(code);
            if (rgt != null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 新增
            rgt = new RightDTO();
            rgt.setCode(code);
            rgt.setName(name);

            this.rightService.create(rgt);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, code);
        } catch (Exception e) {
            logger.error("权限新增异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 修改权限
     */
    @RequestMapping(value = "/right/update-{id}.htm", method = RequestMethod.GET)
    public String update(@PathVariable long id) {
        this.setWebData("rightId", id);
        return this.toAdminView(VOPT_RIGHT_MANAGE, "right-update");
    }

    @ResponseBody
    @RequestMapping(value = "/right/update.htm", method = RequestMethod.POST)
    public BizResponse update(String code, String name) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            if (StringUtils.isBlank(code)) {
                this.buildResponse(response, BizResponseEnum.REQUIRE_PARAM);
                return response;
            }

            // 存在性
            RightDTO rgt = this.rightService.find(code);
            if (rgt == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 修改
            rgt.setCode(code);
            rgt.setName(name);

            this.rightService.update(rgt);
        } catch (Exception e) {
            logger.error("权限更新异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 用户授权
     */
    @RequestMapping(value = "/right/assign-{id}.htm", method = RequestMethod.GET)
    public String assign(@PathVariable long id) {
        this.setWebData("userId", id);

        return this.toAdminView(VOPT_USER_MANAGE, "right-assign");
    }

    /**
     * 授权
     */
    @ResponseBody
    @RequestMapping(value = "/right/grant.htm", method = RequestMethod.POST)
    public BizResponse grant(String userNo, String rgtCode) {
        this.setWebData("userNo", userNo).setWebData("rgtCode", rgtCode);

        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            UserDTO user = this.userService.findByNo(userNo);
            if (user != null && (user.findValve().gotAdmin() != ValveBoolEnum.TRUE)) {
                UserRightDTO userRgt = this.userRightService.findByUnique(userNo, rgtCode);
                if (userRgt == null) {
                    RightDTO right = this.rightService.find(rgtCode);

                    userRgt = new UserRightDTO();
                    userRgt.setUserNo(userNo);
                    userRgt.setNickName(user.getNickName());
                    userRgt.setRgtCode(rgtCode);
                    userRgt.setRgtName(right.getName());

                    this.userRightService.create(userRgt);
                    response.getBizData().put(BizResponse.BIZ_ID_KEY, userNo + "-" + rgtCode);
                }
            }
        } catch (Exception e) {
            logger.error("用户授权异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 取消权限
     */
    @ResponseBody
    @RequestMapping(value = "/right/revoke.htm", method = RequestMethod.POST)
    public BizResponse revoke(String userNo, String rgtCode) {
        this.setWebData("userId", userNo).setWebData("rgtCode", rgtCode);

        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            UserDTO user = this.userService.findByNo(userNo);
            if (user != null) {
                this.userRightService.removeByUnique(userNo, rgtCode);
            }
        } catch (Exception e) {
            logger.error("用户取消权限异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
