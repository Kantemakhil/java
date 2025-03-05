package net.syscon.s4.triggers.impl;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.CaseloadAccountCodesJn;
import net.syscon.s4.triggers.CaseloadAccountCodesTjnRepository;
import net.syscon.s4.triggers.CaseloadAccountCodesTjnService;


@Service
public class CaseloadAccountCodesTjnServicempl implements CaseloadAccountCodesTjnService{

	@Autowired
	CaseloadAccountCodesTjnRepository caseloadAccountCodesTjnRepository;

	private static Logger logger = LogManager.getLogger(CaseloadAccountCodesTjnServicempl.class.getName());

	@Transactional
	@Override
	public void caseloadAccountCodesTjnService(final CaseloadAccountCodesJn oldCaseloadAccountCodesJn,
			final CaseloadAccountCodesJn newCaseloadAccountCodesJn,final String userName) {
		if(Objects.isNull(oldCaseloadAccountCodesJn) && Objects.nonNull(newCaseloadAccountCodesJn)) {
			caseloadAccountCodesTjnRepository.insertCaseloadAccountCodesJn(newCaseloadAccountCodesJn, userName);
		}
		else if(Objects.nonNull(oldCaseloadAccountCodesJn) && Objects.nonNull(newCaseloadAccountCodesJn) 
				&& !oldCaseloadAccountCodesJn.equals(newCaseloadAccountCodesJn)) {
			caseloadAccountCodesTjnRepository.insertCaseloadAccountCodesJn(oldCaseloadAccountCodesJn, userName);
		}
		else if(Objects.nonNull(oldCaseloadAccountCodesJn) && Objects.isNull(newCaseloadAccountCodesJn)) {
			caseloadAccountCodesTjnRepository.insertCaseloadAccountCodesJn(oldCaseloadAccountCodesJn, userName);
		}
	}
}
