package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenceIndicator;
import net.syscon.s4.im.beans.OffenderAlerts;


/**
 * The persistent class for the OFFENDER_RELEASE_DETAILS database table.
 * 
 */
public class OffenderReleaseDetails extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long offenderBookId;
	private String caseLoadId;
private String lastName;
private String firstName;

	public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

	public String getCaseLoadId() {
		return caseLoadId;
	}

	public void setCaseLoadId(final String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	private Date approvedReleaseDate;

	private Date autoReleaseDate;

	private String commentText;

	private Date createDatetime;

	private String createUserId;

	private Date dtoApprovedDate;

	private Date dtoMidTermDate;

	private BigDecimal eventId;

	private String eventStatus;
	private String agyLocId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String movementReasonCode;

	private String movementType;

	private Date releaseDate;

	private String sealFlag;

	private String verifiedFlag;
	private Date fromDate;
	private Date toDate;
	private String offenderIdDisplay;
	private String nbtName;
	private int proposedMvmntSeq;
	
	private int returnValue;
	private int searchResult;
	
	@JsonProperty("keyDateListObj")
	private List<KeyDateValueBean> keyDateListObj; 
	
	@JsonProperty("alertsList")
	private List<OffenderAlerts> alertsList;
	
	@JsonProperty("alertsData")
	private String alertsData;
	
	@JsonProperty("chargeIndData")
	private List<OffenceIndicator> chargeIndData;
	
	@JsonProperty("indicatorsData")
	private String indicatorsData;
	
	@JsonProperty("dataExistFlag")
	private String dataExistFlag;
	public int getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(final int searchResult) {
		this.searchResult = searchResult;
	}

	@JsonProperty("caseloadId")
	private String caseloadId;
	
	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;
	
	@JsonProperty("verifyPopUpCloseFlag")
	private String verifyPopUpCloseFlag;
	
	@JsonProperty("agyLocIdDesc")
	private String agyLocIdDesc;
	
	@JsonProperty("facilityList")
	private List<String> facilityList;
	
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(final int returnValue) {
		this.returnValue = returnValue;
	}

	public int getProposedMvmntSeq() {
		return proposedMvmntSeq;
	}

	public void setProposedMvmntSeq(final int proposedMvmntSeq) {
		this.proposedMvmntSeq = proposedMvmntSeq;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getNbtName() {
		return nbtName;
	}

	public void setNbtName(final String nbtName) {
		this.nbtName = nbtName;
	}

	private String dateType;
	
	private String facility;

	
	public String getFacility() {
		return facility;
	}

	public void setFacility(final String facility) {
		this.facility = facility;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(final String dateType) {
		this.dateType = dateType;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}

	public OffenderReleaseDetails() {
	}

	public Long getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getApprovedReleaseDate() {
		return this.approvedReleaseDate;
	}

	public void setApprovedReleaseDate(final Date approvedReleaseDate) {
		this.approvedReleaseDate = approvedReleaseDate;
	}

	public Date getAutoReleaseDate() {
		return this.autoReleaseDate;
	}

	public void setAutoReleaseDate(final Date autoReleaseDate) {
		this.autoReleaseDate = autoReleaseDate;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getDtoApprovedDate() {
		return this.dtoApprovedDate;
	}

	public void setDtoApprovedDate(final Date dtoApprovedDate) {
		this.dtoApprovedDate = dtoApprovedDate;
	}

	public Date getDtoMidTermDate() {
		return this.dtoMidTermDate;
	}

	public void setDtoMidTermDate(final Date dtoMidTermDate) {
		this.dtoMidTermDate = dtoMidTermDate;
	}

	public BigDecimal getEventId() {
		return this.eventId;
	}

	public void setEventId(final BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getEventStatus() {
		return this.eventStatus;
	}

	public void setEventStatus(final String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getMovementReasonCode() {
		return this.movementReasonCode;
	}

	public void setMovementReasonCode(final String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	public String getMovementType() {
		return this.movementType;
	}

	public void setMovementType(final String movementType) {
		this.movementType = movementType;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(final Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getVerifiedFlag() {
		return this.verifiedFlag;
	}

	public void setVerifiedFlag(final String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	public List<KeyDateValueBean> getKeyDateListObj() {
		return keyDateListObj;
	}

	public void setKeyDateListObj(List<KeyDateValueBean> keyDateListObj) {
		this.keyDateListObj = keyDateListObj;
	}

	public List<OffenderAlerts> getAlertsList() {
		return alertsList;
	}

	public void setAlertsList(List<OffenderAlerts> alertsList) {
		this.alertsList = alertsList;
	}

	public String getAlertsData() {
		return alertsData;
	}

	public void setAlertsData(String alertsData) {
		this.alertsData = alertsData;
	}

	public List<OffenceIndicator> getChargeIndData() {
		return chargeIndData;
	}

	public void setChargeIndData(List<OffenceIndicator> chargeIndData) {
		this.chargeIndData = chargeIndData;
	}

	public String getIndicatorsData() {
		return indicatorsData;
	}

	public void setIndicatorsData(String indicatorsData) {
		this.indicatorsData = indicatorsData;
	}

	public String getDataExistFlag() {
		return dataExistFlag;
	}

	public void setDataExistFlag(String dataExistFlag) {
		this.dataExistFlag = dataExistFlag;
	}

	public String getVerifyPopUpCloseFlag() {
		return verifyPopUpCloseFlag;
	}

	public void setVerifyPopUpCloseFlag(String verifyPopUpCloseFlag) {
		this.verifyPopUpCloseFlag = verifyPopUpCloseFlag;
	}

	public String getAgyLocIdDesc() {
		return agyLocIdDesc;
	}

	public void setAgyLocIdDesc(String agyLocIdDesc) {
		this.agyLocIdDesc = agyLocIdDesc;
	}

	public List<String> getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(List<String> facilityList) {
		this.facilityList = facilityList;
	}

	
}
