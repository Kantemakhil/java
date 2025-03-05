package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.programsservices.OcduprojRepository;
import net.syscon.s4.cm.programsservices.OcduprojService;
import net.syscon.s4.cm.programsservices.OcuoscpvService;
import net.syscon.s4.cm.programsservices.OffenderCourseApptGrp;
import net.syscon.s4.cm.programsservices.OffenderCourseApptRule;
import net.syscon.s4.cm.programsservices.OffenderUnpaidWorkAdj;
import net.syscon.s4.cm.programsservices.OffenderUnpaidWorkAdjCommitBean;
import net.syscon.s4.cm.programsservices.VOffenderSentCondActs;
import net.syscon.s4.cm.programsservices.VOffenderSentCondActsCommitBean;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkills;
import net.syscon.s4.cm.programsservices.maintenance.OffenderCourseSkillsCommitBean;
import net.syscon.s4.cm.weeklyactivityplans.OweacplnRepository;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;
import net.syscon.s4.inst.legals.OcucondiService;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.iwp.impl.OcdclogsServiceImpl;
import net.syscon.s4.pkgs.tag_prg.TagPrgService;
import net.syscon.s4.pkgs.tag_unpaid_work.TagUnpaidWorkService;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfService;
import net.syscon.s4.triggers.OffenderProgramProfilesTrService;
import net.syscon.s4.triggers.OffenderUnpaidWorkAdjTr1Service;
import net.syscon.s4.triggers.VOffenderCourseEventsTdService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuRepository;
import net.syscon.s4.triggers.VOffenderCourseEventsTuService;

/**
 * Class OcduprojServiceImpl
 */
@Service
public class OcduprojServiceImpl extends BaseBusiness implements OcduprojService {
	
	private static Logger logger = LogManager.getLogger(OcduprojServiceImpl.class);

	
	@Autowired
	private OcduprojRepository ocduprojRepository;

	@Autowired
	private TagUnpaidWorkService tagUnpaidWorkService;

	@Autowired
	private OffenderProgramProfilesTrService offenderProgramProfilesTrService;

	@Autowired
	private VOffenderCourseEventsTuService vOffenderCourseEventsTuService;

	@Autowired
	private OffenderUnpaidWorkAdjTr1Service offenderUnpaidWorkAdjTr1Service;

	@Autowired
	private OcuoscpvService ocuoscpvServiceImpl;
	
	@Autowired
	private OcucondiService ocucondiService;
	
	@Autowired
	private VOffenderCourseEventsTdService vOffenderCourseEventsTdService;
	
	@Autowired
	VOffenderCourseEventsTuRepository vOffenderCourseEventsTuRepository;
	
	@Autowired
	private OffenderCourseAttndnsTwfService offenderCourseAttndnsTwfService;
	
	@Autowired
	private TagPrgService tagPrgService;
		

	@Autowired
	private OcdclogsServiceImpl ocdclogsServiceImpl;
	
	private static final String COMP = "COMP";
	private static final String END = "END";
	private static final String REM = "REM";
	
	@Autowired
	private OweacplnRepository oweacplnRepository;

	/**
	 * Fetch the records from database table
	 *
	 */
	public List<VOffenderSentCondActs> unpaidWkExecuteQuery(final VOffenderSentCondActs searchRecord) {
//		List<VOffenderSentCondActs> returnList = ocduprojRepository.unpaidWkExecuteQuery(searchRecord);
		/*
		 * This is method(getSentenceData) is used to retrieve data from OCDLEGLO screen(Custodial Orders).
		 */
		List<VOffenderSentCondActs> returnList = getSentenceData(searchRecord) ;
		List<VOffenderSentCondActs> newList = new ArrayList<VOffenderSentCondActs>();
//		returnList.stream().forEach(i -> {
//			if (i.getOffenderSentConditionId() != null) {
//				if (Optional.ofNullable(i.getConditionStatus()).isPresent() && i.getConditionStatus().equals("A")) {
//					i.setSentenceStatusDesc("ACTIVE");
//				} else {
//					i.setSentenceStatusDesc("INACTIVE");
//				}
//			} else if (i.getSentenceStatus().equals("A")) {
//				i.setSentenceStatusDesc("ACTIVE");
//			} else {
//				i.setSentenceStatusDesc("INACTIVE");
//			}
//			if (i.getLength() == null || i.getLength().compareTo(BigDecimal.ZERO) == 0) {
//				BigDecimal length =tagUnpaidWorkService.getOffSentTermHrs(i.getOffenderBookId(), i.getSentenceSeq());
//				if (Optional.ofNullable(length).isPresent()) {
//					i.setLength(length);
//				}
//			}
//			i.setRemaining(i.getLength().subtract(i.getCreditedUnits()));
//		});
		for (VOffenderSentCondActs i : returnList) {
			BigDecimal creditedHours = tagPrgService.creditHours(i.getOffenderBookId(), i.getSentenceSeq(), i.getOffenderSentConditionId());
			i.setCreditedUnits(creditedHours);
			//S4-21198
			/*if (i.getOffenderSentConditionId() != null) {
				if (Optional.ofNullable(i.getConditionStatus()).isPresent() && (i.getConditionStatus().equals("A")|| i.getConditionStatus().equalsIgnoreCase("Active"))) {
					i.setSentenceStatusDesc("ACTIVE");
				} else {
					i.setSentenceStatusDesc("INACTIVE");
				}
			} else if (i.getSentenceStatus()!=null&&i.getSentenceStatus().equals("A")) {
				i.setSentenceStatusDesc("ACTIVE");
			} else if(i.getSentenceStatus()!=null) {
				i.setSentenceStatusDesc("INACTIVE");
			} */
//			if (i.getConditionLength() != null && i.getConditionLength().contains("DAYS")) {
//				Integer days  = Integer.parseInt(i.getConditionLength().substring(0,i.getConditionLength().indexOf(" ")));
//				//Integer day= Integer.valueOf(i.getConditionLength().substring(0,i.getConditionLength().indexOf(" ")));
//				BigDecimal length =new BigDecimal( days*24);
//				if (Optional.ofNullable(length).isPresent()) {
//					i.setLength(length );
//				}
//			}
//			if (i.getConditionLength() != null && i.getConditionLength().contains("WEEKS")) {
//				Integer days  = Integer.parseInt(i.getConditionLength().substring(0,i.getConditionLength().indexOf(" ")));
//				//Integer day= Integer.valueOf(i.getConditionLength().substring(0,i.getConditionLength().indexOf(" ")));
//				BigDecimal length =new BigDecimal( days*168);
//				if (Optional.ofNullable(length).isPresent()) {
//					i.setLength(length );
//				}
//			}
			if( i.getConditionLength()!=null && i.getConditionLength().contains("HOURS")) {
				String str  = i.getConditionLength().substring(0,i.getConditionLength().indexOf(" "));
				i.setLength(new BigDecimal(str));
			}
			
			if(i.getLength()!=null && i.getCreditedUnits()!=null) {
				i.setRemaining(i.getLength().subtract(i.getCreditedUnits()));
			}
			newList.add(i);
		}

//		returnList.forEach(action -> {
//			if (action.getRemaining() != null) {
//				String number = action.getRemaining().toString();
//				if (number.startsWith("-")) {
//					action.setRemaining(BigDecimal.ZERO);
//				}
//			}
//		});
		newList.forEach(action -> {
			if (action.getRemaining() != null) {
				String number = action.getRemaining().toString();
				if (number.startsWith("-")) {
					action.setRemaining(BigDecimal.ZERO);
				}
			}
		});
		return newList;
	}

	/**
	 * Insert the records from database table
	 */
	@Transactional
	public Integer unpaidWkCommit(final VOffenderSentCondActsCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 */
	public List<OffenderProgramProfiles> projAllocExecuteQuery(final OffenderProgramProfiles searchRecord) {
		return ocduprojRepository.projAllocExecuteQuery(searchRecord);
		/*if (returnList.size() > 0) {
			for (OffenderProgramProfiles i : returnList) {
				Map<String, Object> returnObject = tagUnpaidWorkService.getProjectAllocDetails(i.getCrsActyId());
				i.setProjectCode(
						returnObject.get("p_project_code") != null ? (String) returnObject.get("p_project_code")
								: null);
				i.setProjectDescription(
						returnObject.get("p_project_desc") != null ? (String) returnObject.get("p_project_desc")
								: null);
				i.setMaxCapacity(returnObject.get("p_max_capacity") != null
						? new BigDecimal((Long) returnObject.get("p_max_capacity"))
						: null);
				i.setpProgramId(returnObject.get("p_program_id") != null
						? new BigDecimal((Long) returnObject.get("p_program_id"))
						: null);
				i.setScheduleStartDate(
						returnObject.get("p_sch_start_date") != null ? (Date) returnObject.get("p_sch_start_date")
								: null);
				i.setScheduleEndDate(
						returnObject.get("p_sch_end_date") != null ? (Date) returnObject.get("p_sch_end_date") : null);
				i.setTeamDescription(
						returnObject.get("p_team_desc") != null ? (String) returnObject.get("p_team_desc") : null);
				i.setNbtteamAreaCode(
						returnObject.get("p_team_area_code") != null ? (String) returnObject.get("p_team_area_code")
								: null);
				i.setNbtAgyLocId(
						returnObject.get("p_team_agy_loc_id") != null ? (String) returnObject.get("p_team_agy_loc_id")
								: null);
		
				CourseActivities courseActivities = ocduprojRepository.getProjectCode(i.getProjectCode(),
						i.getCrsActyId());
				if (courseActivities != null && Optional.ofNullable(courseActivities).isPresent()
						&& Optional.ofNullable(courseActivities.getCode()).isPresent()) {
					i.setCode(courseActivities.getCode());
				}
			}
		}*/
	}

	public Integer projAllocOnDeleteQuery(final OffenderProgramProfiles searchBean) {
		return ocduprojRepository.projAllocOnDeleteQuery(searchBean);
	}

	/**
	 * Insert the records from database table
	 */

	@Transactional
	public Integer projAllocCommit(final OffenderProgramProfilesCommitBean commitBean) {
		int liReturn = 0;
		 
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderProgramProfiles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				Integer val = offenderProgramProfilesTrService.offenderProgramProfilesTr(obj);
			}
			liReturn = ocduprojRepository.projAllocInsertOffenderProgramProfiles(commitBean.getInsertList());
			return liReturn;
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderProgramProfiles obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				Integer val = offenderProgramProfilesTrService.offenderProgramProfilesTr(obj);
			}
			//update eventStatus
			statusUpdate(commitBean);
			commitBean.getUpdateList().forEach(bo->{
				List<WeeklyActivityPlans> finalSaveList = new ArrayList<WeeklyActivityPlans>();
				if("CANC".equalsIgnoreCase(bo.getOffenderProgramStatus()) || ("COMP".equalsIgnoreCase(bo.getOffenderProgramStatus()) && bo.getOffenderEndDate()==null) || ("END".equalsIgnoreCase(bo.getOffenderProgramStatus()) && bo.getOffenderEndDate()==null) || ("REM".equalsIgnoreCase(bo.getOffenderProgramStatus()) && bo.getOffenderEndDate()==null)) {
					bo.setOffenderEndDate(new Date());
				}
				List<OffenderProgramProfiles> oldObj = ocduprojRepository.projectOldData(bo); 
				oldObj.forEach(obj -> {
					if("COMP".equalsIgnoreCase(obj.getOffenderProgramStatus()) || "END".equalsIgnoreCase(obj.getOffenderProgramStatus()) || "REM".equalsIgnoreCase(obj.getOffenderProgramStatus())) {
						if(("ALLOC".equalsIgnoreCase(bo.getOffenderProgramStatus()))) {
							bo.setOffenderEndDate(null);
						}	
					}
				});
			
				try {
					VOffenderAllSchedules offsch = new VOffenderAllSchedules();
					offsch.setEventDate(bo.getOffenderStartDate());
					offsch.setOffenderBookId(BigDecimal.valueOf(bo.getOffenderBookId()));
					offsch.setScheduleType("CS");
					offsch.setCreateUserId(commitBean.getCreateUserId());
					List<WeeklyActivityPlans> returnList = ocdclogsServiceImpl.getWeeklyActivityPlanData(offsch);
					List<WeeklyActivityPlans> saveList = new ArrayList<WeeklyActivityPlans>();
					for (WeeklyActivityPlans weeklyActivityPlans : returnList) {
						if(weeklyActivityPlans.getOffPrgrefId()!=null && "CS".equals(weeklyActivityPlans.getRecordSource()) && (BigDecimal.valueOf(bo.getOffPrgrefId()).compareTo(weeklyActivityPlans.getOffPrgrefId()) == 0)) {
							if("END".equals(bo.getOffenderProgramStatus()) || "REM".equals(bo.getOffenderProgramStatus()) || "COMP".equals(bo.getOffenderProgramStatus())) {
								if(weeklyActivityPlans.getActivityDate()!=null && bo.getOffenderStartDate()!=null && weeklyActivityPlans.getActivityDate().compareTo(bo.getOffenderStartDate()) < 1) {
									saveList.add(weeklyActivityPlans);
								}
							}
						} else {
							saveList.add(weeklyActivityPlans);
						}
					}	
					
					for (WeeklyActivityPlans saveObj : saveList) {
						finalSaveList = new ArrayList<WeeklyActivityPlans>();
						if(saveObj.getVersionNo()!=null) {
							if(saveObj.getHtyVersionNo()!=null) {						
								saveObj.setVersionNo(saveObj.getHtyVersionNo().add(BigDecimal.ONE));
							} else {
								saveObj.setVersionNo(saveObj.getVersionNo());
							}
							saveObj.setModifyUserId(commitBean.getCreateUserId());
							saveObj.setFinalized("N");
							finalSaveList.add(saveObj);
						}
					}
					
					if(!finalSaveList.isEmpty()) {					
						 oweacplnRepository.weeklyActivityCommitUpdateData(finalSaveList);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			});
			liReturn = ocduprojRepository.projAllocUpdateOffenderProgramProfiles(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderProgramProfiles obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocduprojRepository.projAllocDeleteOffenderProgramProfiles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 */
	public List<VOffenderCourseEvents> attendanceExecuteQuery(final VOffenderCourseEvents searchRecord) {
		return ocduprojRepository.attendanceExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 */
	@Transactional
	public Integer attendanceCommit(final VOffenderCourseEventsCommitBean commitBean) {
		int liReturn = 0;
		List<ReferenceCodes> refCodeList = ocduprojRepository.rgAttendanceRecordGroup();
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (VOffenderCourseEvents voffevent : commitBean.getUpdateList()) {			
						
				        VOffenderAllSchedules offsch = new VOffenderAllSchedules();
						offsch.setEventDate(voffevent.getEventDate());
						offsch.setStartTime(voffevent.getInTime());
						offsch.setEndTime(voffevent.getOutTime());
						offsch.setOffenderBookId(voffevent.getOffenderBookId());
						offsch.setEventOutcome(voffevent.getEventOutcome());
						offsch.setScheduleType("CS");
						offsch.setCreateUserId(commitBean.getCreateUserId());
						offsch.setEventId(voffevent.getEventId());
						List<WeeklyActivityPlans> returnList = ocdclogsServiceImpl.getWeeklyActivityPlanData(offsch);
						String statusValue="";
						for (ReferenceCodes referenceCodes : refCodeList) {
							if(referenceCodes.getCode().equals(voffevent.getEventOutcome())) {
								if("Y".equals(referenceCodes.getActiveFlag())) {
									statusValue = "CANC";
								} else {
									statusValue = "COMP";
								}
							}
						}
						
									
				if (voffevent.getEventId() != null && voffevent.getEventId().intValue() > 0) {
					if (voffevent.getCrsActyId() != null
							&& (voffevent.getCrsActyId().longValue() != voffevent.getNbtRefCrsActyId())
							&& (voffevent.getOffPrgrefId() != voffevent.getNbtProjectRefStrId())) {
						voffevent.setEventStatus(statusValue);
						voffevent.setEventClass("COMM");
						// procedure
						// int result = ocduprojRepository.createOffCourseAttendance(voffevent);
						int result = tagUnpaidWorkService.createOffCourseAttendance(voffevent,
								voffevent.getCreateUserId());
						//voffevent.setEventStatus("CANC");
						voffevent.setEventClass("COMM");
						//voffevent.setEventOutcome("CANC");
					} else {
						voffevent.setEventStatus(statusValue);
						voffevent.setEventClass("COMM");
					}
				} else {
					voffevent.setEventStatus(statusValue);
					voffevent.setEventClass("COMM");
				}

				// liReturn =
				// ocduprojRepository.attendanceUpdateVOffenderCourseEvents(commitBean.getUpdateList());
				List<VOffenderCourseEvents> oldObjList = ocduprojRepository.getOldRecvOffenderCourseEvents(
						voffevent.getEventId() != null ? voffevent.getEventId().longValue() : null);
				VOffenderCourseEvents oldObj = new VOffenderCourseEvents();
				if ( oldObjList != null && !oldObjList.isEmpty()) {
					oldObj = oldObjList.get(0);
				}
				voffevent.setCreateUserId(commitBean.getCreateUserId());
				liReturn = vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(oldObj, voffevent);
				if(liReturn > 1) {
//					ocduprojRepository.unpaidWorkCreditHours(commitBean.getUpdateList().get(0));
					tagUnpaidWorkService.updateCredithrs(voffevent, commitBean.getCreateUserId());
				}
				
				if(!returnList.isEmpty()) {			
					ocdclogsServiceImpl.saveWeeklyActivitePlanData(returnList, offsch);
				}
			}
			
			
			
		}

		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 */
	public List<OffenderCourseSkills> skillsExecuteQuery(final OffenderCourseSkills searchRecord) {
		List<OffenderCourseSkills> list = ocduprojRepository.skillsExecuteQuery(searchRecord);
		list.forEach(bo -> {
			if (bo.getStaffId() != null) {
				OffenderCourseSkills name = ocduprojRepository.firstNameLastName(bo);
				bo.setFirstName(name.getFirstName());
				bo.setLastName(name.getLastName());
			}

		});

		return list;

	}

	/**
	 * Insert the records from database table
	 *
	 */
	@Transactional
	public Integer skillsCommit(final OffenderCourseSkillsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if(commitBean.getEventUpdateList() != null && commitBean.getEventUpdateList().size() > 0) {
			Integer eventId = ocduprojRepository.getEventId();
			commitBean.getEventUpdateList().get(0).setEventId(new BigDecimal(eventId));
			
			 vOffenderCourseEventsTuRepository.insert(commitBean.getEventUpdateList().get(0));
			 commitBean.getEventUpdateList().get(0).setEventId(null);
			 OffenderCourseAttendances offCa = new OffenderCourseAttendances();
			BeanUtils.copyProperties(commitBean.getEventUpdateList().get(0), offCa);
			 offenderCourseAttndnsTwfService.offenderCourseAttndnsTwf(offCa, "INSERTING");
			 for (OffenderCourseSkills obj : commitBean.getInsertList()) {
					obj.setEventId(eventId.longValue());
				}
			//commitBean(eventsCommitBean);
		}
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderCourseSkills obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocduprojRepository.skillsInsertOffenderCourseSkills(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderCourseSkills obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocduprojRepository.skillsUpdateOffenderCourseSkills(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (OffenderCourseSkills obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocduprojRepository.skillsDeleteOffenderCourseSkills(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 */
	public List<OffenderUnpaidWorkAdj> creditAdjExecuteQuery(final OffenderUnpaidWorkAdj searchRecord) {
		List<OffenderUnpaidWorkAdj> list = ocduprojRepository.creditAdjExecuteQuery(searchRecord);
		list.forEach(bo -> {
			String useName = ocduprojRepository.userNameRecordGroup(bo.getCreateUserId());
			bo.setCreateUserId(useName);
		});
		return list;

	}

	/**
	 * Insert the records from database table
	 *
	 */
	@Transactional
	public Integer creditAdjCommit(final OffenderUnpaidWorkAdjCommitBean commitBean) {
		int liReturn = 0;
		List<OffenderUnpaidWorkAdj> list = new ArrayList<OffenderUnpaidWorkAdj>();
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderUnpaidWorkAdj obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				list.add(obj);
			}

			try {
				Integer triVal = offenderUnpaidWorkAdjTr1Service.offenderUnpaidWorkAdjTr1Tgr(list);
			} catch (CustomException e) {
				e.printStackTrace();
			}
			liReturn = ocduprojRepository.creditAdjInsertOffenderUnpaidWorkAdj(commitBean.getInsertList());

			return liReturn;
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgAttendanceRecordGroup() {
		return ocduprojRepository.rgAttendanceRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgSupervisorRecordGroup(final Long crsActyId) {
		List<ReferenceCodes> returnList = ocduprojRepository.rgSupervisorRecordGroup(crsActyId);
		returnList.forEach(action -> {
			action.setCodes(action.getCode());
			action.setCode(action.getStaffId().toString());
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgBehaviourRecordGroup() {
		return ocduprojRepository.rgBehaviourRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgWorkQualityRecordGroup() {
		return ocduprojRepository.rgWorkQualityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<CourseActivities> rgProjectCheckRecordGroup() {
		return ocduprojRepository.rgProjectCheckRecordGroup();

	}

	/**
	 * This method is used to execute a record groupn
	 */
	public List<CourseActivities> rgProjectRecordGroup(final Long offenderBookId) {
		List<CourseActivities> returnList = ocduprojRepository.rgProjectRecordGroup(offenderBookId);
		Long order = 1L;
		for (CourseActivities ref : returnList) {
			order = order + 1;
			ref.setListSeq(order);
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgSkillsRecordGroup() {
		return ocduprojRepository.rgSkillsRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<StaffMembers> rgStaffCheckRecordGroup() {
		return ocduprojRepository.rgStaffCheckRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgAdjReasonRecordGroup() {
		return ocduprojRepository.rgAdjReasonRecordGroup();

	}

	@Override
	public List<OffenderProgramProfiles> unpaidWkOnCheckDeleteMaster(final OffenderProgramProfiles paramBean) {
		return ocduprojRepository.unpaidWkOnCheckDeleteMaster(paramBean);
	}

	@Override
	public List<VOffenderCourseEvents> projAllocOnCheckDeleteMaster(final VOffenderCourseEvents paramBean) {
		return ocduprojRepository.projAllocOnCheckDeleteMaster(paramBean);
	}

	@Override
	public List<OffenderCourseSkills> attendanceOnCheckDeleteMaster(final OffenderCourseSkills paramBean) {
		return ocduprojRepository.attendanceOnCheckDeleteMaster(paramBean);
	}

	@Override
	public List<VOffenderSentCondActs> offBkgOnCheckDeleteMaster(final VOffenderSentCondActs paramBean) {
		return ocduprojRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

	public List<ReferenceCodes> reaonsReferenceCodes() {
		List<ReferenceCodes> list = new ArrayList<ReferenceCodes>();
		ReferenceCodes data = new ReferenceCodes();
		data.setDescription("Credit");
		data.setCode("C");
		list.add(data);
		ReferenceCodes dataObj = new ReferenceCodes();
		dataObj.setDescription("Debit");
		dataObj.setCode("D");
		list.add(dataObj);
		return list;
	}

	@Override
	public Integer getActiveFlag(Long offenderId) {
		return ocduprojRepository.getActiveFlag(offenderId);
	}

	@Override
	public List<VHeaderBlock> getLastAndFirstName(Long offenderId) {
		return ocduprojRepository.getLastAndFirstName(offenderId);
	}

	@Override
	public String checkingNonAssociations(final OffenderProgramProfilesCommitBean commitBean) {
		String returnData = "";
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			returnData = generateNonAssociationMessage(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			returnData = generateNonAssociationMessage(commitBean.getInsertList());
		}
		if (returnData != null && returnData.length() > 0)
			returnData = getFinalMessageString(returnData);
		if ("".equals(returnData)) {
			returnData = ApplicationConstants.EMPTYDATA;
		}
		return returnData;
	}

	public String generateNonAssociationMessage(List<OffenderProgramProfiles> insertList) {
		String returnMessage = "";
		boolean schedulesFound = false;
		for (OffenderProgramProfiles search : insertList) {
			List<OffenderNonAssociations> returnList = ocduprojRepository.checkNonAssociations(search);
			int count;
			if (returnList.size() > 0) {
				for (OffenderNonAssociations offNonAss : returnList) {
					schedulesFound = false;
					count = 0;
					List<OffenderProgramProfiles> nonAssOffProgProfList = ocduprojRepository
							.checkNonAssociationProgramAssignment(search, offNonAss);
					SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat time = new SimpleDateFormat("HH:mm");
					if (nonAssOffProgProfList != null && nonAssOffProgProfList.size() > 0) {
						String message = "";
						for (OffenderProgramProfiles nonAssOffProgProf : nonAssOffProgProfList) {
							count++;
							if (count == 1) {
								VHeaderBlock bean = ocduprojRepository
										.ocdprogrGetOffenderNames(nonAssOffProgProf.getOffenderBookId());
								message = bean.getLastName() + " " + bean.getFirstName() + ", "
										+ bean.getOffenderIdDisplay() + "\n";
							}
							OffenderCourseApptGrp searchBean = new OffenderCourseApptGrp();
							searchBean.setOffPrgrefId(BigDecimal.valueOf(nonAssOffProgProf.getOffPrgrefId()));
							List<OffenderCourseApptGrp> offenderCourseApptGrpList = ocuoscpvServiceImpl
									.weeklyDefExecuteQuery(searchBean);
							if (offenderCourseApptGrpList != null && offenderCourseApptGrpList.size() > 0) {
								schedulesFound = true;
								message = message + "ocuoscpv.weeklydefinition:\n";
								String daysList = "";
								String startDate = "";
								String endDate = "";
								String startTime = "";
								String endTime = "";
								for (OffenderCourseApptGrp offenderCourseApptGrp : offenderCourseApptGrpList) {
									if (offenderCourseApptGrp.getEndDate().after(new Date())
											|| date.format(offenderCourseApptGrp.getEndDate())
													.equals(date.format(new Date()))) {
										OffenderCourseApptRule searchRecord = new OffenderCourseApptRule();
										searchRecord.setOffenderCourseApptGrpId(
												offenderCourseApptGrp.getOffenderCourseApptGrpId());

										List<OffenderCourseApptRule> offenderCourseApptRuleList = ocuoscpvServiceImpl
												.offSchDefExecuteQuery(searchRecord);
										if (offenderCourseApptRuleList != null
												&& offenderCourseApptRuleList.size() > 0) {
											startDate = date.format(offenderCourseApptGrp.getStartDate());
											endDate = date.format(offenderCourseApptGrp.getEndDate());
											for (OffenderCourseApptRule offenderCourseApptRule : offenderCourseApptRuleList) {
												daysList = "";
												if (offenderCourseApptRule.getMondayFlag().equalsIgnoreCase("Y"))
													daysList = daysList + "Monday, ";
												if (offenderCourseApptRule.getTuesdayFlag().equalsIgnoreCase("Y"))
													daysList = daysList + "Tuesday, ";
												if (offenderCourseApptRule.getWednesdayFlag().equalsIgnoreCase("Y"))
													daysList = daysList + "Wednesday, ";
												if (offenderCourseApptRule.getThursdayFlag().equalsIgnoreCase("Y"))
													daysList = daysList + "Thursday, ";
												if (offenderCourseApptRule.getFridayFlag().equalsIgnoreCase("Y"))
													daysList = daysList + "Friday, ";
												if (offenderCourseApptRule.getSaturdayFlag().equalsIgnoreCase("Y"))
													daysList = daysList + "Saturday, ";
												if (offenderCourseApptRule.getSundayFlag().equalsIgnoreCase("Y"))
													daysList = daysList + "Sunday, ";
												startTime = time.format(offenderCourseApptRule.getStartTime());
												endTime = time.format(offenderCourseApptRule.getEndTime());
												message = message + startDate + ", " + endDate + ", " + daysList
														+ startTime + ", " + endTime + " \n";
											}
										}
									}
								}
							}
							List<VOffenderCourseEvents> manualList = ocduprojRepository
									.getOffederManualSchedules(nonAssOffProgProf);
							if (manualList != null && manualList.size() > 0) {
								String startDate = "";
								String startTime = "";
								String endTime = "";
								message = message + "ocuoscpv.offenderschedules: \n";
								for (VOffenderCourseEvents manual : manualList) {
									if (manual.getEventDate().after(new Date())
											|| date.format(manual.getEventDate()).equals(date.format(new Date()))) {
										String daysList = "";
										schedulesFound = true;
										startDate = date.format(manual.getEventDate());
										startTime = time.format(manual.getStartTime());
										endTime = time.format(manual.getEndTime());
										if (manual.getWeekday().equalsIgnoreCase("MON"))
											daysList = daysList + "Monday, ";
										if (manual.getWeekday().equalsIgnoreCase("TUE"))
											daysList = daysList + "Tuesday, ";
										if (manual.getWeekday().equalsIgnoreCase("WED"))
											daysList = daysList + "Wednesday, ";
										if (manual.getWeekday().equalsIgnoreCase("THU"))
											daysList = daysList + "Thursday, ";
										if (manual.getWeekday().equalsIgnoreCase("FRI"))
											daysList = daysList + "Friday, ";
										if (manual.getWeekday().equalsIgnoreCase("SAT"))
											daysList = daysList + "Saturday, ";
										if (manual.getWeekday().equalsIgnoreCase("SUN"))
											daysList = daysList + "Sunday, ";
										message = message + startDate + ", " + daysList + startTime + ", " + endTime
												+ " \n ";
									}
								}
							}
						}
						if (!schedulesFound) {
							message = message + "ocduproj.schedulesnotfound \n";
						}
						returnMessage = returnMessage + message + "\n";
					}
				}
			}
		}
		return returnMessage;
	}

	public String getFinalMessageString(final String messageData) {
		return "ocduproj.programnonassciationmessage  \n\n " + messageData + "\n ocduproj.doyouwanttocontinue";
	}
	
	
	@Transactional
	private Integer statusUpdate(OffenderProgramProfilesCommitBean commitBean) {
		Integer count = 0;
		for (OffenderProgramProfiles bean : commitBean.getUpdateList()) {
			List<VOffenderCourseEvents> list = new ArrayList<VOffenderCourseEvents>();
			List<ReferenceCodes> referCodeList = ocduprojRepository.getReferenceDomainCodes("OFF_PRG_STS");	
			String statusValue="";
			if (bean != null) {
				bean.setOffenderEndDate(new Date());
				list = ocduprojRepository.getVOffenderCourseEventsData(bean.getOffPrgrefId(),bean.getOffenderEndDate());
				
				for (ReferenceCodes referenceCodes : referCodeList) {
					if(referenceCodes.getCode().equals(bean.getOffenderProgramStatus())) {
						if("A".equals(referenceCodes.getParentCode())) {
							statusValue ="SCH";
						} else {
							statusValue ="PROG_CANC";
						}
						
					}
				}
				
				if (list.size() > 0 && !list.isEmpty()) {
				for( VOffenderCourseEvents newObj :list) {
					newObj.setModifyUserId(commitBean.getCreateUserId());
					newObj.setCreateUserId(commitBean.getCreateUserId());
					newObj.setEventStatus(statusValue);
					newObj.setCrsActyId(new BigDecimal(bean.getCrsActyId()));
					if("SCH".equals(statusValue)) {
						newObj.setEventOutcome("");
					}
					vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(newObj, newObj);
				}
//				count = ocduprojRepository.updateVOffenderCourseEventsDataStatus(list);
				}
				

//					count = ocduprojRepository.updateVOffenderCourseEventsDataStatus(list);
			}
		}
		return count;
	}

	
	/*
	 * This method is used to Retrieve data from OCDLEGLO screen
	 */
	public List<VOffenderSentCondActs> getSentenceData(VOffenderSentCondActs searchBean) {
		List<VOffenderSentCondActs> SetencetList = new ArrayList<VOffenderSentCondActs>();
		List<VOffenderSentCondActs> returnList = new ArrayList<VOffenderSentCondActs>();
		List<SentenceCalcTypes> sentTypeList = new ArrayList<SentenceCalcTypes>();
		try {
			SetencetList = ocduprojRepository.getSentenceData(searchBean);
			sentTypeList = senTypeExecuteQuery("NCUS");
			for (VOffenderSentCondActs obj : SetencetList) {
				obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
				ObjectMapper orderMapper = new ObjectMapper();
				Map<String, String> orderMap = orderMapper.readValue(obj.getFormIdentifier(), Map.class);
				if(orderMap.get("orderType")!= null && orderMap.get("orderType").equalsIgnoreCase("NONCUST") ) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					Map<String, Object> map = mapper.readValue(obj.getFormInfoJson(), Map.class);
					if (map != null && map.get("myJsonRowData") != null) {
						List<Map<String, Object>> Sentencelist = new ArrayList();
						Sentencelist = (List<Map<String, Object>>) map.get("myJsonRowData");
						for (Map<String, Object> sentenceObj : Sentencelist) {
							Integer orderNo = null;
							try {
								orderNo = (Integer) sentenceObj.get("orderNo");;
							} catch (Exception e) {
								logger.error("Exception in " + this.getClass().getName() +" getSentenceData method while converting OrderNo to Integer", e.getMessage());
							}
							if(orderNo == null) {
								continue;
							}
							obj.setOffenderBookId(searchBean.getOffenderBookId());
							String sentenceSeq = sentenceObj.get("displayNo").toString();
							if (sentenceSeq != null/* && sentenceSeq == obj.getSentenceSeq() */) {
								List<VOffenderSentCondActs> filteredList = returnList.stream()
										.filter(e -> e.getOffenderBookId().equals(obj.getOffenderBookId())
												&& e.getSentenceSeqNo() ==sentenceObj.get("displayNo").toString()
												)
										.collect(Collectors.toList());
								
								if (filteredList != null && filteredList.size() == 0) {
									VOffenderSentCondActs offenderConditions = new VOffenderSentCondActs();
									offenderConditions.setOffenderBookId(obj.getOffenderBookId());
									offenderConditions.setOrderType(orderMap.get("orderType")!= null ? orderMap.get("orderType").toString():null);
									String court = ocduprojRepository.getCourtrecords(sentenceObj.get("court")!=null?sentenceObj.get("court").toString():null);
									offenderConditions.setCourtName(court);
									offenderConditions.setSentenceSeqNo(sentenceObj.get("displayNo").toString());
									offenderConditions.setSentenceSeq(new BigDecimal( sentenceObj.get("orderNo").toString()));
//									offenderConditions.setSentenceStatusDesc(sentenceObj.get("status")!=null ? sentenceObj.get("status").toString():null);
									List<SentenceCalcTypes> sentFilterList = sentTypeList.stream()
											.filter(e -> e.getCode().equals(sentenceObj.get("sentenceCalcType").toString()))
											.collect(Collectors.toList());
									offenderConditions.setSentenceCategory((sentFilterList!=null && sentFilterList.size()>0)?sentFilterList.get(0).getSentenceCategory():null);

									offenderConditions.setSentenceDesc((sentFilterList!=null && sentFilterList.size()>0)?sentFilterList.get(0).getDescription():null);
									List<OffenderSentConditions> offCond =  new ArrayList<OffenderSentConditions>();
									OffenderSentConditions searchObj = new OffenderSentConditions();
									searchObj.setSentenceSeqNo(sentenceObj.get("orderNo").toString());
									searchObj.setCommConditionType("NCUS");
									searchObj.setSealFlag("Y");
									searchObj.setOffenderBookId(searchBean.getOffenderBookId().longValue()) ;
									offCond = ocucondiService.getConditionTypeGrid(searchObj);		
									if(!offCond.isEmpty() && offCond.size()>0) {
										offCond.forEach(action -> {
											if (action.getSentenceSeq() != null
													&& action.getSentenceSeq()
															.equals(offenderConditions.getSentenceSeq())
													&& action.getProgramMethod() != null
													&& action.getProgramMethod().equalsIgnoreCase("UW")) {
												VOffenderSentCondActs offenderConditionsTemp = new VOffenderSentCondActs();
												BeanUtils.copyProperties(offenderConditions, offenderConditionsTemp);
												offenderConditionsTemp.setConditionStartDate(action.getStartDate());
												offenderConditionsTemp.setConditionEndDate(action.getExpiryDate());
												offenderConditionsTemp.setOffenderSentConditionId(new BigDecimal(action.getOffenderSentConditionId()));
                                            String condLength = (action.getLength()!=null?action.getLength():"") +" " +(action.getLengthUnit()!=null?action.getLengthUnit():"");

                                            offenderConditionsTemp.setConditionLength(condLength);
                                            offenderConditionsTemp.setConditionStatus(action.getConditionStatus());
                                            offenderConditionsTemp.setCaseInfoNumber(sentenceObj.get("matter")!= null ? sentenceObj.get("matter").toString():null);
                                            offenderConditionsTemp.setSentenceStatusDesc(action.getConditionStatus()!=null ? action.getConditionStatus():null);
												returnList.add(offenderConditionsTemp);
											}
										
										});
									}
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error("Exception in OcduprojServiceImpl class getSentenceData method", e.getMessage());
				}
			}
			}
		} catch (Exception e) {
			logger.error("Exception in OcondawaitServiceImpl class getSentenceData method", e.getMessage());
		}
		return returnList;
	}
	
	public List<SentenceCalcTypes> senTypeExecuteQuery(String senCategory) {
		return ocduprojRepository.senTypeExecuteQuery(senCategory);

	}
	


}