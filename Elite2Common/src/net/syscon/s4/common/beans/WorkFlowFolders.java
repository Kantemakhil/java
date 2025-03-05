package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkFlowFolders extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	@Size(max = 12)
	@JsonProperty("workFlowCode")
	private String workFlowCode;
	private String code;
	
	
	private String activeFlag;
	
	
	private Date expiryDate;

	@JsonProperty("description")
	private String description;

	@JsonProperty("workFlowSeq")
	private BigDecimal workFlowSeq;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@NotNull
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@NotNull
	@Size(max = 32)
	@JsonProperty("createUserId")
	private String createUserId;


	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("state")
	private Integer state;

	@JsonProperty("depth")
	private Integer depth;

	@JsonProperty("icon")
	private String icon;

	private String caseLoadType;
	public String getCaseLoadType() {
		return caseLoadType;
	}
	public void setCaseLoadType(final String caseLoadType) {
		this.caseLoadType = caseLoadType;
	}
	public WorkFlowFolders() {
      // WorkFlowFolders
	}
	public String getCode() {
		return code;
	}
	public void setCode(final String code) {
		this.code = code;
	}

	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(final Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(final Integer state) {
		this.state = state;
	}

	/**
	 * @return the depth
	 */
	public Integer getDepth() {
		return depth;
	}

	/**
	 * @param depth
	 *            the depth to set
	 */
	public void setDepth(final Integer depth) {
		this.depth = depth;
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
	 * @return the workFlowCode
	 */
	public String getWorkFlowCode() {
		return workFlowCode;
	}

	/**
	 * @param workFlowCode
	 *            the workFlowCode to set
	 */
	public void setWorkFlowCode(final String workFlowCode) {
		this.workFlowCode = workFlowCode;
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
	 * @return the workFlowSeq
	 */
	public BigDecimal getWorkFlowSeq() {
		return workFlowSeq;
	}

	/**
	 * @param workFlowSeq
	 *            the workFlowSeq to set
	 */
	public void setWorkFlowSeq(final BigDecimal workFlowSeq) {
		this.workFlowSeq = workFlowSeq;
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

}
