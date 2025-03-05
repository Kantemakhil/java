package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class WorkRates extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String payRangeCode;
	private String unitCode;
	private String compensationCode;
	private String performanceCode;
	private BigDecimal rateAmount;
	private String modifyUserId;
	private Date modifyDatetime;
	private Date createDatetime;
	private String createUserId;
	private String sealFlag;
	private String jnOperation;

	public String getPayRangeCode() {
		return payRangeCode;
	}

	public void setPayRangeCode(String payRangeCode) {
		this.payRangeCode = payRangeCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getCompensationCode() {
		return compensationCode;
	}

	public void setCompensationCode(String compensationCode) {
		this.compensationCode = compensationCode;
	}

	public String getPerformanceCode() {
		return performanceCode;
	}

	public void setPerformanceCode(String performanceCode) {
		this.performanceCode = performanceCode;
	}

	public BigDecimal getRateAmount() {
		return rateAmount;
	}

	public void setRateAmount(BigDecimal rateAmount) {
		this.rateAmount = rateAmount;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getJnOperation() {
		return jnOperation;
	}

	public void setJnOperation(String jnOperation) {
		this.jnOperation = jnOperation;
	}

}
