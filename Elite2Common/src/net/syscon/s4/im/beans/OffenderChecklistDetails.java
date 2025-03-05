package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderChecklistDetails extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("caseloadType")
	private String caseloadType;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("createDateTime")
	private Date createDateTime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("listSeq")
	private BigDecimal listSeq;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("releasePlanId")
	private String modifyUserId;

	@JsonProperty("profileCode")
	private String profileCode;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("offenderChecklistId")
	private Long offenderChecklistId;

	@JsonProperty("profileSeq")
	private Long profileSeq;

	@JsonProperty("profileType")
	private String profileType;

	@JsonProperty("checklistType")
	private String checklistType;

	@JsonProperty("description")
	private String description;
	
	@JsonProperty("count")
	private Integer count;
	
	@JsonProperty("codevalueType")
	private String codevalueType;
	
	@JsonProperty("UpdateAllowedFlag")
	private String UpdateAllowedFlag;
	
	@JsonProperty("profileCodeVal")
	private String profileCodeVal;
	
	@JsonProperty("mandatoryFlag")
	private String mandatoryFlag;

	/**
	 * Creates new ReleasePlan class Object
	 */
	public OffenderChecklistDetails() {
		// OffenderChecklistDetails
	}

	/**
	 * @return the caseloadType
	 */
	public String getCaseloadType() {
		return caseloadType;
	}

	/**
	 * @param caseloadType
	 *            the caseloadType to set
	 */
	public void setCaseloadType(String caseloadType) {
		this.caseloadType = caseloadType;
	}

	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return commentText;
	}

	/**
	 * @param commentText
	 *            the commentText to set
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
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
	 * @return the listSeq
	 */
	public BigDecimal getListSeq() {
		return listSeq;
	}

	/**
	 * @param listSeq
	 *            the listSeq to set
	 */
	public void setListSeq(BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

	/**
	 * @return the modifyDateTime
	 */
	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	/**
	 * @param modifyDateTime
	 *            the modifyDateTime to set
	 */
	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
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
	 * @return the profileCode
	 */
	public String getProfileCode() {
		return profileCode;
	}

	/**
	 * @param profileCode
	 *            the profileCode to set
	 */
	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
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
	 * @return the offenderChecklistId
	 */
	public Long getOffenderChecklistId() {
		return offenderChecklistId;
	}

	/**
	 * @param offenderChecklistId
	 *            the offenderChecklistId to set
	 */
	public void setOffenderChecklistId(Long offenderChecklistId) {
		this.offenderChecklistId = offenderChecklistId;
	}

	/**
	 * @return the profileSeq
	 */
	public Long getProfileSeq() {
		return profileSeq;
	}

	/**
	 * @param profileSeq
	 *            the profileSeq to set
	 */
	public void setProfileSeq(Long profileSeq) {
		this.profileSeq = profileSeq;
	}

	/**
	 * @return the profileType
	 */
	public String getProfileType() {
		return profileType;
	}

	/**
	 * @param profileType
	 *            the profileType to set
	 */
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	/**
	 * @return the checklistType
	 */
	public String getChecklistType() {
		return checklistType;
	}

	/**
	 * @param checklistType
	 *            the checklistType to set
	 */
	public void setChecklistType(String checklistType) {
		this.checklistType = checklistType;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCodevalueType() {
		return codevalueType;
	}

	public String getUpdateAllowedFlag() {
		return UpdateAllowedFlag;
	}

	public void setCodevalueType(String codevalueType) {
		this.codevalueType = codevalueType;
	}

	public void setUpdateAllowedFlag(String updateAllowedFlag) {
		UpdateAllowedFlag = updateAllowedFlag;
	}

	public String getProfileCodeVal() {
		return profileCodeVal;
	}

	public void setProfileCodeVal(String profileCodeVal) {
		this.profileCodeVal = profileCodeVal;
	}

	public String getMandatoryFlag() {
		return mandatoryFlag;
	}

	public void setMandatoryFlag(String mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}

}
