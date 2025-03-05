package net.syscon.s4.common.beans;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceDomains extends BaseModel {
	
	/**
	 * @return the oldCodeTable
	 */
	public String getOldCodeTable() {
		return oldCodeTable;
	}

	/**
	 * @param oldCodeTable the oldCodeTable to set
	 */
	public void setOldCodeTable(String oldCodeTable) {
		this.oldCodeTable = oldCodeTable;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("domain")
	private String domain;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("domainStatus")
	private String domainStatus;
	
	@JsonProperty("ownerCode")
	private String ownerCode;
	
	@JsonProperty("applnCode")
	private String applnCode;

	@JsonProperty("oldCodeTable")
	private String oldCodeTable;

	@JsonProperty("parentDomain")
	private String parentDomain;

	@JsonProperty("codeLength")
	private Integer codeLength;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Timestamp modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("superSetDomain")
	private String superSetDomain;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

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
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the domainStatus
	 */
	public String getDomainStatus() {
		return domainStatus;
	}

	/**
	 * @param domainStatus
	 *            the domainStatus to set
	 */
	public void setDomainStatus(String domainStatus) {
		this.domainStatus = domainStatus;
	}

	/**
	 * @return the ownerCode
	 */
	public String getOwnerCode() {
		return ownerCode;
	}

	/**
	 * @param ownerCode
	 *            the ownerCode to set
	 */
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	/**
	 * @return the applnCode
	 */
	public String getApplnCode() {
		return applnCode;
	}

	/**
	 * @param applnCode
	 *            the applnCode to set
	 */
	public void setApplnCode(String applnCode) {
		this.applnCode = applnCode;
	}

	/**
	 * @return the oldCodetable
	 *//*
	public String getOldCodetable() {
		return oldCodetable;
	}

	

	/**
	 * @return the parentDomain
	 */
	public String getParentDomain() {
		return parentDomain;
	}

	/**
	 * @param parentDomain
	 *            the parentDomain to set
	 */
	public void setParentDomain(String parentDomain) {
		this.parentDomain = parentDomain;
	}

	/**
	 * @return the codeLength
	 */
	public Integer getCodeLength() {
		return codeLength;
	}

	/**
	 * @param codeLength
	 *            the codeLength to set
	 */
	public void setCodeLength(Integer codeLength) {
		this.codeLength = codeLength;
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Timestamp getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(Timestamp modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * @return the modifyUserId
	 */
	public String getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the superSetDomain
	 */
	public String getSuperSetDomain() {
		return superSetDomain;
	}

	/**
	 * @param superSetDomain
	 *            the superSetDomain to set
	 */
	public void setSuperSetDomain(String superSetDomain) {
		this.superSetDomain = superSetDomain;
	}

	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * @param sealFlag
	 *            the sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}