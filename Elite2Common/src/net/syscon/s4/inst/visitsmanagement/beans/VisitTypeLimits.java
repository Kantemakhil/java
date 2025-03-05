package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VisitTypeLimits extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("visitCycleLimitId")
	private Long visitCycleLimitId;

	@JsonProperty("visitType")
	private String visitType;

	@JsonProperty("maxHrsType")
	private BigDecimal maxHrsType;

	@JsonProperty("maxVisitsType")
	private Long maxVisitsType;

	@JsonProperty("maxVisitorsType")
	private Long maxVisitorsType;

	@JsonProperty("reinstateFlag")
	private String reinstateFlag;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("expiryDate")
	private Date expiryDate;

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

	@JsonProperty("mmin")
	private Long mmin;
	
	@JsonProperty("maxHrsTypeTemp")
	private BigDecimal maxHrsTypeTemp;
	
	

	public BigDecimal getMaxHrsTypeTemp() {
		return maxHrsTypeTemp;
	}

	public void setMaxHrsTypeTemp(BigDecimal maxHrsTypeTemp) {
		this.maxHrsTypeTemp = maxHrsTypeTemp;
	}

	/**
	 * @return the visitCycleLimitId
	 */
	public Long getVisitCycleLimitId() {
		return visitCycleLimitId;
	}

	/**
	 * @param visitCycleLimitId
	 *            the visitCycleLimitId to set
	 */
	public void setVisitCycleLimitId(final Long visitCycleLimitId) {
		this.visitCycleLimitId = visitCycleLimitId;
	}

	/**
	 * @return the visitType
	 */
	public String getVisitType() {
		return visitType;
	}

	/**
	 * @param visitType
	 *            the visitType to set
	 */
	public void setVisitType(final String visitType) {
		this.visitType = visitType;
	}

	/**
	 * @return the maxHrsType
	 */
	public BigDecimal getMaxHrsType() {
		return maxHrsType;
	}

	/**
	 * @param maxHrsType
	 *            the maxHrsType to set
	 */
	public void setMaxHrsType(final BigDecimal maxHrsType) {
		this.maxHrsType = maxHrsType;
	}

	/**
	 * @return the maxVisitsType
	 */
	public Long getMaxVisitsType() {
		return maxVisitsType;
	}

	/**
	 * @param maxVisitsType
	 *            the maxVisitsType to set
	 */
	public void setMaxVisitsType(final Long maxVisitsType) {
		this.maxVisitsType = maxVisitsType;
	}

	/**
	 * @return the maxVisitorsType
	 */
	public Long getMaxVisitorsType() {
		return maxVisitorsType;
	}

	/**
	 * @param maxVisitorsType
	 *            the maxVisitorsType to set
	 */
	public void setMaxVisitorsType(final Long maxVisitorsType) {
		this.maxVisitorsType = maxVisitorsType;
	}

	/**
	 * @return the reinstateFlag
	 */
	public String getReinstateFlag() {
		return reinstateFlag;
	}

	/**
	 * @param reinstateFlag
	 *            the reinstateFlag to set
	 */
	public void setReinstateFlag(final String reinstateFlag) {
		this.reinstateFlag = reinstateFlag;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
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
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
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
	public Date getModifyDatetime() {
		return modifyDatetime;
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
		return modifyUserId;
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
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
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
	 * @return the mmin
	 */
	public Long getMmin() {
		return mmin;
	}

	/**
	 * @param mmin
	 *            the mmin to set
	 */
	public void setMmin(final Long mmin) {
		this.mmin = mmin;
	}

}
