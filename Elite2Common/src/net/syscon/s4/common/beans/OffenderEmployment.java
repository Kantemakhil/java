package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderEmployment extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("certification")
	private String certification;

	@JsonProperty("checkBox1")
	private String checkBox1;

	@JsonProperty("checkBox2")
	private String checkBox2;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("contactEmployerFlag")
	private String contactEmployerFlag;

	@JsonProperty("contactNumber")
	private String contactNumber;

	@JsonProperty("contactType")
	private String contactType;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("employerAwareFlag")
	private String employerAwareFlag;

	@JsonProperty("employerName")
	private String employerName;

	@JsonProperty("employmentDate")
	private Date employmentDate;

	@JsonProperty("employmentPostCode")
	private String employmentPostCode;

	@JsonProperty("employmentSchedule")
	private String employmentSchedule;

	@JsonProperty("employmentType")
	private String employmentType;

	@JsonProperty("hoursWeek")
	private BigDecimal hoursWeek;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("occupationsCode")
	private String occupationsCode;

	@JsonProperty("offenderEmploymentId")
	private BigDecimal offenderEmploymentId;

	@JsonProperty("partialEmploymentDateFlag")
	private String partialEmploymentDateFlag;

	@JsonProperty("partialTerminationDateFlag")
	private String partialTerminationDateFlag;

	@JsonProperty("position")
	private String position;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("scheduleHours")
	private BigDecimal scheduleHours;

	@JsonProperty("scheduleType")
	private String scheduleType;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("supervisorName")
	private String supervisorName;

	@JsonProperty("terminationDate")
	private Date terminationDate;

	@JsonProperty("terminationReasonText")
	private String terminationReasonText;

	@JsonProperty("wage")
	private BigDecimal wage;

	@JsonProperty("wagePeriodCode")
	private String wagePeriodCode;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("employSeq")
	private long employSeq;

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
	public String getCertification() {
		return certification;
	}

	/**
	 *
	 * @param certification
	 */
	public void setCertification(String certification) {
		this.certification = certification;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox1() {
		return checkBox1;
	}

	/**
	 *
	 * @param checkBox1
	 */
	public void setCheckBox1(String checkBox1) {
		this.checkBox1 = checkBox1;
	}

	/**
	 *
	 * @return
	 */
	public String getCheckBox2() {
		return checkBox2;
	}

	/**
	 *
	 * @param checkBox2
	 */
	public void setCheckBox2(String checkBox2) {
		this.checkBox2 = checkBox2;
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
	public String getContactEmployerFlag() {
		return contactEmployerFlag;
	}

	/**
	 *
	 * @param contactEmployerFlag
	 */
	public void setContactEmployerFlag(String contactEmployerFlag) {
		this.contactEmployerFlag = contactEmployerFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 *
	 * @param contactNumber
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 *
	 * @return
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 *
	 * @param contactType
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
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
	public String getEmployerAwareFlag() {
		return employerAwareFlag;
	}

	/**
	 *
	 * @param employerAwareFlag
	 */
	public void setEmployerAwareFlag(String employerAwareFlag) {
		this.employerAwareFlag = employerAwareFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getEmployerName() {
		return employerName;
	}

	/**
	 *
	 * @param employerName
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	/**
	 *
	 * @return
	 */
	public Date getEmploymentDate() {
		return employmentDate;
	}

	/**
	 *
	 * @param employmentDate
	 */
	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	/**
	 *
	 * @return
	 */
	public String getEmploymentPostCode() {
		return employmentPostCode;
	}

	/**
	 *
	 * @param employmentPostCode
	 */
	public void setEmploymentPostCode(String employmentPostCode) {
		this.employmentPostCode = employmentPostCode;
	}

	/**
	 *
	 * @return
	 */
	public String getEmploymentSchedule() {
		return employmentSchedule;
	}

	/**
	 *
	 * @param employmentSchedule
	 */
	public void setEmploymentSchedule(String employmentSchedule) {
		this.employmentSchedule = employmentSchedule;
	}

	/**
	 *
	 * @return
	 */
	public String getEmploymentType() {
		return employmentType;
	}

	/**
	 *
	 * @param employmentType
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getHoursWeek() {
		return hoursWeek;
	}

	/**
	 *
	 * @param hoursWeek
	 */
	public void setHoursWeek(BigDecimal hoursWeek) {
		this.hoursWeek = hoursWeek;
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
	public String getOccupationsCode() {
		return occupationsCode;
	}

	/**
	 *
	 * @param occupationsCode
	 */
	public void setOccupationsCode(String occupationsCode) {
		this.occupationsCode = occupationsCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOffenderEmploymentId() {
		return offenderEmploymentId;
	}

	/**
	 *
	 * @param offenderEmploymentId
	 */
	public void setOffenderEmploymentId(BigDecimal offenderEmploymentId) {
		this.offenderEmploymentId = offenderEmploymentId;
	}

	/**
	 *
	 * @return
	 */
	public String getPartialEmploymentDateFlag() {
		return partialEmploymentDateFlag;
	}

	/**
	 *
	 * @param partialEmploymentDateFlag
	 */
	public void setPartialEmploymentDateFlag(String partialEmploymentDateFlag) {
		this.partialEmploymentDateFlag = partialEmploymentDateFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getPartialTerminationDateFlag() {
		return partialTerminationDateFlag;
	}

	/**
	 *
	 * @param partialTerminationDateFlag
	 */
	public void setPartialTerminationDateFlag(String partialTerminationDateFlag) {
		this.partialTerminationDateFlag = partialTerminationDateFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getPosition() {
		return position;
	}

	/**
	 *
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
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
	public BigDecimal getScheduleHours() {
		return scheduleHours;
	}

	/**
	 *
	 * @param scheduleHours
	 */
	public void setScheduleHours(BigDecimal scheduleHours) {
		this.scheduleHours = scheduleHours;
	}

	/**
	 *
	 * @return
	 */
	public String getScheduleType() {
		return scheduleType;
	}

	/**
	 *
	 * @param scheduleType
	 */
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
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
	public String getSupervisorName() {
		return supervisorName;
	}

	/**
	 *
	 * @param supervisorName
	 */
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	/**
	 *
	 * @return
	 */
	public Date getTerminationDate() {
		return terminationDate;
	}

	/**
	 *
	 * @param terminationDate
	 */
	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	/**
	 *
	 * @return
	 */
	public String getTerminationReasonText() {
		return terminationReasonText;
	}

	/**
	 *
	 * @param terminationReasonText
	 */
	public void setTerminationReasonText(String terminationReasonText) {
		this.terminationReasonText = terminationReasonText;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getWage() {
		return wage;
	}

	/**
	 *
	 * @param wage
	 */
	public void setWage(BigDecimal wage) {
		this.wage = wage;
	}

	/**
	 *
	 * @return
	 */
	public String getWagePeriodCode() {
		return wagePeriodCode;
	}

	/**
	 *
	 * @param wagePeriodCode
	 */
	public void setWagePeriodCode(String wagePeriodCode) {
		this.wagePeriodCode = wagePeriodCode;
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
	public long getEmploySeq() {
		return employSeq;
	}

	/**
	 *
	 * @param employSeq
	 */
	public void setEmploySeq(long employSeq) {
		this.employSeq = employSeq;
	}

}