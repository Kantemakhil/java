package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class CaseloadFormatPrinters extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("printFormatCode")
	private String printFormatCode;

	@JsonProperty("defaultPrinterId")
	private String defaultPrinterId;

	@JsonProperty("printResourceId")
	private String printResourceId;

	@JsonProperty("printResourceFile")
	private String printResourceFile;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getPrintFormatCode() {
		return printFormatCode;
	}

	public void setPrintFormatCode(final String printFormatCode) {
		this.printFormatCode = printFormatCode;
	}

	public String getDefaultPrinterId() {
		return defaultPrinterId;
	}

	public void setDefaultPrinterId(final String defaultPrinterId) {
		this.defaultPrinterId = defaultPrinterId;
	}

	public String getPrintResourceId() {
		return printResourceId;
	}

	public void setPrintResourceId(final String printResourceId) {
		this.printResourceId = printResourceId;
	}

	public String getPrintResourceFile() {
		return printResourceFile;
	}

	public void setPrintResourceFile(final String printResourceFile) {
		this.printResourceFile = printResourceFile;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
