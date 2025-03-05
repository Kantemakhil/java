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
public class ReferenceCodes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("description")
	@NotNull
	@Size(max = 40)
	private String description;

	@JsonProperty("expiredDate")
	private Date expiredDate;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	@Size(max = 32)
	private String modifyUserId;

	@JsonProperty("newCode")
	@Size(max = 12)
	private String newCode;

	@JsonProperty("parentCode")
	@Size(max = 12)
	private String parentCode;

	@JsonProperty("parentDomain")
	@Size(max = 12)
	private String parentDomain;

	@JsonProperty("sealFlag")
	@Size(max = 1)
	private String sealFlag;

	@JsonProperty("systemDataFlag")
	@NotNull
	@Size(max = 1)
	private String systemDataFlag;

	@JsonProperty("domain")
	@NotNull
	@Size(max = 12)
	private String domain;

	@JsonProperty("code")
	@NotNull
	@Size(max = 12)
	private String code;
	@JsonProperty("profileType")
	private String profileType;

	@JsonProperty("oicOffenceType")
	private String oicOffenceType;

	@JsonProperty("incidentType")
	private String incidentType;
	
	@JsonProperty("format")
	private String format;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Creates new ReferenceCodes class Object
	 */
	public ReferenceCodes() {
		//ReferenceCodes
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the oicOffenceType
	 */
	public String getOicOffenceType() {
		return oicOffenceType;
	}

	/**
	 * @param oicOffenceType
	 *            the oicOffenceType to set
	 */
	public void setOicOffenceType(final String oicOffenceType) {
		this.oicOffenceType = oicOffenceType;
	}

	/**
	 * @return the incidentType
	 */
	public String getIncidentType() {
		return incidentType;
	}

	/**
	 * @param incidentType
	 *            the incidentType to set
	 */
	public void setIncidentType(final String incidentType) {
		this.incidentType = incidentType;
	}

	/**
	 *
	 * @return
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 *
	 * @param activeFlag
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 *
	 * @return
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 *
	 * @param createDatetime
	 */
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
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
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 *
	 * @return
	 */
	public Date getExpiredDate() {
		return expiredDate;
	}

	/**
	 *
	 * @param expiredDate
	 */
	public void setExpiredDate(final Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	/**
	 *
	 * @return
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 *
	 * @param listSeq
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 *
	 * @return
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 *
	 * @param modifyDatetime
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
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
	public String getNewCode() {
		return newCode;
	}

	/**
	 *
	 * @param newCode
	 */
	public void setNewCode(final String newCode) {
		this.newCode = newCode;
	}

	/**
	 *
	 * @return
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 *
	 * @param parentCode
	 */
	public void setParentCode(final String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 *
	 * @return
	 */
	public String getParentDomain() {
		return parentDomain;
	}

	/**
	 *
	 * @param parentDomain
	 */
	public void setParentDomain(final String parentDomain) {
		this.parentDomain = parentDomain;
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
	public String getSystemDataFlag() {
		return systemDataFlag;
	}

	/**
	 *
	 * @param systemDataFlag
	 */
	public void setSystemDataFlag(final String systemDataFlag) {
		this.systemDataFlag = systemDataFlag;
	}

	/**
	 *
	 * @return
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 *
	 * @param domain
	 */
	public void setDomain(final String domain) {
		this.domain = domain;
	}

	/**
	 *
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 *
	 * @param code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

}