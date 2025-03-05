package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.cm.weeklyactivityplans.OweacplnRepository;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.CaseNotePermissions;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.inst.casemanagement.OidcnoteRepository;
import net.syscon.s4.inst.schedules.RecurringScheduleService;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.iwp.OcdclogsRepository;
import net.syscon.s4.iwp.OcdclogsService;
import net.syscon.s4.pkgs.tag_contact_log.TagContactLogService;
import net.syscon.s4.pkgs.tag_multiple_failure.TagMultipleFailureService;
import net.syscon.s4.pkgs.tag_schedule.TagScheduleService;
import net.syscon.s4.pkgs.tag_utils.TagUtilsService;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TdService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuService;

/**
 * Class OcdclogsServiceImpl
 */
@Service
public class OcdclogsServiceImpl implements OcdclogsService {

	private static Logger logger = LogManager.getLogger(OcdclogsServiceImpl.class.getName());
	
	@Autowired
	private OcdclogsRepository ocdclogsRepository;

	@Autowired
	private OcdprogrRepository ocdprogrRepository;
	
	@Autowired
	private OidcnoteRepository oidcnoteRepository;

	private static String YFLAG = "Y";
	private static String NFLAG = "N";
	private static String COMM = "COMM";
	private static String SCH = "SCH";

	@Autowired
	private TagUtilsService tagUtilsService;

	@Autowired
	private TagContactLogService tagContactLogService;

	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;

	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service;

	@Autowired
	private OffenderIndSchedulesT3Service offenderIndSchedulesT3Service;

	@Autowired
	private OffenderIndSchedulesTwfService offenderIndSchedulesTwfService;

	@Autowired
	private TagScheduleService tagScheduleService;

	@Autowired
	private VOffenderAllSchedules2TdService vOffenderAllSchedules2TdService;

	@Autowired
	private TagMultipleFailureService tagMultipleFailureService;

	@Autowired
	private VOffenderAllSchedules2TuService vOffenderAllSchedules2TuService;
	
	@Autowired
	private RecurringScheduleService recurringScheduleService;
	
	@Autowired
	private OweacplnRepository oweacplnRepository;

	/**
	 * This method is used to execute a record group 2
	 */
	public List<ReferenceCodes> rgCasenoteTypeRecordGroup(final String caseloadType,String username) {
	
		List<ReferenceCodes> lovList = ocdclogsRepository.rgCasenoteTypeRecordGroup(caseloadType,username);
		List<CaseNotePermissions>  caseNoteList=oidcnoteRepository.getCaseNoteLovs(username);
		List<ReferenceCodes> newList = new ArrayList<ReferenceCodes>();

		for (ReferenceCodes obj : lovList) {
			for (CaseNotePermissions obj1:caseNoteList) {
				if(obj1.getWorkId().compareTo(new BigDecimal(obj.getWorkId()))==0) {
					if(obj1.getCreateFlag().equalsIgnoreCase(YFLAG) && obj.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YFLAG) && (caseloadType.equalsIgnoreCase(obj.getState()) || "BOTH".equalsIgnoreCase(obj.getState()))) {
						obj.setCanDisplay(true);
					}else if(obj.getCanDisplay()== null || obj.getCanDisplay()== false) {
						obj.setCanDisplay(false);
					}
				}
			}
		}
		List<ReferenceCodes> collect = lovList.stream().filter(e->e.getCanDisplay()).collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(ReferenceCodes::getDescription)))).stream().collect(Collectors.toList());
		List<ReferenceCodes> collect2 = lovList.stream().filter(e->!e.getCanDisplay()).collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(ReferenceCodes::getDescription)))).stream().collect(Collectors.toList());
		
		newList.addAll(collect);
		newList.addAll(collect2);
		return newList;
	}

	/**
	 * This method is used to execute a record group 1
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(final String caseNoteType,String username,String caseloadType) {
		List<ReferenceCodes> lovList=ocdclogsRepository.rgCasenoteSubtypeRecordGroup(caseNoteType,username,caseloadType);
		List<CaseNotePermissions>  caseNoteList=oidcnoteRepository.getCaseNoteLovs(username);
		for (ReferenceCodes obj : lovList) {
			for (CaseNotePermissions obj1:caseNoteList) {
				if(obj1.getWorkId().compareTo(new BigDecimal(obj.getWorkId()))==0) {
					if(obj1.getCreateFlag().equalsIgnoreCase("Y") && obj.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YFLAG) && (caseloadType.equalsIgnoreCase(obj.getState()) || "BOTH".equalsIgnoreCase(obj.getState()))) {
						obj.setCanDisplay(true);
					}else if(obj.getCanDisplay()== null || obj.getCanDisplay()== false) {
						obj.setCanDisplay(false);
					}
				}
			}
		}
		

			
      return lovList;
	}

	/**
	 * Fetch the records from database table 3
	 *
	 */
	public List<OffenderCaseNotes> offNotesExecuteQuery(OffenderCaseNotes searchRecord) {
		List<OffenderCaseNotes> returnList = new ArrayList<OffenderCaseNotes>();
		returnList = ocdclogsRepository.offNotesExecuteQuery(searchRecord);
		List<OffenderCaseNotes> returnList1 = new ArrayList<OffenderCaseNotes>();
		for(OffenderCaseNotes obj : returnList) {
			if(obj.getViewFlag().equalsIgnoreCase("Y")) {
			returnList1.add(obj);
			}
		}
		return returnList1;
	}

	public Integer validateNoteTypeSubType(OffenderCaseNotes searchRecord) {
		return ocdclogsRepository.validateNoteTypeSubType(searchRecord);

	}

	/**
	 * This method is used to execute a record group 1
	 *
	 * @throws SQLException
	 */
	public List<OffenderCaseNotes> rgCasenotestaffNameRecordGroup(final String tip) {
		String caseloadId = null;
		String agyLocId = null;
		Long offenderBookId = null;
		List<OffenderCaseNotes> returnList = new ArrayList<>();

		final String[] inputArray = tip.split(",");
		if (inputArray.length > 0) {
			if (inputArray[0] != null && !inputArray[0].equals(""))
				caseloadId = inputArray[0];
			if (inputArray.length > 1 && inputArray[1] != null && !inputArray[1].equals(""))
				agyLocId = inputArray[1];
			if (inputArray.length > 2 && inputArray[2] != null && !inputArray[2].equals(""))
				offenderBookId = Long.parseLong(inputArray[2]);
		}
		returnList = ocdclogsRepository.rgCasenotestaffNameRecordGroup(caseloadId, agyLocId, offenderBookId);
		for (final OffenderCaseNotes obj : returnList) {
			if (obj.getActiveFlag().equals("Y")) {
				obj.setCanDisplay(true);
			} else {
				obj.setCanDisplay(false);
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_NOTES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offNotesCommit(OffenderCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderCaseNotes bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offNotesInsertOffenderCaseNotes(commitBean.getInsertList());
		}

		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderCaseNotes bean : commitBean.getUpdateList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocdclogsRepository.offNotesUpdateOffenderCaseNotes(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderCaseNotes bean : commitBean.getDeleteList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offNotesDelete(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public Integer offNotesDelete(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		int count = 11;

		for (final OffenderCaseNotes obj : lstOffenderCaseNotes) {
			count = checkPrivilegeExists(obj);
		}
		return count;
	}

	public Integer checkPrivilegeExists(OffenderCaseNotes obj) {
		int count = 11;
		int cpe = ocdclogsRepository.checkPrivilegeExists(obj.getLvRoleCode(),obj.getCreateUserId());

		if (cpe > 0) {
			// Function call
			int otdr = tagUtilsService.okToDeleteRecord(obj.getCaseNoteId());
			if (otdr == 1) {
				int del = ocdclogsRepository.offNotesDeleteOffenderCaseNotes(obj);
				if (del > 0) {
					count = 1;
				}
			}
		}
		return count;
	}

	public Integer offNotesInsertOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		Integer caseNoteId = 0;
		Integer staffId = 0;
		int count = 0;
		for (final OffenderCaseNotes obj : lstOffenderCaseNotes) {
			caseNoteId = ocdclogsRepository.getcaseNoteId();
			staffId = ocdclogsRepository.getStaffId(obj.getCreateUserId());
			obj.setCaseNoteId(caseNoteId);
			obj.setStaffId(staffId);
		}
		try {
			ocdclogsRepository.offNotesInsertOffenderCaseNotes(lstOffenderCaseNotes);
		} catch (Exception e) {
			logger.error("offNotesInsertOffenderCaseNotes:", e);
		}
		count = postInsert(lstOffenderCaseNotes);

		return count;
	}

	public Integer postInsert(final List<OffenderCaseNotes> list) {
		int count = 0;

		for (final OffenderCaseNotes obj : list) {
			Long schId = null;
			// Procedure call
			count = tagContactLogService.insNoteSchAssociation(obj.getOffenderBookId().longValue(),
					obj.getCaseNoteId().longValue(), schId, obj.getCreateUserId());
		}
		return count;
	}

	/**
	 * Fetch the records from database table
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(VOffenderAllSchedules searchRecord) {
		List<VOffenderAllSchedules> returnList = ocdclogsRepository.offSchExecuteQuery(searchRecord);
		String outComeUpdateFlag;
		for (VOffenderAllSchedules vOffenderAllSchedules : returnList) {
			if(vOffenderAllSchedules.getSmsFlag() == null) {
				vOffenderAllSchedules.setSmsFlag(vOffenderAllSchedules.getSmsFlagTemp());
			}
			if(vOffenderAllSchedules.getEmailFlag() == null) {
				vOffenderAllSchedules.setEmailFlag(vOffenderAllSchedules.getEmailFlagTemp());
			}
			
			vOffenderAllSchedules.setNbtFirstName("existingRecord");
			if (vOffenderAllSchedules.getEventOutcome() != null) {
				// Function call
				outComeUpdateFlag = tagContactLogService.checkUpdOutcomeLogValid(vOffenderAllSchedules);
				if (outComeUpdateFlag != null) {
					vOffenderAllSchedules.setNbtUpdOutcomeFlag(outComeUpdateFlag);
				}
			} else {
				if ("UW".equals(vOffenderAllSchedules.getEventType())
						|| "ACP".equals(vOffenderAllSchedules.getEventType())
						|| "SA".equals(vOffenderAllSchedules.getEventType())
						|| "DRR".equals(vOffenderAllSchedules.getEventType())) {
					vOffenderAllSchedules.setNbtUpdOutcomeFlag("N");
				} else {
					vOffenderAllSchedules.setNbtUpdOutcomeFlag("Y");
				}
			}
			
			if("ACP".equals(vOffenderAllSchedules.getEventType()) && vOffenderAllSchedules.getAgyLocId() == null) {
				vOffenderAllSchedules.setToAgyLocId(vOffenderAllSchedules.getLocation());
			}
		}
		return returnList;

	}

	public List<VOffenderAllSchedules> schExecuteQuery(final BigDecimal offenderBookId) {
		return ocdclogsRepository.schExecuteQuery(offenderBookId);

	}

	//
	///**Insert the records from database table
	// *
	// * @param lstOFF_SCH
	// *
	// * @throws SQLException
	// */
	@Transactional
	public Integer offSchCommit(VOffenderAllSchedulesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (VOffenderAllSchedules bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offschInsert(commitBean.getInsertList());
		}

		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (VOffenderAllSchedules bean : commitBean.getUpdateList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = offSchUpdate(commitBean.getUpdateList());
		}

		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			VOffenderAllSchedulesCommitBean deleteBean = new VOffenderAllSchedulesCommitBean();
			deleteBean.setDeleteList(new ArrayList<VOffenderAllSchedules>());
			List<VOffenderAllSchedules> deleteList = new ArrayList<VOffenderAllSchedules>();
			for (VOffenderAllSchedules bean : commitBean.getDeleteList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setModifyUserId(commitBean.getCreateUserId());
				bean.setRecordSource("SCH");

				if(bean.getSeriesId() != null && bean.getIsSeriesDelete()) {
					deleteBean.getDeleteList().add(bean);
				}else {
					deleteList.add(bean);
				}
			}
			if(deleteBean.getDeleteList() != null && !deleteBean.getDeleteList().isEmpty()) {
				liReturn = recurringScheduleService.recurrRuleCommit(commitBean);
			}else {
				commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = offSchDelete(commitBean.getDeleteList());
			}
		}

		return liReturn;
	}

	public Integer offschInsert(List<VOffenderAllSchedules> insertList) {
		int count = 0;
		int countOffIndSch = 0;
		Long nbtSchCount;
		BigDecimal checkSum;
		BigDecimal eventId;

		if (insertList != null && insertList.size() > 0) {
			for (VOffenderAllSchedules vOffAllSch : insertList) {
				List<OffenderIndSchedules> lstInsertOff = new ArrayList<OffenderIndSchedules>();
				OffenderIndSchedules objOffIndSch = new OffenderIndSchedules();

				// gettitng EventId
				eventId = ocdclogsRepository.offSchEventId();
				vOffAllSch.setEventId(eventId);

				// gettitng In Charge StaffId
				if (vOffAllSch.getInChargeStaffName() != null) {
					BigDecimal staffId = ocdclogsRepository.inChargeStaffId(vOffAllSch.getInChargeStaffName());
					vOffAllSch.setInChargeStaffId(staffId);
				}

				// Pre-Insert
				// CHECK_FOR_SHEDULE_CONFLICT pre-insert
				nbtSchCount = ocdclogsRepository.checkForSheduleConflict(vOffAllSch);
				if (nbtSchCount != vOffAllSch.getNbtSchCount() && nbtSchCount > 0) {
					vOffAllSch.setNbtSchCount(nbtSchCount);
				}

				// setting data to OffenderIndSchedules bean
				objOffIndSch.setOffenderBookId(vOffAllSch.getOffenderBookId().intValue());
				objOffIndSch.setEventType(vOffAllSch.getEventType());
				objOffIndSch.setEventSubType(vOffAllSch.getEventSubType());
				objOffIndSch.setEventDate(vOffAllSch.getEventDate());
				objOffIndSch.setStartTime(vOffAllSch.getStartTime());
				objOffIndSch.setEndTime(vOffAllSch.getEndTime());
				objOffIndSch.setToAgyLocId(vOffAllSch.getToAgyLocId());
				objOffIndSch.setSmsFlag(vOffAllSch.getSmsFlag());
				objOffIndSch.setEmailFlag(vOffAllSch.getEmailFlag());
				objOffIndSch.setSmsScheduleHoursBefore(vOffAllSch.getSmsScheduleHoursBefore());
				objOffIndSch.setEmailScheduleHoursBefore(vOffAllSch.getEmailScheduleHoursBefore());
				if (vOffAllSch.getInChargeStaffId() != null) {
					objOffIndSch.setInChargeStaffId(vOffAllSch.getInChargeStaffId().intValue());
				}
				objOffIndSch.setCommentText(vOffAllSch.getCommentText());
				objOffIndSch.setEventId(eventId.intValue());
				objOffIndSch.setEventClass(COMM);
				objOffIndSch.setEventStatus(SCH);
				objOffIndSch.setCreateUserId(vOffAllSch.getCreateUserId());
				objOffIndSch.setModifyUserId(vOffAllSch.getCreateUserId());
				objOffIndSch.setUnexcusedAbsenceFlag(vOffAllSch.getUnexcusedAbsenceFlag());
				objOffIndSch.setSeriesId(vOffAllSch.getSeriesId());
				objOffIndSch.setEventOutcome(vOffAllSch.getEventOutcome());
				// adding bean to list
				lstInsertOff.add(objOffIndSch);
				// Trigger call
				lstInsertOff = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(lstInsertOff);
				// Trigger call
				try {
					lstInsertOff = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(lstInsertOff);
				} catch (Exception e) {
					logger.error("offenderIndSchedulesT2Service:" + e);
				}
				// Trigger call
				for (int i = 0; i < lstInsertOff.size(); i++) {
					try {
						offenderIndSchedulesT3Service.getVnumRows(lstInsertOff.get(i));
					} catch (Exception e) {
						logger.error("offenderIndSchedulesT3Service:" + e);
					}
					// Trigger call
					offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(lstInsertOff.get(i), "INSERTING");
				}
				countOffIndSch = ocdclogsRepository.offIndSchInsert(lstInsertOff);

				if (countOffIndSch > 0) {
					count = 1;
				}

				if (eventId != null) {
					Long caseNoteId = null;
					// Procedure call
					tagContactLogService.insNoteSchAssociation(vOffAllSch.getOffenderBookId().longValue(), caseNoteId,
							eventId.longValue(), vOffAllSch.getCreateUserId());
				}

				// Post-Insert GET_CHECK_SUM
				checkSum = ocdclogsRepository.offSchPostInsert(eventId);
				vOffAllSch.setCheckSum(checkSum);
			}
		}
		return count;
	}

	/* offSchUpdate */
	public Integer offSchUpdate(List<VOffenderAllSchedules> updateList) {
		int count = 0;
		int upsch1 = 0;
		int upsch2 = 0;
		BigDecimal checkSum;
		Long nbtSchCouUp;
		String source = "OCDCSCH";

		for (VOffenderAllSchedules offsch : updateList) {
			VOffenderAllSchedules2 oldData = ocdclogsRepository.getOldData(offsch.getEventId().longValue());
			if (offsch.getEventDateCount() != offsch.getEventDate()) {
				nbtSchCouUp = ocdclogsRepository.checkForSheduleConflict(offsch);
				if (nbtSchCouUp != offsch.getNbtSchCount() && nbtSchCouUp > 0) {
					offsch.setNbtSchCount(nbtSchCouUp);
				}
			}

			if (offsch.getEventOutcomeCount() != offsch.getEventOutcome()) {
				// Procedure call
				tagMultipleFailureService.adjustUa(offsch, offsch.getCreateUserId());
			}
			// Procedure call
			tagMultipleFailureService.adjustUa(offsch, offsch.getCreateUserId());

			if (offsch.getEventOutcome() != null) {
				offsch.setEventStatus("COMP");
			} else {
				offsch.setEventStatus("SCH");
			}
			if(ApplicationConstants.YES.equalsIgnoreCase(offsch.getCancelFlag())) {
				offsch.setEventStatus("CANC");
			}

			if (offsch.getInChargeStaffName() != null && !"".equals(offsch.getInChargeStaffName().trim())) {
				BigDecimal staffId = ocdclogsRepository.inChargeStaffId(offsch.getInChargeStaffName());
				offsch.setInChargeStaffId(staffId);
			}
			// tag_contact_log.update_schedule #Procedure eid is not null
			if (offsch.getEventId() != null) {
				OffenderIndSchedules newData = new OffenderIndSchedules();
				BeanUtils.copyProperties(offsch, newData);
				if(offsch.getInChargeStaffId() != null) {
				newData.setInChargeStaffId(offsch.getInChargeStaffId().intValue());
				}
				if(offsch.getToInternalLocationId()!=null) {					
					newData.setToInternalLocationId(offsch.getToInternalLocationId().intValue());
				}
				OffenderIndSchedules oldData1 = new OffenderIndSchedules();
				BeanUtils.copyProperties(oldData, oldData1);
				oldData1.setOffenderBookId(oldData.getOffenderBookId().intValue());
				oldData1.setEventId(oldData.getEventId().intValue());
				offsch.setRecordSource("SCH");
				try {
					upsch1 = vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(newData, oldData1,
							offsch.getRecordSource(), source);
				} catch (Exception e) {
					logger.error("vOffenderAllSchedules2TuTrigger:" + e);
				}
				if (upsch1 > 0) {
					count = 1;
				}
			} else {
				OffenderIndSchedules newData = new OffenderIndSchedules();
				BeanUtils.copyProperties(offsch, newData);
				OffenderIndSchedules oldData1 = new OffenderIndSchedules();
				BeanUtils.copyProperties(oldData, oldData1);
				oldData1.setEventId(oldData.getEventId().intValue());
				try {
					upsch1 = vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(newData, oldData1,
							offsch.getRecordSource(), source);
				} catch (Exception e) {
					logger.error("vOffenderAllSchedules2TuTrigger:" + e);
				}
				if (upsch2 > 0) {
					count = 1;
				}
			}

			// Post-Update GET_CHECK_SUM
			checkSum = ocdclogsRepository.offSchPostInsert(offsch.getEventId());
			offsch.setCheckSum(checkSum);
			
			List<WeeklyActivityPlans> returnList = new ArrayList<WeeklyActivityPlans>();
			returnList = getWeeklyActivityPlanData(offsch);
			if(!returnList.isEmpty()) {	
				offsch.setScheduleType("SCH");
				saveWeeklyActivitePlanData(returnList,offsch);			
			}
		}	
		return count;
	}

	
	/* offSchDelete */
	public Integer offSchDelete(List<VOffenderAllSchedules> updateList) {
		int count = 0;
		int delSch = 0;
		int delVir = 0;
		String source = "OCDCSCH";

		for (VOffenderAllSchedules offSch : updateList) {
			// Procedure call
			tagContactLogService.delNoteSchAssociation(offSch.getOffenderBookId().longValue(), offSch.getEventId(),offSch.getModifyUserId());
			// Function call
			int okToDel = tagUtilsService.okToDeleteRecord(offSch.getEventId().intValue(), "OFFENDER_IND_SCHEDULES",
					"EVENT_ID", null, offSch.getCreateUserId());
			if (okToDel == 1) {
				if (offSch.getEventId() != null) {
					// Procedure call(deleting V_OFFENDER_ALL_SCHEDULES_2 table data)
					try {
						VOffenderAllSchedules2 bean = new VOffenderAllSchedules2();
						BeanUtils.copyProperties(offSch, bean);
						delSch = vOffenderAllSchedules2TdService.vOffenderAllSchedules2TdTgr(bean, bean,source);
					} catch (Exception e) {
						logger.error("vOffenderAllSchedules2TdTgr:" + e);
					}
					if (delSch > 0) {
						count = 1;
					} else {
						count = 3;
					}
				} else {
					// Procedure call(deleting V_OFFENDER_ALL_SCHEDULES_2 table data)
					try {
						VOffenderAllSchedules2 bean = new VOffenderAllSchedules2();
						BeanUtils.copyProperties(offSch, bean);
						delVir = vOffenderAllSchedules2TdService.vOffenderAllSchedules2TdTgr(bean, bean, source);
					} catch (Exception e) {
						logger.error("vOffenderAllSchedules2TdTgr:" + e);
					}
					if (delVir > 0) {
						count = 1;
					}
				}
			}
		}
		return count;
	}

	/**
	 * This method is used to grtting staffId
	 */
	public StaffMembers lvLoginUserStaffName(String userName) {
		return ocdclogsRepository.lvLoginUserStaffName(userName);

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgEventOutcomeRecordGroup(String threeip) {
		String eventType = null;
		String eventSubType = null;
		String nbtUpdOutcomeFlag = null;

		final String[] inputArray = threeip.split(",");
		if (inputArray.length > 0) {
			if (inputArray[0] != null && !inputArray[0].equals(""))
				eventType = inputArray[0];
			if (inputArray.length > 1 && inputArray[1] != null && !inputArray[1].equals(""))
				eventSubType = inputArray[1];
			if (inputArray.length > 2 && inputArray[2] != null && !inputArray[2].equals("")) {
				nbtUpdOutcomeFlag = (inputArray[2]);
				if (nbtUpdOutcomeFlag.equals("true")) {
					nbtUpdOutcomeFlag = YFLAG;
				} else {
					nbtUpdOutcomeFlag = NFLAG;
				}
			}
		}
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = ocdclogsRepository.rgEventOutcomeRecordGroup(eventType, eventSubType, nbtUpdOutcomeFlag);
		for (ReferenceCodes referenceCodes : returnList) {
			if (YFLAG.equals(referenceCodes.getActiveFlag())) {
				referenceCodes.setCanDisplay(true);
			} else {
				referenceCodes.setCanDisplay(false);
			}
		}
		return returnList;
	}

	///**
	// * This method is used to execute a record group
	// *
	// *@throws SQLException
	//*/
	public List<AgencyLocations> rgLocationRecordGroup() {
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = ocdclogsRepository.rgLocationRecordGroup();
		if (!returnList.isEmpty()) {
			for (AgencyLocations agencyLocations : returnList) {
				if ("Y".equals(agencyLocations.getActiveFlag())) {
					agencyLocations.setCanDisplay(true);
				} else {
					agencyLocations.setCanDisplay(false);
				}
			}
		}
		
		
		List<AgencyLocations> corpuratesList = ocdclogsRepository.getAllCorporates();
		if (corpuratesList != null && corpuratesList.size() > 0) {
			for (AgencyLocations agencyLocations : corpuratesList) {
				agencyLocations.setCanDisplay(false);
			}
			returnList.addAll(corpuratesList);
		}

		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		recordList = ocdclogsRepository.rgScheduleTypeRecordGroup();

		for (ReferenceCodes referenceCodes : recordList) {
			if (YFLAG.equals(referenceCodes.getActiveFlag())) {
				referenceCodes.setCanDisplay(true);
			} else {
				referenceCodes.setCanDisplay(false);
			}
		}
		return recordList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgScheduleSubTypeRecordGroup(final String eventTypeDesc) {
		List<ReferenceCodes> returnList = ocdclogsRepository.rgScheduleSubTypeRecordGroup(eventTypeDesc);
		if (!returnList.isEmpty()) {
			for (ReferenceCodes referenceCodes : returnList) {
				if (YFLAG.equals(referenceCodes.getActiveFlag())) {
					referenceCodes.setCanDisplay(true);
				} else {
					referenceCodes.setCanDisplay(false);
				}
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgnoteSourceRecordGroup() {
		List<ReferenceCodes> returnList = ocdclogsRepository.rgnoteSourceRecordGroup();
		returnList.forEach(element -> {
			if (NFLAG.equals(element.getActiveFlag())) {
				element.setCanDisplay(false);
			}
		});
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<StaffLocationRoles> rgStaffnameRecordGroup() {
		return ocdclogsRepository.rgStaffnameRecordGroup();
	}

	public String getModuleName(OffenderCaseNotes searchRecord) {
		String returnValue = ocdclogsRepository.getModuleName(searchRecord);
		if (returnValue == null) {
			returnValue = "nodata";
		}
		return returnValue;
	}

	public List<Areas> rglovOutComeRecordGroup() {
		List<Areas> returnList = ocdclogsRepository.rglovOutComeRecordGroup();
		Integer count = 0;
		for (final Areas areas : returnList) {
			count = count + 1;
			areas.setListSeq(count);
			areas.setCanDisplay(true);
		}
		return returnList;
	}

	@Override
	public Integer getStaffIdOne(Integer caseNoteId) {
		Integer staffId = null;
		return staffId = ocdclogsRepository.getStaffIdOne(caseNoteId);
	}

	@Override
	public String getcaseNoteTextData(OffenderCaseNotes caseNotesObj) {
		return ocdclogsRepository.getcaseNoteTextData(caseNotesObj);
	}

	@Override
	public String checkNonAssociations(VOffenderAllSchedulesCommitBean commitBean) {
		String returnMessage = "";
		if (commitBean != null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			OffenderCourseAttendance paramBean = new OffenderCourseAttendance();
			paramBean.setOffenderBookId(commitBean.getInsertList().get(0).getOffenderBookId().longValue());
			List<OffenderNonAssociations> nonAssociationList = ocdprogrRepository.checkNonAssociations(paramBean);
			if (!nonAssociationList.isEmpty()) {
				for (VOffenderAllSchedules vOffSch : commitBean.getInsertList()) {
				    if("Y".equals(vOffSch.getNonAssociationFlag())) {
					for (OffenderNonAssociations obj : nonAssociationList) {
						int count = 0;
						List<OffenderIndSchedules> offSchedules = ocdclogsRepository.checkOffenderScheduleConflicts(obj, vOffSch);
						if (offSchedules != null && offSchedules.size() > 0) {
							for (OffenderIndSchedules nonAssOffCourse : offSchedules) {
								String message = "";
								count++;
								if (count == 1) {
									VHeaderBlock bean = ocdprogrRepository
											.ocdprogrGetOffenderNames(obj.getNsOffenderBookId(),commitBean.getCreateUserId());
									message = bean.getLastName() + " " + bean.getFirstName() + ", "
											+ bean.getOffenderIdDisplay() + "\n";
								}
								java.util.Date stDate = nonAssOffCourse.getStartTime();
								java.util.Date enDate = nonAssOffCourse.getEndTime();
								java.util.Date apDate = nonAssOffCourse.getEventDate();
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								String startTime = stDate != null ? sdf.format(stDate) : "";
								String endTime = enDate != null ? sdf.format(enDate) : null;
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
								String appDate = sdf1 != null ? sdf1.format(apDate) : "";
								message = message + appDate + ", " + startTime + (endTime != null ? ", " + endTime : "")
										+ "\n";
								returnMessage = returnMessage + message;
							}
							returnMessage = returnMessage + "\n";
						}

						count=0;
						OffenderCourseAttendance search = new OffenderCourseAttendance();
						search.setAgyLocId(vOffSch.getToAgyLocId());
						search.setEventDate(vOffSch.getEventDate());
						search.setStartTime(vOffSch.getStartTime());
						search.setEndTime(vOffSch.getEndTime());
						List<OffenderCourseAttendance> nonAssOffCourseList = ocdprogrRepository.checkNonAssociationAppointments(search, obj);
						if(nonAssOffCourseList != null && nonAssOffCourseList.size()>0) {
							for (OffenderCourseAttendance nonAssOffCourse : nonAssOffCourseList) {
								String message = "";
								count++;
								if(count ==1) {
									VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(obj.getNsOffenderBookId(),commitBean.getCreateUserId());
									message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay() + "\n";
								}
								java.util.Date stDate = nonAssOffCourse.getStartTime();
								java.util.Date enDate = nonAssOffCourse.getEndTime();
								java.util.Date apDate = nonAssOffCourse.getEventDate();
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								String startTime = sdf.format(stDate);
								String endTime = sdf.format(enDate);
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
								String appDate = sdf1.format(apDate);
								message = message + appDate
										+ ", " + startTime + ", " + endTime + "\n";
								returnMessage = returnMessage + message;
							}
							returnMessage = returnMessage+ "\n";
						}
					}

				}
			  }
			}
		}

		if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {

			OffenderCourseAttendance paramBean = new OffenderCourseAttendance();
			paramBean.setOffenderBookId(commitBean.getUpdateList().get(0).getOffenderBookId().longValue());
			List<OffenderNonAssociations> nonAssociationList = ocdprogrRepository.checkNonAssociations(paramBean);
			if (!nonAssociationList.isEmpty()) {
				for (VOffenderAllSchedules vOffSch : commitBean.getUpdateList()) {
					if("Y".equals(vOffSch.getNonAssociationFlag())) {
					for (OffenderNonAssociations obj : nonAssociationList) {
						int count = 0;
						List<OffenderIndSchedules> offSchedules = ocdclogsRepository.checkOffenderScheduleConflicts(obj, vOffSch);
						if (offSchedules != null && offSchedules.size() > 0) {
							for (OffenderIndSchedules nonAssOffCourse : offSchedules) {
								String message = "";
								count++;
								if (count == 1) {
									VHeaderBlock bean = ocdprogrRepository
											.ocdprogrGetOffenderNames(obj.getNsOffenderBookId(),commitBean.getCreateUserId());
									message = bean.getLastName() + " " + bean.getFirstName() + ", "
											+ bean.getOffenderIdDisplay() + "\n";
								}
								java.util.Date stDate = nonAssOffCourse.getStartTime();
								java.util.Date enDate = nonAssOffCourse.getEndTime();
								java.util.Date apDate = nonAssOffCourse.getEventDate();
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								String startTime = stDate != null ? sdf.format(stDate) : "";
								String endTime = enDate != null ? sdf.format(enDate) : null;
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
								String appDate = sdf1 != null ? sdf1.format(apDate) : "";
								message = message + appDate + ", " + startTime + (endTime != null ? ", " + endTime : "")
										+ "\n";
								returnMessage = returnMessage + message;
							}
							returnMessage = returnMessage + "\n";
						}

						count=0;
						OffenderCourseAttendance search = new OffenderCourseAttendance();
						search.setAgyLocId(vOffSch.getToAgyLocId());
						search.setEventDate(vOffSch.getEventDate());
						search.setStartTime(vOffSch.getStartTime());
						search.setEndTime(vOffSch.getEndTime());
						List<OffenderCourseAttendance> nonAssOffCourseList = ocdprogrRepository.checkNonAssociationAppointments(search, obj);
						if(nonAssOffCourseList != null && nonAssOffCourseList.size()>0) {
							for (OffenderCourseAttendance nonAssOffCourse : nonAssOffCourseList) {
								String message = "";
								count++;
								if(count ==1) {
									VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(obj.getNsOffenderBookId(),commitBean.getCreateUserId());
									message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay() + "\n";
								}
								java.util.Date stDate = nonAssOffCourse.getStartTime();
								java.util.Date enDate = nonAssOffCourse.getEndTime();
								java.util.Date apDate = nonAssOffCourse.getEventDate();
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
								String startTime = sdf.format(stDate);
								String endTime = sdf.format(enDate);
								SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
								String appDate = sdf1.format(apDate);
								message = message + appDate
										+ ", " + startTime + ", " + endTime + "\n";
								returnMessage = returnMessage + message;
							}
							returnMessage = returnMessage+ "\n";
						}
					}
				}
			  }
			}
		}
		if ("".equals(returnMessage)) {
			return ApplicationConstants.EMPTYDATA;
		} else {
			return getFinalMessageString(returnMessage);
		}
	}

	public String getFinalMessageString(final String messageData) {
		return "ocdclogs.nonassociationconflictmsg \n\n"
				+ messageData + " \n ocdclogs.doyouwanttocontinue ";
	}
	
	@Override
	public List<EventMeasures> getEmailSmsFlag(VOffenderAllSchedules beanOne) {		
		return ocdclogsRepository.getEmailSmsFlag(beanOne);
	}

	@Override
	public String getCancelFlag(VOffenderAllSchedules beanOne) {
		return ocdclogsRepository.getCancelFlag(beanOne);
	}
	@Override
	public String checkCasenoteSubType(final String caseNoteType,final String caseNoteCode, final String userName) {
		return ocdclogsRepository.checkCasenoteSubType(caseNoteType, caseNoteCode, userName);

	}
	
	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	
	public List<WeeklyActivityPlans> getWeeklyActivityPlanData(VOffenderAllSchedules offsch) {	
		LocalDate localFromDate = convertToLocalDateViaInstant(offsch.getEventDate()); 
		LocalDate previousMonday = localFromDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate weekEndDate= previousMonday.plusDays(6);
		List<WeeklyActivityPlans> returnList = new ArrayList<WeeklyActivityPlans>();
		WeeklyActivityPlans searchBean=new WeeklyActivityPlans();
		searchBean.setOffenderBookId(offsch.getOffenderBookId());
		Date fromdate = Date.from(previousMonday.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date todate = Date.from(weekEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		searchBean.setFromDate(fromdate);
		searchBean.setToDate(todate);
		try {
			returnList = oweacplnRepository.getWeeklyActivity(searchBean);
		} catch (Exception e) {
			logger.error("Exception in " + this.getClass().getName() + " getWeeklyActivity", e);
		}
		return returnList;
	}
	
	 public Integer saveWeeklyActivitePlanData(List<WeeklyActivityPlans> returnList, VOffenderAllSchedules offsch) {
		Integer returnData=0;
		List<WeeklyActivityPlans> saveList=new ArrayList<WeeklyActivityPlans>();
		for (WeeklyActivityPlans wapActivityDate : returnList) {
			if((wapActivityDate.getEventId()!=null && offsch.getEventId()!=null && wapActivityDate.getEventId().compareTo(offsch.getEventId())==0) && wapActivityDate.getVersionNo().signum()!=0) {				
				wapActivityDate.setActivityStart(offsch.getStartTime());
				wapActivityDate.setActivityFinish(offsch.getEndTime());
				if(wapActivityDate.getVersionNo()!=null &&  offsch.getScheduleType().equals(wapActivityDate.getRecordSource()) &&!"CANC".equals(offsch.getEventOutcome())) {						
					if(wapActivityDate.getHtyVersionNo()!=null) {						
						wapActivityDate.setVersionNo(wapActivityDate.getHtyVersionNo().add(BigDecimal.ONE));
					} else {
						wapActivityDate.setVersionNo(wapActivityDate.getVersionNo());
					}
					wapActivityDate.setModifyUserId(offsch.getCreateUserId());
					wapActivityDate.setFinalized("N");
					if(offsch.getScheduleType().equals("SCH") || offsch.getScheduleType().equals("CE") || offsch.getScheduleType().equals("ACPA")) {						
						wapActivityDate.setActivityAddress(ocdclogsRepository.getAgencyLocationDescription(offsch.getToAgyLocId()));
					}
					saveList.add(wapActivityDate);
				}
				else if(!offsch.getScheduleType().equals(wapActivityDate.getRecordSource())){
					if(wapActivityDate.getHtyVersionNo()!=null) {							
						wapActivityDate.setVersionNo(wapActivityDate.getHtyVersionNo().add(BigDecimal.ONE));
					} else {
						wapActivityDate.setVersionNo(wapActivityDate.getVersionNo());
					}
					wapActivityDate.setModifyUserId(offsch.getCreateUserId());
					wapActivityDate.setFinalized("N");
					saveList.add(wapActivityDate);
				}
			}  else {
				if(wapActivityDate.getHtyVersionNo()!=null) {							
					wapActivityDate.setVersionNo(wapActivityDate.getHtyVersionNo().add(BigDecimal.ONE));
				} else {
					wapActivityDate.setVersionNo(wapActivityDate.getVersionNo());
				}
				wapActivityDate.setModifyUserId(offsch.getCreateUserId());
				wapActivityDate.setFinalized("N");
				saveList.add(wapActivityDate);
			}
			
		}
		if(!saveList.isEmpty()) {					
		 returnData=oweacplnRepository.weeklyActivityCommitUpdateData(saveList);
		}
		return returnData;
	}

}
