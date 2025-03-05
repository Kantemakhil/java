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
import net.syscon.s4.triggers.HdcRequestReferrals;
import net.syscon.s4.triggers.HdcRequestReferralsTwfRepository;
import net.syscon.s4.triggers.HdcRequestReferralsTwfService;

@Service
public class HdcRequestReferralsTwfServiceImpl implements HdcRequestReferralsTwfService {
	private final Logger logger = LogManager.getLogger(HdcRequestReferralsTwfServiceImpl.class);
	@Autowired
	HdcRequestReferralsTwfRepository hdcRequestReferralsTwfRepository;
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer hdcRequestReferralsTwfTrigger(final HdcRequestReferrals hdcRequestReferrals,
			final String sqlOperation) {
		String lvReferral;
		String lvWorkTrigger = null;
		final HdcRequestReferrals hdcRequestReferralsOld = hdcRequestReferralsTwfRepository
				.getHdcRequestReferrals(hdcRequestReferrals);
//		lvXml=tagWfmsgService.createXml();
		final Clob lvXml = null;
		if ((Optional.ofNullable(hdcRequestReferrals).isPresent() && hdcRequestReferrals.getSealFlag() == null)
				|| (Optional.ofNullable(hdcRequestReferralsOld).isPresent()
						&& hdcRequestReferrals.getSealFlag().equals(hdcRequestReferralsOld.getSealFlag()))) {
			try {
				if ("INSERTING".equals(sqlOperation) || "UPDATING".equals(sqlOperation)) {
					lvReferral = Optional.ofNullable(hdcRequestReferrals).isPresent()
							? hdcRequestReferrals.getReferralTo()
							: null;
				} else {
					lvReferral = Optional.ofNullable(hdcRequestReferralsOld).isPresent()
							? hdcRequestReferralsOld.getReferralTo()
							: null;
				}
				if ("INTPROB".equals(lvReferral)) {
					lvWorkTrigger = "HDC_INP_DUE";
				} else if ("GOVERNOR".equals(lvReferral)) {
					lvWorkTrigger = "HDC_GOV_DUE";
				} else if ("PERSOFF".equals(lvReferral)) {
					lvWorkTrigger = "HDC_PO_DUE";
				} else if ("BOARD".equals(lvReferral)) {
					lvWorkTrigger = "HDC_BRD_DUE";
				}

				if ("INSERTING".equals(sqlOperation)) {
					if ("INTPROB".equals(lvReferral) || "PERSOFF".equals(lvReferral) || "BOARD".equals(lvReferral)
							|| "GOVERNOR".equals(lvReferral) && "PRISON".equals(hdcRequestReferrals.getReferredBy())) {
						tagWfmsgService.append("referral_info", hdcRequestReferrals.getReferralInformation(), lvXml);
						tagWorkflowService.createWorkflow(new BigDecimal(hdcRequestReferrals.getOffenderCurfewId()),
								sqlOperation, lvXml, new BigDecimal(hdcRequestReferrals.getOffenderBookId()), null,
								hdcRequestReferrals.getResponseDueDate(), -1, hdcRequestReferrals.getCreateUserId(),
								lvWorkTrigger);
					}
				}
				if ("UPDATING".equals(sqlOperation)) {
					if (Optional.ofNullable(hdcRequestReferrals.getReceivedDate()).isPresent()
							&& (hdcRequestReferralsOld == null
									|| (Optional.ofNullable(hdcRequestReferralsOld).isPresent()
											&& hdcRequestReferralsOld == null))) {
						if ("PERSOFF".equals(hdcRequestReferrals.getReferralTo())) {
							tagWorkflowService.createWorkflow(new BigDecimal(hdcRequestReferrals.getOffenderCurfewId()),
									null, null, new BigDecimal(hdcRequestReferrals.getOffenderBookId()), null, null,
									null, hdcRequestReferrals.getCreateUserId(), "HDC_PO_DONE");
						} else if ("INTPROB".equals(hdcRequestReferrals.getReferralTo())) {
							tagWorkflowService.createWorkflow(new BigDecimal(hdcRequestReferrals.getOffenderCurfewId()),
									null, null, new BigDecimal(hdcRequestReferrals.getOffenderBookId()), null, null,
									null, hdcRequestReferrals.getCreateUserId(), "HDC_INP_DONE");
						}
						if (Optional.ofNullable(lvWorkTrigger).isPresent()) {
							tagWorkflowService.completeWorkflow(lvWorkTrigger,
									new BigDecimal(hdcRequestReferrals.getOffenderCurfewId()), null, null);
						}

					}

				}
				if ("DELETING".equals(sqlOperation)) {
					if (("INTPROB".equals(hdcRequestReferralsOld.getReferralTo())
							|| "PERSOFF".equals(hdcRequestReferralsOld.getReferralTo())
							|| "GOVERNOR".equals(hdcRequestReferralsOld.getReferralTo()))
							&& Optional.ofNullable(lvWorkTrigger).isPresent()) {
						tagWorkflowService.completeWorkflow(lvWorkTrigger,
								new BigDecimal(hdcRequestReferrals.getOffenderCurfewId()), null, null);
					}
				}

				if ((("UPDATING".equals(sqlOperation) && "OM".equals(hdcRequestReferrals.getReferralTo())
						&& "PRISON".equals(hdcRequestReferralsOld.getReferredBy()))
						|| ("INSERTING".equals(sqlOperation) && "OM".equals(hdcRequestReferrals.getReferralTo())))
						&& hdcRequestReferrals.getReceivedDate() == null) {
//				lvXml=tagWfmsgService.createXml();
					tagWfmsgService.append("comments", hdcRequestReferrals.getReferralInformation(), lvXml);
					tagWfmsgService.append("hdc_request_referral_id",
							String.valueOf(hdcRequestReferrals.getHdcRequestReferralId()), lvXml);
					tagWorkflowService.createWorkflow(new BigDecimal(hdcRequestReferrals.getOffenderCurfewId()), null,
							lvXml, new BigDecimal(hdcRequestReferrals.getOffenderBookId()), null, null, null,
							hdcRequestReferrals.getCreateUserId(), "HDC_OM_DUE");
				}
				if (("UPDATING".equals(sqlOperation))
						&& (Optional.ofNullable(hdcRequestReferrals.getReceivedDate()).isPresent()
								&& (hdcRequestReferralsOld == null || (hdcRequestReferralsOld != null
										&& hdcRequestReferralsOld.getReceivedDate() == null)))
						&& "OM".equals(hdcRequestReferrals.getReferralTo())) {
					tagWorkflowService.completeWorkflow("HDC_OM_DUE",
							new BigDecimal(hdcRequestReferrals.getOffenderCurfewId()), null, null);
//				lvXml=tagWfmsgService.createXml();
					tagWorkflowService.createWorkflow(new BigDecimal(hdcRequestReferrals.getOffenderCurfewId()), null,
							lvXml, new BigDecimal(hdcRequestReferrals.getOffenderBookId()), null, null, null,
							hdcRequestReferrals.getCreateUserId(), "HDC_OM_DONE");
				}
			} catch (final Exception e) {
//				 tag_error.handle ( );
				logger.error("hdcRequestReferralsTwfTrigger", e);
			}
		}
		return null;
	}

}
