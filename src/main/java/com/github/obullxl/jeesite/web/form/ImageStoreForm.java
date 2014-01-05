/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.form;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.obullxl.jeesite.dal.DBSize;
import com.github.obullxl.lang.web.form.AbstractForm;
import com.github.obullxl.lang.web.form.EnumBaseValidate;

/**
 * 相册图片上传表单
 * 
 * @author obullxl@gmail.com
 * @version $Id: ImageStoreForm.java, V1.0.1 2014年1月4日 下午4:21:13 $
 */
public class ImageStoreForm extends AbstractForm {
    private static final long serialVersionUID = 674487632807486228L;

    @NotNull
    @Size(max = DBSize.Image.TOPIC_MAX)
    private String            imgTopic;

    @NotNull
    @Size(max = DBSize.Image.TITLE_MAX)
    private String            imgTitle;

    @Size(max = DBSize.Image.URL_MAX)
    private String            imgNetUrl;

    @NotNull
    @Size(max = DBSize.Image.SUMMARY_MAX)
    private String            imgSummary;

    /** 
     * @see com.github.obullxl.lang.web.form.AbstractForm#enumBases(java.util.List)
     */
    public void enumBases(List<EnumBaseValidate> validates) {
    }

    public String getImgTopic() {
        return imgTopic;
    }

    public void setImgTopic(String imgTopic) {
        this.imgTopic = imgTopic;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImgNetUrl() {
        return imgNetUrl;
    }

    public void setImgNetUrl(String imgNetUrl) {
        this.imgNetUrl = imgNetUrl;
    }

    public String getImgSummary() {
        return imgSummary;
    }

    public void setImgSummary(String imgSummary) {
        this.imgSummary = imgSummary;
    }

}
