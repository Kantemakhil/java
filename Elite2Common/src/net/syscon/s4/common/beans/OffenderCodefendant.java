package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderCodefendant extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("requestId")
	private BigDecimal requestId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBooking1")
	private OffenderBookings offenderBooking1;

	@JsonProperty("offenderBooking2")
	private OffenderBookings offenderBooking2;

	@JsonProperty("codefendantSeq")
	private long codefendantSeq;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

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
	public String getFirstName() {
		return firstName;
	}

	/**
	 *
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 *
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 *
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public BigDecimal getRequestId() {
		return requestId;
	}

	/**
	 *
	 * @param requestId
	 */
	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
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
	public OffenderBookings getOffenderBooking1() {
		return offenderBooking1;
	}

	/**
	 *
	 * @param offenderBooking1
	 */
	public void setOffenderBooking1(OffenderBookings offenderBooking1) {
		this.offenderBooking1 = offenderBooking1;
	}

	/**
	 *
	 * @return
	 */
	public OffenderBookings getOffenderBooking2() {
		return offenderBooking2;
	}

	/**
	 *
	 * @param offenderBooking2
	 */
	public void setOffenderBooking2(OffenderBookings offenderBooking2) {
		this.offenderBooking2 = offenderBooking2;
	}

	/**
	 *
	 * @return
	 */
	public long getCodefendantSeq() {
		return codefendantSeq;
	}

	/**
	 *
	 * @param codefendantSeq
	 */
	public void setCodefendantSeq(long codefendantSeq) {
		this.codefendantSeq = codefendantSeq;
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

}