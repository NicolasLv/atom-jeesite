/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.webx;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.github.obullxl.lang.webx.WebX;
import com.github.obullxl.model.catg.CatgModel;
import com.github.obullxl.model.catg.CatgUtils;

/**
 * 主题分类X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgWebX.java, V1.0.1 2013年12月13日 下午1:09:17 $
 */
@Component("catgWebX")
public class CatgWebX implements WebX {

    /**
     * 获取分类
     */
    public static CatgModel find(String code) {
        return CatgUtils.find(code);
    }

    /**
     * 获取分类名称
     */
    public static String findTitle(String code) {
        CatgModel catg = find(code);
        if (catg != null) {
            return catg.getTitle();
        }

        return StringUtils.EMPTY;
    }

    /**
     * 获取Top分类代码
     */
    public static String findTopCode(String code) {
        return findTopCode(find(code));
    }

    /**
     * 获取Top分类代码
     */
    public static String findTopCode(CatgModel catg) {
        if (catg == null) {
            return StringUtils.EMPTY;
        }

        CatgModel parent = catg.getParent();
        if (parent == null) {
            return catg.getCode();
        }

        return findTopCode(parent);
    }

    /**
     * 是否为相册分类
     */
    public static boolean isAlbumCatg(String catg) {
        return StringUtils.equalsIgnoreCase(catg, CfgWebX.findCatgAlbumCode());
    }

    /**
     * 获取缓存中所有分类信息
     */
    public static List<CatgModel> findAll() {
        List<CatgModel> catgs = CatgUtils.find();
        return new ArrayList<CatgModel>(catgs);
    }

}
