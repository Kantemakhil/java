package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderDeductionReceiptsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderDeductionReceipts> insertList;

	@JsonProperty("deleteList")
	private List<OffenderDeductionReceipts> deleteList;

	@JsonProperty("updateList")
	private List<OffenderDeductionReceipts> updateList;

	/**
	 * Creates new OffenderDeductionReceiptsCommitBean class Object
	 */
	public OffenderDeductionReceiptsCommitBean() {
		// TODO: OffenderDeductionReceiptsCommitBean
	}

	/**
	 * @return the insertList
	 */
	public List<OffenderDeductionReceipts> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<OffenderDeductionReceipts> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenderDeductionReceipts> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<OffenderDeductionReceipts> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenderDeductionReceipts> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<OffenderDeductionReceipts> updateList) {
		this.updateList = updateList;
	}

}
