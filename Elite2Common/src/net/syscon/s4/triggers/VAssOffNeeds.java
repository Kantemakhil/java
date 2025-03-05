package net.syscon.s4.triggers;

import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VAssOffNeeds extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long assessmentId;
	private Long offAssNeedId;
	private String assessedNeedCode;
	private String objective;
	private Date targetDate;
	private String activeFlag;
	private Date expiryDate;
	private String assOffCodeDesc;

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Long getOffAssNeedId() {
		return offAssNeedId;
	}

	public void setOffAssNeedId(Long offAssNeedId) {
		this.offAssNeedId = offAssNeedId;
	}

	public String getAssessedNeedCode() {
		return assessedNeedCode;
	}

	public void setAssessedNeedCode(String assessedNeedCode) {
		this.assessedNeedCode = assessedNeedCode;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getAssOffCodeDesc() {
		return assOffCodeDesc;
	}

	public void setAssOffCodeDesc(String assOffCodeDesc) {
		this.assOffCodeDesc = assOffCodeDesc;
	}

}
