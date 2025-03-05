package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VSummaryCasePlans implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal casePlanId;

	private String caseworkType;

	private String caseworkTypeDesc;

	private Date endDate;

	private String issue;

	private String notes;

	private BigDecimal offenderBookId;

	private String programDesc;

	private Date startDate;

	private String type;

	public VSummaryCasePlans() {
		//VSummaryCasePlan
	}

	public BigDecimal getCasePlanId() {
		return this.casePlanId;
	}

	public void setCasePlanId(BigDecimal casePlanId) {
		this.casePlanId = casePlanId;
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

	public String getIssue() {
		return this.issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getProgramDesc() {
		return this.programDesc;
	}

	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
