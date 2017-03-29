package com.cyg.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.NewsContentDao;
import com.cyg.models.NewsContent;

@Repository
public class NewsContentDaoImpl extends BaseDao<NewsContent> implements NewsContentDao {

	@Override
	public boolean saveNewsContent(NewsContent newsContent) {
		// TODO Auto-generated method stub
		return save(newsContent);
	}

	@Override
	public boolean updateReadPoint(Long id) {
		// TODO Auto-generated method stub
		Session session = getSession();
        String sql = "update cms_c_news set readPoint = readPoint + 1 where contentid=?";
        try {
        	Query query = session.createSQLQuery(sql);
        	query.setParameter(0, id);
            query.executeUpdate();
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return false;
		}
        
        return true;
	}

	@Override
	public NewsContent getById(Long id) {
		// TODO Auto-generated method stub
		return get(id);
	}

	@Override
	public boolean updateNewsContent(NewsContent newsContent) {
		// TODO Auto-generated method stub
		return update(newsContent);
	}

}
