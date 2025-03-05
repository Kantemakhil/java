package net.syscon.s4.im.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OtrbnrcnBean {
	@JsonProperty("accountCode")
	private Integer accountCode;

	@JsonProperty("accountCodeDesc")
	private String accountCodeDesc;

	@JsonProperty("displayAmntBnk")
	private BigDecimal displayAmntBnk;

	@JsonProperty("amntPAdj")
	private BigDecimal amntPAdj;

	@JsonProperty("amntLAdj")
	private BigDecimal amntLAdj;

	@JsonProperty("amntAdjBalance")
	private BigDecimal amntAdjBalance;

	@JsonProperty("adjComment")
	private String adjComment;
	
	@JsonProperty("fCrdate2")
	private Date fCrdate2;
	
	@JsonProperty("fCrReference2")
	private Long fCrReference2;
	
	@JsonProperty("fCrPye2")
	private String fCrPye2;
	
	@JsonProperty("fCrAmount2")
	private BigDecimal fCrAmount2;
	
	@JsonProperty("fTotalCreditAmount2")
	private BigDecimal fTotalCreditAmount2;
	
	
	@JsonProperty("fCrdate1")
	private Date fCrdate1;
	
	@JsonProperty("fCrReference1")
	private Long fCrReference1;
	
	@JsonProperty("fCrPye1")
	private String fCrPye1;
	
	@JsonProperty("fCrAmount1")
	private BigDecimal fCrAmount1;
	
	@JsonProperty("fTotalCreditAmount1")
	private BigDecimal fTotalCreditAmount1;
	
	@JsonProperty("fDrDate1")
	private Date fDrDate1;
	
	@JsonProperty("fDr")
	private String fDr;
	
	@JsonProperty("fDrReference1")
	private String fDrReference1;
	
	@JsonProperty("fDrDesc")
	private String fDrDesc;

	@JsonProperty("fDrAmount")
	private BigDecimal fDrAmount;
	
	@JsonProperty("userDate")
	private String userDate;
	
	@JsonProperty("fTotalDebitAmount1")
	private BigDecimal fTotalDebitAmount1;
	

	@JsonProperty("fSystemBalance1")
	private BigDecimal fSystemBalance1;
	

	@JsonProperty("fReconBankBalance1")
	private BigDecimal fReconBankBalance1;
	

	@JsonProperty("fDifference1")
	private BigDecimal fDifference1;
	
	@JsonProperty("crTxn")
	private Long crTxn;
	
	@JsonProperty("crType")
	private String crType;
	
	@JsonProperty("fields")
	private List<?> fields;
	
	@JsonProperty("queryThreeList")
	private List<OtrbnrcnBean> queryThreeList;
	
	@JsonProperty("queryFiveList")
	private List<OtrbnrcnBean> queryFiveList; 
	
	@JsonProperty("queryFourList")
	private List<OtrbnrcnBean> queryFourList;
	
	@JsonProperty("fTotalCredit")
	private BigDecimal fTotalCredit;
	
	@JsonProperty("userName")
	private String userName;
	
	
	
	
	

	
	
	
	
	
	
	
	
	@JsonProperty("report")
	private byte[] report;
	

	public OtrbnrcnBean() {
	}

	/**
	 * @return the accountCode
	 */
	public Integer getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(final Integer accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the accountCodeDesc
	 */
	public String getAccountCodeDesc() {
		return accountCodeDesc;
	}

	/**
	 * @param accountCodeDesc the accountCodeDesc to set
	 */
	public void setAccountCodeDesc(final String accountCodeDesc) {
		this.accountCodeDesc = accountCodeDesc;
	}

	/**
	 * @return the displayAmntBnk
	 */
	public BigDecimal getDisplayAmntBnk() {
		return displayAmntBnk;
	}

	/**
	 * @param displayAmntBnk the displayAmntBnk to set
	 */
	public void setDisplayAmntBnk(final BigDecimal displayAmntBnk) {
		this.displayAmntBnk = displayAmntBnk;
	}

	/**
	 * @return the amntPAdj
	 */
	public BigDecimal getAmntPAdj() {
		return amntPAdj;
	}

	/**
	 * @param amntPAdj the amntPAdj to set
	 */
	public void setAmntPAdj(final BigDecimal amntPAdj) {
		this.amntPAdj = amntPAdj;
	}

	/**
	 * @return the amntLAdj
	 */
	public BigDecimal getAmntLAdj() {
		return amntLAdj;
	}

	/**
	 * @param amntLAdj the amntLAdj to set
	 */
	public void setAmntLAdj(final BigDecimal amntLAdj) {
		this.amntLAdj = amntLAdj;
	}

	/**
	 * @return the amntAdjBalance
	 */
	public BigDecimal getAmntAdjBalance() {
		return amntAdjBalance;
	}

	/**
	 * @param amntAdjBalance the amntAdjBalance to set
	 */
	public void setAmntAdjBalance(final BigDecimal amntAdjBalance) {
		this.amntAdjBalance = amntAdjBalance;
	}

	/**
	 * @return the adjComment
	 */
	public String getAdjComment() {
		return adjComment;
	}

	/**
	 * @param adjComment the adjComment to set
	 */
	public void setAdjComment(final String adjComment) {
		this.adjComment = adjComment;
	}

	/**
	 * @return the report
	 */
	public byte[] getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(final byte[] report) {
		this.report = report;
	}

	/**
	 * @return the fCrdate2
	 */
	public Date getfCrdate2() {
		return fCrdate2;
	}

	/**
	 * @param fCrdate2 the fCrdate2 to set
	 */
	public void setfCrdate2(final Date fCrdate2) {
		this.fCrdate2 = fCrdate2;
	}

	/**
	 * @return the fCrReference2
	 */
	public Long getfCrReference2() {
		return fCrReference2;
	}

	/**
	 * @param fCrReference2 the fCrReference2 to set
	 */
	public void setfCrReference2(final Long fCrReference2) {
		this.fCrReference2 = fCrReference2;
	}

	/**
	 * @return the fCrPye2
	 */
	public String getfCrPye2() {
		return fCrPye2;
	}

	/**
	 * @param fCrPye2 the fCrPye2 to set
	 */
	public void setfCrPye2(final String fCrPye2) {
		this.fCrPye2 = fCrPye2;
	}

	/**
	 * @return the fCrAmount2
	 */
	public BigDecimal getfCrAmount2() {
		return fCrAmount2;
	}

	/**
	 * @param fCrAmount2 the fCrAmount2 to set
	 */
	public void setfCrAmount2(final BigDecimal fCrAmount2) {
		this.fCrAmount2 = fCrAmount2;
	}

	/**
	 * @return the fTotalCreditAmount2
	 */
	public BigDecimal getfTotalCreditAmount2() {
		return fTotalCreditAmount2;
	}

	/**
	 * @param fTotalCreditAmount2 the fTotalCreditAmount2 to set
	 */
	public void setfTotalCreditAmount2(final BigDecimal fTotalCreditAmount2) {
		this.fTotalCreditAmount2 = fTotalCreditAmount2;
	}

	/**
	 * @return the fCrdate1
	 */
	public Date getfCrdate1() {
		return fCrdate1;
	}

	/**
	 * @param fCrdate1 the fCrdate1 to set
	 */
	public void setfCrdate1(final Date fCrdate1) {
		this.fCrdate1 = fCrdate1;
	}

	/**
	 * @return the fCrReference1
	 */
	public Long getfCrReference1() {
		return fCrReference1;
	}

	/**
	 * @param fCrReference1 the fCrReference1 to set
	 */
	public void setfCrReference1(final Long fCrReference1) {
		this.fCrReference1 = fCrReference1;
	}

	/**
	 * @return the fCrPye1
	 */
	public String getfCrPye1() {
		return fCrPye1;
	}

	/**
	 * @param fCrPye1 the fCrPye1 to set
	 */
	public void setfCrPye1(final String fCrPye1) {
		this.fCrPye1 = fCrPye1;
	}

	/**
	 * @return the fCrAmount1
	 */
	public BigDecimal getfCrAmount1() {
		return fCrAmount1;
	}

	/**
	 * @param fCrAmount1 the fCrAmount1 to set
	 */
	public void setfCrAmount1(final BigDecimal fCrAmount1) {
		this.fCrAmount1 = fCrAmount1;
	}

	/**
	 * @return the fTotalCreditAmount1
	 */
	public BigDecimal getfTotalCreditAmount1() {
		return fTotalCreditAmount1;
	}

	/**
	 * @param fTotalCreditAmount1 the fTotalCreditAmount1 to set
	 */
	public void setfTotalCreditAmount1(final BigDecimal fTotalCreditAmount1) {
		this.fTotalCreditAmount1 = fTotalCreditAmount1;
	}

	/**
	 * @return the fDrDate1
	 */
	public Date getfDrDate1() {
		return fDrDate1;
	}

	/**
	 * @param fDrDate1 the fDrDate1 to set
	 */
	public void setfDrDate1(final Date fDrDate1) {
		this.fDrDate1 = fDrDate1;
	}

	/**
	 * @return the fDrReference1
	 */
	public String getfDrReference1() {
		return fDrReference1;
	}

	/**
	 * @param fDrReference1 the fDrReference1 to set
	 */
	public void setfDrReference1(final String fDrReference1) {
		this.fDrReference1 = fDrReference1;
	}

	/**
	 * @return the fDrDesc
	 */
	public String getfDrDesc() {
		return fDrDesc;
	}

	/**
	 * @param fDrDesc the fDrDesc to set
	 */
	public void setfDrDesc(final String fDrDesc) {
		this.fDrDesc = fDrDesc;
	}

	/**
	 * @return the fDrAmount
	 */
	public BigDecimal getfDrAmount() {
		return fDrAmount;
	}

	/**
	 * @param fDrAmount the fDrAmount to set
	 */
	public void setfDrAmount(final BigDecimal fDrAmount) {
		this.fDrAmount = fDrAmount;
	}

	/**
	 * @return the userDate
	 */
	public String getUserDate() {
		return userDate;
	}

	/**
	 * @param userDate the userDate to set
	 */
	public void setUserDate(final String userDate) {
		this.userDate = userDate;
	}

	/**
	 * @return the fTotalDebitAmount1
	 */
	public BigDecimal getfTotalDebitAmount1() {
		return fTotalDebitAmount1;
	}

	/**
	 * @param fTotalDebitAmount1 the fTotalDebitAmount1 to set
	 */
	public void setfTotalDebitAmount1(final BigDecimal fTotalDebitAmount1) {
		this.fTotalDebitAmount1 = fTotalDebitAmount1;
	}

	/**
	 * @return the fSystemBalance1
	 */
	public BigDecimal getfSystemBalance1() {
		return fSystemBalance1;
	}

	/**
	 * @param fSystemBalance1 the fSystemBalance1 to set
	 */
	public void setfSystemBalance1(final BigDecimal fSystemBalance1) {
		this.fSystemBalance1 = fSystemBalance1;
	}

	/**
	 * @return the fReconBankBalance1
	 */
	public BigDecimal getfReconBankBalance1() {
		return fReconBankBalance1;
	}

	/**
	 * @param fReconBankBalance1 the fReconBankBalance1 to set
	 */
	public void setfReconBankBalance1(final BigDecimal fReconBankBalance1) {
		this.fReconBankBalance1 = fReconBankBalance1;
	}

	/**
	 * @return the fDifference1
	 */
	public BigDecimal getfDifference1() {
		return fDifference1;
	}

	/**
	 * @param fDifference1 the fDifference1 to set
	 */
	public void setfDifference1(final BigDecimal fDifference1) {
		this.fDifference1 = fDifference1;
	}

	/**
	 * @return the crTxn
	 */
	public Long getCrTxn() {
		return crTxn;
	}

	/**
	 * @param crTxn the crTxn to set
	 */
	public void setCrTxn(final Long crTxn) {
		this.crTxn = crTxn;
	}

	/**
	 * @return the crType
	 */
	public String getCrType() {
		return crType;
	}

	/**
	 * @param crType the crType to set
	 */
	public void setCrType(final String crType) {
		this.crType = crType;
	}

	/**
	 * @return the fields
	 */
	public List<?> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(final List<?> fields) {
		this.fields = fields;
	}

	/**
	 * @return the queryThreeList
	 */
	public List<OtrbnrcnBean> getQueryThreeList() {
		return queryThreeList;
	}

	/**
	 * @param queryThreeList the queryThreeList to set
	 */
	public void setQueryThreeList(final List<OtrbnrcnBean> queryThreeList) {
		this.queryThreeList = queryThreeList;
	}

	/**
	 * @return the queryFiveList
	 */
	public List<OtrbnrcnBean> getQueryFiveList() {
		return queryFiveList;
	}

	/**
	 * @param queryFiveList the queryFiveList to set
	 */
	public void setQueryFiveList(final List<OtrbnrcnBean> queryFiveList) {
		this.queryFiveList = queryFiveList;
	}

	/**
	 * @return the fDr
	 */
	public String getfDr() {
		return fDr;
	}

	/**
	 * @param fDr the fDr to set
	 */
	public void setfDr(final String fDr) {
		this.fDr = fDr;
	}

	/**
	 * @return the queryFourList
	 */
	public List<OtrbnrcnBean> getQueryFourList() {
		return queryFourList;
	}

	/**
	 * @param queryFourList the queryFourList to set
	 */
	public void setQueryFourList(final List<OtrbnrcnBean> queryFourList) {
		this.queryFourList = queryFourList;
	}

	/**
	 * @return the fTotalCredit
	 */
	public BigDecimal getfTotalCredit() {
		return fTotalCredit;
	}

	/**
	 * @param fTotalCredit the fTotalCredit to set
	 */
	public void setfTotalCredit(final BigDecimal fTotalCredit) {
		this.fTotalCredit = fTotalCredit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
