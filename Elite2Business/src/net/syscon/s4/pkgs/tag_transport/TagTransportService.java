package net.syscon.s4.pkgs.tag_transport;

import java.util.Date;
import java.util.List;

import net.syscon.s4.inst.transportation.beans.VAssignOffenderTrips;
import net.syscon.s4.inst.transportation.beans.VLocalTripOffenders;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;

public interface TagTransportService {

	Integer generateSchedules(ScheduledTrips bean);

	Integer generateSchedules(String tripCode, String routeName, Long weekNo, String days, Long vMaxWeek,
			Date departureTime, Date fromDate, Date toDate, String userId);

	Date getRouteLen(String route, Date pEstDepTime, Date pDepDate);
	

	Date getDayTravel(String routeName, Date departureDate, Date completionDate);

	Integer insertMvmntDetails(Long pOffBkg, Long pMoveSeq, String pStatus, String pTxnStat, String pAppRsn,
			String pTxnRsn, String pUser, Long pDetSeq);

	Long getSeqCur(Long pOffBkg, Long pMoveSeq);

	Long getSeqCurOne(Long pInmId);

	
	
	Integer insertNadmMvmntDetails(Long pInmId, String pStatus, String pTxnStat, String pAppRsn, String pTxnRsn,
			String pUser, Long pDetSeq);

	Integer pCancelTrip(String pTripCode, Date pDepDt, Long pSchId, String pRoute);

	Date getDayTravel(String pRoute, Date pDepDt);

	String getTripStatus(Long pTripId);

	Long ifExMovExists(Long pEventId);

	Integer updateScheduledTrips(String routeName, String userName);

	Integer removeFromTrip(Long pTripId, Long pOffBkg, Long pMoveSeq, Long pInmId, String pStatCode, String pTxnCode,
			String pAction,String recordBy);

	Integer pAgencySegmentLengths(String routeName, String userName);

	void assignOidaotstGlobals(String pStartTime, String pEndTime, String pType, String pReason, String pGender,
			String pOffDisplay, String pOffender);

	Integer removeOffenderFromTrip(VLocalTripOffenders bean);

	Integer assignOffenderToTrip(VAssignOffenderTrips bean);

	Integer retrieveOidaotstCriteria(String fromDate, String toDate, String lFromAgyLocId, String lToAgyLocId);

	Boolean tripscheckAnyNonAssocnExists(VLocalTripOffenders offenderBookId);

	Boolean offShedscheckAnyNonAssocnExists(List<VAssignOffenderTrips> offenderBookId);

	List<OffenderMovementDetails>  latestStatuses(String pChoice, Integer pOffBkg, Integer pMoveSeq);

	Integer getMovementSeq(Integer pOffBkg);

	Integer getLocationSeq(Integer offenderBookId);

	List<OffenderLocChngDtls> latestStatusesIntlocs(String pChoice, Integer pOffBkg, Integer pLocSeq);

	String getImprisonmentStatus(Integer offenderBookId);

	Integer ifSanctionExists(Integer offenderBookId);

	Integer getStgAffiliation(Integer offenderBookId);

	String ifRoleAssigned(String userId, String roleNm);

	String getImprisonmentStatus(Long offenderBookId);

	String ifNonAssociationExists(Long offenderBookId, String agyLocId);

	Integer insertLocChngDtls(OffenderLocChngDtls insertBean);
	
	Integer insertLocChngDtls(Long pOffBkg, Integer pLocSeq, String pStatus, String pTxnStat, Date pRecdDt, String pAppRsn, String pTxnRsn, Integer pDetSeq );

	String ifIntrNonAssoExists(Integer offenderBookId, String currAgyId, Integer toLivingUnitId);
	
	Integer insertOffMovDtls(OffenderMovementDetails insertBean);
	
	String nonAssociationExists(Long scheduledTripId,Long offenderBookId);
	
	Integer insertMvmntDetails(OffenderMovementDetails insertBean);
	
	Integer insertNadmMvmntDetails(OffenderMovementDetails insertBean);
	

}
