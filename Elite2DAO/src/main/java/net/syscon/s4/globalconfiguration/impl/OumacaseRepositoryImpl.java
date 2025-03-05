package net.syscon.s4.globalconfiguration.impl;

import java.sql.SQLException;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OumacaseRepository;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Class OumacaseRepositoryImpl
 */
@Repository
public class OumacaseRepositoryImpl extends RepositoryBase implements OumacaseRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumacaseRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("COMMISSARY_FLAG", 			new FieldMapper("commissaryFlag"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("TRUST_ACCOUNTS_FLAG", 		new FieldMapper("trustAccountsFlag"))
			.put("BILLING_FLAG", 				new FieldMapper("billingFlag"))
			.put("TRUST_CASELOAD_ID", 			new FieldMapper("trustCaseloadId"))
			.put("COMMISSARY_TRUST_CASELOAD", 	new FieldMapper("commissaryTrustCaseload"))
			.put("CASELOAD_TYPE", 				new FieldMapper("caseloadType"))
			.put("PAYROLL_TRUST_CASELOAD", 		new FieldMapper("payrollTrustCaseload"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("ACCESS_PROPERTY_FLAG", 		new FieldMapper("accessPropertyFlag"))
			.put("DEACTIVATION_DATE", 			new FieldMapper("deactivationDate"))
			.put("TRUST_COMMISSARY_CASELOAD", 	new FieldMapper("trustCommissaryCaseload"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("COMMUNITY_TRUST_CASELOAD", 	new FieldMapper("communityTrustCaseload"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("MDT_FLAG", 					new FieldMapper("mdtFlag"))
			.put("PAYROLL_FLAG", 				new FieldMapper("payrollFlag"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", 	new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> clAgyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 			new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", 			new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", 	new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID",  	new FieldMapper("modifyUserId"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", 	new FieldMapper("modifyDateTime"))
			.put("CASELOAD_ID", 		new FieldMapper("caseloadId"))
			.build();

	/**
	 * Creates new OumacaseRepositoryImpl class Object
	 */
	public OumacaseRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Caseloads
	 *
	 * @return List<Caseloads>
	 *
	 * @throws SQLException
	 */
	public List<Caseloads> executeQuery(final Caseloads objSearchDao) {
		final String sql = getQuery("OUMACASE_CSLD_FIND_CASELOADS");
		final RowMapper<Caseloads> clRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, caseloadsMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getCaseloadId() != null) {
				sqlQuery.append("CASELOAD_ID =  :caseLoadId " + " and ");
				valuesList.addValue("caseLoadId", objSearchDao.getCaseloadId());
			}

			if (objSearchDao.getDescription() != null) {
				sqlQuery.append("DESCRIPTION =  :description " + " and ");
				valuesList.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getCaseloadType() != null) {
				sqlQuery.append("CASELOAD_TYPE =  :caseloadType " + " and ");
				valuesList.addValue("caseloadType", objSearchDao.getCaseloadType());
			}

			if (objSearchDao.getDeactivationDate() != null) {
				sqlQuery.append("DEACTIVATION_DATE =  :deactivationDate " + " and ");
				valuesList.addValue("deactivationDate", objSearchDao.getDeactivationDate());
			}
			
			if (objSearchDao.getActiveFlag() != null) {
				sqlQuery.append("ACTIVE_FLAG =  :activeFlag " + " and ");
				valuesList.addValue("activeFlag", objSearchDao.getActiveFlag());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " WHERE ACCESS_PROPERTY_FLAG IS NOT NULL";
		List<Caseloads> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, clRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records newly created caseloads in the
	 * data base tables
	 *
	 * @param lstCaseloads
	 *            List<Caseloads>
	 *
	 * @return Integer
	 *
	 * @throws SQLException
	 */
	public Integer insertCaseloads(final List<Caseloads> lstCaseloads) {
		final String sql = getQuery("OUMACASE_CSLD_INSERT_CASELOADS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Caseloads caseloads : lstCaseloads) {
			parameters.add(createCaseLoads(caseloads));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloads.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to create new BeanPropertySqlParameterSource
	 * 
	 * @param caseloads
	 *            Caseloads
	 * 
	 * @return BeanPropertySqlParameterSource
	 */
	private BeanPropertySqlParameterSource createCaseLoads(final Caseloads caseloads) {
		return new BeanPropertySqlParameterSource(caseloads);
	}

	/**
	 * This method is used to create new BeanPropertySqlParameterSource
	 * 
	 * @param caseloads
	 *            CaseloadAgencyLocations
	 * 
	 * @return BeanPropertySqlParameterSource
	 */
	private BeanPropertySqlParameterSource createCaseLoadAgyLoc(final CaseloadAgencyLocations caseloadAgyLoc) {
		return new BeanPropertySqlParameterSource(caseloadAgyLoc);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloads
	 *            List<Caseloads>
	 *
	 * @throws SQLException
	 */
	public Integer updateCaseloads(final List<Caseloads> lstCaseloads) {
		int[] returnArray = new int[] {};
		Integer returnVal = 0;
		try {
		final String sql = getQuery("OUMACASE_CSLD_UPDATE_CASELOADS");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Caseloads caseloads : lstCaseloads) {
			parameters.add(createCaseLoads(caseloads));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCaseloads.size() == returnArray.length) {
			returnVal = 1;
		} else {
			returnVal = 0;
		}
		} catch(Exception e) {
			e.printStackTrace();
			returnVal = 0;
		}
		return returnVal;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadAgencyLocations
	 *
	 * @return List<CaseloadAgencyLocations>
	 *
	 * @throws SQLException
	 */
	public List<CaseloadAgencyLocations> alExecuteQuery(final CaseloadAgencyLocations objSearchDao) {
		final String sql = getQuery("OUMACASE_CSLDAL_FIND_CASELOAD_AGENCY_LOCATIONS");

		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getCaseloadId() != null) {
				sqlQuery.append("CASELOAD_ID =  :caseLoadId " + " and ");
				valuesList.addValue("caseLoadId", objSearchDao.getCaseloadId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<CaseloadAgencyLocations> clAgyLocRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, clAgyLocMapping);
		final ArrayList<CaseloadAgencyLocations> returnList = (ArrayList<CaseloadAgencyLocations>) namedParameterJdbcTemplate
				.query(preparedSql, valuesList, clAgyLocRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstClAgencyLocs
	 *            List<CaseloadAgencyLocations>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer insertCaseloadAgencyLocations(final List<CaseloadAgencyLocations> lstClAgencyLocs) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("OUMACASE_CSLDAL_INSERT_CASELOAD_AGENCY_LOCATIONS");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadAgencyLocations clAgyLocations : lstClAgencyLocs) {
			parameters.add(createCaseLoadAgyLoc(clAgyLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstClAgencyLocs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the CaseloadAgencyLocations
	 *
	 * @param lstClAgencyLocs
	 *            List<CaseloadAgencyLocations>
	 * 
	 * @return Integer
	 *
	 * @throws SQLException
	 */
	public Integer updateCaseloadAgencyLocations(final List<CaseloadAgencyLocations> lstClAgencyLocs) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("OUMACASE_CSLDAL_UPDATE_CASELOAD_AGENCY_LOCATIONS");
		int returnValue = 0;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadAgencyLocations clAgencyLocations : lstClAgencyLocs) {
			parameters.add(createCaseLoadAgyLoc(clAgencyLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstClAgencyLocs.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstClAgencyLocs
	 *            List<CaseloadAgencyLocations>
	 * 
	 * @return Integer
	 *
	 * @throws SQLException
	 */
	public Integer deleteCaseloadAgencyLocations(final List<CaseloadAgencyLocations> lstClAgencyLocs) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("OUMACASE_CSLDAL_DELETE_CASELOAD_AGENCY_LOCATIONS");
		int returnValue = 0;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadAgencyLocations clAgencyLocations : lstClAgencyLocs) {
			parameters.add(createCaseLoadAgyLoc(clAgencyLocations));
		}
		try {
			String tableName = "CASELOAD_AGENCY_LOCATIONS";
			String whereClause = "CASELOAD_ID  = :caseloadId and AGY_LOC_ID = :agyLocId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteCaseloadAgencyLocations", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstClAgencyLocs.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> payrollTrustRgRecordGroup() {
		final String sql = getQuery("OUMACASE_FIND_PAYROLLTRUSTRG");
		final RowMapper<Caseloads> clRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, caseloadsMapping);
		List<Caseloads> lstCaseLoads = new ArrayList<Caseloads>();
		try {
			lstCaseLoads = (List<Caseloads>) namedParameterJdbcTemplate.query(sql, createParams(), clRowMapper);
		} catch (Exception e) {
			logger.error("In payrollTrustRgRecordGroup method : ", e);
		}
		return lstCaseLoads;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> commissaryTrustRgRecordGroup() {
		final String sql = getQuery("OUMACASE_FIND_COMMISSARYTRUSTRG");
		final RowMapper<Caseloads> clRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, caseloadsMapping);
		List<Caseloads> lstCaseLoads = new ArrayList<Caseloads>();
		try {
			lstCaseLoads = (List<Caseloads>) namedParameterJdbcTemplate.query(sql, createParams(), clRowMapper);
		} catch (Exception e) {
			logger.error("In commissaryTrustRgRecordGroup method : ", e);
		}
		return lstCaseLoads;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> trustCommissaryRgRecordGroup() {
		final String sql = getQuery("OUMACASE_FIND_TRUSTCOMMISSARYRG");
		final RowMapper<Caseloads> clRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, caseloadsMapping);
		List<Caseloads> lstCaseLoads = new ArrayList<Caseloads>();
		try {
			lstCaseLoads = (List<Caseloads>) namedParameterJdbcTemplate.query(sql, createParams(), clRowMapper);
		} catch (Exception e) {
			logger.error("In trustCommissaryRgRecordGroup method : ", e);
		}
		return lstCaseLoads;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Caseloads>
	 */
	public List<Caseloads> communityTrustRgRecordGroup() {
		final String sql = getQuery("OUMACASE_FIND_COMMUNITYTRUSTRG");
		final RowMapper<Caseloads> clRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, caseloadsMapping);
		List<Caseloads> lstCaseLoads = new ArrayList<Caseloads>();
		try {
			lstCaseLoads = (List<Caseloads>) namedParameterJdbcTemplate.query(sql, createParams(), clRowMapper);
		} catch (Exception e) {
			logger.error("In communityTrustRgRecordGroup method : ", e);
		}
		return lstCaseLoads;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> typeRgRecordGroup() {
		final String sql = getQuery("OUMACASE_FIND_CASELOADTYPERG");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(),
					refCodesRowMapper);
		} catch (Exception e) {
			logger.error("In typeRgRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> alAgyLocIdRgRecordGroup() {
		final String sql = getQuery("OUMACASE_FIND_ALAGYLOCIDRG");
		List<AgencyLocations> returnList = new ArrayList<>();
		final List<AgencyLocations> lstAgyRecords = new ArrayList<>();
		final RowMapper<AgencyLocations> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		try {
			returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql, createParams(),
					agyLocRowMapper);

			for (final AgencyLocations objAgencyLoc : returnList) {
				objAgencyLoc.setCode(objAgencyLoc.getAgyLocId());
				lstAgyRecords.add(objAgencyLoc);
			}
		} catch (Exception e) {
			logger.error("In alAgyLocIdRgRecordGroup method : ", e);
		}
		return lstAgyRecords;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * csldOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<CaseloadAgencyLocations> onCheckDeleteMaster(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OUMACASE_CSLD_ONCHECKDELETEMASTER");
		List<CaseloadAgencyLocations> returnObj = new ArrayList<CaseloadAgencyLocations>();
		try {
			returnObj = namedParameterJdbcTemplate.queryForList(sql,
					createParams("caseloadId", paramBean.getCaseloadId(), "agyLocId", paramBean.getAgyLocId()),
					CaseloadAgencyLocations.class);
		} catch (Exception e) {
			logger.error("In onCheckDeleteMaster method : ", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkAgency
	 *
	 * @param params
	 *
	 */
	public List<Object> checkAgency(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OUMACASE_CHECK_AGENCY");
		List<Object> lstCaseLoads = new ArrayList<Object>();
		try {
			lstCaseLoads = namedParameterJdbcTemplate.queryForList(sql,
					createParams("caseloadId", paramBean.getCaseloadId(), "agyLocId", paramBean.getAgyLocId()),
					Object.class);
		} catch (Exception e) {
			logger.error("In checkAgency method : ", e);
		}
		return lstCaseLoads;
	}


	@Override
	public Integer checkCaseloadSeqExistorNot(String seqName) {
		final String sql = getQuery("OUMACASE_CASELOAD_SEQ_EXISTSORNOT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("SEQUENCE_NAME", seqName), Integer.class);
	}

	@Override
	public Integer generateCaseloadSeq(String seqname) {
		final String createSeqSql = getQuery("OUMACASE_DYNAMIC_CREATE_SEQ").replace("%seqname%", seqname);
		
		try {
			jdbcTemplate.execute(createSeqSql);
		}
		catch(Exception e) {
			return 0;
		}
			return 1;
	}

}
