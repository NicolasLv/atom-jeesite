/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.RightDTO;
import com.github.obullxl.lang.FlagValve;

/**
 * Valve for RightDTO.
 */
public class RightValve extends FlagValve {

    /** The object class. */
	private final RightDTO object;
	
	/**
	 * CTOR
	 */
	public RightValve(RightDTO object) {
	    this.object = object;
	}

	/**
	 * Getter for object.
	 */
    public RightDTO getObject() {
        return this.object;
    }
	
	// Custome codes.
	
}
