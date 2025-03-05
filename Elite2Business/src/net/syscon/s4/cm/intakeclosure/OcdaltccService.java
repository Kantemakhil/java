package net.syscon.s4.cm.intakeclosure;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CommunityHeaderStatuses;
import net.syscon.s4.im.beans.OffenderBookingAgyLocsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OcdaltccService
 */
public interface OcdaltccService {

	List<OffenderBookingAgyLocs> offagyExecuteQuery(OffenderBookingAgyLocs obj);

	List<CommunityHeaderStatuses> offenderStatusRecordGroup();

	List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup();

	Integer offagyCommit(OffenderBookingAgyLocsCommitBean commitBean);

	List<AgencyLocations> cgfkOffagy1DspDescription22RecordGroup(Long offenderBookId);

	OffenderBookingEvent evntDate(OffenderBookingAgyLocs searchBean);
	
 List<OffenderBookingAgyLocs> offagy1ExecuteQuery(OffenderBookingAgyLocs obj);

}
