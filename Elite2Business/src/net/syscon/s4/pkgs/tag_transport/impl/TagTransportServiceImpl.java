package net.syscon.s4.pkgs.tag_transport.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.inst.transportation.beans.VAssignOffenderTrips;
import net.syscon.s4.inst.transportation.beans.VLocalTripOffenders;
import net.syscon.s4.inst.transportation.maintenance.OffenderLocChngDtls;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.RouteStopDetails;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.pkgs.tag_transport.TagTransportRepository;
import net.syscon.s4.pkgs.tag_transport.TagTransportService;

@Service
public class TagTransportServiceImpl implements TagTransportService {

	private static Logger logger = LogManager.getLogger(TagTransportServiceImpl.class.getName());
	@Autowired
	private TagTransportRepository transportRepository;

	private static String gStartTime;
	private static String gEndTime;
	private static String gType;
	private static String gReason;
	private static String gGender;
	private static String gOffDisplay;
	private static String gOffender;

	@Override
	public Integer updateScheduledTrips(final String routeName, final String userName) {
		int returnval = 0;
		Long vSegmentLength;
		try {
			vSegmentLength = transportRepository.getvSegmentLength(routeName);
			returnval = transportRepository.updateScheduledTrips(routeName, userName, vSegmentLength);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " updateScheduledTrips() ", e);
		}
		return returnval;
	}

	@Override
	public Integer pAgencySegmentLengths(final String routeName, final String userName) {
		Integer insertcount = null;
		BigDecimal vLegId;
		BigDecimal vLegSeq;
		String vAgyLoc;
		String vFromAgyLoc = null;
		String vToAgyLoc = null;
		Integer vCount = null;
		List<RouteStopDetails> returnList = new ArrayList<RouteStopDetails>();
		try {
			returnList = transportRepository.getLocCur(routeName);
			for (RouteStopDetails rs : returnList) {
				vLegId = rs.getLegId();
				vLegSeq = rs.getLegSeq();
				vAgyLoc = rs.getAgyLocId();
				if (vToAgyLoc == null) {
					vToAgyLoc = vAgyLoc;
					vFromAgyLoc = vAgyLoc;
				} else {
					vFromAgyLoc = vToAgyLoc;
					vToAgyLoc = vAgyLoc;
				}
				vCount = transportRepository.ifSegExistsCur(vFromAgyLoc, vToAgyLoc);

				if (vCount == 0) {
					insertcount = transportRepository.pAgencySegmentLengths(vFromAgyLoc, vToAgyLoc, userName);

				}
			}
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " pAgencySegmentLengths() ", e);
		}

		return insertcount;
	}

	@Override
	public Integer removeFromTrip(Long pTripId, Long pOffBkg, Long pMoveSeq, Long pInmId, String pStatCode,
			String pTxnCode, String pAction, String recordBy) {
		Integer res = 0;
		if (pOffBkg != null) {
			res = transportRepository.offenderProposedMvmnts(pOffBkg, pMoveSeq);
			if (pAction.equals("VERIFY") || pAction.equals("APPROVE")) {
				res = this.insertMvmntDetails(pOffBkg, pMoveSeq, pStatCode, pTxnCode, null, ".", recordBy, null);
			} else {
				res = transportRepository.nonAdmittedInmateMvmts(pInmId);
				if (pAction.equals("VERIFY") || pAction.equals("APPROVE")) {
					res = this.insertNadmMvmntDetails(pTripId, pStatCode, pTxnCode, null, ".", recordBy, null);
				}
			}
		}
		return res;
	}

	@Override
	public Integer insertMvmntDetails(Long pOffBkg, Long pMoveSeq, String pStatus, String pTxnStat, String pAppRsn,
			String pTxnRsn, String pUser, Long pDetSeq) {
		pDetSeq = this.getSeqCur(pOffBkg, pMoveSeq);
		pDetSeq = transportRepository.insertMvmntDetails(pOffBkg, pMoveSeq, pDetSeq, pStatus, pUser, pAppRsn, pTxnStat,
				pTxnRsn);
		return null;
	}

	@Override
	public Long getSeqCur(Long pOffBkg, Long pMoveSeq) {
		return transportRepository.getSeqCur(pOffBkg, pMoveSeq);
	}

	@Override
	public Integer insertNadmMvmntDetails(Long pInmId, String pStatus, String pTxnStat, String pAppRsn, String pTxnRsn,
			String pUser, Long pDetSeq) {
		pDetSeq = this.getSeqCurOne(pInmId);

		return transportRepository.insertNadmMvmntDetails(pInmId, pStatus, pTxnStat, null, pAppRsn, pTxnRsn, pUser,
				pDetSeq);
	}

	@Override
	public Long getSeqCurOne(Long pInmId) {
		return transportRepository.getSeqCurOne(pInmId);
	}

	@Override
	public Integer pCancelTrip(String pTripCode, Date pDepDt, Long pSchId, String pRoute) {
		Date vDepDt = null;
		Date vCompDt = null;
		Long vSchId = null;
		String vRoute = null, vRoute1 = pRoute;
		List<ScheduledTrips> schTripCurList = transportRepository.schTripCur(pTripCode, pDepDt);
		for (ScheduledTrips item : schTripCurList) {
			vSchId = item.getScheduledTripId();
			vDepDt = item.getDepartureDate();
			vRoute = item.getRouteName();
			vCompDt = this.getDayTravel(vRoute1, vDepDt);
			transportRepository.scheduledTrips(vRoute1, vCompDt, vSchId);
			vRoute1 = vRoute;
		}
		return null;
	}

	@Override
	public Date getDayTravel(String pRoute, Date pDepDt) {
		Date pCompDt = null;
		Long vCount = 0l, vStopLeg = null, vStopSeq = null, vNightLeg = null, vNightSeq = null;
		vCount = transportRepository.overnightCur(pRoute);
		List<BigDecimal> mxStopCurList = transportRepository.mxStopCur(pRoute);
		for (BigDecimal item : mxStopCurList) {
			vStopLeg = item.longValue();
			vStopSeq = item.longValue();
		}
		List<BigDecimal> mxNightCurList = transportRepository.mxNightCur(pRoute);
		for (BigDecimal item : mxNightCurList) {
			vStopLeg = item.longValue();
			vStopSeq = item.longValue();
		}
		if (vStopLeg == vNightLeg && vStopSeq == vNightSeq) {
			LocalDateTime today = pDepDt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			today = today.plusDays(vCount - 1);
			Date pDepDt1 = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());
			pCompDt = pDepDt1;
		} else {
			LocalDateTime today = pDepDt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			today = today.plusDays(vCount);
			Date pDepDt1 = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());
			pCompDt = pDepDt1;
		}
		return pCompDt;

	}

	@Override
	public String getTripStatus(Long pTripId) {
		return transportRepository.tripDetCur(pTripId);
	}

	private Integer getJulianDayDifference(Date paramDate) {
		LocalDateTime date = paramDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		long dayMs = Duration.ofDays(1).toMillis();
		LocalTime timeOfDay = date.toLocalTime();
		long result = date.getLong(JulianFields.JULIAN_DAY);
		result += timeOfDay.get(ChronoField.MILLI_OF_DAY) / dayMs - 0.5;
		return (int) result;
	}

	@Override
	public Long ifExMovExists(Long pEventId) {
		return transportRepository.ifExMovExists(pEventId);
	}

	String getDayNumber(int day) {
		String dayName = "";
		switch (day) {
		case 0:
			dayName = "SUN";
			break;
		case 1:
			dayName = "MON";
			break;
		case 2:
			dayName = "TUE";
			break;
		case 3:
			dayName = "WED";
			break;
		case 4:
			dayName = "THU";
			break;
		case 5:
			dayName = "FRI";
			break;
		case 6:
			dayName = "SAT";
			break;
		}
		return dayName;
	}

	@Override
	public Integer generateSchedules(String tripCode, String routeName, Long weekNo, String days, Long vMaxWeek,
			Date departureTime, Date fromDate, Date toDate, String userId) {
		Long lWeekNo = 0L;
		Date lSchDate = null;
		String lDay = null;
		Date l_idx = null;
		Integer routeLength = transportRepository.tagtransportGenerateSchedules(routeName);
		Integer count = 0;

		lDay = (days.toUpperCase()).substring(0, 3);
		lWeekNo = 0l;
		if (fromDate != null && toDate != null) {

			getJulianDayDifference(fromDate);
			getJulianDayDifference(toDate);
			lSchDate = transportRepository.getSchDate(lDay, fromDate, weekNo.intValue());

			while (lSchDate != null && lSchDate.compareTo(toDate) <= 0) {
				// insert call
				ScheduledTrips ob = new ScheduledTrips();
				ob.setTripCode(tripCode);
				ob.setDepartureDate(departureTime);
				ob.setRouteName(routeName);
				ob.setCreateUserId(userId);
				count = transportRepository.tagtransportInsert(ob, lSchDate, routeLength);
				LocalDateTime today = lSchDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				today = today.plusDays(vMaxWeek * 7);
				lSchDate = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());
			}

		}
		return count;

	}

	public String getWeekDay(int day) {
		if (day == 1) {
			return "SUN";
		} else if (day == 2) {
			return "MON";
		} else if (day == 3) {
			return "TUE";
		} else if (day == 4) {
			return "WED";
		} else if (day == 5) {
			return "THU";
		} else if (day == 6) {
			return "FRI";
		} else {
			return "SAT";
		}
	}

	@Override
	public Integer generateSchedules(ScheduledTrips bean) {
		return null;
	}

	@Override
	public Date getRouteLen(String route, Date pEstDepTime, Date pDepDate) {
		Integer vRouteLen = 0;
		Date vEstCompTime = null;
		vRouteLen = transportRepository.tagtransportGetRouteLen(route);
		vEstCompTime = transportRepository.tagtransportCompTimeCur(vRouteLen, pEstDepTime, pDepDate);
		return vEstCompTime;
	}

	@Override
	public Date getDayTravel(String routeName, Date departureDate, Date completionDate) {

		Integer overnightCur = 0;
		RouteStopDetails mxStopCur = new RouteStopDetails();
		RouteStopDetails mxNightCur = new RouteStopDetails();
		overnightCur = transportRepository.tagtransportOvernightCur(routeName);
		overnightCur = (overnightCur == null || overnightCur == 0) ? 0 : overnightCur;
		mxStopCur = transportRepository.tagtransportMxStopCur(routeName);
		mxNightCur = transportRepository.tagtransportMxNightCur(routeName);
		if (mxStopCur != null && mxNightCur != null && mxStopCur.getLegId() == mxNightCur.getLegId()) {
			if (departureDate != null) {
				LocalDateTime today = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				today.plusDays(overnightCur - 1);
				completionDate = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());
			}
		} else {
			LocalDateTime today = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			today.plusDays(overnightCur);
			completionDate = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());
		}
		return completionDate;
	}

	@Override
	public void assignOidaotstGlobals(String pStartTime, String pEndTime, String pType, String pReason, String pGender,
			String pOffDisplay, String pOffender) {
		gStartTime = pStartTime;
		gEndTime = pEndTime;
		gType = pType;
		gReason = pReason;
		gGender = pGender;
		gOffDisplay = pOffDisplay;
		gOffender = pOffender;
	}

	public Integer removeOffenderFromTrip(VLocalTripOffenders bean) {
		Integer updateIndSchedules = 0;
		Integer courtEvents = 0;
		Integer removeOffenderFromTrip = 0;
		if (bean.getRecordSource() == "SCH") {
			try {
				updateIndSchedules = transportRepository.oidaotstUpdateOffenderIndSchedules(bean);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " removeOffenderFromTrip() ", e);
			}
		} else if (bean.getRecordSource() == "COURT") {
			try {
				courtEvents = transportRepository.oidaotstUpdateOffenderCourtEvents(bean);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " removeOffenderFromTrip() ", e);
			}
		} else if (bean.getRecordSource() == null) {
			try {
				removeOffenderFromTrip = transportRepository.oidaotstUpdateOffenderIndSchedules(bean);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " removeOffenderFromTrip() ", e);
			}
		}
		if (updateIndSchedules == 1 && courtEvents == 1 && removeOffenderFromTrip == 1) {
			return 1;
		}
		return 0;
	}

	public Integer assignOffenderToTrip(VAssignOffenderTrips bean) {
		Integer assignOffenderToTrip = 0;
		Integer updateIndSchedules = 0;
		Integer courtEvents = 0;
		String lSqlStatement;
		Integer lDummy;
		Integer lCursorId;
		Integer lIdx;
		Integer lOccupancy;
		Integer lNoVacancyFlag = null;

		try {
			assignOffenderToTrip = transportRepository.ScheduledTripsNowait(bean.getScheduledTripId());
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " assignOffenderToTrip() ", e);
			throw e;
		}

		try {
			assignOffenderToTrip = transportRepository.TotalScheduledTripId(bean.getScheduledTripId());
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " assignOffenderToTrip() ", e);
		}
		try {
			assignOffenderToTrip = transportRepository.TotalOffenderIdScheduledId(bean.getScheduledTripId(),
					bean.getOffenderBookId());
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " assignOffenderToTrip() ", e);
		}
		try {
			assignOffenderToTrip = transportRepository.assignOffenderToTrip(bean);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " assignOffenderToTrip() ", e);
		}

		if (bean.getRecordSource() == "SCH") {
			try {
				updateIndSchedules = transportRepository.UpdateOffenderIndSchedules(bean);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " assignOffenderToTrip() ", e);
			}
		} else if (bean.getRecordSource() == "COURT") {
			try {
				courtEvents = transportRepository.UpdateOffenderCourtEvents(bean);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " assignOffenderToTrip() ", e);
			}
		} else if (bean.getRecordSource() == null) {
			try {
				assignOffenderToTrip = transportRepository.UpdateOffenderIndSchedules(bean);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " assignOffenderToTrip() ", e);
			}
		}
		return assignOffenderToTrip;
	}

	public Integer retrieveOidaotstCriteria(String fromDate, String toDate, String lFromAgyLocId, String lToAgyLocId) {
		Integer retrieveOidaotstCriteria = 0;
		try {
			retrieveOidaotstCriteria = transportRepository.retrieveOidaotstCriteria(fromDate, toDate, lFromAgyLocId,
					lToAgyLocId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " retrieveOidaotstCriteria() ", e);
		}
		return retrieveOidaotstCriteria;
	}

	public Boolean tripscheckAnyNonAssocnExists(VLocalTripOffenders offenderBookId) {
		boolean checkAnyNonAssocnExists = false;
		Integer lNonAssocnFlag = null;

		Integer rootOffenderId = 0;

		lNonAssocnFlag = transportRepository.tripscheckAnyNonAssocnExists(rootOffenderId,
				offenderBookId.getOffenderBookId());

		rootOffenderId = transportRepository.lRootOffenderIdCur(offenderBookId.getOffenderBookId());

		if (lNonAssocnFlag == 1) {
			checkAnyNonAssocnExists = true;
		} else {
			checkAnyNonAssocnExists = false;
		}

		return checkAnyNonAssocnExists;

	}

	public Boolean offShedscheckAnyNonAssocnExists(List<VAssignOffenderTrips> offenderBookId) {
		boolean checkAnyNonAssocnExists = false;
		Integer lNonAssocnFlag = null;
		Integer lRootOffenderIdCur = 0;
		try {
			lNonAssocnFlag = transportRepository.offShedscheckAnyNonAssocnExists(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " offShedscheckAnyNonAssocnExists() ",
					e);
		}

		if (lNonAssocnFlag == 1) {
			checkAnyNonAssocnExists = true;
		} else {
			checkAnyNonAssocnExists = false;
		}
		lRootOffenderIdCur = transportRepository.lRootOffenderIdCur(lRootOffenderIdCur);

		return checkAnyNonAssocnExists;

	}

	@Override
	public List<OffenderMovementDetails> latestStatuses(String pChoice, Integer pOffBkg, Integer pMoveSeq) {
		List<OffenderMovementDetails> returnListObj = new ArrayList<OffenderMovementDetails>();

		if (pOffBkg != null) {
			if ((ApplicationConstants.NEW.equalsIgnoreCase(pChoice))
					|| (ApplicationConstants.APP.equalsIgnoreCase(pChoice))
					|| (ApplicationConstants.TXN.equalsIgnoreCase(pChoice))) {

				returnListObj = transportRepository.statusInmCur(pChoice, pOffBkg, pMoveSeq);
			} else {

				returnListObj = transportRepository.maxStatusInmCur(pChoice, pOffBkg, pMoveSeq);
			}
		}
		return returnListObj;

	}

	@Override
	public Integer getMovementSeq(Integer pOffBkg) {
		Integer vMoveSeq = null;
		try {
			vMoveSeq = transportRepository.getSeqCur(pOffBkg);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getMovementSeq() ", e);
		}
		return vMoveSeq;
	}

	@Override
	public Integer getLocationSeq(Integer offenderBookId) {
		Integer locSeqFlag = 0;
		try {
			locSeqFlag = transportRepository.getSeqCurInst(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getLocationSeq() ", e);
		}
		return locSeqFlag;
	}

	@Override
	public List<OffenderLocChngDtls> latestStatusesIntlocs(String pChoice, Integer pOffBkg, Integer pLocSeq) {
		List<OffenderLocChngDtls> returnList = new ArrayList<>();
		try {
			if (pChoice != null) {
				if ((pChoice.equalsIgnoreCase("NEW")) || (pChoice.equalsIgnoreCase("APP"))
						|| (pChoice.equalsIgnoreCase("TXN"))) {
					returnList = transportRepository.statusInmCurInstLoc(pChoice, pOffBkg, pLocSeq);
				} else {
					returnList = transportRepository.maxStatusInmCurInstLoc(pChoice, pOffBkg, pLocSeq);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " latestStatusesIntlocs() ", e);
		}
		return returnList;
	}

	@Override
	public String getImprisonmentStatus(Integer offenderBookId) {
		String vImpStatus = null;
		try {
			vImpStatus = transportRepository.impStatusCur(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getImprisonmentStatus() ", e);
		}
		return vImpStatus;
	}

	@Override
	public Integer ifSanctionExists(Integer offenderBookId) {
		Integer vCount = null;
		try {
			vCount = transportRepository.sanctionCur(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " ifSanctionExists() ", e);
		}
		if (vCount > 0) {
			return 1;
		}
		return vCount;
	}

	@Override
	public Integer getStgAffiliation(Integer offenderBookId) {
		Integer vDescp = null;
		try {
			vDescp = transportRepository.stgAffiCur(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " getStgAffiliation() ", e);
		}
		return vDescp;
	}

	@Override
	public String ifRoleAssigned(String userId, String roleNm) {
		return transportRepository.ifRoleAssigned(userId, roleNm);
	}

	@Override
	public String getImprisonmentStatus(Long offenderBookId) {
		String imprismntStatus = null;

		imprismntStatus = transportRepository.getImprisonmentStatus(offenderBookId);

		return imprismntStatus;
	}

	@Override
	public String ifNonAssociationExists(Long offenderBookId, String agyLocId) {
		String nonAssExits = null;
		Integer vCount = 0;
		vCount = transportRepository.nonAssoCur(offenderBookId, agyLocId);
		if (vCount > 0) {
			nonAssExits = "Y";
			return nonAssExits;
		} else {
			vCount = transportRepository.nonAssoNsCur(offenderBookId, agyLocId);
			if (vCount > 0) {
				nonAssExits = "Y";
				return nonAssExits;
			}
		}
		nonAssExits = "N";
		return nonAssExits;
	}

	@Override
	public Integer insertLocChngDtls(OffenderLocChngDtls insertBean) {
		Integer detSeq = transportRepository.getSeqCur(insertBean.getOffenderBookId(), insertBean.getLocationSeq());
		if (detSeq != null) {
			insertBean.setDetailSeq(detSeq);
		}
		return transportRepository.insertLocChngDtls(insertBean);
	}

	@Override
	public Integer insertLocChngDtls(Long pOffBkg, Integer pLocSeq, String pStatus, String pTxnStat, Date pRecdDt,
			String pAppRsn, String pTxnRsn, Integer pDetSeq) {
		pDetSeq = transportRepository.getSeqCur(pOffBkg.intValue(), pLocSeq);
		transportRepository.insertLocChngDtls(pOffBkg, pLocSeq, pDetSeq, pStatus, null, pRecdDt, pAppRsn, pTxnStat,
				pTxnRsn);
		return pDetSeq;
	}

	@Override
	public String ifIntrNonAssoExists(Integer offenderBookId, String currAgyId, Integer toLivingUnitId) {
		return transportRepository.ifIntrNonAssoExists(offenderBookId, currAgyId, toLivingUnitId);

	}

	@Override
	public Integer insertOffMovDtls(OffenderMovementDetails insertBean) {
		Long detSeq = transportRepository.getSeqCur(insertBean.getOffenderBookId(), insertBean.getMovementSeq());
		if (detSeq != null) {
			insertBean.setDetailSeq(detSeq);
		}
		return transportRepository.insertOffMovDtls(insertBean);

	}

	@Override
	public String nonAssociationExists(Long scheduledTripId, Long offenderBookId) {
		String lExistsFlag = null;
		Integer lStgCount = 0;
		List<VLocalTripOffenders> excitNonAssoList = transportRepository.existsNonAsso(scheduledTripId, offenderBookId);
		for (VLocalTripOffenders lIdx : excitNonAssoList) {
			Long offfbookId = lIdx.getOffenderBookId().longValue();
			lExistsFlag = transportRepository.ifNonAssocBetween(offenderBookId, offfbookId);
			if (lExistsFlag.equals("Y")) {
				break;
			}
		}
		if (lExistsFlag != null && lExistsFlag.equals("N")) {
			lStgCount = transportRepository.existsNonAssoCount(offenderBookId, scheduledTripId);
			if (lStgCount > 0) {
				lExistsFlag = "Y";
			}
		}
		return lExistsFlag;
	}

	@Override
	public Integer insertMvmntDetails(OffenderMovementDetails insertBean) {
		Long detSeq = transportRepository.getSeqCur(insertBean.getOffenderBookId(), insertBean.getMovementSeq());
		if (detSeq != null) {
			insertBean.setDetailSeq(detSeq);
		}
		return transportRepository.insertMvmntDetails(insertBean);
	}

	@Override
	public Integer insertNadmMvmntDetails(OffenderMovementDetails insertBean) {
		Long detSeq = transportRepository.getSeqCurr(insertBean.getNonAdmInmateId());
		if (detSeq != null) {
			insertBean.setDetailSeq(detSeq);

		}
		return transportRepository.insertNadmMvmntDetails(insertBean);
	}
}
