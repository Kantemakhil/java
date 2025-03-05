package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRulesCommitBean;
import net.syscon.s4.iwp.OcumpvavRepository;
import net.syscon.s4.iwp.OcumpvavService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;

/**
 * Class OcumpvavServiceImpl
 */
@Service
public class OcumpvavServiceImpl extends BaseBusiness implements OcumpvavService {

	@Autowired
	private OcumpvavRepository ocumpvavRepository;
	
	@Autowired
	private TagServiceService tagServiceService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CourseActivities> crsActExecuteQuery(final CourseActivities searchRecord) {
		List<CourseActivities> courseActivitiesList = ocumpvavRepository.crsActExecuteQuery(searchRecord);
		for (CourseActivities courseActivities : courseActivitiesList) {
			String progIdVal = ocumpvavRepository.getPrgSrvDetails(courseActivities.getProgramId());
			courseActivities.setProgramIdVal(progIdVal);
		}
		return courseActivitiesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules searchRecord) {
		return ocumpvavRepository.crsScheduleRulExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCRS_SCHEDULE_RUL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer crsScheduleRulCommit(final CourseScheduleRulesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final CourseScheduleRules courSchRules : commitBean.getInsertList()) {
				//calling tagService pkg based  function funname: get_next_cs_rule_seq
				final Long courSchRuleId = tagServiceService.getNextCsRuleSeq();
				courSchRules.setCourseScheduleRuleId(courSchRuleId);
				courSchRules.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocumpvavRepository.crsScheduleRulInsertCourseScheduleRules(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocumpvavRepository.crsScheduleRulUpdateCourseScheduleRules(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocumpvavRepository.crsScheduleRulDeleteCourseScheduleRules(commitBean.getDeleteList());
		}
		//Holiday Flag Update Query
		if (commitBean.getActUpdate() != null && commitBean.getActUpdate().size() > 0) {
			commitBean.getActUpdate().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocumpvavRepository.holidayFlagUpdateCourseActivities(commitBean.getActUpdate());
		}
		return liReturn;
	}

}