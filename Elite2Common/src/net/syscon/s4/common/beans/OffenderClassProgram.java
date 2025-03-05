package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderClassProgram extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("approvedStaffId")
	private BigDecimal approvedStaffId;

	@JsonProperty("classProgramType")
	private String classProgramType;

	@JsonProperty("completeDate")
	private Date completeDate;

	@JsonProperty("conditionCommentText")
	private String conditionCommentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("defaultCommentText")
	private String defaultCommentText;

	@JsonProperty("effectiveDate")
	private Date effectiveDate;

	@JsonProperty("entryCommentText")
	private String entryCommentText;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("programResultCode")
	private String programResultCode;

	@JsonProperty("recordStatus")
	private String recordStatus;

	@JsonProperty("reviewDate")
	private Date reviewDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("classProgSeq")
	private long classProgSeq;

	/**
	 *
	 * @return
	 */
	public BigDecimal getApprovedStaffId() {
		return approvedStaffId;
	}

	/**
	 *
	 * @param approvedStaffId
	 */
	public void setApprovedStaffId(BigDecimal approvedStaffId) {
		this.approvedStaffId = approvedStaffId;
	}

	/**
	 *
	 * @return
	 */
	public String getClassProgramType() {
		return classProgramType;
	}

	/**
	 *
	 * @param classProgramType
	 */
	public void setClassProgramType(String classProgramType) {
		this.classProgramType = classProgramType;
	}

	/**
	 *
	 * @return
	 */
	public Date getCompleteDate() {
		return completeDate;
	}

	/**
	 *
	 * @param completeDate
	 */
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	/**
	 *
	 * @return
	 */
	public String getConditionCommentText() {
		return conditionCommentText;
	}

	/**
	 *
	 * @param conditionCommentText
	 */
	public void setConditionCommentText(String conditionCommentText) {
		this.conditionCommentText = conditionCommentText;
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
	public String getDefaultCommentText() {
		return defaultCommentText;
	}

	/**
	 *
	 * @param defaultCommentText
	 */
	public void setDefaultCommentText(String defaultCommentText) {
		this.defaultCommentText = defaultCommentText;
	}

	/**
	 *
	 * @return
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 *
	 * @param effectiveDate
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 *
	 * @return
	 */
	public String getEntryCommentText() {
		return entryCommentText;
	}

	/**
	 *
	 * @param entryCommentText
	 */
	public void setEntryCommentText(String entryCommentText) {
		this.entryCommentText = entryCommentText;
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
	public String getProgramResultCode() {
		return programResultCode;
	}

	/**
	 *
	 * @param programResultCode
	 */
	public void setProgramResultCode(String programResultCode) {
		this.programResultCode = programResultCode;
	}

	/**
	 *
	 * @return
	 */
	public String getRecordStatus() {
		return recordStatus;
	}

	/**
	 *
	 * @param recordStatus
	 */
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	/**
	 *
	 * @return
	 */
	public Date getReviewDate() {
		return reviewDate;
	}

	/**
	 *
	 * @param reviewDate
	 */
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
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
	public long getClassProgSeq() {
		return classProgSeq;
	}

	/**
	 *
	 * @param classProgSeq
	 */
	public void setClassProgSeq(long classProgSeq) {
		this.classProgSeq = classProgSeq;
	}

}