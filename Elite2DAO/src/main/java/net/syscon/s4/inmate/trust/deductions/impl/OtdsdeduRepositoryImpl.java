package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.SuspendDeductionTypes;
import net.syscon.s4.im.beans.SuspendDeductions;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.deductions.OtdsdeduRepository;

/**
 * Class OtdsdeduRepositoryImpl
 */
@Repository
public class OtdsdeduRepositoryImpl extends RepositoryBase implements OtdsdeduRepository {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdsdeduRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> suspendDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("END_DATE", 				new FieldMapper("endDate"))
			.put("START_DATE", 				new FieldMapper("startDate"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("SUSPEND_DEDUCTION_ID", 	new FieldMapper("suspendDeductionId"))
			.build();
	private final Map<String, FieldMapper> suspendDeductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("SUSPEND_DEDUCTION_ID", 	new FieldMapper("suspendDeductionId"))
			.put("SUSPENDED_FLAG", 			new FieldMapper("suspendedFlag"))
			.build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.build();
	private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 					new FieldMapper("code"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("DEDUCTION_DESC", 			new FieldMapper("deductionDesc"))
			.put("CASELOAD_TYPE", 			new FieldMapper("caseloadType"))
			.put("MODE", 					new FieldMapper("mode"))
			.put("DEDUCTION_CATEGORY", 		new FieldMapper("deductionCategory")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DEDUCTION_TYPE", 			new FieldMapper("deductionType"))
			.put("DEDUCTION_DESC", 			new FieldMapper("deductionDesc"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.build();

	/**
	 * Creates new OtdsdeduRepositoryImpl class Object
	 */
	public OtdsdeduRepositoryImpl() {
		// OtdsdeduRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SuspendDeductions
	 *
	 * @return List<SuspendDeductions>
	 *
	 * 
	 */
	public List<SuspendDeductions> susDedExecuteQuery(final SuspendDeductions objSearchDao) {
		final String sql = getQuery("OTDSDEDU_SUSDED_FIND_SUSPEND_DEDUCTIONS");
		final RowMapper<SuspendDeductions> SuspendDeductionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SuspendDeductions.class, suspendDeductionsMapping);
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("caseloadId", objSearchDao.getCaseloadId());
		if (objSearchDao.getStartDate() != null) {
			param.addValue("startDate", new SimpleDateFormat("dd/MM/yyyy").format(objSearchDao.getStartDate()));
		} else {
			param.addValue("startDate", null);
		}
		if (objSearchDao.getEndDate() != null) {
			param.addValue("endDate", new SimpleDateFormat("dd/MM/yyyy").format(objSearchDao.getEndDate()));
		} else {
			param.addValue("endDate", null);
		}
		List<SuspendDeductions> returnList = namedParameterJdbcTemplate.query(sql, param, SuspendDeductionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSuspendDeductions
	 *            List<SuspendDeductions>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer susDedInsertSuspendDeductions(final List<SuspendDeductions> lstSuspendDeductions) {
		String sql = getQuery("OTDSDEDU_SUSDED_INSERT_SUSPEND_DEDUCTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SuspendDeductions suspendDeductions : lstSuspendDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(suspendDeductions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSuspendDeductions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSuspendDeductions
	 *            List<SuspendDeductions>
	 *
	 * 
	 */
	public Integer susDedUpdateSuspendDeductions(final List<SuspendDeductions> lstSuspendDeductions) {
		String sql = getQuery("OTDSDEDU_SUSDED_UPDATE_SUSPEND_DEDUCTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SuspendDeductions suspendDeductions : lstSuspendDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(suspendDeductions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSuspendDeductions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSuspendDeductions
	 *            List<SuspendDeductions>
	 *
	 * 
	 */
	public Integer susDedDeleteSuspendDeductions(final List<SuspendDeductions> lstSuspendDeductions) {
		String sql = getQuery("OTDSDEDU_SUSDED_DELETE_SUSPEND_DEDUCTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SuspendDeductions suspendDeductions : lstSuspendDeductions) {
			parameters.add(new BeanPropertySqlParameterSource(suspendDeductions));
		}
		try {
			String tableName = "SUSPEND_DEDUCTIONS";
			String whereClause = "SUSPEND_DEDUCTION_ID  = :suspendDeductionId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method susDedDeleteSuspendDeductions", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSuspendDeductions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SuspendDeductionTypes
	 *
	 * @return List<SuspendDeductionTypes>
	 *
	 * 
	 */
	public List<SuspendDeductionTypes> susDtExecuteQuery(final SuspendDeductionTypes objSearchDao) {
		final String sql = getQuery("OTDSDEDU_SUSDT_FIND_SUSPEND_DEDUCTION_TYPES");
		final RowMapper<SuspendDeductionTypes> SuspendDeductionTypesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SuspendDeductionTypes.class, suspendDeductionTypesMapping);
		List<SuspendDeductionTypes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("SUSPENDDEDUCTIONID", objSearchDao.getSuspendDeductionId()),
				SuspendDeductionTypesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSuspendDeductionTypes
	 *            List<SuspendDeductionTypes>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer susDtInsertSuspendDeductionTypes(final List<SuspendDeductionTypes> lstSuspendDeductionTypes) {
		String sql = getQuery("OTDSDEDU_SUSDT_INSERT_SUSPEND_DEDUCTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SuspendDeductionTypes suspendDeductionTypes : lstSuspendDeductionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(suspendDeductionTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSuspendDeductionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSuspendDeductionTypes
	 *            List<SuspendDeductionTypes>
	 *
	 * 
	 */
	public Integer susDtUpdateSuspendDeductionTypes(final List<SuspendDeductionTypes> lstSuspendDeductionTypes) {
		String sql = getQuery("OTDSDEDU_SUSDT_UPDATE_SUSPEND_DEDUCTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SuspendDeductionTypes suspendDeductionTypes : lstSuspendDeductionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(suspendDeductionTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSuspendDeductionTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSuspendDeductionTypes
	 *            List<SuspendDeductionTypes>
	 *
	 * 
	 */
	public Integer susDtDeleteSuspendDeductionTypes(final List<SuspendDeductionTypes> lstSuspendDeductionTypes) {
		String sql = getQuery("OTDSDEDU_SUSDT_DELETE_SUSPEND_DEDUCTION_TYPES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (SuspendDeductionTypes suspendDeductionTypes : lstSuspendDeductionTypes) {
			parameters.add(new BeanPropertySqlParameterSource(suspendDeductionTypes));
		}
		try {
			String tableName = "SUSPEND_DEDUCTION_TYPES";
			String whereClause = "SUSPEND_DEDUCTION_ID  = :suspendDeductionId AND DEDUCTION_TYPE = :deductionType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method susDtDeleteSuspendDeductionTypes", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSuspendDeductionTypes.size() == returnArray.length) {
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
	public List<Caseloads> cgfkSusDedCaseloadIdRecordGroup() {
		final String sql = getQuery("OTDSDEDU_FIND_CGFKSUSDEDCASELOADID");
		final RowMapper<Caseloads> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<DeductionTypes> cgfkSusDtDeductionTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTDSDEDU_FIND_CGFKSUSDTDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		List<DeductionTypes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADTYPE", caseloadType), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkSusDtDeductionTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkSusDedSusDedCasel
	 *
	 * @param params
	 *
	 */
	public Caseloads cgfkchkSusDedSusDedCasel(final Caseloads paramBean) {
		final String sql = getQuery("OTDSDEDU_CGFKCHK_SUS_DED_SUS_DED_CASEL");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		Caseloads returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkSusDtSusDtDedTyp
	 *
	 * @param params
	 *
	 */
	public DeductionTypes cgfkchkSusDtSusDtDedTyp(final DeductionTypes paramBean) {
		final String sql = getQuery("OTDSDEDU_CGFKCHK_SUS_DT_SUS_DT_DED_TYP");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		DeductionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 */
	public Long suspendDeductionsPreInsertc() {
		final String sql = getQuery("OTDSDEDU_SUS_DED_PREINSERT");
		Long returnval = null;
		returnval = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return returnval;
	}

	@Override
	public Integer deductionsUpdateDeductionStatus(Long pSusDedId, String pCaseloadId, String pSuspendFlag,
			String pDeductionType) {
		final String sql = getQuery("OTDSDEDU_DEDUCTIONS_UPDATE_DEDUCTION_STATUS");
		Map<String, Object> param = new HashMap<>();
		param.put("P_SUS_DED_ID", pSusDedId);
		param.put("P_CASELOAD_ID", pCaseloadId);
		param.put("P_SUSPEND_FLAG", pSuspendFlag);
		param.put("P_DEDUCTION_TYPE", pDeductionType);
		return namedParameterJdbcTemplate.update(sql, param);
	}

	@Override
	public Integer deductionsSuspendOffDeductions() {
		final String sql = getQuery("OTDSDEDU_DEDUCTIONS_SUSPEND_OFF_DEDUCTIONS");
		Map<String, Object> param = new HashMap<>();
		try {
			namedParameterJdbcTemplate.update(sql, param);
		} catch (Exception e) {
			return 0;
		}
		return 1; 
	}

	@Override
	public BigDecimal chkOverlapDate(String caseloadId, String startDate, String endDate, String flag) {
		final String sql = getQuery("OTDSDEDU_CHECK_OVERLAP_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("caseloadId", caseloadId, "startDate", startDate, "endDate", endDate, "flag", flag),
				BigDecimal.class);
	}

}
