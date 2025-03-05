package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OrderProposals extends BaseModel implements Serializable {
	
	
	@JsonProperty("orderId")
	private Long orderId;
	
	@JsonProperty("orderProposalCode")
	private String orderProposalCode;
	
	@JsonProperty("rowId")
	private String rowId;
	
	
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	

	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	

	@JsonProperty("sealFlag")
	private String sealFlag;
	

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("sentenceCategory")
	private String sentenceCategory;
	
	private String oldOrderProposalCode;
	
	@JsonProperty("notSuitableFlag")
	private String notSuitableFlag;
	
	
	@JsonProperty("notSuitableReason")
	private String notSuitableReason;
	
	
	@JsonProperty("commentText")
	private String commentText;

	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public String getOrderProposalCode() {
		return orderProposalCode;
	}


	public void setOrderProposalCode(String orderProposalCode) {
		this.orderProposalCode = orderProposalCode;
	}


	public String getSentenceCalcType() {
		return sentenceCalcType;
	}


	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
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


	public Date getModifyDatetime() {
		return modifyDatetime;
	}


	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}


	public String getSentenceCategory() {
		return sentenceCategory;
	}


	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}


	public String getRowId() {
		return rowId;
	}


	public void setRowId(String rowId) {
		this.rowId = rowId;
	}


	public String getOldOrderProposalCode() {
		return oldOrderProposalCode;
	}


	public void setOldOrderProposalCode(String oldOrderProposalCode) {
		this.oldOrderProposalCode = oldOrderProposalCode;
	}


	public String getNotSuitableFlag() {
		return notSuitableFlag;
	}


	public String getNotSuitableReason() {
		return notSuitableReason;
	}


	public String getCommentText() {
		return commentText;
	}


	public void setNotSuitableFlag(String notSuitableFlag) {
		this.notSuitableFlag = notSuitableFlag;
	}


	public void setNotSuitableReason(String notSuitableReason) {
		this.notSuitableReason = notSuitableReason;
	}


	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	



	
	
	

		


	
	

}
