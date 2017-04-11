package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.MoocDao;
import com.cyg.models.Mooc;
import com.cyg.utils.DaoUtils;
import com.cyg.utils.DataWrapper;

@Repository
public class MoocDaoImpl extends BaseDao<Mooc> implements MoocDao {

	@Override
	public DataWrapper<List<Mooc>> getMoocList(Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		DataWrapper<List<Mooc>> dataWrapper = new DataWrapper<List<Mooc>>();
		List<Mooc> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Mooc.class);
		if (numPerPage == null) {
			numPerPage = 10;
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		criteria.setProjection(Projections.rowCount());
		int totalltemNum = ((Long) criteria.uniqueResult()).intValue();
        int totalPageNum = DaoUtils.getTotalPageNum(totalltemNum, numPerPage);
        
        criteria.setProjection(null);
        criteria.addOrder(Order.desc("date"));
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
	public boolean addMooc(Mooc mooc) {
		// TODO Auto-generated method stub
		return save(mooc);
	}

	@Override
	public boolean deleteMooc(Mooc mooc) {
		// TODO Auto-generated method stub
		return delete(mooc);
	}

	@Override
	public Mooc getById(Long id) {
		// TODO Auto-generated method stub
		return get(id);
	}

}
