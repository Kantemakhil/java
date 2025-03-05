package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class KeyDates extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("keyDates")
	private String keyDates;
	
	@JsonProperty("calculatedDate")
	private Date calculatedDate;
	
	@JsonProperty("overrideDate")
	private Date overrideDate;
	
	@JsonProperty("overridedFlag")
	private boolean overridedFlag;
	
	@JsonProperty("profileType")
	private String profileType;
	
	@JsonProperty("profileType2")
	private String profileType2;
	
	@JsonProperty("dispListSeq")
	private String dispListSeq;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("staffName")
	private String staffName;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("calculationReason")
	private String calaculationReason;
	
	@JsonProperty("sentCalculationId")
	private Long sentCalculationId;
	
	public String getKeyDates() {
		return keyDates;
	}

	public void setKeyDates(String keyDates) {
		this.keyDates = keyDates;
	}

	public Date getCalculatedDate() {
		return calculatedDate;
	}

	public void setCalculatedDate(Date calculatedDate) {
		this.calculatedDate = calculatedDate;
	}

	public Date getOverrideDate() {
		return overrideDate;
	}

	public void setOverrideDate(Date overrideDate) {
		this.overrideDate = overrideDate;
	}

	public boolean isOverridedFlag() {
		return overridedFlag;
	}

	public void setOverridedFlag(boolean overridedFlag) {
		this.overridedFlag = overridedFlag;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getProfileType2() {
		return profileType2;
	}

	public void setProfileType2(String profileType2) {
		this.profileType2 = profileType2;
	}

	public String getDispListSeq() {
		return dispListSeq;
	}

	public void setDispListSeq(String dispListSeq) {
		this.dispListSeq = dispListSeq;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCalaculationReason() {
		return calaculationReason;
	}

	public void setCalaculationReason(String calaculationReason) {
		this.calaculationReason = calaculationReason;
	}

	public Long getSentCalculationId() {
		return sentCalculationId;
	}

	public void setSentCalculationId(Long sentCalculationId) {
		this.sentCalculationId = sentCalculationId;
	}
	
	

}
