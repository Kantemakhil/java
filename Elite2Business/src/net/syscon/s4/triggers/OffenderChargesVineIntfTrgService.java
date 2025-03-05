package net.syscon.s4.triggers;

import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;

public interface OffenderChargesVineIntfTrgService {
	Integer OffenderChargesVineIntfTrg(OffenderCharges offenderCases,final String operationType);
}
