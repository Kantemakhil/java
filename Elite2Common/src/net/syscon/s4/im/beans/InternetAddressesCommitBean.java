package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.InternetAddresses;

/**
 * Class InternetAddressesCommitBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InternetAddressesCommitBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<InternetAddresses> insertList;

	@JsonProperty("deleteList")
	private List<InternetAddresses> deleteList;

	@JsonProperty("updateList")
	private List<InternetAddresses> updateList;
	
	
	@JsonProperty("workupdateList")
	private List<Work> workupdateList;
	
	@JsonProperty("workdeleteList")
	private List<Work> workdeleteList;
	

	/**
	 *
	 * @return
	 */
	public List<InternetAddresses> getInsertList() {
		return insertList;
	}

	/**
	 *
	 * @param insertList
	 */
	public void setInsertList(List<InternetAddresses> insertList) {
		this.insertList = insertList;
	}

	/**
	 *
	 * @return
	 */
	public List<InternetAddresses> getDeleteList() {
		return deleteList;
	}

	/**
	 *
	 * @param deleteList
	 */
	public void setDeleteList(List<InternetAddresses> deleteList) {
		this.deleteList = deleteList;
	}

	/**
	 *
	 * @return
	 */
	public List<InternetAddresses> getUpdateList() {
		return updateList;
	}

	/**
	 *
	 * @param updateList
	 */
	public void setUpdateList(List<InternetAddresses> updateList) {
		this.updateList = updateList;
	}

	public List<Work> getWorkupdateList() {
		return workupdateList;
	}

	public void setWorkupdateList(List<Work> workupdateList) {
		this.workupdateList = workupdateList;
	}

	public List<Work> getWorkdeleteList() {
		return workdeleteList;
	}

	public void setWorkdeleteList(List<Work> workdeleteList) {
		this.workdeleteList = workdeleteList;
	}

}