package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.PhotoWallDao;
import com.cyg.models.PhotoWall;
import com.cyg.utils.DaoUtils;
import com.cyg.utils.DataWrapper;

@Repository
public class PhotoWallDaoImpl extends BaseDao<PhotoWall> implements PhotoWallDao {

	@Override
	public DataWrapper<List<PhotoWall>> getPhotoList(Integer year, Integer numPerPage, Integer pageNum) {
		// TODO Auto-generated method stub
		DataWrapper<List<PhotoWall>> dataWrapper = new DataWrapper<List<PhotoWall>>();
		List<PhotoWall> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(PhotoWall.class);
		
		if (numPerPage == null) {
			numPerPage = 10;
		}
        if (pageNum == null) {
			pageNum = 1;
		}
        if (year != null) {
			criteria.add(Restrictions.eq("year", year));
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
	public boolean addPhotoWall(PhotoWall photoWall) {
		// TODO Auto-generated method stub
		return save(photoWall);
	}

	@Override
	public PhotoWall getById(Long id) {
		// TODO Auto-generated method stub
		return get(id);
	}

	@Override
	public boolean deletePhotoWall(PhotoWall photoWall) {
		// TODO Auto-generated method stub
		return delete(photoWall);
	}

	

}
