package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class WorkflowScreens extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	@Size(max = 12)
	@JsonProperty("workFlowCode")
	private String workFlowCode;
	
	@NotNull
	@Size(max = 20)
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("workFlowSeq")
	private Integer workFlowSeq;
	
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	

	@JsonProperty("caseLoadType")
	private String caseLoadType;
	
	@NotNull
	@JsonProperty("createDatetime")
	private Date createDatetime;
	
	@NotNull
	@Size(max = 32)
	@JsonProperty("createUserId")
	private String createUserId;
	
	@JsonProperty("sealFlag")
	private String sealFlag;
	
	@JsonProperty("inserted")
	private boolean inserted;
	
	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("state")
	private Integer state;

	@JsonProperty("depth")
	private Integer depth;

	@JsonProperty("icon")
	private String icon;
	
	@JsonProperty("toolTip")
	private String toolTip;
	
	public WorkflowScreens() {
     // WorkflowScreens 
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
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
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
	public Integer getWorkFlowSeq() {
		return workFlowSeq;
	}

	/**
	 * @param workFlowSeq
	 *            the workFlowSeq to set
	 */
	public void setWorkFlowSeq(final Integer workFlowSeq) {
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
	 * @return the caseLoadType
	 */
	public String getCaseLoadType() {
		return caseLoadType;
	}

	/**
	 * @param caseLoadType
	 *            the caseLoadType to set
	 */
	public void setCaseLoadType(final String caseLoadType) {
		this.caseLoadType = caseLoadType;
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
	public void setInserted(final boolean inserted) {
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
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return the toolTip
	 */
	public String getToolTip() {
		return toolTip;
	}

	/**
	 * @param toolTip
	 *            the toolTip to set
	 */
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

}