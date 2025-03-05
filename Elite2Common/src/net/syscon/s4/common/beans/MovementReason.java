package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovementReason extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("billingServiceFlag")
	private String billingServiceFlag;

	@JsonProperty("closeContactFlag")
	private String closeContactFlag;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("driveToOidumappFlag")
	private String driveToOidumappFlag;

	@JsonProperty("escRecapFlag")
	private String escRecapFlag;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("headerStatusFlag")
	private String headerStatusFlag;

	@JsonProperty("inMovementReasonCode")
	private String inMovementReasonCode;

	@JsonProperty("inMovementType")
	private String inMovementType;

	@JsonProperty("inactivateSent")
	private String inactivateSent;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("notificationFlag")
	private String notificationFlag;

	@JsonProperty("notificationType")
	private String notificationType;

	@JsonProperty("openContactFlag")
	private String openContactFlag;

	@JsonProperty("reasonFlag")
	private String reasonFlag;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("subsequentMvRsnCode")
	private String subsequentMvRsnCode;

	@JsonProperty("subsequentMvType")
	private String subsequentMvType;

	@JsonProperty("suspendReactivateSent")
	private String suspendReactivateSent;

	@JsonProperty("transportationFlag")
	private String transportationFlag;

	@JsonProperty("trnAssignPriorityFlag")
	private String trnAssignPriorityFlag;

	@JsonProperty("trnPListFlag")
	private String trnPListFlag;

	@JsonProperty("trnPrioritySeq")
	private BigDecimal trnPrioritySeq;

	@JsonProperty("trnReqApvlFlag")
	private String trnReqApvlFlag;

	@JsonProperty("trnSchdTripFlag")
	private String trnSchdTripFlag;

	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;

	@JsonProperty("modifyTables")
	private List<ModifyTable> modifyTables;

	@JsonProperty("notifyMovementTypes")
	private List<NotifyMovementType> notifyMovementTypes;

	@JsonProperty("offenderExternalMovements")
	private List<OffenderExternalMovement> offenderExternalMovements;

	@JsonProperty("movementType")
	private String movementType;

	@JsonProperty("movementReasonCode")
	private String movementReasonCode;

	/**
	 *
	 * @return
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 *
	 * @param activeFlag
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getBillingServiceFlag() {
		return billingServiceFlag;
	}

	/**
	 *
	 * @param billingServiceFlag
	 */
	public void setBillingServiceFlag(String billingServiceFlag) {
		this.billingServiceFlag = billingServiceFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getCloseContactFlag() {
		return closeContactFlag;
	}

	/**
	 *
	 * @param closeContactFlag
	 */
	public void setCloseContactFlag(String closeContactFlag) {
		this.closeContactFlag = closeContactFlag;
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
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *
	 * @return
	 */
	public String getDriveToOidumappFlag() {
		return driveToOidumappFlag;
	}

	/**
	 *
	 * @param driveToOidumappFlag
	 */
	public void setDriveToOidumappFlag(String driveToOidumappFlag) {
		this.driveToOidumappFlag = driveToOidumappFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getEscRecapFlag() {
		return escRecapFlag;
	}

	/**
	 *
	 * @param escRecapFlag
	 */
	public void setEscRecapFlag(String escRecapFlag) {
		this.escRecapFlag = escRecapFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 *
	 * @param expiryDate
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 *
	 * @return
	 */
	public String getHeaderStatusFlag() {
		return headerStatusFlag;
	}

	/**
	 *
	 * @param headerStatusFlag
	 */
	public void setHeaderStatusFlag(String headerStatusFlag) {
		this.headerStatusFlag = headerStatusFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getInMovementReasonCode() {
		return inMovementReasonCode;
	}

	/**
	 *
	 * @param inMovementReasonCode
	 */
	public void setInMovementReasonCode(String inMovementReasonCode) {
		this.inMovementReasonCode = inMovementReasonCode;
	}

	/**
	 *
	 * @return
	 */
	public String getInMovementType() {
		return inMovementType;
	}

	/**
	 *
	 * @param inMovementType
	 */
	public void setInMovementType(String inMovementType) {
		this.inMovementType = inMovementType;
	}

	/**
	 *
	 * @return
	 */
	public String getInactivateSent() {
		return inactivateSent;
	}

	/**
	 *
	 * @param inactivateSent
	 */
	public void setInactivateSent(String inactivateSent) {
		this.inactivateSent = inactivateSent;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 *
	 * @param listSeq
	 */
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
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
	public String getNotificationFlag() {
		return notificationFlag;
	}

	/**
	 *
	 * @param notificationFlag
	 */
	public void setNotificationFlag(String notificationFlag) {
		this.notificationFlag = notificationFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 *
	 * @param notificationType
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 *
	 * @return
	 */
	public String getOpenContactFlag() {
		return openContactFlag;
	}

	/**
	 *
	 * @param openContactFlag
	 */
	public void setOpenContactFlag(String openContactFlag) {
		this.openContactFlag = openContactFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getReasonFlag() {
		return reasonFlag;
	}

	/**
	 *
	 * @param reasonFlag
	 */
	public void setReasonFlag(String reasonFlag) {
		this.reasonFlag = reasonFlag;
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
	public String getSubsequentMvRsnCode() {
		return subsequentMvRsnCode;
	}

	/**
	 *
	 * @param subsequentMvRsnCode
	 */
	public void setSubsequentMvRsnCode(String subsequentMvRsnCode) {
		this.subsequentMvRsnCode = subsequentMvRsnCode;
	}

	/**
	 *
	 * @return
	 */
	public String getSubsequentMvType() {
		return subsequentMvType;
	}

	/**
	 *
	 * @param subsequentMvType
	 */
	public void setSubsequentMvType(String subsequentMvType) {
		this.subsequentMvType = subsequentMvType;
	}

	/**
	 *
	 * @return
	 */
	public String getSuspendReactivateSent() {
		return suspendReactivateSent;
	}

	/**
	 *
	 * @param suspendReactivateSent
	 */
	public void setSuspendReactivateSent(String suspendReactivateSent) {
		this.suspendReactivateSent = suspendReactivateSent;
	}

	/**
	 *
	 * @return
	 */
	public String getTransportationFlag() {
		return transportationFlag;
	}

	/**
	 *
	 * @param transportationFlag
	 */
	public void setTransportationFlag(String transportationFlag) {
		this.transportationFlag = transportationFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getTrnAssignPriorityFlag() {
		return trnAssignPriorityFlag;
	}

	/**
	 *
	 * @param trnAssignPriorityFlag
	 */
	public void setTrnAssignPriorityFlag(String trnAssignPriorityFlag) {
		this.trnAssignPriorityFlag = trnAssignPriorityFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getTrnPListFlag() {
		return trnPListFlag;
	}

	/**
	 *
	 * @param trnPListFlag
	 */
	public void setTrnPListFlag(String trnPListFlag) {
		this.trnPListFlag = trnPListFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getTrnPrioritySeq() {
		return trnPrioritySeq;
	}

	/**
	 *
	 * @param trnPrioritySeq
	 */
	public void setTrnPrioritySeq(BigDecimal trnPrioritySeq) {
		this.trnPrioritySeq = trnPrioritySeq;
	}

	/**
	 *
	 * @return
	 */
	public String getTrnReqApvlFlag() {
		return trnReqApvlFlag;
	}

	/**
	 *
	 * @param trnReqApvlFlag
	 */
	public void setTrnReqApvlFlag(String trnReqApvlFlag) {
		this.trnReqApvlFlag = trnReqApvlFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getTrnSchdTripFlag() {
		return trnSchdTripFlag;
	}

	/**
	 *
	 * @param trnSchdTripFlag
	 */
	public void setTrnSchdTripFlag(String trnSchdTripFlag) {
		this.trnSchdTripFlag = trnSchdTripFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	/**
	 *
	 * @param updateAllowedFlag
	 */
	public void setUpdateAllowedFlag(String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	/**
	 *
	 * @return
	 */
	public List<ModifyTable> getModifyTables() {
		return modifyTables;
	}

	/**
	 *
	 * @param modifyTables
	 */
	public void setModifyTables(List<ModifyTable> modifyTables) {
		this.modifyTables = modifyTables;
	}

	/**
	 *
	 * @return
	 */
	public List<NotifyMovementType> getNotifyMovementTypes() {
		return notifyMovementTypes;
	}

	/**
	 *
	 * @param notifyMovementTypes
	 */
	public void setNotifyMovementTypes(List<NotifyMovementType> notifyMovementTypes) {
		this.notifyMovementTypes = notifyMovementTypes;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderExternalMovement> getOffenderExternalMovements() {
		return offenderExternalMovements;
	}

	/**
	 *
	 * @param offenderExternalMovements
	 */
	public void setOffenderExternalMovements(List<OffenderExternalMovement> offenderExternalMovements) {
		this.offenderExternalMovements = offenderExternalMovements;
	}

	/**
	 *
	 * @return
	 */
	public String getMovementType() {
		return movementType;
	}

	/**
	 *
	 * @param movementType
	 */
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	/**
	 *
	 * @return
	 */
	public String getMovementReasonCode() {
		return movementReasonCode;
	}

	/**
	 *
	 * @param movementReasonCode
	 */
	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

}