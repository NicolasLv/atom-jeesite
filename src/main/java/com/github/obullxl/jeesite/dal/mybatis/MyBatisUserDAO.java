/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.dal.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.InitializingBean;

import com.github.obullxl.jeesite.dal.dao.UserDAO;
import com.github.obullxl.jeesite.dal.dto.UserDO;

/**
 * 
 * @author obullxl@gmail.com
 * @version $Id: MyBatisUserDAO.java, V1.0.1 2013年11月25日 下午1:29:26 $
 */
public class MyBatisUserDAO extends SqlSessionDaoSupport implements InitializingBean, UserDAO {
    
    /** 
     * @see org.springframework.dao.support.DaoSupport#initDao()
     */
    protected void initDao() throws Exception {
        super.initDao();
        
        SqlSession session = this.getSqlSession();
        session.getConfiguration();
    }
    
    /** 
     * @see com.github.obullxl.jeesite.dal.dao.UserDAO#count()
     */
    public int count() {
        return this.getSqlSession().selectOne("ATOM-USER.count");
    }

    /** 
     * @see com.github.obullxl.jeesite.dal.dao.UserDAO#findAll()
     */
    public List<UserDO> findAll() {
        return this.getSqlSession().selectList("ATOM-USER.findAll");
    }

}
