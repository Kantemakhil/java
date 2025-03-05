package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.triggers.OffenderOicSanctionsT1Repository;
import net.syscon.s4.triggers.OffenderOicSanctionsT1Service;
@Service
public class OffenderOicSanctionsT1ServiceImpl implements OffenderOicSanctionsT1Service {
	@Autowired
	private  OffenderOicSanctionsT1Repository offenderOicSanctionsT1Repository;
	
	@Override
	public OffenderOicSanctions offenderOicSanctionsT1(OffenderOicSanctions off) {
		//  IF NVL (SYS_CONTEXT ('NOMIS', 'AUDIT_MODULE_NAME', 50), 'X') <>  'CREATE_OIC_SANCTIONS'
		off.setLidsSanctionNumber(offenderOicSanctionsT1Repository.getValue(off));
		return off;
	}

}
