package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;



/**
 * The persistent class for the CASELOAD_FORMAT_PRINTERS database table.
 * 
 */
public class CaseloadFormatPrinters extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date createDatetime;

	private String createUserId;

	private String defaultPrinterId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String printResourceFile;

	private String printResourceId;

	private String sealFlag;
	private int returnValue;
	

	//bi-directional many-to-one association to Caseload
	//private Caseload caseload;
	
	public int getReturnValue() {
		return returnValue;
	}



	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

	private String caseloadId;

	private String printFormatCode;

	public CaseloadFormatPrinters() {
	}

	

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getDefaultPrinterId() {
		return this.defaultPrinterId;
	}

	public void setDefaultPrinterId(String defaultPrinterId) {
		this.defaultPrinterId = defaultPrinterId;
	}

	

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}



	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}



	public Date getModifyDatetime() {
		return modifyDatetime;
	}



	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}



	public String getPrintResourceFile() {
		return this.printResourceFile;
	}

	public void setPrintResourceFile(String printResourceFile) {
		this.printResourceFile = printResourceFile;
	}

	public String getPrintResourceId() {
		return this.printResourceId;
	}

	public void setPrintResourceId(String printResourceId) {
		this.printResourceId = printResourceId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/*public Caseload getCaseload() {
		return this.caseload;
	}

	public void setCaseload(Caseload caseload) {
		this.caseload = caseload;
	}*/
	
	public String getCaseloadId() {
		return this.caseloadId;
	}
	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}
	public String getPrintFormatCode() {
		return this.printFormatCode;
	}
	public void setPrintFormatCode(String printFormatCode) {
		this.printFormatCode = printFormatCode;
	}

	/*public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CaseloadFormatPrinterPK)) {
			return false;
		}
		CaseloadFormatPrinterPK castOther = (CaseloadFormatPrinterPK)other;
		return 
			this.caseloadId.equals(castOther.getCaseloadId())
			&& this.printFormatCode.equals(castOther.getPrintFormatCode());
	}*/

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.caseloadId.hashCode();
		hash = hash * prime + this.printFormatCode.hashCode();
		
		return hash;
	}

}
