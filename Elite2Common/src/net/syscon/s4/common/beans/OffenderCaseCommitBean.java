package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.inst.legals.beans.CourtCases;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderCaseCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<CourtCases> insertList;

	@JsonProperty("updateList")
	private List<CourtCases> updateList;

	/**
	 * Creates new OffenderCasesCommitBean class Object
	 */
	public OffenderCaseCommitBean() {
		// OffenderAlertsCommitBean
	}

	public void setInsertList(final List<CourtCases> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<CourtCases> updateList) {
		this.updateList = updateList;
	}

	public List<CourtCases> getInsertList() {
		return insertList;
	}

	public List<CourtCases> getUpdateList() {
		return updateList;
	}

}
