package net.syscon.s4.inst.classification.beans;

import java.io.Serializable;

import net.syscon.s4.common.beans.BaseModel;

import java.math.BigDecimal;


/**
 * The persistent class for the STAFF_MEMBERS_V1 database table.
 * 
 */
public class StaffMembersV1 extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	private String name;

	private BigDecimal staffId;

	private String userId;
	
	private String code;
	
	private String description;
	
	public StaffMembersV1() {
		// StaffMembersV1
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
