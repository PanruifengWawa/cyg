package com.cyg.models;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="m_chuangyegu_place")
public class Event {
	private Long id;
	private Long userId;
	private String applyUnit;
	private String contactName;
	private String contactPhone;
	private String mobilePhone;
	private Date useDate;
	private String useTime;
	private String useTimeId;
	private String eventName;
	private String eventContent;
	private Integer campus;
	private String rentalPlace;
	private String eventEquipment;
	private String otherEquipment;
	private String photo;
	private Long appointmentTime;
	private Integer status;
	
	private BigInteger applyCount;
	
	
	
	@Transient
	public BigInteger getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(BigInteger applyCount) {
		this.applyCount = applyCount;
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
    @Column(name = "apply_unit")
	public String getApplyUnit() {
		return applyUnit;
	}
	public void setApplyUnit(String applyUnit) {
		this.applyUnit = applyUnit;
	}
	
	@Basic
    @Column(name = "contact_name")
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	@Basic
    @Column(name = "contact_phone")
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	@Basic
    @Column(name = "mobile_phone")
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Basic
	@Temporal(TemporalType.DATE)
    @Column(name = "use_date")
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	
	@Basic
    @Column(name = "use_time")
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	
	@Basic
    @Column(name = "use_time_id")
	public String getUseTimeId() {
		return useTimeId;
	}
	public void setUseTimeId(String useTimeId) {
		this.useTimeId = useTimeId;
	}
	
	@Basic
    @Column(name = "event_name")
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@Basic
    @Column(name = "event_content")
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	
	@Basic
    @Column(name = "campus")
	public Integer getCampus() {
		return campus;
	}
	public void setCampus(Integer campus) {
		this.campus = campus;
	}
	
	@Basic
    @Column(name = "rental_place")
	public String getRentalPlace() {
		return rentalPlace;
	}
	public void setRentalPlace(String rentalPlace) {
		this.rentalPlace = rentalPlace;
	}
	
	@Basic
    @Column(name = "event_equipment")
	public String getEventEquipment() {
		return eventEquipment;
	}
	public void setEventEquipment(String eventEquipment) {
		this.eventEquipment = eventEquipment;
	}
	
	@Basic
    @Column(name = "other_equipment")
	public String getOtherEquipment() {
		return otherEquipment;
	}
	public void setOtherEquipment(String otherEquipment) {
		this.otherEquipment = otherEquipment;
	}
	
	@Basic
    @Column(name = "photo")
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Basic
    @Column(name = "appointment_time")
	public Long getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Long appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
	@Basic
    @Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	

}
