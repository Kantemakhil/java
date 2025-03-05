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
import net.syscon.s4.im.beans.OffenderLimits;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inmate.trust.financialsmaintenance.OtmoflimRepository;

/**
 * Class OtmoflimRepositoryImpl
 */

@Repository
public class OtmoflimRepositoryImpl extends RepositoryBase implements OtmoflimRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	
	private static Logger logger = LogManager.getLogger(OtmoflimRepositoryImpl.class.getName());

	
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()

			.put("LIST_SEQ",               new FieldMapper("listSeq"))
			.put("DESCRIPTION",            new FieldMapper("description"))
			.put("LIMIT_TYPE",             new FieldMapper("limitType"))
			.build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE",                   new FieldMapper("code"))
			.put("DESCRIPTION",            new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE",           new FieldMapper("profileType"))
			.put("PROFILE_CODE",           new FieldMapper("profileCode"))
			.put("DESCRIPTION",            new FieldMapper("description"))
			.put("PROFILE_VALUE",          new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2",        new FieldMapper("profileValue2"))
			.put("MODIFY_USER_ID",         new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME",         new FieldMapper("oldTableName"))
			.put("CREATE_DATETIME",        new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",         new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",        new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG     ",         new FieldMapper("sealFlag"))
			.build();
	
	/**
	 * Creates new OtmoflimRepositoryImpl class Object
	 */
	
	public OtmoflimRepositoryImpl() {
		// OtmoflimRepositoryImpl
	}

	/**
	 * Fetch the records from database table method:offLimExecuteQuery
	 * 
	 * @param objSearchDao
	 * @return List<OffenderLimits>
	 */
	public List<OffenderLimits> offLimExecuteQuery(final OffenderLimits objSearchDao) {
		final String sql = getQuery("OTMOFLIM_OFFLIM_FIND_OFFENDER_LIMITS");
		final RowMapper<OffenderLimits> OffenderLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderLimits.class, referenceCodesMapping);
		List<OffenderLimits> returnList = new ArrayList<OffenderLimits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", objSearchDao.getCaseloadId(), "OFFENDERID", objSearchDao.getOffenderId()),
					OffenderLimitsRowMapper);
		} catch (Exception e) {
			logger.error("offLimExecuteQuery", e);
		}

		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 * method:offLimInsertOffenderLimits
	 * @param lstOffenderLimits
	 * @return Integer
	 */
	
	public Integer offLimInsertOffenderLimits(final List<OffenderLimits> lstOffenderLimits) {
		String sql = getQuery("OTMOFLIM_OFFLIM_INSERT_OFFENDER_LIMITS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderLimits offenderLimits : lstOffenderLimits) {
			parameters.add(new BeanPropertySqlParameterSource(offenderLimits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderLimits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * method:offLimUpdateOffenderLimits
	 * @param lstOffenderLimits
	 * @return Integer
	 */
	
	public Integer offLimUpdateOffenderLimits(final List<OffenderLimits> lstOffenderLimits) {
		String sql = getQuery("OTMOFLIM_OFFLIM_UPDATE_OFFENDER_LIMITS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderLimits offenderLimits : lstOffenderLimits) {
			parameters.add(new BeanPropertySqlParameterSource(offenderLimits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderLimits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * method:offLimDeleteOffenderLimits
	 * @param lstOffenderLimits
	 * @return Integer
	 */
	
	public Integer offLimDeleteOffenderLimits(final List<OffenderLimits> lstOffenderLimits) {
		String sql = getQuery("OTMOFLIM_OFFLIM_DELETE_OFFENDER_LIMITS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderLimits offenderLimits : lstOffenderLimits) {
			parameters.add(new BeanPropertySqlParameterSource(offenderLimits));
		}
		try {
			String tableName = "OFFENDER_LIMITS";
			String whereClause = "CASELOAD_ID =:caseloadId and OFFENDER_ID=:offenderId and LIMIT_TYPE=:limitType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offLimDeleteOffenderLimits", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderLimits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 * method:sysPflExecuteQuery
	 * @param objSearchDao
	 * @return List<SystemProfiles>
	 */
	
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTMOFLIM_SYSPFL_FIND_SYSTEM_PROFILES");	
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList =  new ArrayList<SystemProfiles>();
		try{
			returnList=namedParameterJdbcTemplate.query(sql,createParams(), SystemProfilesRowMapper);
		}catch(Exception e){
			logger.error("sysPflExecuteQuery",e);
			}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * method:cgfkOffLimLimitTypeRecordGroup
	 * @return List<ReferenceCodes>
	 */
	
	public List<ReferenceCodes> cgfkOffLimLimitTypeRecordGroup() {
		final String sql = getQuery("OTMOFLIM_FIND_CGFKOFFLIMLIMITTYPE");		
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
       try {
    	   returnList=namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkOffLimLimitTypeRecordGroup", e);

		}
		return returnList;
      }

	/**
	 * This method is execute a sql query when trigger event is called
	 * method:cgfkchkOffLimOffLimType
	 * @param params
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkchkOffLimOffLimType(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTMOFLIM_CGFKCHK_OFF_LIM_OFF_LIM_TYPE_");
	
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();		
		try{
			returnList=namedParameterJdbcTemplate.query(sql,createParams(), columnRowMapper);
		}catch(Exception e){
			logger.error("cgfkchkOffLimOffLimType",e);
		}
		return returnList;
	}
}