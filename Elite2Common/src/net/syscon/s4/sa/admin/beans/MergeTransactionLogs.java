package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the MERGE_TRANSACTION_LOGS database table.
 * 
 */
public class MergeTransactionLogs extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("mergeTransactionLogId")
	private long mergeTransactionLogId;
	@JsonProperty("mergeTransactionId")
	private long mergeTransactionId;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;

	/**
	 * @return the mergeTransactionId
	 */
	public long getMergeTransactionId() {
		return mergeTransactionId;
	}

	/**
	 * @param mergeTransactionId
	 *            the mergeTransactionId to set
	 */
	public void setMergeTransactionId(final long mergeTransactionId) {
		this.mergeTransactionId = mergeTransactionId;
	}

	@JsonProperty("logLevel")
	private String logLevel;
	@JsonProperty("logMsgPart")
	private BigDecimal logMsgPart;
	@JsonProperty("logText")
	private String logText;
	@JsonProperty("logTimestamp")
	private Date logTimestamp;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;

	public MergeTransactionLogs() {
	}

	/**
	 * @return the mergeTransactionLogId
	 */
	public long getMergeTransactionLogId() {
		return this.mergeTransactionLogId;
	}

	/**
	 * @param mergeTransactionLogId
	 *            the mergeTransactionLogId to set
	 */
	public void setMergeTransactionLogId(final long mergeTransactionLogId) {
		this.mergeTransactionLogId = mergeTransactionLogId;
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
	 * @return the logLevel
	 */
	public String getLogLevel() {
		return this.logLevel;
	}

	/**
	 * @param logLevel
	 *            the logLevel to set
	 */
	public void setLogLevel(final String logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * @return the logMsgPart
	 */
	public BigDecimal getLogMsgPart() {
		return this.logMsgPart;
	}

	/**
	 * @param logMsgPart
	 *            the logMsgPart to set
	 */
	public void setLogMsgPart(final BigDecimal logMsgPart) {
		this.logMsgPart = logMsgPart;
	}

	/**
	 * @return the logText
	 */
	public String getLogText() {
		return this.logText;
	}

	/**
	 * @param logText
	 *            the logText to set
	 */
	public void setLogText(final String logText) {
		this.logText = logText;
	}

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
	 * @return the logTimestamp
	 */
	public Date getLogTimestamp() {
		return logTimestamp;
	}

	/**
	 * @param logTimestamp
	 *            the logTimestamp to set
	 */
	public void setLogTimestamp(final Date logTimestamp) {
		this.logTimestamp = logTimestamp;
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

}
