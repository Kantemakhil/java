package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VPrisonActivities extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String activeFlag;

	private String activity;

	private String agyLocDesc;

	private String agyLocId;

	private Long crsActyId;

	private String internalLocationDesc;

	private Long internalLocationId;

	private Long listSeq;

	private String programCode;

	private Long programId;

	private Date scheduleEndDate;

	private Date scheduleStartDate;

	private String service;
	
	private String code;
	
	private String description;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
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
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(final String activity) {
		this.activity = activity;
	}

	/**
	 * @return the agyLocDesc
	 */
	public String getAgyLocDesc() {
		return agyLocDesc;
	}

	/**
	 * @param agyLocDesc
	 *            the agyLocDesc to set
	 */
	public void setAgyLocDesc(final String agyLocDesc) {
		this.agyLocDesc = agyLocDesc;
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
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the crsActyId
	 */
	public Long getCrsActyId() {
		return crsActyId;
	}

	/**
	 * @param crsActyId
	 *            the crsActyId to set
	 */
	public void setCrsActyId(final Long crsActyId) {
		this.crsActyId = crsActyId;
	}

	/**
	 * @return the internalLocationDesc
	 */
	public String getInternalLocationDesc() {
		return internalLocationDesc;
	}

	/**
	 * @param internalLocationDesc
	 *            the internalLocationDesc to set
	 */
	public void setInternalLocationDesc(final String internalLocationDesc) {
		this.internalLocationDesc = internalLocationDesc;
	}

	/**
	 * @return the internalLocationId
	 */
	public Long getInternalLocationId() {
		return internalLocationId;
	}

	/**
	 * @param internalLocationId
	 *            the internalLocationId to set
	 */
	public void setInternalLocationId(final Long internalLocationId) {
		this.internalLocationId = internalLocationId;
	}

	/**
	 * @return the listSeq
	 */
	public Long getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final Long listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}

	/**
	 * @param programCode
	 *            the programCode to set
	 */
	public void setProgramCode(final String programCode) {
		this.programCode = programCode;
	}

	/**
	 * @return the programId
	 */
	public Long getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(final Long programId) {
		this.programId = programId;
	}

	/**
	 * @return the scheduleEndDate
	 */
	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	/**
	 * @param scheduleEndDate
	 *            the scheduleEndDate to set
	 */
	public void setScheduleEndDate(final Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	/**
	 * @return the scheduleStartDate
	 */
	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	/**
	 * @param scheduleStartDate
	 *            the scheduleStartDate to set
	 */
	public void setScheduleStartDate(final Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(final String service) {
		this.service = service;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

}
