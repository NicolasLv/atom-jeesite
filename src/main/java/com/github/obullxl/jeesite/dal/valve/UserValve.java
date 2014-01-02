/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.UserDTO;
import com.github.obullxl.lang.FlagValve;

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
	    this.object = object;
	}

	/**
	 * Getter for object.
	 */
    public UserDTO getObject() {
        return this.object;
    }
	
	// Custome codes.
	
}
