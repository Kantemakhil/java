package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CaseloadAdmOtherProfiles extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("youngOffenderAge")
	private Integer youngOffenderAge;
	@JsonProperty("messageNumber")
	private Integer messageNumber;
	@JsonProperty("applnCode")
	private String applnCode;
	@JsonProperty("trustAccountFlag")
	private String trustAccountFlag;
	@JsonProperty("livingUnitId")
	private Integer livingUnitId;
	@JsonProperty("livingUnitDesc")
	private String livingUnitDesc;
	@JsonProperty("agyLocId")
	private String agyLocId;
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
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("code")
	private String code;
	@JsonProperty("description")
	private String description;
	@JsonProperty("dspDescription")
	private String dspDescription;
	@JsonProperty("livUnitId")
	private String livUnitId;
	@JsonProperty("livUnitCode")
	private String livUnitCode;
	@JsonProperty("rowId")
	private Integer rowId;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("canDisplay")
	 private Boolean canDisplay = true;
	
	@JsonProperty("avalibleBedsInLocation")
	private Integer avalibleBedsInLocation;

	public Integer getAvalibleBedsInLocation() {
		return avalibleBedsInLocation;
	}

	public void setAvalibleBedsInLocation(Integer avalibleBedsInLocation) {
		this.avalibleBedsInLocation = avalibleBedsInLocation;
	}

	/**
	 * Creates new StaffMembers class Object
	 */
	public CaseloadAdmOtherProfiles() {
		// CaseloadAdmOtherProfiles
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId
	 *            the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the youngOffenderAge
	 */
	public Integer getYoungOffenderAge() {
		return youngOffenderAge;
	}

	/**
	 * @param youngOffenderAge
	 *            the youngOffenderAge to set
	 */
	public void setYoungOffenderAge(final Integer youngOffenderAge) {
		this.youngOffenderAge = youngOffenderAge;
	}

	/**
	 * @return the messageNumber
	 */
	public Integer getMessageNumber() {
		return messageNumber;
	}

	/**
	 * @param messageNumber
	 *            the messageNumber to set
	 */
	public void setMessageNumber(final Integer messageNumber) {
		this.messageNumber = messageNumber;
	}

	/**
	 * @return the applnCode
	 */
	public String getApplnCode() {
		return applnCode;
	}

	/**
	 * @param applnCode
	 *            the applnCode to set
	 */
	public void setApplnCode(final String applnCode) {
		this.applnCode = applnCode;
	}

	/**
	 * @return the trustAccountFlag
	 */
	public String getTrustAccountFlag() {
		return trustAccountFlag;
	}

	/**
	 * @param trustAccountFlag
	 *            the trustAccountFlag to set
	 */
	public void setTrustAccountFlag(final String trustAccountFlag) {
		this.trustAccountFlag = trustAccountFlag;
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
	 * @return the modifyDateTime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
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

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDspDescription() {
		return dspDescription;
	}

	public void setDspDescription(final String dspDescription) {
		this.dspDescription = dspDescription;
	}

	public String getLivUnitId() {
		return livUnitId;
	}

	public void setLivUnitId(final String livUnitId) {
		this.livUnitId = livUnitId;
	}

	public String getLivUnitCode() {
		return livUnitCode;
	}

	public void setLivUnitCode(final String livUnitCode) {
		this.livUnitCode = livUnitCode;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(final Integer rowId) {
		this.rowId = rowId;
	}

	public String getLivingUnitDesc() {
		return livingUnitDesc;
	}

	public void setLivingUnitDesc(final String livingUnitDesc) {
		this.livingUnitDesc = livingUnitDesc;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}


}
