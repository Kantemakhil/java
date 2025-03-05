package net.syscon.s4.inst.movementexternal.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.movementexternal.OidscexmRepository;
import net.syscon.s4.inst.movementexternal.OidscexmService;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.inst.movementexternal.beans.VOiusstri;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.pkgs.oidscexm.OidscexmPkgService;
import net.syscon.s4.pkgs.oms_movements.OmsMovementsService;
import net.syscon.s4.pkgs.payroll.PayrollService;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesService;
import net.syscon.s4.triggers.OffCourtEventVineIntfTrgService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuRepository;

/**
 * Class OidscexmServiceImpl
 */
@Service
public class OidscexmServiceImpl extends BaseBusiness implements OidscexmService {

	
	private  List<VOffenderAllSchedules> offSchTemp;
	
	@Autowired
	private OidscexmRepository oidscexmRepository;

	@Autowired
	private EliteDateService dateService;
	
	@Autowired
	private OmsMovementsService OmsMovementsService;
	
	@Autowired
	private VOffenderAllSchedules2TuService vOffenderAllSchedules2TuService; 
	
	@Autowired
	private OidscexmPkgService OidscexmPkgService;
	
	@Autowired
	private PayrollService payrollService;
	
	@Autowired
	private TagPrisonActivitiesService tagPrisonActivitiesService;
	
	@Autowired
	private OffCourtEventVineIntfTrgService OffCourtEventVineIntfTrgService;

	@Autowired
	VOffenderCourseEventsTuRepository vOffenderCourseEventsTuRepository;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidscexmServiceImpl.class.getName());

	/**
	 * Creates new OidscexmServiceImpl class Object
	 */
	public OidscexmServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderPendNotifications ChkNotification(final OffenderPendNotifications paramBean) {
		Integer vCount = 0;
		Long vNotiS;
		Long vNotiMoveS;
		
		OffenderPendNotifications offenderPendNotifications = oidscexmRepository
				.chkNotificationgetpendnotiinfocur(paramBean);
		vNotiS = offenderPendNotifications.getNotiSeq();
		vNotiMoveS = offenderPendNotifications.getNotiMoveSeq();
		if (vNotiS != 0 && vNotiMoveS != 0) {
			vCount = oidscexmRepository.chkNotificationgetcountcur(paramBean);
		}
		if (vCount > 1) {
			offenderPendNotifications.setMovementReasonCode("ConfimMsg");
		}
		return offenderPendNotifications;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OmsModules CreateFormGlobals(final OmsModules paramBean) {
		final OmsModules omsModules = oidscexmRepository.createFormGlobals(paramBean);
		return omsModules;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules searchRecord) {
		List<VOffenderAllSchedules> list = null;
		Integer rowId=0;
		list =  oidscexmRepository.offSchExecuteQuery(searchRecord);
		if(list != null && list.size()>0) {
			for(VOffenderAllSchedules data:list) {
				data.setRowId(++rowId);
				if (data.getToAgyLocId() == null && data.getToAddressId()!= null) {
					final String currentLocation = oidscexmRepository.getExternalAddress(data.getToAddressId());
					if (currentLocation != null) {
						data.setToAgyLocDesc(currentLocation);
					}
				}
			}
			offSchTemp=list;
		}
		return list;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SCH
	 *
	 * 
	 */
	@Transactional
	public Integer offSchCommit(final VOffenderAllSchedulesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = oidscexmRepository.offSchUpdateVOffenderAllSchedules(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgMoveTypeRecordGroup() {
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		refList = oidscexmRepository.rgMoveTypeRecordGroup();
		return refList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> rgBuildingRecordGroup() {
		return oidscexmRepository.rgBuildingRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgAgyIdRecordGroup() {
		 List<AgencyLocations> refList = oidscexmRepository.rgAgyIdRecordGroup();
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> rgTierRecordGroup() {
		return oidscexmRepository.rgTierRecordGroup();

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VOiusstri> schTripsExecuteQuery(final VOiusstri searchRecord) {
		return oidscexmRepository.schTripsExecuteQuery(searchRecord);
	}

	public OffenderExternalMovements getLastMovementDateTime(final VOffenderAllSchedules paramBean) {
		OffenderPendNotifications confirmMsg = new OffenderPendNotifications();
		confirmMsg.setOffenderBookId(Long.valueOf(paramBean.getOffenderBookId().toString()));
		confirmMsg.setScheduleId(paramBean.getEventId());
		OffenderExternalMovements returnList = new OffenderExternalMovements();
		returnList = oidscexmRepository.getLastMovementDateTime(paramBean.getOffenderBookId().toString());
		returnList.setDspDescription(oidscexmRepository.prisonActivities(paramBean).toString());
		confirmMsg = ChkNotification(confirmMsg);
		returnList.setCommentText(confirmMsg.getModifyUserId());
		return returnList;
	}

	public VOffenderAllSchedules processExternalMovement(final VOffenderAllSchedulesCommitBean commitBean) {
		String fromAddress = null;
		String fromAgyLocId = null;
		String lvFromCity = null;
		String toAgyLocId = null;
		String lvToAddressId = null;
		Integer count = 0;
		int sucess = 0;
		VOffenderAllSchedules returnList = new VOffenderAllSchedules();
		OffenderBookings processExternalList = new OffenderBookings();
		for (final VOffenderAllSchedules paramBean : commitBean.getUpdateList()) {
			paramBean.setModifyUserId(commitBean.getCreateUserId());
			paramBean.setCreateUserId(commitBean.getCreateUserId());
			Date lv_return_date = paramBean.getReturnDate();
			if (paramBean.getEventType().equals("TRN")) {
				returnList = OidscexmPkgService.deactivateOffender(paramBean,paramBean.getCreateUserId());
			} else {
				returnList = OidscexmPkgService.updateOffenderInOutStatus(paramBean,paramBean.getCreateUserId());
			}
			
			if(paramBean.getEventId()==null) {
				paramBean.setEventIdTemp(vOffenderCourseEventsTuRepository.getEventId());
				paramBean.setEventId(paramBean.getEventIdTemp());
			}
			int rowId=paramBean.getRowId();
			String recordSource = null;
			OffenderIndSchedules oldObj = new OffenderIndSchedules();
			for(VOffenderAllSchedules data:offSchTemp) {
				if (rowId == data.getRowId()) {
					oldObj = new OffenderIndSchedules();
					oldObj.setAgyLocId(data.getAgyLocId());
					oldObj.setEscortCode(data.getEscortCode());
					oldObj.setEventSubType(data.getEventSubType());
					oldObj.setEventStatus(data.getEventStatus());
					oldObj.setEventDate(data.getEventDate());
					oldObj.setToAgyLocId(data.getToAgyLocId());
					oldObj.setStartTime(data.getStartTime());
					oldObj.setCommentText(data.getCommentText());
					oldObj.setHiddenCommentText(data.getHiddenCommentText());
					oldObj.setEventType(data.getEventType());
					recordSource=data.getRecordSource();
					oldObj.setOffPrgrefId(data.getOffPrgrefId()!=null ? data.getOffPrgrefId().intValue():null);
					oldObj.setInTime(data.getInTime());
					oldObj.setOutTime(data.getOutTime());
					oldObj.setStartTime(data.getStartTime());
					oldObj.setEndTime(data.getEndTime());
					oldObj.setEventOutcome(data.getOutcome());
					oldObj.setDirectionCode(data.getDirectionCode());
					oldObj.setRecordSource(recordSource);
					if (data.getEventId() != null) {
						final String strEventId = String.valueOf(data.getEventId());
						oldObj.setEventId(Integer.parseInt(strEventId));
					}
					if (data.getReferenceId() != null) {
						oldObj.setReferenceIdTemp(data.getReferenceId()!=null?data.getReferenceId():null);
					}
					if (data.getOffenderBookId() != null) {
						oldObj.setOffenderBookId(data.getReferenceId()!=null?data.getReferenceId().intValue():null);
					}
					break;
			     }
			  }
			if ("OUT".equalsIgnoreCase(paramBean.getDirectionCode())
					&& ("CRT".equalsIgnoreCase(paramBean.getEventType()) || "TAP".equalsIgnoreCase(paramBean.getEventType()) || "WR".equalsIgnoreCase(paramBean.getEventType()))) {
				try {
				returnList = OidscexmPkgService.insertReturnSchedule(paramBean,paramBean.getCreateUserId(),oldObj);
				}
				catch(Exception e) {
					logger.error(e);
				}
			}
			if ("IN".equalsIgnoreCase(paramBean.getDirectionCode())) {
				if ("TAP".equalsIgnoreCase(paramBean.getEventType())) {

					fromAgyLocId = OmsMovementsService
							.getLastToAgyLocId(Integer.valueOf(paramBean.getOffenderBookId().toString()));
					toAgyLocId =   OmsMovementsService
							.getLastFromAgyLocId(Integer.valueOf(paramBean.getOffenderBookId().toString()));
					lvFromCity = OmsMovementsService
							.getLastToCity(Integer.valueOf(paramBean.getOffenderBookId().toString()));
					if (fromAgyLocId == null && lvFromCity == null) {
						fromAddress = OmsMovementsService
								.getToAddress(Integer.valueOf(paramBean.getOffenderBookId().toString()));
					} else {
						fromAddress = null;
					}
				}
				if ("CRT".equalsIgnoreCase(paramBean.getEventType())) {
					fromAgyLocId = OmsMovementsService
							.getLastToAgyLocId(Integer.valueOf(paramBean.getOffenderBookId().toString()));
					toAgyLocId =   OmsMovementsService
							.getLastFromAgyLocId(Integer.valueOf(paramBean.getOffenderBookId().toString()));
				}
			} else if ("OUT".equalsIgnoreCase(paramBean.getDirectionCode())) {
				fromAgyLocId = paramBean.getAgyLocId();
			}
			if (paramBean.getToCityCode() != null) {
				toAgyLocId = null;
				paramBean.setToAgyLocId(null);
			} else if (paramBean.getToAgyLocId() != null && "OUT".equalsIgnoreCase(paramBean.getDirectionCode())) {
				lvToAddressId = null;
				toAgyLocId = paramBean.getToAgyLocId();
			} else {
				lvToAddressId = (paramBean.getToAddressId() == null) ? null : paramBean.getToAddressId().toString();
			}
			
			returnList = OidscexmPkgService.updateOffExtmvnts(paramBean,paramBean.getModifyUserId());
			
			OffenderExternalMovements iem = new OffenderExternalMovements();
			iem.setOffenderBookId(Long.valueOf(paramBean.getOffenderBookId().toString()));
			iem.setEventId((paramBean.getEventId()));
			iem.setFromAgyLocId(fromAgyLocId);
			iem.setFromCity(lvFromCity);
			iem.setToAgyLocId(toAgyLocId);
			iem.setToAddressId((lvToAddressId == null) ? null : new BigDecimal(lvToAddressId));
			iem.setMovementType(paramBean.getEventType());
			iem.setMovementReasonCode(paramBean.getEventSubType());
			iem.setEventSubType(paramBean.getEventSubType());
			iem.setEscortCode(paramBean.getEscortCode());
			iem.setDirectionCode(paramBean.getDirectionCode());
			iem.setCommentText(paramBean.getCommentText());
			iem.setEventTime(paramBean.getfMTime());
			iem.setToCity(paramBean.getToCityCode());
			iem.setFromAddressId((fromAddress == null) ? null : new BigDecimal(fromAddress));
			iem.setProposedMvmntSeq(0);
			iem.setMovementTime(paramBean.getfMTime());
			iem.setMovementDate(new Date());
			iem.setCreateUserId(paramBean.getCreateUserId());
			iem.setMovementTime(paramBean.getfMTime());
			
			returnList = OidscexmPkgService.insertExternalMovement(iem,paramBean.getCreateUserId() );
			if("CRT".equalsIgnoreCase(paramBean.getEventType())) {
			returnList = OidscexmPkgService.updateCrtEventStatus(paramBean.getEventId()!=null ?Integer.valueOf(paramBean.getEventId().intValue()):null,paramBean.getCreateUserId());
			CourtEvents ceNew = new CourtEvents();
			if(paramBean.getEventId()!=null ) {
				ceNew.setEventId(paramBean.getEventId().intValue());				
			}
			ceNew.setEventDate(paramBean.getEventDate()!=null ? paramBean.getEventDate() : null);
			ceNew.setOffenderBookId(paramBean.getOffenderBookId()!=null ? paramBean.getOffenderBookId().intValue():null);
			ceNew.setStartTime(paramBean.getStartTime()!=null ? paramBean.getStartTime() : null);
			ceNew.setEventStatus(paramBean.getEventStatus()!=null ? paramBean.getEventStatus() : null);
			ceNew.setCreateUserId(paramBean.getCreateUserId()!=null ? paramBean.getCreateUserId() : null);
			ceNew.setCaseId(null);
			ceNew.setStartTime(paramBean.getStartTime()!=null ? paramBean.getStartTime() : null);
			ceNew.setAgyLocId(paramBean.getAgyLocId()!=null ? paramBean.getAgyLocId() : null);
			ceNew.setCourtEventType(paramBean.getEventSubType()!=null ? paramBean.getEventSubType() : null);
			OffCourtEventVineIntfTrgService.offCourtEventVineIntfTrg(ceNew, "UPDATING");	
		} else if (paramBean.getEventType() != null && "WR".equalsIgnoreCase(paramBean.getEventType())) {
			if (paramBean.getDirectionCode() != null && "IN".equalsIgnoreCase(paramBean.getDirectionCode())) {
				OffenderIndSchedules objOffIndSch = new OffenderIndSchedules();

				objOffIndSch.setAgyLocId(paramBean.getAgyLocId());
				objOffIndSch.setDirectionCode(paramBean.getDirectionCode());
				objOffIndSch.setEscortCode(paramBean.getEscortCode());
				objOffIndSch.setEventSubType(paramBean.getEventSubType());
				objOffIndSch.setEventStatus(paramBean.getEventStatus());
				objOffIndSch.setEventDate(paramBean.getEventDate());
				if (paramBean.getOffenderBookId() != null) {
					final String strOffenderId = String.valueOf(paramBean.getOffenderBookId());
					objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
				}
				objOffIndSch.setToAgyLocId(paramBean.getToAgyLocId());
				objOffIndSch.setStartTime(paramBean.getStartTime());
				objOffIndSch.setCommentText(paramBean.getCommentText());
				objOffIndSch.setEventClass(paramBean.getEventClass());
				objOffIndSch.setEventType(paramBean.getEventType());
				objOffIndSch.setHiddenCommentText(paramBean.getHiddenCommentText());
				objOffIndSch.setEventId(paramBean.getEventId() != null ? paramBean.getEventId().intValue() : null);
				objOffIndSch.setCreateUserId(paramBean.getCreateUserId());
				objOffIndSch.setEventOutcome(paramBean.getEventOutcome());
				objOffIndSch.setOutcomeReasonCode(paramBean.getOutcomeReasonCode());
				objOffIndSch.setEventSubType(paramBean.getEventSubType());
				objOffIndSch.setToAgyLocId(paramBean.getToAgyLocId());
				objOffIndSch.setScheduledTripId(
						paramBean.getScheduledTripId() != null ? paramBean.getScheduledTripId().intValue() : null);
				objOffIndSch.setReturnDate(lv_return_date);
				objOffIndSch.setReturnTime(paramBean.getReturnTime());
				objOffIndSch.setToAddressId(
						paramBean.getToAddressId() != null ? paramBean.getToAddressId().intValue() : null);
				objOffIndSch.setToAddressOwnerClass(paramBean.getToAddressOwnerClass());
				objOffIndSch.setTransportCode(paramBean.getTransportCode());
				objOffIndSch.setApplicationDate(paramBean.getApplicationDate());
				objOffIndSch.setDirectionCode(null);
				objOffIndSch.setEventStatus("COMP");
				objOffIndSch.setEventOutcome("COMP");
				objOffIndSch.setOutcomeReasonCode(null);

				objOffIndSch.setOffPrgrefId(
						paramBean.getOffPrgrefId() != null ? paramBean.getOffPrgrefId().intValue() : null);
				objOffIndSch.setApplicationTime(
						paramBean.getApplicationTime() != null ? paramBean.getApplicationTime() : null);
				objOffIndSch.setInTime(paramBean.getInTime());
				objOffIndSch.setOutTime(paramBean.getOutTime());
				objOffIndSch.setStartTime(paramBean.getStartTime());
				objOffIndSch.setEndTime(paramBean.getEndTime());
				oldObj.setEventId(paramBean.getEventId() != null ? paramBean.getEventId().intValue() : null);
				try {
					vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(objOffIndSch, oldObj, recordSource, null);
				} catch (Exception e) {
					logger.error("processExternalMovement :" + e);
				}

			}

		} else {
			OffenderIndSchedules objOffIndSch = new OffenderIndSchedules();
			objOffIndSch.setAgyLocId(paramBean.getAgyLocId());
			objOffIndSch.setDirectionCode(paramBean.getDirectionCode());
			objOffIndSch.setEscortCode(paramBean.getEscortCode());
			objOffIndSch.setEventSubType(paramBean.getEventSubType());
			objOffIndSch.setEventStatus(paramBean.getEventStatus());
			objOffIndSch.setEventDate(paramBean.getEventDate());
			if (paramBean.getOffenderBookId() != null) {
				final String strOffenderId = String.valueOf(paramBean.getOffenderBookId());
				objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
			}
			objOffIndSch.setToAgyLocId(paramBean.getToAgyLocId());
			objOffIndSch.setStartTime(paramBean.getStartTime());
			objOffIndSch.setCommentText(paramBean.getCommentText());
			objOffIndSch.setEventClass(paramBean.getEventClass());
			objOffIndSch.setEventType(paramBean.getEventType());
			objOffIndSch.setHiddenCommentText(paramBean.getHiddenCommentText());
			objOffIndSch.setEventId(paramBean.getEventId() != null ? paramBean.getEventId().intValue() : null);
			objOffIndSch.setCreateUserId(paramBean.getCreateUserId());
			objOffIndSch.setEventOutcome(paramBean.getEventOutcome());
			objOffIndSch.setOutcomeReasonCode(paramBean.getOutcomeReasonCode());
			objOffIndSch.setEventSubType(paramBean.getEventSubType());
			objOffIndSch.setToAgyLocId(paramBean.getToAgyLocId());
			objOffIndSch.setScheduledTripId(
					paramBean.getScheduledTripId() != null ? paramBean.getScheduledTripId().intValue() : null);
			objOffIndSch.setReturnDate(lv_return_date);
			objOffIndSch.setReturnTime(paramBean.getReturnTime());
			objOffIndSch
					.setToAddressId(paramBean.getToAddressId() != null ? paramBean.getToAddressId().intValue() : null);
			objOffIndSch.setToAddressOwnerClass(paramBean.getToAddressOwnerClass());
			objOffIndSch.setTransportCode(paramBean.getTransportCode());
			objOffIndSch.setApplicationDate(paramBean.getApplicationDate());
			objOffIndSch.setEventStatus("COMP");
			objOffIndSch
					.setOffPrgrefId(paramBean.getOffPrgrefId() != null ? paramBean.getOffPrgrefId().intValue() : null);
			objOffIndSch
					.setApplicationTime(paramBean.getApplicationTime() != null ? paramBean.getApplicationTime() : null);
			objOffIndSch.setInTime(paramBean.getInTime());
			objOffIndSch.setOutTime(paramBean.getOutTime());
			objOffIndSch.setStartTime(paramBean.getStartTime());
			objOffIndSch.setEndTime(paramBean.getEndTime());
			objOffIndSch.setEventOutcome(paramBean.getOutcome());
			objOffIndSch.setModifyUserId(paramBean.getModifyUserId());
			oldObj.setEventId(paramBean.getEventId() != null ? paramBean.getEventId().intValue() : null);
			try {
				vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(objOffIndSch, oldObj, recordSource, null);
			} catch (Exception e) {
				logger.error("processExternalMovement :" + e);
			}
		}

			processExternalList.setOffenderBookId(Long.valueOf(paramBean.getOffenderBookId().toString()));
			processExternalList = oidscexmRepository.processExternalMovement(processExternalList);
	
			 payrollService.updateWorkAsgnStatuses(
					processExternalList.getRootOffenderId(), paramBean.getCaseLoadId(),
					dateService.getDBTime(),commitBean.getCreateUserId());
			
			sucess = oidscexmRepository.statusRecords(paramBean);
			if (sucess == 1) {
				count = count + 1;
			}
		}
		returnList.setCheckSum(BigDecimal.valueOf(count));

		return returnList;
	}

	public OffenderExternalMovements suspendAllocations(final VOffenderAllSchedules paramBean) {
		OffenderExternalMovements returnList = new OffenderExternalMovements();
		if (paramBean.getCommentText() != null && paramBean.getCommentText().equals("suspend")) {
			 tagPrisonActivitiesService.suspendAllocations(paramBean.getOffenderBookId(),paramBean.getEventDate(),paramBean.getModifyUserId());
		} else {
			tagPrisonActivitiesService.endWaitlistAndAllocations(paramBean.getOffenderBookId(),paramBean.getEventDate(),"TRF",paramBean.getModifyUserId());
		}
		return returnList;
	}

}
