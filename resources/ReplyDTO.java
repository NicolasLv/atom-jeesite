/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;




/**
 * A data object class directly models database table <tt>atom_reply</tt>.
 */
public class ReplyDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;



	/** column:id */
	private long id;

	/** column:state */
	private String state;

	/** column:topic */
	private String topic;

	/** column:title */
	private String title;

	/** column:uname */
	private String uname;

	/** column:uemail */
	private String uemail;

	/** column:usite */
	private String usite;

	/** column:content */
	private String content;




    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUsite() {
		return usite;
	}

	public void setUsite(String usite) {
		this.usite = usite;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
