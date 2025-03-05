package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.im.beans.StgLocations;

/**
 * Interface OidstghlRepository
 */
public interface OidstghlRepository {

	List<StgLocations> stgLocationsExecuteQuery(StgLocations objStgLocations);

	List<ReferenceCodes> recCityRecordGroup();

	List<SecurityThreatGroups> stgPreInsert(SecurityThreatGroups paramBean);

	List<ReferenceCodes> recStateRecordGroup();

	List<StgLocations> stgOnCheckDeleteMaster(StgLocations paramBean);

	Integer stgInsertSecurityThreatGroups(List<SecurityThreatGroups> lstSecThrtGroups);

	Integer stgLocationsUpdateStgLocations(List<StgLocations> lstStgLocations);

	Long stgLocationsPreInsert(Long stgId);

	Integer stgUpdateSecurityThreatGroups(List<SecurityThreatGroups> lstSecThrtGroups);

	List<SecurityThreatGroups> stgExecuteQuery(SecurityThreatGroups lstSecThrtGroups);

	List<ReferenceCodes> stgLocationsPostQuery(ReferenceCodes paramBean);

	List<StaffMemberRoles> cgwhenNewFormInstance(String userName);

	Integer stgLocationsInsertStgLocations(List<StgLocations> lstStgLocations);

	List<ReferenceCodes> recCountryRecordGroup();

	Integer stgLocationsDeleteStgLocations(List<StgLocations> lstStgLocations);

	Integer stgDeleteSecurityThreatGroups(List<SecurityThreatGroups> lstSecThrtGroups);

	String getProfileValue();

}
