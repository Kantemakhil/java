package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.List;

import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;


/**
 * The persistent class for the INTERNAL_LOCATION_USAGES database table.
 * 
 */
public class InternalLocationUsages implements Serializable {
	private static final long serialVersionUID = 1L;

	private long internalLocationUsageId;

	private String agyLocId;

	private Object createDatetime;

	private String createUserId;

	private String eventSubType;

	private String internalLocationUsage;

	private Object modifyDatetime;

	private String modifyUserId;

	private String sealFlag;
	
	private String description;
	
	private Integer code;
	
	private String internalLocationCode;

	//bi-directional many-to-one association to IntLocUsageLocation
	private List<IntLocUsageLocations> intLocUsageLocations;

	public InternalLocationUsages() {
	}

	public long getInternalLocationUsageId() {
		return this.internalLocationUsageId;
	}

	public void setInternalLocationUsageId(long internalLocationUsageId) {
		this.internalLocationUsageId = internalLocationUsageId;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getEventSubType() {
		return this.eventSubType;
	}

	public void setEventSubType(String eventSubType) {
		this.eventSubType = eventSubType;
	}

	public String getInternalLocationUsage() {
		return this.internalLocationUsage;
	}

	public void setInternalLocationUsage(String internalLocationUsage) {
		this.internalLocationUsage = internalLocationUsage;
	}

	public Object getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Object modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public List<IntLocUsageLocations> getIntLocUsageLocations() {
		return this.intLocUsageLocations;
	}

	public void setIntLocUsageLocations(List<IntLocUsageLocations> intLocUsageLocations) {
		this.intLocUsageLocations = intLocUsageLocations;
	}

	public IntLocUsageLocations addIntLocUsageLocation(IntLocUsageLocations intLocUsageLocation) {
		getIntLocUsageLocations().add(intLocUsageLocation);
		//intLocUsageLocation.setInternalLocationUsage(this);

		return intLocUsageLocation;
	}

	public IntLocUsageLocations removeIntLocUsageLocation(IntLocUsageLocations intLocUsageLocation) {
		getIntLocUsageLocations().remove(intLocUsageLocation);
		//intLocUsageLocation.setInternalLocationUsage(null);

		return intLocUsageLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getInternalLocationCode() {
		return internalLocationCode;
	}

	public void setInternalLocationCode(String internalLocationCode) {
		this.internalLocationCode = internalLocationCode;
	}

}
