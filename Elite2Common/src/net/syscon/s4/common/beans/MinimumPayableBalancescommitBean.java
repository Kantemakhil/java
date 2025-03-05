package net.syscon.s4.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MinimumPayableBalancescommitBean extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty( "insertList")
	private List<MinimumPayableBalances> insertList;
	@JsonProperty( "deleteList")
	private List<MinimumPayableBalances> deleteList;
	@JsonProperty( "updateList")
	private List<MinimumPayableBalances> updateList;
	
	public MinimumPayableBalancescommitBean() {
//		MinimumPayableBalancescommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<MinimumPayableBalances> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<MinimumPayableBalances> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<MinimumPayableBalances> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<MinimumPayableBalances> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<MinimumPayableBalances> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<MinimumPayableBalances> updateList) {
		this.updateList = updateList;
	}

}
