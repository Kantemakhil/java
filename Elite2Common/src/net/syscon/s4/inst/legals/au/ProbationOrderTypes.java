package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class ProbationOrderTypes extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderType;
	private String orderCode;
	private String description;
	private Long reportDueDays;
	private String displayHoursSummary;
	private String activeFlag;
	private String updateFlag;
	private Long listSeq;
	private Date expiryDate;
	private String hearingType;
	private String closeCaseFlag;
	private String populateStatus;
	private String sustainedOffenceFlag;
	private String allowSendWork;
	private Date createDatetime;
	private String createUserId;
	private Date modifyDatetime;
	private String modifyUserId;
	private String sealFlag;
	
	
	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(final String orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}
	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(final String orderCode) {
		this.orderCode = orderCode;
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
	/**
	 * @return the reportDueDays
	 */
	public Long getReportDueDays() {
		return reportDueDays;
	}
	/**
	 * @param reportDueDays the reportDueDays to set
	 */
	public void setReportDueDays(final Long reportDueDays) {
		this.reportDueDays = reportDueDays;
	}
	/**
	 * @return the displayHoursSummary
	 */
	public String getDisplayHoursSummary() {
		return displayHoursSummary;
	}
	/**
	 * @param displayHoursSummary the displayHoursSummary to set
	 */
	public void setDisplayHoursSummary(final String displayHoursSummary) {
		this.displayHoursSummary = displayHoursSummary;
	}
	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}
	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return the updateFlag
	 */
	public String getUpdateFlag() {
		return updateFlag;
	}
	/**
	 * @param updateFlag the updateFlag to set
	 */
	public void setUpdateFlag(final String updateFlag) {
		this.updateFlag = updateFlag;
	}
	/**
	 * @return the listSeq
	 */
	public Long getListSeq() {
		return listSeq;
	}
	/**
	 * @param listSeq the listSeq to set
	 */
	public void setListSeq(final Long listSeq) {
		this.listSeq = listSeq;
	}
	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the hearingType
	 */
	public String getHearingType() {
		return hearingType;
	}
	/**
	 * @param hearingType the hearingType to set
	 */
	public void setHearingType(final String hearingType) {
		this.hearingType = hearingType;
	}
	/**
	 * @return the closeCaseFlag
	 */
	public String getCloseCaseFlag() {
		return closeCaseFlag;
	}
	/**
	 * @param closeCaseFlag the closeCaseFlag to set
	 */
	public void setCloseCaseFlag(final String closeCaseFlag) {
		this.closeCaseFlag = closeCaseFlag;
	}
	/**
	 * @return the populateStatus
	 */
	public String getPopulateStatus() {
		return populateStatus;
	}
	/**
	 * @param populateStatus the populateStatus to set
	 */
	public void setPopulateStatus(final String populateStatus) {
		this.populateStatus = populateStatus;
	}
	/**
	 * @return the sustainedOffenceFlag
	 */
	public String getSustainedOffenceFlag() {
		return sustainedOffenceFlag;
	}
	/**
	 * @param sustainedOffenceFlag the sustainedOffenceFlag to set
	 */
	public void setSustainedOffenceFlag(final String sustainedOffenceFlag) {
		this.sustainedOffenceFlag = sustainedOffenceFlag;
	}
	/**
	 * @return the allowSendWork
	 */
	public String getAllowSendWork() {
		return allowSendWork;
	}
	/**
	 * @param allowSendWork the allowSendWork to set
	 */
	public void setAllowSendWork(final String allowSendWork) {
		this.allowSendWork = allowSendWork;
	}
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}
	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}
	/**
	 * @param sealFlag the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	

}
