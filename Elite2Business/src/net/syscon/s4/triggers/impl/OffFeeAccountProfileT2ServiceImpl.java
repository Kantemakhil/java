package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Repository;
import net.syscon.s4.triggers.OffFeeAccountProfileT2Service;

@Service
public class OffFeeAccountProfileT2ServiceImpl implements OffFeeAccountProfileT2Service {

	@Autowired
	OffFeeAccountProfileT2Repository offFeeAccountProfileT2Repository;

	@Override
	public Integer offFeeAccountProfileT2(FeeAccountProfiles feeAccountProfiles) {
		Integer retVal = 0;
		String oldFeeActStatus = offFeeAccountProfileT2Repository.OldOffFeeActStatus(feeAccountProfiles.getOffenderFeeId());
		if ("A".equals(feeAccountProfiles.getFeeActStatus()) && !("A".equals(oldFeeActStatus))) {
			retVal = offFeeAccountProfileT2Repository.OffFeeAccProfileT2InsertA(feeAccountProfiles);
		} else {
			retVal = offFeeAccountProfileT2Repository.OffFeeAccProfileT2InsertP(feeAccountProfiles);
		}
		return retVal;
	}

}
