package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class SentenceCalculation extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("staffName")
	private String staffName;
	
	@JsonProperty("calcCode")
	private String calculationReason;
		
	@JsonProperty("comment")
	private String comment;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("createUserId")
	private String createUserId;

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getCalculationReason() {
		return calculationReason;
	}

	public void setCalculationReason(String calculationReason) {
		this.calculationReason = calculationReason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	
	
	
}
