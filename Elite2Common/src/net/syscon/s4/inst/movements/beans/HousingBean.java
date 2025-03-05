package net.syscon.s4.inst.movements.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class HousingBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("housingId")
	private Long housingId;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("internalLocationType")
	private String internalLocationType;

	
	@JsonProperty("internalLocationId")
	private Long internalLocationId;
	
	@JsonProperty("internalLocationCode")
	private String internalLocationCode;
	
	@JsonProperty("xCoordinate")
	private Integer xCoordinate;
	
	@JsonProperty("yCoordinate")
	private Integer yCoordinate;
	
	
	@JsonProperty("parentLocId")
	private Long parentLocId;
	
	@JsonProperty("totalBeds")
	private Integer totalBeds;
	
	@JsonProperty("availBeds")
	private Integer availBeds;
	
	@JsonProperty("allocatedBeds")
	private Integer allocatedBeds;
	
	@JsonProperty("internalLocHotspotId")
	private Integer internalLocHotspotId;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("offenderId")
	private Long offenderId;
	
	@JsonProperty("oprationalConflict")
	private String oprationalConflict=""; 
	
	@JsonProperty("securityConflict")
	private String securityConflict=""; 
	
	@JsonProperty("offenderConflict")
	private String offenderConflict=""; 
	
	@JsonProperty("cellSharingConflict")
	private String cellSharingConflict=""; 
	
	@JsonProperty("imageId")
	private BigDecimal  imageId; 
	
	@JsonProperty("floorPlanId")
	private Integer floorPlanId; 
	
	@JsonProperty("description")
	private String description; 
	
	@JsonProperty("floorPlanNextId")
	private Integer floorPlanNextId; 
	
	@JsonProperty("rootFloorPlan")
	private String rootFloorPlan; 
	
	@JsonProperty("capacity")
	private Integer capacity;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("imageThumbnail")
	private byte[] imageThumbnail;
	
	@JsonProperty("imageObjectId")
	private Long imageObjectId;
	
	@JsonProperty("parentFloorPlan")
	private Integer parentFloorPlan;
	
	@JsonProperty("internalLocId")
	private Integer internalLocId;
	
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
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	private String modifyUserId;
	
	public Long getHousingId() {
		return housingId;
	}

	public void setHousingId(Long housingId) {
		this.housingId = housingId;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Long getInternalLocationId() {
		return internalLocationId;
	}

	public void setInternalLocationId(Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getInternalLocationCode() {
		return internalLocationCode;
	}

	public void setInternalLocationCode(String internalLocationCode) {
		this.internalLocationCode = internalLocationCode;
	}

	public Long getParentLocId() {
		return parentLocId;
	}

	public void setParentLocId(Long parentLocId) {
		this.parentLocId = parentLocId;
	}

	public Integer getTotalBeds() {
		return totalBeds;
	}

	public void setTotalBeds(Integer totalBeds) {
		this.totalBeds = totalBeds;
	}

	public Integer getAvailBeds() {
		return availBeds;
	}

	public void setAvailBeds(Integer availBeds) {
		this.availBeds = availBeds;
	}

	public Integer getAllocatedBeds() {
		return allocatedBeds;
	}

	public void setAllocatedBeds(Integer allocatedBeds) {
		this.allocatedBeds = allocatedBeds;
	}

	public Integer getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(Integer xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Integer getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(Integer yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public String getInternalLocationType() {
		return internalLocationType;
	}

	public void setInternalLocationType(String internalLocationType) {
		this.internalLocationType = internalLocationType;
	}

	public Integer getInternalLocHotspotId() {
		return internalLocHotspotId;
	}

	public void setInternalLocHotspotId(Integer internalLocHotspotId) {
		this.internalLocHotspotId = internalLocHotspotId;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public String getOprationalConflict() {
		return oprationalConflict;
	}

	public void setOprationalConflict(String oprationalConflict) {
		this.oprationalConflict = oprationalConflict;
	}

	public String getSecurityConflict() {
		return securityConflict;
	}

	public void setSecurityConflict(String securityConflict) {
		this.securityConflict = securityConflict;
	}

	public String getOffenderConflict() {
		return offenderConflict;
	}

	public void setOffenderConflict(String offenderConflict) {
		this.offenderConflict = offenderConflict;
	}

	public String getCellSharingConflict() {
		return cellSharingConflict;
	}

	public void setCellSharingConflict(String cellSharingConflict) {
		this.cellSharingConflict = cellSharingConflict;
	}

	public BigDecimal  getImageId() {
		return imageId;
	}

	public void setImageId(BigDecimal  imageId) {
		this.imageId = imageId;
	}

	public Integer getFloorPlanId() {
		return floorPlanId;
	}

	public void setFloorPlanId(Integer floorPlanId) {
		this.floorPlanId = floorPlanId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFloorPlanNextId() {
		return floorPlanNextId;
	}

	public void setFloorPlanNextId(Integer floorPlanNextId) {
		this.floorPlanNextId = floorPlanNextId;
	}

	public String getRootFloorPlan() {
		return rootFloorPlan;
	}

	public void setRootFloorPlan(String rootFloorPlan) {
		this.rootFloorPlan = rootFloorPlan;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getImageObjectId() {
		return imageObjectId;
	}

	public void setImageObjectId(Long imageObjectId) {
		this.imageObjectId = imageObjectId;
	}

	public byte[] getImageThumbnail() {
		return imageThumbnail;
	}

	public void setImageThumbnail(byte[] imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}

	public Integer getParentFloorPlan() {
		return parentFloorPlan;
	}

	public void setParentFloorPlan(Integer parentFloorPlan) {
		this.parentFloorPlan = parentFloorPlan;
	}

	public Integer getInternalLocId() {
		return internalLocId;
	}

	public void setInternalLocId(Integer internalLocId) {
		this.internalLocId = internalLocId;
	}

	public String getpLevel1Code() {
		return pLevel1Code;
	}

	public void setpLevel1Code(String pLevel1Code) {
		this.pLevel1Code = pLevel1Code;
	}

	public String getpLevel2Code() {
		return pLevel2Code;
	}

	public void setpLevel2Code(String pLevel2Code) {
		this.pLevel2Code = pLevel2Code;
	}

	public String getpLevel3Code() {
		return pLevel3Code;
	}

	public void setpLevel3Code(String pLevel3Code) {
		this.pLevel3Code = pLevel3Code;
	}

	public String getpLevel4Code() {
		return pLevel4Code;
	}

	public void setpLevel4Code(String pLevel4Code) {
		this.pLevel4Code = pLevel4Code;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
}
