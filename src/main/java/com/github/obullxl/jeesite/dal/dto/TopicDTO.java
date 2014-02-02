/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;


	import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	/** Valve */
	private TopicValve valve;
	
	/**
     * FetchValve
     */
    public TopicValve findValve() {
        if (this.valve == null) {
            this.valve = new TopicValve(this);
        }

        return this.valve;
    }
    
    /**
     * ResetValve
     */
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

	/** column:type */
	private String type;

	/** column:topic */
	private String topic;

	/** column:post_user_no */
	private String postUserNo;

	/** column:post_nick_name */
	private String postNickName;

	/** column:link_url */
	private String linkUrl;

	/** column:media_url */
	private String mediaUrl;

	/** column:gmt_post */
	private Date gmtPost;

	/** column:visit_count */
	private int visitCount;

	/** column:reply_count */
	private int replyCount;

	/** column:reply_user_no */
	private String replyUserNo;

	/** column:reply_nick_name */
	private String replyNickName;

	/** column:gmt_reply */
	private Date gmtReply;

	/** column:ext_map */
	private String extMap;

	/** column:title_style */
	private String titleStyle;

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
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getPostUserNo() {
		return postUserNo;
	}

	public void setPostUserNo(String postUserNo) {
		this.postUserNo = postUserNo;
	}
	public String getPostNickName() {
		return postNickName;
	}

	public void setPostNickName(String postNickName) {
		this.postNickName = postNickName;
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
	public Date getGmtPost() {
		return gmtPost;
	}

	public void setGmtPost(Date gmtPost) {
		this.gmtPost = gmtPost;
	}
	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public String getReplyUserNo() {
		return replyUserNo;
	}

	public void setReplyUserNo(String replyUserNo) {
		this.replyUserNo = replyUserNo;
	}
	public String getReplyNickName() {
		return replyNickName;
	}

	public void setReplyNickName(String replyNickName) {
		this.replyNickName = replyNickName;
	}
	public Date getGmtReply() {
		return gmtReply;
	}

	public void setGmtReply(Date gmtReply) {
		this.gmtReply = gmtReply;
	}
	public String getExtMap() {
		return extMap;
	}

	public void setExtMap(String extMap) {
		this.extMap = extMap;
	}
	public String getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(String titleStyle) {
		this.titleStyle = titleStyle;
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
