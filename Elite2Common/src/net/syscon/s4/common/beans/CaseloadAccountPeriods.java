package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the CASELOAD_ACCOUNT_PERIODS database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseloadAccountPeriods  extends BaseModel  implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountPeriodStatus")
	private String accountPeriodStatus;
	@JsonProperty("closingDate")
	private Date closingDate;
	@JsonProperty("closingUserId")
	private String closingUserId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("reopenTxnId")
	private BigDecimal reopenTxnId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("rowNum")
	private BigDecimal rowNum;
	@JsonProperty("toRowNum")
	private BigDecimal toRowNum;
	@JsonProperty("fromRowNum")
	private BigDecimal fromRowNum;

	private BigDecimal accountPeriodId;

	public CaseloadAccountPeriods() {
//		CaseloadAccountPeriods
	}

	/**
	 * @return the accountPeriodStatus
	 */
	public String getAccountPeriodStatus() {
		return accountPeriodStatus;
	}

	/**
	 * @param accountPeriodStatus the accountPeriodStatus to set
	 */
	public void setAccountPeriodStatus(final String accountPeriodStatus) {
		this.accountPeriodStatus = accountPeriodStatus;
	}

	/**
	 * @return the closingDate
	 */
	public Date getClosingDate() {
		return closingDate;
	}

	/**
	 * @param closingDate the closingDate to set
	 */
	public void setClosingDate(final Date closingDate) {
		this.closingDate = closingDate;
	}

	/**
	 * @return the closingUserId
	 */
	public String getClosingUserId() {
		return closingUserId;
	}

	/**
	 * @param closingUserId the closingUserId to set
	 */
	public void setClosingUserId(final String closingUserId) {
		this.closingUserId = closingUserId;
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
	 * @return the reopenTxnId
	 */
	public BigDecimal getReopenTxnId() {
		return reopenTxnId;
	}

	/**
	 * @param reopenTxnId the reopenTxnId to set
	 */
	public void setReopenTxnId(final BigDecimal reopenTxnId) {
		this.reopenTxnId = reopenTxnId;
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
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the accountPeriodId
	 */
	public BigDecimal getAccountPeriodId() {
		return accountPeriodId;
	}

	/**
	 * @param accountPeriodId the accountPeriodId to set
	 */
	public void setAccountPeriodId(final BigDecimal accountPeriodId) {
		this.accountPeriodId = accountPeriodId;
	}

	/**
	 * @return the rowNum
	 */
	public BigDecimal getRowNum() {
		return rowNum;
	}

	/**
	 * @param rowNum the rowNum to set
	 */
	public void setRowNum(final BigDecimal rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * @return the toRowNum
	 */
	public BigDecimal getToRowNum() {
		return toRowNum;
	}

	/**
	 * @param toRowNum the toRowNum to set
	 */
	public void setToRowNum(final BigDecimal toRowNum) {
		this.toRowNum = toRowNum;
	}

	/**
	 * @return the fromRowNum
	 */
	public BigDecimal getFromRowNum() {
		return fromRowNum;
	}

	/**
	 * @param fromRowNum the fromRowNum to set
	 */
	public void setFromRowNum(final BigDecimal fromRowNum) {
		this.fromRowNum = fromRowNum;
	}

	
	
}
