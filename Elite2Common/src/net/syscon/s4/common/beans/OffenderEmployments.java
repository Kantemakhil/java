package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class OffenderEmployments extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("employSeq")
	private Integer employSeq;

	@JsonProperty("employmentDate")
	private Date employmentDate;

	@JsonProperty("employmentPostCode")
	private String employmentPostCode;

	@JsonProperty("employmentPostDBCode")
	private String employmentPostDBCode;

	@JsonProperty("employmentType")
	private String employmentType;

	@JsonProperty("terminationDate")
	private Date terminationDate;

	@JsonProperty("employerName")
	private String employerName;

	@JsonProperty("supervisorName")
	private String supervisorName;

	@JsonProperty("position")
	private String position;

	@JsonProperty("terminationReasonText")
	private String terminationReasonText;

	@JsonProperty("wage")
	private BigDecimal wage;

	@JsonProperty("wagePeriodCode")
	private String wagePeriodCode;

	@JsonProperty("wageStr")
	private String wageStr;

	@JsonProperty("occupationsCode")
	private String occupationsCode;

	@JsonProperty("occupationsDBCode")
	private String occupationsDBCode;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("rootOffenderId")
	private Integer rootOffenderId;

	@JsonProperty("contactType")
	private String contactType;

	@JsonProperty("contactNumber")
	private String contactNumber;

	@JsonProperty("scheduleType")
	private String scheduleType;

	@JsonProperty("scheduleHours")
	private Integer scheduleHours;

	@JsonProperty("hoursWeek")
	private Integer hoursWeek;

	@JsonProperty("hoursWeekStr")
	private String hoursWeekStr;

	@JsonProperty("partialEmploymentDateFlag")
	private String partialEmploymentDateFlag;

	@JsonProperty("partialTerminationDateFlag")
	private String partialTerminationDateFlag;

	@JsonProperty("checkBox1")
	private String checkBox1;

	@JsonProperty("checkBox2")
	private String checkBox2;

	@JsonProperty("employerAwareFlag")
	@NotNull
	@Size(max = 1)
	private String employerAwareFlag;

	@JsonProperty("contactEmployerFlag")
	@NotNull
	@Size(max = 1)
	private String contactEmployerFlag;

	@JsonProperty("offenderEmploymentId")
	private Integer offenderEmploymentId;

	@JsonProperty("employmentSchedule")
	private String employmentSchedule;

	@JsonProperty("certification")
	private String certification;

	@JsonProperty("createDatetime")
	private Timestamp createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Timestamp modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("proposedEmployment")
	private String proposedEmployment;

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
	 * @return the employSeq
	 */
	public Integer getEmploySeq() {
		return employSeq;
	}

	/**
	 * @param employSeq
	 *            the employSeq to set
	 */
	public void setEmploySeq(final Integer employSeq) {
		this.employSeq = employSeq;
	}

	/**
	 * @return the employmentDate
	 */
	public Date getEmploymentDate() {
		return employmentDate;
	}

	/**
	 * @param employmentDate
	 *            the employmentDate to set
	 */
	public void setEmploymentDate(final Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	/**
	 * @return the employmentPostCode
	 */
	public String getEmploymentPostCode() {
		return employmentPostCode;
	}

	/**
	 * @param employmentPostCode
	 *            the employmentPostCode to set
	 */
	public void setEmploymentPostCode(final String employmentPostCode) {
		this.employmentPostCode = employmentPostCode;
	}

	/**
	 * @return the employmentType
	 */
	public String getEmploymentType() {
		return employmentType;
	}

	/**
	 * @param employmentType
	 *            the employmentType to set
	 */
	public void setEmploymentType(final String employmentType) {
		this.employmentType = employmentType;
	}

	/**
	 * @return the terminationDate
	 */
	public Date getTerminationDate() {
		return terminationDate;
	}

	/**
	 * @param terminationDate
	 *            the terminationDate to set
	 */
	public void setTerminationDate(final Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	/**
	 * @return the mployerName
	 */
	public String getEmployerName() {
		return employerName;
	}

	/**
	 * @param mployerName
	 *            the mployerName to set
	 */
	public void setEmployerName(final String mployerName) {
		this.employerName = mployerName;
	}

	/**
	 * @return the supervisorName
	 */
	public String getSupervisorName() {
		return supervisorName;
	}

	/**
	 * @param supervisorName
	 *            the supervisorName to set
	 */
	public void setSupervisorName(final String supervisorName) {
		this.supervisorName = supervisorName;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(final String position) {
		this.position = position;
	}

	/**
	 * @return the terminationReasonText
	 */
	public String getTerminationReasonText() {
		return terminationReasonText;
	}

	/**
	 * @param terminationReasonText
	 *            the terminationReasonText to set
	 */
	public void setTerminationReasonText(String terminationReasonText) {
		this.terminationReasonText = terminationReasonText;
	}

	/**
	 * @return the wage
	 */
	public BigDecimal getWage() {
		return wage;
	}

	/**
	 * @param wage
	 *            the wage to set
	 */
	public void setWage(final BigDecimal wage) {
		this.wage = wage;
	}

	/**
	 * @return the wagePeriodCode
	 */
	public String getWagePeriodCode() {
		return wagePeriodCode;
	}

	/**
	 * @param wagePeriodCode
	 *            the wagePeriodCode to set
	 */
	public void setWagePeriodCode(final String wagePeriodCode) {
		this.wagePeriodCode = wagePeriodCode;
	}

	/**
	 * @return the occupationsCode
	 */
	public String getOccupationsCode() {
		return occupationsCode;
	}

	/**
	 * @param occupationsCode
	 *            the occupationsCode to set
	 */
	public void setOccupationsCode(final String occupationsCode) {
		this.occupationsCode = occupationsCode;
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
	 * @return the contactType
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 * @param contactType
	 *            the contactType to set
	 */
	public void setContactType(final String contactType) {
		this.contactType = contactType;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 *            the contactNumber to set
	 */
	public void setContactNumber(final String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the scheduleType
	 */
	public String getScheduleType() {
		return scheduleType;
	}

	/**
	 * @param scheduleType
	 *            the scheduleType to set
	 */
	public void setScheduleType(final String scheduleType) {
		this.scheduleType = scheduleType;
	}

	/**
	 * @return the scheduleHours
	 */
	public Integer getScheduleHours() {
		return scheduleHours;
	}

	/**
	 * @param scheduleHours
	 *            the scheduleHours to set
	 */
	public void setScheduleHours(final Integer scheduleHours) {
		this.scheduleHours = scheduleHours;
	}

	/**
	 * @return the hoursWeek
	 */
	public Integer getHoursWeek() {
		return hoursWeek;
	}

	/**
	 * @param hoursWeek
	 *            the hoursWeek to set
	 */
	public void setHoursWeek(final Integer hoursWeek) {
		this.hoursWeek = hoursWeek;
	}

	/**
	 * @return the partialEmploymentDateFlag
	 */
	public String getPartialEmploymentDateFlag() {
		return partialEmploymentDateFlag;
	}

	/**
	 * @param partialEmploymentDateFlag
	 *            the partialEmploymentDateFlag to set
	 */
	public void setPartialEmploymentDateFlag(final String partialEmploymentDateFlag) {
		this.partialEmploymentDateFlag = partialEmploymentDateFlag;
	}

	/**
	 * @return the partialTerminationDateFlag
	 */
	public String getPartialTerminationDateFlag() {
		return partialTerminationDateFlag;
	}

	/**
	 * @param partialTerminationDateFlag
	 *            the partialTerminationDateFlag to set
	 */
	public void setPartialTerminationDateFlag(final String partialTerminationDateFlag) {
		this.partialTerminationDateFlag = partialTerminationDateFlag;
	}

	/**
	 * @return the checkBox1
	 */
	public String getCheckBox1() {
		return checkBox1;
	}

	/**
	 * @param checkBox1
	 *            the checkBox1 to set
	 */
	public void setCheckBox1(final String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	/**
	 * @return the checkBox2
	 */
	public String getCheckBox2() {
		return checkBox2;
	}

	/**
	 * @param checkBox2
	 *            the checkBox2 to set
	 */
	public void setCheckBox2(final String checkBox2) {
		this.checkBox2 = checkBox2;
	}

	/**
	 * @return the employerAwareFlag
	 */
	public String getEmployerAwareFlag() {
		return employerAwareFlag;
	}

	/**
	 * @param employerAwareFlag
	 *            the employerAwareFlag to set
	 */
	public void setEmployerAwareFlag(final String employerAwareFlag) {
		this.employerAwareFlag = employerAwareFlag;
	}

	/**
	 * @return the contactEmployerFlag
	 */
	public String getContactEmployerFlag() {
		return contactEmployerFlag;
	}

	/**
	 * @param contactEmployerFlag
	 *            the contactEmployerFlag to set
	 */
	public void setContactEmployerFlag(final String contactEmployerFlag) {
		this.contactEmployerFlag = contactEmployerFlag;
	}

	/**
	 * @return the offenderEmploymentId
	 */
	public Integer getOffenderEmploymentId() {
		return offenderEmploymentId;
	}

	/**
	 * @param offenderEmploymentId
	 *            the offenderEmploymentId to set
	 */
	public void setOffenderEmploymentId(final Integer offenderEmploymentId) {
		this.offenderEmploymentId = offenderEmploymentId;
	}

	/**
	 * @return the employmentSchedule
	 */
	public String getEmploymentSchedule() {
		return employmentSchedule;
	}

	/**
	 * @param employmentSchedule
	 *            the employmentSchedule to set
	 */
	public void setEmploymentSchedule(final String employmentSchedule) {
		this.employmentSchedule = employmentSchedule;
	}

	/**
	 * @return the certification
	 */
	public String getCertification() {
		return certification;
	}

	/**
	 * @param certification
	 *            the certification to set
	 */
	public void setCertification(final String certification) {
		this.certification = certification;
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

	public String getEmploymentPostDBCode() {
		return employmentPostDBCode;
	}

	final public void setEmploymentPostDBCode(final String employmentPostDBCode) {
		this.employmentPostDBCode = employmentPostDBCode;
	}

	public String getOccupationsDBCode() {
		return occupationsDBCode;
	}

	public void setOccupationsDBCode(final String occupationsDBCode) {
		this.occupationsDBCode = occupationsDBCode;
	}

	public String getWageStr() {
		return wageStr;
	}

	public void setWageStr(final String wageStr) {
		this.wageStr = wageStr;
	}

	public String getHoursWeekStr() {
		return hoursWeekStr;
	}

	public void setHoursWeekStr(final String hoursWeekStr) {
		this.hoursWeekStr = hoursWeekStr;
	}

	/**
	 * @return the proposedEmployment
	 */
	public String getProposedEmployment() {
		return proposedEmployment;
	}

	/**
	 * @param proposedEmployment
	 *            the proposedEmployment to set
	 */
	public void setProposedEmployment(String proposedEmployment) {
		this.proposedEmployment = proposedEmployment;
	}

	/**
	 * @param OFFENDER_BOOK_ID
	 *            OFFENDER_BOOK_ID to set
	 */

}