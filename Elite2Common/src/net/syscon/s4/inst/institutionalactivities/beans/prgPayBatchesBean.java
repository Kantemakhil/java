package net.syscon.s4.inst.institutionalactivities.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class prgPayBatchesBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("batchId")
	private Integer batchId;

	@JsonProperty("fromDate")
	private Date fromDate;

	@JsonProperty("toDate")
	private Date toDate;

	@JsonProperty("batchPayAmount")
	private BigDecimal batchPayAmount;

	@JsonProperty("batchGeneratedDatetime")
	private Date batchGeneratedDatetime;

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
	
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	
	public prgPayBatchesBean() {
		// prgPayBatchesBean
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public BigDecimal getBatchPayAmount() {
		return batchPayAmount;
	}

	public void setBatchPayAmount(BigDecimal batchPayAmount) {
		this.batchPayAmount = batchPayAmount;
	}

	public Date getBatchGeneratedDatetime() {
		return batchGeneratedDatetime;
	}

	public void setBatchGeneratedDatetime(Date batchGeneratedDatetime) {
		this.batchGeneratedDatetime = batchGeneratedDatetime;
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

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

}
