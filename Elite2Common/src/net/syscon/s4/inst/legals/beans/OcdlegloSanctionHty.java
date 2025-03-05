package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OcdlegloSanctionHty extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offSanctionSentHtyId")
	private BigDecimal offSanctionSentHtyId;
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;	
	
	@JsonProperty("sentenceSeq")
	private Integer sentenceSeq;
	
	@JsonProperty("orderType")
	private String orderType;	
	
	@JsonProperty("adjustReason")
	private String adjustReason;
	
	@JsonProperty("staffId")
	private BigDecimal staffId;	
	
	@JsonProperty("adjustDate")
	private Date adjustDate;
	
	@JsonProperty("adjustTime")
	private Date adjustTime;	
	
	@JsonProperty("newCounter")
	private Integer newCounter;	
	
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
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("noOfUnexcusedAbsence")
	private long noOfUnexcusedAbsence;
	
	@JsonProperty("staffName")
	private String staffName;

	public BigDecimal getOffSanctionSentHtyId() {
		return offSanctionSentHtyId;
	}

	public void setOffSanctionSentHtyId(BigDecimal offSanctionSentHtyId) {
		this.offSanctionSentHtyId = offSanctionSentHtyId;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	public BigDecimal getStaffId() {
		return staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public Date getAdjustTime() {
		return adjustTime;
	}

	public void setAdjustTime(Date adjustTime) {
		this.adjustTime = adjustTime;
	}

	public Integer getNewCounter() {
		return newCounter;
	}

	public void setNewCounter(Integer newCounter) {
		this.newCounter = newCounter;
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

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public long getNoOfUnexcusedAbsence() {
		return noOfUnexcusedAbsence;
	}

	public void setNoOfUnexcusedAbsence(long noOfUnexcusedAbsence) {
		this.noOfUnexcusedAbsence = noOfUnexcusedAbsence;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	
	

}
