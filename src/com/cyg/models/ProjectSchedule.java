package com.cyg.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_chuangyegu_project_schedule")
public class ProjectSchedule {
	private Long id;
	private Long projectId;
	private String time;
	private String taskIntro;
	private Integer scheduleType;
	private String endTime;
	
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
    @Column(name = "time")
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	@Basic
    @Column(name = "task_intro")
	public String getTaskIntro() {
		return taskIntro;
	}
	public void setTaskIntro(String taskIntro) {
		this.taskIntro = taskIntro;
	}
	
	@Basic
    @Column(name = "schedule_type")
	public Integer getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(Integer scheduleType) {
		this.scheduleType = scheduleType;
	}
	
	@Basic
    @Column(name = "end_time")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
	

}
