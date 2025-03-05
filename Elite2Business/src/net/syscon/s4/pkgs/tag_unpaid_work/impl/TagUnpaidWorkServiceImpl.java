package net.syscon.s4.pkgs.tag_unpaid_work.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.pkgs.oidincde.OidincdePkgRepository;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsRepository;
import net.syscon.s4.pkgs.tag_reference_codes.TagReferenceCodesRepository;
import net.syscon.s4.pkgs.tag_unpaid_work.TagUnpaidWorkRepository;
import net.syscon.s4.pkgs.tag_unpaid_work.TagUnpaidWorkService;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfService;

/*
 * Below comments are copied from package TAG_UNPAID_WORK
--    Purpose: This package provides procedures for Prison Activities.
--
--    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
--    --------------------
--    Person                   DATE       Version   Comments
--    ---------            ---------     ---------  -----------------------------------
--    Steve                 06-Aug-2008   2.62      Modified create_offender_schedules to include reference id and crs_acty_id
--    Krishna               20-Aug-2007   2.61      Code change in get_travel_hrs, get_credited_hrs functions to return '00:00' when there is no value
--    Krishna               17-Aug-2007   2.60      Code change in get_travel_hrs, get_credited_hrs functions to return in HH:MI format
--    Krishna               17-Aug-2007   2.59      Code change in convert_hours_to_date func
--    Krishna               06-Aug-2007   2.58      Returing get_penalty as hh:mi format in characters
--    Krishna               02-Aug-2007   2.57      Removed Round function in get_penalty func, fix for #7450
--    Surya                 12-Jul-2007   2.56      Corrected show_version function as there was no return.
--    Surya                 08-Jul-2007   2.55      User Admin Security - Modified get_staff_team.
--                                                  Removed version history from Specification as it is not required.
--    Krishna               21-Mar-2007   2.54      Removed get_credited_hrs_value func, as we are using tag_prg.credited_hours func in OCDUPROJ screen
--                                                  fix for #6305
--    Krishna               20-Mar-2007   2.53      Code change in get_credited_hrs_value to calculate correct hours
--    Krishna               13-Mar-2007   2.52      Added get_off_adj_id func, #6305
--    Krishna             27-Feb-2008     2.51     Added Convert_Date_to_Hours, get_penalty, Convert_Hours_to_Date func.
--    D Rice                22-Nov-2006   2.50      Defect#5183 - Procedure get_attend_info: changed
--						    call from: Osinames.get_off_details_by_book_id to: Oidincde.get_off_details_by_book_id
--						    Also removed parameter: p_agy_loc_id from procedure as no longer required
--    Neil                  23-Oct-2006   2.49     Corrected check_attendance_recorded.
--    Krishna               18-Oct-2006   2.48     Added p_comment_text parameter to create_off_course_attendance proc
--    Krishna               12-Oct-2006   2.47     Added lock_off_crs_event_id, get_off_crs_event_id_cs procs to fix #5113
--    D Rice 	      	    25-Sep-2006   2.46     Defect# 4816 - Removed procedure: chk_staff_id as logic of this procedure has been moved to the form (OCMSSVCT)
--    D Rice	      	    21-Sep-2006   2.45     Defect# 4816 - Added new parameter (p_program_id) to: chk_staff_id.
--						   Added new cursor: staff_id_inst_prog_cur to chk_staff_id
--    Igor                  11-Sep-2006   2.44     Fixed TO_MANY_ROWS exception in the procedure check_team_name
--    D Rice                18-Aug-2006   2.43     Version number now in sync
--    D Rice                16-Aug-2006   2.42     Changed all references of: OFFENDER_PLACEMENT_ASSES to OFFENDER_PLACEMENT_SCORES
--					     	   Changed references of: education_score to EDUCATION_SCORE
--    David		    16-Aug-2006   2.41     Bug Fix
--    Johnson               08-Aug-2008   2.40     Removed the following functions
                                                   Update_counter, handle_event_counter
--    Krishna               26-Jul-2006   2.39     Added get_off_sent_term_hrs
--    Krishna               25-Jul-2006   2.38     move the sql to cursor in get_staff_team
--    Krishna               25-Jul-2006   2.37     Change in create_off_course_attendance, added event_sub_type
--    Krishna               19-Jul-2006   2.36     Added get_event_id
--    Krishna               19-Jul-2006   2.35     Added check_attendace_recorded, delete_schedule, get_holiday_count,
--    Johnson               14-Jul-2006   2.34     Package Formatted using SQL Formatter
--    Johnson               14-Jul-2006   2.33     Modified function ins_event_counter.
--    Johnson               12-Jul-2006   2.32     Added functions handle_counter, ins_event_counter
--                                                 and modified the function update_counter.
--    Krishna               05-Jul-2006   2.31     Added create_offender_schedule procedure
--    Krishna               03-Jul-2006   2.30     Added sort_by_offender, sort_by_project functions,
--                                                 removed sorting_by_activity_code, sorting_by_name
--                                                 Code change in check_weekly_def_exists function
--    Erin                  30-Jun-2006   2.29     Added PROCEDURE chk_staff_id (for OCMSSVCT)
--    Aasim                 29-Jun-2006   2.28     Removed noms_no_for_sorting. Added Sort_by_name and sort_by_activity_code. D#2916.
--    Johnson               02-Jun-2006   2.27     Added function Update_counter
--    Aasim                 30-May-2006   2.26     Added noms_no_for_sorting
--    Erin                  30-May-2006   2.25     remove redundant funtions, replace implicit with explicit cursors
--    Aasim                 30-May-2006   2.24     Added get_attend_info.
--    Erin                  30-May-2006   2.23     Added function alloc_exists
--    Aasim                 26-May-2006   2.22     Added check_Team_name.
--    Krishna               26-May-2006   2.21     Added get_credited_hrs function
--    Aasim                 25-May-2006   2.20      Exception handling in get_acty_desc...
--    Erin                  25-May-2006   2.19     Added procedure UPDATE_ENDDATE
--    Krishna               25-May-2006   2.18     Added update_credit_hrs proc.
--    Aasim                 25-May-2006   2.17     Error correction to previous change..
--    Aasim                 25-May-2006   2.16     Removed references to UNPAID_WK and replaced with UW.
--    Aasim                 24-May-2006   2.15     Improvement to calc_pqs.
--    Krishna               23-May-2006   2.14      Added check_project_exists,check_weekly_def_exists,check_off_sch_exists functions
--    Aasim                 23-May-2006   2.13     Added calc_pqs
--    Krishna               23-May-2006   2.12     Added get_schedule_count function
--    Krishna               22-May-2006   2.11      Added get_weekly_def_start_date proc
--    Krishna               19-May-2006   2.10      Added g_schedule_rec, get_schedules, get_crs_appt_grp_id, get_crs_appt_rule_id
--    Aasim                 18-MAY-2006   2.8       Added event_type column in INSERT statement of create_off_course_attendance .
--    Aasim                 18-MAY-2006   2.7       Added disp_acty_desc function
--    Aasim                 17-MAY-2006   2.6       Added get_staff_name function.
--    Krishna               15-May-2006   2.5       Added create_off_course_attendances proc
--    Aasim                 15-MAY-2006   2.3       Added get_staff_team function
--    Krishna               11-May-2006   2.1       Added get_project_alloc_details proc
--    Aasim                 10-MAY-2006   2.0       Initial version for work package
--
*/
@Service
public class TagUnpaidWorkServiceImpl implements TagUnpaidWorkService {
	@Autowired
	private TagUnpaidWorkRepository tagUnpaidWorkRepository;
	@Autowired
	private OidincdePkgRepository oidincdeRepository;
	@Autowired
	private TagReferenceCodesRepository tagReferenceCodesRepository;
	@Autowired
	private OffenderCourseAttndnsTwfService offenderCourseAttndnsTwfService;
	@Autowired
	private OmsUtilsRepository omsUtilsRepository;
	private static Logger logger = LogManager.getLogger(TagUnpaidWorkServiceImpl.class.getName());

	@Override
	public Integer createOffCourseAttendance(final VOffenderCourseEvents offAttCourse, final String userName) {
		offAttCourse.setUserName(userName);
		Integer retVal = tagUnpaidWorkRepository.insertOffCourseAttendance(offAttCourse);
		OffenderCourseAttendances newRef = new OffenderCourseAttendances();
		BeanUtils.copyProperties(offAttCourse, newRef);
		newRef.setEventId(offAttCourse.getEventId() != null ? offAttCourse.getEventId().longValue() : null);
		newRef.setEventOutcome(offAttCourse.getEventOutcome());
		newRef.setEventType(offAttCourse.getEventType());
		newRef.setOffenderBookId(
				offAttCourse.getOffenderBookId() != null ? offAttCourse.getOffenderBookId().longValue() : null);
		newRef.setCreateUserId(offAttCourse.getCreateUserId());
		newRef.setOffPrgrefId(offAttCourse.getOffPrgrefId() != null ? offAttCourse.getOffPrgrefId().longValue() : null);
		Integer triggerVal = offenderCourseAttndnsTwfService.offenderCourseAttndnsTwf(newRef, userName);
		return retVal;
	}

	@Override
	public Map<String, Object> getProjectAllocDetails(final Long crsActyId) {
		final Map<String, Object> outParams = new HashMap<String, Object>();
		CourseActivities coursActivities = new CourseActivities();
		// get_proj_alloc_dtls_cur 151
		coursActivities = tagUnpaidWorkRepository.getProjectAllocDetails(crsActyId);

		outParams.put("p_project_code", coursActivities.getCode());
		outParams.put("p_project_desc", coursActivities.getDescription());
		outParams.put("p_team_id", coursActivities.getOffPrgrefId());

		outParams.put("p_team_desc", coursActivities.getName());
		outParams.put("p_team_area_code", coursActivities.getAreaInformation());
		outParams.put("p_team_agy_loc_id", coursActivities.getAgyLocId());

		outParams.put("p_max_capacity", coursActivities.getCapacity());
		outParams.put("p_program_id", coursActivities.getProgramId());
		outParams.put("p_sch_start_date", coursActivities.getScheduleStartDate());

		outParams.put("p_sch_end_date", coursActivities.getScheduleEndDate());
		return outParams;
	}

	@Override
	public void updateCredithrs(final VOffenderCourseEvents offcourseAtt, final String userName) {
		final Float hours = tagUnpaidWorkRepository.getTotCreditHrsCur(offcourseAtt);
		if (hours > 0) {
			tagUnpaidWorkRepository.updateOffProgramprofiles(offcourseAtt, hours, userName);
		}
	}

	@Override
	public VOffenderCourseEvents getAttendInfo(final OffenderCourseAttendance offCourAttends) {
		final VOffenderCourseEvents resultObject = new VOffenderCourseEvents();
		String pName = null;
		String offIdDisp = null;
		String pAttendance = null;
		String pBehaviour = null;
		String pWorkQuality = null;
		String pSupervisorName = null;
		String code = null;
		String desc = null;
		List<Offenders> offDetailsList = null;
		List<CourseActivities> codeNdActivity = null;

		try {
			// Oidincde.get_off_details_by_book_id 972
			offDetailsList = oidincdeRepository.getOffDetailsByBookId(offCourAttends.getOffenderBookId());

			for (final Offenders off : offDetailsList) {
				pName = off.getLastName().concat(", ").concat(off.getFirstName());
				offIdDisp = off.getOffenderIdDisplay();
			}

			pAttendance = tagReferenceCodesRepository.getDescCode("OUTCOMES", offCourAttends.getEventOutcome());
			pBehaviour = tagReferenceCodesRepository.getDescCode("PS_BEHAVIOUR", offCourAttends.getBehaviourCode());
			pWorkQuality = tagReferenceCodesRepository.getDescCode("PS_UPW_QUAL", offCourAttends.getPerformanceCode());

			// Oms_Utils.get_staff_name 988
			pSupervisorName = omsUtilsRepository.getStaffName(offCourAttends.getSupervisorStaffId());

			// Tag_Unpaid_Work.disp_acty_desc 989
			codeNdActivity = tagUnpaidWorkRepository.getDispActyDesc(offCourAttends.getCrsActyId(),
					offCourAttends.getOffenderBookId());
			for (final CourseActivities courseActy : codeNdActivity) {
				code = courseActy.getCode();
				desc = courseActy.getDescription();
			}
			resultObject.setpOffenderIdDisplay(offIdDisp);
			resultObject.setpName(pName);
			resultObject.setpAttendance(pAttendance);
			resultObject.setpBehaviour(pBehaviour);
			resultObject.setpWorkQuality(pWorkQuality);
			resultObject.setpSupervisorName(pSupervisorName);
			resultObject.setpCode(code);
			resultObject.setpActivityDesc(desc);
		} catch (Exception e) {
			logger.error("getAttendInfo :" + e);
		}
		return resultObject;
	}

	@Override
	public List<CourseActivities> getDispActyDesc(final BigDecimal crsActyId, final Long offenderBookId) {
		return tagUnpaidWorkRepository.getDispActyDesc(crsActyId, offenderBookId);

	}

	// This FUNCTION alloc_exists is migrated from oracle
	@Override
	public Integer allocExists(final Long pCrsActyId, final Date pScheduleEndDate) {
		// This methos is used to get count
		return tagUnpaidWorkRepository.getCount(pCrsActyId, pScheduleEndDate);
	}

	@Override
	public BigDecimal getOffSentTermHrs(final BigDecimal offenderBookId, final BigDecimal sentenceSeq) {
		return tagUnpaidWorkRepository.getOffSentTermHrs(offenderBookId, sentenceSeq);
	}

	@Override
	public Integer getHolidayCount(final Date eventDate) {
		return tagUnpaidWorkRepository.getHolidayCount(eventDate);
	}

	@Override
	public Date getWeeklyDefStartDate(final BigDecimal offPrgrefId) {
		Date returnDate = null;
		final Calendar cal = Calendar.getInstance();
		try {
			returnDate = tagUnpaidWorkRepository.getWeeklyDefStartDate(offPrgrefId);
			cal.setTime(returnDate);
			cal.add(Calendar.DATE, 1);
			returnDate = cal.getTime();
		} catch (Exception e) {
			logger.error("getWeeklyDefStartDate", e);
		}
		return returnDate;
	}

	@Override
	public Integer getCrsApptGrpId() {
		return tagUnpaidWorkRepository.getCrsApptGrpId();
	}

	// This PROCEDURE update_enddate is migrated from oracle
	// This method is used to update OFFENDER_PROGRAM_PROFILES table
	@Override
	public Integer updateEnddate(final CourseActivities off) {
		Long offPrgrefId = null;
		Date offenderEndDate = null;
		try {
			// This methos CURSOR c1 is migrated from oracle
			final List<Object[]> list = tagUnpaidWorkRepository.c1Select(off.getCrsActyId(), off.getScheduleEndDate());
			for (final Object[] obj : list) {
				offPrgrefId = (Long) obj[0];
				offenderEndDate = (Date) obj[1];
			}
			if(offPrgrefId!=null) {			
				// This method is used to update OFFENDER_PROGRAM_PROFILES table
				tagUnpaidWorkRepository.offenderProgramProfilesUpdate(offenderEndDate, offPrgrefId, off.getModifyUserId());
				// This method is used to DELETE FROM v_offender_course_events table
				tagUnpaidWorkRepository.vOffenderCourseEventsDelete(offenderEndDate, offPrgrefId,off.getModifyUserId());
			}
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

}
