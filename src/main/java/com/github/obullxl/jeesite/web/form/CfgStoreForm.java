/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.obullxl.jeesite.dal.DBSize;
import com.github.obullxl.lang.ToString;

/**
 * 系统参数存储表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgStoreForm.java, V1.0.1 2013年12月25日 下午3:44:20 $
 */
public class CfgStoreForm extends ToString {
    private static final long serialVersionUID = -6853737465217652787L;

    @NotNull
    @Size(min = 1, max = DBSize.Cfg.CATG_MAX)
    private String            cfgCatg;

    @NotNull
    @Size(min = 1, max = DBSize.Cfg.NAME_MAX)
    private String            cfgName;

    @NotNull
    @Size(min = 1, max = DBSize.Cfg.TITLE_MAX)
    private String            cfgTitle;

    @Size(max = DBSize.Cfg.VALUE_MAX)
    private String            cfgValue;

    @Size(max = DBSize.Cfg.VALUE_EXT_MAX)
    private String            cfgValueExt;

    // ~~~~~~~~~~~~ getters and setters ~~~~~~~~~~~~~~ //

    public String getCfgCatg() {
        return cfgCatg;
    }

    public void setCfgCatg(String cfgCatg) {
        this.cfgCatg = cfgCatg;
    }

    public String getCfgName() {
        return cfgName;
    }

    public void setCfgName(String cfgName) {
        this.cfgName = cfgName;
    }

    public String getCfgTitle() {
        return cfgTitle;
    }

    public void setCfgTitle(String cfgTitle) {
        this.cfgTitle = cfgTitle;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }

    public String getCfgValueExt() {
        return cfgValueExt;
    }

    public void setCfgValueExt(String cfgValueExt) {
        this.cfgValueExt = cfgValueExt;
    }

}
