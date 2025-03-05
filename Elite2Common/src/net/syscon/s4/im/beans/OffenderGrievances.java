package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderGrievances extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("grievanceId")
	private Long grievanceId;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("reportDate")
	private Date reportDate;
	@JsonProperty("grievType")
	private String grievType;
	@JsonProperty("grievReasonCode")
	private String grievReasonCode;
	@JsonProperty("agencyIncidentId")
	private Long agencyIncidentId;
	@JsonProperty("partySeq")
	private Long partySeq;
	@JsonProperty("claimAmount")
	private Integer claimAmount;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("currStatus")
	private String currStatus;
	@JsonProperty("currLevel")
	private String currLevel;
	@JsonProperty("agyLocIdDesc")
	private String agyLocIdDesc;
	private boolean inserted;
	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;
	@JsonProperty("staffInvolvedFlag")
	private String staffInvolvedFlag;
	@JsonProperty("grievanceIdCur")
	private Integer grievanceIdCur;
	private String createFlag;

	/**
	 * Creates new OffenderGrievances class Object
	 */
	public OffenderGrievances() {
		// OffenderGrievances
	}

	/**
	 * @return the grievanceId
	 */
	public Long getGrievanceId() {
		return grievanceId;
	}

	/**
	 * @param grievanceId
	 *            the grievanceId to set
	 */
	public void setGrievanceId(Long grievanceId) {
		this.grievanceId = grievanceId;
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
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate
	 *            the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return the grievType
	 */
	public String getGrievType() {
		return grievType;
	}

	/**
	 * @param grievType
	 *            the grievType to set
	 */
	public void setGrievType(String grievType) {
		this.grievType = grievType;
	}

	/**
	 * @return the grievReasonCode
	 */
	public String getGrievReasonCode() {
		return grievReasonCode;
	}

	/**
	 * @param grievReasonCode
	 *            the grievReasonCode to set
	 */
	public void setGrievReasonCode(String grievReasonCode) {
		this.grievReasonCode = grievReasonCode;
	}

	/**
	 * @return the agencyIncidentId
	 */
	public Long getAgencyIncidentId() {
		return agencyIncidentId;
	}

	/**
	 * @param agencyIncidentId
	 *            the agencyIncidentId to set
	 */
	public void setAgencyIncidentId(Long agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	/**
	 * @return the partySeq
	 */
	public Long getPartySeq() {
		return partySeq;
	}

	/**
	 * @param partySeq
	 *            the partySeq to set
	 */
	public void setPartySeq(Long partySeq) {
		this.partySeq = partySeq;
	}

	/**
	 * @return the claimAmount
	 */
	public Integer getClaimAmount() {
		return claimAmount;
	}

	/**
	 * @param claimAmount
	 *            the claimAmount to set
	 */
	public void setClaimAmount(Integer claimAmount) {
		this.claimAmount = claimAmount;
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
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	public void setModifyUserId(String modifyUserId) {
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
	public void setModifyDatetime(Date modifyDatetime) {
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
	public void setCreateDatetime(Date createDatetime) {
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
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
	public void setSealFlag(String sealFlag) {
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
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the currStatus
	 */
	public String getCurrStatus() {
		return currStatus;
	}

	/**
	 * @param currStatus
	 *            the currStatus to set
	 */
	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}

	/**
	 * @return the currLevel
	 */
	public String getCurrLevel() {
		return currLevel;
	}

	/**
	 * @param currLevel
	 *            the currLevel to set
	 */
	public void setCurrLevel(String currLevel) {
		this.currLevel = currLevel;
	}

	/**
	 * @return the agyLocIdDesc
	 */
	public String getAgyLocIdDesc() {
		return agyLocIdDesc;
	}

	/**
	 * @param agyLocIdDesc
	 *            the agyLocIdDesc to set
	 */
	public void setAgyLocIdDesc(String agyLocIdDesc) {
		this.agyLocIdDesc = agyLocIdDesc;
	}

	/**
	 * @return the rootOffenderId
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getStaffInvolvedFlag() {
		return staffInvolvedFlag;
	}

	public void setStaffInvolvedFlag(String staffInvolvedFlag) {
		this.staffInvolvedFlag = staffInvolvedFlag;
	}

	public Integer getGrievanceIdCur() {
		return grievanceIdCur;
	}

	public void setGrievanceIdCur(Integer grievanceIdCur) {
		this.grievanceIdCur = grievanceIdCur;
	}

	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}
	

}
