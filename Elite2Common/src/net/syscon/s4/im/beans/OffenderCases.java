package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderCases extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("caseId")
	@NotNull
	private Long caseId;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("agyLocId")
	@NotNull
	@Size(max = 6)
	private String agyLocId;

	@JsonProperty("beginDate")
	private Date beginDate;

	@JsonProperty("caseInfoNumber")
	private String caseInfoNumber;

	@JsonProperty("caseInfoPrefix")
	private String caseInfoPrefix;

	@JsonProperty("caseSeq")
	@NotNull
	private BigDecimal caseSeq;

	@JsonProperty("caseStatus")
	private String caseStatus;

	@JsonProperty("caseType")
	private String caseType;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("lidsCaseNumber")
	private BigDecimal lidsCaseNumber;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("statusUpdateComment")
	private String statusUpdateComment;

	@JsonProperty("statusUpdateDate")
	private Date statusUpdateDate;

	@JsonProperty("statusUpdateReason")
	private String statusUpdateReason;

	@JsonProperty("statusUpdateStaffId")
	private BigDecimal statusUpdateStaffId;

	@JsonProperty("victimLiaisonUnit")
	private String victimLiaisonUnit;
	
	private String offenceCode;
	
	private String offenceDescription;
	private String complicityTypeDesc;
	
	private String chargeId;
	private BigDecimal offenderChargeId;
	private String offenceType;
	private Date offenceDate;
	private long sentenceEventId;
	private String code;
	private String description;
	
	
	@JsonProperty("chargeInfoNumber")
	private String chargeInfoNumber;
	
	@JsonProperty("commitFlag")
	private String commitFlag;
	
	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Creates new OffenderCases class Object
	 */
	public OffenderCases() {
		// OffenderCases
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

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(final Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the caseInfoNumber
	 */
	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}

	/**
	 * @param caseInfoNumber
	 *            the caseInfoNumber to set
	 */
	public void setCaseInfoNumber(final String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	/**
	 * @return the caseInfoPrefix
	 */
	public String getCaseInfoPrefix() {
		return caseInfoPrefix;
	}

	/**
	 * @param caseInfoPrefix
	 *            the caseInfoPrefix to set
	 */
	public void setCaseInfoPrefix(final String caseInfoPrefix) {
		this.caseInfoPrefix = caseInfoPrefix;
	}

	/**
	 * @return the caseSeq
	 */
	public BigDecimal getCaseSeq() {
		return caseSeq;
	}

	/**
	 * @param caseSeq
	 *            the caseSeq to set
	 */
	public void setCaseSeq(final BigDecimal caseSeq) {
		this.caseSeq = caseSeq;
	}

	/**
	 * @return the caseStatus
	 */
	public String getCaseStatus() {
		return caseStatus;
	}

	/**
	 * @param caseStatus
	 *            the caseStatus to set
	 */
	public void setCaseStatus(final String caseStatus) {
		this.caseStatus = caseStatus;
	}

	/**
	 * @return the caseType
	 */
	public String getCaseType() {
		return caseType;
	}

	/**
	 * @param caseType
	 *            the caseType to set
	 */
	public void setCaseType(final String caseType) {
		this.caseType = caseType;
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
	 * @return the lidsCaseNumber
	 */
	public BigDecimal getLidsCaseNumber() {
		return lidsCaseNumber;
	}

	/**
	 * @param lidsCaseNumber
	 *            the lidsCaseNumber to set
	 */
	public void setLidsCaseNumber(final BigDecimal lidsCaseNumber) {
		this.lidsCaseNumber = lidsCaseNumber;
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
	 * @return the statusUpdateComment
	 */
	public String getStatusUpdateComment() {
		return statusUpdateComment;
	}

	/**
	 * @param statusUpdateComment
	 *            the statusUpdateComment to set
	 */
	public void setStatusUpdateComment(final String statusUpdateComment) {
		this.statusUpdateComment = statusUpdateComment;
	}

	/**
	 * @return the statusUpdateDate
	 */
	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}

	/**
	 * @param statusUpdateDate
	 *            the statusUpdateDate to set
	 */
	public void setStatusUpdateDate(final Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	/**
	 * @return the statusUpdateReason
	 */
	public String getStatusUpdateReason() {
		return statusUpdateReason;
	}

	/**
	 * @param statusUpdateReason
	 *            the statusUpdateReason to set
	 */
	public void setStatusUpdateReason(final String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	/**
	 * @return the statusUpdateStaffId
	 */
	public BigDecimal getStatusUpdateStaffId() {
		return statusUpdateStaffId;
	}

	/**
	 * @param statusUpdateStaffId
	 *            the statusUpdateStaffId to set
	 */
	public void setStatusUpdateStaffId(final BigDecimal statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	/**
	 * @return the victimLiaisonUnit
	 */
	public String getVictimLiaisonUnit() {
		return victimLiaisonUnit;
	}

	/**
	 * @param victimLiaisonUnit
	 *            the victimLiaisonUnit to set
	 */
	public void setVictimLiaisonUnit(final String victimLiaisonUnit) {
		this.victimLiaisonUnit = victimLiaisonUnit;
	}

	public String getOffenceCode() {
		return offenceCode;
	}

	public void setOffenceCode(final String offenceCode) {
		this.offenceCode = offenceCode;
	}

	public String getOffenceDescription() {
		return offenceDescription;
	}

	public void setOffenceDescription(final String offenceDescription) {
		this.offenceDescription = offenceDescription;
	}

	public String getComplicityTypeDesc() {
		return complicityTypeDesc;
	}

	public void setComplicityTypeDesc(final String complicityTypeDesc) {
		this.complicityTypeDesc = complicityTypeDesc;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(final String chargeId) {
		this.chargeId = chargeId;
	}

	

	public String getOffenceType() {
		return offenceType;
	}

	public void setOffenceType(final String offenceType) {
		this.offenceType = offenceType;
	}

	public Date getOffenceDate() {
		return offenceDate;
	}

	public long getSentenceEventId() {
		return sentenceEventId;
	}

	public void setSentenceEventId(final long sentenceEventId) {
		this.sentenceEventId = sentenceEventId;
	}

	public void setOffenceDate(final Date offenceDate) {
		this.offenceDate = offenceDate;
	}

	public BigDecimal getOffenderChargeId() {
		return offenderChargeId;
	}

	public void setOffenderChargeId(final BigDecimal offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}

	public String getChargeInfoNumber() {
		return chargeInfoNumber;
	}

	public void setChargeInfoNumber(final String chargeInfoNumber) {
		this.chargeInfoNumber = chargeInfoNumber;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCommitFlag() {
		return commitFlag;
	}

	public void setCommitFlag(String commitFlag) {
		this.commitFlag = commitFlag;
	}

}
