package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

import java.io.Serializable;
import java.math.*;
public class VExternalMoves extends  BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("offName")
	private String offName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;
	
	@JsonProperty("offenderId")
	private Integer offenderId;
	
	@JsonProperty("sexCode")
	private String sexCode;
	
	@JsonProperty("raceCode")
	private String raceCode;
	
	@JsonProperty("birthDate")
	private Date birthDate;
	
	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	
	@JsonProperty("movementSeq")
	private Integer movementSeq;
	
	@JsonProperty("detailSeq")
	private Integer detailSeq;
	
	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;
	
	@JsonProperty("toAgyLocId")
	private String toAgyLocId;
	
	@JsonProperty("movementType")
	private String movementType;
	
	@JsonProperty("movementReason")
	private String movementReason;
	
	@JsonProperty("eventDate")
	private Date eventDate;
	
	@JsonProperty("moveByDate")
	private Date moveByDate;
	
	@JsonProperty("moveAllowDate")
	private Date moveAllowDate;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("inmComment")
	private String inmComment;
	
	@JsonProperty("ssn")
	private String ssn;
	
	@JsonProperty("agyId")
	private String agyId;
	
	@JsonProperty("agyDir")
	private String agyDir;
	
	@JsonProperty("moveType")
	private String moveType;
	
	@JsonProperty("moveResn")
	private String moveResn;
	
	@JsonProperty("scheduledTripId")
	private BigDecimal scheduledTripId;
	
	@JsonProperty("statusCode")
	private String statusCode;
	
	@JsonProperty("txnStatus")
	private String txnStatus;
	
	@JsonProperty("currAgyId")
	private String currAgyId;
	
	@JsonProperty("nbtLivDescp")
	private String nbtLivDescp;
	
	@JsonProperty("priorityCode")
	private String priorityCode;
	
	@JsonProperty("alternateAgyLocId")
	private String alternateAgyLocId;
	
	@JsonProperty("tmpGroupId")
	private String tmpGroupId;
	
	@JsonProperty("dateType")
	private String dateType;
	
	@JsonProperty("reasonType")
	private  String reasonType;
	
	@JsonProperty("checklocation")
	private Boolean checklocation;
	
	@JsonProperty("forSegement")
	private Boolean forSegement;
	
	@JsonProperty("must")
	private String must;
	
	@JsonProperty("cant")
	private String cant;
	
	@JsonProperty("approve")
	private String approve;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("toDate")
	private Date toDate;
	
	@JsonProperty("alerts")
   private Integer alerts;	
	
	@JsonProperty("alertCode")
	private String alertCode;
	
	

	@JsonProperty("imprisonmentStatus")
	private String imprisonmentStatus;
	
	@JsonProperty("securityLevel")
	private String securityLevel;
	
	@JsonProperty("ethnicity")
	private String ethnicity;
	
	@JsonProperty("nonAssociation")
	private Boolean nonAssociation;
	
	@JsonProperty("affilliation")
	private Integer affilliation;
	
	@JsonProperty("sanctions")
	private String sanctions;
	
	@JsonProperty("PotentialScheduledconflicts")
	private Boolean PotentialScheduledconflicts;
	
	@JsonProperty("schedYn")
	private String schedYn;
	
	@JsonProperty("scheduleDateType")
    private Integer scheduleDateType;
	
	@JsonProperty("nbtSchDate")
    private Date nbtSchDate;
	
	@JsonProperty("vScheduleId")
    private Integer vScheduleId;
	
	@JsonProperty("Schedule")
    private String Schedule;
	
	@JsonProperty("agyFrom")
	private String agyFrom;
	
	@JsonProperty("agyTo")
	private String agyTo;
	
	@JsonProperty("pCommentRole")
	private Boolean pCommentRole;
	

	@JsonProperty("appNew")
	private String appNew;
	
	@JsonProperty("appPend")
	private String appPend;
	
	@JsonProperty("appApp")
	private String appApp;
	
	@JsonProperty("appDen")
	private String appDen;
	
	@JsonProperty("nbtAppStat")
	private String nbtAppStat;

	@JsonProperty("txnPend")
	private String txnPend;
	
	@JsonProperty("txnSchd")
	private String txnSchd;
	
	@JsonProperty("txnComp")
	private String txnComp;
	
	@JsonProperty("nbtApprDate")
	private Date nbtApprDate;
	
	@JsonProperty("txnCanc")
	private String txnCanc;
	
	@JsonProperty("diffLoc")
	private String diffLoc;
	
	@JsonProperty("ctrlCancReq")
	private String ctrlCancReq;
	
	@JsonProperty("noBkg")
	private String noBkg;
	
	@JsonProperty("nonAdmInmateId")
	private Long nonAdmInmateId;
	
	@JsonProperty("nbtCancDate")
	private Date nbtCancDate;
	
	@JsonProperty("vPrioFlag")
	private String vPrioFlag;
	
	@JsonProperty("roleAssigned")
	private String roleAssigned;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("reason")
	private String reason;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("agencyTo")
	private String agencyTo;
	
	@JsonProperty("agencyFrom")
	private String agencyFrom;
	
	@JsonProperty("selected")
	private Boolean selected;
	
	@JsonProperty("ScheduleDate")
	private Date ScheduleDate;
	
	@JsonProperty("sancCode")
	private String sancCode;
	
	@JsonProperty("potSchFlag")
	private String potSchFlag;
	
	@JsonProperty("appReason")
	private String appReason;

	@JsonProperty("txnRsn")
	private String txnRsn;
	
	@JsonProperty("commentRole")
	private String commentRole;
	
	@JsonProperty("nonAssoFlag")
	private String nonAssoFlag;
	
	@JsonProperty("recordedBy")
	private String recordedBy;
	
	@JsonProperty("recordedDate")
	private Date recordedDate;
	
	@JsonProperty("judgeName")
	private String judgeName;
	
	public String getNonAssoFlag() {
		return nonAssoFlag;
	}
	public void setNonAssoFlag(String nonAssoFlag) {
		this.nonAssoFlag = nonAssoFlag;
	}
	public String getAlertCode() {
		return alertCode;
	}
	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}
	
	public String getAppReason() {
		return appReason;
	}
	public void setAppReason(String appReason) {
		this.appReason = appReason;
	}
	public String getTxnRsn() {
		return txnRsn;
	}
	public void setTxnRsn(String txnRsn) {
		this.txnRsn = txnRsn;
	}
	public String getCommentRole() {
		return commentRole;
	}
	public void setCommentRole(String commentRole) {
		this.commentRole = commentRole;
	}
	
	public Date getScheduleDate() {
		return ScheduleDate;
	}
	public String getSancCode() {
		return sancCode;
	}
	public void setSancCode(String sancCode) {
		this.sancCode = sancCode;
	}
	public String getPotSchFlag() {
		return potSchFlag;
	}
	public void setPotSchFlag(String potSchFlag) {
		this.potSchFlag = potSchFlag;
	}
	public void setScheduleDate(Date scheduleDate) {
		ScheduleDate = scheduleDate;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public String getAgencyTo() {
		return agencyTo;
	}
	public void setAgencyTo(String agencyTo) {
		this.agencyTo = agencyTo;
	}
	public String getAgencyFrom() {
		return agencyFrom;
	}
	public void setAgencyFrom(String agencyFrom) {
		this.agencyFrom = agencyFrom;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getSchedule() {
		return Schedule;
	}
	public Boolean getpCommentRole() {
		return pCommentRole;
	}
	public void setpCommentRole(Boolean pCommentRole) {
		this.pCommentRole = pCommentRole;
	}
	public void setSchedule(String schedule) {
		Schedule = schedule;
	}
	public Integer getvScheduleId() {
		return vScheduleId;
	}
	public void setvScheduleId(Integer vScheduleId) {
		this.vScheduleId = vScheduleId;
	}
	public Integer getScheduleDateType() {
		return scheduleDateType;
	}
	public void setScheduleDateType(Integer scheduleDateType) {
		this.scheduleDateType = scheduleDateType;
	}
	public Date getNbtSchDate() {
		return nbtSchDate;
	}
	public void setNbtSchDate(Date nbtSchDate) {
		this.nbtSchDate = nbtSchDate;
	}
	public String getSchedYn() {
		return schedYn;
	}
	public void setSchedYn(String schedYn) {
		this.schedYn = schedYn;
	}
	public Boolean getPotentialScheduledconflicts() {
		return PotentialScheduledconflicts;
	}
	public void setPotentialScheduledconflicts(Boolean potentialScheduledconflicts) {
		PotentialScheduledconflicts = potentialScheduledconflicts;
	}
	public String getSanctions() {
		return sanctions;
	}
	public void setSanctions(String sanctions) {
		this.sanctions = sanctions;
	}
	public Integer getAlerts() {
		return alerts;
	}
	public void setAlerts(Integer alerts) {
		this.alerts = alerts;
	}
	public String getImprisonmentStatus() {
		return imprisonmentStatus;
	}
	public void setImprisonmentStatus(String imprisonmentStatus) {
		this.imprisonmentStatus = imprisonmentStatus;
	}
	public String getSecurityLevel() {
		return securityLevel;
	}
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public Boolean getNonAssociation() {
		return nonAssociation;
	}
	public void setNonAssociation(Boolean nonAssociation) {
		this.nonAssociation = nonAssociation;
	}
	public Integer getAffilliation() {
		return affilliation;
	}
	public void setAffilliation(Integer affilliation) {
		this.affilliation = affilliation;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getMust() {
		return must;
	}
	public void setMust(String must) {
		this.must = must;
	}
	public String getCant() {
		return cant;
	}
	public void setCant(String cant) {
		this.cant = cant;
	}
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public Boolean getChecklocation() {
		return checklocation;
	}
	public void setChecklocation(Boolean checklocation) {
		this.checklocation = checklocation;
	}
	public Boolean getForSegement() {
		return forSegement;
	}
	public void setForSegement(Boolean forSegement) {
		this.forSegement = forSegement;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getAgyId() {
		return agyId;
	}
	public void setAgyId(String agyId) {
		this.agyId = agyId;
	}
	public String getAgyDir() {
		return agyDir;
	}
	public void setAgyDir(String agyDir) {
		this.agyDir = agyDir;
	}
	public String getMoveType() {
		return moveType;
	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	public String getMoveResn() {
		return moveResn;
	}
	public void setMoveResn(String moveResn) {
		this.moveResn = moveResn;
	}
	public String getAgyFrom() {
		return agyFrom;
	}
	public void setAgyFrom(String agyFrom) {
		this.agyFrom = agyFrom;
	}
	public String getAgyTo() {
		return agyTo;
	}
	public void setAgyTo(String agyTo) {
		this.agyTo = agyTo;
	}

	public String getOffName() {
		return offName;
	}
	public void setOffName(String offName) {
		this.offName = offName;
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
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}
	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}
	public Integer getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(Integer offenderId) {
		this.offenderId = offenderId;
	}
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public String getRaceCode() {
		return raceCode;
	}
	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}
	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}
	public Integer getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public Integer getMovementSeq() {
		return movementSeq;
	}
	public void setMovementSeq(Integer movementSeq) {
		this.movementSeq = movementSeq;
	}
	public Integer getDetailSeq() {
		return detailSeq;
	}
	public void setDetailSeq(Integer detailSeq) {
		this.detailSeq = detailSeq;
	}
	public String getFromAgyLocId() {
		return fromAgyLocId;
	}
	public void setFromAgyLocId(String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}
	public String getToAgyLocId() {
		return toAgyLocId;
	}
	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}
	public String getMovementType() {
		return movementType;
	}
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	public String getMovementReason() {
		return movementReason;
	}
	public void setMovementReason(String movementReason) {
		this.movementReason = movementReason;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Date getMoveByDate() {
		return moveByDate;
	}
	public void setMoveByDate(Date moveByDate) {
		this.moveByDate = moveByDate;
	}
	public Date getMoveAllowDate() {
		return moveAllowDate;
	}
	public void setMoveAllowDate(Date moveAllowDate) {
		this.moveAllowDate = moveAllowDate;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getInmComment() {
		return inmComment;
	}
	public void setInmComment(String inmComment) {
		this.inmComment = inmComment;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public BigDecimal getScheduledTripId() {
		return scheduledTripId;
	}
	public void setScheduledTripId(BigDecimal scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
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
	public String getCurrAgyId() {
		return currAgyId;
	}
	public void setCurrAgyId(String currAgyId) {
		this.currAgyId = currAgyId;
	}
	public String getNbtLivDescp() {
		return nbtLivDescp;
	}
	public void setNbtLivDescp(String nbtLivDescp) {
		this.nbtLivDescp = nbtLivDescp;
	}
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	public String getAlternateAgyLocId() {
		return alternateAgyLocId;
	}
	public void setAlternateAgyLocId(String alternateAgyLocId) {
		this.alternateAgyLocId = alternateAgyLocId;
	}
	public String getTmpGroupId() {
		return tmpGroupId;
	}
	public void setTmpGroupId(String tmpGroupId) {
		this.tmpGroupId = tmpGroupId;
	}
	public String getAppNew() {
		return appNew;
	}
	public void setAppNew(String appNew) {
		this.appNew = appNew;
	}
	public String getAppPend() {
		return appPend;
	}
	public void setAppPend(String appPend) {
		this.appPend = appPend;
	}
	public String getAppApp() {
		return appApp;
	}
	public void setAppApp(String appApp) {
		this.appApp = appApp;
	}
	public String getAppDen() {
		return appDen;
	}
	public void setAppDen(String appDen) {
		this.appDen = appDen;
	}
	public String getNbtAppStat() {
		return nbtAppStat;
	}
	public void setNbtAppStat(String nbtAppStat) {
		this.nbtAppStat = nbtAppStat;
	}
	public String getTxnPend() {
		return txnPend;
	}
	public void setTxnPend(String txnPend) {
		this.txnPend = txnPend;
	}
	public String getTxnSchd() {
		return txnSchd;
	}
	public void setTxnSchd(String txnSchd) {
		this.txnSchd = txnSchd;
	}
	public String getTxnComp() {
		return txnComp;
	}
	public void setTxnComp(String txnComp) {
		this.txnComp = txnComp;
	}
	public Date getNbtApprDate() {
		return nbtApprDate;
	}
	public void setNbtApprDate(Date nbtApprDate) {
		this.nbtApprDate = nbtApprDate;
	}
	public String getTxnCanc() {
		return txnCanc;
	}
	public void setTxnCanc(String txnCanc) {
		this.txnCanc = txnCanc;
	}
	public String getDiffLoc() {
		return diffLoc;
	}
	public void setDiffLoc(String diffLoc) {
		this.diffLoc = diffLoc;
	}
	public String getCtrlCancReq() {
		return ctrlCancReq;
	}
	public void setCtrlCancReq(String ctrlCancReq) {
		this.ctrlCancReq = ctrlCancReq;
	}
	public String getNoBkg() {
		return noBkg;
	}
	public void setNoBkg(String noBkg) {
		this.noBkg = noBkg;
	}
	public Long getNonAdmInmateId() {
		return nonAdmInmateId;
	}
	public void setNonAdmInmateId(Long nonAdmInmateId) {
		this.nonAdmInmateId = nonAdmInmateId;
	}
	public Date getNbtCancDate() {
		return nbtCancDate;
	}
	public void setNbtCancDate(Date nbtCancDate) {
		this.nbtCancDate = nbtCancDate;
	}
	public String getvPrioFlag() {
		return vPrioFlag;
	}
	public void setvPrioFlag(String vPrioFlag) {
		this.vPrioFlag = vPrioFlag;
	}
	public String getRoleAssigned() {
		return roleAssigned;
	}
	public void setRoleAssigned(String roleAssigned) {
		this.roleAssigned = roleAssigned;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getRecordedBy() {
		return recordedBy;
	}
	public void setRecordedBy(String recordedBy) {
		this.recordedBy = recordedBy;
	}
	public Date getRecordedDate() {
		return recordedDate;
	}
	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}
	public String getJudgeName() {
		return judgeName;
	}
	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}
	
	
	
	
	

}