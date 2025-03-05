package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the ORDER_TYPES database table.
 * 
 */
public class OrderTypes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String orderType;

	private String activeFlag;

	private String caseloadType;

	private String chargesFlag;

	private Date createDatetime;

	private String createUserId;

	private BigDecimal custodyDays;

	private String custodyFlag;

	private String description;

	private Date expiryDate;

	private BigDecimal listSeq;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal noOfHoldDays;

	private String orderCategory;

	private String scheduleFlag;

	private String sealFlag;

	private BigDecimal severityRank;

	private String timeSensitiveFlag;

	private String updateAllowedFlag;

	private String youthOrderFlag;
	
	private String code;

	public OrderTypes() {
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(final String orderType) {
		this.orderType = orderType;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getChargesFlag() {
		return this.chargesFlag;
	}

	public void setChargesFlag(final String chargesFlag) {
		this.chargesFlag = chargesFlag;
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

	public BigDecimal getCustodyDays() {
		return this.custodyDays;
	}

	public void setCustodyDays(final BigDecimal custodyDays) {
		this.custodyDays = custodyDays;
	}

	public String getCustodyFlag() {
		return this.custodyFlag;
	}

	public void setCustodyFlag(final String custodyFlag) {
		this.custodyFlag = custodyFlag;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
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

	public BigDecimal getNoOfHoldDays() {
		return this.noOfHoldDays;
	}

	public void setNoOfHoldDays(final BigDecimal noOfHoldDays) {
		this.noOfHoldDays = noOfHoldDays;
	}

	public String getOrderCategory() {
		return this.orderCategory;
	}

	public void setOrderCategory(final String orderCategory) {
		this.orderCategory = orderCategory;
	}

	public String getScheduleFlag() {
		return this.scheduleFlag;
	}

	public void setScheduleFlag(final String scheduleFlag) {
		this.scheduleFlag = scheduleFlag;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getSeverityRank() {
		return this.severityRank;
	}

	public void setSeverityRank(final BigDecimal severityRank) {
		this.severityRank = severityRank;
	}

	public String getTimeSensitiveFlag() {
		return this.timeSensitiveFlag;
	}

	public void setTimeSensitiveFlag(final String timeSensitiveFlag) {
		this.timeSensitiveFlag = timeSensitiveFlag;
	}

	public String getUpdateAllowedFlag() {
		return this.updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	public String getYouthOrderFlag() {
		return this.youthOrderFlag;
	}

	public void setYouthOrderFlag(final String youthOrderFlag) {
		this.youthOrderFlag = youthOrderFlag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
