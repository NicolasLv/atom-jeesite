/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;

// auto generated imports
import java.util.Date;

/**
 * A data object class directly models database table <tt>atom_user</tt>.
 */
public class UserDO extends BaseDO {
    private static final long serialVersionUID = 741231858441822688L;

	/** column:id */
	private long id;
	/** column:uname */
	private String uname;
	/** column:passwd */
	private String passwd;
	/** column:uemail */
	private String uemail;
	/** column:gmt_create */
	private Date gmtCreate;
	/** column:gmt_modify */
	private Date gmtModify;

    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //

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
