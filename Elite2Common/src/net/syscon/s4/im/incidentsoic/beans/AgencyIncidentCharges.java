package net.syscon.s4.im.incidentsoic.beans;

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
public class AgencyIncidentCharges extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId;

	@JsonProperty("chargeSeq")
	@NotNull
	private Integer chargeSeq;

	@JsonProperty("partySeq")
	@NotNull
	private Integer partySeq;

	@JsonProperty("oicChargeId")
	private String oicChargeId;

	@JsonProperty("findingCode")
	private String findingCode;

	@JsonProperty("guiltyEvidenceText")
	private String guiltyEvidenceText;

	@JsonProperty("reportText")
	private String reportText;

	@JsonProperty("evidenceDisposeText")
	private String evidenceDisposeText;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("lidsChargeNumber")
	private Integer lidsChargeNumber;

	@JsonProperty("chargedOicOffenceId")
	@NotNull
	private Integer chargedOicOffenceId;

	@JsonProperty("resultOicOffenceId")
	private Integer resultOicOffenceId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	private boolean inserted;
	private String errorMessage;
	
	@JsonProperty("chargedOicOffenceCode")
	private String chargedOicOffenceCode;

	@JsonProperty("offenceType")
	private String offenceType;

	@JsonProperty("offenceDesc")
	private String offenceDesc;

	@JsonProperty("dspCategory")
	private String dspCategory;
	
	@JsonProperty("oicOffenceType")
	private String oicOffenceType;

	/**
	 * Creates new AgencyIncidentCharges class Object
	 */
	public AgencyIncidentCharges() {
		// AgencyIncidentCharges
	}

	/**
	 * @param agencyIncidentId
	 *            agencyIncidentId to set
	 */
	public void setAgencyIncidentId(Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	/**
	 * return the agencyIncidentId
	 */
	public Integer getAgencyIncidentId() {
		return this.agencyIncidentId;
	}

	/**
	 * @param chargeSeq
	 *            chargeSeq to set
	 */
	public void setChargeSeq(Integer chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	/**
	 * return the chargeSeq
	 */
	public Integer getChargeSeq() {
		return this.chargeSeq;
	}

	/**
	 * @param partySeq
	 *            partySeq to set
	 */
	public void setPartySeq(final Integer partySeq) {
		this.partySeq = partySeq;
	}

	/**
	 * return the partySeq
	 */
	public Integer getPartySeq() {
		return this.partySeq;
	}

	/**
	 * @param oicChargeId
	 *            oicChargeId to set
	 */
	public void setOicChargeId( final String oicChargeId) {
		this.oicChargeId = oicChargeId;
	}

	/**
	 * return the oicChargeId
	 */
	public String getOicChargeId() {
		return this.oicChargeId;
	}

	/**
	 * @param findingCode
	 *            findingCode to set
	 */
	public void setFindingCode(final String findingCode) {
		this.findingCode = findingCode;
	}

	/**
	 * return the findingCode
	 */
	public String getFindingCode() {
		return this.findingCode;
	}

	/**
	 * @param guiltyEvidenceText
	 *            guiltyEvidenceText to set
	 */
	public void setGuiltyEvidenceText(final String guiltyEvidenceText) {
		this.guiltyEvidenceText = guiltyEvidenceText;
	}

	/**
	 * return the guiltyEvidenceText
	 */
	public String getGuiltyEvidenceText() {
		return this.guiltyEvidenceText;
	}

	/**
	 * @return the oicOffenceType
	 */
	public String getOicOffenceType() {
		return oicOffenceType;
	}

	/**
	 * @param oicOffenceType the oicOffenceType to set
	 */
	public void setOicOffenceType(String oicOffenceType) {
		this.oicOffenceType = oicOffenceType;
	}

	/**
	 * @param reportText
	 *            reportText to set
	 */
	public void setReportText(final String reportText) {
		this.reportText = reportText;
	}

	/**
	 * return the reportText
	 */
	public String getReportText() {
		return this.reportText;
	}

	/**
	 * @param evidenceDisposeText
	 *            evidenceDisposeText to set
	 */
	public void setEvidenceDisposeText(final String evidenceDisposeText) {
		this.evidenceDisposeText = evidenceDisposeText;
	}

	/**
	 * return the evidenceDisposeText
	 */
	public String getEvidenceDisposeText() {
		return this.evidenceDisposeText;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * return the createDatetime
	 */
	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return the createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * return the modifyDatetime
	 */
	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param lidsChargeNumber
	 *            lidsChargeNumber to set
	 */
	public void setLidsChargeNumber(final Integer lidsChargeNumber) {
		this.lidsChargeNumber = lidsChargeNumber;
	}

	/**
	 * return the lidsChargeNumber
	 */
	public Integer getLidsChargeNumber() {
		return this.lidsChargeNumber;
	}

	/**
	 * @param chargedOicOffenceId
	 *            chargedOicOffenceId to set
	 */
	public void setChargedOicOffenceId(final Integer chargedOicOffenceId) {
		this.chargedOicOffenceId = chargedOicOffenceId;
	}

	/**
	 * return the chargedOicOffenceId
	 */
	public Integer getChargedOicOffenceId() {
		return this.chargedOicOffenceId;
	}

	/**
	 * @param resultOicOffenceId
	 *            resultOicOffenceId to set
	 */
	public void setResultOicOffenceId(final Integer resultOicOffenceId) {
		this.resultOicOffenceId = resultOicOffenceId;
	}

	/**
	 * return the resultOicOffenceId
	 */
	public Integer getResultOicOffenceId() {
		return this.resultOicOffenceId;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return the sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
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

	public String getChargedOicOffenceCode() {
		return chargedOicOffenceCode;
	}

	public void setChargedOicOffenceCode(String chargedOicOffenceCode) {
		this.chargedOicOffenceCode = chargedOicOffenceCode;
	}

	public String getOffenceType() {
		return offenceType;
	}

	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}

	public String getOffenceDesc() {
		return offenceDesc;
	}

	public void setOffenceDesc(String offenceDesc) {
		this.offenceDesc = offenceDesc;
	}

	public String getDspCategory() {
		return dspCategory;
	}

	public void setDspCategory(String dspCategory) {
		this.dspCategory = dspCategory;
	}

}