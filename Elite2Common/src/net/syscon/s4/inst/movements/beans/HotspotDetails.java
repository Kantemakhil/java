package net.syscon.s4.inst.movements.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class HotspotDetails extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("internalLocationId")
	private Long internalLocationId;

	@JsonProperty("internalLocationCode")
	private String internalLocationCode;

	@JsonProperty("internalLocationType")
	private String internalLocationType;

	@JsonProperty("parentId")
	private Integer parentId;

	@JsonProperty("activeFlag")
	private String activeFlag;

	@JsonProperty("internalLocHotspotId")
	private Integer internalLocHotspotId;

	@JsonProperty("xCoordinate")
	private Integer xCoordinate;

	@JsonProperty("yCoordinate")
	private Integer yCoordinate;
	
	@JsonProperty("floorPlanId")
	private Integer floorPlanId; 
	
	@JsonProperty("floorPlanNextId")
	private Integer floorPlanNextId; 
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	private String modifyUserId;
	private String createUserId;

	

	public String getInternalLocationCode() {
		return internalLocationCode;
	}

	public void setInternalLocationCode(String internalLocationCode) {
		this.internalLocationCode = internalLocationCode;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getInternalLocHotspotId() {
		return internalLocHotspotId;
	}

	public void setInternalLocHotspotId(Integer internalLocHotspotId) {
		this.internalLocHotspotId = internalLocHotspotId;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getInternalLocationType() {
		return internalLocationType;
	}

	public void setInternalLocationType(String internalLocationType) {
		this.internalLocationType = internalLocationType;
	}

	public Integer getFloorPlanId() {
		return floorPlanId;
	}

	public void setFloorPlanId(Integer floorPlanId) {
		this.floorPlanId = floorPlanId;
	}

	public Integer getFloorPlanNextId() {
		return floorPlanNextId;
	}

	public void setFloorPlanNextId(Integer floorPlanNextId) {
		this.floorPlanNextId = floorPlanNextId;
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

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

}
