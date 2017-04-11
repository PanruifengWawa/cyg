package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.ProjectJieTiDao;
import com.cyg.models.ProjectJieTi;

@Repository
public class ProjectJieTiDaoImpl extends BaseDao<ProjectJieTi> implements ProjectJieTiDao {

	@Override
	public boolean addProjectJieTi(ProjectJieTi projectJieTi) {
		// TODO Auto-generated method stub
		return save(projectJieTi);
	}

	@Override
	public List<ProjectJieTi> getByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		List<ProjectJieTi> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(ProjectJieTi.class);
		
        criteria.add(Restrictions.eq("projectId", projectId));
        
		try {
			ret = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

        return ret;
	}

}
