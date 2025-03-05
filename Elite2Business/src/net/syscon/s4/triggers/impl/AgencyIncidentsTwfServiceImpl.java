package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.AgencyIncidentsTwfRepository;
import net.syscon.s4.triggers.AgencyIncidentsTwfService;

@Service
public class AgencyIncidentsTwfServiceImpl implements AgencyIncidentsTwfService {
	Logger logger = LogManager.getLogger(AgencyIncidentsTwfServiceImpl.class);
	@Autowired
	AgencyIncidentsTwfRepository agencyIncidentsTwfRepository;
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;
	@Autowired
	OmsMiscellaneousService omsMiscellaneousService;

	@Override
	public Integer agencyIncidentsTwf(final AgencyIncidents agencyIncidents) {
		final AgencyIncidents agencyIncidentsOld = agencyIncidentsTwfRepository
				.getAgencyIncidents(Long.valueOf(agencyIncidents.getAgencyIncidentId()));
		final Map<String, Object> profileMap = omsMiscellaneousService.getProfileValues("CLIENT", "CREATE_OIC");
		final String actionCode = !profileMap.isEmpty() && profileMap.get("P_PROFILE_VALUE") != null
				? profileMap.get("P_PROFILE_VALUE").toString()
				: null;
		final List<AgencyIncidentParties> incidentPartiesCur = agencyIncidentsTwfRepository
				.agencyIncidentParties(actionCode, Long.valueOf(agencyIncidents.getAgencyIncidentId()));
		if (incidentPartiesCur != null && !incidentPartiesCur.isEmpty()) {
			try {
				for (final AgencyIncidentParties incParRec : incidentPartiesCur) {
//				lvXml = tag_wfmsg.create_xml;
					final String stgPartiesCur = agencyIncidentsTwfRepository
							.stgPartiesCur(Long.parseLong(incParRec.getOffenderBookId().toString()));
					tagWfmsgService.append("AGY_INC_ID", String.valueOf(agencyIncidentsOld.getAgencyIncidentId()),
							null);
					tagWfmsgService.append("OFF_BKG_ID", String.valueOf(incParRec.getOffenderBookId()), null);
					tagWfmsgService.append("PARTY_SEQ", String.valueOf(incParRec.getPartySeq()), null);
					tagWorkflowService.createWorkflow(new BigDecimal(agencyIncidentsOld.getAgencyIncidentId()), null,
							null, new BigDecimal(incParRec.getOffenderBookId()), null, null, null,
							agencyIncidents.getCreateUserId(), "RCL_OIC_CHG");
				}
			} catch (final Exception e) {
				logger.error("agencyIncidentsTwf", e);
				return 0;
			}
		}
		return 1;
	}

}
