/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import com.github.obullxl.jeesite.dal.dto.UserDO;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_user</tt>.
 */
public interface UserDAO extends BaseDAO {
	/**
	 *  Query DB table <tt>atom_user</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select COUNT(*) from atom_user</tt>
	 *
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long count() throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_user</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_user where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return UserDO
	 *	@throws DataAccessException
	 */	 
    public UserDO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_user</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_user</tt>
	 *
	 *	@return List<UserDO>
	 *	@throws DataAccessException
	 */	 
    public List<UserDO> findAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_user</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select uname from atom_user</tt>
	 *
	 *	@return List<String>
	 *	@throws DataAccessException
	 */	 
    public List<String> findName() throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_user</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_user where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(long id) throws DataAccessException;

}
