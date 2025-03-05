package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class IepLevelBean extends BaseModel implements Serializable {

	@JsonProperty("iepLevelCode")
	private String iepLevelCode;
	@JsonProperty("iepLeveldescription")
	private String iepLeveldescription;
	@JsonProperty("sequence")
	private Integer sequence;
	@JsonProperty("reviewDays")
	private Integer reviewDays;
	@JsonProperty("intakeIpe")
	private String intakeIpe;
	@JsonProperty("canteenLimit")
	private Float canteenLimit;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("code")
	private String code;
	@JsonProperty("description")
	private String description;
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("dateAsigned")
	private Date dateAsigned;
	@JsonProperty("approvedStaff")
	private String approvedStaff;
	@JsonProperty("reviewComment")
	private String reviewComment;
	@JsonProperty("nextReviewDate")
	private Date nextReviewDate;
	@JsonProperty("staffId")
	private Integer staffId;
	@JsonProperty("agencyLocation")
	private  String agencyLocation;
	@JsonProperty("livingUnit")
	private String livingUnit;
	
	private Long internalLocationId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("staffMailId")
	private String staffMailId;
	
	@JsonProperty("offenderId")
	private Integer offenderId;
	
	
	
	@JsonProperty("offenderName")
	private String offenderName;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("currentDate")
	private Date currentDate;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay;
	
	
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public Long getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getAgencyLocation() {
		return agencyLocation;
	}

	public void setAgencyLocation(String agencyLocation) {
		this.agencyLocation = agencyLocation;
	}

	public String getLivingUnit() {
		return livingUnit;
	}

	public void setLivingUnit(String livingUnit) {
		this.livingUnit = livingUnit;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getDateAsigned() {
		return dateAsigned;
	}

	public void setDateAsigned(Date dateAsigned) {
		this.dateAsigned = dateAsigned;
	}

	public String getApprovedStaff() {
		return approvedStaff;
	}

	public void setApprovedStaff(String approvedStaff) {
		this.approvedStaff = approvedStaff;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public Date getNextReviewDate() {
		return nextReviewDate;
	}

	public void setNextReviewDate(Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}

	public String getIepLevelCode() {
		return iepLevelCode;
	}

	public void setIepLevelCode(String iepLevelCode) {
		this.iepLevelCode = iepLevelCode;
	}

	public String getIepLeveldescription() {
		return iepLeveldescription;
	}

	public void setIepLeveldescription(String iepLeveldescription) {
		this.iepLeveldescription = iepLeveldescription;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getReviewDays() {
		return reviewDays;
	}

	public void setReviewDays(Integer reviewDays) {
		this.reviewDays = reviewDays;
	}

	public String getIntakeIpe() {
		return intakeIpe;
	}

	public void setIntakeIpe(String intakeIpe) {
		this.intakeIpe = intakeIpe;
	}

	public Float getCanteenLimit() {
		return canteenLimit;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public void setCanteenLimit(Float canteenLimit) {
		this.canteenLimit = canteenLimit;
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

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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

	@Override
	public String toString() {
		return "IepLevelBean [iepLevelCode=" + iepLevelCode + ", iepLeveldescription=" + iepLeveldescription
				+ ", sequence=" + sequence + ", reviewDays=" + reviewDays + ", intakeIpe=" + intakeIpe
				+ ", canteenLimit=" + canteenLimit + ", activeFlag=" + activeFlag + ", expiryDate=" + expiryDate
				+ ", createDatetime=" + createDatetime + ", modifyDatetime=" + modifyDatetime + ", createUserId="
				+ createUserId + ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + ", code=" + code
				+ ", description=" + description + ", offenderBookId=" + offenderBookId + ", dateAsigned=" + dateAsigned
				+ ", approvedStaff=" + approvedStaff + ", reviewComment=" + reviewComment + ", nextReviewDate="
				+ nextReviewDate + ", staffId=" + staffId + "]";
	}

	public String getStaffMailId() {
		return staffMailId;
	}

	public void setStaffMailId(String staffMailId) {
		this.staffMailId = staffMailId;
	}

	public Integer getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Integer offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}
