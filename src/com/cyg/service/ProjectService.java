package com.cyg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cyg.models.Project;
import com.cyg.models.ProjectAll;
import com.cyg.models.ProjectButt;
import com.cyg.models.ProjectJieTi;
import com.cyg.models.ProjectMember;
import com.cyg.models.ProjectPeopleDemand;
import com.cyg.models.ProjectSchedule;
import com.cyg.models.ProjectYS;
import com.cyg.utils.DataWrapper;

public interface ProjectService {
	DataWrapper<Void> addProject( String projectName,String xiaoqu,ProjectYS projectYS,String token,HttpServletRequest request,MultipartFile jhs);
	DataWrapper<Void> deleteProject(Long projectId,String token);
	
	 DataWrapper<List<Project>> getUserProjectList(Integer numPerPage,Integer pageNum,String token);
	 
	 DataWrapper<ProjectAll> getProjectDetails(Long projectId);

	 DataWrapper<Void> updateProject(Long projectIdToUpdate,Project project,String token);

	 DataWrapper<Void> updateYSProject(Long projectIdToUpdate,String projectName,String xiaoqu,ProjectYS projectYS,String token,HttpServletRequest request,MultipartFile jhs);
	 
	 DataWrapper<Void> addProjectMember(ProjectMember projectMember,String token);
	 
	 DataWrapper<Void> addProjectSchedule(ProjectSchedule projectSchedule,String token);
	 
	 DataWrapper<Void> addProjectButt(ProjectButt projectButt,MultipartFile personalWork,String token,HttpServletRequest request);
	 
	 DataWrapper<Void> addProjectPeopleDemand(ProjectPeopleDemand projectPeopleDemand,String token);
	 
	 DataWrapper<Void> jieti(ProjectJieTi projectJieTi,MultipartFile jieti,String token,HttpServletRequest request);
	 
	 DataWrapper<Void> verify(Long projectId,Integer status,String token);
	 
	 DataWrapper<List<Project>> getProjectList(String demand,String type,Integer source,Integer status,Integer numPerPage,Integer pageNum);
}
