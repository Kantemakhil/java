package net.syscon.s4.inst.correspondencetracking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderMailLog extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal offenderBookId;
	private String inoutMailType;
	private Date logDate;
	private BigDecimal mailAddressId;
	private String mailType;
	private String statusCode;
	private String alertCode;
	private String commentText;
	private Date createDatetime;
	private String createUserId;
	private String modifyUserId;
	private Date modifyDatetime;
	private String sealFlag;
	private String contactType;
	private String contactName;
	private String contactAddress;
	private String activeFlag;
	@JsonProperty("personId")
	private BigDecimal personId;
	@JsonProperty("corporateId")
	private BigDecimal corporateId;
	@JsonProperty("logSeq")
	private Long logSeq;
	
	
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getInoutMailType() {
		return inoutMailType;
	}
	public void setInoutMailType(String inoutMailType) {
		this.inoutMailType = inoutMailType;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public BigDecimal getMailAddressId() {
		return mailAddressId;
	}
	public void setMailAddressId(BigDecimal mailAddressId) {
		this.mailAddressId = mailAddressId;
	}
	public String getMailType() {
		return mailType;
	}
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getAlertCode() {
		return alertCode;
	}
	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public BigDecimal getPersonId() {
		return personId;
	}
	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}
	public BigDecimal getCorporateId() {
		return corporateId;
	}
	public void setCorporateId(BigDecimal corporateId) {
		this.corporateId = corporateId;
	}
	public Long getLogSeq() {
		return logSeq;
	}
	public void setLogSeq(Long logSeq) {
		this.logSeq = logSeq;
	}
	
	
	
	
	

}
