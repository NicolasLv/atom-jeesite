/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.obullxl.jeesite.web.enums.TmptCatgEnum;
import com.github.obullxl.lang.web.form.AbstractForm;
import com.github.obullxl.lang.web.form.EnumBaseValidate;

/**
 * 创建模板表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: TmptCreateForm.java, V1.0.1 2014年1月3日 下午2:46:22 $
 */
public class TmptCreateForm extends AbstractForm {
    private static final long serialVersionUID = 5625871136496597425L;

    @NotNull
    @Size(min = 1, max = 1)
    private String            catgFlag;

    @NotNull
    @Size(min = 4, max = 256)
    private String            tmptName;

    /** 
     * @see com.github.obullxl.jeesite.web.form.AbstractForm#enumBases(java.util.List)
     */
    public void enumBases(List<EnumBaseValidate> validates) {
        validates.add(new EnumBaseValidate("catgFlag", this.catgFlag, TmptCatgEnum.values()));
    }

    public String getCatgFlag() {
        return catgFlag;
    }

    public void setCatgFlag(String catgFlag) {
        this.catgFlag = catgFlag;
    }

    public String getTmptName() {
        return tmptName;
    }

    public void setTmptName(String tmptName) {
        this.tmptName = tmptName;
    }

}
