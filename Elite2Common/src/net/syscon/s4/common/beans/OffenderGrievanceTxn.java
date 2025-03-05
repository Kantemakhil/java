package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderGrievanceTxn extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("assignedStaffId")
	private java.math.BigDecimal assignedStaffId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("finding")
	private String finding;

	@JsonProperty("grievLevel")
	private String grievLevel;

	@JsonProperty("grievType")
	private String grievType;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("officialResponse")
	private String officialResponse;

	@JsonProperty("proposedResponse")
	private String proposedResponse;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("status")
	private String status;

	@JsonProperty("txnType")
	private String txnType;

	@JsonProperty("offenderGrievance")
	private OffenderGrievance offenderGrievance;

	/**
	 *
	 * @return
	 */
	public java.math.BigDecimal getAssignedStaffId() {
		return assignedStaffId;
	}

	/**
	 *
	 * @param assignedStaffId
	 */
	public void setAssignedStaffId(java.math.BigDecimal assignedStaffId) {
		this.assignedStaffId = assignedStaffId;
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
	public Date getEndDate() {
		return endDate;
	}

	/**
	 *
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 *
	 * @return
	 */
	public String getFinding() {
		return finding;
	}

	/**
	 *
	 * @param finding
	 */
	public void setFinding(String finding) {
		this.finding = finding;
	}

	/**
	 *
	 * @return
	 */
	public String getGrievLevel() {
		return grievLevel;
	}

	/**
	 *
	 * @param grievLevel
	 */
	public void setGrievLevel(String grievLevel) {
		this.grievLevel = grievLevel;
	}

	/**
	 *
	 * @return
	 */
	public String getGrievType() {
		return grievType;
	}

	/**
	 *
	 * @param grievType
	 */
	public void setGrievType(String grievType) {
		this.grievType = grievType;
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
	public String getOfficialResponse() {
		return officialResponse;
	}

	/**
	 *
	 * @param officialResponse
	 */
	public void setOfficialResponse(String officialResponse) {
		this.officialResponse = officialResponse;
	}

	/**
	 *
	 * @return
	 */
	public String getProposedResponse() {
		return proposedResponse;
	}

	/**
	 *
	 * @param proposedResponse
	 */
	public void setProposedResponse(String proposedResponse) {
		this.proposedResponse = proposedResponse;
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
	public Date getStartDate() {
		return startDate;
	}

	/**
	 *
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 *
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 *
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 *
	 * @return
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 *
	 * @param txnType
	 */
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	/**
	 *
	 * @return
	 */
	public OffenderGrievance getOffenderGrievance() {
		return offenderGrievance;
	}

	/**
	 *
	 * @param offenderGrievance
	 */
	public void setOffenderGrievance(OffenderGrievance offenderGrievance) {
		this.offenderGrievance = offenderGrievance;
	}

}