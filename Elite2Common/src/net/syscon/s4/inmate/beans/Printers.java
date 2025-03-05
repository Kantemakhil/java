package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the PRINTERS database table.
 * 
 */
public class Printers extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String printerId;

	private String printerType;

	private String activeFlag;

	private Date createDatetime;

	private String createUserId;

	private String code;

	private String description;

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

	public String getDefaultPrinterId() {
		return defaultPrinterId;
	}

	public void setDefaultPrinterId(final String defaultPrinterId) {
		this.defaultPrinterId = defaultPrinterId;
	}

	private String defaultPrinterId;

	private Date expiryDate;

	private BigDecimal listSeq;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private String updateAllowedFlag;

	public Printers() {
		// Printers
	}

	public String getPrinterId() {
		return this.printerId;
	}

	public void setPrinterId(final String printerId) {
		this.printerId = printerId;
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

	public String getPrinterType() {
		return printerType;
	}

	public void setPrinterType(final String printerType) {
		this.printerType = printerType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
