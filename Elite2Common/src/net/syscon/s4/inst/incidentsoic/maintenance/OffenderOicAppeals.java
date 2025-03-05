package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderOicAppeals extends BaseModel implements Serializable {
	
	@JsonProperty("oicApprealId")
	private Long oicApprealId;
	
	@JsonProperty("offenderBookingId")
	private BigDecimal offenderBookingId;
	
	@JsonProperty("apprealDate")
	private Date apprealDate;
	
	@JsonProperty("aprrealReasonCode")
	private String aprrealReasonCode;
	
	@JsonProperty("hearingDate")
	private Date hearingDate;
	
	@JsonProperty("hearingResultCode")
	private String hearingResultCode;
	
	@JsonProperty("hearingTime")
	private Date hearingTime;
	
	@JsonProperty("heardBy")
	private Long heardBy;
	
	@JsonProperty("summary")
	private String summary;
	
	@JsonProperty("reviewDate")
	private Date reviewDate;
	
	@JsonProperty("otherRepresentative")
	private String otherRepresentative;
	
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

	public Long getOicApprealId() {
		return oicApprealId;
	}

	public void setOicApprealId(Long oicApprealId) {
		this.oicApprealId = oicApprealId;
	}

	public BigDecimal getOffenderBookingId() {
		return offenderBookingId;
	}

	public void setOffenderBookingId(BigDecimal offenderBookingId) {
		this.offenderBookingId = offenderBookingId;
	}

	public Date getApprealDate() {
		return apprealDate;
	}

	public void setApprealDate(Date apprealDate) {
		this.apprealDate = apprealDate;
	}

	public String getAprrealReasonCode() {
		return aprrealReasonCode;
	}

	public void setAprrealReasonCode(String aprrealReasonCode) {
		this.aprrealReasonCode = aprrealReasonCode;
	}

	public Date getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(Date hearingDate) {
		this.hearingDate = hearingDate;
	}

	public String getHearingResultCode() {
		return hearingResultCode;
	}

	public void setHearingResultCode(String hearingResultCode) {
		this.hearingResultCode = hearingResultCode;
	}

	public Date getHearingTime() {
		return hearingTime;
	}

	public void setHearingTime(Date hearingTime) {
		this.hearingTime = hearingTime;
	}

	public Long getHeardBy() {
		return heardBy;
	}

	public void setHeardBy(Long heardBy) {
		this.heardBy = heardBy;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getOtherRepresentative() {
		return otherRepresentative;
	}

	public void setOtherRepresentative(String otherRepresentative) {
		this.otherRepresentative = otherRepresentative;
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
	
	

}
