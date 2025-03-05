package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.OidsiappRepository;
import net.syscon.s4.inst.schedules.OidsiappService;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TdService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuService;

/**
 * Class OidsiappServiceImpl
 */
@Service
public class OidsiappServiceImpl extends BaseBusiness implements OidsiappService {

	@Autowired
	private OidsiappRepository oidsiappRepo;

	private static Logger logger = LogManager.getLogger(OidsiappServiceImpl.class.getName());

	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;

	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service;

	@Autowired
	private OffenderIndSchedulesTwfService offenderIndSchedulesTwfService;

	@Autowired
	private OffenderIndSchedulesT3Service offenderIndSchedulesT3Service;
	
	@Autowired
	private VOffenderAllSchedules2TdService vOffenderAllSchedules2TdService;
	@Autowired
	private VOffenderAllSchedules2TuService vOffenderAllSchedules2TuService;

	@Autowired
	private OcdprogrRepository ocdprogrRepository;

	@Autowired
	private NonAssociationService nonAssociationsService;

	/**
	 * Creates new OidsiappServiceImpl class Object
	 */
	public OidsiappServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(final VOffenderAllSchedules paramBean) {
		List<VOffenderAllSchedules> schedule=new ArrayList<>();
		try {
			schedule= oidsiappRepo.offBkgOnCheckDeleteMaster(paramBean);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In offBkgOnCheckDeleteMaster method : ", e);
		}
		return schedule;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<VHeaderBlock> checkNa(final VHeaderBlock paramBean) {
		List<VHeaderBlock> block=new ArrayList<>();
		try {
			block= oidsiappRepo.checkNa(paramBean);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In checkNa method : ", e);
		}
		return block;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules searchRecord) {
		List<VOffenderAllSchedules> schedule=new ArrayList<>();
		try {
			schedule=oidsiappRepo.offSchExecuteQuery(searchRecord);			
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In offSchExecuteQuery method : ", e);
		}
        return schedule;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SCH
	 *
	 */
	@Transactional
	public Integer offSchCommit(final VOffenderAllSchedulesCommitBean commitBean) {
		int liReturn = 0;
		Integer internalLocationId=null;
		String strOffenderId = null;
		OffenderIndSchedules objOffIndSch = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenderIndSchedules> lstInsertOff = new ArrayList<OffenderIndSchedules>();
			Integer eventId = null;
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getInsertList()) {
				lstInsertOff = new ArrayList<OffenderIndSchedules>();
				eventId = oidsiappRepo.offSchPreInsert();
				objOffIndSch = new OffenderIndSchedules();
				objOffIndSch.setEventDate(vOffAllSch.getEventDate());
				objOffIndSch.setStartTime(vOffAllSch.getStartTime());
				objOffIndSch.setCommentText(vOffAllSch.getCommentText());
				objOffIndSch.setEventType("APP");
				objOffIndSch.setEventSubType(vOffAllSch.getEventSubType());
				objOffIndSch.setEventClass("INT_MOV");
				objOffIndSch.setEventStatus("SCH");
				objOffIndSch.setEventId(eventId);
				objOffIndSch.setCancelFlag(vOffAllSch.getCancelFlag());
				if (vOffAllSch.getOffenderBookId() != null) {
					strOffenderId = String.valueOf(vOffAllSch.getOffenderBookId());
					objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
				}
				objOffIndSch.setAgyLocId(vOffAllSch.getAgyLocId());
				internalLocationId = oidsiappRepo.getLocationId(vOffAllSch.getToIntLocLevel1Code(),vOffAllSch.getAgyLocId());
				 objOffIndSch.setToInternalLocationId(internalLocationId);
//				objOffIndSch.setToInternalLocationId(vOffAllSch.getToInternalLocationId().intValue());
				lstInsertOff.add(objOffIndSch);
				lstInsertOff.forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
				try {
					logger.info(this.getClass().getName() +"offenderIndSchedulesT2Tgr method call");
					offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(lstInsertOff);
					logger.info(this.getClass().getName() +"offenderIndSchedulesT1Tgn method call");
					offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(lstInsertOff);
					logger.info(this.getClass().getName() +"offenderIndSchedulesTwfTgr method call");
					offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(objOffIndSch, "INSERTING");
					logger.info(this.getClass().getName() +"getVnumRows method call");
					offenderIndSchedulesT3Service.getVnumRows(objOffIndSch);
				} catch (Exception e) {
					logger.error("Error in Class " + this.getClass().getName() +"In offSchCommit method : ",e);
				}
				liReturn = oidsiappRepo.offSchInsertVOffenderAllSchedules(lstInsertOff);

			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			List<OffenderIndSchedules> lstUpdateOff = new ArrayList<OffenderIndSchedules>();
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getUpdateList()) {
				objOffIndSch = new OffenderIndSchedules();
				objOffIndSch.setEventDate(vOffAllSch.getEventDate());
				objOffIndSch.setStartTime(vOffAllSch.getStartTime());
				internalLocationId = oidsiappRepo.getLocationId(vOffAllSch.getToIntLocLevel1Code(),vOffAllSch.getAgyLocId());
				objOffIndSch.setToInternalLocationId(internalLocationId);
//				objOffIndSch.setToInternalLocationId(vOffAllSch.getToInternalLocationId().intValue());
				objOffIndSch.setEventSubType(vOffAllSch.getEventSubType());
				objOffIndSch.setCommentText(vOffAllSch.getCommentText());
				objOffIndSch.setOffenderBookId(vOffAllSch.getOffenderBookId().intValue());
				objOffIndSch.setEventClass(vOffAllSch.getEventClass());
				objOffIndSch.setEventType(vOffAllSch.getEventType());
				objOffIndSch.setEventStatus(vOffAllSch.getEventStatus());
				objOffIndSch.setModifyUserId(commitBean.getCreateUserId());
				objOffIndSch.setRecordSource(vOffAllSch.getRecordSource());
				if(ApplicationConstants.YES.equalsIgnoreCase(vOffAllSch.getCancelFlag())) {
				objOffIndSch.setEventStatus("CANC");
				objOffIndSch.setEventOutcome(vOffAllSch.getEventOutcome());
				}
				objOffIndSch.setAgyLocId(vOffAllSch.getAgyLocId());
				if (vOffAllSch.getEventId() != null) {
					final String strEventId = String.valueOf(vOffAllSch.getEventId());
					objOffIndSch.setEventId(Integer.parseInt(strEventId));
				}
				lstUpdateOff.add(objOffIndSch);

				try {
					List<OffenderIndSchedules> list = oidsiappRepo.getOldRecord(objOffIndSch.getEventId());
					logger.info(this.getClass().getName() +"getOldRecord method call");
					liReturn = vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(objOffIndSch,
							list != null && list.size() > 0 ? list.get(0) : null, objOffIndSch.getRecordSource(), null);
					logger.info(this.getClass().getName() +"vOffenderAllSchedules2TuTrigger method call");
					
				} catch (Exception e) {
					logger.error("Error in Class " + this.getClass().getName() +"In offSchCommit method",e);
				}
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			final List<OffenderIndSchedules> lstDeleteOff = new ArrayList<OffenderIndSchedules>();
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getDeleteList()) {
				objOffIndSch = new OffenderIndSchedules();
				if (vOffAllSch.getEventId() != null) {
					final String strEventId = String.valueOf(vOffAllSch.getEventId());
					objOffIndSch.setEventId(Integer.parseInt(strEventId));
				}
				lstDeleteOff.add(objOffIndSch);
			}
			for (VOffenderAllSchedules bean : commitBean.getDeleteList()) {
				VOffenderAllSchedules2 schedule = new VOffenderAllSchedules2();
				BeanUtils.copyProperties(bean, schedule);
				schedule.setRecordSource(bean.getRecordSource());
				schedule.setEventId(bean.getEventId());
				schedule.setOffenderBookId(bean.getOffenderBookId());
				schedule.setReferenceId(bean.getReferenceId());
				schedule.setModifyUserId(commitBean.getCreateUserId());
				try {
					liReturn = vOffenderAllSchedules2TdService.vOffenderAllSchedules2TdTgr(schedule, null, null);
				} catch (CustomException e) {
					logger.error("Error in Class " + this.getClass().getName() +"In offSchCommit method",e);
				}
			}
		}

		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<IntLocUsageLocations> rgInternalMoveLocationsRecordGroup(final String agyLocId) {

		List<IntLocUsageLocations> returnList = new ArrayList<>();

		if (agyLocId != null && !agyLocId.equals("null")) {
			try {
			returnList = (List<IntLocUsageLocations>) oidsiappRepo.rgInternalMoveLocationsRecordGroup(agyLocId);
			}catch (Exception e) {
				logger.error("Error in Class " + this.getClass().getName() +"In rgInternalMoveLocationsRecordGroup method",e);
			}
			for (final IntLocUsageLocations result : returnList) {
				if (result.getSeq() == 0) {
					result.setCanDisplay(true);
				} else {
					result.setCanDisplay(false);
				}
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup() {
		List<InternalScheduleReasons> returnList =new ArrayList<>();
		try {
		 returnList = oidsiappRepo.rgSchInternalScheduleRecordGroup();
		for (InternalScheduleReasons internalScheduleReasons : returnList) {
			
			String value = oidsiappRepo.gettingLovDescription(internalScheduleReasons.getCode());
			internalScheduleReasons.setInternalScheduleType(internalScheduleReasons.getDescription());
			internalScheduleReasons.setDescription(value);
		}
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In rgSchInternalScheduleRecordGroup method",e);
		}
		return returnList;
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 * 
	 * @param vOffAllSch
	 * @return Integer
	 */
	public Integer checkScheduleConflict(final VOffenderAllSchedules vOffAllSch) {
		Integer sch=null;
		try {
		 sch=oidsiappRepo.checkScheduleConflict(vOffAllSch);
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In checkScheduleConflict method",e);
		}
		return sch;
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 * 
	 * @param vOffAllSch
	 * @return Integer
	 */
	public String checkNonAssociations(VOffenderAllSchedulesCommitBean commitBean) {
		String returnMessage = "";
		String individualConflictsMessage = "";
		String gangConflictsMessage = "";
		List<VOffenderAllSchedules> vOffenderAllSchedulesList = new ArrayList<VOffenderAllSchedules>();
		try {
		if(commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			vOffenderAllSchedulesList.addAll(commitBean.getInsertList());
		}
		if(commitBean.getUpdateList() != null &&commitBean.getUpdateList().size() > 0) {
			vOffenderAllSchedulesList.addAll(commitBean.getUpdateList());
		}
		if(vOffenderAllSchedulesList != null && vOffenderAllSchedulesList.size() > 0) {
			for(VOffenderAllSchedules vOffenderAllSchedule : vOffenderAllSchedulesList) {			
				vOffenderAllSchedule.setToInternalLocationId(BigDecimal.valueOf(oidsiappRepo.getLocationId(vOffenderAllSchedule.getToIntLocLevel1Code(),vOffenderAllSchedule.getAgyLocId())));
				List<OffenderNaDetails>  offenderNaDetailsList = nonAssociationsService.checkNonAssociationOffendersWithLivingUnit(vOffenderAllSchedule.getOffenderBookId(), vOffenderAllSchedule.getToInternalLocationId(), null);
				logger.info(this.getClass().getName() +"checkNonAssociationOffendersWithLivingUnit method call");
				if(offenderNaDetailsList != null && offenderNaDetailsList.size() > 0) {
					for(OffenderNaDetails offenderNaDetails : offenderNaDetailsList) {
						List<OffenderIndSchedules> offenderIndSchedulesList = oidsiappRepo.getNonAssociationScheduleConflicts(offenderNaDetails.getNsOffenderBookId(), vOffenderAllSchedule.getEventDate(), vOffenderAllSchedule.getToInternalLocationId());
						if (offenderIndSchedulesList != null && offenderIndSchedulesList.size() > 0) {
							logger.info(this.getClass().getName() +"ocdprogrGetOffenderNames method call");
							VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(offenderNaDetails.getNsOffenderBookId(),commitBean.getCreateUserId());
							String message = "";
							message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay() + "\n";							
							for (OffenderIndSchedules offenderIndSchedule : offenderIndSchedulesList) {
								java.util.Date stDate = offenderIndSchedule.getStartTime();
								java.util.Date enDate = offenderIndSchedule.getEndTime();
								java.util.Date apDate = offenderIndSchedule.getEventDate();
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								String startTime = stDate != null ? sdf.format(stDate) : "";
								String endTime = enDate != null ? sdf.format(enDate) : null;
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
								String appDate = sdf1 != null ? sdf1.format(apDate) : "";
								message = message + appDate + ", " + startTime + (endTime != null ? ", " + endTime : "") + "\n";
							}
							individualConflictsMessage = individualConflictsMessage + message + "\n";
						}
					}
				}
			}
			if(individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) {
				individualConflictsMessage = "oidsiapp.indinonassocconflict:\n\n" + individualConflictsMessage;
			}
			logger.info(this.getClass().getName() +"getOffendersOfNonAssociationGroup method call");
			List<OffenderStgAffiliations> offenderStgAffiliationsList = nonAssociationsService.getOffendersOfNonAssociationGroup(vOffenderAllSchedulesList.get(0).getOffenderBookId());
			if(offenderStgAffiliationsList != null && offenderStgAffiliationsList.size()>0) {
				for(OffenderStgAffiliations offenderStgAffiliations : offenderStgAffiliationsList) {
					for(VOffenderAllSchedules vOffenderAllSchedule : vOffenderAllSchedulesList) {
						logger.info(this.getClass().getName() +"ocdprogrGetOffenderNames method call");
						List<OffenderIndSchedules> offenderIndSchedulesList = oidsiappRepo.getNonAssociationScheduleConflicts(offenderStgAffiliations.getOffenderBookId(), vOffenderAllSchedule.getEventDate(), vOffenderAllSchedule.getToInternalLocationId());
						if (offenderIndSchedulesList != null && offenderIndSchedulesList.size() > 0) {
							logger.info(this.getClass().getName() +"ocdprogrGetOffenderNames method call");
							VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(offenderStgAffiliations.getOffenderBookId(),commitBean.getCreateUserId());
							String message = "";
							message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay() + "\n";							
							for (OffenderIndSchedules offenderIndSchedule : offenderIndSchedulesList) {
								java.util.Date stDate = offenderIndSchedule.getStartTime();
								java.util.Date enDate = offenderIndSchedule.getEndTime();
								java.util.Date apDate = offenderIndSchedule.getEventDate();
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								String startTime = stDate != null ? sdf.format(stDate) : "";
								String endTime = enDate != null ? sdf.format(enDate) : null;
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
								String appDate = sdf1 != null ? sdf1.format(apDate) : "";
								message = message + appDate + ", " + startTime + (endTime != null ? ", " + endTime : "") + "\n";
							}
							gangConflictsMessage = gangConflictsMessage + message + "\n";
						}
					}

				}
			}

			if(gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")) {
				gangConflictsMessage = "oidsiapp.gangnonassocconflict:\n\n" + gangConflictsMessage;
			}

			if((individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) && (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase(""))) {
				returnMessage = getFinalMessageString(individualConflictsMessage + "\n" + gangConflictsMessage);
			} else if(individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")){
				returnMessage = getFinalMessageString(individualConflictsMessage);
			} else if(gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")){
				returnMessage = getFinalMessageString(gangConflictsMessage);
			} else {
				returnMessage = ApplicationConstants.EMPTYDATA;
			}
		} else {
			returnMessage = ApplicationConstants.EMPTYDATA;
		}
		} catch(Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +" in checkNonAssociations " + e);
		}
		return returnMessage;
	}

	public String getFinalMessageString(final String messageData) {
		String conflictmsg="";
		try {
			conflictmsg= "oidsiapp.nonassociationconflictmsg \n\n"
				+ messageData + " \n oidsiapp.doyouwanttocontinue ";
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In getFinalMessageString method " + e);
		}
		return conflictmsg;
	}

}
