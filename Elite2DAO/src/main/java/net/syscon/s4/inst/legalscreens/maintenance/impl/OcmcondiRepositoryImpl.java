package net.syscon.s4.inst.legalscreens.maintenance.impl;


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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legalscreens.maintenance.OcmcondiRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;
/**
 * Class OcmcondiRepositoryImpl
 */
@Repository
public class OcmcondiRepositoryImpl extends RepositoryBase implements OcmcondiRepository{
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmcondiRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> communityConditionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CONDITION_TEXT", 						new FieldMapper("conditionText"))
			.put("ALLOCATION_FLAG", 						new FieldMapper("allocationFlag"))
			.put("CSO_FLAG", 						new FieldMapper("csoFlag"))
			.put("CONDITION_UNIT_TYPE", 						new FieldMapper("conditionUnitType"))
			.put("SUBSTANCE_FLAG", 						new FieldMapper("substanceFlag"))
			.put("FINANCIAL_FLAG", 						new FieldMapper("financialFlag"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
			.put("PROVISO_FLAG", 						new FieldMapper("provisoFlag"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("PROGRAM_METHOD", 						new FieldMapper("programMethod"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("CASE_PLAN_FLAG", 						new FieldMapper("casePlanFlag"))
			.put("PROGRAM_FLAG", 						new FieldMapper("programFlag"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("MAXIMUM_AMOUNT", 						new FieldMapper("maximumAmount"))
			.put("DUE_DATE_REQUIRED_FLAG", 						new FieldMapper("dueDateRequiredFlag"))
			.put("CATEGORY_TYPE", 						new FieldMapper("categoryType"))
			.put("COMM_CONDITION_TYPE", 						new FieldMapper("commConditionType"))
			.put("CREATION_USER", 						new FieldMapper("creationUser"))
			.put("COMM_CONDITION_CODE", 						new FieldMapper("commConditionCode"))
			.put("UPDATE_ALLOWED_FLAG", 						new FieldMapper("updateAllowedFlag"))
			.put("AMOUNT_REQUIRED_FLAG", 						new FieldMapper("amountRequiredFlag"))
			.put("FUNCTION_TYPE", 						new FieldMapper("functionType"))
			.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
			.put("CREATION_DATE", 						new FieldMapper("creationDate"))
			.put("PROGRAM_UNITS", 						new FieldMapper("programUnits"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description          "))
			.put("CODE", 						new FieldMapper("code "))
			.build();

	private final Map<String, FieldMapper> listForparentMaqpping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TABLE_NAME", 						new FieldMapper("tableName"))
			.put("COLUMN_NAME", 						new FieldMapper("columnName"))
			.build();
	/**
	 * Creates new OcmcondiRepositoryImpl class Object 
	 */
	public OcmcondiRepositoryImpl() {
		
	}
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CommunityConditions
	 *
	 * @return List<CommunityConditions>
	 *
	 * @throws SQLException
	 */
	public List<CommunityConditions> comCondExecuteQuery(final CommunityConditions objSearchDao)  {
		final String sql = getQuery("OCMCONDI_COMCOND_FIND_COMMUNITY_CONDITIONS");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getCommConditionType() != null && !objSearchDao.getCommConditionType().isEmpty()) {
				pSql.append(" COMM_CONDITION_TYPE LIKE :commConditionType  AND " );
				param.addValue("commConditionType", objSearchDao.getCommConditionType());
			}
			if (objSearchDao.getCommConditionCode() != null && !objSearchDao.getCommConditionCode().isEmpty() && !objSearchDao.getCommConditionCode().trim().equals("")) {
				pSql.append(" COMM_CONDITION_CODE LIKE :commConditionCode  AND " );
				param.addValue("commConditionCode", objSearchDao.getCommConditionCode().trim());
			}

			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty() && !objSearchDao.getDescription().trim().equals("")) {
				pSql.append(" DESCRIPTION LIKE :description AND ");
				param.addValue("description", objSearchDao.getDescription().trim());
			}

			if (objSearchDao.getCategoryType() != null && !objSearchDao.getCategoryType().isEmpty()) {
				pSql.append("CATEGORY_TYPE = :categoryType AND ");
				param.addValue("categoryType", objSearchDao.getCategoryType());
			}

			if (objSearchDao.getConditionUnitType() != null && !objSearchDao.getConditionUnitType().isEmpty()) {
				pSql.append(" CONDITION_UNIT_TYPE LIKE :conditionUnitType AND ");
				param.addValue("conditionUnitType", objSearchDao.getConditionUnitType());
			}
			if (objSearchDao.getFunctionType() != null && !objSearchDao.getFunctionType().isEmpty()) {
				pSql.append("FUNCTION_TYPE = :functionType AND ");
				param.addValue("functionType", objSearchDao.getFunctionType());
			}

			if (objSearchDao.getProgramMethod() != null && !objSearchDao.getProgramMethod().isEmpty()) {
				pSql.append(" PROGRAM_METHOD LIKE :programMethod AND ");
				param.addValue("programMethod", objSearchDao.getProgramMethod());
			}
			if (objSearchDao.getAllocationFlag() != null && !objSearchDao.getAllocationFlag().isEmpty()) {
				if ("true".equals(objSearchDao.getAllocationFlag())) {
					objSearchDao.setAllocationFlag("Y");
				} else {
					objSearchDao.setAllocationFlag("N");
				}
				pSql.append(" ALLOCATION_FLAG LIKE :allocationFlag AND ");
				param.addValue("allocationFlag", objSearchDao.getAllocationFlag());
			}
			if (objSearchDao.getCasePlanFlag() != null && !objSearchDao.getCasePlanFlag().isEmpty()) {
				if ("true".equals(objSearchDao.getCasePlanFlag())) {
					objSearchDao.setCasePlanFlag("Y");
				} else {
					objSearchDao.setCasePlanFlag("N");
				}
				pSql.append(" CASE_PLAN_FLAG LIKE :casePlanFlag AND ");
				param.addValue("casePlanFlag", objSearchDao.getCasePlanFlag());
			}

			if (objSearchDao.getActiveFlag() != null && !objSearchDao.getActiveFlag().isEmpty()) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				pSql.append(" ACTIVE_FLAG LIKE :activeFlag AND ");
				param.addValue("activeFlag", objSearchDao.getActiveFlag());
			}

			if (objSearchDao.getListSeq() != null) {
				pSql.append(" LIST_SEQ ::TEXT LIKE (:listSeq::TEXT) AND  ");
				param.addValue("listSeq", objSearchDao.getListSeq());
			}

			if (objSearchDao.getExpiryDate() != null ) {
				pSql.append("EXPIRY_DATE = :expiryDate AND ");
				param.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by LIST_SEQ, COMM_CONDITION_TYPE,COMM_CONDITION_CODE ");
		final RowMapper<CommunityConditions> LegalUpdateReasonsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, CommunityConditions.class,communityConditionsMapping);
		List<CommunityConditions> returnList =new ArrayList<>();
		try {
			returnList=namedParameterJdbcTemplate.query(preparedSql, param, LegalUpdateReasonsRowMapper);
		}catch (Exception e) {
			logger.error("comCondExecuteQuery",e);
		}
		return returnList;
	}
	@Override
	public List<CommunityConditions> comCondFilteredData(final OffenderSentConditions objSearchDao)  {
		final String sql = getQuery("OCMCONDI_COMCOND_FILTERED_CONDS");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		preparedSql = pSql.toString().trim();
		param.addValue("offenderBookId", objSearchDao.getOffenderBookId());
		param.addValue("commConditionType", objSearchDao.getCommConditionType());
		param.addValue("sentenceSeq", objSearchDao.getSentenceSeq());
		param.addValue("categoryType", objSearchDao.getCategoryType());
		preparedSql = preparedSql.concat(" order by LIST_SEQ, COMM_CONDITION_TYPE,COMM_CONDITION_CODE ");
		final RowMapper<CommunityConditions> LegalUpdateReasonsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, CommunityConditions.class,communityConditionsMapping);
		List<CommunityConditions> returnList =new ArrayList<>();
		try {
			returnList=namedParameterJdbcTemplate.query(preparedSql, param, LegalUpdateReasonsRowMapper);
		}catch (Exception e) {
			logger.error("comCondFilteredData :: ",e);
		}
		return returnList;
	}
	

	/**
	 *  This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCommunityConditions List<CommunityConditions>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer comcondInsertCommunityConditions(final List<CommunityConditions> lstCommunityConditions)  {
		final String sql = getQuery("OCMCONDI_COMCOND_INSERT_COMMUNITY_CONDITIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CommunityConditions communityConditions : lstCommunityConditions) {
			parameters.add(new BeanPropertySqlParameterSource(communityConditions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstCommunityConditions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCommunityConditions List<CommunityConditions>
	 *
	 * @throws SQLException
	 */
	public Integer comCondUpdateCommunityConditions(final List<CommunityConditions> lstCommunityConditions)  {
		final String sql = getQuery("OCMCONDI_COMCOND_UPDATE_COMMUNITY_CONDITIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CommunityConditions communityConditions : lstCommunityConditions) {
			parameters.add(new BeanPropertySqlParameterSource(communityConditions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCommunityConditions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from  data base tables based on
	 *
	 * @param lstCommunityConditions List<CommunityConditions>
	 *
	 * @throws SQLException
	 */
	public CommunityConditions comCondDeleteCommunityConditions(final List<CommunityConditions> lstCommunityConditions)  {
		final String sql = getQuery("OCMCONDI_COMCOND_DELETE_COMMUNITY_CONDITIONS");
		int[] returnArray = new int[] {};
		final CommunityConditions returnData=new CommunityConditions();
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CommunityConditions communityConditions : lstCommunityConditions) {
			parameters.add(new BeanPropertySqlParameterSource(communityConditions));
		}
		try {
			batchUpdatePreDeletedRows("COMMUNITY_CONDITIONS", "COMM_CONDITION_TYPE  = :commConditionType AND COMM_CONDITION_CODE  = :commConditionCode AND CATEGORY_TYPE  = :categoryType", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in comCondDeleteCommunityConditions"+e);
		}
		
		try{
			
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
				final String tableName = errorNameValidation(error);
				returnData.setSealFlag(tableName);
				returnData.setServerCode(2292);
				return returnData;
			}
		}
		if (lstCommunityConditions.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);
			return returnData;
		}

	}
	
	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMCONDI_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			returnData = null;
			return returnData;
		}
		return returnData;
	}
	/**
	 * Used to capture results from select query
	 * @return List<M> 
	 */
	public List<ReferenceCodes> rgCatRecordGroup()  {
		final String sql = getQuery("OCMCONDI_FIND_RGCAT");
		final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgCatRecordGroup",e);
			return Collections.emptyList();  
		}
	}
	/**
	 * Used to capture results from select query
	 * @return List<MM> 
	 */
	public List<ReferenceCodes> rgTypeRecordGroup()  {
		final String sql = getQuery("OCMCONDI_FIND_RGTYPE");
		final RowMapper<ReferenceCodes>mMRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,createParams(),mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgTypeRecordGroup",e);
			return Collections.emptyList();  
		}
	}
	/**
	 * Used to capture results from select query
	 * @return List<M> 
	 */
	public List<ReferenceCodes> rgUnitRecordGroup()  {
		final String sql = getQuery("OCMCONDI_FIND_RGUNIT");
		final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgUnitRecordGroup",e);
			return Collections.emptyList();  
		}
	}
	/**
	 * Used to capture results from select query
	 * @return List<M> 
	 */
	public List<ReferenceCodes> rgSvcOblRecordGroup()  {
		final String sql = getQuery("OCMCONDI_FIND_RGSVCOBL");
		final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgSvcOblRecordGroup",e);
			return Collections.emptyList();  
		}
	}
	/**
	 * Used to capture results from select query
	 * @return List<M> 
	 */
	public List<ReferenceCodes> rgFunctionTypeRecordGroup()  {
		final String sql = getQuery("OCMCONDI_FIND_RGFUNCTIONTYPE");
		final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgFunctionTypeRecordGroup",e);
			return Collections.emptyList();  
		}
	}
	/**
	 * Used to capture List of  parent table details
	 * @return List<M> 
	 */
	public List<TableColumnNameBean> getTableColumNames()  {
		final String sql = getQuery("OCMCONDI_DELETEORNOT_TABLE_CONDITIONS");
		final RowMapper<TableColumnNameBean>mRowMapper = Row2BeanRowMapper.makeMapping(sql,TableColumnNameBean.class, listForparentMaqpping);

		try {
			return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getTableColumNames",e);
			return Collections.emptyList();  
		}
	}
	/**
	 * Used to capture delete the record or not from parent table
	 * @return List<M> 
	 */
	public Integer getDeleteRecordOrNot(final CommunityConditions communityCondition,final String tableName) {
		final String sql = "SELECT count(*) FROM ";
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (tableName != null) {
			sqlQuery.append(tableName);
			sqlQuery.append(" WHERE ");
			if (communityCondition.getCommConditionType() != null) {
				sqlQuery.append(" COMM_CONDITION_TYPE = :commConditionType " + " AND ");
				params.addValue("commConditionType", communityCondition.getCommConditionType());
			}
			if (communityCondition.getCommConditionCode() != null) {
				sqlQuery.append(" COMM_CONDITION_CODE = :commConditionCode " + " AND ");
				params.addValue("commConditionCode", communityCondition.getCommConditionCode());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(preparedSql, params, Integer.class);
		return returnList;
	}
}
