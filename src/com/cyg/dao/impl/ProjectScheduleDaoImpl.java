package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.ProjectScheduleDao;
import com.cyg.models.ProjectSchedule;

@Repository
public class ProjectScheduleDaoImpl extends BaseDao<ProjectSchedule> implements ProjectScheduleDao {

	@Override
	public boolean addProjectSchedule(ProjectSchedule projectSchedule) {
		// TODO Auto-generated method stub
		return save(projectSchedule);
	}

	@Override
	public List<ProjectSchedule> getByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		List<ProjectSchedule> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(ProjectSchedule.class);
		
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
