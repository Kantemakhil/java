package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtrdreceReportBean  extends BaseModel implements Serializable   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("domain")
	private String domain;
	@JsonProperty("client")
	private String client;
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
	@JsonProperty("offName")
	private String offName;
	@JsonProperty("offId")
	private String offId;
	@JsonProperty("txnDesc")
	private String txnDesc;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("payeeName")
	private String payeeName;
	@JsonProperty("checkNum")
	private String checkNum;
	@JsonProperty("location")
	private String location;
	@JsonProperty("bookId")
	private String bookId;
	@JsonProperty("txnUsage")
	private String txnUsage;
	@JsonProperty("amtText")
	private String amtText;
	
	@JsonProperty("caseloadId")
	private String caseloadId;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	@JsonProperty("txnId")
	private Integer txnId;
	
	public OtrdreceReportBean() {
//		OtrdreceReportBean
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(final String domain) {
		this.domain = domain;
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
	 * @return the txnDesc
	 */
	public String getTxnDesc() {
		return txnDesc;
	}

	/**
	 * @param txnDesc the txnDesc to set
	 */
	public void setTxnDesc(final String txnDesc) {
		this.txnDesc = txnDesc;
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
	 * @return the payeeName
	 */
	public String getPayeeName() {
		return payeeName;
	}

	/**
	 * @param payeeName the payeeName to set
	 */
	public void setPayeeName(final String payeeName) {
		this.payeeName = payeeName;
	}

	/**
	 * @return the checkNum
	 */
	public String getCheckNum() {
		return checkNum;
	}

	/**
	 * @param checkNum the checkNum to set
	 */
	public void setCheckNum(final String checkNum) {
		this.checkNum = checkNum;
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
	 * @return the bookId
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(final String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the txnUsage
	 */
	public String getTxnUsage() {
		return txnUsage;
	}

	/**
	 * @param txnUsage the txnUsage to set
	 */
	public void setTxnUsage(final String txnUsage) {
		this.txnUsage = txnUsage;
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
