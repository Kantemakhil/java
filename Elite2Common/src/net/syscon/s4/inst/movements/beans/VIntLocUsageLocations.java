package net.syscon.s4.inst.movements.beans;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the V_INT_LOC_USAGE_LOCATIONS database table.
 * 
 */
public class VIntLocUsageLocations implements Serializable {
	private static final long serialVersionUID = 1L;

	private String agyLocId;

	private BigDecimal capacity;

	private String description;

	private String eventSubType;

	private Object intLocDeactivateDate;

	private String internalLocationCode;

	private BigDecimal internalLocationId;

	private String internalLocationUsage;

	private BigDecimal internalLocationUsageId;

	private BigDecimal listSeq;

	private String lowestLevelFlag;

	private BigDecimal parentUsageLocationId;

	private BigDecimal usageLocationId;

	private String usageLocationType;

	private String userDesc;

	public VIntLocUsageLocations() {
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public BigDecimal getCapacity() {
		return this.capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventSubType() {
		return this.eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public Object getIntLocDeactivateDate() {
		return this.intLocDeactivateDate;
	}

	public void setIntLocDeactivateDate(Object intLocDeactivateDate) {
		this.intLocDeactivateDate = intLocDeactivateDate;
	}

	public String getInternalLocationCode() {
		return this.internalLocationCode;
	}

	public void setInternalLocationCode(String internalLocationCode) {
		this.internalLocationCode = internalLocationCode;
	}

	public BigDecimal getInternalLocationId() {
		return this.internalLocationId;
	}

	public void setInternalLocationId(BigDecimal internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	public String getInternalLocationUsage() {
		return this.internalLocationUsage;
	}

	public void setInternalLocationUsage(String internalLocationUsage) {
		this.internalLocationUsage = internalLocationUsage;
	}

	public BigDecimal getInternalLocationUsageId() {
		return this.internalLocationUsageId;
	}

	public void setInternalLocationUsageId(BigDecimal internalLocationUsageId) {
		this.internalLocationUsageId = internalLocationUsageId;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public String getLowestLevelFlag() {
		return this.lowestLevelFlag;
	}

	public void setLowestLevelFlag(String lowestLevelFlag) {
		this.lowestLevelFlag = lowestLevelFlag;
	}

	public BigDecimal getParentUsageLocationId() {
		return this.parentUsageLocationId;
	}

	public void setParentUsageLocationId(BigDecimal parentUsageLocationId) {
		this.parentUsageLocationId = parentUsageLocationId;
	}

	public BigDecimal getUsageLocationId() {
		return this.usageLocationId;
	}

	public void setUsageLocationId(BigDecimal usageLocationId) {
		this.usageLocationId = usageLocationId;
	}

	public String getUsageLocationType() {
		return this.usageLocationType;
	}

	public void setUsageLocationType(String usageLocationType) {
		this.usageLocationType = usageLocationType;
	}

	public String getUserDesc() {
		return this.userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

}
