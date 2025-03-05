package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SENTENCE_TERMS database table.
 * 
 */
public class SentenceTerms extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private Date createDatetime;

	private String createUserId;

	private Date expiryDate;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;
	private BigDecimal returnedOutput;

	//bi-directional many-to-one association to SentenceCalcType
		
	public BigDecimal getReturnedOutput() {
		return returnedOutput;
	}

	public void setReturnedOutput(final BigDecimal returnedOutput) {
		this.returnedOutput = returnedOutput;
	}

	private SentenceCalcTypes sentenceCalcTypeBean;

	//bi-directional many-to-one association to SentenceTermAdjustment
	private List<SentenceTermAdjustment> sentenceTermAdjustments;
	
	private String sentenceCategory;

	private String sentenceCalcType;

	private String termCode;

	public SentenceTerms() {
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

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(final String termCode) {
		this.termCode = termCode;
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

	public List<SentenceTermAdjustment> getSentenceTermAdjustments() {
		return this.sentenceTermAdjustments;
	}

	public void setSentenceTermAdjustments(final List<SentenceTermAdjustment> sentenceTermAdjustments) {
		this.sentenceTermAdjustments = sentenceTermAdjustments;
	}

	public SentenceTermAdjustment addSentenceTermAdjustment(final SentenceTermAdjustment sentenceTermAdjustment) {
		getSentenceTermAdjustments().add(sentenceTermAdjustment);
		sentenceTermAdjustment.setSentenceTerms(this);

		return sentenceTermAdjustment;
	}

	public SentenceTermAdjustment removeSentenceTermAdjustment(final SentenceTermAdjustment sentenceTermAdjustment) {
		getSentenceTermAdjustments().remove(sentenceTermAdjustment);
		sentenceTermAdjustment.setSentenceTerms(null);

		return sentenceTermAdjustment;
	}
	
	private Integer minValue;
	private String minValueType;
	private Integer maxValue;
	private String maxValueType;
	private String headSentFlag;

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	public String getMinValueType() {
		return minValueType;
	}

	public void setMinValueType(String minValueType) {
		this.minValueType = minValueType;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public String getMaxValueType() {
		return maxValueType;
	}

	public void setMaxValueType(String maxValueType) {
		this.maxValueType = maxValueType;
	}

	public String getHeadSentFlag() {
		return headSentFlag;
	}

	public void setHeadSentFlag(String headSentFlag) {
		this.headSentFlag = headSentFlag;
	}
	
}
