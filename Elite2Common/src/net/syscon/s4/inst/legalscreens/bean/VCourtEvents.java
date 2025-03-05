package net.syscon.s4.inst.legalscreens.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VCourtEvent
 */
public class VCourtEvents extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String agyLocId;

	private String agyLocIdName;

	private Object birthDate;

	private String bookingActiveFlag;

	private BigDecimal caseId;

	private String caseInfoNumber;

	private String caseInfoPrefix;

	private BigDecimal checkSum;

	private String commentText;

	private String courtEventType;

	private String courtEventTypeDesc;

	private Date endTime;

	private String eventClass;

	private Date eventDate;

	private BigDecimal eventId;

	private String eventOutcome;

	private String eventStatus;

	private String eventType;

	private String firstName;

	private String judgeName;

	private String lastName;

	private String level1Code;

	private String level2Code;

	private String level3Code;

	private String level4Code;

	private BigDecimal livingUnitId;

	private String middleName;

	private String movementReasonCode;

	private String movementReasonDesc;

	private String offenderAgyLocDesc;

	private String offenderAgyLocId;

	private BigDecimal offenderBookId;

	private String offenderIdDisplay;

	private BigDecimal parentEventId;

	private String resultCode;

	private Date startTime;

	private String appearanceLocation;
	private String appearanceType;
	
	private String outcomeReasonCode;
	
	private Boolean cancelFlag;
	
	
	public Boolean getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getOutcomeReasonCode() {
		return outcomeReasonCode;
	}

	public void setOutcomeReasonCode(String outcomeReasonCode) {
		this.outcomeReasonCode = outcomeReasonCode;
	}

	public VCourtEvents() {
		// VCourtEvents
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getAgyLocIdName() {
		return this.agyLocIdName;
	}

	public void setAgyLocIdName(String agyLocIdName) {
		this.agyLocIdName = agyLocIdName;
	}

	public Object getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Object birthDate) {
		this.birthDate = birthDate;
	}

	public String getBookingActiveFlag() {
		return this.bookingActiveFlag;
	}

	public void setBookingActiveFlag(String bookingActiveFlag) {
		this.bookingActiveFlag = bookingActiveFlag;
	}

	public BigDecimal getCaseId() {
		return this.caseId;
	}

	public void setCaseId(BigDecimal caseId) {
		this.caseId = caseId;
	}

	public String getCaseInfoNumber() {
		return this.caseInfoNumber;
	}

	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	public String getCaseInfoPrefix() {
		return this.caseInfoPrefix;
	}

	public void setCaseInfoPrefix(String caseInfoPrefix) {
		this.caseInfoPrefix = caseInfoPrefix;
	}

	public BigDecimal getCheckSum() {
		return this.checkSum;
	}

	public void setCheckSum(BigDecimal checkSum) {
		this.checkSum = checkSum;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCourtEventType() {
		return this.courtEventType;
	}

	public void setCourtEventType(String courtEventType) {
		this.courtEventType = courtEventType;
	}

	public String getCourtEventTypeDesc() {
		return this.courtEventTypeDesc;
	}

	public void setCourtEventTypeDesc(String courtEventTypeDesc) {
		this.courtEventTypeDesc = courtEventTypeDesc;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEventClass() {
		return this.eventClass;
	}

	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public BigDecimal getEventId() {
		return this.eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getEventOutcome() {
		return this.eventOutcome;
	}

	public void setEventOutcome(String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}

	public String getEventStatus() {
		return this.eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getJudgeName() {
		return this.judgeName;
	}

	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLevel1Code() {
		return this.level1Code;
	}

	public void setLevel1Code(String level1Code) {
		this.level1Code = level1Code;
	}

	public String getLevel2Code() {
		return this.level2Code;
	}

	public void setLevel2Code(String level2Code) {
		this.level2Code = level2Code;
	}

	public String getLevel3Code() {
		return this.level3Code;
	}

	public void setLevel3Code(String level3Code) {
		this.level3Code = level3Code;
	}

	public String getLevel4Code() {
		return this.level4Code;
	}

	public void setLevel4Code(String level4Code) {
		this.level4Code = level4Code;
	}

	public BigDecimal getLivingUnitId() {
		return this.livingUnitId;
	}

	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMovementReasonCode() {
		return this.movementReasonCode;
	}

	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	public String getMovementReasonDesc() {
		return this.movementReasonDesc;
	}

	public void setMovementReasonDesc(String movementReasonDesc) {
		this.movementReasonDesc = movementReasonDesc;
	}

	public String getOffenderAgyLocDesc() {
		return this.offenderAgyLocDesc;
	}

	public void setOffenderAgyLocDesc(String offenderAgyLocDesc) {
		this.offenderAgyLocDesc = offenderAgyLocDesc;
	}

	public String getOffenderAgyLocId() {
		return this.offenderAgyLocId;
	}

	public void setOffenderAgyLocId(String offenderAgyLocId) {
		this.offenderAgyLocId = offenderAgyLocId;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public BigDecimal getParentEventId() {
		return this.parentEventId;
	}

	public void setParentEventId(BigDecimal parentEventId) {
		this.parentEventId = parentEventId;
	}

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getAppearanceLocation() {
		return appearanceLocation;
	}

	public void setAppearanceLocation(String appearanceLocation) {
		this.appearanceLocation = appearanceLocation;
	}

	public String getAppearanceType() {
		return appearanceType;
	}

	public void setAppearanceType(String appearanceType) {
		this.appearanceType = appearanceType;
	}

	
}
