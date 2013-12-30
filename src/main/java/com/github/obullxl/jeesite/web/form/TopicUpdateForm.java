/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import com.github.obullxl.lang.ToString;

/**
 * 更新/创建主题表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicUpdateForm.java, V1.0.1 2013年12月29日 上午11:07:31 $
 */
public class TopicUpdateForm extends ToString {
    private static final long serialVersionUID = 4246838516504646955L;

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
    
}
