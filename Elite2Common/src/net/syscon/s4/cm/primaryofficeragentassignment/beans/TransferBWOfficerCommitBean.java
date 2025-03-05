package net.syscon.s4.cm.primaryofficeragentassignment.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;

public class TransferBWOfficerCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<OffenderBookings> insertList;
	@JsonProperty("deleteList")
	private List<OffenderBookings> deleteList;
	@JsonProperty("updateList")
	private List<OffenderBookings> updateList;
	@JsonProperty("staffupdateList")
	private List<StaffLocationRoles> staffupdateList;
	
	
	
	@JsonProperty("staffMembers")
	private StaffMembers staffMembers;
	
	@JsonProperty("sealFlag")
	private String sealFlag;

	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * Creates new OffenderBookingsCommitBean class Object
	 */
	public TransferBWOfficerCommitBean() {
		// OffenderBookingsCommitBean
	}

	public StaffMembers getStaffMembers() {
		return staffMembers;
	}

	public void setStaffMembers(StaffMembers staffMembers) {
		this.staffMembers = staffMembers;
	}

	public List<OffenderBookings> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<OffenderBookings> insertList) {
		this.insertList = insertList;
	}
	public List<OffenderBookings> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<OffenderBookings> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffenderBookings> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<OffenderBookings> updateList) {
		this.updateList = updateList;
	}

	public List<StaffLocationRoles> getStaffupdateList() {
		return staffupdateList;
	}

	public void setStaffupdateList(List<StaffLocationRoles> staffupdateList) {
		this.staffupdateList = staffupdateList;
	}
}
