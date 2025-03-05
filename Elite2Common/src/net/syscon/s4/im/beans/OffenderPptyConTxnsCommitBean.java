package net.syscon.s4.im.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class OffenderPptyConTxnsCommitBean extends BaseModel {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<OffenderPptyConTxns> insertList;
	@JsonProperty("deleteList")
	private List<OffenderPptyConTxns> deleteList;
	@JsonProperty("updateList")
	private List<OffenderPptyConTxns> updateList;

	/**
	 * Creates new OffenderPptyConTxnsCommitBean class Object
	 */
	public OffenderPptyConTxnsCommitBean() {
		// OffenderPptyConTxnsCommitBean
	}

	public void setInsertList(List<OffenderPptyConTxns> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderPptyConTxns> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderPptyConTxns> deleteList) {
		this.deleteList = deleteList;
	}

	public List<OffenderPptyConTxns> getInsertList() {
		return insertList;
	}

	public List<OffenderPptyConTxns> getUpdateList() {
		return updateList;
	}

	public List<OffenderPptyConTxns> getDeleteList() {
		return deleteList;
	}

}
