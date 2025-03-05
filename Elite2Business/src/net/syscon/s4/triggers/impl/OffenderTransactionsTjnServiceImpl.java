package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.triggers.OffenderTransactionsTjnRepository;
import net.syscon.s4.triggers.OffenderTransactionsTjnService;

@Service
public class OffenderTransactionsTjnServiceImpl implements OffenderTransactionsTjnService {

	private static final Logger logger = LogManager.getLogger(OffenderTransactionsTjnServiceImpl.class);

	@Autowired
	OffenderTransactionsTjnRepository offenderTransactionsTjnRepository;

	@Override
	public Integer OffenderTransactionsTjn(OffenderTransactions newObj, OffenderTransactions oldObj, String operation) {
		Integer val = 0;
		try {
			if (operation.equalsIgnoreCase("INSERT")) {
				val = offenderTransactionsTjnRepository.insertOffenderTransactionsTjn(newObj);
				logger.info("insertOffenderTransactionsTjn reponse" + val);
			} else if (operation.equalsIgnoreCase("UPDATE")) {
				val = offenderTransactionsTjnRepository.updateOffenderTransactionsTjn(oldObj);
				logger.info("updateOffenderTransactionsTjn reponse" + val);
			} else if (operation.equalsIgnoreCase("DELETE")) {
				val = offenderTransactionsTjnRepository.deleteOffenderTransactionsTjn(oldObj);
				logger.info("deleteOffenderTransactionsTjn reponse" + val);
			}

		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " OffenderTransactionsTjn error :: ", e);
			return val = 0;
		}

		return val;
	}

}
