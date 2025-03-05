package net.syscon.s4.inst.correspondencetracking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffMailRestrictions extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("restrictionSeq")
	private BigDecimal restrictionSeq;
	@JsonProperty("restrictioAddressId")
	private BigDecimal restrictioAddressId;
	@JsonProperty("restrictionType")
	private String restrictionType;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("contactType")
	private String contactType;
	@JsonProperty("contactName")
	private String contactName;
	@JsonProperty("contactAddress")
	private String contactAddress;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("personId")
	private BigDecimal personId;
	@JsonProperty("corporateId")
	private BigDecimal corporateId;
	
	@JsonProperty("logSequence")
	private Long logSequence;
	
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public BigDecimal getRestrictionSeq() {
		return restrictionSeq;
	}
	public void setRestrictionSeq(BigDecimal restrictionSeq) {
		this.restrictionSeq = restrictionSeq;
	}
	public BigDecimal getRestrictioAddressId() {
		return restrictioAddressId;
	}
	public void setRestrictioAddressId(BigDecimal restrictioAddressId) {
		this.restrictioAddressId = restrictioAddressId;
	}
	public String getRestrictionType() {
		return restrictionType;
	}
	public void setRestrictionType(String restrictionType) {
		this.restrictionType = restrictionType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	public Long getLogSequence() {
		return logSequence;
	}
	public void setLogSequence(Long logSequence) {
		this.logSequence = logSequence;
	}
	

	

}
