package net.syscon.s4.inst.property.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class Printer extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("printerId")
	@NotNull
	@Size(max = 12)
	private String printerId;

	@JsonProperty("activeFlag")
	@Size(max = 1)
	@NotNull
	private String activeFlag;

	@JsonProperty("createDatetime")
	@NotNull
	private Object createDatetime;

	@JsonProperty("createUserId")
	@Size(max = 32)
	@NotNull
	private String createUserId;

	@JsonProperty("description")
	@Size(max = 40)
	@NotNull
	private String description;

	@JsonProperty("expiryDate")
	private Object expiryDate;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDatetime")
	private Object modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("updateAllowedFlag")
	@Size(max = 1)
	@NotNull
	private String updateAllowedFlag;
	
	@JsonProperty("printerType")
	@Size(max = 12)
	private String printerType;

	/**
	 * Creates new Printer class Object
	 */
	public Printer() {
		//Printer
	}

	/**
	 * @return the printerType
	 */
	public String getPrinterType() {
		return printerType;
	}

	/**
	 * @param printerType the printerType to set
	 */
	public void setPrinterType(final String printerType) {
		this.printerType = printerType;
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

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Object createDatetime) {
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

	public Object getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(final Object expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Object modifyDatetime) {
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


}
