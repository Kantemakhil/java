package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.inst.legals.beans.Sentences;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class SentenceCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<Sentences> insertList;

	@JsonProperty("updateList")
	private List<Sentences> updateList;

	/**
	 * Creates new OffenderCasesCommitBean class Object
	 */
	public SentenceCommitBean() {
		// OffenderAlertsCommitBean
	}

	public List<Sentences> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<Sentences> insertList) {
		this.insertList = insertList;
	}

	public List<Sentences> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<Sentences> updateList) {
		this.updateList = updateList;
	}

	

}
