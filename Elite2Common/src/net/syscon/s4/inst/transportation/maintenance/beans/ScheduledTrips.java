package net.syscon.s4.inst.transportation.maintenance.beans;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;
public class ScheduledTrips extends BaseModel implements Serializable {

	@JsonProperty("scheduledTripId")
	private Long scheduledTripId;

	@JsonProperty("tripCode")
	private String tripCode;

	@JsonProperty("routeName")
	private String routeName;

	@JsonProperty("departureDate")
	private Date departureDate;

	@JsonProperty("completionDate")
	private Date completionDate;

	@JsonProperty("cancelFlag")
	private String cancelFlag;

	@JsonProperty("cancelBy")
	private String cancelBy;

	@JsonProperty("cancelDate")
	private Date cancelDate;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("estDepartureTime")
	private Date estDepartureTime;

	@JsonProperty("estCompletionTime")
	private Date estCompletionTime;

	@JsonProperty("status")
	private String status;

	@JsonProperty("actDepartureTime")
	private Date actDepartureTime;

	@JsonProperty("actCompletionTime")
	private Date actCompletionTime;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("vAction")
	private String vAction;

	@JsonProperty("vNum")
	private Long vNum;

	@JsonProperty("btnStatus")
	private String btnStatus;

	@JsonProperty("vBkg")
	private Long vBkg;

	@JsonProperty("vNonAdt")
	private Long vNonAdt;

	@JsonProperty("vFlag")
	private String vFlag;

	@JsonProperty("vCount")
	private Long vCount;

	@JsonProperty("vMdate")
	private Date vMdate;

	@JsonProperty("vMdate1")
	private Date vMdate1;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("gStartDate")
	private Date gStartDate;

	@JsonProperty("gEndDate")
	private Date gEndDate;

	@JsonProperty("gCommitFlag")
	private String gCommitFlag;

	@JsonProperty("weekNo")
	private Integer weekNo;

	@JsonProperty("day")
	private String day;

	@JsonProperty("startDay")
	private String startDay;

	@JsonProperty("endDay")
	private String endDay;

	@JsonProperty("estEndTime")
	private String estEndTime;

	@JsonProperty("tripType")
	private String tripType;

	@JsonProperty("vCnt")
	private Integer vCnt;

	@JsonProperty("lNonOffCount")
	private Integer lNonOffCount;

	@JsonProperty("lVehicleCount")
	private Integer lVehicleCount;

	@JsonProperty("lStaffCount")
	private Integer lStaffCount;

	@JsonProperty("weekDay")
	private String weekDay;

	@JsonProperty("validateDate")
	private Date validateDate;
	
	@JsonProperty("ifOffOnTripCur")
	private Date ifOffOnTripCur;
	
	@JsonProperty("tripStartDate")
	private Date tripStartDate;
	
	@JsonProperty("vCurNum")
	private Integer vCurNum;
	
	@JsonProperty("description")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Integer getvCurNum() {
		return vCurNum;
	}

	public void setvCurNum(Integer vCurNum) {
		this.vCurNum = vCurNum;
	}

	public Date getIfOffOnTripCur() {
		return ifOffOnTripCur;
	}

	@JsonProperty("optCap")
	private Long optCap;

	@JsonProperty("physCap")
	private Long physCap;

	public Long getOptCap() {
		return optCap;
	}

	public void setOptCap(Long optCap) {
		this.optCap = optCap;
	}

	public Long getPhysCap() {
		return physCap;
	}

	public void setPhysCap(Long physCap) {
		this.physCap = physCap;
	}

	@JsonProperty("tripEndDate")
	private Date tripEndDate;
	
	
	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getgStartDate() {
		return gStartDate;
	}

	public Date getgEndDate() {
		return gEndDate;
	}

	public String getgCommitFlag() {
		return gCommitFlag;
	}

	public Integer getWeekNo() {
		return weekNo;
	}

	public String getDay() {
		return day;
	}

	public String getStartDay() {
		return startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public String getEstEndTime() {
		return estEndTime;
	}

	public String getTripType() {
		return tripType;
	}

	public Integer getvCnt() {
		return vCnt;
	}

	public Integer getlNonOffCount() {
		return lNonOffCount;
	}

	public Integer getlVehicleCount() {
		return lVehicleCount;
	}

	public Integer getlStaffCount() {
		return lStaffCount;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public Date getValidateDate() {
		return validateDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setgStartDate(Date gStartDate) {
		this.gStartDate = gStartDate;
	}

	public void setgEndDate(Date gEndDate) {
		this.gEndDate = gEndDate;
	}

	public void setgCommitFlag(String gCommitFlag) {
		this.gCommitFlag = gCommitFlag;
	}

	public void setWeekNo(Integer weekNo) {
		this.weekNo = weekNo;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public void setEstEndTime(String estEndTime) {
		this.estEndTime = estEndTime;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public void setvCnt(Integer vCnt) {
		this.vCnt = vCnt;
	}

	public void setlNonOffCount(Integer lNonOffCount) {
		this.lNonOffCount = lNonOffCount;
	}

	public void setlVehicleCount(Integer lVehicleCount) {
		this.lVehicleCount = lVehicleCount;
	}

	public void setlStaffCount(Integer lStaffCount) {
		this.lStaffCount = lStaffCount;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
	}

	public Date getvMdate1() {
		return vMdate1;
	}

	public void setvMdate1(Date vMdate1) {
		this.vMdate1 = vMdate1;
	}

	public String getvFlag() {
		return vFlag;
	}

	public void setvFlag(String vFlag) {
		this.vFlag = vFlag;
	}

	public Long getvCount() {
		return vCount;
	}

	public void setvCount(Long vCount) {
		this.vCount = vCount;
	}

	public Date getvMdate() {
		return vMdate;
	}

	public void setvMdate(Date vMdate) {
		this.vMdate = vMdate;
	}

	public Long getvBkg() {
		return vBkg;
	}

	public void setvBkg(Long vBkg) {
		this.vBkg = vBkg;
	}

	public Long getvNonAdt() {
		return vNonAdt;
	}

	public void setvNonAdt(Long vNonAdt) {
		this.vNonAdt = vNonAdt;
	}

	public String getvAction() {
		return vAction;
	}

	public void setvAction(String vAction) {
		this.vAction = vAction;
	}

	public Long getvNum() {
		return vNum;
	}

	public void setvNum(Long vNum) {
		this.vNum = vNum;
	}

	public ScheduledTrips() {

	}

	public Long getScheduledTripId() {
		return scheduledTripId;
	}

	public void setScheduledTripId(Long scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
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

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getBtnStatus() {
		return btnStatus;
	}

	public void setBtnStatus(String btnStatus) {
		this.btnStatus = btnStatus;
	}
public Date getTripStartDate() {
		return tripStartDate;
	}

	public Date getTripEndDate() {
		return tripEndDate;
	}

	public void setIfOffOnTripCur(Date ifOffOnTripCur) {
		this.ifOffOnTripCur = ifOffOnTripCur;
	}

	public void setTripStartDate(Date tripStartDate) {
		this.tripStartDate = tripStartDate;
	}

	public void setTripEndDate(Date tripEndDate) {
		this.tripEndDate = tripEndDate;
	}

	@Override
	public String toString() {
		return "ScheduledTrips [scheduledTripId=" + scheduledTripId + ", tripCode=" + tripCode + ", routeName="
				+ routeName + ", departureDate=" + departureDate + ", completionDate=" + completionDate
				+ ", cancelFlag=" + cancelFlag + ", cancelBy=" + cancelBy + ", cancelDate=" + cancelDate
				+ ", createUserId=" + createUserId + ", createDatetime=" + createDatetime + ", estDepartureTime="
				+ estDepartureTime + ", estCompletionTime=" + estCompletionTime + ", status=" + status
				+ ", actDepartureTime=" + actDepartureTime + ", actCompletionTime=" + actCompletionTime + ", sealFlag="
				+ sealFlag + ", modifyDatetime=" + modifyDatetime + ", modifyUserId=" + modifyUserId + ", vAction="
				+ vAction + ", vNum=" + vNum + ", btnStatus=" + btnStatus + ", vBkg=" + vBkg + ", vNonAdt=" + vNonAdt
				+ ", vFlag=" + vFlag + ", vCount=" + vCount + ", vMdate=" + vMdate + ", vMdate1=" + vMdate1
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", gStartDate=" + gStartDate + ", gEndDate="
				+ gEndDate + ", gCommitFlag=" + gCommitFlag + ", weekNo=" + weekNo + ", day=" + day + ", startDay="
				+ startDay + ", endDay=" + endDay + ", estEndTime=" + estEndTime + ", tripType=" + tripType + ", vCnt="
				+ vCnt + ", lNonOffCount=" + lNonOffCount + ", lVehicleCount=" + lVehicleCount + ", lStaffCount="
				+ lStaffCount + ", weekDay=" + weekDay + ", validateDate=" + validateDate + ", ifOffOnTripCur="
				+ ifOffOnTripCur + ", tripStartDate=" + tripStartDate + ", vCurNum=" + vCurNum + ", optCap=" + optCap
				+ ", physCap=" + physCap + ", tripEndDate=" + tripEndDate + "]";
	}

	
	

}
