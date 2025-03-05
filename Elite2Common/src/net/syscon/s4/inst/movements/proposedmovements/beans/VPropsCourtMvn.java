package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VPropsCourtMvn extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("offenderBookId")
	private String  offenderBookId;
	
	@JsonProperty("rootOffenderId")
	private String  rootOffenderId;
	
	@JsonProperty("offenderId")
	private String offenderId;
	
	@JsonProperty("movementSeq")
	private String movementSeq;
	
	@JsonProperty("movementType")
	private String movementType;
	
	@JsonProperty("reasonText")
	private String  reasonText;
	
	@JsonProperty("alternateAgyLocId")
	private String alternateAgyLocId;
	
	@JsonProperty("detailSeq")
	private String  detailSeq;
	
	@JsonProperty("appRsn")
	private String  appRsn;
	
	@JsonProperty("recordedBy")
	private String recordedBy;
	
	@JsonProperty("recordedDate")
	private String  recordedDate;
	
	@JsonProperty("txnRsn")
	private String  txnRsn;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;
	
	@JsonProperty("movementReason")
	private String movementReason;
	
	@JsonProperty("toAgyLocId")
	private String toAgyLocId;
	
	@JsonProperty("judge")
	private String judge;
	
	@JsonProperty("eventDate")
	private Date eventDate;
	
	@JsonProperty("eventTime")
	private Date eventTime;
	
	@JsonProperty("statusCode")
	private String statusCode;
	
	@JsonProperty("txnStatus")
	private String txnStatus;
	
	public String getAgyLocId() {
		return agyLocId;
	}
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	public String getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(String offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getRootOffenderId() {
		return rootOffenderId;
	}
	public void setRootOffenderId(String rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}
	public String getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(String offenderId) {
		this.offenderId = offenderId;
	}
	public String getMovementSeq() {
		return movementSeq;
	}
	public void setMovementSeq(String movementSeq) {
		this.movementSeq = movementSeq;
	}
	public String getMovementType() {
		return movementType;
	}
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	public String getReasonText() {
		return reasonText;
	}
	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}
	public String getAlternateAgyLocId() {
		return alternateAgyLocId;
	}
	public void setAlternateAgyLocId(String alternateAgyLocId) {
		this.alternateAgyLocId = alternateAgyLocId;
	}
	public String getDetailSeq() {
		return detailSeq;
	}
	public void setDetailSeq(String detailSeq) {
		this.detailSeq = detailSeq;
	}
	public String getAppRsn() {
		return appRsn;
	}
	public void setAppRsn(String appRsn) {
		this.appRsn = appRsn;
	}
	public String getRecordedBy() {
		return recordedBy;
	}
	public void setRecordedBy(String recordedBy) {
		this.recordedBy = recordedBy;
	}
	public String getRecordedDate() {
		return recordedDate;
	}
	public void setRecordedDate(String recordedDate) {
		this.recordedDate = recordedDate;
	}
	public String getTxnRsn() {
		return txnRsn;
	}
	public void setTxnRsn(String txnRsn) {
		this.txnRsn = txnRsn;
	}
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
	public String getFromAgyLocId() {
		return fromAgyLocId;
	}
	public void setFromAgyLocId(String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}
	public String getMovementReason() {
		return movementReason;
	}
	public void setMovementReason(String movementReason) {
		this.movementReason = movementReason;
	}
	public String getToAgyLocId() {
		return toAgyLocId;
	}
	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}
}
