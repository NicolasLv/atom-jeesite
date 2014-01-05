/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.ImageDTO;
import com.github.obullxl.lang.FlagValve;
import com.github.obullxl.lang.enums.ImageTypeEnum;
import com.github.obullxl.lang.enums.ValveBoolEnum;

/**
 * Valve for ImageDTO.
 */
public class ImageValve extends FlagValve {

    /** The object class. */
    private final ImageDTO object;

    /**
     * CTOR
     */
    public ImageValve(ImageDTO object) {
        super(object.getFlag());
        this.object = object;
    }

    /**
     * Getter for object.
     */
    public ImageDTO getObject() {
        return this.object;
    }

    // Custome codes.

    /**
     * 0-状态
     */
    public ValveBoolEnum gotState() {
        return super.gotEnumBase(0, ValveBoolEnum.values(), ValveBoolEnum.FALSE);
    }

    /**
     * 0-状态
     */
    public ImageValve sotState(ValveBoolEnum value) {
        super.sotValue(0, value);
        return this;
    }

    /**
     * 1-类型
     */
    public ImageTypeEnum gotImgType() {
        return super.gotEnumBase(1, ImageTypeEnum.values(), ImageTypeEnum.findDefault());
    }

    /**
     * 1-类型
     */
    public ImageValve sotImgType(ImageTypeEnum value) {
        super.sotValue(1, value);
        return this;
    }

}
