package net.syscon.s4.sa.usersystemsecurity.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the V_MEMBER_SKILLS database table.
 * 
 */
public class VMemberSkills extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("agyLocId")
	private String agyLocId;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("position")
	private String position;
	@JsonProperty("role")
	private String role;
	@JsonProperty("scheduleType")
	private String scheduleType;
	@JsonProperty("sexCode")
	private String sexCode;
	@JsonProperty("staffId")
	private BigDecimal staffId;
	@JsonProperty("status")
	private String status;
	@JsonProperty("skillType")
	private String skillType;
	@JsonProperty("subType")
	private String subType;
	@JsonProperty("city")
	private String city;
	@JsonProperty("agencyLocationType")
	private String agencyLocationType;
	@JsonProperty("nomsRegionCode")
	private String nomsRegionCode;
	/**
	 * @return the skillType
	 */
	public String getSkillType() {
		return skillType;
	}
	/**
	 * @param skillType
	 *            the skillType to set
	 */
	public void setSkillType(final String skillType) {
		this.skillType = skillType;
	}
	/**
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}
	/**
	 * @param subType
	 *            the subType to set
	 */
	public void setSubType(final String subType) {
		this.subType = subType;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}
	/**
	 * @return the agencyLocationType
	 */
	public String getAgencyLocationType() {
		return agencyLocationType;
	}
	/**
	 * @param agencyLocationType
	 *            the agencyLocationType to set
	 */
	public void setAgencyLocationType(final String agencyLocationType) {
		this.agencyLocationType = agencyLocationType;
	}
	/**
	 * @return the nomsRegionCode
	 */
	public String getNomsRegionCode() {
		return nomsRegionCode;
	}
	/**
	 * @param nomsRegionCode
	 *            the nomsRegionCode to set
	 */
	public void setNomsRegionCode(final String nomsRegionCode) {
		this.nomsRegionCode = nomsRegionCode;
	}

	public VMemberSkills() {
	}
	/**
	 * @return the agyLocId
	 */
	public String getAgyLocId() {
		return this.agyLocId;
	}
	/**
	 * @param agyLocId
	 *            the agyLocId to set
	 */
	public void setAgyLocId(final String agyLocId) {
		this.agyLocId = agyLocId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}
	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}
	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return this.position;
	}
	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(final String position) {
		this.position = position;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return this.role;
	}
	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(final String role) {
		this.role = role;
	}
	/**
	 * @return the scheduleType
	 */
	public String getScheduleType() {
		return this.scheduleType;
	}
	/**
	 * @param scheduleType
	 *            the scheduleType to set
	 */
	public void setScheduleType(final String scheduleType) {
		this.scheduleType = scheduleType;
	}
	/**
	 * @return the sexCode
	 */
	public String getSexCode() {
		return this.sexCode;
	}
	/**
	 * @param sexCode
	 *            the sexCode to set
	 */
	public void setSexCode(final String sexCode) {
		this.sexCode = sexCode;
	}
	/**
	 * @return the staffId
	 */
	public BigDecimal getStaffId() {
		return this.staffId;
	}
	/**
	 * @param staffId
	 *            the staffId to set
	 */
	public void setStaffId(final BigDecimal staffId) {
		this.staffId = staffId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

}
