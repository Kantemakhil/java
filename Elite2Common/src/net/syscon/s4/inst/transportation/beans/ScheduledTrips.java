package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import net.syscon.s4.common.beans.BaseModel;

public class ScheduledTrips extends BaseModel  implements Serializable{

	private Date departureDate;
	private Integer physicalCapacity;
	private String routeName;
	private String description;
	private Date estDepartureTime;
	private Date estCompletionTime;
	private Date actDepartureTime;
	private Date actCompletionTime;
	private String tripCode;
	private BigDecimal scheduledTripId;
	private String status;
	private Date completionDate;
	private String cancelFlag;
	private String cancelBy;
	private Date cancelDate;
	private String createUserId;
	private Date createDatetime;
	private String sealFlag;
	private Date modifyDatetime;
	private String modifyUserId;
	
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getCancelBy() {
		return cancelBy;
	}
	public void setCancelBy(String cancelBy) {
		this.cancelBy = cancelBy;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
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
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Integer getPhysicalCapacity() {
		return physicalCapacity;
	}
	public void setPhysicalCapacity(Integer physicalCapacity) {
		this.physicalCapacity = physicalCapacity;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEstDepartureTime() {
		return estDepartureTime;
	}
	public void setEstDepartureTime(Date estDepartureTime) {
		this.estDepartureTime = estDepartureTime;
	}
	public Date getEstCompletionTime() {
		return estCompletionTime;
	}
	public void setEstCompletionTime(Date estCompletionTime) {
		this.estCompletionTime = estCompletionTime;
	}
	public Date getActDepartureTime() {
		return actDepartureTime;
	}
	public void setActDepartureTime(Date actDepartureTime) {
		this.actDepartureTime = actDepartureTime;
	}
	public Date getActCompletionTime() {
		return actCompletionTime;
	}
	public void setActCompletionTime(Date actCompletionTime) {
		this.actCompletionTime = actCompletionTime;
	}
	public BigDecimal getScheduledTripId() {
		return scheduledTripId;
	}
	public void setScheduledTripId(BigDecimal scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
