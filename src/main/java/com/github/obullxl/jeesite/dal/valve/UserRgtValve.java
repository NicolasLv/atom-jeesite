/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.UserRgtDTO;
import com.github.obullxl.lang.FlagValve;

/**
 * Valve for UserRgtDTO.
 */
public class UserRgtValve extends FlagValve {

    /** The object class. */
	private final UserRgtDTO object;
	
	/**
	 * CTOR
	 */
	public UserRgtValve(UserRgtDTO object) {
	    this.object = object;
	}

	/**
	 * Getter for object.
	 */
    public UserRgtDTO getObject() {
        return this.object;
    }
	
	// Custome codes.
	
}
