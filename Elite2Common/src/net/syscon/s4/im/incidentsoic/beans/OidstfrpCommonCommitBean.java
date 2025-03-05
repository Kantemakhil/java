package net.syscon.s4.im.incidentsoic.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class OidstfrpCommonCommitBean extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("staffReportCommitList")
	private IncidentStaffReportCommitBean staffReportCommitList;
	@JsonProperty("staffForceCommitList")
	private StaffForceCommitBean staffForceCommitList;
	@JsonProperty("staffEquipmentCommitList")
	private StaffEquipmentCommitBean staffEquipmentCommitList;

	public void setstaffReportCommitList(IncidentStaffReportCommitBean staffReportCommitList) {
		 this.staffReportCommitList = staffReportCommitList;
	}
	public IncidentStaffReportCommitBean getstaffReportCommitList(){
		 return staffReportCommitList;
	}
	public void setstaffForceCommitList(StaffForceCommitBean staffForceCommitList) {
		 this.staffForceCommitList = staffForceCommitList;
	}
	public StaffForceCommitBean getstaffForceCommitList(){
		 return staffForceCommitList;
	}
	public void setstaffEquipmentCommitList(StaffEquipmentCommitBean staffEquipmentCommitList) {
		 this.staffEquipmentCommitList = staffEquipmentCommitList;
	}
	public StaffEquipmentCommitBean getstaffEquipmentCommitList(){
		 return staffEquipmentCommitList;
	}

}
