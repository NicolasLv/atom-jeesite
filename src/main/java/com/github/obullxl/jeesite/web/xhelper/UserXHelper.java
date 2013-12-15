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

}
