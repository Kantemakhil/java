package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StgValidationsCommitBean  extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<StgValidations> insertList;
	@JsonProperty("deleteList")
	private List<StgValidations> deleteList;
	@JsonProperty("updateList")
	private List<StgValidations> updateList;
	
	public StgValidationsCommitBean() {
		// StgValidationsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<StgValidations> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<StgValidations> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<StgValidations> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<StgValidations> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<StgValidations> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<StgValidations> updateList) {
		this.updateList = updateList;
	}

}
