package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderPpslCondActivities extends BaseModel implements Serializable {
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("orderProposalConditionId")
	private Long orderProposalConditionId;
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("conditionActivityCode")
	private String conditionActivityCode;
	

	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("orderPpslCondActivityId")
	private Long orderPpslCondActivityId;
	
	
	@JsonProperty("programId")
	private Long programId;

	@JsonProperty("orderPropCondActivityId")
	private BigDecimal orderPropCondActivityId;
	
	@JsonProperty("orderId")
	private BigDecimal orderId;
	
	
    
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


	public String getCreateUserId() {
		return createUserId;
	}


	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}


	public String getConditionActivityCode() {
		return conditionActivityCode;
	}


	public void setConditionActivityCode(String conditionActivityCode) {
		this.conditionActivityCode = conditionActivityCode;
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


	public Long getOrderPpslCondActivityId() {
		return orderPpslCondActivityId;
	}


	public void setOrderPpslCondActivityId(Long orderPpslCondActivityId) {
		this.orderPpslCondActivityId = orderPpslCondActivityId;
	}


	public Long getProgramId() {
		return programId;
	}


	public void setProgramId(Long programId) {
		this.programId = programId;
	}


	public BigDecimal getOrderPropCondActivityId() {
		return orderPropCondActivityId;
	}


	public void setOrderPropCondActivityId(BigDecimal orderPropCondActivityId) {
		this.orderPropCondActivityId = orderPropCondActivityId;
	}


	public BigDecimal getOrderId() {
		return orderId;
	}


	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}
	
	
	
}
