package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class MovementReasons extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("movementType")
	@NotNull
	@Size(max = 12)
	private String movementType;

	@JsonProperty("movementReasonCode")
	@NotNull
	@Size(max = 12)
	private String movementReasonCode;

	@JsonProperty("description")
	@NotNull
	@Size(max = 40)
	private String description;

	@JsonProperty("openContactFlag")
	@NotNull
	@Size(max = 1)
	private String openContactFlag;

	@JsonProperty("closeContactFlag")
	@NotNull
	@Size(max = 1)
	private String closeContactFlag;

	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("listSeq")
	private Integer listSeq;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("notificationType")
	@Size(max = 1)
	private String notificationType;

	@JsonProperty("notificationFlag")
	@Size(max = 1)
	private String notificationFlag;

	@JsonProperty("billingServiceFlag")
	@Size(max = 1)
	private String billingServiceFlag;

	@JsonProperty("transportationFlag")
	@Size(max = 1)
	private String transportationFlag;

	@JsonProperty("headerStatusFlag")
	@Size(max = 1)
	private String headerStatusFlag;

	@JsonProperty("inMovementType")
	@Size(max = 12)
	private String inMovementType;

	@JsonProperty("inMovementReasonCode")
	@Size(max = 12)
	private String inMovementReasonCode;

	@JsonProperty("escRecapFlag")
	@Size(max = 1)
	private String escRecapFlag;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("updateAllowedFlag")
	@NotNull
	@Size(max = 1)
	private String updateAllowedFlag;

	@JsonProperty("trnReqApvlFlag")
	@Size(max = 1)
	private String trnReqApvlFlag;

	@JsonProperty("trnSchdTripFlag")
	@Size(max = 1)
	private String trnSchdTripFlag;

	@JsonProperty("trnAssignPriorityFlag")
	@Size(max = 1)
	private String trnAssignPriorityFlag;

	@JsonProperty("trnpListFlag")
	@Size(max = 1)
	private String trnpListFlag;

	@JsonProperty("trnPrioritySeq")
	private Integer trnPrioritySeq;

	@JsonProperty("subSequentMvType")
	@Size(max = 12)
	private String subSequentMvType;

	@JsonProperty("subSequentMvRsnCode")
	@Size(max = 12)
	private String subSequentMvRsnCode;;

	@JsonProperty("reasonFlag")
	@Size(max = 1)
	private String reasonFlag;

	@JsonProperty("driveToOidumappFlag")
	@Size(max = 1)
	private String driveToOidumappFlag;

	@JsonProperty("inActivateSent")
	@Size(max = 1)
	private String inActivateSent;

	@JsonProperty("suspendReactiveSent")
	@Size(max = 1)
	private String suspendReactiveSent;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("canDisplay")
	private boolean canDisplay = true;


	/**
	 * Creates new MovementReasons class Object
	 */
	public MovementReasons() {
		// Super();
	}

	/**
	 * @return the movementType
	 */
	public String getMovementType() {
		return movementType;
	}

	/**
	 * @param movementType
	 *            the movementType to set
	 */
	public void setMovementType(final String movementType) {
		this.movementType = movementType;
	}

	/**
	 * @return the movementReasonCode
	 */
	public String getMovementReasonCode() {
		return movementReasonCode;
	}

	/**
	 * @param movementReasonCode
	 *            the movementReasonCode to set
	 */
	public void setMovementReasonCode(final String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the openContactFlag
	 */
	public String getOpenContactFlag() {
		return openContactFlag;
	}

	/**
	 * @param openContactFlag
	 *            the openContactFlag to set
	 */
	public void setOpenContactFlag(final String openContactFlag) {
		this.openContactFlag = openContactFlag;
	}

	/**
	 * @return the closeContactFlag
	 */
	public String getCloseContactFlag() {
		return closeContactFlag;
	}

	/**
	 * @param closeContactFlag
	 *            the closeContactFlag to set
	 */
	public void setCloseContactFlag(final String closeContactFlag) {
		this.closeContactFlag = closeContactFlag;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the listSeq
	 */
	public Integer getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
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
	 * @return the notificationType
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 * @param notificationType
	 *            the notificationType to set
	 */
	public void setNotificationType(final String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * @return the notificationFlag
	 */
	public String getNotificationFlag() {
		return notificationFlag;
	}

	/**
	 * @param notificationFlag
	 *            the notificationFlag to set
	 */
	public void setNotificationFlag(final String notificationFlag) {
		this.notificationFlag = notificationFlag;
	}

	/**
	 * @return the billingServiceFlag
	 */
	public String getBillingServiceFlag() {
		return billingServiceFlag;
	}

	/**
	 * @param billingServiceFlag
	 *            the billingServiceFlag to set
	 */
	public void setBillingServiceFlag(final String billingServiceFlag) {
		this.billingServiceFlag = billingServiceFlag;
	}

	/**
	 * @return the transportationFlag
	 */
	public String getTransportationFlag() {
		return transportationFlag;
	}

	/**
	 * @param transportationFlag
	 *            the transportationFlag to set
	 */
	public void setTransportationFlag(final String transportationFlag) {
		this.transportationFlag = transportationFlag;
	}

	/**
	 * @return the headerStatusFlag
	 */
	public String getHeaderStatusFlag() {
		return headerStatusFlag;
	}

	/**
	 * @param headerStatusFlag
	 *            the headerStatusFlag to set
	 */
	public void setHeaderStatusFlag(final String headerStatusFlag) {
		this.headerStatusFlag = headerStatusFlag;
	}

	/**
	 * @return the inMovementType
	 */
	public String getInMovementType() {
		return inMovementType;
	}

	/**
	 * @param inMovementType
	 *            the inMovementType to set
	 */
	public void setInMovementType(final String inMovementType) {
		this.inMovementType = inMovementType;
	}

	/**
	 * @return the inMovementReasonCode
	 */
	public String getInMovementReasonCode() {
		return inMovementReasonCode;
	}

	/**
	 * @param inMovementReasonCode
	 *            the inMovementReasonCode to set
	 */
	public void setInMovementReasonCode(final String inMovementReasonCode) {
		this.inMovementReasonCode = inMovementReasonCode;
	}

	/**
	 * @return the escRecapFlag
	 */
	public String getEscRecapFlag() {
		return escRecapFlag;
	}

	/**
	 * @param escRecapFlag
	 *            the escRecapFlag to set
	 */
	public void setEscRecapFlag(final String escRecapFlag) {
		this.escRecapFlag = escRecapFlag;
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
	 * @return the updateAllowedFlag
	 */
	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	/**
	 * @param updateAllowedFlag
	 *            the updateAllowedFlag to set
	 */
	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	/**
	 * @return the trnReqApvlFlag
	 */
	public String getTrnReqApvlFlag() {
		return trnReqApvlFlag;
	}

	/**
	 * @param trnReqApvlFlag
	 *            the trnReqApvlFlag to set
	 */
	public void setTrnReqApvlFlag(final String trnReqApvlFlag) {
		this.trnReqApvlFlag = trnReqApvlFlag;
	}

	/**
	 * @return the trnSchdTripFlag
	 */
	public String getTrnSchdTripFlag() {
		return trnSchdTripFlag;
	}

	/**
	 * @param trnSchdTripFlag
	 *            the trnSchdTripFlag to set
	 */
	public void setTrnSchdTripFlag(final String trnSchdTripFlag) {
		this.trnSchdTripFlag = trnSchdTripFlag;
	}

	/**
	 * @return the trnAssignPriorityFlag
	 */
	public String getTrnAssignPriorityFlag() {
		return trnAssignPriorityFlag;
	}

	/**
	 * @param trnAssignPriorityFlag
	 *            the trnAssignPriorityFlag to set
	 */
	public void setTrnAssignPriorityFlag(final String trnAssignPriorityFlag) {
		this.trnAssignPriorityFlag = trnAssignPriorityFlag;
	}

	/**
	 * @return the trnpListFlag
	 */
	public String getTrnpListFlag() {
		return trnpListFlag;
	}

	/**
	 * @param trnpListFlag
	 *            the trnpListFlag to set
	 */
	public void setTrnpListFlag(final String trnpListFlag) {
		this.trnpListFlag = trnpListFlag;
	}

	/**
	 * @return the trnPrioritySeq
	 */
	public Integer getTrnPrioritySeq() {
		return trnPrioritySeq;
	}

	/**
	 * @param trnPrioritySeq
	 *            the trnPrioritySeq to set
	 */
	public void setTrnPrioritySeq(final Integer trnPrioritySeq) {
		this.trnPrioritySeq = trnPrioritySeq;
	}

	/**
	 * @return the subSequentMvType
	 */
	public String getSubSequentMvType() {
		return subSequentMvType;
	}

	/**
	 * @param subSequentMvType
	 *            the subSequentMvType to set
	 */
	public void setSubSequentMvType(final String subSequentMvType) {
		this.subSequentMvType = subSequentMvType;
	}

	/**
	 * @return the subSequentMvRsnCode
	 */
	public String getSubSequentMvRsnCode() {
		return subSequentMvRsnCode;
	}

	/**
	 * @param subSequentMvRsnCode
	 *            the subSequentMvRsnCode to set
	 */
	public void setSubSequentMvRsnCode(final String subSequentMvRsnCode) {
		this.subSequentMvRsnCode = subSequentMvRsnCode;
	}

	/**
	 * @return the reasonFlag
	 */
	public String getReasonFlag() {
		return reasonFlag;
	}

	/**
	 * @param reasonFlag
	 *            the reasonFlag to set
	 */
	public void setReasonFlag(final String reasonFlag) {
		this.reasonFlag = reasonFlag;
	}

	/**
	 * @return the driveToOidumappFlag
	 */
	public String getDriveToOidumappFlag() {
		return driveToOidumappFlag;
	}

	/**
	 * @param driveToOidumappFlag
	 *            the driveToOidumappFlag to set
	 */
	public void setDriveToOidumappFlag(final String driveToOidumappFlag) {
		this.driveToOidumappFlag = driveToOidumappFlag;
	}

	/**
	 * @return the inActivateSent
	 */
	public String getInActivateSent() {
		return inActivateSent;
	}

	/**
	 * @param inActivateSent
	 *            the inActivateSent to set
	 */
	public void setInActivateSent(final String inActivateSent) {
		this.inActivateSent = inActivateSent;
	}

	/**
	 * @return the suspendReactiveSent
	 */
	public String getSuspendReactiveSent() {
		return suspendReactiveSent;
	}

	/**
	 * @param suspendReactiveSent
	 *            the suspendReactiveSent to set
	 */
	public void setSuspendReactiveSent(final String suspendReactiveSent) {
		this.suspendReactiveSent = suspendReactiveSent;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the canDisplay
	 */
	public boolean getCanDisplay() {
		return canDisplay;
	}

	/**
	 * @param canDisplay the canDisplay to set
	 */
	public void setCanDisplay(final boolean canDisplay) {
		this.canDisplay = canDisplay;
	}
	private int returnValue;


	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(final int returnValue) {
		this.returnValue = returnValue;
	}
	private int offenderDeleteCount;
	private int externalMovmentCount;


	public int getOffenderDeleteCount() {
		return offenderDeleteCount;
	}

	public void setOffenderDeleteCount(final int offenderDeleteCount) {
		this.offenderDeleteCount = offenderDeleteCount;
	}

	public int getExternalMovmentCount() {
		return externalMovmentCount;
	}

	public void setExternalMovmentCount(final int externalMovmentCount) {
		this.externalMovmentCount = externalMovmentCount;
	}
	
	
}