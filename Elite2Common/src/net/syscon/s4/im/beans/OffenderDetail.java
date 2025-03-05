package net.syscon.s4.im.beans;

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
public class OffenderDetail extends BaseModel implements Serializable {
	
	
	private static final long serialVersionUID = 1L;


	@JsonProperty("birthDate")
	private Date birthDate;

	@JsonProperty("firstName")
	@Size(max = 35)
	private String firstName;

	
	@JsonProperty("lastName")
	@NotNull
	private String lastName;

	
	@JsonProperty("middleName")
	@Size(max = 35)
	private String middleName;

	@JsonProperty("raceCode")
	@Size(max = 12)
	private String raceCode;

	@JsonProperty("remarkCode")
	@Size(max = 12)
	private String remarkCode;
	
	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("sexCode")
	@NotNull
	@Size(max = 12)
	private String sexCode;
	
	private String offenderDisplayId;
	
	private String identifyingNumber;
	private String uniqueIdNumber;
	private String courtLocationProcessingCase;
	private String courtCaseNumber;
	private String caseType;
	private Date courtDate;
	private Date startCourtTime;
	private String charegeStatue;
	private String offence;
	private Date offenceDate;
	private String disposition;
	private String sentenceCategory;
	private String sentenceType;
	private String status;
	private Date senetenceStartDate;
	private String senetenceTermType;
	private Date senetenceTermStartDate;
	private String senetenceTermInYear;
	private String senetenceTermInMonths;
	private String senetenceTermInDays;
	
	
	
	
	
	
	
	

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("offenderBookId")
	private Long offenderBookId;

	@JsonProperty("offenderId")
	@NotNull
	private Long offenderId;

	

	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public String getRemarkCode() {
		return remarkCode;
	}

	public void setRemarkCode(String remarkCode) {
		this.remarkCode = remarkCode;
	}

	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	public void setRootOffenderId(BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public Long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public Long getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(Long offenderId) {
		this.offenderId = offenderId;
	}

	public String getIdentifyingNumber() {
		return identifyingNumber;
	}

	public void setIdentifyingNumber(String identifyingNumber) {
		this.identifyingNumber = identifyingNumber;
	}

	public String getUniqueIdNumber() {
		return uniqueIdNumber;
	}

	public void setUniqueIdNumber(String uniqueIdNumber) {
		this.uniqueIdNumber = uniqueIdNumber;
	}

	public String getCourtLocationProcessingCase() {
		return courtLocationProcessingCase;
	}

	public void setCourtLocationProcessingCase(String courtLocationProcessingCase) {
		this.courtLocationProcessingCase = courtLocationProcessingCase;
	}

	public String getCourtCaseNumber() {
		return courtCaseNumber;
	}

	public void setCourtCaseNumber(String courtCaseNumber) {
		this.courtCaseNumber = courtCaseNumber;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public Date getCourtDate() {
		return courtDate;
	}

	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}

	public Date getStartCourtTime() {
		return startCourtTime;
	}

	public void setStartCourtTime(Date startCourtTime) {
		this.startCourtTime = startCourtTime;
	}

	public String getCharegeStatue() {
		return charegeStatue;
	}

	public void setCharegeStatue(String charegeStatue) {
		this.charegeStatue = charegeStatue;
	}

	public String getOffence() {
		return offence;
	}

	public void setOffence(String offence) {
		this.offence = offence;
	}

	public Date getOffenceDate() {
		return offenceDate;
	}

	public void setOffenceDate(Date offenceDate) {
		this.offenceDate = offenceDate;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getSentenceCategory() {
		return sentenceCategory;
	}

	public void setSentenceCategory(String sentenceCategory) {
		this.sentenceCategory = sentenceCategory;
	}

	public String getSentenceType() {
		return sentenceType;
	}

	public void setSentenceType(String sentenceType) {
		this.sentenceType = sentenceType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSenetenceStartDate() {
		return senetenceStartDate;
	}

	public void setSenetenceStartDate(Date senetenceStartDate) {
		this.senetenceStartDate = senetenceStartDate;
	}

	public String getSenetenceTermType() {
		return senetenceTermType;
	}

	public void setSenetenceTermType(String senetenceTermType) {
		this.senetenceTermType = senetenceTermType;
	}

	public Date getSenetenceTermStartDate() {
		return senetenceTermStartDate;
	}

	public void setSenetenceTermStartDate(Date senetenceTermStartDate) {
		this.senetenceTermStartDate = senetenceTermStartDate;
	}

	public String getSenetenceTermInYear() {
		return senetenceTermInYear;
	}

	public void setSenetenceTermInYear(String senetenceTermInYear) {
		this.senetenceTermInYear = senetenceTermInYear;
	}
	
	

	public String getOffenderDisplayId() {
		return offenderDisplayId;
	}

	public void setOffenderDisplayId(String offenderDisplayId) {
		this.offenderDisplayId = offenderDisplayId;
	}

	public String getSenetenceTermInMonths() {
		return senetenceTermInMonths;
	}

	public void setSenetenceTermInMonths(String senetenceTermInMonths) {
		this.senetenceTermInMonths = senetenceTermInMonths;
	}

	public String getSenetenceTermInDays() {
		return senetenceTermInDays;
	}

	public void setSenetenceTermInDays(String senetenceTermInDays) {
		this.senetenceTermInDays = senetenceTermInDays;
	}

	
	
	
	
}
