package net.syscon.s4.workspace.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffendersIntakeSummary;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.workspace.OwintakeRepository;
import net.syscon.s4.workspace.OwintakeService;


@Service
public class OwintakeServiceImlp extends BaseBusiness implements OwintakeService {
	
	@Autowired
	private OwintakeRepository owintakeRepository;
	
	
	@Override
	public List<OffendersIntakeSummary> getOffendersSummary(String caseLoadId) {
		return owintakeRepository.getOffendersSummary(caseLoadId);
	}

}
