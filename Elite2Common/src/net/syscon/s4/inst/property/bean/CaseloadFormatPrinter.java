package net.syscon.s4.inst.property.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
@XmlRootElement
public class CaseloadFormatPrinter extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@JsonProperty("createDatetime")
	@NotNull
	private Object createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("defaultPrinterId")
	@NotNull
	@Size(max = 12)
	private String defaultPrinterId;

	@JsonProperty("modifyDatetime")
	private Object modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("printResourceFile")
	@Size(max = 60)
	private String printResourceFile;

	@JsonProperty("printResourceId")
	@Size(max = 12)
	private String printResourceId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("caseloadId")
	@NotNull
	@Size(max = 6)
	private String caseloadId;

	@JsonProperty("printFormatCode")
	@NotNull
	@Size(max = 12)
	private String printFormatCode;

	public CaseloadFormatPrinter() {
		
		//CaseloadFormatPrinter
	}

	/**
	 * @return the createDatetime
	 */
	public Object getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(final Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the defaultPrinterId
	 */
	public String getDefaultPrinterId() {
		return defaultPrinterId;
	}

	/**
	 * @param defaultPrinterId the defaultPrinterId to set
	 */
	public void setDefaultPrinterId(final String defaultPrinterId) {
		this.defaultPrinterId = defaultPrinterId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Object getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the printResourceFile
	 */
	public String getPrintResourceFile() {
		return printResourceFile;
	}

	/**
	 * @param printResourceFile the printResourceFile to set
	 */
	public void setPrintResourceFile(final String printResourceFile) {
		this.printResourceFile = printResourceFile;
	}

	/**
	 * @return the printResourceId
	 */
	public String getPrintResourceId() {
		return printResourceId;
	}

	/**
	 * @param printResourceId the printResourceId to set
	 */
	public void setPrintResourceId(final String printResourceId) {
		this.printResourceId = printResourceId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the printFormatCode
	 */
	public String getPrintFormatCode() {
		return printFormatCode;
	}

	/**
	 * @param printFormatCode the printFormatCode to set
	 */
	public void setPrintFormatCode(final String printFormatCode) {
		this.printFormatCode = printFormatCode;
	}

	
}
