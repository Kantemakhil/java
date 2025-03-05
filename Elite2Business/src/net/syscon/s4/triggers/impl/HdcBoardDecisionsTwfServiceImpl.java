package net.syscon.s4.triggers.impl;

import java.sql.Clob;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.HdcBoardDecisions;
import net.syscon.s4.triggers.HdcBoardDecisionsT1Repository;
import net.syscon.s4.triggers.HdcBoardDecisionsTwfService;

@Service
public class HdcBoardDecisionsTwfServiceImpl implements HdcBoardDecisionsTwfService {
	private final Logger logger = LogManager.getLogger(HdcBoardDecisionsTwfServiceImpl.class);
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;
	@Autowired
	HdcBoardDecisionsT1Repository hdcBoardDecisionsT1Repository;

	@Override
	public Integer hdcBoardDecisionsTwfTrigger(final HdcBoardDecisions hdcBoardDecisions) {
//		Object lvXml = tagWfmsgService.createXml();
		final Clob lvXml = null;
		final HdcBoardDecisions hdcBoardDecisionsOld = hdcBoardDecisionsT1Repository.getHdcBoardDecisions(hdcBoardDecisions);
		try {
			/*
			 * TODO IF ( INSERTING AND :NEW.board_recommendation IS NOT NULL ) OR ( UPDATING
			 * AND :NEW.board_recommendation IS NOT NULL AND :OLD.board_recommendation IS
			 * NULL )
			 */
			if (Optional.ofNullable(hdcBoardDecisions.getHdcRequestReferralId()).isPresent()
					&& (hdcBoardDecisionsOld == null || hdcBoardDecisionsOld.getBoardRecommendation() == null)) {

				tagWfmsgService.append("hdc_request_referral_id",
						String.valueOf(hdcBoardDecisions.getBoardRecommendation()), lvXml);
				tagWorkflowService.createWorkflow(null, null, lvXml, null, null, null, null, null, "HDC_BRD_DONE");
				tagWorkflowService.createWorkflow(null, "HDC_BRD_DONE", lvXml, null, null, null, -1, null,
						"HDC_GOV_DUE");
			}
		} catch (final Exception e) {
//			tag_error.handle ( );
			logger.error("hdcBoardDecisionsTwfTrigger", e);
		}
		return null;
	}

}
