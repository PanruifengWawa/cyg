package com.cyg.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.EventDao;
import com.cyg.models.Event;
import com.cyg.utils.DaoUtils;
import com.cyg.utils.DataWrapper;

@Repository
public class EventDaoImpl extends BaseDao<Event> implements EventDao {
	
	public DataWrapper<List<Event>> getEventList(String useTimeId,String rentalPlace,Date useDate,Integer status,Integer numPerPage,Integer pageNum) {
		// TODO Auto-generated method stub
		DataWrapper<List<Event>> dataWrapper = new DataWrapper<List<Event>>();
		List<Event> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Event.class);
		
		
        if (numPerPage == null) {
			numPerPage = 10;
		}
        if (pageNum == null) {
			pageNum = 1;
		}
        if (useTimeId != null) {
        	criteria.add(Restrictions.like("useTimeId", "%" + useTimeId + "%"));
		}
        
        if (rentalPlace != null) {
        	criteria.add(Restrictions.eq("rentalPlace", rentalPlace));
		}
        if (useDate != null) {
        	criteria.add(Restrictions.eq("useDate", useDate));
		}

        if (status != null) {
        	criteria.add(Restrictions.eq("status", status));
		}
        
        criteria.add(Restrictions.isNotNull("useDate"));
        
        criteria.setProjection(Projections.rowCount());
        int totalltemNum = ((Long) criteria.uniqueResult()).intValue();
        int totalPageNum = DaoUtils.getTotalPageNum(totalltemNum, numPerPage);

        criteria.addOrder(Order.desc("useDate"));
        criteria.addOrder(Order.asc("useTimeId"));

        criteria.setProjection(null);
        if (numPerPage > 0 && pageNum > 0) {
            criteria.setMaxResults(numPerPage);
            criteria.setFirstResult((pageNum - 1) * numPerPage);
        }
        
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		dataWrapper.setCurrentPage(pageNum);
		dataWrapper.setNumberPerPage(numPerPage);
		dataWrapper.setTotalPage(totalPageNum);
		dataWrapper.setTotalNumber(totalltemNum);
        dataWrapper.setData(ret);
        return dataWrapper;
	}

	@Override
	public DataWrapper<List<Event>> getEventList(Date startDate, Date endDate,Integer status,Long userId) {
		// TODO Auto-generated method stub
		DataWrapper<List<Event>> dataWrapper = new DataWrapper<List<Event>>();
		List<Event> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Event.class);
		
		if (startDate == null) {
			startDate = new Date();
		}
        if (endDate == null) {
        	endDate = new Date();
		}
        
        
        criteria.add(Restrictions.between("useDate", startDate, endDate));
        if (status != null) {
        	criteria.add(Restrictions.eq("status", status));
		}
        if (userId != null) {
        	criteria.add(Restrictions.eq("userId", userId));
		}
        
        criteria.addOrder(Order.asc("useDate"));
        criteria.addOrder(Order.asc("useTimeId"));

        
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        dataWrapper.setData(ret);
        return dataWrapper;
	}

	@Override
	public boolean addEvent(Event event) {
		// TODO Auto-generated method stub
		return save(event);
	}

	@Override
	public boolean deleteEvent(Event event) {
		// TODO Auto-generated method stub
		return delete(event);
	}



	@Override
	public Event getById(Long id) {
		// TODO Auto-generated method stub
		return get(id);
	}

	@Override
	public boolean updateEvent(Event event) {
		// TODO Auto-generated method stub
		return update(event);
	}

	@Override
	public DataWrapper<List<Event>> getEventListByUser(Date startDate, Date endDate, Integer status, Long userId,
			Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		DataWrapper<List<Event>> dataWrapper = new DataWrapper<List<Event>>();
		List<Event> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Event.class);
		
		if (numPerPage == null) {
			numPerPage = -1;
		}
        if (pageNum == null) {
			pageNum = -1;
		}
        
        if (startDate != null) {
        	 criteria.add(Restrictions.ge("useDate", startDate));
		}
        
        if (endDate != null) {
        	 criteria.add(Restrictions.le("useDate", endDate));
		}
        if (status != null) {
        	criteria.add(Restrictions.eq("status", status));
		}
        if (userId != null) {
        	criteria.add(Restrictions.eq("userId", userId));
		}
        
        criteria.addOrder(Order.asc("useDate"));
        criteria.addOrder(Order.asc("useTimeId"));

        
        criteria.setProjection(Projections.rowCount());
        int totalltemNum = ((Long) criteria.uniqueResult()).intValue();
        int totalPageNum = DaoUtils.getTotalPageNum(totalltemNum, numPerPage);

        criteria.addOrder(Order.desc("useDate"));
        criteria.addOrder(Order.asc("useTimeId"));

        criteria.setProjection(null);
        if (numPerPage > 0 && pageNum > 0) {
            criteria.setMaxResults(numPerPage);
            criteria.setFirstResult((pageNum - 1) * numPerPage);
        }
        
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		dataWrapper.setCurrentPage(pageNum);
		dataWrapper.setNumberPerPage(numPerPage);
		dataWrapper.setTotalPage(totalPageNum);
		dataWrapper.setTotalNumber(totalltemNum);
        dataWrapper.setData(ret);
        return dataWrapper;
	}

}
