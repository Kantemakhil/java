package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OffenderFreezeDisbursementsCommitBean   extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderFreezeDisbursements> insertList;
	@JsonProperty("deleteList")
	private List<OffenderFreezeDisbursements> deleteList;
	@JsonProperty("updateList")
	private List<OffenderFreezeDisbursements> updateList;

	public OffenderFreezeDisbursementsCommitBean() {
	}

	/**
	 * @return the insertList
	 */
	public List<OffenderFreezeDisbursements> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(List<OffenderFreezeDisbursements> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderFreezeDisbursements> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(List<OffenderFreezeDisbursements> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderFreezeDisbursements> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(List<OffenderFreezeDisbursements> updateList) {
		this.updateList = updateList;
	}
	
	
	
	

}
