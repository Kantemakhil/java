package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class CourtCases extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("caseId")
	private Long caseId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("caseInfoNumber")
	private String caseInfoNumber;
	
	@JsonProperty("caseType")
	private String caseType;
		
	@JsonProperty("caseStatus")
	private String caseStatus;
	
	@JsonProperty("combinedCaseId")
	private Integer combinedCaseId;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("beginDate")
	private Date beginDate;
		
	@JsonProperty("agy_loc_id")
	private String agy_loc_id;
	
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("caseInfoPrefix")
	private String caseInfoPrefix;
	
	@JsonProperty("victimLiaisonUnit")
	private String victimLiaisonUnit;
	
	@JsonProperty("statusUpdateReason")
	private String statusUpdateReason;
	
	@JsonProperty("statusUpdateComment")
	private String statusUpdateComment;
	
	@JsonProperty("status_update_date")
	private Date status_update_date;
	
	@JsonProperty("status_update_staff_id")
	private Integer status_update_staff_id;
	
	@JsonProperty("lidsCaseNumber")
	private Long lidsCaseNumber;
	
	@JsonProperty("case_Seq")
	private Long case_Seq;
	
	@JsonProperty("seal_flag")
	private String seal_flag;
	
	@JsonProperty("linkCaseSeq")
	private Integer linkCaseSeq;

	@JsonProperty("eventInsertList")
	private List<CourtEvent> courtEventInsertList;
	
	@JsonProperty("dummyCaseId")
	private Integer dummyCaseId;
	
	@JsonProperty("commitFlag")
	private String commitFlag;
	
	@JsonProperty("courtEventList")
	private List<CourtEvent> courtEventList;
	
	@JsonProperty("sentencesList")
	private List<Sentences> sentencesList;
	
	@JsonProperty("bailDetailsList")
	private List<OffenderBailDetails> bailDetailsList;
	
	@JsonProperty("rowId")
	private String rowId;
	
	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public Long getcaseId() {
		return caseId;
	}

	public void setcaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getCaseInfoNumber() {
		return caseInfoNumber;
	}

	public void setCaseInfoNumber(String caseInfoNumber) {
		this.caseInfoNumber = caseInfoNumber;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public Integer getCombinedCaseId() {
		return combinedCaseId;
	}

	public void setCombinedCaseId(Integer combinedCaseId) {
		this.combinedCaseId = combinedCaseId;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getAgy_loc_id() {
		return agy_loc_id;
	}

	public void setAgy_loc_id(String agy_loc_id) {
		this.agy_loc_id = agy_loc_id;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCaseInfoPrefix() {
		return caseInfoPrefix;
	}

	public void setCaseInfoPrefix(String caseInfoPrefix) {
		this.caseInfoPrefix = caseInfoPrefix;
	}

	public String getVictimLiaisonUnit() {
		return victimLiaisonUnit;
	}

	public void setVictimLiaisonUnit(String victimLiaisonUnit) {
		this.victimLiaisonUnit = victimLiaisonUnit;
	}

	public String getStatusUpdateReason() {
		return statusUpdateReason;
	}

	public void setStatusUpdateReason(String statusUpdateReason) {
		this.statusUpdateReason = statusUpdateReason;
	}

	public String getStatusUpdateComment() {
		return statusUpdateComment;
	}

	public void setStatusUpdateComment(String statusUpdateComment) {
		this.statusUpdateComment = statusUpdateComment;
	}

	public Date getStatus_update_date() {
		return status_update_date;
	}

	public void setStatus_update_date(Date status_update_date) {
		this.status_update_date = status_update_date;
	}

	public Integer getStatus_update_staff_id() {
		return status_update_staff_id;
	}

	public void setStatus_update_staff_id(Integer status_update_staff_id) {
		this.status_update_staff_id = status_update_staff_id;
	}

	public Long getLidsCaseNumber() {
		return lidsCaseNumber;
	}

	public void setLidsCaseNumber(Long lidsCaseNumber) {
		this.lidsCaseNumber = lidsCaseNumber;
	}

	public Long getCase_Seq() {
		return case_Seq;
	}

	public void setCase_Seq(Long case_Seq) {
		this.case_Seq = case_Seq;
	}

	public String getSeal_flag() {
		return seal_flag;
	}

	public void setSeal_flag(String seal_flag) {
		this.seal_flag = seal_flag;
	}

	public Integer getLinkCaseSeq() {
		return linkCaseSeq;
	}

	public void setLinkCaseSeq(Integer linkCaseSeq) {
		this.linkCaseSeq = linkCaseSeq;
	}
	
	public List<CourtEvent> getCourtEventInsertList() {
		return courtEventInsertList;
	}
	public void setCourtEventInsertList(List<CourtEvent> courtEventInsertList) {
		this.courtEventInsertList = courtEventInsertList;
	}

	public Integer getDummyCaseId() {
		return dummyCaseId;
	}

	public void setDummyCaseId(Integer dummyCaseId) {
		this.dummyCaseId = dummyCaseId;
	}

	public String getCommitFlag() {
		return commitFlag;
	}

	public void setCommitFlag(String commitFlag) {
		this.commitFlag = commitFlag;
	}

	public List<CourtEvent> getCourtEventList() {
		return courtEventList;
	}

	public void setCourtEventList(List<CourtEvent> courtEventList) {
		this.courtEventList = courtEventList;
	}

	public List<Sentences> getSentencesList() {
		return sentencesList;
	}

	public void setSentencesList(List<Sentences> sentencesList) {
		this.sentencesList = sentencesList;
	}

	public List<OffenderBailDetails> getBailDetailsList() {
		return bailDetailsList;
	}

	public void setBailDetailsList(List<OffenderBailDetails> bailDetailsList) {
		this.bailDetailsList = bailDetailsList;
	}
	
}
