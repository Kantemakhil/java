package net.syscon.s4.inst.movements.proposedmovements.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VHousingMoves extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offName")
	private String offName;

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

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("currAgyId")
	private String currAgyId;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("locationSeq")
	private Integer locationSeq;

	@JsonProperty("detailSeq")
	private Integer detailSeq;

	@JsonProperty("fromLivingUnitId")
	private BigDecimal fromLivingUnitId;

	@JsonProperty("toLivingUnitId")
	private BigDecimal toLivingUnitId;

	@JsonProperty("movementType")
	private String movementType;

	@JsonProperty("movementReason")
	private String movementReason;

	@JsonProperty("statusCode")
	private String statusCode;

	@JsonProperty("txnStatus")
	private String txnStatus;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("alerts")
	private Integer alerts;

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

	@JsonProperty("cancReq")
	private String cancReq;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("approvalDate")
	private Date approvalDate;

	@JsonProperty("alertCode")
	private String alertCode;

	@JsonProperty("secLevel")
	private String secLevel;

	@JsonProperty("impStatus")
	private String impStatus;

	@JsonProperty("sancCode")
	private String sancCode;

	@JsonProperty("affiliation")
	private String affiliation;

	@JsonProperty("potSchFlag")
	private String potSchFlag;

	@JsonProperty("nonAssoFlag")
	private String nonAssoFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("staffName")
	private String staffName;

	@JsonProperty("appReason")
	private String appReason;

	@JsonProperty("txnRsn")
	private String txnRsn;

//	@JsonProperty("nbtLivDescp")
//	private String nbtLivDescp;
//
//	@JsonProperty("nbtLivFromDs")
//	private String nbtLivFromDs;
//
//	@JsonProperty("nbtLivToDs")
//	private String nbtLivToDs;
//
//	@JsonProperty("nbtNewToLiv")
//	private Integer nbtNewToLiv;

	@JsonProperty("schedYn")
	private String schedYn;

	@JsonProperty("butDefine")
	private String butDefine;

	@JsonProperty("agyId")
	private String agyId;

	@JsonProperty("toLocationId")
	private String toLocationId;

	@JsonProperty("nbtLivTo")
	private String nbtLivTo;
	
	@JsonProperty("commentRole")
	private String commentRole;
	
	@JsonProperty("currAgyDesc")
	private String currAgyDesc;
	
	@JsonProperty("fromAgyDesc")
	private String fromAgyDesc;
	
	@JsonProperty("toAgyDesc")
	private String toAgyDesc;
	
	@JsonProperty("statusObj")
	private List<String> statusObj;

	@JsonProperty("txnObj")
	private List<String> txnObj;
	
	@JsonProperty("vCapacity")
	private Long vCapacity;
	
	@JsonProperty("vOccupied")
	private Long vOccupied;
	
	@JsonProperty("lvBedMaxAssignSeq")
	private Long lvBedMaxAssignSeq;
	
	@JsonProperty("vBedSeq")
	private Long vBedSeq;

	public VHousingMoves() {
		// VHousingMoves
	}

	public String getOffName() {
		return offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
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

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getCurrAgyId() {
		return currAgyId;
	}

	public void setCurrAgyId(String currAgyId) {
		this.currAgyId = currAgyId;
	}

	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public Integer getDetailSeq() {
		return detailSeq;
	}

	public void setDetailSeq(Integer detailSeq) {
		this.detailSeq = detailSeq;
	}

	public BigDecimal getFromLivingUnitId() {
		return fromLivingUnitId;
	}

	public void setFromLivingUnitId(BigDecimal fromLivingUnitId) {
		this.fromLivingUnitId = fromLivingUnitId;
	}

	public BigDecimal getToLivingUnitId() {
		return toLivingUnitId;
	}

	public void setToLivingUnitId(BigDecimal toLivingUnitId) {
		this.toLivingUnitId = toLivingUnitId;
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

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
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

	public String getSanctions() {
		return sanctions;
	}

	public void setSanctions(String sanctions) {
		this.sanctions = sanctions;
	}

	public String getCancReq() {
		return cancReq;
	}

	public void setCancReq(String cancReq) {
		this.cancReq = cancReq;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getAlertCode() {
		return alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}

	public String getSecLevel() {
		return secLevel;
	}

	public void setSecLevel(String secLevel) {
		this.secLevel = secLevel;
	}

	public String getImpStatus() {
		return impStatus;
	}

	public void setImpStatus(String impStatus) {
		this.impStatus = impStatus;
	}

	public String getSancCode() {
		return sancCode;
	}

	public void setSancCode(String sancCode) {
		this.sancCode = sancCode;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getPotSchFlag() {
		return potSchFlag;
	}

	public void setPotSchFlag(String potSchFlag) {
		this.potSchFlag = potSchFlag;
	}

//	public String getNbtLivDescp() {
//		return nbtLivDescp;
//	}
//
//	public void setNbtLivDescp(String nbtLivDescp) {
//		this.nbtLivDescp = nbtLivDescp;
//	}
//
//	public String getNbtLivFromDs() {
//		return nbtLivFromDs;
//	}
//
//	public void setNbtLivFromDs(String nbtLivFromDs) {
//		this.nbtLivFromDs = nbtLivFromDs;
//	}
//
//	public String getNbtLivToDs() {
//		return nbtLivToDs;
//	}
//
//	public void setNbtLivToDs(String nbtLivToDs) {
//		this.nbtLivToDs = nbtLivToDs;
//	}
//
//	public Integer getNbtNewToLiv() {
//		return nbtNewToLiv;
//	}
//
//	public void setNbtNewToLiv(Integer nbtNewToLiv) {
//		this.nbtNewToLiv = nbtNewToLiv;
//	}

	public String getSchedYn() {
		return schedYn;
	}

	public void setSchedYn(String schedYn) {
		this.schedYn = schedYn;
	}

	public String getButDefine() {
		return butDefine;
	}

	public void setButDefine(String butDefine) {
		this.butDefine = butDefine;
	}

	public String getAgyId() {
		return agyId;
	}

	public void setAgyId(String agyId) {
		this.agyId = agyId;
	}

	public String getToLocationId() {
		return toLocationId;
	}

	public void setToLocationId(String toLocationId) {
		this.toLocationId = toLocationId;
	}

	public String getNbtLivTo() {
		return nbtLivTo;
	}

	public void setNbtLivTo(String nbtLivTo) {
		this.nbtLivTo = nbtLivTo;
	}

	public String getNonAssoFlag() {
		return nonAssoFlag;
	}

	public void setNonAssoFlag(String nonAssoFlag) {
		this.nonAssoFlag = nonAssoFlag;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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

	public Integer getLocationSeq() {
		return locationSeq;
	}

	public void setLocationSeq(Integer locationSeq) {
		this.locationSeq = locationSeq;
	}

	public String getCommentRole() {
		return commentRole;
	}

	public void setCommentRole(String commentRole) {
		this.commentRole = commentRole;
	}

	public String getCurrAgyDesc() {
		return currAgyDesc;
	}

	public void setCurrAgyDesc(String currAgyDesc) {
		this.currAgyDesc = currAgyDesc;
	}

	public String getFromAgyDesc() {
		return fromAgyDesc;
	}

	public void setFromAgyDesc(String fromAgyDesc) {
		this.fromAgyDesc = fromAgyDesc;
	}

	public String getToAgyDesc() {
		return toAgyDesc;
	}

	public void setToAgyDesc(String toAgyDesc) {
		this.toAgyDesc = toAgyDesc;
	}

	public List<String> getStatusObj() {
		return statusObj;
	}

	public List<String> getTxnObj() {
		return txnObj;
	}

	public void setStatusObj(List<String> statusObj) {
		this.statusObj = statusObj;
	}

	public void setTxnObj(List<String> txnObj) {
		this.txnObj = txnObj;
	}

	public Long getvCapacity() {
		return vCapacity;
	}

	public void setvCapacity(Long vCapacity) {
		this.vCapacity = vCapacity;
	}

	public Long getvOccupied() {
		return vOccupied;
	}

	public void setvOccupied(Long vOccupied) {
		this.vOccupied = vOccupied;
	}

	public Long getLvBedMaxAssignSeq() {
		return lvBedMaxAssignSeq;
	}

	public void setLvBedMaxAssignSeq(Long lvBedMaxAssignSeq) {
		this.lvBedMaxAssignSeq = lvBedMaxAssignSeq;
	}

	public Long getvBedSeq() {
		return vBedSeq;
	}

	public void setvBedSeq(Long vBedSeq) {
		this.vBedSeq = vBedSeq;
	}

	
}
