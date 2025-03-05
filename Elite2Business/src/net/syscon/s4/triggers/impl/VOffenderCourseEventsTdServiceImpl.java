package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.triggers.VOffenderCourseEventsTdRepository;
import net.syscon.s4.triggers.VOffenderCourseEventsTdService;

@Service
public class VOffenderCourseEventsTdServiceImpl implements VOffenderCourseEventsTdService {
	private final Logger logger = LogManager.getLogger(VOffenderCourseEventsTdServiceImpl.class.getName());
	@Autowired
	VOffenderCourseEventsTdRepository vOffenderCourseEventsTdRepository;

	@Override
	public Integer vOffenderCourseEventsTdTgr(final VOffenderCourseEvents old, final VOffenderCourseEvents newObj) {
		Integer result = 0;
		final VOffenderCourseEvents targetObj = new VOffenderCourseEvents();
		BeanUtils.copyProperties(old, targetObj);
		targetObj.setStartTime(newObj.getStartTime());
		targetObj.setEndTime(newObj.getEndTime());
		targetObj.setInTime(newObj.getInTime());
		targetObj.setOutTime(newObj.getOutTime());
		try {
			if (Optional.ofNullable(old).isPresent() && !Optional.ofNullable(old.getEventId()).isPresent()) {
				result = vOffenderCourseEventsTdRepository.insert(targetObj);
			} else {
				result = vOffenderCourseEventsTdRepository.update(old);
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("vOffenderCourseEventsTdTgr", e);
		}
		return result;
	}
}
