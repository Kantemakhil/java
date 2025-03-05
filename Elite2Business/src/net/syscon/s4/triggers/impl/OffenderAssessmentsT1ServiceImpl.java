package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.pkgs.tag_assessment.TagAssessmentService;
import net.syscon.s4.triggers.OffenderAssessmentsT1Repository;
import net.syscon.s4.triggers.OffenderAssessmentsT1Service;

@Service
public class OffenderAssessmentsT1ServiceImpl implements OffenderAssessmentsT1Service {
	private final Logger logger = LogManager.getLogger(OffenderAssessmentsT1ServiceImpl.class);
	@Autowired
	OffenderAssessmentsT1Repository offenderAssessmentsT1Repository;
	@Autowired
	TagAssessmentService tagAssessmentService;

	@Override
	public Integer offenderAssessmentsT1Trigger(final OffenderAssessments offenderAssessments) {
		Integer result = 0;
		String pAssessmentCode = null;
		try {
			if (offenderAssessments != null && offenderAssessments.getApprovedSupLevelType() == null) {
				pAssessmentCode = offenderAssessments.getApprovedSupLevelType();
			}
			if (pAssessmentCode == null) {
				pAssessmentCode = offenderAssessments.getOverridedSupLevelType();
			}
			if (pAssessmentCode == null) {
				pAssessmentCode = offenderAssessments.getCalcSupLevelType();
			}
			final String lvCsa = tagAssessmentService.chkAssessmentCsa(offenderAssessments.getAssessmentTypeId(),
					pAssessmentCode);
			if (Optional.ofNullable(lvCsa).isPresent()) {
				result = tagAssessmentService
						.updateBkgCsa(offenderAssessments.getOffenderBookId(), lvCsa,
						offenderAssessments.getModifyUserId());
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("offenderAssessmentsT1Trigger", e);
		}
		return result;
	}

}
