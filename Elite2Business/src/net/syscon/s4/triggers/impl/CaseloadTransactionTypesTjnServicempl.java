package net.syscon.s4.triggers.impl;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.CaseloadTransactionTypesjn;
import net.syscon.s4.triggers.CaseloadTransactionTypesTjnService;
import net.syscon.s4.triggers.CaseloadTransactionTypesTjnRepository;


@Service
public class CaseloadTransactionTypesTjnServicempl implements CaseloadTransactionTypesTjnService{

	@Autowired
	CaseloadTransactionTypesTjnRepository caseloadTransactionTypesTjnRepository;

	private static Logger logger = LogManager.getLogger(CaseloadTransactionTypesTjnServicempl.class.getName());

	@Transactional
	@Override
	public void caseloadAccountCodesTjnService(final CaseloadTransactionTypesjn oldCaseloadTransactionTypesjn,
			final CaseloadTransactionTypesjn newCaseloadTransactionTypesjn,final String userName) {
		if(Objects.isNull(oldCaseloadTransactionTypesjn) && Objects.nonNull(newCaseloadTransactionTypesjn)) {
			caseloadTransactionTypesTjnRepository.insertCaseloadTransactionTypesJn(newCaseloadTransactionTypesjn, userName);
		}
		else if(Objects.nonNull(oldCaseloadTransactionTypesjn) && Objects.nonNull(newCaseloadTransactionTypesjn) 
				&& !oldCaseloadTransactionTypesjn.equals(newCaseloadTransactionTypesjn)) {
			caseloadTransactionTypesTjnRepository.insertCaseloadTransactionTypesJn(oldCaseloadTransactionTypesjn, userName);
		}
		else if(Objects.nonNull(oldCaseloadTransactionTypesjn) && Objects.isNull(newCaseloadTransactionTypesjn)) {
			caseloadTransactionTypesTjnRepository.insertCaseloadTransactionTypesJn(oldCaseloadTransactionTypesjn, userName);
		}
	}
}
