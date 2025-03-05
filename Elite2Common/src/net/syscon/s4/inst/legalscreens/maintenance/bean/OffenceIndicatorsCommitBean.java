package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenceIndicator;

public class OffenceIndicatorsCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenceIndicator> insertList;

	@JsonProperty("deleteList")
	private List<OffenceIndicator> deleteList;

	@JsonProperty("updateList")
	private List<OffenceIndicator> updateList;

	/**
	 * @return the insertList
	 */
	public List<OffenceIndicator> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<OffenceIndicator> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<OffenceIndicator> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<OffenceIndicator> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<OffenceIndicator> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<OffenceIndicator> updateList) {
		this.updateList = updateList;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


}
