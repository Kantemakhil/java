package net.syscon.s4.cf.deductions.maintenance.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TransactionFeeDetailsCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<TransactionFeeDetails> insertList;
	@JsonProperty("deleteList")
	private List<TransactionFeeDetails> deleteList;
	@JsonProperty("updateList")
	private List<TransactionFeeDetails> updateList;

	public TransactionFeeDetailsCommitBean() {
//		TransactionFeeDetailsCommitBean
	}
	/**
	 * @return the insertList
	 */
	public List<TransactionFeeDetails> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<TransactionFeeDetails> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<TransactionFeeDetails> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<TransactionFeeDetails> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<TransactionFeeDetails> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<TransactionFeeDetails> updateList) {
		this.updateList = updateList;
	}
	
}
