package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SuspendDeductionTypes extends BaseModel implements Serializable {
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
	@JsonProperty("suspendedFlag")
	private String suspendedFlag;
	@JsonProperty("suspendDeductionId")
	private Long suspendDeductionId;
	@JsonProperty("deductionType")
	private String deductionType;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("startDate")
	private Date startDate;

	/**
	 * Creates new OffenderAlerts class Object
	 */
	public SuspendDeductionTypes() {
		// SuspendDeductionTypes
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
	public void setCreateDatetime(Date createDatetime) {
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
	public void setCreateUserId(String createUserId) {
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
	public void setModifyUserId(String modifyUserId) {
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
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the suspendedFlag
	 */
	public String getSuspendedFlag() {
		return suspendedFlag;
	}

	/**
	 * @param suspendedFlag
	 *            the suspendedFlag to set
	 */
	public void setSuspendedFlag(String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	/**
	 * @return the suspendDeductionId
	 */
	public Long getSuspendDeductionId() {
		return suspendDeductionId;
	}

	/**
	 * @param suspendDeductionId
	 *            the suspendDeductionId to set
	 */
	public void setSuspendDeductionId(Long suspendDeductionId) {
		this.suspendDeductionId = suspendDeductionId;
	}

	/**
	 * @return the deductionType
	 */
	public String getDeductionType() {
		return deductionType;
	}

	/**
	 * @param deductionType
	 *            the deductionType to set
	 */
	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
