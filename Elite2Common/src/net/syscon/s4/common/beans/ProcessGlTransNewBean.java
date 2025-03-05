package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class ProcessGlTransNewBean  extends BaseModel implements Serializable  {

	@JsonProperty("pCsldId")
	private String pCsldId;
	@JsonProperty("pTransType")
	private String pTransType;
	@JsonProperty("pOperationType")
	private String pOperationType;
	@JsonProperty("pTransAmount")
	private Double pTransAmount;
	@JsonProperty("pTransNumber")
	private Long pTransNumber;
	@JsonProperty("pTransDate")
	private Date pTransDate;
	@JsonProperty("pTransDesc")
	private String pTransDesc;
	@JsonProperty("pTransSeq")
	private Long pTransSeq;
	@JsonProperty("pModuleName")
	private String pModuleName;
	@JsonProperty("pOffId")
	private Long pOffId;
	@JsonProperty("pOffBookId")
	private Long pOffBookId;
	@JsonProperty("pSubActTypeDr")
	private String pSubActTypeDr;
	@JsonProperty("pSubActTypeCr")
	private String pSubActTypeCr;
	@JsonProperty("pPayeePersId")
	private Long pPayeePersId;
	@JsonProperty("pPayeeCorpId")
	private Long pPayeeCorpId;
	@JsonProperty("pPayeeNameText")
	private String pPayeeNameText;
	@JsonProperty("pGlSqnc")
	private Long pGlSqnc;
	@JsonProperty("pOffDedId")
	private Long pOffDedId;
	
	public ProcessGlTransNewBean() {
//		ProcessGlTransNewBean
	}

	/**
	 * @return the pCsldId
	 */
	public String getpCsldId() {
		return pCsldId;
	}

	/**
	 * @param pCsldId the pCsldId to set
	 */
	public void setpCsldId(final String pCsldId) {
		this.pCsldId = pCsldId;
	}

	/**
	 * @return the pTransType
	 */
	public String getpTransType() {
		return pTransType;
	}

	/**
	 * @param pTransType the pTransType to set
	 */
	public void setpTransType(final String pTransType) {
		this.pTransType = pTransType;
	}

	/**
	 * @return the pOperationType
	 */
	public String getpOperationType() {
		return pOperationType;
	}

	/**
	 * @param pOperationType the pOperationType to set
	 */
	public void setpOperationType(final String pOperationType) {
		this.pOperationType = pOperationType;
	}

	/**
	 * @return the pTransAmount
	 */
	public Double getpTransAmount() {
		return pTransAmount;
	}

	/**
	 * @param pTransAmount the pTransAmount to set
	 */
	public void setpTransAmount(final Double pTransAmount) {
		this.pTransAmount = pTransAmount;
	}

	/**
	 * @return the pTransNumber
	 */
	public Long getpTransNumber() {
		return pTransNumber;
	}

	/**
	 * @param pTransNumber the pTransNumber to set
	 */
	public void setpTransNumber(final Long pTransNumber) {
		this.pTransNumber = pTransNumber;
	}

	/**
	 * @return the pTransDate
	 */
	public Date getpTransDate() {
		return pTransDate;
	}

	/**
	 * @param pTransDate the pTransDate to set
	 */
	public void setpTransDate(final Date pTransDate) {
		this.pTransDate = pTransDate;
	}

	/**
	 * @return the pTransDesc
	 */
	public String getpTransDesc() {
		return pTransDesc;
	}

	/**
	 * @param pTransDesc the pTransDesc to set
	 */
	public void setpTransDesc(final String pTransDesc) {
		this.pTransDesc = pTransDesc;
	}

	/**
	 * @return the pTransSeq
	 */
	public Long getpTransSeq() {
		return pTransSeq;
	}

	/**
	 * @param pTransSeq the pTransSeq to set
	 */
	public void setpTransSeq(final Long pTransSeq) {
		this.pTransSeq = pTransSeq;
	}

	/**
	 * @return the pModuleName
	 */
	public String getpModuleName() {
		return pModuleName;
	}

	/**
	 * @param pModuleName the pModuleName to set
	 */
	public void setpModuleName(final String pModuleName) {
		this.pModuleName = pModuleName;
	}

	/**
	 * @return the pOffId
	 */
	public Long getpOffId() {
		return pOffId;
	}

	/**
	 * @param pOffId the pOffId to set
	 */
	public void setpOffId(final Long pOffId) {
		this.pOffId = pOffId;
	}

	/**
	 * @return the pOffBookId
	 */
	public Long getpOffBookId() {
		return pOffBookId;
	}

	/**
	 * @param pOffBookId the pOffBookId to set
	 */
	public void setpOffBookId(final Long pOffBookId) {
		this.pOffBookId = pOffBookId;
	}

	/**
	 * @return the pSubActTypeDr
	 */
	public String getpSubActTypeDr() {
		return pSubActTypeDr;
	}

	/**
	 * @param pSubActTypeDr the pSubActTypeDr to set
	 */
	public void setpSubActTypeDr(final String pSubActTypeDr) {
		this.pSubActTypeDr = pSubActTypeDr;
	}

	/**
	 * @return the pSubActTypeCr
	 */
	public String getpSubActTypeCr() {
		return pSubActTypeCr;
	}

	/**
	 * @param pSubActTypeCr the pSubActTypeCr to set
	 */
	public void setpSubActTypeCr(final String pSubActTypeCr) {
		this.pSubActTypeCr = pSubActTypeCr;
	}

	/**
	 * @return the pPayeePersId
	 */
	public Long getpPayeePersId() {
		return pPayeePersId;
	}

	/**
	 * @param pPayeePersId the pPayeePersId to set
	 */
	public void setpPayeePersId(final Long pPayeePersId) {
		this.pPayeePersId = pPayeePersId;
	}

	/**
	 * @return the pPayeeCorpId
	 */
	public Long getpPayeeCorpId() {
		return pPayeeCorpId;
	}

	/**
	 * @param pPayeeCorpId the pPayeeCorpId to set
	 */
	public void setpPayeeCorpId(final Long pPayeeCorpId) {
		this.pPayeeCorpId = pPayeeCorpId;
	}

	/**
	 * @return the pPayeeNameText
	 */
	public String getpPayeeNameText() {
		return pPayeeNameText;
	}

	/**
	 * @param pPayeeNameText the pPayeeNameText to set
	 */
	public void setpPayeeNameText(final String pPayeeNameText) {
		this.pPayeeNameText = pPayeeNameText;
	}

	/**
	 * @return the pGlSqnc
	 */
	public Long getpGlSqnc() {
		return pGlSqnc;
	}

	/**
	 * @param pGlSqnc the pGlSqnc to set
	 */
	public void setpGlSqnc(final Long pGlSqnc) {
		this.pGlSqnc = pGlSqnc;
	}

	/**
	 * @return the pOffDedId
	 */
	public Long getpOffDedId() {
		return pOffDedId;
	}

	/**
	 * @param pOffDedId the pOffDedId to set
	 */
	public void setpOffDedId(final Long pOffDedId) {
		this.pOffDedId = pOffDedId;
	}

}
