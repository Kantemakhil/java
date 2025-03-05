package net.syscon.s4.inst.careinplacement.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffObsPeriodChecks extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("obsPeriodId")
	private long obsPeriodId;
	
	@JsonProperty("checkId")
	private long checkId;
	
	@JsonProperty("checkDatetime")
	private Date checkDatetime;	
	
	@JsonProperty("userId")
	private String userId;	
	
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
	
	@JsonProperty("checkTime")
	private Date checkTime;
	
	@JsonProperty("scheduleDatetime")
	private Date scheduleDatetime;
	
	@JsonProperty("scheduleTime")
	private Date scheduleTime;
	
	@JsonProperty("frequency")
	private long frequency;
	
	@JsonProperty("characteristicsCode")
	private String characteristicsCode;
	
	@JsonProperty("observationType")
	private String observationType;
	
	@JsonProperty("overdueFlag")
	private String overdueFlag;
	
	@JsonProperty("nextScheduleDate")
	private Date nextScheduleDate;
	
	@JsonProperty("returnedOutput")
	private BigDecimal returnedOutput;
	
	@JsonProperty("commentText")
	private String commentText;
	
	@JsonProperty("performingStaffId")
	private BigDecimal performingStaffId;
	
	@JsonProperty("obsTypeVersionId")
	private BigDecimal obsTypeVersionId;
	public OffObsPeriodChecks() {
		
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getObsPeriodId() {
		return obsPeriodId;
	}

	public void setObsPeriodId(final long obsPeriodId) {
		this.obsPeriodId = obsPeriodId;
	}

	public long getCheckId() {
		return checkId;
	}

	public void setCheckId(final long checkId) {
		this.checkId = checkId;
	}

	public Date getCheckDatetime() {
		return checkDatetime;
	}

	public void setCheckDatetime(final Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(final Date checkTime) {
		this.checkTime = checkTime;
	}
	
	public Date getScheduleDatetime() {
		return scheduleDatetime;
	}

	public void setScheduleDatetime(Date scheduleDatetime) {
		this.scheduleDatetime = scheduleDatetime;
	}

	public Date getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(Date scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public long getFrequency() {
		return frequency;
	}

	public void setFrequency(final long frequency) {
		this.frequency = frequency;
	}

	public String getCharacteristicsCode() {
		return characteristicsCode;
	}

	public void setCharacteristicsCode(final String characteristicsCode) {
		this.characteristicsCode = characteristicsCode;
	}

	public String getObservationType() {
		return observationType;
	}

	public void setObservationType(final String observationType) {
		this.observationType = observationType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOverdueFlag() {
		return overdueFlag;
	}

	public void setOverdueFlag(String overdueFlag) {
		this.overdueFlag = overdueFlag;
	}

	public Date getNextScheduleDate() {
		return nextScheduleDate;
	}

	public void setNextScheduleDate(Date nextScheduleDate) {
		this.nextScheduleDate = nextScheduleDate;
	}

	public BigDecimal getReturnedOutput() {
		return returnedOutput;
	}

	public void setReturnedOutput(BigDecimal returnedOutput) {
		this.returnedOutput = returnedOutput;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public BigDecimal getPerformingStaffId() {
		return performingStaffId;
	}

	public void setPerformingStaffId(BigDecimal performingStaffId) {
		this.performingStaffId = performingStaffId;
	}

	public BigDecimal getObsTypeVersionId() {
		return obsTypeVersionId;
	}

	public void setObsTypeVersionId(BigDecimal obsTypeVersionId) {
		this.obsTypeVersionId = obsTypeVersionId;
	}	

	
	
}
