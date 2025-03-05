package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderClothe extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private OffenderClothePK id;

	@JsonProperty("availableClothesType")
	private String availableClothesType;

	@JsonProperty("clothesSizeCode")
	private String clothesSizeCode;

	@JsonProperty("clothesType")
	private String clothesType;

	@JsonProperty("createDate")
	private Date createDate;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("issueReasonCode")
	private String issueReasonCode;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("quantity")
	private BigDecimal quantity;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderBookings")
	private OffenderBookings offenderBookings;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("clothesSeq")
	private long clothesSeq;

	/**
	 *
	 * @return
	 */
	public OffenderClothePK getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(OffenderClothePK id) {
		this.id = id;
	}

	/**
	 *
	 * @return
	 */
	public String getAvailableClothesType() {
		return availableClothesType;
	}

	/**
	 *
	 * @param availableClothesType
	 */
	public void setAvailableClothesType(String availableClothesType) {
		this.availableClothesType = availableClothesType;
	}

	/**
	 *
	 * @return
	 */
	public String getClothesSizeCode() {
		return clothesSizeCode;
	}

	/**
	 *
	 * @param clothesSizeCode
	 */
	public void setClothesSizeCode(String clothesSizeCode) {
		this.clothesSizeCode = clothesSizeCode;
	}

	/**
	 *
	 * @return
	 */
	public String getClothesType() {
		return clothesType;
	}

	/**
	 *
	 * @param clothesType
	 */
	public void setClothesType(String clothesType) {
		this.clothesType = clothesType;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 *
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public String getIssueReasonCode() {
		return issueReasonCode;
	}

	/**
	 *
	 * @param issueReasonCode
	 */
	public void setIssueReasonCode(String issueReasonCode) {
		this.issueReasonCode = issueReasonCode;
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
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 *
	 * @param quantity
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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
	public long getClothesSeq() {
		return clothesSeq;
	}

	/**
	 *
	 * @param clothesSeq
	 */
	public void setClothesSeq(long clothesSeq) {
		this.clothesSeq = clothesSeq;
	}

}