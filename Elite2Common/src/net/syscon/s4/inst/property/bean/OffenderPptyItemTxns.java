package net.syscon.s4.inst.property.bean;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderPptyItemTxns extends BaseModel implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@JsonProperty("propertyItemTxnId")
	@NotNull
	private Integer propertyItemTxnId;
	
	@JsonProperty("offenderBookId")
	@NotNull
	private Integer offenderBookId;
	
	@JsonProperty("propertyItemSeq")
	@NotNull
	private Integer propertyItemSeq;
	
	@JsonProperty("eventSeq")
	private Integer eventSeq;
	
	@JsonProperty("fromStatusCode")
	@Size(max=12)
	private String fromStatusCode;
	
	@JsonProperty("toStatusCode")
	@NotNull
	@Size(max=12)
	private String toStatusCode;
	
	@JsonProperty("createDate")
	@NotNull
	private Date createDate;
	
	@JsonProperty("verifyFlagTemp")
	private Boolean verifyFlagTemp;
	
	@JsonProperty("createUserId")
	@NotNull
	@Size(max=32)
	private String createUserId;
	
	@JsonProperty("commentText")
	@Size(max=240)
	private String commentText;
	
	@JsonProperty("agyLocId")
	@NotNull
	@Size(max=6)
	private String agyLocId;
	
	@JsonProperty("disposedToOffenderFlag")
	@Size(max=1)
	private String disposedToOffenderFlag;
	
	@JsonProperty("verificationFlag")
	@NotNull
	@Size(max=1)
	private String verificationFlag;
	
	@JsonProperty("verifyFlag")
	@NotNull
	@Size(max=1)
	private String verifyFlag;
	
	@JsonProperty("disposedToCorpId")
	private Integer disposedToCorpId;
	
	@JsonProperty("disposedToPerson")
	@Size(max=72)
	private String disposedToPerson;
	
	@JsonProperty("propertyContainerId")
	private Integer propertyContainerId;
	
	@JsonProperty("propertyContainerTxnId")
	private Integer propertyContainerTxnId;
	
	@JsonProperty("toPropertyContainerId")
	private Integer toPropertyContainerId;
	
	@JsonProperty("disposedToPersonId")
	private Integer disposedToPersonId;
	
	@JsonProperty("quantity")
	private Integer quantity;
	
	@JsonProperty("color")
	@Size(max=5)
	private String color;
	
	@JsonProperty("make")
	@Size(max=5)
	private String make;
	
	@JsonProperty("serialNo")
	@Size(max=12)
	private String serialNo;
	
	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	@Size(max=32)
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	@Size(max=1)
	private String sealFlag;
	
	@JsonProperty("inserted")
	private boolean inserted;
	
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("propertyDescription")
	private String propertyDescription;
	
	
	@JsonProperty("propertyType")
	private String propertyType;
	
	@JsonProperty("conditionCode")
	private String conditionCode;
	
	@JsonProperty("colorName")
	private String colorName;
	
	@JsonProperty("makeType")
	private String makeType;
	
	@JsonProperty("disposedToCorpName")
	private String disposedToCorpName;
	
	@JsonProperty("nbtDspDescription")
	private String nbtDspDescription;
	
	@JsonProperty("disposedToPersonIdDes")
	private String disposedToPersonIdDes;
	
	@JsonProperty("actionReason")
	private String actionReason;
	
	@JsonProperty("actionCode")
	private String actionCode;
	
	
	/**
	 * Creates new OffenderPptyItemTxns class Object
	 */
	
	public OffenderPptyItemTxns() {
		
		//OffenderPptyItemTxns
	}
	
	/**
	 * @return the propertyItemTxnId
	 */
	public Integer getPropertyItemTxnId() {
		return propertyItemTxnId;
	}
	/**
	 * @param propertyItemTxnId the propertyItemTxnId to set
	 */
	public void setPropertyItemTxnId(final Integer propertyItemTxnId) {
		this.propertyItemTxnId = propertyItemTxnId;
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
	/**
	 * @return the propertyItemSeq
	 */
	public Integer getPropertyItemSeq() {
		return propertyItemSeq;
	}
	/**
	 * @param propertyItemSeq the propertyItemSeq to set
	 */
	public void setPropertyItemSeq(final Integer propertyItemSeq) {
		this.propertyItemSeq = propertyItemSeq;
	}
	/**
	 * @return the eventSeq
	 */
	public Integer getEventSeq() {
		return eventSeq;
	}
	/**
	 * @param eventSeq the eventSeq to set
	 */
	public void setEventSeq(final Integer eventSeq) {
		this.eventSeq = eventSeq;
	}
	/**
	 * @return the fromStatusCode
	 */
	public String getFromStatusCode() {
		return fromStatusCode;
	}
	/**
	 * @param fromStatusCode the fromStatusCode to set
	 */
	public void setFromStatusCode(final String fromStatusCode) {
		this.fromStatusCode = fromStatusCode;
	}
	/**
	 * @return the toStatusCode
	 */
	public String getToStatusCode() {
		return toStatusCode;
	}
	/**
	 * @param toStatusCode the toStatusCode to set
	 */
	public void setToStatusCode(final String toStatusCode) {
		this.toStatusCode = toStatusCode;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
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
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}
	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}
	/**
	 * @return the disposedToOffenderFlag
	 */
	public String getDisposedToOffenderFlag() {
		return disposedToOffenderFlag;
	}
	/**
	 * @param disposedToOffenderFlag the disposedToOffenderFlag to set
	 */
	public void setDisposedToOffenderFlag(final String disposedToOffenderFlag) {
		this.disposedToOffenderFlag = disposedToOffenderFlag;
	}
	/**
	 * @return the verificationFlag
	 */
	public String getVerificationFlag() {
		return verificationFlag;
	}
	/**
	 * @param verificationFlag the verificationFlag to set
	 */
	public void setVerificationFlag(final String verificationFlag) {
		this.verificationFlag = verificationFlag;
	}
	/**
	 * @return the verifyFlag
	 */
	public String getVerifyFlag() {
		return verifyFlag;
	}
	/**
	 * @param verifyFlag the verifyFlag to set
	 */
	public void setVerifyFlag(final String verifyFlag) {
		this.verifyFlag = verifyFlag;
	}
	/**
	 * @return the disposedToCorpId
	 */
	public Integer getDisposedToCorpId() {
		return disposedToCorpId;
	}
	/**
	 * @param disposedToCorpId the disposedToCorpId to set
	 */
	public void setDisposedToCorpId(final Integer disposedToCorpId) {
		this.disposedToCorpId = disposedToCorpId;
	}
	/**
	 * @return the disposedToPerson
	 */
	public String getDisposedToPerson() {
		return disposedToPerson;
	}
	/**
	 * @param disposedToPerson the disposedToPerson to set
	 */
	public void setDisposedToPerson(final String disposedToPerson) {
		this.disposedToPerson = disposedToPerson;
	}
	/**
	 * @return the propertyContainerId
	 */
	public Integer getPropertyContainerId() {
		return propertyContainerId;
	}
	/**
	 * @param propertyContainerId the propertyContainerId to set
	 */
	public void setPropertyContainerId(final Integer propertyContainerId) {
		this.propertyContainerId = propertyContainerId;
	}
	/**
	 * @return the propertyContainerTxnId
	 */
	public Integer getPropertyContainerTxnId() {
		return propertyContainerTxnId;
	}
	/**
	 * @param propertyContainerTxnId the propertyContainerTxnId to set
	 */
	public void setPropertyContainerTxnId(final Integer propertyContainerTxnId) {
		this.propertyContainerTxnId = propertyContainerTxnId;
	}
	/**
	 * @return the toPropertyContainerId
	 */
	public Integer getToPropertyContainerId() {
		return toPropertyContainerId;
	}
	/**
	 * @param toPropertyContainerId the toPropertyContainerId to set
	 */
	public void setToPropertyContainerId(final Integer toPropertyContainerId) {
		this.toPropertyContainerId = toPropertyContainerId;
	}
	/**
	 * @return the disposedToPersonId
	 */
	public Integer getDisposedToPersonId() {
		return disposedToPersonId;
	}
	/**
	 * @param disposedToPersonId the disposedToPersonId to set
	 */
	public void setDisposedToPersonId(Integer disposedToPersonId) {
		this.disposedToPersonId = disposedToPersonId;
	}
	
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}
	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(final String serialNo) {
		this.serialNo = serialNo;
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
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}
	/**
	 * @param inserted the inserted to set
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
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the disposedToCorpName
	 */
	public String getDisposedToCorpName() {
		return disposedToCorpName;
	}
	/**
	 * @param disposedToCorpName the disposedToCorpName to set
	 */
	public void setDisposedToCorpName(final String disposedToCorpName) {
		this.disposedToCorpName = disposedToCorpName;
	}
	/**
	 * @return the propertyDescription
	 */
	public String getPropertyDescription() {
		return propertyDescription;
	}
	/**
	 * @param propertyDescription the propertyDescription to set
	 */
	public void setPropertyDescription(final String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}
	/**
	 * @return the propertyType
	 */
	public String getPropertyType() {
		return propertyType;
	}
	/**
	 * @param propertyType the propertyType to set
	 */
	public void setPropertyType(final String propertyType) {
		this.propertyType = propertyType;
	}
	/**
	 * @return the conditionCode
	 */
	public String getConditionCode() {
		return conditionCode;
	}
	/**
	 * @param conditionCode the conditionCode to set
	 */
	public void setConditionCode(final String conditionCode) {
		this.conditionCode = conditionCode;
	}
	/**
	 * @return the colorName
	 */
	public String getColorName() {
		return colorName;
	}
	/**
	 * @param colorName the colorName to set
	 */
	public void setColorName(final String colorName) {
		this.colorName = colorName;
	}
	/**
	 * @return the makeType
	 */
	public String getMakeType() {
		return makeType;
	}
	/**
	 * @param makeType the makeType to set
	 */
	public void setMakeType(final String makeType) {
		this.makeType = makeType;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the verifyFlagTemp
	 */
	public Boolean getVerifyFlagTemp() {
		return verifyFlagTemp;
	}
	/**
	 * @param verifyFlagTemp the verifyFlagTemp to set
	 */
	public void setVerifyFlagTemp(final Boolean verifyFlagTemp) {
		this.verifyFlagTemp = verifyFlagTemp;
	}
	public String getNbtDspDescription() {
		return nbtDspDescription;
	}
	public void setNbtDspDescription(final String nbtDspDescription) {
		this.nbtDspDescription = nbtDspDescription;
	}

	/**
	 * @return the disposedToPersonIdDes
	 */
	public String getDisposedToPersonIdDes() {
		return disposedToPersonIdDes;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	/**
	 * @param disposedToPersonIdDes the disposedToPersonIdDes to set
	 */
	public void setDisposedToPersonIdDes(String disposedToPersonIdDes) {
		this.disposedToPersonIdDes = disposedToPersonIdDes;
	}

	public String getActionReason() {
		return actionReason;
	}

	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}

	@Override
	public String toString() {
		return "OffenderPptyItemTxns [propertyItemTxnId=" + propertyItemTxnId + ", offenderBookId=" + offenderBookId
				+ ", propertyItemSeq=" + propertyItemSeq + ", eventSeq=" + eventSeq + ", fromStatusCode="
				+ fromStatusCode + ", toStatusCode=" + toStatusCode + ", createDate=" + createDate + ", verifyFlagTemp="
				+ verifyFlagTemp + ", createUserId=" + createUserId + ", commentText=" + commentText + ", agyLocId="
				+ agyLocId + ", disposedToOffenderFlag=" + disposedToOffenderFlag + ", verificationFlag="
				+ verificationFlag + ", verifyFlag=" + verifyFlag + ", disposedToCorpId=" + disposedToCorpId
				+ ", disposedToPerson=" + disposedToPerson + ", propertyContainerId=" + propertyContainerId
				+ ", propertyContainerTxnId=" + propertyContainerTxnId + ", toPropertyContainerId="
				+ toPropertyContainerId + ", disposedToPersonId=" + disposedToPersonId + ", quantity=" + quantity
				+ ", color=" + color + ", make=" + make + ", serialNo=" + serialNo + ", createDatetime="
				+ createDatetime + ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId
				+ ", sealFlag=" + sealFlag + ", inserted=" + inserted + ", errorMessage=" + errorMessage
				+ ", propertyDescription=" + propertyDescription + ", propertyType=" + propertyType + ", conditionCode="
				+ conditionCode + ", colorName=" + colorName + ", makeType=" + makeType + ", disposedToCorpName="
				+ disposedToCorpName + ", nbtDspDescription=" + nbtDspDescription + ", disposedToPersonIdDes="
				+ disposedToPersonIdDes + "]";
	}

 
}