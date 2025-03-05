package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.beans.VOffenderProceedingSents;
import net.syscon.s4.triggers.VOffenderProceedingSentsTuRepository;
import net.syscon.s4.triggers.VOffenderProceedingSentsTuService;

@Service
public class VOffenderProceedingSentsTuServiceImpl implements VOffenderProceedingSentsTuService {
	private final Logger logger = LogManager.getLogger(VOffenderProceedingSentsTuServiceImpl.class.getName());
	@Autowired
	VOffenderProceedingSentsTuRepository vOffenderProceedingSentsTuRepository;

	@Override
	public Integer vOffenderProceedingSentsTuTgr(final VOffenderProceedingSents newObj,
			final VOffenderProceedingSents old) {
		Integer result = 0;
		try {
			if (Optional.ofNullable(newObj).isPresent() && Optional.ofNullable(old).isPresent()
					&& !old.getProceedingSentenceFlag().equals(newObj.getProceedingSentenceFlag())) {
				if ("N".equals(newObj.getProceedingSentenceFlag())) {
					result = vOffenderProceedingSentsTuRepository.delete(newObj);
				} else if ("Y".equals(newObj.getProceedingSentenceFlag())) {
					result = vOffenderProceedingSentsTuRepository.insert(newObj);
				}
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("vOffenderProceedingSentsTuTgr", e);
		}
		return result;
	}

}
