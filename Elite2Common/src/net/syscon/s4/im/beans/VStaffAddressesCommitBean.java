package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class VStaffAddressesCommitBean extends BaseModel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VStaffAddressesCommitBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<VStaffAddresses> insertList;
	@JsonProperty("deleteList")
	private List<VStaffAddresses> deleteList;
	@JsonProperty("updateList")
	private List<VStaffAddresses> updateList;

	
	/**
	 * Creates new VStaffAddressesCommitBean class Object
	 */
	public VStaffAddressesCommitBean() {
		// TODO: VStaffAddressesCommitBean
	}
	
	public void setInsertList(final List<VStaffAddresses> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VStaffAddresses> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VStaffAddresses> deleteList) {
		this.deleteList = deleteList;
	}

	public List<VStaffAddresses> getInsertList() {
		return insertList;
	}

	public List<VStaffAddresses> getUpdateList() {
		return updateList;
	}

	public List<VStaffAddresses> getDeleteList() {
		return deleteList;
	}

}
