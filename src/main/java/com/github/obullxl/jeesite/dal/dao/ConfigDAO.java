/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import com.github.obullxl.jeesite.dal.dto.ConfigDTO;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_config</tt>.
 */
public interface ConfigDAO extends BaseDAO {
    /** The name of the DAO */
    public static final String NAME = "ConfigDAO";

	/**
	 *  Insert one <tt>ConfigDTO</tt> object to DB table <tt>atom_config</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into atom_config(id,catg,name,state,value,cvalue,gmt_create,gmt_modify) values (?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param config
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long insert(ConfigDTO config) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_config</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_config set state='STATE', value='VALUE', cvalue='CVALUE', gmt_modify='NOW' where ((catg = 'CATG') AND (name = 'NAME'))</tt>
	 *
	 *	@param catg
	 *	@param name
	 *	@param state
	 *	@param value
	 *	@param cvalue
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(String catg, String name, String state, String value, String cvalue) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_config</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_config where ((catg = ?) AND (name = ?))</tt>
	 *
	 *	@param catg
	 *	@param name
	 *	@return ConfigDTO
	 *	@throws DataAccessException
	 */	 
    public ConfigDTO find(String catg, String name) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_config</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_config where (catg = ?)</tt>
	 *
	 *	@param catg
	 *	@return List<ConfigDTO>
	 *	@throws DataAccessException
	 */	 
    public List<ConfigDTO> findCatg(String catg) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_config</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_config</tt>
	 *
	 *	@return List<ConfigDTO>
	 *	@throws DataAccessException
	 */	 
    public List<ConfigDTO> findAll() throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_config</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_config where ((catg = ?) AND (name = ?))</tt>
	 *
	 *	@param catg
	 *	@param name
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(String catg, String name) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_config</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_config where (id >= 0)</tt>
	 *
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteAll() throws DataAccessException;

}
