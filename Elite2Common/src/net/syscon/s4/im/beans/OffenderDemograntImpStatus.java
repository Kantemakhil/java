package net.syscon.s4.im.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class OffenderDemograntImpStatus
 */
public class OffenderDemograntImpStatus {

@JsonProperty("demograntId") 
private Integer demograntId;
@JsonProperty("ineligibleImprisonmentStatus") 
private String ineligibleImprisonmentStatus;
@JsonProperty("createDatetime") 
private Date createDatetime;
@JsonProperty("createUserId") 
private String createUserId;
@JsonProperty("modifyDatetime") 
private Date modifyDatetime;
@JsonProperty("modifyUserId") 
private String modifyUserId;
@JsonProperty("sealFlag") 
private String sealFlag;
	private boolean inserted;

	/**
	 * @param demograntId
	 *            demograntId to set
	 */
	public void setDemograntId(Integer demograntId) {
		this.demograntId = demograntId;
	}

	/**
	 * return thedemograntId
	 */
	public Integer getDemograntId() {
		return this.demograntId;
	}

	/**
	 * @param ineligibleImprisonmentStatus
	 *            ineligibleImprisonmentStatus to set
	 */
	public void setIneligibleImprisonmentStatus(final String ineligibleImprisonmentStatus) {
		this.ineligibleImprisonmentStatus = ineligibleImprisonmentStatus;
	}

	/**
	 * return theineligibleImprisonmentStatus
	 */
	public String getIneligibleImprisonmentStatus() {
		return this.ineligibleImprisonmentStatus;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final boolean inserted) {
		this.inserted = inserted;
	}

}