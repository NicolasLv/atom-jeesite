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
    FLAG(1, "flag", "flag"),
	//
    CATG(2, "catg", "catg"),
	//
    LINK_URL(3, "link_url", "link_url"),
	//
    MEDIA_URL(4, "media_url", "media_url"),
	//
    VISIT_CNT(5, "visit_cnt", "visit_cnt"),
	//
    REPLY_CNT(6, "reply_cnt", "reply_cnt"),
	//
    TITLE(7, "title", "title"),
	//
    SUMMARY(8, "summary", "summary"),
	//
    CONTENT(9, "content", "content"),
	//
    GMT_CREATE(10, "gmt_create", "gmt_create"),
	//
    GMT_MODIFY(11, "gmt_modify", "gmt_modify"),
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
     * 根据字段名获取枚举内�?	 */
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
