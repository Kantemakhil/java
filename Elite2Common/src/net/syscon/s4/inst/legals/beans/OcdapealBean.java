package net.syscon.s4.inst.legals.beans;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OcdapealBean extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("caseId")
	private Long caseId;
	@JsonProperty("offenderChargeId")
	private Long offenderChargeId;
	@JsonProperty("offenceDescription")
	private String offenceDescription;
	@JsonProperty("complicityTypeDesc")
	private String complicityTypeDesc;
	@JsonProperty("applyFlag")
	private String applyFlag;
	@JsonProperty("offenceCode")
	private String offenceCode;
	@JsonProperty("statuteCode")
	private String statuteCode;
	@JsonProperty("chargeInfoNumber")
	private String chargeInfoNumber;
	@JsonProperty("offenceDate")
	private Date offenceDate;
	@JsonProperty("dispositionCode")
	private String dispositionCode;
	@JsonProperty("convictionFlag")
	private String convictionFlag;
	@JsonProperty("offenceType")
	private String offenceType;
	@JsonProperty("resultCode1Desc")
	private String resultCode1Desc;
	@JsonProperty("resultCode1")
	private String resultCode1;
	@JsonProperty("resultCode1Indicator")
	private String resultCode1Indicator;
	@JsonProperty("offRes")
	private String offRes;
	@JsonProperty("eventId")
	private Long eventId;
	@JsonProperty("offenceDescriptionType")
	private String offenceDescriptionType;
	@JsonProperty("appealId")
	private Long appealId;
	@JsonProperty("bailApplyFlag")
	private String bailApplyFlag;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public Long getOffenderChargeId() {
		return offenderChargeId;
	}
	public void setOffenderChargeId(Long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
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
	public String getApplyFlag() {
		return applyFlag;
	}
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
	public String getOffenceCode() {
		return offenceCode;
	}
	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}
	public String getStatuteCode() {
		return statuteCode;
	}
	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}
	public String getChargeInfoNumber() {
		return chargeInfoNumber;
	}
	public void setChargeInfoNumber(String chargeInfoNumber) {
		this.chargeInfoNumber = chargeInfoNumber;
	}
	public Date getOffenceDate() {
		return offenceDate;
	}
	public void setOffenceDate(Date offenceDate) {
		this.offenceDate = offenceDate;
	}
	public String getDispositionCode() {
		return dispositionCode;
	}
	public void setDispositionCode(String dispositionCode) {
		this.dispositionCode = dispositionCode;
	}
	public String getConvictionFlag() {
		return convictionFlag;
	}
	public void setConvictionFlag(String convictionFlag) {
		this.convictionFlag = convictionFlag;
	}
	public String getOffenceType() {
		return offenceType;
	}
	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}
	public String getResultCode1Desc() {
		return resultCode1Desc;
	}
	public void setResultCode1Desc(String resultCode1Desc) {
		this.resultCode1Desc = resultCode1Desc;
	}
	public String getResultCode1() {
		return resultCode1;
	}
	public void setResultCode1(String resultCode1) {
		this.resultCode1 = resultCode1;
	}
	public String getResultCode1Indicator() {
		return resultCode1Indicator;
	}
	public void setResultCode1Indicator(String resultCode1Indicator) {
		this.resultCode1Indicator = resultCode1Indicator;
	}
	public String getOffRes() {
		return offRes;
	}
	public void setOffRes(String offRes) {
		this.offRes = offRes;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getOffenceDescriptionType() {
		return offenceDescriptionType;
	}
	public void setOffenceDescriptionType(String offenceDescriptionType) {
		this.offenceDescriptionType = offenceDescriptionType;
	}
	public Long getAppealId() {
		return appealId;
	}
	public void setAppealId(Long appealId) {
		this.appealId = appealId;
	}
	public String getBailApplyFlag() {
		return bailApplyFlag;
	}
	public void setBailApplyFlag(String bailApplyFlag) {
		this.bailApplyFlag = bailApplyFlag;
	}
	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}
	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	
	

	
}
