package com.cyg.dao;

import com.cyg.models.ProjectYS;

public interface ProjectYSDao {
	boolean addProjectYS(ProjectYS projectYS);
	boolean deleteProjectYS(ProjectYS projectYS);
	boolean updateProjectYS(ProjectYS projectYS);
	ProjectYS getByProjectId(Long projectId);

}
