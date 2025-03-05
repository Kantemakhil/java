package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class OffApV1 implements Serializable {
	private static final long serialVersionUID = 1L;

	private String caseworkType;

	private String caseworkTypeDesc;

	private Date endDate;

	private String notes;

	private BigDecimal offActionPlanId;

	private BigDecimal offCaseCondId;

	private BigDecimal offCrimNeedId;

	private String prgCategoryDesc;

	private String programCategory;

	private String programDesc;

	private BigDecimal programId;

	private Date startDate;
	
	private String modifyUserId;
	private String createUserId;

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public OffApV1() {
		// OffApV1
	}

	public String getCaseworkType() {
		return this.caseworkType;
	}

	public void setCaseworkType(String caseworkType) {
		this.caseworkType = caseworkType;
	}

	public String getCaseworkTypeDesc() {
		return this.caseworkTypeDesc;
	}

	public void setCaseworkTypeDesc(String caseworkTypeDesc) {
		this.caseworkTypeDesc = caseworkTypeDesc;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getOffActionPlanId() {
		return this.offActionPlanId;
	}

	public void setOffActionPlanId(BigDecimal offActionPlanId) {
		this.offActionPlanId = offActionPlanId;
	}

	public BigDecimal getOffCaseCondId() {
		return this.offCaseCondId;
	}

	public void setOffCaseCondId(BigDecimal offCaseCondId) {
		this.offCaseCondId = offCaseCondId;
	}

	public BigDecimal getOffCrimNeedId() {
		return this.offCrimNeedId;
	}

	public void setOffCrimNeedId(BigDecimal offCrimNeedId) {
		this.offCrimNeedId = offCrimNeedId;
	}

	public String getPrgCategoryDesc() {
		return this.prgCategoryDesc;
	}

	public void setPrgCategoryDesc(String prgCategoryDesc) {
		this.prgCategoryDesc = prgCategoryDesc;
	}

	public String getProgramCategory() {
		return this.programCategory;
	}

	public void setProgramCategory(String programCategory) {
		this.programCategory = programCategory;
	}

	public String getProgramDesc() {
		return this.programDesc;
	}

	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}

	public BigDecimal getProgramId() {
		return this.programId;
	}

	public void setProgramId(BigDecimal programId) {
		this.programId = programId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
