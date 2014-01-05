/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import com.github.obullxl.jeesite.dal.dto.ImageDTO;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_image</tt>.
 */
public interface ImageDAO extends BaseDAO {
    /** The name of the DAO */
    public static final String NAME = "ImageDAO";

	/**
	 *  Insert one <tt>ImageDTO</tt> object to DB table <tt>atom_image</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into atom_image(id,flag,topic,title,size,width,height,url,summary,gmt_create,gmt_modify) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param image
	 *	@return String
	 *	@throws DataAccessException
	 */	 
    public String insert(ImageDTO image) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_image</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_image set flag=?, topic=?, title=?, size=?, width=?, height=?, url=?, summary=? where (id = ?)</tt>
	 *
	 *	@param image
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(ImageDTO image) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_image</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_image set flag='FLAG', url='URL' where (id = 'ID')</tt>
	 *
	 *	@param image
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateUrl(ImageDTO image) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_image</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_image where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return ImageDTO
	 *	@throws DataAccessException
	 */	 
    public ImageDTO find(String id) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_image</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_image</tt>
	 *
	 *	@return List<ImageDTO>
	 *	@throws DataAccessException
	 */	 
    public List<ImageDTO> findAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_image</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_image where (topic = ?)</tt>
	 *
	 *	@param topic
	 *	@return List<ImageDTO>
	 *	@throws DataAccessException
	 */	 
    public List<ImageDTO> findTopic(String topic) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_image</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_image where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(String id) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_image</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_image where (topic = ?)</tt>
	 *
	 *	@param topic
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteTopic(String topic) throws DataAccessException;

}
