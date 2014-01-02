/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.github.obullxl.jeesite.dal.query.TopicQuery;
import com.github.obullxl.jeesite.web.xhelper.CatgXHelper;
import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.utils.QueryLikeUtils;

/**
 * 主题查询表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicQueryForm.java, V1.0.1 2013年12月28日 下午12:47:49 $
 */
public class TopicQueryForm extends ToString {
    private static final long  serialVersionUID = 3566482674275741781L;

    public static final String FUZZY            = "fuzzy";
    public static final String SINGLE           = "single";

    private int                page;

    private String             formCatg;

    private String             tpcId;

    private String             tpcState;
    private String             tpcTop;
    private String             tpcReply;
    private String             tpcFrom;
    private long               tpcCatg;
    private String             tpcCatgText;
    private String             tpcTitle;

    public TopicQuery to() {
        TopicQuery dst = new TopicQuery();

        if (StringUtils.equalsIgnoreCase(this.formCatg, SINGLE)) {
            dst.setId(StringUtils.trimToNull(this.tpcId));
        } else {
            dst.setState(StringUtils.trimToNull(this.tpcState));
            dst.setTflag(StringUtils.trimToNull(this.tpcTop));
            dst.setTreply(StringUtils.trimToNull(this.tpcReply));
            dst.setRflag(StringUtils.trimToNull(this.tpcFrom));

            if (this.tpcCatg > 0L) {
                List<String> codes = CatgXHelper.findAllCatgCode(this.tpcCatg);
                if (CollectionUtils.isNotEmpty(codes)) {
                    dst.setCatgs(codes);
                }
            }

            dst.setTitle(QueryLikeUtils.format(this.tpcTitle));
        }

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

    public String getTpcFrom() {
        return tpcFrom;
    }

    public void setTpcFrom(String tpcFrom) {
        this.tpcFrom = tpcFrom;
    }

    public long getTpcCatg() {
        return tpcCatg;
    }

    public void setTpcCatg(long tpcCatg) {
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
