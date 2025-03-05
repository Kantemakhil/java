package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class KeyDateValueMapping extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String calculationDateColumn;	
	private String calculationDateValue;
	private String overrideDateColumn;	
	private String overrideDateValue;
	private String createUserId;
	private String createDateTime;
	private String calculatedReason;
	private Long offenderBookId;
	private String commentText;
	private Integer staffId;
	
	public String getCalculationDateColumn() {
		return calculationDateColumn;
	}
	public void setCalculationDateColumn(String calculationDateColumn) {
		this.calculationDateColumn = calculationDateColumn;
	}
	public String getCalculationDateValue() {
		return calculationDateValue;
	}
	public void setCalculationDateValue(String calculationDateValue) {
		this.calculationDateValue = calculationDateValue;
	}
	public String getOverrideDateColumn() {
		return overrideDateColumn;
	}
	public void setOverrideDateColumn(String overrideDateColumn) {
		this.overrideDateColumn = overrideDateColumn;
	}
	public String getOverrideDateValue() {
		return overrideDateValue;
	}
	public void setOverrideDateValue(String overrideDateValue) {
		this.overrideDateValue = overrideDateValue;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getCalculatedReason() {
		return calculatedReason;
	}
	public void setCalculatedReason(String calculatedReason) {
		this.calculatedReason = calculatedReason;
	}
	public Long getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	

}
