package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class VNameSearch2
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VNameSearch2 extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderId")
	private Integer offenderId;
	@JsonProperty("aliasOffenderId")
	private Integer aliasOffenderId;
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("suffix")
	private String suffix;
	@JsonProperty("birthDate")
	private Date birthDate;
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("bookingNo")
	private String bookingNo;
	@JsonProperty("bookingBeginDate")
	private Date bookingBeginDate;
	@JsonProperty("bookingEndDate")
	private Date bookingEndDate;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("agencyImlId")
	private Integer agencyImlId;
	@JsonProperty("livingUnitId")
	private Integer livingUnitId;
	@JsonProperty("disclosureFlag")
	private String disclosureFlag;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("bookingStatus")
	private String bookingStatus;
	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;
	@JsonProperty("inOutStatus")
	private String inOutStatus;
	@JsonProperty("statusDisplay")
	private String statusDisplay;
	@JsonProperty("rootOffenderId")
	private Integer rootOffenderId;
	@JsonProperty("assignedStaffId")
	private Integer assignedStaffId;
	@JsonProperty("agyLocType")
	private String agyLocType;
	@JsonProperty("createAgyLocId")
	private String createAgyLocId;
	@JsonProperty("bookingType")
	private String bookingType;
	@JsonProperty("bookingCreatedDate")
	private Date bookingCreatedDate;
	@JsonProperty("locationCode")
	private String locationCode;
	@JsonProperty("staffFirstName")
	private String staffFirstName;
	@JsonProperty("staffLastName")
	private String staffLastName;
	@JsonProperty("livUnitDesc")
	private String livUnitDesc;
	@JsonProperty("intakeAgyLocId")
	private String intakeAgyLocId;
	@JsonProperty("communityActiveFlag")
	private String communityActiveFlag;
	@JsonProperty("createIntakeAgyLocId")
	private String createIntakeAgyLocId;
	@JsonProperty("communityStatus")
	private String communityStatus;
	@JsonProperty("agyLocName")
	private String agyLocName;
	@JsonProperty("communityAgyLocId")
	private String communityAgyLocId;
	private boolean inserted;
	@JsonProperty("moduleName")
	private String moduleName;
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("parentForm")
	private String parentForm;

	public String getParentForm() {
		return parentForm;
	}

	public void setParentForm(String parentForm) {
		this.parentForm = parentForm;
	}

	public VNameSearch2() {
		// VNameSearch2
	}

	/**
	 * @param offenderId
	 *            offenderId to set
	 */
	public void setOffenderId(final Integer offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * return theoffenderId
	 */
	public Integer getOffenderId() {
		return this.offenderId;
	}

	/**
	 * @param aliasOffenderId
	 *            aliasOffenderId to set
	 */
	public void setAliasOffenderId(final Integer aliasOffenderId) {
		this.aliasOffenderId = aliasOffenderId;
	}

	/**
	 * return thealiasOffenderId
	 */
	public Integer getAliasOffenderId() {
		return this.aliasOffenderId;
	}

	/**
	 * @param offenderIdDisplay
	 *            offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * return theoffenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	/**
	 * @param lastName
	 *            lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * return thelastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param firstName
	 *            firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * return thefirstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param middleName
	 *            middleName to set
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * return themiddleName
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * @param suffix
	 *            suffix to set
	 */
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}

	/**
	 * return thesuffix
	 */
	public String getSuffix() {
		return this.suffix;
	}

	/**
	 * @param birthDate
	 *            birthDate to set
	 */
	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * return thebirthDate
	 */
	public Date getBirthDate() {
		return this.birthDate;
	}

	/**
	 * @param offenderBookId
	 *            offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * return theoffenderBookId
	 */
	public Integer getOffenderBookId() {
		return this.offenderBookId;
	}

	/**
	 * @param bookingNo
	 *            bookingNo to set
	 */
	public void setBookingNo(final String bookingNo) {
		this.bookingNo = bookingNo;
	}

	/**
	 * return thebookingNo
	 */
	public String getBookingNo() {
		return this.bookingNo;
	}

	/**
	 * @param bookingBeginDate
	 *            bookingBeginDate to set
	 */
	public void setBookingBeginDate(final Date bookingBeginDate) {
		this.bookingBeginDate = bookingBeginDate;
	}

	/**
	 * return thebookingBeginDate
	 */
	public Date getBookingBeginDate() {
		return this.bookingBeginDate;
	}

	/**
	 * @param bookingEndDate
	 *            bookingEndDate to set
	 */
	public void setBookingEndDate(final Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	/**
	 * return thebookingEndDate
	 */
	public Date getBookingEndDate() {
		return this.bookingEndDate;
	}

	/**
	 * @param agyLocId
	 *            agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * return theagyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}

	/**
	 * @param agencyImlId
	 *            agencyImlId to set
	 */
	public void setAgencyImlId(final Integer agencyImlId) {
		this.agencyImlId = agencyImlId;
	}

	/**
	 * return theagencyImlId
	 */
	public Integer getAgencyImlId() {
		return this.agencyImlId;
	}

	/**
	 * @param livingUnitId
	 *            livingUnitId to set
	 */
	public void setLivingUnitId(final Integer livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 * return thelivingUnitId
	 */
	public Integer getLivingUnitId() {
		return this.livingUnitId;
	}

	/**
	 * @param disclosureFlag
	 *            disclosureFlag to set
	 */
	public void setDisclosureFlag(final String disclosureFlag) {
		this.disclosureFlag = disclosureFlag;
	}

	/**
	 * return thedisclosureFlag
	 */
	public String getDisclosureFlag() {
		return this.disclosureFlag;
	}

	/**
	 * @param activeFlag
	 *            activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * return theactiveFlag
	 */
	public String getActiveFlag() {
		return this.activeFlag;
	}

	/**
	 * @param bookingStatus
	 *            bookingStatus to set
	 */
	public void setBookingStatus(final String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	/**
	 * return thebookingStatus
	 */
	public String getBookingStatus() {
		return this.bookingStatus;
	}

	/**
	 * @param livingUnitDescription
	 *            livingUnitDescription to set
	 */
	public void setLivingUnitDescription(final String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}

	/**
	 * return thelivingUnitDescription
	 */
	public String getLivingUnitDescription() {
		return this.livingUnitDescription;
	}

	/**
	 * @param inOutStatus
	 *            inOutStatus to set
	 */
	public void setInOutStatus(final String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	/**
	 * return theinOutStatus
	 */
	public String getInOutStatus() {
		return this.inOutStatus;
	}

	/**
	 * @param statusDisplay
	 *            statusDisplay to set
	 */
	public void setStatusDisplay(final String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	/**
	 * return thestatusDisplay
	 */
	public String getStatusDisplay() {
		return this.statusDisplay;
	}

	/**
	 * @param rootOffenderId
	 *            rootOffenderId to set
	 */
	public void setRootOffenderId(final Integer rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * return therootOffenderId
	 */
	public Integer getRootOffenderId() {
		return this.rootOffenderId;
	}

	/**
	 * @param assignedStaffId
	 *            assignedStaffId to set
	 */
	public void setAssignedStaffId(final Integer assignedStaffId) {
		this.assignedStaffId = assignedStaffId;
	}

	/**
	 * return theassignedStaffId
	 */
	public Integer getAssignedStaffId() {
		return this.assignedStaffId;
	}

	/**
	 * @param agyLocType
	 *            agyLocType to set
	 */
	public void setAgyLocType(final String agyLocType) {
		this.agyLocType = agyLocType;
	}

	/**
	 * return theagyLocType
	 */
	public String getAgyLocType() {
		return this.agyLocType;
	}

	/**
	 * @param createAgyLocId
	 *            createAgyLocId to set
	 */
	public void setCreateAgyLocId(final String createAgyLocId) {
		this.createAgyLocId = createAgyLocId;
	}

	/**
	 * return thecreateAgyLocId
	 */
	public String getCreateAgyLocId() {
		return this.createAgyLocId;
	}

	/**
	 * @param bookingType
	 *            bookingType to set
	 */
	public void setBookingType(final String bookingType) {
		this.bookingType = bookingType;
	}

	/**
	 * return thebookingType
	 */
	public String getBookingType() {
		return this.bookingType;
	}

	/**
	 * @param bookingCreatedDate
	 *            bookingCreatedDate to set
	 */
	public void setBookingCreatedDate(final Date bookingCreatedDate) {
		this.bookingCreatedDate = bookingCreatedDate;
	}

	/**
	 * return thebookingCreatedDate
	 */
	public Date getBookingCreatedDate() {
		return this.bookingCreatedDate;
	}

	/**
	 * @param locationCode
	 *            locationCode to set
	 */
	public void setLocationCode(final String locationCode) {
		this.locationCode = locationCode;
	}

	/**
	 * return thelocationCode
	 */
	public String getLocationCode() {
		return this.locationCode;
	}

	/**
	 * @param staffFirstName
	 *            staffFirstName to set
	 */
	public void setStaffFirstName(final String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	/**
	 * return thestaffFirstName
	 */
	public String getStaffFirstName() {
		return this.staffFirstName;
	}

	/**
	 * @param staffLastName
	 *            staffLastName to set
	 */
	public void setStaffLastName(final String staffLastName) {
		this.staffLastName = staffLastName;
	}

	/**
	 * return thestaffLastName
	 */
	public String getStaffLastName() {
		return this.staffLastName;
	}

	/**
	 * @param livUnitDesc
	 *            livUnitDesc to set
	 */
	public void setLivUnitDesc(final String livUnitDesc) {
		this.livUnitDesc = livUnitDesc;
	}

	/**
	 * return thelivUnitDesc
	 */
	public String getLivUnitDesc() {
		return this.livUnitDesc;
	}

	/**
	 * @param intakeAgyLocId
	 *            intakeAgyLocId to set
	 */
	public void setIntakeAgyLocId(final String intakeAgyLocId) {
		this.intakeAgyLocId = intakeAgyLocId;
	}

	/**
	 * return theintakeAgyLocId
	 */
	public String getIntakeAgyLocId() {
		return this.intakeAgyLocId;
	}

	/**
	 * @param communityActiveFlag
	 *            communityActiveFlag to set
	 */
	public void setCommunityActiveFlag(final String communityActiveFlag) {
		this.communityActiveFlag = communityActiveFlag;
	}

	/**
	 * return thecommunityActiveFlag
	 */
	public String getCommunityActiveFlag() {
		return this.communityActiveFlag;
	}

	/**
	 * @param createIntakeAgyLocId
	 *            createIntakeAgyLocId to set
	 */
	public void setCreateIntakeAgyLocId(final String createIntakeAgyLocId) {
		this.createIntakeAgyLocId = createIntakeAgyLocId;
	}

	/**
	 * return thecreateIntakeAgyLocId
	 */
	public String getCreateIntakeAgyLocId() {
		return this.createIntakeAgyLocId;
	}

	/**
	 * @param communityStatus
	 *            communityStatus to set
	 */
	public void setCommunityStatus(final String communityStatus) {
		this.communityStatus = communityStatus;
	}

	/**
	 * return thecommunityStatus
	 */
	public String getCommunityStatus() {
		return this.communityStatus;
	}

	/**
	 * @param agyLocName
	 *            agyLocName to set
	 */
	public void setAgyLocName(final String agyLocName) {
		this.agyLocName = agyLocName;
	}

	/**
	 * return theagyLocName
	 */
	public String getAgyLocName() {
		return this.agyLocName;
	}

	/**
	 * @param communityAgyLocId
	 *            communityAgyLocId to set
	 */
	public void setCommunityAgyLocId(final String communityAgyLocId) {
		this.communityAgyLocId = communityAgyLocId;
	}

	/**
	 * return thecommunityAgyLocId
	 */
	public String getCommunityAgyLocId() {
		return this.communityAgyLocId;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}