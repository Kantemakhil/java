package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.StockItemsJn;
import net.syscon.s4.triggers.StockItemsTjnRepository;
import net.syscon.s4.triggers.StockItemsTjnService;

@Service
public class StockItemsTjnServiceImpl implements StockItemsTjnService {
	@Autowired
	private StockItemsTjnRepository stockItemsTjnRepository;
	private static Logger logger = LogManager.getLogger(StockItemsTjnServiceImpl.class);

	@Override
	public Integer stockItemsTjn(final StockItemsJn stockItemsJn) {
		Integer result = null;
		final StockItemsJn target = new StockItemsJn();
		try {
//		 IF INSERTING THEN
			BeanUtils.copyProperties(stockItemsJn, target);
			target.setJnOperation("INS");
			target.setJnOracleUser(stockItemsJn.getCreateUserId());
			target.setJnDatetime(new Date());
			target.setJnNotes(null);
			target.setJnAppln(null);
			target.setJnSession(new BigDecimal(0));
			result = stockItemsTjnRepository.inserting(target);
//		ELSIF UPDATING THEN
			target.setJnOperation("UPD");
			result = stockItemsTjnRepository.inserting(target);
//		ELSIF DELETING THEN
			// need to pass old object but table doesn't have primary key
			target.setJnOperation("DEL");
			result = stockItemsTjnRepository.inserting(stockItemsJn);
		} catch (final Exception e) {
			logger.error(e);
			return null;
		}
		return result;
	}
}
