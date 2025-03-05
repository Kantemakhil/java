package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OtmacprdRepository;

/**
 * Class OtmacprdRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OtmacprdRepositoryImpl extends RepositoryBase implements OtmacprdRepository {

	private static Logger logger = LogManager.getLogger(OtmacprdRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> glTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("VAL", 				new FieldMapper("")).build();
	
	private final Map<String, FieldMapper> accountPeriodsMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("ACCOUNT_PERIOD_ID", 			new FieldMapper("accountPeriodId"))
	.put("ACCOUNT_PERIOD_TYPE", 		new FieldMapper("accountPeriodType"))
	.put("CREATE_USER_ID",	 			new FieldMapper("createUserId"))
	.put("START_DATE", 					new FieldMapper("startDate"))
	.put("END_DATE", 					new FieldMapper("endDate"))
	.put("PARENT_ACCOUNT_PERIOD_ID", 	new FieldMapper("parentAccountPeriodId"))
	.put("MODIFY_DATE", 				new FieldMapper("modifyDate"))
	.put("LIST_SEQ", 					new FieldMapper("listSeq"))	
	.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
	.put("CREATE_DATETIME", 			new FieldMapper("createDateTime"))
	.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
	.put("MODIFY_USER_ID",	 			new FieldMapper("modifyUserId"))
	.build();
	
	/**
	 * Creates new OtmacprdRepositoryImpl class Object
	 */
	public OtmacprdRepositoryImpl() {
		//  OtmacprdRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AccountPeriods
	 *
	 * @return List<AccountPeriods>
	 *
	 * 
	 */
	public List<AccountPeriods> acPrdExecuteQuery(final AccountPeriods objSearchDao) {
		final String sql = getQuery("OTMACPRD_ACPRD_FIND_ACCOUNT_PERIODS");
		final RowMapper<AccountPeriods> accountPeriodsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AccountPeriods.class, accountPeriodsMapping);
		List<AccountPeriods> returnList = new ArrayList<AccountPeriods>();
		String preparedSql = null;
		String psql =null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
		if (objSearchDao.getAccountPeriodId() != null) {
			sqlQuery.append("ACCOUNT_PERIOD_ID = :ACCOUNT_PERIOD_ID" + " AND  ");
			inParameterSource.addValue("ACCOUNT_PERIOD_ID", objSearchDao.getAccountPeriodId());
		}
		if (objSearchDao.getAccountPeriodType() != null) {
			sqlQuery.append("ACCOUNT_PERIOD_TYPE = :ACCOUNT_PERIOD_TYPE" + " AND  ");
			inParameterSource.addValue("ACCOUNT_PERIOD_TYPE", objSearchDao.getAccountPeriodType());
		}
		if (objSearchDao.getStartDate() != null) {
			sqlQuery.append("START_DATE = :START_DATE" + " AND  ");
			inParameterSource.addValue("START_DATE", objSearchDao.getStartDate());
		}
		if (objSearchDao.getEndDate() != null) {
			sqlQuery.append("END_DATE = :END_DATE" + " AND  ");
			inParameterSource.addValue("END_DATE", objSearchDao.getEndDate());
		}
		if (objSearchDao.getParentAccountPeriodId() != null) {
			sqlQuery.append("PARENT_ACCOUNT_PERIOD_ID = :PARENT_ACCOUNT_PERIOD_ID" + " AND  ");
			inParameterSource.addValue("PARENT_ACCOUNT_PERIOD_ID", objSearchDao.getParentAccountPeriodId());
		}
		preparedSql = sqlQuery.toString().trim();
		}
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		psql = preparedSql.concat("  ORDER BY ACCOUNT_PERIOD_ID");
		
		
		 returnList =  namedParameterJdbcTemplate.query(psql,inParameterSource, accountPeriodsRowMapper);
		return returnList;
	}


	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAccountPeriods
	 *            List<AccountPeriods>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer acPrdInsertAccountPeriods(final List<AccountPeriods> lstAccountPeriods) {
		String sql = getQuery("OTMACPRD_ACPRD_INSERT_ACCOUNT_PERIODS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AccountPeriods accountPeriods : lstAccountPeriods) {
			parameters.add(new BeanPropertySqlParameterSource(accountPeriods));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAccountPeriods.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAccountPeriods
	 *            List<AccountPeriods>
	 *
	 * 
	 */
	public Integer acPrdUpdateAccountPeriods(final List<AccountPeriods> lstAccountPeriods) {
		String sql = getQuery("OTMACPRD_ACPRD_UPDATE_ACCOUNT_PERIODS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AccountPeriods accountPeriods : lstAccountPeriods) {
			parameters.add(new BeanPropertySqlParameterSource(accountPeriods));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAccountPeriods.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAccountPeriods
	 *            List<AccountPeriods>
	 *
	 * 
	 */
	public Integer acPrdDeleteAccountPeriods(final List<AccountPeriods> lstAccountPeriods) {
		String sql = getQuery("OTMACPRD_ACPRD_DELETE_ACCOUNT_PERIODS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (AccountPeriods accountPeriods : lstAccountPeriods) {
			parameters.add(new BeanPropertySqlParameterSource(accountPeriods));
		}
		try {
			String tableName = "ACCOUNT_PERIODS";
			String whereClause = "ACCOUNT_PERIOD_ID =:accountPeriodId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method acPrdDeleteAccountPeriods", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAccountPeriods.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkAccountPeriods
	 *
	 * @param params
	 *
	 */
	public List<GlTransactions> cgrichkAccountPeriods(GlTransactions paramBean) {
		final String sql = getQuery("OTMACPRD_CGRICHK_ACCOUNT_PERIODS");
		final RowMapper<GlTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, GlTransactions.class,
				glTransactionsMapping);
		final ArrayList<GlTransactions> returnList = (ArrayList<GlTransactions>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	public List<AccountPeriods> overlapdates() {
		final String sql = getQuery("OTMACPRD_OVERLAPDATES");
		final RowMapper<AccountPeriods> accountPeriodsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AccountPeriods.class, accountPeriodsMapping);
		List<AccountPeriods> returnList = new ArrayList<AccountPeriods>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(),accountPeriodsRowMapper);
		return returnList;
	}

	public Integer duplicateAccountperiodId(Long accountPeriodId) {
		final String sql = getQuery("OTMACPRD_DUPLICATE_VALUE");
		Integer periodExist = 0;
		try{
		periodExist = namedParameterJdbcTemplate.queryForObject(sql, createParams("accountPeriodId",accountPeriodId), Integer.class);
		} 
		catch (EmptyResultDataAccessException e) {
			periodExist = 0;
		} catch (NullPointerException e) {
			periodExist = 0;
		}
		return periodExist;
	}

	public Integer duplicateOverlapDate(final String overlapDate) {
		final String sql = getQuery("OTMACPRD_DUPLICATEOVERLAPDATE");
		Integer val =0;
		try {
		val= namedParameterJdbcTemplate.queryForObject(sql, createParams("overlapDate",overlapDate), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			val =0;
		}
		catch (NullPointerException e) {
			val =0;
		}
		return val;
	}


}
