package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the STATUTES database table.
 * 
 */
public class Statutes extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String statuteCode;

	private String activeFlag;

	private Date createDatetime;

	private String createUserId;

	private String description;

	private Date expiryDate;

	private String legislatingBodyCode;

	private BigDecimal listSeq;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private String updateAllowedFlag;

	private String code;

	private Long rowId;

	public Statutes() {
	}

	public String getStatuteCode() {
		return this.statuteCode;
	}

	public void setStatuteCode(final String statuteCode) {
		this.statuteCode = statuteCode;
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

	public String getLegislatingBodyCode() {
		return this.legislatingBodyCode;
	}

	public void setLegislatingBodyCode(final String legislatingBodyCode) {
		this.legislatingBodyCode = legislatingBodyCode;
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

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
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

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(final Long rowId) {
		this.rowId = rowId;
	}

}
