package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Phones extends BaseModel implements Serializable {

	private static final Long serialVersionUID = 1L;

	@JsonProperty("phoneId")
	@NotNull
	private Long phoneId;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("extNo")
	@Size(max = 7)
	private String extNo;

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

	@JsonProperty("phoneNo")
	@NotNull
	@Size(max = 40)
	private String phoneNo;

	@JsonProperty("phoneType")
	@NotNull
	@Size(max = 12)
	private String phoneType;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	
	@JsonProperty("format")
	private String format;
	/**
	 *
	 * @return
	 */
	public Long getPhoneId() {
		return phoneId;
	}

	/**
	 *
	 * @param phoneId
	 */
	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
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
	public String getExtNo() {
		return extNo;
	}

	/**
	 *
	 * @param extNo
	 */
	public void setExtNo(String extNo) {
		this.extNo = extNo;
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
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 *
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 *
	 * @return
	 */
	public String getPhoneType() {
		return phoneType;
	}

	/**
	 *
	 * @param phoneType
	 */
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
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

}