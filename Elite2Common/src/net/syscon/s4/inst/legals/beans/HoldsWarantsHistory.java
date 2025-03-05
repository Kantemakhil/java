package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class HoldsWarantsHistory extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("holdWarrentId")
	private Long holdWarrentId;
	
	@JsonProperty("holdWarrentDetainerHistoryId")
	private Long holdWarrentDetainerHistoryId;
	
	@JsonProperty("eventType")
	private String eventType;
	
	@JsonProperty("eventComment")
	private String eventComment;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("eventDateTime")
	private Date eventDateTime;
	
	@JsonProperty("createDateTime")
	private Date createDateTime;
	
	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	public Long getHoldWarrentId() {
		return holdWarrentId;
	}

	public void setHoldWarrentId(Long holdWarrentId) {
		this.holdWarrentId = holdWarrentId;
	}

	public Long getHoldWarrentDetainerHistoryId() {
		return holdWarrentDetainerHistoryId;
	}

	public void setHoldWarrentDetainerHistoryId(Long holdWarrentDetainerHistoryId) {
		this.holdWarrentDetainerHistoryId = holdWarrentDetainerHistoryId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventComment() {
		return eventComment;
	}

	public void setEventComment(String eventComment) {
		this.eventComment = eventComment;
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

	public Date getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}
	
	

}
