/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.field;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.enums.EnumBase;

/**
 * A fields enum object database table <tt>atom_topic</tt>.
 */
public enum TopicFieldEnum implements EnumBase {
	//
    ID(0, "id", "id"),
	//
    STATE(1, "state", "state"),
	//
    CATG(2, "catg", "catg"),
	//
    TFLAG(3, "tflag", "tflag"),
	//
    RFLAG(4, "rflag", "rflag"),
	//
    RFROM(5, "rfrom", "rfrom"),
	//
    MFLAG(6, "mflag", "mflag"),
	//
    MPATH(7, "mpath", "mpath"),
	//
    MCOUNT(8, "mcount", "mcount"),
	//
    TREPLY(9, "treply", "treply"),
	//
    VISIT(10, "visit", "visit"),
	//
    REPLY(11, "reply", "reply"),
	//
    TITLE(12, "title", "title"),
	//
    SUMMARY(13, "summary", "summary"),
	//
    CONTENT(14, "content", "content"),
	//
    GMT_CREATE(15, "gmt_create", "gmt_create"),
	//
    GMT_MODIFY(16, "gmt_modify", "gmt_modify"),
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
    private TopicFieldEnum(int index, String field, String remark) {
        this.index = index;
        this.field = field;
		this.remark = remark;
    }

    /**
     * 根据字段名获取枚举内容
	 */
    public static TopicFieldEnum findByField(String field) {
        field = StringUtils.lowerCase(field);
        if(StringUtils.isBlank(field)) {
            return null;
        }
        
        for(TopicFieldEnum enm : values()) {
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
