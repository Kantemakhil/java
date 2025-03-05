package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class IncidentFollowUpDetails extends BaseModel implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
@JsonProperty("agencyIncidentId")
private BigDecimal agencyIncidentId;

@JsonProperty("incidentFollowUpId")
private BigDecimal incidentFollowUpId;

@JsonProperty("policy")
private String policy;

@JsonProperty("compliance")
private String compliance;

@JsonProperty("commentText")
private String commentText;

@JsonProperty("createDateTime")
private Date createDateTime;

@JsonProperty("createUserId")
private String createUserId;

@JsonProperty("modifyDateTime")
private Date modifyDateTime;

@JsonProperty("modifyUserId")
private String modifyUserId;

@JsonProperty("sealFlag")
private String sealFlag;

@JsonProperty("returnedOutput")
private BigDecimal returnedOutput;
public BigDecimal getAgencyIncidentId() {
	return agencyIncidentId;
}

public void setAgencyIncidentId(BigDecimal agencyIncidentId) {
	this.agencyIncidentId = agencyIncidentId;
}

public BigDecimal getIncidentFollowUpId() {
	return incidentFollowUpId;
}

public void setIncidentFollowUpId(BigDecimal incidentFollowUpId) {
	this.incidentFollowUpId = incidentFollowUpId;
}

public String getPolicy() {
	return policy;
}

public void setPolicy(String policy) {
	this.policy = policy;
}

public String getCompliance() {
	return compliance;
}

public void setCompliance(String compliance) {
	this.compliance = compliance;
}

public String getCommentText() {
	return commentText;
}

public void setCommentText(String commentText) {
	this.commentText = commentText;
}

public Date getCreateDateTime() {
	return createDateTime;
}

public void setCreateDateTime(Date createDateTime) {
	this.createDateTime = createDateTime;
}

public String getCreateUserId() {
	return createUserId;
}

public void setCreateUserId(String createUserId) {
	this.createUserId = createUserId;
}

public Date getModifyDateTime() {
	return modifyDateTime;
}

public void setModifyDateTime(Date modifyDateTime) {
	this.modifyDateTime = modifyDateTime;
}

public String getModifyUserId() {
	return modifyUserId;
}

public void setModifyUserId(String modifyUserId) {
	this.modifyUserId = modifyUserId;
}

public String getSealFlag() {
	return sealFlag;
}

public void setSealFlag(String sealFlag) {
	this.sealFlag = sealFlag;
}

public BigDecimal getReturnedOutput() {
	return returnedOutput;
}

public void setReturnedOutput(BigDecimal returnedOutput) {
	this.returnedOutput = returnedOutput;
}

}
