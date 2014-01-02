/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import com.github.obullxl.jeesite.dal.dto.CatgDTO;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_catg</tt>.
 */
public interface CatgDAO extends BaseDAO {
    /** The name of the DAO */
    public static final String NAME = "CatgDAO";

	/**
	 *  Insert one <tt>CatgDTO</tt> object to DB table <tt>atom_catg</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into atom_catg(id,code,top,catg,sort,name,gmt_create,gmt_modify) values (?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param catg
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long insert(CatgDTO catg) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_catg</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_catg set code=?, top=?, catg=?, sort=?, name=?, gmt_modify=? where (id = ?)</tt>
	 *
	 *	@param catg
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(CatgDTO catg) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_catg</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_catg where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return CatgDTO
	 *	@throws DataAccessException
	 */	 
    public CatgDTO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_catg</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_catg order by sort ASC</tt>
	 *
	 *	@return List<CatgDTO>
	 *	@throws DataAccessException
	 */	 
    public List<CatgDTO> findAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_catg</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_catg where (code = ?)</tt>
	 *
	 *	@param code
	 *	@return CatgDTO
	 *	@throws DataAccessException
	 */	 
    public CatgDTO findCode(String code) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_catg</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_catg where (name = ?)</tt>
	 *
	 *	@param name
	 *	@return CatgDTO
	 *	@throws DataAccessException
	 */	 
    public CatgDTO findName(String name) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_catg</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_catg where (catg = 'catg')</tt>
	 *
	 *	@param value
	 *	@return CatgDTO
	 *	@throws DataAccessException
	 */	 
    public CatgDTO findCatg(long value) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_catg</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_catg where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(long id) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_catg</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_catg where (id >= 0)</tt>
	 *
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteAll() throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_catg</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_catg where ((id = 'id') OR (catg = 'id'))</tt>
	 *
	 *	@param value
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteCatg(long value) throws DataAccessException;

}
