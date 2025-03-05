package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

/**
 * The persistent class for the INSTITUTION_MINI_BALANCES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class InstitutionMiniBalances extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("indDays")
	private BigDecimal indDays;
	@JsonProperty("listSeq")
	private BigDecimal listSeq;
	@JsonProperty("minimumAccountBalance")
	private BigDecimal minimumAccountBalance;
	@JsonProperty("modifyDate")
	private Date modifyDate;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("caseloadId")
	private String caseloadId;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("accountCode")
	private BigDecimal accountCode;
	@JsonProperty("nbtAccountCode")
	private String nbtAccountCode;

	public InstitutionMiniBalances() {
		// InstitutionMiniBalances
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(final BigDecimal accountCode) {
		this.accountCode = accountCode;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getIndDays() {
		return this.indDays;
	}

	public void setIndDays(final BigDecimal indDays) {
		this.indDays = indDays;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public BigDecimal getMinimumAccountBalance() {
		return this.minimumAccountBalance;
	}

	public void setMinimumAccountBalance(final BigDecimal minimumAccountBalance) {
		this.minimumAccountBalance = minimumAccountBalance;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(final Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the nbtAccountCode
	 */
	public String getNbtAccountCode() {
		return nbtAccountCode;
	}

	/**
	 * @param nbtAccountCode the nbtAccountCode to set
	 */
	public void setNbtAccountCode(final String nbtAccountCode) {
		this.nbtAccountCode = nbtAccountCode;
	}

}
