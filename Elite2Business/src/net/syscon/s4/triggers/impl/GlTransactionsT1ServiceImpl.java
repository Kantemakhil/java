package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.GlTransactionsT1Repository;
import net.syscon.s4.triggers.GlTransactionsT1Service;
import net.syscon.s4.triggers.TrustAudits;

@Service
public class GlTransactionsT1ServiceImpl implements GlTransactionsT1Service {
	private final Logger logger = LogManager.getLogger(GlTransactionsT1ServiceImpl.class);
	@Autowired
	GlTransactionsT1Repository glTransactionsT1Repository;

	@Override
	public Integer glTransactionsT1Trigger(final TrustAudits trustAudits) {
		Integer result = 0;
		final String vProfileValue = glTransactionsT1Repository.getVProfileValue();
		if ("Y".equals(vProfileValue)) {
//			v_sessionid     NUMBER (12) := TO_NUMBER(USERENV('SESSIONID'));
			final Long vSessionid = null;
			try {
				final String moduleName = glTransactionsT1Repository.getModuleName(vSessionid);
				if (Optional.ofNullable(moduleName).isPresent()) {
					trustAudits.setModuleName(moduleName);
					result = glTransactionsT1Repository.savetrustAudits(trustAudits);
				}
			} catch (final Exception e) {
				result = 0;
				logger.info("Couldn't find Module Name for GL Auditing.");
				logger.error("glTransactionsT1Trigger", e);
			}
		}
		return result;
	}
}
