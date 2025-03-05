package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmschrcRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmschrcService;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmsoschRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRulesCommitBean;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.pkgs.tag_service_sched.TagServiceSchedService;

/**
 * Class OcmschrcServiceImpl 
 */
@Service
public class OcmschrcServiceImpl extends BaseBusiness implements OcmschrcService{

	@Autowired
	private OcmschrcRepository ocmschrcRepo;
	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;
	@Autowired
	private TagServiceSchedService tagServiceSchedService;
	@Autowired
	private TagServiceService tagServiceService;
	@Autowired
	private OcmsoschRepository ocmsoschRepo;
	

	/**Fetch the records from database table
	 * @param searchRecord
	 * @throws SQLException
	 */
	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules searchRecord)  {
			return ocmschrcRepo.crsScheduleRulExecuteQuery(searchRecord);
	
	}
	
	/**Insert the records from database table
	 * @param lstCRS_SCHEDULE_RUL
	 * @throws SQLException
	 */
	@Transactional
	public CourseScheduleRules crsScheduleRulCommit(final CourseScheduleRulesCommitBean commitBean)  {
			final CourseScheduleRules crsShed = new CourseScheduleRules();
			Integer liReturn = 2;
			Integer totalRecords = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CourseScheduleRules bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setCourseScheduleRuleId(tagServiceService.getNextCsRuleSeq());
				String result =  ocmsoschRepo.diffBetweenDates(bean.getStartTime(),bean.getEndTime());
				if(result != null && "N".equals(result)) {
					crsShed.setLireturn(3);
					return crsShed;
				}
			}
			liReturn = ocmschrcRepo.crsScheduleRulInsertCourseScheduleRules(commitBean.getInsertList());
			if(liReturn == 1) {
				totalRecords = totalRecords + commitBean.getInsertList().size();
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CourseScheduleRules bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				String result =  ocmsoschRepo.diffBetweenDates(bean.getStartTime(),bean.getEndTime());
				if(result != null && "N".equals(result)) {
					crsShed.setLireturn(3);
					return crsShed;
				}
			}
			liReturn = ocmschrcRepo.crsScheduleRulUpdateCourseScheduleRules(commitBean.getUpdateList());
			if(liReturn == 1) {
				totalRecords = totalRecords + commitBean.getUpdateList().size();
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocmschrcRepo.crsScheduleRulDeleteCourseScheduleRules(commitBean.getDeleteList());
			if(liReturn == 1) {
				totalRecords = totalRecords + commitBean.getDeleteList().size();
			}
		}
		if (commitBean.getActUpdate() != null && commitBean.getActUpdate().size() > 0) {
			commitBean.getActUpdate().forEach(action-> action.setModifyUserId(commitBean.getCreateUserId()));
			String flag = ocmschrcRepo.getFlag(commitBean.getActUpdate().get(0).getCrsActyId());
				if(!((commitBean.getActUpdate().get(0).getHolidayFlag()).equals(flag))){
					liReturn = ocmschrcRepo.crsActUpdateCourseActivities(commitBean.getActUpdate());
					if(liReturn == 1) {
						totalRecords = totalRecords + commitBean.getActUpdate().size();
						}
				}
		}
		crsShed.setTotalRecords(totalRecords);
		crsShed.setLireturn(liReturn);
		return crsShed;
	}

	@Override
	public CourseScheduleRules buildRecurringSchedule(final CourseScheduleRules CourSchRules) {
		return tagServiceSchedService.csBuildRecurringSchedule(CourSchRules.getCrsActyId(),CourSchRules.getNoOfDays(), CourSchRules.getCreateUserId());
	}

	@Override
	public Long getProfileValues() {
		try {
			final SystemProfiles sys = new SystemProfiles();
			sys.setProfileType("CLIENT");
			sys.setProfileCode("SCH_GEN");
			String profileValue = omsMiscellaneousService.getProfileValue(sys);
			return Long.parseLong(profileValue);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public String getPrgSrvDetails(final Long programId) {
		return ocmschrcRepo.getPrgSrvDetails(programId);
	}

	@Override
	public String getHolidayFlag(final CourseActivities crsactModel) {
		return ocmschrcRepo.getFlag(crsactModel.getCrsActyId());
	}

}