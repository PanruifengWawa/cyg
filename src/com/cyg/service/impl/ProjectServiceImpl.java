package com.cyg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cyg.dao.ProjectButtDao;
import com.cyg.dao.ProjectDao;
import com.cyg.dao.ProjectJHSDao;
import com.cyg.dao.ProjectJieTiDao;
import com.cyg.dao.ProjectMemberDao;
import com.cyg.dao.ProjectPeopleDemandDao;
import com.cyg.dao.ProjectScheduleDao;
import com.cyg.dao.ProjectYSDao;
import com.cyg.enums.ErrorCodeEnum;
import com.cyg.models.ButtedProject;
import com.cyg.models.Project;
import com.cyg.models.ProjectAll;
import com.cyg.models.ProjectButt;
import com.cyg.models.ProjectJHS;
import com.cyg.models.ProjectJieTi;
import com.cyg.models.ProjectMember;
import com.cyg.models.ProjectPeopleDemand;
import com.cyg.models.ProjectSchedule;
import com.cyg.models.ProjectYS;
import com.cyg.models.User;
import com.cyg.service.ProjectService;
import com.cyg.utils.DataWrapper;
import com.cyg.utils.FileUtils;
import com.cyg.utils.SessionManager;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	ProjectYSDao projectYSDao;
	
	@Autowired
	ProjectJHSDao projectJHSDao;
	
	@Autowired
	ProjectMemberDao projectMemberDao;
	
	@Autowired
	ProjectScheduleDao projectScheduleDao;
	
	@Autowired
	ProjectButtDao projectButtDao;
	
	@Autowired
	ProjectPeopleDemandDao projectPeopleDemandDao;
	
	@Autowired
	ProjectJieTiDao projectJieTiDao;

	@Override
	public DataWrapper<Void> deleteProject(Long projectId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null) {
			Project project = projectDao.getById(projectId);
			ProjectYS projectYS = projectYSDao.getByProjectId(projectId);
			if (project != null && (project.getUserId().equals(user.getId()) ||  user.getId() < 0)) {
				projectDao.deleteProject(project);
			}
			if (projectYS != null && ( projectYS.getUserId().equals(user.getId()) || user.getId() < 0 )  ) {
				projectYSDao.deleteProjectYS(projectYS);
			}


		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> addProject(String projectName, String xiaoqu, ProjectYS projectYS, String token,HttpServletRequest request,MultipartFile jhs) {
		// TODO Auto-generated method stub
		
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null && user.getId() > 0 && jhs != null)  {
			
			String filePath = FileUtils.saveFile(jhs, "jhs", request);
			if (filePath != null) {
				Project project = new Project();
				project.setId(null);
				project.setUserId(user.getId());
				project.setProjectName(projectName);
				project.setType(user.getIdentity());
				project.setStatus(11);
				project.setApplyTime(System.currentTimeMillis()/1000);
				project.setXiaoqu(xiaoqu);
				project.setTdkzpzj("");
				project.setTalent(0);
				project.setMentor(0);
				project.setMoney(0);
				projectDao.addProject(project);
				if (project.getId() != null) {
					projectYS.setId(null);
					projectYS.setProjectId(project.getId());
					projectYS.setUserId(user.getId());
					projectYS.setType(user.getIdentity());
					projectYS.setInputTime(System.currentTimeMillis()/1000);
					
					ProjectJHS projectJHS = new ProjectJHS();
					projectJHS.setId(null);
					projectJHS.setInputTime(System.currentTimeMillis()/1000);
					projectJHS.setName(jhs.getOriginalFilename());
					projectJHS.setProjectId(project.getId());
					projectJHS.setUrl(filePath);
					if (!projectYSDao.addProjectYS(projectYS) || !projectJHSDao.add(projectJHS)) {
						dataWrapper.setErrorCode(ErrorCodeEnum.Error);
					}
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
				
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}

			
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Project>> getUserProjectList(Integer numPerPage, Integer pageNum, String token) {
		// TODO Auto-generated method stub
		DataWrapper<List<Project>> dataWrapper = new DataWrapper<List<Project>>();
		User user = SessionManager.getSession(token);
		if (user != null) {
			dataWrapper = projectDao.getUserProjectList(numPerPage, pageNum, user.getId());
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<ProjectAll> getProjectDetails(Long projectId) {
		// TODO Auto-generated method stub
		DataWrapper<ProjectAll> dataWrapper = new DataWrapper<ProjectAll>();
		ProjectAll projectAll = new ProjectAll();
		Project project = projectDao.getById(projectId);
		List<ProjectButt> projectButts = projectButtDao.getByProjectId(projectId);
		List<ProjectMember> projectMembers = projectMemberDao.getByProjectId(projectId);
		List<ProjectPeopleDemand> projectPeopleDemands = projectPeopleDemandDao.getByProjectId(projectId);
		List<ProjectSchedule> projectSchedules = projectScheduleDao.getByProjectId(projectId);
		ProjectYS projectYS = projectYSDao.getByProjectId(projectId);
		ProjectJHS projectJHS = projectJHSDao.getByProjectId(projectId);
		List<ProjectJieTi> projectJieTis = projectJieTiDao.getByProjectId(projectId);
		
		List<ProjectMember> projectMembersNew = new ArrayList<ProjectMember>();
		for(ProjectMember temp: projectMembers) {
			if (temp.getMemberName() != null && !temp.getMemberName().equals("")) {
				projectMembersNew.add(temp);
			}
		}
		
		projectAll.setProjectJieTis(projectJieTis);
		projectAll.setProjectJHS(projectJHS);
		projectAll.setProjectYS(projectYS);
		projectAll.setProjectSchedules(projectSchedules);
		projectAll.setProjectPeopleDemands(projectPeopleDemands);
		projectAll.setProjectMembers(projectMembersNew);
		projectAll.setProject(project);
		projectAll.setProjectButts(projectButts);;
		dataWrapper.setData(projectAll);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateYSProject(Long projectIdToUpdate, String projectName, String xiaoqu,
			ProjectYS projectYS, String token, HttpServletRequest request, MultipartFile jhs) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null && user.getId() > 0)  {
			Project project = projectDao.getById(projectIdToUpdate);
			if (project != null) {
				project.setProjectName(projectName);
				project.setXiaoqu(xiaoqu);
				
				projectDao.updateProject(project);
				
				ProjectYS projectYSINDB = projectYSDao.getByProjectId(projectIdToUpdate);
				if (projectYSINDB != null) {
					projectYSINDB.setName(projectYS.getName());
					projectYSINDB.setPhone(projectYS.getPhone());
					projectYSINDB.setEmail(projectYS.getEmail());
					projectYSINDB.setWeixin(projectYS.getWeixin());
					projectYSINDB.setGrade(projectYS.getGrade());
					projectYSINDB.setZczj(projectYS.getZczj());
					projectYSINDB.setTdcy(projectYS.getTdcy());
					projectYSINDB.setProjectType(projectYS.getProjectType());
					projectYSINDB.setProjectStatus(projectYS.getProjectStatus());
					projectYSINDB.setContent(projectYS.getContent());
					projectYSDao.updateProjectYS(projectYSINDB);
				}
				
				if (jhs != null) {
					String filePath = FileUtils.saveFile(jhs, "jhs", request);
					ProjectJHS projectJHS = projectJHSDao.getByProjectId(projectIdToUpdate);
					projectJHS.setName(jhs.getOriginalFilename());
					projectJHS.setUrl(filePath);
					projectJHSDao.updateJHS(projectJHS);
				}

			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}

			
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		
		return dataWrapper;
	}

	@Override
	public DataWrapper<Project> updateProject(Long projectIdToUpdate, Project project, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Project> dataWrapper = new  DataWrapper<Project>();
		User user = SessionManager.getSession(token);
		if (user != null) {
			Project projectINDB = projectDao.getById(projectIdToUpdate);
			if (projectINDB != null && projectINDB.getUserId().equals(user.getId())) {
				
				if (project.getProjectName() != null && !project.getProjectName().equals("")) {
					projectINDB.setProjectName(project.getProjectName());
				}
				if (project.getProjectField() != null && !project.getProjectField().equals("")) {
					projectINDB.setProjectField(project.getProjectField());
				}
				if (project.getApplyName() != null && !project.getApplyName().equals("")) {
					projectINDB.setApplyName(project.getApplyName());
				}
				if (project.getApplyCollege() != null && !project.getApplyCollege().equals("")) {
					projectINDB.setApplyCollege(project.getApplyCollege());
				}
				
				if (project.getApplyMajor() != null && !project.getApplyMajor().equals("")) {
					projectINDB.setApplyMajor(project.getApplyMajor());
				}
				if (project.getStudentId() != null && !project.getStudentId().equals("")) {
					projectINDB.setStudentId(project.getStudentId());
				}
				if (project.getApplyTeacherId() != null && !project.getApplyTeacherId().equals("")) {
					project.setApplyTeacherId(project.getApplyTeacherId());
				}
				if (project.getApplyPhone() != null && !project.getApplyPhone().equals("")) {
					projectINDB.setApplyPhone(project.getApplyPhone());
				}
				if (project.getEmail() != null && !project.getEmail().equals("")) {
					projectINDB.setEmail(project.getEmail());
				}
				if (project.getTeacherName() != null && !project.getTeacherName().equals("")) {
					projectINDB.setTeacherName(project.getTeacherName());
				}
				if (project.getTeacherId() != null && !project.getTeacherId().equals("")) {
					projectINDB.setTeacherId(project.getTeacherId() );
				}
				if (project.getFaculty() != null && !project.getFaculty().equals("")) {
					projectINDB.setFaculty(project.getFaculty());
				}
				if (project.getTeacherMajor() != null && !project.getTeacherMajor().equals("")) {
					projectINDB.setTeacherMajor(project.getTeacherMajor());
				}
				if (project.getTitle() != null && !project.getTitle().equals("")) {
					projectINDB.setTitle(project.getTitle());
				}
				if (project.getTeacherPhone() != null && !project.getTeacherPhone().equals("")) {
					projectINDB.setTeacherPhone(project.getTeacherPhone());
				}
				if (project.getProjectType() != null && !project.getProjectType().equals("")) {
					projectINDB.setProjectType(project.getProjectType());
				}
				if (project.getProjectProperty() != null && !project.getProjectProperty().equals("")) {
					projectINDB.setProjectProperty(project.getProjectProperty());
				}
				if (project.getIsCompany() != null && !project.getIsCompany().equals("")) {
					projectINDB.setIsCompany(project.getIsCompany());
				}
				if (project.getCompanyMoney() != null && !project.getCompanyMoney().equals("")) {
					projectINDB.setCompanyMoney(project.getCompanyMoney());
				}
				if (project.getProjectIntro() != null && !project.getProjectIntro().equals("")) {
					projectINDB.setProjectIntro(project.getProjectIntro());
				}
				if (project.getProjectBackGround() != null && !project.getProjectBackGround().equals("")) {
					projectINDB.setProjectBackGround(project.getProjectBackGround());
				}
				if (project.getProjectBrief() != null && !project.getProjectBrief().equals("")) {
					projectINDB.setProjectBrief(project.getProjectBrief());
				}
				if (project.getSlogan() != null && !project.getSlogan().equals("")) {
					projectINDB.setSlogan(project.getSlogan());
				}
				if (project.getProjectTotalTime() != null && !project.getProjectTotalTime().equals("")) {
					projectINDB.setProjectTotalTime(project.getProjectTotalTime());
				}
				if (project.getTeacherEmail() != null && !project.getTeacherEmail().equals("")) {
					projectINDB.setTeacherEmail(project.getTeacherEmail());
				}
				if (project.getTeacherField() != null && !project.getTeacherField().equals("")) {
					projectINDB.setTeacherField(project.getTeacherField());
				}
				if (project.getProjectMarket() != null && !project.getProjectMarket().equals("")) {
					projectINDB.setProjectMarket(project.getProjectMarket());
				}
				if (project.getProjectBenefits() != null && !project.getProjectBenefits().equals("")) {
					projectINDB.setProjectBenefits(project.getProjectBenefits());
				}
				if (project.getProjectTeacher() != null && !project.getProjectTeacher().equals("")) {
					projectINDB.setProjectTeacher(project.getProjectTeacher());
				}
				if (project.getProjectStaff() != null && !project.getProjectStaff().equals("")) {
					projectINDB.setProjectStaff(project.getProjectStaff());
				}
				if (project.getProjectReport() != null && !project.getProjectReport().equals("")) {
					projectINDB.setProjectReport(project.getProjectReport());
				}
				if (project.getProjectFunding() != null && !project.getProjectFunding().equals("")) {
					projectINDB.setProjectFunding(project.getProjectFunding());
				}
				if (project.getProjectOther() != null && !project.getProjectOther().equals("")) {
					projectINDB.setProjectOther(project.getProjectOther());
				}
				if (project.getApplyTeacherTitle() != null && !project.getApplyTeacherTitle().equals("")) {
					projectINDB.setApplyTeacherTitle(project.getApplyTeacherTitle());
				}
				if (project.getApplyNumber() != null && !project.getApplyNumber().equals("")) {
					projectINDB.setApplyNumber(project.getApplyNumber());
				}
				if (project.getApplyOfficeSector() != null && !project.getApplyOfficeSector().equals("")) {
					projectINDB.setApplyOfficeSector(project.getApplyOfficeSector());
				}
				if (project.getApplyWorkUnit() != null && !project.getApplyWorkUnit().equals("")) {
					projectINDB.setApplyWorkUnit(project.getApplyWorkUnit());
				}
				if (project.getCompanyName() != null && !project.getCompanyName().equals("")) {
					projectINDB.setCompanyName(project.getCompanyName());
				}
				if (project.getCompanyType() != null && !project.getCompanyType().equals("")) {
					projectINDB.setCompanyType(project.getCompanyType());
				}
				if (project.getCompanyContact() != null && !project.getCompanyContact().equals("")) {
					projectINDB.setCompanyContact(project.getCompanyContact());
				}
				if (project.getOfficeSector() != null && !project.getOfficeSector().equals("")) {
					projectINDB.setOfficeSector(project.getOfficeSector());
				}
				if (project.getXqOtherRemark() != null && !project.getXqOtherRemark().equals("")) {
					projectINDB.setXqOtherRemark(project.getXqOtherRemark());
				}
				if (project.getXiaoqu() != null && !project.getXiaoqu().equals("")) {
					projectINDB.setXiaoqu(project.getXiaoqu());
				}
				if (project.getTalent() != null) {
					projectINDB.setTalent(project.getTalent());
				}
				if (project.getTalentNum() != null && !project.getTalentNum().equals("")) {
					projectINDB.setTalentNum(project.getTalentNum());
				}
				if (project.getTalentRemark() != null && !project.getTalentRemark().equals("")) {
					projectINDB.setTalentRemark(project.getTalentRemark());
				}
				if (project.getMoney() != null) {
					projectINDB.setMoney(project.getMoney());
				}
				if (project.getMoneyNum() != null && !project.getMoneyNum().equals("")) {
					projectINDB.setMoneyNum(project.getMoneyNum());
				}
				if (project.getMoneyRemark() != null && !project.getMoneyRemark().equals("")) {
					projectINDB.setMoneyRemark(project.getMoneyRemark());
				}
				if (project.getMentor() != null) {
					projectINDB.setMentor(project.getMentor());
				}
				if (project.getMentorNum() != null && !project.getMentorNum().equals("")) {
					projectINDB.setMentorNum(project.getMentorNum());
				}
				if (project.getMentorRemark() != null && !project.getMentorRemark().equals("")) {
					projectINDB.setMentorRemark(project.getMentorRemark());
				}
				if (project.getTdkzpzj() != null && !project.getTdkzpzj().equals("")) {
					projectINDB.setTdkzpzj(project.getTdkzpzj());
				}
				if (!projectDao.updateProject(projectINDB)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				} else {
					dataWrapper.setData(projectINDB);
				}
			}
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		
		return dataWrapper;
		
	}

	@Override
	public DataWrapper<Void> addProjectMember(ProjectMember projectMember, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null && projectMember.getProjectId() != null && projectMember.getMemberName() != null) {
			projectMember.setId(null);
			if (!projectMemberDao.addProjectMember(projectMember)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> addProjectSchedule(ProjectSchedule projectSchedule, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null && projectSchedule.getProjectId() != null && projectSchedule.getTaskIntro() != null && projectSchedule.getTime() != null) {
			projectSchedule.setId(null);
			if (!projectScheduleDao.addProjectSchedule(projectSchedule)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> addProjectButt(ProjectButt projectButt, MultipartFile personalWork, String token,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null && projectButt.getProjectId() != null && projectButt.getName() != null) {
			projectButt.setId(null);
			projectButt.setUserId(user.getId());
			projectButt.setButtTime(System.currentTimeMillis()/1000);
			if (personalWork != null) {
				String filePath = FileUtils.saveFile(personalWork, "personalwork", request);
				projectButt.setUrl(filePath);
				
			}
			projectButt.setStatus(1);
			if (!projectButtDao.addProjectButt(projectButt)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> addProjectPeopleDemand(ProjectPeopleDemand projectPeopleDemand, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null && projectPeopleDemand.getProjectId() != null) {
			projectPeopleDemand.setId(null);
			projectPeopleDemand.setAddTime(new Date());
			
			if (!projectPeopleDemandDao.addProjectPeopleDemand(projectPeopleDemand)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> jieti(ProjectJieTi projectJieTi, MultipartFile jieti, String token,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		
		if (user != null && projectJieTi.getProjectId() != null && jieti != null) {
			Project project = projectDao.getById(projectJieTi.getProjectId());
			if (project != null && project.getUserId().equals(user.getId())) {
				project.setStatus(21);
				project.setJietiTime(System.currentTimeMillis()/1000);
				projectDao.updateProject(project);
				
				
				String filePath = FileUtils.saveFile(jieti, "jieti", request);
				projectJieTi.setId(null);
				projectJieTi.setName(jieti.getOriginalFilename());
				projectJieTi.setUrl(filePath);
				projectJieTi.setInputTime(System.currentTimeMillis()/1000);
				
				if (!projectJieTiDao.addProjectJieTi(projectJieTi)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
			
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> verify(Long projectId, Integer status, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		
		if (user != null && projectId != null && user.getId() < 0) {
			Project project = projectDao.getById(projectId);
			if (project != null) {
				project.setStatus(status);
				if (!projectDao.updateProject(project)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Project>> getProjectList(String demand, String type, Integer source,Integer status, Integer numPerPage,
			Integer pageNum) {
		// TODO Auto-generated method stub
		return projectDao.getProjectList(demand, type, source, status,numPerPage, pageNum);
	}

	@Override
	public DataWrapper<List<ButtedProject>> getButtProjectList(Integer numPerPage, Integer pageNum, String token) {
		// TODO Auto-generated method stub
		DataWrapper<List<ButtedProject>> dataWrapper = new DataWrapper<List<ButtedProject>>();
		User user = SessionManager.getSession(token);
		if (user != null) {
			if (user.getId() < 0) {
				dataWrapper = projectDao.getButtProjectList(numPerPage, pageNum, null);
			} else {
				dataWrapper = projectDao.getButtProjectList(numPerPage, pageNum, user.getId());
			}
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> verifyButtProject(Long buttId, Integer status,String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new  DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		
		if (user != null && buttId != null) {
			ProjectButt projectButt = projectButtDao.getById(buttId);
			if (projectButt != null) {
				projectButt.setStatus(status);
				if (!projectButtDao.updatePorjectButt(projectButt)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	

}
