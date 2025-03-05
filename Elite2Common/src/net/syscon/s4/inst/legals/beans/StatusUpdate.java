package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class StatusUpdate extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("user")
	private String user;
	
	@JsonProperty("staff_id")
	private Integer staff_id;
	
	@JsonProperty("staff_Name")
	private String staff_Name;
	
	@JsonProperty("user_role")
	private String user_role;
	
	@JsonProperty("last_name")
	private String last_name;
	
	@JsonProperty("first_name")
	private String first_name;
	
	@JsonProperty("user_Name")
	private String user_Name;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}

	public String getStaff_Name() {
		return staff_Name;
	}

	public void setStaff_Name(String staff_Name) {
		this.staff_Name = staff_Name;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	
}
