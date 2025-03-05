package net.syscon.s4.pkgs.tag_programmes.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderPrgObligationHty;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesRepository;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;
import net.syscon.s4.pkgs.tag_schedule.TagScheduleService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.pkgs.tag_service_sched.TagServiceSchedService;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfService;
import net.syscon.s4.triggers.OffenderPrgObligationsT1Service;
import net.syscon.s4.triggers.OffenderProgramProfilesTrService;

/*
 * Below comments are copied from package TAG_PROGRAMMES
||    Purpose: This package provides procedures for programmes.
||
||    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
||    --------------------
||    Person                   DATE       Version  Comments
||    --------------------------------------------------------------------------------------------------------------
||    Erin                 24-Feb-2014       2.36  HPQC#21426: modified ALLOCATE_COURSE_TO_OFFENDER and CREATE_ALLOCATION to allow users to create past allocations through OIDPWAIT
||    Abhishek             13-DEC-2012       2.35  HPQC#18587. Call Update_status function to update Offender Program status.
||    Rose                 21-JUL-2010       2.34  HPQC#3257. Work Release, Created procedure update_prg_decision_date_WR, if program status is changed to 'ACCEP' or 'REJ', update DECISION_DATE as well.
||    Anand		         21-05-2010        2.33  Issue#2267 Modified Cursor "get_phase_cur" in get_program_info Procedure.
||    Manjul	         04-11-2009        2.32  Removed Create public synonym statement from pkg in last 
||    Naveen	         05-10-2009        2.31  Created procedure create_off_WR_return_schedule, set_off_WR_schedule_status
||                                                 and create_off_a_WR_schedule          
||    Manjul	         30-09-2009        2.30  Modified procedure update_obligation_WR
||    Manjul	         12-08-2009        2.29  Added procedure update_obligation_WR
||    Erin                 21-08-2007        2.28  Fix version label
||    Rajshree             05-06-2007        2.27  6808,6821,6789 Added check_active_sa.
||    Rajshree             03-05-2007        2.26  #6555 Added check_app_sa,check_appointments_sa.
||    Claus                26-04-2007        2.25  D# 6558. Modified check_appointments_acts, check_course_profiles_acts and
||                                                 delete_obligations.
||    Rajshree             19-04-2007        2.24  # 6372 .Removed get_condition_status.
||    Rajshree             18-04-2007        2.23  # 6372 .Fixed :-get_adjustment_detail Changed C - D, D- C.
||    Rajshree             16-04-2007        2.22  # 6372 .Fixed peer review issue :-get_adjustment_detail
||    Rajshree             29-Mar-2007       2.21  # 6372 Added code for specified activity allocation.
||                                                 Added get_obligation_adjs_id, get_adjustment_detail,get_condition_status ,get_crs_schedule_count
||    Neil                 21-Mar-2007       2.20  D#6306 For retrospective data entry: Added check_module_allocated, check_session_allocated,
||                                                 delete_opp_attendances, create_opp_attendances and get_schedule_status..
||    Neil                 05-Jan-2007       2.19  D#5618. Added check_appontments_acts and check_course_profiles_acts.
||    Neil                 07-Dec-2006       2.18  D#5536 Added check_phases_completed and added p_ession_no argument
||                                                 to update_off_crs_attendances.
||    Neil                 16-Nov-2006       2.17  D#5559 Added check_course_profiles and check_appointments.
||    Neil                 14-Nov-2006       2.16  D#5508 check event outcome is NULL rather than NOT NULL
||    GJC                  14-Oct-2006       2.15  SHOW_VERSION changed from procedure to function
||    Neil                 13-Oct-2006       2.14  Added update_off_crs_attendances
||    Adekoya              07-Sep-2006       2.13   ACP Iteration 3. D# 4213. Amend get_course_schedule
||    Erin                 07-Sep-2006       2.12  Remove Event_Outcome from PROCEDURE ins_off_crs_att_catchup
||    Neil                 05-Sep-2006       2.11  ACP Iteration 3. destroy module_allocation now removes associated
||                                                 attendance records.
||    Adekoya              04-Sep-2006       2.10  ACP Iteration 3. D# 4213. Add get_event_id
||    Erin                 31-Aug-2006       2.9   ACP Iteration 3. Added FUNCTION ins_course_schedules_catchup and PROCEDURE ins_off_crs_att_catchup
||    Neil                 31-Aug-2006       2.8   ACP Iteration 3. Changed routines to take account of catch-up.
||    Adekoya              30-Aug-2006       2.7   ACP Iteration 3. D# 4213. Add get_course_schedule,
||                                                 lock_offend_course_attend, get_off_course_attend_checksum
||    Claus                23-Aug-2006       2.6   ACP Iteration 3. D# 4170. Modified check_attendance_outcomes.
||    Claus                23-Aug-2006       2.5   ACP Iteration 3. D# 4170. Added valid_allocation_end_date,
||                                                 check_attendance_outcomes and modified check_active_obligation.
||    D Rice               17-Aug-2006      2.4   ACP Iteration 2.  Defect# 4062 - New cursor: get_phase_cur added
||    Neil                 10-Aug-2006       2.3   Version for ACP Iteration 2 (contd.)
||    Neil                 03-Aug-2006       2.2   Version for ACP Iteration 2.
||    GJC                  20-Jun-2006       2.1   Initial version for work package
*/ 
@Service
public class TagProgrammesServiceImpl implements TagProgrammesService {

	@Autowired
	private TagProgrammesRepository tagProgrammesRepository;

	@Autowired
	private TagScheduleService tagScheduleService;

	@Autowired
	private TagServiceService tagServiceService;

	@Autowired
	private TagServiceSchedService tagServiceSchedService;
	@Autowired
	private OffenderPrgObligationsT1Service offenderPrgObligationsT1Service;

	@Autowired
	private OffenderCourseAttndnsTwfService offenderCourseAttndnsTwfService;

	@Autowired
	private OffenderProgramProfilesTrService offenderProgramProfilesTrService;

	private static final String PRG_MOD = "PRG_MOD";
	private static final String PRG_PH = "PRG_PH";
	private static final String CRS_MOD = "CRS_MOD";
	private static final String CRS_PH = "CRS_PH";
	private static final String Y = "Y";
	private static final String INSERTING = "INSERTING";

	private static Logger logger = LogManager.getLogger(TagProgrammesServiceImpl.class.getName());

	@Override
	public void cancelAttendForSchedule(final Long crsSchId, final String userName) {
		final List<OffenderCourseAttendance> list = tagProgrammesRepository.selectCrsSchId(crsSchId);
		if (!list.isEmpty()) {
			for (final OffenderCourseAttendance sbean : list) {
				tagProgrammesRepository.updateOffCourseAttendance(crsSchId, userName);
			}
		}
	}

	@Override
	public void updateCourseAttendanceDates(final VAcpSchedules vacp, final String userName) {
		final List<OffenderCourseAttendance> list = tagProgrammesRepository.ocaCur(vacp.getCrsSchId());
		list.forEach(data -> {
			tagProgrammesRepository.updateOffenderCourseAttendance(data.getCrsSchId(), vacp, userName);
		});

	}

	@Override
	public Integer updateStatus(final OffenderPrgObligationHty searchBean) {
		OffenderPrgObligationHty oldRecord = null;
		tagProgrammesRepository.getXFromOffPrgObligations(searchBean.getOffenderPrgObligationId().longValue());
		List<OffenderPrgObligationHty> list = tagProgrammesRepository
				.getOldRecordForUpdateStatus(searchBean.getOffenderPrgObligationId());
		if (list != null)
			oldRecord = list.get(0);
		tagProgrammesRepository.updateStatusUpdateOffPrgObli(searchBean.getStatus(), searchBean.getStatusChangeReason(),
				searchBean.getStatusChangeDate(), searchBean.getOffenderPrgObligationId().longValue(),
				searchBean.getCreateUserId());
		OffenderPrgObligations newRec = new OffenderPrgObligations();
		BeanUtils.copyProperties(searchBean, newRec);
		newRec.setOffenderPrgObligationId(searchBean.getOffenderPrgObligationId().longValue());
		OffenderPrgObligations oldRec = new OffenderPrgObligations();
		BeanUtils.copyProperties(oldRecord, oldRec);
		oldRec.setOffenderPrgObligationId(oldRecord.getOffenderPrgObligationId().longValue());
		offenderPrgObligationsT1Service.offenderPrgObligationsT1(newRec, oldRec);
		return 1;

	}

	@Override
	public void insertOffenderCourseAttendances(final OffenderCourseAttendance bean1) {
		final OffenderCourseAttendance offCrsAtt = tagProgrammesRepository.selectOffCourseAtt(bean1);
		tagProgrammesRepository.insertOffenderCourseAttendance(offCrsAtt);
	}

	@Override
	public void createOffWRReturnSchedule(final Integer eventId) {
		tagProgrammesRepository.createOffWRReturnSchedule(eventId);
	}

	@Override
	public Integer lockOffPrgObligationId(final Long pOffenderPrgObligationId, final Long pCheckSum) {
		Long vCs;
		Integer count = 0;
		try {
			final List<OffenderPrgObligations> objList = tagProgrammesRepository
					.getOffPrgOblDetails(pOffenderPrgObligationId);
			OffenderPrgObligations obj = objList.get(0);
			if (obj.getModifyDatetime() != null) {
				vCs = tagScheduleService.checkSum(obj.getModifyDatetime());
			} else {
				vCs = tagScheduleService.checkSum(obj.getCreateDatetime());
			}
			if (!(vCs.equals(pCheckSum))) {
			}
			count = 1;
		} catch (Exception e) {
			logger.error("lockOffPrgObligationId", e);
			count = 0;
		}
		return count;

	}

	@Override
	@Transactional
	public void allocateCourseToOffender(final VOffenderPrgObligations vOff, final Integer serTabCount,
			final String userName) {

		ProgramServices lvPgm = new ProgramServices();
		List<CourseActivities> couAct = new ArrayList<CourseActivities>();
		Long lvParentOffPrgrefId = null;
		Long lvProgramId = null;
		Date lvStartDate = null;
		Date lvEndDate = null;
		List<CourseActivities> lvSEProList = null;
		lvParentOffPrgrefId = createAllocation(serTabCount!=null?serTabCount.longValue():null, vOff.getOffenderBookId().longValue(),
				vOff.getOffenderPrgObligationId().longValue(), null, null, null, userName);
		lvSEProList = tagServiceService.getCrsDetails(serTabCount!=null?serTabCount.longValue():null, lvStartDate, lvEndDate, lvProgramId);
		for (final CourseActivities courseActivities : lvSEProList) {
			lvStartDate = courseActivities.getScheduleStartDate();
			lvEndDate = courseActivities.getScheduleEndDate();
			lvProgramId = courseActivities.getProgramId();
		}
		lvPgm = tagServiceService.getPrgSrvDetails(lvProgramId);
		if ("Y".equals(lvPgm.getModuleFlag())) {
			couAct = tagProgrammesRepository.modCur(serTabCount!=null?serTabCount.longValue():null);
			for (final CourseActivities courActy : couAct) {
				createAllocation(courActy.getCrsActyId(), vOff.getOffenderBookId().longValue(),
						vOff.getOffenderPrgObligationId().longValue(), lvParentOffPrgrefId, null, null, userName);
			}
		}

	}

	@Override
	public Long createAllocation(final Long pCrsActyId, final Long pOffenderBookId, final Long pOffenderPrgObligationId,
			final Long pParentOffPrgrefId, final Long pSessionNo, final Date pScheduleDate, final String userName) {
		CourseActivities lvCrs = new CourseActivities();
		Date lvScheduleStartDate = null;
		Date lvScheduleEndDate = null;
		Long lvProgramId = null;
		Long lvStartSessionNo = null;
		Long lvProgramOffPrgrefId = null;
		Long lvOffPrgrefId = null;
		final Date sysDate = new Date();
		lvCrs = tagServiceService.getCrsDetails(pCrsActyId);
		lvScheduleStartDate = lvCrs.getScheduleStartDate();
		lvScheduleEndDate = lvCrs.getScheduleEndDate();
		lvProgramId = lvCrs.getProgramId();

		if (CRS_PH.equals(lvCrs.getCourseClass())) {
			if (pSessionNo != null) {
				lvStartSessionNo = pSessionNo;
				lvScheduleStartDate = pScheduleDate;
			} else {
				lvStartSessionNo = 1l;
			}
		} else if (CRS_MOD.equals(lvCrs.getCourseClass())) {
			lvStartSessionNo = tagServiceSchedService.getModStartSessionNumber(pCrsActyId, lvCrs.getListSeq(),
					lvCrs.getParentCrsActyId());
		}
		lvProgramOffPrgrefId = getPrgProfile(pOffenderPrgObligationId, lvProgramId);
		lvOffPrgrefId = tagProgrammesRepository.getRetOffPrgRefId();
		OffenderProgramProfiles obj = new OffenderProgramProfiles();
		obj.setCrsActyId(pCrsActyId);
		offenderProgramProfilesTrService.offenderProgramProfilesTr(obj);
		tagProgrammesRepository.insertOffProgramProfiles(lvOffPrgrefId, pOffenderBookId, lvProgramId, pCrsActyId,
				pOffenderPrgObligationId, pParentOffPrgrefId, lvProgramOffPrgrefId, lvScheduleStartDate,
				lvStartSessionNo, lvScheduleEndDate, userName);
		updateStatus(pOffenderPrgObligationId, userName);

		if (lvScheduleStartDate!=null && lvScheduleStartDate.compareTo(sysDate) <= 0) {
			createOppAttendances(lvOffPrgrefId, userName);

		}
		return lvOffPrgrefId;
	}

	@Override
	public void createOppAttendances(final Long lvOffPrgrefId, final String userName) {
		tagProgrammesRepository.insertCreateOppAttendances(lvOffPrgrefId, userName);
	}

	@Override
	public Long getPrgProfile(final Long pOffenderPrgObligationId, final Long lvProgramId) {
		return tagProgrammesRepository.getPrgProfile(pOffenderPrgObligationId, lvProgramId);
	}

	@Override
	public void updateStatus(final Long pOffenderPrgObligationId, final String userName) {
		tagProgrammesRepository.updateStatusSelect(pOffenderPrgObligationId);
		List<OffenderPrgObligations> oldList = tagProgrammesRepository.getOldRecOffenderPrgObligations(new BigDecimal(pOffenderPrgObligationId));
		tagProgrammesRepository.updateStatusUpOperation(pOffenderPrgObligationId, userName);
		OffenderPrgObligations newRec = new OffenderPrgObligations();
		OffenderPrgObligations oldRec = oldList.get(0);
		newRec.setOffenderPrgObligationId(pOffenderPrgObligationId);
		newRec.setCreateUserId(userName);
		newRec.setStatus("ALLOC");
		offenderPrgObligationsT1Service.offenderPrgObligationsT1(newRec, oldRec);
	}

	@Override
	public void adjustAllocationDates(final Long pCrsActyId, final String pCourseClass, final Date pScheduleStartDate,
			final Date pScheduleEndDate, final Long pListSeq, final Long pParentCrsActyId, final String userName) {
		Date lvBigDate = null;
		final String valuee = "01/01/9000";
		try {
			lvBigDate = new SimpleDateFormat("dd/MM/yyyy").parse(valuee);
		} catch (ParseException e) {
			logger.error(e);
		}
		Long lvStartSessionNo = null;
		Date lvOffenderStartDate = null;
		Date lvOffenderEndDate = null;
		if (pCourseClass.equals(CRS_PH)) {
			lvStartSessionNo = 1l;
		} else if (pCourseClass.equals(CRS_MOD)) {
			lvStartSessionNo = tagServiceSchedService.getModStartSessionNumber(pCrsActyId, pListSeq, pParentCrsActyId);
		}
		final List<OffenderProgramProfiles> alloCur = tagProgrammesRepository.allocCur(pCrsActyId);
		for (int i = 0; i < alloCur.size(); i++) {
			final OffenderProgramProfiles data = alloCur.get(i);
			if (data.getStartSessionNo() == lvStartSessionNo) {
				lvOffenderStartDate = pScheduleStartDate;
			} else {
				lvOffenderStartDate = tagServiceSchedService.getScheduleDateForSession(pCrsActyId,
						data.getStartSessionNo());
			}
			if (pCourseClass.equals(CRS_PH) && data.getSealFlag().equals(Y)) {
				lvOffenderEndDate = getAllocationEndDate(data.getOffPrgrefId());
			} else {
				lvOffenderEndDate = pScheduleEndDate;
			}
			if ((((data.getOffenderStartDate() != null) ? data.getOffenderStartDate() : lvBigDate)
					.compareTo((lvOffenderStartDate != null) ? lvOffenderStartDate : lvBigDate) != 0)
					|| (((data.getOffenderEndDate() != null) ? data.getOffenderEndDate() : lvBigDate)
							.compareTo((lvOffenderEndDate != null) ? lvOffenderEndDate : lvBigDate) != 0)) {
				tagProgrammesRepository.updateOffenderProgramProfiles(data.getOffPrgrefId(), lvOffenderStartDate,
						lvOffenderEndDate, userName);
			}

		}

		return;
	}

	@Override
	public Date getAllocationEndDate(Long pOffPrgrefId) {
		Date lvBigDate = null;
		final String valuee = "01/01/9000";
		try {
			lvBigDate = new SimpleDateFormat("dd/MM/yyyy").parse(valuee);
		} catch (ParseException e) {
			logger.error(e);
		}
		Date lvEndDate;
		lvEndDate = tagProgrammesRepository.endCur(pOffPrgrefId, lvBigDate);
		if (lvEndDate.compareTo(lvBigDate) == 0) {
			lvEndDate = null;
		}
		return lvEndDate;
	}

	@Override
	public OffenderCourseAttendance getProgramInfo(final Long offPrgrefId) {
		final OffenderCourseAttendance bean = new OffenderCourseAttendance();
		final Map<String, Object> outParams = new HashMap<String, Object>();
		List<ProgramServices> prgServicesList = new ArrayList<ProgramServices>();
		List<ProgramServices> prgIdNdDescList = null;
		Long pModuleId = null;
		String pModuleDescription = null;

		Long pPhaseId = null;
		String pPhaseDescription = null;

		prgServicesList = tagProgrammesRepository.getPgmCur(offPrgrefId);
		for (final ProgramServices programServices : prgServicesList) {
			if (PRG_MOD.equals(programServices.getProgramClass())) {
				pModuleId = programServices.getProgramId();
				pModuleDescription = programServices.getDescription();
				prgIdNdDescList = tagProgrammesRepository.getPhaseCur(programServices.getParentOffPrgrefId());
				for (final ProgramServices prgSer : prgIdNdDescList) {
					pPhaseId = prgSer.getProgramId();
					pPhaseDescription = prgSer.getDescription();
				}
			} else if (PRG_PH.equals(programServices.getProgramClass())) {
				pPhaseId = programServices.getProgramId();
				pPhaseDescription = programServices.getDescription();
			}
		}
		outParams.put("P_PHASE_ID", pPhaseId);
		outParams.put("P_PHASE_DESCRIPTION", pPhaseDescription);
		outParams.put("P_MODULE_ID", pModuleId);
		outParams.put("P_MODULE_DESCRIPTION", pModuleDescription);
		bean.setPhaseId(pPhaseId != null ? pPhaseId.intValue() : null);
		bean.setModuleId(pModuleId != null ? pModuleId.intValue() : null);
		return bean;
	}

	@Override
	public BigDecimal insCourseSchedulesCatchup(final OffenderCourseAttendance courseSchedules) {
		BigDecimal crsSchId = null;
		try {
			crsSchId = tagProgrammesRepository.getCrsSchId();
			courseSchedules.setCrsSchId(crsSchId);
			tagProgrammesRepository.insertCourseSchedules(courseSchedules);
		} catch (Exception e) {
			logger.error("insCourseSchedulesCatchup", e);
		}
		return crsSchId;
	}

	@Override
	public Date getOffenderDates(final Long offenderBookId) {
		return tagProgrammesRepository.getOffenderDates(offenderBookId);
	}

	@Override
	public CourseSchedules getCourseScheduleRec(final Long pCrsSchId) {
		return tagProgrammesRepository.getCourseScheduleRec(pCrsSchId);
	}

	@Override
	public Long getOffCourseAttendChecksum(final Long eventId) {
		return tagProgrammesRepository.getOffCourseAttendChecksum(eventId);
	}

	@Override
	public Integer updateObligationWr(Long offenderBookId,String user) {
		Integer result = 0;
		List<OffenderPrgObligations> lvOffenderPrgObligatioIdList = tagProgrammesRepository
				.offPrgProfCur(offenderBookId);
		try {
			if (!lvOffenderPrgObligatioIdList.isEmpty()) {
				for (OffenderPrgObligations refObject : lvOffenderPrgObligatioIdList) {
					refObject.setCreateDatetime(new Date());
					if (Optional.ofNullable(refObject).isPresent()
							&& Optional.ofNullable(refObject.getOffenderPrgObligationId()).isPresent()) {
						result = tagProgrammesRepository.updateOffenderProgPros(offenderBookId,
								refObject.getOffenderPrgObligationId(), refObject);
					} else {
						result = 0;
						return result;
					}
				}
			}

			result = tagProgrammesRepository.updateOffenderPrgOblig(offenderBookId);
		} catch (Exception e) {
			result = 0;
			logger.error("Error: This resource is currently locked by another user.", e);
		}

		return result;
	}

	@Override
	public Integer insOffCrsAttCatchup(final OffenderCourseAttendance offCouAttds) {
		Integer retValue = 0;
		OffenderCourseAttendances newObj = new OffenderCourseAttendances();
		retValue = tagProgrammesRepository.insertOffenderCourseAttendancesForCatchup(offCouAttds);
		BeanUtils.copyProperties(offCouAttds, newObj);
		offenderCourseAttndnsTwfService.offenderCourseAttndnsTwf(newObj, INSERTING);
		return retValue;
	}

	@Override
	public OffenderProgramProfiles getListSeqRange(final OffenderProgramProfiles offPrg) {
		return tagProgrammesRepository.getListSeqRange(offPrg);
	}

	@Override
	public void createProgramProfile(final Long programId, final Long offenderPrgObligationId,
			final Long offenderBookId, final String userName) {
		List<ProgramServices> prgServiceList = new ArrayList<ProgramServices>();
		Map<Long, Long> lvOffPrgrefIdTab = new LinkedHashMap<Long, Long>();
		List<Long> lvProgramIdTab = new LinkedList<Long>();
		List<Long> lvParentOffPrgrefIdTab = new LinkedList<Long>();
		try {
			prgServiceList = tagProgrammesRepository.pgmCurList(programId);
			for (ProgramServices programServices : prgServiceList) {
				lvOffPrgrefIdTab.put(programServices.getProgramId(), programServices.getOffPrgrefId());
				lvProgramIdTab.add(programServices.getProgramId());
				lvParentOffPrgrefIdTab.add(programServices.getParentProgramId() != null
						? lvOffPrgrefIdTab.get(programServices.getParentProgramId().longValue())
						: null);
			}
			if (lvOffPrgrefIdTab != null && lvOffPrgrefIdTab.size() > 0) {
				List<Long> values = new LinkedList<Long>();
				for (Map.Entry<Long, Long> keySet : lvOffPrgrefIdTab.entrySet()) {
					values.add(keySet.getValue());
				}

				for (int i = 0; i < values.size(); i++) {
					OffenderProgramProfiles offPrg = new OffenderProgramProfiles();
					offPrg.setOffPrgrefId((values.get(i)));
					offPrg.setProgramId(lvProgramIdTab.get(i));
					offPrg.setParentOffPrgrefId(lvParentOffPrgrefIdTab.get(i));
					offPrg.setOffenderBookId(offenderBookId);
					offPrg.setOffenderPrgObligationId(offenderPrgObligationId);
					offPrg.setCreateUserId(userName);
					offenderProgramProfilesTrService.offenderProgramProfilesTr(offPrg);
					tagProgrammesRepository.insertOffenderProgramProfiles(offPrg);
				}
			}
		} catch (Exception e) {
			logger.error("createProgramProfile", e);
		}
	}

	@Override
	public Boolean checkAllocationExists(final OffenderProgramProfiles offPrg) {
		Integer count = tagProgrammesRepository.checkAllocationExists(offPrg);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public Boolean checkAttendanceOutcomes(final Long offPrgrefId) {
		Integer count = tagProgrammesRepository.checkAttendanceOutcomes(offPrgrefId);
		if (count > 0)
			return false;
		return true;
	}

	@Override
	public Boolean checkAttendanceTaken(final Long offPrgrefId, final Date endDate) {
		Integer count = tagProgrammesRepository.checkAttendanceTaken(offPrgrefId, endDate);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public Integer cancelModuleAllocations(final OffenderProgramProfiles offPrg) {
		offenderProgramProfilesTrService.offenderProgramProfilesTr(offPrg);
		tagProgrammesRepository.updateCancelModuleAllocations(offPrg);
		return 0;
	}

	@Override
	public Integer endModuleAllocations(final OffenderProgramProfiles offPrg) {
		try {
			offenderProgramProfilesTrService.offenderProgramProfilesTr(offPrg);
			Date lvBigDate = null;
			Calendar cal = Calendar.getInstance();
			cal.set(9000, 01, 01, 0, 0, 0);
			lvBigDate = cal.getTime();
			offPrg.setCreateDatetime(lvBigDate);
			tagProgrammesRepository.updateEndModuleAllocations(offPrg);
		} catch (Exception e) {
			logger.error("endModuleAllocations", e);
		}
		return 0;
	}

	@Override
	public OffenderProgramProfiles getAllocationListSeqRange(OffenderProgramProfiles offPrg) {
		return tagProgrammesRepository.getAllocationListSeqRange(offPrg);
	}

	@Override
	public Boolean checkSessionAllocated(OffenderProgramProfiles offPrg) {
		Integer count = tagProgrammesRepository.checkSessionAllocated(offPrg);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public Boolean checkModuleAllocated(OffenderProgramProfiles offPrg) {
		Integer count = tagProgrammesRepository.checkModuleAllocated(offPrg);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public Boolean validAllocationEndDate(OffenderProgramProfiles offPrg) {
		Integer count = tagProgrammesRepository.validAllocationEndDate(offPrg);
		if (count > 0)
			return false;
		return true;
	}

	@Override
	public Integer deleteOppAttendances(OffenderProgramProfiles offPrg) {
		List<OffenderCourseAttendances> offCouList = new ArrayList<OffenderCourseAttendances>();
		try {
			offCouList = tagProgrammesRepository.attCur(offPrg);
			for (final OffenderCourseAttendances offCou : offCouList) {
				offCou.setModifyUserId(offPrg.getModifyUserId());
				tagProgrammesRepository.deleteOffenderCourseAttendances(offCou);
			}
		} catch (Exception e) {
			logger.error("deleteOppAttendances", e);
		}
		return 0;
	}

	@Override
	public Integer updateModuleAllocations(final OffenderProgramProfiles offPrg) {
		List<CourseActivities> couActyList = new ArrayList<CourseActivities>();
		OffenderProgramProfiles offPrgOne = new OffenderProgramProfiles();
		try {
			couActyList = tagProgrammesRepository.crsCur(offPrg.getCrsActyId());
			for (CourseActivities courseActivities : couActyList) {
				if (courseActivities.getListSeq() >= offPrg.getModuleFrom()
						&& courseActivities.getListSeq() <= offPrg.getModuleTo()) {

					offPrgOne.setOffenderPrgObligationId(offPrg.getOffenderPrgObligationId());
					offPrgOne.setCrsActyId(offPrg.getCrsActyId());
					offPrgOne.setParentOffPrgrefId(offPrg.getOffPrgrefId());
					if (!checkAllocationExists(offPrgOne)) {
					} else {
						destroyModuleAllocation(offPrg.getOffenderPrgObligationId(), courseActivities.getCrsActyId(),
								offPrg.getOffPrgrefId(),offPrg.getModifyUserId());
					}
				}
			}
		} catch (Exception e) {
			logger.error("updateModuleAllocations", e);
		}
		return 0;
	}

	private void destroyModuleAllocation(final Long offenderPrgObligationId, final Long crsActyId,
			final Long parentOffPrgrefId,String modifyUserId) {
		tagProgrammesRepository.offenderCourseAttendancesDelete(offenderPrgObligationId, crsActyId, parentOffPrgrefId,modifyUserId);
		tagProgrammesRepository.offenderProgramProfilesDelete(offenderPrgObligationId, crsActyId, parentOffPrgrefId,modifyUserId);
	}

}
