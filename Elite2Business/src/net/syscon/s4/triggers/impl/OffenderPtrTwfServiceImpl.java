package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.sql.SQLXML;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.CreateAgencyWorkflow;
import net.syscon.s4.triggers.OffenderPtr;
import net.syscon.s4.triggers.OffenderPtrTwfRepository;
import net.syscon.s4.triggers.OffenderPtrTwfService;

@Service
public class OffenderPtrTwfServiceImpl implements OffenderPtrTwfService {
	private static Logger logger = LogManager.getLogger(OffenderPtrTwfServiceImpl.class);
	@Autowired
	OffenderPtrTwfRepository offenderPtrTwfRepository;
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer offenderPtrTwfTgr(final OffenderPtr offenderPtr, final String sqlOperation) {
//		SQLXML lvXml = tagWfmsgService.createXml();
		final SQLXML lvXml = null;
		CreateAgencyWorkflow createAgencyWorkflow = new CreateAgencyWorkflow();
		tagWfmsgService.append("ptr_id", String.valueOf(offenderPtr.getPtrId()), null);
		final OffenderPtr offenderPtrOld = offenderPtrTwfRepository.getOffenderPtr(offenderPtr.getPtrId());
		try {
			if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
				/* Create workflow and casenote for new request */
				createAgencyWorkflow.setOffenderBookId(offenderPtr.getOffenderBookId());
				createAgencyWorkflow.setTriggerName("EXT_OWN_PTR");
				createAgencyWorkflow.setAgyLocId(offenderPtr.getToAgyLocId());
				createAgencyWorkflow.setKey(String.valueOf(offenderPtr.getPtrId()));
				createAgencyWorkflow.setParams(lvXml);
				createAgencyWorkflow.setCreateUserId(offenderPtr.getCreateUserId());
				tagWorkflowService.createAgencyWorkflow(createAgencyWorkflow);
				tagWorkflowService.createCaseNote(new BigDecimal(offenderPtr.getOffenderBookId()), "EXT_OWN_PTR", null,
						null, null, null, offenderPtr.getCreateUserId());
			} else if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
				final String decisionCodeNew = Optional.ofNullable(offenderPtr).isPresent()
						&& Optional.ofNullable(offenderPtr.getDecisionCode()).isPresent()
								? offenderPtr.getDecisionCode()
								: "";
				final String decisionCodeOld = Optional.ofNullable(offenderPtrOld).isPresent()
						&& Optional.ofNullable(offenderPtrOld.getDecisionCode()).isPresent()
								? offenderPtrOld.getDecisionCode()
								: "";
				if (decisionCodeNew.equals(decisionCodeOld)) {
					tagWorkflowService.createCaseNote(new BigDecimal(offenderPtr.getOffenderBookId()), "EXT_PTR_OUTC",
							null, null, null, null, offenderPtr.getCreateUserId());
					/* complete request workflow */
					tagWorkflowService.completeWorkflow("EXT_OWN_PTR", new BigDecimal(offenderPtr.getPtrId()), null,
							null);
					if (Optional.ofNullable(offenderPtr).isPresent()
							&& "ACCEPT".equals(offenderPtr.getDecisionCode())) {
						/* If decision is accepted then create initialise transfer workflow. */
						createAgencyWorkflow = new CreateAgencyWorkflow();
						createAgencyWorkflow.setOffenderBookId(offenderPtr.getOffenderBookId());
						createAgencyWorkflow.setTriggerName("EXT_OWN_ITRN");
						createAgencyWorkflow.setAgyLocId(offenderPtr.getToAgyLocId());
						createAgencyWorkflow.setKey(String.valueOf(offenderPtr.getPtrId()));
						createAgencyWorkflow.setParams(lvXml);
						createAgencyWorkflow.setCreateUserId(offenderPtr.getCreateUserId());
						tagWorkflowService.createAgencyWorkflow(createAgencyWorkflow);
					}
				}
				if (Optional.ofNullable(offenderPtr).isPresent() && "Y".equals(offenderPtr.getTransferFlag())
						&& Optional.ofNullable(offenderPtrOld).isPresent()
						&& "N".equals(offenderPtrOld.getTransferFlag())) {
					/*
					 * If transferred then create transfer workflow, complete initialise transfer
					 * and create casenote
					 */
					createAgencyWorkflow = new CreateAgencyWorkflow();
					createAgencyWorkflow.setOffenderBookId(offenderPtr.getOffenderBookId());
					createAgencyWorkflow.setTriggerName("EXT_OWN_TRN");
					createAgencyWorkflow.setAgyLocId(offenderPtr.getToAgyLocId());
					createAgencyWorkflow.setKey(String.valueOf(offenderPtr.getPtrId()));
					createAgencyWorkflow.setParams(lvXml);
					createAgencyWorkflow.setCreateUserId(offenderPtr.getCreateUserId());
					tagWorkflowService.createAgencyWorkflow(createAgencyWorkflow);
					tagWorkflowService.completeWorkflow("EXT_OWN_ITRN", new BigDecimal(offenderPtr.getPtrId()), null,
							null);
					tagWorkflowService.createCaseNote(new BigDecimal(offenderPtr.getOffenderBookId()), "EXT_OWN_TRN",
							null, null, null, null, offenderPtr.getCreateUserId());
				}
				if (Optional.ofNullable(offenderPtr).isPresent() && "Y".equals(offenderPtr.getTransferCancelFlag())
						&& Optional.ofNullable(offenderPtrOld).isPresent()
						&& "N".equals(offenderPtrOld.getTransferCancelFlag())) {
					/*
					 * If transferred cancelled then create transfer workflow, complete initialise
					 * transfer and create casenote
					 */
					createAgencyWorkflow = new CreateAgencyWorkflow();
					createAgencyWorkflow.setOffenderBookId(offenderPtr.getOffenderBookId());
					createAgencyWorkflow.setTriggerName("EXT_TRN_CANC");
					createAgencyWorkflow.setAgyLocId(offenderPtr.getToAgyLocId());
					createAgencyWorkflow.setKey(String.valueOf(offenderPtr.getPtrId()));
					createAgencyWorkflow.setParams(lvXml);
					createAgencyWorkflow.setCreateUserId(offenderPtr.getCreateUserId());
					tagWorkflowService.createAgencyWorkflow(createAgencyWorkflow);
					tagWorkflowService.completeWorkflow("EXT_OWN_ITRN", new BigDecimal(offenderPtr.getPtrId()), null,
							null);
					tagWorkflowService.createCaseNote(new BigDecimal(offenderPtr.getOffenderBookId()), "EXT_TRN_CANC",
							null, null, null, null, offenderPtr.getCreateUserId());
				}
				if (Optional.ofNullable(offenderPtr).isPresent() && "Y".equals(offenderPtr.getPtrCancelFlag())
						&& Optional.ofNullable(offenderPtrOld).isPresent()
						&& "N".equals(offenderPtrOld.getPtrCancelFlag())) {
					/*
					 * If transferred cancelled then create transfer workflow, complete initialise
					 * transfer and create casenote
					 */
					createAgencyWorkflow = new CreateAgencyWorkflow();
					createAgencyWorkflow.setOffenderBookId(offenderPtr.getOffenderBookId());
					createAgencyWorkflow.setTriggerName("EXT_PTR_CANC");
					createAgencyWorkflow.setAgyLocId(offenderPtr.getToAgyLocId());
					createAgencyWorkflow.setKey(String.valueOf(offenderPtr.getPtrId()));
					createAgencyWorkflow.setParams(lvXml);
					createAgencyWorkflow.setCreateUserId(offenderPtr.getCreateUserId());
					tagWorkflowService.createAgencyWorkflow(createAgencyWorkflow);
					tagWorkflowService.completeWorkflow("EXT_OWN_PTR", new BigDecimal(offenderPtr.getPtrId()), null,
							null);
					tagWorkflowService.createCaseNote(new BigDecimal(offenderPtr.getOffenderBookId()), "EXT_PTR_CANC",
							null, null, null, null, offenderPtr.getCreateUserId());
				}

			}
		} catch (final Exception e) {
			logger.error("offenderPtrTwfTgr", e);
		}
		return null;
	}
}
