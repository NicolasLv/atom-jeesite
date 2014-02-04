/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test.right;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * URI权限测试
 * 
 * @author obullxl@gmail.com
 * @version $Id: RightTestMain.java, V1.0.1 2013年12月15日 上午9:41:24 $
 */
public class RightTestMain {

    /**
     * @param args
     */
    @Test
    public void test() {
        PathMatcher matcher = new AntPathMatcher();
        
        Assert.assertTrue(matcher.match("/admin/topic/create*.htm", "/admin/topic/create.htm"));
        
        Assert.assertTrue(matcher.matchStart("/admin/topic/update*", "/admin/topic/update-102.htm"));
    }

}
