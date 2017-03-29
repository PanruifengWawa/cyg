package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.NewsDao;
import com.cyg.models.News;
import com.cyg.utils.DaoUtils;
import com.cyg.utils.DataWrapper;

@Repository
public class NewsDaoImpl extends BaseDao<News> implements NewsDao {
	
	@Override
	public int getCount(Integer ifImage) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) from cms_content c,cms_c_news n where c.contentid = n.contentid and c.status =99 and c.catid =6593";
		if(ifImage != null && ifImage == 1) {
			sql += " and pic is not null and pic != ''"; 
		}
		List<java.math.BigInteger> ret = null;
        Session session = getSession();
        
        try {
            Query query = session.createSQLQuery(sql);
            ret = query.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret != null && ret.size() > 0) {
			return ret.get(0).intValue();
		}
		return 0;
	}

	@Override
	public DataWrapper<List<News>> getNewsList(Integer ifImage,Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		int totalltemNum = getCount(ifImage);
		
		String sql = "SELECT c.contentid as id, c.title as title, n.content as content, c.input_time as date,c.pic as pic,c.username as userName,n.readpoint as readPoint,c.update_time as updateDate " +
						"from cms_content c,cms_c_news n where c.contentid = n.contentid and c.status =99 and c.catid =6593";
		if(ifImage != null && ifImage == 1) {
			sql += " and pic is not null and pic != ''"; 
		}
		
		sql += " order by date desc";
		DataWrapper<List<News>> dataWrapper = new DataWrapper<List<News>>();
		List<News> ret = null;
		Session session = getSession();
		Query query = session.createSQLQuery(sql)
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("title",StandardBasicTypes.STRING)
        		.addScalar("content",StandardBasicTypes.STRING)
        		.addScalar("date",StandardBasicTypes.LONG)
        		.addScalar("pic",StandardBasicTypes.STRING)
        		.addScalar("userName",StandardBasicTypes.STRING)
        		.addScalar("readPoint",StandardBasicTypes.LONG)
        		.addScalar("updateDate",StandardBasicTypes.LONG)
        		.setResultTransformer(Transformers.aliasToBean(News.class));
        
        if (numPerPage == null) {
			numPerPage = 10;
		}
        if (pageNum == null) {
			pageNum = 1;
		}
        
        int totalPageNum = DaoUtils.getTotalPageNum(totalltemNum, numPerPage);
       
        if (numPerPage > 0 && pageNum > 0) {
            query.setMaxResults(numPerPage);
            query.setFirstResult((pageNum - 1) * numPerPage);
        }
        dataWrapper.setCurrentPage(pageNum);
        dataWrapper.setNumberPerPage(numPerPage);
        dataWrapper.setTotalPage(totalPageNum);
        dataWrapper.setTotalNumber(totalltemNum);
        
        try {
        	ret = query.list();
        } catch (Exception e) {
			// TODO: handle exception
		}
        
        dataWrapper.setData(ret);
        return dataWrapper;
	}

	

	@Override
	public boolean deleteNews(String table,Long id) {
		// TODO Auto-generated method stub
		Session session = getSession();
        String sql = "delete from " + table + " where contentid=?";
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
	public News getById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT c.contentid as id, c.title as title, n.content as content, c.input_time as date,c.pic as pic,c.username as userName,n.readpoint as readPoint,c.update_time as updateDate " +
				"from cms_content c,cms_c_news n where c.contentid = ? and c.contentid = n.contentid and c.status =99 and c.catid =6593";
		List<News> ret = null;
		Session session = getSession();
		Query query = session.createSQLQuery(sql)
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("title",StandardBasicTypes.STRING)
				.addScalar("content",StandardBasicTypes.STRING)
				.addScalar("date",StandardBasicTypes.LONG)
				.addScalar("pic",StandardBasicTypes.STRING)
				.addScalar("userName",StandardBasicTypes.STRING)
        		.addScalar("readPoint",StandardBasicTypes.LONG)
        		.addScalar("updateDate",StandardBasicTypes.LONG)
				.setResultTransformer(Transformers.aliasToBean(News.class));
		query.setParameter(0, id);
		try {
			ret = query.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (ret != null && ret.size() > 0) {
			return ret.get(0);
		}
		return null;
	}

	@Override
	public DataWrapper<List<News>> getNoticeList(Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		int totalltemNum = getNoticeCount();
		
		String sql = "SELECT c.contentid as id, c.title as title, n.content as content, c.input_time as date,c.pic as pic,c.username as userName,n.readpoint as readPoint,c.update_time as updateDate " +
						"from cms_content c,cms_c_news n where c.contentid = n.contentid and c.status =99 and c.catid =6599";

		
		sql += " order by date desc";
		DataWrapper<List<News>> dataWrapper = new DataWrapper<List<News>>();
		List<News> ret = null;
		Session session = getSession();
		Query query = session.createSQLQuery(sql)
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("title",StandardBasicTypes.STRING)
        		.addScalar("content",StandardBasicTypes.STRING)
        		.addScalar("date",StandardBasicTypes.LONG)
        		.addScalar("pic",StandardBasicTypes.STRING)
        		.addScalar("userName",StandardBasicTypes.STRING)
        		.addScalar("readPoint",StandardBasicTypes.LONG)
        		.addScalar("updateDate",StandardBasicTypes.LONG)
        		.setResultTransformer(Transformers.aliasToBean(News.class));
        
        if (numPerPage == null) {
			numPerPage = 10;
		}
        if (pageNum == null) {
			pageNum = 1;
		}
        
        int totalPageNum = DaoUtils.getTotalPageNum(totalltemNum, numPerPage);
       
        if (numPerPage > 0 && pageNum > 0) {
            query.setMaxResults(numPerPage);
            query.setFirstResult((pageNum - 1) * numPerPage);
        }
        dataWrapper.setCurrentPage(pageNum);
        dataWrapper.setNumberPerPage(numPerPage);
        dataWrapper.setTotalPage(totalPageNum);
        dataWrapper.setTotalNumber(totalltemNum);
        
        try {
        	ret = query.list();
        } catch (Exception e) {
			// TODO: handle exception
		}
        
        dataWrapper.setData(ret);
        return dataWrapper;
	}
	
	public int getNoticeCount() {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) from cms_content c,cms_c_news n where c.contentid = n.contentid and c.status =99 and c.catid =6599";

		List<java.math.BigInteger> ret = null;
        Session session = getSession();
        
        try {
            Query query = session.createSQLQuery(sql);
            ret = query.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret != null && ret.size() > 0) {
			return ret.get(0).intValue();
		}
		return 0;
	}

	@Override
	public News getNoticeById(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT c.contentid as id, c.title as title, n.content as content, c.input_time as date,c.pic as pic,c.username as userName,n.readpoint as readPoint,c.update_time as updateDate " +
				"from cms_content c,cms_c_news n where c.contentid = ? and c.contentid = n.contentid and c.status =99 and c.catid =6599";
		List<News> ret = null;
		Session session = getSession();
		Query query = session.createSQLQuery(sql)
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("title",StandardBasicTypes.STRING)
				.addScalar("content",StandardBasicTypes.STRING)
				.addScalar("date",StandardBasicTypes.LONG)
				.addScalar("pic",StandardBasicTypes.STRING)
				.addScalar("userName",StandardBasicTypes.STRING)
        		.addScalar("readPoint",StandardBasicTypes.LONG)
        		.addScalar("updateDate",StandardBasicTypes.LONG)
				.setResultTransformer(Transformers.aliasToBean(News.class));
		query.setParameter(0, id);
		try {
			ret = query.list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (ret != null && ret.size() > 0) {
			return ret.get(0);
		}
		return null;
	}


}
