package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffensesOutcome  extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("offenderId")
	private Long offenderId;
	@JsonProperty("eventId")
	private Long eventId; 
	@JsonProperty("offenderChargeId")
	private Long offenderChargeId;
	@JsonProperty("caseId")
	private Long caseId;
	@JsonProperty("offenseDescription")
	private String offenseDescription;
	@JsonProperty("offenceCode")
	private String offenceCode;
	@JsonProperty("statuteCode")
	private String statuteCode;
	@JsonProperty("mostseriousflag")
	private String mostseriousflag;
	@JsonProperty("plea")
	private String plea;
	@JsonProperty("propertyValue")
	private Long propertyValue;
	@JsonProperty("offenseDate")
	private Date offenseDate;
	@JsonProperty("range")
	private Date range;
	@JsonProperty("resultcode1desc")
	private String resultcode1desc;
	@JsonProperty("resultcode1")
	private String resultcode1;
	@JsonProperty("resultcode1indicator")
	private String resultcode1indicator;
	@JsonProperty("resultcode2desc")
	private String resultcode2desc;
	@JsonProperty("resultcode2")
	private String resultcode2;
	@JsonProperty("resultcode2indicator")
	private String resultcode2indicator;
	@JsonProperty("cjitoffencecode1")
	private String cjitoffencecode1;
	@JsonProperty("cjitoffencecode2")
	private String cjitoffencecode2;
	@JsonProperty("cjitoffencecode3")
	private String cjitoffencecode3;
	@JsonProperty("noofoffences")
	private Long noofoffences;
	@JsonProperty("totalpropertyvalue")
	private Long totalpropertyvalue;
	@JsonProperty("chargeStatus")
	private String chargeStatus;
	@JsonProperty("applyflag")
	private String applyflag;
	@JsonProperty("checksum")
	private Integer checksum;
	@JsonProperty("offenseType")
	private String offenseType;
	
	@JsonProperty("sentenceSeq")
	private Long sentenceSeq;
	@JsonProperty("result")
	private String result;
	@JsonProperty("disposition")
	private String disposition;
	@JsonProperty("offencedescription")
	private String offencedescription;
	@JsonProperty("button")
	private String button;
	@JsonProperty("apply")
	private Boolean apply;
	@JsonProperty("offense")
	private String offense;
	@JsonProperty("commitFlag")
	private String commitFlag;
	@JsonProperty("category")
	private String category;
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	@JsonProperty("sentenceType")
	private String sentenceType;
	@JsonProperty("offenceType")
	private String offenceType;
	@JsonProperty("chargeInfoNumber")
	private String chargeInfoNumber;
	@JsonProperty("complicityTypeDesc")
	private String complicityTypeDesc;
	@JsonProperty("lpcComplicityTypeCode")
	private String lpcComplicityTypeCode;
	@JsonProperty("offenceRangeDate")
	private Date offenceRangeDate;
	@JsonProperty("vclFlag")
	private String vclFlag;
	@JsonProperty("dummyCaseId")
	private Integer dummyCaseId;
	@JsonProperty("dummyEventId")
	private Integer dummyEventId;
	@JsonProperty("dummySentenceSeq")
	private Integer dummySentenceSeq;
	@JsonProperty("levyAmount")
	private Long levyAmount;
	@JsonProperty("countNumber")
	private Integer countNumber;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("applyFlagDbVal")
	private String applyFlagDbVal;
	@JsonProperty("vclFlagDbVal")
	private String vclFlagDbVal;
	
	@JsonProperty("expectedReleaseDate")
	private String expectedReleaseDate;
	
	@JsonProperty("paroleEligibilityDate")
	private String paroleEligibilityDate;
	
	@JsonProperty("remissionEligibilityDate")
	private String remissionEligibilityDate;
	
	@JsonProperty("latestReleaseDate")
	private String latestReleaseDate;
	
	@JsonProperty("custodyStatus")
	private String custodyStatus;
	
	@JsonProperty("confirmedReleaseDate")
	private String confirmedReleaseDate;
	
	@JsonProperty("value")
	private String value;
	
	@JsonProperty("dateType")
	private String dateType;
	
	@JsonProperty("override")
	private String override;
	
	@JsonProperty("code")
	private String code;
	

	public String getApplyFlagDbVal() {
		return applyFlagDbVal;
	}

	public void setApplyFlagDbVal(String applyFlagDbVal) {
		this.applyFlagDbVal = applyFlagDbVal;
	}

	public String getVclFlagDbVal() {
		return vclFlagDbVal;
	}

	public void setVclFlagDbVal(String vclFlagDbVal) {
		this.vclFlagDbVal = vclFlagDbVal;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Long getLevyAmount() {
		return levyAmount;
	}

	public void setLevyAmount(Long levyAmount) {
		this.levyAmount = levyAmount;
	}

	public Integer getCountNumber() {
		return countNumber;
	}

	public void setCountNumber(Integer countNumber) {
		this.countNumber = countNumber;
	}

	public Integer getDummyEventId() {
		return dummyEventId;
	}

	public void setDummyEventId(Integer dummyEventId) {
		this.dummyEventId = dummyEventId;
	}
	
	public String getOffenceType() {
		return offenceType;
	}
	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}
	public String getChargeInfoNumber() {
		return chargeInfoNumber;
	}
	public void setChargeInfoNumber(String chargeInfoNumber) {
		this.chargeInfoNumber = chargeInfoNumber;
	}
	public String getComplicityTypeDesc() {
		return complicityTypeDesc;
	}
	public void setComplicityTypeDesc(String complicityTypeDesc) {
		this.complicityTypeDesc = complicityTypeDesc;
	}
	public String getLpcComplicityTypeCode() {
		return lpcComplicityTypeCode;
	}
	public void setLpcComplicityTypeCode(String lpcComplicityTypeCode) {
		this.lpcComplicityTypeCode = lpcComplicityTypeCode;
	}
	public Date getOffenceRangeDate() {
		return offenceRangeDate;
	}
	public void setOffenceRangeDate(Date offenceRangeDate) {
		this.offenceRangeDate = offenceRangeDate;
	}
	public String getVclFlag() {
		return vclFlag;
	}
	public void setVclFlag(String vclFlag) {
		this.vclFlag = vclFlag;
	}
	public String getOffenceCode() {
		return offenceCode;
	}
	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}

	

	/**
	 * 
	 * @return
	 */
	public Boolean getApply() {
		return apply;
	}

	/**
	 * 
	 * @param apply
	 */
	public void setApply(Boolean apply) {
		this.apply = apply;
	}

	/**
	 * 
	 * @return
	 */
	public String getOffense() {
		return offense;
	}

	/**
	 * 
	 * @param offense
	 */
	public void setOffense(String offense) {
		this.offense = offense;
	}
	
	public Long getOffenderChargeId() {
		return offenderChargeId;
	}

	public void setOffenderChargeId(Long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}

	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOffenseDescription() {
		return offenseDescription;
	}

	/**
	 * 
	 * @param offenseDescription
	 */
	public void setOffenseDescription(String offenseDescription) {
		this.offenseDescription = offenseDescription;
	}

	/**
	 * 
	 * @return
	 */
	public String getOffenseType() {
		return offenseType;
	}

	/**
	 * 
	 * @param offenseType
	 */
	public void setOffenseType(String offenseType) {
		this.offenseType = offenseType;
	}

	/**
	 * 
	 * @return
	 */
	public String getPlea() {
		return plea;
	}

	/**
	 * 
	 * @param plea
	 */
	public void setPlea(String plea) {
		this.plea = plea;
	}

	/**
	 * 
	 * @return
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}

	/**
	 * 
	 * @param offenseDate
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}

	/**
	 * 
	 * @return
	 */
	public Date getRange() {
		return range;
	}

	/**
	 * 
	 * @param range
	 */
	public void setRange(Date range) {
		this.range = range;
	}

	/**
	 * 
	 * @return
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 
	 * @return
	 */
	public String getDisposition() {
		return disposition;
	}

	/**
	 * 
	 * @param unknownField
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	
	public String getStatuteCode() {
		return statuteCode;
	}

	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getOffencedescription() {
		return offencedescription;
	}

	public void setOffencedescription(String offencedescription) {
		this.offencedescription = offencedescription;
	}

	/*
	 * public String getResultbutton() { return resultbutton; }
	 * 
	 * public void setResultbutton(String resultbutton) { this.resultbutton =
	 * resultbutton; }
	 */

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCase_id(Long caseId) {
		this.caseId = caseId;
	}

	public String getMostseriousflag() {
		return mostseriousflag;
	}

	public void setMostseriousflag(String mostseriousflag) {
		this.mostseriousflag = mostseriousflag;
	}

	public Long getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Long propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getResultcode1() {
		return resultcode1;
	}

	public void setResultcode1(String resultcode1) {
		this.resultcode1 = resultcode1;
	}

	public String getResultcode1desc() {
		return resultcode1desc;
	}

	public void setResultcode1desc(String resultcode1desc) {
		this.resultcode1desc = resultcode1desc;
	}

	public String getResultcode1indicator() {
		return resultcode1indicator;
	}

	public void setResultcode1indicator(String resultcode1indicator) {
		this.resultcode1indicator = resultcode1indicator;
	}

	public String getResultcode2desc() {
		return resultcode2desc;
	}

	public void setResultcode2desc(String resultcode2desc) {
		this.resultcode2desc = resultcode2desc;
	}

	public String getResultcode2() {
		return resultcode2;
	}

	public void setResultcode2(String resultcode2) {
		this.resultcode2 = resultcode2;
	}

	public String getResultcode2indicator() {
		return resultcode2indicator;
	}

	public void setResultcode2indicator(String resultcode2indicator) {
		this.resultcode2indicator = resultcode2indicator;
	}

	public String getCjitoffencecode1() {
		return cjitoffencecode1;
	}

	public void setCjitoffencecode1(String cjitoffencecode1) {
		this.cjitoffencecode1 = cjitoffencecode1;
	}

	public String getCjitoffencecode2() {
		return cjitoffencecode2;
	}

	public void setCjitoffencecode2(String cjitoffencecode2) {
		this.cjitoffencecode2 = cjitoffencecode2;
	}

	public String getCjitoffencecode3() {
		return cjitoffencecode3;
	}

	public void setCjitoffencecode3(String cjitoffencecode3) {
		this.cjitoffencecode3 = cjitoffencecode3;
	}

	public Long getNoofoffences() {
		return noofoffences;
	}

	public void setNoofoffences(Long noofoffences) {
		this.noofoffences = noofoffences;
	}

	public Long getTotalpropertyvalue() {
		return totalpropertyvalue;
	}

	public void setTotalpropertyvalue(Long totalpropertyvalue) {
		this.totalpropertyvalue = totalpropertyvalue;
	}

	public String getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public String getApplyflag() {
		return applyflag;
	}

	public void setApplyflag(String applyflag) {
		this.applyflag = applyflag;
	}

	public Integer getChecksum() {
		return checksum;
	}

	public void setChecksum(Integer checksum) {
		this.checksum = checksum;
	}
	public String getCommitFlag() {
		return commitFlag;
	}
	public void setCommitFlag(String commitFlag) {
		this.commitFlag = commitFlag;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSentenceCalcType() {
		return sentenceCalcType;
	}
	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}
	public String getSentenceType() {
		return sentenceType;
	}
	public void setSentenceType(String sentenceType) {
		this.sentenceType = sentenceType;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}	
	
	public Integer getDummyCaseId() {
		return dummyCaseId;
	}
	
	public void setDummyCaseId(Integer dummyCaseId) {
		this.dummyCaseId = dummyCaseId;
	}

	public Integer getDummySentenceSeq() {
		return dummySentenceSeq;
	}

	public void setDummySentenceSeq(Integer dummySentenceSeq) {
		this.dummySentenceSeq = dummySentenceSeq;
	}

	public String getExpectedReleaseDate() {
		return expectedReleaseDate;
	}

	public void setExpectedReleaseDate(String expectedReleaseDate) {
		this.expectedReleaseDate = expectedReleaseDate;
	}

	public String getParoleEligibilityDate() {
		return paroleEligibilityDate;
	}

	public void setParoleEligibilityDate(String paroleEligibilityDate) {
		this.paroleEligibilityDate = paroleEligibilityDate;
	}

	public String getRemissionEligibilityDate() {
		return remissionEligibilityDate;
	}

	public void setRemissionEligibilityDate(String remissionEligibilityDate) {
		this.remissionEligibilityDate = remissionEligibilityDate;
	}

	public String getLatestReleaseDate() {
		return latestReleaseDate;
	}

	public void setLatestReleaseDate(String latestReleaseDate) {
		this.latestReleaseDate = latestReleaseDate;
	}

	public String getCustodyStatus() {
		return custodyStatus;
	}

	public void setCustodyStatus(String custodyStatus) {
		this.custodyStatus = custodyStatus;
	}

	public String getConfirmedReleaseDate() {
		return confirmedReleaseDate;
	}

	public void setConfirmedReleaseDate(String confirmedReleaseDate) {
		this.confirmedReleaseDate = confirmedReleaseDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getOverride() {
		return override;
	}

	public void setOverride(String override) {
		this.override = override;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
