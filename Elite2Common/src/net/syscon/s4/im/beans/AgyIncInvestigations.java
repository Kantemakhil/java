package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgyIncInvestigations extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("agyIncInvestigationId")
	@NotNull
	private Integer agyIncInvestigationId;
	
	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId;
	
	@JsonProperty("investigatorId")
	@NotNull
	private Integer investigatorId;
	
	@JsonProperty("assignedDate")
	@NotNull
	private Date assignedDate;
	
	@JsonProperty("commentText")
	@Size(max=240)
	private String commentText;
	
	@JsonProperty("createDatetime")
	@NotNull
	private Date createDatetime;
	
	@JsonProperty("createUserId")
	@NotNull
	@Size(max=32)
	private String createUserId;
	
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	
	@JsonProperty("modifyUserId")
	@Size(max=32)
	private String modifyUserId;
	
	@JsonProperty("partySeq")
	@NotNull
	private Integer partySeq;
	
	@JsonProperty("sealFlag")
	@Size(max=1)
	private String sealFlag;
	
	@JsonProperty("investigatorIdDes")
	private String investigatorIdDes;
	
	@JsonProperty("inserted")
	private boolean inserted;
	
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	/**
	 * Creates new AgyIncInvestigations class Object
	 */
	public AgyIncInvestigations() {
		
		//AgyIncInvestigations
	}
	
	
	
	/**
	 * @return the agyIncInvestigationId
	 */
	public Integer getAgyIncInvestigationId() {
		return agyIncInvestigationId;
	}
	/**
	 * @param agyIncInvestigationId the agyIncInvestigationId to set
	 */
	public void setAgyIncInvestigationId(final Integer agyIncInvestigationId) {
		this.agyIncInvestigationId = agyIncInvestigationId;
	}
	/**
	 * @return the agencyIncidentId
	 */
	public Integer getAgencyIncidentId() {
		return agencyIncidentId;
	}
	/**
	 * @param agencyIncidentId the agencyIncidentId to set
	 */
	public void setAgencyIncidentId(final Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}
	/**
	 * @return the investigatorId
	 */
	public Integer getInvestigatorId() {
		return investigatorId;
	}
	/**
	 * @param investigatorId the investigatorId to set
	 */
	public void setInvestigatorId(final Integer investigatorId) {
		this.investigatorId = investigatorId;
	}
	/**
	 * @return the assignedDate
	 */
	public Date getAssignedDate() {
		return assignedDate;
	}
	/**
	 * @param assignedDate the assignedDate to set
	 */
	public void setAssignedDate(final Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}
	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}
	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}
	/**
	 * @param createDatetime the createDatetime to set
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
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the modifyDatetime
	 */
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	/**
	 * @param modifyDatetime the modifyDatetime to set
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
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the partySeq
	 */
	public Integer getPartySeq() {
		return partySeq;
	}
	/**
	 * @param partySeq the partySeq to set
	 */
	public void setPartySeq(final Integer partySeq) {
		this.partySeq = partySeq;
	}
	/**
	 * @return the sealFlag
	 */
	public String getSealFlag() {
		return sealFlag;
	}
	/**
	 * @param sealFlag the sealFlag to set
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
	 * @param inserted the inserted to set
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
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the investigatorIdDes
	 */
	public String getInvestigatorIdDes() {
		return investigatorIdDes;
	}
	/**
	 * @param investigatorIdDes the investigatorIdDes to set
	 */
	public void setInvestigatorIdDes(final String investigatorIdDes) {
		this.investigatorIdDes = investigatorIdDes;
	}


 
}