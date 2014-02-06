/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.obullxl.lang.das.DAS;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.web.form.AbstractForm;
import com.github.obullxl.lang.web.form.EnumBaseValidate;
import com.github.obullxl.model.topic.enums.TopicMediaEnum;
import com.github.obullxl.model.topic.enums.TopicStateEnum;

/**
 * 更新/创建主题存储表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicUpdateForm.java, V1.0.1 2013年12月29日 上午11:07:31 $
 */
public class TopicStoreForm extends AbstractForm {
    private static final long serialVersionUID = 4246838516504646955L;

    @Size(max = DAS.TOPIC.ID_MAX)
    private String            tpcId;

    @NotNull
    @Size(min = 1, max = 1)
    private String            tpcStateFlag;

    @NotNull
    @Size(min = 1, max = DAS.TOPIC.CATG_MAX)
    private String            tpcCatg;
    private String            tpcCatgName;

    @NotNull
    @Size(min = 1, max = 1)
    private String            tpcTopFlag;

    @NotNull
    @Size(min = 1, max = 1)
    private String            tpcLinkFlag;

    @Size(max = DAS.TOPIC.LINK_URL_MAX)
    private String            tpcLinkUrl;

    @NotNull
    @Size(min = 1, max = 1)
    private String            tpcMediaFlag;

    @Size(max = DAS.TOPIC.MEDIA_URL_MAX)
    private String            tpcMediaUrl;

    @NotNull
    @Size(min = 1, max = 1)
    private String            tpcReplyFlag;

    @DecimalMin("0")
    private long              tpcVisitCnt      = 0L;

    @DecimalMin("0")
    private long              tpcReplyCnt      = 0L;

    @NotNull
    @Size(min = 1, max = DAS.TOPIC.TITLE_MAX)
    private String            tpcTitle;

    @Size(max = DAS.TOPIC.SUMMARY_MAX)
    private String            tpcSummary;

    @NotNull
    @Size(min = 1, max = DAS.TOPIC.CONTENT_MAX)
    private String            tpcContent;

    /** 
     * @see com.github.obullxl.jeesite.web.form.AbstractForm#enumBases(java.util.List)
     */
    public void enumBases(List<EnumBaseValidate> validates) {
        validates.add(new EnumBaseValidate("tpcStateFlag", this.tpcStateFlag, TopicStateEnum.values()));
        validates.add(new EnumBaseValidate("tpcTopFlag", this.tpcTopFlag, ValveBoolEnum.values()));
        validates.add(new EnumBaseValidate("tpcLinkFlag", this.tpcLinkFlag, ValveBoolEnum.values()));
        validates.add(new EnumBaseValidate("tpcMediaFlag", this.tpcMediaFlag, TopicMediaEnum.values()));
        validates.add(new EnumBaseValidate("tpcReplyFlag", this.tpcReplyFlag, ValveBoolEnum.values()));
    }

    public String getTpcId() {
        return tpcId;
    }

    public void setTpcId(String tpcId) {
        this.tpcId = tpcId;
    }

    public String getTpcStateFlag() {
        return tpcStateFlag;
    }

    public void setTpcStateFlag(String tpcStateFlag) {
        this.tpcStateFlag = tpcStateFlag;
    }

    public String getTpcCatg() {
        return tpcCatg;
    }

    public void setTpcCatg(String tpcCatg) {
        this.tpcCatg = tpcCatg;
    }

    public String getTpcCatgName() {
        return tpcCatgName;
    }

    public void setTpcCatgName(String tpcCatgName) {
        this.tpcCatgName = tpcCatgName;
    }

    public String getTpcTopFlag() {
        return tpcTopFlag;
    }

    public void setTpcTopFlag(String tpcTopFlag) {
        this.tpcTopFlag = tpcTopFlag;
    }

    public String getTpcLinkFlag() {
        return tpcLinkFlag;
    }

    public void setTpcLinkFlag(String tpcLinkFlag) {
        this.tpcLinkFlag = tpcLinkFlag;
    }

    public String getTpcLinkUrl() {
        return tpcLinkUrl;
    }

    public void setTpcLinkUrl(String tpcLinkUrl) {
        this.tpcLinkUrl = tpcLinkUrl;
    }

    public String getTpcMediaFlag() {
        return tpcMediaFlag;
    }

    public void setTpcMediaFlag(String tpcMediaFlag) {
        this.tpcMediaFlag = tpcMediaFlag;
    }

    public String getTpcMediaUrl() {
        return tpcMediaUrl;
    }

    public void setTpcMediaUrl(String tpcMediaUrl) {
        this.tpcMediaUrl = tpcMediaUrl;
    }

    public String getTpcReplyFlag() {
        return tpcReplyFlag;
    }

    public void setTpcReplyFlag(String tpcReplyFlag) {
        this.tpcReplyFlag = tpcReplyFlag;
    }

    public long getTpcVisitCnt() {
        return tpcVisitCnt;
    }

    public void setTpcVisitCnt(long tpcVisitCnt) {
        this.tpcVisitCnt = tpcVisitCnt;
    }

    public long getTpcReplyCnt() {
        return tpcReplyCnt;
    }

    public void setTpcReplyCnt(long tpcReplyCnt) {
        this.tpcReplyCnt = tpcReplyCnt;
    }

    public String getTpcTitle() {
        return tpcTitle;
    }

    public void setTpcTitle(String tpcTitle) {
        this.tpcTitle = tpcTitle;
    }

    public String getTpcSummary() {
        return tpcSummary;
    }

    public void setTpcSummary(String tpcSummary) {
        this.tpcSummary = tpcSummary;
    }

    public String getTpcContent() {
        return tpcContent;
    }

    public void setTpcContent(String tpcContent) {
        this.tpcContent = tpcContent;
    }

}
