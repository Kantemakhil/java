package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VCbSentTermsHis extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBalCalcId")
	private Long offenderBalCalcId;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;
	@JsonProperty("termSeq")
	private Long termSeq;
	@JsonProperty("sentenceCategory")
	private String sentenceCategory;
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	@JsonProperty("sentenceTermCode")
	private String sentenceTermCode;
	@JsonProperty("sentencePeriod")
	private String sentencePeriod;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("ver")
	private Long ver;

	/**
	 * Creates new VCbSentTermsHis class Object
	 */
	public VCbSentTermsHis() {
		// VCbSentTermsHis
	}

	/**
	 * @return the offenderBalCalcId
	 */
	public Long getOffenderBalCalcId() {
		return offenderBalCalcId;
	}

	/**
	 * @param offenderBalCalcId the offenderBalCalcId to set
	 */
	public void setOffenderBalCalcId(final Long offenderBalCalcId) {
		this.offenderBalCalcId = offenderBalCalcId;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the sentenceSeq
	 */
	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	/**
	 * @param sentenceSeq the sentenceSeq to set
	 */
	public void setSentenceSeq(final Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	/**
	 * @return the termSeq
	 */
	public Long getTermSeq() {
		return termSeq;
	}

	/**
	 * @param termSeq the termSeq to set
	 */
	public void setTermSeq(final Long termSeq) {
		this.termSeq = termSeq;
	}

	/**
	 * @return the sentenceCategory
	 */
	public String getSentenceCategory() {
		return sentenceCategory;
	}

	/**
	 * @param sentenceCategory the sentenceCategory to set
	 */
	public void setSentenceCategory(final String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	/**
	 * @return the sentenceCalcType
	 */
	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	/**
	 * @param sentenceCalcType the sentenceCalcType to set
	 */
	public void setSentenceCalcType(final String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	/**
	 * @return the sentenceTermCode
	 */
	public String getSentenceTermCode() {
		return sentenceTermCode;
	}

	/**
	 * @param sentenceTermCode the sentenceTermCode to set
	 */
	public void setSentenceTermCode(final String sentenceTermCode) {
		this.sentenceTermCode = sentenceTermCode;
	}

	/**
	 * @return the sentencePeriod
	 */
	public String getSentencePeriod() {
		return sentencePeriod;
	}

	/**
	 * @param sentencePeriod the sentencePeriod to set
	 */
	public void setSentencePeriod(final String sentencePeriod) {
		this.sentencePeriod = sentencePeriod;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the ver
	 */
	public Long getVer() {
		return ver;
	}

	/**
	 * @param ver the ver to set
	 */
	public void setVer(final Long ver) {
		this.ver = ver;
	}

}
