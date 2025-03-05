package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgyIncInvestigationsCommitBean extends BaseModel implements Serializable {
	@JsonProperty("insertList")
	private List<AgyIncInvestigations> insertList;
	@JsonProperty("deleteList")
	private List<AgyIncInvestigations> deleteList;
	@JsonProperty("updateList")
	private List<AgyIncInvestigations> updateList;
	@JsonProperty("insertListInv")
	private List<AgyIncInvStatements> insertListInv;
	@JsonProperty("updateListInv")
	private List<AgyIncInvStatements> updateListInv;

	/**
	 * Creates new AgyIncInvestigationsCommitBean class Object
	 */
	public AgyIncInvestigationsCommitBean() {

		// AgyIncInvestigationsCommitBean
	}

	public void setInsertList(final List<AgyIncInvestigations> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<AgyIncInvestigations> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<AgyIncInvestigations> deleteList) {
		this.deleteList = deleteList;
	}

	public List<AgyIncInvestigations> getInsertList() {
		return insertList;
	}

	public List<AgyIncInvestigations> getUpdateList() {
		return updateList;
	}

	public List<AgyIncInvestigations> getDeleteList() {
		return deleteList;
	}

	/**
	 * @return the insertListInv
	 */
	public List<AgyIncInvStatements> getInsertListInv() {
		return insertListInv;
	}

	/**
	 * @param insertListInv
	 *            the insertListInv to set
	 */
	public void setInsertListInv(List<AgyIncInvStatements> insertListInv) {
		this.insertListInv = insertListInv;
	}

	/**
	 * @return the updateListInv
	 */
	public List<AgyIncInvStatements> getUpdateListInv() {
		return updateListInv;
	}

	/**
	 * @param updateListInv
	 *            the updateListInv to set
	 */
	public void setUpdateListInv(List<AgyIncInvStatements> updateListInv) {
		this.updateListInv = updateListInv;
	}

}
