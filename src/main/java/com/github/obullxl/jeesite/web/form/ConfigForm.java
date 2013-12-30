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
 * 参数配置表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: ConfigForm.java, V1.0.1 2013年12月25日 下午3:44:20 $
 */
public class ConfigForm extends ToString {
    private static final long serialVersionUID = -6853737465217652787L;

    @NotNull
    @Size(min = 1, max = DBSize.Config.CATG_MAX)
    private String            cfgCatg;

    @NotNull
    @Size(min = 1, max = DBSize.Config.NAME_MAX)
    private String            cfgName;

    @NotNull
    @Size(min = 1, max = DBSize.Config.STATE_MAX)
    private String            cfgState;

    @NotNull
    @Size(min = 1, max = DBSize.Config.VALUE_MAX)
    private String            cfgValue;

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

    public String getCfgState() {
        return cfgState;
    }

    public void setCfgState(String cfgState) {
        this.cfgState = cfgState;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }

}
