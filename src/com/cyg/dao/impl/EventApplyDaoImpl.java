package com.cyg.dao.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.EventApplyDao;
import com.cyg.models.Event;
import com.cyg.models.EventApply;
import com.cyg.models.News;
import com.cyg.utils.DaoUtils;
import com.cyg.utils.DataWrapper;

@Repository
public class EventApplyDaoImpl extends BaseDao<EventApply> implements EventApplyDao {

	@Override
	public boolean addEventApply(EventApply eventApply) {
		// TODO Auto-generated method stub
		return save(eventApply);
	}

	@Override
	public boolean updateEventApply(Long eventApplyId, Integer state) {
		// TODO Auto-generated method stub
		Session session = getSession();
        String sql = "update m_chuangyegu_signup set state = ? where id = ?";
        try {
        	Query query = session.createSQLQuery(sql);
        	query.setParameter(0, state);
        	query.setParameter(1, eventApplyId);
            query.executeUpdate();
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	return false;
		}
        
        return true;
	}

	@Override
	public DataWrapper<List<EventApply>> getEventApplyList(Long eventId, Integer state, 
			Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		DataWrapper<List<EventApply>> dataWrapper = new DataWrapper<List<EventApply>>();
		List<EventApply> ret = null;
		String sql = "select u.login_name as userName,a.id as id,a.event_id as eventId,a.user_id as userId,a.state as state,a.registration_time as registrationTime from m_chuangyegu_signup a, m_chuangyegu_login u where a.user_id=u.id";
		if (eventId != null) {
			sql += " and a.event_id = " + eventId;
		}
		if (state != null) {
			sql += " and a.state = " + state;
		}
		int totalltemNum = getcount(eventId, state, null);
		Session session = getSession();
		Query query = session.createSQLQuery(sql)
				.addScalar("id",StandardBasicTypes.LONG)
				.addScalar("eventId",StandardBasicTypes.LONG)
        		.addScalar("userId",StandardBasicTypes.LONG)
        		.addScalar("state",StandardBasicTypes.INTEGER)
        		.addScalar("registrationTime",StandardBasicTypes.LONG)
        		.addScalar("userName",StandardBasicTypes.STRING)
        		.setResultTransformer(Transformers.aliasToBean(EventApply.class));
        
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
        	e.printStackTrace();
		}
        
        dataWrapper.setData(ret);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Event>> getAppliedEventList(Long userId, Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		DataWrapper<List<Event>> dataWrapper = new DataWrapper<List<Event>>();
		List<Event> ret = null;
		
		
		String sql = "select e.* from m_chuangyegu_signup a, m_chuangyegu_place e where e.id=a.event_id";
		if (userId != null) {
			sql += " and a.user_id = " + userId;
		}
		
		sql += " order by e.use_date desc";
		Session session = getSession();
		Query query = session.createSQLQuery(sql).addEntity(Event.class);
        
        if (numPerPage == null) {
			numPerPage = 10;
		}
        if (pageNum == null) {
			pageNum = 1;
		}
        int totalltemNum = getcount(userId);
        int totalPageNum = DaoUtils.getTotalPageNum(totalltemNum, numPerPage);
       
        if (numPerPage > 0 && pageNum > 0) {
            query.setMaxResults(numPerPage);
            query.setFirstResult((pageNum - 1) * numPerPage);
        }
       
        
        try {
        	ret = query.list();
        } catch (Exception e) {
			// TODO: handle exception
		}
        
        dataWrapper.setData(ret);
        dataWrapper.setCurrentPage(pageNum);
        dataWrapper.setNumberPerPage(numPerPage);
        dataWrapper.setTotalPage(totalPageNum);
        dataWrapper.setTotalNumber(totalltemNum);
		return dataWrapper;
	}

	@Override
	public BigInteger getCount(Long eventId) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from  m_chuangyegu_signup";
		if (eventId != null) {
			sql += " where event_id=" + eventId;
		}
		List<BigInteger> ret = null;
        Session session = getSession();
        
        try {
            Query query = session.createSQLQuery(sql);
            ret = query.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret != null && ret.size() > 0) {
			return ret.get(0);
		}
		return null;
	}

	public int  getcount(Long userId) {
		String sql = "select count(*) from m_chuangyegu_signup a, m_chuangyegu_place e where e.id=a.event_id";

		if (userId != null) {
			sql += " and a.user_id = " + userId;
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
	public int getcount(Long eventId, Integer state, Long userId) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from m_chuangyegu_signup a, m_chuangyegu_login u where a.user_id=u.id";
		if (eventId != null) {
			sql += " and a.event_id = " + eventId;
		}
		if (state != null) {
			sql += " and a.state = " + state;
		}
		if (userId != null) {
			sql += " and u.id = " + userId;
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
	public EventApply getByUserIdEventId(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		List<EventApply> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(EventApply.class);
		
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("eventId", eventId));


        
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        if (ret != null && ret.size() > 0) {
			return ret.get(0);
		}
        return null;
	}
	

}
