package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CasePlans extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("autoAssessModifyDatetime")
	private Date autoAssessModifyDatetime;

	@JsonProperty("autoConditionModifyDatetime")
	private Date autoConditionModifyDatetime;

	@JsonProperty("calAgyLocId")
	private String calAgyLocId;

	@JsonProperty("agyLocId")
	@NotNull
	@Size(max = 6)
	private String agyLocId;

	@JsonProperty("casePlanStatus")
	@NotNull
	@Size(max = 12)
	private String casePlanStatus;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("changes")
	private String changes;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("creationDate")
	@NotNull
	private Date creationDate;

	@JsonProperty("creationUser")
	@NotNull
	@Size(max = 32)
	private String creationUser;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("fromDate")
	private Date fromDate;

	@JsonProperty("instCalAgyLocId")
	private String instCalAgyLocId;

	@JsonProperty("instFromDate")
	private Date instFromDate;

	@JsonProperty("instPosition")
	private String instPosition;

	@JsonProperty("instRole")
	private String instRole;

	@JsonProperty("instSacStaffId")
	private java.math.BigDecimal instSacStaffId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("nextReviewDate")
	private Date nextReviewDate;

	@JsonProperty("position")
	private String position;

	@JsonProperty("role")
	private String role;

	@JsonProperty("sacStaffId")
	private java.math.BigDecimal sacStaffId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("startDate")
	@NotNull
	private Date startDate;

	@JsonProperty("supervisionLevel")
	private String supervisionLevel;

	@JsonProperty("verifiedFlag")
	private String verifiedFlag;

	@JsonProperty("offenderBookId")
	@NotNull
	private Long offenderBookId;

	@JsonProperty("casePlanId")
	@NotNull
	private Long casePlanId;

	@JsonProperty("userId")
	@Size(max = 32)
	private String userId;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("officer")
	private String officer;
	
	@JsonProperty("instCalAgyLocIdDesc")
	private String instCalAgyLocIdDesc;
	
	@JsonProperty("supervisionLevelDesc")
	private String supervisionLevelDesc;
	
	@JsonProperty("casePlanStatusDesc")
	private String casePlanStatusDesc;
	
	@JsonProperty("calAgyLocIdDesc")
	private String calAgyLocIdDesc;
	
	@JsonProperty("sacStaffIdDesc")
	private String sacStaffIdDesc;
	
	@JsonProperty("reqReview")
	private String reqReview;
	
	@JsonProperty("workFlowId")
	private long workFlowId;
	
	@JsonProperty("instActiveFlag")
	private String instActiveFlag;
	
	
	@JsonProperty("sacStaffId1")
	private Long sacStaffId1;
	
	@JsonProperty("list")
	private  List<CasePlanStaff> list;
	
	
	@JsonProperty("custodialLocation")
	private String custodialLocation;
	
	
	@JsonProperty("casePlanUserId")
	private String casePlanUserId;
	
	@JsonProperty("reviewFlag")
	private String reviewFlag;
	
	@JsonProperty("workFlowStatus")
	private String workFlowStatus;
	
	@JsonProperty("communityStaffName")
	private String communityStaffName;
	
	@JsonProperty("cnOfficer")
	private String cnOfficer;
	
	@JsonProperty("staffList")
	private String staffList;
	
	@JsonProperty("staffIdList")
	private List<Integer> staffIdList;
	
	@JsonProperty("casePlanStaff")
	private String casePlanStaff;
	
	public String getCnOfficer() {
		return cnOfficer;
	}

	public void setCnOfficer(String cnOfficer) {
		this.cnOfficer = cnOfficer;
	}

	public String getCommunityStaffName() {
		return communityStaffName;
	}

	public void setCommunityStaffName(String communityStaffName) {
		this.communityStaffName = communityStaffName;
	}

	public String getWorkFlowStatus() {
		return workFlowStatus;
	}

	public void setWorkFlowStatus(String workFlowStatus) {
		this.workFlowStatus = workFlowStatus;
	}

	public String getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public String getCustodialLocation() {
		return custodialLocation;
	}

	public void setCustodialLocation(String custodialLocation) {
		this.custodialLocation = custodialLocation;
	}

	

	public List<CasePlanStaff> getList() {
		return list;
	}

	public void setList(List<CasePlanStaff> list) {
		this.list = list;
	}

	public Long getSacStaffId1() {
		return sacStaffId1;
	}

	public void setSacStaffId1(Long sacStaffId1) {
		this.sacStaffId1 = sacStaffId1;
	}

	/**
	 * Creates new CasePlans class Object
	 */
//	public CasePlans() {
//		// CasePlans
//	}

	/**
	 * @return the autoAssessModifyDatetime
	 */
	public Date getAutoAssessModifyDatetime() {
		return autoAssessModifyDatetime;
	}

	/**
	 * @param autoAssessModifyDatetime
	 *            the autoAssessModifyDatetime to set
	 */
	public void setAutoAssessModifyDatetime(Date autoAssessModifyDatetime) {
		this.autoAssessModifyDatetime = autoAssessModifyDatetime;
	}

	/**
	 * @return the autoConditionModifyDatetime
	 */
	public Date getAutoConditionModifyDatetime() {
		return autoConditionModifyDatetime;
	}

	/**
	 * @param autoConditionModifyDatetime
	 *            the autoConditionModifyDatetime to set
	 */
	public void setAutoConditionModifyDatetime(Date autoConditionModifyDatetime) {
		this.autoConditionModifyDatetime = autoConditionModifyDatetime;
	}

	/**
	 * @return the calAgyLocId
	 */
	public String getCalAgyLocId() {
		return calAgyLocId;
	}

	/**
	 * @param calAgyLocId
	 *            the calAgyLocId to set
	 */
	public void setCalAgyLocId(String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the casePlanStatus
	 */
	public String getCasePlanStatus() {
		return casePlanStatus;
	}

	/**
	 * @param casePlanStatus
	 *            the casePlanStatus to set
	 */
	public void setCasePlanStatus(String casePlanStatus) {
		this.casePlanStatus = casePlanStatus;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType
	 *            the caseloadType to set
	 */
	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the changes
	 */
	public String getChanges() {
		return changes;
	}

	/**
	 * @param changes
	 *            the changes to set
	 */
	public void setChanges(String changes) {
		this.changes = changes;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the creationUser
	 */
	public String getCreationUser() {
		return creationUser;
	}

	/**
	 * @param creationUser
	 *            the creationUser to set
	 */
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the instCalAgyLocId
	 */
	public String getInstCalAgyLocId() {
		return instCalAgyLocId;
	}

	/**
	 * @param instCalAgyLocId
	 *            the instCalAgyLocId to set
	 */
	public void setInstCalAgyLocId(String instCalAgyLocId) {
		this.instCalAgyLocId = instCalAgyLocId;
	}

	/**
	 * @return the instFromDate
	 */
	public Date getInstFromDate() {
		return instFromDate;
	}

	/**
	 * @param instFromDate
	 *            the instFromDate to set
	 */
	public void setInstFromDate(Date instFromDate) {
		this.instFromDate = instFromDate;
	}

	/**
	 * @return the instPosition
	 */
	public String getInstPosition() {
		return instPosition;
	}

	/**
	 * @param instPosition
	 *            the instPosition to set
	 */
	public void setInstPosition(String instPosition) {
		this.instPosition = instPosition;
	}

	/**
	 * @return the instRole
	 */
	public String getInstRole() {
		return instRole;
	}

	/**
	 * @param instRole
	 *            the instRole to set
	 */
	public void setInstRole(String instRole) {
		this.instRole = instRole;
	}

	/**
	 * @return the instSacStaffId
	 */
	public java.math.BigDecimal getInstSacStaffId() {
		return instSacStaffId;
	}

	/**
	 * @param instSacStaffId
	 *            the instSacStaffId to set
	 */
	public void setInstSacStaffId(java.math.BigDecimal instSacStaffId) {
		this.instSacStaffId = instSacStaffId;
	}

	/**
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the nextReviewDate
	 */
	public Date getNextReviewDate() {
		return nextReviewDate;
	}

	/**
	 * @param nextReviewDate
	 *            the nextReviewDate to set
	 */
	public void setNextReviewDate(Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the sacStaffId
	 */
	public java.math.BigDecimal getSacStaffId() {
		return sacStaffId;
	}

	/**
	 * @param sacStaffId
	 *            the sacStaffId to set
	 */
	public void setSacStaffId(java.math.BigDecimal sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
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
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the supervisionLevel
	 */
	public String getSupervisionLevel() {
		return supervisionLevel;
	}

	/**
	 * @param supervisionLevel
	 *            the supervisionLevel to set
	 */
	public void setSupervisionLevel(String supervisionLevel) {
		this.supervisionLevel = supervisionLevel;
	}

	/**
	 * @return the verifiedFlag
	 */
	public String getVerifiedFlag() {
		return verifiedFlag;
	}

	/**
	 * @param verifiedFlag
	 *            the verifiedFlag to set
	 */
	public void setVerifiedFlag(String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the casePlanId
	 */
	public Long getCasePlanId() {
		return casePlanId;
	}

	/**
	 * @param casePlanId
	 *            the casePlanId to set
	 */
	public void setCasePlanId(Long casePlanId) {
		this.casePlanId = casePlanId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the officer
	 */
	public String getOfficer() {
		return officer;
	}

	/**
	 * @param officer
	 *            the officer to set
	 */
	public void setOfficer(String officer) {
		this.officer = officer;
	}

	/**
	 * @return the instCalAgyLocIdDesc
	 */
	public String getInstCalAgyLocIdDesc() {
		return instCalAgyLocIdDesc;
	}

	/**
	 * @param instCalAgyLocIdDesc the instCalAgyLocIdDesc to set
	 */
	public void setInstCalAgyLocIdDesc(String instCalAgyLocIdDesc) {
		this.instCalAgyLocIdDesc = instCalAgyLocIdDesc;
	}

	/**
	 * @return the supervisionLevelDesc
	 */
	public String getSupervisionLevelDesc() {
		return supervisionLevelDesc;
	}

	/**
	 * @param supervisionLevelDesc the supervisionLevelDesc to set
	 */
	public void setSupervisionLevelDesc(String supervisionLevelDesc) {
		this.supervisionLevelDesc = supervisionLevelDesc;
	}

	/**
	 * @return the casePlanStatusDesc
	 */
	public String getCasePlanStatusDesc() {
		return casePlanStatusDesc;
	}

	/**
	 * @param casePlanStatusDesc the casePlanStatusDesc to set
	 */
	public void setCasePlanStatusDesc(String casePlanStatusDesc) {
		this.casePlanStatusDesc = casePlanStatusDesc;
	}

	/**
	 * @return the calAgyLocIdDesc
	 */
	public String getCalAgyLocIdDesc() {
		return calAgyLocIdDesc;
	}

	/**
	 * @param calAgyLocIdDesc the calAgyLocIdDesc to set
	 */
	public void setCalAgyLocIdDesc(String calAgyLocIdDesc) {
		this.calAgyLocIdDesc = calAgyLocIdDesc;
	}

	/**
	 * @return the sacStaffIdDesc
	 */
	public String getSacStaffIdDesc() {
		return sacStaffIdDesc;
	}

	/**
	 * @param sacStaffIdDesc the sacStaffIdDesc to set
	 */
	public void setSacStaffIdDesc(String sacStaffIdDesc) {
		this.sacStaffIdDesc = sacStaffIdDesc;
	}

	/**
	 * @return the reqReview
	 */
	public String getReqReview() {
		return reqReview;
	}

	/**
	 * @param reqReview the reqReview to set
	 */
	public void setReqReview(String reqReview) {
		this.reqReview = reqReview;
	}

	/**
	 * @return the workFlowId
	 */
	public long getWorkFlowId() {
		return workFlowId;
	}

	/**
	 * @param workFlowId the workFlowId to set
	 */
	public void setWorkFlowId(long workFlowId) {
		this.workFlowId = workFlowId;
	}

	public String getInstActiveFlag() {
		return instActiveFlag;
	}

	public void setInstActiveFlag(String instActiveFlag) {
		this.instActiveFlag = instActiveFlag;
	}

	public String getCasePlanUserId() {
		return casePlanUserId;
	}

	public void setCasePlanUserId(String casePlanUserId) {
		this.casePlanUserId = casePlanUserId;
	}

	public String getStaffList() {
		return staffList;
	}

	public void setStaffList(String staffList) {
		this.staffList = staffList;
	}

	public List<Integer> getStaffIdList() {
		return staffIdList;
	}

	public void setStaffIdList(List<Integer> staffIdList) {
		this.staffIdList = staffIdList;
	}

	public String getCasePlanStaff() {
		return casePlanStaff;
	}

	public void setCasePlanStaff(String casePlanStaff) {
		this.casePlanStaff = casePlanStaff;
	}
	
}
