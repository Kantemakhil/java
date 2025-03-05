package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class GrievanceReasons extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("description")
	private String description;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("modifiedDatetime")
	private Date modifiedDatetime;
	@JsonProperty("modifiedUserId")
	private String modifiedUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("grievType")
	private String grievType;
	@JsonProperty("grievReasonCode")
	private String grievReasonCode;
	@JsonProperty("returnValue")
	private int returnValue;
	@JsonProperty("deleteCountRecord")
	private int deleteCountRecord;
	@JsonProperty("checkFlag")
	private String checkFlag;

	/**
	 * Creates new GrievanceReasons class Object
	 */
	public GrievanceReasons() {
		// GrievanceReasons
	}

	public int getDeleteCountRecord() {
		return deleteCountRecord;
	}

	public void setDeleteCountRecord(int deleteCountRecord) {
		this.deleteCountRecord = deleteCountRecord;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
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
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the modifiedDatetime
	 */
	public Date getModifiedDatetime() {
		return modifiedDatetime;
	}

	/**
	 * @param modifiedDatetime
	 *            the modifiedDatetime to set
	 */
	public void setModifiedDatetime(final Date modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	/**
	 * @return the modifiedUserId
	 */
	public String getModifiedUserId() {
		return modifiedUserId;
	}

	/**
	 * @param modifiedUserId
	 *            the modifiedUserId to set
	 */
	public void setModifiedUserId(final String modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
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
	 * @return the grievType
	 */
	public String getGrievType() {
		return grievType;
	}

	/**
	 * @param grievType
	 *            the grievType to set
	 */
	public void setGrievType(final String grievType) {
		this.grievType = grievType;
	}

	/**
	 * @return the grievReasonCode
	 */
	public String getGrievReasonCode() {
		return grievReasonCode;
	}

	/**
	 * @param grievReasonCode
	 *            the grievReasonCode to set
	 */
	public void setGrievReasonCode(final String grievReasonCode) {
		this.grievReasonCode = grievReasonCode;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(final String checkFlag) {
		this.checkFlag = checkFlag;
	}

}
