package net.syscon.s4.common.beans;

import java.io.Serializable;

import net.syscon.s4.inst.legals.beans.SentenceAdjustment;


/**
 * The persistent class for the SENTENCE_TERM_ADJUSTMENTS database table.
 * 
 */
public class SentenceTermAdjustment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private Object createDatetime;

	private String createUserId;

	private String description;

	private Object expiryDate;

	private Object modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	//bi-directional many-to-one association to SentenceAdjustment
	private SentenceAdjustment sentenceAdjustment;

	//bi-directional many-to-one association to SentenceTerm
		
	private SentenceTerms sentenceTerm;
	
	private String sentenceCategory;

	private String sentenceCalcType;

	private String termCode;

	private String adjustCode;

	public SentenceTermAdjustment() {
	}
	
	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public String getAdjustCode() {
		return adjustCode;
	}

	public void setAdjustCode(String adjustCode) {
		this.adjustCode = adjustCode;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
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

	public Object getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Object expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public SentenceAdjustment getSentenceAdjustment() {
		return this.sentenceAdjustment;
	}

	public void setSentenceAdjustment(SentenceAdjustment sentenceAdjustment) {
		this.sentenceAdjustment = sentenceAdjustment;
	}

	public SentenceTerms getSentenceTerms() {
		return this.sentenceTerm;
	}

	public void setSentenceTerms(SentenceTerms sentenceTerm) {
		this.sentenceTerm = sentenceTerm;
	}

}

