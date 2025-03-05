package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.Offence;

public class OffenceCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<Offence> insertList;

	@JsonProperty("deleteList")
	private List<Offence> deleteList;

	@JsonProperty("updateList")
	private List<Offence> updateList;

	/**
	 * @return the insertList
	 */
	public List<Offence> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<Offence> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<Offence> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<Offence> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<Offence> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<Offence> updateList) {
		this.updateList = updateList;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
