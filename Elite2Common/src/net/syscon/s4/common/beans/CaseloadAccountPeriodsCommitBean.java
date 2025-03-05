package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CaseloadAccountPeriodsCommitBean extends BaseModel implements Serializable {

	@JsonProperty("insertList")
	private List<CaseloadAccountPeriods> insertList;
	@JsonProperty("deleteList")
	private List<CaseloadAccountPeriods> deleteList;
	@JsonProperty("updateList")
	private List<CaseloadAccountPeriods> updateList;
	
	public CaseloadAccountPeriodsCommitBean() {
//		CaseloadAccountPeriodsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<CaseloadAccountPeriods> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<CaseloadAccountPeriods> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<CaseloadAccountPeriods> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<CaseloadAccountPeriods> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<CaseloadAccountPeriods> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<CaseloadAccountPeriods> updateList) {
		this.updateList = updateList;
	}

}
