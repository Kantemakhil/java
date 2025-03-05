package net.syscon.s4.common.beans;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreation extends BaseModel {
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("passWord")
	private String passWord;
	
	@JsonProperty("defaultTableSpace")
	private String defaultTableSpace;
	
	@JsonProperty("tempTableSpace")
	private String tempTableSpace;
	
	@JsonProperty("lastName")
	@NotNull
	@Size(max = 35)
	private String lastName;

	@JsonProperty("firstName")
	@NotNull
	@Size(max = 35)
	private String firstName;
	
	@JsonProperty("personnelType")
	@Size(max = 12)
	private String personnelType;
	
	@JsonProperty("status")
	@Size(max = 12)
	private String status;
	
	
	@JsonProperty("grantUserName")
	private String grantUserName;
	
	private List<String> caseLoadList;
	
	private List<StaffMemberRoles> roleAccessList;
	
	private Integer staffId;
	
	private String assignedCaseloadId;
	
	@JsonProperty("userType")
	private String userType;
	
	@JsonProperty("mailId")
	private String mailId;
	
	@JsonProperty("insightUserFlag")
	private String insightUserFlag;
	
	@JsonProperty("insightsGropId")
	private List<String> insightsGropId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(final String passWord) {
		this.passWord = passWord;
	}

	public String getDefaultTableSpace() {
		return defaultTableSpace;
	}

	public void setDefaultTableSpace(final String defaultTableSpace) {
		this.defaultTableSpace = defaultTableSpace;
	}

	public String getTempTableSpace() {
		return tempTableSpace;
	}

	public void setTempTableSpace(final String tempTableSpace) {
		this.tempTableSpace = tempTableSpace;
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

	public String getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGrantUserName() {
		return grantUserName;
	}

	public void setGrantUserName(String grantUserName) {
		this.grantUserName = grantUserName;
	}

	public List<String> getCaseLoadList() {
		return caseLoadList;
	}

	public void setCaseLoadList(List<String> caseLoadList) {
		this.caseLoadList = caseLoadList;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public List<StaffMemberRoles> getRoleAccessList() {
		return roleAccessList;
	}

	public void setRoleAccessList(List<StaffMemberRoles> roleAccessList) {
		this.roleAccessList = roleAccessList;
	}

	public String getAssignedCaseloadId() {
		return assignedCaseloadId;
	}

	public void setAssignedCaseloadId(String assignedCaseloadId) {
		this.assignedCaseloadId = assignedCaseloadId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getInsightUserFlag() {
		return insightUserFlag;
	}

	public List<String> getInsightsGropId() {
		return insightsGropId;
	}

	public void setInsightsGropId(List<String> insightsGropId) {
		this.insightsGropId = insightsGropId;
	}

	public void setInsightUserFlag(String insightUserFlag) {
		this.insightUserFlag = insightUserFlag;
	}
	
	

}
