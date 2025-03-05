package net.syscon.s4.triggers.impl;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.triggers.AddressesT2Repository;
import net.syscon.s4.triggers.AddressesT2Service;
import net.syscon.s4.triggers.OffenderCasesT1Repository;
import net.syscon.s4.triggers.OffenderCasesT1Service;

@Service
public class OffenderCasesT1ServiceImpl implements OffenderCasesT1Service {
	Logger logger = LogManager.getLogger(OffenderCasesT1ServiceImpl.class.getName());

	@Autowired
	OffenderCasesT1Repository offenderCasesT1Repository;

	@Transactional
	@Override
	public void offenderCasesT1(final OffenderCases newOffenderCases) {
		OffenderCases oldOffenderCases = offenderCasesT1Repository.getOffenderCases(newOffenderCases.getCaseId());
		if((Objects.nonNull(oldOffenderCases) && Objects.nonNull(newOffenderCases) 
				&& Objects.isNull(newOffenderCases.getSealFlag())) || 
				StringUtils.equals(oldOffenderCases.getSealFlag(), newOffenderCases.getSealFlag())) {
			
		}
		String caseIntegrateFlag="N";
		caseIntegrateFlag=offenderCasesT1Repository.getProfileValue();
		if("Y".equals(caseIntegrateFlag)) {
			offenderCasesT1Repository.updateOffenderDeductions(newOffenderCases.getCaseInfoNumber(), newOffenderCases.getCaseId());
			offenderCasesT1Repository.updateOffenderPaymentPlans(newOffenderCases.getCaseInfoNumber(), newOffenderCases.getCaseId());
		}
	}
}
