package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderBailDetail extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("bailDate")
	private Date bailDate;

	@JsonProperty("bailStatus")
	private String bailStatus;

	@JsonProperty("cash")
	private BigDecimal cash;

	@JsonProperty("cashFlag")
	private String cashFlag;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("judge")
	private String judge;

	@JsonProperty("method")
	private String method;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("perfectedBy")
	private String perfectedBy;

	@JsonProperty("perfectedDatetime")
	private Date perfectedDatetime;

	@JsonProperty("property")
	private BigDecimal property;

	@JsonProperty("receiptReferenceText")
	private String receiptReferenceText;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("surety")
	private BigDecimal surety;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("offenderChargeId")
	private long offenderChargeId;

	/**
	 *
	 * @return
	 */
	public Date getBailDate() {
		return bailDate;
	}

	/**
	 *
	 * @param bailDate
	 */
	public void setBailDate(Date bailDate) {
		this.bailDate = bailDate;
	}

	/**
	 *
	 * @return
	 */
	public String getBailStatus() {
		return bailStatus;
	}

	/**
	 *
	 * @param bailStatus
	 */
	public void setBailStatus(String bailStatus) {
		this.bailStatus = bailStatus;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getCash() {
		return cash;
	}

	/**
	 *
	 * @param cash
	 */
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	/**
	 *
	 * @return
	 */
	public String getCashFlag() {
		return cashFlag;
	}

	/**
	 *
	 * @param cashFlag
	 */
	public void setCashFlag(String cashFlag) {
		this.cashFlag = cashFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 *
	 * @param commentText
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
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
	public String getJudge() {
		return judge;
	}

	/**
	 *
	 * @param judge
	 */
	public void setJudge(String judge) {
		this.judge = judge;
	}

	/**
	 *
	 * @return
	 */
	public String getMethod() {
		return method;
	}

	/**
	 *
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
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
	public String getPerfectedBy() {
		return perfectedBy;
	}

	/**
	 *
	 * @param perfectedBy
	 */
	public void setPerfectedBy(String perfectedBy) {
		this.perfectedBy = perfectedBy;
	}

	/**
	 *
	 * @return
	 */
	public Date getPerfectedDatetime() {
		return perfectedDatetime;
	}

	/**
	 *
	 * @param perfectedDatetime
	 */
	public void setPerfectedDatetime(Date perfectedDatetime) {
		this.perfectedDatetime = perfectedDatetime;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getProperty() {
		return property;
	}

	/**
	 *
	 * @param property
	 */
	public void setProperty(BigDecimal property) {
		this.property = property;
	}

	/**
	 *
	 * @return
	 */
	public String getReceiptReferenceText() {
		return receiptReferenceText;
	}

	/**
	 *
	 * @param receiptReferenceText
	 */
	public void setReceiptReferenceText(String receiptReferenceText) {
		this.receiptReferenceText = receiptReferenceText;
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
	public BigDecimal getSurety() {
		return surety;
	}

	/**
	 *
	 * @param surety
	 */
	public void setSurety(BigDecimal surety) {
		this.surety = surety;
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

}