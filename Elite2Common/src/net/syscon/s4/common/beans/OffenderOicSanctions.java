package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

/**
 * Class OffenderOicSanctions
 * 
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderOicSanctions extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("sanctionSeq")
	private Integer sanctionSeq;

	@JsonProperty("oicSanctionCode")
	private String oicSanctionCode;

	@JsonProperty("compensationAmount")
	private Double compensationAmount;
	
	@JsonProperty("compensation")
	private String compensation;

	@JsonProperty("sanctionMonths")
	private Integer sanctionMonths;

	@JsonProperty("sanctionDays")
	private Integer sanctionDays;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("appealingDate")
	private Date appealingDate;

	@JsonProperty("consecutiveOffenderBookId")
	private Integer consecutiveOffenderBookId;

	@JsonProperty("consecutiveSanctionSeq")
	private Integer consecutiveSanctionSeq;

	@JsonProperty("oicHearingId")
	private Integer oicHearingId;

	@JsonProperty("status")
	private String status;

	@JsonProperty("offenderAdjustId")
	private Integer offenderAdjustId;

	@JsonProperty("resultSeq")
	private Integer resultSeq;

	@JsonProperty("createDatetime")
	private Timestamp createDatetime;
	
	private Long planSeq;
	
	private Long paroleHearingSeq;
	
	private Long scheduleId;
	
	private Integer paroleDecisionSeq;
	

	public Integer getParoleDecisionSeq() {
		return paroleDecisionSeq;
	}

	public void setParoleDecisionSeq(Integer paroleDecisionSeq) {
		this.paroleDecisionSeq = paroleDecisionSeq;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Long getParoleHearingSeq() {
		return paroleHearingSeq;
	}

	public void setParoleHearingSeq(Long paroleHearingSeq) {
		this.paroleHearingSeq = paroleHearingSeq;
	}

	public Long getPlanSeq() {
		return planSeq;
	}

	public void setPlanSeq(Long planSeq) {
		this.planSeq = planSeq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the compensation
	 */
	public String getCompensation() {
		return compensation;
	}

	/**
	 * @param compensation the compensation to set
	 */
	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}

	@JsonProperty("createUserID")
	private String createUserID;

	@JsonProperty("modifyDatetime")
	private Timestamp modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("statusDate")
	private Date statusDate;

	@JsonProperty("oicIncidentId")
	private Integer oicIncidentId;

	@JsonProperty("lidsSanctionNumber")
	private Integer lidsSanctionNumber;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("oicOffenceCode")
	@NotNull
	@Size(max = 12)
	private String oicOffenceCode;

	@JsonProperty("oicOffenceType")
	private String oicOffenceType;

	@JsonProperty("oicChargeId")
	private String oicChargeId;

	@JsonProperty("hearingDate")
	private Date hearingDate;

	@JsonProperty("hearingTime")
	private Date hearingTime;

	@JsonProperty("chargeSeq")
	@NotNull
	private Integer chargeSeq;
	@JsonProperty("errorMessage")
	private String errorMessage;
    
	@JsonProperty("description")
	@NotNull
	@Size(max = 40)
	private String description;
	
	@JsonProperty("constoicIncidentId")
	private Integer constoicIncidentId;
	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the sanctionSeq
	 */
	public Integer getSanctionSeq() {
		return sanctionSeq;
	}

	/**
	 * @param sanctionSeq
	 *            the sanctionSeq to set
	 */
	public void setSanctionSeq(final Integer sanctionSeq) {
		this.sanctionSeq = sanctionSeq;
	}

	/**
	 * @return the oicSanctionCode
	 */
	public String getOicSanctionCode() {
		return oicSanctionCode;
	}

	/**
	 * @param oicSanctionCode
	 *            the oicSanctionCode to set
	 */
	public void setOicSanctionCode(final String oicSanctionCode) {
		this.oicSanctionCode = oicSanctionCode;
	}

	/**
	 * @return the compensationAmount
	 */
	public Double getCompensationAmount() {
		return compensationAmount;
	}

	/**
	 * @param compensationAmount
	 *            the compensationAmount to set
	 */
	public void setCompensationAmount(final Double compensationAmount) {
		this.compensationAmount = compensationAmount;
	}

	/**
	 * @return the sanctionMonths
	 */
	public Integer getSanctionMonths() {
		return sanctionMonths;
	}

	/**
	 * @param sanctionMonths
	 *            the sanctionMonths to set
	 */
	public void setSanctionMonths(final Integer sanctionMonths) {
		this.sanctionMonths = sanctionMonths;
	}

	/**
	 * @return the sanctionDays
	 */
	public Integer getSanctionDays() {
		return sanctionDays;
	}

	/**
	 * @param sanctionDays
	 *            the sanctionDays to set
	 */
	public void setSanctionDays(final Integer sanctionDays) {
		this.sanctionDays = sanctionDays;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentTesx
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the appealingDate
	 */
	public Date getAppealingDate() {
		return appealingDate;
	}

	/**
	 * @param appealingDate
	 *            the appealingDate to set
	 */
	public void setAppealingDate(final Date appealingDate) {
		this.appealingDate = appealingDate;
	}

	/**
	 * @return the consecutiveOffenderBookId
	 */
	public Integer getConsecutiveOffenderBookId() {
		return consecutiveOffenderBookId;
	}

	/**
	 * @param consecutiveOffenderBookId
	 *            the consecutiveOffenderBookId to set
	 */
	public void setConsecutiveOffenderBookId(final Integer consecutiveOffenderBookId) {
		this.consecutiveOffenderBookId = consecutiveOffenderBookId;
	}

	/**
	 * @return the consecutiveSanctionSeq
	 */
	public Integer getConsecutiveSanctionSeq() {
		return consecutiveSanctionSeq;
	}

	/**
	 * @param consecutiveSanctionSeq
	 *            the consecutiveSanctionSeq to set
	 */
	public void setConsecutiveSanctionSeq(final Integer consecutiveSanctionSeq) {
		this.consecutiveSanctionSeq = consecutiveSanctionSeq;
	}

	/**
	 * @return the oicHearingId
	 */
	public Integer getOicHearingId() {
		return oicHearingId;
	}

	/**
	 * @param oicHearingId
	 *            the oicHearingId to set
	 */
	public void setOicHearingId(final Integer oicHearingId) {
		this.oicHearingId = oicHearingId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the constoicIncidentId
	 */
	public Integer getConstoicIncidentId() {
		return constoicIncidentId;
	}

	/**
	 * @param constoicIncidentId the constoicIncidentId to set
	 */
	public void setConstoicIncidentId(Integer constoicIncidentId) {
		this.constoicIncidentId = constoicIncidentId;
	}

	/**
	 * @return the offenderAdjustId
	 */
	public Integer getOffenderAdjustId() {
		return offenderAdjustId;
	}

	/**
	 * @param offenderAdjustId
	 *            the offenderAdjustId to set
	 */
	public void setOffenderAdjustId(final Integer offenderAdjustId) {
		this.offenderAdjustId = offenderAdjustId;
	}

	/**
	 * @return the resultSeq
	 */
	public Integer getResultSeq() {
		return resultSeq;
	}

	/**
	 * @param resultSeq
	 *            the resultSeq to set
	 */
	public void setResultSeq(final Integer resultSeq) {
		this.resultSeq = resultSeq;
	}

	/**
	 * @return the createDatetime
	 */
	public Timestamp getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}

	/**
	 * @param createUserID
	 *            the createUserID to set
	 */
	public void setCreateUserID(final String createUserID) {
		this.createUserID = createUserID;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Timestamp getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Timestamp modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
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
	 * @return the statusDate
	 */
	public Date getStatusDate() {
		return statusDate;
	}

	/**
	 * @param statusDate
	 *            the statusDate to set
	 */
	public void setStatusDate(final Date statusDate) {
		this.statusDate = statusDate;
	}

	/**
	 * @return the oicIncidentId
	 */
	public Integer getOicIncidentId() {
		return oicIncidentId;
	}

	/**
	 * @param oicIncidentId
	 *            the oicIncidentId to set
	 */
	public void setOicIncidentId(final Integer oicIncidentId) {
		this.oicIncidentId = oicIncidentId;
	}

	/**
	 * @return the lidsSanctionNumber
	 */
	public Integer getLidsSanctionNumber() {
		return lidsSanctionNumber;
	}

	/**
	 * @param lidsSanctionNumber
	 *            the lidsSanctionNumber to set
	 */
	public void setLidsSanctionNumber(final Integer lidsSanctionNumber) {
		this.lidsSanctionNumber = lidsSanctionNumber;
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
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the oicOffenceCode
	 */
	public final String getOicOffenceCode() {
		return oicOffenceCode;
	}

	/**
	 * @param oicOffenceCode the oicOffenceCode to set
	 */
	public final void setOicOffenceCode(final String oicOffenceCode) {
		this.oicOffenceCode = oicOffenceCode;
	}

	/**
	 * @return the oicOffenceType
	 */
	public final String getOicOffenceType() {
		return oicOffenceType;
	}

	/**
	 * @param oicOffenceType the oicOffenceType to set
	 */
	public final void setOicOffenceType(final String oicOffenceType) {
		this.oicOffenceType = oicOffenceType;
	}

	/**
	 * @return the oicChargeId
	 */
	public final String getOicChargeId() {
		return oicChargeId;
	}

	/**
	 * @param oicChargeId the oicChargeId to set
	 */
	public final void setOicChargeId(final String oicChargeId) {
		this.oicChargeId = oicChargeId;
	}

	/**
	 * @return the hearingDate
	 */
	public final Date getHearingDate() {
		return hearingDate;
	}

	/**
	 * @param hearingDate the hearingDate to set
	 */
	public final void setHearingDate(final Date hearingDate) {
		this.hearingDate = hearingDate;
	}

	/**
	 * @return the hearingTime
	 */
	public final Date getHearingTime() {
		return hearingTime;
	}

	/**
	 * @param hearingTime the hearingTime to set
	 */
	public final void setHearingTime(final Date hearingTime) {
		this.hearingTime = hearingTime;
	}

	/**
	 * @return the chargeSeq
	 */
	public final Integer getChargeSeq() {
		return chargeSeq;
	}

	/**
	 * @param chargeSeq the chargeSeq to set
	 */
	public final void setChargeSeq(final Integer chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param OFFENDER_BOOK_ID
	 *            OFFENDER_BOOK_ID to set
	 */

}