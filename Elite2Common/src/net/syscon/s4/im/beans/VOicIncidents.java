package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VOicIncidents extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("agencyIncidentId")
	private BigDecimal agencyIncidentId;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("bookingNo")
	private String bookingNo;

	@JsonProperty("incidentDate")
	private Date incidentDate;

	@JsonProperty("incidentDetails")
	private String incidentDetails;

	@JsonProperty("incidentTime")
	private Date incidentTime;

	@JsonProperty("incidentType")
	private String incidentType;

	@JsonProperty("incidentTypeDesc")
	private String incidentTypeDesc;

	@JsonProperty("intLocDescription")
	private String intLocDescription;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("oicChargeFlag")
	private String oicChargeFlag;

	@JsonProperty("oicIncidentId")
	private BigDecimal oicIncidentId;

	@JsonProperty("partySeq")
	private BigDecimal partySeq;

	@JsonProperty("reportDate")
	private Date reportDate;

	@JsonProperty("reportedStaffId")
	private BigDecimal reportedStaffId;
	
	@JsonProperty("staffIdDescription")
	private String staffIdDescription;

	/**
	 * Creates new VOicIncidents class Object
	 */
	public VOicIncidents() {

		// VOicIncidents
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getAgencyIncidentId() {
		return agencyIncidentId;
	}

	/**
	 *
	 * @param agencyIncidentId
	 */
	public void setAgencyIncidentId(final BigDecimal agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	/**
	 *
	 * @return
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 *
	 * @param agyLocId
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getBookingNo() {
		return bookingNo;
	}

	/**
	 *
	 * @param bookingNo
	 */
	public void setBookingNo(final String bookingNo) {
		this.bookingNo = bookingNo;
	}

	/**
	 *
	 * @return
	 */
	public Date getIncidentDate() {
		return incidentDate;
	}

	/**
	 *
	 * @param incidentDate
	 */
	public void setIncidentDate(final Date incidentDate) {
		this.incidentDate = incidentDate;
	}

	/**
	 *
	 * @return
	 */
	public String getIncidentDetails() {
		return incidentDetails;
	}

	/**
	 *
	 * @param incidentDetails
	 */
	public void setIncidentDetails(final String incidentDetails) {
		this.incidentDetails = incidentDetails;
	}

	/**
	 *
	 * @return
	 */
	public Date getIncidentTime() {
		return incidentTime;
	}

	/**
	 *
	 * @param incidentTime
	 */
	public void setIncidentTime(final Date incidentTime) {
		this.incidentTime = incidentTime;
	}

	/**
	 *
	 * @return
	 */
	public String getIncidentType() {
		return incidentType;
	}

	/**
	 *
	 * @param incidentType
	 */
	public void setIncidentType(final String incidentType) {
		this.incidentType = incidentType;
	}

	/**
	 *
	 * @return
	 */
	public String getIncidentTypeDesc() {
		return incidentTypeDesc;
	}

	/**
	 *
	 * @param incidentTypeDesc
	 */
	public void setIncidentTypeDesc(final String incidentTypeDesc) {
		this.incidentTypeDesc = incidentTypeDesc;
	}

	/**
	 *
	 * @return
	 */
	public String getIntLocDescription() {
		return intLocDescription;
	}

	/**
	 *
	 * @param intLocDescription
	 */
	public void setIntLocDescription(final String intLocDescription) {
		this.intLocDescription = intLocDescription;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public String getOicChargeFlag() {
		return oicChargeFlag;
	}

	/**
	 *
	 * @param oicChargeFlag
	 */
	public void setOicChargeFlag(final String oicChargeFlag) {
		this.oicChargeFlag = oicChargeFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOicIncidentId() {
		return oicIncidentId;
	}

	/**
	 *
	 * @param oicIncidentId
	 */
	public void setOicIncidentId(final BigDecimal oicIncidentId) {
		this.oicIncidentId = oicIncidentId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getPartySeq() {
		return partySeq;
	}

	/**
	 *
	 * @param partySeq
	 */
	public void setPartySeq(final BigDecimal partySeq) {
		this.partySeq = partySeq;
	}

	/**
	 *
	 * @return
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 *
	 * @param reportDate
	 */
	public void setReportDate(final Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getReportedStaffId() {
		return reportedStaffId;
	}

	/**
	 *
	 * @param reportedStaffId
	 */
	public void setReportedStaffId(final BigDecimal reportedStaffId) {
		this.reportedStaffId = reportedStaffId;
	}

	/**
	 * @return the staffIdDescription
	 */
	public String getStaffIdDescription() {
		return staffIdDescription;
	}

	/**
	 * @param staffIdDescription the staffIdDescription to set
	 */
	public void setStaffIdDescription(final String staffIdDescription) {
		this.staffIdDescription = staffIdDescription;
	}

}