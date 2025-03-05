package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the MINIMUM_PAYABLE_BALANCES database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MinimumPayableBalances extends BaseModel  implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountCode")
	private BigDecimal accountCode;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("minPayAmount")
	private BigDecimal minPayAmount;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty( "sealFlag") 
	private String sealFlag;
	@JsonProperty( "dspAccountName") 
	private String dspAccountName;
	@JsonProperty( "caseloadType") 
	private String caseloadType;
	public MinimumPayableBalances() {
//		MinimumPayableBalance
	}

	/**
	 * @return the accountCode
	 */
	public BigDecimal getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(final BigDecimal accountCode) {
		this.accountCode = accountCode;
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
	 * @return the minPayAmount
	 */
	public BigDecimal getMinPayAmount() {
		return minPayAmount;
	}

	/**
	 * @param minPayAmount the minPayAmount to set
	 */
	public void setMinPayAmount(final BigDecimal minPayAmount) {
		this.minPayAmount = minPayAmount;
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
	 * @return the dspAccountName
	 */
	public String getDspAccountName() {
		return dspAccountName;
	}

	/**
	 * @param dspAccountName the dspAccountName to set
	 */
	public void setDspAccountName(final String dspAccountName) {
		this.dspAccountName = dspAccountName;
	}

	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	
}
