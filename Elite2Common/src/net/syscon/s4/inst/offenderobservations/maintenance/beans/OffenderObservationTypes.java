package net.syscon.s4.inst.offenderobservations.maintenance.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderObservationTypes extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("observationType")
	private String observationType;
	@JsonProperty("frequency")
	private BigDecimal frequency;
	@JsonProperty("notificationFlag")
	private String notificationFlag;
	@JsonProperty("notificationTiming")
	private BigDecimal notificationTiming;
	@JsonProperty("linkAssessFlag")
	private String linkAssessFlag;
	@JsonProperty("linkSegDiFlag")
	private String linkSegDiFlag;
	@JsonProperty("linkIncidentFlag")
	private String linkIncidentFlag;
	@JsonProperty("linkOicFlag")
	private String linkOicFlag;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
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
	
	@JsonProperty("activityList")
	private List<String> activityList;
	@JsonProperty("commonDetailsCatList")
	private List<String> commonDetailsCatList;
	@JsonProperty("notInCellList")
	private List<String> notInCellList;
	
	@JsonProperty("cellConditionFlag")
	private String cellConditionFlag;
	@JsonProperty("activityFlag")
    private String activityFlag;
	@JsonProperty("demeanorFlag")
    private String demeanorFlag;
	@JsonProperty("notInCellFlag")
    private String notInCellFlag;
	@JsonProperty("officerNotesFlag")
    private String officerNotesFlag;
	@JsonProperty("obsTypeVersionId")
	private BigDecimal obsTypeVersionId;
	
	@JsonProperty("offObsCharacteristicsInsertList")
	private List<OffObsCharacteristics> offObsCharacteristicsInsertList;
	
	@JsonProperty("offObsCharacteristicsUpdateList")
	private List<OffObsCharacteristics> offObsCharacteristicsUpdateList;
	
	@JsonProperty("code")
	private String code;
	@JsonProperty("description")
	private String description;
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;
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
	public String getNotificationFlag() {
		return notificationFlag;
	}
	public void setNotificationFlag(String notificationFlag) {
		this.notificationFlag = notificationFlag;
	}
	public BigDecimal getNotificationTiming() {
		return notificationTiming;
	}
	public void setNotificationTiming(BigDecimal notificationTiming) {
		this.notificationTiming = notificationTiming;
	}
	public String getLinkAssessFlag() {
		return linkAssessFlag;
	}
	public void setLinkAssessFlag(String linkAssessFlag) {
		this.linkAssessFlag = linkAssessFlag;
	}
	public String getLinkSegDiFlag() {
		return linkSegDiFlag;
	}
	public void setLinkSegDiFlag(String linkSegDiFlag) {
		this.linkSegDiFlag = linkSegDiFlag;
	}
	public String getLinkIncidentFlag() {
		return linkIncidentFlag;
	}
	public void setLinkIncidentFlag(String linkIncidentFlag) {
		this.linkIncidentFlag = linkIncidentFlag;
	}
	public String getLinkOicFlag() {
		return linkOicFlag;
	}
	public void setLinkOicFlag(String linkOicFlag) {
		this.linkOicFlag = linkOicFlag;
	}
	public BigDecimal getListSeq() {
		return listSeq;
	}
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
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
	public List<String> getNotInCellList() {
		return notInCellList;
	}
	public void setNotInCellList(List<String> notInCellList) {
		this.notInCellList = notInCellList;
	}
	public String getCellConditionFlag() {
		return cellConditionFlag;
	}
	public void setCellConditionFlag(String cellConditionFlag) {
		this.cellConditionFlag = cellConditionFlag;
	}
	public String getActivityFlag() {
		return activityFlag;
	}
	public void setActivityFlag(String activityFlag) {
		this.activityFlag = activityFlag;
	}
	public String getDemeanorFlag() {
		return demeanorFlag;
	}
	public void setDemeanorFlag(String demeanorFlag) {
		this.demeanorFlag = demeanorFlag;
	}
	public String getNotInCellFlag() {
		return notInCellFlag;
	}
	public void setNotInCellFlag(String notInCellFlag) {
		this.notInCellFlag = notInCellFlag;
	}
	public String getOfficerNotesFlag() {
		return officerNotesFlag;
	}
	public void setOfficerNotesFlag(String officerNotesFlag) {
		this.officerNotesFlag = officerNotesFlag;
	}
	public BigDecimal getObsTypeVersionId() {
		return obsTypeVersionId;
	}
	public void setObsTypeVersionId(BigDecimal obsTypeVersionId) {
		this.obsTypeVersionId = obsTypeVersionId;
	}
	public List<OffObsCharacteristics> getOffObsCharacteristicsInsertList() {
		return offObsCharacteristicsInsertList;
	}
	public void setOffObsCharacteristicsInsertList(List<OffObsCharacteristics> offObsCharacteristicsInsertList) {
		this.offObsCharacteristicsInsertList = offObsCharacteristicsInsertList;
	}
	public List<OffObsCharacteristics> getOffObsCharacteristicsUpdateList() {
		return offObsCharacteristicsUpdateList;
	}
	public void setOffObsCharacteristicsUpdateList(List<OffObsCharacteristics> offObsCharacteristicsUpdateList) {
		this.offObsCharacteristicsUpdateList = offObsCharacteristicsUpdateList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getCanDisplay() {
		return canDisplay;
	}
	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}
	
	
}
