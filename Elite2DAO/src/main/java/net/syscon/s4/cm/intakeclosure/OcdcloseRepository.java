package net.syscon.s4.cm.intakeclosure;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.legalorders.OffenderCommunityFiles;

/**
 * Interface OcdcloseRepository
 */
public interface OcdcloseRepository {
	Integer obeInsertOffenderBookingEvents(List<OffenderBookingEvent> object);

	List<AgencyLocations> agencyLocationRecordGroup(final Integer offenderBookId, final String caseloadId);

	List<ReferenceCodes> cgfklkpObeObeRefCodeF2(ReferenceCodes paramBean);

	List<AgencyLocations> cgfklkpObeObeToAgyLocF1(AgencyLocations paramBean);

	Integer obePreInsert(OffenderBookingEvent paramBean);

	List<OffenderBookingEvent> obeExecuteQuery(OffenderBookingEvent object);

	List<ReferenceCodes> cgfkchkObeObeRefCodeF2(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkObeDspDescriptionRecordGroup();

	Integer checkInstUpdateOffenderBookings(OffenderBookingEvent object);

	Integer checkInstUpdateOffenderBookingAgyLoc(OffenderBookingEvent object);

	Integer checkMultyCsldUpdateOffenderBookings(OffenderBookingEvent object);

	Integer checkMultyCsldUpdateOffenderBookingAgyLoc(OffenderBookingEvent object);

	OffenderBookingEvent getBokingBeginDate(VHeaderBlock paramBean);

	Integer checkExternalTransfer(OffenderBookingEvent paramBean);

	Integer updateOffenderBookings(OffenderBookingEvent obj);

	Integer updateOffenderComm(String vno, Long offenderId);

	Integer checkCaseloadAccessCountOne(OffenderBookingEvent paramBean);

	Integer checkCaseloadAccessCountTwo(OffenderBookingEvent paramBean);

	List<CaseloadAgencyLocations> checkCaseloadAccessAgyLocCur(OffenderBookingEvent paramBean);

	List<CaseloadAgencyLocations> checkCaseloadAccessAgyLocCurTwo(OffenderBookingEvent paramBean);

	CasePlans caseplanAndPaperfileCasePlanCur(OffenderBookingEvent paramBean);

	List<OffenderCommunityFile> caseplanAndPaperfileVsPaperCur(OffenderBookingEvent paramBean);

	Integer casePlanInsert(CasePlans returnObj);

	Integer casePlanUpdate(CasePlans returnObj);

	String vagyLocTo();

	String vProfileValue();

	Integer offenderCommunityFilesUpdate(OffenderCommunityFile object);

	Integer pimsFileTracking(OffenderCommunityFile offTrans);

	String getvProfileValue();

	String getvNo();

	String tagTerminationChkTasks(OffenderBookingEvent obj);

	Integer updateOffenderBookingsy(OffenderBookingEvent obj);

	OffenderBookings aluesOffenderBookings(Long offenderBookId);

	String ocdcloseTriggerDelete();

	List<OffenderCommunityFiles> offenderCommunityFiles(Long offenderBookId);

	Integer getRefCodes(List<String> codes);

	List<ReferenceCodes> getListOfReasonRefCode();

	Boolean isActiveOrderPresent(Integer offenderBookId);

	Boolean isActiveCourtReportPresent(Integer offenderBookId);

}
