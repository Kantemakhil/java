package net.syscon.s4.cf.deductions.maintenance.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderTierTxnFeeAmountsCommitBean extends BaseBean
 * 
 */
@XmlRootElement
public class OffenderTierTxnFeeAmountsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderTierTxnFeeAmounts> insertList;
	private List<OffenderTierTxnFeeAmounts> deleteList;
	private List<OffenderTierTxnFeeAmounts> updateList;

	public void setInsertList(List<OffenderTierTxnFeeAmounts> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderTierTxnFeeAmounts> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderTierTxnFeeAmounts> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderTierTxnFeeAmounts> getInsertList() {
		return insertList;
	}

	public List<OffenderTierTxnFeeAmounts> getUpdateList() {
		return updateList;
	}

	public List<OffenderTierTxnFeeAmounts> getDeleteList() {
		return deleteList;
	}

}
