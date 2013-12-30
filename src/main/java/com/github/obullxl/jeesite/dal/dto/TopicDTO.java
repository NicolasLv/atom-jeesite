/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;


	import java.util.List;
	import java.util.ArrayList;

/**
 * A data object class directly models database table <tt>atom_topic</tt>.
 */
public class TopicDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;

	public static final String REPLYS_KEY = "replys";
	
	public void setReplys(List<ReplyDTO> replys) {
		this.extData.put(REPLYS_KEY, replys);
	}
	
	public List<ReplyDTO> getReplys() {
		List<ReplyDTO> replys = this.findExtData(REPLYS_KEY);
		
		if(replys == null) {
			replys = new ArrayList<ReplyDTO>();
			this.extData.put(REPLYS_KEY, replys);
		}
		
		return replys;
	}

	/** column:id */
	private String id;

	/** column:state */
	private String state;

	/** column:catg */
	private long catg;

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




    // ~~~~~~~~~~~ getters and setters ~~~~~~~~~~~ //
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public long getCatg() {
		return catg;
	}

	public void setCatg(long catg) {
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

}
