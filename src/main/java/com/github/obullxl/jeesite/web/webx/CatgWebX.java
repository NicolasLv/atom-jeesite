/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.webx;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.github.obullxl.lang.catg.CatgDTO;
import com.github.obullxl.lang.catg.CatgUtils;
import com.github.obullxl.lang.webx.WebX;

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
    public static CatgDTO find(String code) {
        return CatgUtils.find(code);
    }

    /**
     * 获取分类名称
     */
    public static String findTitle(String code) {
        CatgDTO catg = find(code);
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
    public static String findTopCode(CatgDTO catg) {
        if (catg == null) {
            return StringUtils.EMPTY;
        }

        CatgDTO parent = catg.getParent();
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
    public static List<CatgDTO> findAll() {
        List<CatgDTO> catgs = CatgUtils.find();
        return new ArrayList<CatgDTO>(catgs);
    }

}
