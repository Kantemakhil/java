package net.syscon.s4.inmate.trust.financialsmaintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmmbalaRepository;

/**
 * Class OtmmbalaRepositoryImpl
 */
@Repository
public class OtmmbalaRepositoryImpl extends RepositoryBase implements OtmmbalaRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmmbalaRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", 				new FieldMapper("accountName"))
			.put("TRUST_ACCOUNT_CODE", 			new FieldMapper("trustAccountCode"))
			.put("SUB_ACCOUNT_TYPE", 			new FieldMapper("subAccountType"))
			.build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_CODE", 			    new FieldMapper("accountCode"))
			.put("SUB_ACCOUNT_TYPE",		    new FieldMapper("subAccountType"))
			.put("ACCOUNT_NAME", 				new FieldMapper("accountName"))
			.build();

	/**
	 * Creates new OtmmbalaRepositoryImpl class Object
	 */
	public OtmmbalaRepositoryImpl() {
		// OtmmbalaRepositoryImpl
	}

	/**
	 * Fetch the records from database table offSubaExecuteQuery
	 * method:offSubaExecuteQuery
	 * 
	 * @param objSearchDao
	 * @return List<OffenderSubAccounts>
	 */
	public List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts objSearchDao) {
		final String sql = getQuery("OTMMBALA_OFFSUBA_FIND_OFFENDER_SUB_ACCOUNTS");
		final RowMapper<OffenderSubAccounts> OffenderSubAccountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, accountCodesMapping);
		List<OffenderSubAccounts> returnList = new ArrayList<OffenderSubAccounts>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERID", objSearchDao.getOffenderId(),
					"CASELOADID", objSearchDao.getCaseloadId()), OffenderSubAccountsRowMapper);
		} catch (Exception e) {

			logger.error("offSubaExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 * method:offSubaUpdateOffenderSubAccounts
	 * 
	 * @param lstOffenderSubAccounts
	 * @return Integer
	 */
	public Integer offSubaUpdateOffenderSubAccounts(final List<OffenderSubAccounts> lstOffenderSubAccounts) {
		String sql = getQuery("OTMMBALA_OFFSUBA_UPDATE_OFFENDER_SUB_ACCOUNTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderSubAccounts offenderSubAccounts : lstOffenderSubAccounts) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSubAccounts));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderSubAccounts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Used to capture results from select query
	 * method:cgfkOffSubaTrustAccountCoRecordGroup
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkOffSubaTrustAccountCoRecordGroup() {
		final String sql = getQuery("OTMMBALA_FIND_CGFKOFFSUBATRUSTACCOUNTCO");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);
		List<AccountCodes> returnList = new ArrayList<AccountCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {

			logger.error("cgfkOffSubaTrustAccountCoRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * method:cgfkchkOffSubaOffSubaAc
	 * 
	 * @param paramBean
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkchkOffSubaOffSubaAc(AccountCodes paramBean) {
		final String sql = getQuery("OTMMBALA_CGFKCHK_OFF_SUBA_OFF_SUBA_AC_");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		List<AccountCodes> returnList = new ArrayList<AccountCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("cgfkchkOffSubaOffSubaAc", e);
		}
		return returnList;
	}

}
