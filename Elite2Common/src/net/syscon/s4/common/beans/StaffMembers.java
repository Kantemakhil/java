package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.beans.StaffDetails;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class StaffMembers extends BaseModel implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("staffId")
	@NotNull
	private Integer staffId;

	@JsonProperty("assignedCaseloadId")
	@Size(max = 6)
	private String assignedCaseloadId;

	@JsonProperty("workingStockLocId")
	@Size(max = 6)
	private String workingStockLocId;

	@JsonProperty("workingCaseloadId")
	@Size(max = 6)
	private String workingCaseloadId;

	@JsonProperty("userId")
	@Size(max = 32)
	private String userId;

	@JsonProperty("badgeId")
	@Size(max = 20)
	private String badgeId;

	@JsonProperty("lastName")
	@NotNull
	@Size(max = 35)
	private String lastName;

	@JsonProperty("firstName")
	@NotNull
	@Size(max = 35)
	private String firstName;

	@JsonProperty("middleName")
	@Size(max = 35)
	private String middleName;

	@JsonProperty("abbreviation")
	@Size(max = 15)
	private String abbreviation;

	@JsonProperty("position")
	@NotNull
	@Size(max = 25)
	private String position;

	@JsonProperty("birthdate")
	private Date birthdate;

	@JsonProperty("terminationDate")
	private Date terminationDate;

	@JsonProperty("updateAllowedFlag")
	@NotNull
	@Size(max = 1)
	private String updateAllowedFlag;

	@JsonProperty("defaultPrinterId")
	private Integer defaultPrinterId;

	@JsonProperty("suspendedFlag")
	@Size(max = 1)
	private String suspendedFlag;

	@JsonProperty("supervisorStaffId")
	private Integer supervisorStaffId;

	@JsonProperty("commReceiptPrinterId")
	@Size(max = 12)
	private String commReceiptPrinterId;

	@JsonProperty("personnelType")
	@Size(max = 12)
	private String personnelType;

	@JsonProperty("asOfDate")
	private Date asOfDate;

	@JsonProperty("emergencyContact")
	@Size(max = 20)
	private String emergencyContact;

	@JsonProperty("role")
	@Size(max = 12)
	private String role;

	@JsonProperty("sexCode")
	@Size(max = 12)
	private String sexCode;

	@JsonProperty("status")
	@Size(max = 12)
	private String status;

	@JsonProperty("suspensionDate")
	private Date suspensionDate;

	@JsonProperty("suspensionReason")
	@Size(max = 12)
	private String suspensionReason;

	@JsonProperty("forcePasswordChangeFlag")
	@Size(max = 1)
	private String forcePasswordChangeFlag;

	@JsonProperty("lastPasswordChangeDate")
	private Date lastPasswordChangeDate;

	@JsonProperty("licenseCode")
	@Size(max = 12)
	private String licenseCode;

	@JsonProperty("licenseExpiryDate")
	private Date licenseExpiryDate;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("title")
	@Size(max = 12)
	private String title;

	@JsonProperty("nameSequence")
	@Size(max = 12)
	private String nameSequence;

	@JsonProperty("queueClusterId")
	private Integer queueClusterId;

	@JsonProperty("firstLogonFlag")
	@Size(max = 1)
	private String firstLogonFlag;

	@JsonProperty("suffix")
	@Size(max = 12)
	private String suffix;

	@JsonProperty("staffName")
	private String staffName;

	@JsonProperty("addressId")
	private Integer addressId;

	@JsonProperty("addrSpecPhoneNumber")
	private List<Phones> addrSpecPhoneNumber;

	@JsonProperty("globalPhoneNumber")
	private List<Phones> globalPhoneNumber;

	@JsonProperty("globalEmails")
	private List<InternetAddresses> globalEmails;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("inserted")
	private Boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("nbtDescription")
	private String nbtDescription;

	@JsonProperty("assignedCaseloadIdDes")
	private String assignedCaseloadIdDes;

	@JsonProperty("dspName")
	private String dspName;

	@JsonProperty("code")
	private Integer code;

	@JsonProperty("description")
	private String description;

	@JsonProperty("officer")
	private String officer;
	
	
	@JsonProperty("fullName")
	private String fullName;
	
	@JsonProperty("fromDate")
	private Date fromDate;
	
	@JsonProperty("teamMemberId")
	private Integer teamMemberId;
	
	@JsonProperty("canDisplay")
	 private Boolean canDisplay = true;

	@JsonProperty("noOfTasks")
	private Integer noOfTasks;
	
	@JsonProperty("teamName")
	private String teamName;
	
	@JsonProperty("offenderId")
	private BigDecimal offenderId;
	
	private String workType;
	
	private String workSubType;
	private String severity;
	private boolean acknowledgementFlag;
	private String acknowledgementSubject;
	private String prisonLocation;
	
	private String workMessage;
	@JsonProperty("workId")
	private Integer workId;
	
	private List<Teams> teamList;
	private String userIdVal;
	@JsonProperty("internetAddress")
	private String internetAddress;
	private BigDecimal hoursPerWeek;	 
	private String scheduleType;
	
	private String mailId;
	
	@JsonProperty("adUser")
	private String adUser;

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	private List<StaffDetails> staffList;
	private Integer offenderBookId; 
	
	@JsonProperty("listSeq")
	private Integer listSeq;

	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("totalWorkload")
	private Integer totalWorkload;
	@JsonProperty("cgnbtNoOffender")
	private Integer cgnbtNoOffender;
	
	@JsonProperty("caseloadType")
	private String caseloadType;
	
	private String  password;
	
	private Integer passwordReturnVal;
	

	public String getCaseloadType() {
		return caseloadType;
	}

	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public Integer getCgnbtNoOffender() {
		return cgnbtNoOffender;
	}

	public void setCgnbtNoOffender(Integer cgnbtNoOffender) {
		this.cgnbtNoOffender = cgnbtNoOffender;
	}

	public Integer getTotalWorkload() {
		return totalWorkload;
	}

	public void setTotalWorkload(Integer totalWorkload) {
		this.totalWorkload = totalWorkload;
	}

	/**
	 * Creates new StaffMembers class Object
	 */
	public StaffMembers() {

		// StaffMembers
	}

	/**
	 * @return the dspName
	 */
	public String getDspName() {
		return dspName;
	}

	/**
	 * @param dspName
	 *            the dspName to set
	 */
	public void setDspName(final String dspName) {
		this.dspName = dspName;
	}

	/**
	 * @return the assignedCaseloadIdDes
	 */
	public String getAssignedCaseloadIdDes() {
		return assignedCaseloadIdDes;
	}

	/**
	 * @param assignedCaseloadIdDes
	 *            the assignedCaseloadIdDes to set
	 */
	public void setAssignedCaseloadIdDes(final String assignedCaseloadIdDes) {
		this.assignedCaseloadIdDes = assignedCaseloadIdDes;
	}

	/**
	 * @return the nbtDescription
	 */
	public String getNbtDescription() {
		return nbtDescription;
	}

	/**
	 * @param nbtDescription
	 *            the nbtDescription to set
	 */
	public void setNbtDescription(final String nbtDescription) {
		this.nbtDescription = nbtDescription;
	}

	/**
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId
	 *            the staffId to set
	 */
	public void setStaffId(final Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the assignedCaseloadId
	 */
	public String getAssignedCaseloadId() {
		return assignedCaseloadId;
	}

	/**
	 * @param assignedCaseloadId
	 *            the assignedCaseloadId to set
	 */
	public void setAssignedCaseloadId(final String assignedCaseloadId) {
		this.assignedCaseloadId = assignedCaseloadId;
	}

	/**
	 * @return the workingStockLocId
	 */
	public String getWorkingStockLocId() {
		return workingStockLocId;
	}

	/**
	 * @param workingStockLocId
	 *            the workingStockLocId to set
	 */
	public void setWorkingStockLocId(final String workingStockLocId) {
		this.workingStockLocId = workingStockLocId;
	}

	/**
	 * @return the workingCaseloadId
	 */
	public String getWorkingCaseloadId() {
		return workingCaseloadId;
	}

	/**
	 * @param workingCaseloadId
	 *            the workingCaseloadId to set
	 */
	public void setWorkingCaseloadId(final String workingCaseloadId) {
		this.workingCaseloadId = workingCaseloadId;
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
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * @return the badgeId
	 */
	public String getBadgeId() {
		return badgeId;
	}

	/**
	 * @param badgeId
	 *            the badgeId to set
	 */
	public void setBadgeId(final String badgeId) {
		this.badgeId = badgeId;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName
	 *            the staffName to set
	 */
	public void setStaffName(final String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
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
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * @param abbreviation
	 *            the abbreviation to set
	 */
	public void setAbbreviation(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	public String getPrisonLocation() {
		return prisonLocation;
	}

	public void setPrisonLocation(String prisonLocation) {
		this.prisonLocation = prisonLocation;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}


	public String getWorkMessage() {
		return workMessage;
	}

	public void setWorkMessage(String workMessage) {
		this.workMessage = workMessage;
	}

	public String getAcknowledgementSubject() {
		return acknowledgementSubject;
	}

	public void setAcknowledgementSubject(String acknowledgementSubject) {
		this.acknowledgementSubject = acknowledgementSubject;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public List<Teams> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Teams> teamList) {
		this.teamList = teamList;
	}

	public List<StaffDetails> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<StaffDetails> staffList) {
		this.staffList = staffList;
	}

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public String getWorkSubType() {
		return workSubType;
	}

	public void setWorkSubType(String workSubType) {
		this.workSubType = workSubType;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(final String position) {
		this.position = position;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(final Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the terminationDate
	 */
	public Date getTerminationDate() {
		return terminationDate;
	}

	/**
	 * @param terminationDate
	 *            the terminationDate to set
	 */
	public void setTerminationDate(final Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	/**
	 * @return the updateAllowedFlag
	 */
	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	public boolean isAcknowledgementFlag() {
		return acknowledgementFlag;
	}

	public void setAcknowledgementFlag(boolean acknowledgementFlag) {
		this.acknowledgementFlag = acknowledgementFlag;
	}

	/**
	 * @param updateAllowedFlag
	 *            the updateAllowedFlag to set
	 */
	public void setUpdateAllowedFlag(final String updateAllowedFlag) {
		this.updateAllowedFlag = updateAllowedFlag;
	}

	/**
	 * @return the defaultPrinterId
	 */
	public Integer getDefaultPrinterId() {
		return defaultPrinterId;
	}

	/**
	 * @param defaultPrinterId
	 *            the defaultPrinterId to set
	 */
	public void setDefaultPrinterId(final Integer defaultPrinterId) {
		this.defaultPrinterId = defaultPrinterId;
	}

	/**
	 * @return the suspendedFlag
	 */
	public String getSuspendedFlag() {
		return suspendedFlag;
	}

	/**
	 * @param suspendedFlag
	 *            the suspendedFlag to set
	 */
	public void setSuspendedFlag(final String suspendedFlag) {
		this.suspendedFlag = suspendedFlag;
	}

	/**
	 * @return the supervisorStaffId
	 */
	public Integer getSupervisorStaffId() {
		return supervisorStaffId;
	}

	/**
	 * @param supervisorStaffId
	 *            the supervisorStaffId to set
	 */
	public void setSupervisorStaffId(final Integer supervisorStaffId) {
		this.supervisorStaffId = supervisorStaffId;
	}

	/**
	 * @return the commReceiptPrinterId
	 */
	public String getCommReceiptPrinterId() {
		return commReceiptPrinterId;
	}

	/**
	 * @param commReceiptPrinterId
	 *            the commReceiptPrinterId to set
	 */
	public void setCommReceiptPrinterId(final String commReceiptPrinterId) {
		this.commReceiptPrinterId = commReceiptPrinterId;
	}

	/**
	 * @return the personnelType
	 */
	public String getPersonnelType() {
		return personnelType;
	}

	/**
	 * @param personnelType
	 *            the personnelType to set
	 */
	public void setPersonnelType(final String personnelType) {
		this.personnelType = personnelType;
	}

	/**
	 * @return the asOfDate
	 */
	public Date getAsOfDate() {
		return asOfDate;
	}

	/**
	 * @param asOfDate
	 *            the asOfDate to set
	 */
	public void setAsOfDate(final Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	/**
	 * @return the emergencyContact
	 */
	public String getEmergencyContact() {
		return emergencyContact;
	}

	/**
	 * @param emergencyContact
	 *            the emergencyContact to set
	 */
	public void setEmergencyContact(final String emergencyContact) {
		this.emergencyContact = emergencyContact;
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
	public void setRole(final String role) {
		this.role = role;
	}

	/**
	 * @return the sexCode
	 */
	public String getSexCode() {
		return sexCode;
	}

	/**
	 * @param sexCode
	 *            the sexCode to set
	 */
	public void setSexCode(final String sexCode) {
		this.sexCode = sexCode;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the suspensionDate
	 */
	public Date getSuspensionDate() {
		return suspensionDate;
	}

	/**
	 * @param suspensionDate
	 *            the suspensionDate to set
	 */
	public void setSuspensionDate(final Date suspensionDate) {
		this.suspensionDate = suspensionDate;
	}

	/**
	 * @return the suspensionReason
	 */
	public String getSuspensionReason() {
		return suspensionReason;
	}

	/**
	 * @param suspensionReason
	 *            the suspensionReason to set
	 */
	public void setSuspensionReason(final String suspensionReason) {
		this.suspensionReason = suspensionReason;
	}

	/**
	 * @return the forcePasswordChangeFlag
	 */
	public String getForcePasswordChangeFlag() {
		return forcePasswordChangeFlag;
	}

	/**
	 * @param forcePasswordChangeFlag
	 *            the forcePasswordChangeFlag to set
	 */
	public void setForcePasswordChangeFlag(final String forcePasswordChangeFlag) {
		this.forcePasswordChangeFlag = forcePasswordChangeFlag;
	}

	/**
	 * @return the lastPasswordChangeDate
	 */
	public Date getLastPasswordChangeDate() {
		return lastPasswordChangeDate;
	}

	/**
	 * @param lastPasswordChangeDate
	 *            the lastPasswordChangeDate to set
	 */
	public void setLastPasswordChangeDate(final Date lastPasswordChangeDate) {
		this.lastPasswordChangeDate = lastPasswordChangeDate;
	}

	/**
	 * @return the licenseCode
	 */
	public String getLicenseCode() {
		return licenseCode;
	}

	/**
	 * @param licenseCode
	 *            the licenseCode to set
	 */
	public void setLicenseCode(final String licenseCode) {
		this.licenseCode = licenseCode;
	}

	/**
	 * @return the licenseExpiryDate
	 */
	public Date getLicenseExpiryDate() {
		return licenseExpiryDate;
	}

	/**
	 * @param licenseExpiryDate
	 *            the licenseExpiryDate to set
	 */
	public void setLicenseExpiryDate(final Date licenseExpiryDate) {
		this.licenseExpiryDate = licenseExpiryDate;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
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
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @return the nameSequence
	 */
	public String getNameSequence() {
		return nameSequence;
	}

	/**
	 * @param nameSequence
	 *            the nameSequence to set
	 */
	public void setNameSequence(final String nameSequence) {
		this.nameSequence = nameSequence;
	}

	/**
	 * @return the queueClusterId
	 */
	public Integer getQueueClusterId() {
		return queueClusterId;
	}

	/**
	 * @param queueClusterId
	 *            the queueClusterId to set
	 */
	public void setQueueClusterId(final Integer queueClusterId) {
		this.queueClusterId = queueClusterId;
	}

	/**
	 * @return the firstLogonFlag
	 */
	public String getFirstLogonFlag() {
		return firstLogonFlag;
	}

	/**
	 * @param firstLogonFlag
	 *            the firstLogonFlag to set
	 */
	public void setFirstLogonFlag(final String firstLogonFlag) {
		this.firstLogonFlag = firstLogonFlag;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param suffix
	 *            the suffix to set
	 */
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
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
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the inserted
	 */
	public Boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final Boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the addressId
	 */
	public Integer getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
	 */
	public void setAddressId(final Integer addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the addrSpecPhoneNumber
	 */
	public List<Phones> getAddrSpecPhoneNumber() {
		return addrSpecPhoneNumber;
	}

	/**
	 * @param addrSpecPhoneNumber
	 *            the addrSpecPhoneNumber to set
	 */
	public void setAddrSpecPhoneNumber(final List<Phones> addrSpecPhoneNumber) {
		this.addrSpecPhoneNumber = addrSpecPhoneNumber;
	}

	/**
	 * @return the globalPhoneNumber
	 */
	public List<Phones> getGlobalPhoneNumber() {
		return globalPhoneNumber;
	}

	/**
	 * @param globalPhoneNumber
	 *            the globalPhoneNumber to set
	 */
	public void setGlobalPhoneNumber(final List<Phones> globalPhoneNumber) {
		this.globalPhoneNumber = globalPhoneNumber;
	}

	/**
	 * @return the globalEmails
	 */
	public List<InternetAddresses> getGlobalEmails() {
		return globalEmails;
	}

	/**
	 * @param globalEmails
	 *            the globalEmails to set
	 */
	public void setGlobalEmails(final List<InternetAddresses> globalEmails) {
		this.globalEmails = globalEmails;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the inserted
	 */
	public Boolean getInserted() {
		return inserted;
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
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the teamMemberId
	 */
	public Integer getTeamMemberId() {
		return teamMemberId;
	}

	/**
	 * @param teamMemberId the teamMemberId to set
	 */
	public void setTeamMemberId(Integer teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	/**
	 * @return the canDisplay
	 */
	public Boolean getCanDisplay() {
		return canDisplay;
	}

	/**
	 * @param canDisplay the canDisplay to set
	 */
	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	/**
	 * @return the noOfTasks
	 */
	public Integer getNoOfTasks() {
		return noOfTasks;
	}

	/**
	 * @param noOfTasks the noOfTasks to set
	 */
	public void setNoOfTasks(Integer noOfTasks) {
		this.noOfTasks = noOfTasks;
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

	public String getUserIdVal() {
		return userIdVal;
	}

	public void setUserIdVal(String userIdVal) {
		this.userIdVal = userIdVal;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getInternetAddress() {
		return internetAddress;
	}
	public String getMailId() {
		return mailId;
	}

	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getAdUser() {
		return adUser;
	}

	public void setAdUser(String adUser) {
		this.adUser = adUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPasswordReturnVal() {
		return passwordReturnVal;
	}

	public void setPasswordReturnVal(Integer passwordReturnVal) {
		this.passwordReturnVal = passwordReturnVal;
	}

}