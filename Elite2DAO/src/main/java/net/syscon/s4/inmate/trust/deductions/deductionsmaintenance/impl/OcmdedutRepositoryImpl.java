package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OcmdedutRepository;

/**
 * Class OcmdedutRepositoryImpl
 */
@Repository
public class OcmdedutRepositoryImpl extends RepositoryBase implements OcmdedutRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmdedutRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", 						new FieldMapper("  '1' "))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 						new FieldMapper("code"))
			.put("FROM_BALANCE_TYPE", 			new FieldMapper("fromBalanceType"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("CASELOAD_CODE", 				new FieldMapper("caseloadCode"))
			.put("DEDUCTION_CATEGORY", 			new FieldMapper("deductionCategory"))
			.build();
	private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'1'", 						new FieldMapper("  '1' "))
			.build();
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 				new FieldMapper("deductionType"))
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> referenceCodesBalanceTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COD", 						new FieldMapper("cod"))
			.build();

	/**
	 * Creates new OcmdedutRepositoryImpl class Object
	 */
	public OcmdedutRepositoryImpl() {
		// OcmdedutRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            DeductionTypes
	 *
	 * @return List<DeductionTypes>
	 */
	public List<DeductionTypes> dedTypeExecuteQuery(final DeductionTypes objSearchDao) {
		final String sql = getQuery("OCMDEDUT_DEDTYPE_FIND_DEDUCTION_TYPES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getDeductionCategory() != null
					&& !("").equals(objSearchDao.getDeductionCategory())) {
				sqlQuery.append(" DEDUCTION_CATEGORY  = :deductionCategory  " + " and");
				params.addValue("deductionCategory", objSearchDao.getDeductionCategory());
			}
			if (objSearchDao != null && objSearchDao.getFromBalanceType() != null
					&& !("").equals(objSearchDao.getFromBalanceType())) {
				sqlQuery.append(" FROM_BALANCE_TYPE  LIKE :fromBalanceType  " + " and");
				params.addValue("fromBalanceType", objSearchDao.getFromBalanceType());
			}
			if (objSearchDao != null && objSearchDao.getDeductionType() != null
					&& !("").equals(objSearchDao.getDeductionType())) {
				sqlQuery.append(" DEDUCTION_TYPE  LIKE :deductionType  " + " and");
				params.addValue("deductionType", objSearchDao.getDeductionType());
			}
			if (objSearchDao != null && objSearchDao.getDeductionDesc() != null
					&& !("").equals(objSearchDao.getDeductionDesc())) {
				sqlQuery.append(" DEDUCTION_DESC  LIKE :deductionDesc  " + " and ");
				params.addValue("deductionDesc", objSearchDao.getDeductionDesc());
			}
			if (objSearchDao != null && objSearchDao.getCaseloadCode() != null
					&& !("").equals(objSearchDao.getCaseloadCode())) {
				sqlQuery.append(" CASELOAD_CODE  LIKE :caseloadCode  " + " and");
				params.addValue("caseloadCode", objSearchDao.getCaseloadCode());
			}
			if (objSearchDao != null && objSearchDao.getCaseloadRestrictedFlag() != null
					&& !("").equals(objSearchDao.getCaseloadRestrictedFlag())) {
				sqlQuery.append("  CASELOAD_RESTRICTED_FLAG  = :caseloadRestrictedFlag " + " and");
				params.addValue("caseloadRestrictedFlag", objSearchDao.getCaseloadRestrictedFlag());
			}
			if (objSearchDao != null && objSearchDao.getActiveFlag() != null
					&& !("").equals(objSearchDao.getActiveFlag())) {
				sqlQuery.append("  ACTIVE_FLAG  = :activeFlag " + " and");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao != null && objSearchDao.getListSeq() != null && !("").equals(objSearchDao.getListSeq())) {
				sqlQuery.append(" LIST_SEQ  =:listSeq " + " and ");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao != null && objSearchDao.getUpdateAllowedFlag() != null
					&& !("").equals(objSearchDao.getUpdateAllowedFlag())) {
				sqlQuery.append("  UPDATE_ALLOWED_FLAG  = :updateAllowedFlag " + " and");
				params.addValue("updateAllowedFlag", objSearchDao.getUpdateAllowedFlag());
			}
			if (objSearchDao != null && objSearchDao.getExpiryDate() != null
					&& !("").equals(objSearchDao.getExpiryDate())) {
				sqlQuery.append("  trunc(EXPIRY_DATE)   = trunc(:expiryDate)" + " and");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
			if (objSearchDao != null && objSearchDao.getParentDeductionType() != null
					&& !("").equals(objSearchDao.getParentDeductionType())) {
				sqlQuery.append(" PARENT_DEDUCTION_TYPE  LIKE :parentDeductionType  " + " and");
				params.addValue("parentDeductionType", objSearchDao.getParentDeductionType() + "%");
			}
			if (objSearchDao != null && objSearchDao.getPercentageOfParent() != null
					&& !("").equals(objSearchDao.getPercentageOfParent())) {
				sqlQuery.append(" PERCENTAGE_OF_PARENT  LIKE :percentageOfParent  " + " and");
				params.addValue("percentageOfParent", objSearchDao.getPercentageOfParent() + "%");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY LIST_SEQ, DEDUCTION_TYPE ");
		final RowMapper<DeductionTypes> DeductionTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				DeductionTypes.class, deductionTypesMapping);
		List<DeductionTypes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, DeductionTypesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstDeductionTypes
	 *            List<DeductionTypes>
	 *
	 * @return List<Integer>
	 */
	public Integer dedTypeInsertDeductionTypes(final List<DeductionTypes> lstDeductionTypes) {
		String sql = getQuery("OCMDEDUT_DEDTYPE_INSERT_DEDUCTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (DeductionTypes deductionTypes : lstDeductionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(deductionTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstDeductionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstDeductionTypes
	 *            List<DeductionTypes>
	 */
	public Integer dedTypeUpdateDeductionTypes(final List<DeductionTypes> lstDeductionTypes) {
		String sql = getQuery("OCMDEDUT_DEDTYPE_UPDATE_DEDUCTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (DeductionTypes deductionTypes : lstDeductionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(deductionTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstDeductionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstDeductionTypes
	 *            List<DeductionTypes>
	 */
	public Integer dedTypeDeleteDeductionTypes(final List<DeductionTypes> lstDeductionTypes) {
		String sql = getQuery("OCMDEDUT_DEDTYPE_DELETE_DEDUCTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (DeductionTypes deductionTypes : lstDeductionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(deductionTypes));
		}
		try {
			String tableName = "DEDUCTION_TYPES";
			String whereClause = "DEDUCTION_TYPE = :deductionType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method dedTypeDeleteDeductionTypes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstDeductionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkDedTypeCaseloadCodeRecordGroup() {
		final String sql = getQuery("OCMDEDUT_FIND_CGFKDEDTYPECASELOADCODE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error("cgfkDedTypeCaseloadCodeRecordGroup :" + e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkDedTypeDeductionCategoRecordGroup() {
		final String sql = getQuery("OCMDEDUT_FIND_CGFKDEDTYPEDEDUCTIONCATEGO");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error("cgfkDedTypeDeductionCategoRecordGroup :" + e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 *
	 * @return List<ReferenceCodesBalanceType>
	 */
	public List<ReferenceCodes> cgfkDedTypeFromBalanceTypRecordGroup() {
		final String sql = getQuery("OCMDEDUT_FIND_CGFKDEDTYPEFROMBALANCETYP");
		final RowMapper<ReferenceCodes> referenceCodesBalanceTypeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesBalanceTypeMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesBalanceTypeRowMapper);
		} catch (Exception e) {
			logger.error("cgfkDedTypeDeductionCategoRecordGroup :" + e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<DeductionTypes>
	 */
	public List<DeductionTypes> rgParentDeductionTypeRecordGroup(final String deductionType) {
		final String sql = getQuery("OCMDEDUT_FIND_RGPARENTDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> deductionTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				DeductionTypes.class, deductionTypesMapping);
		List<DeductionTypes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("DEDUCTIONTYPE", deductionType),
					deductionTypesRowMapper);
		} catch (Exception e) {
			logger.error("rgParentDeductionTypeRecordGroup :" + e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return List<TransactionTypes>
	 * 
	 * @param paramBean
	 */
	public List<ReferenceCodes> cgfkchkDedTypeDedTypeRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMDEDUT_CGFKCHK_DED_TYPE_DED_TYPE_REF");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return List<TransactionTypes>
	 *
	 * @param paramBean
	 */
	public List<ReferenceCodes> cgfkchkDedTypeDedType(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMDEDUT_CGFKCHK_DED_TYPE_DED_TYPE_BALANCE_TYPE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @return List<TransactionTypes>
	 *
	 * @param paramBean
	 */
	public List<ReferenceCodes> cgfkchkDedTypeDedTypededutCat(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCMDEDUT_CGFKCHK_DED_TYPE_DED_TYPE_DEDUCT_CAT");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<TransactionTypes>
	 * 
	 * @param paramBean
	 */
	public List<TransactionTypes> cgrichkDeductionTypes(final TransactionTypes paramBean) {
		final String sql = getQuery("OCMDEDUT_CGRICHK_DEDUCTION_TYPES");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		List<TransactionTypes> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<TransactionTypes>
	 * 
	 * @param paramBean
	 *
	 */
	public List<OffenderDeductions> cgrichkDeductionTypes(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCMDEDUT_CGRICHK_DEDUCTION_TYPES");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		List<OffenderDeductions> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	public String dedCodeValidation(final String dedCode) {
		final String sql = getQuery("OCMDEDUT_DED_CODE_VALIDATION");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDCODE", dedCode), String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer caseloadDeductionProfiles(String deductionType) {
		final String sql = getQuery("OCMDEDUT_CASELOAD_DEDUCTION_PROFILES");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDCODE", deductionType),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer deleteTransactionTypes(String deductionType) {
		final String sql = getQuery("OCMDEDUT_TRANSACTION_TYPES");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDCODE", deductionType),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer commDeleteTransactionTypes(String deductionType) {
		final String sql = getQuery("OCMDEDUT_COMM_TRANSACTION_TYPES");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDCODE", deductionType),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer deleteDeductionLimitTypes(String deductionType) {
		final String sql = getQuery("OCMDEDUT_DEDUCTION_LIMIT_TYPES");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDCODE", deductionType),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer deleteOffenderSentObligations(String deductionType) {
		final String sql = getQuery("OCMDEDUT_OFFENDER_SENT_OBLIGATIONS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDCODE", deductionType),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public Integer deleteOffenderDeductions(String deductionType) {
		final String sql = getQuery("OCMDEDUT_OFFENDER_DEDUCTIONS");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("DEDCODE", deductionType),
					Integer.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
}
