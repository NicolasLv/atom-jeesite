/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.web.enums.TopicMediaEnum;
import com.github.obullxl.jeesite.web.enums.TopicReplyEnum;
import com.github.obullxl.jeesite.web.enums.TopicStateEnum;
import com.github.obullxl.lang.FlagValve;
import com.github.obullxl.lang.enums.ValveBoolEnum;

/**
 * Valve for TopicDTO.
 */
public class TopicValve extends FlagValve {

    /** The object class. */
    private final TopicDTO object;

    /**
     * CTOR
     */
    public TopicValve(TopicDTO object) {
        super(object.getFlag());
        this.object = object;
    }

    /**
     * Getter for object.
     */
    public TopicDTO getObject() {
        return this.object;
    }

    // Custome codes.

    /**
     * 0-状态
     */
    public TopicStateEnum gotState() {
        return super.gotEnumBase(0, TopicStateEnum.values(), TopicStateEnum.INVALID);
    }

    /**
     * 0-状态
     */
    public TopicValve sotState(TopicStateEnum value) {
        super.sotValue(0, value);
        return this;
    }

    /**
     * 1-置顶
     */
    public ValveBoolEnum gotTop() {
        return ValveBoolEnum.findDefault(super.gotString(1));
    }

    /**
     * 1-置顶
     */
    public TopicValve sotTop(ValveBoolEnum value) {
        super.sotValue(1, value);
        return this;
    }

    /**
     * 2-引用
     */
    public ValveBoolEnum gotLink() {
        return ValveBoolEnum.findDefault(super.gotString(2));
    }

    /**
     * 2-引用
     */
    public TopicValve sotLink(ValveBoolEnum value) {
        super.sotValue(2, value);
        return this;
    }

    /**
     * 3-多媒体
     */
    public TopicMediaEnum gotMedia() {
        return super.gotEnumBase(3, TopicMediaEnum.values(), TopicMediaEnum.findDefault());
    }

    /**
     * 3-多媒体
     */
    public TopicValve sotMedia(TopicMediaEnum value) {
        super.sotValue(3, value);
        return this;
    }

    /**
     * 4-评论
     */
    public TopicReplyEnum gotReply() {
        return super.gotEnumBase(4, TopicReplyEnum.values(), TopicReplyEnum.findDefault());
    }

    /**
     * 4-评论
     */
    public TopicValve sotReply(TopicReplyEnum value) {
        super.sotValue(4, value);
        return this;
    }

}
