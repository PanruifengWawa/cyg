package com.cyg.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_chuangyegu_project_butt")
public class ProjectButt {
	private Long id;
	private Long projectId;
	private Long userId;
	private Integer demandType;
	private String name;
	private String college;
	private String major;
	private String teacherTitle;
	private String companyName;
	private String post;
	private String userNub;
	private String phone;
	private String email;
	private String qq;
	private String applyPost;
	private String specialty;
	private String certificate;
	private String content;
	private String url;
	private String other;
	private String research;
	private String mode;
	private String cooperation;
	private String ckOther;
	private Long buttTime;
	private Integer status;
	private String money;
	
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
    @Column(name = "project_id")
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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
    @Column(name = "demand_type")
	public Integer getDemandType() {
		return demandType;
	}
	public void setDemandType(Integer demandType) {
		this.demandType = demandType;
	}
	
	@Basic
    @Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
    @Column(name = "college")
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	
	@Basic
    @Column(name = "major")
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	@Basic
    @Column(name = "teacher_title")
	public String getTeacherTitle() {
		return teacherTitle;
	}
	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
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
    @Column(name = "post")
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	@Basic
    @Column(name = "user_nub")
	public String getUserNub() {
		return userNub;
	}
	public void setUserNub(String userNub) {
		this.userNub = userNub;
	}
	
	@Basic
    @Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
    @Column(name = "qq")
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Basic
    @Column(name = "apply_post")
	public String getApplyPost() {
		return applyPost;
	}
	public void setApplyPost(String applyPost) {
		this.applyPost = applyPost;
	}
	
	@Basic
    @Column(name = "specialty")
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	@Basic
    @Column(name = "certificate")
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	
	@Basic
    @Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Basic
    @Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Basic
    @Column(name = "other")
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	@Basic
    @Column(name = "research")
	public String getResearch() {
		return research;
	}
	public void setResearch(String research) {
		this.research = research;
	}
	
	@Basic
    @Column(name = "mode")
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	@Basic
    @Column(name = "cooperation")
	public String getCooperation() {
		return cooperation;
	}
	public void setCooperation(String cooperation) {
		this.cooperation = cooperation;
	}
	
	@Basic
    @Column(name = "ck_other")
	public String getCkOther() {
		return ckOther;
	}
	public void setCkOther(String ckOther) {
		this.ckOther = ckOther;
	}
	
	@Basic
    @Column(name = "butt_time")
	public Long getButtTime() {
		return buttTime;
	}
	public void setButtTime(Long buttTime) {
		this.buttTime = buttTime;
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
    @Column(name = "money")
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	

}
