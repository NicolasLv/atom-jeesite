/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;



import com.github.obullxl.jeesite.dal.valve.ImageValve;

/**
 * A data object class directly models database table <tt>atom_image</tt>.
 */
public class ImageDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;


	/** Âº?Ö≥ÂÄ?*/
	private ImageValve valve;
	
	/**
     * Ëé∑ÂèñÂº?Ö≥ÂÄ?     */
    public ImageValve findValve() {
        if (this.valve == null) {
            this.valve = new ImageValve(this);
        }

        return this.valve;
    }
    
    /**
     * ÈáçÁΩÆÂº?Ö≥ÂÄ?     */
    public ImageDTO resetValve() {
        this.valve = null;
		return this;
    }

	/** column:id */
	private String id;

	/** column:flag */
	private String flag;

	/** column:topic */
	private String topic;

	/** column:title */
	private String title;

	/** column:size */
	private long size;

	/** column:width */
	private long width;

	/** column:height */
	private long height;

	/** column:url */
	private String url;

	/** column:summary */
	private String summary;




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
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}
	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
