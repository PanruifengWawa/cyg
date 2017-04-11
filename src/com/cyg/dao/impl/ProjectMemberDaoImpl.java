package com.cyg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cyg.dao.BaseDao;
import com.cyg.dao.ProjectMemberDao;
import com.cyg.models.ProjectMember;

@Repository
public class ProjectMemberDaoImpl extends BaseDao<ProjectMember> implements ProjectMemberDao {

	@Override
	public boolean addProjectMember(ProjectMember projectMember) {
		// TODO Auto-generated method stub
		return save(projectMember);
	}

	@Override
	public List<ProjectMember> getByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		List<ProjectMember> ret = null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(ProjectMember.class);
		
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
