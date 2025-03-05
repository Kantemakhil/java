package net.syscon.s4.inmate.trust.financialsmaintenance.transaction.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.beans.CaseloadAccountPeriods;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.trust.financialsmaintenance.transaction.OtmoncoaRepository;
/**
 * Class OtmoncoaRepositoryImpl
 */
@Repository
public class OtmoncoaRepositoryImpl extends RepositoryBase implements OtmoncoaRepository{

	private static Logger logger = LogManager.getLogger(OtmoncoaRepositoryImpl.class.getName());
	
private final Map<String, FieldMapper> caseloadsAccPrdMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ROW_NUM",								new FieldMapper("rowNum"))
.put("OFFENDER_ID",							new FieldMapper("offenderId"))
.put("OFFENDER_NAME_SEQ",					new FieldMapper("offenderNameSeq"))
.put("ID_SOURCE_CODE",						new FieldMapper("idSourceCode"))
.put("LAST_NAME",							new FieldMapper("lastName"))
.put("NAME_TYPE",							new FieldMapper("nameType"))
.put("FIRST_NAME",							new FieldMapper("firstName"))
.put("MIDDLE_NAME",							new FieldMapper("middleName"))
.put("BIRTH_DATE",							new FieldMapper("birthDate"))
.put("SEX_CODE",							new FieldMapper("sexCode"))
.put("SUFFIX",								new FieldMapper("suffix"))
.put("LAST_NAME_SOUNDEX",					new FieldMapper("lastNameSoundex"))
.put("BIRTH_PLACE",							new FieldMapper("birthPlace"))
.put("BIRTH_COUNTRY_CODE",					new FieldMapper("birthCountryCode"))
.put("CREATE_DATE",							new FieldMapper("createDate"))
.put("LAST_NAME_KEY",						new FieldMapper("lastNameKey"))
.put("ALIAS_OFFENDER_ID",					new FieldMapper("aliasOffenderId"))
.put("FIRST_NAME_KEY",						new FieldMapper("firstNameKey"))
.put("MIDDLE_NAME_KEY",						new FieldMapper("middleNameKey"))
.put("OFFENDER_ID_DISPLAY",					new FieldMapper("offenderIdDisplay"))
.put("ROOT_OFFENDER_ID",					new FieldMapper("rootOffenderId"))
.put("CASELOAD_TYPE",						new FieldMapper("caseloadType"))
.put("MODIFY_USER_ID",						new FieldMapper("modifyUserId"))
.put("MODIFY_DATETIME",						new FieldMapper("modifyDatetime"))
.put("ALIAS_NAME_TYPE",						new FieldMapper("aliasNameType"))
.put("PARENT_OFFENDER_ID",					new FieldMapper("parentOffenderId"))
.put("UNIQUE_OBLIGATION_FLAG",				new FieldMapper("uniqueObligationFlag"))
.put("SUSPENDED_FLAG",						new FieldMapper("suspendedFlag"))
.put("SUSPENDED_DATE",						new FieldMapper("suspendedDate"))
.put("RACE_CODE",							new FieldMapper("raceCode"))
.put("REMARK_CODE",							new FieldMapper("remarkCode"))
.put("ADD_INFO_CODE",						new FieldMapper("addInfoCode"))
.put("BIRTH_COUNTY",						new FieldMapper("birthCounty"))
.put("BIRTH_STATE",							new FieldMapper("birthState"))
.put("MIDDLE_NAME_2",						new FieldMapper("middleName2"))
.put("TITLE",								new FieldMapper("title"))
.put("AGE",									new FieldMapper("age"))
.put("CREATE_USER_ID",						new FieldMapper("createUserId"))
.put("LAST_NAME_ALPHA_KEY",					new FieldMapper("lastNameAlphaKey"))
.put("CREATE_DATETIME",						new FieldMapper("createDatetime"))
.put("NAME_SEQUENCE",						new FieldMapper("nameSequence"))
.put("SEAL_FLAG",							new FieldMapper("sealFlag"))
			.build();

private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LIST_SEQ", 				new FieldMapper("listSeq"))
.put("DESCRIPTION", 			new FieldMapper("description"))
.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 			new FieldMapper("description"))
.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
.put("CODE", 					new FieldMapper("code"))
.put("LIST_SEQ", 				new FieldMapper("listSeq"))
.build();

	/**
	 * Creates new OtmoncoaRepositoryImpl class Object
	 */
	public OtmoncoaRepositoryImpl() {
		// OtmoncoaRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadAccountPeriods
	 *
	 * @return List<CaseloadAccountPeriods>
	 *
	 * @throws SQLException
	 */
	public List<CaseloadAccountPeriods> csldApExecuteQuery(CaseloadAccountPeriods objSearchDao) {
		final String sql = getQuery("OTMONCOA_CSLDAP_FIND_CASELOAD_ACCOUNT_PERIODS");
		String paramSql = sql;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		if (objSearchDao.getFromRowNum().compareTo(objSearchDao.getToRowNum()) > 0) {
			params.addValue("fromRowNum", objSearchDao.getToRowNum());
			params.addValue("toRowNum", objSearchDao.getFromRowNum());

		} else {
			params.addValue("toRowNum", objSearchDao.getToRowNum());
			params.addValue("fromRowNum", objSearchDao.getFromRowNum());

		}
		if (objSearchDao.getCaseloadId() != null && !objSearchDao.getCaseloadId().isEmpty()) {
			paramSql = paramSql.replace("#param", " WHERE CASELOAD_ID = :caseloadId ");
			params.addValue("caseloadId", objSearchDao.getCaseloadId());
		} else {
			paramSql = paramSql.replace("#param", " ");
		}

		final RowMapper<CaseloadAccountPeriods> CaseloadAccountPeriodsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAccountPeriods.class, caseloadsAccPrdMapping);
		final List<CaseloadAccountPeriods> returnList = namedParameterJdbcTemplate.query(paramSql, params,
				CaseloadAccountPeriodsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadAccountPeriods
	 *            List<CaseloadAccountPeriods>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer csldApInsertCaseloadAccountPeriods(final List<CaseloadAccountPeriods> lstCaseloadAccountPeriods) {
		int insertCount = 0;
		String sql = getQuery("OTMONCOA_CSLDAP_INSERT_CASELOAD_ACCOUNT_PERIODS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount++;
		}
		if (lstCaseloadAccountPeriods.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCaseloadAccountPeriods
	 *            List<CaseloadAccountPeriods>
	 *
	 * @throws SQLException
	 */
	public Integer csldApDeleteCaseloadAccountPeriods(final List<CaseloadAccountPeriods> lstCaseloadAccountPeriods) {
		String sql = getQuery("OTMONCOA_CSLDAP_DELETE_CASELOAD_ACCOUNT_PERIODS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseloadAccountPeriods caseloadAccountPeriods : lstCaseloadAccountPeriods) {
			parameters.add(new BeanPropertySqlParameterSource(caseloadAccountPeriods));
		}
		try {
			String tableName = "CASELOAD_ACCOUNT_PERIODS";
			String whereClause = "ACCOUNT_PERIOD_ID = :accountPeriodId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldApDeleteCaseloadAccountPeriods", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloadAccountPeriods.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Caseloads> cgfkCsldApCaseloadIdRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMONCOA_FIND_CGFKCSLDAPCASELOADID");
		final RowMapper<Caseloads> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldApCsldApCsld
	 *
	 * @param params
	 *
	 */
	public List<Caseloads> cgfkchkCsldApCsldApCsld(Caseloads paramBean) {
		final String sql = getQuery("OTMONCOA_CGFKCHK_CSLD_AP_CSLD_AP_CSLD_");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		final ArrayList<Caseloads> returnList = (ArrayList<Caseloads>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(SysDual paramBean) {
		final String sql = getQuery("OTMONCOA_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object.class, caseloadsMapping);
		final List<Object> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public BigDecimal getTotalCount(final String caseloadId) {
		final String sql = getQuery("OTMONCOA_GET_TOTAL_COUNT");
		String paramSql = sql;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		if (caseloadId != null && !caseloadId.isEmpty()) {
			paramSql = paramSql.replace("#param", " WHERE CASELOAD_ID = :caseloadId ");
			params.addValue("caseloadId", caseloadId);
		} else {
			paramSql = paramSql.replace("#param", " ");
		}
		return namedParameterJdbcTemplate.queryForObject(paramSql, params, BigDecimal.class);
	}

	@Override
	public void otmoncoaGenAccountCodes(String caseloadId) {
		final String sql = getQuery("OTMONCOA_OTMONCOA_GEN_ACCOUNT_CODES");
		namedParameterJdbcTemplate.update(sql, createParams("P_CASELOAD_ID", caseloadId, "P_ACCOUNT_CODE", null));
	}

}
