package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.triggers.VOffenderVisitsTiRepository;
import net.syscon.s4.triggers.VOffenderVisitsTiService;

@Service
public class VOffenderVisitsTiServiceImpl implements VOffenderVisitsTiService {
	private final Logger logger = LogManager.getLogger(VOffenderVisitsTiServiceImpl.class);
	@Autowired
	VOffenderVisitsTiRepository vOffenderVisitsTiRepository;

	@Override
	public Integer VOffenderVisitsTiTgr(final VOffenderVisits vOffenderVisits) {
		Integer result = 0;
		final Integer lCount = vOffenderVisitsTiRepository.lCount(vOffenderVisits.getOffenderVisitId());
		try {

			if (lCount == 0) {
				result = vOffenderVisitsTiRepository.insertOffenderVisits(vOffenderVisits);
			}
			result = vOffenderVisitsTiRepository.insertOffenderVisitVisitors(vOffenderVisits);
		} catch (final Exception e) {
			result = 0;
			logger.error("VOffenderVisitsTiTgr", e);
		}
		return result;
	}

}
