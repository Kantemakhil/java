package net.syscon.s4.im.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class VDistinctLinkedOffenders extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("contactType")
	private String contactType;
	@JsonProperty("contactTypeDescription")
	private String contactTypeDescription;
	@JsonProperty("relationshipType")
	private String relationshipType;
	@JsonProperty("relationshipTypeDescription")
	private String relationshipTypeDescription;
	@JsonProperty("personId")
	private Long personId;
	@JsonProperty("rootOffenderId")
	private Long rootOffenderId;
	private boolean inserted;

	/**
	 * Creates new VDistinctLinkedOffenders class Object
	 */
	public VDistinctLinkedOffenders() {
		// VDistinctLinkedOffenders
	}

	/**
	 * @return the offenderIdDisplay
	 */
	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	/**
	 * @param offenderIdDisplay
	 *            the offenderIdDisplay to set
	 */
	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the contactType
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 * @param contactType
	 *            the contactType to set
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	/**
	 * @return the contactTypeDescription
	 */
	public String getContactTypeDescription() {
		return contactTypeDescription;
	}

	/**
	 * @param contactTypeDescription
	 *            the contactTypeDescription to set
	 */
	public void setContactTypeDescription(String contactTypeDescription) {
		this.contactTypeDescription = contactTypeDescription;
	}

	/**
	 * @return the relationshipType
	 */
	public String getRelationshipType() {
		return relationshipType;
	}

	/**
	 * @param relationshipType
	 *            the relationshipType to set
	 */
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	/**
	 * @return the relationshipTypeDescription
	 */
	public String getRelationshipTypeDescription() {
		return relationshipTypeDescription;
	}

	/**
	 * @param relationshipTypeDescription
	 *            the relationshipTypeDescription to set
	 */
	public void setRelationshipTypeDescription(String relationshipTypeDescription) {
		this.relationshipTypeDescription = relationshipTypeDescription;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	/**
	 * @return the rootOffenderId
	 */
	public Long getRootOffenderId() {
		return rootOffenderId;
	}

	/**
	 * @param rootOffenderId
	 *            the rootOffenderId to set
	 */
	public void setRootOffenderId(Long rootOffenderId) {
		this.rootOffenderId = rootOffenderId;
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

}