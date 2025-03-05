package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.Images;
/**
 * Class OffenderPptyItems
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderPptyItems extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("propertyItemSeq")
	private Integer propertyItemSeq;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	
	@JsonProperty("propertyType")
	private String propertyType;
	
	@JsonProperty("propertyDescription")
	private String propertyDescription;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("statusCode")
	private String statusCode;
	
	@JsonProperty("newStatusCode")
	private String newStatusCode;
	
	@JsonProperty("conditionCode")
	private String conditionCode;
	
	@JsonProperty("receivedFrom")
	private String receivedFrom;
	
	@JsonProperty("propertyContainerId")
	private Integer propertyContainerId;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("confirmFlag")
	private String confirmFlag;
	
	@JsonProperty("disposedToCorpId")
	private Integer disposedToCorpId;
	
	@JsonProperty("disposedToOffenderFlag")
	private String disposedToOffenderFlag;
	
	@JsonProperty("disposedToPerson")
	private String disposedToPerson;
	
	@JsonProperty("disposedToPersonId")
	private Integer disposedToPersonId;
	
	@JsonProperty("color")
	private String color;
	
	@JsonProperty("make")
	private String make;
	
	@JsonProperty("serialNo")
	private String serialNo;
	
	@JsonProperty("quantity")
	private Integer quantity;
	
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
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("bookingNumber")
    private String bookingNumber ;
    
    @JsonProperty("disableRow")
    private String disableRow ;
    
    @JsonProperty("propertyTypeDesc")
	private String propertyTypeDesc;
    
	@JsonProperty("internalLocationId")
	private Integer internalLocationId;
	
	@JsonProperty("dspDescription")
	private String dspDescription;
	
	private String pptyDescription;
	private String groupId;
	
	@JsonProperty("imageUrl")
	private String imageUrl;
	
	@JsonProperty("imageId")
	private BigDecimal  imageId; 
	
	@JsonProperty("images")
	private List<Images> images;
	
	@JsonProperty("imageFlag")
	private String imageFlag;
	
	@JsonProperty("propertyOnlyFlag")
	private String propertyOnlyFlag;
	
	@JsonProperty("contdes")
	private String contdes;
	
	@JsonProperty("activeFlag")
	private String activeFlag;
	
	@JsonProperty("proposedDisposalDate")
	private Date proposedDisposalDate;
	
	@JsonProperty("offInternalLocationId")
	private Number offInternalLocationId;
	
	@JsonProperty("containerCode")
	private String containerCode;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("sealMark")
	private String sealMark;
	
	@JsonProperty("trnFromAgyLocId")
	private String trnFromAgyLocId;
	
	@JsonProperty("propertyStorageId")
	private String propertyStorageId;
	
	@JsonProperty("propertyValue")
	private Double propertyValue;
	
	@JsonProperty("propertySize")
	private String propertySize;
	
	@JsonProperty("cancelReason")
	private String cancelReason;
	
	
	@JsonProperty("cancelLocation")
	private String cancelLocation;
	
	@JsonProperty("actionCode")
	private String actionCode;
	
	@JsonProperty("actionReason")
	private String actionReason;
	
	@JsonProperty("conditionCodeDesc")
	private String conditionCodeDesc;
	
	public String getConditionCodeDesc() {
		return conditionCodeDesc;
	}

	public void setConditionCodeDesc(String conditionCodeDesc) {
		this.conditionCodeDesc = conditionCodeDesc;
	}
	
	public String getPropertyOnlyFlag() {
		return propertyOnlyFlag;
	}

	public void setPropertyOnlyFlag(String propertyOnlyFlag) {
		this.propertyOnlyFlag = propertyOnlyFlag;
	}

	public String getContdes() {
		return contdes;
	}

	public void setContdes(String contdes) {
		this.contdes = contdes;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getProposedDisposalDate() {
		return proposedDisposalDate;
	}

	public void setProposedDisposalDate(Date proposedDisposalDate) {
		this.proposedDisposalDate = proposedDisposalDate;
	}

	public Number getOffInternalLocationId() {
		return offInternalLocationId;
	}

	public void setOffInternalLocationId(Number offInternalLocationId) {
		this.offInternalLocationId = offInternalLocationId;
	}

	public String getContainerCode() {
		return containerCode;
	}

	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getSealMark() {
		return sealMark;
	}

	public void setSealMark(String sealMark) {
		this.sealMark = sealMark;
	}

	public String getTrnFromAgyLocId() {
		return trnFromAgyLocId;
	}

	public void setTrnFromAgyLocId(String trnFromAgyLocId) {
		this.trnFromAgyLocId = trnFromAgyLocId;
	}

	public String getPropertyStorageId() {
		return propertyStorageId;
	}

	public void setPropertyStorageId(String propertyStorageId) {
		this.propertyStorageId = propertyStorageId;
	}

	public String getDisposedTo() {
		return disposedTo;
	}

	public void setDisposedTo(String disposedTo) {
		this.disposedTo = disposedTo;
	}

	public String getDisposedToName() {
		return disposedToName;
	}

	public void setDisposedToName(String disposedToName) {
		this.disposedToName = disposedToName;
	}

	public String getLocdes() {
		return locdes;
	}

	public void setLocdes(String locdes) {
		this.locdes = locdes;
	}

	@JsonProperty("disposedTo")
	private String disposedTo;
	
	@JsonProperty("disposedToName")
	private String disposedToName;
	
	@JsonProperty("locdes")
	private String locdes;
	
	
	public OffenderPptyItems() {
    	// OffenderPptyItems
    }

    /**
	 * @return the imageFlag
	 */
	public String getImageFlag() {
		return imageFlag;
	}

	/**
	 * @param imageFlag the imageFlag to set
	 */
	public void setImageFlag(String imageFlag) {
		this.imageFlag = imageFlag;
	}
	/**
	 * @return the pptyDescription
	 */
	public String getPptyDescription() {
		return pptyDescription;
	}

	/**
	 * @param pptyDescription the pptyDescription to set
	 */
	public void setPptyDescription(final String pptyDescription) {
		this.pptyDescription = pptyDescription;
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
	 * @return the internalLocationId
	 */
	public Integer getInternalLocationId() {
		return internalLocationId;
	}

	/**
	 * @param internalLocationId
	 *            the internalLocationId to set
	 */
	public void setInternalLocationId(Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}
	
	/**
	 * @return the newStatusCode
	 */
	public String getNewStatusCode() {
		return newStatusCode;
	}

	/**
	 * @param newStatusCode
	 *            the newStatusCode to set
	 */
	public void setNewStatusCode(final String newStatusCode) {
		this.newStatusCode = newStatusCode;
	}
	
	/**
	 * @return the propertyItemSeq
	 */
	public Integer getPropertyItemSeq() {
		return propertyItemSeq;
	}

	/**
	 * @param propertyItemSeq
	 *            the propertyItemSeq to set
	 */
	public void setPropertyItemSeq(final Integer propertyItemSeq) {
		this.propertyItemSeq = propertyItemSeq;
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
	 * @return the propertyType
	 */
	public String getPropertyType() {
		return propertyType;
	}

	/**
	 * @param propertyType
	 *            the propertyType to set
	 */
	public void setPropertyType(final String propertyType) {
		this.propertyType = propertyType;
	}

	/**
	 * @return the propertyDescription
	 */
	public String getPropertyDescription() {
		return propertyDescription;
	}

	/**
	 * @param propertyDescription
	 *            the propertyDescription to set
	 */
	public void setPropertyDescription(final String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(final String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the conditionCode
	 */
	public String getConditionCode() {
		return conditionCode;
	}

	/**
	 * @param conditionCode
	 *            the conditionCode to set
	 */
	public void setConditionCode(final String conditionCode) {
		this.conditionCode = conditionCode;
	}

	/**
	 * @return the receivedFrom
	 */
	public String getReceivedFrom() {
		return receivedFrom;
	}

	/**
	 * @param receivedFrom
	 *            the receivedFrom to set
	 */
	public void setReceivedFrom(final String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	/**
	 * @return the propertyContainerId
	 */
	public Integer getPropertyContainerId() {
		return propertyContainerId;
	}

	/**
	 * @param propertyContainerId
	 *            the propertyContainerId to set
	 */
	public void setPropertyContainerId(final Integer propertyContainerId) {
		this.propertyContainerId = propertyContainerId;
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
	 * @return the confirmFlag
	 */
	public String getConfirmFlag() {
		return confirmFlag;
	}

	/**
	 * @param confirmFlag
	 *            the confirmFlag to set
	 */
	public void setConfirmFlag(final String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	/**
	 * @return the disposedToCorpId
	 */
	public Integer getDisposedToCorpId() {
		return disposedToCorpId;
	}

	/**
	 * @param disposedToCorpId
	 *            the disposedToCorpId to set
	 */
	public void setDisposedToCorpId(final Integer disposedToCorpId) {
		this.disposedToCorpId = disposedToCorpId;
	}

	/**
	 * @return the disposedToOffenderFlag
	 */
	public String getDisposedToOffenderFlag() {
		return disposedToOffenderFlag;
	}

	/**
	 * @param disposedToOffenderFlag
	 *            the disposedToOffenderFlag to set
	 */
	public void setDisposedToOffenderFlag(final String disposedToOffenderFlag) {
		this.disposedToOffenderFlag = disposedToOffenderFlag;
	}

	/**
	 * @return the disposedToPerson
	 */
	public String getDisposedToPerson() {
		return disposedToPerson;
	}

	/**
	 * @param disposedToPerson
	 *            the disposedToPerson to set
	 */
	public void setDisposedToPerson(final String disposedToPerson) {
		this.disposedToPerson = disposedToPerson;
	}

	/**
	 * @return the disposedToPersonId
	 */
	public Integer getDisposedToPersonId() {
		return disposedToPersonId;
	}

	/**
	 * @param disposedToPersonId
	 *            the disposedToPersonId to set
	 */
	public void setDisposedToPersonId(final Integer disposedToPersonId) {
		this.disposedToPersonId = disposedToPersonId;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(final String color) {
		this.color = color;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make
	 *            the make to set
	 */
	public void setMake(final String make) {
		this.make = make;
	}

	/**
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo
	 *            the serialNo to set
	 */
	public void setSerialNo(final String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
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
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the bookingNumber
	 */
	public String getBookingNumber() {
		return bookingNumber;
	}

	/**
	 * @param bookingNumber
	 *            the bookingNumber to set
	 */
	public void setBookingNumber(final String bookingNumber) {
		this.bookingNumber = bookingNumber;
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
	 * @return the disableRow
	 */
	public String getDisableRow() {
		return disableRow;
	}

	/**
	 * @param disableRow
	 *            the disableRow to set
	 */
	public void setDisableRow(final String disableRow) {
		this.disableRow = disableRow;
	}

	public String getPropertyTypeDesc() {
		return propertyTypeDesc;
	}

	public void setPropertyTypeDesc(String propertyTypeDesc) {
		this.propertyTypeDesc = propertyTypeDesc;
	}
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getImageId() {
		return imageId;
	}

	public void setImageId(BigDecimal imageId) {
		this.imageId = imageId;
	}

	public List<Images> getImages() {
		return images;
	}
	
	public void setImages(List<Images> images) {
		this.images = images;
	}
	
	public Double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Double propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getPropertySize() {
		return propertySize;
	}

	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getCancelLocation() {
		return cancelLocation;
	}

	public void setCancelLocation(String cancelLocation) {
		this.cancelLocation = cancelLocation;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionReason() {
		return actionReason;
	}

	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}

	@Override
	public String toString() {
		return "OffenderPptyItems [propertyItemSeq=" + propertyItemSeq + ", offenderBookId=" + offenderBookId
				+ ", propertyType=" + propertyType + ", propertyDescription=" + propertyDescription + ", agyLocId="
				+ agyLocId + ", statusCode=" + statusCode + ", newStatusCode=" + newStatusCode + ", conditionCode="
				+ conditionCode + ", receivedFrom=" + receivedFrom + ", propertyContainerId=" + propertyContainerId
				+ ", commentText=" + commentText + ", confirmFlag=" + confirmFlag + ", disposedToCorpId="
				+ disposedToCorpId + ", disposedToOffenderFlag=" + disposedToOffenderFlag + ", disposedToPerson="
				+ disposedToPerson + ", disposedToPersonId=" + disposedToPersonId + ", color=" + color + ", make="
				+ make + ", serialNo=" + serialNo + ", quantity=" + quantity + ", createDatetime=" + createDatetime
				+ ", createUserId=" + createUserId + ", modifyDatetime=" + modifyDatetime + ", modifyUserId="
				+ modifyUserId + ", sealFlag=" + sealFlag + ", inserted=" + inserted + ", errorMessage=" + errorMessage
				+ ", offenderIdDisplay=" + offenderIdDisplay + ", bookingNumber=" + bookingNumber + ", disableRow="
				+ disableRow + ", propertyTypeDesc=" + propertyTypeDesc + ", internalLocationId=" + internalLocationId
				+ ", dspDescription=" + dspDescription + ", pptyDescription=" + pptyDescription + ", groupId=" + groupId
				+ ", imageUrl=" + imageUrl + ", imageId=" + imageId + ", images=" + images + ", imageFlag=" + imageFlag
				+ ", propertyOnlyFlag=" + propertyOnlyFlag + ", contdes=" + contdes + ", activeFlag=" + activeFlag
				+ ", proposedDisposalDate=" + proposedDisposalDate + ", offInternalLocationId=" + offInternalLocationId
				+ ", containerCode=" + containerCode + ", expiryDate=" + expiryDate + ", sealMark=" + sealMark
				+ ", trnFromAgyLocId=" + trnFromAgyLocId + ", propertyStorageId=" + propertyStorageId + ", disposedTo="
				+ disposedTo + ", disposedToName=" + disposedToName + ", locdes=" + locdes + "]";
	}
	
 }