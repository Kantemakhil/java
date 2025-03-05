package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.HdcGovernorDecisions;
import net.syscon.s4.triggers.HdcGovernorDecisionsTwfRepository;
import net.syscon.s4.triggers.HdcGovernorDecisionsTwfService;

@Service
public class HdcGovernorDecisionsTwfServiceImpl implements HdcGovernorDecisionsTwfService {
	private final Logger logger = LogManager.getLogger(HdcGovernorDecisionsTwfServiceImpl.class);
	@Autowired
	HdcGovernorDecisionsTwfRepository hdcGovernorDecisionsTwfRepository;
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer hdcRequestReferralsTwfTrigger(final HdcGovernorDecisions hdcGovernorDecis) {
		final HdcGovernorDecisions hdcGoveDecisOld = hdcGovernorDecisionsTwfRepository
				.getHdcGovernorDecisions(hdcGovernorDecis);
		if ((Optional.ofNullable(hdcGovernorDecis).isPresent() && hdcGovernorDecis.getSealFlag() == null)
				|| (Optional.ofNullable(hdcGoveDecisOld).isPresent()
						&& hdcGovernorDecis.getSealFlag().equals(hdcGoveDecisOld.getSealFlag()))) {
			try {

				if (Optional.ofNullable(hdcGovernorDecis).isPresent()
						&& (Optional.ofNullable(hdcGovernorDecis.getGovernorsDecision()).isPresent())
						|| (Optional.ofNullable(hdcGovernorDecis.getGovernorsDecision()).isPresent()
								&& !Optional.ofNullable(hdcGoveDecisOld.getGovernorsDecision()).isPresent())) {
//				Clob lvXml = tagWfmsgService.createXml();
					final Clob lvXml = null;
					tagWfmsgService.append("governors_decision", hdcGovernorDecis.getGovernorsDecision(), lvXml);
					tagWfmsgService.append("hdc_request_referral_id",
							String.valueOf(hdcGovernorDecis.getHdcRequestReferralId()), lvXml);
					tagWorkflowService.createWorkflow(null, null, lvXml, null, null, null, null, null, "HDC_DECISION");
				}
				if (("OM".equals(hdcGovernorDecis.getReferralTo()) && !"OM".equals(hdcGoveDecisOld.getReferralTo()))
						|| "OM".equals(hdcGovernorDecis.getReferralTo())) {
//				Clob lvXml = tagWfmsgService.createXml();
					final Clob lvXml = null;
					tagWfmsgService.append("comments", hdcGovernorDecis.getCommentText(), lvXml);
					tagWfmsgService.append("hdc_request_referral_id",
							String.valueOf(hdcGovernorDecis.getHdcRequestReferralId()), lvXml);
					tagWorkflowService.createWorkflow(new BigDecimal(hdcGovernorDecis.getHdcRequestReferralId()), null,
							lvXml, null, null, null, null, null, "HDC_OM_DUE");

				}
			} catch (final Exception e) {
//			Tag_Error.handle ( );
				logger.error("hdcRequestReferralsTwfTrigger", e);
			}
		}
		return null;
	}

}
