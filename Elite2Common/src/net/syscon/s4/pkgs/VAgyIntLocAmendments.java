package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VAgyIntLocAmendments extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long livingUnitId;
	private String agyLocId;
	private String livingUnitType;
	private String livingUnitCode;
	private String description;
	private String livingUnitDescription;
	private String level1Code;
	private String level2Code;
	private String level3Code;
	private String level4Code;
	private Long agyIntLocAmendmentId;
	private Long internalLocId;
	private Date amendDate;
	private String columnName;
	private Integer oldValue;
	private Integer newValue;
	private String deactivateReasonCode;
	private String actionCode;
	private String amendUserId;
	private Date amendDateTo;
	private Date amendDateFrom;
	
	public Date getAmendDateTo() {
		return amendDateTo;
	}
	public void setAmendDateTo(Date amendDateTo) {
		this.amendDateTo = amendDateTo;
	}
	public Long getLivingUnitId() {
		return livingUnitId;
	}
	public void setLivingUnitId(Long livingUnitId) {
		this.livingUnitId = livingUnitId;
	}
	public String getAgyLocId() {
		return agyLocId;
	}
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}
	public String getLivingUnitType() {
		return livingUnitType;
	}
	public void setLivingUnitType(String livingUnitType) {
		this.livingUnitType = livingUnitType;
	}
	public String getLivingUnitCode() {
		return livingUnitCode;
	}
	public void setLivingUnitCode(String livingUnitCode) {
		this.livingUnitCode = livingUnitCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLivingUnitDescription() {
		return livingUnitDescription;
	}
	public void setLivingUnitDescription(String livingUnitDescription) {
		this.livingUnitDescription = livingUnitDescription;
	}
	public String getLevel1Code() {
		return level1Code;
	}
	public void setLevel1Code(String level1Code) {
		this.level1Code = level1Code;
	}
	public String getLevel2Code() {
		return level2Code;
	}
	public void setLevel2Code(String level2Code) {
		this.level2Code = level2Code;
	}
	public String getLevel3Code() {
		return level3Code;
	}
	public void setLevel3Code(String level3Code) {
		this.level3Code = level3Code;
	}
	public String getLevel4Code() {
		return level4Code;
	}
	public void setLevel4Code(String level4Code) {
		this.level4Code = level4Code;
	}
	public Long getAgyIntLocAmendmentId() {
		return agyIntLocAmendmentId;
	}
	public void setAgyIntLocAmendmentId(Long agyIntLocAmendmentId) {
		this.agyIntLocAmendmentId = agyIntLocAmendmentId;
	}
	public Long getInternalLocId() {
		return internalLocId;
	}
	public void setInternalLocId(Long internalLocId) {
		this.internalLocId = internalLocId;
	}
	public Date getAmendDate() {
		return amendDate;
	}
	public void setAmendDate(Date amendDate) {
		this.amendDate = amendDate;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Integer getOldValue() {
		return oldValue;
	}
	public Date getAmendDateFrom() {
		return amendDateFrom;
	}
	public void setAmendDateFrom(Date amendDateFrom) {
		this.amendDateFrom = amendDateFrom;
	}
	public void setOldValue(Integer oldValue) {
		this.oldValue = oldValue;
	}
	public Integer getNewValue() {
		return newValue;
	}
	public void setNewValue(Integer newValue) {
		this.newValue = newValue;
	}
	public String getDeactivateReasonCode() {
		return deactivateReasonCode;
	}
	public void setDeactivateReasonCode(String deactivateReasonCode) {
		this.deactivateReasonCode = deactivateReasonCode;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getAmendUserId() {
		return amendUserId;
	}
	public void setAmendUserId(String amendUserId) {
		this.amendUserId = amendUserId;
	}
	
	
	
}
