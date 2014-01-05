/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal;

/**
 * A fields const object database.
 */
public interface DBSize {

	//
	public static interface Catg {
		//
		public static final int CODE_MAX = 20;
		//
		public static final int TOP_MAX = 10;
		//
		public static final int NAME_MAX = 64;
	}
	
	//
	public static interface Config {
		//
		public static final int CATG_MAX = 64;
		//
		public static final int NAME_MAX = 64;
		//
		public static final int STATE_MAX = 20;
		//
		public static final int VALUE_MAX = 255;
		//
		public static final int CVALUE_MAX = 255;
	}
	
	//
	public static interface Crawl {
		//
		public static final int NAME_MAX = 255;
		//
		public static final int CONTENT_MAX = 65535;
	}
	
	//
	public static interface Image {
		//
		public static final int ID_MAX = 32;
		//
		public static final int FLAG_MAX = 20;
		//
		public static final int TOPIC_MAX = 32;
		//
		public static final int TITLE_MAX = 255;
		//
		public static final int URL_MAX = 255;
		//
		public static final int SUMMARY_MAX = 255;
	}
	
	//
	public static interface Reply {
		//
		public static final int STATE_MAX = 20;
		//
		public static final int TOPIC_MAX = 255;
		//
		public static final int TITLE_MAX = 255;
		//
		public static final int UNAME_MAX = 128;
		//
		public static final int UEMAIL_MAX = 255;
		//
		public static final int USITE_MAX = 255;
		//
		public static final int CONTENT_MAX = 65535;
	}
	
	//
	public static interface Right {
		//
		public static final int CODE_MAX = 128;
		//
		public static final int NAME_MAX = 128;
	}
	
	//
	public static interface Topic {
		//
		public static final int ID_MAX = 32;
		//
		public static final int FLAG_MAX = 128;
		//
		public static final int CATG_MAX = 20;
		//
		public static final int LINK_URL_MAX = 255;
		//
		public static final int MEDIA_URL_MAX = 255;
		//
		public static final int TITLE_MAX = 255;
		//
		public static final int SUMMARY_MAX = 255;
		//
		public static final int CONTENT_MAX = 65535;
	}
	
	//
	public static interface User {
		//
		public static final int UNAME_MAX = 128;
		//
		public static final int PASSWD_MAX = 255;
		//
		public static final int UEMAIL_MAX = 255;
		//
		public static final int UFLAG_MAX = 64;
		//
		public static final int UNICK_MAX = 64;
	}
	
	//
	public static interface UserRgt {
		//
		public static final int NAME_MAX = 128;
		//
		public static final int RGT_CODE_MAX = 128;
	}
	
}
