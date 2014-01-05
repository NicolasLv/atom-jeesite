/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.obullxl.jeesite.dal.DBSize;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.web.form.AbstractForm;
import com.github.obullxl.lang.web.form.EnumBaseValidate;

/**
 * 分类创建/更新存储对象
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgStoreForm.java, V1.0.1 2013年12月17日 下午6:59:00 $
 */
public class CatgStoreForm extends AbstractForm {
    private static final long serialVersionUID = -273118024735820451L;

    @DecimalMin("0")
    private long              ctgId            = 0L;

    @NotNull
    @Size(min = 1, max = DBSize.Catg.CODE_MAX)
    private String            ctgCode;

    @NotNull
    @Size(min = 1, max = DBSize.Catg.TOP_MAX)
    private String            ctgTop;

    @DecimalMin("0")
    private long              ctgCatg          = 0L;

    @DecimalMin("0")
    private long              ctgSort          = 0L;

    @NotNull
    @Size(min = 1, max = DBSize.Catg.NAME_MAX)
    private String            ctgName;

    /** 
     * @see com.github.obullxl.jeesite.web.form.AbstractForm#enumBases(java.util.List)
     */
    public void enumBases(List<EnumBaseValidate> validates) {
        validates.add(new EnumBaseValidate("ctgTop", this.ctgTop, ValveBoolEnum.values()));
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public long getCtgId() {
        return ctgId;
    }

    public void setCtgId(long ctgId) {
        this.ctgId = ctgId;
    }

    public String getCtgCode() {
        return ctgCode;
    }

    public void setCtgCode(String ctgCode) {
        this.ctgCode = ctgCode;
    }

    public String getCtgTop() {
        return ctgTop;
    }

    public void setCtgTop(String ctgTop) {
        this.ctgTop = ctgTop;
    }

    public long getCtgCatg() {
        return ctgCatg;
    }

    public void setCtgCatg(long ctgCatg) {
        this.ctgCatg = ctgCatg;
    }

    public long getCtgSort() {
        return ctgSort;
    }

    public void setCtgSort(long ctgSort) {
        this.ctgSort = ctgSort;
    }

    public String getCtgName() {
        return ctgName;
    }

    public void setCtgName(String ctgName) {
        this.ctgName = ctgName;
    }

}
