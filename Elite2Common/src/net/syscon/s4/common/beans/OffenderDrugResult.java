package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderDrugResult extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("auditAdditionalInfo")
	private String auditAdditionalInfo;

	@JsonProperty("auditClientIpAddress")
	private String auditClientIpAddress;

	@JsonProperty("auditClientUserId")
	private String auditClientUserId;

	@JsonProperty("auditClientWorkstationName")
	private String auditClientWorkstationName;

	@JsonProperty("auditModuleName")
	private String auditModuleName;

	@JsonProperty("auditTimestamp")
	private Serializable auditTimestamp;

	@JsonProperty("auditUserId")
	private String auditUserId;

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

	@JsonProperty("offenderDrugSample")
	private OffenderDrugSample offenderDrugSample;

	@JsonProperty("offenderDrugSampleId")
	private long offenderDrugSampleId;

	@JsonProperty("drugCode")
	private String drugCode;

	/**
	 *
	 * @return
	 */
	public String getAuditAdditionalInfo() {
		return auditAdditionalInfo;
	}

	/**
	 *
	 * @param auditAdditionalInfo
	 */
	public void setAuditAdditionalInfo(String auditAdditionalInfo) {
		this.auditAdditionalInfo = auditAdditionalInfo;
	}

	/**
	 *
	 * @return
	 */
	public String getAuditClientIpAddress() {
		return auditClientIpAddress;
	}

	/**
	 *
	 * @param auditClientIpAddress
	 */
	public void setAuditClientIpAddress(String auditClientIpAddress) {
		this.auditClientIpAddress = auditClientIpAddress;
	}

	/**
	 *
	 * @return
	 */
	public String getAuditClientUserId() {
		return auditClientUserId;
	}

	/**
	 *
	 * @param auditClientUserId
	 */
	public void setAuditClientUserId(String auditClientUserId) {
		this.auditClientUserId = auditClientUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getAuditClientWorkstationName() {
		return auditClientWorkstationName;
	}

	/**
	 *
	 * @param auditClientWorkstationName
	 */
	public void setAuditClientWorkstationName(String auditClientWorkstationName) {
		this.auditClientWorkstationName = auditClientWorkstationName;
	}

	/**
	 *
	 * @return
	 */
	public String getAuditModuleName() {
		return auditModuleName;
	}

	/**
	 *
	 * @param auditModuleName
	 */
	public void setAuditModuleName(String auditModuleName) {
		this.auditModuleName = auditModuleName;
	}

	/**
	 *
	 * @return
	 */
	public Serializable getAuditTimestamp() {
		return auditTimestamp;
	}

	/**
	 *
	 * @param auditTimestamp
	 */
	public void setAuditTimestamp(Serializable auditTimestamp) {
		this.auditTimestamp = auditTimestamp;
	}

	/**
	 *
	 * @return
	 */
	public String getAuditUserId() {
		return auditUserId;
	}

	/**
	 *
	 * @param auditUserId
	 */
	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public OffenderDrugSample getOffenderDrugSample() {
		return offenderDrugSample;
	}

	/**
	 *
	 * @param offenderDrugSample
	 */
	public void setOffenderDrugSample(OffenderDrugSample offenderDrugSample) {
		this.offenderDrugSample = offenderDrugSample;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderDrugSampleId() {
		return offenderDrugSampleId;
	}

	/**
	 *
	 * @param offenderDrugSampleId
	 */
	public void setOffenderDrugSampleId(long offenderDrugSampleId) {
		this.offenderDrugSampleId = offenderDrugSampleId;
	}

	/**
	 *
	 * @return
	 */
	public String getDrugCode() {
		return drugCode;
	}

	/**
	 *
	 * @param drugCode
	 */
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

}