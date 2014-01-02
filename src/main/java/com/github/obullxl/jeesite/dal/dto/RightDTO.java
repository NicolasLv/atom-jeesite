/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;




/**
 * A data object class directly models database table <tt>atom_right</tt>.
 */
public class RightDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;



	/** column:id */
	private long id;

	/** column:code */
	private String code;

	/** column:name */
	private String name;




    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
