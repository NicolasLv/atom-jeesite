/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal;

/**
 * A fields const object database.
 */
public interface DBSize {

	//
	public static interface CRAWL {
		//
		public static final String ID = "id";
		//
		public static final String NAME = "name";
		//
		public static final int NAME_MAX = 255;
		//
		public static final String CONTENT = "content";
		//
		public static final int CONTENT_MAX = 65535;
		//
		public static final String GMT_CREATE = "gmt_create";
		//
		public static final String GMT_MODIFY = "gmt_modify";
	}
	
	//
	public static interface IMAGE {
		//
		public static final String ID = "id";
		//
		public static final int ID_MAX = 32;
		//
		public static final String FLAG = "flag";
		//
		public static final int FLAG_MAX = 20;
		//
		public static final String TOPIC = "topic";
		//
		public static final int TOPIC_MAX = 32;
		//
		public static final String TITLE = "title";
		//
		public static final int TITLE_MAX = 255;
		//
		public static final String SIZE = "size";
		//
		public static final String WIDTH = "width";
		//
		public static final String HEIGHT = "height";
		//
		public static final String URL = "url";
		//
		public static final int URL_MAX = 255;
		//
		public static final String SUMMARY = "summary";
		//
		public static final int SUMMARY_MAX = 255;
		//
		public static final String GMT_CREATE = "gmt_create";
		//
		public static final String GMT_MODIFY = "gmt_modify";
	}
	
}
