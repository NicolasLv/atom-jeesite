/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.ConfigDTO;
import com.github.obullxl.lang.FlagValve;

/**
 * Valve for ConfigDTO.
 */
public class ConfigValve extends FlagValve {

    /** The object class. */
	private final ConfigDTO object;
	
	/**
	 * CTOR
	 */
	public ConfigValve(ConfigDTO object) {
	    this.object = object;
	}

	/**
	 * Getter for object.
	 */
    public ConfigDTO getObject() {
        return this.object;
    }
	
	// Custome codes.
	
}
