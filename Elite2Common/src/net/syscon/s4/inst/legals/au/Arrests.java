package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class Arrests extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("arrestId")
	private Long arrestId;
	
	
	@JsonProperty("caseId")
	private Long caseId;
	
	@JsonProperty("staffId")
	private Long staffId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("controlArrestAgyLocId")
	private String controlArrestAgyLocId;
	
	
	@JsonProperty("orderId")
	private Long orderId;
	
	@JsonProperty("arrestDate")
	private Date arrestDate;
	
	@JsonProperty("arrestTime")
	private Date arrestTime;
	
	
	@JsonProperty("bloodAlcoholContent")
	private Long bloodAlcoholContent;
	
	
	@JsonProperty("arrestLocationText")
	private String arrestLocationText;
	
	@JsonProperty("enforcementAgyLocId")
	private String enforcementAgyLocId;
	
	@JsonProperty("commentText")
	private String commentText;
	
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
	
	@JsonProperty("policeStation")
	private String policeStation;
	
	@JsonProperty("officerFirstName")
	private String officerFirstName;
	
	@JsonProperty("officerLastName")
	private String officerLastName;

	/**
	 * @return the arrestId
	 */
	public Long getArrestId() {
		return arrestId;
	}

	/**
	 * @param arrestId the arrestId to set
	 */
	public void setArrestId(final Long arrestId) {
		this.arrestId = arrestId;
	}

	/**
	 * @return the caseId
	 */
	public Long getCaseId() {
		return caseId;
	}

	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(final Long caseId) {
		this.caseId = caseId;
	}

	/**
	 * @return the staffId
	 */
	public Long getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(final Long staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the controlArrestAgyLocId
	 */
	public String getControlArrestAgyLocId() {
		return controlArrestAgyLocId;
	}

	/**
	 * @param controlArrestAgyLocId the controlArrestAgyLocId to set
	 */
	public void setControlArrestAgyLocId(final String controlArrestAgyLocId) {
		this.controlArrestAgyLocId = controlArrestAgyLocId;
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(final Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the arrestDate
	 */
	public Date getArrestDate() {
		return arrestDate;
	}

	/**
	 * @param arrestDate the arrestDate to set
	 */
	public void setArrestDate(final Date arrestDate) {
		this.arrestDate = arrestDate;
	}

	/**
	 * @return the arrestTime
	 */
	public Date getArrestTime() {
		return arrestTime;
	}

	/**
	 * @param arrestTime the arrestTime to set
	 */
	public void setArrestTime(final Date arrestTime) {
		this.arrestTime = arrestTime;
	}

	/**
	 * @return the bloodAlcoholContent
	 */
	public Long getBloodAlcoholContent() {
		return bloodAlcoholContent;
	}

	/**
	 * @param bloodAlcoholContent the bloodAlcoholContent to set
	 */
	public void setBloodAlcoholContent(final Long bloodAlcoholContent) {
		this.bloodAlcoholContent = bloodAlcoholContent;
	}

	/**
	 * @return the arrestLocationText
	 */
	public String getArrestLocationText() {
		return arrestLocationText;
	}

	/**
	 * @param arrestLocationText the arrestLocationText to set
	 */
	public void setArrestLocationText(final String arrestLocationText) {
		this.arrestLocationText = arrestLocationText;
	}

	/**
	 * @return the enforcementAgyLocId
	 */
	public String getEnforcementAgyLocId() {
		return enforcementAgyLocId;
	}

	/**
	 * @param enforcementAgyLocId the enforcementAgyLocId to set
	 */
	public void setEnforcementAgyLocId(final String enforcementAgyLocId) {
		this.enforcementAgyLocId = enforcementAgyLocId;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
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
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
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
	 * @param modifyUserId the modifyUserId to set
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
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the policeStation
	 */
	public String getPoliceStation() {
		return policeStation;
	}

	/**
	 * @param policeStation the policeStation to set
	 */
	public void setPoliceStation(final String policeStation) {
		this.policeStation = policeStation;
	}

	/**
	 * @return the officerFirstName
	 */
	public String getOfficerFirstName() {
		return officerFirstName;
	}

	/**
	 * @param officerFirstName the officerFirstName to set
	 */
	public void setOfficerFirstName(final String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}

	/**
	 * @return the officerLastName
	 */
	public String getOfficerLastName() {
		return officerLastName;
	}

	/**
	 * @param officerLastName the officerLastName to set
	 */
	public void setOfficerLastName(final String officerLastName) {
		this.officerLastName = officerLastName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
