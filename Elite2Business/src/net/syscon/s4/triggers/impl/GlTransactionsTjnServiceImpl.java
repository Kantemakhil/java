package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.triggers.GlTransactionsJn;
import net.syscon.s4.triggers.GlTransactionsTjnRepository;
import net.syscon.s4.triggers.GlTransactionsTjnService;

@Service
public class GlTransactionsTjnServiceImpl implements GlTransactionsTjnService {
	private final Logger logger = LogManager.getLogger(GlTransactionsTjnServiceImpl.class);
	@Autowired
	GlTransactionsTjnRepository glTransactionsTjnRepository;

	@Override
	public Integer glTransactionsTjnTrigger(final GlTransactions glTransactions,String operationType) {
		List<GlTransactionsJn> glTransaJnList = new ArrayList<GlTransactionsJn>();
		GlTransactionsJn targetObject;
		Integer result = 0;
		 GlTransactions glTransactionsOld=null;
		try {
		 glTransactionsOld = glTransactionsTjnRepository.getGlTransactions(glTransactions);
		}catch (Exception e) {
			logger.error(e);
		}
		try {
		if("INSERTING".equals(operationType)) {
			targetObject = new GlTransactionsJn();
			BeanUtils.copyProperties(glTransactions, targetObject);
			targetObject.setJnOperation("INS");
			dataMapper(glTransactions, targetObject);
			glTransaJnList = new ArrayList<GlTransactionsJn>();
			glTransaJnList.add(targetObject);
			result = glTransactionsTjnRepository.insertUpdateDelete(glTransaJnList);
		}
//		ELSIF UPDATING THEN
		else if("UPDATING".equals(operationType)) {
			targetObject = new GlTransactionsJn();
			BeanUtils.copyProperties(glTransactions, targetObject);
			targetObject.setJnOperation("UPD");
			dataMapper(glTransactionsOld, targetObject);
			glTransaJnList = new ArrayList<GlTransactionsJn>();
			glTransaJnList.add(targetObject);
			result = glTransactionsTjnRepository.insertUpdateDelete(glTransaJnList);
		}
//		ELSIF DELETING THEN
		else if("DELETING".equals(operationType)) {
			targetObject = new GlTransactionsJn();
			BeanUtils.copyProperties(glTransactions, targetObject);
			targetObject.setJnOperation("UPD");
			dataMapper(glTransactionsOld, targetObject);
			glTransaJnList = new ArrayList<GlTransactionsJn>();
			glTransaJnList.add(targetObject);
			result = glTransactionsTjnRepository.insertUpdateDelete(glTransaJnList);
		}
		}catch (final Exception e) {
			result = 0;
			logger.error("glTransactionsTjnTrigger", e);
		}
		return result;
	}

	private void dataMapper(final GlTransactions glTransactions, final GlTransactionsJn targetObject) {
		targetObject.setJnOracleUser(targetObject.getCreateUserId());
		targetObject.setJnDatetime(targetObject.getCreateDatetime());
		targetObject.setJnNotes(null);
		targetObject.setJnAppln(null);
		targetObject.setJnSession(new BigDecimal(0));
		targetObject.setCreateDatetime(glTransactions.getCreateDatetime());
		targetObject.setModifyDatetime(glTransactions.getCreateDatetime());
		targetObject.setCreateUserId(glTransactions.getCreateUserId());
	}

}
