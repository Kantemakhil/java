package net.syscon.s4.portalapp.beans;

import java.io.Serializable;
import java.util.Date;


public class Term implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long termId;
	private String sentenceTermCode;
	private String lifeSentenceFlag;
	private Date startDate;
	private Integer years;
	private Integer months;
	private Integer weeks;
	private Integer days;
	private Integer hours;
	
	public String getSentenceTermCode() {
		return sentenceTermCode;
	}
	public void setSentenceTermCode(String sentenceTermCode) {
		this.sentenceTermCode = sentenceTermCode;
	}
	public Long getTermId() {
		return termId;
	}
	public void setTermId(Long termId) {
		this.termId = termId;
	}
	public String getLifeSentenceFlag() {
		return lifeSentenceFlag;
	}
	public void setLifeSentenceFlag(String lifeSentenceFlag) {
		this.lifeSentenceFlag = lifeSentenceFlag;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}
	public Integer getMonths() {
		return months;
	}
	public void setMonths(Integer months) {
		this.months = months;
	}
	public Integer getWeeks() {
		return weeks;
	}
	public void setWeeks(Integer weeks) {
		this.weeks = weeks;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
}
