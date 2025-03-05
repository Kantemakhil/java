package net.syscon.s4.inst.casemanagement.beans;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class CaseReviewPeriods extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String supervisionLevel;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal reviewPeriod;

	private String sealFlag;

	private Integer returnValue;

	private Integer rowId;

	public CaseReviewPeriods() {
		// CaseReviewPeriods
	}

	public String getSupervisionLevel() {
		return this.supervisionLevel;
	}

	public void setSupervisionLevel(final String supervisionLevel) {
		this.supervisionLevel = supervisionLevel;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getReviewPeriod() {
		return this.reviewPeriod;
	}

	public void setReviewPeriod(final BigDecimal reviewPeriod) {
		this.reviewPeriod = reviewPeriod;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Integer getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(final Integer returnValue) {
		this.returnValue = returnValue;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(final Integer rowId) {
		this.rowId = rowId;
	}

}
