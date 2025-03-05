package net.syscon.s4.cf.deductions.maintenance.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderTxnFeeDetailCommitBean extends BaseBean
 * 
 */
@XmlRootElement
public class OffenderTxnFeeDetailsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderTxnFeeDetails> insertList;
	private List<OffenderTxnFeeDetails> deleteList;
	private List<OffenderTxnFeeDetails> updateList;

	public void setInsertList(List<OffenderTxnFeeDetails> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderTxnFeeDetails> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderTxnFeeDetails> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderTxnFeeDetails> getInsertList() {
		return insertList;
	}

	public List<OffenderTxnFeeDetails> getUpdateList() {
		return updateList;
	}

	public List<OffenderTxnFeeDetails> getDeleteList() {
		return deleteList;
	}

}
