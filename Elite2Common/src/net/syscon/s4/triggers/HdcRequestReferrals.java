package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class HdcRequestReferrals extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long hdcRequestReferralId;
	private String referralTo;
	private String referredBy;
	private Long offenderBookId;
	private Date responseDueDate;
	private String referralInformation;
	private Integer staffId;
	private String contactOfAddrByLetter;
	private String contactOfAddrByVisit;
	private String contactOfAddrByTelephone;
	private Date referralDate;
	private String victimIssuesFlag;
	private Long offenderCurfewId;
	private Date receivedDate;
	private Date responseDate;
	private Long responseStaffId;
	private Long parentHdcRequestReferralId;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String messageId;
	private String sealFlag;

	public Long getHdcRequestReferralId() {
		return hdcRequestReferralId;
	}

	public void setHdcRequestReferralId(final Long hdcRequestReferralId) {
		this.hdcRequestReferralId = hdcRequestReferralId;
	}

	public String getReferralTo() {
		return referralTo;
	}

	public void setReferralTo(final String referralTo) {
		this.referralTo = referralTo;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(final String referredBy) {
		this.referredBy = referredBy;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getResponseDueDate() {
		return responseDueDate;
	}

	public void setResponseDueDate(final Date responseDueDate) {
		this.responseDueDate = responseDueDate;
	}

	public String getReferralInformation() {
		return referralInformation;
	}

	public void setReferralInformation(final String referralInformation) {
		this.referralInformation = referralInformation;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(final Integer staffId) {
		this.staffId = staffId;
	}

	public String getContactOfAddrByLetter() {
		return contactOfAddrByLetter;
	}

	public void setContactOfAddrByLetter(final String contactOfAddrByLetter) {
		this.contactOfAddrByLetter = contactOfAddrByLetter;
	}

	public String getContactOfAddrByVisit() {
		return contactOfAddrByVisit;
	}

	public void setContactOfAddrByVisit(final String contactOfAddrByVisit) {
		this.contactOfAddrByVisit = contactOfAddrByVisit;
	}

	public String getContactOfAddrByTelephone() {
		return contactOfAddrByTelephone;
	}

	public void setContactOfAddrByTelephone(final String contactOfAddrByTelephone) {
		this.contactOfAddrByTelephone = contactOfAddrByTelephone;
	}

	public Date getReferralDate() {
		return referralDate;
	}

	public void setReferralDate(final Date referralDate) {
		this.referralDate = referralDate;
	}

	public String getVictimIssuesFlag() {
		return victimIssuesFlag;
	}

	public void setVictimIssuesFlag(final String victimIssuesFlag) {
		this.victimIssuesFlag = victimIssuesFlag;
	}

	public Long getOffenderCurfewId() {
		return offenderCurfewId;
	}

	public void setOffenderCurfewId(final Long offenderCurfewId) {
		this.offenderCurfewId = offenderCurfewId;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(final Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(final Date responseDate) {
		this.responseDate = responseDate;
	}

	public Long getResponseStaffId() {
		return responseStaffId;
	}

	public void setResponseStaffId(final Long responseStaffId) {
		this.responseStaffId = responseStaffId;
	}

	public Long getParentHdcRequestReferralId() {
		return parentHdcRequestReferralId;
	}

	public void setParentHdcRequestReferralId(final Long parentHdcRequestReferralId) {
		this.parentHdcRequestReferralId = parentHdcRequestReferralId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(final String messageId) {
		this.messageId = messageId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
