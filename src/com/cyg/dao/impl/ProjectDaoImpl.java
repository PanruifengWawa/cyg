package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.ProjectDao;
import com.cyg.models.Project;
import com.cyg.utils.DaoUtils;
import com.cyg.utils.DataWrapper;

@Repository
public class ProjectDaoImpl extends BaseDao<Project> implements ProjectDao {

	@Override
	public boolean addProject(Project project) {
		// TODO Auto-generated method stub
		return save(project);
	}

	@Override
	public boolean deleteProject(Project project) {
		// TODO Auto-generated method stub
		return delete(project);
	}

	@Override
	public boolean updateProject(Project project) {
		// TODO Auto-generated method stub
		return update(project);
	}

	@Override
	public Project getById(Long id) {
		// TODO Auto-generated method stub
		return get(id);
	}

	@Override
	public DataWrapper<List<Project>> getUserProjectList(Integer numPerPage, Integer pageNum, Long userId) {
		// TODO Auto-generated method stub
		DataWrapper<List<Project>> dataWrapper = new DataWrapper<List<Project>>();
		List<Project> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Project.class);
		
		if (numPerPage == null) {
			numPerPage = 10;
		}
        if (pageNum == null) {
			pageNum = 1;
		}
        criteria.add(Restrictions.eq("userId", userId));
        
        criteria.setProjection(Projections.rowCount());
        int totalltemNum = ((Long) criteria.uniqueResult()).intValue();
        int totalPageNum = DaoUtils.getTotalPageNum(totalltemNum, numPerPage);
        
       
        
        criteria.setProjection(null);
        criteria.addOrder(Order.desc("applyTime"));
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
	public DataWrapper<List<Project>> getProjectList(String demand, String type, Integer source,Integer status, Integer numPerPage,
			Integer pageNum) {
		// TODO Auto-generated method stub
		DataWrapper<List<Project>> dataWrapper = new DataWrapper<List<Project>>();
		List<Project> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Project.class);
		
		if (numPerPage == null) {
			numPerPage = 10;
		}
        if (pageNum == null) {
			pageNum = 1;
		}
        
        if (demand != null) {
        	if (demand.equals("talent")) {
        		 criteria.add(Restrictions.eq("talent", 1));
			} else if (demand.equals("mentor")) {
				 criteria.add(Restrictions.eq("mentor", 1));
			}else if (demand.equals("money")) {
				 criteria.add(Restrictions.eq("money", 1));
			}
			
		}
        if (type != null) {
        	 criteria.add(Restrictions.like("projectType", type, MatchMode.ANYWHERE));
		}
        if (source != null) {
        	criteria.add(Restrictions.eq("type", source));
		}
        if (status != null) {
        	criteria.add(Restrictions.eq("status", status));
		}
        
        criteria.setProjection(Projections.rowCount());
        int totalltemNum = ((Long) criteria.uniqueResult()).intValue();
        int totalPageNum = DaoUtils.getTotalPageNum(totalltemNum, numPerPage);
        
       
        
        criteria.setProjection(null);
        criteria.addOrder(Order.desc("applyTime"));
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
