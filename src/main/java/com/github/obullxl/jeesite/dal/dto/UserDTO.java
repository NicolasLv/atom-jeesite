/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;


	import com.github.obullxl.jeesite.flag.UserBitFlag;

/**
 * A data object class directly models database table <tt>atom_user</tt>.
 */
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;

	/** 用户位标志 */
    private UserBitFlag       bitFlag;

    /**
     * 获取用户位标志
     */
    public UserBitFlag findBitFlag() {
        if (this.bitFlag == null) {
            this.bitFlag = new UserBitFlag(this.uflag);
        }

        return this.bitFlag;
    }
    
    /**
     * 重置用户位标志
     */
    public void resetBitFlag() {
        this.bitFlag = null;
    }

	/** column:id */
	private long id;

	/** column:uname */
	private String uname;

	/** column:passwd */
	private String passwd;

	/** column:uemail */
	private String uemail;

	/** column:uflag */
	private String uflag;

	/** column:unick */
	private String unick;




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
	public String getUflag() {
		return uflag;
	}

	public void setUflag(String uflag) {
		this.uflag = uflag;
	}
	public String getUnick() {
		return unick;
	}

	public void setUnick(String unick) {
		this.unick = unick;
	}

}
