package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;


/**
 * The persistent class for the AGENCY_VISIT_SLOTS database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AgencyVisitSlots extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("agencyVisitSlotId")
	private long agencyVisitSlotId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("maxAdults")
	private BigDecimal maxAdults;
	@JsonProperty("maxGroups")
	private BigDecimal maxGroups;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("internalLocationId")
	private BigDecimal internalLocationId;
	@JsonProperty("nbtLocationDesc")
	private String nbtLocationDesc;
	@JsonProperty("pDesc")
	private String pDesc;
	@JsonProperty("canDisplay")
	private boolean canDisplay = true;
	@JsonProperty("startTime")
	private Date startTime;
	
	@JsonProperty("code")
	private String code;
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public boolean isCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(boolean canDisplay) {
		this.canDisplay = canDisplay;
	}
	/**
	 * @return the pDesc
	 */
	public String getpDesc() {
		return pDesc;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	/**
	 * @return the nbtLocationDesc
	 */
	public String getNbtLocationDesc() {
		return nbtLocationDesc;
	}

	public void setNbtLocationDesc(String nbtLocationDesc) {
		this.nbtLocationDesc = nbtLocationDesc;
	}

	/**
	 * @return the internalLocationId
	 */
	public BigDecimal getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(BigDecimal internalLocationId) {
		this.internalLocationId = internalLocationId;
	}


	@JsonProperty("weekDay")
	private String weekDay;
	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	/**
	 * @return the weekDay
	 */
	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	//bi-directional many-to-one association to AgencyInternalLocation
	private AgencyInternalLocations agencyInternalLocation;

	//bi-directional many-to-one association to AgencyVisitTime

	private AgencyVisitTimes agencyVisitTime;
	@JsonProperty("timeSlotSeq")
	private String timeSlotSeq;
	/**
	 * @return the timeSlotSeq
	 */
	public String getTimeSlotSeq() {
		return timeSlotSeq;
	}

	public void setTimeSlotSeq(String timeSlotSeq) {
		this.timeSlotSeq = timeSlotSeq;
	}

	public AgencyVisitSlots() {
	}
	/**
	 * @return the agencyVisitSlotId
	 */
	public long getAgencyVisitSlotId() {
		return this.agencyVisitSlotId;
	}

	public void setAgencyVisitSlotId(long agencyVisitSlotId) {
		this.agencyVisitSlotId = agencyVisitSlotId;
	}
	/**
	 * @return the createDatetime
	 */
	public Object getCreateDatetime() {
		return this.createDatetime;
	}


	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the maxAdults
	 */
	public BigDecimal getMaxAdults() {
		return this.maxAdults;
	}

	public void setMaxAdults(BigDecimal maxAdults) {
		this.maxAdults = maxAdults;
	}
	/**
	 * @return the maxGroups
	 */
	public BigDecimal getMaxGroups() {
		return this.maxGroups;
	}

	public void setMaxGroups(BigDecimal maxGroups) {
		this.maxGroups = maxGroups;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}


	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the getSealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	/**
	 * @return the getAgencyInternalLocation
	 */
	public AgencyInternalLocations getAgencyInternalLocation() {
		return this.agencyInternalLocation;
	}

	public void setAgencyInternalLocation(AgencyInternalLocations agencyInternalLocation) {
		this.agencyInternalLocation = agencyInternalLocation;
	}

	public AgencyVisitTimes getAgencyVisitTime() {
		return this.agencyVisitTime;
	}

	public void setAgencyVisitTime(AgencyVisitTimes agencyVisitTime) {
		this.agencyVisitTime = agencyVisitTime;
	}
	@JsonProperty("pIntLocId")
	private String pIntLocId;
	@JsonProperty("pAgyLocId")
	private String pAgyLocId;
	@JsonProperty("pCapacity")
	private String pCapacity;
	
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("visitDate")
    private Date visitDate;
	@JsonProperty("capacity")
	private int capacity;
	@JsonProperty("groupsBooked")
	private long groupsBooked;
	@JsonProperty("totalBooked")
	private long totalBooked;
	@JsonProperty("adultsBooked")
	private long adultsBooked;
	@JsonProperty("description")
	private String description;
	
	/**
	 * @return the pIntLocId
	 */
	public String getpIntLocId() {
		return pIntLocId;
	}

	public void setpIntLocId(String pIntLocId) {
		this.pIntLocId = pIntLocId;
	}
	/**
	 * @return the pAgyLocId
	 */
	public String getpAgyLocId() {
		return pAgyLocId;
	}

	public void setpAgyLocId(String pAgyLocId) {
		this.pAgyLocId = pAgyLocId;
	}
	/**
	 * @return the pCapacity
	 */
	public String getpCapacity() {
		return pCapacity;
	}

	public void setpCapacity(String pCapacity) {
		this.pCapacity = pCapacity;
	}
	@JsonProperty("returnValue")
	private BigDecimal returnValue;
	/**
	 * @return the returnValue
	 */
	public BigDecimal getReturnValue() {
		return returnValue;
	}



	public void setReturnValue(BigDecimal returnValue) {
		this.returnValue = returnValue;
	}
	@JsonProperty("serverCode")
	private int serverCode;
	/**
	 * @return the serverCode
	 */
	public int getServerCode() {
		return serverCode;
	}

	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the visitDate
	 */
	public Date getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the groupsBooked
	 */
	public long getGroupsBooked() {
		return groupsBooked;
	}

	/**
	 * @param groupsBooked the groupsBooked to set
	 */
	public void setGroupsBooked(long groupsBooked) {
		this.groupsBooked = groupsBooked;
	}

	/**
	 * @return the totalBooked
	 */
	public long getTotalBooked() {
		return totalBooked;
	}

	/**
	 * @param totalBooked the totalBooked to set
	 */
	public void setTotalBooked(long totalBooked) {
		this.totalBooked = totalBooked;
	}

	/**
	 * @return the adultsBooked
	 */
	public long getAdultsBooked() {
		return adultsBooked;
	}

	/**
	 * @param adultsBooked the adultsBooked to set
	 */
	public void setAdultsBooked(long adultsBooked) {
		this.adultsBooked = adultsBooked;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
