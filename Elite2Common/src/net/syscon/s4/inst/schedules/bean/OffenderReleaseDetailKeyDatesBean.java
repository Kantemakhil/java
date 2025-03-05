package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderReleaseDetailKeyDatesBean extends BaseModel implements Serializable {
	public String getNbtName() {
		return nbtName;
	}

	public void setNbtName(String nbtName) {
		this.nbtName = nbtName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("formInfoJson")
	private String formInfoJson;
	
	@JsonProperty("formInfoJsonBlob")
	private byte[] formInfoJsonBlob;
	
	@JsonProperty("formIdentifier")
	private String formIdentifier;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("keyDateListObj")
	private List<KeyDateValueBean> keyDateListObj;
	
	@JsonProperty("nbtName")
	private String nbtName;
	
	@JsonProperty("movementReasonCode")
	private String movementReasonCode;
	
	@JsonProperty("movementType")
	private String movementType;

	@JsonProperty("releaseDate")
	private Date releaseDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("verifiedFlag")
	private String verifiedFlag;
	
	@JsonProperty("eventId")
	private BigDecimal eventId;

	@JsonProperty("eventStatus")
	private String eventStatus;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("dataExistFlag")
	private String dataExistFlag;
	
	@JsonProperty("agyLocIdDesc")
	private String agyLocIdDesc;
	
	public String getFormInfoJson() {
		return formInfoJson;
	}

	public void setFormInfoJson(String formInfoJson) {
		this.formInfoJson = formInfoJson;
	}

	public byte[] getFormInfoJsonBlob() {
		return formInfoJsonBlob;
	}

	public void setFormInfoJsonBlob(byte[] formInfoJsonBlob) {
		this.formInfoJsonBlob = formInfoJsonBlob;
	}

	public String getFormIdentifier() {
		return formIdentifier;
	}

	public void setFormIdentifier(String formIdentifier) {
		this.formIdentifier = formIdentifier;
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

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public List<KeyDateValueBean> getKeyDateListObj() {
		return keyDateListObj;
	}

	public void setKeyDateListObj(List<KeyDateValueBean> keyDateListObj) {
		this.keyDateListObj = keyDateListObj;
	}

	public String getMovementReasonCode() {
		return movementReasonCode;
	}

	public void setMovementReasonCode(String movementReasonCode) {
		this.movementReasonCode = movementReasonCode;
	}

	public String getMovementType() {
		return movementType;
	}

	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getVerifiedFlag() {
		return verifiedFlag;
	}

	public void setVerifiedFlag(String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	public BigDecimal getEventId() {
		return eventId;
	}

	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getDataExistFlag() {
		return dataExistFlag;
	}

	public void setDataExistFlag(String dataExistFlag) {
		this.dataExistFlag = dataExistFlag;
	}

	public String getAgyLocIdDesc() {
		return agyLocIdDesc;
	}

	public void setAgyLocIdDesc(String agyLocIdDesc) {
		this.agyLocIdDesc = agyLocIdDesc;
	}
	
	
	
}
