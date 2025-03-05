package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseloadAccountCodesJn extends BaseModel implements Serializable {

	String caseloadId;
	BigDecimal accountCode;
	BigDecimal listSeq;
	Date modifyDate; 
	String modifyUserId;
	String createUserId; 
	Date createDatetime;
	Date modifyDatetime;
	public String getCaseloadId() {
		return caseloadId;
	}
	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}
	public BigDecimal getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(BigDecimal accountCode) {
		this.accountCode = accountCode;
	}
	public BigDecimal getListSeq() {
		return listSeq;
	}
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
	
	
	

}
