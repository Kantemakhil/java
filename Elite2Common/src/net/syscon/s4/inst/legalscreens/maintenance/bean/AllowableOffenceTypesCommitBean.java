package net.syscon.s4.inst.legalscreens.maintenance.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.syscon.s4.common.beans.BaseModel;

public class AllowableOffenceTypesCommitBean extends BaseModel {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<AllowableOffenceTypes> insertList;

	@JsonProperty("deleteList")
	private List<AllowableOffenceTypes> deleteList;

	@JsonProperty("updateList")
	private List<AllowableOffenceTypes> updateList;

	/**
	 * @return the insertList
	 */
	public List<AllowableOffenceTypes> getInsertList() {
		return insertList;
	}

	/**
	 * @param insertList the insertList to set
	 */
	public void setInsertList(final List<AllowableOffenceTypes> insertList) {
		this.insertList = insertList;
	}

	/**
	 * @return the deleteList
	 */
	public List<AllowableOffenceTypes> getDeleteList() {
		return deleteList;
	}

	/**
	 * @param deleteList the deleteList to set
	 */
	public void setDeleteList(final List<AllowableOffenceTypes> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 * @return the updateList
	 */
	public List<AllowableOffenceTypes> getUpdateList() {
		return updateList;
	}

	/**
	 * @param updateList the updateList to set
	 */
	public void setUpdateList(final List<AllowableOffenceTypes> updateList) {
		this.updateList = updateList;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
