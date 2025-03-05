/**
 * 
 */
package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class ProgramServicesCommitBean
 *
 */
public class ProgramServicesCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ProgramServices> insertList;
	private List<ProgramServices> deleteList;
	private List<ProgramServices> updateList;

	/**
	 * @return the insertList
	 */
	public List<ProgramServices> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList
	 *            the insertList to set
	 */
	public void setInsertList(final List<ProgramServices> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<ProgramServices> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList
	 *            the deleteList to set
	 */
	public void setDeleteList(final List<ProgramServices> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<ProgramServices> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList
	 *            the updateList to set
	 */
	public void setUpdateList(final List<ProgramServices> updateList) {
		this.updateList = updateList;
	}

}
