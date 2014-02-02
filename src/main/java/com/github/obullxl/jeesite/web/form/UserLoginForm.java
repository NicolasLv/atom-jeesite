/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.obullxl.jeesite.dal.DBSize;
import com.github.obullxl.lang.web.form.AbstractForm;
import com.github.obullxl.lang.web.form.EnumBaseValidate;

/**
 * 用户登录表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserLoginForm.java, V1.0.1 2014年1月8日 下午12:01:49 $
 */
public class UserLoginForm extends AbstractForm {
    private static final long serialVersionUID = 4516505919139939596L;

    @NotNull
    @Size(min = 1, max = DBSize.User.NICK_NAME_MAX)
    private String            usrName;

    @NotNull
    @Size(min = 1, max = DBSize.User.PASSWD_MAX)
    private String            usrPasswd;

    /**
     * @see com.github.obullxl.lang.web.form.AbstractForm#enumBases(java.util.List)
     */
    public void enumBases(List<EnumBaseValidate> validates) {
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPasswd() {
        return usrPasswd;
    }

    public void setUsrPasswd(String usrPasswd) {
        this.usrPasswd = usrPasswd;
    }

}
