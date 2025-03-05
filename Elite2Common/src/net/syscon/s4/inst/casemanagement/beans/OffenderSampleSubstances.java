package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSampleSubstances extends BaseModel implements Serializable {
	
private static final long serialVersionUID = 1L;

	@JsonProperty("offenderSampleSubstanceId")
	private Integer offenderSampleSubstanceId;

	@JsonProperty("offenderSampleId")
	private Integer offenderSampleId;
	
	@JsonProperty("substanceCode")
	private String substanceCode;

	@JsonProperty("resultCode")
	private String resultCode;
	
	@JsonProperty("dispositionCode")
	private String dispositionCode;

	@JsonProperty("commentText")
	private String commentText;
	
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
	
	public OffenderSampleSubstances() {
		// offenderSampleSubstances
	}

	public Integer getOffenderSampleSubstanceId() {
		return offenderSampleSubstanceId;
	}

	public void setOffenderSampleSubstanceId(final Integer offenderSampleSubstanceId) {
		this.offenderSampleSubstanceId = offenderSampleSubstanceId;
	}

	public Integer getOffenderSampleId() {
		return offenderSampleId;
	}

	public void setOffenderSampleId(final Integer offenderSampleId) {
		this.offenderSampleId = offenderSampleId;
	}

	public String getSubstanceCode() {
		return substanceCode;
	}

	public void setSubstanceCode(final String substanceCode) {
		this.substanceCode = substanceCode;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(final String resultCode) {
		this.resultCode = resultCode;
	}

	public String getDispositionCode() {
		return dispositionCode;
	}

	public void setDispositionCode(final String dispositionCode) {
		this.dispositionCode = dispositionCode;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
}
