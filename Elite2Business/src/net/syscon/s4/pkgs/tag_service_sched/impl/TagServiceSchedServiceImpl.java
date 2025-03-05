package net.syscon.s4.pkgs.tag_service_sched.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.VAcpSchedules;
import net.syscon.s4.cm.weeklyactivityplans.OweacplnRepository;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.pkgs.tag_service_sched.TagServiceSchedRepository;
import net.syscon.s4.pkgs.tag_service_sched.TagServiceSchedService;
/*
 * Below comments are copied from TAG_SERVICE_SCHED procedure.
 * Purpose: Functionality for service schedules.
--
-- MODIFICATION HISTORY
-- Person      Date        Version Comments
-- ---------   ------      ------- ------------------------------------------
-- Edward      04-MAR-2013 2.21    HPQC#19053: Added get_crs_sch_rule_cap, get_rule_for_date_flag, remove_med_enc_times, add_med_enc_times
-- Steve       16-DEC-2008 2.20    Tr#7342: Replaced PA with IA when generating new Inst Activity schedules
-- Steve       04-OCT-2008 2.19    Trackplus 7394.  Modified get_holidays to intialise table
--                                 and initilised variable g_got_hols to false in procedures.
-- Patrick     10-APR-2007 2.18    Defect 6374. Added code for Specified Activities for creating schedules as Intervention requires
--                                 creation of offender_course_attendances
--                                 Prashant T. added get_min_alloc_start_date
-- Neil        27/03/2007  2.16    D#6306. Various changes to support retrospective data entry.
-- GJC         05/01/2007  2.15    Defect 5911
-- Neil        15/12/2006  2.14    D#4193 Peer review changes.
-- Neil        28/11/2006  2.13    D#4193. Added check_schedules_exist.
-- GJC         14-Oct-2006 2.12    SHOW_VERSION changed from procedure to function
-- Neil        11/09/2006  2.11    2.10 continued.
-- Neil        11/09/2006  2.10    Added clear_course_attendances and call it from
--                                 cs_acp_reschedule, to remove future cancellations on rescheduling.
-- Johnson     08/09/2006  2.9     Minor Modification made to os_build_schedule.
-- Neil        05/09/2006  2.8     Accredited Programmes Iteration 3.
-- Neil        04/09/2006  2.7     Accredited Programmes Iteration 3.
-- Johnson     08/08/2006  2.6     Modification made to os_build_schedule.
-- Neil        03/08/2006  2.5     Added for iteration 2 accredited programmes
-- Neil        30/06/2006  2.4     Added for iteration 1 accredited programmes
-- GJC         31/03/2006          Add batch process
-- Neil        12/12/2005          Initial Draft*/

@Service
public class TagServiceSchedServiceImpl implements TagServiceSchedService {
	private static Logger logger = LogManager.getLogger(TagServiceSchedServiceImpl.class.getName());
	@Autowired
	private TagServiceSchedRepository TtagServiceSchedRepository;
	@Autowired
	private TagServiceService tagServiceService;
	@Autowired
	private TagProgrammesService tagProgrammesService;
	
	@Autowired
	private OweacplnRepository oweacplnRepository;

	private Boolean gGotHols = false;
	private Long gWeekNo=1l;
	private Date gDate;
	private String gTimes;
	private Long gSessionNo;
	private Long gWeeksBetween= 0l;
	private Boolean gRespectHols = false;
	private Long gMaxWeek = 0l;
	private List<Date> gHolTab=new ArrayList<Date>();
	private Map<String,Map<String,Boolean>> gRulesTab =new LinkedHashMap<String, Map<String,Boolean>>();
	private List<CourseScheduleRules> gCrsSchRuleList = null;
	private static final String CRS_PH = "CRS_PH";
	private static final String COURSE = "COURSE";
	private static final String N = "N";
	private static final String Y = "Y";
	private static final String CRS_MOD = "CRS_MOD";
	private static final String SCH = "SCH";

	/*
	 * This procedure is migrated from oracle TAG_SERVICE_SCHED
	 * 
	 * @Procedure cs_clear_schedules to be used for returning schedule_date, start_time FROM course_schedules
	 * search results.
	 */
	@Override
	public String csClearSchedules(final Long crsSchId, final Long crActyId,String modifyUserId) {
		String code = null;
		try {
			final CourseSchedules courseScedule = TtagServiceSchedRepository.crsCur(crsSchId);
			//This method is used for delete the course schedules
			TtagServiceSchedRepository.deleteCourseSchedule(courseScedule.getScheduleDate(),
					courseScedule.getStartTime(),courseScedule.getCrsActyId(),modifyUserId);
			code = "1";
		} catch (Exception e) {
			code = "2";
		}
		return code;
	}

	/*
	 * This procedure is migrated from oracle TAG_SERVICE_SCHED
	 * 
	 * @Procedure cs_acp_clear_sessions to be used for returning schedule_date, start_time FROM course_schedules
	 * search results.
	 */
	@Override
	public void csAcpClearSessions(final VAcpSchedules searchBean) {
		final CourseSchedules courseScedule = TtagServiceSchedRepository.crsCur(searchBean.getCrsSchId());
		
		WeeklyActivityPlans searchBeanWap=new WeeklyActivityPlans();
		List<WeeklyActivityPlans> wapActiveList=new ArrayList<>();
		List<WeeklyActivityPlans> insertList=new ArrayList<>();
		List<WeeklyActivityPlans> deleteList=new ArrayList<>();		
		wapActiveList = oweacplnRepository.getActiveWapList(searchBean.getProgramInstanceId(),searchBean.getPhaseInstanceId(),searchBean.getCrsSchId());	
		
		for (WeeklyActivityPlans weeklyActivityPlans : wapActiveList) {
			searchBeanWap.setFromDate(weeklyActivityPlans.getFromDate());
			searchBeanWap.setToDate(weeklyActivityPlans.getToDate());
			searchBeanWap.setOffenderBookId(weeklyActivityPlans.getOffenderBookId());
			try {
				List<WeeklyActivityPlans> wapOffenderRelatedList=new ArrayList<>();
				wapOffenderRelatedList = oweacplnRepository.getWeeklyActivity(searchBeanWap);			
				for (WeeklyActivityPlans deleteObj : wapOffenderRelatedList) {
					if(deleteObj.getEventId()!=null && deleteObj.getRecordSource() != null && weeklyActivityPlans.getEventIdTemp()!=null && 
							(deleteObj.getRecordSource()  + "_" + deleteObj.getEventId()).equals(weeklyActivityPlans.getEventIdTemp())) {
						deleteList.add(deleteObj);
					} else {
						if(deleteObj.getHtyVersionNo()!=null) {
							deleteObj.setVersionNo(deleteObj.getHtyVersionNo().add(BigDecimal.ONE));
							deleteObj.setFinalized("N");
						} else {
							deleteObj.setVersionNo(deleteObj.getVersionNo());
							deleteObj.setFinalized("N");
						}
						insertList.add(deleteObj);
					}
				}			
			} catch (Exception e) {
				logger.error("Exception in " + this.getClass().getName() + " getWeeklyActivity", e);
			}
		}
		
		// delete any future cancelation records in attendances table
		final long count = TtagServiceSchedRepository.clearCourseAttendances(searchBean.getProgramInstanceId(),
				searchBean.getPhaseInstanceId(), searchBean.getCrsSchId());
		// delete the course schedules
		TtagServiceSchedRepository.deleteCourseSchedules(searchBean.getProgramInstanceId(),
				searchBean.getPhaseInstanceId(), searchBean.getCrsSchId(), courseScedule.getScheduleDate(),
				courseScedule.getStartTime());
		adjustAllPeriods(searchBean.getProgramInstanceId(), searchBean.getCreateUserId());	
		if(!deleteList.isEmpty()) {
			oweacplnRepository.weeklyActivityDeleteData(deleteList);
		}
		if(!insertList.isEmpty()) {
			oweacplnRepository.weeklyActivityCommitUpdateData(insertList);
		}

	}

	public Date getMaxStartTimeCursorPriorCur(final VAcpSchedules paramBean) {
		final Long lvSessionSeq = TtagServiceSchedRepository.getLvSessionSeq(paramBean);
		if (lvSessionSeq != null) {
			paramBean.setSessionNo(lvSessionSeq);
		}
		final Date returnDate = TtagServiceSchedRepository.getMaxStartTimeCursorPriorCur(paramBean);
		return returnDate;
	}

	public Date getMinStartTimeCursorPriorCur(final VAcpSchedules paramBean) {
		final Long lvSessionSeq = TtagServiceSchedRepository.getLvSessionSeq(paramBean);
		final Date returnDateVal = TtagServiceSchedRepository.getMinStartTimeCursorPriorCur(paramBean);
		return returnDateVal;
	}

	@Override
	@Transactional
	public Map<String, Object> getCrsActyWithGaps(final Long crsActyId) {
		Long lvCrsActyId = null;
		Long lvParentCrsActyId = null;
		String lvCourseClass = null;
		String lvPhaseCourseClass = null;
		Date lvStartDate = null;
		Long lvNoSessions = null;
		Long lvProgramId = null;
		String lvDescription = null;
		Long lvListSeq = null;
		Long lvSessionLength = null;

		Long pModuleListSeq = 0l;
		Long pPhaseListSeq = 0l;
		Long pPhaseSessionLength = 0l;
		Long pPhaseInstanceId = null;
		String pPhaseInstanceDesc = null;
		Long pModuleInstanceId = null;
		String pModuleInstanceDesc = null;
		final Map<String, Object> mapObject = new HashMap<String, Object>();

		final CourseActivities courseActivities = TtagServiceSchedRepository.gapCur(crsActyId);
		if (courseActivities != null) {
			lvCrsActyId = courseActivities.getCrsActyId();
			lvCourseClass = courseActivities.getCourseClass();
			lvParentCrsActyId = courseActivities.getParentCrsActyId();
			lvListSeq = courseActivities.getListSeq();
			lvSessionLength = courseActivities.getSessionLength();
			lvDescription = courseActivities.getDescription();
		}

		if (lvCourseClass != null && lvCourseClass.equals(CRS_MOD)) {
			pModuleInstanceId = lvCrsActyId;
			pModuleInstanceDesc = lvDescription;
			pModuleListSeq = lvListSeq;
			pPhaseInstanceId = lvParentCrsActyId;

			final CourseActivities obj = TtagServiceSchedRepository.phsCur(pPhaseInstanceId);
			if (obj != null) {
				pPhaseInstanceDesc = obj.getDescription();
				pPhaseListSeq = obj.getListSeq();
				pPhaseSessionLength = obj.getSessionLength();

			}
		} else {
			pModuleInstanceId = null;
			pModuleInstanceDesc = null;
			pModuleListSeq = 0l;
			pPhaseInstanceId = lvCrsActyId;
			pPhaseInstanceDesc = lvDescription;
			pPhaseListSeq = lvListSeq;
			pPhaseSessionLength = lvSessionLength;
		}

		mapObject.put("P_PHASE_INSTANCE_ID", pPhaseInstanceId);
		mapObject.put("P_PHASE_INSTANCE_DESC", pPhaseInstanceDesc);
		mapObject.put("P_PHASE_LIST_SEQ", pPhaseListSeq);
		mapObject.put("P_PHASE_SESSION_LENGTH", pPhaseSessionLength);
		mapObject.put("P_MODULE_INSTANCE_ID", pModuleInstanceId);
		mapObject.put("P_MODULE_INSTANCE_DESC", pModuleInstanceDesc);
		mapObject.put("P_MODULE_LIST_SEQ", pModuleListSeq);

		return mapObject;
	}

	@Override
	public Date getLastSchedDate(final Long pCrsActyId) {
		return TtagServiceSchedRepository.getLastSchedDate(pCrsActyId);
	}
	/*
	 * This procedure is migrated from oracle TAG_SERVICE_SCHED
	 * 
	 * @Procedure cs_build_recurring_schedule to be used for getting  p_crs_acty_id, p_period_start_date.
	 * search results.
	 */
	@Override
	public CourseScheduleRules csBuildRecurringSchedule(final Long pCrsActyId, final Long pNoDays, String userName) {
		Date lvLastDate = null;
		Date lvCaStartDate = null;
		Date lvCaEndDate = null;
		Date lvStartDate = null;
		Date lvEndDate = null;
		gGotHols = false;
		gDate = new Date();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// get last schedule date
		lvLastDate = tagServiceService.getLastSchedDate(pCrsActyId);
		// get start and end dates of activity/project
		final List<CourseActivities> list = tagServiceService.getCaDates(pCrsActyId);
		if(list != null ) {
				for (final CourseActivities data : list) {
				lvCaStartDate = data.getScheduleStartDate();
				lvCaEndDate = data.getScheduleEndDate();
			}
		}

		if (lvLastDate != null) {
			final LocalDate date = lvLastDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate plusDays = date.plusDays(1);
			lvStartDate = Date.from(plusDays.atStartOfDay(ZoneId.systemDefault()).toInstant());
		} else if(lvCaStartDate != null){
			lvStartDate = lvCaStartDate;
		} else {
			lvStartDate = new Date();
		}
		final LocalDate date = LocalDate.now();
		Calendar c = Calendar.getInstance();
		c.setTime(gDate);
		c.add(Calendar.DATE, Integer.valueOf(pNoDays.toString()));
		lvEndDate = c.getTime();
		
//		date.plusDays(pNoDays);
//		lvEndDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		if (lvCaEndDate != null && lvCaEndDate.compareTo(lvEndDate) < 0) {
			lvEndDate = lvCaEndDate;
		}

		returnMap = csBuildRecurringSchedule(pCrsActyId, lvStartDate, lvEndDate, userName);
		final CourseScheduleRules obj = new CourseScheduleRules();
		final Long pNoSessions = (Long) returnMap.get("P_NO_SESSIONS");
		final Date pLastSessionDate = (Date) returnMap.get("P_LAST_SESSION_DATE");
		obj.setNoBuilt(pNoSessions);
		obj.setLastDate(pLastSessionDate);
		return obj;
	}

	@Override
	public Map<String, Object> csBuildRecurringSchedule(final Long pCrsActyId, final Date pPeriodStartDate,
			final Date pPeriodEndDate, final String userName) {
		Long pNoSessions = null;
		Date pLastSessionDate = null;
		Date lvFirstSessionDate = null;
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		csInitBuild(pCrsActyId, pPeriodStartDate);
		final Map<String, Object> map1 = csGetBuildRules(pCrsActyId);
		if(map1 != null) {
			gMaxWeek = (Long) map1.get("P_MAX_WEEK");
		}
		final Map<String, Object> map = csCreateSchedule(pCrsActyId, null, pPeriodEndDate, userName);
		pNoSessions = (Long) map.get("P_NO_SESSIONS");
		lvFirstSessionDate = (Date) map.get("P_FIRST_START_DATE");
		pLastSessionDate = (Date) map.get("P_LAST_END_DATE");
		returnMap.put("P_NO_SESSIONS", pNoSessions);
		returnMap.put("P_LAST_SESSION_DATE", pLastSessionDate);
		finaliseBuild();
		return returnMap;
	}
	/*
	 * This procedure is migrated from oracle TAG_SERVICE_SCHED
	 * 
	 * @Procedure cs_init_build to be used for returning
	 * search results.
	 */
	public void csInitBuild(final Long pCrsActyId, final Date pStartDate) {
		// check if we should respect holidays.
		gRespectHols = false;
		final List<CourseActivities> list = TtagServiceSchedRepository.checkRespectHols(pCrsActyId);
		if(list != null && list.size() >0) {
			for (final CourseActivities data : list) {
				if (data.getHolidayFlag().equals("Y")) {
					gRespectHols = true;
				}
			} 
		}


		if (gRespectHols && !gGotHols) {
			getHolidays(pStartDate);
		}

		gWeekNo = 1l;
		gDate = pStartDate;
		gTimes = null;
		gSessionNo = null;
		gWeeksBetween = 0l;
		final Map<String, Object> map = csGetBuildRules(pCrsActyId);

		if(map != null) {
			gMaxWeek = (Long) map.get("P_MAX_WEEK");
			gRulesTab=(Map<String, Map<String, Boolean>>) map.get("P_RULES_TAB");
			if (map.get("P_RULES_TAB") == null || gRulesTab.size() < 1) {
				throw new IllegalArgumentException("No Build rules exist");
			}
		}

		// IF g_rules_tab.FIRST IS NULL
		// THEN
		// tag_error.raise_app_error ( -20001, 'No Build rules exist' );
		// END IF;
	}

	//Retrieving index value
	@Override
	public Map<String, Object> csCreateSchedule(final Long pCrsActyId, final Long pNoOfSessions, final Date pEndDate,String userName) {
		Date lvDate = null;
		Date lvStartTime = null;
		Date lvEndTime = null;
		Long lvIndex = 0l;
		List<CourseSchedules> lv_cs_tab = new ArrayList<CourseSchedules>();
		Date lvLastDate = null;
		Date pFirstStartDate = null;
		Date pLastEndDate = null;
		List<CourseSchedules> courseSchedules = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();

		if (pNoOfSessions == null && pEndDate == null) {
			throw new IllegalArgumentException(
					"tag_service_sched.cs_create_schedule: p_no_of_sessions and p_end_date cannot both be null");
		}

		for (int i = 0; i < i + 1; i++) {
			if (pNoOfSessions !=null && lvIndex != null && lvIndex >= pNoOfSessions) {
				break;
			}
//			final Map<String, Object> map = getNextSession();
			final Map<String, Object> map = getNextSession(lvDate,lvStartTime,lvEndTime);
			if(map != null && !map.isEmpty()) {
				lvDate = (Date) map.get("P_DATE");
				lvStartTime = (Date) map.get("P_START_TIME");
				lvEndTime = (Date) map.get("P_END_TIME");
				if (lvDate == null || (lvDate != null && pEndDate != null && lvDate.compareTo(pEndDate) > 0)) {
					break;
				}
				if (pFirstStartDate == null) {
					pFirstStartDate = lvStartTime;
				}
				if (gSessionNo != null) {
					gSessionNo = gSessionNo + 1;
				}

				final CourseSchedules crsSch = new CourseSchedules();
				crsSch.setCrsActyId(pCrsActyId);
				crsSch.setScheduleDate(lvDate);
				crsSch.setStartTime(lvStartTime);
				crsSch.setEndTime(lvEndTime);
				crsSch.setScheduleStatus(SCH);
				crsSch.setSessionNo(gSessionNo);
				lv_cs_tab.add(lvIndex.intValue(), crsSch);
				lvIndex = lvIndex + 1;
				lvLastDate = lvStartTime;
			}
		}

		lv_cs_tab.forEach(data -> {
			// data.setCrsSchId(tagServiceService.getCrsSchId());
			VAcpSchedules obj=new VAcpSchedules();
			obj.setpCrsActyId(data.getCrsActyId());
			obj.setScheduleDate(data.getScheduleDate());
			obj.setStartTime(data.getStartTime());
			obj.setEndTime(data.getEndTime());
			obj.setSessionNo(data.getSessionNo());
			insertCourseSchedule(obj,userName); 
		});
		lv_cs_tab = null;
		pLastEndDate = lvLastDate;
		returnMap.put("P_FIRST_START_DATE", pFirstStartDate);
		returnMap.put("P_LAST_END_DATE", pLastEndDate);
		returnMap.put("P_NO_SESSIONS", lvIndex);
		return returnMap;
	}

	private Map<String, Object> getNextSession() {
		String lvDay;
		String lvRulesIndex;
		Date pDate = null;
		Date pStartTime = null;
		Date pEndTime = null;
		String hours = null;
		String minutes = null;
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		Boolean schedulePicked = false;

		if (gDate == null) {
			throw new IllegalArgumentException("Must initialise before calling get_next_seession");
		}
		for (int i = 0; i < 1; i++) {

			if(gCrsSchRuleList != null) {
				CourseScheduleRules processSchdRule = null;
				for(CourseScheduleRules crsSchRule : gCrsSchRuleList) {
					processSchdRule = new CourseScheduleRules();
					if(gWeekNo.equals(crsSchRule.getWeekNo())) {
						processSchdRule = crsSchRule;
						break;
					}		
				}
				if(("Y").equalsIgnoreCase(getDayFlag(getDayOfWeek(gDate), processSchdRule))) {
					final DateFormat hrsFormat = new SimpleDateFormat("HH");
					final DateFormat minsFormat = new SimpleDateFormat("mm");

					hours = hrsFormat.format(processSchdRule.getStartTime());
					minutes = minsFormat.format(processSchdRule.getStartTime());
					Calendar startTime = Calendar.getInstance();
					startTime.setTime(gDate);
					startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
					startTime.set(Calendar.MINUTE, Integer.parseInt(minutes));
					pStartTime = startTime.getTime();

					hours = hrsFormat.format(processSchdRule.getEndTime());
					minutes = minsFormat.format(processSchdRule.getEndTime());
					Calendar endTime = Calendar.getInstance();
					endTime.setTime(gDate);
					endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
					endTime.set(Calendar.MINUTE, Integer.parseInt(minutes));
					pEndTime = endTime.getTime();

					pDate = gDate;
					schedulePicked = true;;

				}
				Calendar c = Calendar.getInstance();
				c.setTime(gDate);
				c.add(Calendar.DATE, 1);
				gDate = c.getTime();
				if (getDayOfWeek(gDate).equals("MON")) {
					if (gWeekNo == gMaxWeek) {
						gWeekNo = 1l;
					} else {
						gWeekNo = gWeekNo + 1;
					}
				}

			}
		}
		if(schedulePicked) {
			returnMap.put("P_DATE", pDate);
			returnMap.put("P_START_TIME", pStartTime);
			returnMap.put("P_END_TIME", pEndTime);
			return returnMap;
		} else
			return null;
	}

	private String getDayOfWeek(final Date date) {
		String retVal = null;
		switch (date.getDay()) {
		case 0:
			retVal = "SUN";
			break;
		case 1:
			retVal = "MON";
			break;
		case 2:
			retVal = "TUE";
			break;
		case 3:
			retVal = "WED";
			break;
		case 4:
			retVal = "THU";
			break;
		case 5:
			retVal = "FRI";
			break;
		case 6:
			retVal = "SAT";
			break;

		}
		return retVal;
	}

	private String getDayFlag(String day, CourseScheduleRules crsSchRule) {
		if(day.equalsIgnoreCase("MON"))
			return crsSchRule.getMondayFlag();
		if(day.equalsIgnoreCase("TUE"))
			return crsSchRule.getTuesdayFlag();
		if(day.equalsIgnoreCase("WED"))
			return crsSchRule.getWednesdayFlag();
		if(day.equalsIgnoreCase("THU"))
			return crsSchRule.getThursdayFlag();
		if(day.equalsIgnoreCase("FRI"))
			return crsSchRule.getFridayFlag();
		if(day.equalsIgnoreCase("SAT"))
			return crsSchRule.getSaturdayFlag();
		if(day.equalsIgnoreCase("SUN"))
			return crsSchRule.getSundayFlag();
		else
			return "NONE";
	}


	private Map<String, Object> extractTimes(final Date pDate, final String pTimes) {
		Date pStartTime = null;
		Date pEndTime = null;
		final Map<String, Object> map = new HashMap<String, Object>();
		final SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd/MM/yyyy");
		String staDateFormt = simpleDateformat.format(pDate);
		staDateFormt = staDateFormt.concat(gTimes.substring(0, 4));
		final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyyHHmm");
		try {
			pStartTime = df.parse(staDateFormt);
			if (gTimes != null && !gTimes.endsWith("NONE")) {
				staDateFormt = staDateFormt.concat(gTimes.substring(4));
				pEndTime = df.parse(staDateFormt);
			}
		} catch (ParseException e) {
			logger.error(e);
		}
		map.put("P_START_TIME", pStartTime);
		map.put("P_END_TIME", pEndTime);
		return map;
	}

	// This FUNCTION is_holiday ( p_date DATE ) used to get date 
	private Boolean isHoliday(final Date pDate) {
		Boolean retVal = false;
		if (!gGotHols) {
			throw new IllegalArgumentException("Must call get_holidays before is_holiday.");
		}
		// IF g_hol_tab.EXISTS ( TO_NUMBER ( TO_CHAR ( p_date, 'YYYYMMDD' ) ) )
		for (Date date : gHolTab) {
			if (pDate != null && date.compareTo(pDate) == 0) {
				retVal = true;
				break;
			}
		}
		return retVal;
	}

	public void getHolidays(Date pStartDate) {
		Date lvDate = null;
		final Calendar cal = Calendar.getInstance();
		cal.setTime(pStartDate);
		cal.add(Calendar.DATE, -365);
		pStartDate = cal.getTime();
		final List<SystemEvents> list = TtagServiceSchedRepository.holCur(pStartDate);
		// g_hol_tab.DELETE;
		gHolTab = new ArrayList<Date>();
		if (list != null && list.size() > 0) {
			for (final SystemEvents data : list) {
				lvDate = data.getEventDate();
				for (int i = 1; i <= data.getEventSeq(); i++) {
					// g_hol_tab ( TO_NUMBER ( TO_CHAR ( lv_date, 'YYYYMMDD' ) ) ) := TRUE;
					gHolTab.add(lvDate);
					cal.setTime(lvDate);
					cal.add(Calendar.DATE, 1);
					lvDate = cal.getTime();
				}

			}
		}

		gGotHols = true;
	}

	private Map<String, Object> csGetBuildRules(final Long pCrsActyId) {
		final List<CourseScheduleRules> crsSchRuleList = TtagServiceSchedRepository.lvRuleCur(pCrsActyId);
		gCrsSchRuleList = crsSchRuleList;
		return getBuildRules(crsSchRuleList);
	}

	private Map<String, Object> getBuildRules(final List<CourseScheduleRules> crsSchdRulesObj) {
		Map<String, Map<String, Boolean>> lvRulesTab = null;
		CourseScheduleRules lvRule = null;
		String lvTimes;
		Long lvMaxWeek = 0l;
		final Map<String, Object> map = new HashMap<String, Object>();
		if (crsSchdRulesObj != null && crsSchdRulesObj.size() > 0) {
			lvRulesTab = new LinkedHashMap<String, Map<String, Boolean>>();
			for (final CourseScheduleRules data : crsSchdRulesObj) {
				lvRule = data;
				CourseScheduleRules obj = lvRule;
				if (lvRule.getWeekNo() > lvMaxWeek) {
					lvMaxWeek = lvRule.getWeekNo();
				}
				// lvTimes := TO_CHAR ( lv_rule.start_time, 'HH24MI' ) || NVL ( TO_CHAR (
				// lv_rule.end_time, 'HH24MI' ), 'NONE' );
				lvTimes = "" + (lvRule.getStartTime().getHours() < 10?"0"+lvRule.getStartTime().getHours():lvRule.getStartTime().getHours()) 
						+( lvRule.getStartTime().getMinutes()<10?"0"+lvRule.getStartTime().getMinutes():lvRule.getStartTime().getMinutes());
				if (lvRule.getEndTime() != null) {
					lvTimes = lvTimes.concat("" + (lvRule.getEndTime().getHours() < 10 ?"0"+ lvRule.getEndTime().getHours():
						lvRule.getEndTime().getHours())+ (lvRule.getEndTime().getMinutes()<10?"0"+lvRule.getEndTime().getMinutes():
							lvRule.getEndTime().getMinutes()));
				} else {
					lvTimes = lvTimes.concat("NONE");
				}

				if (lvRule.getMondayFlag().equals(Y)) {
					// lv_rules_tab ( TO_CHAR ( lv_rule.week_no ) || 'MON' ) ( lv_times ) := TRUE;
					Map<String, Boolean> value = new HashMap();
					value.put(lvTimes, true);
					lvRulesTab.put(lvRule.getWeekNo() + "MON", value);
				}
				if (lvRule.getTuesdayFlag().equals(Y)) {
					// lv_rules_tab ( TO_CHAR ( lv_rule.week_no ) || 'TUE' ) ( lv_times ) := TRUE;
					Map<String, Boolean> value = new HashMap();
					value.put(lvTimes, true);
					lvRulesTab.put(lvRule.getWeekNo() + "TUE", value);
				}
				if (lvRule.getWednesdayFlag().equals(Y)) {
					// lv_rules_tab ( TO_CHAR ( lv_rule.week_no ) || 'WED' ) ( lv_times ) := TRUE;
					Map<String, Boolean> value = new HashMap();
					value.put(lvTimes, true);
					lvRulesTab.put(lvRule.getWeekNo() + "WED", value);
				}
				if (lvRule.getThursdayFlag().equals(Y)) {
					// lv_rules_tab ( TO_CHAR ( lv_rule.week_no ) || 'THU' ) ( lv_times ) := TRUE;
					Map<String, Boolean> value = new HashMap();
					value.put(lvTimes, true);
					lvRulesTab.put(lvRule.getWeekNo() + "THU", value);
				}
				if (lvRule.getFridayFlag().equals(Y)) {
					// lv_rules_tab ( TO_CHAR ( lv_rule.week_no ) || 'FRI' ) ( lv_times ) := TRUE;
					Map<String, Boolean> value = new HashMap();
					value.put(lvTimes, true);
					lvRulesTab.put(lvRule.getWeekNo() + "FRI", value);
				}
				if (lvRule.getSaturdayFlag().equals(Y)) {
					// lv_rules_tab ( TO_CHAR ( lv_rule.week_no ) || 'SAT' ) ( lv_times ) := TRUE;
					Map<String, Boolean> value = new HashMap();
					value.put(lvTimes, true);
					lvRulesTab.put(lvRule.getWeekNo() + "SAT", value);
				}
				if (lvRule.getSundayFlag().equals(Y)) {
					// lv_rules_tab ( TO_CHAR ( lv_rule.week_no ) || 'SUN' ) ( lv_times ) := TRUE;
					Map<String, Boolean> value = new HashMap();
					value.put(lvTimes, true);
					lvRulesTab.put(lvRule.getWeekNo() + "SUN", value);
				}

			}
		}

		map.put("P_RULES_TAB", lvRulesTab);
		map.put("P_MAX_WEEK", lvMaxWeek);
		return map;
	}

	@Override
	public Long getStartSessionNumber(final Long crsActyId, final String courseClass, final Long listSeq,
			final Long parentCrsActyId) {
		Long sessionNo = 0l;
		if (CRS_MOD.equals(courseClass)) {
			// ssn_cur
			sessionNo = TtagServiceSchedRepository.ssnCur(listSeq, parentCrsActyId);
		}
		sessionNo = sessionNo + 1;

		return sessionNo;
	}

	@Override
	public Long getModStartSessionNumber(final Long crsActyId, final Long listSeq, final Long parentCrsActyId) {
		return getStartSessionNumber(crsActyId, CRS_MOD, listSeq, parentCrsActyId);
	}

	@Override
	public Date changeCourseSchedule(final VAcpSchedules vacpSchedules, final String userName) {
		Date lvStartDate = null;
		Date lvEndDate = null;

		vacpSchedules.setpCrsActyId((vacpSchedules.getModuleInstanceId() != null) ? vacpSchedules.getModuleInstanceId()
				: vacpSchedules.getPhaseInstanceId());
		updateCourseSchedule(vacpSchedules, userName);

		if (vacpSchedules.getCatchUpSessionFlag() == null || vacpSchedules.getCatchUpSessionFlag().equals(N)) {
			final Map<String, Object> map = adjustPeriod(vacpSchedules.getpCrsActyId(), userName);
			lvStartDate = (Date) map.get("P_START_DATE");
			lvEndDate = (Date) map.get("P_END_DATE");
		}

		return lvStartDate;
	}

	@Override
	public void updateCourseSchedule(final VAcpSchedules vacpSchedules, final String userName) {
		TtagServiceSchedRepository.updateCourseSchedule(vacpSchedules, userName);
	}

	@Override
	public Map<String, Object> adjustPeriod(final Long pCrsActyId, final String userName) {
		Date pStartDate = null;
		Date pEndDate = null;
		Long lvMinSessionNo = null;
		Long lvMaxSessionNo = null;
		Long lvStartSessionNo = null;
		Long lvEndSessionNo = null;
		Date lvMinScheduleDate = null;
		Date lvMaxScheduleDate = null;
		final Map<String, Object> returnMap = new HashMap<String, Object>();
		final List<CourseActivities> list = TtagServiceSchedRepository.crsCurAp(pCrsActyId);
		for (int i = 0; i < list.size(); i++) {
			final CourseActivities data = list.get(i);
			final CourseSchedules gslCur = getSessionLimits(data.getCrsActyId());
			lvMinSessionNo = gslCur.getCrsSchId();
			lvMinScheduleDate = gslCur.getStartTime();
			lvMaxSessionNo = gslCur.getCrsActyId();
			lvMaxScheduleDate = gslCur.getEndTime();
			lvStartSessionNo = getStartSessionNumber(data.getCrsActyId(), data.getCourseClass(), data.getListSeq(),
					data.getParentCrsActyId());
			lvEndSessionNo = lvStartSessionNo + data.getNoOfSessions() - 1;
			TtagServiceSchedRepository.updateCourseActivities(lvMinSessionNo, lvStartSessionNo, lvMinScheduleDate,
					lvMaxSessionNo, lvEndSessionNo, lvMaxScheduleDate, data.getCrsActyId(), userName);
			final CourseActivities crsAct = TtagServiceSchedRepository.getCourseActivities(data.getCrsActyId());
			pStartDate = crsAct.getScheduleStartDate();
			pEndDate = crsAct.getScheduleEndDate();
			tagProgrammesService.adjustAllocationDates(data.getCrsActyId(), data.getCourseClass(), pStartDate, pEndDate,
					data.getListSeq(), data.getParentCrsActyId(), userName);
		}
		returnMap.put("P_START_DATE", pStartDate);
		returnMap.put("P_END_DATE", pEndDate);
		return returnMap;

	}

	@Override
	public CourseSchedules getSessionLimits(final Long crsActyId) {
		return TtagServiceSchedRepository.getSessionLimits(crsActyId);
	}

	@Override
	public Date getScheduleDateForSession(final Long pCrsActyId, final Long pSessionNumber) {
		return TtagServiceSchedRepository.schCur(pCrsActyId, pSessionNumber);
	}

	/*
	 * This function is migrated from oracle TAG_SERVICE_SCHED.
	 * 
	 * get total number of schedules at crs_acty level and lower
	 */
	@Override
	public Long countSessions(final Long pCrsActyId) {
		return TtagServiceSchedRepository.countSessions(pCrsActyId);
	}

	@Override
	public Date getLastProgramScheduleDate(final Long pProgramInstanceId, final Long pPhaseListSeq) {
		return TtagServiceSchedRepository.getLastProgramScheduleDate(pProgramInstanceId, pPhaseListSeq);
	}

	@Override
	public Date getNextPhaseScheduleDate(final Long pProgramInstanceId, final Long pPhaseListSeq) {
		return TtagServiceSchedRepository.getNextPhaseScheduleDate(pProgramInstanceId, pPhaseListSeq);
	}
	//This method is used for calculating no of sessions done
	@Override
	public Long noSessionsDone(final Long pCrsActyId) {
		Integer lvMaxSession = 0;
		Long lvStartSession = 1l;
		try {
			lvMaxSession = TtagServiceSchedRepository.getMaxSessions(pCrsActyId);
			final CourseActivities obj = TtagServiceSchedRepository.getCourseActstartCur(pCrsActyId);
			lvStartSession = getStartSessionNumber(obj.getCrsActyId(), obj.getCourseClass(), obj.getListSeq(),
					obj.getParentCrsActyId());
		} catch (Exception e) {
			logger.error("getCourseActstartCur :" + e);
		}
		if (lvMaxSession == 0) {
			return 0l;
		} else {
			return (lvMaxSession - lvStartSession + 1);
		}
	}

	@Override
	public Date getPriorScheduleDate(final CourseActivities bean) {
		final Long sessionSeq = (bean.getProgramId() * 10000000) + bean.getNoOfSessions();
		bean.setSessionLength(sessionSeq);
		return TtagServiceSchedRepository.getPriorSchedueDate(bean);
	}

	@Override
	public Date createCourseSchedule(final VAcpSchedules vacpSchedules, final String userName) {
		Long lvCrsSchId;
		Date lvStartDate;
		Date lvEndDate;

		vacpSchedules.setpCrsActyId((vacpSchedules.getModuleInstanceId() != null) ? vacpSchedules.getModuleInstanceId()
				: vacpSchedules.getPhaseInstanceId());
		lvCrsSchId = insertCourseSchedule(vacpSchedules, userName);
		final Map<String, Object> map = adjustPeriod(vacpSchedules.getpCrsActyId(), userName);
		lvStartDate = (Date) map.get("P_START_DATE");
		lvEndDate = (Date) map.get("P_END_DATE");

		if (vacpSchedules.getScheduleDate().compareTo(new Date()) <= 0) {
			createSchAttendances(lvCrsSchId, userName);
		}
		return lvStartDate;
	}

	@Override
	public Long insertCourseSchedule(final VAcpSchedules vacpSchedules, final String userName) {
		Long crsSchId=TtagServiceSchedRepository.getCrsSchId();
		vacpSchedules.setCrsSchId(crsSchId);
		TtagServiceSchedRepository.insertCourseSchedule(vacpSchedules, userName);
		return crsSchId;
	}

	@Override
	public void createSchAttendances(final Long pCrsSchId, final String userName) {
		TtagServiceSchedRepository.createSchAttendances(pCrsSchId, userName);
	}

	@Override
	@Transactional
	public CourseActivities csAcpReSchedule(final Long pCrsActyId, final Long pCrsSchId, final Date pStartDate,
			final String userName) {
		CourseActivities retObj=new CourseActivities();
		List<VAcpSchedules> vAcpSchList = new ArrayList<VAcpSchedules>();
		List<CourseActivities> crsList = null;
		String lvCourseClass = null;
		Long lvParentCrsActyId = null;
		Long lvPhaseInstanceId = null;
		Long lvProgramInstanceId = null;
		Date lvDate = null;
		Date lvStartTime = null;
		Date lvEndTime = null;
		Date lvLastDate = null;
		Integer lvIndex = 0;
		Date pFromDate = null;
		Date pToDate=null;
		ArrayList<Long> lvCrsSchIdTab=null;
		ArrayList<Date> lvDateTab=null;
		ArrayList<Date> lvStartTab=null;
		ArrayList<Date> lvEndTab=null;


		//		tag_service_sched.cs_init_build ( p_crs_acty_id, p_start_date );
		csInitBuild(pCrsActyId, pStartDate); 

		// OPEN crs_cur;
		crsList = TtagServiceSchedRepository.crsCurTwo(pCrsActyId);
		for (final CourseActivities courseActivities : crsList) {
			lvCourseClass = courseActivities.getCourseClass();
			lvParentCrsActyId = courseActivities.getParentCrsActyId();
		}
		if (CRS_PH.equals(lvCourseClass)) {
			lvProgramInstanceId = lvParentCrsActyId;
			lvPhaseInstanceId = pCrsActyId;
		} else if (COURSE.equals(lvCourseClass)) {
			lvProgramInstanceId = pCrsActyId;
		}

		// CURSOR sch_cur
		vAcpSchList = TtagServiceSchedRepository.schCur(lvProgramInstanceId, lvPhaseInstanceId, pCrsSchId);
		if (vAcpSchList != null && vAcpSchList.size() > 0) {
			lvCrsSchIdTab=new ArrayList<Long>();
			lvDateTab=new ArrayList<Date>();
			lvStartTab=new ArrayList<Date>();
			lvEndTab=new ArrayList<Date>();
			for (final VAcpSchedules vAcpSch : vAcpSchList) {
				Map<String, Object> map = null;
				// get_next_session ( lv_date, lv_start_time, lv_end_time ); Procedure
				while(map == null) {
				map = getNextSession(lvDate,lvStartTime,lvEndTime);
//					map = getNextSession();
					Date  date = (Date) map.get("P_DATE");
					if (map != null && !map.isEmpty() && date != null) {
						lvDate = (Date) map.get("P_DATE");
						lvStartTime = (Date) map.get("P_START_TIME");
						lvEndTime = (Date) map.get("P_END_TIME");
	
						lvCrsSchIdTab.add(lvIndex, vAcpSch.getCrsSchId());
						if(lvDate != null && lvStartTime != null && lvEndTime  != null) {
							vAcpSch.setScheduleDate(lvDate);
							vAcpSch.setStartTime(lvStartTime);
							vAcpSch.setEndTime(lvEndTime);
						}
						lvLastDate = lvStartTime;
						if (pFromDate == null) {
							pFromDate = lvLastDate;
						}
					}
				}
			}
		}

		if(vAcpSchList != null && vAcpSchList.size()>0) {
			for(VAcpSchedules vAcpSch : vAcpSchList){
				if(vAcpSch!= null && vAcpSch.getScheduleDate()!=null) {
				CourseSchedules obj=new CourseSchedules();
				obj.setScheduleDate(vAcpSch.getScheduleDate());
				obj.setStartTime(vAcpSch.getStartTime());
				obj.setEndTime(vAcpSch.getEndTime());
				obj.setCrsSchId(vAcpSch.getCrsSchId());
				obj.setModifyUserId(userName);
				TtagServiceSchedRepository.updateCourseSchedule(obj);
				}
			}

		}
		// tag_service_sched.adjust_all_periods ( p_crs_acty_id ); 1850
		adjustAllPeriods(pCrsActyId, userName);
		// 1852 tag_service_sched.clear_course_attendances ( p_crs_sch_id,
		// lv_program_instance_id, lv_phase_instance_id );
		clearCourseAttendances(pCrsActyId, lvProgramInstanceId, lvPhaseInstanceId,userName);
		pToDate=lvLastDate;
		finaliseBuild();

		retObj.setOfferingStartDate(pFromDate);
		retObj.setOfferingEndDate(pToDate);
		return retObj;
	}

	@Override
	public void clearCourseAttendances(final Long pCrsActyId, final Long lvProgramInstanceId,
			final Long lvPhaseInstanceId,String modifyUserId) {
		TtagServiceSchedRepository.clearCourseAttendancesTwo(pCrsActyId, lvProgramInstanceId, lvPhaseInstanceId,modifyUserId);
	}

	@Override
	public void adjustAllPeriods(final Long pCrsActyId, final String userName) {
		List<CourseActivities> couActList = new ArrayList<CourseActivities>();
		Map<String, Object> StaEndDates = new HashMap<String, Object>();
		// Cursor crs_cur
		couActList = TtagServiceSchedRepository.adjAllPerCrsCur(pCrsActyId);
		if (couActList != null && couActList.size() > 0) {
			for (final CourseActivities courseActivities : couActList) {
				// adjust_period ( crs_rec.crs_acty_id, lv_date, lv_date );
				//StaEndDates = adjustPeriod(courseActivities.getCrsActyId(), userName);
				StaEndDates = adjustPeriod(courseActivities.getCrsActyId());
			}
		}
	}

	@Override
	public Map<String, Object> getNextSession(Date pDate, Date pStartTime, Date pEndTime) {
		Calendar c = Calendar.getInstance();
		final SimpleDateFormat simpleDateformat = new SimpleDateFormat("EE");
		String lvDay = null;
		String lvRulesIndex = null;
		pDate = null;
		Date[] staEdTimeArry = new Date[2];
		Date[] staEdTimeArryOne = new Date[2];
		final Map<String, Object> returnMap = new HashMap<String, Object>();

		if (gDate == null) {
			throw new IllegalArgumentException("Must initialise before calling get_next_seession");
		}
		while(returnMap.isEmpty()) {
		lvDay = simpleDateformat.format(gDate);
		lvRulesIndex = gWeekNo + "" + lvDay.toUpperCase();

		if (gDate != null && gTimes != null) {
			pDate = gDate;
			// extract_times ( p_date, g_times, p_start_time, p_end_time );
			staEdTimeArry = extractTimess(pDate, gTimes);
			returnMap.put("P_DATE", pDate);
			returnMap.put("P_START_TIME", staEdTimeArry[0]);
			returnMap.put("P_END_TIME", staEdTimeArry[1]);
			gTimes=null;
			// one Line pending
			// g_times := g_rules_tab ( lv_rules_index ).NEXT ( g_times );

		} else {
			if (!(gRespectHols && isHoliday(gDate)) && gRulesTab.get(lvRulesIndex)!=null ) { // IF condition pending AND g_rules_tab.EXISTS (
														// lv_rules_index )
				pDate = gDate;
				// one line pending
				Map<String, Boolean> timeData = gRulesTab.get(lvRulesIndex);
				gTimes = timeData.keySet().stream().findFirst().get();
				// extract_times ( p_date, g_times, p_start_time, p_end_time );
				staEdTimeArryOne = extractTimess(pDate, gTimes);
				// one line pending
				returnMap.put("P_DATE", pDate);
				returnMap.put("P_START_TIME", staEdTimeArryOne[0]);
				returnMap.put("P_END_TIME", staEdTimeArryOne[1]);
				gTimes = null;
			}
		}
		if (gTimes == null) {
			c.setTime(gDate);
			c.add(Calendar.DATE, 1);
			gDate = c.getTime();

			final String dayFormt = simpleDateformat.format(gDate);
			if (dayFormt!=null && dayFormt.equalsIgnoreCase("MON")) {
				if(gWeekNo == gMaxWeek) {
					gWeekNo = (long) 1;
				} else {
					gWeekNo = gWeekNo + 1;
				}
			}
		}
		}
		return returnMap;
	}


	@Override
	public Date[] extractTimess(final Date pDate, final String gTimes) {
		Date[] starEndTime = new Date[2];
		final SimpleDateFormat simpleDateformat = new SimpleDateFormat("dd/MM/yyyy");
		final DateFormat df = new SimpleDateFormat("dd/MM/yyyyHHmm");

		final String staDateFormt = simpleDateformat.format(pDate).concat(gTimes.substring(0, 4));

		final String endDateFormt = simpleDateformat.format(pDate).concat(gTimes.substring(4));
		final String gTimSubStr = gTimes.substring(4);
		try {
			starEndTime[0] = df.parse(staDateFormt);
			if (gTimSubStr != null) {
				starEndTime[1] = df.parse(endDateFormt);
			}
		} catch (ParseException e) {
			logger.error("extractTimess", e);
		}
		return starEndTime;
	}

	// This PROCEDURE last_crs_acty_built is used to get max count
	@Override
	public CourseActivities lastCrsActyBuilt(final Long pParentCrsActyId) {
		final CourseActivities bean = new CourseActivities();
		Long pCrsActyId = null;
		String pCourseClass = null;
		String pDescription = null;
		Long pListSeq = null;
		final Map<String, Object> map = new HashMap<String, Object>();
		pListSeq = 0L;
		try {
			// This methods is used to get_last_phase_details
			final CourseActivities obj = TtagServiceSchedRepository.getCourseAcctDetails(pParentCrsActyId);
			if (obj != null) {
				pCrsActyId = obj.getCrsActyId();
				pListSeq = obj.getListSeq();
				pCourseClass = obj.getCourseClass();
				pDescription = obj.getDescription();
			}
			bean.setCrsActyId(pCrsActyId != null ? pCrsActyId : null);
			bean.setCourseClass(pCourseClass != null ? pCourseClass : null);
			bean.setLastDescription(pDescription != null ? pDescription : null);
			bean.setLastListSeq(pListSeq != null ? pListSeq : null);
		} catch (Exception e) {
			logger.error("lastCrsActyBuilt :" + e);
		}
		return bean;
	}

	@Override
	public Map<String, Object> getScheduleDateLimits(Long pProgramInstanceId, Long pPhaseListSeq, Long pSessionNo) {
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put("P_PRIOR_DATE", getPriorScheduleDate(pProgramInstanceId, pPhaseListSeq, pSessionNo));
		returnObj.put("P_NEXT_DATE", getNextScheduleDate(pProgramInstanceId, pPhaseListSeq, pSessionNo));
		return returnObj;
	}

	private Date getPriorScheduleDate(Long pProgramInstanceId, Long pPhaseListSeq, Long pSessionNo) {
		BigDecimal lvSessionSeq = BigDecimal.valueOf((pPhaseListSeq != null ? pPhaseListSeq * 10000000 : 0) + (pSessionNo != null ?pSessionNo:0));
		return TtagServiceSchedRepository.getPriorScheduleDate(pProgramInstanceId, lvSessionSeq != BigDecimal.ZERO ?lvSessionSeq:null);
	}

	private Date getNextScheduleDate(Long pProgramInstanceId, Long pPhaseListSeq, Long pSessionNo) {
		BigDecimal lvSessionSeq = BigDecimal.valueOf((pPhaseListSeq != null ? pPhaseListSeq * 10000000 : 0) + (pSessionNo != null ? pSessionNo:0));
		return TtagServiceSchedRepository.getNextScheduleDate(pProgramInstanceId, lvSessionSeq != BigDecimal.ZERO ?lvSessionSeq:null);

	}

	/*
	 * This procedure is migrated from oracle TAG_SERVICE_SCHED
	 * 
	 * @Procedure cs_build_acp_schedule to be used for returning no_of_sessions, course_class, parent_crs_acty_id
	 * search results.
	 */
	@Override
	@Transactional
	public Map<String, Object> csBuildAcpSchedule(Long pCrsActyId, Date pStartDate, Long pListSeqEnd, 
			Long pWeeksBetween, String userName) {

		gGotHols = false;
		Long lvNoOfSessions = null;
		Long lvProgramInstanceId = null;
		Long lvPhaseInstanceId = null;
		Long lvParentCrsActyId = null;
		Long lvPhaseListSeq = null;
		Long lvModuleListSeq = null;
		String lvCourseClass = null;

		if (pWeeksBetween == null) {
			pWeeksBetween = 0l;
		}
		// intialise the acp build
		List<CourseActivities> list = TtagServiceSchedRepository.caCurCsBldAcpSch(pCrsActyId);
		if(list != null && list.size() > 0) {
			for (CourseActivities data : list) {
				lvNoOfSessions = data.getNoOfSessions();
				lvCourseClass = data.getCourseClass();
				lvParentCrsActyId = data.getParentCrsActyId();
			}
		}

		if (lvCourseClass != null && lvCourseClass.equals("COURSE")) {
			lvProgramInstanceId = pCrsActyId;
			lvPhaseInstanceId = null;
		} else {
			lvProgramInstanceId = lvParentCrsActyId;
			lvPhaseInstanceId = pCrsActyId;
			lvNoOfSessions = lvNoOfSessions - noSessionsDoneCsBldAcpSch(lvPhaseInstanceId);

		}
		Map<String, Object> map = acpInitBuildCsBldAcpSch(lvProgramInstanceId, lvPhaseInstanceId, pStartDate,
				pWeeksBetween);
		lvPhaseListSeq = (Long) map.get("P_PHASE_LIST_SEQ");
		lvModuleListSeq = (Long) map.get("P_MODULE_LIST_SEQ");
		Map<String, Object> returnMap = csBuildAcpSchedule(pCrsActyId, lvCourseClass, lvNoOfSessions, lvPhaseListSeq,
				lvModuleListSeq, pListSeqEnd,userName);
		finaliseBuild();
		return returnMap;
	}

	private Long noSessionsDoneCsBldAcpSch(Long pCrsActyId) {
		Long lvMaxSession = 0l;
		Long lvStartSession = 1l;

		lvMaxSession = TtagServiceSchedRepository.maxCurCsBldAcpSch(pCrsActyId);
		lvStartSession = TtagServiceSchedRepository.startCurCsBldAcpSch(pCrsActyId);
		if (lvMaxSession != null && lvMaxSession == 0) {
			return 0l;
		} else {
			return (lvMaxSession - lvStartSession + 1);
		}

	}

	private Map<String, Object> acpInitBuildCsBldAcpSch(Long pProgramInstanceId, Long pPhaseInstanceId, Date pStartDate,
			Long pWeeksBetween) {
		Long pPhaseListSeq = 0l;
		Long pModuleListSeq = 0l;
		Long lvSessionNoBegin = 0l;
		Map<String, Object> map = new HashMap<String, Object>();

		VAcpSchedules lvSch = null;
		csInitBuild((pPhaseInstanceId != null) ? pPhaseInstanceId : pProgramInstanceId, pStartDate);
		lvSch = getLastSessionDetailsCsBldAcpSch(pProgramInstanceId, pPhaseInstanceId);
		if (lvSch != null && lvSch.getProgramInstanceId() != null) {
			pPhaseListSeq = lvSch.getPhaseListSeq();
			pModuleListSeq = lvSch.getModuleListSeq();
			lvSessionNoBegin = lvSch.getSessionNo();
		}
		gSessionNo = lvSessionNoBegin;
		gWeeksBetween = pWeeksBetween;
		map.put("P_PHASE_LIST_SEQ", pPhaseListSeq);
		map.put("P_MODULE_LIST_SEQ", pModuleListSeq);
		return map;
	}

	private VAcpSchedules getLastSessionDetailsCsBldAcpSch(Long pProgramInstanceId, Long pPhaseInstanceId) {
		return TtagServiceSchedRepository.getLastSessionDetailsCsBldAcpSch(pProgramInstanceId, pPhaseInstanceId);
	}

	@Override
	public Map<String, Object> csBuildAcpSchedule(Long pCrsActyId, String pCourseClass, Long pNoOfSessions,
			Long pPhaseListSeq, Long pModuleListSeq, Long pListSeqEnd,String userName) {
		List<CourseActivities> lvCaRef;
		CourseActivities lvCaRec;
		Long lvLastSessionNo = null;
		Long lvNoOfSessions = null;
		Long lvNoSchedules = null;
		Long lvTotalSchedules = 0l;
		Long lvModuleListSeq = null;
		Long lvPhaseListSeq = null;
		Boolean lvLowest = null;
		Date lvDate = null;
		Date lvStartDate = null;
		Date lvEndDate = null;
		Date lvFromDate = null;
		Date lvToDate = null;
		Date lvDummyDate = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();

		lvPhaseListSeq = pPhaseListSeq;
		lvModuleListSeq = pModuleListSeq;
		lvLowest = true;
		lvCaRef = TtagServiceSchedRepository.lvCaRefCsBldAcpSch(pCrsActyId, lvPhaseListSeq, lvModuleListSeq,
				pListSeqEnd);
		for (CourseActivities data : lvCaRef) {
			lvCaRec = data;
			lvLowest = false;
			lvNoOfSessions = Math.abs(lvCaRec.getNoOfSessions() - noSessionsDoneCsBldAcpSch(lvCaRec.getCrsActyId()));
			Map<String, Object> map = csBuildAcpSchedule(lvCaRec.getCrsActyId(), lvCaRec.getCourseClass(),
					lvNoOfSessions, lvPhaseListSeq, lvModuleListSeq, null,userName);
			lvNoSchedules = (Long) map.get("P_NO_SCHEDULES");
			lvStartDate = (Date) map.get("P_FROM_DATE");
			lvEndDate = (Date) map.get("P_TO_DATE");

			lvTotalSchedules = lvNoSchedules + lvTotalSchedules;
			Calendar cal = Calendar.getInstance();
			if (lvFromDate != null) {
				lvFromDate = (lvFromDate.compareTo(lvStartDate) < 0) ? lvFromDate : lvStartDate;
			} else {
				Date lvFromDateTemp = null;
				try {
					cal.setTime(lvStartDate);
					cal.add(Calendar.DATE, +1);
					lvFromDateTemp = cal.getTime();
					lvFromDate = (lvFromDateTemp.compareTo(lvStartDate) < 0) ? lvFromDateTemp : lvStartDate;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			if (lvToDate != null) {
				lvToDate = (lvToDate.compareTo(lvEndDate) > 0) ? lvToDate : lvEndDate;
			} else {
				Date lvToDateTemp = null;
				try {
					cal.setTime(lvEndDate);
					cal.add(Calendar.DATE, -1);
					lvToDateTemp = cal.getTime();
					lvToDate = (lvToDateTemp.compareTo(lvEndDate) > 0) ? lvToDateTemp : lvEndDate;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}

			if (lvCaRec.getCourseClass().equals("CRS_PH")) {
				if (lvCaRec.getNoOfSessions() == gSessionNo) {
					gSessionNo = 0l;
				}
				lvModuleListSeq = 1l;
			}

		}

		if (lvLowest) {
			Map<String, Object> map = csCreateSchedule(pCrsActyId, pNoOfSessions, null,userName);
			lvFromDate = (Date) map.get("P_FIRST_START_DATE");
			lvToDate = (Date) map.get("P_LAST_END_DATE");
			lvTotalSchedules = (Long) map.get("P_NO_SESSIONS");
			if (pCourseClass.equals("CRS_MOD") && gWeeksBetween > 0) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(lvToDate);
				cal.add(Calendar.DATE, (int) (1 + (gWeeksBetween * 7)));
				gDate = cal.getTime();
			}
		}

		if (pCourseClass.equals("CRS_PH") || pCourseClass.equals("CRS_MOD")) {
			adjustPeriod(pCrsActyId,userName);
		}
		returnMap.put("P_NO_SCHEDULES", lvTotalSchedules);
		returnMap.put("P_FROM_DATE", lvFromDate);
		returnMap.put("P_TO_DATE", lvToDate);

		return returnMap;
	}

	@Override
	public Map<String, Object> adjustPeriod(Long pCrsActyId) {
		Date pStartDate = null;
		Date pEndDate = null;
		Long lvMinSessionNo = null;
		Long lvMaxSessionNo = null;
		Long lvStartSessionNo = null;
		Long lvEndSessionNo = null;
		Date lvMinScheduleDate = null;
		Date lvMaxScheduleDate = null;
		Date schStartDate = null;
		Date schEndDate = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<CourseActivities> list = TtagServiceSchedRepository.crsCurCsBldAcpSch(pCrsActyId);
		if(list!= null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				CourseActivities data = list.get(i);
				CourseSchedules gslCur = TtagServiceSchedRepository.getSessionLimitsCsBldAcpSch(data.getCrsActyId());
				lvMinSessionNo = gslCur.getCrsSchId();
				lvMinScheduleDate = gslCur.getStartTime();
				lvMaxSessionNo = gslCur.getCrsActyId();
				lvMaxScheduleDate = gslCur.getEndTime();
				lvStartSessionNo = getStartSessionNumberCsBldAcpSch(data.getCrsActyId(), data.getCourseClass(),
						data.getListSeq(), data.getParentCrsActyId());
				lvEndSessionNo = lvStartSessionNo + data.getNoOfSessions() - 1;

				if(lvMinSessionNo != null && lvStartSessionNo != null && lvMinSessionNo == lvStartSessionNo)
					schStartDate = lvMinScheduleDate;
				if(lvMaxSessionNo != null && lvEndSessionNo != null && lvMaxSessionNo == lvEndSessionNo)
					schEndDate = lvMaxScheduleDate;
				TtagServiceSchedRepository.updateCourseActivitiesCsBldAcpSch(schStartDate, schEndDate, data.getCrsActyId());
				CourseActivities crsAct = TtagServiceSchedRepository.getCourseActivitiesCsBldAcpSch(data.getCrsActyId());
				if(crsAct!=null && crsAct.getScheduleStartDate() != null)
					pStartDate = crsAct.getScheduleStartDate();
				if(crsAct!=null && crsAct.getScheduleEndDate() != null)
					pEndDate = crsAct.getScheduleEndDate();
				adjustAllocationDatesCsBldAcpSch(data.getCrsActyId(), data.getCourseClass(), pStartDate, pEndDate,
						data.getListSeq(), data.getParentCrsActyId());
			}
		}
		returnMap.put("P_START_DATE", pStartDate);
		returnMap.put("P_END_DATE", pEndDate);
		return returnMap;

	}

	private Long getStartSessionNumberCsBldAcpSch(Long pCrsActyId, String pCourseClass, Long pListSeq,
			Long pParentCrsActyId) {
		Long lvLastSessionNo = 0l;
		if (pCourseClass.equals("CRS_MOD")) {
			lvLastSessionNo = TtagServiceSchedRepository.ssnCurCsBldAcpSch(pListSeq, pParentCrsActyId);
		}
		return lvLastSessionNo + 1;
	}

	private void adjustAllocationDatesCsBldAcpSch(Long pCrsActyId, String pCourseClass, Date pScheduleStartDate,
			Date pScheduleEndDate, Long pListSeq, Long pParentCrsActyId) {
		Date lvBigDate = null;
		String valuee = "01/01/9000";
		try {
			lvBigDate = new SimpleDateFormat("dd/MM/yyyy").parse(valuee);
		} catch (ParseException e) {
			logger.error(e);
		}
		Long lvStartSessionNo = null;
		Date lvOffenderStartDate = null;
		Date lvOffenderEndDate = null;
		if (pCourseClass.equals("CRS_PH")) {
			lvStartSessionNo = 1l;
		} else if (pCourseClass.equals("CRS_MOD")) {
			lvStartSessionNo = getStartSessionNumberCsBldAcpSch(pCrsActyId, "CRS_MOD", pListSeq, pParentCrsActyId);
		}
		List<OffenderProgramProfiles> alloCur = TtagServiceSchedRepository.allocCurCsBldAcpSch(pCrsActyId);
		for (int i = 0; i < alloCur.size(); i++) {
			OffenderProgramProfiles data = alloCur.get(i);
			if (data.getStartSessionNo() == lvStartSessionNo) {
				lvOffenderStartDate = pScheduleStartDate;
			} else {
				lvOffenderStartDate = getScheduleDateForSessionCsBldAcpSch(pCrsActyId, data.getStartSessionNo());
			}
			if (pCourseClass.equals("CRS_PH") && data.getSealFlag().equals("Y")) {
				lvOffenderEndDate = getAllocationEndDateCsBldAcpSch(data.getOffPrgrefId());
			} else {
				lvOffenderEndDate = pScheduleEndDate;
			}
			if ((((data.getOffenderStartDate() != null) ? data.getOffenderStartDate() : lvBigDate)
					.compareTo((lvOffenderStartDate != null) ? lvOffenderStartDate : lvBigDate) != 0)
					|| (((data.getOffenderEndDate() != null) ? data.getOffenderEndDate() : lvBigDate)
							.compareTo((lvOffenderEndDate != null) ? lvOffenderEndDate : lvBigDate) != 0)) {
				TtagServiceSchedRepository.updateOffenderProgramProfilesCsBldAcpSch(data.getOffPrgrefId(),
						lvOffenderStartDate, lvOffenderEndDate);
			}

		}

		return;
	}

	private Date getScheduleDateForSessionCsBldAcpSch(Long pCrsActyId, Long pSessionNumber) {
		return TtagServiceSchedRepository.schCurCsBldAcpSch(pCrsActyId, pSessionNumber);
	}

	private Date getAllocationEndDateCsBldAcpSch(Long pOffPrgrefId) {
		Date lvBigDate = null;
		String valuee = "01/01/9000";
		try {
			lvBigDate = new SimpleDateFormat("dd/MM/yyyy").parse(valuee);
		} catch (ParseException e) {
			logger.error(e);
		}
		Date lvEndDate;
		lvEndDate = TtagServiceSchedRepository.endCurCsBldAcpSch(pOffPrgrefId, lvBigDate);
		if (lvEndDate!= null &&  lvEndDate.compareTo(lvBigDate) == 0) {
			lvEndDate = null;
		}

		return lvEndDate;

	}

	private void finaliseBuild() {
		gRulesTab = new LinkedHashMap<String, Map<String,Boolean>>();
	}

}