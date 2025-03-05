package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ChkFreezeDisbursements extends BaseModel implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("pCsldId")
	private String pCsldId;
	@JsonProperty("pOffId")
	private Long pOffId;
	@JsonProperty("pTxnType")
	private String pTxnType;
	@JsonProperty("pAcntCode")
	private Long pAcntCode;
	@JsonProperty("pCsldType")
	private String pCsldType;
	@JsonProperty("pModuleName")
	private String pModuleName;
	@JsonProperty("pDate")
	private Date pDate;
	@JsonProperty("frzFlag")
	private String frzFlag;
	
	public ChkFreezeDisbursements() {
//		ChkFreezeDisbursements
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
	public void setpOffId(final Long pOffId) {
		this.pOffId = pOffId;
	}

	/**
	 * @return the pTxnType
	 */
	public String getpTxnType() {
		return pTxnType;
	}

	/**
	 * @param pTxnType the pTxnType to set
	 */
	public void setpTxnType(final String pTxnType) {
		this.pTxnType = pTxnType;
	}

	/**
	 * @return the pAcntCode
	 */
	public Long getpAcntCode() {
		return pAcntCode;
	}

	/**
	 * @param pAcntCode the pAcntCode to set
	 */
	public void setpAcntCode(final Long pAcntCode) {
		this.pAcntCode = pAcntCode;
	}

	/**
	 * @return the pCsldType
	 */
	public String getpCsldType() {
		return pCsldType;
	}

	/**
	 * @param pCsldType the pCsldType to set
	 */
	public void setpCsldType(final String pCsldType) {
		this.pCsldType = pCsldType;
	}

	/**
	 * @return the pModuleName
	 */
	public String getpModuleName() {
		return pModuleName;
	}

	/**
	 * @param pModuleName the pModuleName to set
	 */
	public void setpModuleName(final String pModuleName) {
		this.pModuleName = pModuleName;
	}

	/**
	 * @return the pDate
	 */
	public Date getpDate() {
		return pDate;
	}

	/**
	 * @param pDate the pDate to set
	 */
	public void setpDate(final Date pDate) {
		this.pDate = pDate;
	}

	/**
	 * @return the frzFlag
	 */
	public String getFrzFlag() {
		return frzFlag;
	}

	/**
	 * @param frzFlag the frzFlag to set
	 */
	public void setFrzFlag(final String frzFlag) {
		this.frzFlag = frzFlag;
	}
	
}
