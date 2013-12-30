/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.field;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * A fields enum object database table <tt>atom_reply</tt>.
 */
public enum ReplyFieldEnum implements EnumBase {
	//
    ID(0, "id", "id"),
	//
    STATE(1, "state", "state"),
	//
    TOPIC(2, "topic", "topic"),
	//
    TITLE(3, "title", "title"),
	//
    UNAME(4, "uname", "uname"),
	//
    UEMAIL(5, "uemail", "uemail"),
	//
    USITE(6, "usite", "usite"),
	//
    CONTENT(7, "content", "content"),
	//
    GMT_CREATE(8, "gmt_create", "gmt_create"),
	//
    GMT_MODIFY(9, "gmt_modify", "gmt_modify"),
    //
    ;

    /** 序号 */
    private final int    index;
    
    /** 字段名称 */
    private final String field;
	
	/** 字段描述 */
	private final String remark;

    /**
     * CTOR
     */
    private ReplyFieldEnum(int index, String field, String remark) {
        this.index = index;
        this.field = field;
		this.remark = remark;
    }

    /**
     * 根据字段名获取枚举内�?	 */
    public static ReplyFieldEnum findByField(String field) {
        field = StringUtils.lowerCase(field);
        if(StringUtils.isBlank(field)) {
            return null;
        }
        
        for(ReplyFieldEnum enm : values()) {
            if(StringUtils.equals(enm.getField(), field)) {
                return enm;
            }
        }
        
        return null;
    }

    /**
     * 获取字段名称
     */
    public String getField() {
        return this.field;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#id()
     */
    public int id() {
        return this.index;
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#code()
     */
    public String code() {
        return name();
    }

    /** 
     * @see com.github.obullxl.lang.enums.EnumBase#desp()
     */
    public String desp() {
        return this.remark;
    }
	
}
