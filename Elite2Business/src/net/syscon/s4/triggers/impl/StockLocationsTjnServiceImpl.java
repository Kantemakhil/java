package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.StockLocations;
import net.syscon.s4.triggers.StockLocationsTjnRepository;
import net.syscon.s4.triggers.StockLocationsTjnService;

@Service
public class StockLocationsTjnServiceImpl implements StockLocationsTjnService {
	private static Logger logger = LogManager.getLogger(StockLocationsTjnServiceImpl.class.getName());
	@Autowired
	StockLocationsTjnRepository stockLocationsTjnRepository;

	@Override
	public Integer stockLocationsTjnTgr(final List<StockLocations> stockLocationsList, final String sqlOperation) {
		Integer result = 0;
		if (!stockLocationsList.isEmpty()) {
			try {
				for (final StockLocations newObj : stockLocationsList) {
					final StockLocations old = stockLocationsTjnRepository.getStockLocations(newObj);
					if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
						result = stockLocationsTjnRepository.insert(newObj);
					} else if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
						result = stockLocationsTjnRepository.insert(old);
					} else if ("DELETING".equalsIgnoreCase(sqlOperation)) {
						result = stockLocationsTjnRepository.insert(old);
					}

				}
			} catch (final Exception e) {
				result = 0;
				logger.error("stockLocationsTjnTgr", e);
			}
		}
		return result;
	}

}
