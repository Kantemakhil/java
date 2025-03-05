package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReportCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OidstfrpCommonCommitBean;
import net.syscon.s4.im.incidentsoic.beans.StaffEquipment;
import net.syscon.s4.im.incidentsoic.beans.StaffEquipmentCommitBean;
import net.syscon.s4.im.incidentsoic.beans.StaffForce;
import net.syscon.s4.im.incidentsoic.beans.StaffForceCommitBean;

public interface OidstfrpService {

	Integer staffReportCommitData(IncidentStaffReportCommitBean commitBean);

	Integer staffReportCommitDataInsert(List<IncidentStaffReport> commitBean);

	Integer staffReportCommitDataUpdate(List<IncidentStaffReport> commitBean);

	Integer staffReportCommitDataDelete(List<IncidentStaffReport> commitBean);

	List<IncidentStaffReport> staffReportExecuteQuery(IncidentStaffReport commitBean);

	List<StaffForce> staffforceExecuteQuery(StaffForce objSearchDao);

	List<StaffEquipment> staffEquipementExecuteQuery(StaffEquipment objSearchDao);

	Integer staffforceCommitData(StaffForceCommitBean commitBean);

	Integer staffEquipementCommitData(StaffEquipmentCommitBean commitBean);

	Integer StaffForceCommitBeanInsert(List<StaffForce> lInsert);

	Integer StaffForceCommitBeanUpdate(List<StaffForce> lUpdate);

	Integer StaffForceCommitBeanDelete(List<StaffForce> lDelete);

	Integer StaffEquipmentCommitBeanInsert(List<StaffEquipment> lInsert);

	Integer StaffEquipmentCommitBeanUpdate(List<StaffEquipment> lUpdate);

	Integer StaffEquipmentCommitBeanDelete(List<StaffEquipment> lDelete);

	Integer staffReportCommonSave(OidstfrpCommonCommitBean commitBean);

	ReferenceCodes getCountDown(AgencyIncidentParties agencyIncidentParties);

	Integer updateSaveFlag(IncidentStaffReport incidentStaffReport);

}
