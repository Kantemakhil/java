package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.OffenderExtMovementsTwfService;

/*===================================================================
Below comments are copied from OFFENDER_EXT_MOVEMENTS_TWF Trigger
====================================================================*/
/* MODIFICATION HISTORY
   Person      Date           Version       Comments
   ------------------------------------------------------------------
   GJC         07-Jun-2006    2.3           Fix version Label
   GJC         09-May-2006    2.1           Async version
   Claus       31-Mar-2006    2.0           Created.
*/
@Service
public class OffenderExtMovementsTwfServiceImpl implements OffenderExtMovementsTwfService {
	Logger logger = LogManager.getLogger(OffenderExtMovementsTwfServiceImpl.class.getName());
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer offenderExternalMovementsTrigger(final OffenderExternalMovements offenExterMovmt) {
		final Clob lvXml = null;
		try {
			if (Optional.ofNullable(offenExterMovmt).isPresent() && "ADM".equals(offenExterMovmt.getMovementType())) {
				tagWfmsgService.append("movement_type", offenExterMovmt.getMovementType(), lvXml);
				tagWfmsgService.append("movement_rsn_code", offenExterMovmt.getMovementReasonCode(), lvXml);
				tagWfmsgService.append("from_agy_loc_id", offenExterMovmt.getFromAgyLocId(), lvXml);
				tagWfmsgService.append("to_agy_loc_id", offenExterMovmt.getToAgyLocId(), lvXml);
				tagWorkflowService.createCaseNote(new BigDecimal(offenExterMovmt.getOffenderBookId()), "PRISON_REL",
						lvXml, null, offenExterMovmt.getCreateDatetime(), "AUTO", offenExterMovmt.getCreateUserId());

				if (Optional.ofNullable(offenExterMovmt).isPresent()
						&& "BCBIC".equals(offenExterMovmt.getToAgyLocId())) {
					tagWorkflowService.createWorkflow(new BigDecimal(offenExterMovmt.getMovementSeq()), "TASK", lvXml,
							new BigDecimal(offenExterMovmt.getOffenderBookId()), null, null, null,
							offenExterMovmt.getCreateUserId(), "PRE_TRIAL");
				} else if (Optional.ofNullable(offenExterMovmt).isPresent()
						&& ("REL".equals(offenExterMovmt.getMovementType())
								|| "TRN".equals(offenExterMovmt.getMovementType()))
						&& "OJ".equals(offenExterMovmt.getMovementReasonCode())) {
					tagWfmsgService.append("movement_type", offenExterMovmt.getMovementType(), lvXml);
					tagWfmsgService.append("movement_rsn_code", offenExterMovmt.getMovementReasonCode(), lvXml);
					tagWfmsgService.append("from_agy_loc_id", offenExterMovmt.getFromAgyLocId(), lvXml);
					tagWfmsgService.append("to_agy_loc_id", offenExterMovmt.getToAgyLocId(), lvXml);
					tagWorkflowService.createCaseNote(new BigDecimal(offenExterMovmt.getOffenderBookId()), "PRISON_REL",
							null, null, null, "AUTO", null);
				}

			}
		} catch (final Exception e) {
			logger.error("offenderExternalMovementsTrigger", e);
		}
		return null;
	}

}
