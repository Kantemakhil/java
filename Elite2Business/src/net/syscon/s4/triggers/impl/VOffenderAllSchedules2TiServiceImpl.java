package net.syscon.s4.triggers.impl;

import java.sql.Date;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.VOffenderAllSchedules2TiRepository;
import net.syscon.s4.triggers.VOffenderAllSchedules2TiService;

@Service
public class VOffenderAllSchedules2TiServiceImpl implements VOffenderAllSchedules2TiService {
	private static Logger logger = LogManager.getLogger(VOffenderAllSchedules2TiServiceImpl.class);
	@Autowired
	VOffenderAllSchedules2TiRepository vOffenderAllSchedules2TiRepository;

	@Override
	public Integer vOffenderAllSchedules2TiTger(final OffenderIndSchedules offenderIndSchedules) throws CustomException {
		Integer result = 0;
		final LocalDate ld = new Date(offenderIndSchedules.getEventDate().getTime()).toLocalDate();
		final LocalDate ld2 = LocalDate.now();
		final LocalDate finalDate = ld2.minusMonths(3);
		if (ld.isBefore(finalDate)) {
			throw new CustomException("No Back-dating 3-month old record is allowed.");

		}
		if ("EXT_MOV".equals(offenderIndSchedules.getEventClass())
				&& "REL".equals(offenderIndSchedules.getEventType())) {
			throw new CustomException("Offender release recode creation is not allowed here.");
		}
		try {
			result = vOffenderAllSchedules2TiRepository.insert(offenderIndSchedules);
		} catch (final Exception e) {
			result = 0;
			logger.error("vOffenderAllSchedules2TiTger", e);
		}
		return result;
	}

}
