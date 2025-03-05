package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.OffenderSentConditionsTwfRepository;
import net.syscon.s4.triggers.OffenderSentConditionsTwfService;

@Service
public class OffenderSentConditionsTwfServiceImpl implements OffenderSentConditionsTwfService {
	Logger logger = LogManager.getLogger(OffenderSentConditionsTwfServiceImpl.class.getName());
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;
	@Autowired
	OffenderSentConditionsTwfRepository offenderSentConditionsTwfRepository;

	@Override
	public Integer offenderSentConditionsTwfTgr(final List<OffenderSentConditions> offenderSentConditionsList) {
		final SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		if (!offenderSentConditionsList.isEmpty()) {
			try {
				for (final OffenderSentConditions newObj : offenderSentConditionsList) {
					final OffenderSentConditions old = offenderSentConditionsTwfRepository.getOffenderSentConditions(newObj);
					if (old.getProgramId() == null && Optional.ofNullable(newObj.getProgramId()).isPresent()) {
						if ("I".equals(newObj.getConditionStatus())) {
//						SQLXML lvXml = tagWfmsgService.createXml();
							tagWfmsgService.append("offender_sent_condition_id",
									String.valueOf(newObj.getOffenderSentConditionId()), null);
							tagWfmsgService.append("length", String.valueOf(newObj.getLength()), null);
							tagWfmsgService.append("length_unit", newObj.getLengthUnit(), null);
							tagWfmsgService.append("condition_type", newObj.getCommConditionType(), null);
							tagWfmsgService.append("condition_code", newObj.getCommConditionCode(), null);
							tagWfmsgService.append("status_update_reason", newObj.getStatusUpdateReason(), null);
							tagWfmsgService.append("start_date", sf.format(newObj.getStartDate()), null);
							tagWorkflowService.createCaseNote(BigDecimal.valueOf(newObj.getOffenderBookId()), "REQ_TERMIND", null, null,
									null, "AUTO", newObj.getCreateUserId());
						}
					}

				}
			} catch (final Exception e) {
				logger.error("offenderSentConditionsTwfTgr", e);
			}
		}
		return null;
	}
}
