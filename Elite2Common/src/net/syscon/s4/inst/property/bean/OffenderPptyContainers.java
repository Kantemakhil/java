package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.Images;

/**
 * Class OffenderPptyContainers
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderPptyContainers extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("propertyOnlyFlag")
	private String propertyOnlyFlag;
	
	@JsonProperty("propertyContainerId")
	private Integer propertyContainerId;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("caseLoadId")
	private String caseLoadId;
	
	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("activeFlagTemp")
	private boolean activeFlagTemp;
	
	@JsonProperty("proposedDisposalDate")
	private Date proposedDisposalDate;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("internalLocationId")
	private Integer internalLocationId;
	
	@JsonProperty("containerCode")
	private String containerCode;
	
	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("sealMark")
	private String sealMark;
	
	@JsonProperty("trnFromAgyLocId")
	private String trnFromAgyLocId;
	
	@JsonProperty("trnToAgyLocId")
	private String trnToAgyLocId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("propertyStorageId")
	private Integer propertyStorageId;
	
	@JsonProperty("disposedTo")
	private String disposedTo;
	
	@JsonProperty("disposedToName")
	private String disposedToName;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("inserted")
	private boolean inserted;
	
	@JsonProperty("dspDescription")
	private String dspDescription;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("offenderName")
	private String offenderName;
	
	@JsonProperty("propertyItemSeq")
	private Integer propertyItemSeq;
	
	@JsonProperty("pptyItemLength")
	private Integer pptyItemLength;
	
	@JsonProperty("containerValue")
	private Integer containerValue;
	
	@JsonProperty("gvAgyLocId")
	private String gvAgyLocId;
	
	@JsonProperty("statusCode")
	private String statusCode;
	
	@JsonProperty("itemsForContainer")
	private List<OffenderPptyItems> itemsForContainer;
	
	@JsonProperty("containerDescription")
	private String containerDescription;
	
	@JsonProperty("imageUrl")
	private String imageUrl;
	
	@JsonProperty("imageId")
	private BigDecimal  imageId; 
	
	@JsonProperty("images")
	private List<Images> images;
	
	@JsonProperty("locationDescription")
	private String locationDescription;
	
	@JsonProperty("propertySize")
	private String propertySize;
	
	@JsonProperty("propertyValue")
	private Double propertyValue;
	
	@JsonProperty("cancelTransfer")
	private String cancelTransfer;
	
	@JsonProperty("cancelReason")
	private String cancelReason;
	
	
	@JsonProperty("cancelLocation")
	private String cancelLocation;
	
	@JsonProperty("rejectFlag")
	private String rejectFlag;
	
	@JsonProperty("rejectReason")
	private String rejectReason;
	
	@JsonProperty("internalLocationCode")
	private String internalLocationCode;
	
	@JsonProperty("actionCode")
	private String actionCode;
	
	@JsonProperty("actionReason")
	private String actionReason;
	
	public OffenderPptyContainers() {
		// OffenderPptyContainers
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
	public void setPropertyItemSeq(Integer propertyItemSeq) {
		this.propertyItemSeq = propertyItemSeq;
	}


	/**
	 * @return the dspDescription
	 */
	public String getDspDescription() {
		return dspDescription;
	}

	/**
	 * @param dspDescription
	 *            the dspDescription to set
	 */
	public void setDspDescription(final String dspDescription) {
		this.dspDescription = dspDescription;
	}

	/**
	 * @return the propertyOnlyFlag
	 */
	public String getPropertyOnlyFlag() {
		return propertyOnlyFlag;
	}

	/**
	 * @param propertyOnlyFlag
	 *            the propertyOnlyFlag to set
	 */
	public void setPropertyOnlyFlag(final String propertyOnlyFlag) {
		this.propertyOnlyFlag = propertyOnlyFlag;
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

	public boolean getActiveFlagTemp() {
		return activeFlagTemp;
	}

	public void setActiveFlagTemp(final boolean activeFlagTemp) {
		this.activeFlagTemp = activeFlagTemp;
	}

	/**
	 * @return the proposedDisposalDate
	 */
	public Date getProposedDisposalDate() {
		return proposedDisposalDate;
	}

	/**
	 * @param proposedDisposalDate
	 *            the proposedDisposalDate to set
	 */
	public void setProposedDisposalDate(final Date proposedDisposalDate) {
		this.proposedDisposalDate = proposedDisposalDate;
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
	 * @return the containerCode
	 */
	public String getContainerCode() {
		return containerCode;
	}

	/**
	 * @param containerCode
	 *            the containerCode to set
	 */
	public void setContainerCode(final String containerCode) {
		this.containerCode = containerCode;
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
	 * @return the sealMark
	 */
	public String getSealMark() {
		return sealMark;
	}

	/**
	 * @param sealMark
	 *            the sealMark to set
	 */
	public void setSealMark(final String sealMark) {
		this.sealMark = sealMark;
	}

	/**
	 * @return the trnFromAgyLocId
	 */
	public String getTrnFromAgyLocId() {
		return trnFromAgyLocId;
	}

	/**
	 * @param trnFromAgyLocId
	 *            the trnFromAgyLocId to set
	 */
	public void setTrnFromAgyLocId(final String trnFromAgyLocId) {
		this.trnFromAgyLocId = trnFromAgyLocId;
	}

	/**
	 * @return the trnToAgyLocId
	 */
	public String getTrnToAgyLocId() {
		return trnToAgyLocId;
	}

	/**
	 * @param trnToAgyLocId
	 *            the trnToAgyLocId to set
	 */
	public void setTrnToAgyLocId(final String trnToAgyLocId) {
		this.trnToAgyLocId = trnToAgyLocId;
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
	 * @return the propertyStorageId
	 */
	public Integer getPropertyStorageId() {
		return propertyStorageId;
	}

	/**
	 * @param propertyStorageId
	 *            the propertyStorageId to set
	 */
	public void setPropertyStorageId(final Integer propertyStorageId) {
		this.propertyStorageId = propertyStorageId;
	}

	/**
	 * @return the disposedTo
	 */
	public String getDisposedTo() {
		return disposedTo;
	}

	/**
	 * @param disposedTo
	 *            the disposedTo to set
	 */
	public void setDisposedTo(final String disposedTo) {
		this.disposedTo = disposedTo;
	}

	/**
	 * @return the disposedToName
	 */
	public String getDisposedToName() {
		return disposedToName;
	}

	/**
	 * @param disposedToName
	 *            the disposedToName to set
	 */
	public void setDisposedToName(final String disposedToName) {
		this.disposedToName = disposedToName;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
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
	 * @return the caseLoadId
	 */
	public String getCaseLoadId() {
		return caseLoadId;
	}

	/**
	 * @param caseLoadId the caseLoadId to set
	 */
	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the offenderName
	 */
	public String getOffenderName() {
		return offenderName;
	}

	/**
	 * @param offenderName the offenderName to set
	 */
	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}


	/**
	 * @return the containerValue
	 */
	public Integer getContainerValue() {
		return containerValue;
	}


	/**
	 * @param containerValue the containerValue to set
	 */
	public void setContainerValue(final Integer containerValue) {
		this.containerValue = containerValue;
	}


	/**
	 * @return the pptyItemLength
	 */
	public Integer getPptyItemLength() {
		return pptyItemLength;
	}


	/**
	 * @param pptyItemLength the pptyItemLength to set
	 */
	public void setPptyItemLength(final Integer pptyItemLength) {
		this.pptyItemLength = pptyItemLength;
	}


	/**
	 * @return the gvAgyLocId
	 */
	public String getGvAgyLocId() {
		return gvAgyLocId;
	}


	/**
	 * @param gvAgyLocId the gvAgyLocId to set
	 */
	public void setGvAgyLocId(final String gvAgyLocId) {
		this.gvAgyLocId = gvAgyLocId;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public List<OffenderPptyItems> getItemsForContainer() {
		return itemsForContainer;
	}


	public void setItemsForContainer(List<OffenderPptyItems> itemsForContainer) {
		this.itemsForContainer = itemsForContainer;
	}


	public String getContainerDescription() {
		return containerDescription;
	}


	public void setContainerDescription(String containerDescription) {
		this.containerDescription = containerDescription;
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


	public String getLocationDescription() {
		return locationDescription;
	}


	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}


	public String getPropertySize() {
		return propertySize;
	}


	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
	}


	public Double getPropertyValue() {
		return propertyValue;
	}


	public void setPropertyValue(Double propertyValue) {
		this.propertyValue = propertyValue;
	}


	public String getCancelTransfer() {
		return cancelTransfer;
	}


	public void setCancelTransfer(String cancelTransfer) {
		this.cancelTransfer = cancelTransfer;
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


	public String getRejectFlag() {
		return rejectFlag;
	}


	public void setRejectFlag(String rejectFlag) {
		this.rejectFlag = rejectFlag;
	}


	public String getRejectReason() {
		return rejectReason;
	}


	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}


	public String getInternalLocationCode() {
		return internalLocationCode;
	}


	public void setInternalLocationCode(String internalLocationCode) {
		this.internalLocationCode = internalLocationCode;
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
	
}