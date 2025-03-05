package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCharge extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderChargeId")
	private long offenderChargeId;

	@JsonProperty("chargeSeq")
	private BigDecimal chargeSeq;

	@JsonProperty("chargeStatus")
	private String chargeStatus;

	@JsonProperty("cjitOffenceCode1")
	private String cjitOffenceCode1;

	@JsonProperty("cjitOffenceCode2")
	private String cjitOffenceCode2;

	@JsonProperty("cjitOffenceCode3")
	private String cjitOffenceCode3;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("lidsOffenceNumber")
	private BigDecimal lidsOffenceNumber;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("mostSeriousFlag")
	private String mostSeriousFlag;

	@JsonProperty("noOfOffences")
	private BigDecimal noOfOffences;

	@JsonProperty("offenceDate")
	private Date offenceDate;

	@JsonProperty("offenceRangeDate")
	private Date offenceRangeDate;

	@JsonProperty("offenceType")
	private String offenceType;

	@JsonProperty("orderId")
	private BigDecimal orderId;

	@JsonProperty("pleaCode")
	private String pleaCode;

	@JsonProperty("propertyValue")
	private BigDecimal propertyValue;

	@JsonProperty("resultCode1")
	private String resultCode1;

	@JsonProperty("resultCode1Indicator")
	private String resultCode1Indicator;

	@JsonProperty("resultCode2")
	private String resultCode2;

	@JsonProperty("resultCode2Indicator")
	private String resultCode2Indicator;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("totalPropertyValue")
	private BigDecimal totalPropertyValue;

	@JsonProperty("offence")
	private Offence offence;

	@JsonProperty("offenderBooking")
	private OffenderBookings offenderBooking;

	@JsonProperty("offenderCas")
	private OffenderCas offenderCas;

	/**
	 *
	 * @return
	 */
	public long getOffenderChargeId() {
		return offenderChargeId;
	}

	/**
	 *
	 * @param offenderChargeId
	 */
	public void setOffenderChargeId(long offenderChargeId) {
		this.offenderChargeId = offenderChargeId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getChargeSeq() {
		return chargeSeq;
	}

	/**
	 *
	 * @param chargeSeq
	 */
	public void setChargeSeq(BigDecimal chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	/**
	 *
	 * @return
	 */
	public String getChargeStatus() {
		return chargeStatus;
	}

	/**
	 *
	 * @param chargeStatus
	 */
	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getCjitOffenceCode1() {
		return cjitOffenceCode1;
	}

	/**
	 *
	 * @param cjitOffenceCode1
	 */
	public void setCjitOffenceCode1(String cjitOffenceCode1) {
		this.cjitOffenceCode1 = cjitOffenceCode1;
	}

	/**
	 *
	 * @return
	 */
	public String getCjitOffenceCode2() {
		return cjitOffenceCode2;
	}

	/**
	 *
	 * @param cjitOffenceCode2
	 */
	public void setCjitOffenceCode2(String cjitOffenceCode2) {
		this.cjitOffenceCode2 = cjitOffenceCode2;
	}

	/**
	 *
	 * @return
	 */
	public String getCjitOffenceCode3() {
		return cjitOffenceCode3;
	}

	/**
	 *
	 * @param cjitOffenceCode3
	 */
	public void setCjitOffenceCode3(String cjitOffenceCode3) {
		this.cjitOffenceCode3 = cjitOffenceCode3;
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
	public BigDecimal getLidsOffenceNumber() {
		return lidsOffenceNumber;
	}

	/**
	 *
	 * @param lidsOffenceNumber
	 */
	public void setLidsOffenceNumber(BigDecimal lidsOffenceNumber) {
		this.lidsOffenceNumber = lidsOffenceNumber;
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
	public String getMostSeriousFlag() {
		return mostSeriousFlag;
	}

	/**
	 *
	 * @param mostSeriousFlag
	 */
	public void setMostSeriousFlag(String mostSeriousFlag) {
		this.mostSeriousFlag = mostSeriousFlag;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getNoOfOffences() {
		return noOfOffences;
	}

	/**
	 *
	 * @param noOfOffences
	 */
	public void setNoOfOffences(BigDecimal noOfOffences) {
		this.noOfOffences = noOfOffences;
	}

	/**
	 *
	 * @return
	 */
	public Date getOffenceDate() {
		return offenceDate;
	}

	/**
	 *
	 * @param offenceDate
	 */
	public void setOffenceDate(Date offenceDate) {
		this.offenceDate = offenceDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getOffenceRangeDate() {
		return offenceRangeDate;
	}

	/**
	 *
	 * @param offenceRangeDate
	 */
	public void setOffenceRangeDate(Date offenceRangeDate) {
		this.offenceRangeDate = offenceRangeDate;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenceType() {
		return offenceType;
	}

	/**
	 *
	 * @param offenceType
	 */
	public void setOffenceType(String offenceType) {
		this.offenceType = offenceType;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOrderId() {
		return orderId;
	}

	/**
	 *
	 * @param orderId
	 */
	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	/**
	 *
	 * @return
	 */
	public String getPleaCode() {
		return pleaCode;
	}

	/**
	 *
	 * @param pleaCode
	 */
	public void setPleaCode(String pleaCode) {
		this.pleaCode = pleaCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getPropertyValue() {
		return propertyValue;
	}

	/**
	 *
	 * @param propertyValue
	 */
	public void setPropertyValue(BigDecimal propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 *
	 * @return
	 */
	public String getResultCode1() {
		return resultCode1;
	}

	/**
	 *
	 * @param resultCode1
	 */
	public void setResultCode1(String resultCode1) {
		this.resultCode1 = resultCode1;
	}

	/**
	 *
	 * @return
	 */
	public String getResultCode1Indicator() {
		return resultCode1Indicator;
	}

	/**
	 *
	 * @param resultCode1Indicator
	 */
	public void setResultCode1Indicator(String resultCode1Indicator) {
		this.resultCode1Indicator = resultCode1Indicator;
	}

	/**
	 *
	 * @return
	 */
	public String getResultCode2() {
		return resultCode2;
	}

	/**
	 *
	 * @param resultCode2
	 */
	public void setResultCode2(String resultCode2) {
		this.resultCode2 = resultCode2;
	}

	/**
	 *
	 * @return
	 */
	public String getResultCode2Indicator() {
		return resultCode2Indicator;
	}

	/**
	 *
	 * @param resultCode2Indicator
	 */
	public void setResultCode2Indicator(String resultCode2Indicator) {
		this.resultCode2Indicator = resultCode2Indicator;
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
	public BigDecimal getTotalPropertyValue() {
		return totalPropertyValue;
	}

	/**
	 *
	 * @param totalPropertyValue
	 */
	public void setTotalPropertyValue(BigDecimal totalPropertyValue) {
		this.totalPropertyValue = totalPropertyValue;
	}

	/**
	 *
	 * @return
	 */
	public Offence getOffence() {
		return offence;
	}

	/**
	 *
	 * @param offence
	 */
	public void setOffence(Offence offence) {
		this.offence = offence;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBooking() {
		return offenderBooking;
	}

	/**
	 *
	 * @param offenderBooking
	 */
	public void setOffenderBooking(OffenderBookings offenderBooking) {
		this.offenderBooking = offenderBooking;
	}

	/**
	 *
	 * @return
	 */
	public OffenderCas getOffenderCas() {
		return offenderCas;
	}

	/**
	 *
	 * @param offenderCas
	 */
	public void setOffenderCas(OffenderCas offenderCas) {
		this.offenderCas = offenderCas;
	}

}