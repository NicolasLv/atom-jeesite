/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.dal.dao;

import java.util.List;

import com.github.obullxl.jeesite.dal.dto.UserDO;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserDAO.java, V1.0.1 2013年11月25日 下午12:49:02 $
 */
public interface UserDAO {

    public int count();
    
    public List<UserDO> findAll();

}
