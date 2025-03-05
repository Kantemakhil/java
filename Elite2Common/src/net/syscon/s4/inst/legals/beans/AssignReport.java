package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignReport  extends BaseModel implements Serializable {

private static final long serialVersionUID = 1L;

	@JsonProperty("function")
	private String function;
	
	@JsonProperty("teamResponsible")
	private String teamResponsible;
	
	@JsonProperty("teamName")
	private String teamName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("position")
	private String position;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("teamMemberId")
	private String teamMemberId;
	
	@JsonProperty("teamId")
	private Integer teamId;
	
	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getTeamResponsible() {
		return teamResponsible;
	}

	public void setTeamResponsible(String teamResponsible) {
		this.teamResponsible = teamResponsible;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(String teamMemberId) {
		this.teamMemberId = teamMemberId;
	}
	
	
	
	
}
