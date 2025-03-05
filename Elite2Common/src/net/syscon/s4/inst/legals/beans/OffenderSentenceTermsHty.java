package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderSentenceTermsHty extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private Date createDatetime;

	private String createUserId;

	private BigDecimal days;

	private Date endDate;

	private BigDecimal hours;

	private String lifeSentenceFlag;

	private Date modifyDatetime;

	private String modifyUserId;

	private BigDecimal months;

	private String sealFlag;

	private String sentenceTermCode;

	private Date startDate;

	private BigDecimal weeks;

	private BigDecimal years;

	private long offenderBookId;

	private long sentenceSeq;

	private long termSeq;
	private long gapSeq;
	
	private String paroleSupervision;
	
	private Date startTime;
	
	private Date endTime;
	
	private BigDecimal workHours;
	private BigDecimal attendenceHours;
	private BigDecimal totalHours;
	private long sentenceEventId;

	public  OffenderSentenceTermsHty() {
		// TODO Auto-generated constructor stub
	}
	
	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public long getTermSeq() {
		return termSeq;
	}

	public void setTermSeq(long termSeq) {
		this.termSeq = termSeq;
	}

	

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public BigDecimal getDays() {
		return this.days;
	}

	public void setDays(BigDecimal days) {
		this.days = days;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getHours() {
		return this.hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

	public String getLifeSentenceFlag() {
		return this.lifeSentenceFlag;
	}

	public void setLifeSentenceFlag(String lifeSentenceFlag) {
		this.lifeSentenceFlag = lifeSentenceFlag;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public BigDecimal getMonths() {
		return this.months;
	}

	public void setMonths(BigDecimal months) {
		this.months = months;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSentenceTermCode() {
		return this.sentenceTermCode;
	}

	public void setSentenceTermCode(String sentenceTermCode) {
		this.sentenceTermCode = sentenceTermCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getWeeks() {
		return this.weeks;
	}

	public void setWeeks(BigDecimal weeks) {
		this.weeks = weeks;
	}

	public BigDecimal getYears() {
		return this.years;
	}

	public void setYears(BigDecimal years) {
		this.years = years;
	}

	public String getParoleSupervision() {
		return paroleSupervision;
	}

	public void setParoleSupervision(String paroleSupervision) {
		this.paroleSupervision = paroleSupervision;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public long getGapSeq() {
		return gapSeq;
	}

	public void setGapSeq(long gapSeq) {
		this.gapSeq = gapSeq;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getWorkHours() {
		return workHours;
	}

	public void setWorkHours(BigDecimal workHours) {
		this.workHours = workHours;
	}

	public BigDecimal getAttendenceHours() {
		return attendenceHours;
	}

	public void setAttendenceHours(BigDecimal attendenceHours) {
		this.attendenceHours = attendenceHours;
	}

	public long getSentenceEventId() {
		return sentenceEventId;
	}

	public void setSentenceEventId(long sentenceEventId) {
		this.sentenceEventId = sentenceEventId;
	}

	public BigDecimal getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(BigDecimal totalHours) {
		this.totalHours = totalHours;
	}


}
