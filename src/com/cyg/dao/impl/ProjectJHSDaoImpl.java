package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.ProjectJHSDao;
import com.cyg.models.ProjectJHS;
import com.cyg.models.ProjectYS;

@Repository
public class ProjectJHSDaoImpl extends BaseDao<ProjectJHS> implements ProjectJHSDao {

	@Override
	public boolean add(ProjectJHS projectJHS) {
		// TODO Auto-generated method stub
		return save(projectJHS);
	}

	@Override
	public ProjectJHS getByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		List<ProjectJHS> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(ProjectJHS.class);
		
        criteria.add(Restrictions.eq("projectId", projectId));
        criteria.addOrder(Order.desc("inputTime"));
        
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

	@Override
	public boolean updateJHS(ProjectJHS projectJHS) {
		// TODO Auto-generated method stub
		return update(projectJHS);
	}

}
