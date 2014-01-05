/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.web.form.AbstractForm;
import com.github.obullxl.lang.web.form.EnumBaseValidate;

/**
 * 模板存储（创建/更新）表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: TmptStoreForm.java, V1.0.1 2014年1月3日 下午12:40:07 $
 */
public class TmptStoreForm extends AbstractForm {
    private static final long serialVersionUID = 9191044088779292045L;

    @NotNull
    @Size(min = 1, max = 1)
    private String            backFlag;

    @NotNull
    @Size(min = 4, max = 256)
    private String            tmptName;

    @NotNull
    @Size(min = 10)
    private String            content;

    /** 
     * @see com.github.obullxl.jeesite.web.form.AbstractForm#enumBases(java.util.List)
     */
    public void enumBases(List<EnumBaseValidate> validates) {
        validates.add(new EnumBaseValidate("backFlag", this.backFlag, ValveBoolEnum.values()));
    }

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }

    public String getTmptName() {
        return tmptName;
    }

    public void setTmptName(String tmptName) {
        this.tmptName = tmptName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
