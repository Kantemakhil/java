package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.OffenderSentCalculations;
import net.syscon.s4.triggers.OffenderSentCalculationsTwfService;

@Service
public class OffenderSentCalculationsTwfServiceImpl implements OffenderSentCalculationsTwfService {
	Logger logger = LogManager.getLogger(OffenderSentCalculationsTwfServiceImpl.class.getName());
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer offenderSentCalculationsTwfTgr(final List<OffenderSentCalculations> offenderSentCalculationsList) {
		if (!offenderSentCalculationsList.isEmpty()) {
			try {
				for (final OffenderSentCalculations newObj : offenderSentCalculationsList) {
//				SQLXML lvXml= tagWfmsgService.createXml();
					final Object lvXml = null;
					final Date lvTrgDate = Optional.ofNullable(newObj).isPresent()
							&& Optional.ofNullable(newObj.getHdcedOverridedDate()).isPresent()
									? newObj.getHdcedOverridedDate()
									: newObj.getHdcedCalculatedDate();
					final SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
					tagWfmsgService.append("trigger_date", sf.format(lvTrgDate), null);
					tagWfmsgService.append("offender_sent_calculation_id",
							String.valueOf(newObj.getOffenderSentCalculationId()), null);
					tagWorkflowService.createWorkflow(new BigDecimal(newObj.getOffenderSentCalculationId()), null,
							lvXml, new BigDecimal(newObj.getOffenderBookId()), new Date(), new Date(), null,
							newObj.getCreateUserId(), "HDC_ELIGIBLE");
				}
			} catch (final Exception e) {
				logger.error("offenderSentCalculationsTwfTgr", e);
			}
		}
		return null;
	}

}
