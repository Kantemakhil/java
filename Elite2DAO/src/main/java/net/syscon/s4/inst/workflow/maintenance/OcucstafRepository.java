package net.syscon.s4.inst.workflow.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.legals.beans.StaffDetails;

/**
 * Interface OcucstafRepository
 */
public interface OcucstafRepository {
	List<ReferenceCodes> rgPositionRecordGroup();

	List<Areas> rgAreaRecordGroup(String areaType);

	List<ReferenceCodes> rgRoleRecordGroup();

	List<ReferenceCodes> rgStaffStatusRecordGroup();

	List<AgencyLocations> rgLocationRecordGroup(String areaCode, String areaType);

	List<ReferenceCodes> rgAgencyTypeRecordGroup();

	List<StaffDetails> staffDetailsExecuteQuery(StaffDetails object);

}
