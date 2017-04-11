package com.cyg.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_chuangyegu_project_member")
public class ProjectMember {
	private Long id;
	private Long projectId;
	private String memberName;
	private String memberCollege;
	private String memberMajor;
	private String memberWorkUnit;
	private String memberOfficeSector;
	private String memberId;
	private String memberPhone;
	private String memberEmail;
	private String function;
	private String memberField;
	private Integer isHas;
	private Integer memberType;
	private String gwlx;
	
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
    @Column(name = "member_name")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	@Basic
    @Column(name = "member_college")
	public String getMemberCollege() {
		return memberCollege;
	}
	public void setMemberCollege(String memberCollege) {
		this.memberCollege = memberCollege;
	}
	
	@Basic
    @Column(name = "member_major")
	public String getMemberMajor() {
		return memberMajor;
	}
	public void setMemberMajor(String memberMajor) {
		this.memberMajor = memberMajor;
	}
	@Basic
    @Column(name = "member_work_unit")
	public String getMemberWorkUnit() {
		return memberWorkUnit;
	}
	public void setMemberWorkUnit(String memberWorkUnit) {
		this.memberWorkUnit = memberWorkUnit;
	}
	@Basic
    @Column(name = "member_office_sector")
	public String getMemberOfficeSector() {
		return memberOfficeSector;
	}
	public void setMemberOfficeSector(String memberOfficeSector) {
		this.memberOfficeSector = memberOfficeSector;
	}
	
	@Basic
    @Column(name = "member_id")
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Basic
    @Column(name = "member_phone")
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	
	@Basic
    @Column(name = "member_email")
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	@Basic
    @Column(name = "function")
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	
	@Basic
    @Column(name = "member_field")
	public String getMemberField() {
		return memberField;
	}
	public void setMemberField(String memberField) {
		this.memberField = memberField;
	}
	
	@Basic
    @Column(name = "is_has")
	public Integer getIsHas() {
		return isHas;
	}
	public void setIsHas(Integer isHas) {
		this.isHas = isHas;
	}
	
	@Basic
    @Column(name = "member_type")
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	
	@Basic
    @Column(name = "gwlx")
	public String getGwlx() {
		return gwlx;
	}
	public void setGwlx(String gwlx) {
		this.gwlx = gwlx;
	}
	
	
	

}
