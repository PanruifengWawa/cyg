package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.ProjectPeopleDemandDao;
import com.cyg.models.ProjectPeopleDemand;

@Repository
public class ProjectPeopleDemandDaoImpl extends BaseDao<ProjectPeopleDemand> implements ProjectPeopleDemandDao {

	@Override
	public boolean addProjectPeopleDemand(ProjectPeopleDemand projectPeopleDemand) {
		// TODO Auto-generated method stub
		return save(projectPeopleDemand);
	}

	@Override
	public List<ProjectPeopleDemand> getByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		List<ProjectPeopleDemand> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(ProjectPeopleDemand.class);
		
        criteria.add(Restrictions.eq("projectId", projectId));
        criteria.addOrder(Order.desc("addTime"));
        
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        return ret;
	}

}
