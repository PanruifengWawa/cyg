package com.cyg.models;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cms_c_news")
public class NewsContent {
	private Long id;
	private String content;
	private Long readPoint;
	
	
	
	@Basic
    @Column(name = "readpoint")
	public Long getReadPoint() {
		return readPoint;
	}
	public void setReadPoint(Long readPoint) {
		this.readPoint = readPoint;
	}
	@Id
    @Column(name = "contentid")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Basic
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
