package net.syscon.s4.cf.statements.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ocdbreciReportsBean {
	@JsonProperty("fClient")
	private String fClient;
	@JsonProperty("fClDesc")
	private String fClDesc;
	@JsonProperty("fTxnDate")
	private Date fTxnDate;
	@JsonProperty("fOne")
	private BigDecimal fOne;
	@JsonProperty("fTwo")
	private BigDecimal fTwo;
	@JsonProperty("fThree")
	private BigDecimal fThree;
	@JsonProperty("fFour")
	private BigDecimal fFour;
	@JsonProperty("fFive")
	private BigDecimal fFive;
	@JsonProperty("fAmtOne")
	private BigDecimal fAmtOne;
	@JsonProperty("fAmtTwo")
	private String fAmtTwo;
	@JsonProperty("fOffName")
	private String fOffName;
	@JsonProperty("fTxnDesc")
	private String fTxnDesc;
	@JsonProperty("fOffId")
	private String fOffId;
	@JsonProperty("fUserId")
	private String fUserId;
	@JsonProperty("txnId")
	private Integer txnId;
	@JsonProperty("fRecNo")
	private String fRecNo;
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	@JsonProperty("amt")
	private BigDecimal amt;
	@JsonProperty("txnSeq")
	private BigDecimal txnSeq;
	@JsonProperty("txnType")
	private String txnType;

	/**
	 * @return the fClient
	 */
	public String getfClient() {
		return fClient;
	}

	/**
	 * @param fClient
	 *            the fClient to set
	 */
	public void setfClient(final String fClient) {
		this.fClient = fClient;
	}

	/**
	 * @return the fClDesc
	 */
	public String getfClDesc() {
		return fClDesc;
	}

	/**
	 * @param fClDesc
	 *            the fClDesc to set
	 */
	public void setfClDesc(final String fClDesc) {
		this.fClDesc = fClDesc;
	}

	/**
	 * @return the fTxnDate
	 */
	public Date getfTxnDate() {
		return fTxnDate;
	}

	/**
	 * @param fTxnDate
	 *            the fTxnDate to set
	 */
	public void setfTxnDate(final Date fTxnDate) {
		this.fTxnDate = fTxnDate;
	}

	/**
	 * @return the fOne
	 */
	public BigDecimal getfOne() {
		return fOne;
	}

	/**
	 * @param fOne
	 *            the fOne to set
	 */
	public void setfOne(final BigDecimal fOne) {
		this.fOne = fOne;
	}

	/**
	 * @return the fTwo
	 */
	public BigDecimal getfTwo() {
		return fTwo;
	}

	/**
	 * @param fTwo
	 *            the fTwo to set
	 */
	public void setfTwo(final BigDecimal fTwo) {
		this.fTwo = fTwo;
	}

	/**
	 * @return the fThree
	 */
	public BigDecimal getfThree() {
		return fThree;
	}

	/**
	 * @param fThree
	 *            the fThree to set
	 */
	public void setfThree(final BigDecimal fThree) {
		this.fThree = fThree;
	}

	/**
	 * @return the fFour
	 */
	public BigDecimal getfFour() {
		return fFour;
	}

	/**
	 * @param fFour
	 *            the fFour to set
	 */
	public void setfFour(final BigDecimal fFour) {
		this.fFour = fFour;
	}

	/**
	 * @return the fFive
	 */
	public BigDecimal getfFive() {
		return fFive;
	}

	/**
	 * @param fFive
	 *            the fFive to set
	 */
	public void setfFive(final BigDecimal fFive) {
		this.fFive = fFive;
	}

	/**
	 * @return the fAmtOne
	 */
	public BigDecimal getfAmtOne() {
		return fAmtOne;
	}

	/**
	 * @param fAmtOne
	 *            the fAmtOne to set
	 */
	public void setfAmtOne(final BigDecimal fAmtOne) {
		this.fAmtOne = fAmtOne;
	}

	/**
	 * @return the fAmtTwo
	 */
	public String getfAmtTwo() {
		return fAmtTwo;
	}

	/**
	 * @param fAmtTwo
	 *            the fAmtTwo to set
	 */
	public void setfAmtTwo(final String fAmtTwo) {
		this.fAmtTwo = fAmtTwo;
	}

	/**
	 * @return the fOffName
	 */
	public String getfOffName() {
		return fOffName;
	}

	/**
	 * @param fOffName
	 *            the fOffName to set
	 */
	public void setfOffName(final String fOffName) {
		this.fOffName = fOffName;
	}

	/**
	 * @return the fTxnDesc
	 */
	public String getfTxnDesc() {
		return fTxnDesc;
	}

	/**
	 * @param fTxnDesc
	 *            the fTxnDesc to set
	 */
	public void setfTxnDesc(final String fTxnDesc) {
		this.fTxnDesc = fTxnDesc;
	}

	/**
	 * @return the fOffId
	 */
	public String getfOffId() {
		return fOffId;
	}

	/**
	 * @param fOffId
	 *            the fOffId to set
	 */
	public void setfOffId(final String fOffId) {
		this.fOffId = fOffId;
	}

	/**
	 * @return the fUserId
	 */
	public String getfUserId() {
		return fUserId;
	}

	/**
	 * @param fUserId
	 *            the fUserId to set
	 */
	public void setfUserId(final String fUserId) {
		this.fUserId = fUserId;
	}

	/**
	 * @return the txnId
	 */
	public Integer getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId
	 *            the txnId to set
	 */
	public void setTxnId(final Integer txnId) {
		this.txnId = txnId;
	}

	/**
	 * @return the fRecNo
	 */
	public String getfRecNo() {
		return fRecNo;
	}

	/**
	 * @param fRecNo
	 *            the fRecNo to set
	 */
	public void setfRecNo(final String fRecNo) {
		this.fRecNo = fRecNo;
	}

	/**
	 * @return the offenderId
	 */
	public BigDecimal getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the amt
	 */
	public BigDecimal getAmt() {
		return amt;
	}

	/**
	 * @param amt
	 *            the amt to set
	 */
	public void setAmt(final BigDecimal amt) {
		this.amt = amt;
	}

	/**
	 * @return the txnSeq
	 */
	public BigDecimal getTxnSeq() {
		return txnSeq;
	}

	/**
	 * @param txnSeq
	 *            the txnSeq to set
	 */
	public void setTxnSeq(final BigDecimal txnSeq) {
		this.txnSeq = txnSeq;
	}

	/**
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}

	/**
	 * @param txnType
	 *            the txnType to set
	 */
	public void setTxnType(final String txnType) {
		this.txnType = txnType;
	}

}
