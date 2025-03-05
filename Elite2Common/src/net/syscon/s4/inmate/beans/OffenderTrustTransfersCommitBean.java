package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
/**
 * 
 *class OffenderTrustTransfersCommitBean
 *
 */
public class OffenderTrustTransfersCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderTrustTransfers> insertList;
	@JsonProperty("deleteList")
	private List<OffenderTrustTransfers> deleteList;
	@JsonProperty("updateList")
	private List<OffenderTrustTransfers> updateList;
	/**
	 * @return the insertList
	 */
	public List<OffenderTrustTransfers> getInsertList() {
		return insertList;
	}
	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<OffenderTrustTransfers> insertList) {
		this.insertList = insertList;
	}
	/**
	 * @return the deleteList
	 */
	public List<OffenderTrustTransfers> getDeleteList() {
		return deleteList;
	}
	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<OffenderTrustTransfers> deleteList) {
		this.deleteList = deleteList;
	}
	/**
	 * @return the updateList
	 */
	public List<OffenderTrustTransfers> getUpdateList() {
		return updateList;
	}
	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<OffenderTrustTransfers> updateList) {
		this.updateList = updateList;
	}
	
	

}
