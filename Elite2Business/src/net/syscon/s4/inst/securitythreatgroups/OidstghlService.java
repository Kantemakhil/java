package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.im.beans.SecurityThreatGroupsCommitBean;
import net.syscon.s4.im.beans.StgLocations;
import net.syscon.s4.im.beans.StgLocationsCommitBean;

/**
 * Interface OidstghlService
 */
public interface OidstghlService {
	List<StgLocations> stgLocationsExecuteQuery(StgLocations objStgLocations);

	List<ReferenceCodes> recCityRecordGroup();

	List<SecurityThreatGroups> stgExecuteQuery(SecurityThreatGroups objDao);

	List<StgLocations> stgOnCheckDeleteMaster(StgLocations paramBean);

	List<ReferenceCodes> stgLocationsPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> recStateRecordGroup();

	List<ReferenceCodes> recCountryRecordGroup();

	List<SecurityThreatGroups> stgPreInsert(SecurityThreatGroups paramBean);

	Integer stgCommit(SecurityThreatGroupsCommitBean commitBean);

	Integer stgLocationsCommit(StgLocationsCommitBean commitBean);

	List<StaffMemberRoles> cgwhenNewFormInstance(String userName);

}
