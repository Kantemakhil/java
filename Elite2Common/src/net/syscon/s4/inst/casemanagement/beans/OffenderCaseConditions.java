package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the OFFENDER_CASE_CONDITIONS database table.
 * 
 */
public class OffenderCaseConditions implements Serializable {
	private static final long serialVersionUID = 1L;

	private long offCaseCondId;

	private BigDecimal casePlanId;

	private String categoryType;

	private String commConditionCode;

	private String commConditionType;

	private String conditionStatus;

	private Date createDatetime;

	private String createUserId;

	private Date endDate;

	private BigDecimal length;

	private String lengthUnit;

	private Date modifyDatetime;

	private String modifyUserId;

	private String objective;

	private BigDecimal offenderBookId;

	private BigDecimal offenderSentConditionId;

	private String sealFlag;

	private Date startDate;

	private String description;
	
	private Date latestDatetime;
	
	private String casePlanStatusDesc;
	
	private String staffName;
	
	private String teamName;
	
	private Long teamId;
	
	private Long officerId;

	public OffenderCaseConditions() {
		// OffenderCaseConditions
	}


	/**
	 * @return the offCaseCondId
	 */
	public long getOffCaseCondId() {
		return offCaseCondId;
	}


	/**
	 * @param offCaseCondId the offCaseCondId to set
	 */
	public void setOffCaseCondId(long offCaseCondId) {
		this.offCaseCondId = offCaseCondId;
	}


	/**
	 * @return the casePlanId
	 */
	public BigDecimal getCasePlanId() {
		return casePlanId;
	}


	/**
	 * @param casePlanId the casePlanId to set
	 */
	public void setCasePlanId(BigDecimal casePlanId) {
		this.casePlanId = casePlanId;
	}


	/**
	 * @return the categoryType
	 */
	public String getCategoryType() {
		return categoryType;
	}


	/**
	 * @param categoryType the categoryType to set
	 */
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}


	/**
	 * @return the commConditionCode
	 */
	public String getCommConditionCode() {
		return commConditionCode;
	}


	/**
	 * @param commConditionCode the commConditionCode to set
	 */
	public void setCommConditionCode(String commConditionCode) {
		this.commConditionCode = commConditionCode;
	}


	/**
	 * @return the commConditionType
	 */
	public String getCommConditionType() {
		return commConditionType;
	}


	/**
	 * @param commConditionType the commConditionType to set
	 */
	public void setCommConditionType(String commConditionType) {
		this.commConditionType = commConditionType;
	}


	/**
	 * @return the conditionStatus
	 */
	public String getConditionStatus() {
		return conditionStatus;
	}


	/**
	 * @param conditionStatus the conditionStatus to set
	 */
	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}


	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}


	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
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


	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}


	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	/**
	 * @return the length
	 */
	public BigDecimal getLength() {
		return length;
	}


	/**
	 * @param length the length to set
	 */
	public void setLength(BigDecimal length) {
		this.length = length;
	}


	/**
	 * @return the lengthUnit
	 */
	public String getLengthUnit() {
		return lengthUnit;
	}


	/**
	 * @param lengthUnit the lengthUnit to set
	 */
	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}


	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}


	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
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
	 * @return the objective
	 */
	public String getObjective() {
		return objective;
	}


	/**
	 * @param objective the objective to set
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}


	/**
	 * @return the offenderBookId
	 */
	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}


	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}


	/**
	 * @return the offenderSentConditionId
	 */
	public BigDecimal getOffenderSentConditionId() {
		return offenderSentConditionId;
	}


	/**
	 * @param offenderSentConditionId the offenderSentConditionId to set
	 */
	public void setOffenderSentConditionId(BigDecimal offenderSentConditionId) {
		this.offenderSentConditionId = offenderSentConditionId;
	}


	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}


	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}


	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the latestDatetime
	 */
	public Date getLatestDatetime() {
		return latestDatetime;
	}


	/**
	 * @param latestDatetime the latestDatetime to set
	 */
	public void setLatestDatetime(Date latestDatetime) {
		this.latestDatetime = latestDatetime;
	}


	public String getCasePlanStatusDesc() {
		return casePlanStatusDesc;
	}


	public void setCasePlanStatusDesc(String casePlanStatusDesc) {
		this.casePlanStatusDesc = casePlanStatusDesc;
	}


	public String getStaffName() {
		return staffName;
	}


	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	public Long getTeamId() {
		return teamId;
	}


	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}


	public Long getOfficerId() {
		return officerId;
	}


	public void setOfficerId(Long officerId) {
		this.officerId = officerId;
	}

	

}
