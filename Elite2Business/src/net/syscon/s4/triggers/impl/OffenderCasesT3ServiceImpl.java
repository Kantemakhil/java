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
import net.syscon.s4.triggers.OffenderCasesT3Repository;
import net.syscon.s4.triggers.OffenderCasesT3Service;

@Service
public class OffenderCasesT3ServiceImpl implements OffenderCasesT3Service {
	Logger logger = LogManager.getLogger(OffenderCasesT3ServiceImpl.class.getName());


	@Autowired
	OffenderCasesT3Repository OffenderCasesT3Repository;

	@Transactional
	@Override
	public void offenderCasesT3(final OffenderCases newOffenderCases) {
		OffenderCases oldOffenderCases = OffenderCasesT3Repository.getOffenderCases(newOffenderCases.getCaseId());
		if((Objects.nonNull(oldOffenderCases) && Objects.nonNull(newOffenderCases) 
				&& Objects.isNull(newOffenderCases.getSealFlag())) || 
				StringUtils.equals(oldOffenderCases.getSealFlag(), newOffenderCases.getSealFlag()) &&
				!StringUtils.equals(oldOffenderCases.getStatusUpdateReason(), newOffenderCases.getStatusUpdateReason())) {
			OffenderCasesT3Repository.insertOffenderCaseStatuses(oldOffenderCases, newOffenderCases);
		}
	}
}
