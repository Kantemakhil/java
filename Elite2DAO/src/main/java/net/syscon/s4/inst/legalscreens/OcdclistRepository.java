package net.syscon.s4.inst.legalscreens;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQuery;
import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

/**
 * Interface OcdclistRepository
 * 
 */
public interface OcdclistRepository {
	Integer ctlLstUpdateOcdclistCourtListQuery(List<OcdclistCourtListQuery> object);

	SystemProfiles getProfileValue(SystemProfiles paramBean);

	List<ReferenceCodes> rgHearingRecordGroup();

	List<VCourtEvents> ctlLstOnCheckDeleteMaster(VCourtEvents paramBean);

	List<VCourtEvents> ctlUnExecuteQuery(VCourtEvents objVCourtEvents);

	List<AgencyLocations> rgAgyLocRecordGroup();

	List<OcdclistCourtListQuery> ctlLstExecuteQuery(OcdclistCourtListQuery object);

	Integer ctlLstInsertOcdclistCourtListQuery(List<OcdclistCourtListQuery> object);

	String courtEventExistsFunction(OcdclistCourtListQuery obj);

	Integer ctlLstUpdateCourtListQuery(List<CourtEvents> updateList);

}
