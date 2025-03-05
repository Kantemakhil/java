package net.syscon.s4.sa.recordmaintenance;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the MERGE_TRANSACTION_PROCESSES database table.
 * 
 */
public class MergeTransactionProcesses implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date beginDate;

	private Date createDatetime;

	private String createUserId;

	private Date endDate;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private String timeframeFlag;

	private long mergeTransactionId;

	private long processId;

	public MergeTransactionProcesses() {
		// MergeTransactionProcesses
	}

	public long getMergeTransactionId() {
		return mergeTransactionId;
	}

	public void setMergeTransactionId(long mergeTransactionId) {
		this.mergeTransactionId = mergeTransactionId;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getTimeframeFlag() {
		return this.timeframeFlag;
	}

	public void setTimeframeFlag(String timeframeFlag) {
		this.timeframeFlag = timeframeFlag;
	}

}
