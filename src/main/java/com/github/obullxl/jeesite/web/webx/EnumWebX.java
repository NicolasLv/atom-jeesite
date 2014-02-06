/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.webx;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.obullxl.jeesite.web.enums.TmptCatgEnum;
import com.github.obullxl.jeesite.web.enums.TrueFalseEnum;
import com.github.obullxl.lang.enums.EnumBaseUtils;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.webx.WebX;
import com.github.obullxl.model.topic.enums.TopicMediaEnum;
import com.github.obullxl.model.topic.enums.TopicStateEnum;

/**
 * 枚举X工具
 * 
 * @author obullxl@gmail.com
 * @version $Id: EnumWebX.java, V1.0.1 2013年12月8日 下午7:42:26 $
 */
@Component("enumWebX")
public class EnumWebX implements WebX {

    /**
     * 主题状态枚举
     */
    public static final TopicStateEnum toTopicStateEnum(String state) {
        return TopicStateEnum.findDefault(state);
    }

    /**
     * 主题状态枚举映射
     */
    public static final Map<String, String> toTopicStateMap() {
        return EnumBaseUtils.toMap(TopicStateEnum.values());
    }

    /**
     * 获取状态标志枚举
     */
    public static final TrueFalseEnum toTrueFalseEnum(String code) {
        return TrueFalseEnum.findDefault(code);
    }

    /**
     * 获取状态标志枚举映射
     */
    public static final Map<String, String> toTrueFalseMap() {
        return EnumBaseUtils.toMap(TrueFalseEnum.values());
    }

    /**
     * 获取布尔开关枚举映射
     */
    public static final Map<String, String> toValveBoolMap() {
        return EnumBaseUtils.toMap(ValveBoolEnum.values());
    }

    /**
     * 获取主题多媒体枚举映射
     */
    public static final Map<String, String> toTopicMediaMap() {
        return EnumBaseUtils.toMap(TopicMediaEnum.values());
    }

    /**
     * 获取模板类型枚举映射
     */
    public static final Map<String, String> toTmptCatgMap() {
        return EnumBaseUtils.toMap(TmptCatgEnum.values());
    }

}
