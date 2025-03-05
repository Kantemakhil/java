package net.syscon.s4.triggers.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffStatusesPkgSpec;
import net.syscon.s4.triggers.AfterUpdateStatementReqRepository;
import net.syscon.s4.triggers.AfterUpdateStatementReqService;

@Service
public class AfterUpdateStatementReqServiceImpl implements AfterUpdateStatementReqService {
	@Autowired
	private AfterUpdateStatementReqRepository afterUpdateStatementReqRepository;
	@Transactional
	@Override
	public void afterUpdateOffInstReq(final OffStatusesPkgSpec offStatusesPkgSpec) {
		if(Objects.nonNull(offStatusesPkgSpec) && Objects.nonNull(offStatusesPkgSpec.getLvBookId())
				&& Objects.nonNull(offStatusesPkgSpec.getLvRequesDate())
				&& Objects.nonNull(offStatusesPkgSpec.getLvRequestType())
				&& offStatusesPkgSpec.getLvRequestType().equals("OPEN")
				&& offStatusesPkgSpec.getLvNewRequestStatus().equals("OPEN")
				&& Objects.nonNull(offStatusesPkgSpec.getLvNextReview())
				&& Objects.nonNull(offStatusesPkgSpec.getLvDurationCode())
				&& Objects.nonNull(offStatusesPkgSpec.getLvRevBox())
				&& offStatusesPkgSpec.getLvRevBox().equals("Y")){
					afterUpdateStatementReqRepository.insertOffenderInternalStatuses(offStatusesPkgSpec);
				}
	}

}
