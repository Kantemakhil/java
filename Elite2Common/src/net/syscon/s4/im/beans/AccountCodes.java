package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class AccountCodes
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountCodes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("accountCode")
	private Integer accountCode;

	@JsonProperty("parentAccountCode")
	private Integer parentAccountCode;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("accountType")
	private String accountType;

	@JsonProperty("allCaseloadFlag")
	private String allCaseloadFlag;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDate")
	private Date modifyDate;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("postingStatusFlag")
	private String postingStatusFlag;

	@JsonProperty("recAccountCode")
	private BigDecimal recAccountCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("subAccountType")
	private String subAccountType;

	@JsonProperty("txnPostingType")
	private String txnPostingType;

	@JsonProperty("code")
	private String code;

	@JsonProperty("description")
	private String description;

	@JsonProperty("drAccountCode")
	private Integer drAccountCode;
	@JsonProperty("aTxnPostingType")
	private String aTxnPostingType;
	@JsonProperty("bPostingStatusFlag")
	private String bPostingStatusFlag;
	@JsonProperty("IntegercrAccountCode")
	private Integer crAccountCode;
	@JsonProperty("bTxnPostingType")
	private String bTxnPostingType;
	@JsonProperty("cPostingStatusFlag")
	private String cPostingStatusFlag;
	@JsonProperty("bankDrAccountCode")
	private Integer bankDrAccountCode;
	@JsonProperty("cTxnPostingType")
	private String cTxnPostingType;
	@JsonProperty("dPostingStatusFlag")
	private String dPostingStatusFlag;
	@JsonProperty("bankCrAccountCode")
	private Integer bankCrAccountCode;
	@JsonProperty("dTxnPostingType")
	private String dTxnPostingType;
	@JsonProperty("ePostingStatusFlag")
	private String ePostingStatusFlag;
	@JsonProperty("txnOperationSeq")
	private Integer txnOperationSeq;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("lvlastClosedPeriod")
	private Integer lvlastClosedPeriod;

	@JsonProperty("lvallowedreopenPeriod")
	private Integer lvallowedreopenPeriod;

	@JsonProperty("lvEnteraccountPeriodId")
	private Integer lvEnteraccountPeriodId;

	@JsonProperty("txnEntryDate")
	private Date txnEntryDate;

	@JsonProperty("accountPeriodStatus")
	private String accountPeriodStatus;
	
	@JsonProperty("childAccountCodes")
	private List<AccountCodes> childAccountCodes;
	
	@JsonProperty("report")
	private byte[] report;
	
	@JsonProperty("superParentAccountCode")
	Integer superParentAccountCode;
	
	@JsonProperty("amount")
	private BigDecimal amount;
	
	@JsonProperty("fReportHeaderLabelName")
	private String fReportHeaderLabelName;
	
	@JsonProperty("fCaseloadNameOne")
	private String fCaseloadNameOne;
	
	
	@JsonProperty("accountPeriodId")
	private Long accountPeriodId;
	
	@JsonProperty("currentBalance")
	private BigDecimal currentBalance;
	
	@JsonProperty("caseloadCurrentAccountId")
	private Long caseloadCurrentAccountId;
	
	@JsonProperty("closingUserId")
	private String closingUserId ;
	
	@JsonProperty("closingDate")
	private Date closingDate ;
	
	
	@JsonProperty("openBalance")
	private BigDecimal openBalance;
	
	@JsonProperty("closeBalance")
	private BigDecimal closeBalance;
	
	@JsonProperty("periodTxnAmount")
	private BigDecimal periodTxnAmount;
	
	@JsonProperty("txnId")
	private Integer txnId;
	
	@JsonProperty("glEntrySeq")
	private Integer glEntrySeq;
	
	@JsonProperty("txnEntrySeq")
	private Integer txnEntrySeq;
	
	

	/**
	 * @return the parentAccountCode
	 */
	public Integer getParentAccountCode() {
		return parentAccountCode;
	}

	/**
	 * @param parentAccountCode
	 *            the parentAccountCode to set
	 */
	public void setParentAccountCode(final Integer parentAccountCode) {
		this.parentAccountCode = parentAccountCode;
	}

	/**
	 * @return the accountCode
	 */
	public Integer getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode
	 *            the accountCode to set
	 */
	public void setAccountCode(final Integer accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName
	 *            the accountName to set
	 */
	public void setAccountName(final String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(final String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the allCaseloadFlag
	 */
	public String getAllCaseloadFlag() {
		return allCaseloadFlag;
	}

	/**
	 * @param allCaseloadFlag
	 *            the allCaseloadFlag to set
	 */
	public void setAllCaseloadFlag(final String allCaseloadFlag) {
		this.allCaseloadFlag = allCaseloadFlag;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType
	 *            the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
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
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
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
	 * @return the postingStatusFlag
	 */
	public String getPostingStatusFlag() {
		return postingStatusFlag;
	}

	/**
	 * @param postingStatusFlag
	 *            the postingStatusFlag to set
	 */
	public void setPostingStatusFlag(final String postingStatusFlag) {
		this.postingStatusFlag = postingStatusFlag;
	}

	/**
	 * @return the recAccountCode
	 */
	public BigDecimal getRecAccountCode() {
		return recAccountCode;
	}

	/**
	 * @param recAccountCode
	 *            the recAccountCode to set
	 */
	public void setRecAccountCode(final BigDecimal recAccountCode) {
		this.recAccountCode = recAccountCode;
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
	 * @return the subAccountType
	 */
	public String getSubAccountType() {
		return subAccountType;
	}

	/**
	 * @param subAccountType
	 *            the subAccountType to set
	 */
	public void setSubAccountType(final String subAccountType) {
		this.subAccountType = subAccountType;
	}

	/**
	 * @return the txnPostingType
	 */
	public String getTxnPostingType() {
		return txnPostingType;
	}

	/**
	 * @param txnPostingType
	 *            the txnPostingType to set
	 */
	public void setTxnPostingType(final String txnPostingType) {
		this.txnPostingType = txnPostingType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the drAccountCode
	 */
	public Integer getDrAccountCode() {
		return drAccountCode;
	}

	/**
	 * @param drAccountCode
	 *            the drAccountCode to set
	 */
	public void setDrAccountCode(final Integer drAccountCode) {
		this.drAccountCode = drAccountCode;
	}

	/**
	 * @return the aTxnPostingType
	 */
	public String getaTxnPostingType() {
		return aTxnPostingType;
	}

	/**
	 * @param aTxnPostingType
	 *            the aTxnPostingType to set
	 */
	public void setaTxnPostingType(final String aTxnPostingType) {
		this.aTxnPostingType = aTxnPostingType;
	}

	/**
	 * @return the bPostingStatusFlag
	 */
	public String getbPostingStatusFlag() {
		return bPostingStatusFlag;
	}

	/**
	 * @param bPostingStatusFlag
	 *            the bPostingStatusFlag to set
	 */
	public void setbPostingStatusFlag(final String bPostingStatusFlag) {
		this.bPostingStatusFlag = bPostingStatusFlag;
	}

	/**
	 * @return the crAccountCode
	 */
	public Integer getCrAccountCode() {
		return crAccountCode;
	}

	/**
	 * @param crAccountCode
	 *            the crAccountCode to set
	 */
	public void setCrAccountCode(final Integer crAccountCode) {
		this.crAccountCode = crAccountCode;
	}

	/**
	 * @return the bTxnPostingType
	 */
	public String getbTxnPostingType() {
		return bTxnPostingType;
	}

	/**
	 * @param bTxnPostingType
	 *            the bTxnPostingType to set
	 */
	public void setbTxnPostingType(final String bTxnPostingType) {
		this.bTxnPostingType = bTxnPostingType;
	}

	/**
	 * @return the cPostingStatusFlag
	 */
	public String getcPostingStatusFlag() {
		return cPostingStatusFlag;
	}

	/**
	 * @param cPostingStatusFlag
	 *            the cPostingStatusFlag to set
	 */
	public void setcPostingStatusFlag(final String cPostingStatusFlag) {
		this.cPostingStatusFlag = cPostingStatusFlag;
	}

	/**
	 * @return the bankDrAccountCode
	 */
	public Integer getBankDrAccountCode() {
		return bankDrAccountCode;
	}

	/**
	 * @param bankDrAccountCode
	 *            the bankDrAccountCode to set
	 */
	public void setBankDrAccountCode(final Integer bankDrAccountCode) {
		this.bankDrAccountCode = bankDrAccountCode;
	}

	/**
	 * @return the cTxnPostingType
	 */
	public String getcTxnPostingType() {
		return cTxnPostingType;
	}

	/**
	 * @param cTxnPostingType
	 *            the cTxnPostingType to set
	 */
	public void setcTxnPostingType(final String cTxnPostingType) {
		this.cTxnPostingType = cTxnPostingType;
	}

	/**
	 * @return the dPostingStatusFlag
	 */
	public String getdPostingStatusFlag() {
		return dPostingStatusFlag;
	}

	/**
	 * @param dPostingStatusFlag
	 *            the dPostingStatusFlag to set
	 */
	public void setdPostingStatusFlag(final String dPostingStatusFlag) {
		this.dPostingStatusFlag = dPostingStatusFlag;
	}

	/**
	 * @return the bankCrAccountCode
	 */
	public Integer getBankCrAccountCode() {
		return bankCrAccountCode;
	}

	/**
	 * @param bankCrAccountCode
	 *            the bankCrAccountCode to set
	 */
	public void setBankCrAccountCode(final Integer bankCrAccountCode) {
		this.bankCrAccountCode = bankCrAccountCode;
	}

	/**
	 * @return the dTxnPostingType
	 */
	public String getdTxnPostingType() {
		return dTxnPostingType;
	}

	/**
	 * @param dTxnPostingType
	 *            the dTxnPostingType to set
	 */
	public void setdTxnPostingType(final String dTxnPostingType) {
		this.dTxnPostingType = dTxnPostingType;
	}

	/**
	 * @return the ePostingStatusFlag
	 */
	public String getePostingStatusFlag() {
		return ePostingStatusFlag;
	}

	/**
	 * @param ePostingStatusFlag
	 *            the ePostingStatusFlag to set
	 */
	public void setePostingStatusFlag(final String ePostingStatusFlag) {
		this.ePostingStatusFlag = ePostingStatusFlag;
	}

	/**
	 * @return the txnOperationSeq
	 */
	public Integer getTxnOperationSeq() {
		return txnOperationSeq;
	}

	/**
	 * @param txnOperationSeq
	 *            the txnOperationSeq to set
	 */
	public void setTxnOperationSeq(final Integer txnOperationSeq) {
		this.txnOperationSeq = txnOperationSeq;
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
	 * @return the lvlastClosedPeriod
	 */
	public Integer getLvlastClosedPeriod() {
		return lvlastClosedPeriod;
	}

	/**
	 * @param lvlastClosedPeriod
	 *            the lvlastClosedPeriod to set
	 */
	public void setLvlastClosedPeriod(final Integer lvlastClosedPeriod) {
		this.lvlastClosedPeriod = lvlastClosedPeriod;
	}

	/**
	 * @return the lvallowedreopenPeriod
	 */
	public Integer getLvallowedreopenPeriod() {
		return lvallowedreopenPeriod;
	}

	/**
	 * @param lvallowedreopenPeriod
	 *            the lvallowedreopenPeriod to set
	 */
	public void setLvallowedreopenPeriod(final Integer lvallowedreopenPeriod) {
		this.lvallowedreopenPeriod = lvallowedreopenPeriod;
	}

	/**
	 * @return the lvEnteraccountPeriodId
	 */
	public Integer getLvEnteraccountPeriodId() {
		return lvEnteraccountPeriodId;
	}

	/**
	 * @param lvEnteraccountPeriodId
	 *            the lvEnteraccountPeriodId to set
	 */
	public void setLvEnteraccountPeriodId(final Integer lvEnteraccountPeriodId) {
		this.lvEnteraccountPeriodId = lvEnteraccountPeriodId;
	}

	/**
	 * @return the txnEntryDate
	 */
	public Date getTxnEntryDate() {
		return txnEntryDate;
	}

	/**
	 * @param txnEntryDate
	 *            the txnEntryDate to set
	 */
	public void setTxnEntryDate(final Date txnEntryDate) {
		this.txnEntryDate = txnEntryDate;
	}

	/**
	 * @return the accountPeriodStatus
	 */
	public String getAccountPeriodStatus() {
		return accountPeriodStatus;
	}

	/**
	 * @param accountPeriodStatus
	 *            the accountPeriodStatus to set
	 */
	public void setAccountPeriodStatus(final String accountPeriodStatus) {
		this.accountPeriodStatus = accountPeriodStatus;
	}

	/**
	 * @return the childAccountCodes
	 */
	public List<AccountCodes> getChildAccountCodes() {
		return childAccountCodes;
	}

	/**
	 * @param childAccountCodes the childAccountCodes to set
	 */
	public void setChildAccountCodes(final List<AccountCodes> childAccountCodes) {
		this.childAccountCodes = childAccountCodes;
	}

	/**
	 * @return the report
	 */
	public byte[] getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(final byte[] report) {
		this.report = report;
	}

	/**
	 * @return the superParentAccountCode
	 */
	public Integer getSuperParentAccountCode() {
		return superParentAccountCode;
	}

	/**
	 * @param superParentAccountCode the superParentAccountCode to set
	 */
	public void setSuperParentAccountCode(final Integer superParentAccountCode) {
		this.superParentAccountCode = superParentAccountCode;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the fReportHeaderLabelName
	 */
	public String getfReportHeaderLabelName() {
		return fReportHeaderLabelName;
	}

	/**
	 * @param fReportHeaderLabelName the fReportHeaderLabelName to set
	 */
	public void setfReportHeaderLabelName(final String fReportHeaderLabelName) {
		this.fReportHeaderLabelName = fReportHeaderLabelName;
	}

	/**
	 * @return the fCaseloadNameOne
	 */
	public String getfCaseloadNameOne() {
		return fCaseloadNameOne;
	}

	/**
	 * @param fCaseloadNameOne the fCaseloadNameOne to set
	 */
	public void setfCaseloadNameOne(final String fCaseloadNameOne) {
		this.fCaseloadNameOne = fCaseloadNameOne;
	}

	/**
	 * @return the accountPeriodId
	 */
	public Long getAccountPeriodId() {
		return accountPeriodId;
	}

	/**
	 * @return the currentBalance
	 */
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	/**
	 * @param accountPeriodId the accountPeriodId to set
	 */
	public void setAccountPeriodId(Long accountPeriodId) {
		this.accountPeriodId = accountPeriodId;
	}

	/**
	 * @param currentBalance the currentBalance to set
	 */
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	/**
	 * @return the caseloadCurrentAccountId
	 */
	public Long getCaseloadCurrentAccountId() {
		return caseloadCurrentAccountId;
	}

	/**
	 * @param caseloadCurrentAccountId the caseloadCurrentAccountId to set
	 */
	public void setCaseloadCurrentAccountId(Long caseloadCurrentAccountId) {
		this.caseloadCurrentAccountId = caseloadCurrentAccountId;
	}

	/**
	 * @return the closingUserId
	 */
	public String getClosingUserId() {
		return closingUserId;
	}

	/**
	 * @return the closingDate
	 */
	public Date getClosingDate() {
		return closingDate;
	}

	/**
	 * @param closingUserId the closingUserId to set
	 */
	public void setClosingUserId(String closingUserId) {
		this.closingUserId = closingUserId;
	}

	/**
	 * @param closingDate the closingDate to set
	 */
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	/**
	 * @return the openBalance
	 */
	public BigDecimal getOpenBalance() {
		return openBalance;
	}

	/**
	 * @return the closeBalance
	 */
	public BigDecimal getCloseBalance() {
		return closeBalance;
	}

	/**
	 * @return the periodTxnAmount
	 */
	public BigDecimal getPeriodTxnAmount() {
		return periodTxnAmount;
	}

	/**
	 * @param openBalance the openBalance to set
	 */
	public void setOpenBalance(BigDecimal openBalance) {
		this.openBalance = openBalance;
	}

	/**
	 * @param closeBalance the closeBalance to set
	 */
	public void setCloseBalance(BigDecimal closeBalance) {
		this.closeBalance = closeBalance;
	}

	/**
	 * @param periodTxnAmount the periodTxnAmount to set
	 */
	public void setPeriodTxnAmount(BigDecimal periodTxnAmount) {
		this.periodTxnAmount = periodTxnAmount;
	}

	/**
	 * @return the txnId
	 */
	public Integer getTxnId() {
		return txnId;
	}

	/**
	 * @return the glEntrySeq
	 */
	public Integer getGlEntrySeq() {
		return glEntrySeq;
	}

	/**
	 * @return the txnEntrySeq
	 */
	public Integer getTxnEntrySeq() {
		return txnEntrySeq;
	}

	/**
	 * @param txnId the txnId to set
	 */
	public void setTxnId(Integer txnId) {
		this.txnId = txnId;
	}

	/**
	 * @param glEntrySeq the glEntrySeq to set
	 */
	public void setGlEntrySeq(Integer glEntrySeq) {
		this.glEntrySeq = glEntrySeq;
	}

	/**
	 * @param txnEntrySeq the txnEntrySeq to set
	 */
	public void setTxnEntrySeq(Integer txnEntrySeq) {
		this.txnEntrySeq = txnEntrySeq;
	}

}