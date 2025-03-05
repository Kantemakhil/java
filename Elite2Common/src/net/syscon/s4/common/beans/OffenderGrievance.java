package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderGrievance extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("grievanceId")
	private long grievanceId;

	@JsonProperty("agencyIncidentId")
	private BigDecimal agencyIncidentId;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("claimAmount")
	private BigDecimal claimAmount;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("grievReasonCode")
	private String grievReasonCode;

	@JsonProperty("grievType")
	private String grievType;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("partySeq")
	private BigDecimal partySeq;

	@JsonProperty("reportDate")
	private Date reportDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderGrievanceTxns")
	private List<OffenderGrievanceTxn> offenderGrievanceTxns;

	@JsonProperty("offenderGrievStaffs")
	private List<OffenderGrievStaff> offenderGrievStaffs;

	/**
	 *
	 * @return
	 */
	public long getGrievanceId() {
		return grievanceId;
	}

	/**
	 *
	 * @param grievanceId
	 */
	public void setGrievanceId(long grievanceId) {
		this.grievanceId = grievanceId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAgencyIncidentId() {
		return agencyIncidentId;
	}

	/**
	 *
	 * @param agencyIncidentId
	 */
	public void setAgencyIncidentId(BigDecimal agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
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
	public BigDecimal getClaimAmount() {
		return claimAmount;
	}

	/**
	 *
	 * @param claimAmount
	 */
	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	public String getGrievReasonCode() {
		return grievReasonCode;
	}

	/**
	 *
	 * @param grievReasonCode
	 */
	public void setGrievReasonCode(String grievReasonCode) {
		this.grievReasonCode = grievReasonCode;
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
	public BigDecimal getPartySeq() {
		return partySeq;
	}

	/**
	 *
	 * @param partySeq
	 */
	public void setPartySeq(BigDecimal partySeq) {
		this.partySeq = partySeq;
	}

	/**
	 *
	 * @return
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 *
	 * @param reportDate
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderGrievanceTxn> getOffenderGrievanceTxns() {
		return offenderGrievanceTxns;
	}

	/**
	 *
	 * @param offenderGrievanceTxns
	 */
	public void setOffenderGrievanceTxns(List<OffenderGrievanceTxn> offenderGrievanceTxns) {
		this.offenderGrievanceTxns = offenderGrievanceTxns;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderGrievStaff> getOffenderGrievStaffs() {
		return offenderGrievStaffs;
	}

	/**
	 *
	 * @param offenderGrievStaffs
	 */
	public void setOffenderGrievStaffs(List<OffenderGrievStaff> offenderGrievStaffs) {
		this.offenderGrievStaffs = offenderGrievStaffs;
	}

}