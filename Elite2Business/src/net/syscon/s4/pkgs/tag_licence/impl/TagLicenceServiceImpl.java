package net.syscon.s4.pkgs.tag_licence.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_licence.TagLicenceRepository;
import net.syscon.s4.pkgs.tag_licence.TagLicenceService;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesService;

@Service
public class TagLicenceServiceImpl implements TagLicenceService {

	@Autowired
	private TagLicenceRepository tagLicenceRepository;
	@Autowired
	private TagPrisonActivitiesService tagPrisonActivitiesService;
	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;

	private static Logger logger = LogManager.getLogger(TagLicenceServiceImpl.class.getName());

	@Override
	public OffenderSentConditions getPostQueryRec(final OffenderSentConditions offSentCond) {
		try {
			offSentCond.setRequirement(getRequirement(offSentCond.getCommConditionCode(),
					offSentCond.getCommConditionType(), offSentCond.getCategoryType()));
			offSentCond.setProgram(tagPrisonActivitiesService.getServices(offSentCond.getProgramId().longValue()));
			offSentCond.setActivityCode(
					omsMiscellaneousService.getDescCode("COND_PRO_ACT", offSentCond.getActivityCode()));
			offSentCond.setCurfewProvider(
					omsMiscellaneousService.getDescCode("COND_CURFEW", offSentCond.getCurfewProvider()));
			offSentCond.setExclusionCode(
					omsMiscellaneousService.getDescCode("COND_EXCLUDE", offSentCond.getExclusionCode()));
			offSentCond.setMentalHealthProvider(
					omsMiscellaneousService.getDescCode("COND_MH_PROV", offSentCond.getMentalHealthProvider()));
			offSentCond.setAlcoholTreatmentProvider(
					omsMiscellaneousService.getDescCode("COND_AT_PROV", offSentCond.getAlcoholTreatmentProvider()));
			offSentCond.setAttendanceCentre(
					omsMiscellaneousService.getDescCode("COND_AC_PROV", offSentCond.getAttendanceCentre()));
			offSentCond.setConditionStatus(
					omsMiscellaneousService.getDescCode("ACTIVE_TYPE", offSentCond.getConditionStatus()));
			offSentCond.setLengthUnit(omsMiscellaneousService.getDescCode("COND_UNIT", offSentCond.getLengthUnit()));
			offSentCond.setReviewCode(omsMiscellaneousService.getDescCode("COND_REVIEW", offSentCond.getReviewCode()));
		} catch (Exception e) {
			logger.error("getPostQueryRec", e);
		}
		return offSentCond;
	}

	@Override
	public String getRequirement(final String pCommConditionCode, final String pCommConditionType,
			final String pCategoryType) {
		return tagLicenceRepository.getRequirement(pCommConditionCode, pCommConditionType, pCategoryType);
	}

}