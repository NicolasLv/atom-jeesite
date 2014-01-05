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
 * 相册图片ID转换器
 * 
 * @author obullxl@gmail.com
 * @version $Id: ImageTicketEncode.java, V1.0.1 2014年1月4日 下午9:10:38 $
 */
public class ImageTicketEncode implements TicketEncode {

    /** 
     * @see com.github.obullxl.ticket.fmt.TicketEncode#encode(long)
     */
    public String encode(long ticket) {
        String no = StringUtils.leftPad(Long.toString(ticket), 10, "0");
        return "M" + DateUtils.toStringDS(new Date()) + no;
    }

}
