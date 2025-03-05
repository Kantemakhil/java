package net.syscon.s4.cf.deductions.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffFeeBills extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("billId")
	private String billId;

	@JsonProperty("offenderFeeId")
	private BigDecimal offenderFeeId;

	@JsonProperty("billDate")
	private Date billDate;

	@JsonProperty("billGenerateDatetime")
	private Date billGenerateDatetime;

	@JsonProperty("billGenerateAmount")
	private BigDecimal billGenerateAmount;

	@JsonProperty("billGenerateStaffId")
	private Integer billGenerateStaffId;

	@JsonProperty("billGenerateUser")
	private String billGenerateUser;

	@JsonProperty("statementGeneratedFlag")
	private String statementGeneratedFlag;

	@JsonProperty("billingStatementId")
	private Integer billingStatementId;

	@JsonProperty("billExpectedStatementDate")
	private Date billExpectedStatementDate;

	@JsonProperty("billExpectedArDueDate")
	private Date billExpectedArDueDate;

	@JsonProperty("billExpectedLdppStartDate")
	private Date billExpectedLdppStartDate;

	@JsonProperty("billExpectedLdppEndDate")
	private Date billExpectedLdppEndDate;

	@JsonProperty("billGenerateStatus")
	private String billGenerateStatus;
	
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
	
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("billOverrideIncreaseAmount")
	private BigDecimal billOverrideIncreaseAmount;
	
	@JsonProperty("billOverrideDecreaseAmount")
	private BigDecimal billOverrideDecreaseAmount;
	
	@JsonProperty("billTxnComment")
	private String billTxnComment;
	@JsonProperty("billTotalAmount")
	private BigDecimal billTotalAmount;
	
	@JsonProperty("billArStartDate")
     private Date billArStartDate;
	
	@JsonProperty("billArDueDate")
	private Date billArDueDate;

	@JsonProperty("backBill")
	private String backBill;
		
	public OffFeeBills() {
		// constructor
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public BigDecimal getOffenderFeeId() {
		return offenderFeeId;
	}

	public void setOffenderFeeId(BigDecimal offenderFeeId) {
		this.offenderFeeId = offenderFeeId;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getBillGenerateDatetime() {
		return billGenerateDatetime;
	}

	public void setBillGenerateDatetime(Date billGenerateDatetime) {
		this.billGenerateDatetime = billGenerateDatetime;
	}

	public BigDecimal getBillGenerateAmount() {
		return billGenerateAmount;
	}

	public void setBillGenerateAmount(BigDecimal billGenerateAmount) {
		this.billGenerateAmount = billGenerateAmount;
	}

	public Integer getBillGenerateStaffId() {
		return billGenerateStaffId;
	}

	public void setBillGenerateStaffId(Integer billGenerateStaffId) {
		this.billGenerateStaffId = billGenerateStaffId;
	}

	public String getBillGenerateUser() {
		return billGenerateUser;
	}

	public void setBillGenerateUser(String billGenerateUser) {
		this.billGenerateUser = billGenerateUser;
	}

	public String getStatementGeneratedFlag() {
		return statementGeneratedFlag;
	}

	public void setStatementGeneratedFlag(String statementGeneratedFlag) {
		this.statementGeneratedFlag = statementGeneratedFlag;
	}

	public Integer getBillingStatementId() {
		return billingStatementId;
	}

	public void setBillingStatementId(Integer billingStatementId) {
		this.billingStatementId = billingStatementId;
	}

	public Date getBillExpectedStatementDate() {
		return billExpectedStatementDate;
	}

	public void setBillExpectedStatementDate(Date billExpectedStatementDate) {
		this.billExpectedStatementDate = billExpectedStatementDate;
	}

	public Date getBillExpectedArDueDate() {
		return billExpectedArDueDate;
	}

	public void setBillExpectedArDueDate(Date billExpectedArDueDate) {
		this.billExpectedArDueDate = billExpectedArDueDate;
	}

	public Date getBillExpectedLdppStartDate() {
		return billExpectedLdppStartDate;
	}

	public void setBillExpectedLdppStartDate(Date billExpectedLdppStartDate) {
		this.billExpectedLdppStartDate = billExpectedLdppStartDate;
	}

	public Date getBillExpectedLdppEndDate() {
		return billExpectedLdppEndDate;
	}

	public void setBillExpectedLdppEndDate(Date billExpectedLdppEndDate) {
		this.billExpectedLdppEndDate = billExpectedLdppEndDate;
	}

	public String getBillGenerateStatus() {
		return billGenerateStatus;
	}

	public void setBillGenerateStatus(String billGenerateStatus) {
		this.billGenerateStatus = billGenerateStatus;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getBillOverrideIncreaseAmount() {
		return billOverrideIncreaseAmount;
	}

	public void setBillOverrideIncreaseAmount(BigDecimal billOverrideIncreaseAmount) {
		this.billOverrideIncreaseAmount = billOverrideIncreaseAmount;
	}

	public BigDecimal getBillOverrideDecreaseAmount() {
		return billOverrideDecreaseAmount;
	}

	public void setBillOverrideDecreaseAmount(BigDecimal billOverrideDecreaseAmount) {
		this.billOverrideDecreaseAmount = billOverrideDecreaseAmount;
	}

	public String getBillTxnComment() {
		return billTxnComment;
	}

	public void setBillTxnComment(String billTxnComment) {
		this.billTxnComment = billTxnComment;
	}

	public BigDecimal getBillTotalAmount() {
		return billTotalAmount;
	}

	public void setBillTotalAmount(BigDecimal billTotalAmount) {
		this.billTotalAmount = billTotalAmount;
	}

	public Date getBillArStartDate() {
		return billArStartDate;
	}

	public void setBillArStartDate(Date billArStartDate) {
		this.billArStartDate = billArStartDate;
	}

	public Date getBillArDueDate() {
		return billArDueDate;
	}

	public void setBillArDueDate(Date billArDueDate) {
		this.billArDueDate = billArDueDate;
	}

	public String getBackBill() {
		return backBill;
	}

	public void setBackBill(String backBill) {
		this.backBill = backBill;
	}
	
}
