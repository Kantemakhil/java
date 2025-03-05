package net.syscon.s4.triggers.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.CourseActivityAreas;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.accreditedprograms.beans.VCoursePhaseOfferings;
import net.syscon.s4.pkgs.ocmsvacp.OcmsvacpPkgService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.triggers.CourseActivitiesT1Service;
import net.syscon.s4.triggers.CourseActivitiesT2Service;
import net.syscon.s4.triggers.CourseActivitiesT3Service;
import net.syscon.s4.triggers.VCourseModules;
import net.syscon.s4.triggers.VCoursePhaseOfferingsTuRepository;
import net.syscon.s4.triggers.VCoursePhaseOfferingsTuService;
/* =========================================================
Below comments are copied from V_COURSE_PHASE_OFFERINGS_TU Trigger
========================================================= */
/* MODIFICATION HISTORY
Person       Date      Version       Comments
---------    ------     ---------    ------------------------------
Neil         09/08/2006 2.3          Added insert/update of services_address_id
                                     and internal_location_id on modules.
Neil         11/07/2006 2.2          Added insert and update of capacity on course_activities.
David Ng     03/06/2006 2.1          NOMIS project(10.2.0)
*/
@Service
public class VCoursePhaseOfferingsTuServiceImpl implements VCoursePhaseOfferingsTuService {
	@Autowired
	VCoursePhaseOfferingsTuRepository vCoursePhaseOfferingsTuRepository;
	@Autowired
	private OcmsvacpPkgService ocmsvacpService;

	@Autowired
	TagServiceService tagServiceService;

	@Autowired
	private CourseActivitiesT1Service courseActivitiesT1Service;

	@Autowired
	private CourseActivitiesT2Service courseActivitiesT2Service;

	@Autowired
	private CourseActivitiesT3Service courseActivitiesT3Service;

	@Override
	public Integer vCoursePhaseOfferingsTuTgr(final VCoursePhaseOfferings newObj, final VCoursePhaseOfferings oldObj) {
		CourseActivities courseActivities;
		Integer result = 0;
		if ("N".equals(oldObj.getOfferingFlag()) && "Y".equals(newObj.getOfferingFlag())) {
			final Long lCrsActyId = vCoursePhaseOfferingsTuRepository.lCrsActyId();
			courseActivities = new CourseActivities();
			String holidayFlag = null;
			dataMapper(newObj, oldObj, courseActivities, lCrsActyId);
			courseActivitiesT1Service.courseActivitiesT1Trigger(oldObj.getCaseloadType(), holidayFlag,
					courseActivities.getCreateUserId());
			vCoursePhaseOfferingsTuRepository.courseActivitiesInsert(courseActivities);
			courseActivitiesT2Service.CourseActivitiesT2Trigger(courseActivities.getProviderPartyCode(),
					courseActivities.getProviderPartyId(), courseActivities.getProviderPartyClass(),
					courseActivities.getCrsActyId(), courseActivities.getCreateUserId());
			courseActivitiesT3Service.courseActivitiesT3Trigger(courseActivities.getCourseClass(),
					courseActivities.getProgramId(), courseActivities.getCrsActyId(),courseActivities.getCreateUserId());
			final VCourseModules vCourseModules = new VCourseModules();
			vCourseModules.setProgramPhaseId(
					oldObj.getProgramPhaseId() == null ? null : oldObj.getProgramPhaseId().longValue());
			vCourseModules.setCoursePhaseId(lCrsActyId);
			vCourseModules.setCaseloadType(oldObj.getCourseCaseLoadType());
			vCourseModules.setServicesAddressId(
					newObj.getCpServicesAddressId() == null ? null : newObj.getCpServicesAddressId().longValue());
			vCourseModules.setInternalLocationId(newObj.getInternalLocationId());
			vCourseModules.setScheduleStartDate(newObj.getCpStartDate());
			vCourseModules.setCreateUserId(newObj.getCreateUserId());
			ocmsvacpService.createCourseModules(vCourseModules);
			courseActivities = new CourseActivities();
			dataMappingForCourseActivit(newObj, courseActivities);
			courseActivities.setCrsActyId(lCrsActyId);
			result = vCoursePhaseOfferingsTuRepository.courseActivitiesUpdate(courseActivities);
		}
		try {
			if ("Y".equals(oldObj.getOfferingFlag())) {
				if ("N".equals(newObj.getOfferingFlag())) {
					courseActivities = new CourseActivities();
					final CourseActivityAreas courseActivityAreas = new CourseActivityAreas();
					courseActivities.setCrsActyId(newObj.getCoursePhaseId().longValue());
					courseActivities.setModifyUserId(newObj.getModifyUserId());
					courseActivityAreas.setCrsActyId(newObj.getCoursePhaseId());
					courseActivityAreas.setModifyUserId(newObj.getModifyUserId());
					vCoursePhaseOfferingsTuRepository.courseActivityAreasDelete(courseActivityAreas);
					vCoursePhaseOfferingsTuRepository.deleteCoursePhaseModules(newObj);
					vCoursePhaseOfferingsTuRepository.courseActivitiesDelete(courseActivities);
				} else {
					courseActivities = new CourseActivities();
					dataMappingForCourseActivit(newObj, courseActivities);
					courseActivities.setCrsActyId(
							oldObj.getCoursePhaseId() == null ? null : oldObj.getCoursePhaseId().longValue());
					result = vCoursePhaseOfferingsTuRepository.courseActivitiesUpdate(courseActivities);
					courseActivities.setParentCrsActyId(
							newObj.getCoursePhaseId() == null ? null : newObj.getCoursePhaseId().longValue());
					result = vCoursePhaseOfferingsTuRepository.courseActivitiesUpdate2(courseActivities);
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return result;
	}

	private void dataMapper(final VCoursePhaseOfferings newObj, final VCoursePhaseOfferings oldObj,
			final CourseActivities courseActivities, final Long lCrsActyId) {
		courseActivities.setCrsActyId(lCrsActyId);
		courseActivities.setCourseClass("CRS_PH");
		courseActivities.setParentCrsActyId(oldObj.getCourseId() == null ? null : oldObj.getCourseId().longValue());
		courseActivities
				.setProgramId(oldObj.getProgramPhaseId() == null ? null : oldObj.getProgramPhaseId().longValue());
		courseActivities.setListSeq(oldObj.getPhListSeq() == null ? null : oldObj.getPhListSeq().longValue());
		courseActivities.setDescription(oldObj.getPhDescription());
		courseActivities.setProviderPartyClass(oldObj.getProviderPartyClass());
		courseActivities.setProviderPartyId(
				oldObj.getProviderPartyId() == null ? null : oldObj.getProviderPartyId().longValue());
		courseActivities.setProviderPartyCode(oldObj.getProviderPartyCode());

		courseActivities.setCapacity(oldObj.getPhCapacity() == null ? null : oldObj.getPhCapacity().longValue());
		courseActivities
				.setNoOfSessions(oldObj.getPhNoOfSessions() == null ? null : oldObj.getPhNoOfSessions().longValue());
		courseActivities
				.setSessionLength(oldObj.getPhSessionLength() == null ? null : oldObj.getPhSessionLength().longValue());
		courseActivities.setServicesAddressId(
				newObj.getCpServicesAddressId() == null ? null : newObj.getCpServicesAddressId().longValue());
		courseActivities.setInternalLocationId(
				newObj.getCrsInternalLocationId() == null ? null : newObj.getCrsInternalLocationId().longValue());
		courseActivities.setCourseActivityType(newObj.getCpCourseActivityType());
		courseActivities.setCaseloadType(oldObj.getCourseCaseLoadType());
		courseActivities.setCreateUserId(newObj.getCreateUserId());
		courseActivities.setCreateDatetime(new Date());
		courseActivities.setModifyDatetime(new Date());
	}

	private void dataMappingForCourseActivit(final VCoursePhaseOfferings newObj,
			final CourseActivities courseActivities) {
		courseActivities.setScheduleStartDate(newObj.getCpStartDate());
		courseActivities.setScheduleEndDate(newObj.getCpEndDate());
		courseActivities.setCommentText(newObj.getCpCommentText());
		courseActivities.setCapacity(newObj.getCpCapacity() == null ? null : newObj.getCpCapacity().longValue());
		courseActivities
				.setNoOfSessions(newObj.getCpNoOfSessions() == null ? null : newObj.getCpNoOfSessions().longValue());
		courseActivities
				.setSessionLength(newObj.getCpSessionLength() == null ? null : newObj.getCpSessionLength().longValue());
		courseActivities.setServicesAddressId(
				newObj.getCpServicesAddressId() == null ? null : newObj.getCpServicesAddressId().longValue());
		courseActivities.setCaseloadType(newObj.getCaseloadType());
		courseActivities.setPlacementCorporateId(
				newObj.getCpPlacementCorporateId() == null ? null : newObj.getCpPlacementCorporateId().longValue());
		courseActivities.setInternalLocationId(
				newObj.getCrsInternalLocationId() == null ? null : newObj.getCrsInternalLocationId().longValue());
		courseActivities.setModifyDatetime(new Date());
		courseActivities.setModifyUserId(newObj.getCreateUserId());
	}
}
