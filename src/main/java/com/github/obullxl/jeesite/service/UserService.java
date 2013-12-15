/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.service;

import java.util.List;

import com.github.obullxl.jeesite.dal.dto.UserDTO;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserService.java, V1.0.1 2013年11月25日 下午12:52:29 $
 */
public interface UserService {
    public long count();
    public List<UserDTO> findAll();
}
