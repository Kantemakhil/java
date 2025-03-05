package net.syscon.s4.pkgs.oidscexm.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.oidscexm.OidscexmPkgRepository;
import net.syscon.s4.pkgs.oidscexm.OidscexmPkgService;
import net.syscon.s4.triggers.OffCourtEventVineIntfTrgService;
import net.syscon.s4.triggers.OffExtMvVineIntfTrgService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffenderExtMovementsTwfService;
import net.syscon.s4.triggers.OffenderExternalMovementT13Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT1Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT3Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT5Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT6Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT8Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT9Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuService;

@Service
public class OidscexmPkgServiceImpl implements OidscexmPkgService {

	
	@Autowired
	private OidscexmPkgRepository oidscexmRepository;
	@Autowired
	private VOffenderAllSchedules2TuService VOffenderAllSchedules2TuService;
	@Autowired
	private OffenderBookingsT2Service OffenderBookingsT2Service;
	@Autowired
	private OffenderBookingsBkadmTrgService OffenderBookingsBkadmTrgService;
	@Autowired
	private OffendersBookVineIntfTrgService OffendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT7Service OffenderBookingsT7Service;
	@Autowired
	private OffCourtEventVineIntfTrgService OffCourtEventVineIntfTrgService;
	@Autowired
	private OffenderIndSchedulesTwfService OffenderIndSchedulesTwfService;
	@Autowired
	private OffenderIndSchedulesT2Service OffenderIndSchedulesT2Service;
	@Autowired
	private OffenderIndSchedulesT1Service OffenderIndSchedulesT1Service;
	@Autowired
	private OffenderIndSchedulesT3Service OffenderIndSchedulesT3Service;
	@Autowired
	private OffenderExternalMovementsT6Service offenderExternalMovementsT6Service;
	@Autowired
	private OffenderExternalMovementsT3Service offenderExternalMovementsT3Service;
	@Autowired
	private OffenderExtMovementsTwfService offenderExtMovementsTwfService;
	@Autowired
	private OffenderExternalMovementsT5Service offenderExternalMovementsT5Service;
	@Autowired
	private OffenderExternalMovementsT8Service offenderExternalMovementsT8Service;
	@Autowired
	private OffenderExternalMovementsT9Service offenderExternalMovementsT9Service;
	@Autowired
	private OffExtMvVineIntfTrgService offExtMvVineIntfTrgService;
	@Autowired
	private OffenderExternalMovementsT1Service offenderExternalMovementsT1Service;
	@Autowired
	private OffenderExternalMovementT13Service offenderExternalMovementT13Service;

	@Autowired
	private EliteDateService eliteDateService;

	private static Logger logger = LogManager.getLogger(OidscexmPkgServiceImpl.class.getName());
	private static final String N = "N";
	private static final String Y = "Y";
	private static final String OUT = "OUT";
	private static final String OJ = "OJ";
	private static final String TRN = "TRN";
	private static final String IN = "IN";
	private static final String TAP = "TAP";
	private static final String CRT = "CRT";
	private static final String WR = "WR";

	@Override
	public VOffenderAllSchedules insertExternalMovement(final OffenderExternalMovements offExtmov,
			final String userName) {
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();

		try {
			final Long seq = oidscexmRepository.getSeq(offExtmov.getOffenderBookId());
			final Long newEventid = oidscexmRepository.getNewEventId();
			Long parentEventId = null;
			offExtmov.setCreateUserId(userName);
			offExtmov.setMovementSeq(seq);
			if (offExtmov != null && IN.equalsIgnoreCase(offExtmov.getDirectionCode()) && TAP.equalsIgnoreCase(offExtmov.getMovementType())
					||CRT.equalsIgnoreCase( offExtmov.getMovementType())) {
				parentEventId = oidscexmRepository.getParentCur(offExtmov.getOffenderBookId());
			}
			
			if (offExtmov != null && IN.equalsIgnoreCase(offExtmov.getDirectionCode()) && WR.equalsIgnoreCase( offExtmov.getMovementType())) {
				offExtmov.setParentEventId(offExtmov.getEventId());
				offExtmov.setEventId(BigDecimal.valueOf(newEventid));
				offExtmov.setCreateDatetime(new Date());
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(offExtmov);	
				oidscexmRepository.insertOffenderExtMov(offExtmov);
				offenderExternalMovementsT6Service.offenderExternalMovementsT6(offExtmov);
				offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(offExtmov, null);
				offenderExtMovementsTwfService.offenderExternalMovementsTrigger(offExtmov);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(offExtmov);
				offenderExternalMovementsT8Service.updateObligationWR(offExtmov.getOffenderBookId(), offExtmov.getMovementType(),userName);
				offExtMvVineIntfTrgService.offExtMvVineIntfTrg(offExtmov);
				MovementReasons newref = new MovementReasons();
				newref.setMovementType(offExtmov.getMovementType());
				newref.setMovementReasonCode(offExtmov.getMovementReasonCode());
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(null, newref, offExtmov.getOffenderBookId().longValue(), "INSERTING");
				offenderExternalMovementT13Service.OffenderExternalMovementT1(offExtmov);
				
			} else {
				offExtmov.setParentEventId(parentEventId!=null?BigDecimal.valueOf(parentEventId):null);
				Date dt = offenderExternalMovementsT9Service.offenderExternalMovementsT9(offExtmov);
				offExtmov.setMovementTime(dt);
				oidscexmRepository.insertOffenderExtMov(offExtmov);
				offenderExternalMovementsT6Service.offenderExternalMovementsT6(offExtmov);
				offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(offExtmov, null);
				offenderExtMovementsTwfService.offenderExternalMovementsTrigger(offExtmov);
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(offExtmov);
				offenderExternalMovementsT8Service.updateObligationWR(offExtmov.getOffenderBookId(), offExtmov.getMovementType(),userName);
				offExtMvVineIntfTrgService.offExtMvVineIntfTrg(offExtmov);
				MovementReasons newref = new MovementReasons();
				newref.setMovementType(offExtmov.getMovementType());
				newref.setMovementReasonCode(offExtmov.getMovementReasonCode());
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(null, newref, offExtmov.getOffenderBookId().longValue(), "INSERTING");
				offenderExternalMovementT13Service.OffenderExternalMovementT1(offExtmov);
			}
		} catch (Exception e) {
			logger.error("insertExternalMovement :" + e);
		}
		return bean;
	}

	@Override
	@Transactional
	public VOffenderAllSchedules deactivateOffender(final VOffenderAllSchedules paramBean, final String userName) {
		String lvInOutStatus = null;
		String lvAgyLocId = null;
		String lvCloseContactFlag = N;
		String lvCommActiveFlag = N;
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();

		try {
			List<OffenderBookings> offBookList = oidscexmRepository.getOldRecOffBooking(paramBean.getOffenderBookId().longValue());
			OffenderBookings offBook = offBookList.get(0);
			OffenderBookings bookingData  = new OffenderBookings();
			BeanUtils.copyProperties(paramBean, bookingData);
			bookingData.setAgyLocId(paramBean.getAgyLocId());
			bookingData.setActiveFlag(paramBean.getActiveFlag());
			bookingData.setOffenderBookId(paramBean.getOffenderBookId().longValue());
			bookingData.setOffenderId(paramBean.getOffenderId());
			bookingData.setModifyUserId(userName);
			if (OJ.equals(paramBean.getEventSubType())) {
				lvInOutStatus = OUT;
				lvAgyLocId = OUT;
				lvCloseContactFlag = oidscexmRepository.getCloseContactFlag();
			} else {
				lvInOutStatus = TRN;
				lvAgyLocId = TRN;
			}
			if (OJ.equals(paramBean.getEventSubType()) && Y.equals(lvCloseContactFlag)) {
				lvCommActiveFlag = oidscexmRepository.getCommActiveFlag(paramBean.getOffenderBookId().longValue());
				
				if (N.equals(lvCommActiveFlag)) {
					 OffenderBookingsT2Service.offenderBookingsT2(bookingData, offBook);
					 oidscexmRepository.updateOffenderBookings(lvInOutStatus, lvAgyLocId,
							paramBean.getOffenderBookId().longValue(), userName);
					OffenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(offBook, bookingData, "UPDATING");
					 OffendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(bookingData,ApplicationConstants.UPDATING);
					 OffenderBookingsT7Service.offenderBookingsT7Trigger(bookingData);
				} else if (Y.equals(lvCommActiveFlag)) {
					 OffenderBookingsT2Service.offenderBookingsT2(bookingData, offBook);
					 oidscexmRepository.updateOffenderBookingsOne(lvInOutStatus, lvAgyLocId,
							paramBean.getOffenderBookId().longValue(), userName);
					OffenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(offBook, bookingData, "UPDATING");
					 OffendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(bookingData,ApplicationConstants.UPDATING);
					OffenderBookingsT7Service.offenderBookingsT7Trigger(bookingData);
				}
			} else {
				 OffenderBookingsT2Service.offenderBookingsT2(bookingData, offBook);
				 oidscexmRepository.updateOffenderBookingsTwo(lvInOutStatus, lvAgyLocId,
						paramBean.getOffenderBookId().longValue(), userName);
				OffenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(offBook, bookingData, "UPDATING");
				 OffendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(bookingData,ApplicationConstants.UPDATING);
				 OffenderBookingsT7Service.offenderBookingsT7Trigger(bookingData);
			}

		} catch (Exception e) {
			logger.error("deactivateOffender", e);
		}
		return bean;
	}

	@Override
	@Transactional
	public VOffenderAllSchedules insertReturnSchedule(final VOffenderAllSchedules offIndSchedls, final String userName,OffenderIndSchedules oldObj)
			throws ParseException {
		Date lvReturnDate = null;
		Date lvReturnTime = null;
		final VOffenderAllSchedules bean = new VOffenderAllSchedules();
		OffenderIndSchedules objOffIndSch = null;
		try {
			if (offIndSchedls.getReturnDate() == null) {
				lvReturnDate = eliteDateService.getDBTime();
			} else {
				lvReturnDate = offIndSchedls.getReturnDate();
			}

			if ( CRT.equalsIgnoreCase(offIndSchedls.getEventType()) || TAP.equalsIgnoreCase(offIndSchedls.getEventType())) {
				if (offIndSchedls.getReturnTime() == null) {
					lvReturnTime = eliteDateService.getDBTime();
				} else {
					lvReturnTime = offIndSchedls.getReturnTime();
				}
			}
			if(lvReturnTime != null) {
			lvReturnDate.setHours(lvReturnTime.getHours());
			lvReturnDate.setMinutes(lvReturnTime.getMinutes());
			lvReturnDate.setSeconds(lvReturnTime.getSeconds());
			}
			
			objOffIndSch = new OffenderIndSchedules();
			objOffIndSch.setAgyLocId(offIndSchedls.getAgyLocId()!=null ? offIndSchedls.getAgyLocId():null);
			objOffIndSch.setDirectionCode(offIndSchedls.getDirectionCode()!=null ? offIndSchedls.getDirectionCode():null);
			objOffIndSch.setEscortCode(offIndSchedls.getEscortCode()!=null ? offIndSchedls.getEscortCode() :null);
			objOffIndSch.setEventSubType(offIndSchedls.getEventSubType()!=null ? offIndSchedls.getEventSubType() :null);
			objOffIndSch.setEventStatus(offIndSchedls.getEventStatus()!=null ? offIndSchedls.getEventStatus() :null);
			objOffIndSch.setEventDate(offIndSchedls.getEventDate()!=null ? offIndSchedls.getEventDate() :null);
			if ( offIndSchedls.getOffenderBookId() != null) {
				final String strOffenderId = String.valueOf(offIndSchedls.getOffenderBookId());
				objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
			}
			objOffIndSch.setToAgyLocId(offIndSchedls.getToAgyLocId()!=null ? offIndSchedls.getToAgyLocId() :null);
			objOffIndSch.setStartTime(offIndSchedls.getStartTime()!=null ? offIndSchedls.getStartTime() : null);
			objOffIndSch.setCommentText(offIndSchedls.getCommentText()!=null ? offIndSchedls.getCommentText() : null);
			objOffIndSch.setEventClass(offIndSchedls.getEventClass()!=null ? offIndSchedls.getEventClass() :null);
			objOffIndSch.setEventType(offIndSchedls.getEventType()!=null ? offIndSchedls.getEventType() : null);
			objOffIndSch.setHiddenCommentText(offIndSchedls.getHiddenCommentText()!=null ? offIndSchedls.getHiddenCommentText() : null);
			objOffIndSch.setEventId(offIndSchedls.getEventId()!=null ? offIndSchedls.getEventId().intValue():null);
			objOffIndSch.setCreateUserId(offIndSchedls.getCreateUserId()!=null ? offIndSchedls.getCreateUserId() : null);
			objOffIndSch.setEventOutcome(offIndSchedls.getEventOutcome()!=null ? offIndSchedls.getEventOutcome() : null);
			objOffIndSch.setOutcomeReasonCode(offIndSchedls.getOutcomeReasonCode()!=null ? offIndSchedls.getOutcomeReasonCode() : null);
			objOffIndSch.setEventSubType(offIndSchedls.getEventSubType()!=null ? offIndSchedls.getEventSubType() : null);
			objOffIndSch.setToAgyLocId(offIndSchedls.getToAgyLocId()!=null ? offIndSchedls.getToAgyLocId() : null);
			objOffIndSch.setScheduledTripId(offIndSchedls.getScheduledTripId()!= null? offIndSchedls.getScheduledTripId().intValue(): null);
			objOffIndSch.setReturnDate(lvReturnDate!=null ? lvReturnDate : null);
			objOffIndSch.setReturnTime(lvReturnDate!=null ? lvReturnDate : null);
			objOffIndSch.setToAddressId(offIndSchedls.getToAddressId()!=null?offIndSchedls.getToAddressId().intValue():null);
			objOffIndSch.setToAddressOwnerClass(offIndSchedls.getToAddressOwnerClass()!=null ? offIndSchedls.getToAddressOwnerClass() : null);
			objOffIndSch.setTransportCode(offIndSchedls.getTransportCode()!=null ? offIndSchedls.getTransportCode(): null);
			objOffIndSch.setApplicationDate(offIndSchedls.getApplicationDate()!=null? offIndSchedls.getApplicationDate():null);
			objOffIndSch.setApplicationTime(offIndSchedls.getApplicationTime()!=null?offIndSchedls.getApplicationTime():null);
			objOffIndSch.setOffPrgrefId(offIndSchedls.getOffPrgrefId()!=null ? offIndSchedls.getOffPrgrefId().intValue():null);
			objOffIndSch.setInTime(offIndSchedls.getInTime());
			objOffIndSch.setOutTime(offIndSchedls.getOutTime());
			objOffIndSch.setStartTime(offIndSchedls.getStartTime());
			objOffIndSch.setEndTime(offIndSchedls.getEndTime());
			objOffIndSch.setEventIdTemp(offIndSchedls.getEventIdTemp()!=null ? offIndSchedls.getEventIdTemp() : null);
			if(objOffIndSch!=null && "WR".equalsIgnoreCase(objOffIndSch.getEventType())) {
				objOffIndSch.setDirectionCode("IN");
			}
			
			VOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(objOffIndSch, oldObj, oldObj.getRecordSource(), null);
			
			if ("CRT".equals(offIndSchedls.getEventType())) {
				oidscexmRepository.insertCourtEvents(offIndSchedls, userName);
				CourtEvents ceNew = new CourtEvents();
				ceNew.setEventId(offIndSchedls.getEventId().intValue());
				ceNew.setEventDate(offIndSchedls.getEventDate());
				ceNew.setOffenderBookId(offIndSchedls.getOffenderBookId().intValue());
				ceNew.setStartTime(offIndSchedls.getStartTime());
				ceNew.setEventStatus(offIndSchedls.getEventStatus());
				ceNew.setCreateUserId(offIndSchedls.getCreateUserId());
				ceNew.setCaseId(null);
				ceNew.setAgyLocId(offIndSchedls.getAgyLocId());
				OffCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(ceNew, "INSERTING");
			}else if(objOffIndSch!=null && "WR".equalsIgnoreCase(objOffIndSch.getEventType())) {
			}else {
				OffenderIndSchedules newOffIndSch = new OffenderIndSchedules();
				newOffIndSch.setEventId(offIndSchedls.getEventId().intValue());
				newOffIndSch.setEventType(offIndSchedls.getEventType());
				newOffIndSch.setEventOutcome(offIndSchedls.getEventOutcome());
				newOffIndSch.setEventSubType(offIndSchedls.getEventSubType());
				newOffIndSch.setOffenderBookId(offIndSchedls.getOffenderBookId().intValue());
				newOffIndSch.setCreateUserId(offIndSchedls.getCreateUserId());
				newOffIndSch.setEventStatus(offIndSchedls.getEventStatus());
				newOffIndSch.setEventDate(offIndSchedls.getEventDate());
				newOffIndSch.setReferenceId(offIndSchedls.getReferenceId()!= null?offIndSchedls.getReferenceId().intValue():null);
				newOffIndSch.setMovementType(offIndSchedls.getMovementType());
				newOffIndSch.setReferenceId(offIndSchedls.getReferenceId()!=null?offIndSchedls.getReferenceId().intValue():null);
				newOffIndSch.setReturnDate(lvReturnDate);
				newOffIndSch.setReturnTime(lvReturnDate);
				OffenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(newOffIndSch, "INSERTING");
				List<OffenderIndSchedules> offenderIndSchedulesList = new ArrayList<OffenderIndSchedules>();
				offenderIndSchedulesList.add(newOffIndSch);
				OffenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(offenderIndSchedulesList);
				OffenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(offenderIndSchedulesList);
				OffenderIndSchedulesT3Service.getVnumRows(newOffIndSch);
				offIndSchedls.setReturnTime(lvReturnDate);
				oidscexmRepository.insertOffenderIndSchedules(offIndSchedls, userName);
			}
		} catch (Exception e) {
			logger.error("insertReturnSchedule", e);
		}
		return bean;
	}

	@Override
	public VOffenderAllSchedules updateCrtEventStatus(final Integer eventId, final String userName) {
		oidscexmRepository.updatecrsEventStatus(eventId, userName);
		return new VOffenderAllSchedules();
	}

	@Override
	public VOffenderAllSchedules updateOffExtmvnts(final VOffenderAllSchedules paramBean, final String userName) {
		OffenderExternalMovements  obj = new OffenderExternalMovements();
		obj.setOffenderBookId(paramBean.getOffenderBookId().longValue());
		obj.setMovementReasonCode(paramBean.getMovementReasonCode());
		obj.setMovementType(paramBean.getMovementType());
		
		obj.setToAgyLocId(paramBean.getToAgyLocId());
		obj.setMovementDate(paramBean.getfMTime());
		obj.setCreateUserId(paramBean.getCreateUserId());
		Date dt = offenderExternalMovementsT9Service.offenderExternalMovementsT9(obj);
		paramBean.setMovementTime(dt);
		
		oidscexmRepository.updateOffExtmvnts(paramBean, userName);
		if(paramBean.getMovementReasonCode() != null) {
		offenderExternalMovementsT5Service.offenderExternalMovementsT5(obj);
		}
		if(paramBean.getMovementType() != null) {
		offenderExternalMovementT13Service.OffenderExternalMovementT1(obj);
		}
		if(paramBean.getMovementReasonCode() != null || paramBean.getMovementType() != null) {
		MovementReasons newref = new MovementReasons();
		newref.setMovementType(paramBean.getMovementType());
		newref.setMovementReasonCode(paramBean.getMovementReasonCode());
		OffenderExternalMovements oldRef = new OffenderExternalMovements();
		List<OffenderExternalMovements> oldRefList = oidscexmRepository.getOldRecOffExtMov(paramBean.getOffenderBookId().longValue(),null);
		if(oldRefList !=null && oldRefList.size() > 0)
		 oldRef = oldRefList.get(0);
		MovementReasons oldref = new MovementReasons();
		oldref.setMovementType(oldRef.getMovementType());
		oldref.setMovementReasonCode(oldRef.getMovementReasonCode());
		offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldref, newref, paramBean.getOffenderBookId().longValue(), "UPDATING");
		}

		return new VOffenderAllSchedules();
	}

	@Override
	public VOffenderAllSchedules updateOffSchStatus(final Integer eventId) {
		oidscexmRepository.updateOffSchStatus(eventId);
		return new VOffenderAllSchedules();
	}

	@Override
	public VOffenderAllSchedules updateOffenderInOutStatus(final VOffenderAllSchedules paramBean,
			final String userName) {
		String lvInOut = null;
		try {
			if (OUT.equals(paramBean.getDirectionCode())) {
				lvInOut = OUT;
			} else {
				lvInOut = IN;
			}
			List<OffenderBookings> offBookList = oidscexmRepository.getOldRecOffBooking(paramBean.getOffenderBookId().longValue());
			OffenderBookings offBook = offBookList.get(0);
			OffenderBookings bookingData  = new OffenderBookings();
			BeanUtils.copyProperties(paramBean, bookingData);
			bookingData.setOffenderBookId(paramBean.getOffenderBookId().longValue());
			bookingData.setActiveFlag(paramBean.getActiveFlag());
			bookingData.setOffenderId(paramBean.getOffenderId());
			bookingData.setAgyLocId(paramBean.getAgyLocId());
			bookingData.setLivingUnitId(paramBean.getLivingUnitId());
			bookingData.setModifyUserId(userName);
			OffenderBookingsT2Service.offenderBookingsT2(bookingData, offBook);
			oidscexmRepository.UpdateInOutStatus(lvInOut, paramBean.getOffenderBookId().longValue(), userName);
			OffenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(offBook, bookingData, "UPDATING");
			OffendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(bookingData,ApplicationConstants.UPDATING);
			OffenderBookingsT7Service.offenderBookingsT7Trigger(bookingData);
		} catch (Exception e) {
			logger.error("updateOffenderInOutStatus :", e);
		}
		return new VOffenderAllSchedules();
	}

	
}
