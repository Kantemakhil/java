package net.syscon.s4.inst.shiftlogs.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffendersShiftLog extends BaseModel implements Serializable {

	@JsonProperty("shiftLogSeq")
	private BigDecimal shiftLogSeq;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("reason")
	private String reason;
	
	@JsonProperty("internalLocationId")
	private Long internalLocationId;
	
	@JsonProperty("offenderFullName")
   private String offenderFullName;
	
	@JsonProperty("listSeq")
	private Integer listSeq;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getOffenderFullName() {
		return offenderFullName;
	}

	public void setOffenderFullName(String offenderFullName) {
		this.offenderFullName = offenderFullName;
	}

	public String getLivingUnitDescription() {
		return livingUnitDescription;
	}

	public void setLivingUnitDescription(String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}

	
	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;
	
	@JsonProperty("offenderIdDisplay")
     private String offenderIdDisplay;

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}


	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	
	

	public BigDecimal getShiftLogSeq() {
		return shiftLogSeq;
	}

	public void setShiftLogSeq(BigDecimal shiftLogSeq) {
		this.shiftLogSeq = shiftLogSeq;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public Long getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}
	
	

}
