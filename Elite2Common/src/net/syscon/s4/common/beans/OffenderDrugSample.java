package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderDrugSample extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderDrugSampleId")
	private long offenderDrugSampleId;

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

	@JsonProperty("drugCode")
	private String drugCode;

	@JsonProperty("eventId")
	private BigDecimal eventId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("referenceId")
	private String referenceId;

	@JsonProperty("sampleDate")
	private Date sampleDate;

	@JsonProperty("sampleTakenPersonName")
	private String sampleTakenPersonName;

	@JsonProperty("sampleTestedLab")
	private String sampleTestedLab;

	@JsonProperty("sampleType")
	private String sampleType;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("takenActionCode")
	private String takenActionCode;

	@JsonProperty("testDate")
	private Date testDate;

	@JsonProperty("testResult")
	private String testResult;

	@JsonProperty("offenderDrugResults")
	private List<OffenderDrugResult> offenderDrugResults;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

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

	/**
	 *
	 * @return
	 */
	public BigDecimal getEventId() {
		return eventId;
	}

	/**
	 *
	 * @param eventId
	 */
	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
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
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 *
	 * @param referenceId
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 *
	 * @return
	 */
	public Date getSampleDate() {
		return sampleDate;
	}

	/**
	 *
	 * @param sampleDate
	 */
	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}

	/**
	 *
	 * @return
	 */
	public String getSampleTakenPersonName() {
		return sampleTakenPersonName;
	}

	/**
	 *
	 * @param sampleTakenPersonName
	 */
	public void setSampleTakenPersonName(String sampleTakenPersonName) {
		this.sampleTakenPersonName = sampleTakenPersonName;
	}

	/**
	 *
	 * @return
	 */
	public String getSampleTestedLab() {
		return sampleTestedLab;
	}

	/**
	 *
	 * @param sampleTestedLab
	 */
	public void setSampleTestedLab(String sampleTestedLab) {
		this.sampleTestedLab = sampleTestedLab;
	}

	/**
	 *
	 * @return
	 */
	public String getSampleType() {
		return sampleType;
	}

	/**
	 *
	 * @param sampleType
	 */
	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
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
	public String getTakenActionCode() {
		return takenActionCode;
	}

	/**
	 *
	 * @param takenActionCode
	 */
	public void setTakenActionCode(String takenActionCode) {
		this.takenActionCode = takenActionCode;
	}

	/**
	 *
	 * @return
	 */
	public Date getTestDate() {
		return testDate;
	}

	/**
	 *
	 * @param testDate
	 */
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	/**
	 *
	 * @return
	 */
	public String getTestResult() {
		return testResult;
	}

	/**
	 *
	 * @param testResult
	 */
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderDrugResult> getOffenderDrugResults() {
		return offenderDrugResults;
	}

	/**
	 *
	 * @param offenderDrugResults
	 */
	public void setOffenderDrugResults(List<OffenderDrugResult> offenderDrugResults) {
		this.offenderDrugResults = offenderDrugResults;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

}