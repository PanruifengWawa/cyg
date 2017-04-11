package com.cyg.dao;

import java.util.List;

import com.cyg.models.ProjectButt;

public interface ProjectButtDao {
	boolean addProjectButt(ProjectButt projectButt);
	List<ProjectButt> getByProjectId(Long projectId);

}
