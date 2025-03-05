package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderEducations extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("educationSeq")
	private Integer educationSeq;

	@JsonProperty("educationType")
	private String educationType;

	@JsonProperty("studyAreaCode")
	private String studyAreaCode;

	@JsonProperty("educationLevelCode")
	private String educationLevelCode;

	@JsonProperty("numberOfYears")
	private Integer numberOfYears;

	@JsonProperty("graduationYear")
	private Integer graduationYear;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("schoolName")
	@NotNull
	@Size(max = 240)
	private String schoolName;

	@JsonProperty("specialEducationFlag")
	@NotNull
	@Size(max = 1)
	private String specialEducationFlag;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("modifyDatetime")
	private Timestamp modifyDatetime;

	@JsonProperty("rootOffenderId")
	private Integer rootOffenderId;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("partialEndDateFlag")
	private String partialEndDateFlag;

	@JsonProperty("partialStartDateFlag")
	private String partialStartDateFlag;

	@JsonProperty("studentId")
	private String studentId;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("schoolCode")
	private String schoolCode;

	@JsonProperty("createDatetime")
	private Timestamp createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("educationSchedule")
	private String educationSchedule;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("educationDbSchedule")
	private String educationDbSchedule;

	@JsonProperty("educationDbLevelCode")
	private String educationDbLevelCode;
	
	public OffenderEducations()
	{
		// OffenderEducations
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * @return the educationSeq
	 */
	public Integer getEducationSeq() {
		return educationSeq;
	}

	/**
	 * @param educationSeq
	 *            the educationSeq to set
	 */
	public void setEducationSeq(final Integer educationSeq) {
		this.educationSeq = educationSeq;
	}

	/**
	 * @return the educationType
	 */
	public String getEducationType() {
		return educationType;
	}

	/**
	 * @param educationType
	 *            the educationType to set
	 */
	public void setEducationType(final String educationType) {
		this.educationType = educationType;
	}

	/**
	 * @return the studyAreaCode
	 */
	public String getStudyAreaCode() {
		return studyAreaCode;
	}

	/**
	 * @param studyAreaCode
	 *            the studyAreaCode to set
	 */
	public void setStudyAreaCode(final String studyAreaCode) {
		this.studyAreaCode = studyAreaCode;
	}

	/**
	 * @return the educationLevelCode
	 */
	public String getEducationLevelCode() {
		return educationLevelCode;
	}

	/**
	 * @param educationLevelCode
	 *            the educationLevelCode to set
	 */
	public void setEducationLevelCode(final String educationLevelCode) {
		this.educationLevelCode = educationLevelCode;
	}

	/**
	 * @return the numberOfYears
	 */
	public Integer getNumberOfYears() {
		return numberOfYears;
	}

	/**
	 * @param numberOfYears
	 *            the numberOfYears to set
	 */
	public void setNumberOfYears(final Integer numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	/**
	 * @return the graduationYear
	 */
	public Integer getGraduationYear() {
		return graduationYear;
	}

	/**
	 * @param graduationYear
	 *            the graduationYear to set
	 */
	public void setGraduationYear(final Integer graduationYear) {
		this.graduationYear = graduationYear;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName
	 *            the schoolName to set
	 */
	public void setSchoolName(final String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 * @return the specialEducationFlag
	 */
	public String getSpecialEducationFlag() {
		return specialEducationFlag;
	}

	/**
	 * @param specialEducationFlag
	 *            the specialEducationFlag to set
	 */
	public void setSpecialEducationFlag(final String specialEducationFlag) {
		this.specialEducationFlag = specialEducationFlag;
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType
	 *            the caseloadType to set
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Timestamp getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Timestamp modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the rootOffenderId
	 */
	public Integer getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(final Integer rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the partialEendDateFlag
	 */
	public String getPartialEndDateFlag() {
		return partialEndDateFlag;
	}

	/**
	 * @param partialEendDateFlag
	 *            the partialEendDateFlag to set
	 */
	public void setPartialEndDateFlag(final String partialEendDateFlag) {
		this.partialEndDateFlag = partialEendDateFlag;
	}

	/**
	 * @return the partialStartDateFlag
	 */
	public String getPartialStartDateFlag() {
		return partialStartDateFlag;
	}

	/**
	 * @param partialStartDateFlag
	 *            the partialStartDateFlag to set
	 */
	public void setPartialStartDateFlag(final String partialStartDateFlag) {
		this.partialStartDateFlag = partialStartDateFlag;
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(final String studentId) {
		this.studentId = studentId;
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
	 * @return the schoolCode
	 */
	public String getSchoolCode() {
		return schoolCode;
	}

	/**
	 * @param schoolCode
	 *            the schoolCode to set
	 */
	public void setSchoolCode(final String schoolCode) {
		this.schoolCode = schoolCode;
	}

	/**
	 * @return the createDatetime
	 */
	public Timestamp getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the educationSchedule
	 */
	public String getEducationSchedule() {
		return educationSchedule;
	}

	/**
	 * @param educationSchedule
	 *            the educationSchedule to set
	 */
	public void setEducationSchedule(final String educationSchedule) {
		this.educationSchedule = educationSchedule;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
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

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the educationDbSchedule
	 */
	public String getEducationDbSchedule() {
		return educationDbSchedule;
	}

	/**
	 * @param educationDbSchedule
	 *            the educationDbSchedule to set
	 */
	public void setEducationDbSchedule(final String educationDbSchedule) {
		this.educationDbSchedule = educationDbSchedule;
	}

	/**
	 * @return the educationDbLevelCode
	 */
	public String getEducationDbLevelCode() {
		return educationDbLevelCode;
	}

	/**
	 * @param educationDbLevelCode
	 *            the educationDbLevelCode to set
	 */
	public void setEducationDbLevelCode(final String educationDbLevelCode) {
		this.educationDbLevelCode = educationDbLevelCode;
	}

	/**
	 * @param OFFENDER_BOOK_ID
	 *            OFFENDER_BOOK_ID to set
	 */

}