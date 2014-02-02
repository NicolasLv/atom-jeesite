/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.webx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.utils.UserConverter;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.user.UserContextHolder;
import com.github.obullxl.lang.user.UserDTO;
import com.github.obullxl.lang.user.UserService;
import com.github.obullxl.lang.webx.WebX;

/**
 * 用户X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserWebX.java, V1.0.1 2013年12月9日 下午7:14:17 $
 */
@Component("userWebX")
public class UserWebX implements WebX {

    /** 用户模型服务 */
    @Autowired
    private UserService userService;

    /**
     * 获取所有用户列表
     */
    public List<UserDTO> findAll() {
        return this.userService.find();
    }

    /**
     * 根据编号获取用户信息
     */
    public UserDTO findByNo(String no) {
        return this.userService.findByNo(no);
    }

    /**
     * 判断用户是否为管理员
     */
    public boolean isAdmin(String no) {
        return isAdmin(this.findByNo(no));
    }

    /**
     * 判断用户是否为管理员
     */
    public static boolean isAdmin(UserDTO user) {
        if (user != null) {
            return (user.findValve().gotAdmin() == ValveBoolEnum.TRUE);
        }

        return false;
    }

    /**
     * 获取登录用户
     */
    public UserDTO findSessionUser() {
        UserDTO user = new UserDTO();
        UserConverter.convert(user, UserContextHolder.get());

        return user;
    }

}
