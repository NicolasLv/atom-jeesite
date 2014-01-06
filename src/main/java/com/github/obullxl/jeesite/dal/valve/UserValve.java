/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.lang.FlagValve;
import com.github.obullxl.lang.enums.ValveBoolEnum;

/**
 * Valve for UserDTO.
 */
public class UserValve extends FlagValve {

    /** The object class. */
    private final UserDTO object;

    /**
     * CTOR
     */
    public UserValve(UserDTO object) {
        super(object.getUflag());
        this.object = object;
    }

    /**
     * Getter for object.
     */
    public UserDTO getObject() {
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
    public UserValve sotState(ValveBoolEnum value) {
        super.sotValue(0, value);
        return this;
    }

    /**
     * 1-管理员
     */
    public ValveBoolEnum gotAdmin() {
        return super.gotEnumBase(1, ValveBoolEnum.values(), ValveBoolEnum.FALSE);
    }

    /**
     * 1-管理员
     */
    public UserValve sotAdmin(ValveBoolEnum value) {
        super.sotValue(1, value);
        return this;
    }

}
