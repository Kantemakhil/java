package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.triggers.OffFeeAccountOverridesThtyRepository;
import net.syscon.s4.triggers.OffFeeAccountOverridesThtyService;

@Service
public class OffFeeAccountOverridesThtyServiceImpl implements OffFeeAccountOverridesThtyService {
	@Autowired
	private OffFeeAccountOverridesThtyRepository offFeeAccountOverridesThtyRepository;

	@Override
	public Integer OffFeeAccountOvverRideHistory(FeeOverrideDetails feeOverrideDetails) {
		return offFeeAccountOverridesThtyRepository.OffFeeAccountOvverRideHistory(feeOverrideDetails);
	}

}
