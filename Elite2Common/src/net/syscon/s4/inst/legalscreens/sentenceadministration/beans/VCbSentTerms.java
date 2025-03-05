package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VCbSentTerms extends BaseModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int offenderBookId;
	private int offenderId;
	private int sentenceSeq;
	private int termSeq;
	private int version;
	private String sentenceStatus;
	private String sentenceCategory;
	private String sentenceCalcType;
	private String sentenceTermCode;
	private String termPeriod;
	private Date startDate;
	private Date startTime;
	private Date ovrStartDate;
	private Date endDate;
	private Date endTime;
	private Date ovrEndDate;
	private String aggregateFlag;
	private String chargeSeq;
	private Date sentenceStartDate;
	private Date probableReleaseDate;
	private Date sentenceExpiryDate;
	private String offenceCode;
	private String statuteCode;
	private String chargeInfoNumber;
	private Date convictionDate;
	private String paroleSupervision;
	private String message;
	private String selectFlag;
	public String getSelectFlag() {
		return selectFlag;
	}
	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(int offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public int getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(int offenderId) {
		this.offenderId = offenderId;
	}
	public int getSentenceSeq() {
		return sentenceSeq;
	}
	public void setSentenceSeq(int sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}
	public int getTermSeq() {
		return termSeq;
	}
	public void setTermSeq(int termSeq) {
		this.termSeq = termSeq;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getSentenceStatus() {
		return sentenceStatus;
	}
	public void setSentenceStatus(String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}
	public String getSentenceCategory() {
		return sentenceCategory;
	}
	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}
	public String getSentenceCalcType() {
		return sentenceCalcType;
	}
	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getOvrStartDate() {
		return ovrStartDate;
	}
	public void setOvrStartDate(Date ovrStartDate) {
		this.ovrStartDate = ovrStartDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getOvrEndDate() {
		return ovrEndDate;
	}
	public void setOvrEndDate(Date ovrEndDate) {
		this.ovrEndDate = ovrEndDate;
	}
	public String getAggregateFlag() {
		return aggregateFlag;
	}
	public void setAggregateFlag(String aggregateFlag) {
		this.aggregateFlag = aggregateFlag;
	}
	public String getChargeSeq() {
		return chargeSeq;
	}
	public void setChargeSeq(String chargeSeq) {
		this.chargeSeq = chargeSeq;
	}
	public Date getSentenceStartDate() {
		return sentenceStartDate;
	}
	public void setSentenceStartDate(Date sentenceStartDate) {
		this.sentenceStartDate = sentenceStartDate;
	}
	public Date getProbableReleaseDate() {
		return probableReleaseDate;
	}
	public void setProbableReleaseDate(Date probableReleaseDate) {
		this.probableReleaseDate = probableReleaseDate;
	}
	public Date getSentenceExpiryDate() {
		return sentenceExpiryDate;
	}
	public void setSentenceExpiryDate(Date sentenceExpiryDate) {
		this.sentenceExpiryDate = sentenceExpiryDate;
	}
	public String getOffenceCode() {
		return offenceCode;
	}
	public void setOffenceCode(String offenceCode) {
		this.offenceCode = offenceCode;
	}
	public String getStatuteCode() {
		return statuteCode;
	}
	public void setStatuteCode(String statuteCode) {
		this.statuteCode = statuteCode;
	}
	public String getChargeInfoNumber() {
		return chargeInfoNumber;
	}
	public void setChargeInfoNumber(String chargeInfoNumber) {
		this.chargeInfoNumber = chargeInfoNumber;
	}
	public Date getConvictionDate() {
		return convictionDate;
	}
	public void setConvictionDate(Date convictionDate) {
		this.convictionDate = convictionDate;
	}
	public String getParoleSupervision() {
		return paroleSupervision;
	}
	public void setParoleSupervision(String paroleSupervision) {
		this.paroleSupervision = paroleSupervision;
	}
}
