/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import com.github.obullxl.jeesite.dal.dto.ReplyDTO;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_reply</tt>.
 */
public interface ReplyDAO extends BaseDAO {
    /** The name of the DAO */
    public static final String NAME = "ReplyDAO";

	/**
	 *  Insert one <tt>ReplyDTO</tt> object to DB table <tt>atom_reply</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into atom_reply(id,state,topic,title,uname,uemail,usite,content,gmt_create,gmt_modify) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param reply
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long insert(ReplyDTO reply) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_reply</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_reply where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return ReplyDTO
	 *	@throws DataAccessException
	 */	 
    public ReplyDTO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_reply</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_reply</tt>
	 *
	 *	@return List<ReplyDTO>
	 *	@throws DataAccessException
	 */	 
    public List<ReplyDTO> findAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_reply</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_reply where (topic = ?)</tt>
	 *
	 *	@param topic
	 *	@return List<ReplyDTO>
	 *	@throws DataAccessException
	 */	 
    public List<ReplyDTO> findTopic(String topic) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_reply</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_reply where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(long id) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_reply</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_reply where (topic = ?)</tt>
	 *
	 *	@param topic
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteTopic(String topic) throws DataAccessException;

}
