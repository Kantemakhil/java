package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.CorporateTypes;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class CorporateTypesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<CorporateTypes> insertList;
	@JsonProperty("deleteList")
	private List<CorporateTypes> deleteList;
	@JsonProperty("updateList")
	private List<CorporateTypes> updateList;
	
	public CorporateTypesCommitBean() {
//		CaseloadAccountPeriodsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<CorporateTypes> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<CorporateTypes> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<CorporateTypes> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<CorporateTypes> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<CorporateTypes> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<CorporateTypes> updateList) {
		this.updateList = updateList;
	}

}
