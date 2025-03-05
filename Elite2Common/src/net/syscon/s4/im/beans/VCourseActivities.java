package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VCourseActivities extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("agencyContact")
	private String agencyContact;

	@JsonProperty("agyLocDesc")
	private String agyLocDesc;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("areaCode")
	private String areaCode;

	@JsonProperty("capacity")
	private BigDecimal capacity;

	@JsonProperty("city")
	private String city;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("country")
	private String country;

	@JsonProperty("countryDesc")
	private String countryDesc;

	@JsonProperty("courseActivityCode")
	private String courseActivityCode;

	@JsonProperty("courseActivityDesc")
	private String courseActivityDesc;

	@JsonProperty("courseClass")
	private String courseClass;

	@JsonProperty("coursePhaseId")
	private Long coursePhaseId;

	@JsonProperty("crsActyId")
	private Long crsActyId;

	@JsonProperty("environment")
	private String environment;

	@JsonProperty("environmentDesc")
	private String environmentDesc;

	@JsonProperty("internalLocationDesc")
	private String internalLocationDesc;

	@JsonProperty("internalLocationId")
	private Long internalLocationId;

	@JsonProperty("nomsRegionCode")
	private String nomsRegionCode;

	@JsonProperty("phaseDesc")
	private String phaseDesc;

	@JsonProperty("placementCorporateId")
	private Long placementCorporateId;

	@JsonProperty("postalCode")
	private String postalCode;

	@JsonProperty("pqsNumber")
	private BigDecimal pqsNumber;

	@JsonProperty("programCategory")
	private String programCategory;

	@JsonProperty("programCategoryDesc")
	private String programCategoryDesc;

	@JsonProperty("programId")
	private Long programId;

	@JsonProperty("programServicesDesc")
	private String programServicesDesc;

	@JsonProperty("providerClass")
	private String providerClass;

	@JsonProperty("providerCode")
	private String providerCode;

	@JsonProperty("providerId")
	private Long providerId;

	@JsonProperty("providerName")
	private String providerName;

	@JsonProperty("scheduleEndDate")
	private Date scheduleEndDate;

	@JsonProperty("scheduleStartDate")
	private Date scheduleStartDate;

	@JsonProperty("servicesAddressId")
	private Long servicesAddressId;

	@JsonProperty("state")
	private String state;

	@JsonProperty("streetInformation")
	private String streetInformation;

	@JsonProperty("suiteNumber")
	private String suiteNumber;

	@JsonProperty("vacancy")
	private BigDecimal vacancy;

	@JsonProperty("nbtGender")
	private String nbtGender;

	@JsonProperty("nbtAge")
	private String nbtAge;

	@JsonProperty("nbtRace")
	private String nbtRace;

	@JsonProperty("nbtFacility")
	private String nbtFacility;

	@JsonProperty("nbtInclude")
	private String nbtInclude;

	@JsonProperty("nbtExclude")
	private String nbtExclude;

	@JsonProperty("nbtStatus")
	private String nbtStatus;

	@JsonProperty("nbtAdvSearch")
	private String nbtAdvSearch;

	@JsonProperty("nbtRegion")
	private String nbtRegion;

	@JsonProperty("nbtCallingForm")
	private String nbtCallingForm;

	@JsonProperty("nbtAgyLocId")
	private String nbtAgyLocId;

	@JsonProperty("parentAreaCode")
	private String parentAreaCode;

	public VCourseActivities() {
		// VCourseActivities
	}

	/**
	 * @return the parentAreaCode
	 */
	public String getParentAreaCode() {
		return parentAreaCode;
	}

	/**
	 * @param parentAreaCode the parentAreaCode to set
	 */
	public void setParentAreaCode(final String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the agencyContact
	 */
	public String getAgencyContact() {
		return agencyContact;
	}

	/**
	 * @param agencyContact the agencyContact to set
	 */
	public void setAgencyContact(final String agencyContact) {
		this.agencyContact = agencyContact;
	}

	/**
	 * @return the agyLocDesc
	 */
	public String getAgyLocDesc() {
		return agyLocDesc;
	}

	/**
	 * @param agyLocDesc the agyLocDesc to set
	 */
	public void setAgyLocDesc(final String agyLocDesc) {
		this.agyLocDesc = agyLocDesc;
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
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(final String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return the capacity
	 */
	public BigDecimal getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(final BigDecimal capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @return the countryDesc
	 */
	public String getCountryDesc() {
		return countryDesc;
	}

	/**
	 * @param countryDesc the countryDesc to set
	 */
	public void setCountryDesc(final String countryDesc) {
		this.countryDesc = countryDesc;
	}

	/**
	 * @return the courseActivityCode
	 */
	public String getCourseActivityCode() {
		return courseActivityCode;
	}

	/**
	 * @param courseActivityCode the courseActivityCode to set
	 */
	public void setCourseActivityCode(final String courseActivityCode) {
		this.courseActivityCode = courseActivityCode;
	}

	/**
	 * @return the courseActivityDesc
	 */
	public String getCourseActivityDesc() {
		return courseActivityDesc;
	}

	/**
	 * @param courseActivityDesc the courseActivityDesc to set
	 */
	public void setCourseActivityDesc(final String courseActivityDesc) {
		this.courseActivityDesc = courseActivityDesc;
	}

	/**
	 * @return the courseClass
	 */
	public String getCourseClass() {
		return courseClass;
	}

	/**
	 * @param courseClass the courseClass to set
	 */
	public void setCourseClass(final String courseClass) {
		this.courseClass = courseClass;
	}

	/**
	 * @return the coursePhaseId
	 */
	public Long getCoursePhaseId() {
		return coursePhaseId;
	}

	/**
	 * @param coursePhaseId the coursePhaseId to set
	 */
	public void setCoursePhaseId(final Long coursePhaseId) {
		this.coursePhaseId = coursePhaseId;
	}

	/**
	 * @return the crsActyId
	 */
	public Long getCrsActyId() {
		return crsActyId;
	}

	/**
	 * @param crsActyId the crsActyId to set
	 */
	public void setCrsActyId(final Long crsActyId) {
		this.crsActyId = crsActyId;
	}

	/**
	 * @return the environment
	 */
	public String getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(final String environment) {
		this.environment = environment;
	}

	/**
	 * @return the environmentDesc
	 */
	public String getEnvironmentDesc() {
		return environmentDesc;
	}

	/**
	 * @param environmentDesc the environmentDesc to set
	 */
	public void setEnvironmentDesc(final String environmentDesc) {
		this.environmentDesc = environmentDesc;
	}

	/**
	 * @return the internalLocationDesc
	 */
	public String getInternalLocationDesc() {
		return internalLocationDesc;
	}

	/**
	 * @param internalLocationDesc the internalLocationDesc to set
	 */
	public void setInternalLocationDesc(final String internalLocationDesc) {
		this.internalLocationDesc = internalLocationDesc;
	}

	/**
	 * @return the internalLocationId
	 */
	public Long getInternalLocationId() {
		return internalLocationId;
	}

	/**
	 * @param internalLocationId the internalLocationId to set
	 */
	public void setInternalLocationId(final Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	/**
	 * @return the nomsRegionCode
	 */
	public String getNomsRegionCode() {
		return nomsRegionCode;
	}

	/**
	 * @param nomsRegionCode the nomsRegionCode to set
	 */
	public void setNomsRegionCode(final String nomsRegionCode) {
		this.nomsRegionCode = nomsRegionCode;
	}

	/**
	 * @return the phaseDesc
	 */
	public String getPhaseDesc() {
		return phaseDesc;
	}

	/**
	 * @param phaseDesc the phaseDesc to set
	 */
	public void setPhaseDesc(final String phaseDesc) {
		this.phaseDesc = phaseDesc;
	}

	/**
	 * @return the placementCorporateId
	 */
	public Long getPlacementCorporateId() {
		return placementCorporateId;
	}

	/**
	 * @param placementCorporateId the placementCorporateId to set
	 */
	public void setPlacementCorporateId(final Long placementCorporateId) {
		this.placementCorporateId = placementCorporateId;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the pqsNumber
	 */
	public BigDecimal getPqsNumber() {
		return pqsNumber;
	}

	/**
	 * @param pqsNumber the pqsNumber to set
	 */
	public void setPqsNumber(final BigDecimal pqsNumber) {
		this.pqsNumber = pqsNumber;
	}

	/**
	 * @return the programCategory
	 */
	public String getProgramCategory() {
		return programCategory;
	}

	/**
	 * @param programCategory the programCategory to set
	 */
	public void setProgramCategory(final String programCategory) {
		this.programCategory = programCategory;
	}

	/**
	 * @return the programCategoryDesc
	 */
	public String getProgramCategoryDesc() {
		return programCategoryDesc;
	}

	/**
	 * @param programCategoryDesc the programCategoryDesc to set
	 */
	public void setProgramCategoryDesc(final String programCategoryDesc) {
		this.programCategoryDesc = programCategoryDesc;
	}

	/**
	 * @return the programId
	 */
	public Long getProgramId() {
		return programId;
	}

	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(final Long programId) {
		this.programId = programId;
	}

	/**
	 * @return the programServicesDesc
	 */
	public String getProgramServicesDesc() {
		return programServicesDesc;
	}

	/**
	 * @param programServicesDesc the programServicesDesc to set
	 */
	public void setProgramServicesDesc(final String programServicesDesc) {
		this.programServicesDesc = programServicesDesc;
	}

	/**
	 * @return the providerClass
	 */
	public String getProviderClass() {
		return providerClass;
	}

	/**
	 * @param providerClass the providerClass to set
	 */
	public void setProviderClass(final String providerClass) {
		this.providerClass = providerClass;
	}

	/**
	 * @return the providerCode
	 */
	public String getProviderCode() {
		return providerCode;
	}

	/**
	 * @param providerCode the providerCode to set
	 */
	public void setProviderCode(final String providerCode) {
		this.providerCode = providerCode;
	}

	/**
	 * @return the providerId
	 */
	public Long getProviderId() {
		return providerId;
	}

	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(final Long providerId) {
		this.providerId = providerId;
	}

	/**
	 * @return the providerName
	 */
	public String getProviderName() {
		return providerName;
	}

	/**
	 * @param providerName the providerName to set
	 */
	public void setProviderName(final String providerName) {
		this.providerName = providerName;
	}

	/**
	 * @return the scheduleEndDate
	 */
	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	/**
	 * @param scheduleEndDate the scheduleEndDate to set
	 */
	public void setScheduleEndDate(final Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	/**
	 * @return the scheduleStartDate
	 */
	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	/**
	 * @param scheduleStartDate the scheduleStartDate to set
	 */
	public void setScheduleStartDate(final Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	/**
	 * @return the servicesAddressId
	 */
	public Long getServicesAddressId() {
		return servicesAddressId;
	}

	/**
	 * @param servicesAddressId the servicesAddressId to set
	 */
	public void setServicesAddressId(final Long servicesAddressId) {
		this.servicesAddressId = servicesAddressId;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @return the streetInformation
	 */
	public String getStreetInformation() {
		return streetInformation;
	}

	/**
	 * @param streetInformation the streetInformation to set
	 */
	public void setStreetInformation(final String streetInformation) {
		this.streetInformation = streetInformation;
	}

	/**
	 * @return the suiteNumber
	 */
	public String getSuiteNumber() {
		return suiteNumber;
	}

	/**
	 * @param suiteNumber the suiteNumber to set
	 */
	public void setSuiteNumber(final String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	/**
	 * @return the vacancy
	 */
	public BigDecimal getVacancy() {
		return vacancy;
	}

	/**
	 * @param vacancy the vacancy to set
	 */
	public void setVacancy(final BigDecimal vacancy) {
		this.vacancy = vacancy;
	}

	/**
	 * @return the nbtGender
	 */
	public String getNbtGender() {
		return nbtGender;
	}

	/**
	 * @param nbtGender the nbtGender to set
	 */
	public void setNbtGender(final String nbtGender) {
		this.nbtGender = nbtGender;
	}

	/**
	 * @return the nbtAge
	 */
	public String getNbtAge() {
		return nbtAge;
	}

	/**
	 * @param nbtAge the nbtAge to set
	 */
	public void setNbtAge(final String nbtAge) {
		this.nbtAge = nbtAge;
	}

	/**
	 * @return the nbtRace
	 */
	public String getNbtRace() {
		return nbtRace;
	}

	/**
	 * @param nbtRace the nbtRace to set
	 */
	public void setNbtRace(final String nbtRace) {
		this.nbtRace = nbtRace;
	}

	/**
	 * @return the nbtFacility
	 */
	public String getNbtFacility() {
		return nbtFacility;
	}

	/**
	 * @param nbtFacility the nbtFacility to set
	 */
	public void setNbtFacility(final String nbtFacility) {
		this.nbtFacility = nbtFacility;
	}

	/**
	 * @return the nbtInclude
	 */
	public String getNbtInclude() {
		return nbtInclude;
	}

	/**
	 * @param nbtInclude the nbtInclude to set
	 */
	public void setNbtInclude(final String nbtInclude) {
		this.nbtInclude = nbtInclude;
	}

	/**
	 * @return the nbtExclude
	 */
	public String getNbtExclude() {
		return nbtExclude;
	}

	/**
	 * @param nbtExclude the nbtExclude to set
	 */
	public void setNbtExclude(final String nbtExclude) {
		this.nbtExclude = nbtExclude;
	}

	/**
	 * @return the nbtStatus
	 */
	public String getNbtStatus() {
		return nbtStatus;
	}

	/**
	 * @param nbtStatus the nbtStatus to set
	 */
	public void setNbtStatus(final String nbtStatus) {
		this.nbtStatus = nbtStatus;
	}

	/**
	 * @return the nbtAdvSearch
	 */
	public String getNbtAdvSearch() {
		return nbtAdvSearch;
	}

	/**
	 * @param nbtAdvSearch the nbtAdvSearch to set
	 */
	public void setNbtAdvSearch(final String nbtAdvSearch) {
		this.nbtAdvSearch = nbtAdvSearch;
	}

	/**
	 * @return the nbtRegion
	 */
	public String getNbtRegion() {
		return nbtRegion;
	}

	/**
	 * @param nbtRegion the nbtRegion to set
	 */
	public void setNbtRegion(final String nbtRegion) {
		this.nbtRegion = nbtRegion;
	}

	/**
	 * @return the nbtCallingForm
	 */
	public String getNbtCallingForm() {
		return nbtCallingForm;
	}

	/**
	 * @param nbtCallingForm the nbtCallingForm to set
	 */
	public void setNbtCallingForm(final String nbtCallingForm) {
		this.nbtCallingForm = nbtCallingForm;
	}

	/**
	 * @return the nbtAgyLocId
	 */
	public String getNbtAgyLocId() {
		return nbtAgyLocId;
	}

	/**
	 * @param nbtAgyLocId the nbtAgyLocId to set
	 */
	public void setNbtAgyLocId(final String nbtAgyLocId) {
		this.nbtAgyLocId = nbtAgyLocId;
	}

}
