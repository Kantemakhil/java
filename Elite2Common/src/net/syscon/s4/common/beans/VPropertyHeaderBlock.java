package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.common.validators.ValidBirthDate;


/**
 * The persistent class for the V_PROPERTY_HEADER_BLOCK database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VPropertyHeaderBlock extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("age")
	private BigDecimal age;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("aliasOffenderId")
	private BigDecimal aliasOffenderId;

	@JsonProperty("birthDate")
	@ValidBirthDate(message = "date.format.not.valid", pattern = Utilities.DATE_PATTEN)
	private Date birthDate;

	@JsonProperty("bookingBeginDate")
	private Date bookingBeginDate;

	@JsonProperty("bookingEndDate")
	private Date bookingEndDate;

	@JsonProperty("bookingNo")
	private String bookingNo;

	@JsonProperty("bookingStatus")
	private String bookingStatus;

	@JsonProperty("disclosureFlag")
	private String disclosureFlag;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("inOutStatus")
	private String inOutStatus;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("movementReason")
	private String movementReason;

	@JsonProperty("offAlerts")
	private String offAlerts;

	@JsonProperty("offSupLevel")
	private String offSupLevel;

	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("officer")
	private String officer;

	@JsonProperty("pptyAgyLocId")
	private String pptyAgyLocId;

	@JsonProperty("prisonLocation")
	private String prisonLocation;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("status1")
	private String status1;

	@JsonProperty("status2")
	private String status2;

	@JsonProperty("status3")
	private String status3;

	@JsonProperty("statusDisplay")
	private String statusDisplay;

	@JsonProperty("suffix")
	private String suffix;
	
	@JsonProperty("imageId")
	private BigDecimal imageId;

	@JsonProperty("imageThumbnail")
	private byte[] imageThumbnail;

	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("parentForm")
	private String parentForm;

	public VPropertyHeaderBlock() {
		// VPropertyHeaderBlock
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public BigDecimal getAge() {
		return this.age;
	}

	public void setAge(final BigDecimal age) {
		this.age = age;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getAliasOffenderId() {
		return this.aliasOffenderId;
	}

	public void setAliasOffenderId(final BigDecimal aliasOffenderId) {
		this.aliasOffenderId = aliasOffenderId;
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

	public String getGender() {
		return this.gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public String getInOutStatus() {
		return this.inOutStatus;
	}

	public void setInOutStatus(final String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
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

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	public String getMovementReason() {
		return this.movementReason;
	}

	public void setMovementReason(final String movementReason) {
		this.movementReason = movementReason;
	}

	public String getOffAlerts() {
		return this.offAlerts;
	}

	public void setOffAlerts(final String offAlerts) {
		this.offAlerts = offAlerts;
	}

	public String getOffSupLevel() {
		return this.offSupLevel;
	}

	public void setOffSupLevel(final String offSupLevel) {
		this.offSupLevel = offSupLevel;
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

	public String getOfficer() {
		return this.officer;
	}

	public void setOfficer(final String officer) {
		this.officer = officer;
	}

	public String getPptyAgyLocId() {
		return this.pptyAgyLocId;
	}

	public void setPptyAgyLocId(final String pptyAgyLocId) {
		this.pptyAgyLocId = pptyAgyLocId;
	}

	public String getPrisonLocation() {
		return this.prisonLocation;
	}

	public void setPrisonLocation(final String prisonLocation) {
		this.prisonLocation = prisonLocation;
	}

	public BigDecimal getRootOffenderId() {
		return this.rootOffenderId;
	}

	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getStatus1() {
		return this.status1;
	}

	public void setStatus1(final String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(final String status2) {
		this.status2 = status2;
	}

	public String getStatus3() {
		return this.status3;
	}

	public void setStatus3(final String status3) {
		this.status3 = status3;
	}

	public String getStatusDisplay() {
		return this.statusDisplay;
	}

	public void setStatusDisplay(final String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}

	public BigDecimal getImageId() {
		return imageId;
	}

	public void setImageId(BigDecimal imageId) {
		this.imageId = imageId;
	}

	public byte[] getImageThumbnail() {
		return imageThumbnail;
	}

	public void setImageThumbnail(byte[] imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	public String getParentForm() {
		return parentForm;
	}

	public void setParentForm(String parentForm) {
		this.parentForm = parentForm;
	}
	
}
