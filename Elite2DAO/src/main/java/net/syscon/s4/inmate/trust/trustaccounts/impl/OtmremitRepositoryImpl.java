package net.syscon.s4.inmate.trust.trustaccounts.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.Remitters;
import net.syscon.s4.inmate.trust.trustaccounts.OtmremitRepository;
/**
 * Class OtmremitRepositoryImpl
 */
@Repository
public class OtmremitRepositoryImpl extends RepositoryBase implements OtmremitRepository{

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmremitRepositoryImpl.class.getName());
	
private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("EXPIRED_DATE", 						new FieldMapper("expiredDate"))
.put("DOMAIN", 						new FieldMapper("domain"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("DSP_DESCRIPTION", 						new FieldMapper("dspDescription"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("LIST_SEQ", 						new FieldMapper("listSeq"))
.put("CODE", 						new FieldMapper("code"))
.put("PARENT_CODE", 						new FieldMapper("parentCode"))
.put("NEW_CODE", 						new FieldMapper("newCode"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("PROV_STATE_CODE", 						new FieldMapper("provStateCode"))
.put("DSP_DESCRIPTION3", 						new FieldMapper("dspDescription3"))
.put("COUNTRY_CODE", 						new FieldMapper("countryCode"))
.build();
private final Map<String, FieldMapper> remittersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("REMITTER_ID", 						new FieldMapper("remitterId"))
.put("HOME_PHONE", 						new FieldMapper("homePhone"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("ZIP_STATE_CODE", 						new FieldMapper("zipStateCode"))
.put("CITY", 						new FieldMapper("city"))
.put("MIDDLE_NAME", 						new FieldMapper("middleName"))
.put("BUS_PHONE", 						new FieldMapper("busPhone"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("ADDRESS", 						new FieldMapper("address"))
.put("PROV_STATE_CODE", 						new FieldMapper("provStateCode"))
.put("HOME_PHONE_AREA", 						new FieldMapper("homePhoneArea"))
.put("HOME_PHONE_EXT", 						new FieldMapper("homePhoneExt"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDateTime"))
.put("COUNTRY_CODE", 						new FieldMapper("countryCode"))
.put("BUS_PHONE_AREA", 						new FieldMapper("busPhoneArea"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDateTime"))
.put("BUS_PHONE_EXT", 						new FieldMapper("busPhoneExt"))
.put("'1'", 						new FieldMapper("  '1' "))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("PROFILE_VALUE_2", 						new FieldMapper("profileValue2"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LIST_SEQ", 						new FieldMapper("listSeq"))
.put("EXPIRED_DATE", 						new FieldMapper("expiredDate"))
.put("DOMAIN", 						new FieldMapper("domain"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("PARENT_CODE", 						new FieldMapper("parentCode"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("NEW_CODE", 						new FieldMapper("newCode"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.build();

	/**
	 * Creates new OtmremitRepositoryImpl class Object
	 */
	public OtmremitRepositoryImpl() {
		// OtmremitRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Remitters
	 *
	 * @return List<Remitters>
	 *
	 * @
	 */
	public List<Remitters> remExecuteQuery(final Remitters objSearchDao) {
		final String sql = getQuery("OTMREMIT_REM_FIND_REMITTERS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getLastName() != null) {
				sqlQuery.append("LAST_NAME  LIKE :lastName" + " and ");
				params.addValue("lastName", objSearchDao.getLastName() + "%");
			}
			if (objSearchDao.getFirstName() != null) {
				sqlQuery.append(" FIRST_NAME LIKE :firstName  " + " and ");
				params.addValue("firstName", objSearchDao.getFirstName() + "%");
			}
			if (objSearchDao.getMiddleName() != null) {
				sqlQuery.append(" MIDDLE_NAME LIKE :middleName  " + " and ");
				params.addValue("middleName", objSearchDao.getMiddleName() + "%");
			}
			
			if (objSearchDao.getRemitterId() != null) {
				sqlQuery.append(" REMITTER_ID = :remitterId  " + " and ");
				params.addValue("remitterId", objSearchDao.getRemitterId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY LAST_NAME,FIRST_NAME ,MIDDLE_NAME ,REMITTER_ID ";
		final RowMapper<Remitters> RemittersRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Remitters.class,
				remittersMapping);
		final ArrayList<Remitters> returnList = (ArrayList<Remitters>) namedParameterJdbcTemplate.query(preparedSql,
				params, RemittersRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstRemitters
	 *            List<Remitters>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer remInsertRemitters(final List<Remitters> lstRemitters) {
		int insertCount = 0;
		final String sql = getQuery("OTMREMIT_REM_INSERT_REMITTERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Remitters remitters : lstRemitters) {
			parameters.add(new BeanPropertySqlParameterSource(remitters));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method remInsertRemitters : ", e);
		}
		for (int i = 0; i < returnArray.length; i++) {
			insertCount += 1;
		}
		if (lstRemitters.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstRemitters
	 *            List<Remitters>
	 *
	 * @
	 */
	public Integer remUpdateRemitters(final List<Remitters> lstRemitters) {
		final String sql = getQuery("OTMREMIT_REM_UPDATE_REMITTERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Remitters remitters : lstRemitters) {
			parameters.add(new BeanPropertySqlParameterSource(remitters));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method remUpdateRemitters : ", e);
		}
		if (lstRemitters.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstRemitters
	 *            List<Remitters>
	 *
	 * @
	 */
	public Integer remDeleteRemitters(final List<Remitters> lstRemitters) {
		final String sql = getQuery("OTMREMIT_REM_DELETE_REMITTERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Remitters remitters : lstRemitters) {
			parameters.add(new BeanPropertySqlParameterSource(remitters));
		}
		try {
			String tableName = "REMITTERS";
			String whereClause = "REMITTER_ID  = :remitterId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method remDeleteRemitters", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method remUpdateRemitters : ", e);
		}
		if (lstRemitters.size() == returnArray.length) {
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
		final String sql = getQuery("OTMREMIT_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer sysPflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OTMREMIT_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method sysPflInsertSystemProfiles : ", e);
		}
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OTMREMIT_SYSPFL_DELETE_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		try {
			String tableName = "SYSTEM_PROFILES";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method sysPflDeleteSystemProfiles", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method sysPflDeleteSystemProfiles : ", e);
		}
		if (lstSystemProfiles.size() == returnArray.length) {
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
	public List<ReferenceCodes> cgfkRemDspDescriptionRecordGroup() {
		final String sql = getQuery("OTMREMIT_FIND_CGFKREM1DSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+"In method cgfkRemDspDescriptionRecordGroup: ",e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkRemitDspDescriptionRecordGroup() {
		final String sql = getQuery("OTMREMIT_FIND_CGFKREM1DSPDESCRIPTION3");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+"In method cgfkRemitDspDescriptionRecordGroup: ",e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkRem1RemRefCodeF3
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkRemRemRefCodeF(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTMREMIT_CGFKCHK_REM1_REM_REF_CODE_F3");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpRem1RemRefCodeF3
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpRemRemRefCodeF(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTMREMIT_CGFKLKP_REM1_REM_REF_CODE_F3");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkRem1RemRefCodeF2
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkRemRemRefCode(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTMREMIT_CGFKCHK_REM1_REM_REF_CODE_F2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpRem1RemRefCodeF2
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpRemRemRefCodes(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTMREMIT_CGFKLKP_REM1_REM_REF_CODE_F2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkRemitters
	 *
	 * @param params
	 *
	 */
	public Remitters cgrichkRemitters(final Remitters paramBean) {
		final String sql = getQuery("OTMREMIT_CGRICHK_REMITTERS");
		final RowMapper<Remitters> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Remitters.class,
				remittersMapping);
		final Remitters returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Remitters
	 *
	 * @return List<Remitters>
	 *
	 * @
	 */
	public Remitters remitExecuteQuery(final Remitters objSearchDao) {
		final String sql = getQuery("OTMREMIT_REM_FIND_REMITTERS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getRemitterId() != 0) {
				sqlQuery.append("REMITTER_ID  =:remitterId");
				params.addValue("remitterId", objSearchDao.getRemitterId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		preparedSql = preparedSql + " ORDER BY LAST_NAME,FIRST_NAME ,MIDDLE_NAME ,REMITTER_ID ";
		final RowMapper<Remitters> RemittersRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Remitters.class,
				remittersMapping);
		Remitters returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(preparedSql, params, RemittersRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method cgfkRemDspDescriptionRecordGroup : ", e);
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> getCodes() {
		final String sql = getQuery("OTMREMIT_FIND_GET_PROV_COUNTRY_CODES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName()+"In method getCodes: ",e);
			return Collections.emptyList();
		}
	}

}
