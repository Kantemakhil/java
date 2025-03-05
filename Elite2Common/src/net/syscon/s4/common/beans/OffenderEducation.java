package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderEducation extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("agyLocId")
	private String agyLocId;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("educationLevelCode")
	private String educationLevelCode;

	@JsonProperty("educationDbLevelCode")
	private String educationDbLevelCode;

	@JsonProperty("educationSchedule")
	private String educationSchedule;

	@JsonProperty("educationDbSchedule")
	private String educationDbSchedule;

	@JsonProperty("educationType")
	private String educationType;

	@JsonProperty("endDate")
	private Date endDate;

	@JsonProperty("graduationYear")
	private BigDecimal graduationYear;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("numberOfYears")
	private BigDecimal numberOfYears;

	@JsonProperty("partialEndDateFlag")
	private String partialEndDateFlag;

	@JsonProperty("partialStartDateFlag")
	private String partialStartDateFlag;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("schoolCode")
	private String schoolCode;

	@JsonProperty("schoolName")
	private String schoolName;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("specialEducationFlag")
	private String specialEducationFlag;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("studentId")
	private String studentId;

	@JsonProperty("studyAreaCode")
	private String studyAreaCode;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("educationSeq")
	private long educationSeq;

	/**
	 *
	 * @return
	 */
	public String getAgyLocId() {
		return agyLocId;
	}

	/**
	 *
	 * @param agyLocId
	 */
	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 *
	 * @return
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 *
	 * @param caseloadType
	 */
	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getEducationLevelCode() {
		return educationLevelCode;
	}

	/**
	 *
	 * @param educationLevelCode
	 */
	public void setEducationLevelCode(String educationLevelCode) {
		this.educationLevelCode = educationLevelCode;
	}

	/**
	 *
	 * @return
	 */
	public String getEducationSchedule() {
		return educationSchedule;
	}

	/**
	 *
	 * @param educationSchedule
	 */
	public void setEducationSchedule(String educationSchedule) {
		this.educationSchedule = educationSchedule;
	}

	/**
	 *
	 * @return
	 */
	public String getEducationType() {
		return educationType;
	}

	/**
	 *
	 * @param educationType
	 */
	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}

	/**
	 *
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 *
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getGraduationYear() {
		return graduationYear;
	}

	/**
	 *
	 * @param graduationYear
	 */
	public void setGraduationYear(BigDecimal graduationYear) {
		this.graduationYear = graduationYear;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getNumberOfYears() {
		return numberOfYears;
	}

	/**
	 *
	 * @param numberOfYears
	 */
	public void setNumberOfYears(BigDecimal numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	/**
	 *
	 * @return
	 */
	public String getPartialEndDateFlag() {
		return partialEndDateFlag;
	}

	/**
	 *
	 * @param partialEndDateFlag
	 */
	public void setPartialEndDateFlag(String partialEndDateFlag) {
		this.partialEndDateFlag = partialEndDateFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getPartialStartDateFlag() {
		return partialStartDateFlag;
	}

	/**
	 *
	 * @param partialStartDateFlag
	 */
	public void setPartialStartDateFlag(String partialStartDateFlag) {
		this.partialStartDateFlag = partialStartDateFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 *
	 * @param rootOffenderId
	 */
	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getSchoolCode() {
		return schoolCode;
	}

	/**
	 *
	 * @param schoolCode
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	/**
	 *
	 * @return
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 *
	 * @param schoolName
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getSpecialEducationFlag() {
		return specialEducationFlag;
	}

	/**
	 *
	 * @param specialEducationFlag
	 */
	public void setSpecialEducationFlag(String specialEducationFlag) {
		this.specialEducationFlag = specialEducationFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 *
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 *
	 * @return
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 *
	 * @param studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 *
	 * @return
	 */
	public String getStudyAreaCode() {
		return studyAreaCode;
	}

	/**
	 *
	 * @param studyAreaCode
	 */
	public void setStudyAreaCode(String studyAreaCode) {
		this.studyAreaCode = studyAreaCode;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBookings() {
		return offenderBookings;
	}

	/**
	 *
	 * @param offenderBookings
	 */
	public void setOffenderBookings(OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getEducationSeq() {
		return educationSeq;
	}

	/**
	 *
	 * @param educationSeq
	 */
	public void setEducationSeq(long educationSeq) {
		this.educationSeq = educationSeq;
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
	public void setEducationDbLevelCode(String educationDbLevelCode) {
		this.educationDbLevelCode = educationDbLevelCode;
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
	public void setEducationDbSchedule(String educationDbSchedule) {
		this.educationDbSchedule = educationDbSchedule;
	}

}