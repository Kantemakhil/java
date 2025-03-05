package net.syscon.s4.cm.programsservices.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcuoscpvRepository;
import net.syscon.s4.cm.programsservices.OcuoscpvService;
import net.syscon.s4.cm.programsservices.OffenderCourseApptGrp;
import net.syscon.s4.cm.programsservices.OffenderCourseApptGrpCommitBean;
import net.syscon.s4.cm.programsservices.OffenderCourseApptRule;
import net.syscon.s4.cm.programsservices.OffenderCourseApptRulesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;
import net.syscon.s4.pkgs.tag_unpaid_work.TagUnpaidWorkService;
import net.syscon.s4.triggers.VOffenderCourseEventsTdService;
import net.syscon.s4.triggers.VOffenderCourseEventsTiService;

@Service
public class OcuoscpvServiceImpl extends BaseBusiness implements OcuoscpvService {

	@Autowired
	private OcuoscpvRepository ocuoscpvRepository;
	
	@Autowired
	private VOffenderCourseEventsTiService vOffenderCourseEventsTiService;
	
	@Autowired
	private VOffenderCourseEventsTdService vOffenderCourseEventsTdService;
	
	@Autowired 
	private TagUnpaidWorkService tagUnpaidService;
	
	public static final Integer CONSTANTVALUE = 2;
	public List<CourseActivities> crsActExecuteQuery(final CourseActivities searchRecord) {
		final List<CourseActivities> list = ocuoscpvRepository.crsActExecuteQuery(searchRecord);
		for (final CourseActivities courseActivities : list) {
			final List<CourseActivities> returnData = ocuoscpvRepository
					.gettingProgramServicePostQuerry(courseActivities);

			for (final CourseActivities courseActivities2 : returnData) {
				final String programService = courseActivities2.getDescription();
				courseActivities.setService(programService);
			}

		}
		return list;

	}

	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules searchRecord) {
		return ocuoscpvRepository.crsScheduleRulExecuteQuery(searchRecord);

	}

	public List<VOffenderCourseEvents> offSchExecuteQuery(final VOffenderCourseEvents searchRecord) {
		final List<VOffenderCourseEvents> vOffenderCourseEventsList = ocuoscpvRepository
				.offSchExecuteQuery(searchRecord);
		if (vOffenderCourseEventsList != null && !vOffenderCourseEventsList.isEmpty()) {
			for (final VOffenderCourseEvents obj : vOffenderCourseEventsList) {
				obj.setRecordStatus(true);
			}
		}
		return vOffenderCourseEventsList;
	}

	@Transactional
	public Integer offSchCommit(final VOffenderCourseEventsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final VOffenderCourseEvents courtEvents : commitBean.getInsertList()) {
				Integer holidayCount  = tagUnpaidService.getHolidayCount(courtEvents.getEventDate());
				if (holidayCount > 0) {
					return 3;
				}

				final String[] lvDay = { "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN" };
				if (courtEvents.getCrsActyId() != null && courtEvents.getCourseScheduleRuleId() != 0) {
					if (lvDay[0].equals(courtEvents.getWeekday())) {
						final CourseScheduleRules returnData = ocuoscpvRepository.gettingLvDayMonday(courtEvents);
						if (returnData != null && "N".equals(returnData.getMondayFlag())) {
							return CONSTANTVALUE;
						}

					} else if (lvDay[1].equals(courtEvents.getWeekday())) {
						final CourseScheduleRules returnData = ocuoscpvRepository.gettingLvDayTuesday(courtEvents);
						if (returnData != null && "N".equals(returnData.getTuesdayFlag())) {
							return CONSTANTVALUE;
						}
					} else if (lvDay[2].equals(courtEvents.getWeekday())) {
						final CourseScheduleRules returnData = ocuoscpvRepository.gettingLvDayWednesday(courtEvents);
						if (returnData != null && "N".equals(returnData.getWednesdayFlag())) {
							return CONSTANTVALUE;
						}
					} else if (lvDay[3].equals(courtEvents.getWeekday())) {
						final CourseScheduleRules returnData = ocuoscpvRepository.gettingLvDayThursday(courtEvents);
						if (returnData != null && "N".equals(returnData.getThursdayFlag())) {
							return CONSTANTVALUE;
						}
					} else if (lvDay[4].equals(courtEvents.getWeekday())) {
						final CourseScheduleRules returnData = ocuoscpvRepository.gettingLvDayFriday(courtEvents);
						if (returnData != null && "N".equals(returnData.getFridayFlag())) {
							return CONSTANTVALUE;
						}
					} else if (lvDay[5].equals(courtEvents.getWeekday())) {
						final CourseScheduleRules returnData = ocuoscpvRepository.gettingLvDaySaturday(courtEvents);
						if (returnData != null && "N".equals(returnData.getSaturdayFlag())) {
							return CONSTANTVALUE;
						}
					} else if (lvDay[6].equals(courtEvents.getWeekday())) {
						final CourseScheduleRules returnData = ocuoscpvRepository.gettingLvDaySunday(courtEvents);
						if (returnData != null && "N".equals(returnData.getSundayFlag())) {
							return CONSTANTVALUE;
						}
					}
				} else {
					return 2;
				}
				if ("WR".equals(courtEvents.getProgramCategory())) {
					ocuoscpvRepository.updatingDirectionCode(courtEvents);
				}
			}
			for (final VOffenderCourseEvents courtEvents : commitBean.getInsertList()) {
				commitBean.getInsertList().forEach(bo->{
					bo.setCreateUserId(commitBean.getCreateUserId());
					if(bo.getProgramCategory()!=null && "WR".equalsIgnoreCase(bo.getProgramCategory())) {
						bo.setDirectionCode("OUT");
					}
				});
				liReturn = vOffenderCourseEventsTiService.vOffenderCourseEventsTiTgr(courtEvents);

			}
			if(liReturn ==1 ) {
				commitBean.getInsertList().forEach(data->{
					data.setModifyUserId(commitBean.getCreateUserId());	
				});
				ocuoscpvRepository.offPrgProfilesStatusCommit( commitBean.getInsertList().get(0).getOffPrgrefId(),commitBean.getCreateUserId());
			}

		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for ( VOffenderCourseEvents courtEvents : commitBean.getDeleteList()) {
				liReturn = vOffenderCourseEventsTdService.vOffenderCourseEventsTdTgr(courtEvents, courtEvents);
			}
		}
		return liReturn;
	}

	public List<OffenderCourseApptGrp> weeklyDefExecuteQuery(final OffenderCourseApptGrp searchRecord) {
		return ocuoscpvRepository.weeklyDefExecuteQuery(searchRecord);

	}

	@Transactional
	public Integer weeklyDefCommit(OffenderCourseApptGrpCommitBean commitBean) {
		int liReturn = 0;
		Integer crsApptGrpid = 0;
		OffenderCourseApptRulesCommitBean offschCommitBean = new OffenderCourseApptRulesCommitBean();
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderCourseApptGrp bean : commitBean.getInsertList()) {
				crsApptGrpid = tagUnpaidService.getCrsApptGrpId();
				bean.setOffenderCourseApptGrpId(crsApptGrpid);
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuoscpvRepository.weeklyDefInsertOffenderCourseApptGrps(commitBean.getInsertList());
		}

		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for(OffenderCourseApptGrp bean : commitBean.getUpdateList() ) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuoscpvRepository.weeklyDefUpdateOffenderCourseApptGrps(commitBean.getUpdateList());
		}

		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (OffenderCourseApptGrp offenderCouseAppGrp : commitBean.getDeleteList()) {
				offenderCouseAppGrp.setModifyUserId(commitBean.getCreateUserId());
				offenderPreDelete(offenderCouseAppGrp);
			}
			liReturn = ocuoscpvRepository.weeklyDefDeleteOffenderCourseApptGrps(commitBean.getDeleteList());
		}
		if (commitBean.getOffschInsertList() != null && !commitBean.getOffschInsertList().isEmpty()) {
			for (OffenderCourseApptRule bean : commitBean.getOffschInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				if(bean.getOffenderCourseApptGrpId() == 0) {
					bean.setOffenderCourseApptGrpId(crsApptGrpid);
				}
				
			}
			offschCommitBean.setInsertList(commitBean.getOffschInsertList());
			offschCommitBean.setCreateUserId(commitBean.getCreateUserId());
			liReturn = offSchDefCommit(offschCommitBean);
		}
		if (commitBean.getOffschUpdateList() != null && !commitBean.getOffschUpdateList().isEmpty()) {
			for (OffenderCourseApptRule bean:commitBean.getOffschUpdateList() ) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			offschCommitBean.setCreateUserId(commitBean.getCreateUserId());
			offschCommitBean.setUpdateList(commitBean.getOffschUpdateList());
			liReturn = offSchDefCommit(offschCommitBean);
		}
		if (commitBean.getOffschDeleteList() != null && !commitBean.getOffschDeleteList().isEmpty()) {

			offschCommitBean.setDeleteList(commitBean.getOffschDeleteList());
			liReturn = offSchDefCommit(offschCommitBean);
		}
		if (commitBean.getUpdateOffPrgList() != null && !commitBean.getUpdateOffPrgList().isEmpty()) {
		liReturn = offPrgProfilesCommit(commitBean.getUpdateOffPrgList());
		}
		if(liReturn ==1 && (!commitBean.getInsertList().isEmpty() && commitBean.getInsertList()!= null)) {
			ocuoscpvRepository.offPrgProfilesStatusCommit( commitBean.getInsertList().get(0).getOffPrgrefId(),commitBean.getCreateUserId());
		}
		return liReturn;
	}

	private Integer offenderPreDelete(OffenderCourseApptGrp offenderCouseAppGrp) {
		int liReturn = 0;

		liReturn = ocuoscpvRepository.weeklyDefPreDeleteQuerry(offenderCouseAppGrp);
		return liReturn;

	}

	public List<OffenderCourseApptRule> offSchDefExecuteQuery(OffenderCourseApptRule searchRecord) {
		return ocuoscpvRepository.offSchDefExecuteQuery(searchRecord);

	}

	@Transactional
	public Integer offSchDefCommit(OffenderCourseApptRulesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (OffenderCourseApptRule offenderCourse : commitBean.getInsertList()) {
				offenderCourse.setCreateUserId(commitBean.getCreateUserId());
				if (offenderCourse.getMondayFlag() != null && "true".equals(offenderCourse.getMondayFlag())) {
					offenderCourse.setMondayFlag("Y");
				} else {
					offenderCourse.setMondayFlag("N");
				}
				if (offenderCourse.getTuesdayFlag() != null && "true".equals(offenderCourse.getTuesdayFlag())) {
					offenderCourse.setTuesdayFlag("Y");
				} else {
					offenderCourse.setTuesdayFlag("N");
				}
				if (offenderCourse.getWednesdayFlag() != null && "true".equals(offenderCourse.getWednesdayFlag())) {
					offenderCourse.setWednesdayFlag("Y");
				} else {
					offenderCourse.setWednesdayFlag("N");
				}
				if (offenderCourse.getThursdayFlag() != null && "true".equals(offenderCourse.getThursdayFlag())) {
					offenderCourse.setThursdayFlag("Y");
				} else {
					offenderCourse.setThursdayFlag("N");
				}
				if (offenderCourse.getFridayFlag() != null && "true".equals(offenderCourse.getFridayFlag())) {
					offenderCourse.setFridayFlag("Y");
				} else {
					offenderCourse.setFridayFlag("N");
				}
				if (offenderCourse.getSaturdayFlag() != null && "true".equals(offenderCourse.getSaturdayFlag())) {
					offenderCourse.setSaturdayFlag("Y");
				} else {
					offenderCourse.setSaturdayFlag("N");
				}
				if (offenderCourse.getSundayFlag() != null && "true".equals(offenderCourse.getSundayFlag())) {
					offenderCourse.setSundayFlag("Y");
				} else {
					offenderCourse.setSundayFlag("N");
				}

			}
			liReturn = ocuoscpvRepository.offschdefInsertOffenderCourseApptRules(commitBean.getInsertList());

		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (OffenderCourseApptRule offenderCourse : commitBean.getDeleteList()) {
				offenderCourse.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocuoscpvRepository.OffSchDefDeleteData(commitBean.getDeleteList());

		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (OffenderCourseApptRule offenderCourse : commitBean.getUpdateList()) {
				offenderCourse.setModifyUserId(commitBean.getCreateUserId());
				if (offenderCourse.getMondayFlag() != null && "true".equals(offenderCourse.getMondayFlag())) {
					offenderCourse.setMondayFlag("Y");
				} else {
					offenderCourse.setMondayFlag("N");
				}
				if (offenderCourse.getTuesdayFlag() != null && "true".equals(offenderCourse.getTuesdayFlag())) {
					offenderCourse.setTuesdayFlag("Y");
				} else {
					offenderCourse.setTuesdayFlag("N");
				}
				if (offenderCourse.getWednesdayFlag() != null && "true".equals(offenderCourse.getWednesdayFlag())) {
					offenderCourse.setWednesdayFlag("Y");
				} else {
					offenderCourse.setWednesdayFlag("N");
				}
				if (offenderCourse.getThursdayFlag() != null && "true".equals(offenderCourse.getThursdayFlag())) {
					offenderCourse.setThursdayFlag("Y");
				} else {
					offenderCourse.setThursdayFlag("N");
				}
				if (offenderCourse.getFridayFlag() != null && "true".equals(offenderCourse.getFridayFlag())) {
					offenderCourse.setFridayFlag("Y");
				} else {
					offenderCourse.setFridayFlag("N");
				}
				if (offenderCourse.getSaturdayFlag() != null && "true".equals(offenderCourse.getSaturdayFlag())) {
					offenderCourse.setSaturdayFlag("Y");
				} else {
					offenderCourse.setSaturdayFlag("N");
				}
				if (offenderCourse.getSundayFlag() != null && "true".equals(offenderCourse.getSundayFlag())) {
					offenderCourse.setSundayFlag("Y");
				} else {
					offenderCourse.setSundayFlag("N");
				}
			}
			liReturn = ocuoscpvRepository.OffSchDefUpdateData(commitBean.getUpdateList());

		}
		return liReturn;
	}

	@Override
	public String gettingWeekday(VOffenderCourseEvents searchBean) {

		return ocuoscpvRepository.gettingWeekdayOffSch(searchBean.getEventDate());
	}

	@Override
	public Integer gettingoffSchCheckScheduleConflict(VOffenderCourseEvents searchBean) {
		return ocuoscpvRepository.gettingVReturnNumberForConfict(searchBean);
	}

	@Override
	public Date gettingStartDate(OffenderCourseApptGrp offenderCourseAppgroup) {
		return tagUnpaidService.getWeeklyDefStartDate(offenderCourseAppgroup.getOffPrgrefId());
	}

	@Override
	public List<CourseScheduleRules> copyFromProviderAvailability(CourseScheduleRules searchBean) {
		return ocuoscpvRepository.gettingSheduleRulesData(searchBean);

	}

	@Override
	public List<OffenderProgramProfiles> offPrgProfilesExecuteQuery(OffenderProgramProfiles searchBean) {
		
		return ocuoscpvRepository.offPrgProfilesExecuteQuery(searchBean);
	}

	@Override
	public Integer offPrgProfilesCommit(List<OffenderProgramProfiles> commitBean) {
		return ocuoscpvRepository.offPrgProfilesCommit(commitBean);
	}

}