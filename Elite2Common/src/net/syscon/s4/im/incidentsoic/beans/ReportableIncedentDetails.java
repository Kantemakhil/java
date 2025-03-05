package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class ReportableIncedentDetails extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("incidentReportableId")
	private BigDecimal incidentReportableId;

	@JsonProperty("agencyIncidentId")
	private Integer agencyIncidentId;

	@JsonProperty("partySeq")
	private Integer partySeq;
	
	@JsonProperty("reportableIncidentType")
	private String reportableIncidentType;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("reportableStaffId")
	private Integer reportableStaffId;
	
	@JsonProperty("reportableDatetime")
	private Date reportableDatetime;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("userName")
	private String userName;

	
	@JsonProperty("returnedOutput")
	private BigDecimal returnedOutput;
	
	public BigDecimal getIncidentReportableId() {
		return incidentReportableId;
	}

	public void setIncidentReportableId(BigDecimal incidentReportableId) {
		this.incidentReportableId = incidentReportableId;
	}

	public Integer getAgencyIncidentId() {
		return agencyIncidentId;
	}

	public void setAgencyIncidentId(Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	public Integer getPartySeq() {
		return partySeq;
	}

	public void setPartySeq(Integer partySeq) {
		this.partySeq = partySeq;
	}

	public String getReportableIncidentType() {
		return reportableIncidentType;
	}

	public void setReportableIncidentType(String reportableIncidentType) {
		this.reportableIncidentType = reportableIncidentType;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Integer getReportableStaffId() {
		return reportableStaffId;
	}

	public void setReportableStaffId(Integer reportableStaffId) {
		this.reportableStaffId = reportableStaffId;
	}

	public Date getReportableDatetime() {
		return reportableDatetime;
	}

	public void setReportableDatetime(Date reportableDatetime) {
		this.reportableDatetime = reportableDatetime;
	}

	

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getReturnedOutput() {
		return returnedOutput;
	}

	public void setReturnedOutput(BigDecimal returnedOutput) {
		this.returnedOutput = returnedOutput;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	
	
}
