package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VLivingUnitSummaries implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("allocated")
	private BigDecimal allocated;

	@JsonProperty("capacity")
	private BigDecimal capacity;

	@JsonProperty("deactivateDate")
	private Object deactivateDate;

	@JsonProperty("filledFlag")
	private String filledFlag;

	@JsonProperty("inLivingUnits")
	private BigDecimal inLivingUnits;

	@JsonProperty("level1Code")
	private String level1Code;

	@JsonProperty("level2Code")
	private String level2Code;

	@JsonProperty("level3Code")
	private String level3Code;

	@JsonProperty("level4Code")
	private String level4Code;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("livingUnitDesc")
	private String livingUnitDesc;

	@JsonProperty("livingUnitId")
	private BigDecimal livingUnitId;

	@JsonProperty("livingUnitType")
	private String livingUnitType;

	@JsonProperty("livingUnitTypeDesc")
	private String livingUnitTypeDesc;

	@JsonProperty("outOfAgy")
	private BigDecimal outOfAgy;

	@JsonProperty("outOfLivingUnits")
	private BigDecimal outOfLivingUnits;

	@JsonProperty("parentLivingUnitId")
	private Long parentLivingUnitId;

	@JsonProperty("reservedBeds")
	private BigDecimal reservedBeds;

	@JsonProperty("userDesc")
	private String userDesc;

	@JsonProperty("vacancy")
	private BigDecimal vacancy;

	@JsonProperty("nextButton")
	private String nextButton;

	/**
	 * Creates new VLivingUnitSummaries class Object
	 */
	public VLivingUnitSummaries() {
		// VLivingUnitSummaries
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
	 * @return the allocated
	 */
	public BigDecimal getAllocated() {
		return allocated;
	}

	/**
	 * @param allocated
	 *            the allocated to set
	 */
	public void setAllocated(BigDecimal allocated) {
		this.allocated = allocated;
	}

	/**
	 * @return the capacity
	 */
	public BigDecimal getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the deactivateDate
	 */
	public Object getDeactivateDate() {
		return deactivateDate;
	}

	/**
	 * @param deactivateDate
	 *            the deactivateDate to set
	 */
	public void setDeactivateDate(Object deactivateDate) {
		this.deactivateDate = deactivateDate;
	}

	/**
	 * @return the filledFlag
	 */
	public String getFilledFlag() {
		return filledFlag;
	}

	/**
	 * @param filledFlag
	 *            the filledFlag to set
	 */
	public void setFilledFlag(String filledFlag) {
		this.filledFlag = filledFlag;
	}

	/**
	 * @return the inLivingUnits
	 */
	public BigDecimal getInLivingUnits() {
		return inLivingUnits;
	}

	/**
	 * @param inLivingUnits
	 *            the inLivingUnits to set
	 */
	public void setInLivingUnits(BigDecimal inLivingUnits) {
		this.inLivingUnits = inLivingUnits;
	}

	/**
	 * @return the level1Code
	 */
	public String getLevel1Code() {
		return level1Code;
	}

	/**
	 * @param level1Code
	 *            the level1Code to set
	 */
	public void setLevel1Code(String level1Code) {
		this.level1Code = level1Code;
	}

	/**
	 * @return the level2Code
	 */
	public String getLevel2Code() {
		return level2Code;
	}

	/**
	 * @param level2Code
	 *            the level2Code to set
	 */
	public void setLevel2Code(String level2Code) {
		this.level2Code = level2Code;
	}

	/**
	 * @return the level3Code
	 */
	public String getLevel3Code() {
		return level3Code;
	}

	/**
	 * @param level3Code
	 *            the level3Code to set
	 */
	public void setLevel3Code(String level3Code) {
		this.level3Code = level3Code;
	}

	/**
	 * @return the level4Code
	 */
	public String getLevel4Code() {
		return level4Code;
	}

	/**
	 * @param level4Code
	 *            the level4Code to set
	 */
	public void setLevel4Code(String level4Code) {
		this.level4Code = level4Code;
	}

	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the livingUnitDesc
	 */
	public String getLivingUnitDesc() {
		return livingUnitDesc;
	}

	/**
	 * @param livingUnitDesc
	 *            the livingUnitDesc to set
	 */
	public void setLivingUnitDesc(String livingUnitDesc) {
		this.livingUnitDesc = livingUnitDesc;
	}

	/**
	 * @return the livingUnitId
	 */
	public BigDecimal getLivingUnitId() {
		return livingUnitId;
	}

	/**
	 * @param livingUnitId
	 *            the livingUnitId to set
	 */
	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	/**
	 * @return the livingUnitType
	 */
	public String getLivingUnitType() {
		return livingUnitType;
	}

	/**
	 * @param livingUnitType
	 *            the livingUnitType to set
	 */
	public void setLivingUnitType(String livingUnitType) {
		this.livingUnitType = livingUnitType;
	}

	/**
	 * @return the livingUnitTypeDesc
	 */
	public String getLivingUnitTypeDesc() {
		return livingUnitTypeDesc;
	}

	/**
	 * @param livingUnitTypeDesc
	 *            the livingUnitTypeDesc to set
	 */
	public void setLivingUnitTypeDesc(String livingUnitTypeDesc) {
		this.livingUnitTypeDesc = livingUnitTypeDesc;
	}

	/**
	 * @return the outOfAgy
	 */
	public BigDecimal getOutOfAgy() {
		return outOfAgy;
	}

	/**
	 * @param outOfAgy
	 *            the outOfAgy to set
	 */
	public void setOutOfAgy(BigDecimal outOfAgy) {
		this.outOfAgy = outOfAgy;
	}

	/**
	 * @return the outOfLivingUnits
	 */
	public BigDecimal getOutOfLivingUnits() {
		return outOfLivingUnits;
	}

	/**
	 * @param outOfLivingUnits
	 *            the outOfLivingUnits to set
	 */
	public void setOutOfLivingUnits(BigDecimal outOfLivingUnits) {
		this.outOfLivingUnits = outOfLivingUnits;
	}

	/**
	 * @return the parentLivingUnitId
	 */
	public Long getParentLivingUnitId() {
		return parentLivingUnitId;
	}

	/**
	 * @param parentLivingUnitId
	 *            the parentLivingUnitId to set
	 */
	public void setParentLivingUnitId(Long parentLivingUnitId) {
		this.parentLivingUnitId = parentLivingUnitId;
	}

	/**
	 * @return the reservedBeds
	 */
	public BigDecimal getReservedBeds() {
		return reservedBeds;
	}

	/**
	 * @param reservedBeds
	 *            the reservedBeds to set
	 */
	public void setReservedBeds(BigDecimal reservedBeds) {
		this.reservedBeds = reservedBeds;
	}

	/**
	 * @return the userDesc
	 */
	public String getUserDesc() {
		return userDesc;
	}

	/**
	 * @param userDesc
	 *            the userDesc to set
	 */
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	/**
	 * @return the vacancy
	 */
	public BigDecimal getVacancy() {
		return vacancy;
	}

	/**
	 * @param vacancy
	 *            the vacancy to set
	 */
	public void setVacancy(BigDecimal vacancy) {
		this.vacancy = vacancy;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the nextButton
	 */
	public String getNextButton() {
		return nextButton;
	}

	/**
	 * @param nextButton
	 *            the nextButton to set
	 */
	public void setNextButton(String nextButton) {
		this.nextButton = nextButton;
	}

}
