/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.jeesite.dal.DBSize;
import com.github.obullxl.lang.ToString;

/**
 * 分类请求对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgRTO.java, V1.0.1 2013年12月17日 下午6:59:00 $
 */
public class CatgForm extends ToString {
    private static final long serialVersionUID = -273118024735820451L;

    @DecimalMin("0")
    private long              catgId           = 0L;

    @NotNull
    @Size(min = 1, max = DBSize.Catg.CODE_MAX)
    private String            catgCode;

    @NotNull
    @Size(min = 1, max = DBSize.Catg.TOP_MAX)
    private String            catgTop;

    @DecimalMin("0")
    private long              catgCatg         = 0L;

    @DecimalMin("0")
    private long              catgSort         = 0L;

    @NotNull
    @Size(min = 1, max = DBSize.Catg.NAME_MAX)
    private String            catgName;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public long getCatgId() {
        return catgId;
    }

    public void setCatgId(long catgId) {
        this.catgId = catgId;
    }

    public String getCatgCode() {
        return StringUtils.lowerCase(catgCode);
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public String getCatgTop() {
        return catgTop;
    }

    public void setCatgTop(String catgTop) {
        this.catgTop = catgTop;
    }

    public long getCatgCatg() {
        return catgCatg;
    }

    public void setCatgCatg(long catgCatg) {
        this.catgCatg = catgCatg;
    }

    public long getCatgSort() {
        return catgSort;
    }

    public void setCatgSort(long catgSort) {
        this.catgSort = catgSort;
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }

}
