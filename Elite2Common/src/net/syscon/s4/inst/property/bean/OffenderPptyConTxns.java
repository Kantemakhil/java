package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderPptyConTxns
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderPptyConTxns extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("propertyContainerTxnId")
	private Integer propertyContainerTxnId;
	@JsonProperty("propertyContainerId")
	private Integer propertyContainerId;
	@JsonProperty("createDate")
	private Date createDate;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("actionCode")
	private String actionCode;
	@JsonProperty("commentText")
	private String commentText;
	@JsonProperty("sealMark")
	private String sealMark;
	@JsonProperty("internalLocationId")
	private Integer internalLocationId;
	@JsonProperty("trnToAgyLocId")
	private String trnToAgyLocId;
	@JsonProperty("trnFromAgyLocId")
	private String trnFromAgyLocId;
	@JsonProperty("createDateTime")
	private Timestamp createDateTime;
	@JsonProperty("modifyDateTime")
	private Timestamp modifyDateTime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("nbtAgyLocDesc")
	private String nbtAgyLocDesc;
	@JsonProperty("nbtFromAgyDesc")
	private String nbtFromAgyDesc;
	@JsonProperty("nbtToAgyDesc")
	private String nbtToAgyDesc;
	@JsonProperty("nbtCreateDate")
	private Date nbtCreateDate;
	
	@JsonProperty("actionReason")
	private String actionReason;


	public OffenderPptyConTxns() {
		// OffenderPptyConTxns
	}

	/**
	 * @return the nbtCreateDate
	 */
	public Date getNbtCreateDate() {
		return nbtCreateDate;
	}

	/**
	 * @param nbtCreateDate
	 *            the nbtCreateDate to set
	 */
	public void setNbtCreateDate(final Date nbtCreateDate) {
		this.nbtCreateDate = nbtCreateDate;
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
	 * @return the nbtAgyLocDesc
	 */
	public String getNbtAgyLocDesc() {
		return nbtAgyLocDesc;
	}

	/**
	 * @param nbtAgyLocDesc
	 *            the nbtAgyLocDesc to set
	 */
	public void setNbtAgyLocDesc(final String nbtAgyLocDesc) {
		this.nbtAgyLocDesc = nbtAgyLocDesc;
	}

	/**
	 * @return the nbtFromAgyDesc
	 */
	public String getNbtFromAgyDesc() {
		return nbtFromAgyDesc;
	}

	/**
	 * @param nbtFromAgyDesc
	 *            the nbtFromAgyDesc to set
	 */
	public void setNbtFromAgyDesc(final String nbtFromAgyDesc) {
		this.nbtFromAgyDesc = nbtFromAgyDesc;
	}

	/**
	 * @return the nbtToAgyDesc
	 */
	public String getNbtToAgyDesc() {
		return nbtToAgyDesc;
	}

	/**
	 * @param nbtToAgyDesc
	 *            the nbtToAgyDesc to set
	 */
	public void setNbtToAgyDesc(final String nbtToAgyDesc) {
		this.nbtToAgyDesc = nbtToAgyDesc;
	}

	/**
	 * @return the propertyContainerTxnId
	 */
	public Integer getPropertyContainerTxnId() {
		return propertyContainerTxnId;
	}

	/**
	 * @param propertyContainerTxnId
	 *            the propertyContainerTxnId to set
	 */
	public void setPropertyContainerTxnId(final Integer propertyContainerTxnId) {
		this.propertyContainerTxnId = propertyContainerTxnId;
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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
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
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
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
	 * @return the actionCode
	 */
	public String getActionCode() {
		return actionCode;
	}

	/**
	 * @param actionCode
	 *            the actionCode to set
	 */
	public void setActionCode(final String actionCode) {
		this.actionCode = actionCode;
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
	 * @return the createDateTime
	 */
	public Timestamp getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(final Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the modifyDateTime
	 */
	public Timestamp getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(final Timestamp modifyDateTime) {
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

	public String getActionReason() {
		return actionReason;
	}

	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}
}