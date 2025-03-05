package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OmuavbedLivUnitsQuery extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("profileType")
	private String profileType;

	@JsonProperty("profileCode")
	private String profileCode;

	@JsonProperty("createDateTime")
	private Timestamp createDateTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDateTime")
	private Timestamp modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("livingUnitId")
	private long livingUnitId;

	// AvailableLocations column in OMUUAVBED screen Available Locations table
	@JsonProperty("description")
	private String description;

	// column in OMUUAVBED screen Available Locations table
	@JsonProperty("noOfOccupant")
	private long noOfOccupant;

	// Capacity column in OMUUAVBED screen Available Locations table
	@JsonProperty("capacity")
	private long capacity;

	// Occupied column in OMUUAVBED screen Available Locations table
	@JsonProperty("noOfAvailable")
	private long noOfAvailable;

	// AtOperationalCapacity column in OMUUAVBED screen Available Locations
	// table
	@JsonProperty("unitAtCapacity")
	private String unitAtCapacity;

	// OffenderConflict column in OMUUAVBED screen Available Locations table
	@JsonProperty("prisonerConflict")
	private String prisonerConflict;

	// SecurityConflict column in OMUUAVBED screen Available Locations table
	@JsonProperty("securityConflict")
	private String securityConflict;

	// CellSharingConflict column in OMUUAVBED screen Available Locations table
	@JsonProperty("cellSharingConflict")
	private String cellSharingConflict;

	// P_OFFENDER_BOOK_ID number
	@JsonProperty("pOffenderBookId")
	private Long pOffenderBookId;

	// P_OFFENDER_ID number
	@JsonProperty("pOffenderId")
	private long pOffenderId;

	// P_CASELOAD_ID varchar
	@JsonProperty("pCaseloadId")
	private String pCaseloadId;

	// P_AGY_LOC_ID varchar
	@JsonProperty("pAgyLocId")
	private String pAgyLocId;

	// P_LIVING_UNIT_TYPE
	@JsonProperty("pLivingUnitType")
	private String pLivingUnitType;

	// P_LEVEL_1_CODE
	@JsonProperty("pLevel1Code")
	private String pLevel1Code;

	// P_LEVEL_2_CODE
	@JsonProperty("pLevel2Code")
	private String pLevel2Code;

	// P_LEVEL_3_CODE
	@JsonProperty("pLevel3Code")
	private String pLevel3Code;

	// P_LEVEL_4_CODE
	@JsonProperty("pLevel4Code")
	private String pLevel4Code;

	@JsonProperty("emptySearchCount")
	private Integer emptySearchCount;
	
	@JsonProperty("profileTypeOne")
	private String profileTypeOne;
	
	@JsonProperty("profileCodeOne")
	private String profileCodeOne;

	public Integer getEmptySearchCount() {
		return emptySearchCount;
	}

	public void setEmptySearchCount(Integer emptySearchCount) {
		this.emptySearchCount = emptySearchCount;
	}

	/**
	 * Creates new OffenderAlertsCommitBean class Object
	 */
	public OmuavbedLivUnitsQuery() {
		// OmuavbedLivUnitsQuery
	}

	/**
	 * @param PROFILE_TYPE PROFILE_TYPE to set
	 */
	public void setProfileType(final String profileType) {
		this.profileType = profileType;
	}

	/**
	 * return thePROFILE_TYPE
	 */
	public String getProfileType() {
		return this.profileType;
	}

	/**
	 * @param PROFILE_CODE PROFILE_CODE to set
	 */
	public void setProfileCode(final String profileCode) {
		this.profileCode = profileCode;
	}

	/**
	 * return thePROFILE_CODE
	 */
	public String getProfileCode() {
		return this.profileCode;
	}

	/**
	 * @param CREATE_DATETIME CREATE_DATETIME to set
	 */
	public void setCreateDateTime(final Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * return theCREATE_DATETIME
	 */
	public Timestamp getCreateDateTime() {
		return this.createDateTime;
	}

	/**
	 * @param CREATE_USER_ID CREATE_USER_ID to set
	 */
	public void setCreateUserId(final String CreateUserId) {
		this.createUserId = CreateUserId;
	}

	/**
	 * return theCREATE_USER_ID
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param MODIFY_DATETIME MODIFY_DATETIME to set
	 */
	public void setModifyDateTime(final Timestamp ModifyDateTime) {
		this.modifyDateTime = ModifyDateTime;
	}

	/**
	 * return theMODIFY_DATETIME
	 */
	public Timestamp getModifyDateTime() {
		return this.modifyDateTime;
	}

	/**
	 * @param MODIFY_USER_ID MODIFY_USER_ID to set
	 */
	public void setModifyUserId(final String ModifyUserId) {
		this.modifyUserId = ModifyUserId;
	}

	/**
	 * return theMODIFY_USER_ID
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the lIVING_UNIT_ID
	 */
	public long getLivingUnitId() {
		return this.livingUnitId;
	}

	/**
	 * @param lIVING_UNIT_ID the lIVING_UNIT_ID to set
	 */
	public void setLivingUnitId(final long livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 * @return the dESCRIPTION
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param dESCRIPTION the dESCRIPTION to set
	 */
	public void setDescription(final String dESCRIPTION) {
		this.description = dESCRIPTION;
	}

	/**
	 * @return the nO_OF_OCCUPANT
	 */
	public long getNoOfOccupant() {
		return this.noOfOccupant;
	}

	/**
	 * @param nO_OF_OCCUPANT the nO_OF_OCCUPANT to set
	 */
	public void setNoOfOccupant(final long noOfOccupant) {
		this.noOfOccupant = noOfOccupant;
	}

	/**
	 * @return the nO_OF_AVAILABLE
	 */
	public long getNoOfAvailable() {
		return this.noOfAvailable;
	}

	/**
	 * @param nO_OF_AVAILABLE the nO_OF_AVAILABLE to set
	 */
	public void setNoOfAvailable(final long noOfAvailable) {
		this.noOfAvailable = noOfAvailable;
	}

	/**
	 * @return the uNIT_AT_CAPACITY
	 */
	public String getUnitAtCapacity() {
		return this.unitAtCapacity;
	}

	/**
	 * @param uNIT_AT_CAPACITY the uNIT_AT_CAPACITY to set
	 */
	public void setUnitAtCapacity(final String unitAtCapacity) {
		this.unitAtCapacity = unitAtCapacity;
	}

	/**
	 * @return the pRISONER_CONFLICT
	 */
	public String getPrisonerConflict() {
		return this.prisonerConflict;
	}

	/**
	 * @param pRISONER_CONFLICT the pRISONER_CONFLICT to set
	 */
	public void setPrisonerConflict(final String prisonerConflict) {
		this.prisonerConflict = prisonerConflict;
	}

	/**
	 * @return the sECURITY_CONFLICT
	 */
	public String getSecurityConflict() {
		return this.securityConflict;
	}

	/**
	 * @param sECURITY_CONFLICT the sECURITY_CONFLICT to set
	 */
	public void setSecurityConflict(final String securityConflict) {
		this.securityConflict = securityConflict;
	}

	/**
	 * @return the cELL_SHARING_CONFLICT
	 */
	public String getCellSharingConflict() {
		return this.cellSharingConflict;
	}

	/**
	 * @param cELL_SHARING_CONFLICT the cELL_SHARING_CONFLICT to set
	 */
	public void setCellSharingConflict(final String cellSharingConflict) {
		this.cellSharingConflict = cellSharingConflict;
	}

	/**
	 * @return the cAPACITY
	 */
	public long getCapacity() {
		return this.capacity;
	}

	/**
	 * @param cAPACITY the cAPACITY to set
	 */
	public void setCapacity(final long capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the p_OFFENDER_BOOK_ID
	 */
	public Long getpOffenderBookId() {
		return this.pOffenderBookId;
	}

	/**
	 * @param p_OFFENDER_BOOK_ID the p_OFFENDER_BOOK_ID to set
	 */
	public void setpOffenderBookId(final Long pOffenderBookId) {
		this.pOffenderBookId = pOffenderBookId;
	}

	/**
	 * @return the p_OFFENDER_ID
	 */
	public long getpOffenderId() {
		return this.pOffenderId;
	}

	/**
	 * @param p_OFFENDER_ID the p_OFFENDER_ID to set
	 */
	public void setpOffenderId(final long pOffenderId) {
		this.pOffenderId = pOffenderId;
	}

	/**
	 * @return the p_CASELOAD_ID
	 */
	public String getpCaseloadId() {
		return this.pCaseloadId;
	}

	/**
	 * @param p_CASELOAD_ID the p_CASELOAD_ID to set
	 */
	public void setpCaseloadId(final String pCaseloadId) {
		this.pCaseloadId = pCaseloadId;
	}

	/**
	 * @return the p_AGY_LOC_ID
	 */
	public String getpAgyLocId() {
		return this.pAgyLocId;
	}

	/**
	 * @param p_AGY_LOC_ID the p_AGY_LOC_ID to set
	 */
	public void setpAgyLocId(final String pAgyLocId) {
		this.pAgyLocId = pAgyLocId;
	}

	/**
	 * @return the p_LIVING_UNIT_TYPE
	 */
	public String getpLivingUnitType() {
		return this.pLivingUnitType;
	}

	/**
	 * @param p_LIVING_UNIT_TYPE the p_LIVING_UNIT_TYPE to set
	 */
	public void setpLivingUnitType(final String pLivingUnitType) {
		this.pLivingUnitType = pLivingUnitType;
	}

	/**
	 * @return the p_LEVEL_1_CODE
	 */
	public String getpLevel1Code() {
		return this.pLevel1Code;
	}

	/**
	 * @param p_LEVEL_1_CODE the p_LEVEL_1_CODE to set
	 */
	public void setpLevel1Code(final String pLevel1Code) {
		this.pLevel1Code = pLevel1Code;
	}

	/**
	 * @return the p_LEVEL_2_CODE
	 */
	public String getpLevel2Code() {
		return this.pLevel2Code;
	}

	/**
	 * @param p_LEVEL_2_CODE the p_LEVEL_2_CODE to set
	 */
	public void setpLevel2Code(final String pLevel2Code) {
		this.pLevel2Code = pLevel2Code;
	}

	/**
	 * @return the p_LEVEL_3_CODE
	 */
	public String getpLevel3Code() {
		return this.pLevel3Code;
	}

	/**
	 * @param p_LEVEL_3_CODE the p_LEVEL_3_CODE to set
	 */
	public void setpLevel3Code(final String pLevel3Code) {
		this.pLevel3Code = pLevel3Code;
	}

	/**
	 * @return the p_LEVEL_4_CODE
	 */
	public String getpLevel4Code() {
		return this.pLevel4Code;
	}

	/**
	 * @param p_LEVEL_4_CODE the p_LEVEL_4_CODE to set
	 */
	public void setpLevel4Code(final String pLevel4Code) {
		this.pLevel4Code = pLevel4Code;
	}
	
	/**
	 * @param PROFILE_TYPE PROFILE_TYPE_ONE to set
	 */
	public void setProfileTypeOne(final String profileTypeOne) {
		this.profileTypeOne = profileTypeOne;
	}

	/**
	 * return thePROFILE_TYPE_ONE
	 */
	public String getProfileTypeOne() {
		return this.profileTypeOne;
	}
	
	/**
	 * @param PROFILE_CODE_ONE PROFILE_CODE_ONE to set
	 */
	public void setProfileCodeOne(final String profileCodeOne) {
		this.profileCodeOne = profileCodeOne;
	}

	/**
	 * return thePROFILE_CODE_ONE
	 */
	public String getProfileCodeOne() {
		return this.profileCodeOne;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OmuavbedLivUnitsQuery [profileType=");
		builder.append(profileType);
		builder.append(", profileCode=");
		builder.append(profileCode);
		builder.append(", createDateTime=");
		builder.append(createDateTime);
		builder.append(", createUserId=");
		builder.append(createUserId);
		builder.append(", modifyDateTime=");
		builder.append(modifyDateTime);
		builder.append(", modifyUserId=");
		builder.append(modifyUserId);
		builder.append(", inserted=");
		builder.append(inserted);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", livingUnitId=");
		builder.append(livingUnitId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", noOfOccupant=");
		builder.append(noOfOccupant);
		builder.append(", capacity=");
		builder.append(capacity);
		builder.append(", noOfAvailable=");
		builder.append(noOfAvailable);
		builder.append(", unitAtCapacity=");
		builder.append(unitAtCapacity);
		builder.append(", prisonerConflict=");
		builder.append(prisonerConflict);
		builder.append(", securityConflict=");
		builder.append(securityConflict);
		builder.append(", cellSharingConflict=");
		builder.append(cellSharingConflict);
		builder.append(", pOffenderBookId=");
		builder.append(pOffenderBookId);
		builder.append(", pOffenderId=");
		builder.append(pOffenderId);
		builder.append(", pCaseloadId=");
		builder.append(pCaseloadId);
		builder.append(", pAgyLocId=");
		builder.append(pAgyLocId);
		builder.append(", pLivingUnitType=");
		builder.append(pLivingUnitType);
		builder.append(", pLevel1Code=");
		builder.append(pLevel1Code);
		builder.append(", pLevel2Code=");
		builder.append(pLevel2Code);
		builder.append(", pLevel3Code=");
		builder.append(pLevel3Code);
		builder.append(", pLevel4Code=");
		builder.append(pLevel4Code);
		builder.append(", profileTypeOne=");
		builder.append(profileTypeOne);
		builder.append(", profileCodeOne=");
		builder.append(profileCodeOne);
		builder.append("]");
		return builder.toString();
	}

}