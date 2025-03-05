package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class BedAssignmentHistories extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("bedAssignSeq")
	private Integer bedAssignSeq;
	@JsonProperty("livingUnitId")
	private Integer livingUnitId;
	@JsonProperty("assignmentDate")
	private Date assignmentDate;
	@JsonProperty("assignmentTime")
	private Date assignmentTime;
	@JsonProperty("assignmentReason")
	private String assignmentReason;
	@JsonProperty("assignmentEndDate")
	private Date assignmentEndDate;
	@JsonProperty("assignmentEndTime")
	private Date assignmentEndTime;
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
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("warningMsg")
	private String warningMsg;
	
	@JsonProperty("warningPrompt")
	private String warningPrompt;
	
	@JsonProperty("dspDescription")
	private String dspDescription;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("notification")
	private String notification;
	@JsonProperty("offenderId")
	private Integer offenderId;
	
	@JsonProperty("offenderName")
	private String offenderName;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("offenderNonAssociationsByGang")
	private List<Offenders> offenderNonAssociationsByGang;
	

	@JsonProperty("offenderNonAssociationsByInd")
	private List<Offenders> offenderNonAssociationsByInd;

	public Integer getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Integer offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * Creates new OffenderAlerts class Object
	 */
	public BedAssignmentHistories() {
		// BedAssignmentHistories
	}

	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the bedAssignSeq
	 */
	public Integer getBedAssignSeq() {
		return bedAssignSeq;
	}

	/**
	 * @param bedAssignSeq
	 *            the bedAssignSeq to set
	 */
	public void setBedAssignSeq(final Integer bedAssignSeq) {
		this.bedAssignSeq = bedAssignSeq;
	}

	/**
	 * @return the livingUnitId
	 */
	public Integer getLivingUnitId() {
		return livingUnitId;
	}

	/**
	 * @param livingUnitId
	 *            the livingUnitId to set
	 */
	public void setLivingUnitId(final Integer livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}

	/**
	 * @param assignmentDate
	 *            the assignmentDate to set
	 */
	public void setAssignmentDate(final Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	/**
	 * @return the assignmentTime
	 */
	public Date getAssignmentTime() {
		return assignmentTime;
	}

	/**
	 * @param assignmentTime
	 *            the assignmentTime to set
	 */
	public void setAssignmentTime(final Date assignmentTime) {
		this.assignmentTime = assignmentTime;
	}

	/**
	 * @return the assignmentReason
	 */
	public String getAssignmentReason() {
		return assignmentReason;
	}

	/**
	 * @param assignmentReason
	 *            the assignmentReason to set
	 */
	public void setAssignmentReason(final String assignmentReason) {
		this.assignmentReason = assignmentReason;
	}

	/**
	 * @return the assignmentEndDate
	 */
	public Date getAssignmentEndDate() {
		return assignmentEndDate;
	}

	/**
	 * @param assignmentEndDate
	 *            the assignmentEndDate to set
	 */
	public void setAssignmentEndDate(final Date assignmentEndDate) {
		this.assignmentEndDate = assignmentEndDate;
	}

	/**
	 * @return the assignmentEndTime
	 */
	public Date getAssignmentEndTime() {
		return assignmentEndTime;
	}

	/**
	 * @param assignmentEndTime
	 *            the assignmentEndTime to set
	 */
	public void setAssignmentEndTime(final Date assignmentEndTime) {
		this.assignmentEndTime = assignmentEndTime;
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
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(final String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getWarningPrompt() {
		return warningPrompt;
	}

	public void setWarningPrompt(final String warningPrompt) {
		this.warningPrompt = warningPrompt;
	}

	/**
	 * @return the dspDescription
	 */
	public String getDspDescription() {
		return dspDescription;
	}

	/**
	 * @param dspDescription the dspDescription to set
	 */
	public void setDspDescription(String dspDescription) {
		this.dspDescription = dspDescription;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public List<Offenders> getOffenderNonAssociationsByGang() {
		return offenderNonAssociationsByGang;
	}

	public void setOffenderNonAssociationsByGang(List<Offenders> offenderNonAssociationsByGang) {
		this.offenderNonAssociationsByGang = offenderNonAssociationsByGang;
	}

	public List<Offenders> getOffenderNonAssociationsByInd() {
		return offenderNonAssociationsByInd;
	}

	public void setOffenderNonAssociationsByInd(List<Offenders> offenderNonAssociationsByInd) {
		this.offenderNonAssociationsByInd = offenderNonAssociationsByInd;
	}

}