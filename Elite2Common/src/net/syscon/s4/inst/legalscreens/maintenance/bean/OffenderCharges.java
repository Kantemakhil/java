package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderCharges extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long offenderBookId;

	private Long offenderChargeId;

	private String statuteCode;

	private String offenceCode;

	private Long noOfOffences;

	private Date offenceDate;

	private Date offenceRangeDate;

	private String pleaCode;

	private Long propertyValue;

	private Long totalPropertyValue;

	private String cjitOffenceCode1;

	private String cjitOffenceCode2;

	private String cjitOffenceCode3;

	private String chargeStatus;

	private String createUserId;

	private String modifyUserId;

	private Date modifyDatetime;

	private Date createDatetime;

	private String resultCode1;

	private String resultCode2;

	private String resultCode1Indicator;

	private String resultCode2Indicator;

	private Long caseId;

	private String mostSeriousFlag;

	private Long chargeSeq;

	private Long orderId;

	private Long lidsOffenceNumber;

	private String offenceType;

	private String sealFlag;

	private String offenceDescription;

	private String complicityTypeDesc;

	private String offenceTypeDescription;

	private String caseInfoNumber;

	private BigDecimal eventId;

	private String resultCodeDesc;

	private Integer requestSeq;

	private String chargeInfoNumber;

	private String applyFlag;

	private String offenceApplyFlag;
	
	private String resultCode1Desc;

	

	private String resultCode2Desc;
	
	private Long sentenceSeq;
	
	private Long checkSum;
	

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getResultCode1Desc() {
		return resultCode1Desc;
	}

	public void setResultCode1Desc(String resultCode1Desc) {
		this.resultCode1Desc = resultCode1Desc;
	}
	/**
	 * @return the offenderChargeId
	 */
	public Long getOffenderChargeId() {
		return offenderChargeId;
	}

	/**
	 * @param offenderChargeId
	 *            the offenderChargeId to set
	 */
	public void setOffenderChargeId(final Long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}

	/**
	 * @return the statuteCode
	 */
	public String getStatuteCode() {
		return statuteCode;
	}

	public String getResultCode2Desc() {
		return resultCode2Desc;
	}

	public void setResultCode2Desc(String resultCode2Desc) {
		this.resultCode2Desc = resultCode2Desc;
	}
	/**
	 * @param statuteCode
	 *            the statuteCode to set
	 */
	public void setStatuteCode(final String statuteCode) {
		this.statuteCode = statuteCode;
	}

	/**
	 * @return the offenceCode
	 */
	public String getOffenceCode() {
		return offenceCode;
	}

	/**
	 * @param offenceCode
	 *            the offenceCode to set
	 */
	public void setOffenceCode(final String offenceCode) {
		this.offenceCode = offenceCode;
	}

	/**
	 * @return the noOfOffences
	 */
	public Long getNoOfOffences() {
		return noOfOffences;
	}

	/**
	 * @param noOfOffences
	 *            the noOfOffences to set
	 */
	public void setNoOfOffences(final Long noOfOffences) {
		this.noOfOffences = noOfOffences;
	}

	/**
	 * @return the offenceDate
	 */
	public Date getOffenceDate() {
		return offenceDate;
	}

	/**
	 * @param offenceDate
	 *            the offenceDate to set
	 */
	public void setOffenceDate(final Date offenceDate) {
		this.offenceDate = offenceDate;
	}

	/**
	 * @return the offenceRangeDate
	 */
	public Date getOffenceRangeDate() {
		return offenceRangeDate;
	}

	/**
	 * @param offenceRangeDate
	 *            the offenceRangeDate to set
	 */
	public void setOffenceRangeDate(final Date offenceRangeDate) {
		this.offenceRangeDate = offenceRangeDate;
	}

	/**
	 * @return the pleaCode
	 */
	public String getPleaCode() {
		return pleaCode;
	}

	/**
	 * @param pleaCode
	 *            the pleaCode to set
	 */
	public void setPleaCode(final String pleaCode) {
		this.pleaCode = pleaCode;
	}

	/**
	 * @return the propertyValue
	 */
	public Long getPropertyValue() {
		return propertyValue;
	}

	/**
	 * @param propertyValue
	 *            the propertyValue to set
	 */
	public void setPropertyValue(final Long propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * @return the totalPropertyValue
	 */
	public Long getTotalPropertyValue() {
		return totalPropertyValue;
	}

	/**
	 * @param totalPropertyValue
	 *            the totalPropertyValue to set
	 */
	public void setTotalPropertyValue(final Long totalPropertyValue) {
		this.totalPropertyValue = totalPropertyValue;
	}

	/**
	 * @return the cjitOffenceCode1
	 */
	public String getCjitOffenceCode1() {
		return cjitOffenceCode1;
	}

	/**
	 * @param cjitOffenceCode1
	 *            the cjitOffenceCode1 to set
	 */
	public void setCjitOffenceCode1(final String cjitOffenceCode1) {
		this.cjitOffenceCode1 = cjitOffenceCode1;
	}

	/**
	 * @return the cjitOffenceCode2
	 */
	public String getCjitOffenceCode2() {
		return cjitOffenceCode2;
	}

	/**
	 * @param cjitOffenceCode2
	 *            the cjitOffenceCode2 to set
	 */
	public void setCjitOffenceCode2(final String cjitOffenceCode2) {
		this.cjitOffenceCode2 = cjitOffenceCode2;
	}

	/**
	 * @return the cjitOffenceCode3
	 */
	public String getCjitOffenceCode3() {
		return cjitOffenceCode3;
	}

	/**
	 * @param cjitOffenceCode3
	 *            the cjitOffenceCode3 to set
	 */
	public void setCjitOffenceCode3(final String cjitOffenceCode3) {
		this.cjitOffenceCode3 = cjitOffenceCode3;
	}

	/**
	 * @return the chargeStatus
	 */
	public String getChargeStatus() {
		return chargeStatus;
	}

	/**
	 * @param chargeStatus
	 *            the chargeStatus to set
	 */
	public void setChargeStatus(final String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the resultCode1
	 */
	public String getResultCode1() {
		return resultCode1;
	}

	/**
	 * @param resultCode1
	 *            the resultCode1 to set
	 */
	public void setResultCode1(final String resultCode1) {
		this.resultCode1 = resultCode1;
	}

	/**
	 * @return the resultCode2
	 */
	public String getResultCode2() {
		return resultCode2;
	}

	/**
	 * @param resultCode2
	 *            the resultCode2 to set
	 */
	public void setResultCode2(final String resultCode2) {
		this.resultCode2 = resultCode2;
	}

	/**
	 * @return the resultCode1Indicator
	 */
	public String getResultCode1Indicator() {
		return resultCode1Indicator;
	}

	/**
	 * @param resultCode1Indicator
	 *            the resultCode1Indicator to set
	 */
	public void setResultCode1Indicator(final String resultCode1Indicator) {
		this.resultCode1Indicator = resultCode1Indicator;
	}

	/**
	 * @return the resultCode2Indicator
	 */
	public String getResultCode2Indicator() {
		return resultCode2Indicator;
	}

	/**
	 * @param resultCode2Indicator
	 *            the resultCode2Indicator to set
	 */
	public void setResultCode2Indicator(final String resultCode2Indicator) {
		this.resultCode2Indicator = resultCode2Indicator;
	}

	/**
	 * @return the caseId
	 */
	public Long getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId
	 *            the caseId to set
	 */
	public void setCaseId(final Long caseId) {
		this.caseId = caseId;
	}

	/**
	 * @return the mostSeriousFlag
	 */
	public String getMostSeriousFlag() {
		return mostSeriousFlag;
	}

	/**
	 * @param mostSeriousFlag
	 *            the mostSeriousFlag to set
	 */
	public void setMostSeriousFlag(final String mostSeriousFlag) {
		this.mostSeriousFlag = mostSeriousFlag;
	}

	/**
	 * @return the chargeSeq
	 */
	public Long getChargeSeq() {
		return chargeSeq;
	}

	/**
	 * @param chargeSeq
	 *            the chargeSeq to set
	 */
	public void setChargeSeq(final Long chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(final Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the lidsOffenceNumber
	 */
	public Long getLidsOffenceNumber() {
		return lidsOffenceNumber;
	}

	/**
	 * @param lidsOffenceNumber
	 *            the lidsOffenceNumber to set
	 */
	public void setLidsOffenceNumber(final Long lidsOffenceNumber) {
		this.lidsOffenceNumber = lidsOffenceNumber;
	}

	/**
	 * @return the offenceType
	 */
	public String getOffenceType() {
		return offenceType;
	}

	/**
	 * @param offenceType
	 *            the offenceType to set
	 */
	public void setOffenceType(final String offenceType) {
		this.offenceType = offenceType;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOffenceDescription() {
		return offenceDescription;
	}

	public void setOffenceDescription(String offenceDescription) {
		this.offenceDescription = offenceDescription;
	}

	public String getComplicityTypeDesc() {
		return complicityTypeDesc;
	}

	public void setComplicityTypeDesc(String complicityTypeDesc) {
		this.complicityTypeDesc = complicityTypeDesc;
	}

	public String getOffenceTypeDescription() {
		return offenceTypeDescription;
	}

	public void setOffenceTypeDescription(String offenceTypeDescription) {
		this.offenceTypeDescription = offenceTypeDescription;
	}

	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}

	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	public BigDecimal getEventId() {
		return eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getResultCodeDesc() {
		return resultCodeDesc;
	}

	public void setResultCodeDesc(String resultCodeDesc) {
		this.resultCodeDesc = resultCodeDesc;
	}

	public Integer getRequestSeq() {
		return requestSeq;
	}

	public void setRequestSeq(Integer requestSeq) {
		this.requestSeq = requestSeq;
	}

	public String getChargeInfoNumber() {
		return chargeInfoNumber;
	}

	public void setChargeInfoNumber(String chargeInfoNumber) {
		this.chargeInfoNumber = chargeInfoNumber;
	}

	public String getApplyFlag() {
		return applyFlag;
	}

	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}

	public String getOffenceApplyFlag() {
		return offenceApplyFlag;
	}

	public void setOffenceApplyFlag(String offenceApplyFlag) {
		this.offenceApplyFlag = offenceApplyFlag;
	}

	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public Long getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(Long checkSum) {
		this.checkSum = checkSum;
	}

}
