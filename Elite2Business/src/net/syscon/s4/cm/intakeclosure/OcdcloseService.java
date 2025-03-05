package net.syscon.s4.cm.intakeclosure;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookingEventCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OcdcloseService
 */
public interface OcdcloseService {
	Integer obePreInsert(OffenderBookingEvent paramBean);

	List<AgencyLocations> agencyLocationRecordGroup(Integer offenderBookId,String caseloadId);

	List<ReferenceCodes> cgfkchkObeObeRefCodeF2(ReferenceCodes paramBean);

	Integer obeCommit(OffenderBookingEventCommitBean commitBean,String authorization);

	List<ReferenceCodes> cgfklkpObeObeRefCodeF2(ReferenceCodes paramBean);

	List<OffenderBookingEvent> obeExecuteQuery(OffenderBookingEvent object);

	List<ReferenceCodes> cgfkObeDspDescriptionRecordGroup();

	Integer checkInstitution(OffenderBookingEvent searchBean);

	Integer checkMultyCaseload(OffenderBookingEvent searchBean);

	OffenderBookingEvent getBokingBeginDate(VHeaderBlock object);

	Integer obePreInsertTwo(OffenderBookingEvent searchBean);

	Integer tagTerminationChkTasks(OffenderBookingEvent searchBean);
	
	Integer updateSentStatus(OffenderBookingEvent obj);

	Boolean isActiveOrderPresent(Integer offenderBookId);

}
