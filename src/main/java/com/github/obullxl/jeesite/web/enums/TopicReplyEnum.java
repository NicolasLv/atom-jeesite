/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.enums;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 主题评论标志
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicReplyEnum.java, V1.0.1 2014年1月1日 下午5:15:43 $
 */
public enum TopicReplyEnum implements EnumBase {
    //
    OPEN(1, "T", "开放"),
    //
    CLOSE(2, "F", "关闭"),
    //
    LOGIN(3, "U", "登录"),
    //
    ;

    private final int    id;
    private final String code;
    private final String desp;

    private TopicReplyEnum(int id, String code, String desp) {
        this.id = id;
        this.code = code;
        this.desp = desp;
    }

    /**
     * 初始状态
     */
    public static final TopicReplyEnum findDefault() {
        return OPEN;
    }

    public static final TopicReplyEnum findDefault(String code) {
        for (TopicReplyEnum enm : values()) {
            if (StringUtils.equalsIgnoreCase(enm.code(), code)) {
                return enm;
            }
        }

        return OPEN;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#id()
     */
    public int id() {
        return this.id;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#code()
     */
    public String code() {
        return this.code;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.desp;
    }
    
}
