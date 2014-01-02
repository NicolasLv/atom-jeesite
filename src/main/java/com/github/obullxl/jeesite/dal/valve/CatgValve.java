/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.CatgDTO;
import com.github.obullxl.lang.FlagValve;

/**
 * Valve for CatgDTO.
 */
public class CatgValve extends FlagValve {

    /** The object class. */
	private final CatgDTO object;
	
	/**
	 * CTOR
	 */
	public CatgValve(CatgDTO object) {
	    this.object = object;
	}

	/**
	 * Getter for object.
	 */
    public CatgDTO getObject() {
        return this.object;
    }
	
	// Custome codes.
	
}
