package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.common.validators.ValidBirthDate;

/**
 * The persistent class for the V_HEADER_BLOCK2 database table.
 * 
 */
@GlobalValidation(message = "atleast.one.mandatory")
public class VHeaderBlock2 extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("agencyImlId")
	private BigDecimal agencyImlId;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("agyLocType")
	private String agyLocType;

	@JsonProperty("aliasOffenderId")
	private BigDecimal aliasOffenderId;

	@JsonProperty("assignedStaffId")
	private BigDecimal assignedStaffId;

	@JsonProperty("birthDate")
	@ValidBirthDate(message = "date.format.not.valid", pattern = Utilities.DATE_PATTEN)
	private Date birthDate;

	@JsonProperty("bookingBeginDate")
	private Date bookingBeginDate;

	@JsonProperty("bookingCreatedDate")
	private Date bookingCreatedDate;

	@JsonProperty("bookingEndDate")
	private Date bookingEndDate;

	@JsonProperty("bookingNo")
	private String bookingNo;

	@JsonProperty("bookingStatus")
	private String bookingStatus;

	@JsonProperty("bookingType")
	private String bookingType;

	@JsonProperty("communityActiveFlag")
	private String communityActiveFlag;

	@JsonProperty("communityStatus")
	private String communityStatus;

	@JsonProperty("createAgyLocId")
	private String createAgyLocId;

	@JsonProperty("createIntakeAgyLocId")
	private String createIntakeAgyLocId;

	@JsonProperty("disclosureFlag")
	private String disclosureFlag;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("headerStatus")
	private String headerStatus;

	@JsonProperty("inOutStatus")
	private String inOutStatus;

	@JsonProperty("intakeAgyLocId")
	private String intakeAgyLocId;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("livUnitDesc")
	private String livUnitDesc;

	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("locationCode")
	private String locationCode;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("staffFirstName")
	private String staffFirstName;

	@JsonProperty("staffLastName")
	private String staffLastName;

	@JsonProperty("statusDisplay")
	private String statusDisplay;

	@JsonProperty("statusReason")
	private String statusReason;

	@JsonProperty("suffix")
	private String suffix;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("caseLoadId")
	private String caseLoadId;

	@JsonProperty("age")
	private BigDecimal age;

	@JsonProperty("prisonLocation")
	private String prisonLocation;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;

	/**
	 * Creates new VHeaderBlock2 class Object
	 */
	public VHeaderBlock2() {
		// VHeaderBlock2
	}

	public BigDecimal getAge() {
		return age;
	}

	public void setAge(final BigDecimal age) {
		this.age = age;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public BigDecimal getAgencyImlId() {
		return this.agencyImlId;
	}

	public void setAgencyImlId(final BigDecimal agencyImlId) {
		this.agencyImlId = agencyImlId;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getAgyLocType() {
		return this.agyLocType;
	}

	public void setAgyLocType(final String agyLocType) {
		this.agyLocType = agyLocType;
	}

	public BigDecimal getAliasOffenderId() {
		return this.aliasOffenderId;
	}

	public void setAliasOffenderId(final BigDecimal aliasOffenderId) {
		this.aliasOffenderId = aliasOffenderId;
	}

	public BigDecimal getAssignedStaffId() {
		return this.assignedStaffId;
	}

	public void setAssignedStaffId(final BigDecimal assignedStaffId) {
		this.assignedStaffId = assignedStaffId;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	public Object getBookingBeginDate() {
		return this.bookingBeginDate;
	}

	public void setBookingBeginDate(final Date bookingBeginDate) {
		this.bookingBeginDate = bookingBeginDate;
	}

	public Object getBookingCreatedDate() {
		return this.bookingCreatedDate;
	}

	public void setBookingCreatedDate(final Date bookingCreatedDate) {
		this.bookingCreatedDate = bookingCreatedDate;
	}

	public Object getBookingEndDate() {
		return this.bookingEndDate;
	}

	public void setBookingEndDate(final Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public String getBookingNo() {
		return this.bookingNo;
	}

	public void setBookingNo(final String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getBookingStatus() {
		return this.bookingStatus;
	}

	public void setBookingStatus(final String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getBookingType() {
		return this.bookingType;
	}

	public void setBookingType(final String bookingType) {
		this.bookingType = bookingType;
	}

	public String getCommunityActiveFlag() {
		return this.communityActiveFlag;
	}

	public void setCommunityActiveFlag(final String communityActiveFlag) {
		this.communityActiveFlag = communityActiveFlag;
	}

	public String getCommunityStatus() {
		return this.communityStatus;
	}

	public void setCommunityStatus(final String communityStatus) {
		this.communityStatus = communityStatus;
	}

	public String getCreateAgyLocId() {
		return this.createAgyLocId;
	}

	public void setCreateAgyLocId(final String createAgyLocId) {
		this.createAgyLocId = createAgyLocId;
	}

	public String getCreateIntakeAgyLocId() {
		return this.createIntakeAgyLocId;
	}

	public void setCreateIntakeAgyLocId(final String createIntakeAgyLocId) {
		this.createIntakeAgyLocId = createIntakeAgyLocId;
	}

	public String getDisclosureFlag() {
		return this.disclosureFlag;
	}

	public void setDisclosureFlag(final String disclosureFlag) {
		this.disclosureFlag = disclosureFlag;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getHeaderStatus() {
		return this.headerStatus;
	}

	public void setHeaderStatus(final String headerStatus) {
		this.headerStatus = headerStatus;
	}

	public String getInOutStatus() {
		return this.inOutStatus;
	}

	public void setInOutStatus(final String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	public String getIntakeAgyLocId() {
		return this.intakeAgyLocId;
	}

	public void setIntakeAgyLocId(final String intakeAgyLocId) {
		this.intakeAgyLocId = intakeAgyLocId;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getLivUnitDesc() {
		return this.livUnitDesc;
	}

	public void setLivUnitDesc(final String livUnitDesc) {
		this.livUnitDesc = livUnitDesc;
	}

	public String getLivingUnitDescription() {
		return this.livingUnitDescription;
	}

	public void setLivingUnitDescription(final String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}

	public BigDecimal getLivingUnitId() {
		return this.livingUnitId;
	}

	public void setLivingUnitId(final BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(final String locationCode) {
		this.locationCode = locationCode;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public BigDecimal getRootOffenderId() {
		return this.rootOffenderId;
	}

	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getStaffFirstName() {
		return this.staffFirstName;
	}

	public void setStaffFirstName(final String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	public String getStaffLastName() {
		return this.staffLastName;
	}

	public void setStaffLastName(final String staffLastName) {
		this.staffLastName = staffLastName;
	}

	public String getStatusDisplay() {
		return this.statusDisplay;
	}

	public void setStatusDisplay(final String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	public String getStatusReason() {
		return this.statusReason;
	}

	public void setStatusReason(final String statusReason) {
		this.statusReason = statusReason;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(final String suffix) {
		this.suffix = suffix;
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
	 * @return the caseLoadId
	 */
	public String getCaseLoadId() {
		return caseLoadId;
	}

	/**
	 * @param caseLoadId
	 *            the caseLoadId to set
	 */
	public void setCaseLoadId(final String caseLoadId) {
		this.caseLoadId = caseLoadId;
	}

	/**
	 * @return the prisonLocation
	 */
	public String getPrisonLocation() {
		return prisonLocation;
	}

	/**
	 * @param prisonLocation the prisonLocation to set
	 */
	public void setPrisonLocation(final String prisonLocation) {
		this.prisonLocation = prisonLocation;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}
