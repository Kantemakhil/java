package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSamples extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer offenderBookId;

	private Date sampleDate;

	private String sampleType;

	private String sampleReason;

	private Integer takenStaffId;
	
	private Integer witnessStaffId;
	
	private String commentText;
	
	private Integer ofCcId;
	
	private Date sampleTime;
	
	private String sealFlag;

	private Integer offenderSampleId;
	
	private String externalTestAgencyFlag;

	private Integer testStaffId;

	private Integer testCorporateId;

	private Date sampleTestDate;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;
	
	private String testedPositive;
	
	private String testedBy;
	
	private Integer countOffSub;
	
	private Date stdTemp;
	
	private String testedByTemp;
	
	private String testedPositiveFlag;

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Date getSampleDate() {
		return sampleDate;
	}

	public void setSampleDate(final Date sampleDate) {
		this.sampleDate = sampleDate;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(final String sampleType) {
		this.sampleType = sampleType;
	}

	public String getSampleReason() {
		return sampleReason;
	}

	public void setSampleReason(final String sampleReason) {
		this.sampleReason = sampleReason;
	}

	public Integer getTakenStaffId() {
		return takenStaffId;
	}

	public void setTakenStaffId(final Integer takenStaffId) {
		this.takenStaffId = takenStaffId;
	}

	public Integer getWitnessStaffId() {
		return witnessStaffId;
	}

	public void setWitnessStaffId(final Integer witnessStaffId) {
		this.witnessStaffId = witnessStaffId;
	}


	public Integer getOfCcId() {
		return ofCcId;
	}

	public void setOfCcId(final Integer ofCcId) {
		this.ofCcId = ofCcId;
	}

	public Integer getOffenderSampleId() {
		return offenderSampleId;
	}

	public void setOffenderSampleId(final Integer offenderSampleId) {
		this.offenderSampleId = offenderSampleId;
	}

	public String getExternalTestAgencyFlag() {
		return externalTestAgencyFlag;
	}

	public void setExternalTestAgencyFlag(final String externalTestAgencyFlag) {
		this.externalTestAgencyFlag = externalTestAgencyFlag;
	}

	public Integer getTestStaffId() {
		return testStaffId;
	}

	public void setTestStaffId(final Integer testStaffId) {
		this.testStaffId = testStaffId;
	}

	public Integer getTestCorporateId() {
		return testCorporateId;
	}

	public void setTestCorporateId(final Integer testCorporateId) {
		this.testCorporateId = testCorporateId;
	}

	public Date getSampleTestDate() {
		return sampleTestDate;
	}

	public void setSampleTestDate(final Date sampleTestDate) {
		this.sampleTestDate = sampleTestDate;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getTestedPositive() {
		return testedPositive;
	}

	public void setTestedPositive(final String testedPositive) {
		this.testedPositive = testedPositive;
	}

	public String getTestedBy() {
		return testedBy;
	}

	public void setTestedBy(final String testedBy) {
		this.testedBy = testedBy;
	}
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	public Date getSampleTime() {
		return sampleTime;
	}

	public void setSampleTime(final Date sampleTime) {
		this.sampleTime = sampleTime;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	/**
	 * @return the countOffSub
	 */
	public Integer getCountOffSub() {
		return countOffSub;
	}

	/**
	 * @param countOffSub the countOffSub to set
	 */
	public void setCountOffSub(final Integer countOffSub) {
		this.countOffSub = countOffSub;
	}

	/**
	 * @return the testedByTemp
	 */
	public String getTestedByTemp() {
		return testedByTemp;
	}

	/**
	 * @param testedByTemp the testedByTemp to set
	 */
	public void setTestedByTemp(final String testedByTemp) {
		this.testedByTemp = testedByTemp;
	}

	/**
	 * @return the stdTemp
	 */
	public Date getStdTemp() {
		return stdTemp;
	}

	/**
	 * @param stdTemp the stdTemp to set
	 */
	public void setStdTemp(final Date stdTemp) {
		this.stdTemp = stdTemp;
	}
	/**
	 * @return the testedPositiveFlag
	 */
	public String getTestedPositiveFlag() {
		return testedPositiveFlag;
	}
	/**
	 * @param testedPositiveFlag the testedPositiveFlag to set
	 */
	public void setTestedPositiveFlag(String testedPositiveFlag) {
		this.testedPositiveFlag = testedPositiveFlag;
	}
}
