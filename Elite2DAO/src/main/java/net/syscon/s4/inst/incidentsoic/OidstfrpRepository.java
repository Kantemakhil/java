package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;
import net.syscon.s4.im.incidentsoic.beans.StaffEquipment;
import net.syscon.s4.im.incidentsoic.beans.StaffForce;

public interface OidstfrpRepository {

	Integer staffReportCommitDataInsert(List<IncidentStaffReport> lInsert);
	
	Integer staffReportCommitDataUpdate(List<IncidentStaffReport> lUpdate);
	
	Integer staffReportCommitDataDelete(List<IncidentStaffReport> lDelete);

	List<IncidentStaffReport> staffReportExecuteQuery(IncidentStaffReport commitBean);
	
	List<StaffForce> staffforceExecuteQuery(StaffForce objSearchDao);

	List<StaffEquipment> staffEquipementExecuteQuery(StaffEquipment objSearchDao);

	 Integer StaffForceCommitBeanInsert(List<StaffForce> lInsert) ;
	

	 Integer StaffForceCommitBeanUpdate(List<StaffForce> lUpdate) ;
	

	 Integer StaffForceCommitBeanDelete(List<StaffForce> lDelete) ;
	

	 Integer StaffEquipmentCommitBeanInsert(List<StaffEquipment> lInsert) ;
	

	 Integer StaffEquipmentCommitBeanUpdate(List<StaffEquipment> lUpdate) ;
	

	 Integer StaffEquipmentCommitBeanDelete(List<StaffEquipment> lDelete) ;
	 Integer getIncidentStaffForecCount(Integer agencyIncidentId);

 	 String getLockFlag(Integer incidentReportId);

 	 String getsfReportAgeIncParties(Integer agencyIncidentId, Integer staffId);
	
 	 int updAgencyIncRepStaffReport(IncidentStaffReport incidentStaffReport);

	ReferenceCodes getCountDownTime(String code);
	
	AgencyIncidentParties getLockReferenceTime(AgencyIncidentParties agencyIncidentParties);

	Integer updateLockFlag(IncidentStaffReport incidentStaffReport);

}
