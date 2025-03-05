package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.sql.SQLXML;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfRepository;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;
/*
==============================================================================================================================
   Below comments are copied from OFFENDER_IND_SCHEDULES_TWF Trigger
==============================================================================================================================
/*-- Name      Date        Version     Comment
---------------------------------------------------------------
-- GJC       25-Jul-2006  2.8        Defect 3464
-- GJC       25-Jul-2006  2.7        Defect 3464
-- GJC       25-Jul-2006  2.6        Defect 3464
-- GJC       24-Jul-2006  2.5        Defect 3464
-- GJC       04-Jul-2006  2.4        FTA and FTC still not correct
-- GJC       28-Jun-2006  2.3        Should be refering to event_outcome
-- GJC       19-Jun-2006  2.2        Defect 2726
-- GJC       12-Jun-2006  2.1        Release 1.1 workflow version
-- GJC       30-May-2006  2.0        Initial version
---------------------------------------------------------------*/
@Service
public class OffenderIndSchedulesTwfServiceImpl implements OffenderIndSchedulesTwfService {
	private final Logger logger = LogManager.getLogger(OffenderIndSchedulesTwfServiceImpl.class);
	@Autowired
	OffenderIndSchedulesTwfRepository offenderIndSchedulesTwfRepository;
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer offenderIndSchedulesTwfTgr(final OffenderIndSchedules newObj, final String sqlOperation) {
		final OffenderIndSchedules offenderIndSchedulesOld = offenderIndSchedulesTwfRepository
				.getOffenderIndSchedules(newObj.getEventId());
		final SQLXML lvXml = null;
		try {
			if (("INSERTING".equalsIgnoreCase(sqlOperation) && (newObj.getEventType()!=null &&!"DRR".equals(newObj.getEventType()))
					&& (Optional.ofNullable(newObj).isPresent()
							&& Optional.ofNullable(newObj.getEventOutcome()).isPresent()))
					|| ("UPDATING".equalsIgnoreCase(sqlOperation) && !"DRR".equals(newObj.getEventType())
							&& (Optional.ofNullable(newObj).isPresent()
									&& Optional.ofNullable(newObj.getEventOutcome()).isPresent()
									&& (offenderIndSchedulesOld == null
											|| (Optional.ofNullable(offenderIndSchedulesOld).isPresent()&&
													(offenderIndSchedulesOld!=null && null == offenderIndSchedulesOld.getEventOutcome())))))) {
				if(newObj!=null && newObj.getEventType()!=null && newObj.getEventSubType()!=null && newObj.getEventOutcome()!=null && newObj.getOffenderBookId()!=null) {
				tagWfmsgService.append("event_type", newObj.getEventType(), null);
				tagWfmsgService.append("event_sub_type", newObj.getEventSubType(), null);
				tagWfmsgService.append("event_outcome", newObj.getEventOutcome(), null);
				tagWorkflowService.createCaseNote(new BigDecimal(newObj.getOffenderBookId()), "APPOINT_OUTC", lvXml,
						null, null, "AUTO", newObj.getCreateUserId());
				}

			}

			if (("UPDATING".equalsIgnoreCase(sqlOperation)
					&& ("FTA".equals(newObj.getEventOutcome()) || "FTC".equals(newObj.getEventOutcome())) &&
					(offenderIndSchedulesOld!=null &&  ("FTA".equals(offenderIndSchedulesOld.getEventOutcome()))
							||(offenderIndSchedulesOld!=null && "FTC".equals(offenderIndSchedulesOld.getEventOutcome()))))
					|| ("INSERTING".equalsIgnoreCase(sqlOperation)
							&& ("FTA".equals(newObj.getEventOutcome()) || "FTC".equals(newObj.getEventOutcome())))) {
				tagWfmsgService.append("event_id", String.valueOf(newObj.getEventId()), null);
				tagWfmsgService.append("event_type", "OCDCLOGS", null);
				tagWorkflowService.createWorkflow(new BigDecimal(newObj.getEventId()), sqlOperation, lvXml,
						new BigDecimal(newObj.getOffenderBookId()), null, null, null, newObj.getCreateUserId(),
						"FIRST_FTA");

			}
			if ("UPDATING".equalsIgnoreCase(sqlOperation)
					&& (offenderIndSchedulesOld.getEventOutcome()!=null && "FTA".equals(offenderIndSchedulesOld.getEventOutcome())
							|| (offenderIndSchedulesOld.getEventOutcome()!=null &&"FTC".equals(offenderIndSchedulesOld.getEventOutcome())))
					&& ("FTA".equals(newObj.getEventOutcome()) || "FTC".equals(newObj.getEventOutcome()))) {
				tagWfmsgService.append("event_id", String.valueOf(newObj.getEventId()), null);
				tagWorkflowService.completeWorkflow("FIRST_FTA", new BigDecimal(newObj.getEventId()), null, lvXml);
			}

		} catch (final Exception e) {
			logger.error("offenderIndSchedulesTwfTgr", e);
		}
		return null;
	}
}
