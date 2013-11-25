/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.dal.dto;

import java.util.Date;

import com.github.obullxl.lang.ToString;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserDO.java, V1.0.1 2013年11月25日 下午2:10:58 $
 */
public class UserDO extends ToString {
    private static final long serialVersionUID = 874172327021963996L;

    private long              id;
    private String            uname;
    private String            passwd;
    private String            uemail;
    private Date              gmtCreate;
    private Date              gmtModify;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

}
