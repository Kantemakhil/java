/**
 * 
 */
package net.syscon.s4.inst.casemanagement.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class OffenderSampleSubstancesCommitBean
 *
 */
public class OffenderSampleSubstancesCommitBean extends BaseModel implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private List<OffenderSampleSubstances> insertList;
	private List<OffenderSampleSubstances> deleteList;
	private List<OffenderSampleSubstances> updateList;
	private OffenderSamples offenderSamples;
	/**
	 * @return the insertList
	 */
	public List<OffenderSampleSubstances> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<OffenderSampleSubstances> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<OffenderSampleSubstances> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<OffenderSampleSubstances> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<OffenderSampleSubstances> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<OffenderSampleSubstances> updateList) {
		this.updateList = updateList;
	}
	/**
	 * @return the offenderSamples
	 */
	public OffenderSamples getOffenderSamples() {
		return offenderSamples;
	}
	/**
	 * @param offenderSamples the offenderSamples to set
	 */
	public void setOffenderSamples(OffenderSamples offenderSamples) {
		this.offenderSamples = offenderSamples;
	}
	

}
