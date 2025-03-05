package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;

public class CaseloadDeductionProfilesCommitBean  extends BaseModel implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<CaseloadDeductionProfiles> insertList;
	@JsonProperty("deleteList")
	private List<CaseloadDeductionProfiles> deleteList;
	@JsonProperty("updateList")
	private List<CaseloadDeductionProfiles> updateList;
	
	public CaseloadDeductionProfilesCommitBean() {
//		CaseloadDeductionProfilesCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<CaseloadDeductionProfiles> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<CaseloadDeductionProfiles> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<CaseloadDeductionProfiles> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<CaseloadDeductionProfiles> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<CaseloadDeductionProfiles> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<CaseloadDeductionProfiles> updateList) {
		this.updateList = updateList;
	}

}
