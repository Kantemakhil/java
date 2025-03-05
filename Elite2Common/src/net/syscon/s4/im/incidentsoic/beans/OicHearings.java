package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OicHearings extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("oicHearingId")
	@NotNull
	private Integer oicHearingId;

	@JsonProperty("oicHearingType")
	@Size(max = 12)
	private String oicHearingType;

	@JsonProperty("oicIncidentId")
	@NotNull
	private Integer oicIncidentId;

	@JsonProperty("scheduleDate")
	private Date scheduleDate;

	@JsonProperty("scheduleTime")
	private Date scheduleTime;

	@JsonProperty("hearingDate")
	private Date hearingDate;

	@JsonProperty("hearingTime")
	private Date hearingTime;

	@JsonProperty("hearingStaffId")
	private Integer hearingStaffId;

	@JsonProperty("visitJusticeText")
	@Size(max = 40)
	private String visitJusticeText;

	@JsonProperty("commentText")
	@Size(max = 240)
	private String commentText;

	@JsonProperty("tapeNumber")
	@Size(max = 12)
	private String tapeNumber;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 12)
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("internalLocationId")
	private Integer internalLocationId;

	@JsonProperty("representativeText")
	@Size(max = 240)
	private String representativeText;

	@JsonProperty("eventId")
	private Integer eventId;

	@JsonProperty("eventStatus")
	@Size(max = 12)
	private String eventStatus;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("inserted")
	private Boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("oicHearingTypeDes")
	private String oicHearingTypeDes;

	@JsonProperty("internalLocationIdDes")
	private String internalLocationIdDes;

	@JsonProperty("hearingStaffIdDes")
	private String hearingStaffIdDes;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	
	@JsonProperty("listSeq")
	private Integer listSeq;
	
	/**
	 * Creates new OicHearings class Object
	 */
	public OicHearings() {
		
		//OicHearingResults
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
	 * @return the oicHearingType
	 */
	public String getOicHearingType() {
		return oicHearingType;
	}

	/**
	 * @param oicHearingType
	 *            the oicHearingType to set
	 */
	public void setOicHearingType(final String oicHearingType) {
		this.oicHearingType = oicHearingType;
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
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * @param scheduleDate
	 *            the scheduleDate to set
	 */
	public void setScheduleDate(final Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	/**
	 * @return the scheduleTime
	 */
	public Date getScheduleTime() {
		return scheduleTime;
	}

	/**
	 * @param scheduleTime
	 *            the scheduleTime to set
	 */
	public void setScheduleTime( final Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	/**
	 * @return the hearingDate
	 */
	public Date getHearingDate() {
		return hearingDate;
	}

	/**
	 * @param hearingDate
	 *            the hearingDate to set
	 */
	public void setHearingDate(final Date hearingDate) {
		this.hearingDate = hearingDate;
	}

	/**
	 * @return the hearingTime
	 */
	public Date getHearingTime() {
		return hearingTime;
	}

	/**
	 * @param hearingTime
	 *            the hearingTime to set
	 */
	public void setHearingTime(final Date hearingTime) {
		this.hearingTime = hearingTime;
	}

	/**
	 * @return the hearingStaffId
	 */
	public Integer getHearingStaffId() {
		return hearingStaffId;
	}

	/**
	 * @param hearingStaffId
	 *            the hearingStaffId to set
	 */
	public void setHearingStaffId(final Integer hearingStaffId) {
		this.hearingStaffId = hearingStaffId;
	}

	/**
	 * @return the visitJusticeText
	 */
	public String getVisitJusticeText() {
		return visitJusticeText;
	}

	/**
	 * @param visitJusticeText
	 *            the visitJusticeText to set
	 */
	public void setVisitJusticeText(final String visitJusticeText) {
		this.visitJusticeText = visitJusticeText;
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
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the tapeNumber
	 */
	public String getTapeNumber() {
		return tapeNumber;
	}

	/**
	 * @param tapeNumber
	 *            the tapeNumber to set
	 */
	public void setTapeNumber(final String tapeNumber) {
		this.tapeNumber = tapeNumber;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
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
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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
	 * @return the internalLocationId
	 */
	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	/**
	 * @param internalLocationId
	 *            the internalLocationId to set
	 */
	public void setInternalLocationId(final Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	/**
	 * @return the representativeText
	 */
	public String getRepresentativeText() {
		return representativeText;
	}

	/**
	 * @param representativeText
	 *            the representativeText to set
	 */
	public void setRepresentativeText(final String representativeText) {
		this.representativeText = representativeText;
	}

	/**
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(final Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventStatus
	 */
	public String getEventStatus() {
		return eventStatus;
	}

	/**
	 * @param eventStatus
	 *            the eventStatus to set
	 */
	public void setEventStatus(final String eventStatus) {
		this.eventStatus = eventStatus;
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
	public Boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final Boolean inserted) {
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
	 * @return the oicHearingTypeDes
	 */
	public String getOicHearingTypeDes() {
		return oicHearingTypeDes;
	}

	/**
	 * @param oicHearingTypeDes
	 *            the oicHearingTypeDes to set
	 */
	public void setOicHearingTypeDes(final String oicHearingTypeDes) {
		this.oicHearingTypeDes = oicHearingTypeDes;
	}

	/**
	 * @return the internalLocationIdDes
	 */
	public String getInternalLocationIdDes() {
		return internalLocationIdDes;
	}

	/**
	 * @param internalLocationIdDes
	 *            the internalLocationIdDes to set
	 */
	public void setInternalLocationIdDes(final String internalLocationIdDes) {
		this.internalLocationIdDes = internalLocationIdDes;
	}

	/**
	 * @return the hearingStaffIdDes
	 */
	public String getHearingStaffIdDes() {
		return hearingStaffIdDes;
	}

	/**
	 * @param hearingStaffIdDes the hearingStaffIdDes to set
	 */
	public void setHearingStaffIdDes(String hearingStaffIdDes) {
		this.hearingStaffIdDes = hearingStaffIdDes;
	}

	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	@Override
	public String toString() {
		return "OicHearings [oicHearingId=" + oicHearingId + ", oicHearingType=" + oicHearingType + ", oicIncidentId="
				+ oicIncidentId + ", scheduleDate=" + scheduleDate + ", scheduleTime=" + scheduleTime + ", hearingDate="
				+ hearingDate + ", hearingTime=" + hearingTime + ", hearingStaffId=" + hearingStaffId
				+ ", visitJusticeText=" + visitJusticeText + ", commentText=" + commentText + ", tapeNumber="
				+ tapeNumber + ", createDateTime=" + createDateTime + ", createUserId=" + createUserId
				+ ", modifyDateTime=" + modifyDateTime + ", modifyUserId=" + modifyUserId + ", internalLocationId="
				+ internalLocationId + ", representativeText=" + representativeText + ", eventId=" + eventId
				+ ", eventStatus=" + eventStatus + ", sealFlag=" + sealFlag + ", inserted=" + inserted
				+ ", errorMessage=" + errorMessage + ", oicHearingTypeDes=" + oicHearingTypeDes
				+ ", internalLocationIdDes=" + internalLocationIdDes + ", hearingStaffIdDes=" + hearingStaffIdDes
				+ ", offenderBookId=" + offenderBookId + ", listSeq=" + listSeq + "]";
	}
	
	
}