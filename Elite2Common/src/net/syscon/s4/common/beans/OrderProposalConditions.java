package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderProposalConditions extends BaseModel implements Serializable {
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	
	@JsonProperty("orderId")
	private BigDecimal orderId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("orderProposalConditionId")
	private Long orderProposalConditionId;
	
	@JsonProperty("categoryType")
	private String categoryType;
	

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	@JsonProperty("orderProposalCode")
	private String orderProposalCode;
	
	@JsonProperty("commConditionType")
	private String commConditionType;
	
	@JsonProperty("commConditionCode")
	private String commConditionCode;
	

	@JsonProperty("length")
	private Long length;
	
	@JsonProperty("lengthUnitCode")
	private String lengthUnitCode;
	
	
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

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public BigDecimal getOrderId() {
		return orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Long getOrderProposalConditionId() {
		return orderProposalConditionId;
	}

	public void setOrderProposalConditionId(Long orderProposalConditionId) {
		this.orderProposalConditionId = orderProposalConditionId;
	}

	public String getOrderProposalCode() {
		return orderProposalCode;
	}

	public void setOrderProposalCode(String orderProposalCode) {
		this.orderProposalCode = orderProposalCode;
	}

	public String getCommConditionType() {
		return commConditionType;
	}

	public void setCommConditionType(String commConditionType) {
		this.commConditionType = commConditionType;
	}

	public String getCommConditionCode() {
		return commConditionCode;
	}

	public void setCommConditionCode(String commConditionCode) {
		this.commConditionCode = commConditionCode;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getLengthUnitCode() {
		return lengthUnitCode;
	}

	public void setLengthUnitCode(String lengthUnitCode) {
		this.lengthUnitCode = lengthUnitCode;
	}

	
	
	
	
	
	
	
	

}
