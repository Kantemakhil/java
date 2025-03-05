package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrdersHty extends BaseModel implements Serializable {

	@JsonProperty("orderHtyId")
	private Long orderHtyId;

	@JsonProperty("orderId")
	private BigDecimal orderId;

	@JsonProperty("orderType")
	private String orderType;

	@JsonProperty("issuingAgyLocId")
	private String issuingAgyLocId;

	@JsonProperty("requestingOfficer")
	private String requestingOfficer;

	@JsonProperty("requestDate")
	private Date requestDate;

	@JsonProperty("dueDate")
	private Date dueDate;

	@JsonProperty("teamId")
	private String teamId;

	@JsonProperty("staffMemberId")
	private String staffMemberId;

	@JsonProperty("orderStatus")
	private String orderStatus;

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
	
	@JsonProperty("defenceSolicitor")
	private String defenceSolicitor;
	
	

	public Long getOrderHtyId() {
		return orderHtyId;
	}

	public void setOrderHtyId(Long orderHtyId) {
		this.orderHtyId = orderHtyId;
	}

	public BigDecimal getOrderId() {
		return orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getIssuingAgyLocId() {
		return issuingAgyLocId;
	}

	public void setIssuingAgyLocId(String issuingAgyLocId) {
		this.issuingAgyLocId = issuingAgyLocId;
	}

	public String getRequestingOfficer() {
		return requestingOfficer;
	}

	public void setRequestingOfficer(String requestingOfficer) {
		this.requestingOfficer = requestingOfficer;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getStaffMemberId() {
		return staffMemberId;
	}

	public void setStaffMemberId(String staffMemberId) {
		this.staffMemberId = staffMemberId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public String getDefenceSolicitor() {
		return defenceSolicitor;
	}

	public void setDefenceSolicitor(String defenceSolicitor) {
		this.defenceSolicitor = defenceSolicitor;
	}

	@Override
	public String toString() {
		return "OrdersHty [orderHtyId=" + orderHtyId + ", orderId=" + orderId + ", orderType=" + orderType
				+ ", issuingAgyLocId=" + issuingAgyLocId + ", requestingOfficer=" + requestingOfficer + ", requestDate="
				+ requestDate + ", dueDate=" + dueDate + ", teamId=" + teamId + ", staffMemberId=" + staffMemberId
				+ ", orderStatus=" + orderStatus + ", createDatetime=" + createDatetime + ", createUserId="
				+ createUserId + ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId + ", sealFlag="
				+ sealFlag + "]";
	}

}
