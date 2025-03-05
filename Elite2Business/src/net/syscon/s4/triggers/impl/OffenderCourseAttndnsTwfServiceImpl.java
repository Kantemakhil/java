package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfRepository;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfService;

@Service
public class OffenderCourseAttndnsTwfServiceImpl implements OffenderCourseAttndnsTwfService {
	private static Logger logger = LogManager.getLogger(OffenderCourseAttndnsTwfServiceImpl.class);
	@Autowired
	OffenderCourseAttndnsTwfRepository offenderCourseAttndnsTwfRepository;
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer offenderCourseAttndnsTwf(final OffenderCourseAttendances newObj, final String sqlOperation) {
		final OffenderCourseAttendances offenderCourseAtt = offenderCourseAttndnsTwfRepository
				.getOffenderCourseAttendances(newObj.getEventId());
		try {
			if (offenderCourseAtt != null
					&& ("UPDATING".equals(sqlOperation)
							&& ("FTA".equals(newObj.getEventOutcome()) || "FTC".equals(newObj.getEventOutcome()))
							&& (!"FTA".equals(offenderCourseAtt.getEventOutcome())
									&& !"FTC".equals(offenderCourseAtt.getEventOutcome())))
					|| ("INSERTING".equals(sqlOperation)
							&& ("FTA".equals(newObj.getEventOutcome()) || "FTC".equals(newObj.getEventOutcome())))
							&& ("DRR".equals(newObj.getEventType()) || "SA".equals(newObj.getEventType())
									|| "ACP".equals(newObj.getEventType()) || "UW".equals(newObj.getEventType()))) {
//			lv_xml = Tag_Wfmsg.create_xml;
				final Object lvXml = null;
				tagWfmsgService.append("program_profile_id", String.valueOf(newObj.getOffPrgrefId()), null);
				tagWfmsgService.append("event_type", newObj.getEventType(), null);
				tagWorkflowService.createWorkflow(new BigDecimal(newObj.getEventId()), null, lvXml,
						new BigDecimal(newObj.getOffenderBookId()), null, null, null, newObj.getCreateUserId(),
						"FIRST_FTA");

			}
			if (offenderCourseAtt != null && "UPDATING".equals(sqlOperation)
					&& ("FTA".equals(offenderCourseAtt.getEventOutcome())
							|| "FTC".equals(offenderCourseAtt.getEventOutcome()))
					&& (!"FTA".equals(newObj.getEventOutcome()) && !"FTC".equals(newObj.getEventOutcome()))
					&& ("DRR".equals(newObj.getEventType()) || "SA".equals(newObj.getEventType())
							|| "ACP".equals(newObj.getEventType()) || "UW".equals(newObj.getEventType()))) {

//			lv_xml = Tag_Wfmsg.create_xml;
				final Object lvXml = null;
				tagWfmsgService.append("event_id", String.valueOf(newObj.getEventId()), null);
				tagWorkflowService.createWorkflow(new BigDecimal(newObj.getEventId()), null, lvXml, null, null, null,
						null, newObj.getCreateUserId(), "FIRST_FTA");

			}
		} catch (final Exception e) {
//	Tag_Error.handle ( );
			logger.error("offenderCourseAttndnsTwf", e);
		}
		return null;
	}

}
