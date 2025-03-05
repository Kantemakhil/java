package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.Offenders;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderOffenderIdentifierUtility extends BaseModel implements Serializable {
	@JsonProperty("offenderList")
	private List<Offenders> offenderList;

	@JsonProperty("offenderIdentifierList")
	private List<OffenderIdentifier> offenderIdentifierList;

	/**
	 * Creates new OffenderOffenderIdentifierUtility class Object
	 */
	public OffenderOffenderIdentifierUtility() {
		super();
	}

	/**
	 * @return the offenderList
	 */
	public List<Offenders> getOffenderList() {
		return offenderList;
	}

	/**
	 * @param offenderList
	 *            the offenderList to set
	 */
	public void setOffenderList(List<Offenders> offenderList) {
		this.offenderList = offenderList;
	}

	/**
	 * @return the offenderIdentifierList
	 */
	public List<OffenderIdentifier> getOffenderIdentifierList() {
		return offenderIdentifierList;
	}

	/**
	 * @param offenderIdentifierList
	 *            the offenderIdentifierList to set
	 */
	public void setOffenderIdentifierList(List<OffenderIdentifier> offenderIdentifierList) {
		this.offenderIdentifierList = offenderIdentifierList;
	}

}
