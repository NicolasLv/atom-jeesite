/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.dal.query;

import java.util.List;

import com.github.obullxl.lang.ToString;

/**
 * 主题查询表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicQuery.java, V1.0.1 2013年12月28日 下午12:43:30 $
 */
public class TopicQuery extends ToString {
    private static final long serialVersionUID = 798651143541130345L;

    /** 偏移量 */
    private int               offset;

    /** 分页大小 */
    private int               pageSize;

    private String            id;

    private String            state;
    private String            tflag;
    private String            treply;
    private String            rflag;
    private List<String>      catgs;
    private String            title;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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

    public String getTflag() {
        return tflag;
    }

    public void setTflag(String tflag) {
        this.tflag = tflag;
    }

    public String getTreply() {
        return treply;
    }

    public void setTreply(String treply) {
        this.treply = treply;
    }

    public String getRflag() {
        return rflag;
    }

    public void setRflag(String rflag) {
        this.rflag = rflag;
    }

    public List<String> getCatgs() {
        return catgs;
    }

    public void setCatgs(List<String> catgs) {
        this.catgs = catgs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
