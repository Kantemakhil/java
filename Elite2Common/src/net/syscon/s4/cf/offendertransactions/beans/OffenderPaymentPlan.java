package net.syscon.s4.cf.offendertransactions.beans;


import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_PAYMENT_PLANS database table.
 * 
 */
public class OffenderPaymentPlan extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	private String code;

	private BigDecimal amount;

	private String biWeekly;

	private BigDecimal caseId;

	private Date createDatetime;

	private String createUserId;

	private Date endDate;

	private String id;

	private String frequency;

	private BigDecimal groupId;

	private String informationNumber;

	private String leniencyFlag;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal monthly;

	private BigDecimal offenderDeductionId;

	private BigDecimal offenderId;

	private BigDecimal originalOwingAmount;

	private Date paymentClosedDate;


	private String paymentClosedFlag;
	
	private String closedFlag;

	private Date paymentCompletionDate;

	private Date regenerationDate;

	private String regenerationFlag;

	private String sealFlag;

	private Date startDate;

	private BigDecimal twiceMonthly1;

	private BigDecimal twiceMonthly2;

	private String weekly;
	
   private String parentInfoId;





	private long paymentPlanId;

	private long paymentPlanSeq;
	
	
	private String paidFlag;
	
	private String jSFlag;
	
	private BigDecimal groupUnpaidAmount;
	
	private BigDecimal totalArrears;
	
	private BigDecimal groupDifference;
	
	private String distribute;
	
	private BigDecimal listSeq;
	
	private String caseLoadId;
	
	private BigDecimal paymentAmount;
	
	private String caseloadType;
	
	private int count;
	
	private String paymentFrequency;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}
	public String getParentInfoId() {
	return parentInfoId;
}

public void setParentInfoId(String parentInfoId) {
	this.parentInfoId = parentInfoId;
}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public String getClosedFlag() {
		return closedFlag;
	}

	public void setClosedFlag(String closedFlag) {
		this.closedFlag = closedFlag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getCaseLoadId() {
		return caseLoadId;
	}

	public void setCaseLoadId(String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	public BigDecimal getListSeq() {
		return listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public String getDistribute() {
		return distribute;
	}

	public void setDistribute(String distribute) {
		this.distribute = distribute;
	}

	public String getPaidFlag() {
		return paidFlag;
	}

	public void setPaidFlag(String paidFlag) {
		this.paidFlag = paidFlag;
	}

	public String getjSFlag() {
		return jSFlag;
	}

	public void setjSFlag(String jSFlag) {
		this.jSFlag = jSFlag;
	}

	public BigDecimal getGroupUnpaidAmount() {
		return groupUnpaidAmount;
	}

	public void setGroupUnpaidAmount(BigDecimal groupUnpaidAmount) {
		this.groupUnpaidAmount = groupUnpaidAmount;
	}

	public BigDecimal getTotalArrears() {
		return totalArrears;
	}

	public void setTotalArrears(BigDecimal totalArrears) {
		this.totalArrears = totalArrears;
	}

	public BigDecimal getGroupDifference() {
		return groupDifference;
	}

	public void setGroupDifference(BigDecimal groupDifference) {
		this.groupDifference = groupDifference;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Date getPaymentClosedDate() {
		return paymentClosedDate;
	}

	public void setPaymentClosedDate(Date paymentClosedDate) {
		this.paymentClosedDate = paymentClosedDate;
	}

	public Date getPaymentCompletionDate() {
		return paymentCompletionDate;
	}

	public void setPaymentCompletionDate(Date paymentCompletionDate) {
		this.paymentCompletionDate = paymentCompletionDate;
	}

	public Date getRegenerationDate() {
		return regenerationDate;
	}

	public void setRegenerationDate(Date regenerationDate) {
		this.regenerationDate = regenerationDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public long getPaymentPlanId() {
		return paymentPlanId;
	}

	public void setPaymentPlanId(long paymentPlanId) {
		this.paymentPlanId = paymentPlanId;
	}

	public long getPaymentPlanSeq() {
		return paymentPlanSeq;
	}

	public void setPaymentPlanSeq(long paymentPlanSeq) {
		this.paymentPlanSeq = paymentPlanSeq;
	}

	public OffenderPaymentPlan() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBiWeekly() {
		return this.biWeekly;
	}

	public void setBiWeekly(String biWeekly) {
		this.biWeekly = biWeekly;
	}

	public BigDecimal getCaseId() {
		return this.caseId;
	}

	public void setCaseId(BigDecimal caseId) {
		this.caseId = caseId;
	}

	

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	public String getInformationNumber() {
		return this.informationNumber;
	}

	public void setInformationNumber(String informationNumber) {
		this.informationNumber = informationNumber;
	}

	public String getLeniencyFlag() {
		return this.leniencyFlag;
	}

	public void setLeniencyFlag(String leniencyFlag) {
		this.leniencyFlag = leniencyFlag;
	}




	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getMonthly() {
		return this.monthly;
	}

	public void setMonthly(BigDecimal monthly) {
		this.monthly = monthly;
	}

	public BigDecimal getOffenderDeductionId() {
		return this.offenderDeductionId;
	}

	public void setOffenderDeductionId(BigDecimal offenderDeductionId) {
		this.offenderDeductionId = offenderDeductionId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public BigDecimal getOriginalOwingAmount() {
		return this.originalOwingAmount;
	}

	public void setOriginalOwingAmount(BigDecimal originalOwingAmount) {
		this.originalOwingAmount = originalOwingAmount;
	}




	public String getPaymentClosedFlag() {
		return this.paymentClosedFlag;
	}

	public void setPaymentClosedFlag(String paymentClosedFlag) {
		this.paymentClosedFlag = paymentClosedFlag;
	}





	public String getRegenerationFlag() {
		return this.regenerationFlag;
	}

	public void setRegenerationFlag(String regenerationFlag) {
		this.regenerationFlag = regenerationFlag;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}




	public BigDecimal getTwiceMonthly1() {
		return this.twiceMonthly1;
	}

	public void setTwiceMonthly1(BigDecimal twiceMonthly1) {
		this.twiceMonthly1 = twiceMonthly1;
	}

	public BigDecimal getTwiceMonthly2() {
		return this.twiceMonthly2;
	}

	public void setTwiceMonthly2(BigDecimal twiceMonthly2) {
		this.twiceMonthly2 = twiceMonthly2;
	}

	public String getWeekly() {
		return this.weekly;
	}

	public void setWeekly(String weekly) {
		this.weekly = weekly;
	}

	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}



		
	}


