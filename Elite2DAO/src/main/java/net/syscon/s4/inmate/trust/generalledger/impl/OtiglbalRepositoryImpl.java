package net.syscon.s4.inmate.trust.generalledger.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.inmate.trust.generalledger.OtiglbalRepository;

/**
 * Class OtiglbalRepositoryImpl
 */
@Repository
public class OtiglbalRepositoryImpl extends RepositoryBase implements OtiglbalRepository {

	private final Map<String, FieldMapper> accCodesMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 				new FieldMapper("accountName"))
			.put("TXN_POSTING_TYPE", 			new FieldMapper("txnPostingType"))
			.put("ACCOUNT_TYPE", 				new FieldMapper("accountType"))
			.put("ACCOUNT_CODE", 				new FieldMapper("accountCode"))
			.build();
	private final Map<String, FieldMapper> caseloadCurAccMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BANK_ACCOUNT_TYPE", 			new FieldMapper("bankAccountType"))
			.put("ACCOUNT_PARTY_TYPE", 			new FieldMapper("accountPartyType"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("CURRENT_BALANCE", 			new FieldMapper("currentBalance"))
			.put("ROUTING_NUMBER", 				new FieldMapper("routingNumber"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
			.put("BANK_ACCOUNT_NUMBER", 		new FieldMapper("bankAccountNumber"))
			.put("ACCOUNT_CODE", 				new FieldMapper("accountCode"))
			.put("ACCOUNT_PERIOD_ID", 			new FieldMapper("accountPeriodId"))
			.put("PAYEE_PERSON_ID", 			new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID", 			new FieldMapper("payeeCorporateId"))
			.build();

	/**
	 * Creates new OtiglbalRepositoryImpl class Object
	 */
	public OtiglbalRepositoryImpl() {
		// OtiglbalRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadCurrentAccounts
	 *
	 * @return List<CaseloadCurrentAccounts>
	 *
	 */
	public List<CaseloadCurrentAccounts> csldCaExecuteQuery(final CaseloadCurrentAccounts objSearchDao) {
		final String sql = getQuery("OTIGLBAL_CSLDCA_FIND_CASELOAD_CURRENT_ACCOUNTS_FN");
		final RowMapper<CaseloadCurrentAccounts> caseloadAccRM = Row2BeanRowMapper.makeMapping(sql,
				CaseloadCurrentAccounts.class, caseloadCurAccMap);
		String preparedSql = null;
		String preSqlQuery = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		List<CaseloadCurrentAccounts> lstCaseloads = new ArrayList<CaseloadCurrentAccounts>();
		if (objSearchDao != null) {
			if (objSearchDao.getGlobalCaseloadType() != null && objSearchDao.getCaseloadId() != null) {
				sqlQuery.append(sql);
				sqlQuery.append(" where ");
				sqlQuery.append(" CASELOAD_ID = :caseloadId AND ACCOUNT_CODE "
						+ "  IN (SELECT ACCOUNT_CODE FROM ACCOUNT_CODES "
						+ " WHERE POSTING_STATUS_FLAG = 'Y' AND CASELOAD_TYPE = :globalCaseloadType ) " + " and");

				valuesList.addValue("caseloadId", objSearchDao.getCaseloadId());
				valuesList.addValue("globalCaseloadType", objSearchDao.getGlobalCaseloadType());
				valuesList.addValue("userId", objSearchDao.getCreateUserId());
				if (objSearchDao.getAccountPartyType() != null || objSearchDao.getDspTxnPostingType() != null
						|| objSearchDao.getAccountCode() != null) {
					sqlQuery.append("  ( (ACCOUNT_CODE) IN  (SELECT ACCOUNT_CODE FROM ACCOUNT_CODES WHERE ");

					if (objSearchDao.getAccountPartyType() != null) {
						sqlQuery.append(" (ACCOUNT_TYPE LIKE :accountType ) " + " and");
						valuesList.addValue("accountType", objSearchDao.getAccountPartyType());
					}
					if (objSearchDao.getDspTxnPostingType() != null) {
						sqlQuery.append(" (TXN_POSTING_TYPE LIKE :postingType ) " + " and");
						valuesList.addValue("postingType", objSearchDao.getDspTxnPostingType());
					}
					if (objSearchDao.getAccountCode() != null) {
						sqlQuery.append(" (ACCOUNT_CODE LIKE :accountCode ) " + " and");
						valuesList.addValue("accountCode", objSearchDao.getAccountCode());
					}

					preparedSql = sqlQuery.toString().trim();
					if (preparedSql.endsWith("where")) {
						preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
					}
					if (preparedSql.endsWith("and")) {
						preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
					}
					preparedSql = preparedSql.concat(")) ");
				} else {
					preparedSql = sqlQuery.toString().trim();
					if (preparedSql.endsWith("where")) {
						preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
					}
					if (preparedSql.endsWith("and")) {
						preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
					}
				}

				preSqlQuery = preparedSql.concat(" ORDER BY ACCOUNT_CODE ");
				lstCaseloads = (List<CaseloadCurrentAccounts>) namedParameterJdbcTemplate.query(preSqlQuery, valuesList,
						caseloadAccRM);
			}
		}
		return lstCaseloads;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldCaCsldAcAcCo
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkCsldCaCsldAcAcCo(final BigDecimal accountCode) {
		final String sql = getQuery("OTIGLBAL_CGFKCHK_CSLD_CA_CSLD_AC_AC_CO");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accCodesMap);
		final AccountCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ACCOUNTCODE", accountCode), columnRowMapper);
		return returnObj;
	}

}
