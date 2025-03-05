package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OicOffences extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("oicOffenceCode")
	@NotNull
	@Size(max = 12)
	private String oicOffenceCode;

	@JsonProperty("description")
	@NotNull
	@Size(max = 240)
	private String description;

	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("listSeq")
	private Integer listSeq;

	@JsonProperty("updateAllowedFlag")
	@NotNull
	@Size(max = 1)
	private String updateAllowedFlag;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("oicOffenceCategory")
	private String oicOffenceCategory;

	@JsonProperty("oicOffenceType")
	private String oicOffenceType;

	@JsonProperty("maxPenaltyMonths")
	private Integer maxPenaltyMonths;

	@JsonProperty("maxPenaltyDays")
	private Integer maxPenaltyDays;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("oicOffenceId")
	@NotNull
	private Integer oicOffenceId;

	@JsonProperty("startDate")
	@NotNull
	private Date startDate;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("chargeSeq")
	private BigDecimal chargeSeq;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("incidentDate")
	private Date incidentDate;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("reportText")
	 private String reportText;
	
	@JsonProperty("findingCode")
    private String findingCode;
	
	@JsonProperty("offenceType")
    private String offenceType;
	
	@JsonProperty("pStartDate")
	private Date pStartDate;

	@JsonProperty("pEndDate")
	private Date pEndDate;
	@JsonProperty("showStartDate")
	private String showStartDate;
	@JsonProperty("showEndDate")
	private String showEndDate;
	@JsonProperty("offenceTypeDesc")
	private String offenceTypeDesc;
	

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
	 * Creates new OicOffences class Object
	 */
	public OicOffences() {
		// OicOffences
	}

	/**
	 * @return the oicOffenceCode
	 */
	public String getOicOffenceCode() {
		return oicOffenceCode;
	}

	/**
	 * @param oicOffenceCode
	 *            the oicOffenceCode to set
	 */
	public void setOicOffenceCode(String oicOffenceCode) {
		this.oicOffenceCode = oicOffenceCode;
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
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the reportText
	 */
	public String getReportText() {
		return reportText;
	}

	/**
	 * @param reportText the reportText to set
	 */
	public void setReportText(String reportText) {
		this.reportText = reportText;
	}

	/**
	 * @return the findingCode
	 */
	public String getFindingCode() {
		return findingCode;
	}

	/**
	 * @param findingCode the findingCode to set
	 */
	public void setFindingCode(String findingCode) {
		this.findingCode = findingCode;
	}

	/**
	 * @return the offenceType
	 */
	public String getOffenceType() {
		return offenceType;
	}

	/**
	 * @param offenceType the offenceType to set
	 */
	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}

	/**
	 * @return the listSeq
	 */
	public Integer getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the updateAllowedFlag
	 */
	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	/**
	 * @param updateAllowedFlag
	 *            the updateAllowedFlag to set
	 */
	public void setUpdateAllowedFlag(String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
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
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	 * @return the oicOffenceCategory
	 */
	public String getOicOffenceCategory() {
		return oicOffenceCategory;
	}

	/**
	 * @param oicOffenceCategory
	 *            the oicOffenceCategory to set
	 */
	public void setOicOffenceCategory(String oicOffenceCategory) {
		this.oicOffenceCategory = oicOffenceCategory;
	}

	/**
	 * @return the oicOffenceType
	 */
	public String getOicOffenceType() {
		return oicOffenceType;
	}



	/**
	 * @param oicOffenceType
	 *            the oicOffenceType to set
	 */
	public void setOicOffenceType(String oicOffenceType) {
		this.oicOffenceType = oicOffenceType;
	}

	/**
	 * @return the maxPenaltyMonths
	 */
	public Integer getMaxPenaltyMonths() {
		return maxPenaltyMonths;
	}

	/**
	 * @param maxPenaltyMonths
	 *            the maxPenaltyMonths to set
	 */
	public void setMaxPenaltyMonths(Integer maxPenaltyMonths) {
		this.maxPenaltyMonths = maxPenaltyMonths;
	}

	/**
	 * @return the maxPenaltyDays
	 */
	public Integer getMaxPenaltyDays() {
		return maxPenaltyDays;
	}

	/**
	 * @param maxPenaltyDays
	 *            the maxPenaltyDays to set
	 */
	public void setMaxPenaltyDays(Integer maxPenaltyDays) {
		this.maxPenaltyDays = maxPenaltyDays;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
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
	 * @return the modifyDatetime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * @return the oicOffenceId
	 */
	public Integer getOicOffenceId() {
		return oicOffenceId;
	}

	/**
	 * @param oicOffenceId
	 *            the oicOffenceId to set
	 */
	public void setOicOffenceId(Integer oicOffenceId) {
		this.oicOffenceId = oicOffenceId;
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
	 * @return the chargeSeq
	 */
	public BigDecimal getChargeSeq() {
		return chargeSeq;
	}

	/**
	 * @param chargeSeq
	 *            the chargeSeq to set
	 */
	public void setChargeSeq(BigDecimal chargeSeq) {
		this.chargeSeq = chargeSeq;
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
	public void setInserted(boolean inserted) {
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
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the incidentDate
	 */
	public Date getIncidentDate() {
		return incidentDate;
	}

	/**
	 * @param incidentDate
	 *            the incidentDate to set
	 */
	public void setIncidentDate(Date incidentDate) {
		this.incidentDate = incidentDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getpStartDate() {
		return pStartDate;
	}

	public void setpStartDate(Date pStartDate) {
		this.pStartDate = pStartDate;
	}

	public Date getpEndDate() {
		return pEndDate;
	}

	public void setpEndDate(Date pEndDate) {
		this.pEndDate = pEndDate;
	}

	public String getShowStartDate() {
		return showStartDate;
	}

	public void setShowStartDate(String showStartDate) {
		this.showStartDate = showStartDate;
	}

	public String getShowEndDate() {
		return showEndDate;
	}

	public void setShowEndDate(String showEndDate) {
		this.showEndDate = showEndDate;
	}

	public String getOffenceTypeDesc() {
		return offenceTypeDesc;
	}

	public void setOffenceTypeDesc(String offenceTypeDesc) {
		this.offenceTypeDesc = offenceTypeDesc;
	}
	
	

}