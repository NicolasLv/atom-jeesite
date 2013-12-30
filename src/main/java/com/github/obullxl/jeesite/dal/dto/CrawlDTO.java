/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dto;



/**
 * A data object class directly models database table <tt>atom_crawl</tt>.
 */
public class CrawlDTO extends BaseDTO {
    private static final long serialVersionUID = 741231858441822688L;


	/** column:id */
	private long id;

	/** column:name */
	private String name;

	/** column:content */
	private String content;




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
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
