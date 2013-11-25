/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.github.obullxl.jeesite.dal.dao.UserDAO;
import com.github.obullxl.jeesite.dal.dto.UserDO;
import com.github.obullxl.jeesite.service.UserService;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserServiceImpl.java, V1.0.1 2013年11月25日 下午12:53:21 $
 */
public class UserServiceImpl implements InitializingBean, UserService {
    private UserDAO userDAO;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        Assert.notNull(this.userDAO, "DAO[UserDAO]注入失败!");
    }

    public int count() {
        return this.userDAO.count();
    }

    public List<UserDO> findAll() {
        return this.userDAO.findAll();
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
