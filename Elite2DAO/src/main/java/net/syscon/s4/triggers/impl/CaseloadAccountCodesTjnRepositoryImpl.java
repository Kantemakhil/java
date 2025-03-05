package net.syscon.s4.triggers.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.CaseloadAccountCodesJn;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.CaseloadAccountCodesTjnRepository;

/**
 * Class CaseloadAccountCodesTjnRepositoryImpl
 * 
 */
@Repository
public class CaseloadAccountCodesTjnRepositoryImpl extends RepositoryBase implements CaseloadAccountCodesTjnRepository {

	private static Logger logger = LogManager.getLogger(CaseloadAccountCodesTjnRepositoryImpl.class);

	@Override
	public Integer insertCaseloadAccountCodesJn(final CaseloadAccountCodesJn caseloadAccountCodes,final String userName) {
		final String sql = getQuery("INSERT_CASELOAD_ACCOUNT_CODES_JN");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		
		inParamMap.put("USERNAME", userName);
		inParamMap.put("CASELOAD_ID", caseloadAccountCodes.getCaseloadId());
		inParamMap.put("ACCOUNT_CODE", caseloadAccountCodes.getAccountCode());
		inParamMap.put("LIST_SEQ", caseloadAccountCodes.getListSeq());
		inParamMap.put("MODIFY_DATE", caseloadAccountCodes.getModifyDate());
		inParamMap.put("MODIFY_USER_ID", caseloadAccountCodes.getModifyUserId());
		inParamMap.put("CREATE_USER_ID", caseloadAccountCodes.getCreateUserId());
		inParamMap.put("CREATE_DATETIME", caseloadAccountCodes.getCreateDatetime());
		inParamMap.put("MODIFY_DATETIME", caseloadAccountCodes.getModifyDatetime());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertCaseloadAccountCodesJn", e);
		}
		return retVal;
	}

}


