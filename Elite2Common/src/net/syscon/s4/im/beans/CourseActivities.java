package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CourseActivities extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("crsActyId")
	private Long crsActyId;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("agencyLocationType")
	private String agencyLocationType;

	@JsonProperty("lastDescription")
	private String lastDescription;

	@JsonProperty("uptoDescription")
	private String uptoDescription;

	@JsonProperty("uptoListSeq")
	private Integer uptoListSeq;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("weeks")
	private Long weeks;

	@JsonProperty("checkSum")
	private Long checkSum;

	@JsonProperty("nbtreScheduleDate")
	private Date nbtreScheduleDate;

	@JsonProperty("nbtPhaseDescription")
	private String nbtPhaseDescription;

	@JsonProperty("nbtSessionNo")
	private Long nbtSessionNo;

	@JsonProperty("nbtCrsSchId")
	private Long nbtCrsSchId;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("allowDoubleBookFlag")
	private String allowDoubleBookFlag;

	@JsonProperty("beneficiaryContact")
	private String beneficiaryContact;

	@JsonProperty("beneficiaryName")
	private String beneficiaryName;

	@JsonProperty("beneficiaryType")
	private String beneficiaryType;

	@JsonProperty("capacity")
	private Long capacity;

	@JsonProperty("caseloadId")
	private String caseloadId;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("code")
	private String code;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("courseActivityType")
	private String courseActivityType;

	@JsonProperty("courseClass")
	private String courseClass;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("holidayFlag")
	private String holidayFlag;

	@JsonProperty("iepLevel")
	private String iepLevel;

	@JsonProperty("internalLocationId")
	private Long internalLocationId;

	@JsonProperty("listSeq")
	private Long listSeq;

	@JsonProperty("lastListSeq")
	private Long lastListSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("multiPhaseSchedulingFlag")
	private String multiPhaseSchedulingFlag;

	@JsonProperty("noOfSessions")
	private Long noOfSessions;
	
	@JsonProperty("noOfSessionsTemp")
	private Long noOfSessionsTemp;

	@JsonProperty("placementText")
	private String placementText;

	@JsonProperty("programId")
	private Long programId;

	@JsonProperty("providerPartyClass")
	private String providerPartyClass;

	@JsonProperty("providerPartyCode")
	private String providerPartyCode;

	@JsonProperty("providerPartyId")
	private Long providerPartyId;

	@JsonProperty("providerType")
	private String providerType;

	@JsonProperty("scheduleEndDate")
	private Date scheduleEndDate;

	@JsonProperty("scheduleNotes")
	private String scheduleNotes;

	@JsonProperty("scheduleStartDate")
	private Date scheduleStartDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("servicesAddressId")
	private Long servicesAddressId;

	@JsonProperty("sessionLength")
	private Long sessionLength;

	@JsonProperty("schEndDate")
	private Date schEndDate;

	@JsonProperty("postUpdate")
	private String postUpdate;

	@JsonProperty("parentCrsActyId")
	private Long parentCrsActyId;

	@JsonProperty("placementCorporateId")
	private Long placementCorporateId;

	@JsonProperty("serviceCode")
	private String serviceCode;

	@JsonProperty("serviceDescription")
	private String serviceDescription;

	@JsonProperty("serviceCategory")
	private String serviceCategory;

	@JsonProperty("offeringCode")
	private String offeringCode;

	@JsonProperty("offeringDescription")
	private String offeringDescription;

	@JsonProperty("offeringEndDate")
	private Date offeringEndDate;

	@JsonProperty("offeringStartDate")
	private Date offeringStartDate;

	@JsonProperty("rowId")
	private String rowId;

	@JsonProperty("actualSessions")
	private Long actualSessions;

	@JsonProperty("phaseStartDate")
	private Date phaseStartDate;

	@JsonProperty("moduleFlag")
	private String moduleFlag;

	@JsonProperty("phaseDescription")
	private String phaseDescription;

	@JsonProperty("totalSessions")
	private Long totalSessions;

	@JsonProperty("phaseInstanceId")
	private Long phaseInstanceId;

	@JsonProperty("programIstanceId")
	private Long programIstanceId;
	
	@JsonProperty("startFlag")
	private String startFlag;
	@JsonProperty("nbtDescription")
	private String nbtDescription;
	@JsonProperty("nosessions")
	private Long nosessions;
	
	
	
	@JsonProperty("provStateCode")
	private String provStateCode;
	
	@JsonProperty("cityCode")
	private String cityCode;
	
	@JsonProperty("cityName")
	private String cityName;
	
	@JsonProperty("streetInformation")
	private String streetInformation;
	
	@JsonProperty("suiteNumber")
	private String suiteNumber;
	
	private Integer caCount;
	
	private Integer carCount;

	private Integer csrCount;
	
	private Integer csCount;
	
	private Integer ocaCount;
	
	private Integer oppCount;
	
	private Integer ppbofcCount;
	
	private Integer ppcCount;
	
	
	@JsonProperty("offenderBookId")
	private BigDecimal offenderBookId;
	
	@JsonProperty("offenderNonAssociations")
	private List<OffenderNonAssociations> offenderNonAssociations;
	
	@JsonProperty("offenderNonAssociationsByGang")
	private List<Offenders> offenderNonAssociationsByGang;
	

	@JsonProperty("offenderNonAssociationsByInd")
	private List<Offenders> offenderNonAssociationsByInd;
	

	public List<Offenders> getOffenderNonAssociationsByInd() {
		return offenderNonAssociationsByInd;
	}

	public void setOffenderNonAssociationsByInd(List<Offenders> offenderNonAssociationsByInd) {
		this.offenderNonAssociationsByInd = offenderNonAssociationsByInd;
	}

	@JsonProperty("offenderId")
	private Long offenderId;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	public List<Offenders> getOffenderNonAssociationsByGang() {
		return offenderNonAssociationsByGang;
	}

	public void setOffenderNonAssociationsByGang(List<Offenders> offenderNonAssociationsByGang) {
		this.offenderNonAssociationsByGang = offenderNonAssociationsByGang;
	}

	@JsonProperty("offenderName")
	private String offenderName;

	@JsonProperty("prgServiceList")
	private List<VCourseActivities> prgServiceList;
	private String providerName;

	@JsonProperty("offenderList")
	private List<VOffenderPrgObligations> offenderList;
	@JsonProperty("offenderProgramStatus")
	private String offenderProgramStatus;

	
	@JsonProperty("waitlistDecisionCode")
	private String waitlistDecisionCode;

	@JsonProperty("offenderEndDate")
	private Date offenderEndDate;
	
	@JsonProperty("allocatedOffender")
	private Long allocatedOffender;
	
	@JsonProperty("weekDay")
	private List<String> weekDay;
	
	@JsonProperty("mondayFlag")
    private String mondayFlag;
	
	
	@JsonProperty("tuesdayFlag")
    private String tuesdayFlag;
	
	
	@JsonProperty("wednesdayFlag")
    private String wednesdayFlag;
	
	@JsonProperty("thursdayFlag")
    private String thursdayFlag;
	
	@JsonProperty("fridayFlag")
    private String fridayFlag;
	
	@JsonProperty("saturdayFlag")
    private String saturdayFlag;
	
	@JsonProperty("sundayFlag")
    private String sundayFlag;
	
	@JsonProperty("avlblCapacityFlag")
	private String avlblCapacityFlag;
	
	@JsonProperty("referredOffenders")
	private Long referredOffenders;
	
	@JsonProperty("targetOffFlag")
	private String targetOffFlag;
    private String provStateDesc;
	
	public String getProvStateDesc() {
		return provStateDesc;
	}

	public void setProvStateDesc(String provStateDesc) {
		this.provStateDesc = provStateDesc;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	private String streetAddress;

	public Integer getUptoListSeq() {
		return uptoListSeq;
	}

	public void setUptoListSeq(Integer uptoListSeq) {
		this.uptoListSeq = uptoListSeq;
	}

	public Date getNbtreScheduleDate() {
		return nbtreScheduleDate;
	}

	public void setNbtreScheduleDate(Date nbtreScheduleDate) {
		this.nbtreScheduleDate = nbtreScheduleDate;
	}

	public String getNbtPhaseDescription() {
		return nbtPhaseDescription;
	}

	public void setNbtPhaseDescription(String nbtPhaseDescription) {
		this.nbtPhaseDescription = nbtPhaseDescription;
	}

	public Long getNbtSessionNo() {
		return nbtSessionNo;
	}

	public void setNbtSessionNo(Long nbtSessionNo) {
		this.nbtSessionNo = nbtSessionNo;
	}

	public Long getNbtCrsSchId() {
		return nbtCrsSchId;
	}

	public void setNbtCrsSchId(Long nbtCrsSchId) {
		this.nbtCrsSchId = nbtCrsSchId;
	}

	public Long getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(Long checkSum) {
		this.checkSum = checkSum;
	}

	public Long getLastListSeq() {
		return lastListSeq;
	}

	public void setLastListSeq(Long lastListSeq) {
		this.lastListSeq = lastListSeq;
	}

	public String getLastDescription() {
		return lastDescription;
	}

	public void setLastDescription(String lastDescription) {
		this.lastDescription = lastDescription;
	}

	public String getUptoDescription() {
		return uptoDescription;
	}

	public void setUptoDescription(String uptoDescription) {
		this.uptoDescription = uptoDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getWeeks() {
		return weeks;
	}

	public void setWeeks(Long weeks) {
		this.weeks = weeks;
	}

	public Date getPhaseStartDate() {
		return phaseStartDate;
	}

	public void setPhaseStartDate(Date phaseStartDate) {
		this.phaseStartDate = phaseStartDate;
	}

	public String getModuleFlag() {
		return moduleFlag;
	}

	public void setModuleFlag(String moduleFlag) {
		this.moduleFlag = moduleFlag;
	}

	public String getPhaseDescription() {
		return phaseDescription;
	}

	public void setPhaseDescription(String phaseDescription) {
		this.phaseDescription = phaseDescription;
	}

	public Long getTotalSessions() {
		return totalSessions;
	}

	public void setTotalSessions(Long totalSessions) {
		this.totalSessions = totalSessions;
	}

	public Long getPhaseInstanceId() {
		return phaseInstanceId;
	}

	public void setPhaseInstanceId(Long phaseInstanceId) {
		this.phaseInstanceId = phaseInstanceId;
	}

	public Long getProgramIstanceId() {
		return programIstanceId;
	}

	public void setProgramIstanceId(Long programIstanceId) {
		this.programIstanceId = programIstanceId;
	}

	public Long getActualSessions() {
		return actualSessions;
	}

	public void setActualSessions(Long actualSessions) {
		this.actualSessions = actualSessions;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public String getOfferingCode() {
		return offeringCode;
	}

	public void setOfferingCode(String offeringCode) {
		this.offeringCode = offeringCode;
	}

	public String getOfferingDescription() {
		return offeringDescription;
	}

	public void setOfferingDescription(String offeringDescription) {
		this.offeringDescription = offeringDescription;
	}

	public Date getOfferingEndDate() {
		return offeringEndDate;
	}

	public void setOfferingEndDate(Date offeringEndDate) {
		this.offeringEndDate = offeringEndDate;
	}

	public Date getOfferingStartDate() {
		return offeringStartDate;
	}

	public void setOfferingStartDate(Date offeringStartDate) {
		this.offeringStartDate = offeringStartDate;
	}

	private String programCode;
	private int returnValue;
	private String programIdVal;
	private String internalLocationIdVal;
	private String commentTemp;
	private String internalLocationIdValTemp;

	private String name;
	private String houseInformation;
	private String areaInformation;
	private String postalCode;
	private String country;
	private Long internaLocationId;
	private BigDecimal seqOne;

	private Long offPrgrefId;
	@JsonProperty("service")
	private String service;
	
	@JsonProperty("conflictMsg")
	private String conflictMsg;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCommentTemp() {
		return commentTemp;
	}

	/**
	 * @return the internaLocationId
	 */
	public Long getInternaLocationId() {
		return internaLocationId;
	}

	/**
	 * @param internaLocationId the internaLocationId to set
	 */
	public void setInternaLocationId(Long internaLocationId) {
		this.internaLocationId = internaLocationId;
	}

	public void setCommentTemp(String commentTemp) {
		this.commentTemp = commentTemp;
	}

	public String getInternalLocationIdValTemp() {
		return internalLocationIdValTemp;
	}

	public Long getNoOfSessionsTemp() {
		return noOfSessionsTemp;
	}

	public void setNoOfSessionsTemp(Long noOfSessionsTemp) {
		this.noOfSessionsTemp = noOfSessionsTemp;
	}

	public void setInternalLocationIdValTemp(String internalLocationIdValTemp) {
		this.internalLocationIdValTemp = internalLocationIdValTemp;
	}

	public String getInternalLocationIdVal() {
		return internalLocationIdVal;
	}

	public void setInternalLocationIdVal(String internalLocationIdVal) {
		this.internalLocationIdVal = internalLocationIdVal;
	}

	public String getProgramIdVal() {
		return programIdVal;
	}

	public void setProgramIdVal(String programIdVal) {
		this.programIdVal = programIdVal;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

	/**
	 * Creates new OffenderAlerts class Object
	 */
	public CourseActivities() {
		// CourseActivities
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
	public void setCrsActyId(Long crsActyId) {
		this.crsActyId = crsActyId;
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
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the agencyLocationType
	 */
	public String getAgencyLocationType() {
		return agencyLocationType;
	}

	/**
	 * @param agencyLocationType the agencyLocationType to set
	 */
	public void setAgencyLocationType(String agencyLocationType) {
		this.agencyLocationType = agencyLocationType;
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
	 * @return the allowDoubleBookFlag
	 */
	public String getAllowDoubleBookFlag() {
		return allowDoubleBookFlag;
	}

	/**
	 * @param allowDoubleBookFlag the allowDoubleBookFlag to set
	 */
	public void setAllowDoubleBookFlag(String allowDoubleBookFlag) {
		this.allowDoubleBookFlag = allowDoubleBookFlag;
	}

	/**
	 * @return the beneficiaryContact
	 */
	public String getBeneficiaryContact() {
		return beneficiaryContact;
	}

	/**
	 * @param beneficiaryContact the beneficiaryContact to set
	 */
	public void setBeneficiaryContact(String beneficiaryContact) {
		this.beneficiaryContact = beneficiaryContact;
	}

	/**
	 * @return the beneficiaryName
	 */
	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	/**
	 * @param beneficiaryName the beneficiaryName to set
	 */
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	/**
	 * @return the beneficiaryType
	 */
	public String getBeneficiaryType() {
		return beneficiaryType;
	}

	/**
	 * @param beneficiaryType the beneficiaryType to set
	 */
	public void setBeneficiaryType(String beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	/**
	 * @return the capacity
	 */
	public Long getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the caseloadId
	 */
	public String getCaseloadId() {
		return caseloadId;
	}

	/**
	 * @param caseloadId the caseloadId to set
	 */
	public void setCaseloadId(String caseloadId) {
		this.caseloadId = caseloadId;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType the caseloadType to set
	 */
	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the courseActivityType
	 */
	public String getCourseActivityType() {
		return courseActivityType;
	}

	/**
	 * @param courseActivityType the courseActivityType to set
	 */
	public void setCourseActivityType(String courseActivityType) {
		this.courseActivityType = courseActivityType;
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
	public void setCourseClass(String courseClass) {
		this.courseClass = courseClass;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the holidayFlag
	 */
	public String getHolidayFlag() {
		return holidayFlag;
	}

	/**
	 * @param holidayFlag the holidayFlag to set
	 */
	public void setHolidayFlag(String holidayFlag) {
		this.holidayFlag = holidayFlag;
	}

	/**
	 * @return the iepLevel
	 */
	public String getIepLevel() {
		return iepLevel;
	}

	/**
	 * @param iepLevel the iepLevel to set
	 */
	public void setIepLevel(String iepLevel) {
		this.iepLevel = iepLevel;
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
	public void setInternalLocationId(Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	/**
	 * @return the listSeq
	 */
	public Long getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq the listSeq to set
	 */
	public void setListSeq(Long listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the multiPhaseSchedulingFlag
	 */
	public String getMultiPhaseSchedulingFlag() {
		return multiPhaseSchedulingFlag;
	}

	/**
	 * @param multiPhaseSchedulingFlag the multiPhaseSchedulingFlag to set
	 */
	public void setMultiPhaseSchedulingFlag(String multiPhaseSchedulingFlag) {
		this.multiPhaseSchedulingFlag = multiPhaseSchedulingFlag;
	}

	/**
	 * @return the noOfSessions
	 */
	public Long getNoOfSessions() {
		return noOfSessions;
	}

	/**
	 * @param noOfSessions the noOfSessions to set
	 */
	public void setNoOfSessions(Long noOfSessions) {
		this.noOfSessions = noOfSessions;
	}

	/**
	 * @return the placementText
	 */
	public String getPlacementText() {
		return placementText;
	}

	/**
	 * @param placementText the placementText to set
	 */
	public void setPlacementText(String placementText) {
		this.placementText = placementText;
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
	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	/**
	 * @return the providerPartyClass
	 */
	public String getProviderPartyClass() {
		return providerPartyClass;
	}

	/**
	 * @param providerPartyClass the providerPartyClass to set
	 */
	public void setProviderPartyClass(String providerPartyClass) {
		this.providerPartyClass = providerPartyClass;
	}

	/**
	 * @return the providerPartyCode
	 */
	public String getProviderPartyCode() {
		return providerPartyCode;
	}

	/**
	 * @param providerPartyCode the providerPartyCode to set
	 */
	public void setProviderPartyCode(String providerPartyCode) {
		this.providerPartyCode = providerPartyCode;
	}

	/**
	 * @return the providerPartyId
	 */
	public Long getProviderPartyId() {
		return providerPartyId;
	}

	/**
	 * @param providerPartyId the providerPartyId to set
	 */
	public void setProviderPartyId(Long providerPartyId) {
		this.providerPartyId = providerPartyId;
	}

	/**
	 * @return the providerType
	 */
	public String getProviderType() {
		return providerType;
	}

	/**
	 * @param providerType the providerType to set
	 */
	public void setProviderType(String providerType) {
		this.providerType = providerType;
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
	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	/**
	 * @return the scheduleNotes
	 */
	public String getScheduleNotes() {
		return scheduleNotes;
	}

	/**
	 * @param scheduleNotes the scheduleNotes to set
	 */
	public void setScheduleNotes(String scheduleNotes) {
		this.scheduleNotes = scheduleNotes;
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
	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
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
	public void setServicesAddressId(Long servicesAddressId) {
		this.servicesAddressId = servicesAddressId;
	}

	/**
	 * @return the sessionLength
	 */
	public Long getSessionLength() {
		return sessionLength;
	}

	/**
	 * @param sessionLength the sessionLength to set
	 */
	public void setSessionLength(Long sessionLength) {
		this.sessionLength = sessionLength;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getSchEndDate() {
		return schEndDate;
	}

	public void setSchEndDate(Date schEndDate) {
		this.schEndDate = schEndDate;
	}

	public String getPostUpdate() {
		return postUpdate;
	}

	public void setPostUpdate(String postUpdate) {
		this.postUpdate = postUpdate;
	}

	public Long getParentCrsActyId() {
		return parentCrsActyId;
	}

	public void setParentCrsActyId(Long parentCrsActyId) {
		this.parentCrsActyId = parentCrsActyId;
	}

	public Long getPlacementCorporateId() {
		return placementCorporateId;
	}

	public void setPlacementCorporateId(Long placementCorporateId) {
		this.placementCorporateId = placementCorporateId;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowid(String rowId) {
		this.rowId = rowId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the houseInformation
	 */
	public String getHouseInformation() {
		return houseInformation;
	}

	/**
	 * @param houseInformation the houseInformation to set
	 */
	public void setHouseInformation(final String houseInformation) {
		this.houseInformation = houseInformation;
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
	 * @return the areaInformation
	 */
	public String getAreaInformation() {
		return areaInformation;
	}

	/**
	 * @param areaInformation the areaInformation to set
	 */
	public void setAreaInformation(final String areaInformation) {
		this.areaInformation = areaInformation;
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

	public final BigDecimal getSeqOne() {
		return seqOne;
	}

	public final void setSeqOne(BigDecimal seqOne) {
		this.seqOne = seqOne;
	}

	public Long getOffPrgrefId() {
		return offPrgrefId;
	}

	public void setOffPrgrefId(Long offPrgrefId) {
		this.offPrgrefId = offPrgrefId;
	}

	public final String getStartFlag() {
		return startFlag;
	}

	public final void setStartFlag(String startFlag) {
		this.startFlag = startFlag;
	}

	public final String getNbtDescription() {
		return nbtDescription;
	}

	public final void setNbtDescription(String nbtDescription) {
		this.nbtDescription = nbtDescription;
	}

	public final Long getNosessions() {
		return nosessions;
	}

	public final void setNosessions(Long nosessions) {
		this.nosessions = nosessions;
	}

	public BigDecimal getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public List<OffenderNonAssociations> getOffenderNonAssociations() {
		return offenderNonAssociations;
	}

	public void setOffenderNonAssociations(List<OffenderNonAssociations> offenderNonAssociations) {
		this.offenderNonAssociations = offenderNonAssociations;
	}
	

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	public List<VCourseActivities> getPrgServiceList() {
		return prgServiceList;
	}

	public void setPrgServiceList(List<VCourseActivities> prgServiceList) {
		this.prgServiceList = prgServiceList;
	}

	public List<VOffenderPrgObligations> getOffenderList() {
		return offenderList;
	}

	public void setOffenderList(List<VOffenderPrgObligations> offenderList) {
		this.offenderList = offenderList;
	}

	public String getConflictMsg() {
		return conflictMsg;
	}

	public void setConflictMsg(String conflictMsg) {
		this.conflictMsg = conflictMsg;
	}

	public Long getAllocatedOffender() {
		return allocatedOffender;
	}

	public void setAllocatedOffender(Long allocatedOffender) {
		this.allocatedOffender = allocatedOffender;
	}

	public List<String> getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(List<String> weekDay) {
		this.weekDay = weekDay;
	}

	public String getMondayFlag() {
		return mondayFlag;
	}

	public void setMondayFlag(String mondayFlag) {
		this.mondayFlag = mondayFlag;
	}

	public String getTuesdayFlag() {
		return tuesdayFlag;
	}

	public void setTuesdayFlag(String tuesdayFlag) {
		this.tuesdayFlag = tuesdayFlag;
	}

	public String getWednesdayFlag() {
		return wednesdayFlag;
	}

	public void setWednesdayFlag(String wednesdayFlag) {
		this.wednesdayFlag = wednesdayFlag;
	}

	public String getThursdayFlag() {
		return thursdayFlag;
	}

	public void setThursdayFlag(String thursdayFlag) {
		this.thursdayFlag = thursdayFlag;
	}

	public String getFridayFlag() {
		return fridayFlag;
	}

	public void setFridayFlag(String fridayFlag) {
		this.fridayFlag = fridayFlag;
	}

	public String getSaturdayFlag() {
		return saturdayFlag;
	}

	public void setSaturdayFlag(String saturdayFlag) {
		this.saturdayFlag = saturdayFlag;
	}

	public String getSundayFlag() {
		return sundayFlag;
	}

	public void setSundayFlag(String sundayFlag) {
		this.sundayFlag = sundayFlag;
	}

	public String getAvlblCapacityFlag() {
		return avlblCapacityFlag;
	}

	public void setAvlblCapacityFlag(String avlblCapacityFlag) {
		this.avlblCapacityFlag = avlblCapacityFlag;
	}

	public Long getReferredOffenders() {
		return referredOffenders;
	}

	public void setReferredOffenders(Long referredOffenders) {
		this.referredOffenders = referredOffenders;
	}

	public String getTargetOffFlag() {
		return targetOffFlag;
	}

	public void setTargetOffFlag(String targetOffFlag) {
		this.targetOffFlag = targetOffFlag;
	}

	public String getProvStateCode() {
		return provStateCode;
	}

	public void setProvStateCode(String provStateCode) {
		this.provStateCode = provStateCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	

	public String getSuiteNumber() {
		return suiteNumber;
	}

	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getOffenderProgramStatus() {
		return offenderProgramStatus;
	}

	public void setOffenderProgramStatus(String offenderProgramStatus) {
		this.offenderProgramStatus = offenderProgramStatus;
	}

	public String getWaitlistDecisionCode() {
		return waitlistDecisionCode;
	}

	public void setWaitlistDecisionCode(String waitlistDecisionCode) {
		this.waitlistDecisionCode = waitlistDecisionCode;
	}

	public Date getOffenderEndDate() {
		return offenderEndDate;
	}

	public void setOffenderEndDate(Date offenderEndDate) {
		this.offenderEndDate = offenderEndDate;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}


	public Integer getCaCount() {
		return caCount;
	}

	public void setCaCount(Integer caCount) {
		this.caCount = caCount;
	}

	public Integer getCarCount() {
		return carCount;
	}

	public void setCarCount(Integer carCount) {
		this.carCount = carCount;
	}

	public Integer getCsrCount() {
		return csrCount;
	}

	public void setCsrCount(Integer csrCount) {
		this.csrCount = csrCount;
	}

	public Integer getCsCount() {
		return csCount;
	}

	public void setCsCount(Integer csCount) {
		this.csCount = csCount;
	}

	public Integer getOcaCount() {
		return ocaCount;
	}

	public void setOcaCount(Integer ocaCount) {
		this.ocaCount = ocaCount;
	}

	public Integer getOppCount() {
		return oppCount;
	}

	public void setOppCount(Integer oppCount) {
		this.oppCount = oppCount;
	}

	public Integer getPpbofcCount() {
		return ppbofcCount;
	}

	public void setPpbofcCount(Integer ppbofcCount) {
		this.ppbofcCount = ppbofcCount;
	}

	public Integer getPpcCount() {
		return ppcCount;
	}

	public void setPpcCount(Integer ppcCount) {
		this.ppcCount = ppcCount;
	}
	
	
	
	
	
	

}
