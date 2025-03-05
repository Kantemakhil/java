package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderDrugAdmission extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderDrugAdmissionId")
	private long offenderDrugAdmissionId;

	@JsonProperty("admitDate")
	private Date admitDate;

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

	@JsonProperty("eventId")
	private BigDecimal eventId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("witnessName")
	private String witnessName;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderDrugAdmDrugs")
	private List<OffenderDrugAdmDrug> offenderDrugAdmDrugs;

	/**
	 *
	 * @return
	 */
	public long getOffenderDrugAdmissionId() {
		return offenderDrugAdmissionId;
	}

	/**
	 *
	 * @param offenderDrugAdmissionId
	 */
	public void setOffenderDrugAdmissionId(long offenderDrugAdmissionId) {
		this.offenderDrugAdmissionId = offenderDrugAdmissionId;
	}

	/**
	 *
	 * @return
	 */
	public Date getAdmitDate() {
		return admitDate;
	}

	/**
	 *
	 * @param admitDate
	 */
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
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
	public String getWitnessName() {
		return witnessName;
	}

	/**
	 *
	 * @param witnessName
	 */
	public void setWitnessName(String witnessName) {
		this.witnessName = witnessName;
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

	/**
	 *
	 * @return
	 */
	public List<OffenderDrugAdmDrug> getOffenderDrugAdmDrugs() {
		return offenderDrugAdmDrugs;
	}

	/**
	 *
	 * @param offenderDrugAdmDrugs
	 */
	public void setOffenderDrugAdmDrugs(List<OffenderDrugAdmDrug> offenderDrugAdmDrugs) {
		this.offenderDrugAdmDrugs = offenderDrugAdmDrugs;
	}

}