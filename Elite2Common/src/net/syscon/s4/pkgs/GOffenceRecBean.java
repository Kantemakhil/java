package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GOffenceRecBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("eventId")
	private int eventId;
	
	@JsonProperty("offenderChargeId")
	private Long offenderChargeId;
	
	@JsonProperty("caseId")
	private Long caseId;
	
	@JsonProperty("offenceDescription")
	private String offenceDescription;
	
	@JsonProperty("offenceCode")
	private String offenceCode;
	
	@JsonProperty("statuteCode")
	private String statuteCode;
	
	@JsonProperty("mostSeriousFlag")
	private String mostSeriousFlag;
	
	@JsonProperty("pleaCode")
	private String pleaCode;
	
	@JsonProperty("propertyValue")
	private Long propertyValue;
	
	@JsonProperty("offenceDate")
	private Date offenceDate;
	
	@JsonProperty("offenceRangeDate")
	private Date offenceRangeDate;
	
	@JsonProperty("resultCode1Desc")
	private String resultCode1Desc;
	
	@JsonProperty("resultcode1")
	private String resultcode1;
	
	@JsonProperty("resultCode1Indicator")
	private String resultCode1Indicator;
	
	@JsonProperty("resultCode2Desc")
	private String resultCode2Desc;
	
	@JsonProperty("resultCode2")
	private String resultCode2;
	
	@JsonProperty("resultCode2Indicator")
	private String resultCode2Indicator;
	
	@JsonProperty("cjitOffenceCode1")
	private String cjitOffenceCode1;
	
	@JsonProperty("cjitOffenceCode2")
	private String cjitOffenceCode2;
	
	@JsonProperty("cjitOffenceCode3")
	private String cjitOffenceCode3;
	
	@JsonProperty("noOfOffences")
	private Long noOfOffences;
	
	@JsonProperty("totalPropertyValue")
	private Long totalPropertyValue;
	
	@JsonProperty("chargeStatus")
	private String chargeStatus;
	
	@JsonProperty("applyFlag")
	private String applyFlag;
	
	@JsonProperty("checkSum")
	private Long checkSum;
	
	@JsonProperty("offenceType")
	private String offenceType;
	
	@JsonProperty("createUserId")
	private String  createUserId;
		
	

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the offenderChargeId
	 */
	public Long getOffenderChargeId() {
		return offenderChargeId;
	}

	/**
	 * @param offenderChargeId the offenderChargeId to set
	 */
	public void setOffenderChargeId(Long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}

	/**
	 * @return the caseId
	 */
	public Long getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	/**
	 * @return the offenceDescription
	 */
	public String getOffenceDescription() {
		return offenceDescription;
	}

	/**
	 * @param offenceDescription the offenceDescription to set
	 */
	public void setOffenceDescription(String offenceDescription) {
		this.offenceDescription = offenceDescription;
	}

	/**
	 * @return the offenceCode
	 */
	public String getOffenceCode() {
		return offenceCode;
	}

	/**
	 * @param offenceCode the offenceCode to set
	 */
	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}

	/**
	 * @return the statuteCode
	 */
	public String getStatuteCode() {
		return statuteCode;
	}

	/**
	 * @param statuteCode the statuteCode to set
	 */
	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}

	/**
	 * @return the mostSeriousFlag
	 */
	public String getMostSeriousFlag() {
		return mostSeriousFlag;
	}

	/**
	 * @param mostSeriousFlag the mostSeriousFlag to set
	 */
	public void setMostSeriousFlag(String mostSeriousFlag) {
		this.mostSeriousFlag = mostSeriousFlag;
	}

	/**
	 * @return the pleaCode
	 */
	public String getPleaCode() {
		return pleaCode;
	}

	/**
	 * @param pleaCode the pleaCode to set
	 */
	public void setPleaCode(String pleaCode) {
		this.pleaCode = pleaCode;
	}

	/**
	 * @return the propertyValue
	 */
	public Long getPropertyValue() {
		return propertyValue;
	}

	/**
	 * @param propertyValue the propertyValue to set
	 */
	public void setPropertyValue(Long propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * @return the offenceDate
	 */
	public Date getOffenceDate() {
		return offenceDate;
	}

	/**
	 * @param offenceDate the offenceDate to set
	 */
	public void setOffenceDate(Date offenceDate) {
		this.offenceDate = offenceDate;
	}

	/**
	 * @return the offenceRangeDate
	 */
	public Date getOffenceRangeDate() {
		return offenceRangeDate;
	}

	/**
	 * @param offenceRangeDate the offenceRangeDate to set
	 */
	public void setOffenceRangeDate(Date offenceRangeDate) {
		this.offenceRangeDate = offenceRangeDate;
	}

	/**
	 * @return the resultCode1Desc
	 */
	public String getResultCode1Desc() {
		return resultCode1Desc;
	}

	/**
	 * @param resultCode1Desc the resultCode1Desc to set
	 */
	public void setResultCode1Desc(String resultCode1Desc) {
		this.resultCode1Desc = resultCode1Desc;
	}

	/**
	 * @return the resultcode1
	 */
	public String getResultcode1() {
		return resultcode1;
	}

	/**
	 * @param resultcode1 the resultcode1 to set
	 */
	public void setResultcode1(String resultcode1) {
		this.resultcode1 = resultcode1;
	}

	/**
	 * @return the resultCode1Indicator
	 */
	public String getResultCode1Indicator() {
		return resultCode1Indicator;
	}

	/**
	 * @param resultCode1Indicator the resultCode1Indicator to set
	 */
	public void setResultCode1Indicator(String resultCode1Indicator) {
		this.resultCode1Indicator = resultCode1Indicator;
	}

	/**
	 * @return the resultCode2Desc
	 */
	public String getResultCode2Desc() {
		return resultCode2Desc;
	}

	/**
	 * @param resultCode2Desc the resultCode2Desc to set
	 */
	public void setResultCode2Desc(String resultCode2Desc) {
		this.resultCode2Desc = resultCode2Desc;
	}

	/**
	 * @return the resultCode2
	 */
	public String getResultCode2() {
		return resultCode2;
	}

	/**
	 * @param resultCode2 the resultCode2 to set
	 */
	public void setResultCode2(String resultCode2) {
		this.resultCode2 = resultCode2;
	}

	/**
	 * @return the resultCode2Indicator
	 */
	public String getResultCode2Indicator() {
		return resultCode2Indicator;
	}

	/**
	 * @param resultCode2Indicator the resultCode2Indicator to set
	 */
	public void setResultCode2Indicator(String resultCode2Indicator) {
		this.resultCode2Indicator = resultCode2Indicator;
	}

	/**
	 * @return the cjitOffenceCode1
	 */
	public String getCjitOffenceCode1() {
		return cjitOffenceCode1;
	}

	/**
	 * @param cjitOffenceCode1 the cjitOffenceCode1 to set
	 */
	public void setCjitOffenceCode1(String cjitOffenceCode1) {
		this.cjitOffenceCode1 = cjitOffenceCode1;
	}

	/**
	 * @return the cjitOffenceCode2
	 */
	public String getCjitOffenceCode2() {
		return cjitOffenceCode2;
	}

	/**
	 * @param cjitOffenceCode2 the cjitOffenceCode2 to set
	 */
	public void setCjitOffenceCode2(String cjitOffenceCode2) {
		this.cjitOffenceCode2 = cjitOffenceCode2;
	}

	/**
	 * @return the cjitOffenceCode3
	 */
	public String getCjitOffenceCode3() {
		return cjitOffenceCode3;
	}

	/**
	 * @param cjitOffenceCode3 the cjitOffenceCode3 to set
	 */
	public void setCjitOffenceCode3(String cjitOffenceCode3) {
		this.cjitOffenceCode3 = cjitOffenceCode3;
	}

	/**
	 * @return the noOfOffences
	 */
	public Long getNoOfOffences() {
		return noOfOffences;
	}

	/**
	 * @param noOfOffences the noOfOffences to set
	 */
	public void setNoOfOffences(Long noOfOffences) {
		this.noOfOffences = noOfOffences;
	}

	/**
	 * @return the totalPropertyValue
	 */
	public Long getTotalPropertyValue() {
		return totalPropertyValue;
	}

	/**
	 * @param totalPropertyValue the totalPropertyValue to set
	 */
	public void setTotalPropertyValue(Long totalPropertyValue) {
		this.totalPropertyValue = totalPropertyValue;
	}

	/**
	 * @return the chargeStatus
	 */
	public String getChargeStatus() {
		return chargeStatus;
	}

	/**
	 * @param chargeStatus the chargeStatus to set
	 */
	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	/**
	 * @return the applyFlag
	 */
	public String getApplyFlag() {
		return applyFlag;
	}

	/**
	 * @param applyFlag the applyFlag to set
	 */
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}

	/**
	 * @return the checkSum
	 */
	public Long getCheckSum() {
		return checkSum;
	}

	/**
	 * @param checkSum the checkSum to set
	 */
	public void setCheckSum(Long checkSum) {
		this.checkSum = checkSum;
	}

	/**
	 * @return the offenceType
	 */
	public String getOffenceType() {
		return offenceType;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @param offenceType the offenceType to set
	 */
	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}
	
	
}

