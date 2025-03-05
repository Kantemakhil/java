package net.syscon.s4.inst.visitsmanagement.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagPersonSearchGetPersonsCommiBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<TagPersonSearchGetPersons> insertList;

	@JsonProperty("deleteList")
	private List<TagPersonSearchGetPersons> deleteList;

	@JsonProperty("updateList")
	private List<TagPersonSearchGetPersons> updateList;

	
	
	
	public List<TagPersonSearchGetPersons> getInsertList() {
		return insertList;
	}

	public List<TagPersonSearchGetPersons> getDeleteList() {
		return deleteList;
	}

	public List<TagPersonSearchGetPersons> getUpdateList() {
		return updateList;
	}

	public void setInsertList(List<TagPersonSearchGetPersons> insertList) {
		this.insertList = insertList;
	}

	public void setDeleteList(List<TagPersonSearchGetPersons> deleteList) {
		this.deleteList = deleteList;
	}

	public void setUpdateList(List<TagPersonSearchGetPersons> updateList) {
		this.updateList = updateList;
	}
	

	


}
