package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the REMITTERS database table.
 * 
 */
public class Remitters extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("remitterId")
	private Long remitterId;

	@JsonProperty("address")
	private String address;

	@JsonProperty("busPhone")
	private String busPhone;

	@JsonProperty("busPhoneArea")
	private String busPhoneArea;

	@JsonProperty("busPhoneExt")
	private String busPhoneExt;

	@JsonProperty("city")
	private String city;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("homePhone")
	private String homePhone;

	@JsonProperty("homePhoneArea")
	private String homePhoneArea;

	@JsonProperty("homePhoneExt")
	private String homePhoneExt;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("provStateCode")
	private String provStateCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("zipStateCode")
	private String zipStateCode;

	public Remitters() {
		// Remitters
	}

	public Long getRemitterId() {
		return this.remitterId;
	}

	public void setRemitterId(Long remitterId) {
		this.remitterId = remitterId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusPhone() {
		return this.busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public String getBusPhoneArea() {
		return this.busPhoneArea;
	}

	public void setBusPhoneArea(String busPhoneArea) {
		this.busPhoneArea = busPhoneArea;
	}

	public String getBusPhoneExt() {
		return this.busPhoneExt;
	}

	public void setBusPhoneExt(String busPhoneExt) {
		this.busPhoneExt = busPhoneExt;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getHomePhoneArea() {
		return this.homePhoneArea;
	}

	public void setHomePhoneArea(String homePhoneArea) {
		this.homePhoneArea = homePhoneArea;
	}

	public String getHomePhoneExt() {
		return this.homePhoneExt;
	}

	public void setHomePhoneExt(String homePhoneExt) {
		this.homePhoneExt = homePhoneExt;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getProvStateCode() {
		return this.provStateCode;
	}

	public void setProvStateCode(String provStateCode) {
		this.provStateCode = provStateCode;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getZipStateCode() {
		return this.zipStateCode;
	}

	public void setZipStateCode(String zipStateCode) {
		this.zipStateCode = zipStateCode;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

}
