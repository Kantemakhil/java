package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderDetail;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNotices;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;

/**
 * Interface OcuoichnRepository
 */
public interface OcuoichnRepository {
	List<StaffMembers> rgAgyIncpStaffIdRecordGroup(String caseLoadId);

	List<ReferenceCodes> rgHearingTypeRecordGroup();

	List<AgencyInternalLocations> rgInternalLocationsRecordGroup(String caseloadId);

	List<OicHearings> oicHearSearchOicHearings(OicHearings objOicHearings);

	List<Object> oicHearOnCheckDeleteMasteroicHearNotiCur(OicHearingNotices paramBean);

	List<OicHearingNotices> oicHearNotiSearchOicHearingNotices(OicHearingNotices obj);

	Integer oicHearUpdateOicHearings(List<OicHearings> lstOicHearings);

	Integer oicHearInsertOicHearings(List<OicHearings> lstOicHearings);

	Object oicHearPreInsertgetEventIdCur(Dual paramBean);

	Integer oicHearDeleteOicHearings(List<OicHearings> lstOicHearings);

	Integer oicHearNotiInsertOicHearingNotices(List<OicHearingNotices> obj);

	Integer oicHearNotiUpdateOicHearingNotices(List<OicHearingNotices> obj);

	Integer oicHearNotiDeleteOicHearingNotices(List<OicHearingNotices> obj);

	Integer getMaxOicHearId();

	Integer getMaxOicNoticeSeq();

	Integer oicHearCheckScheduleConflict(OicHearings searchBean);
	
	Integer getOffenderBookId(Integer oicIncidentId); 
	
	Integer getIncidentId(Integer offenderBookId); 
	
	List<OicHearings> getNonAssocationVisitSchedule(Integer offenderBookId,OicHearings oicHearings );

	List<OffenderDetail> getNonAssocationVisitScheduleOffenderDetails(Integer oicIncidentId);
	
	List<OffenderNaDetails> getNonAssocationOffenderList(Integer offenderBookId,Integer internalLocation);
	
}
