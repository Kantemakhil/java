/**
 *
 */
package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.im.beans.OffenderAttributes;

/**
 * @author ankur.gupta
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FindHousingExecuteQueryBean extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("personalAttributeList")
	private List<OffenderAttributes> offenderAttributesList;

	@JsonProperty("offenderBookId")
	private String offenderBookId;

	@JsonProperty("agencyLocId")
	private String agencyLocId;

	public List<OffenderAttributes> getOffenderAttributesList() {
		return offenderAttributesList;
	}

	public void setOffenderAttributesList(List<OffenderAttributes> offenderAttributesList) {
		this.offenderAttributesList = offenderAttributesList;
	}

	public String getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(String offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getAgencyLocId() {
		return agencyLocId;
	}

	public void setAgencyLocId(String agencyLocId) {
		this.agencyLocId = agencyLocId;
	}

}
