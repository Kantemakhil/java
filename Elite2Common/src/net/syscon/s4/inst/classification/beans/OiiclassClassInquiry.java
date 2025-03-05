/**
 * 
 */
package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;


public class OiiclassClassInquiry extends BaseModel implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<OiiclassClassInquiry> resultSet;
	
	private String pSearchType;
	
	private String pCaseload;
	
	private Date pFromDate;
	
	private Date pToDate;
	
	private Long pAssessmentId;
	
	private String pAgyLocId;
	
	private String pLocation;
	
	private Long offenderBookID;
	
	private String offenderIdDisplay;
	
	private String bookingNo;
	
	private String offenderName;
	
	private String location;
	
	private String currentLevel;
	
	private String assessmentType;
	
	private String assessmentId;
	
	private Date scheduleDate;

	private String omsIntakeGetOffenderSupervision;
	
	private String caseloadType;
	
	private String primaryOfficer;


	
	public OiiclassClassInquiry() { 
		// OiiclassClassInquiry
	}
	/**
	 * @return the resultSet
	 */
	public List<OiiclassClassInquiry> getResultSet() {
		return resultSet;
	}

	/**
	 * @param resultSet the resultSet to set
	 */
	public void setResultSet(List<OiiclassClassInquiry> resultSet) {
		this.resultSet = resultSet;
	}

	/**
	 * @return the pSearchType
	 */
	public String getpSearchType() {
		return pSearchType;
	}

	/**
	 * @param pSearchType the pSearchType to set
	 */
	public void setpSearchType(String pSearchType) {
		this.pSearchType = pSearchType;
	}

	/**
	 * @return the pCaseload
	 */
	public String getpCaseload() {
		return pCaseload;
	}

	/**
	 * @param pCaseload the pCaseload to set
	 */
	public void setpCaseload(String pCaseload) {
		this.pCaseload = pCaseload;
	}

	/**
	 * @return the pFromDate
	 */
	public Date getpFromDate() {
		return pFromDate;
	}

	/**
	 * @param pFromDate the pFromDate to set
	 */
	public void setpFromDate(Date pFromDate) {
		this.pFromDate = pFromDate;
	}

	/**
	 * @return the pToDate
	 */
	public Date getpToDate() {
		return pToDate;
	}

	/**
	 * @param pToDate the pToDate to set
	 */
	public void setpToDate(Date pToDate) {
		this.pToDate = pToDate;
	}

	/**
	 * @return the pAssessmentId
	 */
	public Long getpAssessmentId() {
		return pAssessmentId;
	}

	/**
	 * @param pAssessmentId the pAssessmentId to set
	 */
	public void setpAssessmentId(Long pAssessmentId) {
		this.pAssessmentId = pAssessmentId;
	}

	/**
	 * @return the pAgyLocId
	 */
	public String getpAgyLocId() {
		return pAgyLocId;
	}

	/**
	 * @param pAgyLocId the pAgyLocId to set
	 */
	public void setpAgyLocId(String pAgyLocId) {
		this.pAgyLocId = pAgyLocId;
	}

	/**
	 * @return the pLocation
	 */
	public String getpLocation() {
		return pLocation;
	}

	/**
	 * @param pLocation the pLocation to set
	 */
	public void setpLocation(String pLocation) {
		this.pLocation = pLocation;
	}

	/**
	 * @return the offenderBookID
	 */
	public Long getOffenderBookID() {
		return offenderBookID;
	}

	/**
	 * @param offenderBookID the offenderBookID to set
	 */
	public void setOffenderBookID(Long offenderBookID) {
		this.offenderBookID = offenderBookID;
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the bookingNo
	 */
	public String getBookingNo() {
		return bookingNo;
	}

	/**
	 * @param bookingNo the bookingNo to set
	 */
	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	/**
	 * @return the offenderName
	 */
	public String getOffenderName() {
		return offenderName;
	}

	/**
	 * @param offenderName the offenderName to set
	 */
	public void setOffenderName(String offenderName) {
		this.offenderName = offenderName;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the currentLevel
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * @param currentLevel the currentLevel to set
	 */
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}

	/**
	 * @return the assessmentType
	 */
	public String getAssessmentType() {
		return assessmentType;
	}

	/**
	 * @param assessmentType the assessmentType to set
	 */
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	/**
	 * @return the assessmentId
	 */
	public String getAssessmentId() {
		return assessmentId;
	}

	/**
	 * @param assessmentId the assessmentId to set
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	/**
	 * @return the scheduleDate
	 */
	public Date getScheduleDate() {
		return scheduleDate;
	}

	/**
	 * @param scheduleDate the scheduleDate to set
	 */
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	/**
	 * @return the omsIntakeGetOffenderSupervision
	 */
	public String getOmsIntakeGetOffenderSupervision() {
		return omsIntakeGetOffenderSupervision;
	}
	/**
	 * @param omsIntakeGetOffenderSupervision the omsIntakeGetOffenderSupervision to set
	 */
	public void setOmsIntakeGetOffenderSupervision(String omsIntakeGetOffenderSupervision) {
		this.omsIntakeGetOffenderSupervision = omsIntakeGetOffenderSupervision;
	}
	public String getCaseloadType() {
		return caseloadType;
	}
	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}
	public String getPrimaryOfficer() {
		return primaryOfficer;
	}
	public void setPrimaryOfficer(String primaryOfficer) {
		this.primaryOfficer = primaryOfficer;
	}
	
	
	
	

}
