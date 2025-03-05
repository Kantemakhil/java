package net.syscon.s4.cm.programsservices.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcmphmodRepository;
import net.syscon.s4.cm.programsservices.OcmphmodService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.pkgs.tag_service_sched.TagServiceSchedService;

/**
 * Class OcmphmodServiceImpl
 */
@Service
public class OcmphmodServiceImpl extends BaseBusiness implements OcmphmodService {

	@Autowired
	private OcmphmodRepository ocmphmodRepository;

	@Autowired
	private TagServiceService tagServiceService;

	@Autowired
	private TagServiceSchedService tagServiceSchedService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<CourseActivities> courseActivitiesExecuteQuery(final CourseActivities searchRecord) {
		final List<CourseActivities> refList = ocmphmodRepository.courseActivitiesExecuteQuery(searchRecord);
		Long listSeq = 0L;
		for (CourseActivities courseActivities : refList) {
			listSeq = listSeq + 1L;
			if (courseActivities.getListSeq() == null) {
				courseActivities.setListSeq(listSeq);
			}
			List<CourseActivities> lvPrg = ocmphmodRepository.getSrgSrvDetails(courseActivities);
			if (lvPrg != null) {
				courseActivities.setNbtDescription(lvPrg.get(0).getDescription());
				courseActivities.setStartFlag(lvPrg.get(0).getStartFlag());
			}
		}

		return refList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCOURSE_ACTIVITIES
	 *
	 * 
	 */
	@Transactional
	public CourseActivities courseActivitiesCommit(CourseActivitiesCommitBean commitBean) {
		CourseActivities returnData = new CourseActivities();
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CourseActivities courseActivities : commitBean.getUpdateList()) {
				courseActivities.setModifyUserId(courseActivities.getCreateUserId());
				List<CourseActivities> list = new ArrayList<CourseActivities>();
				list.add(courseActivities);
				returnData = ocmphmodRepository.courseActivitiesUpdateCourseActivities(list);
				if (courseActivities.getNoOfSessions() != courseActivities.getNoOfSessionsTemp()) {
					Long count = tagServiceSchedService.countSessions(courseActivities.getCrsActyId());
					if (count != null && count > 0) {
						returnData.setSealFlag("2");
						return returnData;
					}
				}
				Long pTotal = ocmphmodRepository.getCrsSessionCount(courseActivities.getParentCrsActyId());
				tagServiceService.doUpdateOnCrsPhase(courseActivities.getParentCrsActyId(), pTotal,
						courseActivities.getCreateUserId());
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (CourseActivities courseActivities : commitBean.getDeleteList()) {
				courseActivities.setModifyUserId(commitBean.getCreateUserId());
			}
			returnData = ocmphmodRepository.courseActDeleteCourseActivities(commitBean.getDeleteList());
		}
		return returnData;
	}

}