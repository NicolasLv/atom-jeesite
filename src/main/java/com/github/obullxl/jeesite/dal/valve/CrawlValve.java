/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.valve;

import com.github.obullxl.jeesite.dal.dto.CrawlDTO;
import com.github.obullxl.lang.FlagValve;

/**
 * Valve for CrawlDTO.
 */
public class CrawlValve extends FlagValve {

    /** The object class. */
	private final CrawlDTO object;
	
	/**
	 * CTOR
	 */
	public CrawlValve(CrawlDTO object) {
	    this.object = object;
	}

	/**
	 * Getter for object.
	 */
    public CrawlDTO getObject() {
        return this.object;
    }
	
	// Custome codes.
	
}
