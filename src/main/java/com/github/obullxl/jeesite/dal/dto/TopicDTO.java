/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;


	import java.util.List;
	import java.util.ArrayList;

import com.github.obullxl.jeesite.dal.valve.TopicValve;

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

	/** Âº?Ö≥ÂÄ?*/
	private TopicValve valve;
	
	/**
     * Ëé∑ÂèñÂº?Ö≥ÂÄ?     */
    public TopicValve findValve() {
        if (this.valve == null) {
            this.valve = new TopicValve(this);
        }

        return this.valve;
    }
    
    /**
     * ÈáçÁΩÆÂº?Ö≥ÂÄ?     */
    public TopicDTO resetValve() {
        this.valve = null;
		return this;
    }

	/** column:id */
	private String id;

	/** column:flag */
	private String flag;

	/** column:catg */
	private String catg;

	/** column:link_url */
	private String linkUrl;

	/** column:media_url */
	private String mediaUrl;

	/** column:visit_cnt */
	private long visitCnt;

	/** column:reply_cnt */
	private long replyCnt;

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
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCatg() {
		return catg;
	}

	public void setCatg(String catg) {
		this.catg = catg;
	}
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
	public long getVisitCnt() {
		return visitCnt;
	}

	public void setVisitCnt(long visitCnt) {
		this.visitCnt = visitCnt;
	}
	public long getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(long replyCnt) {
		this.replyCnt = replyCnt;
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
