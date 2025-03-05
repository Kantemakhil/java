package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOffenderSubBalanceBean  extends BaseModel implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("pCsldId")
	private String pCsldId;
	@JsonProperty("pOffId")
	private Long pOffId;
	@JsonProperty("pSubActType")
	private String pSubActType;
	@JsonProperty("pAmount")
	private BigDecimal pAmount;
	@JsonProperty("pMinbal")
	private BigDecimal pMinbal;
	@JsonProperty("pInddays")
	private Integer pInddays;
	@JsonProperty("pInddate")
	private Date pInddate;
	@JsonProperty("pTrstcode")
	private Integer pTrstcode;
	@JsonProperty("pLockFlag")
	private String pLockFlag;
	@JsonProperty("txntype")
	private String txntype;
	@JsonProperty("modName")
	private String modName;
	@JsonProperty("pSetupCsldId")
	private String pSetupCsldId;

	public GetOffenderSubBalanceBean() {
//		GetOffenderSubBalanceBean
	}

	/**
	 * @return the pCsldId
	 */
	public String getpCsldId() {
		return pCsldId;
	}

	/**
	 * @param pCsldId the pCsldId to set
	 */
	public void setpCsldId(final String pCsldId) {
		this.pCsldId = pCsldId;
	}

	/**
	 * @return the pOffId
	 */
	public Long getpOffId() {
		return pOffId;
	}

	/**
	 * @param pOffId the pOffId to set
	 */
	public void setpOffId(Long pOffId) {
		this.pOffId = pOffId;
	}

	/**
	 * @return the pSubActType
	 */
	public String getpSubActType() {
		return pSubActType;
	}

	/**
	 * @param pSubActType the pSubActType to set
	 */
	public void setpSubActType(final String pSubActType) {
		this.pSubActType = pSubActType;
	}

	/**
	 * @return the pAmount
	 */
	public BigDecimal getpAmount() {
		return pAmount;
	}

	/**
	 * @param pAmount the pAmount to set
	 */
	public void setpAmount(final BigDecimal pAmount) {
		this.pAmount = pAmount;
	}

	/**
	 * @return the pMinbal
	 */
	public BigDecimal getpMinbal() {
		return pMinbal;
	}

	/**
	 * @param pMinbal the pMinbal to set
	 */
	public void setpMinbal(final BigDecimal pMinbal) {
		this.pMinbal = pMinbal;
	}

	/**
	 * @return the pInddays
	 */
	public Integer getpInddays() {
		return pInddays;
	}

	/**
	 * @param pInddays the pInddays to set
	 */
	public void setpInddays(final Integer pInddays) {
		this.pInddays = pInddays;
	}

	/**
	 * @return the pInddate
	 */
	public Date getpInddate() {
		return pInddate;
	}

	/**
	 * @param pInddate the pInddate to set
	 */
	public void setpInddate(final Date pInddate) {
		this.pInddate = pInddate;
	}

	/**
	 * @return the pTrstcode
	 */
	public Integer getpTrstcode() {
		return pTrstcode;
	}

	/**
	 * @param pTrstcode the pTrstcode to set
	 */
	public void setpTrstcode(final Integer pTrstcode) {
		this.pTrstcode = pTrstcode;
	}

	/**
	 * @return the pLockFlag
	 */
	public String getpLockFlag() {
		return pLockFlag;
	}

	/**
	 * @param pLockFlag the pLockFlag to set
	 */
	public void setpLockFlag(final String pLockFlag) {
		this.pLockFlag = pLockFlag;
	}

	/**
	 * @return the txntype
	 */
	public String getTxntype() {
		return txntype;
	}

	/**
	 * @param txntype the txntype to set
	 */
	public void setTxntype(final String txntype) {
		this.txntype = txntype;
	}

	/**
	 * @return the modName
	 */
	public String getModName() {
		return modName;
	}

	/**
	 * @param modName the modName to set
	 */
	public void setModName(final String modName) {
		this.modName = modName;
	}

	/**
	 * @return the pSetupCsldId
	 */
	public String getpSetupCsldId() {
		return pSetupCsldId;
	}

	/**
	 * @param pSetupCsldId the pSetupCsldId to set
	 */
	public void setpSetupCsldId(final String pSetupCsldId) {
		this.pSetupCsldId = pSetupCsldId;
	}
	
	
	
	

}
