/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.dao;

import com.github.obullxl.jeesite.dal.query.TopicQuery;

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
	 *  <tt>insert into atom_topic(id,flag,catg,link_url,media_url,visit_cnt,reply_cnt,title,summary,content,gmt_create,gmt_modify) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)</tt>
	 *
	 *	@param topic
	 *	@return String
	 *	@throws DataAccessException
	 */	 
    public String insert(TopicDTO topic) throws DataAccessException;

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
    public TopicDTO find(String id) throws DataAccessException;

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
	 *  <tt>select * from atom_topic where (catg = 'catg')</tt>
	 *
	 *	@param catgs
	 *	@return TopicDTO
	 *	@throws DataAccessException
	 */	 
    public TopicDTO findCatgOne(List catgs) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic where ((id = 'ID') AND (catg = 'CATG') AND (title = 'TITLE'))</tt>
	 *
	 *	@param args
	 *	@return List<TopicDTO>
	 *	@throws DataAccessException
	 */	 
    public List<TopicDTO> findFuzzy(TopicQuery args) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select COUNT(*) from atom_topic where ((id = 'ID') AND (catg = 'CATG') AND (title = 'TITLE'))</tt>
	 *
	 *	@param args
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long findFuzzyCount(TopicQuery args) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic where (catg = 'CATG')</tt>
	 *
	 *	@param catgs
	 *	@param size
	 *	@return List<TopicDTO>
	 *	@throws DataAccessException
	 */	 
    public List<TopicDTO> findTopVisit(List catgs, int size) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic where (catg = 'CATG')</tt>
	 *
	 *	@param catgs
	 *	@param size
	 *	@return List<TopicDTO>
	 *	@throws DataAccessException
	 */	 
    public List<TopicDTO> findTopReply(List catgs, int size) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_topic</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_topic set flag=?, catg=?, link_url=?, media_url=?, title=?, summary=?, content=? where (id = ?)</tt>
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
	 *  <tt>update atom_topic set visit_cnt='visit+1', gmt_modify='NOW' where (id = '1')</tt>
	 *
	 *	@param id
	 *	@param count
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateVisit(String id, int count) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_topic</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_topic set reply_cnt='reply+1', gmt_modify='NOW' where (id = '1')</tt>
	 *
	 *	@param id
	 *	@param count
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateReply(String id, int count) throws DataAccessException;

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
    public int delete(String id) throws DataAccessException;

}
