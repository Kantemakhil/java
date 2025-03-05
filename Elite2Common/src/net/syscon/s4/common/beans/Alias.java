package net.syscon.s4.common.beans;

import java.util.Date;
import java.util.List;

public class Alias {
	public String aliasTypeCode;
    public String givenName;
    public String otherGivenNames;
    public String familyName;
    public String dateOfBirth;
	public String getAliasTypeCode() {
		return aliasTypeCode;
	}
	public void setAliasTypeCode(String aliasTypeCode) {
		this.aliasTypeCode = aliasTypeCode;
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
