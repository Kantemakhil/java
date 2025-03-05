package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

/**
 * The persistent class for the CORPORATE_TYPES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SubAccountTransactionsBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("fSubAcctNameOne")
	private String fSubAcctNameOne;
	@JsonProperty("fBookLabel")
	private String fBookLabel;
	@JsonProperty("fTxnEnt")
	private Date fTxnEnt;
	@JsonProperty("fBookNumberOne")
	private String fBookNumberOne;
	@JsonProperty("fTxnType")
	private String fTxnType;
	@JsonProperty("fTxnEntryDesc")
	private String fTxnEntryDesc;
	@JsonProperty("fTxnEntryAmount")
	private BigDecimal fTxnEntryAmount;
	@JsonProperty("fBalance")
	private BigDecimal fBalance;
	@JsonProperty("fType")
	private String fType;

	public SubAccountTransactionsBean() {
		// SubAccountTransactionsBean
	}

	/**
	 * @return the fType
	 */
	public String getfType() {
		return fType;
	}

	/**
	 * @param fType
	 *            the fType to set
	 */
	public void setfType(final String fType) {
		this.fType = fType;
	}

	/**
	 * @return the fSubAcctNameOne
	 */
	public String getfSubAcctNameOne() {
		return fSubAcctNameOne;
	}

	/**
	 * @param fSubAcctNameOne
	 *            the fSubAcctNameOne to set
	 */
	public void setfSubAcctNameOne(final String fSubAcctNameOne) {
		this.fSubAcctNameOne = fSubAcctNameOne;
	}

	/**
	 * @return the fBookLabel
	 */
	public String getfBookLabel() {
		return fBookLabel;
	}

	/**
	 * @param fBookLabel
	 *            the fBookLabel to set
	 */
	public void setfBookLabel(final String fBookLabel) {
		this.fBookLabel = fBookLabel;
	}

	/**
	 * @return the fTxnEnt
	 */
	public Date getfTxnEnt() {
		return fTxnEnt;
	}

	/**
	 * @param fTxnEnt
	 *            the fTxnEnt to set
	 */
	public void setfTxnEnt(final Date fTxnEnt) {
		this.fTxnEnt = fTxnEnt;
	}

	/**
	 * @return the fBookNumberOne
	 */
	public String getfBookNumberOne() {
		return fBookNumberOne;
	}

	/**
	 * @param fBookNumberOne
	 *            the fBookNumberOne to set
	 */
	public void setfBookNumberOne(final String fBookNumberOne) {
		this.fBookNumberOne = fBookNumberOne;
	}

	/**
	 * @return the fTxnType
	 */
	public String getfTxnType() {
		return fTxnType;
	}

	/**
	 * @param fTxnType
	 *            the fTxnType to set
	 */
	public void setfTxnType(final String fTxnType) {
		this.fTxnType = fTxnType;
	}

	/**
	 * @return the fTxnEntryDesc
	 */
	public String getfTxnEntryDesc() {
		return fTxnEntryDesc;
	}

	/**
	 * @param fTxnEntryDesc
	 *            the fTxnEntryDesc to set
	 */
	public void setfTxnEntryDesc(final String fTxnEntryDesc) {
		this.fTxnEntryDesc = fTxnEntryDesc;
	}

	/**
	 * @return the fTxnEntryAmount
	 */
	public BigDecimal getfTxnEntryAmount() {
		return fTxnEntryAmount;
	}

	/**
	 * @param fTxnEntryAmount
	 *            the fTxnEntryAmount to set
	 */
	public void setfTxnEntryAmount(final BigDecimal fTxnEntryAmount) {
		this.fTxnEntryAmount = fTxnEntryAmount;
	}

	/**
	 * @return the fBalance
	 */
	public BigDecimal getfBalance() {
		return fBalance;
	}

	/**
	 * @param fBalance
	 *            the fBalance to set
	 */
	public void setfBalance(final BigDecimal fBalance) {
		this.fBalance = fBalance;
	}

}