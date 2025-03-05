package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCas extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("caseId")
	private long caseId;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("beginDate")
	private Date beginDate;

	@JsonProperty("caseInfoNumber")
	private String caseInfoNumber;

	@JsonProperty("caseInfoPrefix")
	private String caseInfoPrefix;

	@JsonProperty("caseSeq")
	private BigDecimal caseSeq;

	@JsonProperty("caseStatus")
	private String caseStatus;

	@JsonProperty("caseType")
	private String caseType;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
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

	@JsonProperty("offenderAssociatedParties")
	private List<OffenderAssociatedParty> offenderAssociatedParties;

	@JsonProperty("offenderBooking")
	private OffenderBookings offenderBooking;

	@JsonProperty("offenderCase")
	private OffenderCas offenderCase;

	@JsonProperty("offenderCases")
	private List<OffenderCas> offenderCases;

	@JsonProperty("offenderCaseIdentifiers")
	private List<OffenderCaseIdentifier> offenderCaseIdentifiers;

	@JsonProperty("offenderCaseStatuses")
	private List<OffenderCaseStatus> offenderCaseStatuses;

	@JsonProperty("offenderCharges")
	private List<OffenderCharge> offenderCharges;

	/**
	 *
	 * @return
	 */
	public long getCaseId() {
		return caseId;
	}

	/**
	 *
	 * @param caseId
	 */
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	/**
	 *
	 * @return
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 *
	 * @param agyLocId
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 *
	 * @return
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 *
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}

	/**
	 *
	 * @param caseInfoNumber
	 */
	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseInfoPrefix() {
		return caseInfoPrefix;
	}

	/**
	 *
	 * @param caseInfoPrefix
	 */
	public void setCaseInfoPrefix(String caseInfoPrefix) {
		this.caseInfoPrefix = caseInfoPrefix;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCaseSeq() {
		return caseSeq;
	}

	/**
	 *
	 * @param caseSeq
	 */
	public void setCaseSeq(BigDecimal caseSeq) {
		this.caseSeq = caseSeq;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseStatus() {
		return caseStatus;
	}

	/**
	 *
	 * @param caseStatus
	 */
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseType() {
		return caseType;
	}

	/**
	 *
	 * @param caseType
	 */
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getLidsCaseNumber() {
		return lidsCaseNumber;
	}

	/**
	 *
	 * @param lidsCaseNumber
	 */
	public void setLidsCaseNumber(BigDecimal lidsCaseNumber) {
		this.lidsCaseNumber = lidsCaseNumber;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getStatusUpdateComment() {
		return statusUpdateComment;
	}

	/**
	 *
	 * @param statusUpdateComment
	 */
	public void setStatusUpdateComment(String statusUpdateComment) {
		this.statusUpdateComment = statusUpdateComment;
	}

	/**
	 *
	 * @return
	 */
	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}

	/**
	 *
	 * @param statusUpdateDate
	 */
	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	/**
	 *
	 * @return
	 */
	public String getStatusUpdateReason() {
		return statusUpdateReason;
	}

	/**
	 *
	 * @param statusUpdateReason
	 */
	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getStatusUpdateStaffId() {
		return statusUpdateStaffId;
	}

	/**
	 *
	 * @param statusUpdateStaffId
	 */
	public void setStatusUpdateStaffId(BigDecimal statusUpdateStaffId) {
		this.statusUpdateStaffId = statusUpdateStaffId;
	}

	/**
	 *
	 * @return
	 */
	public String getVictimLiaisonUnit() {
		return victimLiaisonUnit;
	}

	/**
	 *
	 * @param victimLiaisonUnit
	 */
	public void setVictimLiaisonUnit(String victimLiaisonUnit) {
		this.victimLiaisonUnit = victimLiaisonUnit;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderAssociatedParty> getOffenderAssociatedParties() {
		return offenderAssociatedParties;
	}

	/**
	 *
	 * @param offenderAssociatedParties
	 */
	public void setOffenderAssociatedParties(List<OffenderAssociatedParty> offenderAssociatedParties) {
		this.offenderAssociatedParties = offenderAssociatedParties;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBooking() {
		return offenderBooking;
	}

	/**
	 *
	 * @param offenderBooking
	 */
	public void setOffenderBooking(OffenderBookings offenderBooking) {
		this.offenderBooking = offenderBooking;
	}

	/**
	 *
	 * @return
	 */
	public OffenderCas getOffenderCase() {
		return offenderCase;
	}

	/**
	 *
	 * @param offenderCase
	 */
	public void setOffenderCase(OffenderCas offenderCase) {
		this.offenderCase = offenderCase;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCas> getOffenderCases() {
		return offenderCases;
	}

	/**
	 *
	 * @param offenderCases
	 */
	public void setOffenderCases(List<OffenderCas> offenderCases) {
		this.offenderCases = offenderCases;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCaseIdentifier> getOffenderCaseIdentifiers() {
		return offenderCaseIdentifiers;
	}

	/**
	 *
	 * @param offenderCaseIdentifiers
	 */
	public void setOffenderCaseIdentifiers(List<OffenderCaseIdentifier> offenderCaseIdentifiers) {
		this.offenderCaseIdentifiers = offenderCaseIdentifiers;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCaseStatus> getOffenderCaseStatuses() {
		return offenderCaseStatuses;
	}

	/**
	 *
	 * @param offenderCaseStatuses
	 */
	public void setOffenderCaseStatuses(List<OffenderCaseStatus> offenderCaseStatuses) {
		this.offenderCaseStatuses = offenderCaseStatuses;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderCharge> getOffenderCharges() {
		return offenderCharges;
	}

	/**
	 *
	 * @param offenderCharges
	 */
	public void setOffenderCharges(List<OffenderCharge> offenderCharges) {
		this.offenderCharges = offenderCharges;
	}

}