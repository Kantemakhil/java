package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderAssessment;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.OffenderAssessmentsTwf1Repository;
import net.syscon.s4.triggers.OffenderAssessmentsTwf1Service;

@Service
public class OffenderAssessmentsTwf1ServiceImpl implements OffenderAssessmentsTwf1Service {
	private final Logger logger = LogManager.getLogger(OffenderAssessmentsTwf1ServiceImpl.class);
	@Autowired
	OffenderAssessmentsTwf1Repository offenderAssessmentsTwf1Repository;
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer OffenderAssessmentsTwf1(final OffenderAssessment offenderAssessment) {
		final Long curAssessmentType = offenderAssessmentsTwf1Repository.curAssessmentType();
		try {
			if (offenderAssessment.getAssessmentTypeId().compareTo(new BigDecimal(curAssessmentType)) == 0
					&& Optional.ofNullable(offenderAssessment.getReviewSupLevelType()).isPresent()
					&& "A".equals(offenderAssessment.getAssessStatus())
					&& "APP".equals(offenderAssessment.getEvaluationResultCode())) {

				final String lvInmateIsAtRisk = offenderAssessmentsTwf1Repository.curInmateIsAtRisk(
						offenderAssessment.getOffenderBookId(), offenderAssessment.getAssessmentSeq(),
						offenderAssessment.getAssessmentTypeId());
				if ("Y".equals(lvInmateIsAtRisk)) {
					final Object lvXml = null;
					tagWfmsgService.append("offender_book_id", String.valueOf(offenderAssessment.getOffenderBookId()),
							null);
					tagWfmsgService.append("assessment_seq", String.valueOf(offenderAssessment.getAssessmentSeq()),
							null);
					tagWorkflowService.createEmailWorkflow("MED_T1_ASSES", lvXml,
							offenderAssessment.getOffenderBookId(), offenderAssessment.getCreateDatetime());
					tagWorkflowService.createWorkflow(new BigDecimal(offenderAssessment.getAssessmentSeq()), null,
							lvXml, new BigDecimal(offenderAssessment.getOffenderBookId()), null, null, null,
							offenderAssessment.getCreateUserId(), "MED_T1_ASSES");
				}
			}
		} catch (final Exception e) {
			logger.error("OffenderAssessmentsTwf1", e);
		}
		return null;
	}

}
