package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;
import net.syscon.s4.triggers.OffenderChargesT1Repository;
import net.syscon.s4.triggers.OffenderChargesT1Service;

@Service
public class OffenderChargesT1ServiceImpl implements OffenderChargesT1Service {
	private static Logger logger = LogManager.getLogger(OffenderChargesT1ServiceImpl.class);
	@Autowired
	OffenderChargesT1Repository offenderChargesT1Repository;

	@Override
	public Long OffenderChargesT1Trigger(final OffenderCharges offenderCharges) {
		try {
//			IF NVL (SYS_CONTEXT ('NOMIS', 'AUDIT_MODULE_NAME', 50), 'X') NOT IN
//            ('CREATE_OFFENCES', 'CREATE_SENTENCE_CHARGES')
			return offenderChargesT1Repository.lidsOffenceNumber(offenderCharges.getOffenderBookId(),
					offenderCharges.getCaseId());
		} catch (final Exception e) {
			logger.error("OffenderChargesT1Trigger", e);
			return null;
		}
	}

}
