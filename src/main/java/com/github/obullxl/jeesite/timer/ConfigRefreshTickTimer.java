/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.timer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.github.obullxl.jeesite.cache.ConfigCache;
import com.github.obullxl.jeesite.dal.dao.ConfigDAO;
import com.github.obullxl.jeesite.dal.dto.ConfigDTO;
import com.github.obullxl.lang.timer.AbstractTickTimer;

/**
 * 系统参数刷新定时器
 * 
 * @author obullxl@gmail.com
 * @version $Id: ConfigRefreshTickTimer.java, V1.0.1 2013年12月5日 上午11:48:19 $
 */
public class ConfigRefreshTickTimer extends AbstractTickTimer implements InitializingBean {

    /** 参数配置DAO */
    private ConfigDAO configDAO;

    /** 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        Assert.notNull(this.configDAO, "[缓存刷新定时器]-DAO[ConfigDAO]注入失败!");
    }

    /** 
     * @see com.github.obullxl.lang.timer.AbstractTickTimer#tickInternal(java.util.Date)
     */
    public void tickInternal(Date start) {
        List<ConfigDTO> configs = this.configDAO.findAll();
        if (configs != null) {
            ConfigCache.refresh(configs);
        }
    }

    // ~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~ //

    public void setConfigDAO(ConfigDAO configDAO) {
        this.configDAO = configDAO;
    }

}
