package net.syscon.s4.pkgs.tag_transport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.inst.transportation.beans.VAssignOffenderTrips;
import net.syscon.s4.inst.transportation.beans.VLocalTripOffenders;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;

public interface TagTransportRepository {

	Integer tagtransportGenerateSchedules(String routeName);

	Integer tagtransportInsert(ScheduledTrips ob, Date lSchDate, Integer routeLength);

	Long getvSegmentLength(final String routeName);

	Integer updateScheduledTrips(final String routeName, final String userName, final Long vSegmentLength);

	List<RouteStopDetails> getLocCur(final String routeName);

	Integer ifSegExistsCur(final String vFromAgyLoc, final String vToAgyLoc);

	Integer pAgencySegmentLengths(final String vFromAgyLoc, final String vToAgyLoc, final String userName);

	Integer offenderProposedMvmnts(Long pOffBkg, Long pMoveSeq);

	Long getSeqCur(Long pOffBkg, Long pMoveSeq);

	Long insertMvmntDetails(Long offenderBookId, Long movementSeq, Long detailSeq, String statusCode, String recordedBy,
			String appRsn, String txnStatus, String txnRsn);

	Integer nonAdmittedInmateMvmts(Long pInmId);

	Long getSeqCurOne(Long pInmId);

	Integer insertNadmMvmntDetails(Long pInmId, String pStatus, String pTxnStat, Date pRecdDt, String pAppRsn,
			String pTxnRsn, String pUser, Long pDetSeq);

	List<ScheduledTrips> schTripCur(String pTripCode, Date pDepDt);

	Long overnightCur(String pRoute);

	List<BigDecimal> mxStopCur(String pRoute);

	List<BigDecimal> mxNightCur(String pRoute);

	Integer scheduledTrips(String vRoute1, Date vCompDt, Long vSchId);

	String tripDetCur(Long pTripId);

	Long ifExMovExists(Long pEventId);

	Integer tagtransportGetRouteLen(String route);

	Date tagtransportCompTimeCur(Integer pRouteLen, Date pEstDepTime, Date pDepDate);

	Integer tagtransportOvernightCur(String route);

	RouteStopDetails tagtransportMxStopCur(String route);

	RouteStopDetails tagtransportMxNightCur(String route);

	Integer oidaotstUpdateOffenderIndSchedules(VLocalTripOffenders bean);

	Integer oidaotstUpdateOffenderCourtEvents(VLocalTripOffenders bean);

	Integer oidaotstremoveOffenderFromTrip(VLocalTripOffenders bean);

	Integer assignOffenderToTrip(VAssignOffenderTrips bean);

	Integer ScheduledTripsNowait(Integer scheduledTripId);

	Integer TotalOffenderIdScheduledId(Integer scheduledTripId, Integer offenderBookId);

	Integer UpdateOffenderIndSchedules(VAssignOffenderTrips bean);

	Integer UpdateOffenderCourtEvents(VAssignOffenderTrips bean);

	Integer TotalScheduledTripId(Integer scheduledTripId);

	Integer retrieveOidaotstCriteria(String fromDate, String toDate, String lFromAgyLocId, String lToAgyLocId);

	Integer tripscheckAnyNonAssocnExists(Integer rootOffenderId,Integer offenderBookId);

	Integer lRootOffenderIdCur(Integer offenderBookId);

	Integer offShedscheckAnyNonAssocnExists(List<VAssignOffenderTrips> offenderBookId);

	List<OffenderMovementDetails> statusInmCur(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	List<OffenderMovementDetails> maxStatusInmCur(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	OffenderMovementDetails statusNonInmCur();

	OffenderMovementDetails maxStatusNonInmCur();

	Integer getSeqCur(Integer pOffBkg);

	List<OffenderLocChngDtls> statusInmCurInstLoc(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	List<OffenderLocChngDtls> maxStatusInmCurInstLoc(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	Integer getSeqCurInst(Integer offenderBookId);

	String impStatusCur(Integer offenderBookId);

	Integer sanctionCur(Integer offenderBookId);

	Integer stgAffiCur(Integer offenderBookId);

	Integer getSeqCurr(Integer offenderBookId, Long movementSeq);

	Long getSeqCurr(Long nonAdmInmateId);

	String ifRoleAssigned(String userId, String roleNm);

	String getImprisonmentStatus(Long offenderBookIdd);

	Integer nonAssoCur(Long offenderBookId, String agyLocId);

	Integer nonAssoNsCur(Long offenderBookId, String agyLocId);

	Date getSchDate(String day, Date fromdate,Integer week);

	Integer insertLocChngDtls(OffenderLocChngDtls insertBean);

	Integer getSeqCur(Integer pOffBkg, Integer pLocSeq);

	Integer insertLocChngDtls(Long pOffBkg, Integer pLocSeq, Integer pDetSeq, String pStatus, String recordedBy,
			Date pRecdDt, String pAppRsn, String pTxnStat, String pTxnRsn);

	String ifIntrNonAssoExists(Integer offenderBookId, String currAgyId, Integer toLivingUnitId);
	
	Integer insertOffMovDtls(OffenderMovementDetails insertBean);
	
	Integer updateoffPrpMvmnts(OffenderMovementDetails remove);
	
	List<VLocalTripOffenders> existsNonAsso(Long scheduledTripId, Long offenderBookId);
	
	String ifNonAssocBetween(Long pOffBkg1,Long pOffBkg2);
	
	Integer existsNonAssoCount(Long offenderBookId,Long scheduledTripId );

	Integer insertMvmntDetails(OffenderMovementDetails insertBean);

Integer insertNadmMvmntDetails(OffenderMovementDetails insertBean);
	

}
