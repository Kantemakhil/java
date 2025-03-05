package net.syscon.s4.im.beans;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the PRINTER_TYPES database table.
 * 
 */
public class PrinterTypes extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String printerType;

	private String activeFlag;

	private String colourFlag;

	private Date createDatetime;

	private String createUserId;

	private String description;

	private Date expiryDate;

	private BigDecimal listSeq;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private String textFlag;

	private String updateAllowedFlag;

	public PrinterTypes() {
	}

	public String getPrinterType() {
		return this.printerType;
	}

	public void setPrinterType(final String printerType) {
		this.printerType = printerType;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getColourFlag() {
		return this.colourFlag;
	}

	public void setColourFlag(final String colourFlag) {
		this.colourFlag = colourFlag;
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

	public String getTextFlag() {
		return this.textFlag;
	}

	public void setTextFlag(final String textFlag) {
		this.textFlag = textFlag;
	}

	public String getUpdateAllowedFlag() {
		return this.updateAllowedFlag;
	}

	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

}
