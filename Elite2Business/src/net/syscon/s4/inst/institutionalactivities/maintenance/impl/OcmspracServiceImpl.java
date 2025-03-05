package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmspracRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmspracService;
import net.syscon.s4.inst.legals.OiddecasRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.triggers.CourseActivitiesT1Service;
import net.syscon.s4.triggers.CourseActivitiesT2Service;
import net.syscon.s4.triggers.CourseActivitiesT3Service;
/**
 * Class OcmspracServiceImpl
 */
@Service
public class OcmspracServiceImpl extends BaseBusiness implements OcmspracService {

	@Autowired
	private OcmspracRepository ocmspracRepository;
	@Autowired
	OiddecasRepository oiddecasRepository;
	@Autowired
	private TagPrisonActivitiesService tagPrisonActivitiesService;
	@Autowired
	private TagServiceService tagServiceService;
	@Autowired
	private CourseActivitiesT2Service courseActivitiesT2Service;
	@Autowired
	private CourseActivitiesT1Service courseActivitiesT1Service;
	@Autowired
	private CourseActivitiesT3Service courseActivitiesT3Service;
	
	
	public static final String successMessage = "success";

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CourseActivities> courseActivitiesExecuteQuery(CourseActivities searchRecord) {
		return ocmspracRepository.courseActivitiesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCOURSE_ACTIVITIES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public CourseActivities courseActivitiesCommit(CourseActivitiesCommitBean commitBean) {
		CourseActivities retBean = new CourseActivities();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (CourseActivities bean : commitBean.getInsertList()) {
				bean.setCrsActyId(tagPrisonActivitiesService.getCrsActyId());
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			Integer codeCount = checkCodeExists(commitBean.getInsertList());
			if (codeCount > 0) {
				retBean.setSealFlag("2");
				return retBean;
			}
			for (CourseActivities bean : commitBean.getInsertList()) {
			courseActivitiesT1Service.courseActivitiesT1Trigger(bean.getCaseloadType(),bean.getHolidayFlag(), bean.getCreateUserId());
			}
			liReturn = ocmspracRepository.courseActivitiesInsertCourseActivities(commitBean.getInsertList());
			for (CourseActivities bean : commitBean.getInsertList()) {
				courseActivitiesT2Service.CourseActivitiesT2Trigger(bean.getProviderPartyCode(), bean.getProviderPartyId(), 
						bean.getProviderPartyClass(), bean.getCrsActyId(),bean.getCreateUserId());
				courseActivitiesT3Service.courseActivitiesT3Trigger(bean.getCourseClass(), bean.getProgramId(), bean.getCrsActyId(),bean.getCreateUserId());

				}
			if (liReturn == 1) {
				retBean.setSealFlag(successMessage);
			}

		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (CourseActivities updBean : commitBean.getUpdateList()) {
				updBean.setModifyUserId(commitBean.getCreateUserId());
			}
			Integer codeCount = checkCodeExists(commitBean.getUpdateList());
			if (codeCount > 0) {
				retBean.setSealFlag("2");
				return retBean;
			}
			liReturn = ocmspracRepository.courseActivitiesUpdateCourseActivities(commitBean.getUpdateList());
			if (liReturn == 1) {
				for (CourseActivities updBean : commitBean.getUpdateList()) {
					if ("Y".equals(updBean.getPostUpdate())) {
						 tagPrisonActivitiesService.UpdateCourseSchedules(updBean.getCrsActyId(), updBean.getScheduleEndDate(),updBean.getCreateUserId());
					}
					tagPrisonActivitiesService.removeCourseSchedules(updBean.getCrsActyId(), updBean.getScheduleEndDate(),commitBean.getCreateUserId());
				}
				retBean.setSealFlag(successMessage);
			}
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (CourseActivities bean : commitBean.getDeleteList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				tagServiceService.deleteCourseActivityAreas(bean.getCrsActyId(),commitBean.getCreateUserId());
				if (delValidation(commitBean.getDeleteList()) != 0) {
					retBean.setSealFlag("childFound");
					return retBean;
				}
			}
			liReturn = ocmspracRepository.courseActivitiesDeleteCourseActivities(commitBean.getDeleteList());
			if (liReturn == 1) {
				retBean.setSealFlag(successMessage);
			}
		}
		return retBean;
	}

	public String chkActyEndDate(final CourseActivities bean) {
		return tagPrisonActivitiesService.chkActyEndDate(bean.getCrsActyId(), bean.getScheduleEndDate());
	}

	public Integer checkCodeExists(final List<CourseActivities> listObj) {
		final List<String> agyLocId = listObj.stream().map(data -> data.getAgyLocId()).collect(Collectors.toList());
		final List<String> code = listObj.stream().map(data -> data.getCode()).collect(Collectors.toList());
		final List<String> caseloadId = listObj.stream().map(data -> data.getCaseloadId()).collect(Collectors.toList());
		final List<String> caseloadType = listObj.stream().map(data -> data.getCaseloadType())
				.collect(Collectors.toList());
		final List<String> rowId = listObj.stream().map(data -> data.getRowId()).collect(Collectors.toList());
		if (listObj.get(0).getCrsActyId() != null) {
			return ocmspracRepository.checkCodeExists(agyLocId, code, caseloadId, caseloadType);
		} else {
			return ocmspracRepository.checkCodeExistsonUpdate(agyLocId, code, caseloadId, caseloadType, rowId);
		}
	}

	public Integer delValidation(final List<CourseActivities> listObj) {
		long crsactyId = listObj.get(0).getCrsActyId();
		final List<TableColumnNameBean> tabColList = ocmspracRepository.okToDelRecord();
		for (TableColumnNameBean bean : tabColList)
			if (bean.getTableName() != null && bean.getColumnName() != null && crsactyId != 0) {
				final Integer count = ocmspracRepository.getChildCount(bean.getTableName(), bean.getColumnName(),
						crsactyId);
				if (count > 0) {
					return count;
				}
			}
		return 0;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseloadId) {
		return ocmspracRepository.rgAgyLocRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ProgramServices> rgPrisonActivityRecordGroup() {
		final List<ProgramServices> returnList = ocmspracRepository.rgPrisonActivityRecordGroup();
		for (ProgramServices bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<IntLocUsageLocations> rgInternalLocationRecordGroup(final String agyLocId) {
		final List<IntLocUsageLocations> returnList = ocmspracRepository.rgInternalLocationRecordGroup(agyLocId);
		for (IntLocUsageLocations bean : returnList) {
			if ("Y".equals(bean.getActiveFlag())) {
				bean.setCanDisplay(true);
			} else {
				bean.setCanDisplay(false);
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgIepLevelRecordGroup() {
		return ocmspracRepository.rgIepLevelRecordGroup();

	}

}