package com.github.obullxl.jeesite.dal.dao;

import java.util.List;

import com.github.obullxl.jeesite.dal.dto.CrawlDTO;

import org.springframework.dao.DataAccessException;


/**
 * A dao interface provides methods to access database table <tt>atom_crawl</tt>.
 */
public interface CrawlDAO extends BaseDAO {
    /** The name of the DAO */
    public static final String NAME = "CrawlDAO";

	/**
	 *  Insert one <tt>CrawlDTO</tt> object to DB table <tt>atom_crawl</tt>, return primary key
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into atom_crawl(id,name,content,gmt_create,gmt_modify) values (?, ?, ?, ?, ?)</tt>
	 *
	 *	@param crawl
	 *	@return long
	 *	@throws DataAccessException
	 */	 
    public long insert(CrawlDTO crawl) throws DataAccessException;

	/**
	 *  Update DB table <tt>atom_crawl</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update atom_crawl set name=?, content=?, gmt_modify='NOW' where (id = ?)</tt>
	 *
	 *	@param crawl
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(CrawlDTO crawl) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_crawl</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_crawl where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return CrawlDTO
	 *	@throws DataAccessException
	 */	 
    public CrawlDTO find(long id) throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_crawl</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select id, name, '' content, gmt_create, gmt_modify from atom_crawl</tt>
	 *
	 *	@return List<CrawlDTO>
	 *	@throws DataAccessException
	 */	 
    public List<CrawlDTO> findNames() throws DataAccessException;

	/**
	 *  Query DB table <tt>atom_crawl</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_crawl</tt>
	 *
	 *	@return List<CrawlDTO>
	 *	@throws DataAccessException
	 */	 
    public List<CrawlDTO> findAll() throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_crawl</tt>.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from atom_crawl where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(long id) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>atom_crawl</tt>.
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
