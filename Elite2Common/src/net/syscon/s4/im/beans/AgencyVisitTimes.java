package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;


/**
 * The persistent class for the AGENCY_VISIT_TIMES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AgencyVisitTimes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("endTime")
	private Date endTime;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("startTime")
	private Date startTime;
	@JsonProperty("rowId")
	private Long rowId;
	@JsonProperty("serverCode")
	private int serverCode;
	

	public int getServerCode() {
		return serverCode;
	}
	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}
	public Long getRowId() {
		return rowId;
	}
	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}
	//bi-directional many-to-one association to AgencyVisitSlot
	private List<AgencyVisitSlots> agencyVisitSlots;

	//bi-directional many-to-one association to AgencyVisitDay

	private AgencyVisitDays agencyVisitDay;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("weekDay")
	private String weekDay;
	@JsonProperty("timeSlotSeq")
	private String timeSlotSeq;
	@JsonProperty("internalLocationId")
	private String internalLocationId;
	/**
	 * @return the internalLocationId
	 */
	public String getInternalLocationId() {
		return internalLocationId;
	}
	/**
	 * @param internalLocationId
	 *            the internalLocationId to set
	 */
	public void setInternalLocationId(final String internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public AgencyVisitTimes() {
	}
	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return this.activeFlag;
	}
	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
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
	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return this.endTime;
	}
	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return this.expiryDate;
	}
	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}
	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}
	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return this.startTime;
	}
	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the agencyVisitSlots
	 */
	public List<AgencyVisitSlots> getAgencyVisitSlots() {
		return this.agencyVisitSlots;
	}
	/**
	 * @param agencyVisitSlots
	 *            the agencyVisitSlots to set
	 */
	public void setAgencyVisitSlots(final List<AgencyVisitSlots> agencyVisitSlots) {
		this.agencyVisitSlots = agencyVisitSlots;
	}

	public AgencyVisitSlots addAgencyVisitSlot(final AgencyVisitSlots agencyVisitSlot) {
		getAgencyVisitSlots().add(agencyVisitSlot);
		agencyVisitSlot.setAgencyVisitTime(this);

		return agencyVisitSlot;
	}

	public AgencyVisitSlots removeAgencyVisitSlot(final AgencyVisitSlots agencyVisitSlot) {
		getAgencyVisitSlots().remove(agencyVisitSlot);
		agencyVisitSlot.setAgencyVisitTime(null);

		return agencyVisitSlot;
	}
	/**
	 * @return the agencyVisitDay
	 */
	public AgencyVisitDays getAgencyVisitDay() {
		return this.agencyVisitDay;
	}
	/**
	 * @param agencyVisitDay
	 *            the agencyVisitDay to set
	 */
	public void setAgencyVisitDay(final AgencyVisitDays agencyVisitDay) {
		this.agencyVisitDay = agencyVisitDay;
	}
	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}
	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}
	/**
	 * @return the weekDay
	 */
	public String getWeekDay() {
		return this.weekDay;
	}
	/**
	 * @param weekDay
	 *            the weekDay to set
	 */
	public void setWeekDay(final String weekDay) {
		this.weekDay = weekDay;
	}
	/**
	 * @return the timeSlotSeq
	 */
	public String getTimeSlotSeq() {
		return this.timeSlotSeq;
	}
	/**
	 * @param timeSlotSeq
	 *            the timeSlotSeq to set
	 */
	public void setTimeSlotSeq(final String timeSlotSeq) {
		this.timeSlotSeq = timeSlotSeq;
	}
	@JsonProperty("returnValue")
	private BigDecimal returnValue;
	/**
	 * @return the returnValue
	 */
	public BigDecimal getReturnValue() {
		return returnValue;
	}

	/**
	 * @param returnValue
	 *            the returnValue to set
	 */

	public void setReturnValue(final BigDecimal returnValue) {
		this.returnValue = returnValue;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.agyLocId.hashCode();
		hash = hash * prime + this.weekDay.hashCode();
		hash = hash * prime + this.timeSlotSeq.hashCode();

		return hash;
	}

}
