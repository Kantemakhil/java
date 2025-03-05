package net.syscon.s4.pkgs.oumacase.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.pkgs.oumacase.OumaCasePkgRepository;
import net.syscon.s4.pkgs.oumacase.OumaCasePkgService;

@Service
public class OumaCasePkgServiceImpl implements OumaCasePkgService {

	@Autowired
	private OumaCasePkgRepository oumaCaseRepository;

	@Override
	public Map<String, Object> caseloadType(String pAgyLocId) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final Caseloads caseloads = oumaCaseRepository.caseloadType(pAgyLocId);
		map.put("P_TRUST", caseloads.getTrustAccountsFlag());
		map.put("P_PAYROLL", caseloads.getPayrollFlag());
		map.put("P_COMM", caseloads.getCommissaryFlag());
		return map;
	}
}
