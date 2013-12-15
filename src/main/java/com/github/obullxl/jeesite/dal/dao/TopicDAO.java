package com.github.obullxl.jeesite.dal.dao;

import com.github.obullxl.jeesite.dal.dto.TopicDTO;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_topic</tt>.
 */
public interface TopicDAO extends BaseDAO {
    /** The name of the DAO */
    public static final String NAME = "TopicDAO";

	/**
	 *  Insert one <tt>TopicDTO</tt> object to DB table <tt>atom_topic</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into atom_topic(id,state,catg,tflag,rflag,rfrom,mflag,mpath,mcount,treply,visit,reply,title,summary,content,gmt_create,gmt_modify) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param topic
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long insert(TopicDTO topic) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return TopicDTO
	 *	@throws DataAccessException
	 */	 
    public TopicDTO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic</tt>
	 *
	 *	@return List<TopicDTO>
	 *	@throws DataAccessException
	 */	 
    public List<TopicDTO> findAll() throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic where (catg = 'CATG')</tt>
	 *
	 *	@param catg
	 *	@param offset
	 *	@param limit
	 *	@return List<TopicDTO>
	 *	@throws DataAccessException
	 */	 
    public List<TopicDTO> findCatgPage(String catg, int offset, int limit) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select COUNT(*) from atom_topic where (catg = 'CATG')</tt>
	 *
	 *	@param value
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long findCatgPageCount(String value) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic where (catg = 'CATG')</tt>
	 *
	 *	@param catg
	 *	@param size
	 *	@return List<TopicDTO>
	 *	@throws DataAccessException
	 */	 
    public List<TopicDTO> findTopVisit(String catg, int size) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic where (catg = 'CATG')</tt>
	 *
	 *	@param catg
	 *	@param size
	 *	@return List<TopicDTO>
	 *	@throws DataAccessException
	 */	 
    public List<TopicDTO> findTopReply(String catg, int size) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_topic</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_topic set catg=?, tflag=?, rflag=?, rfrom=?, mflag=?, mpath=?, treply=?, title=?, summary=?, content=? where (id = ?)</tt>
	 *
	 *	@param topic
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(TopicDTO topic) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_topic</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_topic set visit='visit+1', gmt_modify='NOW' where (id = '1')</tt>
	 *
	 *	@param id
	 *	@param count
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateVisit(long id, int count) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_topic</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_topic set reply='reply+1', gmt_modify='NOW' where (id = '1')</tt>
	 *
	 *	@param id
	 *	@param count
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateReply(long id, int count) throws DataAccessException;

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
