package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.triggers.AgencyLocationsT2Repository;
import net.syscon.s4.triggers.AgencyLocationsT2Service;

@Service
public class AgencyLocationsT2ServiceImpl implements AgencyLocationsT2Service {
	@Autowired
	AgencyLocationsT2Repository agencyLocationsT2Repository;

	@Override
	public Integer agencyLocationsT2Trigger(final String agyLocId) throws CustomException {
		final Integer vNumrows = agencyLocationsT2Repository.addressRecords(agyLocId);
		if (vNumrows > 0) {
			throw new CustomException(
					"Cannot DELETE the agency location record because agency location address records exists.");
		}
		final Integer vNumrowsOne = agencyLocationsT2Repository.phoneRecords(agyLocId);
		if (vNumrowsOne > 0) {
			throw new CustomException(
					"Cannot DELETE the agency location record because agency location phone records exists.");
		}
		final Integer vNumrowsTwo = agencyLocationsT2Repository.internetAddresses(agyLocId);
		if (vNumrowsTwo > 0) {
			throw new CustomException(
					"Cannot DELETE the agency location record because agency location internet addresses records exists.");
		}
		return null;
	}

}
