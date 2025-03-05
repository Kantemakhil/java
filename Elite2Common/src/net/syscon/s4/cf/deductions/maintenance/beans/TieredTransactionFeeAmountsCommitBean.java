package net.syscon.s4.cf.deductions.maintenance.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class TieredTransactionFeeAmountsCommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<TieredTransactionFeeAmounts> insertList;
	@JsonProperty("deleteList")
	private List<TieredTransactionFeeAmounts> deleteList;
	@JsonProperty("updateList")
	private List<TieredTransactionFeeAmounts> updateList;
	public TieredTransactionFeeAmountsCommitBean() {
//		TieredTransactionFeeAmountsCommitBean
	}
	/**
	 * @return the insertList
	 */
	public List<TieredTransactionFeeAmounts> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<TieredTransactionFeeAmounts> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<TieredTransactionFeeAmounts> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<TieredTransactionFeeAmounts> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<TieredTransactionFeeAmounts> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<TieredTransactionFeeAmounts> updateList) {
		this.updateList = updateList;
	}
	
}
