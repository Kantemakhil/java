package net.syscon.s4.cm.programsservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcupatofRepository;
import net.syscon.s4.cm.programsservices.OcupatofService;
import net.syscon.s4.cm.programsservices.OffenderCourseAttendancesCommitBean;
import net.syscon.s4.cm.weeklyactivityplans.WeeklyActivityPlans;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpssetService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.iwp.impl.OcdclogsServiceImpl;
import net.syscon.s4.pkgs.ocupatof.OcupatofPkgService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuService;

@Service
public class OcupatofServiceImpl extends BaseBusiness implements OcupatofService {

	@Autowired
	private OcupatofRepository ocupatofRepository;

	@Autowired
	private VOffenderCourseEventsTuService vOffenderCourseEventsTuService;

	@Autowired
	private OcupatofPkgService ocupatofPkgService;

	@Autowired
	private OcmpssetService ocmpssetService;
	
	@Autowired
	private OcdclogsServiceImpl ocdclogsServiceImpl;

	@Override
	public List<OffenderCourseAttendance> offCrsAttExecuteQuery(OffenderCourseAttendance objO) {
		List<OffenderCourseAttendance> list = ocupatofRepository.offCrsAttExecuteQuery(objO);
		list.forEach(bo -> {
			bo.setStartTime(bo.getInTime());
			bo.setEndTime(bo.getInTime());
			if (Optional.ofNullable(bo.getCrsActyId()).isPresent()) {
				bo.setCatchUpFlag(ocupatofRepository.getCatchupFlag(bo.getCrsSchId()));
				String desc = ocupatofRepository.getModuleDescription(bo.getCrsActyId());
				if (desc != null) {
					bo.setModule(desc);
				} else {
					bo.setModule(ocupatofRepository.getModule(bo));
				}

			}

		});
		return list;
	}

	/**
	 * Insert the records from database table
	 */

	@Transactional
	public Integer offCrsAttCommit(OffenderCourseAttendancesCommitBean CommitBean) {
		int liReturn = 0;
		if (CommitBean.getUpdateList() != null && CommitBean.getUpdateList().size() > 0) {
			for (OffenderCourseAttendance bean : CommitBean.getUpdateList()) {
				if (bean.getViewCode().equals("PAST_PRESENT")) {
					bean.setModifyUserId(CommitBean.getCreateUserId());
					liReturn = ocupatofPkgService.updateOffCrseAttend(bean);
					if (liReturn == 1) {
						ocmpssetService.updateSchedulePay(BigDecimal.valueOf(bean.getOffenderBookId()),
								Integer.valueOf(String.valueOf(bean.getEventId())), "ACP",bean.getModifyUserId());
					}
				} else {
					VOffenderCourseEvents newObj = new VOffenderCourseEvents();
					VOffenderCourseEvents oldObj = new VOffenderCourseEvents();
					if (bean.getEventId() != 0L) {
						oldObj.setEventId(new BigDecimal(bean.getEventId()));
					}
					try {
						BeanUtils.copyProperties(newObj, bean);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
					newObj.setEventId(null);
					newObj.setSessionNo(bean.getSessionNo());
					newObj.setCreateUserId(CommitBean.getCreateUserId());
					newObj.setModifyUserId(CommitBean.getCreateUserId());
					liReturn = vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(oldObj, newObj);
				}
				
				try {
					VOffenderAllSchedules offsch = new VOffenderAllSchedules();
					offsch.setEventDate(bean.getEventDate());
					offsch.setStartTime(bean.getInTime());
					offsch.setEndTime(bean.getOutTime());
					offsch.setOffenderBookId(BigDecimal.valueOf(bean.getOffenderBookId()));
					offsch.setEventOutcome(bean.getEventOutcome());
					//offsch.setToAgyLocId(obj.getCourt());
					offsch.setScheduleType("ACP");
					offsch.setCreateUserId(CommitBean.getCreateUserId());
					offsch.setEventId(BigDecimal.valueOf(bean.getEventId()));
					List<WeeklyActivityPlans> returnList = ocdclogsServiceImpl.getWeeklyActivityPlanData(offsch);
					ocdclogsServiceImpl.saveWeeklyActivitePlanData(returnList, offsch);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgAttendancesRecordGroup() {
		return ocupatofRepository.rgAttendancesRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgAttendancyViewRecordGroup() {
		return ocupatofRepository.rgAttendancyViewRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgEngagementRecordGroup() {
		return ocupatofRepository.rgEngagementRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	@Override
	public List<ReferenceCodes> rgUnderstandingRecordGroup() {
		return ocupatofRepository.rgUnderstandingRecordGroup();

	}

}