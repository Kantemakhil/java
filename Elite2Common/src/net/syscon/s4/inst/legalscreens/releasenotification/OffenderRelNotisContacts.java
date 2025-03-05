package net.syscon.s4.inst.legalscreens.releasenotification;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderRelNotisContacts implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("notiSeq") 
	private long notiSeq;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("phoneNo")
	private String phoneNo;
	
	@JsonProperty("mobile")
	private String mobile;
	
	@JsonProperty("fax")
	private String fax;
	
	@JsonProperty("modifyUserId") 
	private String modifyUserId;
	
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@JsonProperty("createUserId") 
	private String createUserId;	
	
	@JsonProperty("modifyDatetime")  
	private Date modifyDatetime;	
	
	@JsonProperty("sealFlag")  
	private String sealFlag;
	
	@JsonProperty("contactSeq")
	private long contactSeq;
	
	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getNotiSeq() {
		return notiSeq;
	}

	public void setNotiSeq(long notiSeq) {
		this.notiSeq = notiSeq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
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

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	public long getContactSeq() {
		return contactSeq;
	}

	public void setContactSeq(long contactSeq) {
		this.contactSeq = contactSeq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
