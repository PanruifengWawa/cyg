package com.cyg.dao;

import java.util.List;

import com.cyg.models.ProjectJieTi;

public interface ProjectJieTiDao {
	boolean addProjectJieTi(ProjectJieTi projectJieTi);
	List<ProjectJieTi> getByProjectId(Long projectId);

}
