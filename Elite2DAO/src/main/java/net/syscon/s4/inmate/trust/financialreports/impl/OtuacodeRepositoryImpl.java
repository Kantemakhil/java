package net.syscon.s4.inmate.trust.financialreports.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.trust.financialreports.OtuacodeRepository;
/**
 * Class OtuacodeRepositoryImpl
 */
@Repository
public class OtuacodeRepositoryImpl extends RepositoryBase implements OtuacodeRepository{
	
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OtuacodeRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REC_ACCOUNT_CODE", 			new FieldMapper("trustAccountCode"))
			.put("ACCOUNT_CODE", 			    new FieldMapper("accountCode"))
			.put("ACCOUNT_NAME", 				new FieldMapper("accountName"))
			.put("POSTING_STATUS_FLAG", 		new FieldMapper("postingStatusFlag"))
			.put("ACCOUNT_TYPE", 				new FieldMapper("accountType"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.put("ALL_CASELOAD_FLAG", 			new FieldMapper("allCaseloadFlag"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("CASELOAD_TYPE", 				new FieldMapper("caseloadType"))
			.put("PARENT_ACCOUNT_CODE", 		new FieldMapper("parentAccountCode"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.build();

	/**
	 * Creates new OtuacodeRepositoryImpl class Object
	 */
	public OtuacodeRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table method:acCodeExecuteQuery
	 * 
	 * @param objSearchDao
	 *            AccountCodes
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> acCodeExecuteQuery(final AccountCodes objSearchDao) {
		final String sql = getQuery("OTUACODE_ACCODE_FIND_ACCOUNT_CODES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao != null && objSearchDao.getAccountCode() != null) {
				sqlQuery.append(" ACCOUNT_CODE  = :accountCode  " + " and");
				params.addValue("accountCode", objSearchDao.getAccountCode());
			}
			if (("OTRCREGI".equals(objSearchDao.getAccountName())) || ("OTRCSTUB".equals(objSearchDao.getAccountName()))
					|| ("OTRBNRCS".equals(objSearchDao.getAccountName()))
					|| ("OTRBANK".equals(objSearchDao.getAccountName())
							|| ("OTRBNRCN".equals(objSearchDao.getAccountName())))
					|| ("YTRBANK".equals(objSearchDao.getAccountName()))
					|| ("YTRBANK".equals(objSearchDao.getAccountName()))
					|| ("YTRCREGI".equals(objSearchDao.getAccountName()))) {
				sqlQuery.append(
						" CASELOAD_TYPE = :CASELOADTYPE AND ACCOUNT_CODE IN (SELECT ACCOUNT_CODE FROM CASELOAD_CURRENT_ACCOUNTS_FN(:userId) WHERE BANK_ACCOUNT_TYPE IS NOT NULL AND CASELOAD_ID = :CASELOADID) ");
				params.addValue("CASELOADTYPE", objSearchDao.getCaseloadType());
				params.addValue("CASELOADID", objSearchDao.getCaseloadId());
				params.addValue("userId", objSearchDao.getCreateUserId());
			} else if ("OTRTPAYR".equals(objSearchDao.getAccountName())) {
				sqlQuery.append(
						" CASELOAD_TYPE = :CASELOADTYPE AND ACCOUNT_CODE IN (SELECT DISTINCT(DR_ACCOUNT_CODE) FROM TRANSACTION_OPERATIONS WHERE MODULE_NAME = 'OTDPAYRO') ");
				params.addValue("CASELOADTYPE", objSearchDao.getCaseloadType());
			} else {
				sqlQuery.append(" CASELOAD_TYPE = :CASELOADTYPE ");
				params.addValue("CASELOADTYPE", objSearchDao.getCaseloadType());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY ACCOUNT_CODE ");
		final RowMapper<AccountCodes> AccountCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		List<AccountCodes> returnList = new ArrayList<AccountCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, AccountCodesRowMapper);
		} catch (Exception e) {
			logger.error("acCodeExecuteQuery", e);
		}
		return returnList;
	}
}
