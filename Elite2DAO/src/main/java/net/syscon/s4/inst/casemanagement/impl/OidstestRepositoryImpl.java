package net.syscon.s4.inst.casemanagement.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.OidstestRepository;
import net.syscon.s4.inst.casemanagement.beans.OffenderSampleSubstances;
import net.syscon.s4.inst.casemanagement.beans.OffenderSamples;
import net.syscon.s4.inst.property.bean.Corporate;
/**
 * Class OidstestRepositoryImpl
 */
@Repository
public class OidstestRepositoryImpl extends RepositoryBase implements OidstestRepository{
	
	private static Logger logger = LogManager.getLogger(OidstestRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEST_STAFF_ID",               new FieldMapper("testStaffId"))
			.put("CORPORATE_NAME",              new FieldMapper("corporateName"))
			.put("TEST_CORPORATE_ID",           new FieldMapper("testCorporateId"))
			.put("LAST_NAME||",                 new FieldMapper("lastName||"))
			.put("FIRST_NAME",                  new FieldMapper("firstName"))
			.put("STAFF_ID",                    new FieldMapper("staffId"))
			.build();
	
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PARENT_CODE",                new FieldMapper("parentCode"))
			.put("OFFENDER_SAMPLE_ID",         new FieldMapper("offenderSampleId"))
			.build();
	
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",               new FieldMapper("profileCode"))
			.put("PROFILE_TYPE",               new FieldMapper("profileType"))
			.put("CREATE_USER_ID",             new FieldMapper("createUserId"))
			.put("SEAL_FLAG",                  new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",            new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID",             new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME",             new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE",              new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME",            new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2",            new FieldMapper("profileValue2"))
			.put("DESCRIPTION",                new FieldMapper("description"))
			.build();
	
	
	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEST_STAFF_ID",            new FieldMapper("testStaffId"))
			.put("CORPORATE_NAME",           new FieldMapper("corporateName"))
			.put("TEST_CORPORATE_ID",        new FieldMapper("testCorporateId"))
			.put("CORPORATE_ID",             new FieldMapper("corporateId"))
			.put("LAST_NAME||",              new FieldMapper("lastName||"))
			.put("FIRST_NAME",               new FieldMapper("firstName"))
			.build();
	
	private final Map<String, FieldMapper> offenderSampleSubstancesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DATE_TAKEN",               new FieldMapper("offenderSampleSubstanceId"))
			.put("TEST_TYPE",                new FieldMapper("offenderSampleId"))
			.put("TEST_REASON",              new FieldMapper("substanceCode"))
			.put("SUBSTANCE",                new FieldMapper("resultCode"))
			.put("TAKEN_BY",                 new FieldMapper("dispositionCode"))
			.put("COMMENT_TEXT",             new FieldMapper("commentText"))
			.put("CREATE_DATETIME",          new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME",          new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",           new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG",                new FieldMapper("sealFlag"))
			.put("CREATE_USER_ID",           new FieldMapper("createUserId"))
			.build();
	
	private final Map<String, FieldMapper> offenderSamplesMapping = new ImmutableMap.Builder<String, FieldMapper>()

			.build();

	/**
	 * Creates new OidstestRepositoryImpl class Object
	 */
	public OidstestRepositoryImpl() {
		// OidstestRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSamples
	 *
	 * @return List<OffenderSamples>
	 *
	 * @
	 */
	public List<OffenderSamples> subSamplExecuteQuery(final OffenderSamples objSearchDao) {
		final String sql = getQuery("OIDSTEST_SUBSAMPL_FIND_OFFENDER_SAMPLES");
		final RowMapper<OffenderSamples> offSamMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSamples.class, offenderSamplesMapping);
		final ArrayList<OffenderSamples> returnList = (ArrayList<OffenderSamples>) namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId()), offSamMapper);
		return returnList;
	}


	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderSamples
	 *            List<OffenderSamples>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offSamplInsertOffenderSamples(final List<OffenderSamples> list) {
		final String sql = getQuery("OIDSTEST_SUBSAMPL_INSERT_OFFENDER_SAMPLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSamples beanObj : list) {
			parameters.add(new BeanPropertySqlParameterSource(beanObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderSamples
	 *            List<OffenderSamples>
	 *
	 * @
	 */
	public Integer offSamplUpdateOffenderSamples(final List<OffenderSamples> lstOffenderSamples) {
		final String sql = getQuery("OIDSTEST_SUBSAMPL_UPDATE_OFFENDER_SAMPLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSamples offenderSamples : lstOffenderSamples) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSamples));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderSamples.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderSamples
	 *            List<OffenderSamples>
	 *
	 * @
	 */
	public Integer offSamplDeleteOffenderSamples(final List<OffenderSamples> list) {
		final String sql = getQuery("OIDSTEST_SUBSAMPL_DELETE_OFFENDER_SAMPLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSamples offenderSamples : list) {
			parameters.add(new BeanPropertySqlParameterSource(offenderSamples));
		}
		try {
			String tableName = "OFFENDER_SAMPLES";
			String whereClause = "OFFENDER_BOOK_ID =:offenderBookId and OFFENDER_SAMPLE_ID=:offenderSampleId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offSamplDeleteOffenderSamples", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSampleSubstances
	 *
	 * @return List<OffenderSampleSubstances>
	 *
	 * @
	 */
	public List<OffenderSampleSubstances> offenderSampleSubstancesExecuteQuery(
			final OffenderSampleSubstances objSearchDao) {
		final String sql = getQuery("OIDSTEST_SUBTEST_FIND_OFFENDER_SAMPLE_SUBSTANCES");
		final RowMapper<OffenderSampleSubstances> offsubSamMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSampleSubstances.class, offenderSampleSubstancesMapping);
		final ArrayList<OffenderSampleSubstances> returnList = (ArrayList<OffenderSampleSubstances>) namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDER_SAMPLE_ID", objSearchDao.getOffenderSampleId()),
						offsubSamMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderSampleSubstances
	 *            List<OffenderSampleSubstances>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer subTestInsertOffenderSampleSubstances(final List<OffenderSampleSubstances> list) {
		final String sql = getQuery("OIDSTEST_SUBTEST_INSERT_OFFENDER_SAMPLE_SUBSTANCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSampleSubstances obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderSampleSubstances
	 *            List<OffenderSampleSubstances>
	 *
	 * @
	 */
	public Integer subTestUpdateOffenderSampleSubstances(final List<OffenderSampleSubstances> list) {
		final String sql = getQuery("OIDSTEST_SUBTEST_UPDATE_OFFENDER_SAMPLE_SUBSTANCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSampleSubstances obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderSampleSubstances
	 *            List<OffenderSampleSubstances>
	 *
	 * @
	 */
	public Integer subTestDeleteOffenderSampleSubstances(final List<OffenderSampleSubstances> list) {
		final String sql = getQuery("OIDSTEST_SUBTEST_DELETE_OFFENDER_SAMPLE_SUBSTANCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderSampleSubstances obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			String tableName = "OFFENDER_SAMPLE_SUBSTANCES";
			String whereClause = "OFFENDER_SAMPLE_SUBSTANCE_ID =:offenderSampleSubstanceId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method subTestDeleteOffenderSampleSubstances", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDSTEST_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfMApper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), sysProfMApper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgWitnessRecordGroup(final String takenStaffId) {
		final String sql = getQuery("OIDSTEST_FIND_RGWITNESS");
		final RowMapper<StaffMembers> staffRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("TAKENSTAFFID", takenStaffId), staffRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSubTesRsltRecordGroup() {
		final String sql = getQuery("OIDSTEST_FIND_RGSUBTESRSLT");
		final RowMapper<ReferenceCodes> refRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
			return namedParameterJdbcTemplate.query(sql, createParams(), refRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSubTesDispRecordGroup() {
		final String sql = getQuery("OIDSTEST_FIND_RGSUBTESDISP");
		final RowMapper<ReferenceCodes> refRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
			return namedParameterJdbcTemplate.query(sql, createParams(), refRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSubTesTypeRecordGroup() {
		final String sql = getQuery("OIDSTEST_FIND_RGSUBTESTYPE");
		final RowMapper<ReferenceCodes> refRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
			return namedParameterJdbcTemplate.query(sql, createParams(), refRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSubTesRsnRecordGroup() {
		final String sql = getQuery("OIDSTEST_FIND_RGSUBTESRSN");
		final RowMapper<ReferenceCodes> refRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
			return namedParameterJdbcTemplate.query(sql, createParams(), refRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgTakenByRecordGroup(final String staffId) {
		final String sql = getQuery("OIDSTEST_FIND_RGTAKENBY");
		final RowMapper<StaffMembers> staffRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("STAFF_ID", staffId), staffRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgTestedByRecordGroup(final String extAgencyFlag) {
		final String sql = getQuery("OIDSTEST_FIND_RGTESTEDBY");
		final RowMapper<ReferenceCodes> staffRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				staffMembersMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("EXTAGENCY", extAgencyFlag), staffRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSubstanceRecordGroup() {
		final String sql = getQuery("OIDSTEST_FIND_RGSUBSTANCE");
		final RowMapper<ReferenceCodes> refRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
			return namedParameterJdbcTemplate.query(sql, createParams(), refRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * subSamplPostQuery
	 *
	 * @param params
	 *
	 */
	public StaffMembers subSamplPostQueryStaffId(final OffenderSamples paramBean) {
		final String sql = getQuery("OIDSTEST_SUB_SAMPL_POSTQUERY_STAFFID");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		StaffMembers returnObj = new StaffMembers();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TESTSTAFFID", paramBean.getTestStaffId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * subSamplPostQuery
	 *
	 * @param params
	 *
	 */
	public Corporate subSamplPostQueryCorporateId(final OffenderSamples paramBean) {
		final String sql = getQuery("OIDSTEST_SUB_SAMPL_POSTQUERY_CORPORATE_ID");
		final RowMapper<Corporate> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporate.class,
				corporatesMapping);
		Corporate returnObj = new Corporate();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TESTCORPORATEID", paramBean.getTestCorporateId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}


	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setPositiveFlag
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> setPositiveFlag(final OffenderSamples paramBean) {
		final String sql = getQuery("OIDSTEST_SET_POSITIVE_FLAG");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDERSAMPLEID", paramBean.getOffenderSampleId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * Method is used to get staffId from DB by using DB Function.
	 */
	public BigDecimal getStaffId() {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] {};
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_UTILS").withFunctionName("GET_STAFF_ID").declareParameters(sqlParameters);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
	}
	/**
	 * Method is used to get the count from DB .
	 */
	public Integer getTheCountOfOffenderSampleSubstances(final OffenderSamples paramBean) {
		final String sql = getQuery("OIDSTEST_SET_DATE_TESTED");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERSAMPLEID", paramBean.getOffenderSampleId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}
	
	public Integer setPositiveTestedFlag(final OffenderSamples paramBean) {
		final String sql = getQuery("OIDSTEST_SET_TESTED_POSITIVE_FLAG");		
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERSAMPLEID", paramBean.getOffenderSampleId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}
}
