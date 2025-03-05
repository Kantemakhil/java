package net.syscon.s4.sa.admin.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the MERGE_TRANSACTIONS database table.
 * 
 */
public class MergeTransactions extends BaseModel {
	private static final long serialVersionUID = 1L;
	// public MergeTransactions() {
	// }
	@JsonProperty("mergeTransactionId")
	private long mergeTransactionId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("firstName1")
	private String firstName1;
	@JsonProperty("firstName2")
	private String firstName2;
	@JsonProperty("lastName1")
	private String lastName1;
	@JsonProperty("lastName2")
	private String lastName2;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("offenderBookId1")
	private BigDecimal offenderBookId1;
	@JsonProperty("offenderBookId2")
	private BigDecimal offenderBookId2;
	@JsonProperty("offenderId1")
	private BigDecimal offenderId1;
	@JsonProperty("offenderId2")
	private BigDecimal offenderId2;
	@JsonProperty("offenderIdDisplay1")
	private String offenderIdDisplay1;
	@JsonProperty("offenderIdDisplay2")
	private String offenderIdDisplay2;
	@JsonProperty("queueMessageId")
	private String queueMessageId;
	@JsonProperty("requestDate")
	private Date requestDate;
	@JsonProperty("requestStatusCode")
	private String requestStatusCode;
	@JsonProperty("rootOffenderId1")
	private BigDecimal rootOffenderId1;
	@JsonProperty("rootOffenderId2")
	private BigDecimal rootOffenderId2;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("transactionSource")
	private String transactionSource;
	@JsonProperty("transactionType")
	private String transactionType;
	@JsonProperty("toDate")
	private Date toDate;
	@JsonProperty("staffName")
	private String staffName;
	@JsonProperty("trnBookingNo")
	private String trnBookingNo;
	
	@JsonProperty("tableName")
	private String tableName;
	@JsonProperty("beginDate")
	private Date beginDate;
	@JsonProperty("endDate")
	private Date endDate;
	
	
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return requestDate;
	}
	/**
	 * @param requestDate
	 *            the requestDate to set
	 */
	public void setRequestDate(final Date requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @return the mergeTransactionId
	 */
	public long getMergeTransactionId() {
		return this.mergeTransactionId;
	}
	/**
	 * @param mergeTransactionId
	 *            the mergeTransactionId to set
	 */
	public void setMergeTransactionId(final long mergeTransactionId) {
		this.mergeTransactionId = mergeTransactionId;
	}
	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the firstName1
	 */
	public String getFirstName1() {
		return this.firstName1;
	}
	/**
	 * @param firstName1
	 *            the firstName1 to set
	 */
	public void setFirstName1(final String firstName1) {
		this.firstName1 = firstName1;
	}
	/**
	 * @return the firstName2
	 */
	public String getFirstName2() {
		return this.firstName2;
	}
	/**
	 * @param firstName2
	 *            the firstName2 to set
	 */
	public void setFirstName2(final String firstName2) {
		this.firstName2 = firstName2;
	}
	/**
	 * @return the lastName1
	 */
	public String getLastName1() {
		return this.lastName1;
	}
	/**
	 * @param lastName1
	 *            the lastName1 to set
	 */
	public void setLastName1(final String lastName1) {
		this.lastName1 = lastName1;
	}
	/**
	 * @return the lastName2
	 */
	public String getLastName2() {
		return this.lastName2;
	}
	/**
	 * @param lastName2
	 *            the lastName2 to set
	 */
	public void setLastName2(final String lastName2) {
		this.lastName2 = lastName2;
	}
	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}
	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the offenderBookId1
	 */
	public BigDecimal getOffenderBookId1() {
		return this.offenderBookId1;
	}
	/**
	 * @param offenderBookId1
	 *            the offenderBookId1 to set
	 */
	public void setOffenderBookId1(final BigDecimal offenderBookId1) {
		this.offenderBookId1 = offenderBookId1;
	}
	/**
	 * @return the offenderBookId2
	 */
	public BigDecimal getOffenderBookId2() {
		return this.offenderBookId2;
	}
	/**
	 * @param offenderBookId2
	 *            the offenderBookId2 to set
	 */
	public void setOffenderBookId2(final BigDecimal offenderBookId2) {
		this.offenderBookId2 = offenderBookId2;
	}
	/**
	 * @return the offenderId1
	 */
	public BigDecimal getOffenderId1() {
		return this.offenderId1;
	}
	/**
	 * @param offenderId1
	 *            the offenderId1 to set
	 */
	public void setOffenderId1(final BigDecimal offenderId1) {
		this.offenderId1 = offenderId1;
	}
	/**
	 * @return the offenderId2
	 */
	public BigDecimal getOffenderId2() {
		return this.offenderId2;
	}
	/**
	 * @param v
	 *            the offenderId2 to set
	 */
	public void setOffenderId2(final BigDecimal offenderId2) {
		this.offenderId2 = offenderId2;
	}
	/**
	 * @return the offenderIdDisplay1
	 */
	public String getOffenderIdDisplay1() {
		return this.offenderIdDisplay1;
	}
	/**
	 * @param offenderIdDisplay1
	 *            the offenderIdDisplay1 to set
	 */
	public void setOffenderIdDisplay1(final String offenderIdDisplay1) {
		this.offenderIdDisplay1 = offenderIdDisplay1;
	}
	/**
	 * @return the offenderIdDisplay2
	 */
	public String getOffenderIdDisplay2() {
		return this.offenderIdDisplay2;
	}
	/**
	 * @param offenderIdDisplay2
	 *            the offenderIdDisplay2 to set
	 */
	public void setOffenderIdDisplay2(final String offenderIdDisplay2) {
		this.offenderIdDisplay2 = offenderIdDisplay2;
	}
	/**
	 * @return the queueMessageId
	 */
	public String getQueueMessageId() {
		return this.queueMessageId;
	}
	/**
	 * @param queueMessageId
	 *            the queueMessageId to set
	 */
	public void setQueueMessageId(final String queueMessageId) {
		this.queueMessageId = queueMessageId;
	}
	/**
	 * @return the requestStatusCode
	 */
	public String getRequestStatusCode() {
		return this.requestStatusCode;
	}
	/**
	 * @param requestStatusCode
	 *            the requestStatusCode to set
	 */
	public void setRequestStatusCode(final String requestStatusCode) {
		this.requestStatusCode = requestStatusCode;
	}
	/**
	 * @return the rootOffenderId1
	 */
	public BigDecimal getRootOffenderId1() {
		return this.rootOffenderId1;
	}
	/**
	 * @param rootOffenderId1
	 *            the rootOffenderId1 to set
	 */
	public void setRootOffenderId1(final BigDecimal rootOffenderId1) {
		this.rootOffenderId1 = rootOffenderId1;
	}
	/**
	 * @return the rootOffenderId2
	 */
	public BigDecimal getRootOffenderId2() {
		return this.rootOffenderId2;
	}
	/**
	 * @param rootOffenderId2
	 *            the rootOffenderId2 to set
	 */
	public void setRootOffenderId2(final BigDecimal rootOffenderId2) {
		this.rootOffenderId2 = rootOffenderId2;
	}
	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}
	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	/**
	 * @return the transactionSource
	 */
	public String getTransactionSource() {
		return this.transactionSource;
	}
	/**
	 * @param transactionSource
	 *            the transactionSource to set
	 */
	public void setTransactionSource(final String transactionSource) {
		this.transactionSource = transactionSource;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return this.transactionType;
	}
	/**
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(final String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}
	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(final Date toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}
	/**
	 * @param staffName
	 *            the staffName to set
	 */
	public void setStaffName(final String staffName) {
		this.staffName = staffName;
	}
	/**
	 * @return the trnBookingNo
	 */
	public String getTrnBookingNo() {
		return trnBookingNo;
	}
	/**
	 * @param trnBookingNo
	 *            the trnBookingNo to set
	 */
	public void setTrnBookingNo(final String trnBookingNo) {
		this.trnBookingNo = trnBookingNo;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}