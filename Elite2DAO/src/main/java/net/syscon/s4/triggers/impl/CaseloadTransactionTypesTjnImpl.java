package net.syscon.s4.triggers.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.CaseloadAccountCodesJn;
import net.syscon.s4.common.beans.CaseloadTransactionTypesjn;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.CaseloadAccountCodesTjnRepository;
import net.syscon.s4.triggers.CaseloadTransactionTypesTjnRepository;

/**
 * Class CaseloadTransactionTypesTjnImpl
 * 
 */
@Repository
public class CaseloadTransactionTypesTjnImpl extends RepositoryBase implements CaseloadTransactionTypesTjnRepository {

	private static Logger logger = LogManager.getLogger(CaseloadTransactionTypesTjnImpl.class);

	@Override
	public Integer insertCaseloadTransactionTypesJn(final CaseloadTransactionTypesjn caseloadTransactionTypesjn,final String userName) {
		final String sql = getQuery("INSERT_CASELOAD_TRANSACTION_TYPES_JN");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		
		inParamMap.put("USERNAME", userName);
		inParamMap.put("CASELOAD_ID", caseloadTransactionTypesjn.getCaseloadId());
		inParamMap.put("ACCOUNT_CODE", caseloadTransactionTypesjn.getAccountCode());
		inParamMap.put("LIST_SEQ", caseloadTransactionTypesjn.getListSeq());
		inParamMap.put("MODIFY_DATE", caseloadTransactionTypesjn.getModifyDate());
		inParamMap.put("MODIFY_USER_ID", caseloadTransactionTypesjn.getModifyUserId());
		inParamMap.put("CREATE_USER_ID", caseloadTransactionTypesjn.getCreateUserId());
		inParamMap.put("CREATE_DATETIME", caseloadTransactionTypesjn.getCreateDatetime());
		inParamMap.put("MODIFY_DATETIME", caseloadTransactionTypesjn.getModifyDatetime());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertCaseloadAccountCodesJn", e);
		}
		return retVal;
	}

}


