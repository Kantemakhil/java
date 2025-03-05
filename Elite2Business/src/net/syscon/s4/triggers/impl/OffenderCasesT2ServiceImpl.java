package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.inst.legals.beans.OffenderCaseIdentifiers;
import net.syscon.s4.pkgs.tag_legal_cases.TagLegalCasesService;
import net.syscon.s4.triggers.OffenderCasesT2Repository;
import net.syscon.s4.triggers.OffenderCasesT2Service;

@Service
public class OffenderCasesT2ServiceImpl implements OffenderCasesT2Service {
	private final Logger logger = LogManager.getLogger(OffenderCasesT2ServiceImpl.class);
	@Autowired
	OffenderCasesT2Repository offenderCasesT2Repository;
	@Autowired
	TagLegalCasesService tagLegalCasesService;

	@Override
	public Integer offenderCasesT2Tgr(final OffenderCases offenderCases, final String sqlOperation) {
		Integer result = 0;
		final OffenderCaseIdentifiers offCaseIden = new OffenderCaseIdentifiers();
		final OffenderCases oldObj = offenderCasesT2Repository.getOffenderCases(offenderCases.getCaseId());
		try {
			if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
				if (Optional.ofNullable(offenderCases).isPresent()
						&& Optional.ofNullable(offenderCases.getCaseInfoNumber()).isPresent()) {
					final String caseInfoPrefix = Optional.ofNullable(offenderCases.getCaseInfoPrefix()).isPresent()
							? offenderCases.getCaseInfoPrefix()
							: "CASE/INFO#";
					offCaseIden.setCaseId(offenderCases.getCaseId());
					offCaseIden.setIdentifierType(caseInfoPrefix);
					offCaseIden.setIdentifierNo(offenderCases.getCaseInfoNumber());
					offCaseIden.setCreateUserId(offenderCases.getCreateUserId());
					offCaseIden.setCreateDatetime(offenderCases.getCreateDatetime());
					offCaseIden.setModifyDatetime(offenderCases.getModifyDatetime());
					result = tagLegalCasesService.caseIdentifiersInsert(offCaseIden);
				}
			} else if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
				if (Optional.ofNullable(offenderCases).isPresent()
						&& Optional.ofNullable(offenderCases.getCaseInfoNumber()).isPresent()) {
					final String vExist = offenderCasesT2Repository.caseIdentifierExist(sqlOperation, sqlOperation);
					if (vExist == null) {
						final String caseInfoPrefix = Optional.ofNullable(offenderCases.getCaseInfoPrefix()).isPresent()
								? offenderCases.getCaseInfoPrefix()
								: "CASE/INFO#";
						offCaseIden.setCaseId(offenderCases.getCaseId());
						offCaseIden.setIdentifierType(caseInfoPrefix);
						offCaseIden.setIdentifierNo(offenderCases.getCaseInfoNumber());
						offCaseIden.setCreateUserId(offenderCases.getCreateUserId());
						offCaseIden.setCreateDatetime(offenderCases.getCreateDatetime());
						offCaseIden.setModifyDatetime(offenderCases.getModifyDatetime());
						result = tagLegalCasesService.caseIdentifiersInsert(offCaseIden);
					}
				}

			}
		} catch (final Exception e) {
			logger.error("offenderCasesT2Tgr", e);
		}
		return result;
	}

}
