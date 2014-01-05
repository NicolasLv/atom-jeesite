/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.github.obullxl.jeesite.dal.ticket;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.ticket.fmt.TicketEncode;

/**
 * 主题ID转换器
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicTicketEncode.java, V1.0.1 2014年1月4日 下午9:09:13 $
 */
public class TopicTicketEncode implements TicketEncode {

    /** 
     * @see com.github.obullxl.ticket.fmt.TicketEncode#encode(long)
     */
    public String encode(long ticket) {
        String no = StringUtils.leftPad(Long.toString(ticket), 10, "0");
        return "T" + DateUtils.toStringDS(new Date()) + no;
    }

}
