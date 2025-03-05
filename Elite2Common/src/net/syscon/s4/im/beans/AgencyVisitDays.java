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
 * The persistent class for the AGENCY_VISIT_DAYS database table.
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AgencyVisitDays extends  BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
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

	//bi-directional many-to-one association to AgencyLocation
	private AgencyLocations agencyLocation;

	//bi-directional many-to-one association to AgencyVisitTime
	private List<AgencyVisitTimes> agencyVisitTimes;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("weekDay")
	private String weekDay;

	public AgencyVisitDays() {
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
	 * @return the modifyDatetime
	 */
	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
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
	 * @return the agencyLocation
	 */
	public AgencyLocations getAgencyLocation() {
		return this.agencyLocation;
	}
	/**
	 * @param agencyLocation
	 *            the agencyLocation to set
	 */
	public void setAgencyLocation(final AgencyLocations agencyLocation) {
		this.agencyLocation = agencyLocation;
	}
	/**
	 * @return the agencyVisitTimes
	 */
	public List<AgencyVisitTimes> getAgencyVisitTimes() {
		return this.agencyVisitTimes;
	}
	/**
	 * @param agencyVisitTimes
	 *            the agencyVisitTimes to set
	 */
	public void setAgencyVisitTimes(final List<AgencyVisitTimes> agencyVisitTimes) {
		this.agencyVisitTimes = agencyVisitTimes;
	}

	public AgencyVisitTimes addAgencyVisitTime(final AgencyVisitTimes agencyVisitTime) {
		getAgencyVisitTimes().add(agencyVisitTime);
		agencyVisitTime.setAgencyVisitDay(this);

		return agencyVisitTime;
	}

	public AgencyVisitTimes removeAgencyVisitTime(final AgencyVisitTimes agencyVisitTime) {
		getAgencyVisitTimes().remove(agencyVisitTime);
		agencyVisitTime.setAgencyVisitDay(null);

		return agencyVisitTime;
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

		return hash;
	}

}
