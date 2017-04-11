package com.cyg.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_chuangyegu_project_ziyuan")
public class ProjectPeopleDemand {
	private Long id;
	private String leixing;
	private String num;
	private Integer xingshi;
	private Long projectId;
	private String gwlx;
	private Date addTime;
	private String dslx;
	
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
    @Column(name = "leixing")
	public String getLeixing() {
		return leixing;
	}
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	
	@Basic
    @Column(name = "num")
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	@Basic
    @Column(name = "xingshi")
	public Integer getXingshi() {
		return xingshi;
	}
	public void setXingshi(Integer xingshi) {
		this.xingshi = xingshi;
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
    @Column(name = "gwlx")
	public String getGwlx() {
		return gwlx;
	}
	public void setGwlx(String gwlx) {
		this.gwlx = gwlx;
	}
	
	@Basic
    @Column(name = "add_time")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	@Basic
    @Column(name = "dslx")
	public String getDslx() {
		return dslx;
	}
	public void setDslx(String dslx) {
		this.dslx = dslx;
	}
	
	
	
	

}
