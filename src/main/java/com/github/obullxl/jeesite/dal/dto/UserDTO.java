package com.github.obullxl.jeesite.dal.dto;



/**
 * A data object class directly models database table <tt>atom_user</tt>.
 */
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;



	/** column:uname */
	private String uname;

	/** column:passwd */
	private String passwd;

	/** column:uemail */
	private String uemail;




    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
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

}
