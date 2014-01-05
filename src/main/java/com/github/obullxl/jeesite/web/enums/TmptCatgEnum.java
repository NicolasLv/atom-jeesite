/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.enums;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 模板分类枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: TmptCatgEnum.java, V1.0.1 2014年1月3日 下午2:43:16 $
 */
public enum TmptCatgEnum implements EnumBase {
    //
    FILE(1, "F", "文件"),
    //
    DIRECTORY(2, "D", "目录"),
    //
    ;

    private final int    id;
    private final String code;
    private final String desp;

    private TmptCatgEnum(int id, String code, String desp) {
        this.id = id;
        this.code = code;
        this.desp = desp;
    }

    /**
     * 根据代码获取枚举
     */
    public static final TmptCatgEnum findByCode(String code) {
        for (TmptCatgEnum enm : values()) {
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
