package net.syscon.s4.inst.workflow.managingteams.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the V_OFFENDER_TEAM_ASSIGN_HTY database table.
 * 
 */
public class VOffenderTeamAssignHty extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date assignDate;

	private Date expiryDate;

	public Date getAssignDate() {
		return assignDate;
	}



	public void setAssignDate(final Date assignDate) {
		this.assignDate = assignDate;
	}



	public Date getExpiryDate() {
		return expiryDate;
	}



	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	private String functionType;

	private String functionTypeDesc;

	private BigDecimal offenderBookId;

	private BigDecimal offenderTeamAssignHtyId;

	private String teamCode;

	private String teamDescription;

	private BigDecimal teamId;

	public VOffenderTeamAssignHty() {
	}

	

	public String getFunctionType() {
		return this.functionType;
	}

	public void setFunctionType(final String functionType) {
		this.functionType = functionType;
	}

	public String getFunctionTypeDesc() {
		return this.functionTypeDesc;
	}

	public void setFunctionTypeDesc(final String functionTypeDesc) {
		this.functionTypeDesc = functionTypeDesc;
	}

	public BigDecimal getOffenderBookId() {
		return this.offenderBookId;
	}

	public void setOffenderBookId(final BigDecimal offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderTeamAssignHtyId() {
		return this.offenderTeamAssignHtyId;
	}

	public void setOffenderTeamAssignHtyId(final BigDecimal offenderTeamAssignHtyId) {
		this.offenderTeamAssignHtyId = offenderTeamAssignHtyId;
	}

	public String getTeamCode() {
		return this.teamCode;
	}

	public void setTeamCode(final String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamDescription() {
		return this.teamDescription;
	}

	public void setTeamDescription(final String teamDescription) {
		this.teamDescription = teamDescription;
	}

	public BigDecimal getTeamId() {
		return this.teamId;
	}

	public void setTeamId(final BigDecimal teamId) {
		this.teamId = teamId;
	}

}
