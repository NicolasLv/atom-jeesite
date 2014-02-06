/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.obullxl.lang.das.DAS;
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

    @Size(max = DAS.CATG.CATG_MAX)
    private String            ctgCatg;

    @NotNull
    @Size(min = 1, max = DAS.CATG.CODE_MAX)
    private String            ctgCode;

    @NotNull
    @Size(min = 1, max = DAS.CATG.SORT_MAX)
    private String            ctgSort;

    @NotNull
    @Size(min = 1, max = DAS.CATG.TITLE_MAX)
    private String            ctgTitle;

    @Size(max = DAS.CATG.EXT_MAP_MAX)
    private String            ctgExtMap;

    @Size(max = DAS.CATG.SUMMARY_MAX)
    private String            ctgSummary;

    /** 
     * @see com.github.obullxl.jeesite.web.form.AbstractForm#enumBases(java.util.List)
     */
    public void enumBases(List<EnumBaseValidate> validates) {
    }

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

    public String getCtgCatg() {
        return ctgCatg;
    }

    public void setCtgCatg(String ctgCatg) {
        this.ctgCatg = ctgCatg;
    }

    public String getCtgCode() {
        return ctgCode;
    }

    public void setCtgCode(String ctgCode) {
        this.ctgCode = ctgCode;
    }

    public String getCtgSort() {
        return ctgSort;
    }

    public void setCtgSort(String ctgSort) {
        this.ctgSort = ctgSort;
    }

    public String getCtgTitle() {
        return ctgTitle;
    }

    public void setCtgTitle(String ctgTitle) {
        this.ctgTitle = ctgTitle;
    }

    public String getCtgExtMap() {
        return ctgExtMap;
    }

    public void setCtgExtMap(String ctgExtMap) {
        this.ctgExtMap = ctgExtMap;
    }

    public String getCtgSummary() {
        return ctgSummary;
    }

    public void setCtgSummary(String ctgSummary) {
        this.ctgSummary = ctgSummary;
    }

}
