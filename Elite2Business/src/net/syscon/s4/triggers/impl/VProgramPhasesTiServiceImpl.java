package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.VProgramPhases;
import net.syscon.s4.triggers.VProgramPhasesTiRepository;
import net.syscon.s4.triggers.VProgramPhasesTiService;

@Service
public class VProgramPhasesTiServiceImpl implements VProgramPhasesTiService {
	private final Logger logger = LogManager.getLogger(VProgramPhasesTiServiceImpl.class.getName());
	@Autowired
	VProgramPhasesTiRepository vProgramPhasesTiRepository;

	@Override
	public Integer vProgramPhasesTiTgr(final VProgramPhases vProgramPhases) {
		Integer result = 0;
		if (Optional.ofNullable(vProgramPhases).isPresent()) {
			try {
				result = vProgramPhasesTiRepository.save(vProgramPhases);
			} catch (final Exception e) {
				logger.error("vProgramPhasesTiTgr", e);
				result = 0;
			}
		}
		return result;
	}

}
