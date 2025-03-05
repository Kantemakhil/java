package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the V_LIVING_UNIT_OFFENDERS database table.
 * 
 */
public class VLivingUnitOffenders extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String agencyImlDesc;

	private BigDecimal agencyImlId;

	private String agyLocId;

	private String alertFlag;

	private String firstName;

	private String inOutStatus;

	private String lastName;

	private String livingUnitDesc;

	private BigDecimal livingUnitId;

	private BigDecimal offenderBookId;

	private BigDecimal offenderId;

	private String offenderIdDisplay;

	private BigDecimal parentLivingUnitId;

	private BigDecimal rootLivingUnitId;

	private String cellType;

	public VLivingUnitOffenders() {
		// VLivingUnitOffenders
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAgencyImlDesc() {
		return this.agencyImlDesc;
	}

	public void setAgencyImlDesc(String agencyImlDesc) {
		this.agencyImlDesc = agencyImlDesc;
	}

	public BigDecimal getAgencyImlId() {
		return this.agencyImlId;
	}

	public void setAgencyImlId(BigDecimal agencyImlId) {
		this.agencyImlId = agencyImlId;
	}

	public String getAgyLocId() {
		return this.agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public String getAlertFlag() {
		return this.alertFlag;
	}

	public void setAlertFlag(String alertFlag) {
		this.alertFlag = alertFlag;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getInOutStatus() {
		return this.inOutStatus;
	}

	public void setInOutStatus(String inOutStatus) {
		this.inOutStatus = inOutStatus;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLivingUnitDesc() {
		return this.livingUnitDesc;
	}

	public void setLivingUnitDesc(String livingUnitDesc) {
		this.livingUnitDesc = livingUnitDesc;
	}

	public BigDecimal getLivingUnitId() {
		return this.livingUnitId;
	}

	public void setLivingUnitId(BigDecimal livingUnitId) {
		this.livingUnitId = livingUnitId;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return this.offenderId;
	}

	public void setOffenderId(BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getOffenderIdDisplay() {
		return this.offenderIdDisplay;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public BigDecimal getParentLivingUnitId() {
		return this.parentLivingUnitId;
	}

	public void setParentLivingUnitId(BigDecimal parentLivingUnitId) {
		this.parentLivingUnitId = parentLivingUnitId;
	}

	public BigDecimal getRootLivingUnitId() {
		return this.rootLivingUnitId;
	}

	public void setRootLivingUnitId(BigDecimal rootLivingUnitId) {
		this.rootLivingUnitId = rootLivingUnitId;
	}

	public String getCellType() {
		return cellType;
	}

	public void setCellType(String cellType) {
		this.cellType = cellType;
	}

}
