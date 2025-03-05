package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class MostSeriousOffence extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	@JsonProperty("sentenceSeq")
	private long sentenceSeq;
	@JsonProperty("offenceDesc")
	private String offenceDesc;
	@JsonProperty("complicityTypeDesc")
	private String complicityTypeDesc;
	@JsonProperty("intCnts")
	private String intCnts;
	@JsonProperty("sentenceTermCode")
	private String sentenceTermCode;
	@JsonProperty("termPeriod")
	private String termPeriod;
	@JsonProperty("startDate")
	private Date startDate;
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("mostSeriousSentenceSeq")
	private long mostSeriousSentenceSeq;
	@JsonProperty("initialCount")
	private int initialCount;

	public int getInitialCount() {
		return initialCount;
	}

	public void setInitialCount(int initialCount) {
		this.initialCount = initialCount;
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

	public String getOffenceDesc() {
		return offenceDesc;
	}

	public void setOffenceDesc(String offenceDesc) {
		this.offenceDesc = offenceDesc;
	}

	public String getComplicityTypeDesc() {
		return complicityTypeDesc;
	}

	public void setComplicityTypeDesc(String complicityTypeDesc) {
		this.complicityTypeDesc = complicityTypeDesc;
	}

	public String getIntCnts() {
		return intCnts;
	}

	public void setIntCnts(String intCnts) {
		this.intCnts = intCnts;
	}

	public String getSentenceTermCode() {
		return sentenceTermCode;
	}

	public void setSentenceTermCode(String sentenceTermCode) {
		this.sentenceTermCode = sentenceTermCode;
	}

	public String getTermPeriod() {
		return termPeriod;
	}

	public void setTermPeriod(String termPeriod) {
		this.termPeriod = termPeriod;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getMostSeriousSentenceSeq() {
		return mostSeriousSentenceSeq;
	}

	public void setMostSeriousSentenceSeq(long mostSeriousSentenceSeq) {
		this.mostSeriousSentenceSeq = mostSeriousSentenceSeq;
	}

	@Override
	public String toString() {
		return "MostSeriousOffence [offenderBookId=" + offenderBookId + ", sentenceSeq=" + sentenceSeq
				+ ", offenceDesc=" + offenceDesc + ", complicityTypeDesc=" + complicityTypeDesc + ", intCnts=" + intCnts
				+ ", sentenceTermCode=" + sentenceTermCode + ", termPeriod=" + termPeriod + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", mostSeriousSentenceSeq=" + mostSeriousSentenceSeq + "]";
	}

}
