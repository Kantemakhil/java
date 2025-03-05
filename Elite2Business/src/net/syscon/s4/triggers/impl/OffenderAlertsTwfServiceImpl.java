package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.sql.Clob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.OffenderAlertsTwfRepository;
import net.syscon.s4.triggers.OffenderAlertsTwfService;

/*
============================================================
   Below comments are copied from OFFENDER_ALERTS_TWF Trigger
============================================================
  MODIFICATION HISTORY
   Person       Date      version      Comments
   GJC       09/05/2006  2.4        Async version
   Krishna   20/04/2006  2.3        Added 'made' for the active alert message; defect #1434
   Krishna   06/04/2006  2.2        Passing blank to tag_workflow.create_case_note proc. as there is a change to the proc. default to 'AUTO'
   Krishna   03/04/2006  2.1        Passing NULL to tag_workflow.create_case_note proc. as there is a change to the proc.
   Krishna   03/04/2006  2.0        Alert triggers (ACTIVE_ALT, INACTIVE_ALT) will create case note message
                                    The active alert trigger (ACTIVE_ALT) will fire when a new offender alert is created
                                    or an existing inactive alert is re-activated
                                    The inactive alert trigger (INACTIVE_ALT) will fire when an active offender alert is
                                    in-activated.
*/
@Service
public class OffenderAlertsTwfServiceImpl implements OffenderAlertsTwfService {
	private final Logger logger = LogManager.getLogger(OffenderAlertsTwfRepositoryImpl.class);
	@Autowired
	OffenderAlertsTwfRepository offenderAlertsTwfRepository;
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer createOffenderAlertMsg(final OffenderAlerts offenderAlerts, final String trgType) {
		try {
//		lvXml = tagWfmsgService.createXml();
			final Clob lvXml = null;
			// below method call is use to prepare XMLType
			tagWfmsgService.append("alert_type", offenderAlerts.getAlertType(), lvXml);
			// below method call is use to prepare XMLType
			tagWfmsgService.append("alert_code", offenderAlerts.getAlertCode(), lvXml);
			// below method call is use to prepare XMLType
			tagWorkflowService.createCaseNote(new BigDecimal(offenderAlerts.getOffenderBookId()), trgType, lvXml, null,
					null, "AUTO", offenderAlerts.getCreateUserId());
		} catch (final Exception e) {
			logger.error("createOffenderAlertMsg", e);
		}
		return null;
	}

	// Below method is call after insert or update on OFFENDER_ALERTS table
	@Override
	public Integer offenderAlertsTwfTrigger(final OffenderAlerts offenderAlerts, final String sqlOperation) {
		// fetching data from OFFENDER_ALERTS table for referencing as old data
		final OffenderAlerts offenderAlertsOld = offenderAlertsTwfRepository
				.getOffenderAlerts(offenderAlerts.getOffenderBookId(), offenderAlerts.getAlertSeq());
		if ("INSERTING".equals(sqlOperation)) {
			if ("INACTIVE".equals(offenderAlerts.getAlertStatus())) {
				createOffenderAlertMsg(offenderAlerts, "INACTIVE_ALT");
			} else {
				createOffenderAlertMsg(offenderAlerts, "ACTIVE_ALT");
			}

		} else {
			if (!offenderAlerts.getAlertStatus().equals(offenderAlertsOld.getAlertStatus())) {
				if (offenderAlerts.getExpiryDate() == null) {
					createOffenderAlertMsg(offenderAlertsOld, "ACTIVE_ALT");
				} else {
					createOffenderAlertMsg(offenderAlertsOld, "INACTIVE_ALT");
				}

			}
		}
		return null;
	}

}
