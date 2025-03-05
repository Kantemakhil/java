package net.syscon.s4.cm.teamsworkflow.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the TEAM_MEMBERS database table.
 * 
 */
public class TeamMembers extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private long teamMemberId;
	
	private Integer teamId;

	private String activeFlag;

	private String agyLocId;

	private Date createDateTime;

	private String createUserId;

	private Date expiryDate;

	private Date locRoleFromDate;

	private Date modifyDateTime;

	private String modifyUserId;

	private BigDecimal noOfTasks;

	private String position;

	private String role;

	private String sealFlag;

	private BigDecimal staffId;
	
	private String lastName;
	
	private String firstName;
	
	private String gender;
	
	private String teamName;
	 private String code;
	 private String description;
	 private BigDecimal hoursPerWeek;	 
	 private String scheduleType;
	 private int returnValue;
	 private Date createDatetime;
	 private Date modifyDatetime;
	 @JsonProperty("checkFlag")
	 private String checkFlag;
	 @JsonProperty("listSeq")
	private Integer listSeq;
	 @JsonProperty("omTeam")
	 private String omTeam;
	 @JsonProperty("count")
	 private Integer count;
	 
	 
	 
		public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

		@JsonProperty("offenderBookId")
		private Long offenderBookId;

		@JsonProperty("intakeAgyLocId")
		private String intakeAgyLocId;

		@JsonProperty("offenders")
		private Long offenders;

		private String staffName;
		
		private boolean teamFlag;
		
		private boolean subType;
		
		private boolean canDisplay;
		
		
		private String userId;
		
		public boolean isSubType() {
			return subType;
		}

		public void setSubType(boolean subType) {
			this.subType = subType;
		}

		public Long getOffenderBookId() {
			return offenderBookId;
		}

		public void setOffenderBookId(Long offenderBookId) {
			this.offenderBookId = offenderBookId;
		}

		public String getIntakeAgyLocId() {
			return intakeAgyLocId;
		}

		public void setIntakeAgyLocId(String intakeAgyLocId) {
			this.intakeAgyLocId = intakeAgyLocId;
		}

		public Long getOffenders() {
			return offenders;
		}

		public void setOffenders(Long offenders) {
			this.offenders = offenders;
		}

		public boolean isTeamFlag() {
			return teamFlag;
		}

		public void setTeamFlag(boolean teamFlag) {
			this.teamFlag = teamFlag;
		}

		public String getStaffName() {
			return staffName;
		}

		public void setStaffName(String staffName) {
			this.staffName = staffName;
		}

		public String getAgencyLocationType() {
			return agencyLocationType;
		}

		public void setAgencyLocationType(String agencyLocationType) {
			this.agencyLocationType = agencyLocationType;
		}

		private String agencyLocationType;
	 
	 
	 
	 
	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

	public BigDecimal getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(BigDecimal hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TeamMembers() {
		// TeamMembers
	}

	public long getTeamMemberId() {
		return this.teamMemberId;
	}

	public void setTeamMemberId(long teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDatetime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getLocRoleFromDate() {
		return this.locRoleFromDate;
	}

	public void setLocRoleFromDate(Date locRoleFromDate) {
		this.locRoleFromDate = locRoleFromDate;
	}

	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	public void setModifyDatetime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getNoOfTasks() {
		return this.noOfTasks;
	}

	public void setNoOfTasks(BigDecimal noOfTasks) {
		this.noOfTasks = noOfTasks;
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

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public Integer getListSeq() {
		return listSeq;
	}

	public void setListSeq(Integer listSeq) {
		this.listSeq = listSeq;
	}

	public boolean isCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public String getOmTeam() {
		return omTeam;
	}

	public void setOmTeam(String omTeam) {
		this.omTeam = omTeam;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
