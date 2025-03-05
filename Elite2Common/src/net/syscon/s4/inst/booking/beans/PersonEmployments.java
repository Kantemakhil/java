package net.syscon.s4.inst.booking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonEmployments implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String address;

	private String address1;

	private String address2;

	private String city;

	private String commentText;

	private String contactNumber;

	private String contactType;

	private Date createDatetime;

	private String createUserId;

	private String employerName;

	private Date employmentDate;

	private BigDecimal hoursWeek;

	private Date modifyDatetime;

	private String modifyUserId;

	private String occupationCode;

	private String phoneArea;

	private String phoneExt;

	private String provStateCode;

	private String scheduleType;

	private String sealFlag;

	private String supervisorName;

	private Date terminationDate;

	private String wage;

	private String wagePeriodCode;

	private long personId;

	private long employmentSeq;

	@JsonProperty("format")
	private String format;
	public PersonEmployments() {
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long getEmploymentSeq() {
		return employmentSeq;
	}

	public void setEmploymentSeq(long employmentSeq) {
		this.employmentSeq = employmentSeq;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactType() {
		return this.contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getEmployerName() {
		return this.employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public Date getEmploymentDate() {
		return this.employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public BigDecimal getHoursWeek() {
		return this.hoursWeek;
	}

	public void setHoursWeek(BigDecimal hoursWeek) {
		this.hoursWeek = hoursWeek;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getOccupationCode() {
		return this.occupationCode;
	}

	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	public String getPhoneArea() {
		return this.phoneArea;
	}

	public void setPhoneArea(String phoneArea) {
		this.phoneArea = phoneArea;
	}

	public String getPhoneExt() {
		return this.phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	public String getProvStateCode() {
		return this.provStateCode;
	}

	public void setProvStateCode(String provStateCode) {
		this.provStateCode = provStateCode;
	}

	public String getScheduleType() {
		return this.scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSupervisorName() {
		return this.supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public Date getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getWage() {
		return this.wage;
	}

	public void setWage(String wage) {
		this.wage = wage;
	}

	public String getWagePeriodCode() {
		return this.wagePeriodCode;
	}

	public void setWagePeriodCode(String wagePeriodCode) {
		this.wagePeriodCode = wagePeriodCode;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
