package net.syscon.s4.inst.accreditedprograms.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import net.syscon.s4.common.beans.BaseModel;


/**
 * The persistent class for the V_PROGRAM_PROVIDERS database table.
 * 
 */
public class VProgramProviders extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	private String partyClass;

	private String partyCode;

	private BigDecimal partyId;

	private String partyName;

	private String code;

	private String description;
	private String provider;
	
	public VProgramProviders() {
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

	public String getPartyClass() {
		return this.partyClass;
	}

	public void setPartyClass(String partyClass) {
		this.partyClass = partyClass;
	}

	public String getPartyCode() {
		return this.partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public BigDecimal getPartyId() {
		return this.partyId;
	}

	public void setPartyId(BigDecimal partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return this.partyName;
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

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

}
