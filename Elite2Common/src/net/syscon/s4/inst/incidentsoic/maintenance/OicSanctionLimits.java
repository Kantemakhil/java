package net.syscon.s4.inst.incidentsoic.maintenance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OicSanctionLimits extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	private String oicHearingType;
	private String oicSanctionCode;
	private String description;
	private Integer maxMonth;
	private Integer maxDays;
	private BigDecimal compensationAmount;
	private String sealFlag;
	private Integer listSeq;
	@JsonProperty("rowId")
	private String rowId;
	
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public Integer getListSeq() {
		return listSeq;
	}
	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
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
	
	public String getOicHearingType() {
		return oicHearingType;
	}
	public void setOicHearingType(String oicHearingType) {
		this.oicHearingType = oicHearingType;
	}
	public String getOicSanctionCode() {
		return oicSanctionCode;
	}
	public void setOicSanctionCode(String oicSanctionCode) {
		this.oicSanctionCode = oicSanctionCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getMaxMonth() {
		return maxMonth;
	}
	public void setMaxMonth(Integer maxMonth) {
		this.maxMonth = maxMonth;
	}
	public Integer getMaxDays() {
		return maxDays;
	}
	public void setMaxDays(Integer maxDays) {
		this.maxDays = maxDays;
	}
	public BigDecimal getCompensationAmount() {
		return compensationAmount;
	}
	public void setCompensationAmount(BigDecimal compensationAmount) {
		this.compensationAmount = compensationAmount;
	}
	public String getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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
