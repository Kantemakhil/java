package net.syscon.s4.inst.offenderobservations.maintenance.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffObsCharacteristics extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("observationType")
	private String observationType;
	@JsonProperty("frequency")
	private BigDecimal frequency;
	@JsonProperty("characteristicsCode")
	private String characteristicsCode;
	@JsonProperty("characteristicsType")
	private String characteristicsType;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
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
	@JsonProperty("returnedOutput")
	private BigDecimal returnedOutput;
	
	@JsonProperty("cellConditionList")
	private List<String> cellConditionList;
	
	@JsonProperty("notInCellList")
	private List<String> notInCellList;
	@JsonProperty("activityList")
	private List<String> activityList;
	@JsonProperty("commonDetailsCatList")
	private List<String> commonDetailsCatList;
	@JsonProperty("officerNotesList")
	private List<String> officerNotesList;
	
	@JsonProperty("cellCondition")
	private String cellCondition;
	@JsonProperty("notInCell")
	private String notInCell;
	@JsonProperty("activity")
	private String activity;
	@JsonProperty("commonDetCat")
	private String commonDetCat;
	@JsonProperty("officerNotes")
	private String officerNotes;
	
	@JsonProperty("checkId")
	private long checkId;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	@JsonProperty("obsPeriodId")
	private BigDecimal obsPeriodId;
	@JsonProperty("reportedUser")
	private String reportedUser;
	
	@JsonProperty("obsTypeVersionId")
	private BigDecimal obsTypeVersionId;
	
	
	@JsonProperty("detailCode")
	private String detailCode;
	@JsonProperty("detailType")
	private String detailType;
	
	@JsonProperty("detailDatetime")
	private Date detailDatetime;
	@JsonProperty("reportingStaffId")
	private BigDecimal reportingStaffId;
	public String getObservationType() {
		return observationType;
	}
	public void setObservationType(String observationType) {
		this.observationType = observationType;
	}
	public BigDecimal getFrequency() {
		return frequency;
	}
	public void setFrequency(BigDecimal frequency) {
		this.frequency = frequency;
	}
	public String getCharacteristicsCode() {
		return characteristicsCode;
	}
	public void setCharacteristicsCode(String characteristicsCode) {
		this.characteristicsCode = characteristicsCode;
	}
	public String getCharacteristicsType() {
		return characteristicsType;
	}
	public void setCharacteristicsType(String characteristicsType) {
		this.characteristicsType = characteristicsType;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	public BigDecimal getReturnedOutput() {
		return returnedOutput;
	}
	public void setReturnedOutput(BigDecimal returnedOutput) {
		this.returnedOutput = returnedOutput;
	}
	public List<String> getCellConditionList() {
		return cellConditionList;
	}
	public void setCellConditionList(List<String> cellConditionList) {
		this.cellConditionList = cellConditionList;
	}
	public List<String> getNotInCellList() {
		return notInCellList;
	}
	public void setNotInCellList(List<String> notInCellList) {
		this.notInCellList = notInCellList;
	}
	public List<String> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<String> activityList) {
		this.activityList = activityList;
	}
	public List<String> getCommonDetailsCatList() {
		return commonDetailsCatList;
	}
	public void setCommonDetailsCatList(List<String> commonDetailsCatList) {
		this.commonDetailsCatList = commonDetailsCatList;
	}
	public List<String> getOfficerNotesList() {
		return officerNotesList;
	}
	public void setOfficerNotesList(List<String> officerNotesList) {
		this.officerNotesList = officerNotesList;
	}
	public String getCellCondition() {
		return cellCondition;
	}
	public void setCellCondition(String cellCondition) {
		this.cellCondition = cellCondition;
	}
	public String getNotInCell() {
		return notInCell;
	}
	public void setNotInCell(String notInCell) {
		this.notInCell = notInCell;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getCommonDetCat() {
		return commonDetCat;
	}
	public void setCommonDetCat(String commonDetCat) {
		this.commonDetCat = commonDetCat;
	}
	public String getOfficerNotes() {
		return officerNotes;
	}
	public void setOfficerNotes(String officerNotes) {
		this.officerNotes = officerNotes;
	}
	public long getCheckId() {
		return checkId;
	}
	public void setCheckId(long checkId) {
		this.checkId = checkId;
	}
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public BigDecimal getObsPeriodId() {
		return obsPeriodId;
	}
	public void setObsPeriodId(BigDecimal obsPeriodId) {
		this.obsPeriodId = obsPeriodId;
	}
	public String getReportedUser() {
		return reportedUser;
	}
	public void setReportedUser(String reportedUser) {
		this.reportedUser = reportedUser;
	}
	public BigDecimal getObsTypeVersionId() {
		return obsTypeVersionId;
	}
	public void setObsTypeVersionId(BigDecimal obsTypeVersionId) {
		this.obsTypeVersionId = obsTypeVersionId;
	}
	public String getDetailCode() {
		return detailCode;
	}
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	public Date getDetailDatetime() {
		return detailDatetime;
	}
	public void setDetailDatetime(Date detailDatetime) {
		this.detailDatetime = detailDatetime;
	}
	public BigDecimal getReportingStaffId() {
		return reportingStaffId;
	}
	public void setReportingStaffId(BigDecimal reportingStaffId) {
		this.reportingStaffId = reportingStaffId;
	}
	
	
}
