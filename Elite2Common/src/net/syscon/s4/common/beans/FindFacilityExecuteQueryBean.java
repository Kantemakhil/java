/**
 *
 */
package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.OffenderAttributes;

/**
 * @author ankur.gupta
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FindFacilityExecuteQueryBean  extends BaseModel{

	/**
	 *
	 */
private static final long serialVersionUID = 1L;

	@JsonProperty("personalAttributeSet")
	private List<OffenderAttributes> personalAttributeSet;

	@JsonProperty("caseType")
	private String caseType;

	@JsonProperty("sentenceType")
	private String sentenceType;

	@JsonProperty("pLivingUnitType")
	private String pLivingUnitType;

	public List<OffenderAttributes> getPersonalAttributeSet() {
		return personalAttributeSet;
	}

	public void setPersonalAttributeSet(List<OffenderAttributes> personalAttributeSet) {
		this.personalAttributeSet = personalAttributeSet;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getSentenceType() {
		return sentenceType;
	}

	public void setSentenceType(String sentenceType) {
		this.sentenceType = sentenceType;
	}

	public String getpLivingUnitType() {
		return pLivingUnitType;
	}

	public void setpLivingUnitType(String pLivingUnitType) {
		this.pLivingUnitType = pLivingUnitType;
	}







}
