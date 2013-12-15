/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.enums;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 业务返回码枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: BizResponseEnum.java, V1.0.1 2013年12月8日 下午8:24:37 $
 */
public enum BizResponseEnum implements EnumBase {
    //
    SYSTEM_ERROR("系统异常"),
    //
    REQUIRE_PARAM("参数为空"),
    //
    INVALID_PASSWD("密码错误"),
    //
    EXIST_UNAME("用户名存在"),
    //
    EXIST_UEMAIL("电子邮箱存在"),
    //
    GROOVY_ERROR("Groovy脚本错误"),
    //
    USER_NOT_EXIST("用户不存在"),
    //
    CATG_NOT_EXIST("分类不存在"),
    //
    ;

    private final String desp;

    private BizResponseEnum(String desp) {
        this.desp = desp;
    }

    public static final BizResponseEnum findByCode(String code) {
        for (BizResponseEnum enm : values()) {
            if (StringUtils.equalsIgnoreCase(enm.code(), code)) {
                return enm;
            }
        }

        return null;
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
