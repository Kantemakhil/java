package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * @author anurag.sachan
 *
 */
public class OffenderAttributes extends BaseModel implements Serializable{
private static final long serialVersionUID = 1L;
	
	@JsonProperty("offenderAttributes")
	private String[] offenderAttributes;
	
	@JsonProperty("offenderAttributeLabel")
	private String offenderAttributeLabel;
	
	@JsonProperty("offenderSystemTable")
	private String offenderSystemTable;
	
	@JsonProperty("offenderSystemTblCol")
	private String offenderSystemTblCol;
	
	@JsonProperty("domainCode")
	private String domainCode;
	
	@JsonProperty("caseType")
	private String caseType;
		
	@JsonProperty("caseStatus")
	private String caseStatus;
	
	@JsonProperty("case_Seq")
	private Long case_Seq;
	
	@JsonProperty("category")
	private String category;
	
	@JsonProperty("sentenceCalcType")
	private String sentenceCalcType;
	
	@JsonProperty("domainValue")
	private String domainValue;
	
	@JsonProperty("offenderAttValues")
	private String offenderAttValues;
	
	@JsonProperty("uniqueId")
	private String uniqueId;
	
	@JsonProperty("attDescription")
	private String attDescription;
	
	@JsonProperty("personalAttributes")
	private String personalAttributes;
	
	@JsonProperty("agyLocId")
	private String agyLocId;
	
	@JsonProperty("offenderBookId")
	private long offenderBookId;
	
	@JsonProperty("pOffenderId")
	private long pOffenderId;

	// P_CASELOAD_ID varchar
	@JsonProperty("pCaseloadId")
	private String pCaseloadId;

	// P_LIVING_UNIT_TYPE
	@JsonProperty("pLivingUnitType")
	private String pLivingUnitType;

	// P_LEVEL_1_CODE
	@JsonProperty("pLevel1Code")
	private String pLevel1Code;

	// P_LEVEL_2_CODE
	@JsonProperty("pLevel2Code")
	private String pLevel2Code;

	// P_LEVEL_3_CODE
	@JsonProperty("pLevel3Code")
	private String pLevel3Code;

	// P_LEVEL_4_CODE
	@JsonProperty("pLevel4Code")
	private String pLevel4Code;

	@JsonProperty("required")
	private String required;

	public String[] getOffenderAttributes() {
		return offenderAttributes;
	}

	public void setOffenderAttributes(String[] offenderAttributes) {
		this.offenderAttributes = offenderAttributes;
	}

	public String getOffenderAttributeLabel() {
		return offenderAttributeLabel;
	}

	public void setOffenderAttributeLabel(String offenderAttributeLabel) {
		this.offenderAttributeLabel = offenderAttributeLabel;
	}

	public String getOffenderSystemTable() {
		return offenderSystemTable;
	}

	public void setOffenderSystemTable(String offenderSystemTable) {
		this.offenderSystemTable = offenderSystemTable;
	}

	public String getOffenderSystemTblCol() {
		return offenderSystemTblCol;
	}

	public void setOffenderSystemTblCol(String offenderSystemTblCol) {
		this.offenderSystemTblCol = offenderSystemTblCol;
	}

	public String getDomainCode() {
		return domainCode;
	}

	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public Long getCase_Seq() {
		return case_Seq;
	}

	public void setCase_Seq(Long case_Seq) {
		this.case_Seq = case_Seq;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSentenceCalcType() {
		return sentenceCalcType;
	}

	public void setSentenceCalcType(String sentenceCalcType) {
		this.sentenceCalcType = sentenceCalcType;
	}

	public String getDomainValue() {
		return domainValue;
	}

	public void setDomainValue(String domainValue) {
		this.domainValue = domainValue;
	}

	public String getOffenderAttValues() {
		return offenderAttValues;
	}

	public void setOffenderAttValues(String offenderAttValues) {
		this.offenderAttValues = offenderAttValues;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getAttDescription() {
		return attDescription;
	}

	public void setAttDescription(String attDescription) {
		this.attDescription = attDescription;
	}

	public String getPersonalAttributes() {
		return personalAttributes;
	}

	public void setPersonalAttributes(String personalAttributes) {
		this.personalAttributes = personalAttributes;
	}

	public String getAgyLocId() {
		return agyLocId;
	}

	public void setAgyLocId(String agyLocId) {
		this.agyLocId = agyLocId;
	}

	public long getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public long getpOffenderId() {
		return pOffenderId;
	}

	public void setpOffenderId(long pOffenderId) {
		this.pOffenderId = pOffenderId;
	}

	public String getpCaseloadId() {
		return pCaseloadId;
	}

	public void setpCaseloadId(String pCaseloadId) {
		this.pCaseloadId = pCaseloadId;
	}

	public String getpLivingUnitType() {
		return pLivingUnitType;
	}

	public void setpLivingUnitType(String pLivingUnitType) {
		this.pLivingUnitType = pLivingUnitType;
	}

	public String getpLevel1Code() {
		return pLevel1Code;
	}

	public void setpLevel1Code(String pLevel1Code) {
		this.pLevel1Code = pLevel1Code;
	}

	public String getpLevel2Code() {
		return pLevel2Code;
	}

	public void setpLevel2Code(String pLevel2Code) {
		this.pLevel2Code = pLevel2Code;
	}

	public String getpLevel3Code() {
		return pLevel3Code;
	}

	public void setpLevel3Code(String pLevel3Code) {
		this.pLevel3Code = pLevel3Code;
	}

	public String getpLevel4Code() {
		return pLevel4Code;
	}

	public void setpLevel4Code(String pLevel4Code) {
		this.pLevel4Code = pLevel4Code;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OffenderAttributes [offenderAttributes=");
		builder.append(Arrays.toString(offenderAttributes));
		builder.append(", offenderAttributeLabel=");
		builder.append(offenderAttributeLabel);
		builder.append(", offenderSystemTable=");
		builder.append(offenderSystemTable);
		builder.append(", offenderSystemTblCol=");
		builder.append(offenderSystemTblCol);
		builder.append(", domainCode=");
		builder.append(domainCode);
		builder.append(", caseType=");
		builder.append(caseType);
		builder.append(", caseStatus=");
		builder.append(caseStatus);
		builder.append(", case_Seq=");
		builder.append(case_Seq);
		builder.append(", category=");
		builder.append(category);
		builder.append(", sentenceCalcType=");
		builder.append(sentenceCalcType);
		builder.append(", domainValue=");
		builder.append(domainValue);
		builder.append(", offenderAttValues=");
		builder.append(offenderAttValues);
		builder.append(", uniqueId=");
		builder.append(uniqueId);
		builder.append(", attDescription=");
		builder.append(attDescription);
		builder.append(", personalAttributes=");
		builder.append(personalAttributes);
		builder.append(", agyLocId=");
		builder.append(agyLocId);
		builder.append(", offenderBookId=");
		builder.append(offenderBookId);
		builder.append(", pOffenderId=");
		builder.append(pOffenderId);
		builder.append(", pCaseloadId=");
		builder.append(pCaseloadId);
		builder.append(", pLivingUnitType=");
		builder.append(pLivingUnitType);
		builder.append(", pLevel1Code=");
		builder.append(pLevel1Code);
		builder.append(", pLevel2Code=");
		builder.append(pLevel2Code);
		builder.append(", pLevel3Code=");
		builder.append(pLevel3Code);
		builder.append(", pLevel4Code=");
		builder.append(pLevel4Code);
		builder.append(", required=");
		builder.append(required);
		builder.append("]");
		return builder.toString();
	}
	
}
