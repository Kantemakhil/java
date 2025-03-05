package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.WorkRates;
import net.syscon.s4.triggers.WorkRatesTjnRepository;
import net.syscon.s4.triggers.WorkRatesTjnService;

@Service
public class WorkRatesTjnServiceImpl implements WorkRatesTjnService {
	private final Logger logger = LogManager.getLogger(WorkRatesTjnServiceImpl.class);
	@Autowired
	WorkRatesTjnRepository workRatesTjnRepository;

	@Override
	public Integer workRatesTjnTgr(final List<WorkRates> workRatesList, final String sqlOperation) {
		Integer result = 0;
		WorkRates workRatesTarger = new WorkRates();
		if (!workRatesList.isEmpty()) {
			for (final WorkRates workRates : workRatesList) {
				final WorkRates workRatesOld = workRatesTjnRepository.getWorkRates(workRates);
				if ("inserting".equalsIgnoreCase(sqlOperation)) {
					workRatesTarger = new WorkRates();
					BeanUtils.copyProperties(workRates, workRatesTarger);
					workRatesTarger.setJnOperation("INS");
					result = workRatesTjnRepository.insertOrUpdateOrDelete(workRatesTarger);
				} else if ("updating".equalsIgnoreCase(sqlOperation)) {
					workRatesTarger = new WorkRates();
					BeanUtils.copyProperties(workRatesOld, workRatesTarger);
					workRatesTarger.setJnOperation("UPD");
					result = workRatesTjnRepository.insertOrUpdateOrDelete(workRates);
				} else if ("deleting".equalsIgnoreCase(sqlOperation)) {
					workRatesTarger = new WorkRates();
					BeanUtils.copyProperties(workRatesOld, workRatesTarger);
					workRatesTarger.setJnOperation("DEL");
					result = workRatesTjnRepository.insertOrUpdateOrDelete(workRates);
				}

			}
		}
		return result;
	}

}
