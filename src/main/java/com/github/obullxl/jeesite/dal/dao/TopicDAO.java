/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.github.obullxl.jeesite.dal.dto.TopicDO;


/**
 * A dao interface provides methods to access database table <tt>atom_topic</tt>.
 */
public interface TopicDAO extends BaseDAO {
	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return TopicDO
	 *	@throws DataAccessException
	 */	 
    public TopicDO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic</tt>
	 *
	 *	@return List<TopicDO>
	 *	@throws DataAccessException
	 */	 
    public List<TopicDO> findAll() throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_topic</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_topic where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(long id) throws DataAccessException;

}
