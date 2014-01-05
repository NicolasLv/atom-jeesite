/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.utils;

import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.lang.user.UserContext;

/**
 * 用户转换器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserConverter.java, V1.0.1 2014年1月4日 下午2:07:34 $
 */
public class UserConverter {

    /**
     * 转换-UserDTO转换到UserContext对象
     */
    public static final void convert(UserContext uctx, UserDTO user) {
        uctx.setUserId(user.getId());
        uctx.setUserName(user.getUname());
        uctx.setUserEmail(user.getUemail());
        uctx.setUserNick(user.getUnick());
    }
    
    /**
     * 转换-UserContext转换到UserDTO对象
     */
    public static final void convert(UserDTO user, UserContext uctx) {
        user.setId(uctx.getUserId());
        user.setUname(uctx.getUserName());
        user.setUemail(uctx.getUserEmail());
        user.setUnick(uctx.getUserNick());
    }
    
}
