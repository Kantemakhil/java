package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VOffenderAuthorisedVisitors
 */
public class VOffenderAuthorisedVisitors extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private Integer offenderBookId;
	@JsonProperty("visitorOffenderIdDisplay")
	private String visitorOffenderIdDisplay;
	@JsonProperty("contactType")
	private String contactType;
	@JsonProperty("relationshipType")
	private String relationshipType;
	@JsonProperty("contactPersonType")
	private String contactPersonType;
	@JsonProperty("age")
	private Integer age;
	@JsonProperty("contactRootOffenderId")
	private Integer contactRootOffenderId;
	@JsonProperty("contactPersonId")
	private Integer contactPersonId;
	@JsonProperty("visitorLastName")
	private String visitorLastName;
	@JsonProperty("visitorFirstName")
	private String visitorFirstName;
	@JsonProperty("offenderContactPersonId")
	private Integer offenderContactPersonId;
	private Boolean inserted;
	@JsonProperty("location")
	private String location;
	@JsonProperty("visitDate")
	private Date visitDate;
	@JsonProperty("restriction")
	private String restriction;
	
	public VOffenderAuthorisedVisitors() {
		// VOffenderAuthorisedVisitors
	}

	/**
	 * @param offenderBookId
	 *            offenderBookId to set
	 */
	public void setOffenderBookId(final Integer offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 * return theoffenderBookId
	 */
	public Integer getOffenderBookId() {
		return this.offenderBookId;
	}

	/**
	 * @param visitorOffenderIdDisplay
	 *            visitorOffenderIdDisplay to set
	 */
	public void setVisitorOffenderIdDisplay(final String visitorOffenderIdDisplay) {
		this.visitorOffenderIdDisplay = visitorOffenderIdDisplay;
	}

	/**
	 * return thevisitorOffenderIdDisplay
	 */
	public String getVisitorOffenderIdDisplay() {
		return this.visitorOffenderIdDisplay;
	}

	/**
	 * @param contactType
	 *            contactType to set
	 */
	public void setContactType(final String contactType) {
		this.contactType = contactType;
	}

	/**
	 * return thecontactType
	 */
	public String getContactType() {
		return this.contactType;
	}

	/**
	 * @param relationshipType
	 *            relationshipType to set
	 */
	public void setRelationshipType(final String relationshipType) {
		this.relationshipType = relationshipType;
	}

	/**
	 * return therelationshipType
	 */
	public String getRelationshipType() {
		return this.relationshipType;
	}

	/**
	 * @param contactPersonType
	 *            contactPersonType to set
	 */
	public void setContactPersonType(final String contactPersonType) {
		this.contactPersonType = contactPersonType;
	}

	/**
	 * return thecontactPersonType
	 */
	public String getContactPersonType() {
		return this.contactPersonType;
	}

	/**
	 * @param age
	 *            age to set
	 */
	public void setAge(final Integer age) {
		this.age = age;
	}

	/**
	 * return theage
	 */
	public Integer getAge() {
		return this.age;
	}

	/**
	 * @param contactRootOffenderId
	 *            contactRootOffenderId to set
	 */
	public void setContactRootOffenderId(final Integer contactRootOffenderId) {
		this.contactRootOffenderId = contactRootOffenderId;
	}

	/**
	 * return thecontactRootOffenderId
	 */
	public Integer getContactRootOffenderId() {
		return this.contactRootOffenderId;
	}

	/**
	 * @param contactPersonId
	 *            contactPersonId to set
	 */
	public void setContactPersonId(final Integer contactPersonId) {
		this.contactPersonId = contactPersonId;
	}

	/**
	 * return thecontactPersonId
	 */
	public Integer getContactPersonId() {
		return this.contactPersonId;
	}

	/**
	 * @param visitorLastName
	 *            visitorLastName to set
	 */
	public void setVisitorLastName(final String visitorLastName) {
		this.visitorLastName = visitorLastName;
	}

	/**
	 * return thevisitorLastName
	 */
	public String getVisitorLastName() {
		return this.visitorLastName;
	}

	/**
	 * @param visitorFirstName
	 *            visitorFirstName to set
	 */
	public void setVisitorFirstName(final String visitorFirstName) {
		this.visitorFirstName = visitorFirstName;
	}

	/**
	 * return thevisitorFirstName
	 */
	public String getVisitorFirstName() {
		return this.visitorFirstName;
	}

	/**
	 * @param offenderContactPersonId
	 *            offenderContactPersonId to set
	 */
	public void setOffenderContactPersonId(final Integer offenderContactPersonId) {
		this.offenderContactPersonId = offenderContactPersonId;
	}

	/**
	 * return theoffenderContactPersonId
	 */
	public Integer getOffenderContactPersonId() {
		return this.offenderContactPersonId;
	}

	/**
	 * @return the inserted
	 */
	public Boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(final Boolean inserted) {
		this.inserted = inserted;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

	/**
	 * @return the visitDate
	 */
	public Date getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(final Date visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the restriction
	 */
	public String getRestriction() {
		return restriction;
	}

	/**
	 * @param restriction the restriction to set
	 */
	public void setRestriction(final String restriction) {
		this.restriction = restriction;
	}

}