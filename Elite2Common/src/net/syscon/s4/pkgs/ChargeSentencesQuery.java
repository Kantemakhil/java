package net.syscon.s4.pkgs;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class ChargeSentencesQuery extends BaseModel implements Serializable {

	private Long offenderBookId;
	private Long offenderChargeId;
	private String description;
	private Date offenceDate;
	private Date offenceRangeDate;
	private String resultCode1Desc;
	private String resultCode1Indicator;
	private String resultCode2Desc;
	private String resultCode2Indicator;
	private String applyFlag;
	private Long checkSum;
	private Long sentenceSeq;

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getOffenderChargeId() {
		return offenderChargeId;
	}

	public void setOffenderChargeId(Long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOffenceDate() {
		return offenceDate;
	}

	public void setOffenceDate(Date offenceDate) {
		this.offenceDate = offenceDate;
	}

	public Date getOffenceRangeDate() {
		return offenceRangeDate;
	}

	public void setOffenceRangeDate(Date offenceRangeDate) {
		this.offenceRangeDate = offenceRangeDate;
	}

	public String getResultCode1Desc() {
		return resultCode1Desc;
	}

	public void setResultCode1Desc(String resultCode1Desc) {
		this.resultCode1Desc = resultCode1Desc;
	}

	public String getResultCode1Indicator() {
		return resultCode1Indicator;
	}

	public void setResultCode1Indicator(String resultCode1Indicator) {
		this.resultCode1Indicator = resultCode1Indicator;
	}

	public String getResultCode2Desc() {
		return resultCode2Desc;
	}

	public void setResultCode2Desc(String resultCode2Desc) {
		this.resultCode2Desc = resultCode2Desc;
	}

	public String getResultCode2Indicator() {
		return resultCode2Indicator;
	}

	public void setResultCode2Indicator(String resultCode2Indicator) {
		this.resultCode2Indicator = resultCode2Indicator;
	}

	public String getApplyFlag() {
		return applyFlag;
	}

	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}

	public Long getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(Long checkSum) {
		this.checkSum = checkSum;
	}

	public Long getSentenceSeq() {
		return sentenceSeq;
	}

	public void setSentenceSeq(Long sentenceSeq) {
		this.sentenceSeq = sentenceSeq;
	}

}
