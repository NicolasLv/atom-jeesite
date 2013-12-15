/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.enums;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * 主题分类枚举
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicCatgEnum.java, V1.0.1 2013年12月6日 上午9:56:16 $
 */
public enum TopicCatgEnum implements EnumBase {
    //
    BLOG(1, "blog", "博客"),
    //
    NEWS(2, "news", "资讯"),
    //
    ALBUM(3, "album", "美图"),
    //
    MISC(4, "misc", "杂谈"),
    //
    ;
    
    private final int id;
    private final String code;
    private final String desp;
    
    private TopicCatgEnum(int id, String code, String desp) {
        this.id = id;
        this.code = code;
        this.desp = desp;
    }
    
    /**
     * 初始分类
     */
    public static final TopicCatgEnum findInit() {
        return BLOG;
    }
    
    public static final TopicCatgEnum findByCode(String code) {
        for(TopicCatgEnum enm : values()) {
            if(StringUtils.equalsIgnoreCase(enm.code(), code)) {
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
