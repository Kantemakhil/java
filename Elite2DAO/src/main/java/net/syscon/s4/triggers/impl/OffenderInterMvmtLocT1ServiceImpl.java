package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.movements.beans.OffenderInterMvmtLocations;
import net.syscon.s4.triggers.OffenderInterMvmtLocT1Repository;
import net.syscon.s4.triggers.OffenderInterMvmtLocT1Service;

@Service
public class OffenderInterMvmtLocT1ServiceImpl implements OffenderInterMvmtLocT1Service {
	Logger logger = LogManager.getLogger(OffenderInterMvmtLocT1ServiceImpl.class.getName());
	@Autowired
	OffenderInterMvmtLocT1Repository offenderInterMvmtLocT1Repository;

	@Override
	public Integer offenderInterMvmtLocT1Tgr(final List<OffenderInterMvmtLocations> offenderInterMvmtLocationsList) {
		Integer result = 0;
		try {
			if (!offenderInterMvmtLocationsList.isEmpty()) {
				result = offenderInterMvmtLocT1Repository.update(offenderInterMvmtLocationsList);
			}
		} catch (final Exception e) {
			logger.error("offenderInterMvmtLocT1Tgr", e);
		}
		return result;
	}
}
