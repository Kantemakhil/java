package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the CASE_PLANS database table.
 * 
 */
public class CasePlans extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("autoAssessModifyDatetime")
	private Date autoAssessModifyDatetime;
	
	@JsonProperty("autoConditionModifyDatetime")
	private Date autoConditionModifyDatetime;

	@JsonProperty("calAgyLocId")
	private String calAgyLocId;

	@JsonProperty("casePlanStatus")
	private String casePlanStatus;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("changes")
	private String changes;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("creationDate")
	private Date creationDate;

	@JsonProperty("creationUser")
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

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

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
	private Date startDate;
	
	@JsonProperty("supervisionLevel")
	private String supervisionLevel;

	@JsonProperty("verifiedFlag")
	private String verifiedFlag;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("casePlanId")
	private long casePlanId;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("instStaffName")
	private String instStaffName;
	
	@JsonProperty("commStaffName")
	private String commStaffName;
	
	@JsonProperty("offenderName")
	private String offenderName;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("lastPcoDate")
	private Date lastPcoDate;
	
	@JsonProperty("cpOwnerName")
	private String cpOwnerName;


	public CasePlans() {
	}

	public Date getAutoAssessModifyDatetime() {
		return this.autoAssessModifyDatetime;
	}

	public void setAutoAssessModifyDatetime(Date autoAssessModifyDatetime) {
		this.autoAssessModifyDatetime = autoAssessModifyDatetime;
	}

	public Date getAutoConditionModifyDatetime() {
		return this.autoConditionModifyDatetime;
	}

	public void setAutoConditionModifyDatetime(Date autoConditionModifyDatetime) {
		this.autoConditionModifyDatetime = autoConditionModifyDatetime;
	}

	public String getCalAgyLocId() {
		return this.calAgyLocId;
	}

	public void setCalAgyLocId(String calAgyLocId) {
		this.calAgyLocId = calAgyLocId;
	}

	public String getCasePlanStatus() {
		return this.casePlanStatus;
	}

	public void setCasePlanStatus(String casePlanStatus) {
		this.casePlanStatus = casePlanStatus;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getChanges() {
		return this.changes;
	}

	public void setChanges(String changes) {
		this.changes = changes;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return this.creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getInstCalAgyLocId() {
		return this.instCalAgyLocId;
	}

	public void setInstCalAgyLocId(String instCalAgyLocId) {
		this.instCalAgyLocId = instCalAgyLocId;
	}

	public Date getInstFromDate() {
		return this.instFromDate;
	}

	public void setInstFromDate(Date instFromDate) {
		this.instFromDate = instFromDate;
	}

	public String getInstPosition() {
		return this.instPosition;
	}

	public void setInstPosition(String instPosition) {
		this.instPosition = instPosition;
	}

	public String getInstRole() {
		return this.instRole;
	}

	public void setInstRole(String instRole) {
		this.instRole = instRole;
	}

	public java.math.BigDecimal getInstSacStaffId() {
		return this.instSacStaffId;
	}

	public void setInstSacStaffId(java.math.BigDecimal instSacStaffId) {
		this.instSacStaffId = instSacStaffId;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getNextReviewDate() {
		return this.nextReviewDate;
	}

	public void setNextReviewDate(Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public java.math.BigDecimal getSacStaffId() {
		return this.sacStaffId;
	}

	public void setSacStaffId(java.math.BigDecimal sacStaffId) {
		this.sacStaffId = sacStaffId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSupervisionLevel() {
		return this.supervisionLevel;
	}

	public void setSupervisionLevel(String supervisionLevel) {
		this.supervisionLevel = supervisionLevel;
	}

	public String getVerifiedFlag() {
		return this.verifiedFlag;
	}

	public void setVerifiedFlag(String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	public long getOffenderBookId() {
		return this.offenderBookId;
	}
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public long getCasePlanId() {
		return this.casePlanId;
	}
	public void setCasePlanId(long casePlanId) {
		this.casePlanId = casePlanId;
	}

		public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.offenderBookId ^ (this.offenderBookId >>> 32)));
		hash = hash * prime + ((int) (this.casePlanId ^ (this.casePlanId >>> 32)));
		
		return hash;
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
		public void setAgyLocId(String agyLocId) {
			this.agyLocId = agyLocId;
		}

		/**
		 * @return the instStaffName
		 */
		public String getInstStaffName() {
			return instStaffName;
		}

		/**
		 * @param instStaffName the instStaffName to set
		 */
		public void setInstStaffName(String instStaffName) {
			this.instStaffName = instStaffName;
		}

		/**
		 * @return the commStaffName
		 */
		public String getCommStaffName() {
			return commStaffName;
		}

		/**
		 * @param commStaffName the commStaffName to set
		 */
		public void setCommStaffName(String commStaffName) {
			this.commStaffName = commStaffName;
		}

		/**
		 * @return the offenderName
		 */
		public String getOffenderName() {
			return offenderName;
		}

		/**
		 * @param offenderName the offenderName to set
		 */
		public void setOffenderName(String offenderName) {
			this.offenderName = offenderName;
		}

		/**
		 * @return the offenderIdDisplay
		 */
		public String getOffenderIdDisplay() {
			return offenderIdDisplay;
		}

		/**
		 * @param offenderIdDisplay the offenderIdDisplay to set
		 */
		public void setOffenderIdDisplay(String offenderIdDisplay) {
			this.offenderIdDisplay = offenderIdDisplay;
		}

		public String getCpOwnerName() {
			return cpOwnerName;
		}

		public void setCpOwnerName(String cpOwnerName) {
			this.cpOwnerName = cpOwnerName;
		}
}
