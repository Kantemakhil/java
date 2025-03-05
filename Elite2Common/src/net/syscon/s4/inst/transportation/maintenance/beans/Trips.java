package net.syscon.s4.inst.transportation.maintenance.beans;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;

public class Trips extends BaseModel implements Serializable {

	@JsonProperty("tripCode")
	private String tripCode;

	@JsonProperty("routeName")
	private String routeName;

	@JsonProperty("vehicleId")
	private Long vehicleId;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("startTime")
	private Date startTime;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("endTime")
	private Date endTime;

	@JsonProperty("status")
	private String status;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("vehicleUseCode")
	private String vehicleUseCode;

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

	@JsonProperty("listSeq")
	private Long listSeq;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("tripType")
	private String tripType;

	@JsonProperty("description")
	private String description;

	@JsonProperty("expiryDate")
	private Date expiryDate;
	
	@JsonProperty("vSdate")
	private Date vSdate;
	
	@JsonProperty("vEdate")
	private Date vEdate;

	public Trips() {

	}
	

	public Date getvSdate() {
		return vSdate;
	}


	public void setvSdate(Date vSdate) {
		this.vSdate = vSdate;
	}


	public Date getvEdate() {
		return vEdate;
	}


	public void setvEdate(Date vEdate) {
		this.vEdate = vEdate;
	}


	public String getTripCode() {
		return tripCode;
	}

	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getVehicleUseCode() {
		return vehicleUseCode;
	}

	public void setVehicleUseCode(String vehicleUseCode) {
		this.vehicleUseCode = vehicleUseCode;
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

	public Long getListSeq() {
		return listSeq;
	}

	public void setListSeq(Long listSeq) {
		this.listSeq = listSeq;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	@Override
	public String toString() {
		return "Trips [tripCode=" + tripCode + ", routeName=" + routeName + ", vehicleId=" + vehicleId + ", startDate="
				+ startDate + ", startTime=" + startTime + ", endDate=" + endDate + ", endTime=" + endTime + ", status="
				+ status + ", commentText=" + commentText + ", vehicleUseCode=" + vehicleUseCode + ", createDatetime="
				+ createDatetime + ", createUserId=" + createUserId + ", modifyDatetime=" + modifyDatetime
				+ ", modifyUserId=" + modifyUserId + ", sealFlag=" + sealFlag + ", listSeq=" + listSeq + ", activeFlag="
				+ activeFlag + ", tripType=" + tripType + ", description=" + description + ", expiryDate=" + expiryDate
				+ ", vSdate=" + vSdate + ", vEdate=" + vEdate + "]";
	}

	

}
