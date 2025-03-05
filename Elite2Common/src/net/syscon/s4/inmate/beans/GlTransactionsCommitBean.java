package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class SystemProfilesCommitBean extends BaseBean
 */
@XmlRootElement
public class GlTransactionsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<GlTransactions> insertList;
	private List<GlTransactions> deleteList;
	private List<GlTransactions> updateList;

	public void setInsertList(final List<GlTransactions> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<GlTransactions> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<GlTransactions> deleteList) {
		this.deleteList = deleteList;
	}

	public List<GlTransactions> getInsertList() {
		return insertList;
	}

	public List<GlTransactions> getUpdateList() {
		return updateList;
	}

	public List<GlTransactions> getDeleteList() {
		return deleteList;
	}

}
