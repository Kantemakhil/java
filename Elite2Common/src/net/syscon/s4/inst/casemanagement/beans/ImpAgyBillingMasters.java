package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImpAgyBillingMasters extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("impAgyBillingMasterId")
	private BigDecimal impAgyBillingMasterId;
	@JsonProperty("instLocationId")
	private String instLocationId;
	@JsonProperty("imprisonmentStatus")
	private String imprisonmentStatus;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
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

	public ImpAgyBillingMasters() {
		// ImpAgyBillingMasters
	}

	/**
	 * @return the impAgyBillingMasterId
	 */
	public BigDecimal getImpAgyBillingMasterId() {
		return impAgyBillingMasterId;
	}

	/**
	 * @param impAgyBillingMasterId
	 *            the impAgyBillingMasterId to set
	 */
	public void setImpAgyBillingMasterId(final BigDecimal impAgyBillingMasterId) {
		this.impAgyBillingMasterId = impAgyBillingMasterId;
	}

	/**
	 * @return the instLocationId
	 */
	public String getInstLocationId() {
		return instLocationId;
	}

	/**
	 * @param instLocationId
	 *            the instLocationId to set
	 */
	public void setInstLocationId(final String instLocationId) {
		this.instLocationId = instLocationId;
	}

	/**
	 * @return the imprisonmentStatus
	 */
	public String getImprisonmentStatus() {
		return imprisonmentStatus;
	}

	/**
	 * @param imprisonmentStatus
	 *            the imprisonmentStatus to set
	 */
	public void setImprisonmentStatus(final String imprisonmentStatus) {
		this.imprisonmentStatus = imprisonmentStatus;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId
	 *            the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the sentenceCalcType
	 */
	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	/**
	 * @param sentenceCalcType
	 *            the sentenceCalcType to set
	 */
	public void setSentenceCalcType(final String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
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
	public void setModifyDatetime(Date modifyDatetime) {
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

}
