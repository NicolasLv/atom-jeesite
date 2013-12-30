/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;



/**
 * A data object class directly models database table <tt>atom_user_rgt</tt>.
 */
public class UserRgtDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;


	/** column:id */
	private long id;

	/** column:name */
	private String name;

	/** column:rgt_code */
	private String rgtCode;




    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getRgtCode() {
		return rgtCode;
	}

	public void setRgtCode(String rgtCode) {
		this.rgtCode = rgtCode;
	}

}
