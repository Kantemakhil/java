package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WorkFlows implements Serializable {
	private static final long serialVersionUID = 1L;

	private long workFlowId;

	private Date createDatetime;
	private Date createDate;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String objectCode;

	private BigDecimal objectId;

	private BigDecimal objectSeq;

	private String sealFlag;
	private int returnValue;

	public WorkFlows() {
		// WorkFlows
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

	public long getWorkFlowId() {
		return this.workFlowId;
	}

	public void setWorkFlowId(long workFlowId) {
		this.workFlowId = workFlowId;
	}

	public Object getCreateDatetime() {
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

	public String getObjectCode() {
		return this.objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public BigDecimal getObjectId() {
		return this.objectId;
	}

	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
	}

	public BigDecimal getObjectSeq() {
		return this.objectSeq;
	}

	public void setObjectSeq(BigDecimal objectSeq) {
		this.objectSeq = objectSeq;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}


}
