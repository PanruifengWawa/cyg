package com.cyg.dao;

import java.util.List;

import com.cyg.models.ProjectPeopleDemand;

public interface ProjectPeopleDemandDao {
	boolean addProjectPeopleDemand(ProjectPeopleDemand projectPeopleDemand);
	List<ProjectPeopleDemand> getByProjectId(Long projectId);
}
