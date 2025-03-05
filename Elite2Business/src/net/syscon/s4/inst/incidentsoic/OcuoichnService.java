package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNotices;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNoticesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.im.incidentsoic.beans.OicHearingsCommitBean;

/**
 * Interface OcuoichnService
 */
public interface OcuoichnService {
	List<StaffMembers> rgAgyIncpStaffIdRecordGroup(String caseloadId);

	List<ReferenceCodes> rgHearingTypeRecordGroup();

	List<AgencyInternalLocations> rgInternalLocationsRecordGroup(String caseloadId);

	List<OicHearings> oicHearSearchOicHearings(OicHearings objOicHearings);

	List<OicHearingNotices> oicHearNotiSearchOicHearingNotices(OicHearingNotices oicHearingNotices);

	List<Object> oicHearOnCheckDeleteMasteroicHearNotiCur(OicHearingNotices paramBean);

	Integer oicHearUpdateOicHearings(List<OicHearings> lstOicHearings);

	Integer oicHearInsertOicHearings(List<OicHearings> lstOicHearings);

	Object oicHearPreInsertgetEventIdCur(Dual paramBean);

	Integer oicHearDeleteOicHearings(List<OicHearings> lstOicHearings);

	Integer oicHearNotiInsertOicHearingNotices(List<OicHearingNotices> oicHearingNotices);

	Integer oicHearNotiUpdateOicHearingNotices(List<OicHearingNotices> oicHearingNotices);

	Integer oicHearNotiDeleteOicHearingNotices(List<OicHearingNotices> oicHearingNotices);

	Integer oicHearCommit(OicHearingsCommitBean commitBean);

	Integer oicHearNotiCommit(OicHearingNoticesCommitBean commitBean);

	Integer oicHearCheckScheduleConflict(OicHearings searchBean);
	
	String checkNonAssociations(OicHearingsCommitBean commitBean);

}
