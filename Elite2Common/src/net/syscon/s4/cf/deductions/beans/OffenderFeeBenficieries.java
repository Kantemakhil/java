package net.syscon.s4.cf.deductions.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderFeeBenficieries extends BaseModel implements Serializable {
	private String personId;
	private String lastName;
	private String firstName;
	private String corporateId;
	private String corporateDescription;
	private Integer priority;
	private BigDecimal percentage;
	private BigDecimal amount;
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(final String personId) {
		this.personId = personId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	public String getCorporateId() {
		return corporateId;
	}
	public void setCorporateId(final String corporateId) {
		this.corporateId = corporateId;
	}
	public String getCorporateDescription() {
		return corporateDescription;
	}
	public void setCorporateDescription(final String corporateDescription) {
		this.corporateDescription = corporateDescription;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(final Integer priority) {
		this.priority = priority;
	}
	public BigDecimal getPercentage() {
		return percentage;
	}
	public void setPercentage(final BigDecimal percentage) {
		this.percentage = percentage;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

}
