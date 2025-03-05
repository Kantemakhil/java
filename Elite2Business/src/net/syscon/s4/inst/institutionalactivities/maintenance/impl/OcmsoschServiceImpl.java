package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmsoschRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmsoschService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedulesCommitBean;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.pkgs.tag_service_sched.TagServiceSchedService;

/**
 * Class OcmsoschServiceImpl
 */
@Service
public class OcmsoschServiceImpl extends BaseBusiness implements OcmsoschService {

	@Autowired
	private OcmsoschRepository ocmsoschRepo;
	@Autowired
    private TagServiceSchedService tagServiceSchedService;
	@Autowired
	private TagServiceService tagServiceService;


	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CourseSchedules> courseSchedExecuteQuery(final CourseSchedules searchRecord) {
		return ocmsoschRepo.courseSchedExecuteQuery(searchRecord);

	}

	public String clearSchedules(final CourseSchedules courseSchedules) {
	return tagServiceSchedService.csClearSchedules(courseSchedules.getCrsSchId(), courseSchedules.getCrsActyId(),courseSchedules.getCreateUserId());
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCOURSE_SCHED
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer courseSchedCommit(final CourseSchedulesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (CourseSchedules bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				bean.setCrsSchId(tagServiceService.getNextCsSeq());
				String result =  ocmsoschRepo.diffBetweenDates(bean.getStartTime(),bean.getEndTime());
				if(result != null && "N".equals(result)) {
					return 2;
				}
			}
			liReturn = ocmsoschRepo.courseSchedInsertCourseSchedules(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CourseSchedules bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				String result =  ocmsoschRepo.diffBetweenDates(bean.getStartTime(),bean.getEndTime());
				if(result != null && "N".equals(result)) {
					return 2;
				}
			}
			liReturn = ocmsoschRepo.courseSchedUpdateCourseSchedules(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = ocmsoschRepo.courseSchedDeleteCourseSchedules(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public String getDate(final CourseSchedules courseSchedules) {
		return ocmsoschRepo.getDate(courseSchedules);
	}

}