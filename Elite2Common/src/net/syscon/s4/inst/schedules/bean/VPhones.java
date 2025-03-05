package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VPhones
 * 
 */
public class VPhones extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String extNo;

	private String ownerClass;

	private String ownerCode;

	private BigDecimal ownerId;

	private BigDecimal ownerSeq;

	private String phoneArea;

	private BigDecimal phoneId;

	private String phoneNo;

	private String phoneType;
	
	@JsonProperty("format")
	private String format;

	public VPhones() {
		// VPhones
	}

	public String getExtNo() {
		return this.extNo;
	}

	public void setExtNo(String extNo) {
		this.extNo = extNo;
	}

	public String getOwnerClass() {
		return this.ownerClass;
	}

	public void setOwnerClass(String ownerClass) {
		this.ownerClass = ownerClass;
	}

	public String getOwnerCode() {
		return this.ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public BigDecimal getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(BigDecimal ownerId) {
		this.ownerId = ownerId;
	}

	public BigDecimal getOwnerSeq() {
		return this.ownerSeq;
	}

	public void setOwnerSeq(BigDecimal ownerSeq) {
		this.ownerSeq = ownerSeq;
	}

	public String getPhoneArea() {
		return this.phoneArea;
	}

	public void setPhoneArea(String phoneArea) {
		this.phoneArea = phoneArea;
	}

	public BigDecimal getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(BigDecimal phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
