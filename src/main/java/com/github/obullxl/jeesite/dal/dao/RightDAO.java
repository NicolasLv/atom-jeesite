/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import com.github.obullxl.jeesite.dal.dto.RightDTO;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_right</tt>.
 */
public interface RightDAO extends BaseDAO {
    /** The name of the DAO */
    public static final String NAME = "RightDAO";

	/**
	 *  Insert one <tt>RightDTO</tt> object to DB table <tt>atom_right</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into atom_right(id,code,name,gmt_create,gmt_modify) values (?, ?, ?, ?, ?)</tt>
	 *
	 *	@param right
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long insert(RightDTO right) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_right</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_right where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return RightDTO
	 *	@throws DataAccessException
	 */	 
    public RightDTO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_right</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_right where (code = ?)</tt>
	 *
	 *	@param code
	 *	@return RightDTO
	 *	@throws DataAccessException
	 */	 
    public RightDTO findCode(String code) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_right</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_right order by code ASC</tt>
	 *
	 *	@return List<RightDTO>
	 *	@throws DataAccessException
	 */	 
    public List<RightDTO> findAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_right</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_right where (code = 'user-rgt-codes')</tt>
	 *
	 *	@param value
	 *	@return List<RightDTO>
	 *	@throws DataAccessException
	 */	 
    public List<RightDTO> findUserRgts(String value) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_right</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_right set code=?, name=? where (code = ?)</tt>
	 *
	 *	@param right
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(RightDTO right) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_right</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_right where (code = ?)</tt>
	 *
	 *	@param code
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(String code) throws DataAccessException;

}
