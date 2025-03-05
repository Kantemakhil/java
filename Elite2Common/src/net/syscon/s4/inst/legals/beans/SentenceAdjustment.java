package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class SentenceAdjustment extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("caseNumber")
	private String caseNumber;

	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;

	@JsonProperty("offenseCode")
	private String offenseCode;

	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;

	@JsonProperty("sentenceStatus")
	private String sentenceStatus;

	@JsonProperty("sentenceAdjustCode")
	private String sentenceAdjustCode;

	@JsonProperty("debitCreditCode")
	private String debitCreditCode;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("usageCode")
	private String usageCode;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	@NotNull
	@Size(max = 12)
	private String code;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getUsageCode() {
		return usageCode;
	}

	public void setUsageCode(final String usageCode) {
		this.usageCode = usageCode;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(final String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(final Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public String getOffenseCode() {
		return offenseCode;
	}

	public void setOffenseCode(final String offenseCode) {
		this.offenseCode = offenseCode;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(final String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceStatus() {
		return sentenceStatus;
	}

	public void setSentenceStatus(final String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	public String getSentenceAdjustCode() {
		return sentenceAdjustCode;
	}

	public void setSentenceAdjustCode(final String sentenceAdjustCode) {
		this.sentenceAdjustCode = sentenceAdjustCode;
	}

	public String getDebitCreditCode() {
		return debitCreditCode;
	}

	public void setDebitCreditCode(final String debitCreditCode) {
		this.debitCreditCode = debitCreditCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

}
