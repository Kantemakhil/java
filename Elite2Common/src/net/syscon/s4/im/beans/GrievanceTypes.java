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
public class GrievanceTypes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("grievType")
	private String grievType;
	@JsonProperty("description")
	private String description;
	@JsonProperty("staffInvolvedFlag")
	private String staffInvolvedFlag;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("modifiedUserId")
	private String modifiedUserId;
	@JsonProperty("modifiedDatetime")
	private Date modifiedDatetime;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
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
	@JsonProperty("code")
	private String code;
	@JsonProperty("returnValue")
	private int returnValue;
	private Boolean canDisplay;
	
    private Integer roleId;
	private String createFlag;
	private String viewFlag;
	private String grievReasonCode;
    private String isSaved;
	
	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

	private boolean inserted;

	/**
	 * Creates new GrievanceTypes class Object
	 */
	public GrievanceTypes() {
		// GrievanceTypes
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
	 * @return the staffInvolvedFlag
	 */
	public String getStaffInvolvedFlag() {
		return staffInvolvedFlag;
	}

	/**
	 * @param staffInvolvedFlag
	 *            the staffInvolvedFlag to set
	 */
	public void setStaffInvolvedFlag(final String staffInvolvedFlag) {
		this.staffInvolvedFlag = staffInvolvedFlag;
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

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	public String getGrievReasonCode() {
		return grievReasonCode;
	}

	public void setGrievReasonCode(String grievReasonCode) {
		this.grievReasonCode = grievReasonCode;
	}

	
}