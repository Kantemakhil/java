package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderGrievanceTxns extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("grievanceId")
	private Long grievanceId;
	@JsonProperty("txnSeq")
	private Long txnSeq;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("grievType")
	private String grievType;
	@JsonProperty("txnType")
	private String txnType;
	@JsonProperty("finding")
	private String finding;
	@JsonProperty("assignedStaffId")
	private Long assignedStaffId;
	@JsonProperty("grievLevel")
	private String grievLevel;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("status")
	private String status;
	@JsonProperty("proposedResponse")
	private String proposedResponse;
	@JsonProperty("officialResponse")
	private String officialResponse;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	private boolean inserted;
	@JsonProperty("assignedStaffName")
	private String assignedStaffName;
	@JsonProperty("daysLeft")
	private Long daysLeft;

	/**
	 * Creates new OffenderGrievanceTxns class Object
	 */
	public OffenderGrievanceTxns() {
		// OffenderGrievanceTxns
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
	 * @return the txnSeq
	 */
	public Long getTxnSeq() {
		return txnSeq;
	}

	/**
	 * @param txnSeq
	 *            the txnSeq to set
	 */
	public void setTxnSeq(Long txnSeq) {
		this.txnSeq = txnSeq;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType
	 *            the txnType to set
	 */
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	/**
	 * @return the finding
	 */
	public String getFinding() {
		return finding;
	}

	/**
	 * @param finding
	 *            the finding to set
	 */
	public void setFinding(String finding) {
		this.finding = finding;
	}

	/**
	 * @return the assignedStaffId
	 */
	public Long getAssignedStaffId() {
		return assignedStaffId;
	}

	/**
	 * @param assignedStaffId
	 *            the assignedStaffId to set
	 */
	public void setAssignedStaffId(Long assignedStaffId) {
		this.assignedStaffId = assignedStaffId;
	}

	/**
	 * @return the grievLevel
	 */
	public String getGrievLevel() {
		return grievLevel;
	}

	/**
	 * @param grievLevel
	 *            the grievLevel to set
	 */
	public void setGrievLevel(String grievLevel) {
		this.grievLevel = grievLevel;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the proposedResponse
	 */
	public String getProposedResponse() {
		return proposedResponse;
	}

	/**
	 * @param proposedResponse
	 *            the proposedResponse to set
	 */
	public void setProposedResponse(String proposedResponse) {
		this.proposedResponse = proposedResponse;
	}

	/**
	 * @return the officialResponse
	 */
	public String getOfficialResponse() {
		return officialResponse;
	}

	/**
	 * @param officialResponse
	 *            the officialResponse to set
	 */
	public void setOfficialResponse(String officialResponse) {
		this.officialResponse = officialResponse;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the assignedStaffName
	 */
	public String getAssignedStaffName() {
		return assignedStaffName;
	}

	/**
	 * @param assignedStaffName
	 *            the assignedStaffName to set
	 */
	public void setAssignedStaffName(String assignedStaffName) {
		this.assignedStaffName = assignedStaffName;
	}

	/**
	 * @return the daysLeft
	 */
	public Long getDaysLeft() {
		return daysLeft;
	}

	/**
	 * @param daysLeft
	 *            the daysLeft to set
	 */
	public void setDaysLeft(Long daysLeft) {
		this.daysLeft = daysLeft;
	}

}