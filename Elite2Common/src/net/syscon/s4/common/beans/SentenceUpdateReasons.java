package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SENTENCE_UPDATE_REASONS database table.
 * 
 */
public class SentenceUpdateReasons extends BaseModel implements Serializable {
private static final long serialVersionUID = 1L;
private String pCode;
private String pDesc;
private String pType;
private String nbtStatusDesc;
	public String getNbtStatusDesc() {
	return nbtStatusDesc;
}

public void setNbtStatusDesc(final String nbtStatusDesc) {
	this.nbtStatusDesc = nbtStatusDesc;
}

	public String getpCode() {
	return pCode;
}

public void setpCode(final String pCode) {
	this.pCode = pCode;
}

public String getpDesc() {
	return pDesc;
}

public void setpDesc(final String pDesc) {
	this.pDesc = pDesc;
}

public String getpType() {
	return pType;
}

public void setpType(final String pType) {
	this.pType = pType;
}

	private String activeFlag;

	private Date createDatetime;

	private String createUserId;

	private String description;

	private Date expiryDate;

	private String legalClass;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	//bi-directional many-to-one association to SentenceCalcType
	private SentenceCalcTypes sentenceCalcTypeBean;
	
	private String sentenceCategory;

	private String sentenceCalcType;

	private String updateReasonCode;
	
	private String activeType;

	public SentenceUpdateReasons() {
	}
	
	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(final String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(final String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getUpdateReasonCode() {
		return updateReasonCode;
	}

	public void setUpdateReasonCode(final String updateReasonCode) {
		this.updateReasonCode = updateReasonCode;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}


	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}




	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
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

	public SentenceCalcTypes getSentenceCalcTypeBean() {
		return sentenceCalcTypeBean;
	}

	public void setSentenceCalcTypeBean(final SentenceCalcTypes sentenceCalcTypeBean) {
		this.sentenceCalcTypeBean = sentenceCalcTypeBean;
	}

	public String getLegalClass() {
		return this.legalClass;
	}

	public void setLegalClass(final String legalClass) {
		this.legalClass = legalClass;
	}


	

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public SentenceCalcTypes getSentenceCalcTypesBean() {
		return this.sentenceCalcTypeBean;
	}

	public void setSentenceCalcTypesBean(final SentenceCalcTypes sentenceCalcTypeBean) {
		this.sentenceCalcTypeBean = sentenceCalcTypeBean;
	}

	private BigDecimal returnedOutput;

	public BigDecimal getReturnedOutput() {
		return returnedOutput;
	}

	public void setReturnedOutput(final BigDecimal returnedOutput) {
		this.returnedOutput = returnedOutput;
	}

	public String getActiveType() {
		return activeType;
	}

	public void setActiveType(final String activeType) {
		this.activeType = activeType;
	}
	
}
