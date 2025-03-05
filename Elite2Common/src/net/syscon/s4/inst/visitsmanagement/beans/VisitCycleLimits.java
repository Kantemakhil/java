package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VisitCycleLimits extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("visitCycleLimitId")
       private Long visitCycleLimitId;
	

	@JsonProperty("agyLocId")
    private String agyLocId;
	
	@JsonProperty("secLevel")
    private String secLevel;
	
	@JsonProperty("cycleType")
    private String cycleType;
	
	@JsonProperty("totHrs")
    private BigDecimal totHrs;
	
	@JsonProperty("totVisits")
    private Long totVisits;
	
	@JsonProperty("startDay")
    private String startDay;
	
	@JsonProperty("expiryDate")
    private Date expiryDate;
	
	@JsonProperty("activeFlag")
    private String activeFlag;
	
	@JsonProperty("createDatetime")
    private Date createDatetime;
	
	@JsonProperty("createUserId")
    private String createUserId;
	
	@JsonProperty("modifyDatetime")
    private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
    private String modifyUserId;
	
	@JsonProperty("tmin")
	private Long tmin;
	
	@JsonProperty("agencyVisitConfig")
	private String agencyVisitConfig;
	
	@JsonProperty("visitConfigTypeValue")
	private String visitConfigTypeValue;
	
	@JsonProperty("visitConfigType")
	private String visitConfigType;
	
	
	@JsonProperty("visitCount")
	private Integer visitCount;
	
		


	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		visitCount = visitCount;
	}

	public String getAgencyVisitConfig() {
		return agencyVisitConfig;
	}

	public void setAgencyVisitConfig(String agencyVisitConfig) {
		this.agencyVisitConfig = agencyVisitConfig;
	}

	public String getVisitConfigTypeValue() {
		return visitConfigTypeValue;
	}

	public void setVisitConfigTypeValue(String visitConfigTypeValue) {
		this.visitConfigTypeValue = visitConfigTypeValue;
	}

	public String getVisitConfigType() {
		return visitConfigType;
	}

	public void setVisitConfigType(String visitConfigType) {
		this.visitConfigType = visitConfigType;
	}

	/**
	 * @return the visitCycleLimitId
	 */
	public Long getVisitCycleLimitId() {
		return visitCycleLimitId;
	}

	/**
	 * @param visitCycleLimitId the visitCycleLimitId to set
	 */
	public void setVisitCycleLimitId(final Long visitCycleLimitId) {
		this.visitCycleLimitId = visitCycleLimitId;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the secLevel
	 */
	public String getSecLevel() {
		return secLevel;
	}

	/**
	 * @param secLevel the secLevel to set
	 */
	public void setSecLevel(final String secLevel) {
		this.secLevel = secLevel;
	}

	/**
	 * @return the cycleType
	 */
	public String getCycleType() {
		return cycleType;
	}

	/**
	 * @param cycleType the cycleType to set
	 */
	public void setCycleType(final String cycleType) {
		this.cycleType = cycleType;
	}

	/**
	 * @return the totHrs
	 */
	public BigDecimal getTotHrs() {
		return totHrs;
	}

	/**
	 * @param totHrs the totHrs to set
	 */
	public void setTotHrs(final BigDecimal totHrs) {
		this.totHrs = totHrs;
	}

	/**
	 * @return the totVisits
	 */
	public Long getTotVisits() {
		return totVisits;
	}

	/**
	 * @param totVisits the totVisits to set
	 */
	public void setTotVisits(final Long totVisits) {
		this.totVisits = totVisits;
	}

	/**
	 * @return the startDay
	 */
	public String getStartDay() {
		return startDay;
	}

	/**
	 * @param startDay the startDay to set
	 */
	public void setStartDay(final String startDay) {
		this.startDay = startDay;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the tmin
	 */
	public Long getTmin() {
		return tmin;
	}

	/**
	 * @param tmin the tmin to set
	 */
	public void setTmin(final Long tmin) {
		this.tmin = tmin;
	}

	
	@JsonProperty("sealFlag")
    private String sealFlag;



	
}
