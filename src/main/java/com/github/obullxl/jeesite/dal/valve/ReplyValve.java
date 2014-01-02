/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.ReplyDTO;
import com.github.obullxl.lang.FlagValve;

/**
 * Valve for ReplyDTO.
 */
public class ReplyValve extends FlagValve {

    /** The object class. */
	private final ReplyDTO object;
	
	/**
	 * CTOR
	 */
	public ReplyValve(ReplyDTO object) {
	    this.object = object;
	}

	/**
	 * Getter for object.
	 */
    public ReplyDTO getObject() {
        return this.object;
    }
	
	// Custome codes.
	
}
