package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VCbSentTerms extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long offenderBookId;
	private BigDecimal offenderId;
	private Integer sentenceSeq;
	private Integer termSeq;
	private Integer version;
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
	private Date lMinSd;
	private Date lMaxEd;
	private Date lNewDate;
	private Integer lBtsDays;
	private Long offBalCalcId;
	private Long offenderBalCalcId;

	public String getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(final String selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public Integer getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(final Integer sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

	public Integer getTermSeq() {
		return termSeq;
	}

	public void setTermSeq(final Integer termSeq) {
		this.termSeq = termSeq;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

	public String getSentenceStatus() {
		return sentenceStatus;
	}

	public void setSentenceStatus(final String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(final String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(final String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceTermCode() {
		return sentenceTermCode;
	}

	public void setSentenceTermCode(final String sentenceTermCode) {
		this.sentenceTermCode = sentenceTermCode;
	}

	public String getTermPeriod() {
		return termPeriod;
	}

	public void setTermPeriod(final String termPeriod) {
		this.termPeriod = termPeriod;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getOvrStartDate() {
		return ovrStartDate;
	}

	public void setOvrStartDate(final Date ovrStartDate) {
		this.ovrStartDate = ovrStartDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public Date getOvrEndDate() {
		return ovrEndDate;
	}

	public void setOvrEndDate(final Date ovrEndDate) {
		this.ovrEndDate = ovrEndDate;
	}

	public String getAggregateFlag() {
		return aggregateFlag;
	}

	public void setAggregateFlag(final String aggregateFlag) {
		this.aggregateFlag = aggregateFlag;
	}

	public String getChargeSeq() {
		return chargeSeq;
	}

	public void setChargeSeq(final String chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public Date getSentenceStartDate() {
		return sentenceStartDate;
	}

	public void setSentenceStartDate(final Date sentenceStartDate) {
		this.sentenceStartDate = sentenceStartDate;
	}

	public Date getProbableReleaseDate() {
		return probableReleaseDate;
	}

	public void setProbableReleaseDate(final Date probableReleaseDate) {
		this.probableReleaseDate = probableReleaseDate;
	}

	public Date getSentenceExpiryDate() {
		return sentenceExpiryDate;
	}

	public void setSentenceExpiryDate(final Date sentenceExpiryDate) {
		this.sentenceExpiryDate = sentenceExpiryDate;
	}

	public String getOffenceCode() {
		return offenceCode;
	}

	public void setOffenceCode(final String offenceCode) {
		this.offenceCode = offenceCode;
	}

	public String getStatuteCode() {
		return statuteCode;
	}

	public void setStatuteCode(final String statuteCode) {
		this.statuteCode = statuteCode;
	}

	public String getChargeInfoNumber() {
		return chargeInfoNumber;
	}

	public void setChargeInfoNumber(final String chargeInfoNumber) {
		this.chargeInfoNumber = chargeInfoNumber;
	}

	public Date getConvictionDate() {
		return convictionDate;
	}

	public void setConvictionDate(final Date convictionDate) {
		this.convictionDate = convictionDate;
	}

	public String getParoleSupervision() {
		return paroleSupervision;
	}

	public void setParoleSupervision(final String paroleSupervision) {
		this.paroleSupervision = paroleSupervision;
	}

	public Date getlMinSd() {
		return lMinSd;
	}

	public void setlMinSd(final Date lMinSd) {
		this.lMinSd = lMinSd;
	}

	public Date getlMaxEd() {
		return lMaxEd;
	}

	public void setlMaxEd(final Date lMaxEd) {
		this.lMaxEd = lMaxEd;
	}

	public Date getlNewDate() {
		return lNewDate;
	}

	public void setlNewDate(final Date lNewDate) {
		this.lNewDate = lNewDate;
	}

	public Integer getlBtsDays() {
		return lBtsDays;
	}

	public void setlBtsDays(final Integer lBtsDays) {
		this.lBtsDays = lBtsDays;
	}

	public Long getOffBalCalcId() {
		return offBalCalcId;
	}

	public void setOffBalCalcId(final Long offBalCalcId) {
		this.offBalCalcId = offBalCalcId;
	}

	public Long getOffenderBalCalcId() {
		return offenderBalCalcId;
	}

	public void setOffenderBalCalcId(final Long offenderBalCalcId) {
		this.offenderBalCalcId = offenderBalCalcId;
	}

}
