package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the SANCTION_NOTICES database table.
 * 
 */
public class SanctionNotices extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private Date createDatetime;

	private String createUserId;
	
	private String code;

	private String description;

	private Date expiryDate;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private BigDecimal seqNum;

	

	private String updateAllowedFlag;
	
	private String sanctionNoticeCode;

	private long lateDays;
	
	
	private BigDecimal issuePeriod;
	
	@JsonProperty("rowId")
	private String rowId;

	public SanctionNotices() {
	}
	
	public String getSanctionNoticeCode() {
		return sanctionNoticeCode;
	}

	public void setSanctionNoticeCode(final String sanctionNoticeCode) {
		this.sanctionNoticeCode = sanctionNoticeCode;
	}

	public long getLateDays() {
		return lateDays;
	}

	public void setLateDays(final long lateDays) {
		this.lateDays = lateDays;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
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

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getSeqNum() {
		return this.seqNum;
	}

	public void setSeqNum(final BigDecimal seqNum) {
		this.seqNum = seqNum;
	}

	public String getUpdateAllowedFlag() {
		return this.updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}
	public BigDecimal getIssuePeriod() {
		return issuePeriod;
	}

	public void setIssuePeriod(BigDecimal issuePeriod) {
		this.issuePeriod = issuePeriod;
	}
}
