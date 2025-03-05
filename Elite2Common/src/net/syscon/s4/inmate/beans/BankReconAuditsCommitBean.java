package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class SystemProfilesCommitBean extends BaseBean
 */
@XmlRootElement
public class BankReconAuditsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<BankReconAudits> insertList;
	private List<BankReconAudits> deleteList;
	private List<BankReconAudits> updateList;

	public void setInsertList(final List<BankReconAudits> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<BankReconAudits> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<BankReconAudits> deleteList) {
		this.deleteList = deleteList;
	}

	public List<BankReconAudits> getInsertList() {
		return insertList;
	}

	public List<BankReconAudits> getUpdateList() {
		return updateList;
	}

	public List<BankReconAudits> getDeleteList() {
		return deleteList;
	}

}
