/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.utils;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.user.UserContext;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.model.user.UserModel;

/**
 * 用户转换器
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserConverter.java, V1.0.1 2014年1月4日 下午2:07:34 $
 */
public class UserConverter {

    public static String encode(long ticket) {
        String no = StringUtils.leftPad(Long.toString(ticket), 10, "0");
        return DateUtils.toString(new Date(), "yyyyMM") + no;
    }
    
    /**
     * 转换-UserModel转换到UserContext对象
     */
    public static final void convert(UserContext uctx, UserModel user) {
        uctx.setUserNo(user.getNo());
        uctx.setUserName(user.getRealName());
        uctx.setUserEmail(user.getEmail());
        uctx.setUserNick(user.getNickName());
    }

    /**
     * 转换-UserContext转换到UserModel对象
     */
    public static final void convert(UserModel user, UserContext uctx) {
        user.setNo(uctx.getUserNo());
        user.setRealName(uctx.getUserName());
        user.setEmail(uctx.getUserEmail());
        user.setNickName(uctx.getUserNick());
    }

}
