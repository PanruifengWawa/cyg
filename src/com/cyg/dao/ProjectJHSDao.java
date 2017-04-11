package com.cyg.dao;

import com.cyg.models.ProjectJHS;


public interface ProjectJHSDao {
	boolean add(ProjectJHS projectJHS);
	boolean delete(ProjectJHS projectJHS);
	ProjectJHS getByProjectId(Long projectId);
	boolean updateJHS(ProjectJHS projectJHS);

}
