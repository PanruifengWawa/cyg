package com.cyg.dao;

import java.util.List;

import com.cyg.models.ProjectMember;

public interface ProjectMemberDao {
	boolean addProjectMember(ProjectMember projectMember);
	List<ProjectMember> getByProjectId(Long projectId);

}
