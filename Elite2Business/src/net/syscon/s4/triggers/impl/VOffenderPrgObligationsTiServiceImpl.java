package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.triggers.VOffenderPrgObligationsTiRepository;
import net.syscon.s4.triggers.VOffenderPrgObligationsTiService;

@Service
public class VOffenderPrgObligationsTiServiceImpl implements VOffenderPrgObligationsTiService {
	@Autowired
	VOffenderPrgObligationsTiRepository vOffenderPrgObligationsTiRepository;

	@Override
	public Integer vOffenderPrgObligationsTiTgr(final VOffenderPrgObligations oldObj,
			final VOffenderPrgObligations newObj, final String sqlOperation) {
		Integer result = 0;
		if ("inserting".equalsIgnoreCase(sqlOperation)) {
			result = vOffenderPrgObligationsTiRepository.insert(newObj);
		} else if ("updating".equalsIgnoreCase(sqlOperation)) {
			result = vOffenderPrgObligationsTiRepository.update(newObj);
		} else {
			result = vOffenderPrgObligationsTiRepository.delete1(oldObj);
			result = vOffenderPrgObligationsTiRepository.delete2(oldObj);
		}
		return result;
	}

}
