package net.syscon.s4.cf.deductions.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderMonDeductions extends BaseModel implements Serializable {

	private Long beneficiaryId;

	private Date monthlyDeductionDate;

	private Long offenderDeducttionId;

	private BigDecimal receivedAmount;

	private BigDecimal personId;

	private BigDecimal corporateId;

	private String unknownBenId;

	private Long deductionAmount;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	public Date getMonthlyDeductionDate() {
		return monthlyDeductionDate;
	}

	public void setMonthlyDeductionDate(Date monthlyDeductionDate) {
		this.monthlyDeductionDate = monthlyDeductionDate;
	}

	public Long getOffenderDeducttionId() {
		return offenderDeducttionId;
	}

	public void setOffenderDeducttionId(Long offenderDeducttionId) {
		this.offenderDeducttionId = offenderDeducttionId;
	}

	public Long getDeductionAmount() {
		return deductionAmount;
	}

	public void setDeductionAmount(Long deductionAmount) {
		this.deductionAmount = deductionAmount;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Long getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getPersonId() {
		return personId;
	}

	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}

	public BigDecimal getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(BigDecimal corporateId) {
		this.corporateId = corporateId;
	}

	public String getUnknownBenId() {
		return unknownBenId;
	}

	public void setUnknownBenId(String unknownBenId) {
		this.unknownBenId = unknownBenId;
	}

}
