package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.VCoursePhases;
import net.syscon.s4.triggers.VCoursePhasesTiRepository;
import net.syscon.s4.triggers.VCoursePhasesTiService;

@Service
public class VCoursePhasesTiServiceImpl implements VCoursePhasesTiService {
	private final Logger logger = LogManager.getLogger(VCoursePhasesTiServiceImpl.class.getName());
	@Autowired
	VCoursePhasesTiRepository vCoursePhasesTiRepository;

	@Override
	public Integer vCoursePhasesTiTgr(final VCoursePhases vCoursePhases) {
		Integer result = 0;
		if (Optional.ofNullable(vCoursePhases).isPresent()) {
			try {
				result = vCoursePhasesTiRepository.save(vCoursePhases);
			} catch (final Exception e) {
				logger.error("vCoursePhasesTiTgr", e);
				result = 0;
			}
		}
		return result;
	}

}
