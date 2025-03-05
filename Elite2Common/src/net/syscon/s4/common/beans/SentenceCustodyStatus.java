package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the SENTENCE_CALC_TYPES database table.
 * 
 */
public class SentenceCustodyStatus extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String sentenceOrderStatus;
	private String custodyStatus;
	private String sentenceCalcType;
	private String sentenceCategory;
	
	private Date createDatetime;

	private String createUserId;

	private String description;
	
	private String code;

	private Date expiryDate;

	private String functionType;

	private String headerLabel;

	private BigDecimal headerSeq;

	private BigDecimal listSeq;

	private Date modifyDatetime;

	private String modifyUserId;

	private String programMethod;

	private String sealFlag;

	private String sentenceOrderStatusTemp;
	private String sentenceCustodyStatusTemp;
	
	private String legalClass;
	
	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getSentenceOrderStatus() {
		return sentenceOrderStatus;
	}

	public void setSentenceOrderStatus(String sentenceOrderStatus) {
		this.sentenceOrderStatus = sentenceOrderStatus;
	}


	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getHeaderLabel() {
		return headerLabel;
	}

	public void setHeaderLabel(String headerLabel) {
		this.headerLabel = headerLabel;
	}

	public BigDecimal getHeaderSeq() {
		return headerSeq;
	}

	public void setHeaderSeq(BigDecimal headerSeq) {
		this.headerSeq = headerSeq;
	}

	public BigDecimal getListSeq() {
		return listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public String getLegalClass() {
		return legalClass;
	}

	public void setLegalClass(String legalClass) {
		this.legalClass = legalClass;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getProgramMethod() {
		return programMethod;
	}

	public void setProgramMethod(String programMethod) {
		this.programMethod = programMethod;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSentenceOrderStatusTemp() {
		return sentenceOrderStatusTemp;
	}

	public void setSentenceOrderStatusTemp(String sentenceOrderStatusTemp) {
		this.sentenceOrderStatusTemp = sentenceOrderStatusTemp;
	}

	public String getSentenceCustodyStatusTemp() {
		return sentenceCustodyStatusTemp;
	}

	public void setSentenceCustodyStatusTemp(String sentenceCustodyStatusTemp) {
		this.sentenceCustodyStatusTemp = sentenceCustodyStatusTemp;
	}

	public String getCustodyStatus() {
		return custodyStatus;
	}

	public void setCustodyStatus(String custodyStatus) {
		this.custodyStatus = custodyStatus;
	}


    

}
