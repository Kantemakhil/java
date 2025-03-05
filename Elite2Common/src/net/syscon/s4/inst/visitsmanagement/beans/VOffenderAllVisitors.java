package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class VOffenderAllVisitors implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	private String middleName;

	private BigDecimal offenderVisitId;

	private String relationship;

	private String visitorId;

	private String visitorType;

	public VOffenderAllVisitors() {
		// VOffenderAllVisitors
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

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public BigDecimal getOffenderVisitId() {
		return this.offenderVisitId;
	}

	public void setOffenderVisitId(BigDecimal offenderVisitId) {
		this.offenderVisitId = offenderVisitId;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getVisitorId() {
		return this.visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public String getVisitorType() {
		return this.visitorType;
	}

	public void setVisitorType(String visitorType) {
		this.visitorType = visitorType;
	}

}
