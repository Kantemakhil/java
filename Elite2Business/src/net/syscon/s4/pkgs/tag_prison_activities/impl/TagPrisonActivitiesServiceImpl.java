package net.syscon.s4.pkgs.tag_prison_activities.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.osinames.OsinamesPkgService;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentService;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesRepository;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesService;
import net.syscon.s4.triggers.OffenderProgramProfilesTrService;

/*
 * Below comments are copied from package TAG_PRISON_ACTIVITIES
 * Purpose: This package provides procedures for Prison Activities.
||
||    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
||    --------------------
||    Person                   DATE       Version   Comments
||    ---------            ---------     ---------  -----------------------------------
||    Erin                 17-Sep-2015   2.30       HPQC#24519:Modified code to fix the issues with SUSPEND/END while offender gets transferred to another facility.
||                                                             Modified procedures end_waitlist_and_allocations, suspend_allocations and check_waitlist_and_allocations. 
||    Steve                09-Jan-2013   2.29       HPQC#18824 Modified function GET_BOOKING_DATE to return DATE instead of varchar2.
||    Steve                07-May-2012   2.28       HPQC#12217 Modified chk_waitlist_2 to exclude the current record it is checking.
||    Anurag               17-JAN-2012   2.27       HPQC#11393 Corrected the version.
||    Amitesh              13-JAN-2012   2.26       HPQC#11393 Added comment to procecedure end_waitlist_and_allocations.
||    Amitesh              06-JAN-2012   2.25       HPQC#11393 Changed procedure end_waitlist_and_allocations. Modified
||                                                             cursor query c_lock. link program_services with offender_program_profiles via
||                                                             program_id and exclude  the program_category 'PWS'.
||    Rajesh               15-DEC-2011   2.24       HPQC#11393 Changed procedure end_waitlist_and_allocations. Added one more table-program_services
||                                                             into cursor query c_lock. link program_services with offender_program_profiles via
||                                                             program_id and exclude  the program_category 'PWS'.
||    Steve                16-DEC-2008   2.23       Tr#7342 Replaced PA with IA when inserting into offender_course_attendances.event_sub_type
      Sarah                08-Jul-2007   2.22       #4202 : Modified backdate_attendances to change event_type  from PRISON_ACT to INST_ACT
                                                                     and removed the hardcoded code= 'Standard' from get_default_ref_codes to  get the record
                                   with seq=1
||    Rajshree             07-MAR-2007   2.21       #5885: Modified functions disp_acty_desc /get_services.
||    Erin                 01-MAR-2007   2.20       #5980: Modified  PROCEDURE chk_waitlist_2
||    Surya                21-NOV-2006   2.19       TD 5686:Modified backdate_attendances:
||                                                  Added agy_loc_id/internal_location_id
||                                                  as the course attendance records were not showing up
||                                                  in internal movements screen.
||                                                  Removed the version history from Spec as it is not required.
||    GJC                  14-Oct-2006   2.18       SHOW_VERSION changed from procedure to function
||    GJC                  18-Aug-2006   2.17       #4128: Check if allocated when placed on wait list
||    Erin                 26-Jul-2006   2.16       #3540: Added FUNCTION get_bulk_allocate, PROCEDURE populate_bulk_allocate,
||                                                  FUNCTION return_bulk_allocate_tab, PROCEDURE flush_bulk_allocate, PROCEDURE bulk_update
||    GJC                  17-MAY-2006   2.13       Add procedure suspend_allocations
||    GJC                  17-MAY-2006   2.12       Add procedure end_waitlist_and_allocations
||    GJC                  15-May-2006   2.11       Defect 1841, handle dups in check routine get_schedule_details
||    GJC                  25-Apr-2006   2.10       refix checksum routines
||    Aasim                23-Apr-2006   2.9        populate_activity ammended so it can also retrieve data for End Reasons.
||    Aasim                23-Apr-2006   2.8        RTN_IEP_LEVEL returning varchar when should return NUMBER.
||    Erin                 21-Apr-2006   2.6        Modifications for IEP level (Aff rtn_iep_seq and pris_iep_level)
||    Erin                 20-Apr-2006   2.5        Clean-up duplicate funstions, Added Backdate_Attendances
||    Erin                 20-Apr-2006   2.4        Modify query get_priority
||    Erin                 13-APR-2006   2.3        Added procedure get_priority
||    Erin                 19-MAR-2006   1.0        Initial version for work package

-- ====================================================================================
   vcp_version   CONSTANT VARCHAR2 (60) := '2.30 17-Sep-2015';

-- ====================================================================================
 */
@Service
public class TagPrisonActivitiesServiceImpl implements TagPrisonActivitiesService {
	@Autowired
	private net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesRepository TagPrisonActivitiesRepository;

	@Autowired
	private TagPrisonActivitiesRepository tagPrisonActivitiesRepository;

	private static Logger logger = LogManager.getLogger(TagPrisonActivitiesServiceImpl.class.getName());
	@Autowired
	private OsinamesPkgService osinamesService;
	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;
	@Autowired
	private TagEstablishmentService tagEstablishmentService;

	@Autowired
	private OffenderProgramProfilesTrService offenderProgramProfilesTrService;

	private static final String WAIT = "WAIT";

	@Override
	public Integer backdateAttendances(final OffenderProgramProfiles bean) {
		Integer seq = 0;
		Date lvScheduleDate = null;
		Date lvStartTime;
		Date lvEndTime;
		Long lvCrsSchId;
		Integer lvEventId;
		Integer vInternalLocationId = null;

		if (vInternalLocationId == null) {
			vInternalLocationId = TagPrisonActivitiesRepository.intIdCur(bean.getCrsActyId());
		}

		List<CourseSchedules> getCourseSchecules = new ArrayList<CourseSchedules>();

		getCourseSchecules = TagPrisonActivitiesRepository.getCourseScheculesDetails(bean.getCrsActyId(),
				bean.getOffenderStartDate());
		for (final CourseSchedules cs : getCourseSchecules) {

			lvStartTime = cs.getStartTime();
			lvEndTime = cs.getEndTime();
			lvScheduleDate = cs.getScheduleDate();
			lvCrsSchId = cs.getCrsSchId();

			if (lvScheduleDate != null) {
				lvEventId = TagPrisonActivitiesRepository.getEventId();
				final OffenderCourseAttendance oca = new OffenderCourseAttendance();
				oca.setEventId(lvEventId);
				oca.setOffenderBookId(bean.getOffenderBookId());
				oca.setEventDate(lvScheduleDate);
				oca.setStartTime(lvStartTime);
				oca.setEndTime(lvEndTime);
				oca.setOffPrgrefId(BigDecimal.valueOf(bean.getOffPrgrefId()));
				oca.setCrsActyId(BigDecimal.valueOf(bean.getCrsActyId()));
				oca.setInTime(lvStartTime);
				oca.setOutTime(lvEndTime);
				oca.setReferenceId(BigDecimal.valueOf(lvCrsSchId));
				oca.setCrsSchId(BigDecimal.valueOf(lvCrsSchId));
				oca.setAgyLocId(bean.getAgyLocId());
				oca.setToInternalLocationId(BigDecimal.valueOf(vInternalLocationId));
				oca.setCreateUserId(bean.getCreateUserId());
				seq=TagPrisonActivitiesRepository.insertOffenderCourseAttendance(oca);

			}
		}
		return seq;
	}

	@Override
	public Map<String, Object> ChkWaitlist2(final OffenderProgramProfiles offPrgProfiles) {
		final Map<String, Object> returnMap = new HashMap<>();
		Integer existsPen = 0;
		Integer existsApp = 0;
		Integer existsAlloc = 0;

		try {
			existsPen = TagPrisonActivitiesRepository.cursorC1(offPrgProfiles);
			existsApp = TagPrisonActivitiesRepository.cursorC2(offPrgProfiles);
			existsAlloc = TagPrisonActivitiesRepository.cursorC3(offPrgProfiles);
			returnMap.put("P_EXISTS_PEN", existsPen);
			returnMap.put("P_EXISTS_APP", existsApp);
			returnMap.put("P_EXISTS_ALLOC", existsAlloc);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnMap;
	}

	@Override
	public Integer UpdateCourseSchedules(final Long crsActId, final Date scheduleEndDate, final String userName) {
		OffenderProgramProfiles newRec = new OffenderProgramProfiles();
		newRec.setCrsActyId(crsActId);
		offenderProgramProfilesTrService.offenderProgramProfilesTr(newRec);
		return TagPrisonActivitiesRepository.UpdateCourseSchedules(crsActId, scheduleEndDate, userName);
	}

	@Override
	@Transactional
	public void suspendAllocations(final BigDecimal offenderBookId, final Date date, final String userName) {
		List<OffenderProgramProfiles> offproList = new ArrayList<OffenderProgramProfiles>();
		try {
			offproList = TagPrisonActivitiesRepository.cLock(offenderBookId, date);
			for (final OffenderProgramProfiles offPrg : offproList) {
				TagPrisonActivitiesRepository.updateOffPrgPro(offPrg.getOffPrgrefId(), userName);
			}
		} catch (Exception e) {
			logger.error("suspendAllocations", e);
		}
	}

	@Override
	@Transactional
	public void removeCourseSchedules(final Long crsActyId, final Date scheduleEndDate,String modifyUserId) {
		final List<CourseSchedules> list = new ArrayList<CourseSchedules>();
		final CourseSchedules couSch = new CourseSchedules();
		couSch.setCrsActyId(crsActyId);
		couSch.setScheduleDate(scheduleEndDate);
		couSch.setModifyUserId(modifyUserId);
		list.add(couSch);
		TagPrisonActivitiesRepository.removeCourseSchedulesDelete(list);
	}

	@Override
	public void endWaitlistAndAllocations(final BigDecimal offenderBookId, final Date pDate, final String pEndReason,
			final String userName) {

		final List<OffenderProgramProfiles> offProgStatusList = TagPrisonActivitiesRepository
				.executeQueryoffenderProgramStatus(offenderBookId);
		String lvOffenderProgramStatus;
		if (offProgStatusList != null && !offProgStatusList.isEmpty()) {
			for (final OffenderProgramProfiles offProgProfiles : offProgStatusList) {
				lvOffenderProgramStatus = offProgProfiles.getOffenderProgramStatus();
				if (lvOffenderProgramStatus != WAIT) {
					TagPrisonActivitiesRepository.offProgProfUpdateOne(pDate, pEndReason, userName);
				} else {
					TagPrisonActivitiesRepository.offProgProfUpdateSecond(pDate, pEndReason, userName);
				}
			} 
		}
	}

	public List<OffenderProgramProfiles> getAgyLocIdDescription(final OffenderProgramProfiles paramBean) {
		final List<OffenderProgramProfiles> list = new ArrayList<OffenderProgramProfiles>();
		for (final OffenderProgramProfiles a : list) {
			if (a.getAgyLocId() != null) {
				final String locId = TagPrisonActivitiesRepository.getAgyLocIdDescription(a.getAgyLocId());
				a.setAgyLocId(locId);
			}
			if (a.getProgramId() != null) {
				final String prgId = TagPrisonActivitiesRepository.getProgramIdDescription(a.getProgramId());
				a.setProgramId(Long.parseLong(prgId));
			}
			if (a.getOffenderEndReason() != null) {
				String pEndReasonId = TagPrisonActivitiesRepository
						.getOffenderEndReasonDescription(a.getOffenderEndReason());
				a.setProgramId(Long.parseLong(pEndReasonId));
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> displayWaitlistDetails(final OffenderProgramProfiles inputObj) {
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		String pNbtNoms = null;
		String pNbtName = null;
		String pNbtActivityDesc = null;
		String pNbtVacancy = null;
		String pNbtDecision = null;
		String pNbtPriority = null;

		String lvFirstName = null;
		String lvLastName = null;
		String lvAgyLocId = null;

		try {
			if (inputObj.getOffenderBookId() != null) {
				final Map<String, Object> mapObject = osinamesService
						.getOffDetailsByBookId(inputObj.getOffenderBookId());
				pNbtNoms = (String) mapObject.get("P_OFFENDER_ID_DISPLAY");
				lvLastName = (String) mapObject.get("P_LAST_NAME");
				lvFirstName = (String) mapObject.get("P_FIRST_NAME");
				lvAgyLocId = (String) mapObject.get("P_AGY_LOC_ID");

				pNbtName = lvLastName + ", " + lvFirstName;
			}

			if (inputObj.getCrsActyId() != null) {
				pNbtActivityDesc = dispActyDesc(inputObj.getCrsActyId());
				pNbtVacancy = courseVacancy(inputObj.getCrsActyId()).toString();

			}

			if (inputObj.getWaitlistDecisionCode() != null) {
				pNbtDecision = TagPrisonActivitiesRepository.getDescCode("PS_ACT_DEC",
						inputObj.getWaitlistDecisionCode());
			}

			if (inputObj.getReferralPriority() != null) {
				pNbtPriority = TagPrisonActivitiesRepository.getDescCode("PS_PRIORITY", inputObj.getReferralPriority());
			}

			returnMap.put("P_NBT_NOMS", pNbtNoms);
			returnMap.put("P_NBT_NAME", pNbtName);
			returnMap.put("P_NBT_ACTIVITY_DESC", pNbtActivityDesc);
			returnMap.put("P_NBT_VACANCY", pNbtVacancy);
			returnMap.put("P_NBT_DECISION", pNbtDecision);
			returnMap.put("P_NBT_PRIORITY", pNbtPriority);
		} catch (Exception e) {
			logger.error("displayWaitlistDetails :" + e);
		}
		return returnMap;
	}

	@Override
	public String dispActyDesc(final Long pCrsActyId) {
		return TagPrisonActivitiesRepository.dispActyDesc(pCrsActyId);
	}

	@Override
	public Integer courseVacancy(final Long pCrsActyId) {
		return TagPrisonActivitiesRepository.getCapacity(pCrsActyId)
				- TagPrisonActivitiesRepository.getCount(pCrsActyId);
	}

	@Override
	public String chkActyEndDate(final Long crsActyId, final Date scheduleEnddate) {
		return TagPrisonActivitiesRepository.chkActyEndDate(crsActyId, scheduleEnddate);

	}
	@Override
	public Long getCrsActyId() {
		return TagPrisonActivitiesRepository.getCrsActyId();

	}

	@Override
	public Long chkwaitList(final OffenderProgramProfiles bean) {
		return TagPrisonActivitiesRepository.chkwaitList(bean);
	}

	@Override
	public Date getAdmissionDate(final Long offenderBookId) {
		return TagPrisonActivitiesRepository.getAdmissionDate(offenderBookId);
	}

	@Override
	public Date getBookingDate(final Long offenderBookId) {
		return TagPrisonActivitiesRepository.getBookingDate(offenderBookId);
	}

	@Override
	public Long chkAllocated(final Long pcrsActId, final Long pOffenderBookId, final Date offStartDate) {
		return TagPrisonActivitiesRepository.chkAllocated(pcrsActId, pOffenderBookId, offStartDate);
	}

	@Override
	public String chkendDate(final Long crsActyId, final Date scheduleEnddate) {
		return TagPrisonActivitiesRepository.chkActyEndDate(crsActyId, scheduleEnddate);
	}

	@Override
	public OffenderProgramProfiles getCourseActivity(final OffenderProgramProfiles paramBean) {
		final OffenderProgramProfiles bean = new OffenderProgramProfiles();
		List<CourseActivities> courList = null;
		Long crsActyId = null;
		Long internalLocationId = null;
		String description = null;

		try {
			courList = tagPrisonActivitiesRepository.getCourseActivity(paramBean.getActivity(),
					paramBean.getProgramId(), paramBean.getAgyLocId());
			for (final CourseActivities courseActivities : courList) {
				crsActyId = courseActivities.getCrsActyId();
				description = courseActivities.getDescription();
				internalLocationId = courseActivities.getInternalLocationId();
			}
			bean.setCrsActyId(crsActyId);
			bean.setInternalLocationId(internalLocationId.intValue());
			bean.setActivityDescription(description);
		} catch (Exception e) {
			logger.error("getCourseActivity :", e);
		}
		return bean;
	}

	@Override
	public String getServices(final Long pProgramId) {
		return tagPrisonActivitiesRepository.getServices(pProgramId);
	}

	@Override
	public Map<String, Object> populateActivity(final OffenderProgramProfiles bean) {
		Map<String, Object> map = new HashedMap();
		String pEstablishment = null;
		String pServices = null;
		String pActivities = null;
		String pEndReasonDesc = null;
		if (bean.getAgyLocId() != null) {
			pEstablishment = tagEstablishmentService.getActiveAgyLocDesc(bean.getAgyLocId());
		}
		if (bean.getProgramId() != null) {
			pServices = getServices(bean.getProgramId());
		}
		if (bean.getCrsActyId() != null) {
			pActivities = dispActyDesc(bean.getCrsActyId());
		}
		if (bean.getOffenderEndReason() != null) {
			pEndReasonDesc = omsMiscellaneousService.getDescCode("PS_END_RSN", bean.getOffenderEndReason());
		}
		map.put("P_ESTABLISHMENT", pEstablishment);
		map.put("P_SERVICES", pServices);
		map.put("P_ACTIVITIES", pActivities);
		map.put("P_END_REASON_DESC", pEndReasonDesc);
		return map;
	}
	
	@Override
	public Integer bulkUpdate(OffenderProgramProfiles inputObj) {
		return tagPrisonActivitiesRepository.bulkUpdate(inputObj);
	}

}