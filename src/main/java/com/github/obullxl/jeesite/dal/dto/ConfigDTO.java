package com.github.obullxl.jeesite.dal.dto;



/**
 * A data object class directly models database table <tt>atom_config</tt>.
 */
public class ConfigDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;



	/** column:catg */
	private String catg;

	/** column:name */
	private String name;

	/** column:state */
	private String state;

	/** column:value */
	private String value;

	/** column:cvalue */
	private String cvalue;




    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
	public String getCatg() {
		return catg;
	}

	public void setCatg(String catg) {
		this.catg = catg;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getCvalue() {
		return cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

}
