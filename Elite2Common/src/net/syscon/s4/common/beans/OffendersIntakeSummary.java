package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffendersIntakeSummary extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderId")
	@NotNull
	private Long offenderId;

	@JsonProperty("firstName")
	@Size(max = 35)
	private String firstName;

	@JsonProperty("lastName")
	@NotNull
	private String lastName;

	@JsonProperty("admittedDateTime")
	@NotNull
	private Date admittedDateTime;

	@JsonProperty("locationCode")
	private String locationCode;
	
	@JsonProperty("imagePresent")
	private String imagePresent;
	
	@JsonProperty("fingerprintPresent")
	private String fingerprintPresent;
	
	@JsonProperty("propertyPresent")
	private String propertyPresent;
	
	@JsonProperty("trustAccountPresent")
	private String trustAccountPresent;
	
	@JsonProperty("assesmentPresent")
	private String assesmentPresent;
	
	@JsonProperty("legalCasePresent")
	private String legalCasePresent;
	
	@JsonProperty("medicalScreeningPresent")
	private String medicalScreeningPresent;
	
	
	/**
	 * Creates new OffendersIntakeSummary class Object
	 */
	public OffendersIntakeSummary() {
		// Offenders
	}

	public Long getOffenderId() {
		return offenderId;
	}

	/**
	 *
	 * @param offenderId
	 */
	public void setOffenderId(final Long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 *
	 * @param firstName
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}


	public String getLegalCasePresent() {
		return legalCasePresent;
	}


	public void setLegalCasePresent(String legalCasePresent) {
		this.legalCasePresent = legalCasePresent;
	}


	public String getLastName() {
		return lastName;
	}

	/**
	 *
	 * @param lastName
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public Date getAdmittedDateTime() {
		return admittedDateTime;
	}

	public void setAdmittedDateTime(Date admittedDateTime) {
		this.admittedDateTime = admittedDateTime;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getImagePresent() {
		return imagePresent;
	}

	public void setImagePresent(String imagePresent) {
		this.imagePresent = imagePresent;
	}

	public String getFingerprintPresent() {
		return fingerprintPresent;
	}

	public void setFingerprintPresent(String fingerprintPresent) {
		this.fingerprintPresent = fingerprintPresent;
	}

	public String getPropertyPresent() {
		return propertyPresent;
	}

	public void setPropertyPresent(String propertyPresent) {
		this.propertyPresent = propertyPresent;
	}

	public String getTrustAccountPresent() {
		return trustAccountPresent;
	}

	public void setTrustAccountPresent(String trustAccountPresent) {
		this.trustAccountPresent = trustAccountPresent;
	}

	public String getAssesmentPresent() {
		return assesmentPresent;
	}

	public void setAssesmentPresent(String assesmentPresent) {
		this.assesmentPresent = assesmentPresent;
	}

	public String getLegalPresent() {
		return legalCasePresent;
	}

	public void setLegalPresent(String legalPresent) {
		this.legalCasePresent = legalPresent;
	}

	public String getMedicalScreeningPresent() {
		return medicalScreeningPresent;
	}

	public void setMedicalScreeningPresent(String medicalScreeningPresent) {
		this.medicalScreeningPresent = medicalScreeningPresent;
	}
	
	

}