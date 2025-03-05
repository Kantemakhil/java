package net.syscon.s4.common.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class VNameSearch
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VNameSearch extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("activeFlag")
	private String activeFlag;
	@JsonProperty("offenderId")
	private Integer offenderId;
	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("bookingNo")
	private String bookingNo;
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("birthDate")
	private Date birthDate;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("agyLocName")
	private String agyLocName;
	@JsonProperty("livingUnitDescription")
	private String livingUnitDescription;
	@JsonProperty("inOutStatus")
	private String inOutStatus;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("inserted")
	private boolean inserted;
	@JsonProperty("errorMessage")
	private String errorMessage;
	@JsonProperty("age")
	private BigDecimal age;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("offAlerts")
	private String offAlerts;
	@JsonProperty("offSupLevel")
	private String offSupLevel;
	@JsonProperty("nbtLivingUnitId")
	private BigDecimal nbtLivingUnitId;
	@JsonProperty("moduleName")
	private String moduleName;
	@JsonProperty("caseloadId")
	private String caseloadId;
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
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the offenderId
	 */
	public Integer getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(Integer offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * @return the offenderBookId
	 */
	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the bookingNo
	 */
	public String getBookingNo() {
		return bookingNo;
	}

	/**
	 * @param bookingNo
	 *            the bookingNo to set
	 */
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the age
	 */
	public BigDecimal getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(BigDecimal age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the offAlerts
	 */
	public String getOffAlerts() {
		return offAlerts;
	}

	/**
	 * @param offAlerts the offAlerts to set
	 */
	public void setOffAlerts(String offAlerts) {
		this.offAlerts = offAlerts;
	}

	/**
	 * @return the offSupLevel
	 */
	public String getOffSupLevel() {
		return offSupLevel;
	}

	/**
	 * @param offSupLevel the offSupLevel to set
	 */
	public void setOffSupLevel(String offSupLevel) {
		this.offSupLevel = offSupLevel;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	 * @return the agyLocName
	 */
	public String getAgyLocName() {
		return agyLocName;
	}

	/**
	 * @param agyLocName
	 *            the agyLocName to set
	 */
	public void setAgyLocName(String agyLocName) {
		this.agyLocName = agyLocName;
	}

	/**
	 * @return the livingUnitDescription
	 */
	public String getLivingUnitDescription() {
		return livingUnitDescription;
	}

	/**
	 * @param livingUnitDescription
	 *            the livingUnitDescription to set
	 */
	public void setLivingUnitDescription(String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}

	/**
	 * @return the inOutStatus
	 */
	public String getInOutStatus() {
		return inOutStatus;
	}

	/**
	 * @param inOutStatus
	 *            the inOutStatus to set
	 */
	public void setInOutStatus(String inOutStatus) {
		this.inOutStatus = inOutStatus;
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
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	public void setInserted(boolean inserted) {
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
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * @return the nbtLivingUnitId
	 */
	public BigDecimal getNbtLivingUnitId() {
		return nbtLivingUnitId;
	}

	/**
	 * @param nbtLivingUnitId the nbtLivingUnitId to set
	 */
	public void setNbtLivingUnitId(final BigDecimal nbtLivingUnitId) {
		this.nbtLivingUnitId = nbtLivingUnitId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getCaseloadId() {
		return caseloadId;
	}

	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}
	
	
	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	@Override
	public String toString() {
		return "VNameSearch [lastName=" + lastName + ", firstName=" + firstName + ", activeFlag=" + activeFlag
				+ ", offenderId=" + offenderId + ", offenderBookId=" + offenderBookId + ", bookingNo=" + bookingNo
				+ ", offenderIdDisplay=" + offenderIdDisplay + ", birthDate=" + birthDate + ", agyLocId=" + agyLocId
				+ ", agyLocName=" + agyLocName + ", livingUnitDescription=" + livingUnitDescription + ", inOutStatus="
				+ inOutStatus + ", middleName=" + middleName + ", inserted=" + inserted + ", errorMessage="
				+ errorMessage + ", age=" + age + ", gender=" + gender + ", offAlerts=" + offAlerts + ", offSupLevel="
				+ offSupLevel + ", nbtLivingUnitId=" + nbtLivingUnitId + ", moduleName=" + moduleName + ", caseloadId="
				+ caseloadId + "]";
	}

}