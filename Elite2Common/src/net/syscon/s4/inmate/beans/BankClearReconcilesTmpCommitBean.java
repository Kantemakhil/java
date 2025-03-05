package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class SystemProfilesCommitBean extends BaseBean
 */
@XmlRootElement
public class BankClearReconcilesTmpCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<BankClearReconcilesTmp> insertList;
	private List<BankClearReconcilesTmp> deleteList;
	private List<BankClearReconcilesTmp> updateList;

	public void setInsertList(final List<BankClearReconcilesTmp> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<BankClearReconcilesTmp> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<BankClearReconcilesTmp> deleteList) {
		this.deleteList = deleteList;
	}

	public List<BankClearReconcilesTmp> getInsertList() {
		return insertList;
	}

	public List<BankClearReconcilesTmp> getUpdateList() {
		return updateList;
	}

	public List<BankClearReconcilesTmp> getDeleteList() {
		return deleteList;
	}

}
