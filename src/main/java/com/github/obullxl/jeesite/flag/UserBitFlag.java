/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.flag;

import com.github.obullxl.lang.config.BitFlag;

/**
 * 用户位标志
 * 
 * @author obullxl@gmail.com
 * @version $Id: UserBitFlag.java, V1.0.1 2013年12月15日 下午7:14:40 $
 */
public class UserBitFlag extends BitFlag {

    /**
     * CTOR
     */
    public UserBitFlag(String flag) {
        super(flag);
    }

    /**
     * 0-管理员
     */
    public boolean isAdmin() {
        return this.findBoolean(0);
    }

}
