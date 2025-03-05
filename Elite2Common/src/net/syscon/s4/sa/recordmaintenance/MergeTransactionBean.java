package net.syscon.s4.sa.recordmaintenance;

import java.math.BigDecimal;
import java.util.Date;

public class MergeTransactionBean {
	private long pFromOffBookId;
	private BigDecimal pFromRootOffId;
	private long pFromOffenderId;
	private String pFromOffIdDisplay;
	private String pFromLastname;
	private String pFromFirstName;
	private long pToOffBookId;
	private BigDecimal pToRootOffId;
	private long pToOffenderId;
	private String pToOffIdDisplay;
	private String pToLastName;
	private String pToFirstName;
	private Integer pMergeTransactionId;
    private Date requestDate;
    private String requestStatusCode;
    private String queueMessageId;
    private String transactionSource;
    private String modifyUserId;
    private String createUserId;
    private Date createDatetime;
    private Date modifyDatetime;
    private String sealFlag;
    private String transactionType;
    private String pFromBookingNo;

	public MergeTransactionBean(){
		// mergeTransactionBean
	}
	public Date getRequestDate() {
		return requestDate;
	}



	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}



	public String getRequestStatusCode() {
		return requestStatusCode;
	}



	public void setRequestStatusCode(String requestStatusCode) {
		this.requestStatusCode = requestStatusCode;
	}



	public String getQueueMessageId() {
		return queueMessageId;
	}



	public void setQueueMessageId(String queueMessageId) {
		this.queueMessageId = queueMessageId;
	}



	public String getTransactionSource() {
		return transactionSource;
	}



	public void setTransactionSource(String transactionSource) {
		this.transactionSource = transactionSource;
	}



	public String getModifyUserId() {
		return modifyUserId;
	}



	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}



	public String getCreateUserId() {
		return createUserId;
	}



	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}



	public Date getCreateDatetime() {
		return createDatetime;
	}



	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}



	public Date getModifyDatetime() {
		return modifyDatetime;
	}



	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}



	public String getSealFlag() {
		return sealFlag;
	}



	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}



	public String getTransactionType() {
		return transactionType;
	}



	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public long getpFromOffBookId() {
		return pFromOffBookId;
	}

	public void setpFromOffBookId(long pFromOffBookId) {
		this.pFromOffBookId = pFromOffBookId;
	}

	public BigDecimal getpFromRootOffId() {
		return pFromRootOffId;
	}

	public void setpFromRootOffId(BigDecimal pFromRootOffId) {
		this.pFromRootOffId = pFromRootOffId;
	}

	public long getpFromOffenderId() {
		return pFromOffenderId;
	}

	public void setpFromOffenderId(long pFromOffenderId) {
		this.pFromOffenderId = pFromOffenderId;
	}

	public String getpFromOffIdDisplay() {
		return pFromOffIdDisplay;
	}

	public void setpFromOffIdDisplay(String pFromOffIdDisplay) {
		this.pFromOffIdDisplay = pFromOffIdDisplay;
	}

	public String getpFromLastname() {
		return pFromLastname;
	}

	public void setpFromLastname(String pFromLastname) {
		this.pFromLastname = pFromLastname;
	}

	public String getpFromFirstName() {
		return pFromFirstName;
	}

	public void setpFromFirstName(String pFromFirstName) {
		this.pFromFirstName = pFromFirstName;
	}

	public long getpToOffBookId() {
		return pToOffBookId;
	}

	public void setpToOffBookId(long pToOffBookId) {
		this.pToOffBookId = pToOffBookId;
	}

	public BigDecimal getpToRootOffId() {
		return pToRootOffId;
	}

	public void setpToRootOffId(BigDecimal pToRootOffId) {
		this.pToRootOffId = pToRootOffId;
	}

	public long getpToOffenderId() {
		return pToOffenderId;
	}

	public void setpToOffenderId(long pToOffenderId) {
		this.pToOffenderId = pToOffenderId;
	}

	public String getpToOffIdDisplay() {
		return pToOffIdDisplay;
	}

	public void setpToOffIdDisplay(String pToOffIdDisplay) {
		this.pToOffIdDisplay = pToOffIdDisplay;
	}

	public String getpToLastName() {
		return pToLastName;
	}

	public void setpToLastName(String pToLastName) {
		this.pToLastName = pToLastName;
	}

	public String getpToFirstName() {
		return pToFirstName;
	}

	public void setpToFirstName(String pToFirstName) {
		this.pToFirstName = pToFirstName;
	}

	public Integer getpMergeTransactionId() {
		return pMergeTransactionId;
	}

	public void setpMergeTransactionId(Integer pMergeTransactionId) {
		this.pMergeTransactionId = pMergeTransactionId;
	}
	
	public String getpFromBookingNo() {
		return pFromBookingNo;
	}
	public void setpFromBookingNo(String pFromBookingNo) {
		this.pFromBookingNo = pFromBookingNo;
	}
}
