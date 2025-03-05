package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the V_STG_MEMBERSHIP_INQUIRY database table.
 * 
 */
public class VStgMembershipInquiry extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("actionCode")
	private String actionCode;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("appealDate")
	private Date appealDate;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("expiredBy")
	private String expiredBy;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("expiryReasonCode")
	private String expiryReasonCode;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("notifiedBy")
	private String notifiedBy;
	
	@JsonProperty("notifiedDate")
	private Date notifiedDate;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("reasonCode")
	private String reasonCode;
	
	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;
	
	@JsonProperty("statusReason")
	private String statusReason;
	
	@JsonProperty("stgAffActiveFlag")
	private String stgAffActiveFlag;
	
	@JsonProperty("stgId")
	private BigDecimal stgId;
	
	@JsonProperty("stgSeq")
	private BigDecimal stgSeq;
	
	@JsonProperty("valDate")
	private Date valDate;

	public VStgMembershipInquiry() {
		// VStgMembershipInquiry
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(final String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getAppealDate() {
		return this.appealDate;
	}

	public void setAppealDate(final Date appealDate) {
		this.appealDate = appealDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getExpiredBy() {
		return this.expiredBy;
	}

	public void setExpiredBy(final String expiredBy) {
		this.expiredBy = expiredBy;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getExpiryReasonCode() {
		return this.expiryReasonCode;
	}

	public void setExpiryReasonCode(final String expiryReasonCode) {
		this.expiryReasonCode = expiryReasonCode;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getNotifiedBy() {
		return this.notifiedBy;
	}

	public void setNotifiedBy(final String notifiedBy) {
		this.notifiedBy = notifiedBy;
	}

	public Date getNotifiedDate() {
		return this.notifiedDate;
	}

	public void setNotifiedDate(final Date notifiedDate) {
		this.notifiedDate = notifiedDate;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(final String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public BigDecimal getRootOffenderId() {
		return this.rootOffenderId;
	}

	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getStatusReason() {
		return this.statusReason;
	}

	public void setStatusReason(final String statusReason) {
		this.statusReason = statusReason;
	}

	public String getStgAffActiveFlag() {
		return this.stgAffActiveFlag;
	}

	public void setStgAffActiveFlag(final String stgAffActiveFlag) {
		this.stgAffActiveFlag = stgAffActiveFlag;
	}

	public BigDecimal getStgId() {
		return this.stgId;
	}

	public void setStgId(final BigDecimal stgId) {
		this.stgId = stgId;
	}

	public BigDecimal getStgSeq() {
		return this.stgSeq;
	}

	public void setStgSeq(final BigDecimal stgSeq) {
		this.stgSeq = stgSeq;
	}

	public Date getValDate() {
		return this.valDate;
	}

	public void setValDate(final Date valDate) {
		this.valDate = valDate;
	}

}
