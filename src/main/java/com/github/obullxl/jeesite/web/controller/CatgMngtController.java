/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.dal.dto.CatgDTO;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.enums.TrueFalseEnum;
import com.github.obullxl.jeesite.web.xhelper.CatgXHelper;
import com.github.obullxl.lang.biz.BizResponse;

/**
 * 主题分类控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgMngtController.java, V1.0.1 2013年12月13日 下午4:43:16 $
 */
@Controller
@RequestMapping("/admin")
public class CatgMngtController extends AbstractController {

    /** 分类X工具 */
    @Autowired
    private CatgXHelper catgXHelper;

    /**
     * 分类管理
     */
    @RequestMapping(value = "/catg/manage.html", method = RequestMethod.GET)
    public String catgManage() {
        return this.toAdminView(VOPT_CATG_MANAGE, "catg-manage");
    }

    /**
     * 新增分类
     */
    @RequestMapping(value = "/catg/create.html", method = RequestMethod.GET)
    public String catgCreate() {
        return this.toAdminView(VOPT_CATG_CREATE, "catg-create");
    }

    @ResponseBody
    @RequestMapping(value = "/catg/create.html", method = RequestMethod.POST)
    public BizResponse catgCreate(long catgCatg, String catgTop, long catgSort, String catgName) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 新增
            CatgDTO catg = new CatgDTO();
            catg.setCatg(catgCatg);
            catg.setTop(TrueFalseEnum.findDefault(catgTop).code());
            catg.setSort(Math.abs(catgSort));
            catg.setName(catgName);

            long id = this.catgDAO.insert(catg);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, Long.toString(id));

            // 刷新缓存
            this.catgXHelper.refresh();
        } catch (Exception e) {
            logger.error("分类新增异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 更新分类
     */
    @RequestMapping(value = "/catg/update-{id}.html", method = RequestMethod.GET)
    public String catgUpdate(@PathVariable long id) {
        this.setWebData("catgId", id);
        return this.toAdminView(VOPT_CATG_MANAGE, "catg-update");
    }
    
    @ResponseBody
    @RequestMapping(value = "/catg/update-{id}.html", method = RequestMethod.POST)
    public BizResponse catgUpdate(@PathVariable long id, long catgCatg, String catgTop, long catgSort, String catgName) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 查询
            CatgDTO catg = this.catgDAO.find(id);
            if (catg == null) {
                this.buildResponse(response, BizResponseEnum.CATG_NOT_EXIST);
                return response;
            }

            // 更新
            catg.setCatg(catgCatg);
            catg.setTop(TrueFalseEnum.findDefault(catgTop).code());
            catg.setSort(catgSort);
            catg.setName(catgName);

            this.catgDAO.update(catg);

            // 刷新缓存
            this.catgXHelper.refresh();
        } catch (Exception e) {
            logger.error("分类更新异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
