package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@GlobalValidation(message = "atleast.one.mandatory")
public class ReferenceCodes extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("staffId")
	private Integer staffId;
	
	@JsonProperty("lastName")
	@Size(max = 35)
	private String lastName;

	@JsonProperty("firstName")
	@Size(max = 35)
	private String firstName;

	@JsonProperty("middleName")
	@Size(max = 35)
	private String middleName;

	@JsonProperty("state")
	private String state;

	@JsonProperty("depth")
	private String depth;

	@JsonProperty("data")
	private String data;

	@JsonProperty("labels")
	private String labels;

	@JsonProperty("workflowSeq")
	private String workflowSeq;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("activeFlag")
	@NotNull
	@Size(max = 1)
	private String activeFlag;

	@JsonProperty("createFlag")
	private String createFlag;
	
	@JsonProperty("domainAccess")
	private String domainAccess;

	
	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

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
	private String modifyUserId;

	@JsonProperty("newCode")
	private String newCode;

	@JsonProperty("parentCode")
	private String parentCode;

	@JsonProperty("parentDomain")
	private String parentDomain;

	@JsonProperty("sealFlag")
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
	
	@JsonProperty("itemToStatus")
	private String itemToStatus;
	
	@JsonProperty("canDisplay")
	private Boolean canDisplay = true;
	
	@JsonProperty("seqValue")
	 private Integer seqValue;
	
	@JsonProperty("workType")
	private String workType;
	
	@JsonProperty("workSubType")
	 private String workSubType;
	
	@JsonProperty("codes")
	private String codes;
	
	@JsonProperty("parentAreaCode")
	private String parentAreaCode;
	
	@JsonProperty("frequency")
	private String frequency;
	
	@JsonProperty("updateFlag")
	private String updateFlag;  
	
	@JsonProperty("updateReasonFlag")
	private String updateReasonFlag;
	
	@JsonProperty("automaticFlag")
	private String automaticFlag    ;
	
	@JsonProperty("length")
	private Integer length;
	
	@JsonProperty("lengthUnit")
	private String lengthUnit;
	
	@JsonProperty("reportType")
	private String reportType;
	
	private long workId;
	    
	public long getWorkId() {
		return workId;
	}

	public void setWorkId(long workId) {
		this.workId = workId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(final String workType) {
		this.workType = workType;
	}

	public String getWorkSubType() {
		return workSubType;
	}

	public void setWorkSubType(final String workSubType) {
		this.workSubType = workSubType;
	}

	public String getParentAreaCode() {
		return parentAreaCode;
	}

	public void setParentAreaCode(final String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(final String codes) {
		this.codes = codes;
	}

	/**
	 * Creates new ReferenceCodes class Object
	 */
	public ReferenceCodes() {
		// ReferenceCodes
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(final String state) {
		this.state = state;
	}

	/**
	 * @return the depth
	 */
	public String getDepth() {
		return depth;
	}

	/**
	 * @param depth
	 *            the depth to set
	 */
	public void setDepth(final String depth) {
		this.depth = depth;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(final String data) {
		this.data = data;
	}

	/**
	 * @return the labels
	 */
	public String getLabels() {
		return labels;
	}

	/**
	 * @param labels
	 *            the labels to set
	 */
	public void setLabels(final String labels) {
		this.labels = labels;
	}

	/**
	 * @return the workflow_seq
	 */
	public String getWorkflowSeq() {
		return workflowSeq;
	}

	/**
	 * @param workflow_seq
	 *            the workflow_seq to set
	 */
	public void setWorkflowSeq(final String workflowSeq) {
		this.workflowSeq = workflowSeq;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(final String icon) {
		this.icon = icon;
	}

	/**
	 * @return the activeFlag
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            the activeFlag to set
	 */
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
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
	public void setCreateDatetime(final Date createDatetime) {
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
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
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
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the itemToStatus
	 */
	public String getItemToStatus() {
		return itemToStatus;
	}

	/**
	 * @param itemToStatus the itemToStatus to set
	 */
	public void setItemToStatus(final String itemToStatus) {
		this.itemToStatus = itemToStatus;
	}

	/**
	 * @return the expiredDate
	 */
	public Date getExpiredDate() {
		return expiredDate;
	}

	/**
	 * @param expiredDate
	 *            the expiredDate to set
	 */
	public void setExpiredDate(final Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	/**
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime
	 *            the modifyDatetime to set
	 */
	public void setModifyDatetime(final Date modifyDatetime) {
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
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the newCode
	 */
	public String getNewCode() {
		return newCode;
	}

	/**
	 * @param newCode
	 *            the newCode to set
	 */
	public void setNewCode(final String newCode) {
		this.newCode = newCode;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode
	 *            the parentCode to set
	 */
	public void setParentCode(final String parentCode) {
		this.parentCode = parentCode;
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
	public void setParentDomain(final String parentDomain) {
		this.parentDomain = parentDomain;
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
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * @return the systemDataFlag
	 */
	public String getSystemDataFlag() {
		return systemDataFlag;
	}

	/**
	 * @param systemDataFlag
	 *            the systemDataFlag to set
	 */
	public void setSystemDataFlag(final String systemDataFlag) {
		this.systemDataFlag = systemDataFlag;
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getCanDisplay() {
		return canDisplay;
	}

	public void setCanDisplay(final Boolean canDisplay) {
		this.canDisplay = canDisplay;
	}

	public Integer getSeqValue() {
		return seqValue;
	}

	public void setSeqValue(final Integer seqValue) {
		this.seqValue = seqValue;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getUpdateReasonFlag() {
		return updateReasonFlag;
	}

	public void setUpdateReasonFlag(String updateReasonFlag) {
		this.updateReasonFlag = updateReasonFlag;
	}

	public String getAutomaticFlag() {
		return automaticFlag;
	}

	public void setAutomaticFlag(String automaticFlag) {
		this.automaticFlag = automaticFlag;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getLengthUnit() {
		return lengthUnit;
	}

	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getDomainAccess() {
		return domainAccess;
	}

	public void setDomainAccess(String domainAccess) {
		this.domainAccess = domainAccess;
	}
	
	

	
	
}