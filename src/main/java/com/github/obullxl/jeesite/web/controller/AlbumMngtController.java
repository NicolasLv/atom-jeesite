/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.io.File;
import java.net.URL;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.obullxl.jeesite.dal.dto.ImageDTO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.dal.valve.ImageValve;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.form.ImageStoreForm;
import com.github.obullxl.jeesite.web.xhelper.CfgXHelper;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.biz.ImageMeta;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.utils.ImageUtils;

/**
 * 相册主题管理控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: AlbumMngtController.java, V1.0.1 2014年1月4日 下午2:33:12 $
 */
@Controller
@RequestMapping("/admin")
public class AlbumMngtController extends AbstractController {

    /**
     * 相册主题管理
     */
    @RequestMapping("/album/manage-{id}.html")
    public String manage(@PathVariable String id) {
        this.setWebData("topicId", id);
        return this.toAdminView(VOPT_TOPIC_MANAGE, "album-manage");
    }

    /**
     * 新增相册图片
     */
    @RequestMapping(value = "/album/create.html", method = RequestMethod.POST)
    public String create(@Valid ImageStoreForm form, BindingResult errors, @RequestParam MultipartFile imgFile) {
        this.setWebData("topicId", form.getImgTopic());
        try {
            // 校验
            form.validateEnumBase(errors);

            if (errors.hasErrors()) {
                this.setWebData("errorMessage", "表单参数错误！");
                return this.toAdminView(VOPT_TOPIC_MANAGE, "album-manage");
            }

            if ((imgFile == null || imgFile.isEmpty()) && StringUtils.isBlank(form.getImgNetUrl())) {
                this.setWebData("errorMessage", "相册图片参数错误！");
                return this.toAdminView(VOPT_TOPIC_MANAGE, "album-manage");
            }

            // 查询
            TopicDTO topic = this.topicDAO.find(form.getImgTopic());
            if (topic == null) {
                this.setWebData("errorMessage", "原有主题不存在！");
                return this.toAdminView(VOPT_TOPIC_MANAGE, "album-manage");
            }

            // 创建目录
            String path = this.findAlbumPath();
            FileUtils.forceMkdir(new File(path));

            // 图片数据
            ImageMeta meta = null;
            if (imgFile != null && !imgFile.isEmpty()) {
                meta = ImageUtils.findImgMeta(imgFile.getInputStream());
            } else {
                URL url = new URL(form.getImgNetUrl());
                meta = ImageUtils.findImgMeta(url.openStream());
            }

            if (!meta.isImage()) {
                this.setWebData("errorMessage", "请上传图片文件！");
                return this.toAdminView(VOPT_TOPIC_MANAGE, "album-manage");
            }

            // 存储对象
            ImageDTO image = new ImageDTO();

            ImageValve valve = image.findValve();
            valve.sotState(ValveBoolEnum.FALSE);
            valve.sotImgType(meta.getImgTypeEnum());

            image.setFlag(valve.getValve());
            image.setTopic(topic.getId());
            image.setTitle(topic.getTitle());
            image.setUrl(StringUtils.EMPTY);
            image.setSize(meta.getLength());
            image.setWidth(meta.getWidth());
            image.setHeight(meta.getHeight());
            image.setSummary(form.getImgSummary());

            String id = this.imageDAO.insert(image);

            // 处理图片
            String name = id + meta.getImgTypeEnum().findImageExt();
            String imgPath = FilenameUtils.normalize(path + "/" + name);
            if (imgFile != null && !imgFile.isEmpty()) {
                // 本地上传
                FileUtils.copyInputStreamToFile(imgFile.getInputStream(), new File(imgPath));
            } else {
                // 网络下载
                FileUtils.copyURLToFile(new URL(form.getImgNetUrl()), new File(imgPath));
            }

            // 更新
            valve.sotState(ValveBoolEnum.TRUE);
            image.setFlag(valve.getValve());
            image.setUrl(name);

            this.imageDAO.updateUrl(image);

            // 设置封面
            if (StringUtils.isBlank(topic.getMediaUrl())) {
                this.setAlbumCover(topic, image);
            }
        } catch (Exception e) {
            logger.error("新增相册图片异常!", e);
            this.setWebData("errorMessage", "系统异常！");
        }

        // 成功返回
        return this.redirectTo("/admin/album/manage-" + form.getImgTopic() + ".html");
    }

    /**
     * 设置相册封面
     */
    @ResponseBody
    @RequestMapping(value = "/album/cover.html", method = RequestMethod.POST)
    public BizResponse cover(String id) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            ImageDTO image = this.imageDAO.find(id);
            if (image == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            TopicDTO topic = this.topicDAO.find(image.getTopic());
            if (topic == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 设置封面
            this.setAlbumCover(topic, image);
        } catch (Exception e) {
            logger.error("设置相册封面异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 删除相册图片
     */
    @ResponseBody
    @RequestMapping(value = "/album/delete.html", method = RequestMethod.POST)
    public BizResponse delete(String id) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            ImageDTO image = this.imageDAO.find(id);
            if (image == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 源图片
            String srcPath = this.findImagePath(image.getUrl());

            // 删除
            FileUtils.deleteQuietly(new File(srcPath));
            this.imageDAO.delete(id);
        } catch (Exception e) {
            logger.error("删除相册图片异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 设置相册主题封面
     */
    private void setAlbumCover(TopicDTO topic, ImageDTO image) throws Exception {
        // 源图片
        String srcPath = this.findImagePath(image.getUrl());
        File srcFile = new File(srcPath);
        if (!srcFile.exists()) {
            return;
        }

        // 缩略图
        String name = topic.getId() + ".pre." + FilenameUtils.getExtension(srcFile.getName());
        String dstPath = new File(srcFile.getParentFile(), name).getAbsolutePath();

        ImageUtils.preview(srcPath, dstPath, CfgXHelper.findAlbumCoverWidth(), CfgXHelper.findAlbumCoverHeight());

        // 更新
        topic.setMediaUrl(name);
        this.topicDAO.update(topic);
    }

    /**
     * 获取相册路径
     */
    private String findAlbumPath() {
        return this.findServletRealPath("/public/album");
    }

    /**
     * 获取相册图片路径
     */
    private String findImagePath(String url) {
        if (!StringUtils.startsWith(url, "/")) {
            url = "/" + url;
        }

        return this.findAlbumPath() + url;
    }

}
