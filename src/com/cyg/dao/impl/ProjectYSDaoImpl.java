package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.ProjectYSDao;
import com.cyg.models.Project;
import com.cyg.models.ProjectYS;
import com.cyg.utils.DaoUtils;
import com.cyg.utils.DataWrapper;

@Repository
public class ProjectYSDaoImpl extends BaseDao<ProjectYS> implements ProjectYSDao {

	@Override
	public boolean addProjectYS(ProjectYS projectYS) {
		// TODO Auto-generated method stub
		return save(projectYS);
	}

	@Override
	public boolean deleteProjectYS(ProjectYS projectYS) {
		// TODO Auto-generated method stub
		return delete(projectYS);
	}

	@Override
	public boolean updateProjectYS(ProjectYS projectYS) {
		// TODO Auto-generated method stub
		return update(projectYS);
	}

	@Override
	public ProjectYS getByProjectId(Long projectId) {
		// TODO Auto-generated method stub

		List<ProjectYS> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(ProjectYS.class);
		
        criteria.add(Restrictions.eq("projectId", projectId));
        
        
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
