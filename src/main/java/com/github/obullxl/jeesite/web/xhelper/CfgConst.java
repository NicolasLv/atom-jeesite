/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.xhelper;

/**
 * 系统参数常量
 * 
 * @author obullxl@gmail.com
 * @version $Id: CfgConst.java, V1.0.1 2013年12月22日 下午12:05:57 $
 */
public interface CfgConst {

    /**
     * 系统参数
     */
    public static interface SYSTEM {
        // 
        public static final String CATG               = "system";

        // 是否展示广告
        public static final String SHOW_ADS           = "show_ads";

        // 关于分类代码
        public static final String CATG_ABOUT_CODE    = "about";

        // 相册分类代码
        public static final String CATG_ALBUM_CODE    = "album";

        // 后台分页大小
        public static final String MNGT_PAGE_SIZE     = "mngt_page_size";

        // 前台排行榜大小
        public static final String FRONT_TOP_SIZE     = "front_top_size";

        // 前台分页大小
        public static final String FRONT_PAGE_SIZE    = "front_page_size";

        // 数据同步主机
        public static final String DATA_SYNC_HOST     = "data_sync_host";

        // 模板Context路径
        public static final String TMPT_CONTEXT_PATH  = "tmpt_context_path";

        // 相册封面宽
        public static final String ALBUM_COVER_WIDTH  = "album_cover_width";

        // 相册封面高
        public static final String ALBUM_COVER_HEIGHT = "album_cover_height";

        // 相册图片主机
        public static final String ALBUM_IMAGE_HOST   = "album_image_host";

        // 展示主题浏览次数
        public static final String TOPIC_SHOW_VISIT   = "topic_show_visit";

        // 更新主题浏览次数
        public static final String TOPIC_UPDATE_VISIT = "topic_update_visit";
    }

}
