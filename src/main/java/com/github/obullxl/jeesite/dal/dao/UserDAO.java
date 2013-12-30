/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import java.util.List;

import com.github.obullxl.jeesite.dal.dto.UserDTO;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_user</tt>.
 */
public interface UserDAO extends BaseDAO {
    /** The name of the DAO */
    public static final String NAME = "UserDAO";

	/**
	 *  Insert one <tt>UserDTO</tt> object to DB table <tt>atom_user</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into atom_user(id,uname,passwd,uemail,uflag,unick,gmt_create,gmt_modify) values (?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param user
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long insert(UserDTO user) throws DataAccessException;

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
	 *	@return UserDTO
	 *	@throws DataAccessException
	 */	 
    public UserDTO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_user</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_user</tt>
	 *
	 *	@return List<UserDTO>
	 *	@throws DataAccessException
	 */	 
    public List<UserDTO> findAll() throws DataAccessException;

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
	 *  Query DB table <tt>atom_user</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_user where (uname = ?)</tt>
	 *
	 *	@param uname
	 *	@return UserDTO
	 *	@throws DataAccessException
	 */	 
    public UserDTO findByName(String uname) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_user</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_user set passwd=?, uemail=?, uflag=?, unick=? where (uname = ?)</tt>
	 *
	 *	@param user
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(UserDTO user) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_user</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_user set passwd=? where (uname = ?)</tt>
	 *
	 *	@param passwd
	 *	@param uname
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updatePasswd(String passwd, String uname) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_user</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_user set uemail=? where (uname = ?)</tt>
	 *
	 *	@param uemail
	 *	@param uname
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateUEmail(String uemail, String uname) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_user</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_user where (uname = ?)</tt>
	 *
	 *	@param uname
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(String uname) throws DataAccessException;

}
