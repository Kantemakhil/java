package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CourseActivityAreas;
import net.syscon.s4.common.beans.CourseActivityAreasCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.iwp.OcmssvasRepository;
import net.syscon.s4.iwp.OcmssvasService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;

/**
 * Class OcmssvasServiceImpl
 */
@Service
public class OcmssvasServiceImpl extends BaseBusiness implements OcmssvasService {
	@Autowired
	private OcmssvasRepository ocmssvasRepository;
	@Autowired
	private TagServiceService tagServiceService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<CourseActivityAreas> cActAExecuteQuery(final CourseActivityAreas searchRecord) {
		List<CourseActivityAreas> courseActivityAreasList = ocmssvasRepository.cActAExecuteQuery(searchRecord);
		List<CourseActivityAreas> returnList = new ArrayList<CourseActivityAreas>();
		if (!courseActivityAreasList.isEmpty()) {
			for (CourseActivityAreas courseActivityAreas : courseActivityAreasList) {
//				String areasList = ocmssvasRepository.rgAreaClassRecordGroup(searchRecord.getCaseLoadType(),courseActivityAreas.getAreaCode());
				 Areas areaCoAndCls =  tagServiceService.cArea(courseActivityAreas.getAreaCode());
					if (areaCoAndCls != null && areaCoAndCls.getAreaClass()!= null) {
						courseActivityAreas.setAreaClass(areaCoAndCls.getAreaClass().toUpperCase());
					}
				if (courseActivityAreas.getAreaClass() != null && courseActivityAreas.getAreaCode() != null) {
					returnList.add(courseActivityAreas);
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstC_ACT_A
	 *
	 * @throws SQLException
	 */
	@Transactional
	public CourseActivityAreas cActACommit(final CourseActivityAreasCommitBean commitBean) {
		CourseActivityAreas returnData = new CourseActivityAreas();
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (CourseActivityAreas obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			returnData = ocmssvasRepository.cActAInsertCourseActivityAreas(commitBean.getInsertList());
			return returnData;
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			returnData = ocmssvasRepository.cActADeleteCourseActivityAreas(commitBean.getDeleteList());
			return returnData;
		}
		return returnData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgAreaClassRecordGroup() {
		return ocmssvasRepository.rgAreaClassRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<Areas> rgAreaRegionRecordGroup(final String caseloadType, final String nbtAreaType) {
		List<Areas> returnLlist = ocmssvasRepository.rgAreaRegionRecordGroup(caseloadType, nbtAreaType);
		returnLlist.forEach(element -> {
			if ("N".equals(element.getActiveFlag())) {
				element.setCanDisplay(false);
			}
		});
		return returnLlist;

	}

}