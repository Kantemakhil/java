package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InternetAddresses extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("internetAddressId")
	@NotNull
	private Long internetAddressId;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("internetAddress")
	@NotNull
	@Size(max = 240)
	private String internetAddress;

	@JsonProperty("internetAddressClass")
	@NotNull
	@Size(max = 12)
	private String internetAddressClass;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("ownerClass")
	@NotNull
	@Size(max = 12)
	private String ownerClass;

	@JsonProperty("ownerCode")
	@Size(max = 12)
	private String ownerCode;

	@JsonProperty("ownerId")
	private BigDecimal ownerId;

	@JsonProperty("ownerSeq")
	private BigDecimal ownerSeq;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;
	
	@JsonProperty("returnAddress")
	@NotNull
	@Size(max = 240)
	private String returnAddress;

	/**
	 *
	 * @return
	 */
	public Long getInternetAddressId() {
		return internetAddressId;
	}

	/**
	 *
	 * @param internetAddressId
	 */
	public void setInternetAddressId(Long internetAddressId) {
		this.internetAddressId = internetAddressId;
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
	public String getInternetAddress() {
		return internetAddress;
	}

	/**
	 *
	 * @param internetAddress
	 */
	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}

	/**
	 *
	 * @return
	 */
	public String getInternetAddressClass() {
		return internetAddressClass;
	}

	/**
	 *
	 * @param internetAddressClass
	 */
	public void setInternetAddressClass(String internetAddressClass) {
		this.internetAddressClass = internetAddressClass;
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
	public String getOwnerClass() {
		return ownerClass;
	}

	/**
	 *
	 * @param ownerClass
	 */
	public void setOwnerClass(String ownerClass) {
		this.ownerClass = ownerClass;
	}

	/**
	 *
	 * @return
	 */
	public String getOwnerCode() {
		return ownerCode;
	}

	/**
	 *
	 * @param ownerCode
	 */
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOwnerId() {
		return ownerId;
	}

	/**
	 *
	 * @param ownerId
	 */
	public void setOwnerId(BigDecimal ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getOwnerSeq() {
		return ownerSeq;
	}

	/**
	 *
	 * @param ownerSeq
	 */
	public void setOwnerSeq(BigDecimal ownerSeq) {
		this.ownerSeq = ownerSeq;
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

	public String getReturnAddress() {
		return returnAddress;
	}

	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}

}