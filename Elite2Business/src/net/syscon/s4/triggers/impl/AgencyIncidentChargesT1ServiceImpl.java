package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.triggers.AgencyIncidentChargesT1Repository;
import net.syscon.s4.triggers.AgencyIncidentChargesT1Service;

/*
=================================================================
Below comments are copied from AGENCY_INCIDENT_CHARGES_T1 Trigger
=================================================================
MODIFICATION HISTORY
Person       Date        version      Comments
-----------  ---------   -----------  -------------------------------
Surya        05/06/2006  2.0          Initial Draft - Populate lids_charge_number
                                      upon insert of a charge.
Surya        09/06/2006  2.1          Lids charge number should be incremented with
                                      respect to YEAR and offender.
Venu         27/06/2006  2.2          Defect# 2923: Corrected the LIDS Charge Number format.
*/
@Service
public class AgencyIncidentChargesT1ServiceImpl implements AgencyIncidentChargesT1Service {
	private final Logger logger = LogManager.getLogger(AgencyIncidentChargesT1ServiceImpl.class);
	@Autowired
	AgencyIncidentChargesT1Repository agencyIncidentChargesT1Repository;

	// Below trigger method is call before insert on AGENCY_INCIDENT_CHARGES table
	@Override
	public List<AgencyIncidentCharges> agencyIncidentChargesT1Tgr(
			final List<AgencyIncidentCharges> agencyIncidentChargesList) {
		try {
			if (!agencyIncidentChargesList.isEmpty()) {
				for (final AgencyIncidentCharges newObj : agencyIncidentChargesList) {
					// getting the lids_charge_number
					final Integer lidsCur = agencyIncidentChargesT1Repository.lidsCur(newObj);
//		IF NVL (SYS_CONTEXT ('NOMIS', 'AUDIT_MODULE_NAME', 50), 'X') NOT IN ('CREATE_INCIDENTS_PARTIES_CHGS'){
					newObj.setLidsChargeNumber(lidsCur);
//	}
				}
			}
		} catch (final Exception e) {
			logger.error("agencyIncidentChargesT1Tgr", e);
		}
		return agencyIncidentChargesList;
	}

}
