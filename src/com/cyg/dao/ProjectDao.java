package com.cyg.dao;

import java.util.List;

import com.cyg.models.ButtedProject;
import com.cyg.models.Project;
import com.cyg.utils.DataWrapper;

public interface ProjectDao {
	boolean addProject(Project project);
	boolean deleteProject(Project project);
	boolean updateProject(Project project);
//	DataWrapper<List<Project>> getProjectList(String project_type,Integer type,Integer status);
	Project getById(Long id);
	
	DataWrapper<List<Project>> getUserProjectList(Integer numPerPage,Integer pageNum,Long userId);
	
	DataWrapper<List<Project>> getProjectList(String demand, String type, Integer source, Integer status, Integer numPerPage,
			Integer pageNum);
	
	DataWrapper<List<ButtedProject>> getButtProjectList(Integer numPerPage, Integer pageNum, Long userId);

}
