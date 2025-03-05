package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VisitorRestrictions extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("personId")
	private Integer personId;
	@JsonProperty("visitRestrictionType")
	private String visitRestrictionType;
	@JsonProperty("effectiveDate")
	private Date effectiveDate;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("commentTxt")
	private String commentTxt;
	@JsonProperty("createDatetime")
	private Date createDatetime;
	@JsonProperty("createUserId")
	private String createUserId;
	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;
	@JsonProperty("modifyUserId")
	private String modifyUserId;
	@JsonProperty("visitorRestrictionId")
	private Integer visitorRestrictionId;
	@JsonProperty("enteredStaffId")
	private Integer enteredStaffId;
	@JsonProperty("offenderId")
	private Integer offenderId;
	@JsonProperty("sealFlag")
	private String sealFlag;
	private boolean inserted;
	@JsonProperty("description")
	private String description;
	@JsonProperty("restrictionDate")
	private Date restrictionDate;
	

	/**
	 * Creates new VisitorRestrictions class Object
	 */
	public VisitorRestrictions() {
		// VisitorRestrictions
	}

	/**
	 * @param personId
	 *            personId to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	/**
	 * return thepersonId
	 */
	public Integer getPersonId() {
		return this.personId;
	}

	/**
	 * @param visitRestrictionType
	 *            visitRestrictionType to set
	 */
	public void setVisitRestrictionType(String visitRestrictionType) {
		this.visitRestrictionType = visitRestrictionType;
	}

	/**
	 * return thevisitRestrictionType
	 */
	public String getVisitRestrictionType() {
		return this.visitRestrictionType;
	}

	/**
	 * @param effectiveDate
	 *            effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * return theeffectiveDate
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * @param expiryDate
	 *            expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * return theexpiryDate
	 */
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	/**
	 * @param commentTxt
	 *            commentTxt to set
	 */
	public void setCommentTxt(String commentTxt) {
		this.commentTxt = commentTxt;
	}

	/**
	 * return thecommentTxt
	 */
	public String getCommentTxt() {
		return this.commentTxt;
	}

	/**
	 * @param createDatetime
	 *            createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * return thecreateDatetime
	 */
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	/**
	 * @param createUserId
	 *            createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * return thecreateUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * @param modifyDatetime
	 *            modifyDatetime to set
	 */
	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	/**
	 * return themodifyDatetime
	 */
	public Date getModifyDatetime() {
		return this.modifyDatetime;
	}

	/**
	 * @param modifyUserId
	 *            modifyUserId to set
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * return themodifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}

	/**
	 * @param visitorRestrictionId
	 *            visitorRestrictionId to set
	 */
	public void setVisitorRestrictionId(Integer visitorRestrictionId) {
		this.visitorRestrictionId = visitorRestrictionId;
	}

	/**
	 * return thevisitorRestrictionId
	 */
	public Integer getVisitorRestrictionId() {
		return this.visitorRestrictionId;
	}

	/**
	 * @param enteredStaffId
	 *            enteredStaffId to set
	 */
	public void setEnteredStaffId(Integer enteredStaffId) {
		this.enteredStaffId = enteredStaffId;
	}

	/**
	 * return theenteredStaffId
	 */
	public Integer getEnteredStaffId() {
		return this.enteredStaffId;
	}

	/**
	 * @param offenderId
	 *            offenderId to set
	 */
	public void setOffenderId(Integer offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 * return theoffenderId
	 */
	public Integer getOffenderId() {
		return this.offenderId;
	}

	/**
	 * @param sealFlag
	 *            sealFlag to set
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * return thesealFlag
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
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
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
	 * @return the restrictionDate
	 */
	public Date getRestrictionDate() {
		return restrictionDate;
	}

	/**
	 * @param restrictionDate the restrictionDate to set
	 */
	public void setRestrictionDate(Date restrictionDate) {
		this.restrictionDate = restrictionDate;
	}

}