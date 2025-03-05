package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VOcuavlocUnavailable
 */
public class VOcuavlocUnavailable extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("weekDay")
	private String weekDay;
	@JsonProperty("internalLocationId")
	private Integer internalLocationId;
	@JsonProperty("agencyVisitSlotId")
	private Integer agencyVisitSlotId;
	@JsonProperty("maxGroups")
	private Integer maxGroups;
	@JsonProperty("maxAdults")
	private Integer maxAdults;
	@JsonProperty("capacity")
	private Integer capacity;
	@JsonProperty("groupsBooked")
	private Integer groupsBooked;
	@JsonProperty("totalBooked")
	private Integer totalBooked;
	@JsonProperty("adultsBooked")
	private Integer adultsBooked;
	@JsonProperty("description")
	private String description;
	@JsonProperty("startTime")
	private String startTime;
	@JsonProperty("endTime")
	private String endTime;
	@JsonProperty("visitDate")
	private Date visitDate;
	private boolean inserted;

	/**
	 * @param agyLocId
	 *            agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * return theagyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}

	/**
	 * @param weekDay
	 *            weekDay to set
	 */
	public void setWeekDay(final String weekDay) {
		this.weekDay = weekDay;
	}

	/**
	 * return theweekDay
	 */
	public String getWeekDay() {
		return this.weekDay;
	}

	/**
	 * @param internalLocationId
	 *            internalLocationId to set
	 */
	public void setInternalLocationId(final Integer internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	/**
	 * return theinternalLocationId
	 */
	public Integer getInternalLocationId() {
		return this.internalLocationId;
	}

	/**
	 * @param agencyVisitSlotId
	 *            agencyVisitSlotId to set
	 */
	public void setAgencyVisitSlotId(final Integer agencyVisitSlotId) {
		this.agencyVisitSlotId = agencyVisitSlotId;
	}

	/**
	 * return theagencyVisitSlotId
	 */
	public Integer getAgencyVisitSlotId() {
		return this.agencyVisitSlotId;
	}

	/**
	 * @param maxGroups
	 *            maxGroups to set
	 */
	public void setMaxGroups(final Integer maxGroups) {
		this.maxGroups = maxGroups;
	}

	/**
	 * return themaxGroups
	 */
	public Integer getMaxGroups() {
		return this.maxGroups;
	}

	/**
	 * @param maxAdults
	 *            maxAdults to set
	 */
	public void setMaxAdults(final Integer maxAdults) {
		this.maxAdults = maxAdults;
	}

	/**
	 * return themaxAdults
	 */
	public Integer getMaxAdults() {
		return this.maxAdults;
	}

	/**
	 * @param capacity
	 *            capacity to set
	 */
	public void setCapacity(final Integer capacity) {
		this.capacity = capacity;
	}

	/**
	 * return thecapacity
	 */
	public Integer getCapacity() {
		return this.capacity;
	}

	/**
	 * @param groupsBooked
	 *            groupsBooked to set
	 */
	public void setGroupsBooked(final Integer groupsBooked) {
		this.groupsBooked = groupsBooked;
	}

	/**
	 * return thegroupsBooked
	 */
	public Integer getGroupsBooked() {
		return this.groupsBooked;
	}

	/**
	 * @param totalBooked
	 *            totalBooked to set
	 */
	public void setTotalBooked(final Integer totalBooked) {
		this.totalBooked = totalBooked;
	}

	/**
	 * return thetotalBooked
	 */
	public Integer getTotalBooked() {
		return this.totalBooked;
	}

	/**
	 * @param adultsBooked
	 *            adultsBooked to set
	 */
	public void setAdultsBooked(final Integer adultsBooked) {
		this.adultsBooked = adultsBooked;
	}

	/**
	 * return theadultsBooked
	 */
	public Integer getAdultsBooked() {
		return this.adultsBooked;
	}

	/**
	 * @param description
	 *            description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * return thedescription
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param startTime
	 *            startTime to set
	 */
	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}

	/**
	 * return thestartTime
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * @param endTime
	 *            endTime to set
	 */
	public void setEndTime(final String endTime) {
		this.endTime = endTime;
	}

	/**
	 * return theendTime
	 */
	public String getEndTime() {
		return this.endTime;
	}

	/**
	 * @param visitDate
	 *            visitDate to set
	 */
	public void setVisitDate(final Date visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * return thevisitDate
	 */
	public Date getVisitDate() {
		return this.visitDate;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

}