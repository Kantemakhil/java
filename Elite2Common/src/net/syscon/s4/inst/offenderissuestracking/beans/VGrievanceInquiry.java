package net.syscon.s4.inst.offenderissuestracking.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the V_GRIEVANCE_INQUIRY database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VGrievanceInquiry  extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("assignedStaffId")
	private BigDecimal assignedStaffId;
	
	@JsonProperty("daysRem")
	private BigDecimal daysRem;

	@JsonProperty("grievLevel")
	private String grievLevel;

	@JsonProperty("grievType")
	private String grievType;

	@JsonProperty("grievanceId")
	private BigDecimal grievanceId;

	@JsonProperty("offName")
	private String offName;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("reportDate")
	private Date reportDate;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("toDate")
	private Date toDate;
	
	@JsonProperty("userInvolvement")
	private String userInvolvement;
	
	@JsonProperty("supervisorReviewed")
	private String supervisorReviewed;
	
	@JsonProperty("noSupervisorReviewed")
	private String noSupervisorReviewed;

	@JsonProperty("grievReasonCode")
	private String grievReasonCode;
	
	@JsonProperty("grievReasonCodeDesc")
	private String grievReasonCodeDesc;
	
	@JsonProperty("txnTypeDesc")
	private String txnTypeDesc;
	
	@JsonProperty("txnType")
	private String txnType;
	
	public VGrievanceInquiry() {
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getAssignedStaffId() {
		return this.assignedStaffId;
	}

	public void setAssignedStaffId(BigDecimal assignedStaffId) {
		this.assignedStaffId = assignedStaffId;
	}

	public BigDecimal getDaysRem() {
		return this.daysRem;
	}

	public void setDaysRem(BigDecimal daysRem) {
		this.daysRem = daysRem;
	}

	public String getGrievLevel() {
		return this.grievLevel;
	}

	public void setGrievLevel(String grievLevel) {
		this.grievLevel = grievLevel;
	}

	public String getGrievType() {
		return this.grievType;
	}

	public void setGrievType(String grievType) {
		this.grievType = grievType;
	}

	public BigDecimal getGrievanceId() {
		return this.grievanceId;
	}

	public void setGrievanceId(BigDecimal grievanceId) {
		this.grievanceId = grievanceId;
	}

	public String getOffName() {
		return this.offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the userInvolvement
	 */
	public String getUserInvolvement() {
		return userInvolvement;
	}

	/**
	 * @param userInvolvement the userInvolvement to set
	 */
	public void setUserInvolvement(String userInvolvement) {
		this.userInvolvement = userInvolvement;
	}

	/**
	 * @return the supervisorReviewed
	 */
	public String getSupervisorReviewed() {
		return supervisorReviewed;
	}

	/**
	 * @param supervisorReviewed the supervisorReviewed to set
	 */
	public void setSupervisorReviewed(String supervisorReviewed) {
		this.supervisorReviewed = supervisorReviewed;
	}

	/**
	 * @return the noSupervisorReviewed
	 */
	public String getNoSupervisorReviewed() {
		return noSupervisorReviewed;
	}

	/**
	 * @param noSupervisorReviewed the noSupervisorReviewed to set
	 */
	public void setNoSupervisorReviewed(String noSupervisorReviewed) {
		this.noSupervisorReviewed = noSupervisorReviewed;
	}

	public String getGrievReasonCode() {
		return grievReasonCode;
	}

	public void setGrievReasonCode(String grievReasonCode) {
		this.grievReasonCode = grievReasonCode;
	}

	public String getGrievReasonCodeDesc() {
		return grievReasonCodeDesc;
	}

	public void setGrievReasonCodeDesc(String grievReasonCodeDesc) {
		this.grievReasonCodeDesc = grievReasonCodeDesc;
	}

	public String getTxnTypeDesc() {
		return txnTypeDesc;
	}

	public void setTxnTypeDesc(String txnTypeDesc) {
		this.txnTypeDesc = txnTypeDesc;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	
}
