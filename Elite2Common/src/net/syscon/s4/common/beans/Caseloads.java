package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Caseloads extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadId")
	@NotNull
	@Size(max = 6)
	private String caseloadId;

	@JsonProperty("accessPropertyFlag")
	@Size(max = 1)
	private String accessPropertyFlag;

	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("billingFlag")
	@NotNull
	@Size(max = 1)
	private String billingFlag;

	@JsonProperty("caseloadType")
	@Size(max = 12)
	private String caseloadType;

	@JsonProperty("commissaryFlag")
	@Size(max = 1)
	private String commissaryFlag;

	@JsonProperty("payrollTrustCaseload")
	@Size(max = 6)
	private String payrollTrustCaseload;

	@JsonProperty("commissaryTrustCaseload")
	@Size(max = 6)
	private String commissaryTrustCaseload;

	@JsonProperty("trustCommissaryCaseload")
	@Size(max = 6)
	private String trustCommissaryCaseload;

	@JsonProperty("communityTrustCaseload")
	@Size(max = 6)
	private String communityTrustCaseload;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("deactivationDate")
	private Date deactivationDate;

	@JsonProperty("description")
	@NotNull
	@Size(max = 40)
	private String description;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("mdtFlag")
	@NotNull
	@Size(max = 1)
	private String mdtFlag;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("payrollFlag")
	@NotNull
	@Size(max = 1)
	private String payrollFlag;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("trustAccountsFlag")
	@NotNull
	@Size(max = 1)
	private String trustAccountsFlag;

	@JsonProperty("trustCaseloadId")
	@Size(max = 6)
	private String trustCaseloadId;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("updateAllowedFlag")
	private String updateAllowedFlag;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	public Caseloads() {
		//Caseloads
	}

	public String getCaseloadId() {
		return this.caseloadId;
	}

	public void setCaseloadId(final String caseloadId) {
		this.caseloadId = caseloadId;
	}

	public String getAccessPropertyFlag() {
		return this.accessPropertyFlag;
	}

	public void setAccessPropertyFlag(final String accessPropertyFlag) {
		this.accessPropertyFlag = accessPropertyFlag;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getBillingFlag() {
		return this.billingFlag;
	}

	public void setBillingFlag(final String billingFlag) {
		this.billingFlag = billingFlag;
	}

	public String getCaseloadType() {
		return this.caseloadType;
	}

	public void setCaseloadType(final String caseloadType) {
		this.caseloadType = caseloadType;
	}

	public String getCommissaryFlag() {
		return this.commissaryFlag;
	}

	public void setCommissaryFlag(final String commissaryFlag) {
		this.commissaryFlag = commissaryFlag;
	}

	/**
	 * @return the payrollTrustCaseload
	 */
	public String getpayrollTrustCaseload() {
		return payrollTrustCaseload;
	}

	/**
	 * @param payrollTrustCaseload
	 *            the payrollTrustCaseload to set
	 */
	public void setpayrollTrustCaseload(final String payrollTrustCaseload) {
		this.payrollTrustCaseload = payrollTrustCaseload;
	}

	/**
	 * @return the commissaryTrustCaseload
	 */
	public String getcommissaryTrustCaseload() {
		return commissaryTrustCaseload;
	}

	/**
	 * @param commissaryTrustCaseload
	 *            the commissaryTrustCaseload to set
	 */
	public void setcommissaryTrustCaseload(final String commissaryTrustCaseload) {
		this.commissaryTrustCaseload = commissaryTrustCaseload;
	}

	/**
	 * @return the trustCommissaryCaseload
	 */
	public String gettrustCommissaryCaseload() {
		return trustCommissaryCaseload;
	}

	/**
	 * @param trustCommissaryCaseload
	 *            the trustCommissaryCaseload to set
	 */
	public void settrustCommissaryCaseload(final String trustCommissaryCaseload) {
		this.trustCommissaryCaseload = trustCommissaryCaseload;
	}

	/**
	 * @return the communityTrustCaseloads
	 */
	public String getcommunityTrustCaseload() {
		return communityTrustCaseload;
	}

	/**
	 * @param communityTrustCaseloads
	 *            the communityTrustCaseloads to set
	 */
	public void setcommunityTrustCaseload(final String communityTrustCaseload) {
		this.communityTrustCaseload = communityTrustCaseload;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getDeactivationDate() {
		return this.deactivationDate;
	}

	public void setDeactivationDate(final Date deactivationDate) {
		this.deactivationDate = deactivationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public BigDecimal getListSeq() {
		return this.listSeq;
	}

	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	public String getMdtFlag() {
		return this.mdtFlag;
	}

	public void setMdtFlag(final String mdtFlag) {
		this.mdtFlag = mdtFlag;
	}

	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getPayrollFlag() {
		return this.payrollFlag;
	}

	public void setPayrollFlag(final String payrollFlag) {
		this.payrollFlag = payrollFlag;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public String getTrustAccountsFlag() {
		return this.trustAccountsFlag;
	}

	public void setTrustAccountsFlag(final String trustAccountsFlag) {
		this.trustAccountsFlag = trustAccountsFlag;
	}

	public String getTrustCaseloadId() {
		return this.trustCaseloadId;
	}

	public void setTrustCaseloadId(final String trustCaseloadId) {
		this.trustCaseloadId = trustCaseloadId;
	}	
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getUpdateAllowedFlag() {
		return updateAllowedFlag;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}
	
	
	

}
