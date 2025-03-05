package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the DURATIONS database table.
 * 
 */
public class Durations implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private Date createDatetime;

	private String createUserId;

	private String description;

	private Date expiryDate;

	private BigDecimal listSeq;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal nextReview;

	private BigDecimal noOfDays;

	private String revBox;

	private String sealFlag;

	private String updateAllowedFlag;

	private String durationCode;

	private String internalStatus;

	private String intStsReasonCode;
	
	private String code;

	public Durations() {
		// Durationss
	}

	public String getDurationCode() {
		return durationCode;
	}

	public void setDurationCode(String durationCode) {
		this.durationCode = durationCode;
	}

	public String getInternalStatus() {
		return internalStatus;
	}

	public void setInternalStatus(String internalStatus) {
		this.internalStatus = internalStatus;
	}

	public String getIntStsReasonCode() {
		return intStsReasonCode;
	}

	public void setIntStsReasonCode(String intStsReasonCode) {
		this.intStsReasonCode = intStsReasonCode;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
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

	public BigDecimal getNextReview() {
		return this.nextReview;
	}

	public void setNextReview(BigDecimal nextReview) {
		this.nextReview = nextReview;
	}

	public BigDecimal getNoOfDays() {
		return this.noOfDays;
	}

	public void setNoOfDays(BigDecimal noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getRevBox() {
		return this.revBox;
	}

	public void setRevBox(String revBox) {
		this.revBox = revBox;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getUpdateAllowedFlag() {
		return this.updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
