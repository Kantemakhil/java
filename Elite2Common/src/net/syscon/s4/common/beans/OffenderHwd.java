package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderHwd extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("hwdId")
	private Long hwdId;

	@JsonProperty("bailAmount")
	private BigDecimal bailAmount;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("expiryDate")
	private Date expiryDate;

	@JsonProperty("hwdInfoId")
	private String hwdInfoId;

	@JsonProperty("hwdStatus")
	private String hwdStatus;

	@JsonProperty("hwdType")
	private String hwdType;

	@JsonProperty("issuingAgy")
	private String issuingAgy;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("probRevocFlag")
	private String probRevocFlag;

	@JsonProperty("receivedDate")
	private Date receivedDate;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderHwdCharges")
	private List<OffenderHwdCharge> offenderHwdCharges;

	@JsonProperty("offenderHwdHties")
	private List<OffenderHwdHty> offenderHwdHties;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("offenderId")
	private Long offenderId;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("expDate")
	private Date expDate;
	

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}

	/**
	 * @return the offenderBookId
	 */
	public Long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public Long getHwdId() {
		return hwdId;
	}

	/**
	 *
	 * @param hwdId
	 */
	public void setHwdId(final Long hwdId) {
		this.hwdId = hwdId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getBailAmount() {
		return bailAmount;
	}

	/**
	 *
	 * @param bailAmount
	 */
	public void setBailAmount(final BigDecimal bailAmount) {
		this.bailAmount = bailAmount;
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
	public void setCreateDatetime(final Date createDatetime) {
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 *
	 * @param expiryDate
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 *
	 * @return
	 */
	public String getHwdInfoId() {
		return hwdInfoId;
	}

	/**
	 *
	 * @param hwdInfoId
	 */
	public void setHwdInfoId(final String hwdInfoId) {
		this.hwdInfoId = hwdInfoId;
	}

	/**
	 *
	 * @return
	 */
	public String getHwdStatus() {
		return hwdStatus;
	}

	/**
	 *
	 * @param hwdStatus
	 */
	public void setHwdStatus(final String hwdStatus) {
		this.hwdStatus = hwdStatus;
	}

	/**
	 *
	 * @return
	 */
	public String getHwdType() {
		return hwdType;
	}

	/**
	 *
	 * @param hwdType
	 */
	public void setHwdType(final String hwdType) {
		this.hwdType = hwdType;
	}

	/**
	 *
	 * @return
	 */
	public String getIssuingAgy() {
		return issuingAgy;
	}

	/**
	 *
	 * @param issuingAgy
	 */
	public void setIssuingAgy(final String issuingAgy) {
		this.issuingAgy = issuingAgy;
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
	public void setModifyDatetime(final Date modifyDatetime) {
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
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getProbRevocFlag() {
		return probRevocFlag;
	}

	/**
	 *
	 * @param probRevocFlag
	 */
	public void setProbRevocFlag(final String probRevocFlag) {
		this.probRevocFlag = probRevocFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 *
	 * @param receivedDate
	 */
	public void setReceivedDate(final Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 * @param offenderId
	 *            the offenderId to set
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 *
	 * @param startDate
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
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
	public void setOffenderBookings(final OffenderBookings offenderBookings) {
		this.offenderBookings = offenderBookings;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderHwdCharge> getOffenderHwdCharges() {
		return offenderHwdCharges;
	}

	/**
	 *
	 * @param offenderHwdCharges
	 */
	public void setOffenderHwdCharges(final List<OffenderHwdCharge> offenderHwdCharges) {
		this.offenderHwdCharges = offenderHwdCharges;
	}

	/**
	 *
	 * @return
	 */
	public List<OffenderHwdHty> getOffenderHwdHties() {
		return offenderHwdHties;
	}

	/**
	 *
	 * @param offenderHwdHties
	 */
	public void setOffenderHwdHties(final List<OffenderHwdHty> offenderHwdHties) {
		this.offenderHwdHties = offenderHwdHties;
	}

	/**
	 * @return the expDate
	 */
	public Date getExpDate() {
		return expDate;
	}

	/**
	 * @param expDate the expDate to set
	 */
	public void setExpDate(final Date expDate) {
		this.expDate = expDate;
	}

}