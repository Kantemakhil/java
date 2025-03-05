package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class AllowableOffenceTypes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String offenceCode;

	private String statuteCode;
	
	private Long offenceId;

	private String offenceType;

	private BigDecimal recommendedBailAmount;

	private String bailAllowedFlag;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;
	
	private String offenceTypeTemp;


	/**
	 * @return the offenceCode
	 */
	public String getOffenceCode() {
		return offenceCode;
	}

	/**
	 * @param offenceCode
	 *            the offenceCode to set
	 */
	public void setOffenceCode(final String offenceCode) {
		this.offenceCode = offenceCode;
	}

	/**
	 * @return the statuteCode
	 */
	public String getStatuteCode() {
		return statuteCode;
	}

	/**
	 * @param statuteCode
	 *            the statuteCode to set
	 */
	public void setStatuteCode(final String statuteCode) {
		this.statuteCode = statuteCode;
	}

	/**
	 * @return the offenceType
	 */
	public String getOffenceType() {
		return offenceType;
	}

	/**
	 * @param offenceType
	 *            the offenceType to set
	 */
	public void setOffenceType(final String offenceType) {
		this.offenceType = offenceType;
	}

	/**
	 * @return the recommendedBailAmount
	 */
	public BigDecimal getRecommendedBailAmount() {
		return recommendedBailAmount;
	}

	/**
	 * @param recommendedBailAmount
	 *            the recommendedBailAmount to set
	 */
	public void setRecommendedBailAmount(final BigDecimal recommendedBailAmount) {
		this.recommendedBailAmount = recommendedBailAmount;
	}

	/**
	 * @return the bailAllowedFlag
	 */
	public String getBailAllowedFlag() {
		return bailAllowedFlag;
	}

	/**
	 * @param bailAllowedFlag
	 *            the bailAllowedFlag to set
	 */
	public void setBailAllowedFlag(final String bailAllowedFlag) {
		this.bailAllowedFlag = bailAllowedFlag;
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

	public Long getOffenceId() {
		return offenceId;
	}

	public void setOffenceId(Long offenceId) {
		this.offenceId = offenceId;
	}

	public String getOffenceTypeTemp() {
		return offenceTypeTemp;
	}

	public void setOffenceTypeTemp(String offenceTypeTemp) {
		this.offenceTypeTemp = offenceTypeTemp;
	}

}
