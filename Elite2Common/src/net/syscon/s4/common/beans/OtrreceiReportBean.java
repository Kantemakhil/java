package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtrreceiReportBean   extends BaseModel implements Serializable    {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("client")
	private String client;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("clDesc")
	private String clDesc;
	@JsonProperty("txnDate")
	private Date txnDate;
	@JsonProperty("recNum")
	private String recNum;
	@JsonProperty("remitter")
	private String remitter;
	@JsonProperty("amt")
	private BigDecimal amt;
	@JsonProperty("amtText")
	private String amtText;
	@JsonProperty("offName")
	private String offName;
	@JsonProperty("offId")
	private String offId;
	@JsonProperty("accountCode")
	private String accountCode;
	@JsonProperty("accountName")
	private String accountName;
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("txnId")
	private Integer txnId;
	

	
	public OtrreceiReportBean() {
//		OtrreceiReportBean
	}

	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(final String client) {
		this.client = client;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * @return the clDesc
	 */
	public String getClDesc() {
		return clDesc;
	}

	/**
	 * @param clDesc the clDesc to set
	 */
	public void setClDesc(final String clDesc) {
		this.clDesc = clDesc;
	}

	/**
	 * @return the txnDate
	 */
	public Date getTxnDate() {
		return txnDate;
	}

	/**
	 * @param txnDate the txnDate to set
	 */
	public void setTxnDate(final Date txnDate) {
		this.txnDate = txnDate;
	}

	/**
	 * @return the recNum
	 */
	public String getRecNum() {
		return recNum;
	}

	/**
	 * @param recNum the recNum to set
	 */
	public void setRecNum(final String recNum) {
		this.recNum = recNum;
	}

	/**
	 * @return the remitter
	 */
	public String getRemitter() {
		return remitter;
	}

	/**
	 * @param remitter the remitter to set
	 */
	public void setRemitter(final String remitter) {
		this.remitter = remitter;
	}

	/**
	 * @return the amt
	 */
	public BigDecimal getAmt() {
		return amt;
	}

	/**
	 * @param amt the amt to set
	 */
	public void setAmt(final BigDecimal amt) {
		this.amt = amt;
	}

	/**
	 * @return the amtText
	 */
	public String getAmtText() {
		return amtText;
	}

	/**
	 * @param amtText the amtText to set
	 */
	public void setAmtText(final String amtText) {
		this.amtText = amtText;
	}

	/**
	 * @return the offName
	 */
	public String getOffName() {
		return offName;
	}

	/**
	 * @param offName the offName to set
	 */
	public void setOffName(final String offName) {
		this.offName = offName;
	}

	/**
	 * @return the offId
	 */
	public String getOffId() {
		return offId;
	}

	/**
	 * @param offId the offId to set
	 */
	public void setOffId(final String offId) {
		this.offId = offId;
	}

	/**
	 * @return the accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(final String accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(final String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
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
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the txnId
	 */
	public Integer getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId the txnId to set
	 */
	public void setTxnId(final Integer txnId) {
		this.txnId = txnId;
	}

}
