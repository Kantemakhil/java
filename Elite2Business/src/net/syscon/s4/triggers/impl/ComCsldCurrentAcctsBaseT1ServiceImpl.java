package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.ComCsldCurrentAccountsBase;
import net.syscon.s4.triggers.ComCsldCurrentAcctsBaseT1Repository;
import net.syscon.s4.triggers.ComCsldCurrentAcctsBaseT1Service;

@Service
public class ComCsldCurrentAcctsBaseT1ServiceImpl implements ComCsldCurrentAcctsBaseT1Service {
	private final Logger logger = LogManager.getLogger(ComCsldCurrentAcctsBaseT1ServiceImpl.class.getName());
	@Autowired
	ComCsldCurrentAcctsBaseT1Repository comCsldCurrentAcctsBaseT1Repository;

	@Override
	public Integer comCsldCurrentAcctsBaseT1Tgr(final List<ComCsldCurrentAccountsBase> comCsldCurrentAccountsBaseList) {
		final ComCsldCurrentAccountsBase targetObj = new ComCsldCurrentAccountsBase();
		Integer result = 0;
		if (!comCsldCurrentAccountsBaseList.isEmpty()) {
			try {
				for (final ComCsldCurrentAccountsBase newObj : comCsldCurrentAccountsBaseList) {
					final ComCsldCurrentAccountsBase old = comCsldCurrentAcctsBaseT1Repository
							.getComCsldCurrentAccountsBase(newObj);
					if (Optional.ofNullable(newObj.getBankAccountNumber()).isPresent()) {
						final String lBankChqBooksExistsCur = comCsldCurrentAcctsBaseT1Repository
								.lBankChqBooksExistsCur(newObj.getCaseloadId(), newObj.getAccountCode());
						if ("Y".equals(lBankChqBooksExistsCur)) {
							BeanUtils.copyProperties(newObj, targetObj);
							targetObj.setCaseloadId(old.getCaseloadId());
							targetObj.setAccountCode(old.getAccountCode());
							result = comCsldCurrentAcctsBaseT1Repository.update(targetObj);
						}
					}
				}
			} catch (final Exception e) {
				result = 0;
				logger.error("comCsldCurrentAcctsBaseT1Tgr", e);
			}
		}
		return result;
	}

}
