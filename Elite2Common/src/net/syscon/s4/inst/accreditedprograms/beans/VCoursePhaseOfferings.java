package net.syscon.s4.inst.accreditedprograms.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the V_COURSE_PHASE_OFFERINGS_2 database table.
 * 
 */
public class VCoursePhaseOfferings extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String caseloadType;

	private BigDecimal courseId;

	private BigDecimal crsInternalLocationId;

	private BigDecimal crsServicesAddressId;

	private BigDecimal programCapacity;

	private String programDescription;

	private BigDecimal programId;

	private BigDecimal programListSeq;

	private String programModuleFlag;

	private BigDecimal programNoOfSessions;

	private BigDecimal programPhaseId;

	private BigDecimal programSessionLength;

	private String providerPartyClass;

	private String providerPartyCode;

	private BigDecimal providerPartyId;
	private BigDecimal coursePhaseId;
	private BigDecimal phListSeq;
	private String offeringFlag;
	private String phDescription;
	private BigDecimal phCapacity;
	private BigDecimal phNoOfSessions;
	private BigDecimal phSessionLength;
	private String phModuleFlag;
	private String cpCaseLoadType;

	private String cpCaseLoadTypeDesc;

	private String cpInternalLocationDesc;

	private BigDecimal cpListSeq;
	private String cpActiveFlag;
	private Date cpExpiryDate;
	private Date cpStartDate;
	
	private String agyLocId;

	private Date cpEndDate;
	private BigDecimal cpNoOfSessions;
	private BigDecimal cpSessionLength;
	private BigDecimal cpCapacity;
	private BigDecimal cpPlacementCorporateId;
	private String cpCommentText;
	private String cpCourseActivityType;
	private BigDecimal cpCheckSum;

	private String courseCaseLoadType;
	private BigDecimal cpServicesAddressId;

	private String name;
	private String houseInformation;
	private String streetInformation;
	private String areaInformation;
	private String postalCode;
	private String country;
	
	private String caseloadId;
	private String sealFlag;
	private BigDecimal listSeq;
	private Long internalLocationId;
	private String provStateDesc;
	
	private String cityName;
	private String streetAddress;
	private String suiteNumber;
	
	private String modifyUserId; 

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Long getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public final BigDecimal getCpServicesAddressId() {
		return cpServicesAddressId;
	}

	public final void setCpServicesAddressId(final BigDecimal cpServicesAddressId) {
		this.cpServicesAddressId = cpServicesAddressId;
	}

	public VCoursePhaseOfferings() {
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public BigDecimal getCourseId() {
		return this.courseId;
	}

	public void setCourseId(final BigDecimal courseId) {
		this.courseId = courseId;
	}

	public BigDecimal getCrsInternalLocationId() {
		return this.crsInternalLocationId;
	}

	public void setCrsInternalLocationId(final BigDecimal crsInternalLocationId) {
		this.crsInternalLocationId = crsInternalLocationId;
	}

	public BigDecimal getCrsServicesAddressId() {
		return this.crsServicesAddressId;
	}

	public void setCrsServicesAddressId(final BigDecimal crsServicesAddressId) {
		this.crsServicesAddressId = crsServicesAddressId;
	}

	public BigDecimal getProgramCapacity() {
		return this.programCapacity;
	}

	public void setProgramCapacity(final BigDecimal programCapacity) {
		this.programCapacity = programCapacity;
	}

	public String getProgramDescription() {
		return this.programDescription;
	}

	public void setProgramDescription(final String programDescription) {
		this.programDescription = programDescription;
	}

	public BigDecimal getProgramId() {
		return this.programId;
	}

	public void setProgramId(final BigDecimal programId) {
		this.programId = programId;
	}

	public BigDecimal getProgramListSeq() {
		return this.programListSeq;
	}

	public void setProgramListSeq(final BigDecimal programListSeq) {
		this.programListSeq = programListSeq;
	}

	public String getProgramModuleFlag() {
		return this.programModuleFlag;
	}

	public void setProgramModuleFlag(final String programModuleFlag) {
		this.programModuleFlag = programModuleFlag;
	}

	public BigDecimal getProgramNoOfSessions() {
		return this.programNoOfSessions;
	}

	public void setProgramNoOfSessions(final BigDecimal programNoOfSessions) {
		this.programNoOfSessions = programNoOfSessions;
	}

	public BigDecimal getProgramPhaseId() {
		return this.programPhaseId;
	}

	public void setProgramPhaseId(final BigDecimal programPhaseId) {
		this.programPhaseId = programPhaseId;
	}

	public BigDecimal getProgramSessionLength() {
		return this.programSessionLength;
	}

	public void setProgramSessionLength(final BigDecimal programSessionLength) {
		this.programSessionLength = programSessionLength;
	}

	public String getProviderPartyClass() {
		return this.providerPartyClass;
	}

	public void setProviderPartyClass(final String providerPartyClass) {
		this.providerPartyClass = providerPartyClass;
	}

	public BigDecimal getCoursePhaseId() {
		return coursePhaseId;
	}

	public void setCoursePhaseId(final BigDecimal coursePhaseId) {
		this.coursePhaseId = coursePhaseId;
	}

	public BigDecimal getPhListSeq() {
		return phListSeq;
	}

	public void setPhListSeq(final BigDecimal phListSeq) {
		this.phListSeq = phListSeq;
	}

	public String getOfferingFlag() {
		return offeringFlag;
	}

	public void setOfferingFlag(final String offeringFlag) {
		this.offeringFlag = offeringFlag;
	}

	public String getPhDescription() {
		return phDescription;
	}

	public void setPhDescription(final String phDescription) {
		this.phDescription = phDescription;
	}

	public BigDecimal getPhCapacity() {
		return phCapacity;
	}

	public void setPhCapacity(final BigDecimal phCapacity) {
		this.phCapacity = phCapacity;
	}

	public BigDecimal getPhNoOfSessions() {
		return phNoOfSessions;
	}

	public void setPhNoOfSessions(final BigDecimal phNoOfSessions) {
		this.phNoOfSessions = phNoOfSessions;
	}

	public BigDecimal getPhSessionLength() {
		return phSessionLength;
	}

	public void setPhSessionLength(final BigDecimal phSessionLength) {
		this.phSessionLength = phSessionLength;
	}

	public String getPhModuleFlag() {
		return phModuleFlag;
	}

	public void setPhModuleFlag(final String phModuleFlag) {
		this.phModuleFlag = phModuleFlag;
	}

	public String getCpCaseLoadType() {
		return cpCaseLoadType;
	}

	public void setCpCaseLoadType(final String cpCaseLoadType) {
		this.cpCaseLoadType = cpCaseLoadType;
	}

	public String getCpCaseLoadTypeDesc() {
		return cpCaseLoadTypeDesc;
	}

	public void setCpCaseLoadTypeDesc(final String cpCaseLoadTypeDesc) {
		this.cpCaseLoadTypeDesc = cpCaseLoadTypeDesc;
	}

	public String getCpInternalLocationDesc() {
		return cpInternalLocationDesc;
	}

	public void setCpInternalLocationDesc(final String cpInternalLocationDesc) {
		this.cpInternalLocationDesc = cpInternalLocationDesc;
	}

	public BigDecimal getCpListSeq() {
		return cpListSeq;
	}

	public void setCpListSeq(final BigDecimal cpListSeq) {
		this.cpListSeq = cpListSeq;
	}

	public String getCpActiveFlag() {
		return cpActiveFlag;
	}

	public void setCpActiveFlag(final String cpActiveFlag) {
		this.cpActiveFlag = cpActiveFlag;
	}

	public Date getCpExpiryDate() {
		return cpExpiryDate;
	}

	public void setCpExpiryDate(final Date cpExpiryDate) {
		this.cpExpiryDate = cpExpiryDate;
	}

	public Date getCpStartDate() {
		return cpStartDate;
	}

	public void setCpStartDate(final Date cpStartDate) {
		this.cpStartDate = cpStartDate;
	}

	public Date getCpEndDate() {
		return cpEndDate;
	}

	public void setCpEndDate(final Date cpEndDate) {
		this.cpEndDate = cpEndDate;
	}

	public BigDecimal getCpNoOfSessions() {
		return cpNoOfSessions;
	}

	public void setCpNoOfSessions(final BigDecimal cpNoOfSessions) {
		this.cpNoOfSessions = cpNoOfSessions;
	}

	public BigDecimal getCpSessionLength() {
		return cpSessionLength;
	}

	public void setCpSessionLength(final BigDecimal cpSessionLength) {
		this.cpSessionLength = cpSessionLength;
	}

	public BigDecimal getCpCapacity() {
		return cpCapacity;
	}

	public void setCpCapacity(final BigDecimal cpCapacity) {
		this.cpCapacity = cpCapacity;
	}

	public BigDecimal getCpPlacementCorporateId() {
		return cpPlacementCorporateId;
	}

	public void setCpPlacementCorporateId(final BigDecimal cpPlacementCorporateId) {
		this.cpPlacementCorporateId = cpPlacementCorporateId;
	}

	public String getCpCommentText() {
		return cpCommentText;
	}

	public void setCpCommentText(final String cpCommentText) {
		this.cpCommentText = cpCommentText;
	}

	public String getCpCourseActivityType() {
		return cpCourseActivityType;
	}

	public void setCpCourseActivityType(final String cpCourseActivityType) {
		this.cpCourseActivityType = cpCourseActivityType;
	}

	public BigDecimal getCpCheckSum() {
		return cpCheckSum;
	}

	public void setCpCheckSum(final BigDecimal cpCheckSum) {
		this.cpCheckSum = cpCheckSum;
	}

	public String getCourseCaseLoadType() {
		return courseCaseLoadType;
	}

	public void setCourseCaseLoadType(final String courseCaseLoadType) {
		this.courseCaseLoadType = courseCaseLoadType;
	}

	public String getProviderPartyCode() {
		return this.providerPartyCode;
	}

	public void setProviderPartyCode(final String providerPartyCode) {
		this.providerPartyCode = providerPartyCode;
	}

	public BigDecimal getProviderPartyId() {
		return this.providerPartyId;
	}

	public void setProviderPartyId(final BigDecimal providerPartyId) {
		this.providerPartyId = providerPartyId;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the houseInformation
	 */
	public final String getHouseInformation() {
		return houseInformation;
	}

	/**
	 * @param houseInformation the houseInformation to set
	 */
	public final void setHouseInformation(final String houseInformation) {
		this.houseInformation = houseInformation;
	}

	/**
	 * @return the streetInformation
	 */
	public final String getStreetInformation() {
		return streetInformation;
	}

	/**
	 * @param streetInformation the streetInformation to set
	 */
	public final void setStreetInformation(final String streetInformation) {
		this.streetInformation = streetInformation;
	}

	/**
	 * @return the areaInformation
	 */
	public final String getAreaInformation() {
		return areaInformation;
	}

	/**
	 * @param areaInformation the areaInformation to set
	 */
	public final void setAreaInformation(final String areaInformation) {
		this.areaInformation = areaInformation;
	}

	/**
	 * @return the postalCode
	 */
	public final String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public final void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the country
	 */
	public final String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public final void setCountry(final String country) {
		this.country = country;
	}

	public final String getAgyLocId() {
		return agyLocId;
	}

	public final void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public final String getCaseloadId() {
		return caseloadId;
	}

	public final void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public BigDecimal getListSeq() {
		return listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public String getProvStateDesc() {
		return provStateDesc;
	}

	public void setProvStateDesc(String provStateDesc) {
		this.provStateDesc = provStateDesc;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSuiteNumber() {
		return suiteNumber;
	}

	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}
	
	

}
