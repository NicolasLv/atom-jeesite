/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;

// auto generated imports
import java.util.Date;

/**
 * A data object class directly models database table <tt>atom_topic</tt>.
 */
public class TopicDO extends BaseDO {
    private static final long serialVersionUID = 741231858441822688L;

	/** column:id */
	private long id;
	/** column:state */
	private String state;
	/** column:catg */
	private String catg;
	/** column:tflag */
	private String tflag;
	/** column:rflag */
	private String rflag;
	/** column:rfrom */
	private String rfrom;
	/** column:mflag */
	private String mflag;
	/** column:mpath */
	private String mpath;
	/** column:mcount */
	private long mcount;
	/** column:treply */
	private String treply;
	/** column:visit */
	private long visit;
	/** column:reply */
	private long reply;
	/** column:title */
	private String title;
	/** column:summary */
	private String summary;
	/** column:content */
	private String content;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCatg() {
		return catg;
	}

	public void setCatg(String catg) {
		this.catg = catg;
	}

	public String getTflag() {
		return tflag;
	}

	public void setTflag(String tflag) {
		this.tflag = tflag;
	}

	public String getRflag() {
		return rflag;
	}

	public void setRflag(String rflag) {
		this.rflag = rflag;
	}

	public String getRfrom() {
		return rfrom;
	}

	public void setRfrom(String rfrom) {
		this.rfrom = rfrom;
	}

	public String getMflag() {
		return mflag;
	}

	public void setMflag(String mflag) {
		this.mflag = mflag;
	}

	public String getMpath() {
		return mpath;
	}

	public void setMpath(String mpath) {
		this.mpath = mpath;
	}

	public long getMcount() {
		return mcount;
	}

	public void setMcount(long mcount) {
		this.mcount = mcount;
	}

	public String getTreply() {
		return treply;
	}

	public void setTreply(String treply) {
		this.treply = treply;
	}

	public long getVisit() {
		return visit;
	}

	public void setVisit(long visit) {
		this.visit = visit;
	}

	public long getReply() {
		return reply;
	}

	public void setReply(long reply) {
		this.reply = reply;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
