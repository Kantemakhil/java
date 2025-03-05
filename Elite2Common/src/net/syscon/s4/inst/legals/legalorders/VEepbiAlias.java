package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class VEepbiAlias extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("cni")
	private  long cni	;
	@JsonProperty("lastName")
	private String lastName	;
	@JsonProperty("firstName")
	private String firstName	;
	@JsonProperty("middleName")
	private String middleName	;
	@JsonProperty("dob")
	private Date dob	;
	@JsonProperty("sex")
	private String sex	;
	public long getCni() {
		return cni;
	}
	public void setCni(long cni) {
		this.cni = cni;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	

}
