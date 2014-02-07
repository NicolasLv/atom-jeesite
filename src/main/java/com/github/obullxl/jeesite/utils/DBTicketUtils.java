/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.utils;

import org.springframework.util.Assert;

import com.github.obullxl.model.TicketUtils;
import com.github.obullxl.ticket.TicketService;

/**
 * 统一ID值工具类
 * 
 * @author obullxl@gmail.com
 * @version $Id: DBTicketUtils.java, V1.0.1 2014年2月7日 下午5:29:48 $
 */
public class DBTicketUtils {
    /** 票据服务 */
    private static TicketService topicTicketService;

    /**
     * 初始化
     */
    public void init() {
        Assert.notNull(topicTicketService, "[票据工具类]-主题票据服务[topicTicketService]注入失败！");
    }

    /**
     * 主题模型ID
     * <br/>
     * yyyyMMdd+00+10位ID，共20位
     */
    public static String newTopicID() {
        try {
            long id = topicTicketService.nextValue();
            return TicketUtils.toTopicID(id);
        } catch (Exception e) {
            throw new RuntimeException("[主题模型]-获取新主题模型ID异常！", e);
        }
    }

    // ~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~ //

    public static void setTopicTicketService(TicketService topicTicketService) {
        DBTicketUtils.topicTicketService = topicTicketService;
    }

}
