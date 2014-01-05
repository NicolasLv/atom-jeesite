/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.xhelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.dal.dao.CatgDAO;
import com.github.obullxl.jeesite.dal.dto.CatgDTO;
import com.github.obullxl.jeesite.web.enums.TrueFalseEnum;
import com.github.obullxl.lang.timer.AbstractTickTimer;
import com.github.obullxl.lang.xhelper.XHelper;

/**
 * 主题分类X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: CatgXHelper.java, V1.0.1 2013年12月13日 下午1:09:17 $
 */
@Component("catgXHelper")
public class CatgXHelper extends AbstractTickTimer implements XHelper, InitializingBean {
    /** 缓存对象 */
    private static final List<CatgDTO>      roots = new ArrayList<CatgDTO>();
    private static final Map<Long, CatgDTO> catgs = new ConcurrentHashMap<Long, CatgDTO>();

    /** 分类DAO */
    @Autowired
    private CatgDAO                         catgDAO;

    /**
     * 初始化
     */
    public CatgXHelper() {
        super.setInterval(1 * 60 * 1000);
    }

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        this.refresh();
    }

    /** 
     * @see com.github.obullxl.lang.xhelper.XHelper#findHelperName()
     */
    public String findHelperName() {
        return this.getClass().getSimpleName();
    }

    /** 
     * @see com.github.obullxl.lang.timer.AbstractTickTimer#tickInternal(java.util.Date)
     */
    public void tickInternal(Date start) {
        this.refresh();
    }

    /**
     * 刷新
     */
    public void refresh() {
        long now = System.currentTimeMillis();

        if (logger.isInfoEnabled()) {
            logger.info("[分类刷新]-开始刷新主题分类...");
        }

        // 查询所有
        List<CatgDTO> dtos = this.catgDAO.findAll();

        // 构建Map树
        catgs.clear();

        for (CatgDTO catg : dtos) {
            catgs.put(catg.getId(), catg);
        }

        for (CatgDTO catg : dtos) {
            long pid = catg.getCatg();
            CatgDTO parent = catgs.get(pid);

            if (parent != null) {
                catg.setParent(parent);
                parent.getChildren().add(catg);
            }
        }

        // 刷新缓存
        roots.clear();

        for (CatgDTO catg : dtos) {
            if (catg.getParent() == null) {
                roots.add(catg);
            }
        }

        if (logger.isInfoEnabled()) {
            logger.info("[分类刷新]-分类刷新完成, 耗时[{}]ms.", (System.currentTimeMillis() - now));
        }
    }

    /**
     * 获取分类
     */
    public static CatgDTO find(long id) {
        return catgs.get(id);
    }

    /**
     * 根据代码获取分类
     */
    public static CatgDTO findByCode(String code) {
        for (CatgDTO catg : catgs.values()) {
            if (StringUtils.equals(catg.getCode(), code)) {
                return catg;
            }
        }

        return null;
    }

    /**
     * 根据名称获取分类
     */
    public static CatgDTO findByName(String name) {
        for (CatgDTO catg : catgs.values()) {
            if (StringUtils.equals(catg.getName(), name)) {
                return catg;
            }
        }

        return null;
    }

    /**
     * 根据ID获取分类名称
     */
    public static String findName(long id) {
        CatgDTO catg = catgs.get(id);
        if (catg != null) {
            return catg.getName();
        }

        return StringUtils.EMPTY;
    }

    /**
     * 根据代码获取Top分类代码
     */
    public static String findTopCode(String code) {
        return findTopCode(findByCode(code));
    }

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
        return StringUtils.equalsIgnoreCase(catg, CfgXHelper.findCatgAlbumCode());
    }

    /**
     * 根据代码获取分类名称
     */
    public static String findName(String code) {
        CatgDTO catg = findByCode(code);
        if (catg != null) {
            return catg.getName();
        }

        return StringUtils.EMPTY;
    }

    /**
     * 复制分类基本信息
     */
    public static CatgDTO copy(CatgDTO src) {
        CatgDTO dst = new CatgDTO();

        dst.setId(src.getId());
        dst.setCode(src.getCode());
        dst.setTop(src.getTop());
        dst.setCatg(src.getCatg());
        dst.setSort(src.getSort());
        dst.setName(src.getName());

        return dst;
    }

    /**
     * 获取导航配置
     */
    public static List<CatgDTO> findTopCatgs() {
        List<CatgDTO> tops = new ArrayList<CatgDTO>();

        for (CatgDTO catg : catgs.values()) {
            if (TrueFalseEnum.findByCode(catg.getTop()) == TrueFalseEnum.TRUE) {
                tops.add(copy(catg));
            }
        }

        Map<Long, CatgDTO> tree = new ConcurrentHashMap<Long, CatgDTO>();
        for (CatgDTO top : tops) {
            tree.put(top.getId(), top);
        }

        for (CatgDTO top : tops) {
            long pid = top.getCatg();
            CatgDTO parent = tree.get(pid);

            if (parent != null) {
                top.setParent(parent);
                parent.getChildren().add(top);
            }
        }

        List<CatgDTO> rtns = new ArrayList<CatgDTO>();
        for (CatgDTO top : tops) {
            if (top.getParent() == null) {
                rtns.add(top);
            }
        }

        Collections.sort(rtns, CatgDTO.COMPARATOR);

        return rtns;
    }

    /**
     * 获取所有分类ID
     */
    public static List<Long> findAllCatgID(long id) {
        Set<Long> ids = new HashSet<Long>();
        ids.add(id);

        List<CatgDTO> catgs = findAllChildren(id);
        for (CatgDTO catg : catgs) {
            ids.add(catg.getId());
        }

        return new ArrayList<Long>(ids);
    }

    /**
     * 获取所有分类代码
     */
    public static List<String> findAllCatgCode(long id) {
        Set<String> codes = new HashSet<String>();

        CatgDTO ctg = find(id);
        if (ctg == null) {
            return new ArrayList<String>();
        } else {
            codes.add(ctg.getCode());
        }

        List<CatgDTO> catgs = findAllChildren(id);
        for (CatgDTO catg : catgs) {
            codes.add(catg.getCode());
        }

        return new ArrayList<String>(codes);
    }

    /**
     * 获取所有分类代码
     */
    public static List<String> findAllCatgCode(String code) {
        CatgDTO ctg = findByCode(code);
        if (ctg != null) {
            return findAllCatgCode(ctg.getId());
        }

        return new ArrayList<String>();
    }

    /**
     * 获取所有下属分类
     */
    public static List<CatgDTO> findAllChildren(long id) {
        Set<CatgDTO> set = new HashSet<CatgDTO>();

        CatgDTO catg = catgs.get(id);
        findAllChildren(set, catg);

        List<CatgDTO> rtns = new ArrayList<CatgDTO>(set);
        Collections.sort(rtns, CatgDTO.COMPARATOR);

        return rtns;
    }

    /**
     * 获取所有下属分类
     */
    public static void findAllChildren(Set<CatgDTO> set, CatgDTO catg) {
        for (CatgDTO child : catg.getChildren()) {
            set.add(child);

            findAllChildren(set, child);
        }
    }

    /**
     * 获取缓存中所有分类信息
     */
    public static List<CatgDTO> findAll() {
        return new ArrayList<CatgDTO>(catgs.values());
    }

}
