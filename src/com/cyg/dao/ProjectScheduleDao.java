package com.cyg.dao;

import java.util.List;

import com.cyg.models.ProjectSchedule;

public interface ProjectScheduleDao {
	boolean addProjectSchedule(ProjectSchedule projectSchedule);
	List<ProjectSchedule> getByProjectId(Long projectId);

}
