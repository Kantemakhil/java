package net.syscon.s4.inst.legals.au;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class JujOrderMappings extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderCategory;

	private String orderType;

	private String bailStatus;

	private String ignoreFlag;

	private String requestOnlyFlag;

	private String knownOffenderOnly;

	private String supervisionFlagOnly;

	private String sentenceCalcType;

	private String sentenceCategory;

	private String sentenceStatus;

	private String csnswInstCommInd;

	private String escortType;

	private String ourtEventType;

	private String courtEventSubType;

	private String resultCode;

	private String breachStatus;

	private String breachDisposition;

	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	public String getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(String orderCategory) {
		this.orderCategory = orderCategory;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getBailStatus() {
		return bailStatus;
	}

	public void setBailStatus(String bailStatus) {
		this.bailStatus = bailStatus;
	}

	public String getIgnoreFlag() {
		return ignoreFlag;
	}

	public void setIgnoreFlag(String ignoreFlag) {
		this.ignoreFlag = ignoreFlag;
	}

	public String getRequestOnlyFlag() {
		return requestOnlyFlag;
	}

	public void setRequestOnlyFlag(String requestOnlyFlag) {
		this.requestOnlyFlag = requestOnlyFlag;
	}

	public String getKnownOffenderOnly() {
		return knownOffenderOnly;
	}

	public void setKnownOffenderOnly(String knownOffenderOnly) {
		this.knownOffenderOnly = knownOffenderOnly;
	}

	public String getSupervisionFlagOnly() {
		return supervisionFlagOnly;
	}

	public void setSupervisionFlagOnly(String supervisionFlagOnly) {
		this.supervisionFlagOnly = supervisionFlagOnly;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public String getSentenceStatus() {
		return sentenceStatus;
	}

	public void setSentenceStatus(String sentenceStatus) {
		this.sentenceStatus = sentenceStatus;
	}

	public String getCsnswInstCommInd() {
		return csnswInstCommInd;
	}

	public void setCsnswInstCommInd(String csnswInstCommInd) {
		this.csnswInstCommInd = csnswInstCommInd;
	}

	public String getEscortType() {
		return escortType;
	}

	public void setEscortType(String escortType) {
		this.escortType = escortType;
	}

	public String getOurtEventType() {
		return ourtEventType;
	}

	public void setOurtEventType(String ourtEventType) {
		this.ourtEventType = ourtEventType;
	}

	public String getCourtEventSubType() {
		return courtEventSubType;
	}

	public void setCourtEventSubType(String courtEventSubType) {
		this.courtEventSubType = courtEventSubType;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getBreachStatus() {
		return breachStatus;
	}

	public void setBreachStatus(String breachStatus) {
		this.breachStatus = breachStatus;
	}

	public String getBreachDisposition() {
		return breachDisposition;
	}

	public void setBreachDisposition(String breachDisposition) {
		this.breachDisposition = breachDisposition;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

}
