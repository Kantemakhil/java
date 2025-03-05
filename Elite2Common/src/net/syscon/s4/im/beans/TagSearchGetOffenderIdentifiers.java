package net.syscon.s4.im.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagSearchGetOffenderIdentifiers extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderId")
	private Integer offenderId;

	@JsonProperty("identifierType")
	private String identifierType;

	@JsonProperty("identifier")
	private String identifier;
	
	public TagSearchGetOffenderIdentifiers()
	{
		// TagSearchGetOffenderIdentifiers
	}

	/**
	 *
	 * @return
	 */
	public Integer getOffenderId() {
		return offenderId;
	}

	/**
	 *
	 * @param offenderId
	 */
	public void setOffenderId(final Integer offenderId) {
		this.offenderId = offenderId;
	}

	/**
	 *
	 * @return
	 */
	public String getIdentifierType() {
		return identifierType;
	}

	/**
	 *
	 * @param identifierType
	 */
	public void setIdentifierType(final String identifierType) {
		this.identifierType = identifierType;
	}

	/**
	 *
	 * @return
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 *
	 * @param identifier
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

}