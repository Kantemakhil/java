package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.triggers.VOffenderVisitsTuRepository;
import net.syscon.s4.triggers.VOffenderVisitsTuService;

@Service
public class VOffenderVisitsTuServiceImpl implements VOffenderVisitsTuService {
	private final Logger logger = LogManager.getLogger(VOffenderVisitsTuServiceImpl.class);
	@Autowired
	VOffenderVisitsTuRepository vOffenderVisitsTuRepository;

	@Override
	public Integer vOffenderVisitsTu(final VOffenderVisits vOffenderVisits) {
		Integer result = 0;
		final Integer lCount = vOffenderVisitsTuRepository.lCount(vOffenderVisits.getOffenderBookId());
		try {
			if (lCount == 0) {
				result = vOffenderVisitsTuRepository.insertOffenderVisits(vOffenderVisits);
				result = vOffenderVisitsTuRepository.insertOffenderVisitVisitors(vOffenderVisits);
			} else {
				if (vOffenderVisits.getOffenderBookId().compareTo(vOffenderVisits.getVisitOffenderBookId()) == 0) {
					result = vOffenderVisitsTuRepository.updateOffenderVisits(vOffenderVisits);
					result = vOffenderVisitsTuRepository.updateOffenderVisitVisitors(vOffenderVisits);
				}
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("vOffenderVisitsTu", e);
		}
		return result;
	}
}
