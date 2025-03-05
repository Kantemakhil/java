package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.Offenders;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderIdentifier extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadType")
	@Size(max = 12)
	private String caseloadType;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("identifier")
	@NotNull
	@Size(max = 20)
	private String identifier;

	@JsonProperty("identifierType")
	@NotNull
	@Size(max = 12)
	private String identifierType;

	@JsonProperty("issuedAuthorityText")
	@Size(max = 240)
	private String issuedAuthorityText;

	@JsonProperty("issuedDate")
	private Date issuedDate;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("rootOffenderId")
	private BigDecimal rootOffenderId;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("verifiedFlag")
	@Size(max = 1)
	private String verifiedFlag;

	@JsonProperty("verifiedFlag1")
	private String verifiedFlag1;

	@JsonProperty("oFFENDERS")
	private Offenders oFFENDERS;

	@JsonProperty("offenderId")
	@NotNull
	private long offenderId;

	@JsonProperty("offenderIdSeq")
	@NotNull
	@Size(max = 12)
	private String offenderIdSeq;

	@JsonProperty("domain")
	private String domain;

	@JsonProperty("code")
	private String code;
	
	private String domainAccess;

	/**
	 * Creates new OffenderIdentifier class Object
	 */
	public OffenderIdentifier() {
		// TODO: OffenderIdentifier
	}

	/**
	 *
	 * @return
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 *
	 * @param caseloadType
	 */
	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 *
	 * @param createDateTime
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 *
	 * @return
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 *
	 * @param createUserId
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 *
	 * @return
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 *
	 * @param identifier
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 *
	 * @return
	 */
	public String getIdentifierType() {
		return identifierType;
	}

	/**
	 *
	 * @param identifierType
	 */
	public void setIdentifierType(final String identifierType) {
		this.identifierType = identifierType;
	}

	/**
	 *
	 * @return
	 */
	public String getIssuedAuthorityText() {
		return issuedAuthorityText;
	}

	/**
	 *
	 * @param issuedAuthorityText
	 */
	public void setIssuedAuthorityText(final String issuedAuthorityText) {
		this.issuedAuthorityText = issuedAuthorityText;
	}

	/**
	 *
	 * @return
	 */
	public Date getIssuedDate() {
		return issuedDate;
	}

	/**
	 *
	 * @param issuedDate
	 */
	public void setIssuedDate(final Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDateTime(final Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	/**
	 *
	 * @return
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 *
	 * @param modifyUserId
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 *
	 * @param rootOffenderId
	 */
	public void setRootOffenderId(final BigDecimal rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 *
	 * @param sealFlag
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getVerifiedFlag() {
		return verifiedFlag;
	}

	/**
	 *
	 * @param verifiedFlag
	 */
	public void setVerifiedFlag(final String verifiedFlag) {
		this.verifiedFlag = verifiedFlag;
	}

	/**
	 *
	 * @return
	 */
	public Offenders getOFFENDERS() {
		return oFFENDERS;
	}

	/**
	 *
	 * @param oFFENDERS
	 */
	public void setOFFENDERS(final Offenders oFFENDERS) {
		this.oFFENDERS = oFFENDERS;
	}

	/**
	 *
	 * @return
	 */
	public long getOffenderId() {
		return offenderId;
	}

	/**
	 *
	 * @param offenderId
	 */
	public void setOffenderId(final long offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getOffenderIdSeq() {
		return offenderIdSeq;
	}

	/**
	 *
	 * @param offenderIdSeq
	 */
	public void setOffenderIdSeq(final String offenderIdSeq) {
		this.offenderIdSeq = offenderIdSeq;
	}

	/**
	 * @return the verifiedFlag1
	 */
	public String getVerifiedFlag1() {
		return verifiedFlag1;
	}

	/**
	 * @param verifiedFlag1
	 *            the verifiedFlag1 to set
	 */
	public void setVerifiedFlag1(final String verifiedFlag1) {
		this.verifiedFlag1 = verifiedFlag1;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(final String domain) {
		this.domain = domain;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	public String getDomainAccess() {
		return domainAccess;
	}

	public void setDomainAccess(String domainAccess) {
		this.domainAccess = domainAccess;
	}
	
	

}