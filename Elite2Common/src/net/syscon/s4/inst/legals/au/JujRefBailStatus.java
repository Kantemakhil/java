package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class JujRefBailStatus extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bailStatus;

	private String description;

	private Integer displayOrder;

	private String isDefault;

	private String isEffective;

	private String allowMultiplesAcrossCases;

	private String allowMultiplesPerProceeding;

	private Date modifiedDate;

	private String modifiedByUser;

	private Integer lockVersion;

	public String getBailStatus() {
		return bailStatus;
	}

	public void setBailStatus(String bailStatus) {
		this.bailStatus = bailStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(String isEffective) {
		this.isEffective = isEffective;
	}

	public String getAllowMultiplesAcrossCases() {
		return allowMultiplesAcrossCases;
	}

	public void setAllowMultiplesAcrossCases(String allowMultiplesAcrossCases) {
		this.allowMultiplesAcrossCases = allowMultiplesAcrossCases;
	}

	public String getAllowMultiplesPerProceeding() {
		return allowMultiplesPerProceeding;
	}

	public void setAllowMultiplesPerProceeding(String allowMultiplesPerProceeding) {
		this.allowMultiplesPerProceeding = allowMultiplesPerProceeding;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	public Integer getLockVersion() {
		return lockVersion;
	}

	public void setLockVersion(Integer lockVersion) {
		this.lockVersion = lockVersion;
	}

}
