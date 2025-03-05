package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VOmTeamMembers {
	
	@JsonProperty ("calAgyLocId")
	private String calAgyLocId;

	@JsonProperty ("role")
	private String role;
	
	@JsonProperty ("scheduleTypeDesc")
	private String scheduleTypeDesc;
	
	@JsonProperty ("scheduleType")
	private String scheduleType;
	
	@JsonProperty ("position")
	private String position;
	
	@JsonProperty ("fromDate")
	private Date fromDate;
	
	@JsonProperty ("sexCode")
	private String sexCode;
	
	@JsonProperty ("staffName")
	private String staffName;
	
	@JsonProperty ("firstName")
	private String firstName;
	
	@JsonProperty ("lastName")
	private String lastName;
	
	@JsonProperty ("staffId")
	private Long staffId;
	
	@JsonProperty ("nbtNoOffender")
	private Long nbtNoOffender;
//	====
			
			
	@JsonProperty ("nbtTeamId")
	private Integer nbtTeamId;
	
	@JsonProperty ("teamReqFlag")
	private String teamReqFlag;
	
	@JsonProperty ("nbtSkillSubType2")
	private String nbtSkillSubType2;
	
	@JsonProperty ("vSupervisionLevel")
	private String vSupervisionLevel;
	
	
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("vNextReviewDate")
	private Date vNextReviewDate;
	
	@JsonProperty("casePlanId")
	private Long casePlanId;
	
	@JsonProperty("remainingWlUnits")
	private BigDecimal remainingWlUnits;
	
	@JsonProperty("staffLocRoleId")
	private Long staffLocRoleId;
	
	
	
	
	

	/**
	 * @return the teamDesc
	 */
	public String getTeamDesc() {
		return teamDesc;
	}

	/**
	 * @param teamDesc the teamDesc to set
	 */
	public void setTeamDesc(final String teamDesc) {
		this.teamDesc = teamDesc;
	}

	@JsonProperty ("nbtStaffId")
	private String nbtStaffId;
	
	@JsonProperty ("teamDesc")
	private String teamDesc;
	
	@JsonProperty ("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty ("verifiedFlag")
	private String verifiedFlag;
	
	@JsonProperty ("offenderId")
	private Long offenderId;
	
	@JsonProperty ("offenderFileSeq")
	private Integer offenderFileSeq;
	
	@JsonProperty ("asstraId")
	private Long asstraId;
	
	@JsonProperty ("agyLocId")
	private String agyLocId;
	
	@JsonProperty ("vFromDate")
	private Date vFromDate;
	
	
	@JsonProperty ("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty ("vInstPosition")
	private String vInstPosition;
	
	@JsonProperty ("vInstRole")
	private String vInstRole;
	
	@JsonProperty ("vInstSacStaffId")
	private BigDecimal vInstSacStaffId;
	
	@JsonProperty ("vInstCalAgyLocId")
	private String vInstCalAgyLocId;
	
	@JsonProperty ("vAutoAssessModify")
	private Date vAutoAssessModify;
	
	
	@JsonProperty ("casePlanStatus")
	private String casePlanStatus;
	
	@JsonProperty ("caseLoadId")
	private String caseLoadId;
	
	@JsonProperty ("vOffassId")
	private Long vOffassId;
	
	@JsonProperty ("subTypeFlag")
	private Boolean subTypeFlag;
	
	@JsonProperty ("omTeamFlag")
	private Boolean omTeamFlag;
	
	@JsonProperty ("sealFlag")
	private String sealFlag;
	
	@JsonProperty ("count")
	private Integer count;
	
	@JsonProperty ("modifyUserId")
	private String modifyUserId;
	
	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the calAgyLocId
	 */
	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	/**
	 * @param calAgyLocId the calAgyLocId to set
	 */
	public void setCalAgyLocId(final String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(final String role) {
		this.role = role;
	}

	/**
	 * @return the scheduleTypeDesc
	 */
	public String getScheduleTypeDesc() {
		return scheduleTypeDesc;
	}

	/**
	 * @param scheduleTypeDesc the scheduleTypeDesc to set
	 */
	public void setScheduleTypeDesc(final String scheduleTypeDesc) {
		this.scheduleTypeDesc = scheduleTypeDesc;
	}

	/**
	 * @return the scheduleType
	 */
	public String getScheduleType() {
		return scheduleType;
	}

	/**
	 * @param scheduleType the scheduleType to set
	 */
	public void setScheduleType(final String scheduleType) {
		this.scheduleType = scheduleType;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(final String position) {
		this.position = position;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(final Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the sexCode
	 */
	public String getSexCode() {
		return sexCode;
	}

	/**
	 * @param sexCode the sexCode to set
	 */
	public void setSexCode(final String sexCode) {
		this.sexCode = sexCode;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(final String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the staffId
	 */
	public Long getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(final Long staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the nbtNoOffender
	 */
	public Long getNbtNoOffender() {
		return nbtNoOffender;
	}

	/**
	 * @param nbtNoOffender the nbtNoOffender to set
	 */
	public void setNbtNoOffender(final Long nbtNoOffender) {
		this.nbtNoOffender = nbtNoOffender;
	}


	/**
	 * @return the teamReqFlag
	 */
	public String getTeamReqFlag() {
		return teamReqFlag;
	}

	/**
	 * @param teamReqFlag the teamReqFlag to set
	 */
	public void setTeamReqFlag(final String teamReqFlag) {
		this.teamReqFlag = teamReqFlag;
	}


	/**
	 * @return the nbtStaffId
	 */
	public String getNbtStaffId() {
		return nbtStaffId;
	}

	/**
	 * @param nbtStaffId the nbtStaffId to set
	 */
	public void setNbtStaffId(final String nbtStaffId) {
		this.nbtStaffId = nbtStaffId;
	}

	/**
	 * @return the nbtTeamId
	 */
	public Integer getNbtTeamId() {
		return nbtTeamId;
	}

	/**
	 * @param nbtTeamId the nbtTeamId to set
	 */
	public void setNbtTeamId(final Integer nbtTeamId) {
		this.nbtTeamId = nbtTeamId;
	}

	/**
	 * @return the nbtSkillSubType2
	 */
	public String getNbtSkillSubType2() {
		return nbtSkillSubType2;
	}

	/**
	 * @param nbtSkillSubType2 the nbtSkillSubType2 to set
	 */
	public void setNbtSkillSubType2(final String nbtSkillSubType2) {
		this.nbtSkillSubType2 = nbtSkillSubType2;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the vSupervisionLevel
	 */
	public String getvSupervisionLevel() {
		return vSupervisionLevel;
	}

	/**
	 * @param vSupervisionLevel the vSupervisionLevel to set
	 */
	public void setvSupervisionLevel(final String vSupervisionLevel) {
		this.vSupervisionLevel = vSupervisionLevel;
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the verifiedFlag
	 */
	public String getVerifiedFlag() {
		return verifiedFlag;
	}

	/**
	 * @param verifiedFlag the verifiedFlag to set
	 */
	public void setVerifiedFlag(final String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId the offenderId to set
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the offenderFileSeq
	 */
	public Integer getOffenderFileSeq() {
		return offenderFileSeq;
	}

	/**
	 * @param offenderFileSeq the offenderFileSeq to set
	 */
	public void setOffenderFileSeq(final Integer offenderFileSeq) {
		this.offenderFileSeq = offenderFileSeq;
	}

	/**
	 * @return the asstraId
	 */
	public Long getAsstraId() {
		return asstraId;
	}

	/**
	 * @param asstraId the asstraId to set
	 */
	public void setAsstraId(final Long asstraId) {
		this.asstraId = asstraId;
	}

	/**
	 * @return the vNextReviewDate
	 */
	public Date getvNextReviewDate() {
		return vNextReviewDate;
	}

	/**
	 * @param vNextReviewDate the vNextReviewDate to set
	 */
	public void setvNextReviewDate(final Date vNextReviewDate) {
		this.vNextReviewDate = vNextReviewDate;
	}

	/**
	 * @return the casePlanId
	 */
	public Long getCasePlanId() {
		return casePlanId;
	}

	/**
	 * @param casePlanId the casePlanId to set
	 */
	public void setCasePlanId(final Long casePlanId) {
		this.casePlanId = casePlanId;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the vFromDate
	 */
	public Date getvFromDate() {
		return vFromDate;
	}

	/**
	 * @param vFromDate the vFromDate to set
	 */
	public void setvFromDate(final Date vFromDate) {
		this.vFromDate = vFromDate;
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
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the vInstPosition
	 */
	public String getvInstPosition() {
		return vInstPosition;
	}

	/**
	 * @param vInstPosition the vInstPosition to set
	 */
	public void setvInstPosition(final String vInstPosition) {
		this.vInstPosition = vInstPosition;
	}

	/**
	 * @return the vInstRole
	 */
	public String getvInstRole() {
		return vInstRole;
	}

	/**
	 * @param vInstRole the vInstRole to set
	 */
	public void setvInstRole(final String vInstRole) {
		this.vInstRole = vInstRole;
	}

	/**
	 * @return the vInstSacStaffId
	 */
	public BigDecimal getvInstSacStaffId() {
		return vInstSacStaffId;
	}

	/**
	 * @param vInstSacStaffId the vInstSacStaffId to set
	 */
	public void setvInstSacStaffId(final BigDecimal vInstSacStaffId) {
		this.vInstSacStaffId = vInstSacStaffId;
	}

	/**
	 * @return the vInstCalAgyLocId
	 */
	public String getvInstCalAgyLocId() {
		return vInstCalAgyLocId;
	}

	/**
	 * @param vInstCalAgyLocId the vInstCalAgyLocId to set
	 */
	public void setvInstCalAgyLocId(final String vInstCalAgyLocId) {
		this.vInstCalAgyLocId = vInstCalAgyLocId;
	}

	/**
	 * @return the vAutoAssessModify
	 */
	public Date getvAutoAssessModify() {
		return vAutoAssessModify;
	}

	/**
	 * @param vAutoAssessModify the vAutoAssessModify to set
	 */
	public void setvAutoAssessModify(final Date vAutoAssessModify) {
		this.vAutoAssessModify = vAutoAssessModify;
	}

	/**
	 * @return the casePlanStatus
	 */
	public String getCasePlanStatus() {
		return casePlanStatus;
	}

	/**
	 * @param casePlanStatus the casePlanStatus to set
	 */
	public void setCasePlanStatus(final String casePlanStatus) {
		this.casePlanStatus = casePlanStatus;
	}

	/**
	 * @return the caseLoadId
	 */
	public String getCaseLoadId() {
		return caseLoadId;
	}

	/**
	 * @param caseLoadId the caseLoadId to set
	 */
	public void setCaseLoadId(final String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	/**
	 * @return the vOffassId
	 */
	public Long getvOffassId() {
		return vOffassId;
	}

	/**
	 * @param vOffassId the vOffassId to set
	 */
	public void setvOffassId(final Long vOffassId) {
		this.vOffassId = vOffassId;
	}

	/**
	 * @return the subTypeFlag
	 */
	public Boolean getSubTypeFlag() {
		return subTypeFlag;
	}

	/**
	 * @param subTypeFlag the subTypeFlag to set
	 */
	public void setSubTypeFlag(final Boolean subTypeFlag) {
		this.subTypeFlag = subTypeFlag;
	}

	/**
	 * @return the omTeamFlag
	 */
	public Boolean getOmTeamFlag() {
		return omTeamFlag;
	}

	/**
	 * @param omTeamFlag the omTeamFlag to set
	 */
	public void setOmTeamFlag(final Boolean omTeamFlag) {
		this.omTeamFlag = omTeamFlag;
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
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(final Integer count) {
		this.count = count;
	}

	public BigDecimal getRemainingWlUnits() {
		return remainingWlUnits;
	}

	public void setRemainingWlUnits(BigDecimal remainingWlUnits) {
		this.remainingWlUnits = remainingWlUnits;
	}

	public Long getStaffLocRoleId() {
		return staffLocRoleId;
	}

	public void setStaffLocRoleId(Long staffLocRoleId) {
		this.staffLocRoleId = staffLocRoleId;
	}
	

}
