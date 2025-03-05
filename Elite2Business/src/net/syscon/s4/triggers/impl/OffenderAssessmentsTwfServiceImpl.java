package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.OffenderAssessmentsTwfService;

@Service
public class OffenderAssessmentsTwfServiceImpl implements OffenderAssessmentsTwfService {
	private final Logger logger = LogManager.getLogger(OffenderAssessmentsTwfServiceImpl.class);
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer offenderAssessmentsTwf(final OffenderAssessments offenderAssessments) {
//		Clob lvXml = tagWfmsgService.createXml();
		final Object lvXml = null;
		try {
			final Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 5);
			tagWfmsgService.append("ASSESSMENT_SEQ", String.valueOf(offenderAssessments.getAssessmentSeq()), null);
			tagWfmsgService.append("event_type", "OCDNOQUE", null);
			tagWorkflowService.createWorkflow(new BigDecimal(offenderAssessments.getAssessmentSeq()), null, lvXml,
					new BigDecimal(offenderAssessments.getOffenderBookId()), offenderAssessments.getAssessmentDate(),
					c.getTime(), -1, offenderAssessments.getCreateUserId(), "CLASS_APP");
		} catch (final Exception e) {
//			 tag_error.handle();
			logger.error("offenderAssessmentsTwf", e);
		}
		return null;
	}

}
