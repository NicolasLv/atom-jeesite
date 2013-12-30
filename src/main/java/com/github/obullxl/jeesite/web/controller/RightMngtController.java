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

import com.github.obullxl.jeesite.dal.dto.RightDTO;
import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.jeesite.dal.dto.UserRgtDTO;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.lang.biz.BizResponse;

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
    @RequestMapping("/right/manage.html")
    public String manage() {
        return this.toAdminView(VOPT_RIGHT_MANAGE, "right-manage");
    }

    /**
     * 新增权限
     */
    @RequestMapping(value = "/right/create.html", method = RequestMethod.GET)
    public String create() {
        return this.toAdminView(VOPT_RIGHT_CREATE, "right-create");
    }

    @ResponseBody
    @RequestMapping(value = "/right/create.html", method = RequestMethod.POST)
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
            RightDTO rgt = this.rightDAO.findCode(code);
            if (rgt != null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_HAS_EXIST);
                return response;
            }

            // 新增
            rgt = new RightDTO();
            rgt.setCode(code);
            rgt.setName(name);

            long id = this.rightDAO.insert(rgt);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, Long.toString(id));
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
    @RequestMapping(value = "/right/update-{id}.html", method = RequestMethod.GET)
    public String update(@PathVariable long id) {
        this.setWebData("rightId", id);
        return this.toAdminView(VOPT_RIGHT_MANAGE, "right-update");
    }

    @ResponseBody
    @RequestMapping(value = "/right/update-{id}.html", method = RequestMethod.POST)
    public BizResponse update(@PathVariable long id, String code, String name) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            if (StringUtils.isBlank(code)) {
                this.buildResponse(response, BizResponseEnum.REQUIRE_PARAM);
                return response;
            }

            // 存在性
            RightDTO rgt = this.rightDAO.find(id);
            if (rgt == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 修改
            rgt.setCode(code);
            rgt.setName(name);

            this.rightDAO.update(rgt);
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
    @RequestMapping(value = "/right/assign-{id}.html", method = RequestMethod.GET)
    public String assign(@PathVariable long id) {
        this.setWebData("userId", id);

        return this.toAdminView(VOPT_USER_MANAGE, "right-assign");
    }

    /**
     * 授权
     */
    @ResponseBody
    @RequestMapping(value = "/right/grant-{id}.html", method = RequestMethod.POST)
    public BizResponse grant(@PathVariable long id, String rgtCode) {
        this.setWebData("userId", id).setWebData("rgtCode", rgtCode);

        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            UserDTO user = this.userDAO.find(id);
            if (user != null && !user.findBitFlag().isAdmin()) {
                UserRgtDTO userRgt = this.userRgtDAO.find(user.getUname(), rgtCode);
                if (userRgt == null) {
                    userRgt = new UserRgtDTO();
                    userRgt.setName(user.getUname());
                    userRgt.setRgtCode(rgtCode);

                    long urid = this.userRgtDAO.insert(userRgt);
                    response.getBizData().put(BizResponse.BIZ_ID_KEY, Long.toString(urid));
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
    @RequestMapping(value = "/right/revoke-{id}.html", method = RequestMethod.POST)
    public BizResponse revoke(@PathVariable long id, String rgtCode) {
        this.setWebData("userId", id).setWebData("rgtCode", rgtCode);

        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            UserDTO user = this.userDAO.find(id);
            if (user != null) {
                this.userRgtDAO.delete(user.getUname(), rgtCode);
            }
        } catch (Exception e) {
            logger.error("用户取消权限异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
