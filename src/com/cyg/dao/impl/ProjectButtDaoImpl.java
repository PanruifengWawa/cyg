package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.ProjectButtDao;
import com.cyg.models.ProjectButt;

@Repository
public class ProjectButtDaoImpl extends BaseDao<ProjectButt> implements ProjectButtDao {

	@Override
	public boolean addProjectButt(ProjectButt projectButt) {
		// TODO Auto-generated method stub
		return save(projectButt);
	}

	@Override
	public List<ProjectButt> getByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		List<ProjectButt> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(ProjectButt.class);
		
        criteria.add(Restrictions.eq("projectId", projectId));
        criteria.addOrder(Order.desc("buttTime"));
        
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        return ret;
	}

}
