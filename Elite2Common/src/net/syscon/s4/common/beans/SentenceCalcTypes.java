package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the SENTENCE_CALC_TYPES database table.
 * 
 */
public class SentenceCalcTypes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

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

	private String sentenceType;
	private String aggregateFlag;
	private String vclObligationFlag;
	private String hoursSummaryFlag;;
	private String supervisionExpiryDateFlag;
	private String generateHours;
	private String sentenceCalc;
    private Integer expiryMonths; 
    private Integer programHoursMin; 
    private Integer programHoursMax; 
    private Integer workHoursMin; 
    private Integer workHoursMax;
    private String instDescription;
    private String sanctionsFlag;
    private String chargesFlag;
    
	public String getAggregateFlag() {
		return aggregateFlag;
	}

	public void setAggregateFlag(final String aggregateFlag) {
		this.aggregateFlag = aggregateFlag;
	}

	public String getVclObligationFlag() {
		return vclObligationFlag;
	}

	public void setVclObligationFlag(final String vclObligationFlag) {
		this.vclObligationFlag = vclObligationFlag;
	}

	public String getHoursSummaryFlag() {
		return hoursSummaryFlag;
	}

	public void setHoursSummaryFlag(final String hoursSummaryFlag) {
		this.hoursSummaryFlag = hoursSummaryFlag;
	}

	public String getSupervisionExpiryDateFlag() {
		return supervisionExpiryDateFlag;
	}

	public void setSupervisionExpiryDateFlag(final String supervisionExpiryDateFlag) {
		this.supervisionExpiryDateFlag = supervisionExpiryDateFlag;
	}

	public String getGenerateHours() {
		return generateHours;
	}

	public void setGenerateHours(final String generateHours) {
		this.generateHours = generateHours;
	}

	public String getSentenceCalc() {
		return sentenceCalc;
	}

	public void setSentenceCalc(final String sentenceCalc) {
		this.sentenceCalc = sentenceCalc;
	}

		public String getInstDescription() {
		return instDescription;
	}

	public void setInstDescription(final String instDescription) {
		this.instDescription = instDescription;
	}

	// bi-directional many-to-one association to SentenceTerm
	private List<SentenceTerms> sentenceTerms;

	// bi-directional many-to-one association to SentenceUpdateReason
	private List<SentenceUpdateReasons> sentenceUpdateReasons;

	private String sentenceCategory;

	private String sentenceCalcType;

	public SentenceCalcTypes() {
	}

	public Integer getExpiryMonths() {
		return expiryMonths;
	}

	public void setExpiryMonths(final Integer expiryMonths) {
		this.expiryMonths = expiryMonths;
	}

	public Integer getProgramHoursMin() {
		return programHoursMin;
	}

	public void setProgramHoursMin(final Integer programHoursMin) {
		this.programHoursMin = programHoursMin;
	}

	public Integer getProgramHoursMax() {
		return programHoursMax;
	}

	public void setProgramHoursMax(final Integer programHoursMax) {
		this.programHoursMax = programHoursMax;
	}

	public Integer getWorkHoursMin() {
		return workHoursMin;
	}

	public void setWorkHoursMin(final Integer workHoursMin) {
		this.workHoursMin = workHoursMin;
	}

	public Integer getWorkHoursMax() {
		return workHoursMax;
	}

	public void setWorkHoursMax(final Integer workHoursMax) {
		this.workHoursMax = workHoursMax;
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

	
	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
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

	public String getFunctionType() {
		return this.functionType;
	}

	public void setFunctionType(final String functionType) {
		this.functionType = functionType;
	}

	public String getHeaderLabel() {
		return this.headerLabel;
	}

	public void setHeaderLabel(final String headerLabel) {
		this.headerLabel = headerLabel;
	}

	public BigDecimal getHeaderSeq() {
		return this.headerSeq;
	}

	public void setHeaderSeq(final BigDecimal headerSeq) {
		this.headerSeq = headerSeq;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getProgramMethod() {
		return this.programMethod;
	}

	public void setProgramMethod(final String programMethod) {
		this.programMethod = programMethod;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSentenceType() {
		return this.sentenceType;
	}

	public void setSentenceType(final String sentenceType) {
		this.sentenceType = sentenceType;
	}

	public List<SentenceTerms> getSentenceTerms() {
		return this.sentenceTerms;
	}

	public void setSentenceTerms(final List<SentenceTerms> sentenceTerms) {
		this.sentenceTerms = sentenceTerms;
	}

	public SentenceTerms addSentenceTerm(final SentenceTerms sentenceTerm) {
		getSentenceTerms().add(sentenceTerm);
		sentenceTerm.setSentenceCalcTypesBean(this);

		return sentenceTerm;
	}

	public SentenceTerms removeSentenceTerm(final SentenceTerms sentenceTerm) {
		getSentenceTerms().remove(sentenceTerm);
		sentenceTerm.setSentenceCalcTypesBean(null);

		return sentenceTerm;
	}

	public List<SentenceUpdateReasons> getSentenceUpdateReasons() {
		return this.sentenceUpdateReasons;
	}

	public void setSentenceUpdateReasons(
			final List<SentenceUpdateReasons> sentenceUpdateReasons) {
		this.sentenceUpdateReasons = sentenceUpdateReasons;
	}

	public SentenceUpdateReasons addSentenceUpdateReason(
			final SentenceUpdateReasons sentenceUpdateReason) {
		getSentenceUpdateReasons().add(sentenceUpdateReason);
		sentenceUpdateReason.setSentenceCalcTypesBean(this);

		return sentenceUpdateReason;
	}

	public SentenceUpdateReasons removeSentenceUpdateReason(
			final SentenceUpdateReasons sentenceUpdateReason) {
		getSentenceUpdateReasons().remove(sentenceUpdateReason);
		sentenceUpdateReason.setSentenceCalcTypesBean(null);

		return sentenceUpdateReason;
	}

	public String getSanctionsFlag() {
		return sanctionsFlag;
	}

	public void setSanctionsFlag(String sanctionsFlag) {
		this.sanctionsFlag = sanctionsFlag;
	}

	public String getChargesFlag() {
		return chargesFlag;
	}

	public void setChargesFlag(String chargesFlag) {
		this.chargesFlag = chargesFlag;
	}

}
