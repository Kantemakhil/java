package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.of.payroll.maintenance.SystemEvents;
import net.syscon.s4.triggers.SystemEventsTjnRepository;
import net.syscon.s4.triggers.SystemEventsTjnService;

@Service
public class SystemEventsTjnServiceImpl implements SystemEventsTjnService {
	private static Logger logger = LogManager.getLogger(SystemEventsTjnServiceImpl.class.getName());
	@Autowired
	SystemEventsTjnRepository systemEventsTjnRepository;

	@Override
	public Integer systemEventsTjnTgr(final List<SystemEvents> systemEventsList, final String sqlOperation) {
		Integer result = 0;
		if (!systemEventsList.isEmpty()) {
			try {
				for (final SystemEvents newObj : systemEventsList) {
					final SystemEvents old = systemEventsTjnRepository.getSystemEvents(newObj);
					if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
						result = systemEventsTjnRepository.insert(newObj);
					} else if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
						result = systemEventsTjnRepository.insert(old);
					} else if ("DELETING".equalsIgnoreCase(sqlOperation)) {
						result = systemEventsTjnRepository.insert(old);
					}

				}
			} catch (final Exception e) {
				result = 0;
				logger.error("systemEventsTjnTgr", e);
			}
		}
		return result;
	}

}
