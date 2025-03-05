package net.syscon.s4.cf.deductions.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
/**
 * @author sathish
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class FeeAccountProfiles extends BaseModel implements Serializable {


	private static final long serialVersionUID = 1L;
	@JsonProperty("location")
	private String location;
	@JsonProperty("offenderFeeId")
	private BigDecimal offenderFeeId;
	@JsonProperty("odp")
	private BigDecimal odp;
	@JsonProperty("feeCode")
	private String feeCode;
	@JsonProperty("amount")
	private BigDecimal amount;
	@JsonProperty("frequency")
	private String frequency;
	@JsonProperty("code")
	private String code;
	@JsonProperty("dayOfMonth")
	private Integer dayOfMonth;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("feeActStatus")
	private String feeActStatus;
	@JsonProperty("statusEffectiveDate")
	private Date statusEffectiveDate;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	@JsonProperty("nbtFrequency")
	private String nbtFrequency;
	@JsonProperty("nbtCode")
	private String nbtCode;

	@JsonProperty("frequencyCode")
	private String frequencyCode;
	@JsonProperty("frequencyType")
	private String frequencyType;

	@JsonProperty("longestSupvExpDate")
	private Date longestSupvExpDate;

	@JsonProperty("infoNumber")
	private String infoNumber;

	@JsonProperty("serviceDate")
	private Date serviceDate;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("supervisionPeriod")
	private String supervisionPeriod;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("backBill")
	private String backBill;

	@JsonProperty("supvPeriodDate")
	private Date supvPeriodDate;

	@JsonProperty("foAlAllOffenderFlag")
	private String foAlAllOffenderFlag;

	
	@JsonProperty("nonBillableStatus")
	private String nonBillableStatus;
	@JsonProperty("insertUpdateString")
	private String insertUpdateString;

	@JsonProperty("recordDatetime")
	private Date recordDatetime;
	
	@JsonProperty("offenderFeeIdTemp")
	private BigDecimal offenderFeeIdTemp;
	
	
	@JsonProperty("caseloadUpdateAllowFlag")
	private String caseloadUpdateAllowFlag;
	
	@JsonProperty("feeActStatusDesc")
	private String feeActStatusDesc;
		
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("isTriggerEnable")
	private String isTriggerEnable;
	
	@JsonProperty("billAmount")
	private Double billAmount;
	
	@JsonProperty("adjustAmount")
	private Double adjustAmount;
	
	@JsonProperty("paidAmount")
	private Double paidAmount;
	
	@JsonProperty("currentBalance")
	private BigDecimal currentBalance;
	
	public String getFoAlAllOffenderFlag() {
		return foAlAllOffenderFlag;
	}

	public void setFoAlAllOffenderFlag(String foAlAllOffenderFlag) {
		this.foAlAllOffenderFlag = foAlAllOffenderFlag;
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


	public String getLocation() {
		return location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}







	public BigDecimal getOdp() {
		return odp;
	}

	public void setOdp(BigDecimal odp) {
		this.odp = odp;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(final String feeCode) {
		this.feeCode = feeCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}



	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}



	public String getFeeActStatus() {
		return feeActStatus;
	}

	public void setFeeActStatus(String feeActStatus) {
		this.feeActStatus = feeActStatus;
	}

	public Date getStatusEffectiveDate() {
		return statusEffectiveDate;
	}

	public void setStatusEffectiveDate(final Date statusEffectiveDate) {
		this.statusEffectiveDate = statusEffectiveDate;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getLongestSupvExpDate() {
		return longestSupvExpDate;
	}

	public void setLongestSupvExpDate(Date longestSupvExpDate) {
		this.longestSupvExpDate = longestSupvExpDate;
	}

	public String getInfoNumber() {
		return infoNumber;
	}

	public void setInfoNumber(String infoNumber) {
		this.infoNumber = infoNumber;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getSupervisionPeriod() {
		return supervisionPeriod;
	}

	public void setSupervisionPeriod(String supervisionPeriod) {
		this.supervisionPeriod = supervisionPeriod;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getBackBill() {
		return backBill;
	}

	public void setBackBill(String backBill) {
		this.backBill = backBill;
	}

	public Date getSupvPeriodDate() {
		return supvPeriodDate;
	}

	public void setSupvPeriodDate(Date supvPeriodDate) {
		this.supvPeriodDate = supvPeriodDate;
	}

	public String getNbtFrequency() {
		return nbtFrequency;
	}

	public void setNbtFrequency(String nbtFrequency) {
		this.nbtFrequency = nbtFrequency;
	}

	public String getNbtCode() {
		return nbtCode;
	}

	public void setNbtCode(String nbtCode) {
		this.nbtCode = nbtCode;
	}

	public String getFrequencyCode() {
		return frequencyCode;
	}

	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
	}

	public String getFreequencyType() {
		return frequencyType;
	}

	public void setFreequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}

	public BigDecimal getOffenderFeeId() {
		return offenderFeeId;
	}

	public void setOffenderFeeId(BigDecimal offenderFeeId) {
		this.offenderFeeId = offenderFeeId;
	}

	public String getFrequencyType() {
		return frequencyType;
	}

	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getNonBillableStatus() {
		return nonBillableStatus;
	}

	public void setNonBillableStatus(String nonBillableStatus) {
		this.nonBillableStatus = nonBillableStatus;
	}

	public String getInsertUpdateString() {
		return insertUpdateString;
	}

	public void setInsertUpdateString(String insertUpdateString) {
		this.insertUpdateString = insertUpdateString;
	}

	public BigDecimal getOffenderFeeIdTemp() {
		return offenderFeeIdTemp;
	}

	public void setOffenderFeeIdTemp(BigDecimal offenderFeeIdTemp) {
		this.offenderFeeIdTemp = offenderFeeIdTemp;
	}

	public String getCaseloadUpdateAllowFlag() {
		return caseloadUpdateAllowFlag;
	}

	public void setCaseloadUpdateAllowFlag(String caseloadUpdateAllowFlag) {
		this.caseloadUpdateAllowFlag = caseloadUpdateAllowFlag;
	}

	public Date getRecordDatetime() {
		return recordDatetime;
	}

	public void setRecordDatetime(Date recordDatetime) {
		this.recordDatetime = recordDatetime;
	}

	public String getFeeActStatusDesc() {
		return feeActStatusDesc;
	}

	public void setFeeActStatusDesc(String feeActStatusDesc) {
		this.feeActStatusDesc = feeActStatusDesc;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsTriggerEnable() {
		return isTriggerEnable;
	}

	public void setIsTriggerEnable(String isTriggerEnable) {
		this.isTriggerEnable = isTriggerEnable;
	}

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public Double getAdjustAmount() {
		return adjustAmount;
	}

	public void setAdjustAmount(Double adjustAmount) {
		this.adjustAmount = adjustAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	
}
