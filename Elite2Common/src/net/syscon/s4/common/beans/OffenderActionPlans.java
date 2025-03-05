package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderActionPlans extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal offActionPlanId;
	private BigDecimal offCrimNeedId;
	private BigDecimal offCaseCondId;
	private String caseworkType;
	private BigDecimal programId;
	private String notes;
	private Date startDate;
	private Date endDate;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;
	
	

	public BigDecimal getOffActionPlanId() {
		return offActionPlanId;
	}

	public void setOffActionPlanId(BigDecimal offActionPlanId) {
		this.offActionPlanId = offActionPlanId;
	}

	public BigDecimal getOffCrimNeedId() {
		return offCrimNeedId;
	}

	public void setOffCrimNeedId(BigDecimal offCrimNeedId) {
		this.offCrimNeedId = offCrimNeedId;
	}

	public BigDecimal getOffCaseCondId() {
		return offCaseCondId;
	}

	public void setOffCaseCondId(BigDecimal offCaseCondId) {
		this.offCaseCondId = offCaseCondId;
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

	public String getCaseworkType() {
		return caseworkType;
	}

	public void setCaseworkType(String caseworkType) {
		this.caseworkType = caseworkType;
	}

	public BigDecimal getProgramId() {
		return programId;
	}

	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
