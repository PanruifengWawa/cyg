package com.cyg.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_chuangyegu_project")
public class Project {
	private Long id;
	private Long userId;
	private String projectName;
	private String projectField;
	private String applyName;
	private String applyCollege;
	private String applyMajor;
	private String studentId;
	private String applyTeacherId;
	private String applyPhone;
	private String email;
	private String teacherName;
	private String teacherId;
	private String faculty;
	private String teacherMajor;
	private String title;
	private String teacherPhone;
	private String projectType;
	private String projectProperty;
	private String isCompany;
	private String companyMoney;
	private String projectIntro;
	private String projectBackGround;
	private String projectBrief;
	private String slogan;
	private String projectTotalTime;
	private Integer type;
	private Integer status;
	private Long applyTime;
	private String teacherEmail;
	private String teacherField;
	private String projectMarket;
	private String projectBenefits;
	private String projectTeacher;
	private String projectStaff;
	private String projectReport;
	private String projectFunding;
	private String projectOther;
	private String applyTeacherTitle;
	private String applyNumber;
	private String applyOfficeSector;
	private String applyWorkUnit;
	private String companyName;
	private String companyType;
	private String companyContact;
	private String officeSector;
	private String xqOtherRemark;
	private String xiaoqu;
	private Long jietiTime;
	
	private Integer talent;
	private String talentNum;
	private String talentRemark;
	private Integer money;
	private String moneyNum;
	private String moneyRemark;
	private Integer mentor;
	private String mentorNum;
	private String mentorRemark;
	
	private String tdkzpzj;
	
	
	
	@Basic
    @Column(name = "tdkzpzj")
	public String getTdkzpzj() {
		return tdkzpzj;
	}
	public void setTdkzpzj(String tdkzpzj) {
		this.tdkzpzj = tdkzpzj;
	}
	@Basic
    @Column(name = "talent")
	public Integer getTalent() {
		return talent;
	}
	public void setTalent(Integer talent) {
		this.talent = talent;
	}
	@Basic
    @Column(name = "talent_num")
	public String getTalentNum() {
		return talentNum;
	}
	public void setTalentNum(String talentNum) {
		this.talentNum = talentNum;
	}
	
	@Basic
    @Column(name = "talent_remark")
	public String getTalentRemark() {
		return talentRemark;
	}
	public void setTalentRemark(String talentRemark) {
		this.talentRemark = talentRemark;
	}
	
	@Basic
    @Column(name = "money")
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	
	@Basic
    @Column(name = "money_num")
	public String getMoneyNum() {
		return moneyNum;
	}
	public void setMoneyNum(String moneyNum) {
		this.moneyNum = moneyNum;
	}
	
	@Basic
    @Column(name = "money_remark")
	public String getMoneyRemark() {
		return moneyRemark;
	}
	public void setMoneyRemark(String moneyRemark) {
		this.moneyRemark = moneyRemark;
	}
	
	@Basic
    @Column(name = "mentor")
	public Integer getMentor() {
		return mentor;
	}
	public void setMentor(Integer mentor) {
		this.mentor = mentor;
	}
	
	@Basic
    @Column(name = "mentor_num")
	public String getMentorNum() {
		return mentorNum;
	}
	public void setMentorNum(String mentorNum) {
		this.mentorNum = mentorNum;
	}
	
	@Basic
    @Column(name = "mentor_remark")
	public String getMentorRemark() {
		return mentorRemark;
	}
	public void setMentorRemark(String mentorRemark) {
		this.mentorRemark = mentorRemark;
	}
	@Basic
    @Column(name = "apply_teacher_id")
	public String getApplyTeacherId() {
		return applyTeacherId;
	}
	public void setApplyTeacherId(String applyTeacherId) {
		this.applyTeacherId = applyTeacherId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Basic
    @Column(name = "project_name")
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Basic
    @Column(name = "project_field")
	public String getProjectField() {
		return projectField;
	}
	public void setProjectField(String projectField) {
		this.projectField = projectField;
	}
	
	@Basic
    @Column(name = "apply_name")
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	
	@Basic
    @Column(name = "apply_college")
	public String getApplyCollege() {
		return applyCollege;
	}
	public void setApplyCollege(String applyCollege) {
		this.applyCollege = applyCollege;
	}
	
	@Basic
    @Column(name = "apply_major")
	public String getApplyMajor() {
		return applyMajor;
	}
	public void setApplyMajor(String applyMajor) {
		this.applyMajor = applyMajor;
	}
	
	@Basic
    @Column(name = "student_id")
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@Basic
    @Column(name = "apply_phone")
	public String getApplyPhone() {
		return applyPhone;
	}
	public void setApplyPhone(String applyPhone) {
		this.applyPhone = applyPhone;
	}
	
	@Basic
    @Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Basic
    @Column(name = "teacher_name")
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	@Basic
    @Column(name = "teacher_id")
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	@Basic
    @Column(name = "faculty")
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	
	@Basic
    @Column(name = "teacher_major")
	public String getTeacherMajor() {
		return teacherMajor;
	}
	public void setTeacherMajor(String teacherMajor) {
		this.teacherMajor = teacherMajor;
	}
	
	@Basic
    @Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Basic
    @Column(name = "teacher_phone")
	public String getTeacherPhone() {
		return teacherPhone;
	}
	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}
	
	@Basic
    @Column(name = "project_type")
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Basic
    @Column(name = "project_property")
	public String getProjectProperty() {
		return projectProperty;
	}
	public void setProjectProperty(String projectProperty) {
		this.projectProperty = projectProperty;
	}
	
	@Basic
    @Column(name = "is_company")
	public String getIsCompany() {
		return isCompany;
	}
	public void setIsCompany(String isCompany) {
		this.isCompany = isCompany;
	}
	
	@Basic
    @Column(name = "company_money")
	public String getCompanyMoney() {
		return companyMoney;
	}
	public void setCompanyMoney(String companyMoney) {
		this.companyMoney = companyMoney;
	}
	
	@Basic
    @Column(name = "project_intro")
	public String getProjectIntro() {
		return projectIntro;
	}
	public void setProjectIntro(String projectIntro) {
		this.projectIntro = projectIntro;
	}
	
	@Basic
    @Column(name = "project_background")
	public String getProjectBackGround() {
		return projectBackGround;
	}
	public void setProjectBackGround(String projectBackGround) {
		this.projectBackGround = projectBackGround;
	}
	
	@Basic
    @Column(name = "project_brief")
	public String getProjectBrief() {
		return projectBrief;
	}
	public void setProjectBrief(String projectBrief) {
		this.projectBrief = projectBrief;
	}
	
	@Basic
    @Column(name = "slogan")
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	@Basic
    @Column(name = "project_total_time")
	public String getProjectTotalTime() {
		return projectTotalTime;
	}
	public void setProjectTotalTime(String projectTotalTime) {
		this.projectTotalTime = projectTotalTime;
	}
	
	@Basic
    @Column(name = "type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Basic
    @Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Basic
    @Column(name = "apply_time")
	public Long getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Long applyTime) {
		this.applyTime = applyTime;
	}
	
	@Basic
    @Column(name = "teacher_email")
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	
	@Basic
    @Column(name = "teacher_field")
	public String getTeacherField() {
		return teacherField;
	}
	public void setTeacherField(String teacherField) {
		this.teacherField = teacherField;
	}
	
	@Basic
    @Column(name = "project_market")
	public String getProjectMarket() {
		return projectMarket;
	}
	public void setProjectMarket(String projectMarket) {
		this.projectMarket = projectMarket;
	}
	
	@Basic
    @Column(name = "project_benefits")
	public String getProjectBenefits() {
		return projectBenefits;
	}
	public void setProjectBenefits(String projectBenefits) {
		this.projectBenefits = projectBenefits;
	}
	
	@Basic
    @Column(name = "project_teacher")
	public String getProjectTeacher() {
		return projectTeacher;
	}
	public void setProjectTeacher(String projectTeacher) {
		this.projectTeacher = projectTeacher;
	}
	
	@Basic
    @Column(name = "project_staff")
	public String getProjectStaff() {
		return projectStaff;
	}
	public void setProjectStaff(String projectStaff) {
		this.projectStaff = projectStaff;
	}
	
	@Basic
    @Column(name = "project_report")
	public String getProjectReport() {
		return projectReport;
	}
	public void setProjectReport(String projectReport) {
		this.projectReport = projectReport;
	}
	
	@Basic
    @Column(name = "project_funding")
	public String getProjectFunding() {
		return projectFunding;
	}
	public void setProjectFunding(String projectFunding) {
		this.projectFunding = projectFunding;
	}
	
	
	@Basic
    @Column(name = "project_other")
	public String getProjectOther() {
		return projectOther;
	}
	public void setProjectOther(String projectOther) {
		this.projectOther = projectOther;
	}
	
	@Basic
    @Column(name = "apply_teacher_title")
	public String getApplyTeacherTitle() {
		return applyTeacherTitle;
	}
	public void setApplyTeacherTitle(String applyTeacherTitle) {
		this.applyTeacherTitle = applyTeacherTitle;
	}
	
	@Basic
    @Column(name = "apply_number")
	public String getApplyNumber() {
		return applyNumber;
	}
	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}
	
	@Basic
    @Column(name = "apply_office_sector")
	public String getApplyOfficeSector() {
		return applyOfficeSector;
	}
	public void setApplyOfficeSector(String applyOfficeSector) {
		this.applyOfficeSector = applyOfficeSector;
	}
	
	@Basic
    @Column(name = "apply_work_unit")
	public String getApplyWorkUnit() {
		return applyWorkUnit;
	}
	public void setApplyWorkUnit(String applyWorkUnit) {
		this.applyWorkUnit = applyWorkUnit;
	}
	
	@Basic
    @Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Basic
    @Column(name = "company_type")
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	@Basic
    @Column(name = "company_contact")
	public String getCompanyContact() {
		return companyContact;
	}
	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}
	
	@Basic
    @Column(name = "office_sector")
	public String getOfficeSector() {
		return officeSector;
	}
	public void setOfficeSector(String officeSector) {
		this.officeSector = officeSector;
	}
	
	@Basic
    @Column(name = "xq_other_remark")
	public String getXqOtherRemark() {
		return xqOtherRemark;
	}
	public void setXqOtherRemark(String xqOtherRemark) {
		this.xqOtherRemark = xqOtherRemark;
	}
	
	@Basic
    @Column(name = "xiaoqu")
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	
	@Basic
    @Column(name = "jieti_time")
	public Long getJietiTime() {
		return jietiTime;
	}
	public void setJietiTime(Long jietiTime) {
		this.jietiTime = jietiTime;
	}
	
	

}
