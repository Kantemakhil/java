package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;

public interface StaffAccessibleCaseloadsT1Repository {

	List<CaseloadAgencyLocations> gettingAgyLocId(String caseloadId);

	Integer checkRoleExist(String agyLocId, Integer staffId);

	void updateStaffLocationsRoles(String agyLocId, Integer staffId, String modifyUserId);

}
