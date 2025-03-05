package net.syscon.s4.cf.maintenance.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class FeeAccounts extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("accountCode")
	private Integer accountCode;

	@JsonProperty("feeCode")
	private String feeCode;

	@JsonProperty("listSeq")
	private Integer listSeq;

	@JsonProperty("recordDatetime")
	private Date recordDatetime;

	@JsonProperty("expiryDatetime")
	private Date expiryDatetime;

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

	@JsonProperty("code")
	private String code;

	@JsonProperty("description")
	private String description;

	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	@JsonProperty("feeCodeDesc")
	private String feeCodeDesc ;
	
	@JsonProperty("accountCodeDesc")
	private String accountCodeDesc ;

	public FeeAccounts() {
		// FeeAccounts
	}

	public Integer getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(Integer accountCode) {
		this.accountCode = accountCode;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	public Date getRecordDatetime() {
		return recordDatetime;
	}

	public void setRecordDatetime(Date recordDatetime) {
		this.recordDatetime = recordDatetime;
	}

	public Date getExpiryDatetime() {
		return expiryDatetime;
	}

	public void setExpiryDatetime(Date expiryDatetime) {
		this.expiryDatetime = expiryDatetime;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public String getFeeCodeDesc() {
		return feeCodeDesc;
	}

	public void setFeeCodeDesc(String feeCodeDesc) {
		this.feeCodeDesc = feeCodeDesc;
	}

	public String getAccountCodeDesc() {
		return accountCodeDesc;
	}

	public void setAccountCodeDesc(String accountCodeDesc) {
		this.accountCodeDesc = accountCodeDesc;
	}

}
