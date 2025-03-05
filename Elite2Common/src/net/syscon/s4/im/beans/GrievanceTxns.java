package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class GrievanceTxns extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("grievType")
	private String grievType;
	@JsonProperty("txnType")
	private String txnType;
	@JsonProperty("description")
	private String description;
	@JsonProperty("daysRespond")
	private Long daysRespond;
	@JsonProperty("docRspFlag")
	private String docRspFlag;
	@JsonProperty("offRspFlag")
	private String offRspFlag;
	@JsonProperty("igRspFlag")
	private String igRspFlag;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("listSeq")
	private Long listSeq;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	private boolean inserted;
	@JsonProperty("code")
	private String code;
	@JsonProperty("returnValue")
	private int returnValue;
	@JsonProperty("deleteRecordCount")
	private int deleteRecordCount;

	@JsonProperty("reasonsTabSecurity")
	private String reasonsTabSecurity;
	@JsonProperty("txnTabSecurity")
	private String txnTabSecurity;
	@JsonProperty("checkFlag")
	private String checkFlag;
	@JsonProperty("offFlag")
	private String offFlag;
	@JsonProperty("agyFlag")
	private String agyFlag;
	@JsonProperty("supFlag")
	private String supFlag;
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	/**
	 * Creates new GrievanceTxns class Object
	 */
	public GrievanceTxns() {
		// GrievanceTxns
	}

	public String getReasonsTabSecurity() {
		return reasonsTabSecurity;
	}

	public void setReasonsTabSecurity(final String reasonsTabSecurity) {
		this.reasonsTabSecurity = reasonsTabSecurity;
	}

	public String getTxnTabSecurity() {
		return txnTabSecurity;
	}

	public void setTxnTabSecurity(final String txnTabSecurity) {
		this.txnTabSecurity = txnTabSecurity;
	}

	public int getDeleteRecordCount() {
		return deleteRecordCount;
	}

	public void setDeleteRecordCount(final int deleteRecordCount) {
		this.deleteRecordCount = deleteRecordCount;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(final int returnValue) {
		this.returnValue = returnValue;
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
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType
	 *            the txnType to set
	 */
	public void setTxnType(final String txnType) {
		this.txnType = txnType;
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
	 * @return the daysRespond
	 */
	public Long getDaysRespond() {
		return daysRespond;
	}

	/**
	 * @param daysRespond
	 *            the daysRespond to set
	 */
	public void setDaysRespond(final Long daysRespond) {
		this.daysRespond = daysRespond;
	}

	/**
	 * @return the docRspFlag
	 */
	public String getDocRspFlag() {
		return docRspFlag;
	}

	/**
	 * @param docRspFlag
	 *            the docRspFlag to set
	 */
	public void setDocRspFlag(final String docRspFlag) {
		this.docRspFlag = docRspFlag;
	}

	/**
	 * @return the offRspFlag
	 */
	public String getOffRspFlag() {
		return offRspFlag;
	}

	/**
	 * @param offRspFlag
	 *            the offRspFlag to set
	 */
	public void setOffRspFlag(final String offRspFlag) {
		this.offRspFlag = offRspFlag;
	}

	/**
	 * @return the igRspFlag
	 */
	public String getIgRspFlag() {
		return igRspFlag;
	}

	/**
	 * @param igRspFlag
	 *            the igRspFlag to set
	 */
	public void setIgRspFlag(final String igRspFlag) {
		this.igRspFlag = igRspFlag;
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
	public Long getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final Long listSeq) {
		this.listSeq = listSeq;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(final String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getOffFlag() {
		return offFlag;
	}

	public void setOffFlag(final String offFlag) {
		this.offFlag = offFlag;
	}

	public String getAgyFlag() {
		return agyFlag;
	}

	public void setAgyFlag(final String agyFlag) {
		this.agyFlag = agyFlag;
	}

	public String getSupFlag() {
		return supFlag;
	}

	public void setSupFlag(final String supFlag) {
		this.supFlag = supFlag;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

}