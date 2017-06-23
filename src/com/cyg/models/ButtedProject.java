package com.cyg.models;

public class ButtedProject {
	private Long id;
	private Long projectId;
	private String projectName;
	private Integer demandType;
	private Long buttTime;
	private Integer status;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getDemandType() {
		return demandType;
	}
	public void setDemandType(Integer demandType) {
		this.demandType = demandType;
	}
	public Long getButtTime() {
		return buttTime;
	}
	public void setButtTime(Long buttTime) {
		this.buttTime = buttTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
