package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class AgencyIncidentRepairs extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("agencyIncidentId")
	@NotNull
	private Integer agencyIncidentId;

	@JsonProperty("repairSeq")
	@NotNull
	private Integer repairSeq;

	@JsonProperty("repairType")
	@NotNull
	@Size(max = 12)
	private String repairType;

	@JsonProperty("commentText")
	private String commentText;

	@JsonProperty("modifyUserId")
	private String modifyUserId;

	@JsonProperty("modifyDateTime")
	private Date modifyDateTime;

	@JsonProperty("repairCost")
	private Double repairCost;

	@JsonProperty("createDateTime")
	@NotNull
	private Date createDateTime;

	@JsonProperty("createUserId")
	@NotNull
	@Size(max = 32)
	private String createUserId;

	@JsonProperty("sealFlag")
	private String sealFlag;

	@JsonProperty("repairTypeDes")
	private String repairTypeDes;

	@JsonProperty("inserted")
	private boolean inserted;

	@JsonProperty("errorMessage")
	private String errorMessage;

	@JsonProperty("code")
	private String code;
	
	
	@JsonProperty("repairCostdes")
	private String repairCostdes;
	/**
	 * Creates new AgencyIncidentRepairs class Object
	 */
	public AgencyIncidentRepairs() {
		// AgencyIncidentRepairs
	}

	/**
	 * @param agencyIncidentId
	 *            agencyIncidentId to set
	 */
	public void setAgencyIncidentId(final Integer agencyIncidentId) {
		this.agencyIncidentId = agencyIncidentId;
	}

	/**
	 * return the agencyIncidentId
	 */
	public Integer getAgencyIncidentId() {
		return this.agencyIncidentId;
	}
    

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}
	
	/**
	 * @param repairSeq
	 *            repairSeq to set
	 */
	public void setRepairSeq(final Integer repairSeq) {
		this.repairSeq = repairSeq;
	}

	/**
	 * return the repairSeq
	 */
	public Integer getRepairSeq() {
		return this.repairSeq;
	}

	/**
	 * @param repairType
	 *            repairType to set
	 */
	public void setRepairType(final String repairType) {
		this.repairType = repairType;
	}

	/**
	 * return the repairType
	 */
	public String getRepairType() {
		return this.repairType;
	}

	/**
	 * @param commentText
	 *            commentText to set
	 */
	public void setCommentText(final String commentText) {
		this.commentText = commentText;
	}

	/**
	 * return the commentText
	 */
	public String getCommentText() {
		return this.commentText;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return the modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDateTime(final Date modifyDatetime) {
		this.modifyDateTime = modifyDatetime;
	}

	/**
	 * return the modifyDatetime
	 */
	public Date getModifyDateTime() {
		return this.modifyDateTime;
	}

	/**
	 * @return the repairCost
	 */
	public Double getRepairCost() {
		return repairCost;
	}

	/**
	 * @param repairCost
	 *            the repairCost to set
	 */
	public void setRepairCost(final Double repairCost) {
		this.repairCost = repairCost;
	}

	/**
	 * @return the repairCostdes
	 */
	public String getRepairCostdes() {
		return repairCostdes;
	}

	/**
	 * @param repairCostdes the repairCostdes to set
	 */
	public void setRepairCostdes(String repairCostdes) {
		this.repairCostdes = repairCostdes;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDateTime(final Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * return the createDatetime
	 */
	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return the createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return the sealFlag
	 */
	public String getSealFlag() {
		return this.sealFlag;
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
	 * @return the repairTypeDes
	 */
	public String getRepairTypeDes() {
		return repairTypeDes;
	}

	/**
	 * @param repairTypeDes
	 *            the repairTypeDes to set
	 */
	public void setRepairTypeDes(final String repairTypeDes) {
		this.repairTypeDes = repairTypeDes;
	}

}