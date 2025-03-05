package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.CommGlTransactions;
import net.syscon.s4.triggers.CommGlTransactionsT1Repository;
import net.syscon.s4.triggers.CommGlTransactionsT1Service;
import net.syscon.s4.triggers.CommissaryAudits;

@Service
public class CommGlTransactionsT1ServiceImpl implements CommGlTransactionsT1Service {
	private final Logger logger = LogManager.getLogger(CommGlTransactionsT1ServiceImpl.class);
	@Autowired
	CommGlTransactionsT1Repository commGlTransactionsT1Repository;

	@Override
	public Integer commGlTransactionsT1Tgr(final List<CommGlTransactions> commGlTransactionsList) {
		final CommissaryAudits commissaryAudits = new CommissaryAudits();
		Integer result = 0;
		try {
			if (!commGlTransactionsList.isEmpty()) {
				for (final CommGlTransactions newObj : commGlTransactionsList) {
					final CommGlTransactions old = commGlTransactionsT1Repository.getCommGlTransactions(newObj);
					if ((Optional.ofNullable(newObj).isPresent() && null == newObj.getSealFlag())
							|| (newObj.getSealFlag().equals(old.getSealFlag()))) {
						final String vProfileValue = commGlTransactionsT1Repository.vProfileValue();
						if ("Y".equals(vProfileValue)) {
//						 v_sessionid     NUMBER (12) := TO_NUMBER(USERENV('SESSIONID'));
							Long vSid = null;
							final Long vSessionid = null;
							final String vModuleName = commGlTransactionsT1Repository.vModuleName(vSessionid);
							if ("OODOSALE".equals(vModuleName) || "OODORETU".equals(vModuleName)) {
								vSid = vSessionid;
							}
							commissaryAudits.setSid(vSid);
							commissaryAudits.setTxnId(newObj.getTxnId().longValue());
							commissaryAudits.setModuleName(vModuleName);
							commissaryAudits.setCreateDatetime(newObj.getCreateDatetime());
							commissaryAudits.setCreateUserId(newObj.getCreateUserId());
							commissaryAudits.setModifyDatetime(newObj.getCreateDatetime());
							result = commGlTransactionsT1Repository.insertCommissaryAudits(commissaryAudits);
						}
					}
				}
			}
		} catch (final Exception e) {
			logger.error("commGlTransactionsT1Tgr", e);
		}
		return result;
	}
}
