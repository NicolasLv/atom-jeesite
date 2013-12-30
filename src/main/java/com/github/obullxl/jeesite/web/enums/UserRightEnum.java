/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.enums;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 用户权限枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserRightEnum.java, V1.0.1 2013年12月15日 下午7:48:24 $
 */
public enum UserRightEnum implements EnumBase {
    //
    RGT_LOGIN_NORMAL("登录"),
    //
    RGT_CATG_CREATE(""),
    //
    RGT_CATG_UPDATE(""),
    //
    RGT_CATG_DELETE(""),
    //
    RGT_CATG_MANAGE(""),
    //
    //
    RGT_CRAWL_CREATE(""),
    //
    RGT_CRAWL_UPDATE(""),
    //
    RGT_CRAWL_DELETE(""),
    //
    RGT_CRAWL_MANAGE(""),
    //
    //
    RGT_TOPIC_CREATE(""),
    //
    RGT_TOPIC_UPDATE(""),
    //
    RGT_TOPIC_DELETE(""),
    //
    RGT_TOPIC_MANAGE(""),
    //
    //
    RGT_USER_CREATE(""),
    //
    RGT_USER_UPDATE(""),
    //
    RGT_USER_DELETE(""),
    //
    RGT_USER_MANAGE(""),
    //
    ;

    private final String desp;

    private UserRightEnum(String desp) {
        this.desp = desp;
    }

    /**
     * 根据代码获取枚举
     */
    public static final UserRightEnum findByCode(String code) {
        try {
            return UserRightEnum.valueOf(code);
        } catch (Exception e) {
            // ignore
            return null;
        }
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#id()
     */
    public int id() {
        return this.ordinal();
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#code()
     */
    public String code() {
        return this.name();
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }

}
