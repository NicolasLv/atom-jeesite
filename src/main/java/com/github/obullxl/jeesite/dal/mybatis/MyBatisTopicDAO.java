/**
 * obullxl@gmail.com
 */
package com.github.obullxl.jeesite.dal.mybatis;
 * @author obullxl@gmail.com
 */
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return TopicDO
	 *	@throws DataAccessException
	 */	 
    public TopicDO find(long id) throws DataAccessException {
        Long param = new Long(id);

        return this.getSqlSession().selectOne("ATOM-TOPIC.find", param);

    }	
	 *  Query DB table <tt>atom_topic</tt> for records.
	 *
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from atom_topic</tt>
	 *
	 *	@return List<TopicDO>
	 *	@throws DataAccessException
	 */	 
    public List<TopicDO> findAll() throws DataAccessException {

        return this.getSqlSession().selectList("ATOM-TOPIC.findAll", null);

    }	
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
    public int delete(long id) throws DataAccessException {
        Long param = new Long(id);

        return this.getSqlSession().delete("ATOM-TOPIC.delete", param);
    }