package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class SentenceAggregates extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("caseId")
	private Long caseId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	
	@JsonProperty("sentenceCategory")
	private String sentenceCategory;
	
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	
	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("endDate")
	private Date endDate;
	
	@JsonProperty("aggregateTerm")
	private Integer aggregateTerm;
	
	@JsonProperty("aggregateAdjustDays")
	private Integer aggregateAdjustDays;
	
	@JsonProperty("sed")
	private Date sed;
	
	@JsonProperty("daysToArdCrd")
	private Integer daysToArdCrd;
	
	@JsonProperty("ardCrdDate")
	private Date ardCrdDate;
	
	@JsonProperty("hdcedCalculatedDate")
	private Date hdcedCalculatedDate;
	
	@JsonProperty("etdCalculatedDate")
	private Date etdCalculatedDate;
	
	@JsonProperty("mtdCalculatedDate")
	private Date mtdCalculatedDate;
	
	@JsonProperty("ltdCalculatedDate")
	private Date ltdCalculatedDate;
	
	@JsonProperty("ardCalculatedDate")
	private Date ardCalculatedDate;
	
	@JsonProperty("crdCalculatedDate")
	private Date crdCalculatedDate;
	
	@JsonProperty("pedCalculatedDate")
	private Date pedCalculatedDate;
	
	@JsonProperty("apdCalculatedDate")
	private Date apdCalculatedDate;
	
	@JsonProperty("npdCalculatedDate")
	private Date npdCalculatedDate;
	
	@JsonProperty("ledCalculatedDate")
	private Date ledCalculatedDate;
	
	@JsonProperty("sedCalculatedDate")
	private Date sedCalculatedDate;
	
	@JsonProperty("prrdCalculatedDate")
	private Date prrdCalculatedDate;
	
	@JsonProperty("tariffCalculatedDate")
	private Date tariffCalculatedDate;
	
	private String remissionElg;
	private String sentenceTerm;

	
	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
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

	public Integer getAggregateTerm() {
		return aggregateTerm;
	}

	public void setAggregateTerm(Integer aggregateTerm) {
		this.aggregateTerm = aggregateTerm;
	}

	public Integer getAggregateAdjustDays() {
		return aggregateAdjustDays;
	}

	public void setAggregateAdjustDays(Integer aggregateAdjustDays) {
		this.aggregateAdjustDays = aggregateAdjustDays;
	}

	public Date getSed() {
		return sed;
	}

	public void setSed(Date sed) {
		this.sed = sed;
	}

	public Integer getDaysToArdCrd() {
		return daysToArdCrd;
	}

	public void setDaysToArdCrd(Integer daysToArdCrd) {
		this.daysToArdCrd = daysToArdCrd;
	}

	public Date getArdCrdDate() {
		return ardCrdDate;
	}

	public void setArdCrdDate(Date ardCrdDate) {
		this.ardCrdDate = ardCrdDate;
	}

	public Date getHdcedCalculatedDate() {
		return hdcedCalculatedDate;
	}

	public void setHdcedCalculatedDate(Date hdcedCalculatedDate) {
		this.hdcedCalculatedDate = hdcedCalculatedDate;
	}

	public Date getEtdCalculatedDate() {
		return etdCalculatedDate;
	}

	public void setEtdCalculatedDate(Date etdCalculatedDate) {
		this.etdCalculatedDate = etdCalculatedDate;
	}

	public Date getMtdCalculatedDate() {
		return mtdCalculatedDate;
	}

	public void setMtdCalculatedDate(Date mtdCalculatedDate) {
		this.mtdCalculatedDate = mtdCalculatedDate;
	}

	public Date getLtdCalculatedDate() {
		return ltdCalculatedDate;
	}

	public void setLtdCalculatedDate(Date ltdCalculatedDate) {
		this.ltdCalculatedDate = ltdCalculatedDate;
	}

	public Date getArdCalculatedDate() {
		return ardCalculatedDate;
	}

	public void setArdCalculatedDate(Date ardCalculatedDate) {
		this.ardCalculatedDate = ardCalculatedDate;
	}

	public Date getCrdCalculatedDate() {
		return crdCalculatedDate;
	}

	public void setCrdCalculatedDate(Date crdCalculatedDate) {
		this.crdCalculatedDate = crdCalculatedDate;
	}

	public Date getPedCalculatedDate() {
		return pedCalculatedDate;
	}

	public void setPedCalculatedDate(Date pedCalculatedDate) {
		this.pedCalculatedDate = pedCalculatedDate;
	}

	public Date getApdCalculatedDate() {
		return apdCalculatedDate;
	}

	public void setApdCalculatedDate(Date apdCalculatedDate) {
		this.apdCalculatedDate = apdCalculatedDate;
	}

	public Date getNpdCalculatedDate() {
		return npdCalculatedDate;
	}

	public void setNpdCalculatedDate(Date npdCalculatedDate) {
		this.npdCalculatedDate = npdCalculatedDate;
	}

	public Date getLedCalculatedDate() {
		return ledCalculatedDate;
	}

	public void setLedCalculatedDate(Date ledCalculatedDate) {
		this.ledCalculatedDate = ledCalculatedDate;
	}

	public Date getSedCalculatedDate() {
		return sedCalculatedDate;
	}

	public void setSedCalculatedDate(Date sedCalculatedDate) {
		this.sedCalculatedDate = sedCalculatedDate;
	}

	public Date getPrrdCalculatedDate() {
		return prrdCalculatedDate;
	}

	public void setPrrdCalculatedDate(Date prrdCalculatedDate) {
		this.prrdCalculatedDate = prrdCalculatedDate;
	}

	public Date getTariffCalculatedDate() {
		return tariffCalculatedDate;
	}

	public void setTariffCalculatedDate(Date tariffCalculatedDate) {
		this.tariffCalculatedDate = tariffCalculatedDate;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getRemissionElg() {
		return remissionElg;
	}

	public void setRemissionElg(String remissionElg) {
		this.remissionElg = remissionElg;
	}

	public String getSentenceTerm() {
		return sentenceTerm;
	}

	public void setSentenceTerm(String sentenceTerm) {
		this.sentenceTerm = sentenceTerm;
	}	
	
	
}
