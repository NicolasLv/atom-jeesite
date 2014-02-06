/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import com.github.obullxl.lang.ToString;
import com.github.obullxl.model.topic.query.TopicQueryForm;

/**
 * 主题查询表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicQueryForm.java, V1.0.1 2013年12月28日 下午12:47:49 $
 */
public class TopicSelectForm extends ToString {
    private static final long  serialVersionUID = 3566482674275741781L;

    public static final String FUZZY            = "fuzzy";
    public static final String SINGLE           = "single";

    private int                page;

    private String             formCatg;

    private String             tpcId;

    private String             tpcState;
    private String             tpcTop;
    private String             tpcLink;
    private String             tpcMedia;
    private String             tpcReply;
    private String             tpcCatg;
    private String             tpcCatgText;
    private String             tpcTitle;
    
    public TopicQueryForm to() {
        TopicQueryForm dst = new TopicQueryForm();
        
        // TODO:
        
        
        return dst;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getFormCatg() {
        return formCatg;
    }

    public void setFormCatg(String formCatg) {
        this.formCatg = formCatg;
    }

    public String getTpcId() {
        return tpcId;
    }

    public void setTpcId(String tpcId) {
        this.tpcId = tpcId;
    }

    public String getTpcState() {
        return tpcState;
    }

    public void setTpcState(String tpcState) {
        this.tpcState = tpcState;
    }

    public String getTpcTop() {
        return tpcTop;
    }

    public void setTpcTop(String tpcTop) {
        this.tpcTop = tpcTop;
    }

    public String getTpcReply() {
        return tpcReply;
    }

    public void setTpcReply(String tpcReply) {
        this.tpcReply = tpcReply;
    }

    public String getTpcLink() {
        return tpcLink;
    }

    public void setTpcLink(String tpcLink) {
        this.tpcLink = tpcLink;
    }

    public String getTpcMedia() {
        return tpcMedia;
    }

    public void setTpcMedia(String tpcMedia) {
        this.tpcMedia = tpcMedia;
    }

    public String getTpcCatg() {
        return tpcCatg;
    }

    public void setTpcCatg(String tpcCatg) {
        this.tpcCatg = tpcCatg;
    }

    public String getTpcCatgText() {
        return tpcCatgText;
    }

    public void setTpcCatgText(String tpcCatgText) {
        this.tpcCatgText = tpcCatgText;
    }

    public String getTpcTitle() {
        return tpcTitle;
    }

    public void setTpcTitle(String tpcTitle) {
        this.tpcTitle = tpcTitle;
    }

}
