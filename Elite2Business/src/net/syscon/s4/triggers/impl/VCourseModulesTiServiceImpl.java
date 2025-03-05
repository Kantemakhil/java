package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.VCourseModules;
import net.syscon.s4.triggers.VCourseModulesTiRepository;
import net.syscon.s4.triggers.VCourseModulesTiService;

@Service
public class VCourseModulesTiServiceImpl implements VCourseModulesTiService {
	private final Logger logger = LogManager.getLogger(VCourseModulesTiServiceImpl.class.getName());
	@Autowired
	VCourseModulesTiRepository vCourseModulesTiRepository;

	@Override
	public Integer vCourseModulesTi(final VCourseModules vCourseModules) {
		Integer reuslt = 0;
		try {
			if (Optional.ofNullable(vCourseModules).isPresent()) {
				reuslt = vCourseModulesTiRepository.insert(vCourseModules);
			}
		} catch (final Exception e) {
			logger.error("vCourseModulesTi", e);
			return 0;
		}
		return reuslt;
	}

}
