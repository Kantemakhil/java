package net.syscon.s4.common.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrisonerDetails {
	public String astriaPersonId;
    public BigDecimal eliteOffenderId;
    public String givenName;
    public String otherGivenNames;
    public String familyName;
    public String dateOfBirth;
    public List<Alias> aliases;
    public List<AdditionalIdentifier> additionalIdentifiers;
	
    public String getAstriaPersonId() {
		return astriaPersonId;
	}
	public void setAstriaPersonId(String astriaPersonId) {
		this.astriaPersonId = astriaPersonId;
	}
	
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getOtherGivenNames() {
		return otherGivenNames;
	}
	public void setOtherGivenNames(String otherGivenNames) {
		this.otherGivenNames = otherGivenNames;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	
	public void setAliases(ArrayList<Alias> aliases) {
		this.aliases = aliases;
	}
	
	
	public void setAdditionalIdentifiers(ArrayList<AdditionalIdentifier> additionalIdentifiers) {
		this.additionalIdentifiers = additionalIdentifiers;
	}
	public BigDecimal getEliteOffenderId() {
		return eliteOffenderId;
	}
	public void setEliteOffenderId(BigDecimal eliteOffenderId) {
		this.eliteOffenderId = eliteOffenderId;
	}
	public List<Alias> getAliases() {
		return aliases;
	}
	public void setAliases(List<Alias> aliases) {
		this.aliases = aliases;
	}
	public List<AdditionalIdentifier> getAdditionalIdentifiers() {
		return additionalIdentifiers;
	}
	public void setAdditionalIdentifiers(List<AdditionalIdentifier> additionalIdentifiers) {
		this.additionalIdentifiers = additionalIdentifiers;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
