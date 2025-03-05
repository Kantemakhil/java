package net.syscon.s4.inst.workflow.managingteams.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * The persistent class for the OFFENDER_TEAM_ASSIGNMENTS database table.
 * 
 */
public class OffenderTeamAssignments extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("assignmentDate")
	private Date assignmentDate;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	@JsonProperty("teamId")
	private BigDecimal teamId;
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	@JsonProperty("functionType")
	private String functionType;
	@JsonProperty("serverCode")
	private long serverCode;
	@JsonProperty("teamIdDesc")
	private String teamIdDesc;
	@JsonProperty("teamCode")
	private String teamCode;
	@JsonProperty("returnValue")
	private int returnValue;
	@JsonProperty("nbtActiveFlag")
	private String nbtActiveFlag;
	/**
	 * @return the nbtActiveFlag
	 */
	public String getNbtActiveFlag() {
		return nbtActiveFlag;
	}
	/**
	 * @param nbtActiveFlag
	 *            the nbtActiveFlag to set
	 */
	public void setNbtActiveFlag(final String nbtActiveFlag) {
		this.nbtActiveFlag = nbtActiveFlag;
	}
	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the serverCode
	 */
	public long getServerCode() {
		return serverCode;
	}
	/**
	 * @param serverCode
	 *            the serverCode to set
	 */
	public void setServerCode(final long serverCode) {
		this.serverCode = serverCode;
	}
	/**
	 * @return the returnValue
	 */
	public int getReturnValue() {
		return returnValue;
	}
	/**
	 * @param returnValue
	 *            the returnValue to set
	 */
	public void setReturnValue(final int returnValue) {
		this.returnValue = returnValue;
	}
	/**
	 * @return the teamCode
	 */
	public String getTeamCode() {
		return teamCode;
	}
	/**
	 * @param teamCode
	 *            the teamCode to set
	 */
	public void setTeamCode(final String teamCode) {
		this.teamCode = teamCode;
	}
	/**
	 * @return the teamIdDesc
	 */
	public String getTeamIdDesc() {
		return teamIdDesc;
	}
	/**
	 * @param teamIdDesc
	 *            the teamIdDesc to set
	 */
	public void setTeamIdDesc(final String teamIdDesc) {
		this.teamIdDesc = teamIdDesc;
	}
	/**
	 * @return the offenderBookId
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}
	/**
	 * @param offenderBookId
	 *            the offenderBookId to set
	 */
	public void setOffenderBookId(final long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	/**
	 * @return the functionType
	 */
	public String getFunctionType() {
		return functionType;
	}
	/**
	 * @param functionType
	 *            the functionType to set
	 */
	public void setFunctionType(final String functionType) {
		this.functionType = functionType;
	}

	public OffenderTeamAssignments() {
		//Constructor
	}
	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}
	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
	}
	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	/**
	 * @return the teamId
	 */
	public BigDecimal getTeamId() {
		return this.teamId;
	}
	/**
	 * @param teamId
	 *            the teamId to set
	 */
	public void setTeamId(final BigDecimal teamId) {
		this.teamId = teamId;
	}
	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	/**
	 * @param assignmentDate
	 *            the assignmentDate to set
	 */
	public void setAssignmentDate(final Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

}
