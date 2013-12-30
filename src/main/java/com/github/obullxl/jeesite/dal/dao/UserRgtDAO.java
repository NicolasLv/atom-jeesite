/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import java.util.List;

import com.github.obullxl.jeesite.dal.dto.UserRgtDTO;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_user_rgt</tt>.
 */
public interface UserRgtDAO extends BaseDAO {
    /** The name of the DAO */
    public static final String NAME = "UserRgtDAO";

	/**
	 *  Insert one <tt>UserRgtDTO</tt> object to DB table <tt>atom_user_rgt</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into atom_user_rgt(id,name,rgt_code,gmt_create,gmt_modify) values (?, ?, ?, ?, ?)</tt>
	 *
	 *	@param userRgt
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long insert(UserRgtDTO userRgt) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_user_rgt</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_user_rgt where ((name = ?) AND (rgt_code = ?))</tt>
	 *
	 *	@param name
	 *	@param rgtCode
	 *	@return UserRgtDTO
	 *	@throws DataAccessException
	 */	 
    public UserRgtDTO find(String name, String rgtCode) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_user_rgt</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_user_rgt</tt>
	 *
	 *	@return List<UserRgtDTO>
	 *	@throws DataAccessException
	 */	 
    public List<UserRgtDTO> findAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_user_rgt</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_user_rgt where (name = ?)</tt>
	 *
	 *	@param name
	 *	@return List<UserRgtDTO>
	 *	@throws DataAccessException
	 */	 
    public List<UserRgtDTO> findByUser(String name) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_user_rgt</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_user_rgt where ((name = ?) AND (rgt_code = ?))</tt>
	 *
	 *	@param name
	 *	@param rgtCode
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(String name, String rgtCode) throws DataAccessException;

}
