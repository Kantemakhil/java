package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.triggers.AgencyIncidentPartiesT1Repository;
import net.syscon.s4.triggers.AgencyIncidentPartiesT1Service;

@Service
public class AgencyIncidentPartiesT1ServiceImpl implements AgencyIncidentPartiesT1Service {
	@Autowired
	AgencyIncidentPartiesT1Repository agencyIncidentPartiesT1Repository;

	@Override
	public Integer agencyIncidentPartiesT1Trigger(final Long offenderBookId, final Long agencyIncidentId)
			throws CustomException {
		Integer vNumrows = 0;
		if (Optional.ofNullable(offenderBookId).isPresent()) {
			vNumrows = agencyIncidentPartiesT1Repository.agencyIncidentParties(offenderBookId, agencyIncidentId);
			if (vNumrows > 0) {
				throw new CustomException("DUP_VAL_ON_INDEX");// raise DUP_VAL_ON_INDEX;
			}
		}
		return vNumrows;
	}

}
