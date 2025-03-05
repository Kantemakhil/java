package net.syscon.s4.common.beans;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskUsers extends BaseModel {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private Integer nodeId;
	private Integer parentNodeId;
	private String nodeName;
	private Integer count;
	private Boolean hasChild;
	private String content;
	private String createDate;
	private String taskId;
	private List<TaskUsers> subChild;
	private String processDefId;
	private String processInstanceId;
	private String assignee;

	private String description;
	private String comment;

	private String sourceName;
	private String actionButton;
	private String moduleDescription;
	private Integer maxNodeId;

	private List<TaskUsers> childData;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;

	@JsonProperty("offenderId")
	private BigDecimal offenderId;

	private String offenderName;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;

	@JsonProperty("imageId")
	private BigDecimal imageId;

	@JsonProperty("imageThumbnail")
	private byte[] imageThumbnail;

	private Map<String, Object> descriptionMapper;

	private Integer taskCount;

	private Boolean isApprovButton;

	private String userTaskId;

	private String icon;

	private Integer agencyIncidentId;

	private String dueDate;
	
	private String followUpDate;
	
	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(final Integer nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(final Integer parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(final String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(final Integer count) {
		this.count = count;
	}

	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(final Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final String createDate) {
		this.createDate = createDate;
	}

	public List<TaskUsers> getSubChild() {
		return subChild;
	}

	public void setSubChild(final List<TaskUsers> subChild) {
		this.subChild = subChild;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(final String processDefId) {
		this.processDefId = processDefId;
	}

	public void setTaskId(final String taskId) {
		this.taskId = taskId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(final String assignee) {
		this.assignee = assignee;
	}

	public Integer getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public BigDecimal getOffenderId() {
		return offenderId;
	}

	public void setOffenderId(final BigDecimal offenderId) {
		this.offenderId = offenderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(final String sourceName) {
		this.sourceName = sourceName;
	}

	public List<TaskUsers> getChildData() {
		return childData;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public void setChildData(final List<TaskUsers> childData) {
		this.childData = childData;
	}

	public String getOffenderName() {
		return offenderName;
	}

	public void setOffenderName(final String offenderName) {
		this.offenderName = offenderName;
	}

	public Map<String, Object> getDescriptionMapper() {
		return descriptionMapper;
	}

	public void setDescriptionMapper(final Map<String, Object> descriptionMapper) {
		this.descriptionMapper = descriptionMapper;
	}

	public BigDecimal getImageId() {
		return imageId;
	}

	public void setImageId(final BigDecimal imageId) {
		this.imageId = imageId;
	}

	public byte[] getImageThumbnail() {
		return imageThumbnail;
	}

	public void setImageThumbnail(final byte[] imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setOffenderIdDisplay(final String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public Boolean getIsApprovButton() {
		return isApprovButton;
	}

	public void setIsApprovButton(Boolean isApprovButton) {
		this.isApprovButton = isApprovButton;
	}

	public String getActionButton() {
		return actionButton;
	}

	public void setActionButton(String actionButton) {
		this.actionButton = actionButton;
	}

	public String getUserTaskId() {
		return userTaskId;
	}

	public void setUserTaskId(String userTaskId) {
		this.userTaskId = userTaskId;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public Integer getAgencyIncidentId() {
		return agencyIncidentId;
	}

	public void setAgencyIncidentId(Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	public Integer getMaxNodeId() {
		return maxNodeId;
	}

	public void setMaxNodeId(Integer maxNodeId) {
		this.maxNodeId = maxNodeId;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getFollowUpDate() {
		return followUpDate;
	}
	public void setFollowUpDate(String followUpDate) {
		this.followUpDate = followUpDate;
	}

}