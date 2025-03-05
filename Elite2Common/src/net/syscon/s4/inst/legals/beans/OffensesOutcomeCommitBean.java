package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffensesOutcomeCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffensesOutcome> insertList;

	@JsonProperty("updateList")
	private List<OffensesOutcome> updateList;
	/**
	 * Creates new OffenderCasesCommitBean class Object
	 */
	public OffensesOutcomeCommitBean() {
		// OffenderAlertsCommitBean
	}
	public List<OffensesOutcome> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffensesOutcome> insertList) {
		this.insertList = insertList;
	}
	public List<OffensesOutcome> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffensesOutcome> updateList) {
		this.updateList = updateList;
	}

	
	

}


