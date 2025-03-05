package net.syscon.s4.inmate.trust.financialsmaintenance.subaccounts.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InstitutionMiniBalances;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.trust.financialsmaintenance.subaccounts.OtmisambRepository;

/**
 * Class OtmisambRepositoryImpl
 * 
 */
@Repository
public class OtmisambRepositoryImpl extends RepositoryBase implements OtmisambRepository {

	private static Logger logger = LogManager.getLogger(OtmisambRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();
	private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_NAME", new FieldMapper("accountName")).put("ACCOUNT_CODE", new FieldMapper("accountCode"))
			.put("SUB_ACCOUNT_TYPE", new FieldMapper("subAccountType")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).put("ACCOUNT_CODE", new FieldMapper("accountCode"))
			.put("SUB_ACCOUNT_TYPE", new FieldMapper("subAccountType")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("ACCOUNT_NAME", new FieldMapper("accountName"))
			.build();
	private final Map<String, FieldMapper> caseloadAgencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("CASELOAD_ID", new FieldMapper("caseloadId")).build();

	/**
	 * Creates new OtmisambRepositoryImpl class Object
	 */
	public OtmisambRepositoryImpl() {
		// OtmisambRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            InstitutionMiniBalances
	 *
	 * @return List<InstitutionMiniBalances>
	 *
	 * 
	 */
	public List<InstitutionMiniBalances> instMnbalExecuteQuery(final InstitutionMiniBalances objSearchDao) {
		final String sql = getQuery("OTMISAMB_INSTMNBAL_FIND_INSTITUTION_MINI_BALANCES");
		final RowMapper<InstitutionMiniBalances> InstitutionMiniBalancesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InstitutionMiniBalances.class, caseloadsMapping);
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getCaseloadId() != null && !objSearchDao.getCaseloadId().isEmpty()) {
				pSql.append(" CASELOAD_ID = :caseloadId  AND ");
				param.addValue("caseloadId", objSearchDao.getCaseloadId());
			}
			if (objSearchDao.getAgyLocId() != null && !objSearchDao.getAgyLocId().isEmpty()) {
				pSql.append(" AGY_LOC_ID = :agyLocId  AND ");
				param.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getAccountCode() != null) {
				pSql.append(" ACCOUNT_CODE = :accountCode  AND ");
				param.addValue("accountCode", objSearchDao.getAccountCode());
			}
			preparedSql = pSql.toString().trim();
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
			}
			preparedSql = preparedSql + " ORDER BY CASELOAD_ID, AGY_LOC_ID, ACCOUNT_CODE";
		}
		final List<InstitutionMiniBalances> returnList = namedParameterJdbcTemplate.query(preparedSql, param,
				InstitutionMiniBalancesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstInstitutionMiniBalances
	 *            List<InstitutionMiniBalances>
	 *
	 * @return List<Integer>
	 *
	 */

	public Integer instMnbalInsertInstitutionMiniBalances(
			final List<InstitutionMiniBalances> lstInstitutionMiniBalances) {

		String sql = getQuery("OTMISAMB_INSTMNBAL_INSERT_INSTITUTION_MINI_BALANCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (InstitutionMiniBalances institutionMiniBalances : lstInstitutionMiniBalances) {
			parameters.add(new BeanPropertySqlParameterSource(institutionMiniBalances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInstitutionMiniBalances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstInstitutionMiniBalances
	 *            List<InstitutionMiniBalances>
	 *
	 * 
	 */
	public Integer instMnbalUpdateInstitutionMiniBalances(
			final List<InstitutionMiniBalances> lstInstitutionMiniBalances) {

		String sql = getQuery("OTMISAMB_INSTMNBAL_UPDATE_INSTITUTION_MINI_BALANCES");

		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (InstitutionMiniBalances institutionMiniBalances : lstInstitutionMiniBalances) {
			parameters.add(new BeanPropertySqlParameterSource(institutionMiniBalances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInstitutionMiniBalances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstInstitutionMiniBalances
	 *            List<InstitutionMiniBalances>
	 *
	 */
	public Integer instMnbalDeleteInstitutionMiniBalances(
			final List<InstitutionMiniBalances> lstInstitutionMiniBalances) {
		String sql = getQuery("OTMISAMB_INSTMNBAL_DELETE_INSTITUTION_MINI_BALANCES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (InstitutionMiniBalances institutionMiniBalances : lstInstitutionMiniBalances) {
			parameters.add(new BeanPropertySqlParameterSource(institutionMiniBalances));
		}
		try {
			String tableName = "INSTITUTION_MINI_BALANCES";
			String whereClause = "CASELOAD_ID = :caseloadId AND AGY_LOC_ID = :agyLocId AND ACCOUNT_CODE = :accountCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method instMnbalDeleteInstitutionMiniBalances", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInstitutionMiniBalances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> cgfkInstMnbalCaseloadIdRecordGroup() {
		final String sql = getQuery("OTMISAMB_FIND_CGFKINSTMNBALCASELOADID");
		final RowMapper<Caseloads> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, mMapping);
		List<Caseloads> returnList = new ArrayList<>();
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkInstMnbalCaseloadIdRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AccountCodes>
	 */
	public List<AccountCodes> cgfkInstMnbalAccountCodeRecordGroup() {
		final String sql = getQuery("OTMISAMB_FIND_CGFKINSTMNBALACCOUNTCODE");
		List<AccountCodes> returnList = new ArrayList<AccountCodes>();
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			returnList=namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
			for (AccountCodes accountCodes : returnList) {
				accountCodes.setListSeq(new BigDecimal(accountCodes.getCode()));
			}
		} catch (Exception e) {
			logger.error("cgfkInstMnbalAccountCodeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<CaseloadAgencyLocations>
	 */
	public List<CaseloadAgencyLocations> cgfkInstMnbalAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OTMISAMB_FIND_CGFKINSTMNBALAGYLOCID");
		List<CaseloadAgencyLocations> returnList = new ArrayList<CaseloadAgencyLocations>();
		final RowMapper<CaseloadAgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkInstMnbalAgyLocIdRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkInstMnbalInstMbCs
	 *
	 * @param params
	 *
	 */
	public List<Caseloads> cgfkchkInstMnbalInstMbCs(final Caseloads paramBean) {
		final String sql = getQuery("OTMISAMB_CGFKCHK_INST_MNBAL_INST_MB_CS");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		final List<Caseloads> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkInstMnbalInstMb2
	 *
	 * @param params
	 *
	 */
	public List<CaseloadAgencyLocations> cgfkchkInstMnbalInstMb2(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OTMISAMB_CGFKCHK_INST_MNBAL_INST_MB2");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadAgencyLocationsMapping);
		final List<CaseloadAgencyLocations> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkInstMnbalInstMbAc
	 *
	 * @param params
	 *
	 */
	public List<AccountCodes> cgfkchkInstMnbalInstMbAc(final AccountCodes paramBean) {
		final String sql = getQuery("OTMISAMB_CGFKCHK_INST_MNBAL_INST_MB_AC");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final List<AccountCodes> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

}
