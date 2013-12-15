/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.xhelper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.dal.dao.CrawlDAO;
import com.github.obullxl.jeesite.dal.dto.CrawlDTO;
import com.github.obullxl.lang.xhelper.AbstractXHelper;

/**
 * 爬虫X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: CrawlXHelper.java, V1.0.1 2013年12月11日 上午9:24:02 $
 */
@Component("crawlXHelper")
public class CrawlXHelper extends AbstractXHelper {

    /** 爬虫DAO */
    @Autowired
    private CrawlDAO crawlDAO;

    /**
     * 获取爬虫详情
     */
    public CrawlDTO findDetail(long id) {
        return this.crawlDAO.find(id);
    }

    /**
     * 获取爬虫列表
     */
    public List<CrawlDTO> findAll() {
        return this.crawlDAO.findAll();
    }

}
