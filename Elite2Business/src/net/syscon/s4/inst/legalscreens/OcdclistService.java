package net.syscon.s4.inst.legalscreens;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQuery;
import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQueryCommitBean;
import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;
import net.syscon.s4.inst.legalscreens.bean.VCourtEventscommitBean;
import net.syscon.s4.inst.schedules.bean.CourtEventsCommitBean;

/**
 * Interface OcdclistService
 */
public interface OcdclistService {
	List<VCourtEvents> ctlLstOnCheckDeleteMaster(VCourtEvents paramBean);

	List<ReferenceCodes> rgHearingRecordGroup();

	Integer ctlUnCommit(VCourtEventscommitBean commitBean);

	Integer ctlLstCommit(OcdclistCourtListQueryCommitBean commitBean);

	List<VCourtEvents> ctlUnExecuteQuery(VCourtEvents objVCourtEvents);

	List<AgencyLocations> rgAgyLocRecordGroup();

	List<OcdclistCourtListQuery> ctlLstExecuteQuery(OcdclistCourtListQuery object);

	Integer ctlLstCommitQuery(CourtEventsCommitBean commitBean);

}
