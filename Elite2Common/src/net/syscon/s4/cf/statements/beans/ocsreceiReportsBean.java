package net.syscon.s4.cf.statements.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ocsreceiReportsBean {
	private String fClient;
	private String fClDesc;
	private String facility;
	private Date fTxnDate;
	private String fRecNo;
	private String payee;
	private Double totalAmount;
	private String forAmount;
	private String fOffName;
	private String fTxnDesc;
	private String fOffId;
	private String userId;
	private String fLocName;
	private String fLocation;
	private Double fAmt;
	@JsonProperty("txnId")
	private Integer txnId;
	private Date txnDate;
	private Long offenderId;

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	public Date getfTxnDate() {
		return fTxnDate;
	}

	public void setfTxnDate(final Date fTxnDate) {
		this.fTxnDate = fTxnDate;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(final Date txnDate) {
		this.txnDate = txnDate;
	}

	public String getfRecNo() {
		return fRecNo;
	}

	public void setfRecNo(final String fRecNo) {
		this.fRecNo = fRecNo;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(final String payee) {
		this.payee = payee;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(final Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getForAmount() {
		return forAmount;
	}

	public void setForAmount(final String forAmount) {
		this.forAmount = forAmount;
	}

	public String getfOffName() {
		return fOffName;
	}

	public void setfOffName(final String fOffName) {
		this.fOffName = fOffName;
	}

	public String getfTxnDesc() {
		return fTxnDesc;
	}

	public void setfTxnDesc(final String fTxnDesc) {
		this.fTxnDesc = fTxnDesc;
	}

	public String getfOffId() {
		return fOffId;
	}

	public void setfOffId(final String fOffId) {
		this.fOffId = fOffId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getfLocName() {
		return fLocName;
	}

	public void setfLocName(final String fLocName) {
		this.fLocName = fLocName;
	}

	public String getfLocation() {
		return fLocation;
	}

	public void setfLocation(final String fLocation) {
		this.fLocation = fLocation;
	}

	public Double getfAmt() {
		return fAmt;
	}

	public void setfAmt(final Double fAmt) {
		this.fAmt = fAmt;
	}

	public String getfClDesc() {
		return fClDesc;
	}

	public void setfClDesc(final String fClDesc) {
		this.fClDesc = fClDesc;
	}

	public String getfClient() {
		return fClient;
	}

	public void setfClient(final String fClient) {
		this.fClient = fClient;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(final String facility) {
		this.facility = facility;
	}

	public Integer getTxnId() {
		return txnId;
	}

	public void setTxnId(final Integer txnId) {
		this.txnId = txnId;
	}

}
