/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.xhelper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.dal.dao.UserDAO;
import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.jeesite.utils.UserConverter;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.user.UserContextHolder;
import com.github.obullxl.lang.xhelper.AbstractXHelper;

/**
 * 用户X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserXHelper.java, V1.0.1 2013年12月9日 下午7:14:17 $
 */
@Component("userXHelper")
public class UserXHelper extends AbstractXHelper {

    /** 用户DAO */
    @Autowired
    private UserDAO userDAO;

    /**
     * 获取所有用户列表
     */
    public List<UserDTO> findAll() {
        return this.userDAO.findAll();
    }

    /**
     * 根据UserID获取用户信息
     */
    public UserDTO findByUID(long userId) {
        return this.userDAO.find(userId);
    }

    /**
     * 判断用户是否为管理员
     */
    public boolean isAdmin(long userId) {
        return isAdmin(this.findByUID(userId));
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
