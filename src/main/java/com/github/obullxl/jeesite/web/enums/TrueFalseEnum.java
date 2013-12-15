/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.enums;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 状态标志枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: TrueFalseEnum.java, V1.0.1 2013年12月14日 下午10:46:13 $
 */
public enum TrueFalseEnum implements EnumBase {
    //
    TRUE(1, "T", "是"),
    //
    FALSE(2, "F", "否"),
    //
    ;

    private final int    id;
    private final String code;
    private final String desp;

    private TrueFalseEnum(int id, String code, String desp) {
        this.id = id;
        this.code = code;
        this.desp = desp;
    }

    /**
     * 初始状态
     */
    public static final TrueFalseEnum findInit() {
        return FALSE;
    }

    /**
     * 根据代码获取枚举
     */
    public static final TrueFalseEnum findByCode(String code) {
        for (TrueFalseEnum enm : values()) {
            if (StringUtils.equalsIgnoreCase(enm.code(), code)) {
                return enm;
            }
        }

        return null;
    }
    
    /**
     * 根据代码获取默认枚举
     */
    public static final TrueFalseEnum findDefault(String code) {
        TrueFalseEnum enm = findByCode(code);
        if(enm == null) {
            enm = findInit();
        }

        return enm;
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
