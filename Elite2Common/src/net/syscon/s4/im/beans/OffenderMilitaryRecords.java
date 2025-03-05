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
public class OffenderMilitaryRecords extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("dischargeLocation")
	private String dischargeLocation;

	@JsonProperty("disciplinaryActionCode")
	private String disciplinaryActionCode;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("enlistmentLocation")
	private String enlistmentLocation;

	@JsonProperty("highestRankAttained")
	private String highestRankAttained;

	@JsonProperty("militaryBranchCode")
	@NotNull
	@Size(max = 12)
	private String militaryBranchCode;

	@JsonProperty("militaryDischargeCode")
	private String militaryDischargeCode;

	@JsonProperty("militaryRankCode")
	private String militaryRankCode;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("selectiveServicesFlag")
	@NotNull
	@Size(max = 1)
	private String selectiveServicesFlag;

	@JsonProperty("serviceNumber")
	private String serviceNumber;

	@JsonProperty("startDate")
	@NotNull
	private Date startDate;

	@JsonProperty("unitNumber")
	private String unitNumber;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("militarySeq")
	@NotNull
	private Long militarySeq;

	/**
	 * Creates new OffenderMilitaryRecords class Object
	 */
	public OffenderMilitaryRecords() {
		// OffenderMilitaryRecords
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
	public void setCreateDateTime(Date createDateTime) {
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
	public void setCreateUserId(String createUserId) {
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
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dischargeLocation
	 */
	public String getDischargeLocation() {
		return dischargeLocation;
	}

	/**
	 * @param dischargeLocation
	 *            the dischargeLocation to set
	 */
	public void setDischargeLocation(String dischargeLocation) {
		this.dischargeLocation = dischargeLocation;
	}

	/**
	 * @return the disciplinaryActionCode
	 */
	public String getDisciplinaryActionCode() {
		return disciplinaryActionCode;
	}

	/**
	 * @param disciplinaryActionCode
	 *            the disciplinaryActionCode to set
	 */
	public void setDisciplinaryActionCode(String disciplinaryActionCode) {
		this.disciplinaryActionCode = disciplinaryActionCode;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the enlistmentLocation
	 */
	public String getEnlistmentLocation() {
		return enlistmentLocation;
	}

	/**
	 * @param enlistmentLocation
	 *            the enlistmentLocation to set
	 */
	public void setEnlistmentLocation(String enlistmentLocation) {
		this.enlistmentLocation = enlistmentLocation;
	}

	/**
	 * @return the highestRankAttained
	 */
	public String getHighestRankAttained() {
		return highestRankAttained;
	}

	/**
	 * @param highestRankAttained
	 *            the highestRankAttained to set
	 */
	public void setHighestRankAttained(String highestRankAttained) {
		this.highestRankAttained = highestRankAttained;
	}

	/**
	 * @return the militaryBranchCode
	 */
	public String getMilitaryBranchCode() {
		return militaryBranchCode;
	}

	/**
	 * @param militaryBranchCode
	 *            the militaryBranchCode to set
	 */
	public void setMilitaryBranchCode(String militaryBranchCode) {
		this.militaryBranchCode = militaryBranchCode;
	}

	/**
	 * @return the militaryDischargeCode
	 */
	public String getMilitaryDischargeCode() {
		return militaryDischargeCode;
	}

	/**
	 * @param militaryDischargeCode
	 *            the militaryDischargeCode to set
	 */
	public void setMilitaryDischargeCode(String militaryDischargeCode) {
		this.militaryDischargeCode = militaryDischargeCode;
	}

	/**
	 * @return the militaryRankCode
	 */
	public String getMilitaryRankCode() {
		return militaryRankCode;
	}

	/**
	 * @param militaryRankCode
	 *            the militaryRankCode to set
	 */
	public void setMilitaryRankCode(String militaryRankCode) {
		this.militaryRankCode = militaryRankCode;
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
	public void setModifyDateTime(Date modifyDateTime) {
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
	public void setModifyUserId(String modifyUserId) {
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
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the selectiveServicesFlag
	 */
	public String getSelectiveServicesFlag() {
		return selectiveServicesFlag;
	}

	/**
	 * @param selectiveServicesFlag
	 *            the selectiveServicesFlag to set
	 */
	public void setSelectiveServicesFlag(String selectiveServicesFlag) {
		this.selectiveServicesFlag = selectiveServicesFlag;
	}

	/**
	 * @return the serviceNumber
	 */
	public String getServiceNumber() {
		return serviceNumber;
	}

	/**
	 * @param serviceNumber
	 *            the serviceNumber to set
	 */
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the unitNumber
	 */
	public String getUnitNumber() {
		return unitNumber;
	}

	/**
	 * @param unitNumber
	 *            the unitNumber to set
	 */
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the militarySeq
	 */
	public Long getMilitarySeq() {
		return militarySeq;
	}

	/**
	 * @param militarySeq
	 *            the militarySeq to set
	 */
	public void setMilitarySeq(Long militarySeq) {
		this.militarySeq = militarySeq;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
